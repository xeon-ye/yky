package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeSubTypeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeSubTypeVo;
import com.deloitte.platform.api.fssc.base.vo.ModifySubTypeStatusForm;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeOrgUnitService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.business.ito.service.IDetailsOfFundsService;
import com.deloitte.services.fssc.business.ppc.service.IProjectPaymentLineDetaiService;
import com.deloitte.services.fssc.business.ppc.service.IProjectRecieveDetailService;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :   收入小类-控制器实现类
 * @Modified :
 */
@Api(tags = "收入小类-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/incomeSubType")
public class BaseIncomeSubTypeController {

    @Autowired
    private IBaseIncomeSubTypeService subTypeService;

    @Autowired
    private IBaseIncomeMainTypeService mainTypeService;

    @Autowired
    IBaseIncomeSubTypeOrgUnitService SubTypeOrgUnitService;

    @Autowired
    IProjectPaymentLineDetaiService paymentLineDetaiService;

    @Autowired
    IRecieveLineMsgService recieveLineMsgService;

    @Autowired
    private IProjectRecieveDetailService recieveDetailService;

    @Autowired
    private IDetailsOfFundsService detailsOfFundsService;

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private FsscCommonServices commonServices;

    @ApiOperation(value = "新增/修改收入小类", httpMethod = "POST", notes = "新增/修改收入小类,更新操作只支持更新名称")
    @ApiParam(name = "subTypeForm", value = "新增/修改收入小类的form表单", required = true)
    @PostMapping(value = "/addOrUpdateSubType")
    public Result addOrUpdateSubType(@Valid @RequestBody BaseIncomeSubTypeForm subTypeForm) {
        log.info("add or update BaseIncomeSubTypeForm:", subTypeForm.toString());
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”；
        BaseIncomeSubType subType;
        if (subTypeForm.getId() != null) {
            // 更新
            subType = subTypeService.getById(subTypeForm.getId());
            if (subType != null) {
                subType.setName(subTypeForm.getName().trim());
                subType.setCAccountCode(subTypeForm.getCAccountCode());
                subType.setYAccountCode(subTypeForm.getYAccountCode());
            } else {
                throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
            }
            return Result.success(subTypeService.updateSubType(subType));
        } else {
            // 新增
            DeptVo deptVo = commonServices.getCurrentDept();
            subTypeForm.setCode(subTypeForm.getCode().trim());
            List<BaseIncomeSubType> sameCodeSubTypeList = subTypeService.getSubTypeByCode(subTypeForm.getCode(),
                    deptVo.getDeptCode(),FsscEums.VALID.getValue());
            if (CollectionUtils.isNotEmpty(sameCodeSubTypeList)) {
                throw new FSSCException(FsscErrorType.EXISTS_INCOME_SUB_SAME_CODE_FOR_ADD);
            }
            BaseIncomeMainType mainType = mainTypeService.getById(subTypeForm.getIncomeMainTypeId());
            if (mainType == null) {
                throw new FSSCException(FsscErrorType.PARENT_NOT_FIND);
            }
            if (!FsscEums.VALID.getValue().equals(mainType.getValidFlag())) {
                throw new FSSCException(FsscErrorType.INCOME_MAIN_TYPE_INVALID);
            }
            subType = new BeanUtils<BaseIncomeSubType>().copyObj(subTypeForm, BaseIncomeSubType.class);
            subType.setIncomeMainTypeId(mainType.getId());
            subType.setName(subType.getName().trim());
            subType.setUnitCode(deptVo.getDeptCode());
            subType.setOrgId(Long.valueOf(deptVo.getDeptId()));
            if (FsscEums.VALID.getValue().equals(subType.getValidFlag())) {
                subType.setValidDate(LocalDateTime.now());
            } else if (FsscEums.UN_VALID.getValue().equals(subType.getValidFlag())) {
                subType.setInvalidDate(LocalDateTime.now());
            }
            return Result.success(subTypeService.saveOrUpdate(subType));
        }
    }

