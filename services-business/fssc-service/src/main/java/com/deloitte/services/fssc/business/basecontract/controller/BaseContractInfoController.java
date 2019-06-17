package com.deloitte.services.fssc.business.basecontract.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.feign.BasicInfoFeignService;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.fssc.basecontract.client.BaseContractInfoClient;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractInfoQueryForm;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractPlanLineQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractInfoForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractInfoVo;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractPlanLineVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractInfo;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanLine;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanMap;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractProject;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractInfoService;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractPlanLineService;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractPlanMapService;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractProjectService;
import com.deloitte.services.fssc.business.bpm.service.IProcessService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :   BaseContractInfo控制器实现类
 * @Modified :
 */
@Api(tags = "合同基本信息操作接口")
@Slf4j
@RestController
public class BaseContractInfoController implements BaseContractInfoClient {

    @Autowired
    public IBaseContractInfoService baseContractInfoService;

    @Autowired
    private IBaseContractPlanLineService contractPlanLineService;

    @Autowired
    private IBaseContractPlanMapService contractPlanMapService;

    @Autowired
    private BasicInfoFeignService feignService;

    @Autowired
    private FsscCommonServices commonServices;

    @ApiOperation(value = "接收合同基本信息", notes = "接收合同基本信息")
    @PostMapping(value = path + "/receiveBasic")
    @Transactional
    public Result receive(@Valid @RequestBody
                          @ApiParam(name = "BasicInfoVo", value = "BasicInfoVo", required = true)
                                  List<BasicInfoVoToFssc> basicInfoVos) {
        log.info("form:{}", JSON.toJSONString(basicInfoVos));
        if (CollectionUtils.isNotEmpty(basicInfoVos)) {
            for (BasicInfoVoToFssc vo : basicInfoVos) {
                saveBasicVo(vo);
                List<BasicInfoVoToFssc> listBasicInfoVo = vo.getListBasicInfoVo();
                if (CollectionUtils.isNotEmpty(listBasicInfoVo)) {
                    for (BasicInfoVoToFssc infoVo : listBasicInfoVo) {
                        changeBasicStatus(infoVo);
                    }
                }
            }
        }
        return Result.success();
    }

