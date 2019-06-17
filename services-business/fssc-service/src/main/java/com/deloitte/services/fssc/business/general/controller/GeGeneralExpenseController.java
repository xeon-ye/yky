package com.deloitte.services.fssc.business.general.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseQueryForm;
import com.deloitte.platform.api.fssc.general.vo.*;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.general.biz.GeGeneralExpenseBiz;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpenseLine;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseLineService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
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
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :   GeGeneralExpense控制器实现类
 * @Modified :
 */
@Api(tags = "通用报账单操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/general/ge-general-expense")
public class GeGeneralExpenseController {

    @Autowired
    public IGeGeneralExpenseService geGeneralExpenseService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IGeExpensePaymentListService paymentListService;
    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;
    @Autowired
    private IBmBorrowBankService bmBorrowBankService;
    @Autowired
    private IGeGeneralExpenseLineService lineService;
    @Autowired
    private BpmTaskBiz bpmTaskBiz;
    @Autowired
    private BpmProcessBiz bpmProcessBiz;
    @Autowired
    private GeGeneralExpenseBiz geGeneralExpenseBiz;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;
    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    @ApiOperation(value = "新增GeGeneralExpense", notes = "新增一个GeGeneralExpense")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "addOrUpdateGeGeneralExpense")
    public Result<GeGeneralExpenseVo> addOrUpdate(@Valid @RequestBody @ApiParam(name = "geGeneralExpenseForm",
            value = "新增GeGeneralExpense的form表单", required = true)
                                                          GeGeneralExpenseForm geGeneralExpenseForm) {
        log.info("form:{}", geGeneralExpenseForm.toString());
        GeGeneralExpense expense = geGeneralExpenseBiz.saveOrUpdateGeExpense(geGeneralExpenseForm);

        return Result.success(new BeanUtils<GeGeneralExpenseVo>().copyObj(expense, GeGeneralExpenseVo.class));
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<GeGeneralExpense> doSubmitProcess(@RequestBody @Valid GeGeneralExpenseForm geGeneralExpenseForm) {
        //todo 待验证参数 加注解
        log.info("borrowMoneyInfoForm:{}", geGeneralExpenseForm.toString());
        List<GeGeneralExpenseLineForm> generalExpenseLines = geGeneralExpenseForm.getGeneralExpenseLines();
        //所选支出大类存在未核销的借款单，请确认本报账单是否关联借款单!
        if(CollectionUtils.isNotEmpty(generalExpenseLines)&&
                "Y".equals(geGeneralExpenseForm.getHasBorrowLines())
        &&CollectionUtils.isEmpty(geGeneralExpenseForm.getBorrowPrepayFormList())){
            String pp="";
            for (GeGeneralExpenseLineForm line:generalExpenseLines){
                pp+=line.getPaymentType()+",";
            }
            if(pp.length()>1){
                pp=pp.substring(0,pp.length()-1);
            }
            GeExpenseBorrowPrepayQueryForm form=new GeExpenseBorrowPrepayQueryForm();
            form.setMainTypeId(geGeneralExpenseForm.getMainTypeId());
            form.setCurrencyCode(geGeneralExpenseForm.getCurrencyCode());
            form.setPaymentTypes(pp);
            form.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
            List<BorrowPrepayListVo> borrowPrepayList
                    = geGeneralExpenseService.findBorrowPrepayList(form);
            AssertUtils.asserts(CollectionUtils.isEmpty(borrowPrepayList),FsscErrorType.YOU_HAVE_MAINTYPE_BORROW_IS);
        }
        GeGeneralExpense expense = geGeneralExpenseBiz.saveOrUpdateGeExpense(geGeneralExpenseForm);
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(expense.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(expense.getDocumentStatus())
                || FsscEums.RECALLED.getValue().equals(expense.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        prepayService.modifyAfterSubmit(true, expense.getId(), FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest(expense.getApplyForId()!=null?"Y":"N");
        valueForm.setProjectId(expense.getProjectId());
        valueForm.setTotalAmount(expense.getTotalAmount().toString());
        valueForm.setMainTypeCode(expense.getMainTypeCode());
        valueForm.setDocumentId(expense.getId());
        valueForm.setDocumentType(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        valueForm.setCreateBy(expense.getCreateBy());
        valueForm.setDeptCode(expense.getDeptCode());
        valueForm.setProjectCode(expense.getProjectCode());
        valueForm.setUnitCode(expense.getUnitCode());
        Map<String, String> andAddProcessValue =
                baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(expense.getId());
        startForm.setDocumentNum(expense.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        startForm.setBudgetWarningCheck(geGeneralExpenseForm.getBudgetWarningCheck());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(),expense.getId());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(geGeneralExpenseForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(geGeneralExpenseForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        //提交时修改金额
        return Result.success(expense);
    }


    @ApiOperation(value = "删除GeGeneralExpense", notes = "根据url的id来指定删除对象")
    @DeleteMapping(value = "deleteById")
    @Transactional
    public Result delete(@ApiParam(value = "借款ID") @RequestBody Map<String, Long> map) {
        Long id = map.get("id");
        AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        GeGeneralExpense expense = geGeneralExpenseService.getById(id);
        AssertUtils.asserts(expense != null, FsscErrorType.DOCUMENT_NOT_FIND);
        String documentStatus = expense.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                FsscEums.RECALLED.getValue().equals(documentStatus) ||
                FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        removeSign(id);
        geGeneralExpenseService.removeById(id);
        String documentType = GeExpenseBorrowPrepay.DOCUMENT_TYPE;
        String documentId = GeExpenseBorrowPrepay.DOCUMENT_ID;
        //删除工资卡 公务卡
        QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
        bankQueryWrapper.eq(documentType, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue()).eq(BmBorrowBank.DOCUMENT_ID, id);
        bmBorrowBankService.remove(bankQueryWrapper);
        //删除对公付款清单
        QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper = new QueryWrapper<>();
        paymentListQueryWrapper.eq(documentType, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue()).eq(documentId, id);
        paymentListService.remove(paymentListQueryWrapper);
        //删除关联付款借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq(documentType, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue()).eq(documentId, id);
        prepayService.remove(prepayQueryWrapper);
        //删除行明细
        QueryWrapper<GeGeneralExpenseLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(documentId, id);
        lineService.remove(lineQueryWrapper);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }

    public void removeSign(Long id) {
        if (id != null) {
            GeGeneralExpense generalExpense = geGeneralExpenseService.getById(id);
            Long paymentId = generalExpense.getPayMentId();
            if (paymentId != null) {
                generalExpense.setPayMentId(null);
                geGeneralExpenseService.updateById(generalExpense);
            }
        }
    }


    /**
     * 根据单据id 获取信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取GeGeneralExpense", notes = "获取指定ID的GeGeneralExpense信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "GeGeneralExpense的ID", required = true, dataType = "long")
    @GetMapping(value = "getGeGeneralExpenseById/{id}")
    public Result<GeGeneralExpenseVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        GeGeneralExpense geGeneralExpense = geGeneralExpenseService.getById(id);
        AssertUtils.asserts(geGeneralExpense != null, FsscErrorType.DOCUMENT_NOT_FIND);
        GeGeneralExpenseVo geGeneralExpenseVo = new BeanUtils<GeGeneralExpenseVo>()
                .copyObj(geGeneralExpense, GeGeneralExpenseVo.class);
        String documentType = GeExpenseBorrowPrepay.DOCUMENT_TYPE;
        String documentId = GeExpenseBorrowPrepay.DOCUMENT_ID;

        //工资卡 和
        QueryWrapper<BmBorrowBank> salaryQueryWrapper = new QueryWrapper<>();
        salaryQueryWrapper.eq(BmBorrowBank.DOCUMENT_ID, id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())
                .eq("GET_OR_RETURN", "SALARY");
        List<BmBorrowBank> bmBorrowBanks = bmBorrowBankService.list(salaryQueryWrapper);
        geGeneralExpenseVo.setSalaryCards(new BeanUtils<BmBorrowBankVo>().copyObjs(bmBorrowBanks, BmBorrowBankVo.class));

        //公务卡
        QueryWrapper<BmBorrowBank> businessQueryWrapper = new QueryWrapper<>();
        businessQueryWrapper.eq(BmBorrowBank.DOCUMENT_ID, id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())
                .eq("GET_OR_RETURN", "BUSINESS");
        List<BmBorrowBank> business = bmBorrowBankService.list(businessQueryWrapper);
        geGeneralExpenseVo.setBusinessCards(new BeanUtils<BmBorrowBankVo>().copyObjs(business, BmBorrowBankVo.class));


        //查询对公付款清单
        QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper = new QueryWrapper<>();
        paymentListQueryWrapper.eq(BmBorrowBank.DOCUMENT_TYPE, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue()).eq(documentId, id);
        geGeneralExpenseVo.setGeExpensePaymentList(new BeanUtils<GeExpensePaymentListVo>()
                .copyObjs(paymentListService.list(paymentListQueryWrapper), GeExpensePaymentListVo.class));
        //查询关联付款借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq(documentType, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue()).eq(documentId, id);
        List<GeExpenseBorrowPrepay> borrowPrepays = prepayService.list(prepayQueryWrapper);
        prepayService.paddingHexiaomingxi(borrowPrepays);
        geGeneralExpenseVo.setBorrowPrepayVoList(new BeanUtils<GeExpenseBorrowPrepayVo>()
                .copyObjs(borrowPrepays, GeExpenseBorrowPrepayVo.class));

        //查询行明细
        QueryWrapper<GeGeneralExpenseLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(documentId, id);
        geGeneralExpenseVo.setGeneralExpenseLines(new BeanUtils<GeGeneralExpenseLineVo>()
                .copyObjs(lineService.list(lineQueryWrapper), GeGeneralExpenseLineVo.class));
        //查询审批历史
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
            geGeneralExpenseVo.setBpmProcessTaskVos(history);
        } catch (FSSCException e) {
            log.error(e.getMessage());
        }
        //todo 预制凭证
        return new Result<GeGeneralExpenseVo>().sucess(geGeneralExpenseVo);
    }


    @ApiOperation(value = "分页查询GeGeneralExpense", notes = "根据条件查询GeGeneralExpense分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<GeGeneralExpenseVo>> search(@ApiParam(name = "geGeneralExpenseQueryForm", value = "GeGeneralExpense查询参数", required = true)
                                                            GeGeneralExpenseQueryForm geGeneralExpenseQueryForm) {
        log.info("search with geGeneralExpenseQueryForm:{}", geGeneralExpenseQueryForm.toString());
        IPage<GeGeneralExpense> geGeneralExpensePage = geGeneralExpenseService.selectPage(geGeneralExpenseQueryForm);
        IPage<GeGeneralExpenseVo> geGeneralExpenseVoPage = new BeanUtils<GeGeneralExpenseVo>()
                .copyPageObjs(geGeneralExpensePage, GeGeneralExpenseVo.class);
        return Result.success(geGeneralExpenseVoPage);
    }


    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(GeGeneralExpenseQueryForm geGeneralExpenseQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(geGeneralExpenseQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<GeGeneralExpense> records = geGeneralExpenseService.selectPage(geGeneralExpenseQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "支出大类", "归属单位", "归属部门", "项目"
                , "报销金额", "创建日期", "单据状态", "付款状态"};
        String fileName = "通用报账单信息列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            GeGeneralExpense vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getMainTypeName();
            content[i][4] = vo.getUnitName();
            content[i][5] = vo.getDeptName();
            content[i][6] = vo.getProjectName();

            content[i][7] = vo.getExpenseAmount() == null ? "0" : vo.getExpenseAmount().toString();
            if (vo.getCreateTime() != null) {
                content[i][8] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][9] = docStatusMap.get(vo.getDocumentStatus());
            content[i][10] = payStatusMap.get(vo.getPayStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }


    /**
     * 查询借款单 对通用报账单提供接口
     *
     * @return
     */
    @ApiOperation(value = "查询借款单或对公预付款单", notes = "查询借款单或对公预付款单")
    @GetMapping(value = "findBorrowList")
    @ResponseBody
    public Result<List<BorrowPrepayListVo>> findBorrowList(@Valid GeExpenseBorrowPrepayQueryForm form) {
        List<BorrowPrepayListVo> borrowPrepayList
                = geGeneralExpenseService.findBorrowPrepayList(form);
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAYMENT_TYPE.getValue()));
        if(CollectionUtils.isNotEmpty(borrowPrepayList)){
            for (BorrowPrepayListVo vo:borrowPrepayList){
                vo.setPaymentTypeText(payStatusMap.get(vo.getPaymentType()));
                QueryWrapper<PyPamentOrderInfo>  pyWrapper=new QueryWrapper<>();
                pyWrapper.eq("PAY_DOCUMENT_NUM",vo.getConnectDocumentNum());
                PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(pyWrapper);
                vo.setBankAccount(pyPamentOrderInfo.getBankAccount());
                vo.setBankType(pyPamentOrderInfo.getBankType());
            }
        }
        return Result.success(borrowPrepayList);
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
        Result<GeGeneralExpenseVo> resultData = get(id);
        GeGeneralExpenseVo geGeneralExpense = resultData.getData();
            String documentStatus = geGeneralExpense.getDocumentStatus();
            String documentNum=geGeneralExpense.getDocumentNum();
            if(pyPamentOrderInfoService.selectCount(documentNum)){
                QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
                PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(queryWrapper);
                if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                    throw new FSSCException(FsscErrorType.DOCUMENT_READY_PAYMENT);
                }
            }
            BigDecimal noPaidMount = geGeneralExpense.getNoPaidAmount();
            if(noPaidMount==null){
                noPaidMount = BigDecimal.ZERO;
            }
            BigDecimal zero = BigDecimal.ZERO;
            if (FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus) && noPaidMount.compareTo(zero) != 0) {
                PyPamentOrderInfoVo pamentOrderInfoVo=new PyPamentOrderInfoVo();
                pamentOrderInfoVo.setDocumentStatus(geGeneralExpense.getDocumentStatus());
               // pamentOrderInfoVo.setDocumentNum();
               // pamentOrderInfoVo.setBankAccount();
                //pamentOrderInfoVo.setBankType();
                pamentOrderInfoVo.setCost(geGeneralExpense.getCost());
                pamentOrderInfoVo.setCreateBy(geGeneralExpense.getCreateBy());
                pamentOrderInfoVo.setCreateTime(geGeneralExpense.getCreateTime());
                pamentOrderInfoVo.setCreateUserName(geGeneralExpense.getCreateUserName());
                pamentOrderInfoVo.setCurrencyCode(geGeneralExpense.getCurrencyCode());
                pamentOrderInfoVo.setDeptCode(geGeneralExpense.getDeptCode());
                pamentOrderInfoVo.setDeptId(geGeneralExpense.getDeptId());
                pamentOrderInfoVo.setDeptName(geGeneralExpense.getDeptName());
                pamentOrderInfoVo.setIsAfterPatch(geGeneralExpense.getIsAfterPatch());
                pamentOrderInfoVo.setNoPaidAmount(geGeneralExpense.getNoPaidAmount());
                pamentOrderInfoVo.setPayDocumentNum(geGeneralExpense.getDocumentNum());
                pamentOrderInfoVo.setPayDocumentType("GE_GENERAL_EXPENSE");
                pamentOrderInfoVo.setPaidAmount(geGeneralExpense.getPaidAmount());
                pamentOrderInfoVo.setPaymentType(geGeneralExpense.getPaymentType());
                pamentOrderInfoVo.setPayStatus(geGeneralExpense.getPayStatus());
                pamentOrderInfoVo.setRemark(geGeneralExpense.getRemark());
                pamentOrderInfoVo.setTotalAmount(geGeneralExpense.getTotalAmount());
                pamentOrderInfoVo.setTotalAmountOtherCurrency(geGeneralExpense.getTotalAmountOtherCurrency());
                pamentOrderInfoVo.setUnitCode(geGeneralExpense.getUnitCode());
                pamentOrderInfoVo.setUnitId(geGeneralExpense.getUnitId());
                pamentOrderInfoVo.setUnitName(geGeneralExpense.getUnitName());
                pamentOrderInfoVo.setUpdateTime(geGeneralExpense.getUpdateTime());
                pamentOrderInfoVo.setUpdateBy(geGeneralExpense.getUpdateBy());
                pamentOrderInfoVo.setVersion(geGeneralExpense.getVersion());
                 List<BmBorrowBankVo> salaryCards=geGeneralExpense.getSalaryCards();

                 if(CollectionUtils.isNotEmpty(salaryCards)){
                     List<PyPamentBusinessLineVo> pyPamentPrivateLineVos=new ArrayList<PyPamentBusinessLineVo>();
                     for (BmBorrowBankVo bmBorrowBankVo:salaryCards) {
                         PyPamentBusinessLineVo pyPamentBusinessLineVo=new PyPamentBusinessLineVo();
                         pyPamentBusinessLineVo.setBankAccount(bmBorrowBankVo.getBankAccount());
                        // pyPamentBusinessLineVo.setBankInternationalCode();
                         pyPamentBusinessLineVo.setBankName(bmBorrowBankVo.getBankName());
                         //pyPamentBusinessLineVo.setBankReturnInfo();
                         //pyPamentBusinessLineVo.setBranchBankName();
                         pyPamentBusinessLineVo.setBusinessCardNum(bmBorrowBankVo.getBankAccount());
                         //pyPamentBusinessLineVo.setCommonCreditCode();
                         pyPamentBusinessLineVo.setConnectDocumentId(geGeneralExpense.getId());
                         pyPamentBusinessLineVo.setConnectDocumentNum(geGeneralExpense.getDocumentNum());
                         pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                         pyPamentBusinessLineVo.setLineType("PRIVATE");
                         pyPamentBusinessLineVo.setConnectDocumentType("GE_GENERAL_EXPENSE");
                         pyPamentBusinessLineVo.setCreateBy(bmBorrowBankVo.getCreateBy());
                         pyPamentBusinessLineVo.setCreateTime(bmBorrowBankVo.getCreateTime());
                         pyPamentBusinessLineVo.setCreateUserName(bmBorrowBankVo.getCreateUserName());
                         pyPamentBusinessLineVo.setEmpNo(bmBorrowBankVo.getEmpNo());
                         pyPamentBusinessLineVo.setInterBranchNumber(bmBorrowBankVo.getInterBranchNumber());
                        // pyPamentBusinessLineVo.setLineNumber();
                         pyPamentBusinessLineVo.setPayAmount(bmBorrowBankVo.getBorrowAmount());
                         pyPamentBusinessLineVo.setPayStatus(bmBorrowBankVo.getPayStatus());
                         pyPamentBusinessLineVo.setRecieveEmpName(bmBorrowBankVo.getCreateUserName());
                         pyPamentBusinessLineVo.setRecieveId(bmBorrowBankVo.getCreateBy());
                         pyPamentBusinessLineVo.setTransactionAmount(bmBorrowBankVo.getTransactionAmount());
                         //pyPamentBusinessLineVo.setTransactionComments();
                         pyPamentBusinessLineVo.setTransactionDate(bmBorrowBankVo.getPayTime());
                         pyPamentBusinessLineVo.setUnitId(geGeneralExpense.getUnitId());
                         pyPamentBusinessLineVo.setUnitName(geGeneralExpense.getUnitName());
                         pyPamentBusinessLineVo.setUpdateBy(geGeneralExpense.getUpdateBy());
                         pyPamentBusinessLineVo.setUpdateTime(geGeneralExpense.getUpdateTime());
                         pyPamentBusinessLineVo.setVersion(geGeneralExpense.getVersion());
                         pyPamentBusinessLineVo.setConnectDocumentTypeName("通用报账单");
                         pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                         pyPamentPrivateLineVos.add(pyPamentBusinessLineVo);
                     }
                     pamentOrderInfoVo.setPyPamentPrivateLineVos(pyPamentPrivateLineVos);
                 }


                 List<BmBorrowBankVo> businessCards=geGeneralExpense.getBusinessCards();
                 if(CollectionUtils.isNotEmpty(businessCards)){
                     List<PyPamentBusinessLineVo> pyPamentBusinessLineVos =new ArrayList<PyPamentBusinessLineVo>();
                     for (BmBorrowBankVo bmBorrowBankVo:businessCards) {
                         PyPamentBusinessLineVo pyPamentBusinessLineVo=new PyPamentBusinessLineVo();
                         pyPamentBusinessLineVo.setBankAccount(bmBorrowBankVo.getBankAccount());
                         // pyPamentBusinessLineVo.setBankInternationalCode();
                         pyPamentBusinessLineVo.setBankName(bmBorrowBankVo.getBankName());
                         //pyPamentBusinessLineVo.setBankReturnInfo();
                         //pyPamentBusinessLineVo.setBranchBankName();
                         pyPamentBusinessLineVo.setBusinessCardNum(bmBorrowBankVo.getBankAccount());
                         //pyPamentBusinessLineVo.setCommonCreditCode();
                         pyPamentBusinessLineVo.setConnectDocumentId(geGeneralExpense.getId());
                         pyPamentBusinessLineVo.setConnectDocumentNum(geGeneralExpense.getDocumentNum());
                         pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                         pyPamentBusinessLineVo.setLineType("BUSINESS_CARD");
                         pyPamentBusinessLineVo.setConnectDocumentType("GE_GENERAL_EXPENSE");
                         pyPamentBusinessLineVo.setCreateBy(bmBorrowBankVo.getCreateBy());
                         pyPamentBusinessLineVo.setCreateTime(bmBorrowBankVo.getCreateTime());
                         pyPamentBusinessLineVo.setCreateUserName(bmBorrowBankVo.getCreateUserName());
                         pyPamentBusinessLineVo.setEmpNo(bmBorrowBankVo.getEmpNo());
                         pyPamentBusinessLineVo.setInterBranchNumber(bmBorrowBankVo.getInterBranchNumber());
                         // pyPamentBusinessLineVo.setLineNumber();
                         pyPamentBusinessLineVo.setPayAmount(bmBorrowBankVo.getBorrowAmount());
                         pyPamentBusinessLineVo.setPayStatus(bmBorrowBankVo.getPayStatus());
                         pyPamentBusinessLineVo.setRecieveEmpName(bmBorrowBankVo.getCreateUserName());
                         pyPamentBusinessLineVo.setRecieveId(bmBorrowBankVo.getCreateBy());
                         pyPamentBusinessLineVo.setTransactionAmount(bmBorrowBankVo.getTransactionAmount());
                         //pyPamentBusinessLineVo.setTransactionComments();
                         pyPamentBusinessLineVo.setTransactionDate(bmBorrowBankVo.getPayTime());
                         pyPamentBusinessLineVo.setUnitId(geGeneralExpense.getUnitId());
                         pyPamentBusinessLineVo.setUnitName(geGeneralExpense.getUnitName());
                         pyPamentBusinessLineVo.setUpdateBy(geGeneralExpense.getUpdateBy());
                         pyPamentBusinessLineVo.setUpdateTime(geGeneralExpense.getUpdateTime());
                         pyPamentBusinessLineVo.setVersion(geGeneralExpense.getVersion());
                         pyPamentBusinessLineVo.setConnectDocumentTypeName("通用报账单");
                         pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                         pyPamentBusinessLineVos.add(pyPamentBusinessLineVo);
                     }
                     pamentOrderInfoVo.setPyPamentBusinessLineVos(pyPamentBusinessLineVos);
                 }
                 List<GeExpensePaymentListVo> geExpensePaymentList=geGeneralExpense.getGeExpensePaymentList();
                if(CollectionUtils.isNotEmpty(geExpensePaymentList)){
                    List<PyPamentBusinessLineVo> pyPamentPublicLineVos=new ArrayList<PyPamentBusinessLineVo>();
                    for(GeExpensePaymentListVo geExpensePaymentListVo:geExpensePaymentList){
                        PyPamentBusinessLineVo pyPamentBusinessLineVo=new PyPamentBusinessLineVo();
                        pyPamentBusinessLineVo.setBankAccount(geExpensePaymentListVo.getBankAccount());
                        pyPamentBusinessLineVo.setBankId(geExpensePaymentListVo.getBankUnitId());
                        // pyPamentBusinessLineVo.setBankInternationalCode();
                        pyPamentBusinessLineVo.setBankName(geExpensePaymentListVo.getBankName());
                        //pyPamentBusinessLineVo.setBankReturnInfo();
                        //pyPamentBusinessLineVo.setBranchBankName();
                        pyPamentBusinessLineVo.setBusinessCardNum(geExpensePaymentListVo.getBankAccount());
                        pyPamentBusinessLineVo.setCommonCreditCode(geExpensePaymentListVo.getCommonCreditCode());
                        pyPamentBusinessLineVo.setConnectDocumentId(geGeneralExpense.getId());
                        pyPamentBusinessLineVo.setConnectDocumentNum(geGeneralExpense.getDocumentNum());
                        pyPamentBusinessLineVo.setConnectNumber(geExpensePaymentListVo.getLineNumber());
                        pyPamentBusinessLineVo.setLineType("PUBLIC");
                        pyPamentBusinessLineVo.setConnectDocumentType("GE_GENERAL_EXPENSE");
                        pyPamentBusinessLineVo.setCreateBy(geExpensePaymentListVo.getCreateBy());
                        pyPamentBusinessLineVo.setCreateTime(geExpensePaymentListVo.getCreateTime());
                        pyPamentBusinessLineVo.setCreateUserName(geExpensePaymentListVo.getCreateUserName());
                     //   pyPamentBusinessLineVo.setEmpNo(geExpensePaymentListVo.getEmpNo());
                        pyPamentBusinessLineVo.setInterBranchNumber(geExpensePaymentListVo.getInterBranchNumber());
                        // pyPamentBusinessLineVo.setLineNumber();
                        pyPamentBusinessLineVo.setPayAmount(geExpensePaymentListVo.getPayAmount());
                        pyPamentBusinessLineVo.setPayStatus(geExpensePaymentListVo.getPayStatus());
                        pyPamentBusinessLineVo.setRecieveEmpName(geGeneralExpense.getCreateUserName());
                        pyPamentBusinessLineVo.setRecieveId(geGeneralExpense.getCreateBy());
                       // pyPamentBusinessLineVo.setTransactionAmount(geExpensePaymentListVo.getTransactionAmount());
                        //pyPamentBusinessLineVo.setTransactionComments();
                        pyPamentBusinessLineVo.setTransactionDate(geExpensePaymentListVo.getCreateTime());
                        pyPamentBusinessLineVo.setUnitId(geGeneralExpense.getUnitId());
                        pyPamentBusinessLineVo.setUnitName(geGeneralExpense.getUnitName());
                        pyPamentBusinessLineVo.setUpdateBy(geGeneralExpense.getUpdateBy());
                        pyPamentBusinessLineVo.setUpdateTime(geGeneralExpense.getUpdateTime());
                        pyPamentBusinessLineVo.setVersion(geGeneralExpense.getVersion());
                        pyPamentBusinessLineVo.setConnectDocumentTypeName("通用报账单");
                        pyPamentBusinessLineVo.setConnectDocumentIdLine(geExpensePaymentListVo.getId());
                        pyPamentPublicLineVos.add(pyPamentBusinessLineVo);
                    }
                    pamentOrderInfoVo.setPyPamentPublicLineVos(pyPamentPublicLineVos);
                }
                return Result.success(pamentOrderInfoVo);
        }
        throw new FSSCException(FsscErrorType.NOT_GENERATE_BILL_PAYMENT);
    }

}



