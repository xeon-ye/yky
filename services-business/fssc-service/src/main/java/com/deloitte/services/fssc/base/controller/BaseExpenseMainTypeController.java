package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseMainTypeQueryForm;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseMainTypeUnitQueryForm;
import com.deloitte.platform.api.fssc.base.vo.AddExpenseMainTypeOrgUnitForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseMainTypeForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseMainTypeUnitForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseMainTypeUnitVo;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseMainTypeVo;
import com.deloitte.platform.api.fssc.base.vo.ModifyMainTypeOrgUnitStatusForm;
import com.deloitte.platform.api.fssc.base.vo.ModifyMainTypeStatusForm;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainTypeUnit;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeExpenseService;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeOrgUnitService;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseExpenseSubTypeService;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  支出大类-控制器实现类
 * @Modified :
 */
@Api(tags = "支出大类-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/expenseMainType")
public class BaseExpenseMainTypeController {

    @Autowired
    private IBaseExpenseMainTypeService mainTypeService;

    @Autowired
    IBaseExpenseMainTypeOrgUnitService mainTypeOrgUnitService;

    @Autowired
    private IBaseExpenseSubTypeService subTypeService;

    @Autowired
    public IBorrowMoneyInfoService borrowMoneyInfoService;

    @Autowired
    public IAdvancePaymentInfoService bmAdvancePaymentInfoService;

    @Autowired
    public ICrbContractReimburseBillService crbContractReimburseBillService;

    @Autowired
    public IGeGeneralExpenseService geGeneralExpenseService;

    @Autowired
    public ILcLaborCostService lcLaborCostService;

    @Autowired
    public ITasTravelApplyInfoService tasTravleApplyInfoService;

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    public ITasTravelReimburseService tasTravelReimburseService;

    @Autowired
    private IBaseDocumentTypeExpenseService documentTypeExpenseService;