    @ApiOperation(value = "删除收入小类", httpMethod = "DELETE", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "BaseIncomeSubTypeID", required = true,
            dataType = "long", allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("delete ids：{}", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 判断其他是否使用过这条记录 使用过的不能删除
        if(paymentLineDetaiService.existsByReceivePayment(ids)
                || recieveLineMsgService.existsByReceivePayment(ids)
                || recieveDetailService.existsByReceivePayment(ids)
                || detailsOfFundsService.existsByFunds(ids)){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        subTypeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取收入小类", httpMethod = "GET", notes = "获取指定ID的收入小类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "收入小类的ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<BaseIncomeSubTypeVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        BaseIncomeSubTypeVo baseIncomeSubTypeVo = subTypeService.getSubTypeById(id);
        if (baseIncomeSubTypeVo == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        return new Result<BaseIncomeSubTypeVo>().sucess(baseIncomeSubTypeVo);
    }

    @ApiOperation(value = "列表查询收入小类", httpMethod = "POST", notes = "根据条件查询收入小类列表数据")
    @ApiParam(name = "queryForm", value = "收入小类查询参数", required = true)
    @PostMapping(value = "/list/conditions")
    public Result<List<BaseIncomeSubTypeVo>> list(
            @Valid @RequestBody BaseIncomeSubTypeQueryForm queryForm) {
        log.info("search with baseIncomeSubTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseIncomeSubType> baseIncomeSubTypeList = subTypeService.selectList(queryForm);
        List<BaseIncomeSubTypeVo> baseIncomeSubTypeVoList = new BeanUtils<BaseIncomeSubTypeVo>()
                .copyObjs(baseIncomeSubTypeList, BaseIncomeSubTypeVo.class);
        return new Result<List<BaseIncomeSubTypeVo>>().sucess(baseIncomeSubTypeVoList);
    }

    @ApiOperation(value = "分页查询收入小类", httpMethod = "POST", notes = "根据条件查询收入小类分页数据")
    @ApiParam(name = "queryForm", value = "收入小类查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public Result<IPage<BaseIncomeSubTypeVo>> search(
            @Valid @RequestBody BaseIncomeSubTypeQueryForm queryForm) {
        log.info("search with baseIncomeSubTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        IPage<BaseIncomeSubTypeVo> pageInfo = subTypeService.selectVoPage(queryForm);
        List<String> unitCodeList = pageInfo.getRecords().stream()
                .map(BaseIncomeSubTypeVo::getUnitCode).collect(
                        Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        for (BaseIncomeSubTypeVo subTypeVo : pageInfo.getRecords()) {
            subTypeVo.setUnitName(codeNameMap.get(subTypeVo.getUnitCode()) + "");
        }
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "列表查询收入小类,用于下拉框", httpMethod = "POST", notes = "列表查询收入小类,用于下拉框")
    @ApiParam(name = "queryForm", value = "收入小类查询参数", required = true)
    @PostMapping(value = "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(
            @Valid @RequestBody BaseIncomeSubTypeQueryForm queryForm) {
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseIncomeSubType> subTypeList = subTypeService.selectList(queryForm);
        if (CollectionUtils.isEmpty(subTypeList)) {
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(subTypeList.size());
        for (BaseIncomeSubType subType : subTypeList) {
            dataSourceList.add(new Select2DataSource(subType.getId(), subType.getCode(),
                    subType.getName()));
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "修改生失效状态", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "form", value = "修改生失效状态Form", required = true)
    @PostMapping(value = "/modifySubTypeStatus")
    public Result modifySubTypeStatus(@RequestBody @Valid ModifySubTypeStatusForm form) {
        log.info("modifyStatus with ModifySubTypeStatusForm:{}", JSON.toJSONString(form));
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        subTypeService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @ApiOperation(value = "导出", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "queryForm", value = "导出Form", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, BaseIncomeSubTypeQueryForm queryForm) {
        log.info("exportExcel with BaseIncomeSubTypeQueryForm:{}", JSON.toJSONString(queryForm));
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseIncomeSubTypeVo> subTypeVoList = subTypeService.selectVoPage(queryForm)
                .getRecords();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("款项小类编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("款项小类名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("款项大类编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("款项大类名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("财务会计科目").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("预算会计科目").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("使用单位").setOneCellWidth(6000));
        List<String> unitCodeList = subTypeVoList.stream().map(BaseIncomeSubTypeVo::getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        String fileName = "收入小类信息";
        Object[][] content = new Object[subTypeVoList.size()][];
        for (int i = 0; i < subTypeVoList.size(); i++) {
            content[i] = new String[headerList.size()];
            BaseIncomeSubTypeVo subTypeVo = subTypeVoList.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = subTypeVo.getCode();
            content[i][2] = subTypeVo.getName();
            content[i][3] = subTypeVo.getIncomeMainTypeCode();
            content[i][4] = subTypeVo.getIncomeMainTypeName();
            content[i][5] = StringUtil.isEmpty(subTypeVo.getValidFlag()) ? ""
                    : (FsscEums.VALID.getValue().equals(subTypeVo.getValidFlag()) ? "是" : "否");
            content[i][6] = subTypeVo.getCAccountCode() + " " + subTypeVo.getCAccountName();
            content[i][7] = subTypeVo.getYAccountCode() + " " + subTypeVo.getYAccountName();
            content[i][8] = codeNameMap.get(subTypeVo.getUnitCode()) + "";
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil
                .getSimpleInstance2(headerList, response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

}



