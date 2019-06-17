package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseBudgetAccountQueryForm;
import com.deloitte.platform.api.fssc.base.vo.AddBudgetAccountExpenseForm;
import com.deloitte.platform.api.fssc.base.vo.BaseBudgetAccountForm;
import com.deloitte.platform.api.fssc.base.vo.BaseBudgetAccountVo;
import com.deloitte.platform.api.fssc.base.vo.ModifyBudgetAccountForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.budget.service.*;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : jaws
 * @Date : Create in 2019-03-06
 * @Description :   预算科目定义-控制器实现类
 * @Modified :
 */
@Api(tags = "预算科目定义-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/budgetAccount")
public class BaseBudgetAccountController {

    @Autowired
    private IBaseBudgetAccountService budgetAccountService;

    @Autowired
    private IBaseExpenseMainTypeService expenseMainTypeService;

    @Autowired
    private IBudgetProjectBudgetService projectBudgetService;

    @Autowired
    private IBudgetBasicBudgetService basicBudgetService;

    @Autowired
    private IBudgetDetailingAdjustLineService detailingAdjustLineService;

    @Autowired
    private IBudgetPublicAdjustLineService publicAdjustLineService;

    @Autowired
    private FsscCommonServices commonServices;

    @ApiOperation(value = "新增/修改预算科目", notes = "新增/修改一个预算科目")
    @ApiParam(name = "budgetAccountForm", value = "新增/修改预算科目的form表单", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "addOrUpdateBudgetAccount")
    @Transactional
    public Result addOrUpdateBudgetAccount(
            @Valid @RequestBody BaseBudgetAccountForm budgetAccountForm) {
        log.info("form:", budgetAccountForm.toString());
        BaseBudgetAccount baseBudgetAccount;
        if (budgetAccountForm.getId() != null) {
            // 修改
            baseBudgetAccount = budgetAccountService.getById(budgetAccountForm.getId());
            if (baseBudgetAccount != null) {
                baseBudgetAccount.setName(budgetAccountForm.getName().trim());
                if (StringUtils.isNotBlank(budgetAccountForm.getDescription())) {
                    baseBudgetAccount.setDescription(budgetAccountForm.getDescription().trim());
                }else{
                    baseBudgetAccount.setDescription("");
                }
            }
            return Result.success(budgetAccountService.saveOrUpdate(baseBudgetAccount));
        } else {
            // 新增
            BaseBudgetAccount sameCodeBudgetAccount = budgetAccountService
                    .getBudgetAccountByCode(budgetAccountForm.getCode());
            if (sameCodeBudgetAccount != null) {
                throw new FSSCException(FsscErrorType.CODE_REPEAT);
            }
            baseBudgetAccount = new BeanUtils<BaseBudgetAccount>()
                    .copyObj(budgetAccountForm, BaseBudgetAccount.class);
            baseBudgetAccount.setName(baseBudgetAccount.getName().trim());
            if (StringUtils.isNotBlank(budgetAccountForm.getDescription())) {
                baseBudgetAccount.setDescription(budgetAccountForm.getDescription().trim());
            }
            return Result.success(budgetAccountService.saveOrUpdate(baseBudgetAccount));
        }
    }

