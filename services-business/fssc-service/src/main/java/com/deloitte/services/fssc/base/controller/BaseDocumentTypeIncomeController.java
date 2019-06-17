package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeIncomeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.AddDocumentTypeIncomeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeIncomeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeIncomeVo;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeIncome;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeExpenseService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeIncomeService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :   单据类型-收入大类 控制器实现类
 * @Modified :
 */
@Api(tags = "单据类型-收入大类 操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/base/documentTypeIncome")
public class BaseDocumentTypeIncomeController {

    @Autowired
    private IBaseDocumentTypeIncomeService docTypeIncomeService;

    @Autowired
    private IBaseDocumentTypeExpenseService docTypeExpenseService;

    @Autowired
    FsscCommonServices commonServices;

    @Autowired
    IBaseDocumentTypeService documentTypeService;

    @ApiOperation(value = "分页查询单据类型-收入大类信息", httpMethod = "POST",
            notes = "根据条件查询单据类型-收入大类信息分页数据")
    @ApiParam(name = "queryForm", value = "单据类型-收入大类信息查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public Result<IPage<BaseDocumentTypeIncomeVo>> search(
            @Valid @RequestBody BaseDocumentTypeIncomeQueryForm queryForm) {
        log.info("search with BaseDocumentTypeIncomeQueryForm:{}",
                queryForm.toString());
        return Result.success(docTypeIncomeService.selectVoPage(queryForm));
    }

    @ApiOperation(value = "修改单据类型-收入大类启用状态", httpMethod = "POST", notes = "支持批量")
    @PostMapping(value = "/modifyValidStatus")
    public Result modifyValidStatus(
            @RequestBody @Valid BaseModifyStatusForm form) {
        if (CollectionUtils.isEmpty(form.getIds())) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        docTypeIncomeService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @ApiOperation(value = "删除单据类型-支出大类",notes = "支持批量")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "单据类型ID集合", required = true,
            dataType = "long", allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        docTypeIncomeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "根据功能模块获取收入大类", notes = "根据功能模块获取收入大类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionModule", value = "功能模块", required = true, paramType = "path"),
            @ApiImplicitParam(name = "validFlag", value = "启用标志", paramType = "path")
    })
    @GetMapping(value = "/getByFunctionModule/{functionModule}/{validFlag}")
    public Result<List<Select2DataSource>> getByFunctionModule(
            @PathVariable("functionModule") String functionModule,
            @PathVariable("validFlag") String validFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(functionModule, deptVo.getDeptCode());
        if (documentType == null) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        BaseDocumentTypeIncomeQueryForm queryForm = new BaseDocumentTypeIncomeQueryForm();
        queryForm.setDocumentTypeId(documentType.getId());
        if (FsscEums.VALID.getValue().equals(validFlag) || FsscEums.UN_VALID.getValue()
                .equals(validFlag)) {
            queryForm.setValidFlag(validFlag);
        }
        List<BaseDocumentTypeIncomeVo> incomeVoList = docTypeIncomeService.listVo(queryForm);
        if (CollectionUtils.isEmpty(incomeVoList)) {
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(incomeVoList.size());
        for (BaseDocumentTypeIncomeVo vo : incomeVoList) {
            dataSourceList
                    .add(new Select2DataSource(vo.getIncomeMainTypeId(), vo.getIncomeMainTypeCode(),
                            vo.getIncomeMainTypeName()));
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "新增单据类型-收入大类", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "addForm", value = "新增单据类型-收入大类Form", required = true)
    @PostMapping(value = "/addDocTypeIncome")
    public Result addDocTypeIncome(@RequestBody AddDocumentTypeIncomeForm addForm)
            throws Exception {
        log.info("search with AddDocumentTypeIncomeForm:{}", JSON.toJSONString(addForm));
        List<BaseDocumentTypeIncomeForm> formList = addForm.getFormList();
        List<BaseDocumentTypeIncome> newAllPoList = new ArrayList<>();
        String[] docTypeIds = addForm.getDocumentTypeIds().split(",");
        log.info("docTypeIds length: {}", docTypeIds.length);
        if (ArrayUtils.isEmpty(docTypeIds)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        List<Long> docTypeIdList = new ArrayList<>();
        for (String docTypeIdStr : docTypeIds) {
            docTypeIdList.add(Long.valueOf(docTypeIdStr));
        }
        if (docTypeExpenseService.existsByDocumentTypeId(docTypeIdList)){
            throw new FSSCException(FsscErrorType.REFERENCES_EXPENSE_MAIN_TYPE);
        }
        for (Long docTypeId  : docTypeIdList) {
            BaseDocumentTypeIncomeQueryForm queryForm = new BaseDocumentTypeIncomeQueryForm();
            queryForm.setDocumentTypeId(docTypeId);
            List<BaseDocumentTypeIncome> existsMainTypeList = docTypeIncomeService
                    .selectList(queryForm);
            List<BaseDocumentTypeIncome> newMainTypePoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(formList)) {
                for (BaseDocumentTypeIncomeForm form : formList) {
                    BaseDocumentTypeIncome docTypeIncome = new BaseDocumentTypeIncome();
                    BeanUtils.copyProperties(form, docTypeIncome);
                    docTypeIncome.setDocumentTypeId(docTypeId);
                    docTypeIncome.setValidFlag(FsscEums.VALID.getValue());
                    newMainTypePoList.add(docTypeIncome);
                }
            }
            List<Long> oldMainTypeIdList = existsMainTypeList.stream()
                    .map(BaseDocumentTypeIncome::getIncomeMainTypeId)
                    .collect(Collectors.toList());
            List<BaseDocumentTypeIncome> filterPoList = newMainTypePoList.stream()
                    .filter(e -> !oldMainTypeIdList
                            .contains(e.getIncomeMainTypeId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(filterPoList)) {
                newAllPoList.addAll(filterPoList);
            }
        }
        if (CollectionUtils.isEmpty(newAllPoList)) {
            throw new FSSCException(FsscErrorType.CANNOT_ALLOCATION_NEW_INCOME);
        }
        docTypeIncomeService.saveOrUpdateBatch(newAllPoList);
        return Result.success();
    }

}



