package com.deloitte.services.srpmp.project.apply.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.RandomUtils;
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
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplyReformQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyReformForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyReformVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonVo;
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
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyReform;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyReformMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyReformService;
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
public class SrpmsProjectApplyReformServiceImpl extends ServiceImpl<SrpmsProjectApplyReformMapper, SrpmsProjectApplyReform> implements ISrpmsProjectApplyReformService {
    @Autowired
    private ISrpmsProjectService projectService;

    //加载上传 uploadFiles
    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private SrpmsProjectApplyReformMapper srpmsProjectApplyReformMapper;

    @Autowired
    private ISrpmsProjectPersonService srpmsProjectPersonService;

    @Autowired
    private ISrpmsProjectPersonJoinService srpmsProjectPersonJoinService;

    @Autowired
    private ISrpmsProjectService srpmsProjectService;

    @Autowired
    SrpmsProjectApplyInnCommonServiceImpl commonService;

    @Autowired
    private ISrpmsProjectFlowService flowServicel;

    @Autowired
    private WordExportServiceImpl wordExportService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private PdfMergeServiceImpl pdfMergeService;

	@Autowired
	private ISrpmsCommonNclobService commonNclobService;
	
    @Override
    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
        String docxFilePath = this.exportWordFile(projectId, userVo, deptVo);
        //转格式
        String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
        String pdfFinalName = docxFilePath.replace("docx", "pdf");
        log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
        //合并
        pdfMergeService.mergeAttachmentApply(pdfFilePath, projectId, pdfFinalName);
        return pdfFinalName;
    }

    @Override
    public Long saveAndUpdatReform(SrpmsProjectApplyReformForm vo, DeptVo deptVo) {
        // 项目ID
        long projectId = 0l;
        SrpmsProjectApplyReform reformEntity = JSONObject.parseObject(JSON.toJSONString(vo), SrpmsProjectApplyReform.class);
        JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(vo));
        if (vo.getId() == null) {
            projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.STRUCTURAL_REFORM, deptVo);
            reformEntity.setId(projectId);
            JSONObject personJson = projectJson.getJSONObject("leadPerson");
            if (personJson != null) {
                reformEntity.setProjectApplicantId(personJson.getLong("id"));
                reformEntity.setProjectCommitmentUnit(personJson.getString("projectCommitmentUnit"));
            }
            this.save(reformEntity);
        } else {
            //校验项目状态 已提交的状态后将无修改保存
            SrpmsProject project = projectService.getById(vo.getId());
            if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())) {
                throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
            }
            projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.STRUCTURAL_REFORM);
            this.updateById(reformEntity);
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
		
        JSONObject result = commonService.queryCommonInfoById(projectId, ProjectCategoryEnums.STRUCTURAL_REFORM);
        result.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));
        result.put("id", projectId + "");
        result.put("subjectCategory", result.getString("subjectCategory") == null ? "" : JSONArray.parseArray(result.getString("subjectCategory")));
        JSONConvert.convertJson(result);
        return result;
    }

    @Override
    public void submitApply(SrpmsProjectApplyReformForm vo, UserVo userVo, DeptVo deptVo) {
        //保存申请书
        long projectId = this.saveAndUpdatReform(vo, deptVo);

        //更新项目状态
        projectService.submitProject(projectId);

        //生成PDF
        this.generateApplyBookPdf(projectId, userVo, deptVo);

        log.info("科技体制改革，提交申请书，已更新项目状态, projectId:{}", projectId);
        //发起流程
        flowServicel.startAuditProcess(projectId, VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);

        log.info("科技体制改革，提交申请书，已发起流程, projectId{}", projectId);

    }

    @Override
    public SrpmsProjectApplyReformVo getAcademyById(long id) {
        SrpmsProjectApplyReform applyReform = this.getById(id);
        if (applyReform == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        SrpmsProjectApplyReformVo applyReformVo = new SrpmsProjectApplyReformVo();
        BeanUtils.copyProperties(applyReform, applyReformVo);
        if (applyReformVo.getProjectApplicantId() != null) {
            //项目申请者
            SrpmsProjectPerson applicantInformation = srpmsProjectPersonService.getById(applyReformVo.getProjectApplicantId());
            if (applicantInformation != null) {
                SrpmsProjectPersonVo srpmsProjectPersonVo = new SrpmsProjectPersonVo();
                BeanUtils.copyProperties(applicantInformation, srpmsProjectPersonVo);
//                srpmsProjectApplyAcademySaveVo.setApplicantInformation(srpmsProjectPersonVo);
            }
            //项目申请人
            SrpmsProjectPersonJoin application = srpmsProjectPersonJoinService.getById(applyReform.getProjectApplicantId());
            if (application != null) {
                SrpmsProjectPersonJoinVo projectAppliaction = new SrpmsProjectPersonJoinVo();
                BeanUtils.copyProperties(application, projectAppliaction);
//                srpmsProjectApplyAcademySaveVo.setProjectApplicant(projectAppliaction);
            }
        }

        //项目参与者
        List<SrpmsProjectPersonJoinVo> personJoinVoList = srpmsProjectPersonJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_MAIN_MEMBER, applyReform.getId());
//        srpmsProjectApplyAcademySaveVo.setPersonJoinVoList(personJoinVoList);

        return applyReformVo;
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
        //删除科技体制改革
        this.removeById(id);

    }


    @Override
    public IPage<SrpmsProjectApplyReform> selectPage(SrpmsProjectApplyReformQueryForm queryForm) {
        QueryWrapper<SrpmsProjectApplyReform> queryWrapper = new QueryWrapper<SrpmsProjectApplyReform>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApplyReformMapper.selectPage(new Page<SrpmsProjectApplyReform>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<SrpmsProjectApplyReform> selectList(SrpmsProjectApplyReformQueryForm queryForm) {
        QueryWrapper<SrpmsProjectApplyReform> queryWrapper = new QueryWrapper<SrpmsProjectApplyReform>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApplyReformMapper.selectList(queryWrapper);
    }

    @Override
    /**
     * 导出基科院申请书
     */
    public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
        InputStream fileIn = null;
        try {
            Map<String, Object> dataMap = new HashMap<>();
            if (projectId == NumberUtils.LONG_ZERO) {
                JSONObject perJson = (JSONObject) JSON.toJSON(userVo);
                String birthDate = perJson.getString("birthDate");
                perJson.put("birthDate", LocalDateUtils.parse(birthDate, LocalDateUtils.PARRERN_YM));
                perJson.put("age", LocalDateUtils.getAge(birthDate));
                dataMap.put("per", perJson);
                dataMap.put("data", new JSONObject());
                dataMap.put("dept", JSONObject.parseObject(JSONObject.toJSONString(deptVo)));
            } else {
                //获取所有信息
                JSONObject voJson = this.queryApplyVoById(projectId, userVo, deptVo);
                voJson.put("projectActionDateStart", LocalDateUtils.parse(voJson.getString("projectActionDateStart"), LocalDateUtils.PARRERN_YMD));
                voJson.put("projectActionDateEnd", LocalDateUtils.parse(voJson.getString("projectActionDateEnd"), LocalDateUtils.PARRERN_YMD));

                //文本信息
                voJson.put("projectAbstract", WordConvertUtil.htmlToText(voJson.getString("projectAbstract")));
                voJson.put("approvalBasisMeaning", WordConvertUtil.htmlToText(voJson.getString("approvalBasisMeaning")));
                voJson.put("contentTargetProblem", WordConvertUtil.htmlToText(voJson.getString("contentTargetProblem")));
                voJson.put("researchSchemeFeasibility", WordConvertUtil.htmlToText(voJson.getString("researchSchemeFeasibility")));
                voJson.put("projectExpectTarget", WordConvertUtil.htmlToText(voJson.getString("projectExpectTarget")));
                voJson.put("projectTechnicalInnovation", WordConvertUtil.htmlToText(voJson.getString("projectTechnicalInnovation")));
                voJson.put("researchFoundation", WordConvertUtil.htmlToText(voJson.getString("researchFoundation")));
                voJson.put("projectApplicantIntroduction", WordConvertUtil.htmlToText(voJson.getString("projectApplicantIntroduction")));
                voJson.put("budgetDescription", WordConvertUtil.htmlToText(voJson.getString("budgetDescription")));
                voJson.put("workinfConditions", WordConvertUtil.htmlToText(voJson.getString("workinfConditions")));
                if (voJson.getJSONArray("budgetPreYearList") != null && voJson.getJSONArray("budgetPreYearList").size() == 0) {
                    voJson.remove("budgetPreYearList");
                }
                JSONObject perJson = JSONObject.parseObject(voJson.getString("leadPerson"));
                JSONObject dept = JSONObject.parseObject(voJson.getString("leadDept"));
                String birthDate = perJson.getString("birthDate");
                perJson.put("birthDate", LocalDateUtils.parse(birthDate, LocalDateUtils.PARRERN_YM));
                perJson.put("age", LocalDateUtils.getAge(birthDate));
                dataMap.put("data", voJson);
                dataMap.put("per", perJson);
                dataMap.put("dept", dept);
            }

            //当前时间
            String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
            dataMap.put("nowDate", nowDate);

            String filename = "医科科技体制改革项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
            String finalWordName = wordExportService.exportWord("template_apply_reform", dataMap, filename);
            fileIn = new FileInputStream(finalWordName);
            return finalWordName;
        } catch (Exception e) {
            log.error("导出word异常。", e);
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
     * 导出pdf tanwx
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
                if (fileInfoVo != null) {
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
            if (pdfFile != null && pdfFile.exists()) {
                pdfFile.delete();
            }
        }
    }

    /**
     * word文档导入
     *
     * @param
     * @return
     */
    @Override
    public SrpmsProjectApplyReformVo importWord(String wordFileUrl) {
        try {
            SrpmsProjectApplyReformVo vo = new SrpmsProjectApplyReformVo();
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
            Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
            //基本信息
            Elements actionTimeElements = document.getElementsMatchingOwnText("项目起止年限：");
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

            //研究内容和预期目标
            vo.setApprovalBasisMeaning(extractCellFromTable(tableElements.get(1), 0, 0, true));
            vo.setContentTargetProblem(extractCellFromTable(tableElements.get(2), 0, 0, true));
            vo.setProjectTechnicalInnovation(extractCellFromTable(tableElements.get(3), 0, 0, true));
            vo.setProjectExpectTarget(extractCellFromTable(tableElements.get(4), 0, 0, true));

            //研究基础和工作条件
            vo.setResearchFoundation(extractCellFromTable(tableElements.get(5), 0, 0, true));
            vo.setWorkinfConditions(extractCellFromTable(tableElements.get(6), 0, 0, true));
            vo.setProjectApplicantIntroduction(extractCellFromTable(tableElements.get(7), 0, 0, true));

//          //主要参与人员
//          List<SrpmsProjectPersonJoinVo> mainMemberList = new ArrayList<>();
//          List<List<String>> list = extractListFromTable(tableElements.get(9), 6, 0);
//          for(List<String> perVo: list){
//              if(StringUtils.isBlank(perVo.get(0))){
//                  break;
//              }
//              SrpmsProjectPersonJoinVo joinVo = new SrpmsProjectPersonJoinVo();
//              joinVo.setPersonName(perVo.get(0));
//              joinVo.setGender(perVo.get(1));
//              joinVo.setAge(NumberUtils.toDouble(perVo.get(2)));
//              joinVo.setPositionTitle(perVo.get(3));
//              joinVo.setMajor(perVo.get(4));
//              mainMemberList.add(joinVo);
//          }
//          vo.setMainMemberList(mainMemberList);

            //预算说明
            vo.setBudgetDescription(extractCellFromTable(tableElements.get(tableElements.size() - 1), 0, 0, true));
            WordConvertUtil.delHtmlFile(htmlFilePath);

            return vo;
        } catch (Exception e) {
            log.error("解析word文件发生异常.", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }
    }
}

