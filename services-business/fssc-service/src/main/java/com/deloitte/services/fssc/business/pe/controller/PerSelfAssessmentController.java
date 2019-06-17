package com.deloitte.services.fssc.business.pe.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.pe.param.PerSelfAssessmentQueryForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfAssessmentForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfAssessmentVo;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfTargetForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfTargetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.pe.entity.PerSelfAssessment;
import com.deloitte.services.fssc.business.pe.entity.PerSelfTarget;
import com.deloitte.services.fssc.business.pe.service.IPerSelfAssessmentService;
import com.deloitte.services.fssc.business.pe.service.IPerSelfTargetService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.performance.entity.PerformanceIndex;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :   PerSelfAssessment控制器实现类
 * @Modified :
 */
@Api(tags = "PerSelfAssessment操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/pe/per-self-assessment")
public class PerSelfAssessmentController {

    @Autowired
    private IPerSelfAssessmentService perSelfAssessmentService;

    @Autowired
    private IPerSelfTargetService perSelfTargetService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    private IPerformanceIndexService indexService;

    @Autowired
    private IBudgetProjectService projectService;

    /**
     * 保存
     *
     * @param perSelfAssessmentForm
     * @return
     */
    private PerSelfAssessment saveOrUpdate(PerSelfAssessmentForm perSelfAssessmentForm) {
        AssertUtils.asserts(perSelfAssessmentForm.getId() != null, FsscErrorType.ID_CANT_BE_NULL);
        List<Long> fileIds = perSelfAssessmentForm.getFileIds();
        PerSelfAssessment oldPerSelfAssessment = perSelfAssessmentService.getById(perSelfAssessmentForm.getId());
        PerSelfAssessment newPerSelfAssessment =
                new BeanUtils<PerSelfAssessment>().copyObj(perSelfAssessmentForm, PerSelfAssessment.class);
        newPerSelfAssessment.setProjectId(oldPerSelfAssessment.getProjectId());
        newPerSelfAssessment.setId(oldPerSelfAssessment.getId());
        perSelfAssessmentService.saveOrUpdate(newPerSelfAssessment);
        List<PerSelfTargetForm> perSelfTargetForms = perSelfAssessmentForm.getPerSelfTargetForms();
        if (CollectionUtils.isNotEmpty(perSelfTargetForms)) {
            for (PerSelfTargetForm form : perSelfTargetForms) {
                AssertUtils.asserts(form.getId() != null, FsscErrorType.ID_CANT_BE_NULL);
            }
            List<PerSelfTarget> perSelfTargets = new BeanUtils<PerSelfTarget>().copyObjs(perSelfTargetForms, PerSelfTarget.class);
            for (PerSelfTarget target : perSelfTargets){
                target.setDocumentId(oldPerSelfAssessment.getId());
            }
            perSelfTargetService.saveOrUpdateBatch(perSelfTargets);
        }
        //文件列表保存
        if (fileIds != null) {
            fileIds.removeAll(Collections.singleton(null));
        }
        if (CollectionUtils.isNotEmpty(fileIds)) {
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID, perSelfAssessmentForm.getId())
                    .eq(File.DOCUMENT_TYPE, FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue())
                    .notIn(File.ID, fileIds);
            fileService.remove(fileQueryWrapper);
            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files), FsscErrorType.FILE_IS_NULL);
            for (File file : files) {
                file.setDocumentId(perSelfAssessmentForm.getId());
            }
            fileService.saveOrUpdateBatch(files);
        }
        return perSelfAssessmentService.getById(oldPerSelfAssessment.getId());
    }


    @ApiOperation(value = "修改", notes = "新增一个PerSelfAssessment")
    @PostMapping(value = "updateAssessment")
    public Result<PerSelfAssessmentVo> add(@Valid @RequestBody @ApiParam(name = "perSelfAssessmentForm",
            value = "新增PerSelfAssessment的form表单", required = true)
                                                   PerSelfAssessmentForm perSelfAssessmentForm) {
        log.info("form:{}", perSelfAssessmentForm.toString());

        PerSelfAssessment assessment = saveOrUpdate(perSelfAssessmentForm);
        return Result.success(new BeanUtils<PerSelfAssessmentVo>().copyObj(assessment, PerSelfAssessmentVo.class));
    }


    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @ApiOperation(value = "修改", notes = "新增一个PerSelfAssessment")
    @PostMapping(value = "doProcess")
    public Result<PerSelfAssessmentVo> doProcess(@Valid @RequestBody @ApiParam(name = "perSelfAssessmentForm",
            value = "新增PerSelfAssessment的form表单", required = true)
                                                         PerSelfAssessmentForm perSelfAssessmentForm) {
        PerSelfAssessment assessment = saveOrUpdate(perSelfAssessmentForm);
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(assessment.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(assessment.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(assessment.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setProjectId(assessment.getProjectId());
        valueForm.setDocumentId(assessment.getId());
        valueForm.setDocumentType(FsscTableNameEums.PER_SELF_ASSESSMENT.getValue());
        valueForm.setDeptCode(assessment.getDoDeptCode());

        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);


        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(assessment.getId());
        startForm.setDocumentNum(assessment.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.PER_SELF_ASSESSMENT.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        startForm.setBudgetWarningCheck("N");

        if (FsscEums.FIRST_SUBMIT.getValue().equals(perSelfAssessmentForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(perSelfAssessmentForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        return Result.success(new BeanUtils<PerSelfAssessmentVo>().copyObj(assessment, PerSelfAssessmentVo.class));
    }


    @ApiOperation(value = "获取PerSelfAssessment", notes = "获取指定ID的PerSelfAssessment信息")
    @GetMapping(value = "getById/{id}")
    public Result<PerSelfAssessmentVo> get(@PathVariable(value = "id") Long id) {
        log.info("get with id:{}", id);
        AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        PerSelfAssessment perSelfAssessment = perSelfAssessmentService.getById(id);
        AssertUtils.asserts(perSelfAssessment != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        PerSelfAssessmentVo perSelfAssessmentVo =
                new BeanUtils<PerSelfAssessmentVo>().copyObj(perSelfAssessment, PerSelfAssessmentVo.class);
        if (perSelfAssessmentVo.getProjectId() != null) {
            BudgetProject project = projectService.getProjectById(perSelfAssessment.getProjectId());
            if (project != null) {
                perSelfAssessmentVo.setProjectName(project.getProjectName());
                perSelfAssessmentVo.setProjectDutyId(Long.valueOf(project.getProjectDutyId()));
                perSelfAssessmentVo.setProjectDuty(project.getProjectDuty());
            }
        }
        QueryWrapper<PerSelfTarget> targetQueryWrapper = new QueryWrapper<>();

        targetQueryWrapper.eq(PerSelfTarget.DOCUMENT_ID, id)
                .eq(PerSelfTarget.DOCUMENT_TYPE, FsscTableNameEums.PER_SELF_ASSESSMENT.getValue());

        List<PerSelfTargetVo> selfTargetVoList = new BeanUtils<PerSelfTargetVo>()
                .copyObjs(perSelfTargetService.list(targetQueryWrapper), PerSelfTargetVo.class);
        if (CollectionUtils.isNotEmpty(selfTargetVoList)){
            List<String> codeList = new ArrayList<>();
            for(PerSelfTargetVo targetVo : selfTargetVoList){
                codeList.add(targetVo.getFirstPer());
                codeList.add(targetVo.getSecondPer());
                codeList.add(targetVo.getThiredPer());
            }
            Map<String,PerformanceIndex> indexCodeBeanMap = indexService.selectIndexByCodes(codeList);
            for(PerSelfTargetVo targetVo : selfTargetVoList){
                PerformanceIndex firstIndex = indexCodeBeanMap.get(targetVo.getFirstPer());
                PerformanceIndex secondIndex = indexCodeBeanMap.get(targetVo.getSecondPer());
                PerformanceIndex thiredIndex = indexCodeBeanMap.get(targetVo.getThiredPer());
                targetVo.setFirstPerName(firstIndex != null ? firstIndex.getName() : "");
                targetVo.setSecondPerName(firstIndex != null ? secondIndex.getName() : "");
                targetVo.setThiredPerName(firstIndex != null ? thiredIndex.getName() : "");
            }
        }
        perSelfAssessmentVo.setTargetVos(selfTargetVoList);

        perSelfAssessmentVo.setBpmProcessTaskVos(
                bpmTaskBiz.findHistoryNoException(id, FsscTableNameEums.PER_SELF_ASSESSMENT.getValue()));
        return new Result<PerSelfAssessmentVo>().sucess(perSelfAssessmentVo);
    }

    @ApiOperation(value = "分页查询PerSelfAssessment", notes = "根据条件查询PerSelfAssessment分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<PerSelfAssessmentVo>> search(@ApiParam(name = "perSelfAssessmentQueryForm", value = "PerSelfAssessment查询参数", required = true) PerSelfAssessmentQueryForm perSelfAssessmentQueryForm) {
        log.info("search with perSelfAssessmentQueryForm:{}", perSelfAssessmentQueryForm.toString());
        IPage<PerSelfAssessment> perSelfAssessmentPage =
                perSelfAssessmentService.selectPage(perSelfAssessmentQueryForm);
        IPage<PerSelfAssessmentVo> perSelfAssessmentVoPage =
                new BeanUtils<PerSelfAssessmentVo>().copyPageObjs(perSelfAssessmentPage, PerSelfAssessmentVo.class);
        if (CollectionUtils.isNotEmpty(perSelfAssessmentPage.getRecords())){
            List<Long> ids = perSelfAssessmentVoPage.getRecords().stream().map(PerSelfAssessmentVo :: getProjectId).collect(Collectors.toList());
            Map<Long,BudgetProject> idBeanMap = projectService.selectByProjectByIds(ids);
            for(PerSelfAssessmentVo assessmentVo : perSelfAssessmentVoPage.getRecords()){
                BudgetProject project = idBeanMap.get(assessmentVo.getProjectId());
                if (assessmentVo.getProjectId() != null && project != null) {
                    assessmentVo.setProjectName(project.getProjectName());
                    assessmentVo.setProjectDutyId(project.getProjectDutyId() != null ? Long.valueOf(project.getProjectDutyId()) : assessmentVo.getProjectDutyId());
                    assessmentVo.setProjectDuty(project.getProjectDuty());
                }
            }
        }
        return new Result<IPage<PerSelfAssessmentVo>>().sucess(perSelfAssessmentVoPage);
    }

}



