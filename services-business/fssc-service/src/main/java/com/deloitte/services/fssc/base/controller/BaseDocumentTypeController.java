package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeVo;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeExpenseService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeIncomeService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.base.service.IBasePersonIncomeTypeService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
 * @Date : Create in 2019-03-18
 * @Description :   单据类型 控制器实现类
 * @Modified :
 */
@Api(tags = "单据类型 操作接口")
@Slf4j
@RestController
@RequestMapping("/base/documentType")
public class BaseDocumentTypeController {

    @Autowired
    private IBaseDocumentTypeService docTypeService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private IBaseDocumentTypeIncomeService incomeService;

    @Autowired
    private IBaseDocumentTypeExpenseService expenseService;

    @Autowired
    private IBasePersonIncomeTypeService personIncomeTypeService;

    @ApiOperation(value = "新增/修改单据类型", httpMethod = "POST",notes = "新增/修改一个单据类型")
    @ApiParam(name = "docTypeForm", value = "新增/修改一个单据类型的form表单", required = true)
    @PostMapping(value = "/addOrUpdateDocType")
    @Transactional
    public Result addOrUpdateDocType(@RequestBody BaseDocumentTypeForm docTypeForm) {
        log.info("add or update addOrUpdateDocType:", docTypeForm.toString());
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”；
        BaseDocumentType docType;
        DeptVo deptVo = commonServices.getCurrentDept();
        if (docTypeForm.getId() != null) {
            // 修改
            docType = docTypeService.getById(docTypeForm.getId());
            if (docType != null) {
                if (!docType.getFunctionModule().equals(docTypeForm.getFunctionModule())) {
                    if (docTypeService.getDocTypeByFunction(docTypeForm.getFunctionModule(),
                            deptVo.getDeptCode()) != null) {
                        throw new FSSCException(FsscErrorType.FUNCTION_MODULE_RELATED);
                    }
                }
                docType.setName(docTypeForm.getName().trim());
                docType.setAuditFlag(docTypeForm.getAuditFlag());
                docType.setPhoneAuditFlag(docTypeForm.getPhoneAuditFlag());
                docType.setPostFlag(docTypeForm.getPostFlag());
                docType.setFunctionModule(docType.getFunctionModule());
            } else {
                throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
            }
            return Result.success(docTypeService.updateById(docType));
        } else {
            // 新增
            docTypeForm.setUnitCode(deptVo.getDeptCode());
            List<BaseDocumentType> sameCodeList = docTypeService.getDocTypeByCode(docTypeForm.getTypeCode(),
                    docTypeForm.getUnitCode(), FsscEums.VALID.getValue());
            if (CollectionUtils.isNotEmpty(sameCodeList)) {
                throw new FSSCException(FsscErrorType.CODE_REPEAT);
            }
            if (docTypeService.getDocTypeByFunction(docTypeForm.getFunctionModule(),docTypeForm.getUnitCode()) != null) {
                throw new FSSCException(FsscErrorType.FUNCTION_MODULE_RELATED);
            }
            docType = new BeanUtils<BaseDocumentType>().copyObj(docTypeForm, BaseDocumentType.class);
            docType.setOrgId(Long.valueOf(deptVo.getDeptId()));
            docType.setName(docType.getName().trim());
            docType.setValidFlag(FsscEums.VALID.getValue());
            docType.setUnitCode(deptVo.getDeptCode());
            this.setBudgetFlag(docType);
            return Result.success(docTypeService.saveOrUpdate(docType));
        }
    }


