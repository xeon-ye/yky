package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultTransQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultTransQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineResultTrans;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineResultTransMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineResultTransService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
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
 * @Description :  SrpmsOutlineResultTrans服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineResultTransServiceImpl extends ServiceImpl<SrpmsOutlineResultTransMapper, SrpmsOutlineResultTrans> implements ISrpmsOutlineResultTransService {


    @Autowired
    private SrpmsOutlineResultTransMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询成果转化service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineResultTransVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineResultTransQueryForm queryForm) {
        SrpmsOutlineResultTransVoList dataVoList = new SrpmsOutlineResultTransVoList();

        // 执行查询
        List<SrpmsOutlineResultTransVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-成果转化service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineResultTransFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-成果转化删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-成果转化新增方法，返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-成果转化excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineResultTransVoList srpmsOutlineVoList = new SrpmsOutlineResultTransVoList();
        List<SrpmsOutlineResultTransVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineResultTransVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.TRANS_WAY, SrpmsConstant.TRANS_FEE_SOURCE});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 13) {
                vo = new SrpmsOutlineResultTransVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setTransWay(dictMap.get(SrpmsConstant.TRANS_WAY.concat(strings[5] == null ? "" : CommonUtil.deleteStringSpace(strings[5]))));// 技术成果转换形式
                        vo.setTransFeeSource(dictMap.get(SrpmsConstant.TRANS_FEE_SOURCE.concat(strings[9] == null ? "" : CommonUtil.deleteStringSpace(strings[9]))));// 转化费来源
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setProResultName(strings[3]);// 成果名称
                    vo.setProInCharge(strings[4]);// 负责人
                    vo.setTransWayName(strings[5]);// 技术成果转换形式名称
                    vo.setContractSigningYear(strings[6]);// 合同签订年份
                    vo.setContractSigningYearName(strings[6]);
                    vo.setContractAmount(CommonUtil.getDoubleValue(strings[7]));// 合同金额（万元）
                    vo.setYearEnsuredAmount(CommonUtil.getDoubleValue(strings[8]));// 本年到位金额（万元）
                    vo.setTransFeeSourceName(strings[9]);// 转化费来源名称
                    vo.setProCode(strings[10]);// 项目编号
                    vo.setProName(strings[11]);// 项目名称
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

    /**
     * 成果转化导出数据service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlineResultTransVo> exportExcelQuery(SrpmsOutlineResultTransQueryForm queryForm) {
        List<SrpmsOutlineResultTransQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineResultTransVo> srpmsOutlineVoList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.TRANS_WAY, SrpmsConstant.TRANS_FEE_SOURCE});

        SrpmsOutlineResultTransVo srpmsOutlineVo;
        SrpmsOutlineResultTransQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineResultTransVo();
            srpmsOutline = (SrpmsOutlineResultTransQueryParam) iterator.next();
            BeanUtils.copyProperties(srpmsOutline, srpmsOutlineVo);
            // 查询单位名称
            if (srpmsOutlineVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(srpmsOutlineVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            }
            srpmsOutlineVo.setOrgName(orgName);

            if (dictMap != null) {
                if (StringUtils.isNotBlank(srpmsOutlineVo.getTransWay())) {
                    srpmsOutlineVo.setTransWayName(dictMap.get(SrpmsConstant.TRANS_WAY.concat(srpmsOutlineVo.getTransWay())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getTransFeeSource())) {
                    srpmsOutlineVo.setTransFeeSourceName(dictMap.get(SrpmsConstant.TRANS_FEE_SOURCE.concat(srpmsOutlineVo.getTransFeeSource())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-成果转化操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineResultTransForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineResultTransForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineResultTransForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-成果转化操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineResultTransForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineResultTransForm> listError = new ArrayList<>();// 返回错误返回界面集合
                SrpmsOutlineResultTrans srpmsOutline;
                SrpmsOutlineResultTransForm titleForm;
                Iterator iterator = dataList.iterator();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineResultTrans> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineResultTrans> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                while (iterator.hasNext()) {
                    srpmsOutline = new SrpmsOutlineResultTrans();
                    titleForm = (SrpmsOutlineResultTransForm) iterator.next();

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

                    // 拼接条件重复验证 单位+年月+成果名称
                    key = new StringBuilder()
                            .append(orgId)
                            .append(year)
                            .append(month)
                            .append(CommonUtil.deleteStringSpace(titleForm.getProResultName())).toString();

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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_04, orgId, year, month);

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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_04);
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
     * 验证成果转化数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineResultTransForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+年月+成果名称)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("year", titleForm.getYear());
        paramMap.put("month", titleForm.getMonth());
        paramMap.put("proResultName", CommonUtil.deleteStringSpace(titleForm.getProResultName()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineResultTransForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
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
        // 项目编码
        if (StringUtils.isBlank(titleForm.getProCode())) {
            checkFlag = true;
            errorMsg.append("项目编号为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProCode(), 50)) {
            checkFlag = true;
            errorMsg.append("项目编号超过设定长度，");
        }
        // 项目名称
        if (StringUtils.isBlank(titleForm.getProName())) {
            checkFlag = true;
            errorMsg.append("项目名称为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProName(), 200)) {
            checkFlag = true;
            errorMsg.append("项目名称超过设定长度，");
        }
        // 成果名称
        if (StringUtils.isBlank(titleForm.getProResultName())) {
            checkFlag = true;
            errorMsg.append("成果名称为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProResultName(), 200)) {
            checkFlag = true;
            errorMsg.append("成果名称超过设定长度，");
        }
        // 负责人
        if (StringUtils.isBlank(titleForm.getProInCharge())) {
            checkFlag = true;
            errorMsg.append("负责人为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProInCharge(), 200)) {
            checkFlag = true;
            errorMsg.append("负责人超过设定长度，");
        }
        // 技术成果转化形式
        if (StringUtils.isBlank(titleForm.getTransWay())) {
            checkFlag = true;
            errorMsg.append("技术成果转化形式为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getTransWay(), 200)) {
            checkFlag = true;
            errorMsg.append("技术成果转化形式超过设定长度，");
        }
        // 合同签订年份
        if (StringUtils.isBlank(titleForm.getContractSigningYear())) {
            checkFlag = true;
            errorMsg.append("合同签订年份为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getContractSigningYear(), 4)) {
            checkFlag = true;
            errorMsg.append("合同签订年份超过设定长度，");
        }
        // 合同金额(万元)
        if (titleForm.getContractAmount() == null) {
            checkFlag = true;
            errorMsg.append("合同金额为空，");
        }
        // 本年到位金额(万元)
        if (titleForm.getYearEnsuredAmount() == null) {
            checkFlag = true;
            errorMsg.append("本年到位金额为空，");
        }
        // 转化费来源
        if (StringUtils.isBlank(titleForm.getTransFeeSource())) {
            checkFlag = true;
            errorMsg.append("转化费来源为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getTransFeeSource(), 20)) {
            checkFlag = true;
            errorMsg.append("转化费来源超过设定长度，");
        }
        return checkFlag;
    }

}