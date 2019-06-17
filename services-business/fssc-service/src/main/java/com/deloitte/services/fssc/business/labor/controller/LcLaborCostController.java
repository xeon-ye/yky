package com.deloitte.services.fssc.business.labor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineQueryForm;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.GePrivatePaymentListVo;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineChinaAndForeignVo;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.labor.biz.LcLaborCostBiz;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :   LcLaborCost控制器实现类
 * @Modified :
 */
@Api(tags = "劳务费报账单操作接口")
@Slf4j
@RestController
@RequestMapping(value = "laborCost")
public class LcLaborCostController {

    @Autowired
    public ILcLaborCostService lcLaborCostService;

    @Autowired
    private LcLaborCostBiz lcLaborCostBiz;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;
    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    /**
     * 计税接口
     *
     * @param id
     */
    @PostMapping(value = "doTax/{id}")
    @Transactional
    public Result doTax(@PathVariable(value = "id") Long id) {
        lcLaborCostBiz.doTax(id);
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.LC_LABOR_COST.getValue(), id);
        return Result.success();
    }


    @ApiOperation(value = "新增或修改劳务费报账单", notes = "新增或修改劳务费报账单有id的时候修改，没有id新增所有接口类似")
    @PostMapping(value = "addOrUpdateLaborCost")
    public Result<LcLaborCostVo> add(@Valid @RequestBody @ApiParam(name = "lcLaborCostForm", value = "新增LcLaborCost的form表单", required = true)
                                             LcLaborCostForm lcLaborCostForm) {
        log.info("form:{}", lcLaborCostForm.toString());
        LcLaborCost lcLaborCost = lcLaborCostBiz.saveOrUpdateLaborCost(lcLaborCostForm);
        return Result.success(new BeanUtils<LcLaborCostVo>().copyObj(lcLaborCost, LcLaborCostVo.class));
    }


    @ApiOperation(value = "删除劳务费报账单", notes = "根据url的id来指定删除劳务费报账单")
    @DeleteMapping(value = "deleteById/{id}")
    public Result delete(@PathVariable(value = "id") Long id) {
        lcLaborCostBiz.deleteById(id);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }


    @ApiOperation(value = "获取劳务费报账单", notes = "获取指定ID的LcLaborCost信息")
    @GetMapping(value = "getLaborCostById/{id}")
    public Result<LcLaborCostVo> get(@PathVariable(value = "id") long id) {
        log.info("get with id:{}", id);
        return new Result<LcLaborCostVo>().sucess(lcLaborCostBiz.getById(id));
    }


    @ApiOperation(value = "分页查询劳务费报账单主表", notes = "根据条件查询LcLaborCost分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<LcLaborCostVo>> search(@ApiParam(name = "lcLaborCostQueryForm", value = "LcLaborCost查询参数", required = true)
                                                       LcLaborCostQueryForm lcLaborCostQueryForm) {
        log.info("search with lcLaborCostQueryForm:{}", lcLaborCostQueryForm.toString());
        IPage<LcLaborCost> lcLaborCostPage = lcLaborCostService.selectPage(lcLaborCostQueryForm);
        IPage<LcLaborCostVo> lcLaborCostVoPage = new BeanUtils<LcLaborCostVo>().copyPageObjs(lcLaborCostPage, LcLaborCostVo.class);
        return new Result<IPage<LcLaborCostVo>>().sucess(lcLaborCostVoPage);
    }


    @ApiOperation(value = "分页查询劳务费报账单行表", notes = "分页查询劳务费报账单行表")
    @GetMapping(value = "page/searchLines")
    public Result<IPage<LcLaborCostLineChinaAndForeignVo>>
    searchLines(@Valid
                @ApiParam(name = "LcLaborCostLineQueryForm", value = "查询参数", required = true)
                        LcLaborCostLineQueryForm queryForm) {

        return Result.success(lcLaborCostBiz.findLineDetail(queryForm));
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result doSubmitProcess(@RequestBody @Valid LcLaborCostForm lcLaborCostForm) {
        //todo 待验证参数 加注解
        log.info("lcLaborCostForm:{}", lcLaborCostForm.toString());
        LcLaborCost lcLaborCost = lcLaborCostBiz.saveOrUpdateLaborCost(lcLaborCostForm);

        AssertUtils.asserts(FsscEums.NEW.getValue().equals(lcLaborCost.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(lcLaborCost.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(lcLaborCost.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);


        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setProjectId(lcLaborCost.getProjectId());
        valueForm.setTotalAmount(lcLaborCost.getShouldGiveTotalAmount().toString());
        valueForm.setMainTypeCode(lcLaborCost.getMainTypeCode());
        valueForm.setDocumentId(lcLaborCost.getId());
        valueForm.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
        valueForm.setCreateBy(lcLaborCost.getCreateBy());
        valueForm.setDeptCode(lcLaborCost.getDeptCode());
        valueForm.setProjectCode(lcLaborCost.getProjectCode());
        valueForm.setUnitCode(lcLaborCost.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(lcLaborCost.getId());
        startForm.setDocumentNum(lcLaborCost.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        startForm.setBudgetWarningCheck(lcLaborCostForm.getBudgetWarningCheck());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(lcLaborCostForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(lcLaborCostForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        return Result.success();
    }


    @ApiOperation(value = "导出劳务费报账单主表", notes = "导出劳务费报账单主表")
    @GetMapping(value = "exportHeaderLabor")
    public void exportHeaderLabor(@ApiParam(name = "lcLaborCostQueryForm", value = "LcLaborCost查询参数", required = true)
                                          LcLaborCostQueryForm lcLaborCostQueryForm) throws IOException {
        log.info("export param:{}", lcLaborCostQueryForm.toString());
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<LcLaborCost> records = lcLaborCostService.selectPage(lcLaborCostQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "归属单位", "归属部门", "项目", "发放人数"
                , "应发金额", "实发金额", "创建日期", "单据状态", "付款状态"};
        String fileName = "劳务费主表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            LcLaborCost vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            content[i][5] = vo.getProjectName();
            content[i][6] = BigDecimalUtil.convert(vo.getGivePeopleCount()).toString();
            content[i][7] = BigDecimalUtil.convert(vo.getShouldGiveTotalAmount()).toString();
            content[i][8] = BigDecimalUtil.convert(vo.getRealGiveTotalAmount()).toString();
            content[i][9] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            content[i][10] = docStatusMap.get(vo.getDocumentStatus());
            content[i][11] = payStatusMap.get(vo.getPayStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }


    @ApiOperation(value = "导出劳务费报账单行表", notes = "导出劳务费报账单行表")
    @GetMapping(value = "exportLineLabor")
    public void exportLineLabor(@ApiParam(name = "LcLaborCostLineQueryForm", value = "查询参数", required = true)
                                        LcLaborCostLineQueryForm queryForm) throws IOException {
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        log.info("export param:{}", queryForm.toString());
        List<LcLaborCostLineChinaAndForeignVo> records = lcLaborCostBiz.findLineDetail(queryForm).getRecords();
        String[] title = {"序号", "收款人", "证件类型", "证件号码", "付款单位", "付款部门", "项目"
                , "应发金额", "扣税金额", "实发金额", "创建日期", "付款状态", "单据编号"};
        String fileName = "劳务费报账单行";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            LcLaborCostLineChinaAndForeignVo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getRecieveUserName();
            content[i][2] = vo.getCertType();
            content[i][3] = vo.getCertNum();
            content[i][4] = vo.getPayUnitName();
            content[i][5] = vo.getPayDeptName();
            content[i][6] = vo.getProjectName();
            content[i][7] = BigDecimalUtil.convert(vo.getShouldGiveAmount()).toString();
            content[i][8] = BigDecimalUtil.convert(vo.getDeductedAmount()).toString();
            content[i][9] = BigDecimalUtil.convert(vo.getRealGiveAmount()).toString();
            content[i][10] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            content[i][11] = payStatusMap.get(vo.getPayStatus());
            content[i][12] = vo.getDocumentNum();
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    /**
     * 已入账，未支付金额大于0
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/generatePayBill")
    @ApiOperation(value = "生成付款单", notes = "生成付款单")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<PyPamentOrderInfoVo> generatePayBill(Long id) {
        log.info("search with geGeneralExpenseQueryForm:", id);
        Result<LcLaborCostVo> resultData = get(id);
        LcLaborCostVo lcLaborCostVo = resultData.getData();
        String documentStatus = lcLaborCostVo.getDocumentStatus();
        String documentNum = lcLaborCostVo.getDocumentNum();
        if (pyPamentOrderInfoService.selectCount(documentNum)) {
            QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
            PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(queryWrapper);
            if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                throw new FSSCException(FsscErrorType.DOCUMENT_READY_PAYMENT);
            }
        }
        BigDecimal noPaidMount = lcLaborCostVo.getNoPaidAmount();
        if (noPaidMount == null) {
            noPaidMount = BigDecimal.ZERO;
        }
        BigDecimal zero = BigDecimal.ZERO;
        if (FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus) && noPaidMount.compareTo(zero) != 0) {
            PyPamentOrderInfoVo pamentOrderInfoVo = new PyPamentOrderInfoVo();
            pamentOrderInfoVo.setDocumentStatus(lcLaborCostVo.getDocumentStatus());
            // pamentOrderInfoVo.setDocumentNum();
            // pamentOrderInfoVo.setBankAccount();
            //pamentOrderInfoVo.setBankType();
            pamentOrderInfoVo.setCost(lcLaborCostVo.getCost());
            pamentOrderInfoVo.setCreateBy(lcLaborCostVo.getCreateBy());
            pamentOrderInfoVo.setCreateTime(lcLaborCostVo.getCreateTime());
            pamentOrderInfoVo.setCreateUserName(lcLaborCostVo.getCreateUserName());
            pamentOrderInfoVo.setCurrencyCode(lcLaborCostVo.getCurrencyCode());
            pamentOrderInfoVo.setDeptCode(lcLaborCostVo.getDeptCode());
            pamentOrderInfoVo.setDeptId(lcLaborCostVo.getDeptId());
            pamentOrderInfoVo.setDeptName(lcLaborCostVo.getDeptName());
            pamentOrderInfoVo.setIsAfterPatch(lcLaborCostVo.getIsAfterPatch());
            pamentOrderInfoVo.setNoPaidAmount(lcLaborCostVo.getNoPaidAmount());
            pamentOrderInfoVo.setPayDocumentNum(lcLaborCostVo.getDocumentNum());
            pamentOrderInfoVo.setPayDocumentType("LC_LABOR_COST");
            pamentOrderInfoVo.setPaidAmount(lcLaborCostVo.getPaidAmount());
            pamentOrderInfoVo.setPaymentType(lcLaborCostVo.getPaymentType());
            pamentOrderInfoVo.setPayStatus(lcLaborCostVo.getPayStatus());
            pamentOrderInfoVo.setRemark(lcLaborCostVo.getRemark());
            pamentOrderInfoVo.setTotalAmount(lcLaborCostVo.getRealGiveTotalAmount());
            // pamentOrderInfoVo.setTotalAmountOtherCurrency(lcLaborCostVo.getTotalAmountOtherCurrency());
            pamentOrderInfoVo.setUnitCode(lcLaborCostVo.getUnitCode());
            pamentOrderInfoVo.setUnitId(lcLaborCostVo.getUnitId());
            pamentOrderInfoVo.setUnitName(lcLaborCostVo.getUnitName());
            pamentOrderInfoVo.setUpdateTime(lcLaborCostVo.getUpdateTime());
            pamentOrderInfoVo.setUpdateBy(lcLaborCostVo.getUpdateBy());
            pamentOrderInfoVo.setVersion(lcLaborCostVo.getVersion());
            List<GePrivatePaymentListVo> salaryCards = lcLaborCostVo.getPrivatePaymentListVos();

            if (CollectionUtils.isNotEmpty(salaryCards)) {
                List<PyPamentBusinessLineVo> pyPamentPrivateLineVos = new ArrayList<PyPamentBusinessLineVo>();
                for (GePrivatePaymentListVo gePrivatePaymentListVo : salaryCards) {
                    PyPamentBusinessLineVo pyPamentBusinessLineVo = new PyPamentBusinessLineVo();
                    pyPamentBusinessLineVo.setBankAccount(gePrivatePaymentListVo.getBanAccount());
                    pyPamentBusinessLineVo.setBankId(gePrivatePaymentListVo.getBankId());
                    pyPamentBusinessLineVo.setBankInternationalCode(gePrivatePaymentListVo.getBankCode());
                    pyPamentBusinessLineVo.setBankName(gePrivatePaymentListVo.getBankBame());
                    //pyPamentBusinessLineVo.setBankReturnInfo();
                    // pyPamentBusinessLineVo.setBranchBankName();
                    pyPamentBusinessLineVo.setBusinessCardNum(gePrivatePaymentListVo.getBanAccount());
                    // pyPamentBusinessLineVo.setCommonCreditCode();
                    pyPamentBusinessLineVo.setConnectDocumentId(lcLaborCostVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(lcLaborCostVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(gePrivatePaymentListVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PRIVATE");
                    pyPamentBusinessLineVo.setConnectDocumentType("LC_LABOR_COST");
                    pyPamentBusinessLineVo.setCreateBy(gePrivatePaymentListVo.getCreateBy());
                    pyPamentBusinessLineVo.setCreateTime(gePrivatePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setCreateUserName(gePrivatePaymentListVo.getCreateUserName());
                    pyPamentBusinessLineVo.setInterBranchNumber(gePrivatePaymentListVo.getInterBranchNumber());
                    // pyPamentBusinessLineVo.setLineNumber();
                    pyPamentBusinessLineVo.setPayAmount(gePrivatePaymentListVo.getPayAmount());
                    pyPamentBusinessLineVo.setPayStatus(gePrivatePaymentListVo.getPayStatus());
                    pyPamentBusinessLineVo.setRecieveEmpName(gePrivatePaymentListVo.getRecieveUserName());
                    pyPamentBusinessLineVo.setRecieveId(gePrivatePaymentListVo.getRecieveUserId());
                    //pyPamentBusinessLineVo.setTransactionAmount(bmBorrowBankVo.getTransactionAmount());
                    // pyPamentBusinessLineVo.setTransactionComments();
                    pyPamentBusinessLineVo.setEmpNo(gePrivatePaymentListVo.getCertNum());
                    pyPamentBusinessLineVo.setTransactionDate(gePrivatePaymentListVo.getPayTime());
                    pyPamentBusinessLineVo.setUnitId(lcLaborCostVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(lcLaborCostVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(lcLaborCostVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(lcLaborCostVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(lcLaborCostVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("劳务费/咨询费报账单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(gePrivatePaymentListVo.getId());
                    pyPamentPrivateLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentPrivateLineVos(pyPamentPrivateLineVos);
            }
            return Result.success(pamentOrderInfoVo);
        }
        throw new FSSCException(FsscErrorType.NOT_GENERATE_BILL_PAYMENT);
    }

}



