package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprUnitEvaInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoFieldForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.EnumMprProcessStatus;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.HTMLParseUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.mpr.entity.MprUnitEvaInfo;
import com.deloitte.services.srpmp.project.mpr.mapper.MprUnitEvaInfoMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprUnitEvaInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprUnitEvaInfo服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class MprUnitEvaInfoServiceImpl extends ServiceImpl<MprUnitEvaInfoMapper, MprUnitEvaInfo> implements IMprUnitEvaInfoService {

    @Autowired
    public IMprUnitEvaInfoService  mprUnitEvaInfoService;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Override
    public String exportWord(Long id) {
        String fileUrl = null;
        DateTimeFormatter dateTimeFormatterIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatterOutYear = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dateTimeFormatterOutMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dateTimeFormatterOutDay = DateTimeFormatter.ofPattern("dd");

        MprUnitEvaInfo mprUnitEvaInfo = this.getById(id);
        if (null == mprUnitEvaInfo) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "请先保存");
        }
        // 格式化富文本
        mprUnitEvaInfo.setOrgManaInfo(WordConvertUtil.htmlToText(mprUnitEvaInfo.getOrgManaInfo()));
        mprUnitEvaInfo.setServiceSupInfo(WordConvertUtil.htmlToText(mprUnitEvaInfo.getServiceSupInfo()));
        mprUnitEvaInfo.setMonitorRespInfo(WordConvertUtil.htmlToText(mprUnitEvaInfo.getMonitorRespInfo()));
        mprUnitEvaInfo.setResultManaInfo(WordConvertUtil.htmlToText(mprUnitEvaInfo.getResultManaInfo()));
        mprUnitEvaInfo.setImplEffect(WordConvertUtil.htmlToText(mprUnitEvaInfo.getImplEffect()));
        mprUnitEvaInfo.setImplAdvice(WordConvertUtil.htmlToText(mprUnitEvaInfo.getImplAdvice()));
        try {
            JSONObject resultJson = (JSONObject) JSON.toJSON(mprUnitEvaInfo);
            resultJson.put("year", mprUnitEvaInfo.getYear());
            JSONObject dataJson = new JSONObject();
            // 时间参数
            LocalDateTime reportDate = resultJson.getObject("createTime", LocalDateTime.class);
            if (null != reportDate) {
                String year = reportDate.format(dateTimeFormatterOutYear);
                String month = reportDate.format(dateTimeFormatterOutMonth);
                String day = reportDate.format(dateTimeFormatterOutDay);

                resultJson.put("y", year);
                resultJson.put("m", month);
                resultJson.put("d", day);
            }



            dataJson.put("ue", resultJson);
            Map dataMap = new HashMap();
            dataMap.put("data", dataJson);

            String filename = "中期绩效报告（附件九）_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
            fileUrl = wordExportService.exportWord("template_unit_eva_info", dataMap, filename);
        } catch (Exception e) {
            log.error("导出word异常。", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
        }
        return fileUrl;
    }

    @Override
    public MprUnitEvaInfoVo importWord(String wordFileUrl) {
        if (!wordFileUrl.endsWith(".doc") && !wordFileUrl.endsWith(".docx")) {
            return new MprUnitEvaInfoVo();
        }

        MprUnitEvaInfoVo mprUnitEvaInfoVo = null;
        try {
            mprUnitEvaInfoVo = new MprUnitEvaInfoVo();
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "UTF-8");
            Elements tableElements = document.getElementsByTag("table");
            // 解析文档获取表格对象元素标签
            Element orgManaInfoElement = tableElements.get(0);
            Element serviceSupInfoElement = tableElements.get(1);
            Element monitorRespInfoElement = tableElements.get(2);
            Element resultManaInfoElement = tableElements.get(3);
            Element implEffectElement = tableElements.get(4);
            Element implAdviceElement = tableElements.get(5);
            // 获取对应的表格元素标签中的内容，以下获取的为一个单元格中内容数据(row:0,col:0)
            String orgManaInfo = (HTMLParseUtil.extractCellFromTable(orgManaInfoElement,0, 0, true)).replaceAll("\"", "\\\\\"");
            String serviceSupInfo = (HTMLParseUtil.extractCellFromTable(serviceSupInfoElement, 0, 0, true)).replaceAll("\"", "\\\\\"");
            String monitorRespInfo = (HTMLParseUtil.extractCellFromTable(monitorRespInfoElement,0, 0, true)).replaceAll("\"", "\\\\\"");
            String resultManaInfo = (HTMLParseUtil.extractCellFromTable(resultManaInfoElement,0, 0, true)).replaceAll("\"", "\\\\\"");
            String implEffect = (HTMLParseUtil.extractCellFromTable(implEffectElement,0, 0, true)).replaceAll("\"", "\\\\\"");
            String implAdvice = (HTMLParseUtil.extractCellFromTable(implAdviceElement,0, 0, true)).replaceAll("\"", "\\\\\"");
            // 赋值
            mprUnitEvaInfoVo.setOrgManaInfo(orgManaInfo);
            mprUnitEvaInfoVo.setServiceSupInfo(serviceSupInfo);
            mprUnitEvaInfoVo.setMonitorRespInfo(monitorRespInfo);
            mprUnitEvaInfoVo.setResultManaInfo(resultManaInfo);
            mprUnitEvaInfoVo.setImplEffect(implEffect);
            mprUnitEvaInfoVo.setImplAdvice(implAdvice);
            // 删除HTML
            WordConvertUtil.delHtmlFile(htmlFilePath);
        } catch (Exception e) {
            log.error("解析word文件发生异常.", e);
            throw new BaseException(SrpmsErrorType.IMPORT_FILE_ERROR);
        } finally {
        }
        return mprUnitEvaInfoVo;
    }

    @Override
    public Long saveAndUpdate(MprUnitEvaInfoForm unitEvaInfoForm, UserVo user, DeptVo dept) {
        //        QueryWrapper<MprUnitEvaInfo> unitEvaInfoQueryWrapper = new QueryWrapper<>();
//        unitEvaInfoQueryWrapper.eq(MprUnitEvaInfo.YEAR, unitEvaInfoForm.getYear());
//        unitEvaInfoQueryWrapper.eq(MprUnitEvaInfo.DEPT_CODE, dept.getDeptCode());
//        MprUnitEvaInfo mprProjEvaInfo = this.getOne(unitEvaInfoQueryWrapper);
//        String processStatus = EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode();
//        if (null != mprProjEvaInfo) {
//            processStatus = mprProjEvaInfo.getProcessStatus();
//            if (!StringUtils.equals(processStatus, EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode())) {
//                throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG, "已提交，不允许修改");
//            }
//            this.removeById(mprProjEvaInfo.getId());
//        }
//        MprUnitEvaInfo insertUnitEvaInfo = new BeanUtils<MprUnitEvaInfo>().copyObj(unitEvaInfoForm, MprUnitEvaInfo.class);
//        insertUnitEvaInfo.setProcessStatus(processStatus);
//        insertUnitEvaInfo.setDeptCode(dept.getDeptCode());
//        insertUnitEvaInfo.setDeptName(dept.getDeptName());
//        insertUnitEvaInfo.setCreateBy(user.getId());
//        insertUnitEvaInfo.setYear(unitEvaInfoForm.getYear());
//        this.save(insertUnitEvaInfo);
//        return insertUnitEvaInfo.getId();
        MprUnitEvaInfo unitEvaInfo = new BeanUtils<MprUnitEvaInfo>().copyObj(unitEvaInfoForm, MprUnitEvaInfo.class);
        String processStatus = EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode();
        if (null == unitEvaInfoForm.getId()) {
            unitEvaInfo.setDeptCode(dept.getDeptCode());
            unitEvaInfo.setDeptName(dept.getDeptName());
            unitEvaInfo.setCreateBy(user.getId());
        } else {
            unitEvaInfo.setUpdateBy(user.getId());
            MprUnitEvaInfo info = this.getById(unitEvaInfoForm.getId());
            if (null == info) {
                throw new BaseException(PlatformErrorType.NO_DATA_FOUND, "要更新的数据不存在");
            }

            processStatus = info.getProcessStatus();
            if (StringUtils.equals(unitEvaInfo.getProcessStatus(), EnumMprProcessStatus.MPR_APPROVED.getCode())
                    || StringUtils.equals(unitEvaInfo.getProcessStatus(), EnumMprProcessStatus.MPR_AUDIT.getCode())
                    || StringUtils.equals(unitEvaInfo.getProcessStatus(), EnumMprProcessStatus.MPR_REFUSE.getCode())) {
                throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG, "已提交，不允许修改");
            }
        }
        unitEvaInfo.setProcessStatus(processStatus);
        this.saveOrUpdate(unitEvaInfo);
        return unitEvaInfo.getId();
    }

    @Override
    public MprUnitEvaInfoVo getOne(MprUnitEvaInfoQueryForm unitEvaInfoQueryForm, UserVo user, DeptVo dept) {
        MprUnitEvaInfo mprUnitEvaInfo;
        if (null != unitEvaInfoQueryForm.getId()) {
            mprUnitEvaInfo = this.getById(unitEvaInfoQueryForm.getId());
        } else {
            QueryWrapper<MprUnitEvaInfo> unitEvaInfoQueryWrapper = new QueryWrapper<>();
            unitEvaInfoQueryWrapper.eq(MprUnitEvaInfo.DEPT_CODE, dept.getDeptCode());
            if (StringUtils.isNotBlank(unitEvaInfoQueryForm.getYear())) {
                unitEvaInfoQueryWrapper.eq(MprUnitEvaInfo.YEAR, unitEvaInfoQueryForm.getYear());
            } else {
                unitEvaInfoQueryWrapper.eq(MprUnitEvaInfo.YEAR, String.valueOf(LocalDateTime.now().getYear()));
            }
            mprUnitEvaInfo = this.getOne(unitEvaInfoQueryWrapper);
        }

        if (null == mprUnitEvaInfo) {
            MprUnitEvaInfoVo infoVo = new MprUnitEvaInfoVo();
            //这里赋值方便前端
            infoVo.setYear(unitEvaInfoQueryForm.getYear());
            infoVo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
            return infoVo;
        } else {
            MprUnitEvaInfoVo unitEvaInfoVo = new BeanUtils<MprUnitEvaInfoVo>().copyObj(mprUnitEvaInfo, MprUnitEvaInfoVo.class);
            if (StringUtils.isNotBlank(mprUnitEvaInfo.getFileInfo())) {
                unitEvaInfoVo.setFileInfo(JSONObject.parseObject(mprUnitEvaInfo.getFileInfo(), MprEvaFileInfoFieldForm.class));
            }
            if (StringUtils.isNotBlank(mprUnitEvaInfo.getAuditFileInfo())) {
                unitEvaInfoVo.setAuditFileInfo(JSONArray.parseArray(mprUnitEvaInfo.getAuditFileInfo(), MprEvaFileInfoFieldForm.class));
            }
            return unitEvaInfoVo;
        }
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprUnitEvaInfo> getQueryWrapper(QueryWrapper<MprUnitEvaInfo> queryWrapper,MprUnitEvaInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprUnitEvaInfo.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getBearerUnit())){
            queryWrapper.eq(MprUnitEvaInfo.BEARER_UNIT,queryForm.getBearerUnit());
        }
        if(StringUtils.isNotBlank(queryForm.getBearerUnitManager())){
            queryWrapper.eq(MprUnitEvaInfo.BEARER_UNIT_MANAGER,queryForm.getBearerUnitManager());
        }
        if(StringUtils.isNotBlank(queryForm.getReportDate())){
            queryWrapper.eq(MprUnitEvaInfo.REPORT_DATE,queryForm.getReportDate());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgManaInfo())){
            queryWrapper.eq(MprUnitEvaInfo.ORG_MANA_INFO,queryForm.getOrgManaInfo());
        }
        if(StringUtils.isNotBlank(queryForm.getServiceSupInfo())){
            queryWrapper.eq(MprUnitEvaInfo.SERVICE_SUP_INFO,queryForm.getServiceSupInfo());
        }
        if(StringUtils.isNotBlank(queryForm.getMonitorRespInfo())){
            queryWrapper.eq(MprUnitEvaInfo.MONITOR_RESP_INFO,queryForm.getMonitorRespInfo());
        }
        if(StringUtils.isNotBlank(queryForm.getResultManaInfo())){
            queryWrapper.eq(MprUnitEvaInfo.RESULT_MANA_INFO,queryForm.getResultManaInfo());
        }
        if(StringUtils.isNotBlank(queryForm.getImplEffect())){
            queryWrapper.eq(MprUnitEvaInfo.IMPL_EFFECT,queryForm.getImplEffect());
        }
        if(StringUtils.isNotBlank(queryForm.getImplAdvice())){
            queryWrapper.eq(MprUnitEvaInfo.IMPL_ADVICE,queryForm.getImplAdvice());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprUnitEvaInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprUnitEvaInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprUnitEvaInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprUnitEvaInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

