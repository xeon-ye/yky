package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineCooperationQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineCooperationQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineCooperation;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineCooperationMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineCooperationService;
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
 * @Description :  SrpmsOutlineCooperation服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineCooperationServiceImpl extends ServiceImpl<SrpmsOutlineCooperationMapper, SrpmsOutlineCooperation> implements ISrpmsOutlineCooperationService {


    @Autowired
    private SrpmsOutlineCooperationMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询国合项目service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineCooperationVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineCooperationQueryForm queryForm) {
        SrpmsOutlineCooperationVoList dataVoList = new SrpmsOutlineCooperationVoList();

        // 执行查询
        List<SrpmsOutlineCooperationVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-国合项目service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineCooperationFormList data, UserVo user) {

        SrpmsOutlineErrorList resultError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-国合项目删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-国合项目新增方法,返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), resultError, user);
        }
        return resultError;
    }

    /**
     * 读取题录-国合项目excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineCooperationVoList srpmsOutlineVoList = new SrpmsOutlineCooperationVoList();
        List<SrpmsOutlineCooperationVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineCooperationVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.COOPERATION_CAT});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 11) {
                vo = new SrpmsOutlineCooperationVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setCooperationCat(dictMap.get(SrpmsConstant.COOPERATION_CAT.concat(strings[5] == null ? "" : CommonUtil.deleteStringSpace(strings[5]))));// 合作类别
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setProCode(strings[3]);// 项目编号
                    vo.setProName(strings[4]);// 项目名称
                    vo.setCooperationCatName(strings[5]);// 合作类别名称
                    vo.setCooperationOrg(strings[6]);// 合作单位
                    vo.setProInCharge(strings[7]);// 项目负责人
                    vo.setProOutlay(CommonUtil.getDoubleValue(strings[8]));// 项目经费（万元）
                    if (CommonUtil.isValidDate(strings[9])) {
                        vo.setProStartDate(DateUtil.strToLocalDateTime(strings[9]));// 项目开始时间
                    }
                    if (CommonUtil.isValidDate(strings[9])) {
                        vo.setProEndDate(DateUtil.strToLocalDateTime(strings[10]));// 项目结束时间
                    }
                    voList.add(vo);
                }
            } else {
                return new Result(SrpmsErrorType.IMPORT_EXCEL_ERROR.getCode(), SrpmsErrorType.IMPORT_EXCEL_ERROR.getMsg(), null);
            }
        }
        srpmsOutlineVoList.setSrpmsOutlineVoList(voList);

        return Result.success(srpmsOutlineVoList);
    }

    /**
     * 国合项目导出service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlineCooperationVo> exportExcelQuery(SrpmsOutlineCooperationQueryForm queryForm) {
        List<SrpmsOutlineCooperationQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineCooperationVo> srpmsOutlineVoList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.COOPERATION_CAT});

        SrpmsOutlineCooperationVo srpmsOutlineVo;
        SrpmsOutlineCooperationQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineCooperationVo();
            srpmsOutline = (SrpmsOutlineCooperationQueryParam) iterator.next();
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

            if (dictMap != null && StringUtils.isNotBlank(srpmsOutlineVo.getCooperationCat())) {
                srpmsOutlineVo.setCooperationCatName(dictMap.get(SrpmsConstant.COOPERATION_CAT.concat(srpmsOutlineVo.getCooperationCat())));
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-国合项目操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineCooperationForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineCooperationForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineCooperationForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-国合项目操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineCooperationForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineCooperationForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineCooperation srpmsOutline;
                SrpmsOutlineCooperationForm titleForm;
                Iterator iterator = dataList.iterator();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineCooperation> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineCooperation> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineCooperation();
                    titleForm = (SrpmsOutlineCooperationForm) e.next();

                    Long orgId = titleForm.getOrgId();
                    String year = titleForm.getYear();
                    String month = titleForm.getMonth();

                    indexRow++;
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

                    // 拼接条件重复验证 单位+项目编号
                    key = new StringBuilder()
                            .append(orgId)
                            .append(CommonUtil.deleteStringSpace(titleForm.getProCode())).toString();

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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_07, orgId, year, month);
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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_07);
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
                    if (!listAddSuccess.isEmpty()) {// 批量新增
                        this.saveBatch(listAddSuccess);
                    }
                    if (!listEditSuccess.isEmpty()) {// 批量更新
                        this.saveOrUpdateBatch(listEditSuccess);
                    }
                }
            } catch (Exception e) {
                log.error("保存操作出现异常" + e.getMessage());
                throw new BaseException(PlatformErrorType.ADD_ERROR);
            }
        }

        return resultError;
    }

    /**
     * 验证国合项目数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineCooperationForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+项目编号)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
//        paramMap.put("year", year);
//        paramMap.put("month", month);
        paramMap.put("proCode", CommonUtil.deleteStringSpace(titleForm.getProCode()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineCooperationForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
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
        // 项目编码
        if (StringUtils.isBlank(titleForm.getProCode())) {
            checkFlag = true;
            errorMsg.append("项目编号为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProCode(), 50)) {
            checkFlag = true;
            errorMsg.append("项目编号超过设定长度,");
        }
        // 项目名称
        if (StringUtils.isBlank(titleForm.getProName())) {
            checkFlag = true;
            errorMsg.append("项目名称为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProName(), 200)) {
            checkFlag = true;
            errorMsg.append("项目名称超过设定长度,");
        }
        // 合作类别
        if (StringUtils.isBlank(titleForm.getCooperationCat())) {
            checkFlag = true;
            errorMsg.append("合作类别为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCooperationCat(), 200)) {
            checkFlag = true;
            errorMsg.append("合作类别超过设定长度,");
        }
        // 合作单位
        if (StringUtils.isBlank(titleForm.getCooperationOrg())) {
            checkFlag = true;
            errorMsg.append("合作单位为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCooperationOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("合作单位超过设定长度,");
        }
        // 项目负责人
        if (StringUtils.isBlank(titleForm.getProInCharge())) {
            checkFlag = true;
            errorMsg.append("项目负责人为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProInCharge(), 50)) {
            checkFlag = true;
            errorMsg.append("项目负责人超过设定长度,");
        }

        // 项目开始时间
        if (titleForm.getProStartDate() == null) {
            checkFlag = true;
            errorMsg.append("项目开始时间为空,");
        }
        // 项目结束时间
        if (titleForm.getProEndDate() == null) {
            checkFlag = true;
            errorMsg.append("项目结束时间为空,");
        }

        // 项目经费(万元)
        if (titleForm.getProOutlay() == null) {
            checkFlag = true;
            errorMsg.append("项目经费为空,");
        }
        return checkFlag;
    }

}

