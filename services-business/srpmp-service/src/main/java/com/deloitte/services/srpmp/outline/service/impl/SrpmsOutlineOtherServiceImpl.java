package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineOtherQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineOtherQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineOther;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineOtherMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineOtherService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.outline.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :  SrpmsOutlineOther服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineOtherServiceImpl extends ServiceImpl<SrpmsOutlineOtherMapper, SrpmsOutlineOther> implements ISrpmsOutlineOtherService {


    @Autowired
    private SrpmsOutlineOtherMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询其他service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineOtherVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineOtherQueryForm queryForm) {

        SrpmsOutlineOtherVoList dataVoList = new SrpmsOutlineOtherVoList();

        // 执行查询
        List<SrpmsOutlineOtherVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-其他service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineOtherFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-其他删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-其他新增方法,返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-其他excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineOtherVoList srpmsOutlineVoList = new SrpmsOutlineOtherVoList();
        List<SrpmsOutlineOtherVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineOtherVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.SRPMS_BASE_CAT, SrpmsConstant.SRPMS_BASE_LEVEL});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 13) {
                vo = new SrpmsOutlineOtherVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setSrpmsBaseCat(dictMap.get(SrpmsConstant.SRPMS_BASE_CAT.concat(strings[3] == null ? "" : CommonUtil.deleteStringSpace(strings[3]))));// 科研基地类别
                        vo.setSrpmsBaseLevel(dictMap.get(SrpmsConstant.SRPMS_BASE_LEVEL.concat(strings[4] == null ? "" : CommonUtil.deleteStringSpace(strings[4]))));// 基地级别
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setSrpmsBaseCatName(strings[3]);// 科研基地类别名称
                    vo.setSrpmsBaseLevelName(strings[4]);// 基地级别名称
                    vo.setExpCenterName(strings[5]);// 实验室/中心名称
                    vo.setExpCenterDirector(strings[6]);// 实验室/中心主任
                    vo.setSupportOrg(strings[7]);// 依托单位
                    if (CommonUtil.isValidDate(strings[8])) {
                        vo.setApprovalTime(DateUtil.strToLocalDateTime(strings[8]));// 批准时间
                    }
                    vo.setApprovalNum(strings[9]);// 批准文号
                    vo.setBuiltTime(DateUtil.strToLocalDateTime(strings[10]));// 成立时间
                    vo.setDetailAddress(strings[11]);// 详细地址
                    vo.setRemarks(strings[12]);// 备注
                    voList.add(vo);
                }
            } else {
                return new Result(SrpmsErrorType.IMPORT_EXCEL_ERROR.getCode(), SrpmsErrorType.IMPORT_EXCEL_ERROR.getMsg(), null);
            }
        }
        srpmsOutlineVoList.setSrpmsOutlineVoList(voList);

        return Result.success(srpmsOutlineVoList);
    }

    @Override
    public List<SrpmsOutlineOtherVo> exportExcelQuery(SrpmsOutlineOtherQueryForm queryForm) {
        List<SrpmsOutlineOtherQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineOtherVo> srpmsOutlineVoList = new ArrayList<>();// 保存集合

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.SRPMS_BASE_CAT, SrpmsConstant.SRPMS_BASE_LEVEL});

        SrpmsOutlineOtherVo srpmsOutlineVo;
        SrpmsOutlineOtherQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineOtherVo();
            srpmsOutline = (SrpmsOutlineOtherQueryParam) iterator.next();
            BeanUtils.copyProperties(srpmsOutline, srpmsOutlineVo);

            // 查询单位名称
            if (!keyMap.containsKey(srpmsOutlineVo.getOrgId()) && srpmsOutlineVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(srpmsOutlineVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            } else {
                keyMap.put(srpmsOutlineVo.getOrgId(), orgName);
            }
            srpmsOutlineVo.setOrgName(orgName);

            if (dictMap != null) {
                if (StringUtils.isNotBlank(srpmsOutlineVo.getSrpmsBaseCat())) {
                    srpmsOutlineVo.setSrpmsBaseCatName(dictMap.get(SrpmsConstant.SRPMS_BASE_CAT.concat(srpmsOutlineVo.getSrpmsBaseCat())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getSrpmsBaseLevel())) {
                    srpmsOutlineVo.setSrpmsBaseLevelName(dictMap.get(SrpmsConstant.SRPMS_BASE_LEVEL.concat(srpmsOutlineVo.getSrpmsBaseLevel())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-其他操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineOtherForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineOtherForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineOtherForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-其他操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineOtherForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineOtherForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineOther srpmsOutline;
                SrpmsOutlineOtherForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineOther> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineOther> listEditSuccess = new ArrayList<>();// 验证通过更新集合


                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineOther();
                    titleForm = (SrpmsOutlineOtherForm) e.next();

                    Long orgId = titleForm.getOrgId();
                    String year = titleForm.getYear();
                    String month = titleForm.getMonth();

                    indexRow ++;
                    checkFlag = false;

                    checkFlag = checkSrpmsOutlineFields(titleForm, checkFlag, stringBuilder);

                    // 点击保存的时候验证数据
                    if (checkFlag) {
                        stringBefore.append("第");
                        stringBefore.append(indexRow);
                        stringBefore.append("条：");
                        stringBuilder.insert(0, stringBefore);
                        listError.add(titleForm);
                        continue;
                    }

                    // 拼接条件重复验证 单位+科研基地类别+实验室/中心名称+批准文号
                    key = new StringBuilder()
                            .append(orgId)
//                        .append(year)
//                        .append(month)
                            .append(titleForm.getSrpmsBaseCat())
                            .append(CommonUtil.deleteStringSpace(titleForm.getExpCenterName()))
                            .append(CommonUtil.deleteStringSpace(titleForm.getApprovalNum())).toString();

                    if (keyMap.containsKey(key)) {
                        stringBuilder.append("第");
                        stringBuilder.append(indexRow);
                        stringBuilder.append("条");
                        stringBuilder.append("保存数据与");
                        stringBuilder.append("第");
                        stringBuilder.append(keyMap.get(key));
                        stringBuilder.append("条");
                        stringBuilder.append("保存数据重复；");
                        listError.add(titleForm);
                        continue;
                    }
                    // 验证数据是否存在
                    if (getSrpmsOutlineExists(titleForm)) {
                        stringBuilder.append("第");
                        stringBuilder.append(indexRow);
                        stringBuilder.append("条");
                        stringBuilder.append("保存数据已存在；");
                        listError.add(titleForm);
                        continue;
                    }

                    BeanUtils.copyProperties(titleForm, srpmsOutline);

                    // 根据条件查询题录基础信息表
                    SrpmsOutlineBase srpmsOutlineBase =
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_11, orgId, year, month);
                    Long baseId;
                    if (srpmsOutlineBase != null) {
                        baseId = srpmsOutlineBase.getId();
                        srpmsOutlineBase.setOrgId(orgId);
                        srpmsOutlineBase.setYear(year);
                        srpmsOutlineBase.setMonth(month);
                        srpmsOutlineBaseService.saveOrUpdate(srpmsOutlineBase);
                    } else {
                        srpmsOutlineBase = new SrpmsOutlineBase();
                        // 执行新增题录基础表信息
                        srpmsOutlineBase.setOrgId(orgId);
                        srpmsOutlineBase.setYear(year);
                        srpmsOutlineBase.setMonth(month);
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_11);
                        // 执行保存操作
                        srpmsOutlineBaseService.save(srpmsOutlineBase);

                        baseId = srpmsOutlineBase.getId();
                    }
                    if (StringUtils.isBlank(titleForm.getId())) {

                        srpmsOutline.setBaseId(baseId);
                        srpmsOutline.setCreateBy(user.getId());
                        listAddSuccess.add(srpmsOutline);
                    } else {
                        srpmsOutline.setId(Long.valueOf(titleForm.getId()));
                        srpmsOutline.setBaseId(baseId);
                        srpmsOutline.setUpdateBy(user.getId());
                        listEditSuccess.add(srpmsOutline);
                    }
                    keyMap.put(key, indexRow);
                }
                // 错误信息返回
                if (!listError.isEmpty()) {
                    String errorMsg = stringBuilder.toString();
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
                    resultError.setErrorMsg(errorMsg);
                    resultError.setErrorFlag(true);
                    resultError.setListError(listError);
                } else {
                    if(!listAddSuccess.isEmpty()) {// 批量新增
                        this.saveBatch(listAddSuccess);
                    }
                    if(!listEditSuccess.isEmpty()) {// 批量更新
                        this.saveOrUpdateBatch(listEditSuccess);
                    }
                }
            } catch (Exception e) {
                log.error("保存操作出现异常"+e.getMessage());
                throw new BaseException(PlatformErrorType.ADD_ERROR);
            }
        }
        return resultError;
    }

    /**
     * 验证其他数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineOtherForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+科研基地类别+实验室/中心名称+批准文号)
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("id", titleForm.getId());
//        paramMap.put("year", year);
//        paramMap.put("month", month);
        paramMap.put("srpmsBaseCat", titleForm.getSrpmsBaseCat());
        paramMap.put("expCenterName", CommonUtil.deleteStringSpace(titleForm.getExpCenterName()));
        paramMap.put("approvalNum", CommonUtil.deleteStringSpace(titleForm.getApprovalNum()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineOtherForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
        // 单位ID
        if (titleForm.getOrgId() == null || titleForm.getOrgId() == 0) {
            checkFlag = true;
            errorMsg.append("单位ID为空,");
        }
        // 年
        if (StringUtils.isBlank(titleForm.getYear())) {
            checkFlag = true;
            errorMsg.append("年为空,");
        }
        // 科研基地类型
        if (StringUtils.isBlank(titleForm.getSrpmsBaseCat())) {
            checkFlag = true;
            errorMsg.append("科研基地类型为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getSrpmsBaseCat(), 200)) {
            checkFlag = true;
            errorMsg.append("科研基地类型超过设定长度,");
        }
        // 实验室/中心名称
        if (StringUtils.isBlank(titleForm.getExpCenterName())) {
            checkFlag = true;
            errorMsg.append("实验室/中心名称为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getExpCenterName(), 200)) {
            checkFlag = true;
            errorMsg.append("实验室/中心名称超过设定长度,");
        }
        // 实验室/中心主任
        if (StringUtils.isBlank(titleForm.getExpCenterDirector())) {
            checkFlag = true;
            errorMsg.append("实验室/中心主任为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getExpCenterDirector(), 200)) {
            checkFlag = true;
            errorMsg.append("实验室/中心主任超过设定长度,");
        }
        // 依托单位
        if (StringUtils.isBlank(titleForm.getSupportOrg())) {
            checkFlag = true;
            errorMsg.append("依托单位为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getSupportOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("依托单位超过设定长度,");
        }
        //  批准文号
        if (StringUtils.isBlank(titleForm.getApprovalNum())) {
            checkFlag = true;
            errorMsg.append("批准文号为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getApprovalNum(), 200)) {
            checkFlag = true;
            errorMsg.append("批准文号超过设定长度,");
        }
        // 批准时间
        if (titleForm.getApprovalTime() == null) {
            checkFlag = true;
            errorMsg.append("批准时间为空,");
        }
        // 成立时间
        if (titleForm.getBuiltTime() == null) {
            checkFlag = true;
            errorMsg.append("成立时间为空,");
        }
        return checkFlag;
    }

}

