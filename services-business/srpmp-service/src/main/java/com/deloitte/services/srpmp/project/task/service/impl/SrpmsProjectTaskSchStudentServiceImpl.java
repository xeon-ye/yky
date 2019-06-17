package com.deloitte.services.srpmp.project.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.StringConvertUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchStu;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchTeach;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplySchStuMapper;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplySchTeachMapper;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskSchStudent;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskSchStudentMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchStudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.*;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListTableRows;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-14
 * @Description :  SrpmsProjectTaskSchStudent服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectTaskSchStudentServiceImpl extends ServiceImpl<SrpmsProjectTaskSchStudentMapper, SrpmsProjectTaskSchStudent> implements ISrpmsProjectTaskSchStudentService {


    @Autowired
    private SrpmsProjectTaskSchStudentMapper srpmsProjectTaskMapper;

    @Autowired
    private SrpmsProjectApplySchStuMapper srpmsProjectApplyStuMapper;

    @Autowired
    private SrpmsProjectApplySchTeachMapper srpmsProjectApplyTeaMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private ISrpmsProjectBudgetDetailService budgetDetailService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private PdfMergeServiceImpl pdfMergeService;

    @Override
    public String exportPdf(Long projectId, String template, UserVo userVo, DeptVo deptVo) throws Exception {
        String docxFilePath = this.exportWordFile(projectId, template, userVo, deptVo);
        //转格式
        String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
        String pdfFinalName = docxFilePath.replace("docx", "pdf");
        log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
        //合并
        pdfMergeService.mergeAttachmentTaskType2(pdfFilePath, projectId, pdfFinalName);
        return pdfFinalName;
    }

    /**
     * 根据ID查询基科费任务书service接口实现
     *
     * @param projectId
     * @return
     */
    @Override
    public SrpmsProjectTaskSchStudentVo queryById(Long projectId) {
        SrpmsProjectTaskSchStudentVo taskVo = new SrpmsProjectTaskSchStudentVo();

        //获取项目信息
        SrpmsProjectVo srpmsProjectVo = new SrpmsProjectVo();
        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }

        if (StringUtils.isNotBlank(projectEntity.getSubjectCategory())) {
            taskVo.setSubjectCategory(JSONArray.parseArray(projectEntity.getSubjectCategory()));
        }

        PersonJoinWayEnums personJoinWay;
        BudgetCategoryEnums budgetCategoryEnums;

        // 查询基科费任务书数据
        SrpmsProjectTaskSchStudent srpmsProjectTask = srpmsProjectTaskMapper.selectById(projectId);

        taskVo.setFirstFlg("false");

        if (srpmsProjectTask == null) {
            budgetCategoryEnums = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR;
            // 查询基科费学生申请书数据
            SrpmsProjectApplySchStu srpmsProjectApplyStu = srpmsProjectApplyStuMapper.selectById(projectId);

            if (srpmsProjectApplyStu == null) {
                // 查询基科费教师申请书数据
                SrpmsProjectApplySchTeach srpmsProjectApply = srpmsProjectApplyTeaMapper.selectById(projectId);
                if (srpmsProjectApply == null) {
                    throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
                }
                BeanUtils.copyProperties(srpmsProjectApply, taskVo);
                taskVo.setBaseScienceStuType(srpmsProjectApply.getBaseScienceTchType());
            } else {
                BeanUtils.copyProperties(srpmsProjectApplyStu, taskVo);
            }

            personJoinWay = PersonJoinWayEnums.APPLY_MAIN_MEMBER;

            if ("true".equals(projectEntity.getTaskFirstOpenFlg())) {
                taskVo.setFirstFlg("true");
                projectEntity.setTaskFirstOpenFlg("false");
                projectService.updateById(projectEntity);
            }

        } else {
            budgetCategoryEnums = BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL;
            personJoinWay = PersonJoinWayEnums.TASK_MAIN_MEMBER;
            BeanUtils.copyProperties(srpmsProjectTask, taskVo);
            taskVo.setResearchYearPlan(srpmsProjectTask.getResearchYearPlan() == null ? null : JSONObject.parseArray(srpmsProjectTask.getResearchYearPlan()));
        }
        taskVo.setId(CommonUtil.objectToString(projectId));// 主键赋值

        taskVo.setProjectName(projectEntity.getProjectName());// 项目名称
        taskVo.setProjectActionDateStart(projectEntity.getProjectActionDateStart());// 项目执行开始时间
        taskVo.setProjectActionDateEnd(projectEntity.getProjectActionDateEnd());// 项目执行结束时间

        BeanUtils.copyProperties(projectEntity, srpmsProjectVo);

        srpmsProjectVo.setId(projectId);
        if (StringUtils.isNotBlank(projectEntity.getLeadPerson())) {

            JSONObject jsonPerson = JSONObject.parseObject(projectEntity.getLeadPerson());
            if (jsonPerson != null && StringUtils.isBlank(jsonPerson.getString("nation"))) {
                Result<UserVo> userVoResult = userFeignService.get(jsonPerson.getLong("id"));
                // 在4A查询信息
                if (userVoResult.getData() instanceof UserVo && userVoResult.getData() != null) {
                    jsonPerson.put("nation", userVoResult.getData().getNation());
                }
            }
            taskVo.setLeadPerson(jsonPerson);
        }
        if (StringUtils.isNotBlank(projectEntity.getLeadDept())) {
            taskVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));
        }
        taskVo.setSrpmsProjectVo(srpmsProjectVo);

        // 项目参加人员
        List<SrpmsProjectPersonJoinVo> personList = personJoinService.queryPersonJoinListByJoinWay(personJoinWay, projectId);
        if (personList != null && personList.size() > 0) {
            taskVo.setMostMemberList(personList);
        } else {
            taskVo.setMostMemberList(new ArrayList<>());
        }

        //年度预算
        List<SrpmsProjectBudgetDetailVo> detailVoList = budgetDetailService.queryBudgetDetailListByCategory(budgetCategoryEnums, projectId);
        if (detailVoList != null && detailVoList.size() > 0) {
            taskVo.setExpenditureBudgetDetail(detailVoList);
        }

        // 相关附件
        JSONObject attachmentJson = attachmentService.queryAttachmentListTaskType2(projectId);
        taskVo.setAttachmentCommittee(attachmentJson.getJSONObject("attachmentCommittee"));
        taskVo.setAttachmentAudit(attachmentJson.getJSONObject("attachmentAudit"));

        return taskVo;
    }

    /**
     * 保存基科费任务书操作service接口实现
     *
     * @param taskForm
     * @return
     */
    @Override
    public Result saveSrpmsProjectTask(SrpmsProjectTaskSchStudentForm taskForm, boolean taskFlag) {
        long projectId = taskForm.getId();

        // 更新项目信息中负责人信息
        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        if (taskForm.getLeadPerson() != null) {
            projectEntity.setLeadPerson(JSONObject.toJSONString(taskForm.getLeadPerson()));
        }
        // 学科分类信息
        if(taskForm.getSubjectCategory() != null) {
            projectEntity.setSubjectCategory(JSONArray.toJSONString(taskForm.getSubjectCategory()));
        }
        if(taskForm.getProjectActionDateStart() != null) {
            projectEntity.setProjectActionDateStart(taskForm.getProjectActionDateStart());
        }
        if(taskForm.getProjectActionDateEnd() != null) {
            projectEntity.setProjectActionDateEnd(taskForm.getProjectActionDateEnd());
        }
        if(StringUtils.isNotBlank(taskForm.getProjectName())) {
            projectEntity.setProjectName(taskForm.getProjectName());
        }
        projectService.saveOrUpdate(projectEntity);


        // 更新项目参加人员
        PersonJoinWayEnums personJoinWay = PersonJoinWayEnums.TASK_MAIN_MEMBER;
        personJoinService.cleanAndSavePersonJoin(taskForm.getMostMemberList(), personJoinWay, projectId);

        // 保存/更新基科费任务书
        SrpmsProjectTaskSchStudent srpmsProjectTask = new SrpmsProjectTaskSchStudent();
        BeanUtils.copyProperties(taskForm, srpmsProjectTask);
        srpmsProjectTask.setId(projectId);
        srpmsProjectTask.setResearchYearPlan(taskForm.getResearchYearPlan() == null ? null : taskForm.getResearchYearPlan().toJSONString());
        if (taskFlag && taskForm.getId() == null) {
            this.save(srpmsProjectTask);// 保存操作
        } else {
            this.saveOrUpdate(srpmsProjectTask);// 更新操作
        }

        //年度预算
        if (taskForm.getExpenditureBudgetDetail() != null) {
            budgetDetailService.cleanAndSaveBudgetDetail(taskForm.getExpenditureBudgetDetail(), BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL, projectId);
        } else {
            budgetDetailService.cleanAndSaveBudgetDetail(null, BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL, projectId);
        }

        // 附件 and 任务书签订各方意见及签章
        JSONObject attachmentVoJson = new JSONObject();
        attachmentVoJson.put("attachmentCommittee", taskForm.getAttachmentCommittee());
        attachmentVoJson.put("attachmentAudit", taskForm.getAttachmentAudit());

        attachmentService.saveAttachmentListTaskType2(attachmentVoJson, projectId);

        return Result.success(projectId);
    }

    /**
     * 提交基科费任务书操作service接口实现
     *
     * @param taskForm
     * @return
     */
    @Override
    public Result submitSrpmsProjectTask(SrpmsProjectTaskSchStudentForm taskForm, UserVo userVo, DeptVo deptVo) {
        long projectId = taskForm.getId();

        saveSrpmsProjectTask(taskForm, true);

        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        if (!SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(projectEntity.getStatus())) {
            throw new BaseException(SrpmsErrorType.PUBLICED_CAN_SUBMITTED);
        }

        boolean checkFlag = false;
        StringBuilder stringBuilder = new StringBuilder();
        checkFlag = checkTask(taskForm, checkFlag, stringBuilder);

        // 提交的时候验证数据
        if (checkFlag) {
            String errorMsg = stringBuilder.toString();
            errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
            return new Result(SrpmsErrorType.TASK_NOT_COMPLETE.getCode(), errorMsg, null);
        }

        //生成申请书pdf文档
        File pdfFile = null;
        FileInputStream fileInputStream = null;
        try {
//            String docxFilePath = this.exportWordFile(projectId, "template_task_sch_stu",userVo, deptVo);
//            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
//            String pdfFilePath = jarPath+ "/"+taskForm.getProjectName()+"任务书.pdf";
//            WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
            String pdfFilePath = this.exportPdf(projectId, "template_task_sch_stu",userVo, deptVo);
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
                    projectFile.setId(projectId);
                    projectFile.setTaskBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
                    projectFile.setTaskBookFileName(fileInfoVo.getFileName());
                    projectFile.setTaskBookFileUrl(fileInfoVo.getFileUrl());
                    projectService.updateById(projectFile);
                }
                pdfFile.delete();
            }else {
                log.error("任务书上传文件服务器失败。");
            }
        } catch (Exception e) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            if (pdfFile != null && pdfFile.exists()){
                pdfFile.delete();
            }
        }
        //更新项目状态
        projectService.submitTaskProject(projectId);

        log.info("校基科费学生，提交任务书，已更新项目状态, projectId:{}", taskForm.getId());
        //发起流程
        flowServicel.startAuditProcess(taskForm.getId(), VoucherTypeEnums.TASK_BOOK, userVo, deptVo);

        log.info("校基科费学生，提交申请书，已发起流程, projectId:{}", taskForm.getId());


        return Result.success(projectId);
    }

    @Override
    public String exportWordFile(Long projectId, String template, UserVo userVo, DeptVo deptVo) {
        String fileUrl = null;
        try {
            if (NumberUtils.LONG_ZERO  == projectId){
                JSONObject dataJson  = new JSONObject();
                dataJson.put("leadDept", deptVo);
                JSONObject leadPersonJson = (JSONObject)JSON.toJSON(userVo);
                leadPersonJson.put("birthDate",LocalDateUtils.parse(leadPersonJson.getString("birthDate"),LocalDateUtils.PARRERN_Y_M_D));
                dataJson.put("leadPerson", leadPersonJson);
                Map dataMap = new HashMap();
                dataJson.put("applyYear", StringConvertUtil.convertNumToWord(LocalDateTimeUtils.formatNow("yyyy")));
                dataMap.put("data",dataJson);
                String filename = "校基科费项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
                fileUrl = wordExportService.exportWord(template, dataMap, filename);
                return fileUrl;
            }

            SrpmsProjectTaskSchStudentVo srpmsProjectTaskSchStudentVo = this.queryById(projectId);
            List<SrpmsProjectPersonJoinVo> mostMemberList = srpmsProjectTaskSchStudentVo.getMostMemberList();
            if (!CollectionUtils.isEmpty(mostMemberList)) {
                for (SrpmsProjectPersonJoinVo personJoinVo : mostMemberList) {
                    personJoinVo.setBirthDateString(personJoinVo.getBirthDate());
                }
            }

            List<SrpmsProjectBudgetDetailVo> projectBudgetDetail = srpmsProjectTaskSchStudentVo.getExpenditureBudgetDetail();
            if (!CollectionUtils.isEmpty(projectBudgetDetail)){
                for (SrpmsProjectBudgetDetailVo detailVo : projectBudgetDetail) {
                    detailVo.setBudgetDetail(JSONArray.parseArray(detailVo.getBudgetDetail().toJSONString()));
                }
            }
            srpmsProjectTaskSchStudentVo.setProjectAbstract(WordConvertUtil.htmlToText(srpmsProjectTaskSchStudentVo.getProjectAbstract()));
            srpmsProjectTaskSchStudentVo.setResultAssessmentIndicators(WordConvertUtil.htmlToText(srpmsProjectTaskSchStudentVo.getResultAssessmentIndicators()));


            JSONObject dataJson = JSONObject.parseObject(JSONObject.toJSONString(srpmsProjectTaskSchStudentVo));
            //格式化时间日期
            dataJson.put("createTime", LocalDateUtils.format(srpmsProjectTaskSchStudentVo.getCreateTime(), LocalDateUtils.PARRERN_YMD));
            dataJson.put("projectActionDateStart", LocalDateUtils.format(srpmsProjectTaskSchStudentVo.getProjectActionDateStart(), LocalDateUtils.PARRERN_YM));
            dataJson.put("projectActionDateEnd", LocalDateUtils.format(srpmsProjectTaskSchStudentVo.getProjectActionDateEnd(), LocalDateUtils.PARRERN_YM));
            JSONObject leadPersonJson = srpmsProjectTaskSchStudentVo.getLeadPerson();
            leadPersonJson.put("birthDate", LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
            dataJson.put("leadPerson", leadPersonJson);

            dataJson.put("applyYear", StringConvertUtil.convertNumToWord(LocalDateTimeUtils.formatNow("yyyy")));

            JSONArray researchYearPlan = srpmsProjectTaskSchStudentVo.getResearchYearPlan();
            if (!CollectionUtils.isEmpty(researchYearPlan)){
                researchYearPlan = JSONArray.parseArray(WordConvertUtil.htmlToText(researchYearPlan.toJSONString()));
            }
            dataJson.put("researchYearPlan", researchYearPlan);

            Map dataMap = new HashMap();
            dataMap.put("data", dataJson);
            String filename = "校基科费项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
            String finalWordName = wordExportService.exportWord(template, dataMap, filename);
            fileUrl = finalWordName;
        } catch (Exception e) {
            log.error("导出word异常。", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
        }
        return fileUrl;
    }


    /**
     * 任务书提交check验证
     *
     * @param taskForm
     * @param checkFlag
     * @param errorMsg
     * @return
     */
    public boolean checkTask(SrpmsProjectTaskSchStudentForm taskForm, boolean checkFlag, StringBuilder errorMsg) {
        // 项目编号
        errorMsg.append("任务书-校基科费-学生项目提交数据校验不通过字段：");

        // 项目摘要
        if (taskForm.getProjectAbstract() == null) {
            checkFlag = true;
            errorMsg.append("项目摘要为空，");
        }

        // 提交成果形式和主要考核指标
        if (taskForm.getResultAssessmentIndicators() == null) {
            checkFlag = true;
            errorMsg.append("提交成果形式和主要考核指标为空，");
        }

        // 年度计划
        if (taskForm.getResearchYearPlan() == null) {
            checkFlag = true;
            errorMsg.append("研究计划及指标完成情况为空，");
        }

        return checkFlag;
    }


    /**
     * word导入项目任务书
     * @param wordFileUrl word文件URL地址
     */
    @Override
    public SrpmsProjectTaskSchStudentForm importWord(String wordFileUrl){
        SrpmsProjectTaskSchStudentForm  taskSchStudentForm  = new SrpmsProjectTaskSchStudentForm();
        try{
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
            Elements tableElements = document.getElementsByTag("table");
            //项目基本信息
            Element projectBaseTable = tableElements.get(0);
            //项目名称
            taskSchStudentForm.setProjectName(extractCellFromTable(projectBaseTable, 0, 1));
            //内容摘要
            taskSchStudentForm.setProjectAbstract(extractCellFromTable(projectBaseTable, 4, 1,true));
            //项目参与人员
//            Elements table0Rows = projectBaseTable.getElementsByTag("tr");
//            List<List<String>> mainMenbers = extractListTableRows(projectBaseTable, 6, table0Rows.size());
//            if (!CollectionUtils.isEmpty(mainMenbers)){
//                List<SrpmsProjectPersonJoinVo> mostMemberList  = new ArrayList<>();
//                for (List<String> listrows:mainMenbers){
//                    if (CollectionUtils.isEmpty(listrows)){
//                        continue;
//                    }
//                    SrpmsProjectPersonJoinVo  personJoinVo  = new SrpmsProjectPersonJoinVo();
//                    personJoinVo.setPersonName(listrows.get(0));
//                    personJoinVo.setGender(listrows.get(1));
//                    personJoinVo.setBirthDateString(listrows.get(2));
//                    personJoinVo.setBirthDate(listrows.get(2));
//                    personJoinVo.setPositionTitle(listrows.get(3));
//                    personJoinVo.setDeptName(listrows.get(4));
//                    mostMemberList.add(personJoinVo);
//                }
//                taskSchStudentForm.setMostMemberList(mostMemberList);
//            }

            //提交成果形式和主要考核指标
            Element resultTable = tableElements.get(1);
            taskSchStudentForm.setResultAssessmentIndicators(extractCellFromTable(resultTable, 0, 0,true));
            /**
             *  年度计划 格式如下
             *  [{"time":["19-03-13","19-04-17"],"text":"<p>暗杀哈哈<br/></p>"},{"time":["19-03-13","19-04-24"],"text":"<p>萨嘎哈哈哈<br/></p>"}]
             */
            Element planTable = tableElements.get(2);
            Elements planRows = planTable.getElementsByTag("tr");
            List<List<String>> yearPlans = extractListTableRows(planTable, 1, planRows.size());
            if (!CollectionUtils.isEmpty(yearPlans)){
                JSONArray  researchYearPlan  =  new JSONArray();
                for (List<String> plan: yearPlans) {
                    if (CollectionUtils.isEmpty(plan)){
                       continue;
                    }
                    JSONObject  jsonObject = new JSONObject();
                    //填充内容
                    String planTimes = plan.get(0);
                    String[] times = planTimes.split("至");
                    if (ArrayUtils.isNotEmpty(times) && times.length == 2 ){
                        LocalDateTime[] tmpTimes = new LocalDateTime[2];
                        for (int i=0;i<times.length; i++){
                            tmpTimes[i] = DateUtil.chinaToLocalDateTime(times[i].trim(),LocalDateUtils.PARRERN_Y_M_D);
                        }
                        jsonObject.put("time",tmpTimes);
                    }
                    String planText = plan.get(1);
                    jsonObject.put("text",planText);
                    researchYearPlan.add(jsonObject);
                }
                taskSchStudentForm.setResearchYearPlan(researchYearPlan);
            }
            //预算明细
//            List<SrpmsProjectBudgetDetailVo> projectBudgetDetail = new ArrayList<>();
//            for (int i=3;i<tableElements.size();i++) {
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
//              }
//                projectBudgetDetail.add(budgetDetailVo);
//            }
//            taskSchStudentForm.setExpenditureBudgetDetail(projectBudgetDetail);
            return taskSchStudentForm;
        }catch (Exception e){
            log.error("解析word文件发生异常.", e);
            throw new BaseException(PlatformErrorType.IMPORT_TEMPLATE_ERROR);
        }
    }

    /**
     *  填充内容
     *  [{"serial":1,"name":"(一)科研业务费","amount":4,"amount1":0,"amount2":0,"child":[{"serial":1,"name":"材料/计算/分析费","amount":"1","amount1":0,"amount2":0},
     * {"serial":2,"name":"会议费/差旅费/国际合作费","amount":"1","amount1":0,"amount2":0},{"serial":3,"name":"出版物/文献/信息传播费","amount":"1","amount1":0,"amount2":0},
     * {"serial":4,"name":"仪器设备购置/租凭费","amount":"1","amount1":0,"amount2":0}]},{"serial":2,"name":"(二)人员费","amount":2,"amount1":0,"amount2":0,
     * "child":[{"serial":1,"name":"专家咨询费","amount":"1","amount1":0,"amount2":0},{"serial":2,"name":"劳务费","amount":"1","amount1":0,"amount2":0}]},
     * {"serial":3,"name":"(三)其他(注详细事项)","amount":1,"amount1":0,"amount2":0,"child":[{"serial":1,"name":"专家咨询费","amount":"1","amount1":0,"amount2":0}]}]
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
}

