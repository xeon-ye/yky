package com.deloitte.services.srpmp.project.apply.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListTableRows;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchStuQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchStuSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.entity.SysDict;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.StringConvertUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchStu;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplySchStuMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplySchStuService;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPerson;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : caoyue
 * @Date : Create in 2019-02-21
 * @Description :  SrpmsProjectApplySchStu服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectApplySchStuServiceImpl extends ServiceImpl<SrpmsProjectApplySchStuMapper, SrpmsProjectApplySchStu> implements ISrpmsProjectApplySchStuService {


    @Autowired
    private SrpmsProjectApplySchStuMapper srpmsProjectApplySchStuMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectPersonService projectPersonService;

    @Autowired
    private ISrpmsProjectPersonJoinService projectPersonJoinService;

    @Autowired
    SrpmsProjectApplyInnCommonServiceImpl commonService;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;


    @Autowired
    private ISrpmsProjectBudgetDetailService budgetDetailService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private PdfMergeServiceImpl pdfMergeService;

	@Autowired
	private ISrpmsCommonNclobService commonNclobService;
	
    @Override
    public IPage<SrpmsProjectApplySchStu> selectPage(SrpmsProjectApplySchStuQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectApplySchStu> queryWrapper = new QueryWrapper <SrpmsProjectApplySchStu>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApplySchStuMapper.selectPage(new Page<SrpmsProjectApplySchStu>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectApplySchStu> selectList(SrpmsProjectApplySchStuQueryForm queryForm) {
        QueryWrapper<SrpmsProjectApplySchStu> queryWrapper = new QueryWrapper <SrpmsProjectApplySchStu>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApplySchStuMapper.selectList(queryWrapper);
    }

    @Override
    public void submitApply(SrpmsProjectApplySchStuSaveVo vo,UserVo userVo, DeptVo deptVo) {
        //保存申请书
        long projectid = this.saveOrUpdateApplySchStu(vo, deptVo);

        //更新项目状态
        projectService.submitProject(projectid);



        File pdfFile = null;
        FileInputStream fileInputStream = null;
        try {
            //生成申请书pdf文档
//            String docxFilePath = this.exportWordFile(vo.getId(), userVo, deptVo);
//            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
//            String pdfFilePath = jarPath+ "/"+vo.getProjectName()+"申请书.pdf";
//            WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
            String pdfFilePath = this.exportPdfFile(vo.getId(), userVo, deptVo);
            pdfFile = new File(pdfFilePath);
            fileInputStream = new FileInputStream(pdfFile);

            MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
//            log.info("fileOperatorFeignService.uploadFile resp:" + JSONObject.toJSONString(result));
            if (result.isSuccess()){
                FileInfoVo fileInfoVo = result.getData();
                if (fileInfoVo != null){
                    SrpmsProject projectFile = new SrpmsProject();//项目实体必须重新查询一次，以获取最新的数据
                    projectFile.setId(projectid);
                    projectFile.setApplyBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
                    projectFile.setApplyBookFileName(fileInfoVo.getFileName());
                    projectFile.setApplyBookFileUrl(fileInfoVo.getFileUrl());
                    projectService.updateById(projectFile);
                }
                pdfFile.delete();
            }else {
                log.error("申请书上传文件服务器失败。");
            }

            //发起流程
            log.info("校基科费学生，提交申请书，已更新项目状态, projectId:{}", projectid);
            //发起流程
            flowServicel.startAuditProcess(projectid, VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);

            log.info("校基科费学生，提交申请书，已发起流程, projectId{}", projectid);

        }catch (Exception e){
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            if (pdfFile != null && pdfFile.exists()){
                pdfFile.delete();
            }
        }
    }

    /**
     * 保存
     * @param vo
     * @return
     */
    @Override
    public long saveOrUpdateApplySchStu(SrpmsProjectApplySchStuSaveVo vo, DeptVo deptVo) {
        // 项目ID
        long projectId = 0l;

        // 重大创新项目信息
        SrpmsProjectApplySchStu applyEntity = JSONObject.parseObject(JSON.toJSONString(vo), SrpmsProjectApplySchStu.class);
        JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(vo));
        if (vo.getId() == null) {
            projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.SCHOOL_STU, deptVo);
            applyEntity.setId(projectId);
            applyEntity.setFirstLevelDiscipline(vo.getSubjectCategory() == null ? "" : JSONArray.toJSONString(vo.getSubjectCategory()));
            this.save(applyEntity);
        } else {
            //校验项目状态 已提交的状态后将无修改保存
            SrpmsProject project = projectService.getById(vo.getId());
            if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())) {
                throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
            }
            projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.SCHOOL_STU);
            applyEntity.setFirstLevelDiscipline(vo.getSubjectCategory() == null ? "" : JSONArray.toJSONString(vo.getSubjectCategory()));
            this.updateById(applyEntity);
        }
        return projectId;
    }

    @Override
    public void deleteApplyById(long id) {
        projectService.removeById(id);
        this.removeById(id);
        projectPersonJoinService.deleteByJoinWayAndProjectId(PersonJoinWayEnums.APPLY_MAIN_MEMBER,id);
    }

    @Override
    public SrpmsProjectApplySchStuSaveVo getApplyById(Long id) {

		String jsonStr = commonNclobService.getApplyVo(id);
		if (jsonStr != null && jsonStr.length() != 0) {
			return JSONObject.parseObject(jsonStr, SrpmsProjectApplySchStuSaveVo.class);
		}
		
        SrpmsProjectApplySchStuSaveVo allInfo = new SrpmsProjectApplySchStuSaveVo();

        //获取申请书基本信息
        SrpmsProject project = projectService.getById(id);

        if (null == project) {
            throw new PlatFormException(PlatformErrorType.NO_DATA_FOUND);
        }

        //获取校基科费学生项目信息
        SrpmsProjectApplySchStu stuProject = this.getById(id);

        //获取项目负责人
        SrpmsProjectPerson leadPerson = new SrpmsProjectPerson();
        if (null != project.getLeadPersonId()) {
            leadPerson = projectPersonService.getById(project.getLeadPersonId());
        }
        SrpmsProjectPersonVo leadPersonVo = new SrpmsProjectPersonVo();
        BeanUtils.copyProperties(leadPerson,leadPersonVo);

        //设置项目负责人
//        allInfo.setLeadPerson(leadPersonVo);

        //根据合作类型和项目Id获取项目参与人员
        List<SrpmsProjectPersonJoinVo> personJoins = projectPersonJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_MAIN_MEMBER, id);

        allInfo.setMainMemberList(personJoins);

        BeanUtils.copyProperties(project, allInfo);
        BeanUtils.copyProperties(stuProject,allInfo);

        return allInfo;
    }

    @Override
    public JSONObject queryApplyVoById(Long projectId, UserVo user, DeptVo dept) {
        if (projectId == 0) {
            JSONObject relust = new JSONObject();
            user.setWorkPerYear(null);
            relust.put("leadPerson", user);
            relust.put("leadDept", dept);
            JSONConvert.convertJson(relust);
            return relust;
        }
        JSONObject result = commonService.queryCommonInfoById(projectId, ProjectCategoryEnums.SCHOOL_STU);
        result.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));
        result.put("id", projectId + "");
        if (result.getString("projectKeyword")!=null){
            result.put("projectKeyword", JSONObject.parseArray(result.getString("projectKeyword")));
        }
        if (result.getString("projectBudgetDetail")!=null){
            result.put("projectBudgetDetail", JSONObject.parseArray(result.getString("projectBudgetDetail")));
        }
        result.put("subjectCategory", result.getString("subjectCategory") == null ? "" : JSONArray.parseArray(result.getString("subjectCategory")));
        result.remove("projectCategory");
        JSONConvert.convertJson(result);
        return result;
    }

    @Override
    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
        String docxFilePath = this.exportWordFile(projectId,userVo, deptVo);
        //转格式
        String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
        String pdfFinalName = docxFilePath.replace("docx", "pdf");
        log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
        //合并
        pdfMergeService.mergeAttachmentApply(pdfFilePath, projectId, pdfFinalName);
        return pdfFinalName;
    }

    @Override
    public String exportWordFile(Long projectId,UserVo user, DeptVo dept) {
        String fileUrl = null;
        try {
            if (NumberUtils.LONG_ZERO  == projectId){
                JSONObject dataJson  = new JSONObject();
                dataJson.put("leadDept", dept);
                JSONObject leadPersonJson = (JSONObject)JSON.toJSON(user);
                leadPersonJson.put("birthDate",LocalDateUtils.parse(leadPersonJson.getString("birthDate"),LocalDateUtils.PARRERN_Y_M));
                dataJson.put("leadPerson", leadPersonJson);
                Map dataMap = new HashMap();
                dataMap.put("data",dataJson);
                String filename = "校基科费学生项目申请书_"+ DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999)+".docx";
                fileUrl =  wordExportService.exportWord("template_apply_sch_stu",dataMap, filename);
                return fileUrl;
            }
            SrpmsProject project = projectService.getById(projectId);
            JSONObject dataJson = JSONObject.parseObject(JSONObject.toJSONString(project));

            //获取参与人员
            List<SrpmsProjectPersonJoinVo> mainMemberList = projectPersonJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_MAIN_MEMBER, projectId);
            if (!CollectionUtils.isEmpty(mainMemberList)){
                Integer  highLever   = NumberUtils.INTEGER_ZERO;
                Integer  midLever   = NumberUtils.INTEGER_ZERO;
                Integer  lowLever   = NumberUtils.INTEGER_ZERO;
                Integer  postDoctorNum   = NumberUtils.INTEGER_ZERO;
                Integer  doctorNum   = NumberUtils.INTEGER_ZERO;
                Integer  masterNum   = NumberUtils.INTEGER_ZERO;

                for (SrpmsProjectPersonJoinVo  personJoinVo: mainMemberList){
                    personJoinVo.setBirthDateString(personJoinVo.getBirthDate());
                    String positionTitle = personJoinVo.getPositionTitle();
                    if (StringUtils.isNotBlank(positionTitle)){
                        if (StringUtils.equals("正高级",positionTitle) || StringUtils.equals("副高级",positionTitle) ){
                            highLever++;
                        }else if(StringUtils.equals("中级",positionTitle)){
                            midLever++;
                        }else if (StringUtils.equals("初高级",positionTitle) || StringUtils.equals("初初级",positionTitle)){
                            lowLever++;
                        }
                    }
                    String degree = personJoinVo.getDegree();
                    if (StringUtils.isNotBlank(degree)){
                        if (StringUtils.equals("博士",degree)){
                            doctorNum++;
                        }
                        if (StringUtils.equals("硕士",degree)){
                            masterNum++;
                        }
                    }

                    String personCategory = personJoinVo.getPersonCategory();
                    if (StringUtils.isNotBlank(personCategory)&& StringUtils.equals(personCategory,"3")){
                        postDoctorNum++;
                    }

                }
                //参与人员 分类人数
                dataJson.put("totalNum", mainMemberList.size());
                dataJson.put("highLever",highLever);
                dataJson.put("midLever",midLever);
                dataJson.put("lowLever", lowLever);
                dataJson.put("postDoctorNum", postDoctorNum);
                dataJson.put("doctorNum", doctorNum);
                dataJson.put("masterNum", masterNum);
            }
            dataJson.put("mainMemberList",mainMemberList);
            //格式化富文本编辑器内容
            SrpmsProjectApplySchStu applySchStu = this.getById(projectId);
            if (Objects.equals(null,applySchStu)){
                log.error("未找到对应得申请书。");
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
            }
            applySchStu.setProjectAbstract(WordConvertUtil.htmlToText(applySchStu.getProjectAbstract()));
            applySchStu.setApprovalBasisMeaning(WordConvertUtil.htmlToText(applySchStu.getApprovalBasisMeaning()));
            applySchStu.setContentTargetProblem(WordConvertUtil.htmlToText(applySchStu.getContentTargetProblem()));
            applySchStu.setResearchSchemeFeasibility(WordConvertUtil.htmlToText(applySchStu.getResearchSchemeFeasibility()));
            applySchStu.setProjectFeatureInnovate(WordConvertUtil.htmlToText(applySchStu.getProjectFeatureInnovate()));
            applySchStu.setProjectExpectAchievement(WordConvertUtil.htmlToText(applySchStu.getProjectExpectAchievement()));
            applySchStu.setResearchFoundation(WordConvertUtil.htmlToText(applySchStu.getResearchFoundation()));
            applySchStu.setWorkinfConditions(WordConvertUtil.htmlToText(applySchStu.getWorkinfConditions()));
            applySchStu.setProjectLeaderIntroduction(WordConvertUtil.htmlToText(applySchStu.getProjectLeaderIntroduction()));
            dataJson.putAll(JSONObject.parseObject(JSON.toJSONString(applySchStu)));

            //格式化时间日期
            dataJson.put("createTime", LocalDateUtils.format(project.getCreateTime(),LocalDateUtils.PARRERN_YMD));
            dataJson.put("projectActionDateStart",LocalDateUtils.format(project.getProjectActionDateStart(),LocalDateUtils.PARRERN_Y_M_D));
            dataJson.put("projectActionDateEnd", LocalDateUtils.format(project.getProjectActionDateEnd(),LocalDateUtils.PARRERN_Y_M_D));
            String projectKeyword = applySchStu.getProjectKeyword();
            if (StringUtils.isNotBlank(projectKeyword)){
                JSONArray jsonArray = JSONArray.parseArray(projectKeyword);
                String word = "";
                for (Object kword:jsonArray) {
                    word  = word + kword.toString()+";";
                }
                dataJson.put("projectKeyword",word);
            }

            dataJson.put("leadDept", JSONObject.parseObject(project.getLeadDept()));
            JSONObject leadPersonJson = JSONObject.parseObject(project.getLeadPerson());
            leadPersonJson.put("birthDate",LocalDateUtils.parse(leadPersonJson.getString("birthDate"),LocalDateUtils.PARRERN_Y_M));
            dataJson.put("leadPerson", leadPersonJson);
            // TODO:学科分类  根据code取
            //[{"code":"0710","value":"生物学"},{"code":"071005","value":"微生物学"}]
            String firstLevelDiscipline = applySchStu.getFirstLevelDiscipline();
            Map<String, String> dictByCategory = sysDictService.getDictByCategory(SysDictEnums.SUBJECT_OPTIONS.getCode());
            dataJson.put("levelDiscipline", WordConvertUtil.paseSubjectChooice(dictByCategory,firstLevelDiscipline));

            //计算预算总计
            List<SrpmsProjectBudgetDetailVo> srpmsProjectBudgetDetailVos = budgetDetailService.queryBudgetDetailListByCategory(BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR, projectId);
            if (!CollectionUtils.isEmpty(srpmsProjectBudgetDetailVos)){
                for (SrpmsProjectBudgetDetailVo detailVo : srpmsProjectBudgetDetailVos) {
                    detailVo.setBudgetDetail(JSONArray.parseArray(WordConvertUtil.htmlToText(detailVo.getBudgetDetail().toJSONString())));
                }
            }
            dataJson.put("budgetArray",srpmsProjectBudgetDetailVos);

            Map dataMap = new HashMap();
            dataMap.put("data",dataJson);
            String filename = "校基科费学生项目申请书_"+ DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999)+".docx";
            String finalWordName =  wordExportService.exportWord("template_apply_sch_stu",dataMap, filename);
            fileUrl = finalWordName;
        }catch (Exception e){
            log.error("导出word异常。", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }finally {
        }
        return fileUrl;
    }

    /**
     * 获取学科分类code
     * @param subject
     * @return
     */
    @Override
    public  JSONArray getSubjectCodes (String subject){
        JSONArray subjectCode = new JSONArray();
        if(StringUtils.isBlank(subject)){
            return subjectCode;
        }
        String[] subValues = subject.split("/");
        if (ArrayUtils.isEmpty(subValues) || subValues.length != 2){
           return subjectCode;
        }
        String firstValue = StringUtils.trim(subValues[0]);
        String secendValue = StringUtils.trim(subValues[1]);
        String firstCode = sysDictService.selectCodeByLikeValue(SysDictEnums.SUBJECT_CHOICE,firstValue);
        if (StringUtils.isBlank(firstCode)){
            return subjectCode;
        }

        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<SysDict>();
        queryWrapper.eq("DICT_CODE",firstCode);
        queryWrapper.eq("DICT_VALUE",firstValue);
        List<SysDict> sysDicts = sysDictService.list(queryWrapper);
        if (CollectionUtils.isEmpty(sysDicts) || sysDicts.size() != NumberUtils.INTEGER_ONE){
            return subjectCode;
        }
        SysDict sysDict = sysDicts.get(NumberUtils.INTEGER_ZERO);
        QueryWrapper<SysDict> secWrapper = new QueryWrapper<SysDict>();
        secWrapper.eq("DICT_PARENT",sysDict.getId());
        secWrapper.eq("DICT_VALUE",secendValue);
        List<SysDict> secDicts = sysDictService.list(secWrapper);
        if (CollectionUtils.isEmpty(secDicts)){
            return null;
        }
        SysDict secdDict = secDicts.get(NumberUtils.INTEGER_ZERO);
        subjectCode.add(firstCode);
        subjectCode.add(secdDict.getDictCode());
        return subjectCode;
    }
    @Override
    public SrpmsProjectApplySchStuSaveVo importWord(String wordFileUrl) {
        SrpmsProjectApplySchStuSaveVo  applySchStuSaveVo  = new SrpmsProjectApplySchStuSaveVo();
        try {
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
            //学科分类
            Elements subjects = document.getElementsMatchingOwnText("（学生项目）");
            if (!CollectionUtils.isEmpty(subjects)){
                Element element = subjects.get(0);
                String subGrah  = element.parent().nextElementSibling().nextElementSibling().nextElementSibling().text();
                if (StringUtils.isNotBlank(subGrah)){
                    String subject =  StringUtils.replace(subGrah,"学 科 分 类:","");
                    applySchStuSaveVo.setFirstLevelDiscipline(getSubjectCodes(subject));
                }
            }

            Elements tableElements = document.getElementsByTag("table");
            //导师基本信息
            Element tutorTable = tableElements.get(0);
            String advisorName = extractCellFromTable(tutorTable, 2, 2);
            applySchStuSaveVo.setAdvisorName(advisorName);
            String advisorPositionTitle = extractCellFromTable(tutorTable, 2, 4);
            applySchStuSaveVo.setAdvisorPositionTitle(advisorPositionTitle);
            String advisorResearchDirection = extractCellFromTable(tutorTable, 2, 6);
            applySchStuSaveVo.setAdvisorResearchDirection(advisorResearchDirection);
            String advisorPhone = extractCellFromTable(tutorTable, 3, 1);
            applySchStuSaveVo.setAdvisorPhone(advisorPhone);
            String advisorDept = extractCellFromTable(tutorTable, 3, 3);
            applySchStuSaveVo.setAdvisorDept(advisorDept);
            String projectName = extractCellFromTable(tutorTable, 4, 2);
            applySchStuSaveVo.setProjectName(projectName);
            String applyFunds = extractCellFromTable(tutorTable, 5, 1);
            applySchStuSaveVo.setApplyFunds(NumberUtils.toDouble(applyFunds));
            String startEndDate = extractCellFromTable(tutorTable, 5, 3);
            if (StringUtils.isNotBlank(startEndDate)){
                String[] prodates = startEndDate.split("至");
                if (prodates.length == 2){
                    applySchStuSaveVo.setProjectActionDateStart(DateUtil.chinaToLocalDateTime(prodates[0],DateUtil.PATTERN_CLASSICAL_SIMPLE));
                    applySchStuSaveVo.setProjectActionDateEnd(DateUtil.chinaToLocalDateTime(prodates[1],DateUtil.PATTERN_CLASSICAL_SIMPLE));
                }
            }

            String projectAbstract = extractCellFromTable(tutorTable, 6, 1,true);
            applySchStuSaveVo.setProjectAbstract(projectAbstract);
            String keyword = extractCellFromTable(tutorTable, 7, 1);
            if (StringUtils.isNotBlank(keyword)){
                String[] split = keyword.split(";");
                JSONArray  jsonArray  = new JSONArray();
                for (String  kword:split) {
                    jsonArray.add(kword);
                }
                applySchStuSaveVo.setProjectKeyword(jsonArray);
            }
            //参与人信息
//            Element menberTable = tableElements.get(1);
//            Elements table0Rows = menberTable.getElementsByTag("tr");
//            List<List<String>> mainMenbers = extractListTableRows(menberTable, 1, table0Rows.size());
//            if (!CollectionUtils.isEmpty(mainMenbers)){
//                List<SrpmsProjectPersonJoinVo> mostMemberList  = new ArrayList<>();
//                for (List<String> listrows:mainMenbers){
//                    if (CollectionUtils.isEmpty(listrows)){
//                        continue;
//                    }
//                    SrpmsProjectPersonJoinVo  personJoinVo  = new SrpmsProjectPersonJoinVo();
//                    personJoinVo.setPersonName(listrows.get(1));
//                    personJoinVo.setBirthDateString(listrows.get(2));
//                    personJoinVo.setBirthDate(listrows.get(2));
//                    personJoinVo.setGender(listrows.get(3));
//                    personJoinVo.setPositionTitle(listrows.get(4));
//                    personJoinVo.setDegree(listrows.get(5));
//                    personJoinVo.setDeptName(listrows.get(6));
//                    personJoinVo.setPhone(listrows.get(7));
//                    personJoinVo.setBelongTask(listrows.get(8));
//                    personJoinVo.setWorkPerYear(NumberUtils.toInt(listrows.get(9)));
//                    mostMemberList.add(personJoinVo);
//                }
//                applySchStuSaveVo.setMainMemberList(mostMemberList);
//            }

            //立项依据相关介绍相关信息
            Element approTable = tableElements.get(3);
            applySchStuSaveVo.setApprovalBasisMeaning(extractCellFromTable(approTable, 1, 0,true));
            applySchStuSaveVo.setContentTargetProblem(extractCellFromTable(approTable, 3, 0,true));
            applySchStuSaveVo.setResearchSchemeFeasibility(extractCellFromTable(approTable, 5, 0,true));
            applySchStuSaveVo.setProjectFeatureInnovate(extractCellFromTable(approTable, 7, 0,true));
            applySchStuSaveVo.setProjectExpectAchievement(extractCellFromTable(approTable, 9, 0,true));
            applySchStuSaveVo.setResearchFoundation(extractCellFromTable(approTable, 11, 0,true));
            applySchStuSaveVo.setWorkinfConditions(extractCellFromTable(approTable, 13, 0,true));
            applySchStuSaveVo.setProjectLeaderIntroduction(extractCellFromTable(approTable, 15, 0,true));

            //预算明细
//            List<SrpmsProjectBudgetDetailVo> projectBudgetDetail = new ArrayList<>();
//            for (int i=4;i<tableElements.size();i++) {
//                Element budgetTable = tableElements.get(i);
//                Elements budRows = budgetTable.getElementsByTag("tr");
//                SrpmsProjectBudgetDetailVo  budgetDetailVo = new SrpmsProjectBudgetDetailVo();
//                budgetDetailVo.setBudgetYear(StringConvertUtil.convert(extractCellFromTable(budgetTable, 0, 0)));
//                budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(budgetTable, budRows.size()-1, 1)));
//                List<List<String>> budgetDetails = extractListTableRows(budgetTable, 1, budRows.size());
//                if (!CollectionUtils.isEmpty(budgetDetails)){
//                    JSONArray  budgetDetail  =   new JSONArray();
//                    budgetDetail.add(createJson(budgetDetails,"(一)","(二)",1));
//                    budgetDetail.add(createJson(budgetDetails,"(二)","(三)",2));
//                    budgetDetail.add(createJson(budgetDetails,"(三)","(四)",3));
//                    budgetDetailVo.setBudgetDetail(budgetDetail);
//                }
//                projectBudgetDetail.add(budgetDetailVo);
//            }
//            applySchStuSaveVo.setBudgetPreYearList(projectBudgetDetail);

        } catch (Exception e) {
            log.error("解析word文件发生异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
        return applySchStuSaveVo;
    }

    /**
     *  填充内容
     * [{"amount":0,"serial":1,"name":"(一)研究经费","child":[{"amount":0,"serial":1,"name":"测试/计算/分析费"},
     * {"amount":0,"serial":2,"name":"会议费/差旅费(<10%)"},{"amount":0,"serial":3,"name":"出版物/文献/信息传播费"},
     * {"amount":0,"serial":4,"name":"原材料/试剂/药品购置费"},{"amount":0,"serial":5,"name":"仪器设备费(<10%)"}]},
     * {"amount":0,"serial":2,"name":"(二)国际合作与交流费","child":[{"amount":0,"serial":1,"name":"项目组成员出国合作交流"},
     * {"amount":0,"serial":2,"name":"境外专家来华合作交流"}]},{"amount":0,"serial":3,"name":"(三)人员费","child":
     * [{"amount":0,"serial":1,"name":"专家咨询费(<5%)"},{"amount":0,"serial":2,"name":"劳务费(<10%)"}]}]
     */
    private  static JSONObject  createJson (List<List<String>> budgetDetails,String start,String end,Integer serial){
        JSONObject jsonObject = new JSONObject();
        JSONArray  child = new JSONArray();
        Iterator<List<String>> iterator = budgetDetails.iterator();
        while (iterator.hasNext()){
            List<String>  budget  = iterator.next();
            if (CollectionUtils.isEmpty(budget)) {
                continue;
            }
            String name = budget.get(0);
            if (StringUtils.isBlank(name)){
                continue;
            }
            if (StringUtils.contains(name,end)) {
                break;
            }
            String amount = budget.get(1);
            String remark = budget.get(2);
            if (StringUtils.contains(name,start)) {
                jsonObject.put("serial", serial);
                jsonObject.put("name", name);
                jsonObject.put("amount", amount);
                jsonObject.put("remark", remark);
                iterator.remove();
                continue;
            }

            JSONObject childObject = new JSONObject();
            String[] namesplit = name.split("\\.");
            if (namesplit.length <=NumberUtils.INTEGER_ONE){
                continue;
            }
            childObject.put("serial", namesplit[NumberUtils.INTEGER_ZERO]);
            childObject.put("name", namesplit[NumberUtils.INTEGER_ONE]);
            childObject.put("amount", amount);
            childObject.put("remark", remark);
            child.add(childObject);
            iterator.remove();
        }
        jsonObject.put("child",child);
        return jsonObject;
    }
    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectApplySchStu> getQueryWrapper(QueryWrapper<SrpmsProjectApplySchStu> queryWrapper,BaseQueryForm<SrpmsProjectApplySchStuQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getAdvisorName())){
            queryWrapper.like(SrpmsProjectApplySchStu.ADVISOR_NAME,srpmsProjectApplySchStu.getAdvisorName());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getAdvisorPositionTitle())){
            queryWrapper.like(SrpmsProjectApplySchStu.ADVISOR_POSITION_TITLE,srpmsProjectApplySchStu.getAdvisorPositionTitle());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getAdvisorResearchDirection())){
            queryWrapper.like(SrpmsProjectApplySchStu.ADVISOR_RESEARCH_DIRECTION,srpmsProjectApplySchStu.getAdvisorResearchDirection());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getAdvisorPhone())){
            queryWrapper.like(SrpmsProjectApplySchStu.ADVISOR_PHONE,srpmsProjectApplySchStu.getAdvisorPhone());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getAdvisorDept())){
            queryWrapper.like(SrpmsProjectApplySchStu.ADVISOR_DEPT,srpmsProjectApplySchStu.getAdvisorDept());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getApplyFunds())){
            queryWrapper.like(SrpmsProjectApplySchStu.APPLY_FUNDS,srpmsProjectApplySchStu.getApplyFunds());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getProjectKeyword())){
            queryWrapper.like(SrpmsProjectApplySchStu.PROJECT_KEYWORD,srpmsProjectApplySchStu.getProjectKeyword());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getProjectAbstract())){
            queryWrapper.like(SrpmsProjectApplySchStu.PROJECT_ABSTRACT,srpmsProjectApplySchStu.getProjectAbstract());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getApprovalBasisMeaning())){
            queryWrapper.like(SrpmsProjectApplySchStu.APPROVAL_BASIS_MEANING,srpmsProjectApplySchStu.getApprovalBasisMeaning());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getContentTargetProblem())){
            queryWrapper.like(SrpmsProjectApplySchStu.CONTENT_TARGET_PROBLEM,srpmsProjectApplySchStu.getContentTargetProblem());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getResearchSchemeFeasibility())){
            queryWrapper.like(SrpmsProjectApplySchStu.RESEARCH_SCHEME_FEASIBILITY,srpmsProjectApplySchStu.getResearchSchemeFeasibility());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getProjectFeatureInnovate())){
            queryWrapper.like(SrpmsProjectApplySchStu.PROJECT_FEATURE_INNOVATE,srpmsProjectApplySchStu.getProjectFeatureInnovate());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getProjectExpectAchievement())){
            queryWrapper.like(SrpmsProjectApplySchStu.PROJECT_EXPECT_ACHIEVEMENT,srpmsProjectApplySchStu.getProjectExpectAchievement());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getResearchFoundation())){
            queryWrapper.like(SrpmsProjectApplySchStu.RESEARCH_FOUNDATION,srpmsProjectApplySchStu.getResearchFoundation());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getWorkinfConditions())){
            queryWrapper.like(SrpmsProjectApplySchStu.WORKINF_CONDITIONS,srpmsProjectApplySchStu.getWorkinfConditions());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getProjectLeaderIntroduction())){
            queryWrapper.like(SrpmsProjectApplySchStu.PROJECT_LEADER_INTRODUCTION,srpmsProjectApplySchStu.getProjectLeaderIntroduction());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getProjectBudgetDetail())){
            queryWrapper.like(SrpmsProjectApplySchStu.PROJECT_BUDGET_DETAIL,srpmsProjectApplySchStu.getProjectBudgetDetail());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getCreateTime())){
            queryWrapper.like(SrpmsProjectApplySchStu.CREATE_TIME,srpmsProjectApplySchStu.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getCreateBy())){
            queryWrapper.like(SrpmsProjectApplySchStu.CREATE_BY,srpmsProjectApplySchStu.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getUpdateTime())){
            queryWrapper.like(SrpmsProjectApplySchStu.UPDATE_TIME,srpmsProjectApplySchStu.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getUpdateBy())){
            queryWrapper.like(SrpmsProjectApplySchStu.UPDATE_BY,srpmsProjectApplySchStu.getUpdateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getFirstLevelDiscipline())){
            queryWrapper.like(SrpmsProjectApplySchStu.FIRST_LEVEL_DISCIPLINE,srpmsProjectApplySchStu.getFirstLevelDiscipline());
        }
        if(StringUtils.isNotBlank(srpmsProjectApplySchStu.getSecondLevelDiscipline())){
            queryWrapper.like(SrpmsProjectApplySchStu.SECOND_LEVEL_DISCIPLINE,srpmsProjectApplySchStu.getSecondLevelDiscipline());
        }
        return queryWrapper;
    }
     */
}