    @ApiOperation(value = "新增支出大类", notes = "新增一个支出大类")
    @ApiParam(name = "baseExpenseMainTypeForm", value = "新增支出大类的form表单", required = true)
    @PostMapping(value = "/addOrUpdateMainType")
    public Result addOrUpdateMainType(@RequestBody BaseExpenseMainTypeForm baseExpenseMainTypeForm) {
        log.info("add or update BaseExpenseMainTypeForm:", baseExpenseMainTypeForm.toString());
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”；
        BaseExpenseMainType mainType;
        if (baseExpenseMainTypeForm.getId() != null){
            // 修改
            List<Long> ids = new ArrayList<>();
            ids.add(baseExpenseMainTypeForm.getId());
            if (subTypeService.existsByExpenseMainTypeIds(ids)){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
            }
            if (mainTypeService.isRelateBudgetAccount(ids)){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
            }
            if (borrowMoneyInfoService.existsByExpenseMainTypeIds(ids)){
                throw new FSSCException(FsscErrorType.BORROW_MONERY_INFO_UPDATE);
            }
            if(bmAdvancePaymentInfoService.existsByExpenseMainTypeAdvIds(ids)){
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_UPDATE);
            }
            if(crbContractReimburseBillService.existsByExpenseMainTypeAdvIds(ids)){
                throw new FSSCException(FsscErrorType.CONTRACT_REIMBURSE_BILL_UPDATE);
            }
            //判断支出大类关联通用
            if(geGeneralExpenseService.existsByExpenseMainTypeGeIds(ids)){
                throw new FSSCException(FsscErrorType.GENERAL_EXPENSE_UPDATE);
            }
            //判断支出大类关联劳务费
            if(lcLaborCostService.existsByExpenseMainTypeLcIds(ids)){
                throw new FSSCException(FsscErrorType.LABOR_COST_UPDATE);
            }
            //判断支出大类关联差旅申请
            if(tasTravleApplyInfoService.existsByExpenseMainTypeTaIds(ids)){
                throw new FSSCException(FsscErrorType.TRAVLE_APPLY_UPDATE);
            }
            //判断支出大类关联差旅报账
            if(tasTravelReimburseService.existsByExpenseMainTypeTrIds(ids)){
                throw new FSSCException(FsscErrorType.TRAVEL_REIMBURSE_UPDATE);
            }
            if (documentTypeExpenseService.existsByExpenseMainTypeIds(ids)){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
            }
            mainType = mainTypeService.getById(baseExpenseMainTypeForm.getId());
            if (mainType != null){
                mainType.setName(baseExpenseMainTypeForm.getName());
                mainType.setParentCode(baseExpenseMainTypeForm.getParentCode());
            }
            return Result.success(mainTypeService.updateMainType(mainType));
        }else {
            // 新增
            DeptVo deptVo = commonServices.getCurrentDept();
            baseExpenseMainTypeForm.setCode(baseExpenseMainTypeForm.getCode().trim());
            BaseExpenseMainType sameCodeMainType = mainTypeService.getMainTypeByCode(
                    baseExpenseMainTypeForm.getCode(),deptVo.getDeptCode());
            if(sameCodeMainType != null){
                throw new FSSCException(FsscErrorType.CODE_REPEAT);
            }
            mainType = new BeanUtils<BaseExpenseMainType>()
                    .copyObj(baseExpenseMainTypeForm, BaseExpenseMainType.class);
            mainType.setName(mainType.getName().trim());
            // 如果新增时，选择是父值,将不设置父值,选择不是父值时才能选择父值
            if (FsscEums.YES.getValue().equals(mainType.getParentFlag())) {
                mainType.setParentCode("");
                mainType.setParentName("");
            } else if (FsscEums.NO.getValue().equals(mainType.getParentFlag())) {
                if (StringUtils.isBlank(mainType.getParentCode())) {
                    throw new FSSCException(FsscErrorType.PARENT_CODE_NOT_EMPTY);
                }
            } else {
                //如果没有选择是否父值,默认为N
                mainType.setParentFlag(FsscEums.NO.getValue());
            }
            mainType.setInvalidFlag(FsscEums.VALID.getValue());
            mainType.setValidDate(LocalDateTime.now());
            BaseExpenseMainType parentMainType = mainTypeService
                    .getMainTypeByCode(mainType.getParentCode(),deptVo.getDeptCode());
            if (parentMainType != null) {
                mainType.setParentName(parentMainType.getName());
            }
            mainType.setUnitCode(deptVo.getDeptCode());
            mainType.setOrgId(Long.valueOf(deptVo.getDeptId()));
            return Result.success(mainTypeService.saveOrUpdate(mainType));
        }
    }


    @ApiOperation(value = "删除支出大类", notes = "根据url的id来指定删除对象")
    @DeleteMapping(value = "/deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("delete ids：{}", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 判断其他是否使用过这条记录 使用过的不能删除,
        //TODO 除了判断支出小类、预算科目,后续还有其他
        isDeleteBillOrsubType(ids);
        List<BaseExpenseMainType> mainType = mainTypeService.selectListData(ids);
        BaseExpenseMainTypeQueryForm baseExpenseMainTypeForm=new BaseExpenseMainTypeQueryForm();
        if(mainType!=null) {
            for (BaseExpenseMainType type : mainType) {
                String code =type.getCode();
                if(StringUtil.isNotEmpty(code)){
                    List<BaseExpenseMainType> mainType1= mainTypeService.selectList(baseExpenseMainTypeForm);
                    for (BaseExpenseMainType type1 : mainType1) {
                        String parentCode =type1.getParentCode();
                        if(StringUtil.isNotEmpty(parentCode)){
                            if(code.equals(parentCode)){
                                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_PARENT);
                            }
                        }
                    }
               }
            }
        }
        mainTypeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取支出大类", notes = "获取指定ID的支出大类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "支出大类的ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<BaseExpenseMainTypeVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        BaseExpenseMainType baseExpenseMainType = mainTypeService.getById(id);
        if (baseExpenseMainType == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        BaseExpenseMainTypeVo baseExpenseMainTypeVo = new BeanUtils<BaseExpenseMainTypeVo>()
                .copyObj(baseExpenseMainType, BaseExpenseMainTypeVo.class);
        return new Result<BaseExpenseMainTypeVo>().sucess(baseExpenseMainTypeVo);
    }

    @ApiOperation(value = "列表查询支出大类", notes = "根据条件查询支出大类列表数据")
    @ApiParam(name = "baseExpenseMainTypeQueryForm", value = "支出大类查询参数", required = true)
    @PostMapping(value = "/list/conditions")
    public Result<List<BaseExpenseMainTypeVo>> list(
            @Valid @RequestBody BaseExpenseMainTypeQueryForm queryForm) {
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        log.info("search with BaseExpenseMainTypeQueryForm:", queryForm.toString());
        List<BaseExpenseMainType> baseExpenseMainTypeList = mainTypeService.selectList(queryForm);
        List<BaseExpenseMainTypeVo> baseExpenseMainTypeVoList = new BeanUtils<BaseExpenseMainTypeVo>()
                .copyObjs(baseExpenseMainTypeList, BaseExpenseMainTypeVo.class);
        return new Result<List<BaseExpenseMainTypeVo>>().sucess(baseExpenseMainTypeVoList);
    }

    @ApiOperation(value = "分页查询支出大类", notes = "根据条件查询支出大类分页数据")
    @ApiParam(name = "baseExpenseMainTypeQueryForm", value = "支出大类查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public  Result<IPage<BaseExpenseMainType>> search(
            @Valid @RequestBody BaseExpenseMainTypeQueryForm queryForm) {
        log.info("search with baseExpenseMainTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        IPage<BaseExpenseMainType> pageInfo = mainTypeService.selectPage(queryForm);
        IPage<BaseExpenseMainTypeVo> voPageInfo = new BeanUtils<BaseExpenseMainTypeVo>()
                .copyPageObjs(pageInfo, BaseExpenseMainTypeVo.class);
        List<String> unitCodeList = voPageInfo.getRecords().stream().map(BaseExpenseMainTypeVo :: getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        queryForm.setUnitCode(deptVo.getDeptCode());
        if (pageInfo.getTotal() < 1){
            return Result.success(voPageInfo);
        }
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        for (BaseExpenseMainTypeVo mainTypeVo : voPageInfo.getRecords()){
            mainTypeVo.setUnitName(codeNameMap.get(mainTypeVo.getUnitCode()));
        }
        return Result.success(voPageInfo);
    }

    @ApiOperation(value = "分页查询被单据或小类选择的支出大类", notes = "分页查询被单据或小类选择的支出大类")
    @ApiParam(name = "baseExpenseMainTypeQueryForm", value = "支出大类查询参数", required = true)
    @PostMapping(value = "/pageForCommon/conditions")
    public  Result<IPage<BaseExpenseMainType>> searchForForm(
            @Valid @RequestBody BaseExpenseMainTypeQueryForm queryForm) {
        //设置查询是否父类标志位N的条件\
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        queryForm.setParentFlag(FsscEums.NO.getValue());
        // TODO 在集成4A后,根据用户的单位查询有相应权限的大类
        log.info("search with baseIncomeMainTypeQueryForm:", queryForm.toString());
        IPage<BaseExpenseMainType> pageInfo = mainTypeService.selectPage(queryForm);
        IPage<BaseExpenseMainTypeVo> baseExpenseMainTypeVoPage = new BeanUtils<BaseExpenseMainTypeVo>()
                .copyPageObjs(pageInfo, BaseExpenseMainTypeVo.class);
        return Result.success(baseExpenseMainTypeVoPage);
    }

    @ApiOperation(value = "分页查询可被预算科目关联的支出大类", notes = "分页查询可被预算科目关联的支出大类")
    @ApiParam(name = "baseExpenseMainTypeQueryForm", value = "支出大类查询参数", required = true)
    @PostMapping(value = "/pageForBudgetAccount/conditions")
    public  Result<IPage<BaseExpenseMainType>> searchForBudgetAccount(
            @Valid @RequestBody BaseExpenseMainTypeQueryForm queryForm) {
        log.info("search with baseExpenseMainTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        queryForm.setParentFlag(FsscEums.NO.getValue());
        queryForm.setSelectByBudgetAccount(true);
        IPage<BaseExpenseMainType> pageInfo = mainTypeService.selectPage(queryForm);
        IPage<BaseExpenseMainTypeVo> baseExpenseMainTypeVoPage = new BeanUtils<BaseExpenseMainTypeVo>()
                .copyPageObjs(pageInfo, BaseExpenseMainTypeVo.class);
        return Result.success(baseExpenseMainTypeVoPage);
    }

    @ApiOperation(value = "分页查询预算科目关联的支出大类", notes = "分页查询预算科目关联的支出大类")
    @ApiParam(name = "baseExpenseMainTypeQueryForm", value = "支出大类查询参数", required = true)
    @PostMapping(value = "/pageByBudgetAccount/conditions")
    public  Result<IPage<BaseExpenseMainType>> searchByBudgetAccount(
            @Valid @RequestBody BaseExpenseMainTypeQueryForm queryForm) {
        log.info("search with baseExpenseMainTypeQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        queryForm.setParentFlag(FsscEums.NO.getValue());
        queryForm.setBudgetAccountId(queryForm.getBudgetAccountId());
        IPage<BaseExpenseMainType> pageInfo = mainTypeService.selectPage(queryForm);
        IPage<BaseExpenseMainTypeVo> baseExpenseMainTypeVoPage = new BeanUtils<BaseExpenseMainTypeVo>()
                .copyPageObjs(pageInfo, BaseExpenseMainTypeVo.class);
        return Result.success(baseExpenseMainTypeVoPage);
    }

    @ApiOperation(value = "修改生失效状态", notes = "支持批量")
    @PostMapping(value = "/modifyMainTypeStatus")
    public Result modifyMainTypeStatus(@RequestBody @Valid ModifyMainTypeStatusForm form) {
        log.info("search with ModifyMainTypeStatusForm:{}", JSON.toJSONString(form));
        // TODO 如果原有状态与新状态相同,需要判断不让更新
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        if(FsscEums.VALID.getValue().equals(status)){
            for(Long id:ids){
                DeptVo deptVo = commonServices.getCurrentDept();
                String unitCode=deptVo.getDeptCode();
                BaseExpenseMainType sameCodeSubType=mainTypeService.getById(id);
                String code=sameCodeSubType.getCode();
                QueryWrapper<BaseExpenseMainType> codeMainTypeWrapper=new QueryWrapper<>();
                codeMainTypeWrapper.eq("CODE",code).eq("INVALID_FLAG","Y").eq("UNIT_CODE",unitCode);
                List<BaseExpenseMainType> baseExpenseSubTypeList=mainTypeService.list(codeMainTypeWrapper);
                if(CollectionUtils.isNotEmpty(baseExpenseSubTypeList)){
                    if (baseExpenseSubTypeList.size()>=1) {
                        throw new FSSCException(FsscErrorType.CODE_REPEAT);
                    }
                }
            }
        }
        if(FsscEums.UN_VALID.getValue().equals(status)){
            if (subTypeService.existsByExpenseMainTypeIds(ids)){
                throw new FSSCException(FsscErrorType.EXPENSE_MAIN_TYPE_VALID);
            }
            if (mainTypeService.isRelateBudgetAccount(ids)){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
            }
            if (borrowMoneyInfoService.existsByExpenseMainTypeIds(ids)){
                throw new FSSCException(FsscErrorType.BORROW_MONERY_INFO_VALID);
            }
            if(bmAdvancePaymentInfoService.existsByExpenseMainTypeAdvIds(ids)){
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_VALID);
            }
            if(crbContractReimburseBillService.existsByExpenseMainTypeAdvIds(ids)){
                throw new FSSCException(FsscErrorType.CONTRACT_REIMBURSE_BILL_VALID);
            }
            //判断支出大类关联通用
            if(geGeneralExpenseService.existsByExpenseMainTypeGeIds(ids)){
                throw new FSSCException(FsscErrorType.GENERAL_EXPENSE_VALID);
            }
            //判断支出大类关联劳务费
            if(lcLaborCostService.existsByExpenseMainTypeLcIds(ids)){
                throw new FSSCException(FsscErrorType.LABOR_COST_VALID);
            }
            //判断支出大类关联差旅申请
            if(tasTravleApplyInfoService.existsByExpenseMainTypeTaIds(ids)){
                throw new FSSCException(FsscErrorType.TRAVLE_APPLY_VALID);
            }
            //判断支出大类关联差旅报账
            if(tasTravelReimburseService.existsByExpenseMainTypeTrIds(ids)){
                throw new FSSCException(FsscErrorType.TRAVEL_REIMBURSE_VALID);
            }
            if (documentTypeExpenseService.existsByExpenseMainTypeIds(ids,FsscEums.VALID.getValue())){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
            }

            List<BaseExpenseMainType> mainType = mainTypeService.selectListData(ids);
            BaseExpenseMainTypeQueryForm baseExpenseMainTypeForm=new BaseExpenseMainTypeQueryForm();
            if(mainType!=null) {
                for (BaseExpenseMainType type : mainType) {
                    String code =type.getCode();
                    if(StringUtil.isNotEmpty(code)){
                        List<BaseExpenseMainType> mainType1= mainTypeService.selectList(baseExpenseMainTypeForm);
                        for (BaseExpenseMainType type1 : mainType1) {
                            String parentCode =type1.getParentCode();
                            if(StringUtil.isNotEmpty(parentCode)){
                                if(code.equals(parentCode)){
                                    throw new FSSCException(FsscErrorType.REFERENCES_BY_FOR_INVALID);
                                }
                            }
                        }
                    }
                }
            }
        }
        mainTypeService.modifyValidFlag(ids, status);
        return Result.success();
    }

    @ApiOperation(value = "查询所有一级大类", notes = "查询所有一级大类")
    @GetMapping(value = "/getParentMainType")
    public Result<List<Select2DataSource>> getAllParentMainType() {
        BaseExpenseMainTypeQueryForm queryForm = new BaseExpenseMainTypeQueryForm();
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        queryForm.setParentFlag(FsscEums.YES.getValue());
        queryForm.setInvalidFlag(FsscEums.YES.getValue());
        queryForm.setParentCode("");
        List<BaseExpenseMainType> mainTypeList = mainTypeService
                .selectList(queryForm);
        List<Select2DataSource> select2DataSources = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(mainTypeList)) {
            for (BaseExpenseMainType mainType : mainTypeList) {
                Select2DataSource option = new Select2DataSource(mainType.getCode(),
                        mainType.getName());
                select2DataSources.add(option);
            }
        }
        return Result.success(select2DataSources);
    }

    @ApiOperation(value = "列表查询支出大类,用于下拉框,只查询非父类", httpMethod = "POST",
            notes = "列表查询支出大类,只查询非父类,用于下拉框")
    @ApiParam(name = "queryForm", value = "支出大类查询参数", required = true)
    @PostMapping(value = "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(@Valid @RequestBody BaseExpenseMainTypeQueryForm queryForm) {
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        queryForm.setParentFlag(FsscEums.NO.getValue());
        List<BaseExpenseMainType> mainTypeList = mainTypeService.selectList(queryForm);
        if (CollectionUtils.isEmpty(mainTypeList)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<Select2DataSource> select2DataSourceList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(mainTypeList)) {
            for (BaseExpenseMainType mainType : mainTypeList) {
                Select2DataSource option = new Select2DataSource(mainType.getId(),mainType.getCode(),
                        mainType.getName());
                select2DataSourceList.add(option);
            }
        }
        return Result.success(select2DataSourceList);
    }

    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, BaseExpenseMainTypeQueryForm queryForm) {
        log.info("search with BaseExpenseMainTypeQueryForm:{}", JSON.toJSONString(queryForm));
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<BaseExpenseMainType> mainTypeList = mainTypeService.selectPage(queryForm)
                .getRecords();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("支出大类编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("支出大类名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("是否父值").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("父值").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("使用单位").setOneCellWidth(6000));
        List<String> unitCodeList = mainTypeList.stream().map(BaseExpenseMainType::getUnitCode)
                .collect(Collectors.toList());
        Result<HashMap<String, String>> deptVoResult = deptFeignService
                .searchNameByCodes(unitCodeList);
        HashMap<String, String> codeNameMap = deptVoResult.getData();
        String fileName = "支出大类信息";
        Object[][] content = new Object[mainTypeList.size()][];
        for (int i = 0; i < mainTypeList.size(); i++) {
            content[i] = new String[headerList.size()];
            BaseExpenseMainType vo = mainTypeList.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getCode();
            content[i][2] = vo.getName();
            content[i][3] = FsscEums.YES.getValue().equals(vo.getParentFlag()) ? "是" : "否";
            content[i][4] = StringUtil.objectToString(vo.getParentCode()) + " " + StringUtil
                    .objectToString(vo.getParentName());
            content[i][5] = StringUtil.isEmpty(vo.getInvalidFlag()) ? "N/A" :
                    (FsscEums.VALID.getValue().equals(vo.getInvalidFlag()) ? "是" : "否");
            content[i][6] = codeNameMap.get(vo.getUnitCode()) + "";
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

//    @ApiOperation(value = "分页查询支出大类-组织单位信息", notes = "根据条件查询支出大类-组织单位信息分页数据")
//    @ApiParam(name = "queryForm", value = "支出大类-组织单位信息查询参数", required = true)
//    @PostMapping(value = "/pageOrgUnit/conditions")
//    public  Result<IPage<BaseExpenseMainTypeUnitVo>> search(
//            @Valid @RequestBody BaseExpenseMainTypeUnitQueryForm queryForm) {
//        log.info("search with baseExpenseMainTypeUnitQueryForm:{}",
//                queryForm.toString());
//        IPage<BaseExpenseMainTypeUnit> pageInfo = mainTypeOrgUnitService
//                .selectPage(queryForm);
//        IPage<BaseExpenseMainTypeUnitVo> baseExpenseMainTypeVoPage = new BeanUtils<BaseExpenseMainTypeUnitVo>()
//                .copyPageObjs(pageInfo, BaseExpenseMainTypeUnitVo.class);
//        return Result.success(baseExpenseMainTypeVoPage);
//    }
//
//    @ApiOperation(value = "修改支出大类-组织单位生失效状态", notes = "支持批量")
//    @PostMapping(value = "/modifyMainTypeOrgUnitStatus")
//    public Result modifyMainTypeOrgUnitStatus(
//            @RequestBody @Valid ModifyMainTypeOrgUnitStatusForm form) {
//        List<Long> ids = form.getIds();
//        String status = form.getStatus();
//        mainTypeOrgUnitService.modifyValidFlag(ids, status);
//        return Result.success();
//    }
//
//    @ApiOperation(value = "新增支出大类-组织单位", notes = "支持批量")
//    @PostMapping(value = "/addMainTypeOrgUnit")
//    public Result addMainTypeOrgUnit(@RequestBody AddExpenseMainTypeOrgUnitForm addForm) throws Exception{
//        log.info("search with addMainTypeOrgUnit:{}",JSON.toJSONString(addForm));
//        List<BaseExpenseMainTypeUnitForm> formList = addForm.getFormList();
//        List<BaseExpenseMainTypeUnit> newAllPoList = new ArrayList<>();
//        String[] expenseMainTypeIds = addForm.getExpenseMainTypeIds().split(",");
//        log.info("expenseMainTypeIds length: {}",expenseMainTypeIds.length);
//        if (ArrayUtils.isEmpty(expenseMainTypeIds)){
//            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
//        }
//        for (String expenseMainTypeIdStr : expenseMainTypeIds){
//            Long expenseMainTypeId = Long.valueOf(expenseMainTypeIdStr);
//            BaseExpenseMainTypeUnitQueryForm queryForm = new BaseExpenseMainTypeUnitQueryForm();
//            queryForm.setExpenseMainTypeId(expenseMainTypeId);
//            List<BaseExpenseMainTypeUnit> oldFormList = mainTypeOrgUnitService.selectList(queryForm);
//            List<String> oldUnitCodeList = oldFormList.stream().map(BaseExpenseMainTypeUnit :: getOrgUnitCode).collect(
//                    Collectors.toList());
//            List<BaseExpenseMainTypeUnit> poList = new ArrayList<>();
//            if (CollectionUtils.isNotEmpty(formList)){
//                for (BaseExpenseMainTypeUnitForm form : formList){
//                    BaseExpenseMainTypeUnit orgUnit = new BaseExpenseMainTypeUnit();
//                    BeanUtils.copyProperties(form,orgUnit);
//                    orgUnit.setExpenseMainTypeId(expenseMainTypeId);
//                    orgUnit.setValidFlag(FsscEums.VALID.getValue());
//                    poList.add(orgUnit);
//                }
//            }
//            List<BaseExpenseMainTypeUnit> newPoList = poList.stream().filter(e -> !oldUnitCodeList
//                    .contains(e.getOrgUnitCode())).collect(Collectors.toList());
//            if (CollectionUtils.isNotEmpty(newPoList)){
//                newAllPoList.addAll(newPoList);
//            }
//        }
//        if (CollectionUtils.isEmpty(newAllPoList)){
//            throw new FSSCException(FsscErrorType.CANNOT_ALLOCATION_NEW_UNIT);
//        }
//        mainTypeOrgUnitService.saveOrUpdateBatch(newAllPoList);
//        return Result.success();
//   }

    public void isDeleteBillOrsubType(List<Long> ids){
        if (subTypeService.existsByExpenseMainTypeIds(ids)){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_SUB_TYPE);
        }
        if (mainTypeService.isRelateBudgetAccount(ids)){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        if (borrowMoneyInfoService.existsByExpenseMainTypeIds(ids)){
            throw new FSSCException(FsscErrorType.BORROW_MONERY_INFO);
        }
        if(bmAdvancePaymentInfoService.existsByExpenseMainTypeAdvIds(ids)){
            throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO);
        }
        if(crbContractReimburseBillService.existsByExpenseMainTypeAdvIds(ids)){
            throw new FSSCException(FsscErrorType.CONTRACT_REIMBURSE_BILL);
        }
        //判断支出大类关联通用
        if(geGeneralExpenseService.existsByExpenseMainTypeGeIds(ids)){
            throw new FSSCException(FsscErrorType.GENERAL_EXPENSE_INFO);
        }
        //判断支出大类关联劳务费
        if(lcLaborCostService.existsByExpenseMainTypeLcIds(ids)){
            throw new FSSCException(FsscErrorType.LABOR_COST_INFO);
        }
        //判断支出大类关联差旅申请
        if(tasTravleApplyInfoService.existsByExpenseMainTypeTaIds(ids)){
            throw new FSSCException(FsscErrorType.TRAVLE_APPLY_INFO);
        }
        //判断支出大类关联差旅报账
        if(tasTravelReimburseService.existsByExpenseMainTypeTrIds(ids)){
            throw new FSSCException(FsscErrorType.TRAVEL_REIMBURSE);
        }
        //单据类型定义
        if (documentTypeExpenseService.existsByExpenseMainTypeIds(ids)){
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
        }

    }

}



