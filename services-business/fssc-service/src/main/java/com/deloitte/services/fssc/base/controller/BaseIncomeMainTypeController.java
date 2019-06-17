package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeMainTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeMainTypeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeMainTypeVo;
import com.deloitte.platform.api.fssc.base.vo.ModifyMainTypeStatusForm;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeIncomeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeOrgUnitService;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.business.ppc.service.INoProjectConfirmationService;
import com.deloitte.services.fssc.business.ppc.service.IProjectConfirmationService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
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
import org.apache.commons.lang3.StringUtils;
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
 * @Description :   收入大类-控制器实现类
 * @Modified :
 */
@Api(tags = "收入大类-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/incomeMainType")
public class BaseIncomeMainTypeController {

    @Autowired
    private IBaseIncomeMainTypeService mainTypeService;

    @Autowired
    IBaseIncomeMainTypeOrgUnitService baseIncomeMainTypeOrgUnitService;

    @Autowired
    private IBaseIncomeSubTypeService subTypeService;

    @Autowired
    private INoProjectConfirmationService confirmationService;

    @Autowired
    private IBaseDocumentTypeIncomeService documentTypeIncomeService;

    @Autowired
    private IProjectConfirmationService projectConfirmationService;

    @Autowired
    private INoProjectConfirmationService noProjectConfirmationService;

    @Autowired
    private IRecievePaymentService receivePaymentService;

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private FsscCommonServices commonServices;

    @ApiOperation(value = "新增/修改收入大类", httpMethod = "POST",notes = "新增/修改一个收入大类")
    @ApiParam(name = "baseIncomeMainTypeForm", value = "新增/修改收入大类的form表单", required = true)
    @PostMapping(value = "/addOrUpdateMainType")
    public Result addOrUpdateMainType(@RequestBody BaseIncomeMainTypeForm baseIncomeMainTypeForm) {
        log.info("add or update BaseIncomeMainTypeForm:", baseIncomeMainTypeForm.toString());
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”；
        BaseIncomeMainType baseIncomeMainType;
        if (baseIncomeMainTypeForm.getId() != null){
            // 修改
            List<Long> ids = new ArrayList<>();
            ids.add(baseIncomeMainTypeForm.getId());
            if (mainTypeService.existChildMainType(ids,null)
                    || subTypeService.existsByIncomeMainTypeIds(ids)
                    || documentTypeIncomeService.existsByIncomeMainTypeIds(ids)
                    || projectConfirmationService.existsByReceivePayment(ids)
                    || noProjectConfirmationService.existsByReceivePayment(ids)
                    || receivePaymentService.existsByReceivePayment(ids)){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
            }
            baseIncomeMainType = mainTypeService.getById(baseIncomeMainTypeForm.getId());
            if (baseIncomeMainType != null){
                baseIncomeMainType.setName(baseIncomeMainTypeForm.getName().trim());
                baseIncomeMainType.setParentCode(baseIncomeMainTypeForm.getParentCode());
            }else{
                throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
            }
            return Result.success(mainTypeService.updateMainType(baseIncomeMainType));
        }else {
            // 新增
            DeptVo deptVo = commonServices.getCurrentDept();
            baseIncomeMainTypeForm.setCode(baseIncomeMainTypeForm.getCode().trim());
            List<BaseIncomeMainType> sameCodeMainTypeList = mainTypeService.getMainTypeByCode(baseIncomeMainTypeForm.getCode(),
                    deptVo.getDeptCode(),FsscEums.VALID.getValue());
            if (CollectionUtils.isNotEmpty(sameCodeMainTypeList)){
                throw new FSSCException(FsscErrorType.EXISTS_INCOME_SAME_CODE_FOR_ADD);
            }
            baseIncomeMainType = new BeanUtils<BaseIncomeMainType>()
                    .copyObj(baseIncomeMainTypeForm, BaseIncomeMainType.class);
            baseIncomeMainType.setName(baseIncomeMainType.getName().trim());
            if (FsscEums.YES.getValue().equals(baseIncomeMainType.getParentFlag())) {
                baseIncomeMainType.setParentCode("");
                baseIncomeMainType.setParentName("");
            } else if (FsscEums.NO.getValue().equals(baseIncomeMainType.getParentFlag())) {
                if (StringUtils.isBlank(baseIncomeMainType.getParentCode())) {
                    throw new FSSCException(FsscErrorType.PARENT_CODE_NOT_EMPTY);
                }
            } else {
                //如果没有选择是否父值,默认为N
                baseIncomeMainType.setParentFlag(FsscEums.NO.getValue());
            }
            baseIncomeMainType.setValidFlag(FsscEums.VALID.getValue());
            baseIncomeMainType.setValidDate(LocalDateTime.now());
            List<BaseIncomeMainType> parentMainTypeList = mainTypeService.getMainTypeByCode(baseIncomeMainType.getParentCode(),
                    deptVo.getDeptCode(),null);
            if (CollectionUtils.isNotEmpty(parentMainTypeList)) {
                baseIncomeMainType.setParentName(parentMainTypeList.get(0).getName());
            }
            baseIncomeMainType.setUnitCode(deptVo.getDeptCode());
            baseIncomeMainType.setOrgId(Long.valueOf(deptVo.getDeptId()));
            return Result.success(mainTypeService.saveOrUpdate(baseIncomeMainType));
        }
    }

