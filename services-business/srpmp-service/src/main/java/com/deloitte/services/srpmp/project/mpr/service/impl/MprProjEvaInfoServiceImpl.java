package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprProjEvaInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaBaseInfoVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.DateParseUtil;
import com.deloitte.services.srpmp.common.util.HTMLParseUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnBcoo;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnBcooService;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;
import com.deloitte.services.srpmp.project.mpr.entity.MprProjEvaInfo;
import com.deloitte.services.srpmp.project.mpr.mapper.MprProjEvaInfoMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaBaseInfoService;
import com.deloitte.services.srpmp.project.mpr.service.IMprProjEvaInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprProjEvaInfo服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class    MprProjEvaInfoServiceImpl extends ServiceImpl<MprProjEvaInfoMapper, MprProjEvaInfo> implements IMprProjEvaInfoService {


    @Autowired
    private MprProjEvaInfoMapper mprProjEvaInfoMapper;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    public IMprProjEvaInfoService  mprProjEvaInfoService;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectApplyInnBcooService projectApplyInnBcooService;

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private IMprEvaBaseInfoService mprEvaBaseInfoService;

    @Override
    public IPage<MprProjEvaInfo> selectPage(MprProjEvaInfoQueryForm queryForm ) {
        QueryWrapper<MprProjEvaInfo> queryWrapper = new QueryWrapper <MprProjEvaInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprProjEvaInfoMapper.selectPage(new Page<MprProjEvaInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprProjEvaInfo> selectList(MprProjEvaInfoQueryForm queryForm) {
        QueryWrapper<MprProjEvaInfo> queryWrapper = new QueryWrapper <MprProjEvaInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprProjEvaInfoMapper.selectList(queryWrapper);
    }

    @Override
    public String exportWord(Long projectId) {
        String fileUrl = null;
        DateTimeFormatter dateTimeFormatterIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatterYearOut = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dateTimeFormatterMonthOut = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dateTimeFormatterDayOut = DateTimeFormatter.ofPattern("dd");

        MprProjEvaInfo mprProjEvaInfo = this.getById(projectId);
        if (null == mprProjEvaInfo) {
            throw new BaseException(SrpmsErrorType.EVA_PROJ_NOT_COMPLETE);
        }

        // 格式化富文本，去除html标签
        mprProjEvaInfo.setTaskProgress(WordConvertUtil.htmlToText(mprProjEvaInfo.getTaskProgress()));
        mprProjEvaInfo.setAdjustState(WordConvertUtil.htmlToText(mprProjEvaInfo.getAdjustState()));
        mprProjEvaInfo.setLandmarkAchieve(WordConvertUtil.htmlToText(mprProjEvaInfo.getLandmarkAchieve()));
        mprProjEvaInfo.setEconSocialBenefits(WordConvertUtil.htmlToText(mprProjEvaInfo.getEconSocialBenefits()));
        mprProjEvaInfo.setCapaImprove(WordConvertUtil.htmlToText(mprProjEvaInfo.getCapaImprove()));
        mprProjEvaInfo.setTeamBuild(WordConvertUtil.htmlToText(mprProjEvaInfo.getTeamBuild()));
        mprProjEvaInfo.setDeveProsAna(WordConvertUtil.htmlToText(mprProjEvaInfo.getDeveProsAna()));
        mprProjEvaInfo.setPersonFundUse(WordConvertUtil.htmlToText(mprProjEvaInfo.getPersonFundUse()));
        mprProjEvaInfo.setFundAdjust(WordConvertUtil.htmlToText(mprProjEvaInfo.getFundAdjust()));
        mprProjEvaInfo.setProblemSuggest(WordConvertUtil.htmlToText(mprProjEvaInfo.getProblemSuggest()));
        mprProjEvaInfo.setProjCont(WordConvertUtil.htmlToText(mprProjEvaInfo.getProjCont()));
        mprProjEvaInfo.setInstructions(WordConvertUtil.htmlToText(mprProjEvaInfo.getInstructions()));

        try {
            JSONObject resultJson = (JSONObject) JSON.toJSON(mprProjEvaInfo);
            JSONObject dataJson = new JSONObject();
            Map dataMap = new HashMap();
            // 项目类别
            List<String> categoryList = Arrays.asList(resultJson.getString("projectCategory"));
            Map<String, String> categoryMap = new HashMap<>();
            categoryMap.put("创新工程-重大协同创新", "创新工程-重大协同创新");
            categoryMap.put("创新工程-协同创新团队", "创新工程-协同创新团队");
            categoryMap.put("创新工程-先导专项", "创新工程-先导专项");
            categoryMap.put("创新工程-青年创新团队项目", "创新工程-青年创新团队项目");
            List<String> projectTypeList = WordConvertUtil.parseCodeListToText(categoryMap, categoryList);
            // 项目分类
            List<String> typeList = Arrays.asList(resultJson.getString("projectType"));
            Map<String, String> typeMap = new HashMap<>();
            typeMap.put("重大项目-前沿研究", "重大项目-前沿研究");
            typeMap.put("重大项目-平台建设与基础性科技工作", "重大项目-平台建设与基础性科技工作");
            typeMap.put("协同创新团队项目", "协同创新团队项目");
            typeMap.put("青年团队项目", "青年团队项目");
            typeMap.put("一带一路", "一带一路");
            typeMap.put("人工智能", "人工智能");
            typeMap.put("健康长寿", "健康长寿");
            List<String> projectCategoryList = WordConvertUtil.parseCodeListToText(typeMap, typeList);
            // 项目周期时间处理
            String projectCycleTime = resultJson.getString("projectCycle");
            String projectCycleStartYear = "";
            String projectCycleStartMonth = "";
            String projectCycleStartDay = "";
            String projectCycleEndYear = "";
            String projectCycleEndMonth = "";
            String projectCycleEndDay = "";
            if (StringUtils.isNotBlank(projectCycleTime)) {
                String[] projectCycleStartAndEnd = projectCycleTime.split(",");
                if (projectCycleStartAndEnd.length > 0) {
                    String projectCycleStart = projectCycleStartAndEnd[0];
                    String projectCycleStartTime = LocalDateTime.parse(projectCycleStart, dateTimeFormatterIn).format(dateTimeFormatterIn);
                    // 起始
                    projectCycleStartYear = LocalDateTime.parse(projectCycleStartTime, dateTimeFormatterIn).format(dateTimeFormatterYearOut);
                    projectCycleStartMonth = LocalDateTime.parse(projectCycleStartTime, dateTimeFormatterIn).format(dateTimeFormatterMonthOut);
                    projectCycleStartDay = LocalDateTime.parse(projectCycleStartTime, dateTimeFormatterIn).format(dateTimeFormatterDayOut);
                    resultJson.put("sy", projectCycleStartYear);
                    resultJson.put("sm", projectCycleStartMonth);
                    resultJson.put("sd", projectCycleStartDay);
                }
                if (projectCycleStartAndEnd.length > 1) {
                    String projectCycleEnd = projectCycleStartAndEnd[1];
                    String projectCycleEndTime = LocalDateTime.parse(projectCycleEnd, dateTimeFormatterIn).format(dateTimeFormatterIn);
                    // 截至
                    projectCycleEndYear = LocalDateTime.parse(projectCycleEndTime, dateTimeFormatterIn).format(dateTimeFormatterYearOut);
                    projectCycleEndMonth = LocalDateTime.parse(projectCycleEndTime, dateTimeFormatterIn).format(dateTimeFormatterMonthOut);
                    projectCycleEndDay = LocalDateTime.parse(projectCycleEndTime, dateTimeFormatterIn).format(dateTimeFormatterDayOut);

                    resultJson.put("ey", projectCycleEndYear);
                    resultJson.put("em", projectCycleEndMonth);
                    resultJson.put("ed", projectCycleEndDay);
                }

            }
            // 项目报告时间处理
            LocalDateTime reportDate = resultJson.getObject("createTime", LocalDateTime.class);
            if (null != reportDate) {
                String reportYear = reportDate.format(dateTimeFormatterYearOut);
                String reportMonth = reportDate.format(dateTimeFormatterMonthOut);
                String reportDay = reportDate.format(dateTimeFormatterDayOut);
                resultJson.put("year", reportYear);
                resultJson.put("month", reportMonth);
                resultJson.put("day", reportDay);
            }

            resultJson.put("projectType", projectTypeList);
            resultJson.put("projectCategory", projectCategoryList);

            dataJson.put("pe", resultJson);
            dataMap.put("data", dataJson);

            String fileName = "中国医学与健康科技创新工程中期绩效考评自评报告_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 999) + ".docx";
            fileUrl = wordExportService.exportWord("template_proj_eva_info", dataMap, fileName);
        } catch (Exception e) {
            log.error("导出word异常。", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
        }
        return fileUrl;
    }

    @Override
    public MprProjEvaInfoVo importWord(String wordFileUrl) {
        if (!wordFileUrl.endsWith(".doc") && !wordFileUrl.endsWith(".docx")) {
            return new MprProjEvaInfoVo();
        }
        MprProjEvaInfoVo mprProjEvaInfoVo = null;
        String projectNoText = null;
        String projectNameText = null;
        String projectCycleStartAndEndText = null;
        String projectCategoryText = null;
        String projectTypeText = null;
        try {
            mprProjEvaInfoVo = new MprProjEvaInfoVo();
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "UTF-8");
            Elements tableElements = document.getElementsByTag("table");

            Elements projectTypeElements = document.getElementsMatchingOwnText("项目类别");
            if (projectTypeElements!= null && projectTypeElements.size() > 0){
                Element spanElement = projectTypeElements.get(0);
                if (spanElement.tagName().equals("span")){
                    Element parentElement = spanElement.parent();
                    String parentText = parentElement.text();
                    if (StringUtils.isNotBlank(parentText) && parentText.indexOf("：") > 0){
                        String actionTimeText = parentText.substring(parentText.indexOf("：") + 1);
                        if (StringUtils.isNotBlank(actionTimeText)){
                            String[] achievArr = actionTimeText.split("\\s+");
                            for (String achiev: achievArr) {
                                if (StringUtils.isBlank(achiev)) {
                                    continue;
                                }
                                if (!achiev.startsWith("□")){
                                    projectTypeText = achiev.substring(1);
                                }
                            }
                        }
                    }
                }
            }
            Elements projectCategoryElements = document.getElementsMatchingOwnText("项目分类");
            if (projectCategoryElements!= null && projectCategoryElements.size() > 0){
                Element spanElement = projectCategoryElements.get(0);
                if (spanElement.tagName().equals("span")){
                    Element parentElement = spanElement.parent();
                    String parentText = parentElement.text();
                    if (StringUtils.isNotBlank(parentText) && parentText.indexOf("：") > 0){
                        String actionTimeText = parentText.substring(parentText.indexOf("：") + 1);
                        if (StringUtils.isNotBlank(actionTimeText)){
                            String[] achievArr = actionTimeText.split("\\s+");
                            for (String achiev: achievArr) {
                                if (StringUtils.isBlank(achiev)) {
                                    continue;
                                }
                                if (!achiev.startsWith("□")){
                                    projectCategoryText = achiev.substring(1);
                                }
                            }
                        }
                    }
                }
            }
            Elements projectNoElements = document.getElementsMatchingOwnText("项目编号");
            if (projectNoElements!= null && projectNoElements.size() > 0){
                Element projectNoSpanElement = projectNoElements.get(0);
                if (projectNoSpanElement.tagName().equals("span")){
                    Element projectNoParentElement = projectNoSpanElement.parent();
                    String projectNoParentText = projectNoParentElement.text();
                    if (StringUtils.isNotBlank(projectNoParentText) && projectNoParentText.indexOf("：") > 0){
                        projectNoText = projectNoParentText.substring(projectNoParentText.indexOf("：") + 1);
                    }
                }
            }
            Elements projectNameElements = document.getElementsMatchingOwnText("项目名称");
            if (projectNameElements!= null && projectNameElements.size() > 0){
                Element projectNameSpanElement = projectNameElements.get(0);
                if (projectNameSpanElement.tagName().equals("span")){
                    Element projectNameParentElement = projectNameSpanElement.parent();
                    String projectNameParentText = projectNameParentElement.text();
                    if (StringUtils.isNotBlank(projectNameParentText) && projectNameParentText.indexOf("：") > 0){
                        projectNameText = projectNameParentText.substring(projectNameParentText.indexOf("：") + 1);
                    }
                }
            }
            Elements projectCycleElements = document.getElementsMatchingOwnText("项目周期");
            if (projectCycleElements!= null && projectCycleElements.size() > 0){
                Element projectCycleSpanElement = projectCycleElements.get(0);
                if (projectCycleSpanElement.tagName().equals("span")){
                    Element projectCycleParentElement = projectCycleSpanElement.parent();
                    String projectCycleParentText = projectCycleParentElement.text();
                    if (StringUtils.isNotBlank(projectCycleParentText) && projectCycleParentText.indexOf("：") > 0){
                        String projectCycleText = projectCycleParentText.substring(projectCycleParentText.indexOf("：") + 1);
                        String[] actionTimeArr = projectCycleText.split("至");
                        if (actionTimeArr.length == 2){
                            String actionTimeStart = actionTimeArr[0].replaceAll(" ", "").trim();
                            String actionTimeEnd = actionTimeArr[1].replaceAll(" ", "").trim();
                            LocalDateTime start = DateParseUtil.parseLocalDateTime(actionTimeStart);
                            LocalDateTime end = DateParseUtil.parseLocalDateTime(actionTimeEnd);
                            String projectCycleStart = LocalDateUtils.format(start, "yyyy-MM-dd HH:mm:ss");
                            String projectCycleEnd = LocalDateUtils.format(end, "yyyy-MM-dd HH:mm:ss");
                            projectCycleStartAndEndText = projectCycleStart + "," + projectCycleEnd;
                        }
                    }
                }
            }
            // 解析文档获取表格对象元素标签
            Element taskProgressElement = tableElements.get(0);
            Element adjustStateElement = tableElements.get(1);
            Element landmarkAchieveElement = tableElements.get(2);
            Element econSocialBenefitsElement = tableElements.get(3);
            Element capaImproveElement = tableElements.get(4);
            Element teamBuildElement = tableElements.get(5);
            Element deveProsAnaElement = tableElements.get(6);
            Element personFundUseElement = tableElements.get(7);
            Element fundAdjustElement = tableElements.get(8);
            Element problemSuggestElement = tableElements.get(9);
            Element projContElement = tableElements.get(10);
            Element instructionsElement = tableElements.get(11);
            // 获取对应的表格元素标签中的内容，以下获取的为一个单元格中内容数据(row:0,col:0)
            String taskProgress = (HTMLParseUtil.extractCellFromTable(taskProgressElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String adjustState = (HTMLParseUtil.extractCellFromTable(adjustStateElement, 0, 0,true)).replaceAll("\"", "\\\\\"");
            String landmarkAchieve = (HTMLParseUtil.extractCellFromTable(landmarkAchieveElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String econSocialBenefits= (HTMLParseUtil.extractCellFromTable(econSocialBenefitsElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String capaImprove = (HTMLParseUtil.extractCellFromTable(capaImproveElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String teamBuild = (HTMLParseUtil.extractCellFromTable(teamBuildElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String deveProsAna = (HTMLParseUtil.extractCellFromTable(deveProsAnaElement, 0, 0,true)).replaceAll("\"", "\\\\\"");
            String personFundUse = (HTMLParseUtil.extractCellFromTable(personFundUseElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String fundAdjust = (HTMLParseUtil.extractCellFromTable(fundAdjustElement, 0, 0,true)).replaceAll("\"", "\\\\\"");
            String problemSuggest = (HTMLParseUtil.extractCellFromTable(problemSuggestElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            String projCont = (HTMLParseUtil.extractCellFromTable(projContElement, 0, 0,true)).replaceAll("\"", "\\\\\"");
            String instructions = (HTMLParseUtil.extractCellFromTable(instructionsElement,0, 0,true)).replaceAll("\"", "\\\\\"");
            // 赋值
            mprProjEvaInfoVo.setProjectType(projectTypeText);
            mprProjEvaInfoVo.setProjectCategory(projectCategoryText);
            mprProjEvaInfoVo.setProjectNo(projectNoText);
            mprProjEvaInfoVo.setProjectName(projectNameText);
            mprProjEvaInfoVo.setProjectCycle(projectCycleStartAndEndText);
            mprProjEvaInfoVo.setTaskProgress(taskProgress);
            mprProjEvaInfoVo.setAdjustState(adjustState);
            mprProjEvaInfoVo.setLandmarkAchieve(landmarkAchieve);
            mprProjEvaInfoVo.setEconSocialBenefits(econSocialBenefits);
            mprProjEvaInfoVo.setCapaImprove(capaImprove);
            mprProjEvaInfoVo.setTeamBuild(teamBuild);
            mprProjEvaInfoVo.setDeveProsAna(deveProsAna);
            mprProjEvaInfoVo.setPersonFundUse(personFundUse);
            mprProjEvaInfoVo.setFundAdjust(fundAdjust);
            mprProjEvaInfoVo.setProblemSuggest(problemSuggest);
            mprProjEvaInfoVo.setProjCont(projCont);
            mprProjEvaInfoVo.setInstructions(instructions);
            // 删除html
            WordConvertUtil.delHtmlFile(htmlFilePath);
        } catch (Exception e) {
            log.error("解析word文件发生异常.", e);
            throw new BaseException(SrpmsErrorType.IMPORT_FILE_ERROR);
        } finally {
        }
        return mprProjEvaInfoVo;
    }

    @Override
    public Long saveAndUpdate(MprProjEvaInfoForm mprProjEvaInfoForm) {
        MprProjEvaInfo mprProjEvaInfo = this.getById(mprProjEvaInfoForm.getId());
        if (null != mprProjEvaInfo) {
            this.removeById(mprProjEvaInfoForm.getId());
        }
        MprProjEvaInfo insertMprProjEvaInfo = new BeanUtils<MprProjEvaInfo>().copyObj(mprProjEvaInfoForm, MprProjEvaInfo.class);
        insertMprProjEvaInfo.setId(mprProjEvaInfoForm.getId());
        this.save(insertMprProjEvaInfo);
        return insertMprProjEvaInfo.getId();
    }

    @Override
    public MprProjEvaInfoVo getOne(Long projectId) {
        MprProjEvaInfo mprProjEvaInfo = this.getById(projectId);
        if (null == mprProjEvaInfo) {
            SrpmsProject project = projectService.getById(projectId);
            MprProjEvaInfoVo projEvaInfoVo = new MprProjEvaInfoVo();
            //项目名称
            projEvaInfoVo.setProjectName(project.getProjectName());
            //项目编号
            projEvaInfoVo.setProjectNo(project.getProjectNum());
            //项目类别
            for (int j = 0; j < ProjectCategoryEnums.values().length; j ++) {
                if (project.getProjectCategory().equals(ProjectCategoryEnums.values()[j].getCode())) {
                    projEvaInfoVo.setProjectCategory(ProjectCategoryEnums.values()[j].getDesc());
                }
            }
//            //项目分类
//            SrpmsProjectApplyInnBcoo bcoo = projectApplyInnBcooService.getById(projectId);
//            if (null != bcoo && StringUtils.isNotBlank(bcoo.getActiveType())) {
//                List<String> codeList = JSON.parseArray(bcoo.getActiveType(), String.class);
//                List<String> codeDesc = Lists.newArrayList();
//                for (String code : codeList) {
//                    String activeTypeName = dictService.selectValueByCode(SysDictEnums.PRO_ACTIVE_TYPE, code);
//                    codeDesc.add(activeTypeName);
//                }
//                projEvaInfoVo.setProjectType(JSON.toJSONString(codeDesc));
//            }
            MprEvaBaseInfo baseInfo = mprEvaBaseInfoService.getMprBaseInfo(projectId);
            if (baseInfo != null) {
                projEvaInfoVo.setProjectType(baseInfo.getProjectType());
            }
            //执行周期
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String begin = "";
            String end = "";
            if (null != project.getProjectActionDateStart()) {
                begin = project.getProjectActionDateStart().format(formatter);
            }
            if (null != project.getProjectActionDateEnd()) {
                end = project.getProjectActionDateEnd().format(formatter);
            }
            projEvaInfoVo.setProjectCycle(begin + "," + end);

            return projEvaInfoVo;
        }
        return new BeanUtils<MprProjEvaInfoVo>().copyObj(mprProjEvaInfo, MprProjEvaInfoVo.class);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprProjEvaInfo> getQueryWrapper(QueryWrapper<MprProjEvaInfo> queryWrapper,MprProjEvaInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectType())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_TYPE,queryForm.getProjectType());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCategory())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_CATEGORY,queryForm.getProjectCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCycle())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_CYCLE,queryForm.getProjectCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectChiefExpert())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_CHIEF_EXPERT,queryForm.getProjectChiefExpert());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectUndertaker())){
            queryWrapper.eq(MprProjEvaInfo.PROJECT_UNDERTAKER,queryForm.getProjectUndertaker());
        }
        if(StringUtils.isNotBlank(queryForm.getReportDate())){
            queryWrapper.eq(MprProjEvaInfo.REPORT_DATE,queryForm.getReportDate());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskProgress())){
            queryWrapper.eq(MprProjEvaInfo.TASK_PROGRESS,queryForm.getTaskProgress());
        }
        if(StringUtils.isNotBlank(queryForm.getAdjustState())){
            queryWrapper.eq(MprProjEvaInfo.ADJUST_STATE,queryForm.getAdjustState());
        }
        if(StringUtils.isNotBlank(queryForm.getLandmarkAchieve())){
            queryWrapper.eq(MprProjEvaInfo.LANDMARK_ACHIEVE,queryForm.getLandmarkAchieve());
        }
        if(StringUtils.isNotBlank(queryForm.getEconSocialBenefits())){
            queryWrapper.eq(MprProjEvaInfo.ECON_SOCIAL_BENEFITS,queryForm.getEconSocialBenefits());
        }
        if(StringUtils.isNotBlank(queryForm.getCapaImprove())){
            queryWrapper.eq(MprProjEvaInfo.CAPA_IMPROVE,queryForm.getCapaImprove());
        }
        if(StringUtils.isNotBlank(queryForm.getTeamBuild())){
            queryWrapper.eq(MprProjEvaInfo.TEAM_BUILD,queryForm.getTeamBuild());
        }
        if(StringUtils.isNotBlank(queryForm.getDeveProsAna())){
            queryWrapper.eq(MprProjEvaInfo.DEVE_PROS_ANA,queryForm.getDeveProsAna());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonFundUse())){
            queryWrapper.eq(MprProjEvaInfo.PERSON_FUND_USE,queryForm.getPersonFundUse());
        }
        if(StringUtils.isNotBlank(queryForm.getFundAdjust())){
            queryWrapper.eq(MprProjEvaInfo.FUND_ADJUST,queryForm.getFundAdjust());
        }
        if(StringUtils.isNotBlank(queryForm.getProblemSuggest())){
            queryWrapper.eq(MprProjEvaInfo.PROBLEM_SUGGEST,queryForm.getProblemSuggest());
        }
        if(StringUtils.isNotBlank(queryForm.getProjCont())){
            queryWrapper.eq(MprProjEvaInfo.PROJ_CONT,queryForm.getProjCont());
        }
        if(StringUtils.isNotBlank(queryForm.getInstructions())){
            queryWrapper.eq(MprProjEvaInfo.INSTRUCTIONS,queryForm.getInstructions());
        }
        if(StringUtils.isNotBlank(queryForm.getAdjSuggestSortTask())){
            queryWrapper.eq(MprProjEvaInfo.ADJ_SUGGEST_SORT_TASK,queryForm.getAdjSuggestSortTask());
        }
        if(StringUtils.isNotBlank(queryForm.getExpertSignSortTask())){
            queryWrapper.eq(MprProjEvaInfo.EXPERT_SIGN_SORT_TASK,queryForm.getExpertSignSortTask());
        }
        if(StringUtils.isNotBlank(queryForm.getSignDateSortTask())){
            queryWrapper.eq(MprProjEvaInfo.SIGN_DATE_SORT_TASK,queryForm.getSignDateSortTask());
        }
        if(StringUtils.isNotBlank(queryForm.getAdjSuggestSortJob())){
            queryWrapper.eq(MprProjEvaInfo.ADJ_SUGGEST_SORT_JOB,queryForm.getAdjSuggestSortJob());
        }
        if(StringUtils.isNotBlank(queryForm.getExpertSignSortJob())){
            queryWrapper.eq(MprProjEvaInfo.EXPERT_SIGN_SORT_JOB,queryForm.getExpertSignSortJob());
        }
        if(StringUtils.isNotBlank(queryForm.getSignDateSortJob())){
            queryWrapper.eq(MprProjEvaInfo.SIGN_DATE_SORT_JOB,queryForm.getSignDateSortJob());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprProjEvaInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprProjEvaInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprProjEvaInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprProjEvaInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