    @ApiOperation(value = "删除预算科目", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "预算科目ID", required = true,
            dataType = "long",allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        // TODO 如果被占用,不能删除,除了判断支出大类,后续还有其他引用的地方
        if (expenseMainTypeService.countByBudgetAccountIds(ids) > 0) {
            throw new FSSCException(FsscErrorType.REFERENCES_BY_EXPENSE_MAIN_TYPE);
        }
        if (projectBudgetService.countByBudgetAccountIds(ids) > 0){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_PROJECT_BUDGET);
        }
        if (basicBudgetService.countByBudgetAccountIds(ids) > 0){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_BASIC_BUDGET);
        }
        if (publicAdjustLineService.countByBudgetAccountIds(ids) > 0){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_PUBLIC_ADJUST);
        }
        if (detailingAdjustLineService.countByBudgetAccountIds(ids) > 0){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_DETAILING_ADJUST);
        }
        budgetAccountService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取预算科目", notes = "获取指定ID的预算科目信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BaseBudgetAccount的ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<BaseBudgetAccountVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BaseBudgetAccount baseBudgetAccount = budgetAccountService.getById(id);
        if (baseBudgetAccount == null){
            return Result.success();
        }
        BaseBudgetAccountVo baseBudgetAccountVo = new BeanUtils<BaseBudgetAccountVo>()
                .copyObj(baseBudgetAccount, BaseBudgetAccountVo.class);
        return Result.success(baseBudgetAccountVo);
    }

    @ApiOperation(value = "列表查询预算科目", notes = "根据条件查询预算科目 列表数据")
    @ApiParam(name = "baseBudgetAccountQueryForm", value = "预算科目查询参数", required = true)
    @PostMapping(value = "/list/conditions")
    public Result<List<BaseBudgetAccountVo>> list(
            @Valid @RequestBody BaseBudgetAccountQueryForm budgetAccountQueryForm) {
        log.info("search with baseBudgetAccountQueryForm:", budgetAccountQueryForm.toString());
        List<BaseBudgetAccount> baseBudgetAccountList = budgetAccountService
                .selectList(budgetAccountQueryForm);
        List<BaseBudgetAccountVo> baseBudgetAccountVoList = new BeanUtils<BaseBudgetAccountVo>()
                .copyObjs(baseBudgetAccountList, BaseBudgetAccountVo.class);
        return Result.success(baseBudgetAccountVoList);
    }

    @ApiOperation(value = "列表查询预算科目，适用于下拉框")
    @ApiParam(name = "baseBudgetAccountQueryForm", value = "预算科目查询参数", required = true)
    @PostMapping(value = "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(
            @Valid @RequestBody BaseBudgetAccountQueryForm budgetAccountQueryForm) {
        log.info("search with baseBudgetAccountQueryForm:", budgetAccountQueryForm.toString());
        budgetAccountQueryForm.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
        List<BaseBudgetAccount> baseBudgetAccountList = budgetAccountService.selectList(budgetAccountQueryForm);
        if (CollectionUtils.isEmpty(baseBudgetAccountList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>();
        for (BaseBudgetAccount budgetAccount : baseBudgetAccountList){
            Select2DataSource dataSource = new Select2DataSource(budgetAccount.getId(),budgetAccount.getCode(),budgetAccount.getName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "分页查询预算科目", notes = "根据条件查询预算科目 分页数据")
    @ApiParam(name = "baseBudgetAccountQueryForm", value = "BaseBudgetAccount查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public Result<IPage<BaseBudgetAccountVo>> search(
            @Valid @RequestBody BaseBudgetAccountQueryForm baseBudgetAccountQueryForm) {
        log.info("search with baseBudgetAccountQueryForm:", baseBudgetAccountQueryForm.toString());
        Map<String, String> budgetTypeMap = commonServices.getValueByCode(
                commonServices.findDicValueList(FsscEums.BUDGET_TYPE.getValue()));
        IPage<BaseBudgetAccount> baseBudgetAccountPage = budgetAccountService
                .selectPage(baseBudgetAccountQueryForm);
        IPage<BaseBudgetAccountVo> baseBudgetAccountVoPage = new BeanUtils<BaseBudgetAccountVo>()
                .copyPageObjs(baseBudgetAccountPage, BaseBudgetAccountVo.class);
        if (CollectionUtils.isNotEmpty(baseBudgetAccountVoPage.getRecords())){
            for (BaseBudgetAccountVo budgetAccountVo : baseBudgetAccountVoPage.getRecords()){
                budgetAccountVo.setBudgetTypeName(budgetTypeMap.get(budgetAccountVo.getBudgetType()));
            }
        }
        return Result.success(baseBudgetAccountVoPage);
    }

    @ApiOperation(value = "修改生失效状态", notes = "支持批量")
    @PostMapping(value = "/modifyBudgetAccountStatus")
    public Result modifyBudgetAccountStatus(@RequestBody @Valid ModifyBudgetAccountForm form) {
        log.info("search with modifyBudgetAccountStatus:{}", JSON.toJSONString(form));
        List<Long> ids = form.getIds();
        if (expenseMainTypeService.countByBudgetAccountIds(ids) > 0) {
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
        }
        String status = form.getStatus();
        budgetAccountService.modifyValidFlag(ids, status);
        return Result.success();
    }

    @ApiOperation(value = "预算科目关联支出大类", notes = "预算科目关联支出大类")
    @PostMapping(value = "/relateExpenseMainType")
    public Result relateExpenseMainType(
            @RequestBody @Valid AddBudgetAccountExpenseForm form) {
        log.info("search with relateExpenseMainType:{}", JSON.toJSONString(form));
        if (StringUtils.isBlank(form.getExpenseMainTypeIds())) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        DeptVo deptVo = commonServices.getCurrentDept();
        Long budgetAccountId = Long.valueOf(form.getBudgetAccountId());
        String[] expenseMainTypeIds = form.getExpenseMainTypeIds().split(",");
        if (expenseMainTypeIds.length > 1){
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_ONLY_RELATE_ONE);
        }
        List budgetAccountIds = new ArrayList();
        budgetAccountIds.add(budgetAccountId);
        Integer relatedExpenseCount = expenseMainTypeService.countByBudgetAccountIds(budgetAccountIds, deptVo.getDeptCode());
        if (relatedExpenseCount > 0){
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_ONLY_RELATE_ONE);
        }
        List<Long> mainTypeIdList = new ArrayList<>(expenseMainTypeIds.length);
        for (String expenseMainTypeId : expenseMainTypeIds) {
            mainTypeIdList.add(Long.valueOf(expenseMainTypeId));
        }
        expenseMainTypeService.updateBudgetAccountId(mainTypeIdList, budgetAccountId, deptVo.getCode());
        return Result.success();
    }

    @ApiOperation(value = "解除预算科目关联的支出大类", notes = "根据url的id来指定解除关系")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "预算科目ID", required = true, dataType = "long")
    @DeleteMapping(value = "/unRelateByIds")
    public Result unRelateByIds(@RequestBody List<Long> ids) {
        log.info("search with unRelateByIds:{}", JSON.toJSONString(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        expenseMainTypeService.unRelateBudgetAccount(ids);
        return Result.success();
    }

}



