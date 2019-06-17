package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseSubTypeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseSubTypeVo;
import com.deloitte.platform.api.fssc.base.vo.ModifySubTypeStatusForm;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubType;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseExpenseSubTypeService;
import com.deloitte.services.fssc.base.service.IBaseExpenseSubTypeUnitService;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentLineService;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyLineService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseLineService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostLineChinaService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostLineForeignService;
import com.deloitte.services.fssc.business.travle.service.ITasCostInformationLineService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelLineService;
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
 * @Date : Create in 2019-03-02
 * @Description :   支出小类-控制器实现类
 * @Modified :
 */
@Api(tags = "支出小类-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/expenseSubType")
public class BaseExpenseSubTypeController {

    @Autowired
    private IBaseExpenseSubTypeService subTypeService;

    @Autowired
    private IBaseExpenseMainTypeService mainTypeService;

    @Autowired
    IBaseExpenseSubTypeUnitService SubTypeOrgUnitService;

    @Autowired
    private IBorrowMoneyLineService borrowMoneyLineService;

    @Autowired
    public IAdvancePaymentLineService bmAdvancePaymentLineService;

    @Autowired
    public IContactDetailService bmContactDetailService;

    @Autowired
    public ILcLaborCostLineChinaService laborCostLineChinaService;

    @Autowired
    public ILcLaborCostLineForeignService laborCostLineForeignService;

    @Autowired
    private IGeGeneralExpenseLineService lineService;

    @Autowired
    public ITasCostInformationLineService tasCostInformationLineService;

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    public ITasTravelLineService tasTravelLineService;

