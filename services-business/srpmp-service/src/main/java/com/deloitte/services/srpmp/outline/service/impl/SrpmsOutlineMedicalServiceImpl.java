package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineMedicalQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineMedicalQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineMedical;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineMedicalMapper;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlinePatentMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineMedicalService;
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
 * @Description :  SrpmsOutlineMedical服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineMedicalServiceImpl extends ServiceImpl<SrpmsOutlineMedicalMapper, SrpmsOutlineMedical> implements ISrpmsOutlineMedicalService {


    @Autowired
    private SrpmsOutlineMedicalMapper srpmsOutlineMapper;

    @Autowired
    private SrpmsOutlinePatentMapper srpmsOutlinePatentMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询新药器械service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineMedicalVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineMedicalQueryForm queryForm) {
        SrpmsOutlineMedicalVoList dataVoList = new SrpmsOutlineMedicalVoList();

        // 执行查询
        List<SrpmsOutlineMedicalVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-新药器械service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineMedicalFormList data, UserVo user) {

        SrpmsOutlineErrorList resultError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-新药器械删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
                deleteSrpmsOutline(data.getDeleteOutlineList());
            }
        }

        // 调用题录-新药器械新增方法,返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), resultError, user);
        }
        return resultError;
    }

    /**
     * 读取题录-新药器械excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineMedicalVoList srpmsOutlineVoList = new SrpmsOutlineMedicalVoList();
        List<SrpmsOutlineMedicalVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineMedicalVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.MEDICAL_CAT, SrpmsConstant.MEDICAL_TYPE, SrpmsConstant.MEDICAL_LEVEL});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 12) {
                vo = new SrpmsOutlineMedicalVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setMedicalCat(dictMap.get(SrpmsConstant.MEDICAL_CAT.concat(strings[4] == null ? "" : CommonUtil.deleteStringSpace(strings[4]))));// 类别
                        vo.setMedicalType(dictMap.get(SrpmsConstant.MEDICAL_TYPE.concat(strings[5] == null ? "" : CommonUtil.deleteStringSpace(strings[5]))));// 种类
                        vo.setMedicalLevel(dictMap.get(SrpmsConstant.MEDICAL_LEVEL.concat(strings[6] == null ? "" : CommonUtil.deleteStringSpace(strings[6]))));// 等级
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setMedicalName(strings[3]);// 新药/器械名称
                    vo.setMedicalCatName(strings[4]);// 类别名称
                    vo.setMedicalTypeName(strings[5]);// 种类名称
                    vo.setMedicalLevelName(strings[6]);// 等级名称
                    vo.setMedicalCertificateNum(strings[7]);// 新药/器械证书号
                    if (CommonUtil.isValidDate(strings[8])) {
                        vo.setApprovalTime(DateUtil.strToLocalDateTime(strings[8]));// 批准时间
                    }
                    vo.setProInCharge(strings[9]);// 项目负责人
                    vo.setProCode(strings[10]);// 项目编号
                    vo.setProName(strings[11]);// 项目名称
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
     * 新药器械导出service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlineMedicalVo> exportExcelQuery(SrpmsOutlineMedicalQueryForm queryForm) {
        List<SrpmsOutlineMedicalQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineMedicalVo> srpmsOutlineVoList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.MEDICAL_CAT, SrpmsConstant.MEDICAL_TYPE, SrpmsConstant.MEDICAL_LEVEL});

        SrpmsOutlineMedicalVo srpmsOutlineVo;
        SrpmsOutlineMedicalQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineMedicalVo();
            srpmsOutline = (SrpmsOutlineMedicalQueryParam) iterator.next();
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
                if (StringUtils.isNotBlank(srpmsOutlineVo.getMedicalCat())) {
                    srpmsOutlineVo.setMedicalCatName(dictMap.get(SrpmsConstant.MEDICAL_CAT.concat(srpmsOutlineVo.getMedicalCat())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getMedicalType())) {
                    srpmsOutlineVo.setMedicalTypeName(dictMap.get(SrpmsConstant.MEDICAL_TYPE.concat(srpmsOutlineVo.getMedicalType())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getMedicalLevel())) {
                    srpmsOutlineVo.setMedicalLevelName(dictMap.get(SrpmsConstant.MEDICAL_LEVEL.concat(srpmsOutlineVo.getMedicalLevel())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-新药器械操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineMedicalForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineMedicalForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineMedicalForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-新药器械操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineMedicalForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineMedicalForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineMedical srpmsOutline;
                SrpmsOutlineMedicalForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineMedical> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineMedical> listEditSuccess = new ArrayList<>();// 验证通过更新集合


                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineMedical();
                    titleForm = (SrpmsOutlineMedicalForm) e.next();

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

                    // 拼接条件重复验证 单位+新药/器械名+新药/器械证书号
                    key = new StringBuilder()
                            .append(orgId)
                            .append(CommonUtil.deleteStringSpace(titleForm.getMedicalName()))
                            .append(CommonUtil.deleteStringSpace(titleForm.getMedicalCertificateNum())).toString();

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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_06, orgId, year, month);
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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_06);
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
     * 验证新药器械数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineMedicalForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+新药/器械名+新药/器械证书号)
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("id", titleForm.getId());
//        paramMap.put("year", titleForm.getYear());
//        paramMap.put("month", titleForm.getMonth());
        paramMap.put("medicalName", CommonUtil.deleteStringSpace(titleForm.getMedicalName()));
        paramMap.put("medicalCertificateNum", CommonUtil.deleteStringSpace(titleForm.getMedicalCertificateNum()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineMedicalForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
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
        // 新药/器械名称
        if (StringUtils.isBlank(titleForm.getMedicalName())) {
            checkFlag = true;
            errorMsg.append("新药/器械名称为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getMedicalName(), 200)) {
            checkFlag = true;
            errorMsg.append("新药/器械名称超过设定长度,");
        }
        // 类别
        if (StringUtils.isBlank(titleForm.getMedicalCat())) {
            checkFlag = true;
            errorMsg.append("类别为空,");
        }
        // 等级
        if (StringUtils.isBlank(titleForm.getMedicalLevel())) {
            checkFlag = true;
            errorMsg.append("等级为空,");
        }
        // 新药/器械证书号
        if (StringUtils.isBlank(titleForm.getMedicalCertificateNum())) {
            checkFlag = true;
            errorMsg.append("新药/器械证书号为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getMedicalCertificateNum(), 200)) {
            checkFlag = true;
            errorMsg.append("新药/器械证书号超过设定长度,");
        }
        // 批准时间
        if (titleForm.getApprovalTime() == null) {
            checkFlag = true;
            errorMsg.append("批准时间为空,");
        }
        return checkFlag;
    }
}