    private void changeBasicStatus(BasicInfoVoToFssc vo) {
        String contractId = vo.getId();
        QueryWrapper<BaseContractInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseContractInfo.CONTRACT_ID, StringUtil.castTolong(contractId));
        BaseContractInfo contractInfo = baseContractInfoService.getOne(queryWrapper);
        contractInfo.setStatue(vo.getStatue());
        baseContractInfoService.updateById(contractInfo);
    }

    /**
     * 保存合同基本信息
     *
     * @param vo
     */
    private void saveBasicVo(BasicInfoVoToFssc vo) {
        String contractId = vo.getId();
        BaseContractInfo baseContractInfo;
        QueryWrapper<BaseContractInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseContractInfo.CONTRACT_ID, StringUtil.castTolong(contractId));
        List<BaseContractInfo> contractInfos = baseContractInfoService.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(contractInfos)) {
            baseContractInfo = contractInfos.get(0);
        } else {
            baseContractInfo = new BaseContractInfo();
        }
        baseContractInfo.setContractId(StringUtil.castTolong(contractId));
        baseContractInfo.setContractName(vo.getContractName());
        baseContractInfo.setContractNo(vo.getContractNo());
        baseContractInfo.setOldContractId(StringUtil.castTolong(vo.getOldContractId()));
        baseContractInfo.setOldContractName(vo.getOldContractName());
        baseContractInfo.setOldContractNo(vo.getOldContractNo());
        String contractType = vo.getContractType();
        if (StringUtil.isNotEmpty(contractType) && contractType.contains("-")) {
            baseContractInfo.setContractType(contractType.split("-")[0]);
        }
        String isForeignContract = vo.getIsForeignContract();
        if ("0".equals(isForeignContract)) {
            baseContractInfo.setIsForeignContract("N");
        }
        if ("1".equals(isForeignContract)) {
            baseContractInfo.setIsForeignContract("Y");
        }
        baseContractInfo.setExecuteStartTime(vo.getExecuteStartTime());
        baseContractInfo.setExecuteEndTime(vo.getExecuteEndTime());
        baseContractInfo.setExecuteStatueTime(vo.getValidTime());
        if ("已退回".equals(vo.getStatueName())) {
            baseContractInfoService.remove(queryWrapper);
            return;
        }
        baseContractInfo.setStatue(vo.getStatue());
        baseContractInfo.setStatueName(vo.getStatueName());
        baseContractInfo.setOperator(vo.getOperator());
        baseContractInfo.setOrg(vo.getOrg());
        baseContractInfo.setOrgCode(vo.getOrgCode());
        baseContractInfo.setAmountIncludTax(vo.getAmountIncludTax());
        //签约主体对象
        ArrayList<BasicSubjectMapVo> basicSubjectList = vo.getBasicSubjectList();
        String my = "";
        String side = "";
        if (CollectionUtils.isNotEmpty(basicSubjectList)) {
            for (BasicSubjectMapVo mapVo : basicSubjectList) {
                String subjectName = mapVo.getSubjectName();
                if ("1".equals(mapVo.getType())) {
                    my += subjectName + ",";
                    baseContractInfo.setOurSubjectId(mapVo.getSubjectId());
                }
                if ("2".equals(mapVo.getType())) {
                    side += subjectName + ",";
                    baseContractInfo.setSideSubjectId(mapVo.getSubjectId());
                }
            }
            if (my.length() > 1) {
                my = my.substring(0, my.length() - 1);
            }
            if (side.length() > 1) {
                side = side.substring(0, side.length() - 1);
            }
        }
        baseContractInfo.setSideSubjectName(side);
        baseContractInfo.setOurSubjectName(my);
        baseContractInfoService.saveOrUpdate(baseContractInfo);
    }

    @Autowired
    private IBaseContractProjectService baseContractProjectService;

    @ApiOperation(value = "接收履行计划", notes = "接收履行计划")
    @PostMapping(value = path + "/receiveFinance")
    @Transactional
    public Result receiveFinance(@Valid @RequestBody
                                 @ApiParam(name = "BasicInfoVo", value = "BasicInfoVo", required = true)
                                         BasicInfoVoToFssc basicInfoVo) {
        //当前新合同id
        Long currentContractId = StringUtil.castTolong(basicInfoVo.getId());
        //查询所有合同id包括当前合同id
        Set<Long> oldIds = new HashSet<>();
        findAllOldContractId(currentContractId, oldIds);
        //删除关联关系
        QueryWrapper<BaseContractPlanMap> mapQueryWrapper = new QueryWrapper<>();
        mapQueryWrapper.in(BaseContractPlanMap.CONTRACT_ID, oldIds);
        List<BaseContractPlanMap> planMaps = contractPlanMapService.list(mapQueryWrapper);
        //删除之前的履行计划
        Set<Long> collect = planMaps.stream().map(kk -> kk.getPlanId()).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(collect)) {
            QueryWrapper<BaseContractPlanLine> planLineQueryWrapper = new QueryWrapper<>();
            planLineQueryWrapper.in(BaseContractPlanLine.TRAVEL_PLAN_ID, collect);
            contractPlanLineService.remove(planLineQueryWrapper);
        }
        //删除关联关系
        if (CollectionUtils.isNotEmpty(oldIds)) {
            contractPlanMapService.remove(mapQueryWrapper);
        }

        List<SysProjectInfoVoFssc> listSysProjectInfoVo = basicInfoVo.getListSysProjectInfoVo();
        if (CollectionUtils.isNotEmpty(listSysProjectInfoVo)) {
            for (SysProjectInfoVoFssc fssc : listSysProjectInfoVo) {
                Long projectId = StringUtil.castTolong(fssc.getId());
                if (projectId != null) {
                    BaseContractProject project = new BaseContractProject();
                    project.setContractId(currentContractId);
                    project.setProjectId(projectId);
                    baseContractProjectService.save(project);
                }
            }
        }

        //建立新关系
        List<FinanceInfoVoToFssc> listFinanceInfoVo = basicInfoVo.getListFinanceInfoVo();
        if (CollectionUtils.isNotEmpty(listFinanceInfoVo)) {
            for (FinanceInfoVoToFssc financeInfoVo : listFinanceInfoVo) {
                BaseContractPlanLine baseContractPlanLine = new BaseContractPlanLine();
                Long id = StringUtil.castTolong(financeInfoVo.getId());
                baseContractPlanLine.setTravelPlanId(id);
                baseContractPlanLine.setSideSubjectCode(financeInfoVo.getOtherContractEntityCode());
                baseContractPlanLine.setTravelDeptName(basicInfoVo.getOrg());
                baseContractPlanLine.setTravelUserName(basicInfoVo.getOperator());
                baseContractPlanLine.setAgreedPaymentAmount(BigDecimalUtil.toDecimal(financeInfoVo.getAppointPayAmount()));
                baseContractPlanLine.setActualPlayAmount(BigDecimalUtil.toDecimal(financeInfoVo.getAppointPayAmount()));
                baseContractPlanLine.setReceipPlayAmount(BigDecimalUtil.toDecimal(financeInfoVo.getAppointPayAmount()));
                baseContractPlanLine.setContractName(financeInfoVo.getContractName());
                baseContractPlanLine.setSideSubjectName(financeInfoVo.getOtherContractEntity());
                baseContractPlanLine.setAppointPayStage(financeInfoVo.getAppointPayStage());
                baseContractPlanLine.setAgreedPropertions(StringUtil.objectToString(financeInfoVo.getAppointPayRate()));
                baseContractPlanLine.setPlanPayTime(financeInfoVo.getPlanPayTime());
                baseContractPlanLine.setPlanRecieveTime(financeInfoVo.getPlanIncomeTime());
                baseContractPlanLine.setAgreedRecieveAmount(BigDecimalUtil.toDecimal(financeInfoVo.getAppointIncomeAmount()));
                baseContractPlanLine.setAppointRecieveStage(financeInfoVo.getAppointIncomeStage());
                baseContractPlanLine.setAgreedRecievePropertions(StringUtil.objectToString(financeInfoVo.getAppointIncomeRate()));

                contractPlanLineService.save(baseContractPlanLine);
                //重新创建关联关系
                for (Long l : oldIds) {
                    BaseContractPlanMap map = new BaseContractPlanMap();
                    map.setContractId(l);
                    map.setPlanId(id);
                    contractPlanMapService.save(map);
                }
            }
        }

        return Result.success();
    }

    /**
     * 根据当前合同id查询所有相关的合同
     *
     * @param currentContractId
     * @return
     */
    public void findAllOldContractId(Long currentContractId, Set<Long> ids) {
        ids.add(currentContractId);
        QueryWrapper<BaseContractInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseContractInfo.CONTRACT_ID, currentContractId);
        BaseContractInfo one = baseContractInfoService.getOne(queryWrapper);
        if (one != null) {
            Long oldContractId = one.getOldContractId();
            if (oldContractId == null) {
                return;
            } else {
                findAllOldContractId(oldContractId, ids);
            }
        }
    }


    @ApiOperation(value = "修改基本信息", notes = "修改基本信息")
    @PostMapping(value = path + "/update")
    @Transactional
    public Result update(@RequestBody BaseContractInfoForm form) {
        AssertUtils.asserts(form.getId() != null, FsscErrorType.ID_CANT_BE_NULL);
        boolean s = baseContractInfoService.updateBaseContract(form);
        AssertUtils.asserts(s, FsscErrorType.SAVE_FAIL);
        if (StringUtil.isNotEmpty(form.getIsPayStampDuty()) || form.getStampDuty() != null
                || form.getTaxAmountOrPieces() != null) {
            BaseContractInfo contractInfo = baseContractInfoService.getById(form.getId());
            BasicInfoForm basicInfoForm = new BasicInfoForm();
            basicInfoForm.setId(contractInfo.getContractId());
            basicInfoForm.setIsImprint(form.getIsPayStampDuty());
            basicInfoForm.setImprintAmount(BigDecimalUtil.convert(form.getTaxAmountOrPieces()).toString());
            basicInfoForm.setImprintCases(BigDecimalUtil.convert(form.getStampDuty()).toString());
            log.info("推送印花税参数:{}", JSON.toJSONString(basicInfoForm));
            Result result = feignService.saveImprint(basicInfoForm);
            log.info("推送印花税:{}", result.getMesg());
            log.info("推送印花税错误信息:{}", JSON.toJSONString(result.getData()));
        }
        return Result.success();
    }


    @ApiOperation(value = "获取BaseContractInfo", notes = "获取指定ID的BaseContractInfo信息")
    @GetMapping(value = path + "getById/{id}")
    public Result<BaseContractInfoVo> get(@PathVariable(value = "id") Long id) {
        log.info("get with id:{}", id);
        BaseContractInfo baseContractInfo = baseContractInfoService.getById(id);
        BaseContractInfoVo baseContractInfoVo = new BeanUtils<BaseContractInfoVo>().copyObj(baseContractInfo, BaseContractInfoVo.class);
        return new Result<BaseContractInfoVo>().sucess(baseContractInfoVo);
    }


    @ApiOperation(value = "分页查询BaseContractInfo", notes = "根据条件查询BaseContractInfo分页数据")
    @GetMapping(value = path + "page/conditions")
    public Result<IPage<BaseContractInfoVo>> search(@Valid
                                                    @ApiParam(name = "baseContractInfoQueryForm", value = "BaseContractInfo查询参数", required = true)
                                                            BaseContractInfoQueryForm baseContractInfoQueryForm) {
        log.info("search with baseContractInfoQueryForm:{}", baseContractInfoQueryForm.toString());
        IPage<BaseContractInfo> baseContractInfoPage = baseContractInfoService.selectPage(baseContractInfoQueryForm);
        IPage<BaseContractInfoVo> baseContractInfoVoPage =
                new BeanUtils<BaseContractInfoVo>().copyPageObjs(baseContractInfoPage, BaseContractInfoVo.class);
        return Result.success(baseContractInfoVoPage);
    }

    @ApiOperation(value = "分页查询BaseContractInfo", notes = "根据条件查询BaseContractInfo分页数据")
    @GetMapping(value = path + "findValidContracts")
    public Result<IPage<BaseContractInfoVo>> search2(@Valid
                                                     @ApiParam(name = "baseContractInfoQueryForm", value = "BaseContractInfo查询参数", required = true)
                                                             BaseContractInfoQueryForm baseContractInfoQueryForm) {

        return findContract(baseContractInfoQueryForm, "支出类");
    }


    @ApiOperation(value = "分页查询BaseContractInfo", notes = "根据条件查询BaseContractInfo分页数据")
    @GetMapping(value = path + "findKxContracts")
    public Result<IPage<BaseContractInfoVo>> search23(@Valid
                                                      @ApiParam(name = "baseContractInfoQueryForm", value = "BaseContractInfo查询参数", required = true)
                                                              BaseContractInfoQueryForm baseContractInfoQueryForm) {

        return findContract(baseContractInfoQueryForm, "收入类");
    }


    private Result<IPage<BaseContractInfoVo>> findContract(BaseContractInfoQueryForm baseContractInfoQueryForm,
                                                           String type) {
        log.info("search with baseContractInfoQueryForm:{}", baseContractInfoQueryForm.toString());
        QueryWrapper<BaseContractInfo> infoQueryWrapper = new QueryWrapper<>();
        String orgCode = "";
        UserVo currentUser = commonServices.getCurrentUser();
        if (currentUser != null) {
            DeputyAccountVo currentDeputyAccount = currentUser.getCurrentDeputyAccount();
            if (currentDeputyAccount != null) {
                orgCode = currentDeputyAccount.getOrgCode();
            }
        }


        infoQueryWrapper.eq("ORG_CODE", orgCode);
        infoQueryWrapper.eq(BaseContractInfo.STATUE_NAME, "履行中");
        infoQueryWrapper.apply("OLD_CONTRACT_ID IS NULL AND (CONTRACT_TYPE LIKE '%有收有支%' OR CONTRACT_TYPE LIKE '%" + type + "%' )");
        IPage<BaseContractInfo> baseContractInfoPage = baseContractInfoService.page(
                new Page<>(baseContractInfoQueryForm.getCurrentPage(), 999999), infoQueryWrapper);
        IPage<BaseContractInfoVo> baseContractInfoVoPage = new BeanUtils<BaseContractInfoVo>().copyPageObjs(baseContractInfoPage, BaseContractInfoVo.class);
        return Result.success(baseContractInfoVoPage);

    }


    /**
     * 查询合同详情
     *
     * @return
     */
    @GetMapping(value = path + "findContractDetail")
    @ApiOperation(value = "查询合同详情", notes = "查询合同详情")
    public Result<List<BaseContractPlanLineVo>> findContractDetail(BaseContractPlanLineQueryForm queryForm) {
        return Result.success(contractPlanLineService.selectPage(queryForm).getRecords());
    }


    @ApiOperation(value = "导出合同基本信息", notes = "导出合同基本信息")
    @GetMapping(value = path + "exportExcel")
    public void exportHeaderLabor(BaseContractInfoQueryForm baseContractInfoQueryForm) throws IOException {
        log.info("export param:{}", baseContractInfoQueryForm.toString());
        List<BaseContractInfo> records = baseContractInfoService.selectPage(baseContractInfoQueryForm).getRecords();
        String[] title = {"序号", "合同编号", "合同名称", "对方签约主体", "我方签约主体", "合同金额(元)", "生效日期"
                , "履行期限从", "履行期限至", "合同类型", "合同状态", "主办部门", "经办人", "是否缴纳印花税", "是否涉外合同"};
        String fileName = "合同基本信息";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BaseContractInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getContractNo();
            content[i][2] = vo.getContractName();
            content[i][3] = vo.getSideSubjectName();
            content[i][4] = vo.getOurSubjectName();
            if (vo.getAmountIncludTax() != null) {
                content[i][5] = vo.getAmountIncludTax().toString();
            }
            if (vo.getExecuteStatueTime() != null) {
                content[i][6] = LocalDateTimeUtils.formatTime(vo.getExecuteStatueTime(), "yyyy-MM-dd HH:mm:ss");
            }
            if (vo.getExecuteStartTime() != null) {
                content[i][7] = LocalDateTimeUtils.formatTime(vo.getExecuteStartTime(), "yyyy-MM-dd HH:mm:ss");
            }
            if (vo.getExecuteEndTime() != null) {
                content[i][8] = LocalDateTimeUtils.formatTime(vo.getExecuteEndTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][9] = vo.getContractType();
            content[i][10] = vo.getStatue();
            content[i][11] = vo.getOrg();
            content[i][12] = vo.getOperator();
            if ("Y".equals(vo.getIsPayStampDuty())) {
                content[i][13] = "是";
            } else if ("N".equals(vo.getIsPayStampDuty())) {
                content[i][13] = "否";
            }
            if ("Y".equals(vo.getIsForeignContract())) {
                content[i][14] = "是";
            } else if ("N".equals(vo.getIsForeignContract())) {
                content[i][14] = "否";
            }

        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    @Autowired
    private IContactDetailService contactDetailService;
    @Autowired
    private IProcessService processService;

    @ApiOperation(value = "是否能修改合同", notes = "是否能修改合同")
    @GetMapping(value = path + "canModifyContract")
    public Result<Boolean> canModifyContract(@RequestParam("financeId") Long financeId) {
        QueryWrapper<ContactDetail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.eq("TRAVEL_PLAN_ID", financeId);
        List<ContactDetail> details = contactDetailService.list(detailQueryWrapper);
        if (CollectionUtils.isNotEmpty(details)) {
            for (ContactDetail detail : details) {
                Long documentId = detail.getDocumentId();
                String documentType = detail.getDocumentType();
                String status = processService.findStatus(documentId, documentType);
                if (FsscEums.SUBMIT.getValue().equals(status) || FsscEums.APPROVALING.getValue().equals(status)
                        || FsscEums.APPROVED.getValue().equals(status) || FsscEums.IN_ACCOUNTING.getValue().equals(status)
                        || FsscEums.HAS_ACCOUTED.getValue().equals(status)) {
                    return Result.success(false);
                }
            }
        }
        return Result.success(true);
    }

}