    @ApiOperation(value = "新增/修改支出小类", notes = "新增/修改支出小类,更新操作只支持更新名称")
    @ApiParam(name = "subTypeForm", value = "新增/修改支出小类的form表单", required = true)
    @PostMapping(value = "/addOrUpdateSubType")
    public Result addOrUpdateSubType(@RequestBody BaseExpenseSubTypeForm subTypeForm) {
        log.info("add or update BaseExpenseSubTypeForm:", subTypeForm.toString());
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”；
        BaseExpenseSubType subType;
        if (subTypeForm.getId() != null) {
            List<Long> ids = new ArrayList<>();
            ids.add(Long.valueOf(subTypeForm.getId()));
            /*if (borrowMoneyLineService.existsByExpenseSubTypeIds(ids)){
                throw new FSSCException(FsscErrorType.BORROW_MONERY_INFO_UPDATE);
            }
            //关联对公预付款
            if (bmAdvancePaymentLineService.existsByExpenseSubApTypeIds(ids)){
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_UPDATE);
            }
            if (bmContactDetailService.existsByExpenseSubCdTypeIds(ids)){
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_UPDATE);
            }
            //关联劳务
            if (laborCostLineChinaService.existsByExpenseSubTypeLWCIds(ids)){
                throw new FSSCException(FsscErrorType.LABOR_COST_UPDATE);
            }
            if (laborCostLineForeignService.existsByExpenseSubTypeLWFIds(ids)){
                throw new FSSCException(FsscErrorType.LABOR_COST_UPDATE);
            }
            //关联通用
            if(lineService.existsByExpenseSubTypeGeIds(ids)){
                throw new FSSCException(FsscErrorType.GENERAL_EXPENSE_UPDATE);
            }
            //关联差旅申请
            if(tasCostInformationLineService.existsByExpenseSubTypeClIds(ids)){
                throw new FSSCException(FsscErrorType.TRAVLE_APPLY_UPDATE);
            }

            //关联差旅报账
            if(tasTravelLineService.existsByExpenseSubTypeTsIds(ids)){
                throw  new FSSCException(FsscErrorType.TRAVEL_REIMBURSE_UPDATE);
            }*/
            // 更新
            subType = subTypeService.getById(subTypeForm.getId());
            if (subType != null) {
                subType.setName(subTypeForm.getName().trim());
                subType.setCAccountCode(subTypeForm.getCAccountCode());
                subType.setYAccountCode(subTypeForm.getYAccountCode());
            }
            return Result.success(subTypeService.updateSubType(subType));
        } else {
            // 新增
            DeptVo deptVo = commonServices.getCurrentDept();
            subTypeForm.setCode(subTypeForm.getCode().trim());
            BaseExpenseSubType sameCodeSubType = subTypeService
                    .getSubTypeByCode(subTypeForm.getCode(),deptVo.getDeptCode());
            if (sameCodeSubType != null) {
                throw new FSSCException(FsscErrorType.CODE_REPEAT);
            }
            BaseExpenseMainType mainType = mainTypeService.getById(subTypeForm.getExpenseMainTypeId());
            if (mainType == null){
                throw new FSSCException(FsscErrorType.PARENT_NOT_FIND);
            }
            subType = new BeanUtils<BaseExpenseSubType>().copyObj(subTypeForm, BaseExpenseSubType.class);
            subType.setExpenseMainTypeId(mainType.getId());
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

    @ApiOperation(value = "删除支出小类", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "BaseExpenseSubTypeID", required = true,
            dataType = "long",allowMultiple = true)
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("delete ids：{}", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 判断其他是否使用过这条记录 使用过的不能删除
        //关联借款
        if (borrowMoneyLineService.existsByExpenseSubTypeIds(ids)){
            throw new FSSCException(FsscErrorType.BORROW_MONERY_INFO);
        }
        //关联对公预付款
        if (bmAdvancePaymentLineService.existsByExpenseSubApTypeIds(ids)){
            throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO);
        }
        if (bmContactDetailService.existsByExpenseSubCdTypeIds(ids)){
            throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO);
        }
        //关联劳务
        if (laborCostLineChinaService.existsByExpenseSubTypeLWCIds(ids)){
            throw new FSSCException(FsscErrorType.LABOR_COST_INFO);
        }
        if (laborCostLineForeignService.existsByExpenseSubTypeLWFIds(ids)){
            throw new FSSCException(FsscErrorType.LABOR_COST_INFO);
        }
        //关联差旅申请
        if(tasCostInformationLineService.existsByExpenseSubTypeClIds(ids)){
            throw new FSSCException(FsscErrorType.TRAVLE_APPLY_INFO);
        }
        //关联通用
        if(lineService.existsByExpenseSubTypeGeIds(ids)){
            throw new FSSCException(FsscErrorType.GENERAL_EXPENSE_INFO);
        }
        //关联差旅报账
        if(tasTravelLineService.existsByExpenseSubTypeTsIds(ids)){
            throw new FSSCException(FsscErrorType.TRAVEL_REIMBURSE);
        }

        subTypeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取支出小类", notes = "获取指定ID的支出小类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "支出小类的ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<BaseExpenseSubTypeVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        BaseExpenseSubTypeVo baseExpenseSubTypeVo = subTypeService.getSubTypeById(id);
        if (baseExpenseSubTypeVo == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        return new Result<BaseExpenseSubTypeVo>().sucess(baseExpenseSubTypeVo);
    }

    @ApiOperation(value = "列表查询支出小类", notes = "根据条件查询支出小类列表数据")
    @ApiParam(name = "queryForm", value = "支出小类查询参数", required = true)
    @PostMapping(value = "/list/conditions")
    public Result<List<BaseExpenseSubTypeVo>> list(
            @Valid @RequestBody BaseExpenseSubTypeQueryForm queryForm) {
        log.info("search with baseExpenseSubTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseExpenseSubType> baseExpenseSubTypeList = subTypeService.selectList(queryForm);
        List<BaseExpenseSubTypeVo> baseExpenseSubTypeVoList = new BeanUtils<BaseExpenseSubTypeVo>()
                .copyObjs(baseExpenseSubTypeList, BaseExpenseSubTypeVo.class);
        return new Result<List<BaseExpenseSubTypeVo>>().sucess(baseExpenseSubTypeVoList);
    }

    @ApiOperation(value = "分页查询支出小类", notes = "根据条件查询支出小类分页数据")
    @ApiParam(name = "queryForm", value = "支出小类查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public  Result<IPage<BaseExpenseSubTypeVo>> search(
            @Valid @RequestBody BaseExpenseSubTypeQueryForm queryForm) {
        log.info("search with baseExpenseSubTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        IPage<BaseExpenseSubTypeVo> pageInfo = subTypeService.selectVoPage(queryForm);
        List<String> unitCodeList = pageInfo.getRecords().stream().map(BaseExpenseSubTypeVo :: getUnitCode).collect(
                Collectors.toList());
        Result<HashMap<String,String>> deptVoResult = deptFeignService.searchNameByCodes(unitCodeList);
        HashMap<String,String> codeNameMap = deptVoResult.getData();
        for(BaseExpenseSubTypeVo subTypeVo : pageInfo.getRecords()){
            subTypeVo.setUnitName(codeNameMap.get(subTypeVo.getUnitCode()) + "");
        }
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "列表查询支出小类,用于下拉框", httpMethod = "POST", notes = "列表查询支出小类,用于下拉框")
    @ApiParam(name = "queryForm", value = "支出小类查询参数", required = true)
    @PostMapping(value = "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(@Valid @RequestBody BaseExpenseSubTypeQueryForm queryForm){
        List<BaseExpenseSubType> subTypeList = subTypeService.selectList(queryForm);
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        if (CollectionUtils.isEmpty(subTypeList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(subTypeList.size());
        for (BaseExpenseSubType subType : subTypeList){
            dataSourceList.add(new Select2DataSource(subType.getId(),subType.getCode(),subType.getName()));
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "修改生失效状态", notes = "支持批量")
    @PostMapping(value = "/modifySubTypeStatus")
    public Result modifySubTypeStatus(@RequestBody @Valid ModifySubTypeStatusForm form) {
        log.info("modifyStatus with ModifySubTypeStatusForm:{}", JSON.toJSONString(form));
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        if(FsscEums.VALID.getValue().equals(status)){
          for(Long id:ids){
              DeptVo deptVo = commonServices.getCurrentDept();
              String unitCode=deptVo.getDeptCode();
              BaseExpenseSubType sameCodeSubType=subTypeService.getById(id);
              String code=sameCodeSubType.getCode();
              QueryWrapper<BaseExpenseSubType>  codeSubTypeWrapper=new QueryWrapper<>();
              codeSubTypeWrapper.eq("CODE",code).eq("VALID_FLAG","Y").eq("UNIT_CODE",unitCode);
              List<BaseExpenseSubType> baseExpenseSubTypeList=subTypeService.list(codeSubTypeWrapper);
              if(CollectionUtils.isNotEmpty(baseExpenseSubTypeList)){
                     if (baseExpenseSubTypeList.size()>=1) {
                         throw new FSSCException(FsscErrorType.CODE_REPEAT);
                     }
               }
          }
        }
        /*if(FsscEums.UN_VALID.getValue().equals(status)){
            //关联借款
            if (borrowMoneyLineService.existsByExpenseSubTypeIds(ids)){
                throw new FSSCException(FsscErrorType.BORROW_MONERY_INFO_VALID);
            }
            //关联对公预付款
            if (bmAdvancePaymentLineService.existsByExpenseSubApTypeIds(ids)){
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_VALID);
            }
            if (bmContactDetailService.existsByExpenseSubCdTypeIds(ids)){
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_VALID);
            }
            //关联劳务
            if (laborCostLineChinaService.existsByExpenseSubTypeLWCIds(ids)){
                throw new FSSCException(FsscErrorType.LABOR_COST_VALID);
            }
            if (laborCostLineForeignService.existsByExpenseSubTypeLWFIds(ids)){
                throw new FSSCException(FsscErrorType.LABOR_COST_VALID);
            }
            //关联通用
            if(lineService.existsByExpenseSubTypeGeIds(ids)){
                throw new FSSCException(FsscErrorType.GENERAL_EXPENSE_VALID);
            }
            //关联差旅申请
            if(tasCostInformationLineService.existsByExpenseSubTypeClIds(ids)){
                throw new FSSCException(FsscErrorType.TRAVLE_APPLY_VALID);
            }
            //关联差旅报账
            if(tasTravelLineService.existsByExpenseSubTypeTsIds(ids)){
                throw  new FSSCException(FsscErrorType.TRAVEL_REIMBURSE_VALID);
            }
        }*/
        subTypeService.modifyValidFlag(ids, status);
        return Result.success();
    }

    @ApiOperation(value = "根据大类ID和费用类型查询支出小类", notes = "根据大类ID和费用类型查询支出小类")
    @ApiParam(name = "queryForm", value = "支出小类查询参数", required = true)
    @PostMapping(value = "/list/querySubDate")
    public Result<List<BaseExpenseSubTypeVo>> querySubDate( @Valid @RequestBody BaseExpenseSubTypeQueryForm queryForm) {
        log.info("get with queryForm:{}", queryForm);
        DeptVo deptVo = commonServices.getCurrentDept();
        QueryWrapper<BaseExpenseSubType> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("EXPENSE_MAIN_TYPE_ID",queryForm.getExpenseMainTypeId())
                .eq(StringUtil.isNotEmpty(queryForm.getCostType()),"COST_TYPE",queryForm.getCostType())
                .eq(StringUtil.isNotEmpty(queryForm.getName()),"NAME",queryForm.getName())
                .eq(StringUtil.isNotEmpty(queryForm.getCode()),"CODE",queryForm.getCode())
                .eq(StringUtil.isNotEmpty(queryForm.getValidFlag()),"VALID_FLAG",queryForm.getValidFlag())
                .eq("UNIT_CODE",deptVo.getDeptCode());
        List<BaseExpenseSubType> baseExpenseSubTypes = subTypeService.list(queryWrapper);
        List<BaseExpenseSubTypeVo> baseExpenseSubTypeVo= new BeanUtils<BaseExpenseSubTypeVo>()
                .copyObjs(baseExpenseSubTypes,BaseExpenseSubTypeVo.class);
        return new Result<List<BaseExpenseSubTypeVo>>().sucess(baseExpenseSubTypeVo);
    }

    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, BaseExpenseSubTypeQueryForm queryForm) {
        log.info("exportExcel with BaseExpenseSubTypeQueryForm:{}", JSON.toJSONString(queryForm));
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseExpenseSubTypeVo> subTypeVoList = subTypeService.selectVoPage(queryForm).getRecords();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("支出小类编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("支出小类名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("支出大类编码").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("支出大类名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("财务会计科目  ").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("预算会计科目").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("使用单位").setOneCellWidth(6000));
        List<String> unitCodeList = subTypeVoList.stream().map(BaseExpenseSubTypeVo::getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        String fileName = "支出小类信息";
        Object[][] content = new Object[subTypeVoList.size()][];
        for (int i = 0; i < subTypeVoList.size(); i++) {
            content[i] = new String[headerList.size()];
            BaseExpenseSubTypeVo subTypeVo = subTypeVoList.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = subTypeVo.getCode();
            content[i][2] = subTypeVo.getName();
            content[i][3] = subTypeVo.getExpenseMainTypeCode();
            content[i][4] = subTypeVo.getExpenseMainTypeName();
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

//    @ApiOperation(value = "分页查询支出小类-组织单位信息", notes = "根据条件查询支出小类-组织单位信息分页数据")
//    @ApiParam(name = "queryForm", value = "支出小类-组织单位信息查询参数", required = true)
//    @PostMapping(value = "/pageOrgUnit/conditions")
//    public Result<IPage<BaseExpenseSubTypeUnitVo>> search(
//            @Valid @RequestBody BaseExpenseSubTypeUnitQueryForm queryForm) {
//        log.info("search with baseExpenseSubTypeOrgUnitQueryForm:{}",
//                queryForm.toString());
//        IPage<BaseExpenseSubTypeUnit> pageInfo = SubTypeOrgUnitService
//                .selectPage(queryForm);
//        IPage<BaseExpenseSubTypeUnitVo> pageVoInfo = new BeanUtils<BaseExpenseSubTypeUnitVo>()
//                .copyPageObjs(pageInfo, BaseExpenseSubTypeUnitVo.class);
//        return Result.success(pageVoInfo);
//    }
//
//    @ApiOperation(value = "修改支出小类-组织单位生失效状态", notes = "支持批量")
//    @PostMapping(value = "/modifySubTypeOrgUnitStatus")
//    public Result modifySubTypeOrgUnitStatus(
//            @RequestBody @Valid ModifySubTypeOrgUnitStatusForm form) {
//        List<Long> ids = form.getIds();
//        String status = form.getStatus();
//        SubTypeOrgUnitService.modifyValidFlag(ids, status);
//        return Result.success();
//    }
//
//    @ApiOperation(value = "新增支出小类-组织单位", notes = "支持批量")
//    @PostMapping(value = "/addSubTypeOrgUnit")
//    public Result addSubTypeOrgUnit(@RequestBody AddExpenseSubTypeOrgUnitForm addForm)
//            throws Exception {
//        log.info("search with addSubTypeOrgUnit:{}", JSON.toJSONString(addForm));
//        List<BaseExpenseSubTypeUnitForm> formList = addForm.getFormList();
//        List<BaseExpenseSubTypeUnit> newAllPoList = new ArrayList<>();
//        String[] expenseSubTypeIds = addForm.getExpenseSubTypeIds().split(",");
//        log.info("incomeSubTypeIds length: {}",expenseSubTypeIds.length);
//        if (ArrayUtils.isEmpty(expenseSubTypeIds)){
//            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
//        }
//        for (String expenseSubTypeIdStr : expenseSubTypeIds){
//            Long expenseSubTypeId = Long.valueOf(expenseSubTypeIdStr);
//            BaseExpenseSubTypeUnitQueryForm queryForm = new BaseExpenseSubTypeUnitQueryForm();
//            queryForm.setExpenseSubTypeId(expenseSubTypeId);
//            List<BaseExpenseSubTypeUnit> oldFormList = SubTypeOrgUnitService.selectList(queryForm);
//            List<String> oldUnitCodeList = oldFormList.stream().map(BaseExpenseSubTypeUnit :: getOrgUnitCode)
//                    .collect(Collectors.toList());
//            List<BaseExpenseSubTypeUnit> poList = new ArrayList<>();
//            if (CollectionUtils.isNotEmpty(formList)) {
//                for (BaseExpenseSubTypeUnitForm form : formList) {
//                    BaseExpenseSubTypeUnit orgUnit = new BaseExpenseSubTypeUnit();
//                    BeanUtils.copyProperties(form, orgUnit);
//                    orgUnit.setExpenseSubTypeId(expenseSubTypeId);
//                    orgUnit.setValidFlag(FsscEums.VALID.getValue());
//                    poList.add(orgUnit);
//                }
//            }
//            List<BaseExpenseSubTypeUnit> newPoList = poList.stream()
//                    .filter(e -> !oldUnitCodeList.contains(e.getOrgUnitCode())).
//                            collect(Collectors.toList());
//            if (CollectionUtils.isNotEmpty(newPoList)){
//                newAllPoList.addAll(newPoList);
//            }
//        }
//        if (CollectionUtils.isEmpty(newAllPoList)) {
//            throw new ServiceException(FsscErrorType.CANNOT_ALLOCATION_NEW_UNIT);
//        }
//        SubTypeOrgUnitService.saveOrUpdateBatch(newAllPoList);
//        return Result.success();
//    }

}



