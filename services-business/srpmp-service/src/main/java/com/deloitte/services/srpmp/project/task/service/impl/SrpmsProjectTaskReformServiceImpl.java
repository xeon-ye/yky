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
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformVo;
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
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyReform;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyAcademyMapper;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyReformMapper;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.*;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskAcademy;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskReform;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskAcademyMapper;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskReformMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskAcademyService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskReformService;
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
public class SrpmsProjectTaskReformServiceImpl extends ServiceImpl<SrpmsProjectTaskReformMapper, SrpmsProjectTaskReform> implements ISrpmsProjectTaskReformService {


    @Autowired
    private SrpmsProjectTaskReformMapper srpmsProjectTaskMapper;

    @Autowired
    private SrpmsProjectApplyReformMapper srpmsProjectApplyMapper;

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
        String docxFilePath = this.exportWordFile(projectId, userVo, deptVo);
        //转格式
        String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
        String pdfFinalName = docxFilePath.replace("docx", "pdf");
        log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
        //合并
        pdfMergeService.mergeAttachmentTaskType2(pdfFilePath, projectId, pdfFinalName);
        return pdfFinalName;
    }

    /**
     * 根据ID查询科技体制改革任务书service接口实现
     *
     * @param projectId
     * @return
     */
    @Override
    public SrpmsProjectTaskReformVo queryById(Long projectId) {
        SrpmsProjectTaskReformVo taskVo = new SrpmsProjectTaskReformVo();

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

        // 查询科技体制改革任务书数据
        SrpmsProjectTaskReform srpmsProjectTask = srpmsProjectTaskMapper.selectById(projectId);
        taskVo.setFirstFlg("false");
        if (srpmsProjectTask == null) {
            budgetCategoryEnums = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR;
            // 查询科技体制改革申请书数据
            SrpmsProjectApplyReform srpmsProjectApply = srpmsProjectApplyMapper.selectById(projectId);
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
            if (StringUtils.isNotBlank(srpmsProjectTask.getResearchYearPlan())) {// 年度计划
                taskVo.setResearchYearPlan(JSONObject.parseArray(srpmsProjectTask.getResearchYearPlan()));
            }
            taskVo.setProjectApplicantId(CommonUtil.objectToString(taskVo.getProjectApplicantId()));// 项目申请人ID
        }


        BeanUtils.copyProperties(projectEntity, srpmsProjectVo);
        srpmsProjectVo.setId(projectId);
        if (StringUtils.isNotBlank(projectEntity.getLeadPerson())) {
            taskVo.setLeadPerson(JSONObject.parseObject(projectEntity.getLeadPerson()));
        }
        if (StringUtils.isNotBlank(projectEntity.getLeadDept())) {
            taskVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));
        }
        taskVo.setSrpmsProjectVo(srpmsProjectVo);

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
     * 保存科技体制改革任务书操作service接口实现
     *
     * @param taskForm
     * @param taskFlag
     * @return
     */
    @Override
    public Result saveSrpmsProjectTask(SrpmsProjectTaskReformForm taskForm, boolean taskFlag) {

        long projectId = taskForm.getId();

        // 更新项目信息中负责人信息
        SrpmsProject projectEntity = projectService.getById(projectId);
        if (projectEntity == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        if (taskForm.getLeadPerson() != null) {
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

        // 保存/更新科技体制改革任务书
        SrpmsProjectTaskReform srpmsProjectTask = new SrpmsProjectTaskReform();
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
     * 提交科技体制改革任务书操作service接口实现
     *
     * @param taskForm
     * @return
     */
    @Override
    public Result submitSrpmsProjectTask(SrpmsProjectTaskReformForm taskForm, UserVo userVo, DeptVo deptVo) {
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

        log.info("科技体制改革，提交任务书，已更新项目状态, projectId:{}", taskForm.getId());
        //发起流程
        flowServicel.startAuditProcess(taskForm.getId(), VoucherTypeEnums.TASK_BOOK, userVo, deptVo);

        log.info("科技体制改革，提交申请书，已发起流程, projectId:{}", taskForm.getId());

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
    public boolean checkTask(SrpmsProjectTaskReformForm taskForm, boolean checkFlag, StringBuilder errorMsg, String leadPerson) {
        errorMsg.append("任务书-科技体制改革提交数据校验不通过字段：");

        // 项目摘要
        if (taskForm.getProjectAbstract() == null) {
            checkFlag = true;
            errorMsg.append("项目摘要为空，");
        }

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
     *
     * @param projectId
     * @param userVo
     * @param deptVo
     * @return
     */
    @Override
    public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
        InputStream fileIn = null;
        String fileUrl = null;
        JSONObject perJson = (JSONObject) JSON.toJSON(userVo);
        try {
            Map<String, Object> dataMap = new HashMap<>();
            if (projectId == NumberUtils.LONG_ZERO) {

            } else {
                SrpmsProjectTaskReformVo myVo = this.queryById(projectId);
                JSONObject jobject = JSONObject.parseObject(JSONObject.toJSONString(myVo));

                jobject.put("projectActionDateStart", LocalDateUtils.parse(jobject.getString("projectActionDateStart"), LocalDateUtils.PARRERN_YMD));
                jobject.put("projectActionDateEnd", LocalDateUtils.parse(jobject.getString("projectActionDateEnd"), LocalDateUtils.PARRERN_YMD));

                jobject.put("projectAbstract", WordConvertUtil.htmlToText(jobject.getString("projectAbstract")));
                jobject.put("contentTargetProblem", WordConvertUtil.htmlToText(jobject.getString("contentTargetProblem")));
                jobject.put("projectTechnicalInnovation", WordConvertUtil.htmlToText(jobject.getString("projectTechnicalInnovation")));
                jobject.put("projectExpectTarget", WordConvertUtil.htmlToText(jobject.getString("projectExpectTarget")));
                jobject.put("assessmentIndicators", WordConvertUtil.htmlToText(jobject.getString("assessmentIndicators")));
                jobject.put("budgetDescription", WordConvertUtil.htmlToText(jobject.getString("budgetDescription")));

                //年度计划及指标
                List<JSONObject> arrayList = new ArrayList<>();
                JSONArray array = jobject.getJSONArray("researchYearPlan");
                if (!CollectionUtils.isEmpty(array)) {
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject obj = new JSONObject();
                        //时间
                        JSONObject jObject = array.getJSONObject(i);
                        JSONArray array1 = jObject.getJSONArray("time");
                        String aa = LocalDateUtils.parse(array1.get(0).toString(), LocalDateUtils.PARRERN_YMD);
                        String bb = LocalDateUtils.parse(array1.get(1).toString(), LocalDateUtils.PARRERN_YMD);
                        //文本
                        obj.put("text", WordConvertUtil.htmlToText(array.getJSONObject(i).getString("text")));
                        obj.put("time", aa + "-" + bb);
                        arrayList.add(obj);
                    }
                }

                //项目信息
                JSONObject demoObject = JSONObject.parseObject(jobject.getString("srpmsProjectVo"));
                //负责人
                perJson = JSONObject.parseObject(jobject.getString("leadPerson"));

                dataMap.put("data", jobject);
                dataMap.put("dem", demoObject);
                dataMap.put("plan", arrayList);

            }

            String birthDate = perJson.getString("birthDate");
            perJson.put("birthDate", LocalDateUtils.parse(birthDate, LocalDateUtils.PARRERN_YM));
            perJson.put("age", LocalDateUtils.getAge(birthDate));
            dataMap.put("lea", perJson);
            dataMap.put("dept", JSONObject.parseObject(JSONObject.toJSONString(deptVo)));
            //当前时间
            String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
            dataMap.put("nowDate", nowDate);

            String filename = "科技体制改革项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
            String finalWordName = wordExportService.exportWord("template_task_reform", dataMap, filename);
            fileIn = new FileInputStream(finalWordName);
            fileUrl = finalWordName;
            return fileUrl;

        } catch (Exception e) {
            log.error("导出word异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            if (fileIn != null) {
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
     *
     * @param wordFileUrl
     * @return
     */
    @Override
    public SrpmsProjectTaskReformVo importWord(String wordFileUrl) {
        DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            SrpmsProjectTaskReformVo vo = new SrpmsProjectTaskReformVo();
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
            //基本信息
            Elements actionTimeElements = document.getElementsMatchingOwnText("起止年限：");
            if (actionTimeElements != null && actionTimeElements.size() > 0) {
                Element spanElement = actionTimeElements.get(0);
                if (spanElement.tagName().equals("span")) {
                    Element parentElement = spanElement.parent();
                    String parentText = parentElement.text();
                    if (parentText != null && parentText.indexOf("：") > 0) {
                        String actionTimeText = parentText.substring(parentText.indexOf("：") + 1);
                        String[] actionTimeArr = actionTimeText.split("至");
                        if (actionTimeArr.length == 2) {
                            vo.setProjectActionDateStart(DateParseUtil.parseLocalDateTime(actionTimeArr[0].trim().replaceAll("[\\u4E00-\\u9FA5]", "")));
                            vo.setProjectActionDateEnd(DateParseUtil.parseLocalDateTime(actionTimeArr[1].trim().replaceAll("[\\u4E00-\\u9FA5]", "")));
                        }
                    }
                }
            }

            Elements tableElements = document.getElementsByTag("table");
            Element baseTable = tableElements.get(0);
            //--项目信息
            vo.setProjectName(extractCellFromTable(baseTable, 2, 2));
            vo.setApplyFunds(NumberUtils.toDouble(extractCellFromTable(baseTable, 3, 1)));
            vo.setProjectAbstract(extractCellFromTable(baseTable, 4, 1, true));

            vo.setProjectExpectTarget(extractCellFromTable(tableElements.get(1), 0, 0, true)); //预期目标
            vo.setContentTargetProblem(extractCellFromTable(tableElements.get(2), 0, 0, true));
            vo.setProjectTechnicalInnovation(extractCellFromTable(tableElements.get(3), 0, 0, true));
            vo.setAssessmentIndicators(extractCellFromTable(tableElements.get(4), 0, 0, true));
            //年度计划
            JSONArray listPlan = new JSONArray();
            List<List<String>> list = extractListFromTable(tableElements.get(5), 1, 0);
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

            //预算说明
            vo.setBudgetDescription(extractCellFromTable(tableElements.get(tableElements.size() - 1), 0, 0, true));
            WordConvertUtil.delHtmlFile(htmlFilePath);

            return vo;
        } catch (Exception e) {
            log.error("解析word文件发生异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }

    /**
     * 导出pdf
     *
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
            String pdfFilePath = this.exportPdf(projectId, userVo, deptVo);
            pdfFile = new File(pdfFilePath);
            fileInputStream = new FileInputStream(pdfFile);
            MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);

            if (result.isSuccess()) {
                FileInfoVo fileInfoVo = result.getData();
                if (fileInfoVo != null) {
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
            if (pdfFile != null && pdfFile.exists()) {
                pdfFile.delete();
            }
        }
    }
}

