package com.deloitte.services.srpmp.project.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.DateParseUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyAcademy;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyAcademyMapper;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.*;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskAcademy;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskAcademyMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskAcademyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-13
 * @Description :  SrpmsProjectTaskAcademy服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectTaskAcademyServiceImpl extends ServiceImpl<SrpmsProjectTaskAcademyMapper, SrpmsProjectTaskAcademy> implements ISrpmsProjectTaskAcademyService {


    @Autowired
    private SrpmsProjectTaskAcademyMapper srpmsProjectTaskMapper;

    @Autowired
    private SrpmsProjectApplyAcademyMapper srpmsProjectApplyMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private ISrpmsProjectBudgetDetailService budgetDetailService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    //导出word service
    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private PdfMergeServiceImpl pdfMergeService;

    @Override
    public String exportPdf(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
        String docxFilePath = this.exportWordFile(projectId,userVo, deptVo);
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
    public SrpmsProjectTaskAcademyVo queryById(Long projectId) {
        SrpmsProjectTaskAcademyVo taskVo = new SrpmsProjectTaskAcademyVo();

        //获取项目信息
        SrpmsProjectVo srpmsProjectVo = new SrpmsProjectVo();
        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        PersonJoinWayEnums personJoinWay;
        BudgetCategoryEnums budgetCategoryEnums;

        // 查询基科费任务书数据
        SrpmsProjectTaskAcademy srpmsProjectTask = srpmsProjectTaskMapper.selectById(projectId);
        taskVo.setFirstFlg("false");
        if (srpmsProjectTask == null) {
            budgetCategoryEnums = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR;
            // 查询基科费申请书数据
            SrpmsProjectApplyAcademy srpmsProjectApply = srpmsProjectApplyMapper.selectById(projectId);
            if (srpmsProjectApply == null) {
                throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
            }
            personJoinWay = PersonJoinWayEnums.APPLY_MAIN_MEMBER;
            BeanUtils.copyProperties(srpmsProjectApply, taskVo);
            taskVo.setId(CommonUtil.objectToString(projectId));// 主键赋值
            taskVo.setProjectApplicantId(CommonUtil.objectToString(taskVo.getProjectApplicantId()));// 项目申请人ID

            if ("true".equals(projectEntity.getTaskFirstOpenFlg())) {
                taskVo.setFirstFlg("true");
                projectEntity.setTaskFirstOpenFlg("false");
                projectService.updateById(projectEntity);
            }

        } else {

            budgetCategoryEnums = BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL;
            personJoinWay = PersonJoinWayEnums.TASK_MAIN_MEMBER;
            BeanUtils.copyProperties(srpmsProjectTask, taskVo);
            taskVo.setId(CommonUtil.objectToString(projectId));// 主键赋值
            if(StringUtils.isNotBlank(srpmsProjectTask.getResearchYearPlan())) {// 年度计划
                taskVo.setResearchYearPlan(JSONObject.parseArray(srpmsProjectTask.getResearchYearPlan()));
            }
            taskVo.setProjectApplicantId(CommonUtil.objectToString(taskVo.getProjectApplicantId()));// 项目申请人ID
        }


        BeanUtils.copyProperties(projectEntity, srpmsProjectVo);
        srpmsProjectVo.setId(projectId);
        if(StringUtils.isNotBlank(projectEntity.getLeadPerson())) {
            taskVo.setLeadPerson(JSONObject.parseObject(projectEntity.getLeadPerson()));
        }
        if(StringUtils.isNotBlank(projectEntity.getLeadDept())) {
            taskVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));
        }
        taskVo.setSrpmsProjectVo(srpmsProjectVo);
        taskVo.setSubjectCategory(projectEntity.getSubjectCategory() == null ? null : JSONArray.parseArray(projectEntity.getSubjectCategory()));

        taskVo.setProjectName(projectEntity.getProjectName());// 项目名称
        taskVo.setProjectActionDateStart(projectEntity.getProjectActionDateStart());// 项目执行开始时间
        taskVo.setProjectActionDateEnd(projectEntity.getProjectActionDateEnd());// 项目执行结束时间

        // 项目参加人员
        List<SrpmsProjectPersonJoinVo> personList = personJoinService.queryPersonJoinListByJoinWay(personJoinWay, projectId);
        if (personList != null && personList.size() > 0) {
            taskVo.setMostMemberList(personList);
        } else {
            taskVo.setMostMemberList(new ArrayList<>());
        }

        //年度预算
        List<SrpmsProjectBudgetDetailVo> detailVoList = budgetDetailService.queryBudgetDetailListByCategory(budgetCategoryEnums, projectId);
        if (detailVoList != null && detailVoList.size() != 0) {
            taskVo.setExpenditureBudgetDetail(detailVoList);
        }

        // 相关附件
        JSONObject attachmentJson = attachmentService.queryAttachmentListTaskType2(projectId);
        taskVo.setAttachmentCommittee(attachmentJson.getJSONObject("attachmentCommittee"));
        taskVo.setAttachmentAudit(attachmentJson.getJSONObject("attachmentAudit"));
        taskVo.setAttachmentStatement(attachmentJson.getJSONObject("attachmentStatement"));

        return taskVo;
    }

    /**
     * 保存基科费任务书操作service接口实现
     *
     * @param taskForm
     * @param taskFlag
     * @return
     */
    @Override
    public Result saveSrpmsProjectTask(SrpmsProjectTaskAcademyForm taskForm, boolean taskFlag) {

        long projectId = taskForm.getId();

        // 更新项目信息中负责人信息
        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        if(taskForm.getLeadPerson() != null) {
            projectEntity.setLeadPerson(JSONObject.toJSONString(taskForm.getLeadPerson()));
        }
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
        SrpmsProjectTaskAcademy srpmsProjectTask = new SrpmsProjectTaskAcademy();
        BeanUtils.copyProperties(taskForm, srpmsProjectTask);
        srpmsProjectTask.setId(projectId);
        // 年度计划
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
        attachmentVoJson.put("attachmentStatement", taskForm.getAttachmentStatement());

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
    public Result submitSrpmsProjectTask(SrpmsProjectTaskAcademyForm taskForm, UserVo userVo, DeptVo deptVo) {
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
        // 调用check验证方法
        checkFlag = checkTask(taskForm, checkFlag, stringBuilder, projectEntity.getLeadPerson());

        // 提交的时候验证数据
        if (checkFlag) {
            String errorMsg = stringBuilder.toString();
            errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
            return new Result(SrpmsErrorType.TASK_NOT_COMPLETE.getCode(), errorMsg, null);
        }

        //更新项目状态
        projectService.submitTaskProject(projectId);

        //生成PDF
        this.generateApplyBookPdf(projectId, userVo, deptVo);

        log.info("院基科费，提交任务书，已更新项目状态, projectId:{}", taskForm.getId());
        //发起流程
        flowServicel.startAuditProcess(taskForm.getId(), VoucherTypeEnums.TASK_BOOK, userVo, deptVo);

        log.info("院基科费，提交申请书，已发起流程, projectId:{}", taskForm.getId());

        return Result.success(projectId);
    }

    /**
     * 任务书提交check验证
     *
     * @param taskForm
     * @param checkFlag
     * @param errorMsg
     * @return
     */
    public boolean checkTask(SrpmsProjectTaskAcademyForm taskForm, boolean checkFlag, StringBuilder errorMsg, String leadPerson) {
        errorMsg.append("任务书-院基科费提交数据校验不通过字段：");

        // 项目摘要
        if (taskForm.getProjectAbstract() == null) {
            checkFlag = true;
            errorMsg.append("项目摘要为空，");
        }

        // 用户信息验证
//        if (leadPerson == null) {
//            checkFlag = true;
//            errorMsg.append("项目负责人信息为空，");
//        } else {
//            JSONObject jsonPerson = JSONObject.parseObject(leadPerson);
//            // 手机号码
//            if (StringUtils.isBlank(jsonPerson.getString("phone"))) {
//                checkFlag = true;
//                errorMsg.append("项目负责人手机号码为空，");
//            } else if (!CommonUtil.isValidMobile(jsonPerson.getString("phone"))) {
//                checkFlag = true;
//                errorMsg.append("项目负责人手机号码");
//                errorMsg.append(jsonPerson.getString("phone"));
//                errorMsg.append("无效，");
//            }
//            // 邮箱
//            if (StringUtils.isBlank(jsonPerson.getString("email"))) {
//                checkFlag = true;
//                errorMsg.append("项目负责人邮箱为空，");
//            } else if (!CommonUtil.isValidMailAddress(jsonPerson.getString("email"))) {
//                checkFlag = true;
//                errorMsg.append("项目负责人邮箱");
//                errorMsg.append(jsonPerson.getString("email"));
//                errorMsg.append("无效，");
//            }
//            // 通讯地址
//            if (StringUtils.isBlank(jsonPerson.getString("address"))) {
//                checkFlag = true;
//                errorMsg.append("项目负责人通讯地址为空，");
//            } else if (CommonUtil.maxLengthCheck(jsonPerson.getString("address"), 200)) {
//                checkFlag = true;
//                errorMsg.append("项目负责人通讯地址录入超过设定长度，");
//            }
//            // 邮编
//            if (StringUtils.isBlank(jsonPerson.getString("zipCode"))) {
//                checkFlag = true;
//                errorMsg.append("项目负责人邮编为空，");
//            } else if (CommonUtil.maxLengthCheck(jsonPerson.getString("zipCode"), 50)) {
//                checkFlag = true;
//                errorMsg.append("项目负责人邮编录入超过设定长度，");
//            }
//        }

        // 研究内容
        if (taskForm.getContentTargetProblem() == null) {
            checkFlag = true;
            errorMsg.append("研究内容为空，");
        }

        // 预期目标
        if (taskForm.getProjectExpectTarget() == null) {
            checkFlag = true;
            errorMsg.append("预期目标为空，");
        }

        // 主要技术特点和创新点
        if (taskForm.getProjectTechnicalInnovation() == null) {
            checkFlag = true;
            errorMsg.append("主要技术特点和创新点为空，");
        }

        // 考核指标
        if (taskForm.getAssessmentIndicators() == null) {
            checkFlag = true;
            errorMsg.append("考核指标为空，");
        }

        // 年度计划
        if (taskForm.getResearchYearPlan() == null) {
            checkFlag = true;
            errorMsg.append("研究计划及指标完成情况为空，");
        }

        return checkFlag;
    }

    /**
     * 导出word
     * @param projectId
     * @param userVo
     * @param deptVo
     * @return
     */
    @Override
    public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
        InputStream fileIn= null;
        String fileUrl = null;
        JSONObject perJson = (JSONObject) JSON.toJSON(userVo);
        try {
            Map<String, Object> dataMap = new HashMap<>();
            if(projectId == NumberUtils.LONG_ZERO){

            } else {
                SrpmsProjectTaskAcademyVo myVo = this.queryById(projectId);
                JSONObject jobject =  JSONObject.parseObject(JSONObject.toJSONString(myVo));

                jobject.put("projectActionDateStart", LocalDateUtils.parse(jobject.getString("projectActionDateStart"),LocalDateUtils.PARRERN_YMD));
                jobject.put("projectActionDateEnd", LocalDateUtils.parse(jobject.getString("projectActionDateEnd"),LocalDateUtils.PARRERN_YMD));
                jobject.put("projectAbstract",WordConvertUtil.htmlToText(jobject.getString("projectAbstract")));
                jobject.put("contentTargetProblem",WordConvertUtil.htmlToText(jobject.getString("contentTargetProblem")));
                jobject.put("projectTechnicalInnovation",WordConvertUtil.htmlToText(jobject.getString("projectTechnicalInnovation")));
                jobject.put("projectExpectTarget",WordConvertUtil.htmlToText(jobject.getString("projectExpectTarget")));
                jobject.put("assessmentIndicators",WordConvertUtil.htmlToText(jobject.getString("assessmentIndicators")));
                jobject.put("budgetDescription",WordConvertUtil.htmlToText(jobject.getString("budgetDescription")));

                //年度计划及指标
                List<JSONObject> arrayList = new ArrayList<>();
                JSONArray array =  jobject.getJSONArray("researchYearPlan");
                if (!CollectionUtils.isEmpty(array)){
                    for(int i = 0; i<array.size();i++) {
                        JSONObject obj = new JSONObject();
                        //时间
                        JSONObject jObject = array.getJSONObject(i);
                        JSONArray array1 = jObject.getJSONArray("time");
                        String aa = LocalDateUtils.parse(array1.get(0).toString(),LocalDateUtils.PARRERN_YMD);
                        String bb =  LocalDateUtils.parse(array1.get(1).toString(),LocalDateUtils.PARRERN_YMD);
                        //文本
                        obj.put("text", WordConvertUtil.htmlToText(array.getJSONObject(i).getString("text")));
                        obj.put("time",aa + "-" + bb);
                        arrayList.add(obj);
                    }
                }

                //计算参与人员年龄
                JSONArray mainMemberList = jobject.getJSONArray("mostMemberList");
//                JSONArray tmpMemberlist = new JSONArray();
//                if (!org.springframework.util.CollectionUtils.isEmpty(mainMemberList)){
//                    for (Object  member:mainMemberList) {
//                        JSONObject  menberObj =   (JSONObject)member;
//                        menberObj.put("age",LocalDateUtils.getAge(menberObj.getString("birthDate")));
//                        tmpMemberlist.add(menberObj);
//                    }
//                }
                jobject.put("mostMemberList",mainMemberList);

                //项目信息
                JSONObject demoObject = JSONObject.parseObject(jobject.getString("srpmsProjectVo"));
                //负责人
                perJson = JSONObject.parseObject(jobject.getString("leadPerson"));

                dataMap.put("data", jobject);
                dataMap.put("dem", demoObject);
                dataMap.put("plan", arrayList);

            }

            String birthDate = perJson.getString("birthDate");
            perJson.put("birthDate", LocalDateUtils.parse(birthDate,LocalDateUtils.PARRERN_YM));
            perJson.put("age",LocalDateUtils.getAge(birthDate));
            dataMap.put("lea", perJson);
            dataMap.put("dept", JSONObject.parseObject(JSONObject.toJSONString(deptVo)));
            //当前时间
            String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
            dataMap.put("nowDate", nowDate);

            String filename = "医科院基科院项目任务书_"+ DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999)+".docx";
            String finalWordName =  wordExportService.exportWord("template_task_ace_my",dataMap, filename);
            fileIn = new FileInputStream(finalWordName);
            fileUrl = finalWordName;
            return fileUrl;

        } catch (Exception e) {
            log.error("导出word异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            if (fileIn != null){
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导入任务书
     * @param wordFileUrl
     * @return
     */
    @Override

    public SrpmsProjectTaskAcademyVo importWord(String wordFileUrl) {
        SrpmsProjectTaskAcademyVo vo = new SrpmsProjectTaskAcademyVo();
        DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
            //基本信息
            Elements actionTimeElements = document.getElementsMatchingOwnText("起止年限");
            if (actionTimeElements!= null && actionTimeElements.size() > 0){
                Element spanElement = actionTimeElements.get(0);
                if(spanElement.tagName().equals("span")){
                    Element parentElement = spanElement.parent();
                    String parentText = parentElement.text();
                    if (parentText!= null && parentText.indexOf("：") > 0){
                        String actionTimeText = parentText.substring(parentText.indexOf("：") + 1);
                        String[] actionTimeArr = actionTimeText.split("至");
                        if(actionTimeArr.length ==2){
                            vo.setProjectActionDateStart(DateParseUtil.parseLocalDateTime(actionTimeArr[0].trim().replaceAll("[\\u4E00-\\u9FA5]","")));
                            vo.setProjectActionDateEnd(DateParseUtil.parseLocalDateTime(actionTimeArr[1].trim().replaceAll("[\\u4E00-\\u9FA5]","")));
                        }
                    }
                }
            }

            Elements elements = document.getElementsByTag("table");
            //基本信息
            Element element = elements.get(0);

            vo.setProjectName(extractCellFromTable(element,0,1));
            vo.setApplyFunds(NumberUtils.toDouble(extractCellFromTable(element,1,1)));
            vo.setProjectAbstract(extractCellFromTable(element,8,1,true));

            //研究内容
            vo.setContentTargetProblem(extractCellFromTable(elements.get(1),0,0,true));
            vo.setProjectTechnicalInnovation(extractCellFromTable(elements.get(2),0,0,true));
            vo.setProjectExpectTarget(extractCellFromTable(elements.get(3),0,0,true));
            vo.setAssessmentIndicators(extractCellFromTable(elements.get(4),0,0,true));

            //年度计划
            JSONArray listPlan = new JSONArray();
            List<List<String>> list = extractListFromTable(elements.get(5), 1, 0);
            for(List<String> planVo: list){
                if(StringUtils.isBlank(planVo.get(0))){
                    break;
                }
                JSONObject object = new JSONObject();
                List<String> arrayList = new ArrayList<>();
                String[] actionTimeArr = list.get(0).get(0).split("-");
                for (int i = 0; i < actionTimeArr.length; i++){
                    arrayList.add(i,DateParseUtil.parseLocalDateTime(actionTimeArr[i].trim().replaceAll("[\\u4E00-\\u9FA5]","")).format(formatterBirth));
                }

                object.put("time",arrayList);
                object.put("text",planVo.get(1));
                listPlan.add(object);
            }
            vo.setResearchYearPlan(listPlan);
            //参与人员
//            List<SrpmsProjectPersonJoinVo> mainMemberList = new ArrayList<>();
//            list = extractListFromTable(elements.get(6), 6, 0);
//            for(List<String> perVo: list){
//                if(StringUtils.isBlank(perVo.get(0))){
//                    break;
//                }
//                SrpmsProjectPersonJoinVo joinVo = new SrpmsProjectPersonJoinVo();
//                joinVo.setPersonName(perVo.get(0));
//                joinVo.setGender(perVo.get(1));
//                joinVo.setAge(NumberUtils.toDouble(perVo.get(2)));
//                joinVo.setPositionTitle(perVo.get(3));
//                joinVo.setMajor(perVo.get(4));
//                mainMemberList.add(joinVo);
//            }
//
//            vo.setMostMemberList(mainMemberList);

            //预算明细
//            list = extractListFromTable(elements.get(7), 0, 0);
//            List<SrpmsProjectBudgetDetailVo> expenditureBudgetDetail = new ArrayList<>();
//
//            int num = 0;
//            List li = new ArrayList();
//            for(List<String> detail: list){
//                if(detail.get(0).trim().replaceAll("[\\d+]","").equals("年度预算内容")){
//                    li.add(num);
//                }
//                num ++;
//            }
//
//            JSONObject commonObj;
//            JSONArray commonArr;
//            for(int a = 0; a < li.size(); a++){
//                int beginNum;
//                int endNum;
//                if(a + 1 < li.size()){
//                    beginNum = Integer.parseInt(li.get(a).toString());
//                    endNum = Integer.parseInt(li.get(a+1).toString());
//                    list = extractListFromTable(elements.get(7), beginNum, list.size() - endNum);
//                } else {
//                    beginNum = Integer.parseInt(li.get(a).toString());
//                    list = extractListFromTable(elements.get(7), beginNum, 0);
//                }
//                endNum = list.size()-1;
//
//                SrpmsProjectBudgetDetailVo projectBudgetDetailVo = new SrpmsProjectBudgetDetailVo();
//                //年度
//                int budgetYear = NumberUtils.toInt(list.get(0).get(0).trim().replaceAll("[^\\d+]",""));
//                projectBudgetDetailVo.setBudgetYear(budgetYear);
//                //合计
//                projectBudgetDetailVo.setBudgetAmount(NumberUtils.toDouble(list.get(endNum).get(1)));
//
//                //明细
//                JSONArray budgetDetail = new JSONArray();
//                //人员费
//                JSONObject pensionCostObject = new JSONObject();
//                pensionCostObject.put("name","(一)人员费");
//                pensionCostObject.put("serial","1");
//                pensionCostObject.put("amount",list.get(1).get(1));
//                pensionCostObject.put("remark",list.get(1).get(2));
//
//                commonObj = new JSONObject();
//                commonArr = new JSONArray();
//                commonObj.put("serial","1");
//                commonObj.put("name","专家咨询费");
//                commonObj.put("amount",list.get(2).get(1));
//                commonObj.put("remark",list.get(2).get(2));
//                commonArr.add(commonObj);
//
//                commonObj = new JSONObject();
//                commonObj.put("serial","2");
//                commonObj.put("name","劳务费");
//                commonObj.put("amount",list.get(3).get(1));
//                commonObj.put("remark",list.get(3).get(2));
//                commonArr.add(commonObj);
//                pensionCostObject.put("child",commonArr);
//
//                budgetDetail.add(pensionCostObject);
//
//                //相关业务费
//                JSONObject serviceObj = new JSONObject();
//                serviceObj.put("name","(二)相关业务费");
//                serviceObj.put("serial","2");
//                serviceObj.put("amount",list.get(4).get(1));
//                serviceObj.put("remark",list.get(4).get(2));
//
//                commonObj = new JSONObject();
//                commonArr = new JSONArray();
//                //1.材料
//                commonObj.put("serial","1");
//                commonObj.put("name","材料费");
//                commonObj.put("amount",list.get(5).get(1));
//                commonObj.put("remark",list.get(5).get(2));
//                commonArr.add(commonObj);
//                //2.测试费
//                commonObj = new JSONObject();
//                commonObj.put("serial","2");
//                commonObj.put("name","测试化验加工费");
//                commonObj.put("amount",list.get(6).get(1));
//                commonObj.put("remark",list.get(6).get(2));
//                commonArr.add(commonObj);
//                //3.差旅费
//                commonObj = new JSONObject();
//                commonObj.put("serial","3");
//                commonObj.put("name","差旅费/会议费/国际合作费");
//                commonObj.put("amount",list.get(7).get(1));
//                commonObj.put("remark",list.get(7).get(2));
//                commonArr.add(commonObj);
//                //4.出版文献费
//                commonObj = new JSONObject();
//                commonObj.put("serial","4");
//                commonObj.put("name","出版/文献/信息传播/知识产权事务费");
//                commonObj.put("amount",list.get(8).get(1));
//                commonObj.put("remark",list.get(8).get(2));
//                commonArr.add(commonObj);
//                serviceObj.put("child",commonArr);
//                budgetDetail.add(serviceObj);
//
//                //其他
//                JSONObject otherObj = new JSONObject();
//                otherObj.put("name","(三)其他费用");
//                otherObj.put("serial","3");
//                otherObj.put("amount",list.get(9).get(1));
//                otherObj.put("remark",list.get(9).get(2));
//
//                commonArr = new JSONArray();
//                for(int b = 10; b < endNum; b ++) {
//                    if(StringUtils.isBlank(list.get(b).get(0))){
//                        break;
//                    }
//                    commonObj = new JSONObject();
//                    commonObj.put("serial",b-9);
//                    commonObj.put("name",list.get(b).get(0));
//                    commonObj.put("amount",list.get(b).get(1));
//                    commonObj.put("remark",list.get(b).get(2));
//                    commonArr.add(commonObj);
//                }
//                otherObj.put("child",commonArr);
//                budgetDetail.add(otherObj);
//                projectBudgetDetailVo.setBudgetDetail(budgetDetail);
//                expenditureBudgetDetail.add(projectBudgetDetailVo);
//            }
//            vo.setExpenditureBudgetDetail(expenditureBudgetDetail);

            //预算说明
            vo.setBudgetDescription(extractCellFromTable(elements.get(elements.size()-1),0,0,true));
            WordConvertUtil.delHtmlFile(htmlFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 导出pdf
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    @Override
    public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
        File pdfFile = null;
        FileInputStream fileInputStream = null;
        SrpmsProject project = projectService.getById(projectId);

        try {
//            String docxFilePath = this.exportWordFile(projectId, userVo, deptVo);
//            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
//            String pdfFilePath = jarPath+ "/"+project.getProjectName()+"申请书.pdf";
//            WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);

            String pdfFilePath = this.exportPdf(projectId, userVo, deptVo);
            pdfFile = new File(pdfFilePath);
            fileInputStream = new FileInputStream(pdfFile);
            MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);

            if (result.isSuccess()) {
                FileInfoVo fileInfoVo = result.getData();
                if(fileInfoVo != null){
                    SrpmsProject projectFile = new SrpmsProject();
                    projectFile.setId(project.getId());
                    projectFile.setTaskBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
                    projectFile.setTaskBookFileName(fileInfoVo.getFileName());
                    projectFile.setTaskBookFileUrl(fileInfoVo.getFileUrl());
                    projectService.updateById(projectFile);
                }
            } else {
                log.error("申请书上传文件服务器失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成pdf异常");
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            if (pdfFile != null && pdfFile.exists()){
                pdfFile.delete();
            }
        }
    }
}

