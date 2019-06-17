package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePatentQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePatentQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlinePatent;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlinePatentMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlinePatentService;
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
 * @Description :  SrpmsOutlinePatent服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlinePatentServiceImpl extends ServiceImpl<SrpmsOutlinePatentMapper, SrpmsOutlinePatent> implements ISrpmsOutlinePatentService {


    @Autowired
    private SrpmsOutlinePatentMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;


    /**
     * 查询专利service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlinePatentVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlinePatentQueryForm queryForm) {
        SrpmsOutlinePatentVoList dataVoList = new SrpmsOutlinePatentVoList();

        // 执行查询
        List<SrpmsOutlinePatentVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-专利service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlinePatentFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-专利删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-专利新增方法，返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-专利excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlinePatentVoList srpmsOutlineVoList = new SrpmsOutlinePatentVoList();
        List<SrpmsOutlinePatentVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlinePatentVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.PATENT_CAT, SrpmsConstant.AUTHORIZED_FLAG, SrpmsConstant.REGION});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 14) {
                vo = new SrpmsOutlinePatentVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setPatentCat(dictMap.get(SrpmsConstant.PATENT_CAT.concat(strings[6] == null ? "" : StringUtils.deleteWhitespace(strings[6]))));// 专利类别
                        vo.setAuthorizedFlag(dictMap.get(SrpmsConstant.AUTHORIZED_FLAG.concat(strings[7] == null ? "" : StringUtils.deleteWhitespace(strings[7]))));// 专利是否授权
                        vo.setAuthorizedRegion(dictMap.get(SrpmsConstant.REGION.concat(strings[11] == null ? "" : StringUtils.deleteWhitespace(strings[11]))));// 区域
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setApplicationNum(strings[3]);// 申请专利号
                    vo.setPatentNum(strings[4]);// 授权专利号
                    vo.setPatentName(strings[5]);// 专利名称
                    vo.setPatentCatName(strings[6]);// 专利类别名称
                    vo.setAuthorizedFlagName(strings[7]);// 专利是否授权字典名称
                    vo.setProposer(strings[8]);// 申请人
                    if (CommonUtil.isValidDate(strings[9])) {
                        vo.setApplicationTime(DateUtil.strToLocalDateTime(strings[9]));// 申请时间
                    }
                    if (CommonUtil.isValidDate(strings[10])) {
                        vo.setAuthorizedTime(DateUtil.strToLocalDateTime(strings[10]));// 授权时间
                    }
                    vo.setAuthorizedRegionName(strings[11]);// 区域字典名称
                    vo.setProCode(strings[12]);// 项目编号
                    vo.setProName(strings[13]);// 项目名称
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
     * 专利导出数据service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlinePatentVo> exportExcelQuery(SrpmsOutlinePatentQueryForm queryForm) {
        List<SrpmsOutlinePatentQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlinePatentVo> srpmsOutlineVoList = new ArrayList<>();// 保存集合

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.PATENT_CAT, SrpmsConstant.AUTHORIZED_FLAG, SrpmsConstant.REGION});

        SrpmsOutlinePatentVo srpmsOutlineVo;
        SrpmsOutlinePatentQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlinePatentVo();
            srpmsOutline = (SrpmsOutlinePatentQueryParam) iterator.next();
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
                if (StringUtils.isNotBlank(srpmsOutlineVo.getPatentCat())) {
                    srpmsOutlineVo.setPatentCatName(dictMap.get(SrpmsConstant.PATENT_CAT.concat(srpmsOutlineVo.getPatentCat())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getAuthorizedFlag())) {
                    srpmsOutlineVo.setAuthorizedFlagName(dictMap.get(SrpmsConstant.AUTHORIZED_FLAG.concat(srpmsOutlineVo.getAuthorizedFlag())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getAuthorizedRegion())) {
                    srpmsOutlineVo.setAuthorizedRegionName(dictMap.get(SrpmsConstant.REGION.concat(srpmsOutlineVo.getAuthorizedRegion())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-专利操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlinePatentForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlinePatentForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlinePatentForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-专利操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlinePatentForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlinePatentForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlinePatent srpmsOutline;
                SrpmsOutlinePatentForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlinePatent> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlinePatent> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlinePatent();
                    titleForm = (SrpmsOutlinePatentForm) e.next();

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

                    // 拼接条件重复验证 单位+期间+是否授权+专利号
                    key = new StringBuilder()
                            .append(orgId)
                            .append(year)
                            .append(month)
                            .append(titleForm.getAuthorizedFlag())
                            .append(StringUtils.deleteWhitespace(titleForm.getApplicationNum()))
                            .append(StringUtils.deleteWhitespace(titleForm.getPatentNum())).toString();

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
                    // 验证数据是否存在，存在数据返回错误数据，不存在执行新增操作
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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_05, orgId, year, month);

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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_05);
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
     * 验证专利数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlinePatentForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+期间+专利号)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("year", titleForm.getYear());
        paramMap.put("month", titleForm.getMonth());
        paramMap.put("authorizedFlag", titleForm.getAuthorizedFlag());
        paramMap.put("applicationNum", CommonUtil.deleteStringSpace(titleForm.getApplicationNum()));
        paramMap.put("patentNum", CommonUtil.deleteStringSpace(titleForm.getPatentNum()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlinePatentForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
        // 单位ID
        if (titleForm.getOrgId() == null || titleForm.getOrgId() == 0) {
            checkFlag = true;
            errorMsg.append("单位ID为空，");
        }
        // 年
        if (StringUtils.isBlank(titleForm.getYear())) {
            checkFlag = true;
            errorMsg.append("年为空，");
        }
        // 专利号
        if (StringUtils.isBlank(titleForm.getApplicationNum())) {
            checkFlag = true;
            errorMsg.append("申请专利号为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getApplicationNum(), 200)) {
            checkFlag = true;
            errorMsg.append("申请专利号超过设定长度，");
        }
        // 专利名称
        if (StringUtils.isBlank(titleForm.getPatentName())) {
            checkFlag = true;
            errorMsg.append("专利名称为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPatentName(), 200)) {
            checkFlag = true;
            errorMsg.append("专利名称超过设定长度，");
        }
        // 专利类别
        if (StringUtils.isBlank(titleForm.getPatentCat())) {
            checkFlag = true;
            errorMsg.append("专利类别为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPatentCat(), 200)) {
            checkFlag = true;
            errorMsg.append("专利类别超过设定长度，");
        }
        // 专利是否授权
        if (StringUtils.isBlank(titleForm.getAuthorizedFlag())) {
            checkFlag = true;
            errorMsg.append("专利是否授权为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getAuthorizedFlag(), 10)) {
            checkFlag = true;
            errorMsg.append("专利是否授权超过设定长度，");
        }
        // 申请人
        if (StringUtils.isBlank(titleForm.getProposer())) {
            checkFlag = true;
            errorMsg.append("申请人为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProposer(), 50)) {
            checkFlag = true;
            errorMsg.append("申请人超过设定长度，");
        }
        // 区域(国内、国外)
        if (StringUtils.isBlank(titleForm.getAuthorizedRegion())) {
            checkFlag = true;
            errorMsg.append("区域为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getAuthorizedRegion(), 200)) {
            checkFlag = true;
            errorMsg.append("区域超过设定长度，");
        }
        // 申请时间
        if (titleForm.getApplicationTime() == null) {
            checkFlag = true;
            errorMsg.append("申请时间为空，");
        }

        if (SrpmsConstant.AUTHORIZED_FLAG_YES.equals(titleForm.getAuthorizedFlag())) {
            // 授权专利号
            if (StringUtils.isBlank(titleForm.getPatentNum())) {
                checkFlag = true;
                errorMsg.append("授权专利号为空，");
            } else if (CommonUtil.maxLengthCheck(titleForm.getPatentNum(), 200)) {
                checkFlag = true;
                errorMsg.append("授权专利号超过设定长度，");
            }

            // 授权时间
            if(titleForm.getAuthorizedTime() == null) {
                checkFlag = true;
                errorMsg.append("专利是否授权为是的时候授权时间不能为空，");
            }

        }
        return checkFlag;
    }

}