    @ApiOperation(value = "删除收入大类", httpMethod = "DELETE",notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "body", name = "id", value = "BaseIncomeMainTypeID", required = true,
            dataType = "Long",allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("delete ids：{}", ids);
        //TODO 判断其他是否使用过这条记录 使用过的不能删除
        //TODO 出了判断存在子大类，小类关联，单据类型定义，后续可能需要判断是否被业务单据关联
        if (subTypeService.existsByIncomeMainTypeIds(ids)){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_SUB_TYPE);
        }
        if(mainTypeService.existChildMainType(ids,null)
                || subTypeService.existsByIncomeMainTypeIds(ids)
                || documentTypeIncomeService.existsByIncomeMainTypeIds(ids)
                || projectConfirmationService.existsByReceivePayment(ids)
                || noProjectConfirmationService.existsByReceivePayment(ids)
                || receivePaymentService.existsByReceivePayment(ids)){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        mainTypeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取收入大类", httpMethod = "GET" ,notes = "获取指定ID的收入大类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "收入大类的ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<BaseIncomeMainTypeVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        BaseIncomeMainType baseIncomeMainType = mainTypeService.getById(id);
        if (baseIncomeMainType == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        BaseIncomeMainTypeVo baseIncomeMainTypeVo = new BeanUtils<BaseIncomeMainTypeVo>()
                .copyObj(baseIncomeMainType, BaseIncomeMainTypeVo.class);
        return new Result<BaseIncomeMainTypeVo>().sucess(baseIncomeMainTypeVo);
    }

    @ApiOperation(value = "列表查询收入大类", httpMethod = "POST" ,notes = "根据条件查询收入大类列表数据")
    @ApiParam(name = "queryForm", value = "收入大类查询参数", required = true)
    @PostMapping(value = "/list/conditions")
    public Result<List<BaseIncomeMainTypeVo>> list(
            @Valid @RequestBody BaseIncomeMainTypeQueryForm queryForm) {
        log.info("search with baseIncomeMainTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseIncomeMainType> baseIncomeMainTypeList = mainTypeService.selectList(queryForm);
        List<BaseIncomeMainTypeVo> baseIncomeMainTypeVoList = new BeanUtils<BaseIncomeMainTypeVo>()
                .copyObjs(baseIncomeMainTypeList, BaseIncomeMainTypeVo.class);
        return new Result<List<BaseIncomeMainTypeVo>>().sucess(baseIncomeMainTypeVoList);
    }

    @ApiOperation(value = "分页查询收入大类", httpMethod = "POST" ,notes = "根据条件查询收入大类分页数据")
    @ApiParam(name = "queryForm", value = "收入大类查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public Result<IPage<BaseIncomeMainTypeVo>> search(@Valid @RequestBody BaseIncomeMainTypeQueryForm queryForm) {
        log.info("search with baseIncomeMainTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        IPage<BaseIncomeMainType> pageInfo = mainTypeService.selectPage(queryForm);
        IPage<BaseIncomeMainTypeVo> voPageInfo = new BeanUtils<BaseIncomeMainTypeVo>()
                .copyPageObjs(pageInfo, BaseIncomeMainTypeVo.class);
        if (pageInfo.getTotal() < 1){
            return Result.success(voPageInfo);
        }
        List<String> unitCodeList = voPageInfo.getRecords().stream().map(BaseIncomeMainTypeVo::getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        for (BaseIncomeMainTypeVo mainTypeVo : voPageInfo.getRecords()){
            mainTypeVo.setUnitName(codeNameMap.get(mainTypeVo.getUnitCode()));
        }
        return Result.success(voPageInfo);
    }

    @ApiOperation(value = "分页查询被单据或小类选择的收入大类", httpMethod = "POST" ,
            notes = "分页查询被单据或小类选择的收入大类")
    @ApiParam(name = "baseIncomeMainTypeQueryForm", value = "收入大类查询参数", required = true)
    @PostMapping(value = "/pageForCommon/conditions")
    public Result<IPage<BaseIncomeMainType>> searchForForm(
            @Valid @RequestBody BaseIncomeMainTypeQueryForm queryForm) {
        // TODO 在集成4A后,根据用户的单位查询有相应权限的大类
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        //设置查询是否父类标志位N的大类
        queryForm.setParentFlag(FsscEums.NO.getValue());
        log.info("search with baseIncomeMainTypeQueryForm:", queryForm.toString());
        IPage<BaseIncomeMainType> pageInfo = mainTypeService.selectPage(queryForm);
        IPage<BaseIncomeMainTypeVo> baseExpenseMainTypeVoPage = new BeanUtils<BaseIncomeMainTypeVo>()
                .copyPageObjs(pageInfo, BaseIncomeMainTypeVo.class);
        return Result.success(baseExpenseMainTypeVoPage);
    }

    @ApiOperation(value = "列表查询收入大类,用于下拉框", httpMethod = "POST", notes = "列表查询收入大类,"
            + "只查询非父类,用于下拉框")
    @ApiParam(name = "queryForm", value = "收入大类查询参数", required = true)
    @PostMapping(value = "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(@Valid @RequestBody BaseIncomeMainTypeQueryForm queryForm){
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        queryForm.setParentFlag(FsscEums.NO.getValue());
        List<BaseIncomeMainType> mainTypeList = mainTypeService.selectList(queryForm);
        if (CollectionUtils.isEmpty(mainTypeList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(mainTypeList.size());
        for (BaseIncomeMainType mainType : mainTypeList){
            dataSourceList.add(new Select2DataSource(mainType.getId(),mainType.getCode(),mainType.getName()));
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "修改生失效状态", httpMethod = "POST" ,notes = "支持批量")
    @ApiParam(name = "form", value = "修改生失效状态Form", required = true)
    @PostMapping(value = "/modifyMainTypeStatus")
    public Result modifyMainTypeStatus(@RequestBody @Valid ModifyMainTypeStatusForm form) {
        log.info("modifyStatus with ModifyMainTypeStatusForm:{}", JSON.toJSONString(form));
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        if (FsscEums.UN_VALID.getValue().equals(form.getStatus())){
            if (mainTypeService.existChildMainType(form.getIds(),FsscEums.VALID.getValue())){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
            }
            if(subTypeService.existsByIncomeMainTypeIds(form.getIds(),FsscEums.VALID.getValue())) {
                throw new FSSCException(FsscErrorType.INCOME_MAIN_TYPE_VALID);
            }
            if (documentTypeIncomeService.existsByIncomeMainTypeIds(form.getIds(),FsscEums.VALID.getValue())){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
            }
        }
        //TODO 如果收入大类关联其他业务后，也需要考虑是否能被失效问题
        mainTypeService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @ApiOperation(value = "查询所有一级大类", httpMethod = "GET" , notes = "查询所有一级大类")
    @GetMapping(value = "/getParentMainType")
    public Result<List<Select2DataSource>> getParentMainType() {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseIncomeMainTypeQueryForm queryForm = new BaseIncomeMainTypeQueryForm();
        queryForm.setParentFlag(FsscEums.YES.getValue());
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseIncomeMainType> mainTypeList = mainTypeService
                .selectList(queryForm);
        List<Select2DataSource> select2DataSources = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(mainTypeList)) {
            for (BaseIncomeMainType mainType : mainTypeList) {
                Select2DataSource option = new Select2DataSource(mainType.getCode(),
                        mainType.getName());
                select2DataSources.add(option);
            }
        }
        return Result.success(select2DataSources);
    }

    @ApiOperation(value = "导出", httpMethod = "GET" , notes = "导出")
    @ApiParam(name = "queryForm", value = "导出 查询Form", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, BaseIncomeMainTypeQueryForm queryForm){
        log.info("exportExcel with BaseIncomeMainTypeQueryForm:{}", JSON.toJSONString(queryForm));
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseIncomeMainType> mainTypeList = mainTypeService.selectPage(queryForm)
                .getRecords();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("款项大类编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("款项大类名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("是否父值").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("父值").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("使用单位").setOneCellWidth(6000));
        List<String> unitCodeList = mainTypeList.stream().map(BaseIncomeMainType::getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        String fileName = "收入大类信息";
        Object[][] content = new Object[mainTypeList.size()][];
        for (int i = 0; i < mainTypeList.size(); i++) {
            content[i] = new String[headerList.size()];
            BaseIncomeMainType vo = mainTypeList.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getCode();
            content[i][2] = vo.getName();
            content[i][3] = FsscEums.YES.getValue().equals(vo.getParentFlag()) ? "是" : "否";
            content[i][4] = StringUtil.objectToString(vo.getParentCode()) + " " + StringUtil
                    .objectToString(vo.getParentName());
            content[i][5] = StringUtil.isEmpty(vo.getValidFlag()) ? "N/A" :
                    (FsscEums.VALID.getValue().equals(vo.getValidFlag()) ? "是" : "否");
            content[i][6] = codeNameMap.get(vo.getUnitCode());
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

}



