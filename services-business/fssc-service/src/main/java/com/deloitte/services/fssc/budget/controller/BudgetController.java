package com.deloitte.services.fssc.budget.controller;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.fssc.budget.vo.BudgetCheckForm;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.budget.entity.Budget;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.deloitte.services.fssc.budget.service.IBudgetBasicBudgetService;
import com.deloitte.services.fssc.budget.service.IBudgetControlService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectBudgetService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.DateUtil;
import com.deloitte.services.fssc.util.UUIDUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.ExcelImportUtil;
import com.deloitte.services.fssc.util.excel.ExcelResult;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-06
 * @Description :  预算导入-控制器实现类
 * @Modified :
 */
@Api(tags = "预算-操作接口")
@Slf4j
@RestController
@RequestMapping("/budget")
public class BudgetController {

    private static final String SPLITER = "$";

    private static final BigDecimal budgetAmountUp = new BigDecimal(10000000000000L);

    @Autowired
    private IBaseBudgetAccountService budgetAccountService;

    @Autowired
    private IBudgetControlService controlService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private OrganizationFeignService orgFeignService;

    @Autowired
    private IBudgetProjectService projectService;

    @Autowired
    private IBudgetProjectBudgetService projectBudgetService;

    @Autowired
    private IBudgetBasicBudgetService basicBudgetService;

