package com.deloitte.services.fssc.budget.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectBudgetQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryParam;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetVo;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectBudgetService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :   项目 控制器实现类
 * @Modified :
 */
@Api(tags = "项目 操作接口")
@Slf4j
@RestController
public class BudgetProjectController{

    @Autowired
    private IBudgetProjectService projectService;

    @Autowired
    private IBudgetProjectBudgetService projectBudgetService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @ApiOperation(value = "新增/修改 项目", notes = "新增/修改一个项目",httpMethod = "POST")
    @ApiParam(name = "projectForm", value = "新增/修改 项目的form表单", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "project/updateProject")
    @Transactional
    public Result<BudgetProjectVo> addOrUpdateProjectBudget(@Valid @RequestBody BudgetProjectForm projectForm) throws Exception{
        log.info("form:", projectForm.toString());
        BudgetProject project = new BeanUtils<BudgetProject>().copyObj(projectForm,BudgetProject.class);
        BudgetProject budgetProject = projectService.getById(project.getId());
        String fsscCode = budgetProject.getFsscCode();
        if(StringUtil.isNotEmpty(fsscCode)&&StringUtil.isNotEmpty(projectForm.getFsscCode())){
            AssertUtils.asserts(fsscCode.equals(projectForm.getFsscCode()),FsscErrorType.FSSC_CODE_CANT_MODIFY);
        }
        projectService.saveOrUpdate(project);
        if(StringUtils.isNotEmpty(budgetProject.getResponsibleUnitCode())){
            accountVoucherToEbsService.sendProjectInfoToEbs(projectForm.getFsscCode(),budgetProject.getProjectName());
        }
        return Result.success(new BeanUtils<BudgetProjectVo>()
                .copyObj(projectService.getById(project.getId()),BudgetProjectVo.class));
    }

