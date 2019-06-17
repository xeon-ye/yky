package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BasePersonIncomeTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.*;
import com.deloitte.platform.api.isump.feign.DictFeignService;
import com.deloitte.platform.api.isump.param.DictQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BasePersonIncomeType;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.base.service.IBasePersonIncomeTypeService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author : jaws
 * @Date : Create in 2019-05-22
 * @Description :   人员类型 控制器实现类
 * @Modified :
 */
@Api(tags = "单据类型-人员类型 操作接口")
@Slf4j
@RestController
@RequestMapping("/base/personType")
public class BasePersonIncomeTypeController {

    @Autowired
    public IBasePersonIncomeTypeService personTypeService;

    @Autowired
    FsscCommonServices commonServices;

    @Autowired
    IBaseDocumentTypeService documentTypeService;

    @Autowired
    DictFeignService dictFeignService;

    @ApiOperation(value = "分页查询人员类型信息", httpMethod = "GET",
            notes = "根据条件查询人员类型信息分页数据")
    @ApiParam(name = "queryForm", value = "人员类型信息查询参数", required = true)
    @GetMapping(value = "/page4aPersonType")
    public Result<IPage<BasePersonIncomeTypeVo>> search(DictQueryForm queryForm) {
        queryForm.setParentCode(FsscEums.PERSON_INCOME_TYPE_KEY.getValue());
        log.info("search with DictQueryForm:{}");
        return Result.success(dictFeignService.searchForFeign(queryForm));
    }

    @ApiOperation(value = "分页查询单据类型-人员类型信息", httpMethod = "GET",
            notes = "根据条件查询单据类型-人员类型信息分页数据")
    @ApiParam(name = "queryForm", value = "单据类型-人员类型信息查询参数", required = true)
    @GetMapping(value = "/page/conditions")
    public Result<IPage<BasePersonIncomeTypeVo>> search(
            @Valid BasePersonIncomeTypeQueryForm queryForm) {
        log.info("search with BasePersonIncomeTypeQueryForm:{}",
                queryForm.toString());
        IPage<BasePersonIncomeType> incomeTypeIPage = personTypeService.selectPage(queryForm);
        IPage<BasePersonIncomeTypeVo> incomeTypeVoIPage = new BeanUtils<BasePersonIncomeTypeVo>()
                .copyPageObjs(incomeTypeIPage,BasePersonIncomeTypeVo.class);
        return Result.success(incomeTypeVoIPage);
    }

    @ApiOperation(value = "修改单据类型-人员类型启用状态", httpMethod = "POST", notes = "支持批量")
    @PostMapping(value = "/modifyValidStatus")
    public Result modifyValidStatus(
            @RequestBody @Valid BaseModifyStatusForm form) {
        if (CollectionUtils.isEmpty(form.getIds())) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        personTypeService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @ApiOperation(value = "删除单据类型-人员类型",notes = "支持批量")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "单据类型ID集合", required = true,
            dataType = "long", allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        personTypeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "根据功能模块获取人员类型", notes = "根据功能模块获取人员类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "functionModule", value = "功能模块", required = true, paramType = "query"),
            @ApiImplicitParam(name = "validFlag", value = "启用标志", paramType = "query")
    })
    @GetMapping(value = "/getByFunctionModule")
    public Result<List<Select2DataSource>> getByFunctionModule(
            @RequestParam("functionModule") String functionModule,
            @RequestParam("validFlag") String validFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(functionModule, deptVo.getDeptCode());
        if (documentType == null) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        BasePersonIncomeTypeQueryForm queryForm = new BasePersonIncomeTypeQueryForm();
        queryForm.setDocumentTypeId(documentType.getId());
        if (FsscEums.VALID.getValue().equals(validFlag) || FsscEums.UN_VALID.getValue()
                .equals(validFlag)) {
            queryForm.setValidFlag(validFlag);
        }
        List<BasePersonIncomeType> incomeList = personTypeService.selectList(queryForm);
        List<BasePersonIncomeTypeVo> incomeVoList = new BeanUtils<BasePersonIncomeTypeVo>().copyObjs(incomeList,BasePersonIncomeTypeVo.class);
        if (CollectionUtils.isEmpty(incomeVoList)) {
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(incomeVoList.size());
        for (BasePersonIncomeTypeVo vo : incomeVoList) {
            dataSourceList
                    .add(new Select2DataSource(vo.getId(), vo.getTypeCode(),
                            vo.getTypeName()));
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "新增单据类型-人员类型", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "addForm", value = "新增单据类型-人员类型Form", required = true)
    @PostMapping(value = "/addPersonType")
    public Result addDocTypeUserType(@RequestBody AddDocumentTypePersonTypeForm addForm)
            throws Exception {
        log.info("search with AddDocumentTypeIncomeForm:{}", JSON.toJSONString(addForm));
        List<BasePersonIncomeTypeForm> formList = addForm.getFormList();
        List<BasePersonIncomeType> newAllPoList = new ArrayList<>();
        String[] docTypeIds = addForm.getDocumentTypeIds().split(",");
        log.info("docTypeIds length: {}", docTypeIds.length);
        if (ArrayUtils.isEmpty(docTypeIds)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        List<Long> docTypeIdList = new ArrayList<>();
        for (String docTypeIdStr : docTypeIds) {
            docTypeIdList.add(Long.valueOf(docTypeIdStr));
        }
        for (Long docTypeId  : docTypeIdList) {
            BasePersonIncomeTypeQueryForm queryForm = new BasePersonIncomeTypeQueryForm();
            queryForm.setDocumentTypeId(docTypeId);
            List<BasePersonIncomeType> existsPersonTypeList = personTypeService.selectList(queryForm);
            List<BasePersonIncomeType> newMainTypePoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(formList)) {
                for (BasePersonIncomeTypeForm form : formList) {
                    BasePersonIncomeType personType = new BasePersonIncomeType();
                    BeanUtils.copyProperties(form, personType);
                    personType.setDocumentTypeId(docTypeId);
                    personType.setValidFlag(FsscEums.VALID.getValue());
                    newMainTypePoList.add(personType);
                }
            }
            List<String> oldPersonTypeCodeList = existsPersonTypeList.stream()
                    .map(BasePersonIncomeType::getTypeCode)
                    .collect(Collectors.toList());
            List<BasePersonIncomeType> filterPoList = newMainTypePoList.stream()
                    .filter(e -> !oldPersonTypeCodeList
                            .contains(e.getTypeCode())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(filterPoList)) {
                newAllPoList.addAll(filterPoList);
            }
        }
        if (CollectionUtils.isEmpty(newAllPoList)) {
            throw new FSSCException(FsscErrorType.CANNOT_ALLOCATION_NEW_PERSON_TYPE);
        }
        personTypeService.saveOrUpdateBatch(newAllPoList);
        return Result.success();
    }

}