    @ApiOperation(value = "导出预算导入模板", notes = "导出预算导入模板")
    @ApiParam(name = "queryForm", value = "导出 查询Form", required = true)
    @GetMapping(value = "/exportTemplet")
    public void exportTemplet(HttpServletResponse response){
        DeptVo deptVo = commonServices.getCurrentDept();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("单位").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("预算类型").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("项目").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("课题").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("任务").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("一级处室").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("二级处室").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("预算科目").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("预算期间").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("预算金额").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("预算年度").setOneCellWidth(3000));
//        headerList.add(new ExcelHeader("申请人").setOneCellWidth(3000));
        String fileName = "预算导入模板";
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.exportExcel();
    }

    @ApiOperation(value = "预算保留检查", notes = "预算保留检查" )
    @ApiParam(name = "checkForm", value = "预算检查Form", required = true)
    @PostMapping(value = "checkBudgetRemain")
    public Result checkBudgetRemain(@RequestBody BudgetCheckForm checkForm){
        controlService.checkBudgetRemain(checkForm.getDocumentId(),checkForm.getDocumentType());
        return Result.success();
    }

    @ApiOperation("预算导入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件对象", required = true, dataType = "MultipartFile")
    })
    @PostMapping(value = "importBudget")
    public Result importBudget(@RequestPart("file") MultipartFile file) {
        if (file != null) {
            MultipartFile excelFile = file;
            //得到文件的原始名称
            String originalFilename = excelFile.getOriginalFilename();
            //得到文件扩展名
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (!FsscEums.EXCEL_2003.getValue().equals(ext) && !FsscEums.EXCEL_2007.getValue()
                    .equals(ext)) {
                throw new FSSCException(FsscErrorType.FILE_FORMAT_NOT_EXCEL);
            }
            File cacheFile;
            try {
                cacheFile = File.createTempFile(UUIDUtil.getRandom32PK(), ext);
                excelFile.transferTo(cacheFile);
                ExcelResult excelResult = ExcelImportUtil.getExcelDate(cacheFile);
                if (!excelResult.readSuccess()) {
                    return Result.fail(excelResult.getErrorMsgBeanList());
                }
                List<Map<String, String>> rowMapList = excelResult.getTableData();
                Map<String, String> budgetTypeMap = commonServices.getValueByCode(
                        commonServices.findDicValueList(FsscEums.BUDGET_TYPE.getValue()));
                Result<HashMap<String,String>> deptResult = deptFeignService.searchCodeAndName(new DeptQueryForm());
                if (deptResult.isFail()){
                    return deptResult;
                }
                //TODO 目前是获取全量处室数据,等4A成熟后,需要做根据单位过滤
                Result<HashMap<String,String>> officeResult = orgFeignService.searchCodeAndName(new OrganizationQueryForm());
                if (officeResult.isFail()){
                    return officeResult;
                }
                DeptVo deptVo = commonServices.getCurrentDept();
                Map<String,BudgetProject> projectCodeBeanMap = projectService.getProjectCodeBeanMap(deptVo.getDeptCode());
                Map<String,BudgetProject> taskCodeBeanMap = projectService.getTaskCodeBeanMap(deptVo.getDeptCode());
                Map<String,Integer> basicKeyRowNumMap = new HashMap<>();
                Map<String,Integer> projectKeyRowNumMap = new HashMap<>();
                List<Budget> budgetList = new ArrayList<>(rowMapList.size());
                int rowNum = 0;
                for (Map<String, String> rowMap : rowMapList) {
                    rowNum++;
                    Budget budget = new Budget();
                    budget.setIndex(rowNum);
                    String unitCode = rowMap.get(Budget.UNIT_CODE_EXCEL);
                    if (StringUtils.isBlank(unitCode)) {
                        excelResult.addErrorMsg(rowNum, Budget.UNIT_CODE_EXCEL, "单位不能为空");
                    }else {
                        // 验证单位是否存在
                        String unitName = deptResult.getData().get(unitCode);
                        if (StringUtils.isBlank(unitName)) {
                            excelResult.addErrorMsg(rowNum, Budget.UNIT_CODE_EXCEL, "单位不存在");
                        }else {
                            budget.setUnitCode(unitCode);
                            budget.setUnitName(unitName);
                        }
                    }
                    String budgetType = rowMap.get(Budget.BUDGET_TYPE_EXCEL);
                    if (StringUtils.isBlank(budgetType)) {
                        excelResult.addErrorMsg(rowNum, Budget.BUDGET_TYPE_EXCEL, "预算类型不能为空");
                    }else {
                        // 验证预算类型是否存在
                        String budgetTypeName = budgetTypeMap.get(budgetType.toUpperCase());
                        if (StringUtils.isBlank(budgetTypeName)) {
                            excelResult.addErrorMsg(rowNum, Budget.BUDGET_TYPE_EXCEL, "预算类型不是数据字典值");
                        }else {
                            budget.setBudgetType(budgetType);
                            budget.setBudgetTypeName(budgetTypeName);
                        }
                    }
                    if (FsscEums.BUDGET_TYPE_PROJECT.getValue().equals(budgetType)){
                        // 验证项目是否存在
                        String projectCode = rowMap.get(Budget.PROJECT_CODE_EXCEL);
                        if (StringUtils.isBlank(projectCode)) {
                            excelResult.addErrorMsg(rowNum, Budget.PROJECT_CODE_EXCEL, "项目不能为空");
                            continue;
                        }
                        if (!projectCodeBeanMap.containsKey(projectCode)){
                            excelResult.addErrorMsg(rowNum, Budget.PROJECT_CODE_EXCEL, "项目不存在");
                            continue;
                        }
                        BudgetProject project = projectCodeBeanMap.get(projectCode);
                        if (!unitCode.equals(project.getOrgUnitCode())){
                            excelResult.addErrorMsg(rowNum, Budget.PROJECT_CODE_EXCEL, "项目承担单位与导入单位不一致");
                            continue;
                        }
                        String projectName = project.getProjectName();
                        budget.setProjectCode(projectCode);
                        budget.setProjectName(projectName);
                        String subjectCode = rowMap.get(Budget.SUBJECT_CODE_EXCEL);
                        budget.setSubjectCode(subjectCode);
                        budget.setSubjectName(subjectCode);
                        String taskCode = rowMap.get(Budget.TASK_CODE_EXCEL);
                        if (StringUtils.isBlank(taskCode)) {
                            excelResult.addErrorMsg(rowNum, Budget.TASK_CODE_EXCEL, "任务不能为空");
                        }else {
                            if (!taskCodeBeanMap.containsKey(taskCode)) {
                                excelResult.addErrorMsg(rowNum, Budget.TASK_CODE_EXCEL, "任务不存在");
                            }
                            BudgetProject task = taskCodeBeanMap.get(taskCode);
//                            if (task.getProjectId() == null && !task.getProjectId().equals(project.getProjectId())){
//                                excelResult.addErrorMsg(rowNum, Budget.PROJECT_CODE_EXCEL, "任务与项目不匹配,请检查");
//                            }
//                            else if (task.getProjectId() != null && !project.getProjectId().equals(task.getParentId())){
//                                excelResult.addErrorMsg(rowNum, Budget.PROJECT_CODE_EXCEL, "任务与项目不匹配,请检查");
//                            }
                            budget.setTaskCode(taskCode);
                            budget.setTaskName(task.getProjectName());
                        }
                    }else if (FsscEums.BUDGET_TYPE_BASIC.getValue().equals(budgetType)) {
                        String Level1OfficeCode = rowMap.get(Budget.LEVEL1_OFFICE_CODE_EXCEL);
                        if (StringUtils.isBlank(Level1OfficeCode)) {
                            excelResult
                                    .addErrorMsg(rowNum, Budget.LEVEL1_OFFICE_CODE_EXCEL,
                                            "一级处室不能为空");
                        }else if (!officeResult.getData().containsKey(Level1OfficeCode)) {
                                excelResult
                                        .addErrorMsg(rowNum, Budget.LEVEL1_OFFICE_CODE_EXCEL,
                                                "一级处室不存在");
                        }else {
                            budget.setLevel1OfficeCode(Level1OfficeCode);
                            String Level1OfficeName = officeResult.getData().get(Level1OfficeCode);
                            budget.setLevel1OfficeName(Level1OfficeName);
                        }
                        String Level2OfficeCode = rowMap.get(Budget.LEVEL2_OFFICE_CODE_EXCEL);
                        if (StringUtils.isBlank(Level2OfficeCode)) {
                            excelResult.addErrorMsg(rowNum, Budget.LEVEL2_OFFICE_CODE_EXCEL,
                                    "二级处室不存在");
                        }else if(!officeResult.getData().containsKey(Level2OfficeCode)){
                            excelResult
                                    .addErrorMsg(rowNum, Budget.LEVEL2_OFFICE_CODE_EXCEL,
                                            "二级处室不存在");
                        }else {
                            //TODO 验证一级和二级处室的关系
                            String Level2OfficeName = officeResult.getData().get(Level2OfficeCode);
                            budget.setLevel2OfficeCode(Level2OfficeCode);
                            budget.setLevel2OfficeName(Level2OfficeName);
                        }
                    }
                    String budgetAccountCode = rowMap.get(Budget.BUDGET_ACCOUNT_CODE_EXCEL);
                    if (StringUtils.isBlank(budgetAccountCode)) {
                        excelResult.addErrorMsg(rowNum, Budget.BUDGET_ACCOUNT_CODE_EXCEL, "预算科目不能为空");
                    }else {
                        //TODO 需要区分基本/项目预算科目
                        BaseBudgetAccount budgetAccount = budgetAccountService.getBudgetAccountByCode(budgetAccountCode,budgetType);
                        if (budgetAccount == null) {
                            excelResult.addErrorMsg(rowNum, Budget.BUDGET_ACCOUNT_CODE_EXCEL, "预算科目不存在");
                        } else if (!budgetAccount.getBudgetType().equals(budgetType)) {
                            excelResult.addErrorMsg(rowNum, Budget.BUDGET_ACCOUNT_CODE_EXCEL, "预算科目的预算类型与导入的预算类型不匹配");
                        }else{
                            budget.setBudgetAccountCode(budgetAccountCode);
                            budget.setBudgetAccountName(budgetAccount.getName());
                            budget.setBudgetAccountId(String.valueOf(budgetAccount.getId()));
                        }
                    }
                    String budgetPeriod = rowMap.get(Budget.BUDGET_PERIOD_EXCEL);
                    if (StringUtils.isBlank(budgetPeriod)){
                        excelResult.addErrorMsg(rowNum, Budget.BUDGET_PERIOD_EXCEL,
                                "预算期间不能为空,请按照yyyy-MM格式填写");
                    }else {
                        if (budgetPeriod.length() != 7) {
                            excelResult.addErrorMsg(rowNum, Budget.BUDGET_PERIOD_EXCEL,
                                    "预算期间格式错误,需要yyyy-MM格式");
                        }
                        Date budgetPeriodDate;
                        try {
                            budgetPeriodDate = DateUtil.stringToDateThrow(budgetPeriod, DateUtil.FM_YYYY_MM);
                            budgetPeriod = DateUtil.dateToString(budgetPeriodDate, DateUtil.FM_YYYY_MM);
                        } catch (Exception e) {
                            try {
                                budgetPeriodDate = DateUtil
                                        .stringToDateThrow(budgetPeriod, DateUtil.FM_YYYY_MM2);
                                budgetPeriod = DateUtil
                                        .dateToString(budgetPeriodDate, DateUtil.FM_YYYY_MM);
                            } catch (Exception e2) {
                                try {
                                    budgetPeriodDate = DateUtil
                                            .stringToDateThrow(budgetPeriod, DateUtil.FM_YYYYMM);
                                    budgetPeriod = DateUtil
                                            .dateToString(budgetPeriodDate, DateUtil.FM_YYYY_MM);
                                } catch (Exception e3) {
                                    log.error("转换预算期间错误: {}", e.getMessage());
                                    excelResult
                                            .addErrorMsg(rowNum, Budget.BUDGET_PERIOD_EXCEL, "预算区间格式错误,"
                                                    + "需要yyyy-MM或yyyy/MM格式");
                                }
                            }
                        }
                        budget.setBudgetPeriod(budgetPeriod);
                    }
                    String budgetAmountStr = rowMap.get(Budget.BUDGET_AMOUNT_EXCEL);
                    if (StringUtils.isBlank(budgetAmountStr)) {
                        excelResult.addErrorMsg(rowNum, Budget.BUDGET_AMOUNT_EXCEL, "预算金额不能为空");
                    }else {
                        try {
                            BigDecimal budgetAmount = new BigDecimal(budgetAmountStr.replaceAll(",", ""));
                            if (budgetAmount.compareTo(budgetAmountUp) >= 0){
                                excelResult
                                        .addErrorMsg(rowNum, Budget.BUDGET_AMOUNT_EXCEL, "预算金额不能超过千万亿");
                            }
                            budget.setBudgetAmount(budgetAmount);
                        } catch (Exception e) {
                            excelResult
                                    .addErrorMsg(rowNum, Budget.BUDGET_AMOUNT_EXCEL, "预算金额不能转换为数值类型");
                        }
                    }
                    String budgetAnnual = rowMap.get(Budget.BUDGET_ANNUAL_EXCEL);
                    if (StringUtils.isBlank(budgetAnnual)){
                        excelResult.addErrorMsg(rowNum, Budget.BUDGET_ANNUAL_EXCEL,
                                "预算年度不能为空,请按照yyyy格式填写");
                    }else {
                        if (budgetAnnual.length() != 4) {
                            excelResult.addErrorMsg(rowNum, Budget.BUDGET_ANNUAL_EXCEL,
                                    "预算年度格式错误,需要yyyy格式");
                        }
                        try {
                            DateUtil.stringToDateThrow(budgetAnnual, DateUtil.FM_YYYY);
                        } catch (Exception e) {
                            log.error("转换预算年度错误: {}", e.getMessage());
                            excelResult.addErrorMsg(rowNum, Budget.BUDGET_ANNUAL_EXCEL,
                                    "预算年度格式错误,需要yyyy格式");
                        }
                        budget.setBudgetAnnual(budgetAnnual);
                    }
                    /*
                    String applyForPerson = rowMap.get(Budget.APPLY_FOR_PERSON_EXCEL);
                    if (StringUtils.isBlank(applyForPerson)){
                        excelResult.addErrorMsg(rowNum, Budget.APPLY_FOR_PERSON_EXCEL,
                                "申请人不能为空,请输入相应的工号");
                    }else {
                        Result userResult = userFeignService.getByEmpNo(applyForPerson);
                        if (deptResult.isFail()) {
                            return userResult;
                        }
                        if (userResult.getData() == null) {
                            excelResult.addErrorMsg(rowNum, Budget.APPLY_FOR_PERSON_EXCEL,
                                    "校验申请人失败,请输入正确的工号");
                        }
                        budget.setApplyForPerson(applyForPerson);
                        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(userResult.getData()));
                        budget.setApplyForPersonName(jsonObject.getString("name"));
                    }
                    */
                    budgetList.add(budget);
                    if (FsscEums.BUDGET_TYPE_PROJECT.getValue().equals(budgetType)){
                        String projectKey = budget.getUnitCode() +  SPLITER + budget.getTaskCode()
                                + SPLITER + budget.getProjectCode()
                                + SPLITER + budget.getBudgetAccountCode() + SPLITER
                                + budget.getBudgetAnnual() + SPLITER + budget.getBudgetPeriod();
                        Integer existsRowNum = projectKeyRowNumMap.get(projectKey);
                        if (existsRowNum != null){
                            excelResult.addErrorMsg(rowNum, Budget.INDEX_EXCEL,
                                    "该行项目预算数据与"+ existsRowNum +"行重复");
                        }else {
                            projectKeyRowNumMap.put(projectKey,rowNum);
                        }
                    }else if(FsscEums.BUDGET_TYPE_BASIC.getValue().equals(budgetType)){
                        String basicKey = budget.getUnitCode() + SPLITER + budget.getLevel2OfficeCode()
                                + SPLITER + budget.getBudgetAccountCode() + SPLITER
                                + budget.getBudgetAnnual() + SPLITER + budget.getBudgetPeriod();
                        Integer existsRowNum = basicKeyRowNumMap.get(basicKey);
                        if (existsRowNum != null){
                            excelResult.addErrorMsg(rowNum, Budget.INDEX_EXCEL,
                                    "该行基本预算数据与"+ existsRowNum +"行重复");
                        }else {
                            basicKeyRowNumMap.put(basicKey,rowNum);
                        }
                    }
                }
                if (excelResult.readSuccess()) {
                    return Result.success(budgetList);
                } else {
                    return Result.fail(excelResult.getErrorMsgBeanList());
                }
            } catch (IOException ioe) {
                log.error("读取Excel文件出错: {}", ioe.getMessage());
                throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
            }
        }
        throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
    }


    @ApiOperation("保存预算")
    @ApiParam(name = "budgetList", value = "预算集合", required = true,allowMultiple = true)
    @PostMapping(value = "saveBudget")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result saveBudget(@RequestBody List<Budget> budgetList){
        log.info("saveBudget with :", JSON.toJSONString(budgetList));
        if (CollectionUtils.isEmpty(budgetList)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        for (Budget budget : budgetList){
            if (FsscEums.BUDGET_TYPE_PROJECT.getValue().equals(budget.getBudgetType())){
                BudgetProjectBudget projectBudget = projectBudgetService.selectByKeyWord(budget.getUnitCode(),budget.getProjectCode(),
                        budget.getTaskCode(),budget.getBudgetAccountCode(),budget.getBudgetAnnual());
                BudgetProjectBudget monthProjectBudget = projectBudgetService.selectMonthByKeyWord(
                        budget.getUnitCode(), budget.getProjectCode(), budget.getTaskCode(),budget.getBudgetAccountCode(),
                        budget.getBudgetAnnual(),budget.getBudgetPeriod());
                if (monthProjectBudget == null){
                    BudgetProjectBudget newMonthProjectBudget = this.constructProjectBudget(budget);
                    BudgetProject project = projectService.getBudgetProject(budget.getUnitCode(),budget.getProjectCode(),budget.getTaskCode());
                    newMonthProjectBudget.setBudgetProjectId(project.getId());
                    newMonthProjectBudget.setBudgetPeriod(budget.getBudgetPeriod());
                    newMonthProjectBudget.setTotalFlag(FsscEums.NO.getValue());
                    projectBudgetService.save(newMonthProjectBudget);
                    if (projectBudget == null){
                        BudgetProjectBudget newProjectBudget = this.constructProjectBudget(budget);
                        newProjectBudget.setBudgetProjectId(project.getId());
                        newProjectBudget.setBudgetPeriod(null);
                        newProjectBudget.setTotalFlag(FsscEums.YES.getValue());
                        projectBudgetService.save(newProjectBudget);
                    }else{
                        BigDecimal newBudgetAmount = projectBudget.getBudgetAmount().add(budget.getBudgetAmount());
                        if (newBudgetAmount.compareTo(budgetAmountUp) >= 0){
                           throw new FSSCException(FsscErrorType.BUDGET_AMOUNT_MORE_THAN_10000000000000);
                        }
                        BigDecimal newBudgetUsableAmount = projectBudget.getBudgetUsableAmount().add(budget.getBudgetAmount());
                        projectBudget.setBudgetAmount(newBudgetAmount);
                        projectBudget.setBudgetUsableAmount(newBudgetUsableAmount);
                        projectBudgetService.updateById(projectBudget);
                    }
                }else{
                    BigDecimal newMonthBudgetAmount = monthProjectBudget.getBudgetAmount().add(budget.getBudgetAmount());
                    BigDecimal newMonthBudgetUsableAmount = monthProjectBudget.getBudgetUsableAmount().add(budget.getBudgetAmount());
                    monthProjectBudget.setBudgetAmount(newMonthBudgetAmount);
                    monthProjectBudget.setBudgetUsableAmount(newMonthBudgetUsableAmount);
                    projectBudgetService.updateById(monthProjectBudget);
                    if (projectBudget == null){
                        throw new FSSCException(FsscErrorType.BUDGET_TOTAL_SUM_NOT_FOUND);
                    }
                    // 更新某月项目预算
                    BigDecimal newBudgetAmount = projectBudget.getBudgetAmount().add(budget.getBudgetAmount());
                    if (newBudgetAmount.compareTo(budgetAmountUp) >= 0){
                        throw new FSSCException(FsscErrorType.BUDGET_AMOUNT_MORE_THAN_10000000000000);
                    }
                    BigDecimal newBudgetUsableAmount = projectBudget.getBudgetUsableAmount().add(budget.getBudgetAmount());
                    projectBudget.setBudgetAmount(newBudgetAmount);
                    projectBudget.setBudgetUsableAmount(newBudgetUsableAmount);
                    projectBudgetService.updateById(projectBudget);
                }

            }else if (FsscEums.BUDGET_TYPE_BASIC.getValue().equals(budget.getBudgetType())){
                BudgetBasicBudget basicBudget = basicBudgetService.selectByKeyWord(budget.getUnitCode(),
                        budget.getLevel2OfficeCode(),budget.getBudgetAccountCode(),budget.getBudgetAnnual());
                BudgetBasicBudget monthBasicBudget = basicBudgetService.selectMonthByKeyWord(budget.getUnitCode(),
                        budget.getLevel2OfficeCode(),budget.getBudgetAccountCode(),budget.getBudgetAnnual(),
                        budget.getBudgetPeriod());
                if (monthBasicBudget == null){
                    BudgetBasicBudget newMonthBasicBudget = this.constructBasicBudget(budget);
                    newMonthBasicBudget.setBudgetPeriod(budget.getBudgetPeriod());
                    newMonthBasicBudget.setTotalFlag(FsscEums.NO.getValue());
                    basicBudgetService.save(newMonthBasicBudget);
                    if (basicBudget == null){
                        BudgetBasicBudget newBasicBudget = this.constructBasicBudget(budget);
                        newBasicBudget.setBudgetPeriod(null);
                        newBasicBudget.setTotalFlag(FsscEums.YES.getValue());
                        basicBudgetService.save(newBasicBudget);
                    }else {
                        // 更新年合计基本预算
                        BigDecimal newBudgetAmount = basicBudget.getBudgetAmount().add(budget.getBudgetAmount());
                        if (newBudgetAmount.compareTo(budgetAmountUp) >= 0){
                            throw new FSSCException(FsscErrorType.BUDGET_AMOUNT_MORE_THAN_10000000000000);
                        }
                        BigDecimal newBudgetUsableAmount = basicBudget.getBudgetUsableAmount().add(budget.getBudgetAmount());
                        basicBudget.setBudgetAmount(newBudgetAmount);
                        basicBudget.setBudgetUsableAmount(newBudgetUsableAmount);
                        basicBudgetService.updateById(basicBudget);
                    }
                }else{
                    // 更新某月基本预算
                    BigDecimal newMonthBudgetAmount = monthBasicBudget.getBudgetAmount().add(budget.getBudgetAmount());
                    BigDecimal newMonthBudgetUsableAmount = monthBasicBudget.getBudgetUsableAmount().add(budget.getBudgetAmount());
                    monthBasicBudget.setBudgetAmount(newMonthBudgetAmount);
                    monthBasicBudget.setBudgetUsableAmount(newMonthBudgetUsableAmount);
                    basicBudgetService.updateById(monthBasicBudget);
                    if (basicBudget == null){
                        throw new FSSCException(FsscErrorType.BUDGET_TOTAL_SUM_NOT_FOUND);
                    }
                    // 更新年合计基本预算
                    BigDecimal newBudgetAmount = basicBudget.getBudgetAmount().add(budget.getBudgetAmount());
                    if (newBudgetAmount.compareTo(budgetAmountUp) >= 0){
                        throw new FSSCException(FsscErrorType.BUDGET_AMOUNT_MORE_THAN_10000000000000);
                    }
                    BigDecimal newBudgetUsableAmount = basicBudget.getBudgetUsableAmount().add(budget.getBudgetAmount());
                    basicBudget.setBudgetAmount(newBudgetAmount);
                    basicBudget.setBudgetUsableAmount(newBudgetUsableAmount);
                    basicBudgetService.updateById(basicBudget);
                }
            }
        }
        return Result.success();
    }

    private BudgetProjectBudget constructProjectBudget(Budget budget) {
        BudgetProjectBudget projectBudget = new BudgetProjectBudget();
        projectBudget.setOrgUnitCode(budget.getUnitCode());
        projectBudget.setProjectCode(budget.getProjectCode());
        projectBudget.setTaskCode(budget.getTaskCode());
        projectBudget.setBudgetAnnual(budget.getBudgetAnnual());
        projectBudget.setBudgetAccountCode(budget.getBudgetAccountCode());
        projectBudget.setBudgetAccountId(Long.valueOf(budget.getBudgetAccountId()));
        projectBudget.setBudgetAmount(budget.getBudgetAmount());
        projectBudget.setBudgetUsableAmount(budget.getBudgetAmount());
        projectBudget.setBudgetRemainAmount(BigDecimal.ZERO);
        projectBudget.setBudgetOccupyAmount(BigDecimal.ZERO);
        return projectBudget;
    }

    private BudgetBasicBudget constructBasicBudget(Budget budget) {
        BudgetBasicBudget basicBudget = new BudgetBasicBudget();
        basicBudget.setOrgUnitCode(budget.getUnitCode());
        basicBudget.setBudgetAnnual(budget.getBudgetAnnual());
        basicBudget.setBudgetAccountCode(budget.getBudgetAccountCode());
        basicBudget.setBudgetAccountId(Long.valueOf(budget.getBudgetAccountId()));
        basicBudget.setBudgetAmount(budget.getBudgetAmount());
        basicBudget.setBudgetUsableAmount(budget.getBudgetAmount());
        basicBudget.setBudgetRemainAmount(BigDecimal.ZERO);
        basicBudget.setBudgetOccupyAmount(BigDecimal.ZERO);
        basicBudget.setApplyForPerson(budget.getApplyForPerson());
        basicBudget.setLevel1OfficeCode(budget.getLevel1OfficeCode());
        basicBudget.setLevel2OfficeCode(budget.getLevel2OfficeCode());
        return basicBudget;
    }

}