    @ApiOperation(value = "修改生失效状态",notes = "支持批量")
    @PostMapping(value = "/modifyValidStatus")
    public Result modifyDocTypeStatus(@RequestBody @Valid BaseModifyStatusForm form) {
        log.info("search with modifyDocTypeStatus:{}", JSON.toJSONString(form));
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 如果关联其他业务后，需要考虑是否能被失效问题
        docTypeService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @ApiOperation(value = "删除单据类型",notes = "支持批量")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "单据类型ID集合", required = true,
            dataType = "long", allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 判断其他是否使用过这条记录 使用过的不能删除
        if (incomeService.existsByDocumentTypeId(ids)){
            throw new FSSCException(FsscErrorType.RELATED_INCOME_FOR_DELETE);
        }
        if (expenseService.existsByDocumentTypeId(ids)){
            throw new FSSCException(FsscErrorType.RELATED_EXPENSE_FOR_DELETE);
        }
        if (personIncomeTypeService.existsByDocumentTypeId(ids)){
            throw new FSSCException(FsscErrorType.RELATED_PERSON_TYPE_FOR_DELETE);
        }
        docTypeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取单据类型", httpMethod = "GET", notes = "获取指定ID的单据类型信息")
    @ApiImplicitParam(paramType = "path", name = "functionModule", value = "功能模块", required = true,
            dataType = "String")
    @GetMapping(value = "/getByFunctionModule/{functionModule}")
    public Result<BaseDocumentTypeVo> getByFunctionModule(@PathVariable String functionModule) {
        log.info("get with functionModule:{}", functionModule);
        DeptVo currentDept = commonServices.getCurrentDept();
        BaseDocumentType baseDocumentType = docTypeService.getDocTypeByFunction(functionModule,currentDept.getDeptCode());
        if (baseDocumentType == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        BaseDocumentTypeVo baseDocumentTypeVo = new BeanUtils<BaseDocumentTypeVo>()
                .copyObj(baseDocumentType, BaseDocumentTypeVo.class);
        baseDocumentTypeVo.setUnitName(currentDept.getDeptName());
        return new Result<BaseDocumentTypeVo>().sucess(baseDocumentTypeVo);
    }

    @ApiOperation(value = "获取单据类型",httpMethod = "GET", notes = "获取指定ID的单据类型信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "单据类型的ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<BaseDocumentTypeVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        BaseDocumentType baseDocumentType = docTypeService.getById(id);
        if (baseDocumentType == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        BaseDocumentTypeVo baseDocumentTypeVo = new BeanUtils<BaseDocumentTypeVo>()
                .copyObj(baseDocumentType, BaseDocumentTypeVo.class);
        Result<DeptVo> deptVoResult = deptFeignService.getByCode(baseDocumentType.getUnitCode());
        if (deptVoResult.isFail() || deptVoResult.getData() == null){
            throw new FSSCException(FsscErrorType.GET_DEPT_NOT_EXIST);
        }
        baseDocumentTypeVo.setUnitName(deptVoResult.getData().getDeptName());
        return new Result<BaseDocumentTypeVo>().sucess(baseDocumentTypeVo);
    }

    @ApiOperation(value = "列表查询单据类型", httpMethod = "POST", notes = "根据条件查询单据类型列表数据")
    @ApiParam(name = "baseDocumentTypeQueryForm", value = "单据类型查询参数", required = true)
    @PostMapping(value = "/list/conditions")
    public Result<List<BaseDocumentTypeVo>> list(
            @Valid @RequestBody BaseDocumentTypeQueryForm baseDocumentTypeQueryForm) {
        log.info("search with baseDocumentTypeQueryForm:", baseDocumentTypeQueryForm.toString());
        List<BaseDocumentType> baseDocumentTypeList = docTypeService.selectList(baseDocumentTypeQueryForm);
        List<BaseDocumentTypeVo> baseDocumentTypeVoList = new BeanUtils<BaseDocumentTypeVo>()
                .copyObjs(baseDocumentTypeList, BaseDocumentTypeVo.class);
        List<String> unitCodeList = baseDocumentTypeList.stream().map(BaseDocumentType :: getUnitCode).collect(
                Collectors.toList());
        Result<HashMap<String,String>> deptVoResult = deptFeignService.searchNameByCodes(unitCodeList);
        HashMap<String,String> codeNameMap = deptVoResult.getData();
        for(BaseDocumentTypeVo documentTypeVo : baseDocumentTypeVoList){
            documentTypeVo.setUnitName(codeNameMap.get(documentTypeVo.getUnitCode()) + "");
        }
        return new Result<List<BaseDocumentTypeVo>>().sucess(baseDocumentTypeVoList);
    }

    @ApiOperation(value = "列表查询单据类型,用于下拉框", httpMethod = "POST", notes = "列表查询单据类型,用于下拉框")
    @ApiParam(name = "queryForm", value = "收入大类查询参数", required = true)
    @PostMapping(value = "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(@Valid @RequestBody BaseDocumentTypeQueryForm queryForm){
        queryForm.setValidFlag(FsscEums.VALID.getValue());
        List<BaseDocumentType> mainTypeList = docTypeService.selectList(queryForm);
        if (CollectionUtils.isEmpty(mainTypeList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(mainTypeList.size());
        for (BaseDocumentType documentType : mainTypeList){
            dataSourceList.add(new Select2DataSource(documentType.getId(),documentType.getTypeCode(),
                    documentType.getName()));
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "分页查询单据类型",httpMethod = "POST", notes = "根据条件查询单据类型分页数据")
    @ApiParam(name = "baseDocumentTypeQueryForm", value = "单据类型查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public Result<IPage<BaseDocumentTypeVo>> search(@Valid
    @RequestBody BaseDocumentTypeQueryForm baseDocumentTypeQueryForm) {
        log.info("search with baseDocumentTypeQueryForm:", baseDocumentTypeQueryForm.toString());
        IPage<BaseDocumentType> docTypePage = docTypeService.selectPage(baseDocumentTypeQueryForm);
        IPage<BaseDocumentTypeVo> docTypePageVoPage = new BeanUtils<BaseDocumentTypeVo>()
                .copyPageObjs(docTypePage, BaseDocumentTypeVo.class);
        List<String> unitCodeList = docTypePage.getRecords().stream().map(BaseDocumentType :: getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String,String>> deptVoResult = deptFeignService.searchNameByCodes(unitCodeList);
        HashMap<String,String> codeNameMap = deptVoResult.getData();
        for(BaseDocumentTypeVo documentTypeVo : docTypePageVoPage.getRecords()){
            documentTypeVo.setUnitName(codeNameMap.get(documentTypeVo.getUnitCode()) + "");
        }
        return Result.success(docTypePageVoPage);
    }

    @ApiOperation(value = "导出",httpMethod = "GET", notes = "导出")
    @ApiParam(name = "queryForm", value = "单据类型查询参数", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response,BaseDocumentTypeQueryForm queryForm) {
        log.info("exportExcel with BaseDocumentTypeQueryForm:{}", JSON.toJSONString(queryForm));
        Map<String, String> functionModuleMap = commonServices.getValueByCode(
                commonServices.findDicValueList(FsscEums.DOCUMENT_FUNCTION_MODULE.getValue()));
        List<BaseDocumentType> records = docTypeService.selectPage(queryForm).getRecords();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("单据编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("单据名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("功能模块").setOneCellWidth(6000));
        /**
        headerList.add(new ExcelHeader("是否预算控制").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("是否预算保留").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("是否占用预算").setOneCellWidth(4000));
        */
        headerList.add(new ExcelHeader("是否需要移动审批").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("是否需要审批").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("是否入账").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("所属单位").setOneCellWidth(8000));
        List<String> unitCodeList = records.stream().map(BaseDocumentType :: getUnitCode).collect(
                Collectors.toList());
        Result<HashMap<String,String>> deptVoResult = deptFeignService.searchNameByCodes(unitCodeList);
        HashMap<String,String> codeNameMap = deptVoResult.getData();
        String fileName = "单据类型定义";
        Object[][] content = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[headerList.size()];
            BaseDocumentType vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getTypeCode();
            content[i][2] = vo.getName();
            //TODO 功能模块 需要数据字段翻译
            content[i][3] = functionModuleMap.get(vo.getFunctionModule()) != null
                    ? functionModuleMap.get(vo.getFunctionModule()) : "";
            //content[i][4] = FsscEums.YES.getValue().equals(vo.getBudgetControlFlag()) ? "是" : "否";
            //content[i][5] = FsscEums.YES.getValue().equals(vo.getBudgetRemainFlag()) ? "是" : "否";
            //content[i][6] = FsscEums.YES.getValue().equals(vo.getBudgetOccupyFlag()) ? "是" : "否";
            content[i][4] = FsscEums.YES.getValue().equals(vo.getPhoneAuditFlag()) ? "是" : "否";
            content[i][5] = FsscEums.YES.getValue().equals(vo.getAuditFlag()) ? "是" : "否";
            content[i][6] = FsscEums.YES.getValue().equals(vo.getPostFlag()) ? "是" : "否";
            content[i][7] = FsscEums.VALID.getValue().equals(vo.getValidFlag()) ? "是" : "否";
            content[i][8] = codeNameMap.get(vo.getUnitCode()) != null ? codeNameMap.get(vo.getUnitCode()) : "";
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList, response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    /**
     *
     * 检查预算设置
     * @deprecated
     * @param docTypeForm
     */
    private void checkBudgetSetting(BaseDocumentTypeForm docTypeForm) {
        //预算占用 依赖 预算保留 依赖 预算控制
        String budgetControlFlag = docTypeForm.getBudgetControlFlag();
        String budgetRemainFlag = docTypeForm.getBudgetRemainFlag();
        String budgetOccupy = docTypeForm.getBudgetOccupyFlag();
        if (FsscEums.YES.getValue().equals(budgetOccupy) && !FsscEums.YES.getValue().equals(budgetControlFlag)){
            throw new FSSCException(FsscErrorType.BUDGET_CONTROL_FLAG_INVALID);
        }
        if (FsscEums.YES.getValue().equals(budgetOccupy) && !FsscEums.YES.getValue().equals(budgetRemainFlag)){
            throw new FSSCException(FsscErrorType.BUDGET_REMAIN_FLAG_INVALID);
        }
        if (FsscEums.YES.getValue().equals(budgetRemainFlag) && !FsscEums.YES.getValue().equals(budgetControlFlag)){
            throw new FSSCException(FsscErrorType.BUDGET_CONTROL_FLAG_INVALID);
        }
        if (FsscEums.YES.getValue().equals(budgetControlFlag) && !FsscEums.YES.getValue().equals(budgetRemainFlag)){
            throw new FSSCException(FsscErrorType.BUDGET_REMAIN_MUST_VALID);
        }
    }

    private void setBudgetFlag(BaseDocumentType docType) {
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.NO.getValue());
        }else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.NO.getValue());
        }else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.NO.getValue());
        }else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.YES.getValue());
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.YES.getValue());
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.YES.getValue());
        }else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(docType.getFunctionModule())){
            docType.setBudgetControlFlag(FsscEums.YES.getValue());
            docType.setBudgetRemainFlag(FsscEums.YES.getValue());
            docType.setBudgetOccupyFlag(FsscEums.YES.getValue());
        }
    }
}



