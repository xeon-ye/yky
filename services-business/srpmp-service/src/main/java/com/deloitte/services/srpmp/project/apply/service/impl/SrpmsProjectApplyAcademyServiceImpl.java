package com.deloitte.services.srpmp.project.apply.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplyAcademyQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyAcademySaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.DateParseUtil;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyAcademy;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyAcademyMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyAcademyService;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPerson;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPersonJoin;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;

import lombok.extern.slf4j.Slf4j;
/**
 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description :  SrpmsProjectApplyAcademy服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class SrpmsProjectApplyAcademyServiceImpl extends ServiceImpl<SrpmsProjectApplyAcademyMapper, SrpmsProjectApplyAcademy> implements ISrpmsProjectApplyAcademyService {
    @Autowired
    private ISrpmsProjectService projectService;

    //加载上传 uploadFiles
    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private SrpmsProjectApplyAcademyMapper srpmsProjectApplyAcademyMapper;

    @Autowired
    private ISrpmsProjectPersonService srpmsProjectPersonService;

    @Autowired
    private ISrpmsProjectPersonJoinService srpmsProjectPersonJoinService;

    @Autowired
    private ISrpmsProjectService srpmsProjectService;

    @Autowired
    SrpmsProjectApplyInnCommonServiceImpl commonService;

	@Autowired
	private ISrpmsCommonNclobService commonNclobService;
	
    //@Autowired
    //private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private PdfMergeServiceImpl pdfMergeService;

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
    public Long saveAndUpdateAcademy(SrpmsProjectApplyAcademySaveVo vo, DeptVo deptVo) {
        // 项目ID
        long projectId = 0l;

        // 重大创新项目信息
        SrpmsProjectApplyAcademy academyEntity =  JSONObject.parseObject(JSON.toJSONString(vo), SrpmsProjectApplyAcademy.class);
        JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(vo));
        if (vo.getId() == null) {
            projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.ACADEMY, deptVo);
            academyEntity.setId(projectId);
            JSONObject personJson = projectJson.getJSONObject("leadPerson");
            if(personJson != null) {
                academyEntity.setProjectApplicantId(personJson.getLong("id"));
                academyEntity.setProjectCommitmentUnit(personJson.getString("projectCommitmentUnit"));
            }
            this.save(academyEntity);
        } else {
            //校验项目状态 已提交的状态后将无修改保存
            SrpmsProject project = projectService.getById(vo.getId());
            if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())){
                throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
            }
            projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.ACADEMY);
            this.updateById(academyEntity);
        }
        return projectId;
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
        
		String jsonStr = commonNclobService.getApplyVo(projectId);
		if (jsonStr != null && jsonStr.length() != 0) {
			return JSONObject.parseObject(jsonStr);
		}
        JSONObject result = commonService.queryCommonInfoById(projectId, ProjectCategoryEnums.ACADEMY);
        result.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));
        result.put("id", projectId + "");
        result.put("subjectCategory", result.getString("subjectCategory") == null ? "" : JSONArray.parseArray(result.getString("subjectCategory")));
        JSONConvert.convertJson(result);
        return result;
    }

    @Override
    public void submitApply(SrpmsProjectApplyAcademySaveVo vo, UserVo userVo, DeptVo deptVo) {
        //保存申请书
        long projectId = this.saveAndUpdateAcademy(vo, deptVo);

        //更新项目状态
        projectService.submitProject(projectId);

        //生成PDF
        this.generateApplyBookPdf(projectId, userVo, deptVo);

        log.info("院基科费，提交申请书，已更新项目状态, projectId:{}", projectId);
        //发起流程
        flowServicel.startAuditProcess(projectId, VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);

        log.info("院基科费，提交申请书，已发起流程, projectId{}", projectId);

    }

    @Override
    public SrpmsProjectApplyAcademySaveVo getAcademyById(long id) {
        SrpmsProjectApplyAcademy applyAcademy = this.getById(id);
        if (applyAcademy == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        SrpmsProjectApplyAcademySaveVo srpmsProjectApplyAcademySaveVo = new SrpmsProjectApplyAcademySaveVo();
        BeanUtils.copyProperties(applyAcademy,srpmsProjectApplyAcademySaveVo);
        if (applyAcademy.getProjectApplicantId() != null) {
            //项目申请者
            SrpmsProjectPerson applicantInformation = srpmsProjectPersonService.getById(applyAcademy.getProjectApplicantId());
            if (applicantInformation != null) {
                SrpmsProjectPersonVo srpmsProjectPersonVo = new SrpmsProjectPersonVo();
                BeanUtils.copyProperties(applicantInformation, srpmsProjectPersonVo);
//                srpmsProjectApplyAcademySaveVo.setApplicantInformation(srpmsProjectPersonVo);
            }
            //项目申请人
            SrpmsProjectPersonJoin application = srpmsProjectPersonJoinService.getById(applyAcademy.getProjectApplicantId());
            if (application != null) {
                SrpmsProjectPersonJoinVo projectAppliaction = new SrpmsProjectPersonJoinVo();
                BeanUtils.copyProperties(application, projectAppliaction);
//                srpmsProjectApplyAcademySaveVo.setProjectApplicant(projectAppliaction);
            }
        }

        //项目参与者
        List<SrpmsProjectPersonJoinVo> personJoinVoList = srpmsProjectPersonJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_MAIN_MEMBER, applyAcademy.getId());
//        srpmsProjectApplyAcademySaveVo.setPersonJoinVoList(personJoinVoList);

        return srpmsProjectApplyAcademySaveVo;
    }

    @Override
    public void deleteAcademyById(long id) {
        Long applicantId = this.getById(id).getProjectApplicantId();
        //删除项目申请者
        srpmsProjectPersonService.removeById(applicantId);
        //删除项目申请人
        srpmsProjectPersonJoinService.removeById(applicantId);
        //删除项目参与者
        srpmsProjectPersonJoinService.deleteByJoinWayAndProjectId(PersonJoinWayEnums.APPLY_MAIN_MEMBER, id);
        //删除院基科费
        this.removeById(id);

    }


    @Override
    public IPage<SrpmsProjectApplyAcademy> selectPage(SrpmsProjectApplyAcademyQueryForm queryForm) {
        QueryWrapper<SrpmsProjectApplyAcademy> queryWrapper = new QueryWrapper<SrpmsProjectApplyAcademy>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApplyAcademyMapper.selectPage(new Page<SrpmsProjectApplyAcademy>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<SrpmsProjectApplyAcademy> selectList(SrpmsProjectApplyAcademyQueryForm queryForm) {
        QueryWrapper<SrpmsProjectApplyAcademy> queryWrapper = new QueryWrapper<SrpmsProjectApplyAcademy>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApplyAcademyMapper.selectList(queryWrapper);
    }
    /**
     *  通用查询
     * @param
     * @return public QueryWrapper<SrpmsProjectApplyAcademy> getQueryWrapper(QueryWrapper<SrpmsProjectApplyAcademy> queryWrapper,BaseQueryForm<SrpmsProjectApplyAcademyQueryParam> queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectName())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_NAME,srpmsProjectApplyAcademy.getProjectName());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getApplyFunds())){
    queryWrapper.like(SrpmsProjectApplyAcademy.APPLY_FUNDS,srpmsProjectApplyAcademy.getApplyFunds());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectActionDateStart())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_ACTION_DATE_START,srpmsProjectApplyAcademy.getProjectActionDateStart());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectActionDateEnd())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_ACTION_DATE_END,srpmsProjectApplyAcademy.getProjectActionDateEnd());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectApplicantId())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_APPLICANT_ID,srpmsProjectApplyAcademy.getProjectApplicantId());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectAbstract())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_ABSTRACT,srpmsProjectApplyAcademy.getProjectAbstract());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getApprovalBasisMeaning())){
    queryWrapper.like(SrpmsProjectApplyAcademy.APPROVAL_BASIS_MEANING,srpmsProjectApplyAcademy.getApprovalBasisMeaning());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getContentTargetProblem())){
    queryWrapper.like(SrpmsProjectApplyAcademy.CONTENT_TARGET_PROBLEM,srpmsProjectApplyAcademy.getContentTargetProblem());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getResearchSchemeFeasibility())){
    queryWrapper.like(SrpmsProjectApplyAcademy.RESEARCH_SCHEME_FEASIBILITY,srpmsProjectApplyAcademy.getResearchSchemeFeasibility());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectExpectTarget())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_EXPECT_TARGET,srpmsProjectApplyAcademy.getProjectExpectTarget());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectTechnicalInnovation())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_TECHNICAL_INNOVATION,srpmsProjectApplyAcademy.getProjectTechnicalInnovation());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getResearchFoundation())){
    queryWrapper.like(SrpmsProjectApplyAcademy.RESEARCH_FOUNDATION,srpmsProjectApplyAcademy.getResearchFoundation());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getWorkinfConditions())){
    queryWrapper.like(SrpmsProjectApplyAcademy.WORKINF_CONDITIONS,srpmsProjectApplyAcademy.getWorkinfConditions());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectApplicantIntroduction())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_APPLICANT_INTRODUCTION,srpmsProjectApplyAcademy.getProjectApplicantIntroduction());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectOrganizingUnit())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_ORGANIZING_UNIT,srpmsProjectApplyAcademy.getProjectOrganizingUnit());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getProjectCommitmentUnit())){
    queryWrapper.like(SrpmsProjectApplyAcademy.PROJECT_COMMITMENT_UNIT,srpmsProjectApplyAcademy.getProjectCommitmentUnit());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getFundSourceAmount())){
    queryWrapper.like(SrpmsProjectApplyAcademy.FUND_SOURCE_AMOUNT,srpmsProjectApplyAcademy.getFundSourceAmount());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getFundSourceFinancial())){
    queryWrapper.like(SrpmsProjectApplyAcademy.FUND_SOURCE_FINANCIAL,srpmsProjectApplyAcademy.getFundSourceFinancial());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getFundSourceSelfRaised())){
    queryWrapper.like(SrpmsProjectApplyAcademy.FUND_SOURCE_SELF_RAISED,srpmsProjectApplyAcademy.getFundSourceSelfRaised());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getFundSourceOther())){
    queryWrapper.like(SrpmsProjectApplyAcademy.FUND_SOURCE_OTHER,srpmsProjectApplyAcademy.getFundSourceOther());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getExpenditureBudgetDetail())){
    queryWrapper.like(SrpmsProjectApplyAcademy.EXPENDITURE_BUDGET_DETAIL,srpmsProjectApplyAcademy.getExpenditureBudgetDetail());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getCreateTime())){
    queryWrapper.like(SrpmsProjectApplyAcademy.CREATE_TIME,srpmsProjectApplyAcademy.getCreateTime());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getCreateBy())){
    queryWrapper.like(SrpmsProjectApplyAcademy.CREATE_BY,srpmsProjectApplyAcademy.getCreateBy());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getUpdateTime())){
    queryWrapper.like(SrpmsProjectApplyAcademy.UPDATE_TIME,srpmsProjectApplyAcademy.getUpdateTime());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getUpdateBy())){
    queryWrapper.like(SrpmsProjectApplyAcademy.UPDATE_BY,srpmsProjectApplyAcademy.getUpdateBy());
    }
    if(StringUtils.isNotBlank(srpmsProjectApplyAcademy.getBudgetDescription())){
    queryWrapper.like(SrpmsProjectApplyAcademy.BUDGET_DESCRIPTION,srpmsProjectApplyAcademy.getBudgetDescription());
    }
    return queryWrapper;
    }
     */

    @Override
    /**
     * 导出基科院申请书
     */
    public String exportWordFile(Long projectId, UserVo userVo,DeptVo deptVo) {
        InputStream fileIn= null;
        try {
            Map<String, Object> dataMap = new HashMap<>();
            if(projectId== NumberUtils.LONG_ZERO){
                JSONObject perJson = (JSONObject)JSON.toJSON(userVo);
                String birthDate = perJson.getString("birthDate");
                perJson.put("birthDate", LocalDateUtils.parse(birthDate,LocalDateUtils.PARRERN_YM));
                perJson.put("age",LocalDateUtils.getAge(birthDate));
                dataMap.put("per", perJson);
                dataMap.put("data",new JSONObject());
                dataMap.put("dept", JSONObject.parseObject(JSONObject.toJSONString(deptVo)));
            } else {
                //获取所有信息
                JSONObject voJson = this.queryApplyVoById(projectId, userVo, deptVo);
                voJson.put("projectActionDateStart", LocalDateUtils.parse(voJson.getString("projectActionDateStart"),LocalDateUtils.PARRERN_YMD));
                voJson.put("projectActionDateEnd", LocalDateUtils.parse(voJson.getString("projectActionDateEnd"),LocalDateUtils.PARRERN_YMD));

                //文本信息
                voJson.put("projectAbstract",WordConvertUtil.htmlToText(voJson.getString("projectAbstract")));
                voJson.put("approvalBasisMeaning",WordConvertUtil.htmlToText(voJson.getString("approvalBasisMeaning")));
                voJson.put("contentTargetProblem",WordConvertUtil.htmlToText(voJson.getString("contentTargetProblem")));
                voJson.put("researchSchemeFeasibility",WordConvertUtil.htmlToText(voJson.getString("researchSchemeFeasibility")));
                voJson.put("projectExpectTarget",WordConvertUtil.htmlToText(voJson.getString("projectExpectTarget")));
                voJson.put("projectTechnicalInnovation",WordConvertUtil.htmlToText(voJson.getString("projectTechnicalInnovation")));
                voJson.put("researchFoundation",WordConvertUtil.htmlToText(voJson.getString("researchFoundation")));
                voJson.put("projectApplicantIntroduction",WordConvertUtil.htmlToText(voJson.getString("projectApplicantIntroduction")));
                voJson.put("budgetDescription",WordConvertUtil.htmlToText(voJson.getString("budgetDescription")));
                voJson.put("workinfConditions",WordConvertUtil.htmlToText(voJson.getString("workinfConditions")));
                //voJson.put("attachmentCommittee",WordConvertUtil.htmlToText(voJson.getString("attachmentCommittee")));

                //计算参与人员年龄
                JSONArray mainMemberList = voJson.getJSONArray("mainMemberList");
//                JSONArray tmpMemberlist = new JSONArray();
//                if (!CollectionUtils.isEmpty(mainMemberList)){
//                    for (Object  member:mainMemberList) {
//                        JSONObject  menberObj =   (JSONObject)member;
//                        menberObj.put("age",LocalDateUtils.getAge(menberObj.getString("birthDate")));
//                        tmpMemberlist.add(menberObj);
//                    }
//                }
                voJson.put("mainMemberList",mainMemberList);

                if (voJson.getJSONArray("budgetPreYearList") != null && voJson.getJSONArray("budgetPreYearList").size()==0){
                    voJson.remove("budgetPreYearList");
                }
                JSONObject perJson = JSONObject.parseObject(voJson.getString("leadPerson"));
                JSONObject dept = JSONObject.parseObject(voJson.getString("leadDept"));
                String birthDate = perJson.getString("birthDate");
                perJson.put("birthDate", LocalDateUtils.parse(birthDate,LocalDateUtils.PARRERN_YM));
                perJson.put("age",LocalDateUtils.getAge(birthDate));
                dataMap.put("data", voJson);
                dataMap.put("per", perJson);
                dataMap.put("dept", dept);
            }

            //当前时间
            String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
            dataMap.put("nowDate", nowDate);

            String filename = "医科院基科费项目申请书_"+ DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999)+".docx";
            String finalWordName =  wordExportService.exportWord("template_apply_ace_my",dataMap, filename);
            fileIn = new FileInputStream(finalWordName);
            return finalWordName;
        } catch (Exception e) {
            log.error("导出word异常。", e);
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
     * 导出pdf tanwx
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    @Override
    public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo){
        File pdfFile = null;
        FileInputStream fileInputStream = null;
        SrpmsProject project = projectService.getById(projectId);

        try {
//            String docxFilePath = this.exportWordFile(projectId, userVo, deptVo);
//            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
//            String pdfFilePath = jarPath+ "/"+project.getProjectName()+"申请书.pdf";
//            WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);

            String pdfFilePath = this.exportPdfFile(projectId, userVo, deptVo);
            pdfFile = new File(pdfFilePath);
            fileInputStream = new FileInputStream(pdfFile);

            MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);

            if (result.isSuccess()) {
                FileInfoVo fileInfoVo = result.getData();
                if(fileInfoVo != null){
                    SrpmsProject projectFile = new SrpmsProject();
                    projectFile.setId(project.getId());
                    projectFile.setApplyBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
                    projectFile.setApplyBookFileName(fileInfoVo.getFileName());
                    projectFile.setApplyBookFileUrl(fileInfoVo.getFileUrl());
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

    /**tanwx
     * word文档导入
     * @param
     * @return
     */
    @Override
    public SrpmsProjectApplyAcademySaveVo importWord(String wordFileUrl){
        SrpmsProjectApplyAcademySaveVo vo = new SrpmsProjectApplyAcademySaveVo();
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

            Elements tableElements = document.getElementsByTag("table");
            Element baseTable = tableElements.get(0);
            vo.setProjectName(extractCellFromTable(baseTable,2,2));
            vo.setApplyFunds(NumberUtils.toDouble(extractCellFromTable(baseTable,3,1)));
            vo.setProjectAbstract(extractCellFromTable(baseTable,4,1,true));

            //--项目信息
            vo.setProjectName(extractCellFromTable(baseTable,2,2));

            //研究内容和预期目标
            vo.setApprovalBasisMeaning(extractCellFromTable(tableElements.get(1),0,0,true));
            vo.setContentTargetProblem(extractCellFromTable(tableElements.get(2),0,0,true));
            vo.setResearchSchemeFeasibility(extractCellFromTable(tableElements.get(3),0,0,true));
            vo.setProjectExpectTarget(extractCellFromTable(tableElements.get(4),0,0,true));
            vo.setProjectTechnicalInnovation(extractCellFromTable(tableElements.get(5),0,0,true));

            //研究基础和工作条件
            vo.setResearchFoundation(extractCellFromTable(tableElements.get(6),0,0,true));
            vo.setWorkinfConditions(extractCellFromTable(tableElements.get(7),0,0,true));
            vo.setProjectApplicantIntroduction(extractCellFromTable(tableElements.get(8),0,0,true));

            //主要参与人员
//            List<SrpmsProjectPersonJoinVo> mainMemberList = new ArrayList<>();
//            List<List<String>> list = extractListFromTable(tableElements.get(9), 6, 0);
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
//            vo.setMainMemberList(mainMemberList);
//
//            //预算明细 判断有几个table
//            int num = 0;
//            for(int i=10; i<100; i++){
//                list = extractListFromTable(tableElements.get(i), 0, 0);
//                if(list.size()<10) break;
//                for(List<String> detail: list){
//                    if(detail.get(0).trim().replaceAll("[\\d+]","").equals("年度预算内容")){
//                        num ++ ;
//                    }else {
//                        break;
//                    }
//                }
//            }
//            List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
//            JSONObject commonObj = null;
//            JSONArray commonArr = null;
//            for(int a = 0;a < num;a++){
//                list = extractListFromTable(tableElements.get(a+10), 0, 0);
//                SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
//                //年度
//                int budgetYear =  NumberUtils.toInt(list.get(0).get(0).trim().replaceAll("[^\\d+]",""));
//                budgetDetailVo.setBudgetYear(budgetYear);
//
//                //明细
//                JSONArray budgetDetail = new JSONArray();
//
//                //1.人员费用
//                JSONObject pensionCostObject = new JSONObject();
//                pensionCostObject.put("pensionCost", "(一)人员费");
//                pensionCostObject.put("serial", "1");
//                pensionCostObject.put("amount", list.get(1).get(1));
//                pensionCostObject.put("remark", list.get(1).get(2));
//
//                commonArr = new JSONArray();
//                commonObj = new JSONObject();
//                commonObj.put("serial", "1");
//                commonObj.put("name", "专家咨询费");
//                commonObj.put("amount", list.get(2).get(1));
//                commonObj.put("remark", list.get(2).get(2));
//                commonArr.add(commonObj);
//
//                commonObj = new JSONObject();
//                commonObj.put("serial", "2");
//                commonObj.put("name", "劳务费");
//                commonObj.put("amount", list.get(3).get(1));
//                commonObj.put("remark", list.get(3).get(2));
//                commonArr.add(commonObj);
//
//                pensionCostObject.put("child",commonArr);
//                budgetDetail.add(pensionCostObject);
//
//                //2.业务费
//                JSONObject serviceAmountObject = new JSONObject();
//                serviceAmountObject.put("serviceAmount", "(二)相关业务费");
//                serviceAmountObject.put("serial", "2");
//                serviceAmountObject.put("amount", list.get(4).get(1));
//                serviceAmountObject.put("remark", list.get(4).get(2));
//                commonArr = new JSONArray();
//                commonObj = new JSONObject();
//                commonObj.put("serial","1");
//                commonObj.put("name","材料费");
//                commonObj.put("amount", list.get(5).get(1));
//                commonObj.put("remark", list.get(5).get(2));
//                commonArr.add(commonObj);
//
//                commonObj = new JSONObject();
//                commonObj.put("serial","2");
//                commonObj.put("name","测试化验加工费");
//                commonObj.put("amount", list.get(6).get(1));
//                commonObj.put("remark", list.get(6).get(2));
//                commonArr.add(commonObj);
//
//                commonObj = new JSONObject();
//                commonObj.put("serial","3");
//                commonObj.put("name","差旅费/会议费/国际合作费");
//                commonObj.put("amount", list.get(7).get(1));
//                commonObj.put("remark", list.get(7).get(2));
//                commonArr.add(commonObj);
//
//                commonObj = new JSONObject();
//                commonObj.put("serial","4");
//                commonObj.put("name","出版/文献/信息传播/知识产权事务费");
//                commonObj.put("amount", list.get(8).get(1));
//                commonObj.put("remark", list.get(8).get(2));
//                commonArr.add(commonObj);
//
//                serviceAmountObject.put("child",commonArr);
//                budgetDetail.add(serviceAmountObject);
//
//                //3.其他
//                JSONObject otherObject = new JSONObject();
//                otherObject.put("name", "(三)其他(注明详细事项)");
//                otherObject.put("serial", "3");
//                otherObject.put("amount", list.get(9).get(1));
//                otherObject.put("remark", list.get(9).get(2));
//                commonArr = new JSONArray();
//                for(int i = 10; i < 12; i++){
//                    if(StringUtils.isBlank(list.get(i).get(0))){
//                        break;
//                    }
//                    commonObj = new JSONObject();
//                    commonObj.put("serial",i-9);
//                    commonObj.put("name",list.get(i).get(0));
//                    commonObj.put("amount",list.get(i).get(1));
//                    commonObj.put("remark",list.get(i).get(2));
//                    commonArr.add(commonObj);
//                }
//                otherObject.put("child",commonArr);
//                budgetDetail.add(otherObject);
//
//                //金额合计
//                //budgetAmount
//                budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(list.get(list.size()-1).get(1)));
//
//                budgetDetailVo.setBudgetDetail(budgetDetail);
//                budgetPreYearList.add(budgetDetailVo);
//            }
//
//            vo.setBudgetPreYearList(budgetPreYearList);

            //预算说明
            vo.setBudgetDescription(extractCellFromTable(tableElements.get(tableElements.size()-1),0,0,true));
            WordConvertUtil.delHtmlFile(htmlFilePath);
            return vo;
        } catch (IOException e) {
            log.error("解析word文件发生异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }
}