    /**
     * 分页查询项目信息
     * @return
     */
    @GetMapping(value = "project/page/conditons")
    public Result<IPage<BudgetProjectVo>> pageConditions(BudgetProjectQueryParam projectQueryParam){
        IPage<BudgetProject> page = projectService.pageConditions(projectQueryParam);
        IPage<BudgetProjectVo> budgetProjectVoIPage = new BeanUtils<BudgetProjectVo>().copyPageObjs(page, BudgetProjectVo.class);
        return Result.success(budgetProjectVoIPage);
    }


    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "project/exportExcel")
    @ResponseBody
    public void exportExcel( BudgetProjectQueryParam projectQueryParam)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(projectQueryParam));
        Map<String, String> projectStatus = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PROJECT_STATUS_Y.getValue()));
        IPage<BudgetProject> page=projectService.pageConditions(projectQueryParam);
        List<BudgetProject> records = page.getRecords();
        String[] title = {"序号", "项目编码", "项目状态","项目类型", "项目名称", "依托单位", "项目启动日期", "项目结束日期"
                , "金额", "负责人"};
        String fileName = "项目列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BudgetProject vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getProjectCode();
            content[i][2] = projectStatus.get(vo.getProjectStatus());
            content[i][3] = vo.getProjectType();
            content[i][4] = vo.getProjectName();
            content[i][5] = vo.getOrgUnitName();
            if (vo.getProjectStartTime() != null) {
                content[i][6] = LocalDateTimeUtils.formatTime(vo.getProjectStartTime(), "yyyy-MM-dd HH:mm:ss");
            }
            if (vo.getProjectEndTime() != null) {
                content[i][7] = LocalDateTimeUtils.formatTime(vo.getProjectEndTime(), "yyyy-MM-dd HH:mm:ss");
            }
            if(vo.getTotalAmount()!=null){
                content[i][8] = vo.getTotalAmount().toString();
            }
            content[i][9] = vo.getProjectDuty();
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    @GetMapping(value = "project/getById/{id}")
    public Result<BudgetProjectVo> getById(@PathVariable(value = "id") Long id){
        AssertUtils.asserts(id!=null, FsscErrorType.ID_CANT_BE_NULL);
        BudgetProject budgetProject = projectService.getById(id);
        AssertUtils.asserts(budgetProject != null,FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        BudgetProjectVo projectVo = new BeanUtils<BudgetProjectVo>().copyObj(budgetProject,BudgetProjectVo.class);
        if (budgetProject.getParentId() != null) {
            BudgetProject parent = projectService.getProjectById(budgetProject.getParentId());
            projectVo.setParentCodeName(parent.getProjectCode()+"("+parent.getProjectName()+")");
        }
        return Result.success(projectVo);
    }


    @ApiOperation(value = "根据条件查询有子任务的项目列表数据,用于下拉框数据源", notes = "根据条件查询有子任务的项目列表数据", httpMethod = "GET")
    @ApiParam(name = "queryForm", value = "项目查询参数", required = true)
    @GetMapping("/listParent")
    public Result<List<Select2DataSource>> list() {
        DeptVo deptVo = commonServices.getCurrentDept();
        BudgetProjectQueryForm queryForm = new BudgetProjectQueryForm();
        queryForm.setParentFlag(FsscEums.YES.getValue());
        queryForm.setOrgUnitCode(deptVo.getDeptCode());
        List<BudgetProject> parentList = projectService.selectList(queryForm);
        if (CollectionUtils.isEmpty(parentList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(parentList.size());
        for (BudgetProject project : parentList){
            Select2DataSource dataSource = new Select2DataSource();
            dataSource.setCode(project.getProjectCode());
            dataSource.setId(StringUtil.objectToString(project.getId()));
            dataSource.setValue(StringUtil.objectToString(project.getId()));
            dataSource.setText(project.getProjectCode()+""+project.getProjectName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "列表查询项目,用于下拉框数据源", notes = "根据条件查询项目列表数据", httpMethod = "POST")
    @ApiParam(name = "queryForm", value = "项目查询参数", required = true)
    @PostMapping("/list/conditions")
    public Result<List<Select2DataSource>> list(@Valid @RequestBody BudgetProjectQueryForm queryForm) {
        queryForm.setParentFlag(FsscEums.NO.getValue());
        log.info("search with queryForm:", queryForm.toString());
        List<BudgetProject> projectList = projectService.selectList(queryForm);
        if (CollectionUtils.isEmpty(projectList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(projectList.size());
        for (BudgetProject project : projectList){
            Select2DataSource dataSource = new Select2DataSource();
            dataSource.setCode(project.getProjectCode());
            dataSource.setId(StringUtil.objectToString(project.getId()));
            dataSource.setValue(StringUtil.objectToString(project.getId()));
            dataSource.setText(project.getProjectName());
            dataSource.setInComeMainTypeId(StringUtil.objectToString(project.getInComeMainTypeId()));
            dataSource.setInComeSubTypeId(StringUtil.objectToString(project.getInComeSubTypeId()));
            dataSource.setProjectUnitId(project.getOrgUnitId());
            dataSource.setPaymentConfirmType(project.getPaymentConfirmType());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "列表查询项目预算科目,用于下拉框数据源",
            notes = "列表查询项目预算科目,用于单据中选择项目后,将根据项目id带出项目预算科目", httpMethod = "POST")
    @ApiImplicitParam(name = "budgetProjectId", value = "项目id", required = true, paramType = "path")
    @PostMapping("/listSelectProjectBudgetAccount/{budgetProjectId}")
    public Result<List<Select2DataSource>> listSelect(@PathVariable String budgetProjectId){
        log.info("search with budgetProjectId:", budgetProjectId);
        BudgetProjectBudgetQueryForm queryForm = new BudgetProjectBudgetQueryForm();
        queryForm.setBudgetProjectId(Long.valueOf(budgetProjectId));
        //项目预算不根据当前登陆人的单位查询,因为项目并不一定是本单位承担的
        queryForm.setOrgUnitCode(null);
        List<BudgetProjectBudgetVo> projectBudgetVoList  =  projectBudgetService.selectVoList(queryForm);
        if (CollectionUtils.isEmpty(projectBudgetVoList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(projectBudgetVoList.size());
        for (BudgetProjectBudgetVo projectBudgetVo : projectBudgetVoList){
            Select2DataSource dataSource = new Select2DataSource(projectBudgetVo.getBudgetAccountId(),
                    projectBudgetVo.getBudgetAccountCode(),
                    projectBudgetVo.getBudgetAccountName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }
}



