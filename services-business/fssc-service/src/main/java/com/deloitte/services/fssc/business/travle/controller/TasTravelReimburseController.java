package com.deloitte.services.fssc.business.travle.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyInfoVo;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyLineVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.fssc.travle.param.TasTravelApplyInfoQueryForm;
import com.deloitte.platform.api.fssc.travle.param.TasTravelReimburseQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelLineForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelLineVo;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelReimburseForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelReimburseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyLineService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.business.travle.entity.TasTravelLine;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelLineService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelTimeService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description :   TasTravelReimburse控制器实现类
 * @Modified :
 */
@Api(tags = "差旅报账操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/travle/travel-reimburse")
public class TasTravelReimburseController {

    @Autowired
    public ITasTravelReimburseService tasTravelReimburseService;

    @Autowired
    public ITasTravelLineService tasTravelLineService;
    @Autowired
    private IBudgetProjectService budgetProjectService;
    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;

    @Autowired
    private IGeExpensePaymentListService paymentListService;

    @Autowired
    private IBmBorrowBankService bmBorrowBankService;

    @Autowired
    public ITasTravelTimeService tasTravelTimeService;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;
    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    public IBorrowMoneyInfoService borrowMoneyInfoService;

    @Autowired
    private IBorrowMoneyLineService borrowMoneyLineService;

    @Autowired
    private IFileService fileService;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @Autowired
    public ITasTravelApplyInfoService tasTravleApplyInfoService;
    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;


    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "新增或修改", notes = "新增或修改")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ResponseBody
    @Transactional
    public Result addOrUpdate(@RequestBody @Valid TasTravelReimburseForm tasTravelReimburseForm) {
        log.info("tasTravelReimburseForm:" + tasTravelReimburseForm);
        return saveAddOrUpdate(tasTravelReimburseForm);
    }

    public Result saveAddOrUpdate(TasTravelReimburseForm tasTravelReimburseForm) {

        //验证单据是否存在
        FsscCommonUtil.valiHasData(tasTravelReimburseForm.getId()
                , FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());

        validCarPayContact(tasTravelReimburseForm);
        TasTravelReimburse tasTravelReimburse = new BeanUtils<TasTravelReimburse>()
                .copyObj(tasTravelReimburseForm, TasTravelReimburse.class);

        Long projectId = tasTravelReimburse.getProjectId();
        if (projectId != null) {
            BudgetProject project = budgetProjectService.getById(projectId);
            if (project != null) {
                tasTravelReimburse.setProjectCode(project.getProjectCode());
                tasTravelReimburse.setProjectName(project.getProjectName());
                tasTravelReimburse.setProjectUnitCode(project.getResponsibleUnitCode());
                tasTravelReimburse.setProjectUnitName(project.getResponsibleUnitName());
                tasTravelReimburse.setFsscCode(project.getFsscCode());
            }
        }

        //验证应校验报销金额
        vaildExpenseAmount(tasTravelReimburseForm);
        boolean save = tasTravelReimburseService.saveOrUpdate(tasTravelReimburse);
        //修改关联的差旅申请锁定状态
        changeTravelIsConnect(tasTravelReimburseForm.getId(), tasTravelReimburseForm.getApplyForId());
        AssertUtils.asserts(save, FsscErrorType.DATA_IS_NOT_LATEST);
        if (save) {
            //行信息
            QueryWrapper<TasTravelLine> lineWrapper = new QueryWrapper<>();
            lineWrapper.eq("DOCUMENT_ID", tasTravelReimburse.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            List<TasTravelLineForm> tasTravelLineFormList = tasTravelReimburseForm.getTasTravelLineForm();
            if (CollectionUtils.isNotEmpty(tasTravelLineFormList)) {
                List<TasTravelLine> tasTravelLineList = new BeanUtils<TasTravelLine>().copyObjs(tasTravelLineFormList, TasTravelLine.class);
                List<Long> ids = tasTravelLineFormList.stream().map(pk -> pk.getId()).collect(Collectors.toList());
                ids.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(ids)) {
                    lineWrapper.notIn("id", ids);
                }
                tasTravelLineService.remove(lineWrapper);

                //核销金额填充
                BigDecimal verAmountBusiness = BigDecimalUtil.convert(tasTravelReimburseForm.getVerAmountBusiness());//核销金额公务卡
                BigDecimal verAmountSarlary = BigDecimalUtil.convert(tasTravelReimburseForm.getVerAmountSalary());//核销金额工资卡
                BigDecimal verAmountPublic = BigDecimalUtil.convert(tasTravelReimburseForm.getVerAmountExpense());//核销金额对公付款
                for (TasTravelLine tasTravelLine : tasTravelLineList) {
                    tasTravelLine.setDocumentId(tasTravelReimburse.getId());
                    tasTravelLine.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
                    BigDecimal invoiceAmount = BigDecimalUtil.convert(tasTravelLine.getTotalForehead());
                    String paymentType = tasTravelLine.getPaymentType();

                    if ("CASH".equals(paymentType) || "SALARY_CARD".equals(paymentType)) {
                        if (verAmountSarlary.compareTo(invoiceAmount) >= 0) {
                            tasTravelLine.setHasVerAmount(invoiceAmount);

                            verAmountSarlary = verAmountSarlary.subtract(invoiceAmount);
                        } else {
                            tasTravelLine.setHasVerAmount(verAmountSarlary);
                            verAmountSarlary = BigDecimal.ZERO;//当核销完后设为0，来用于后面的循环值
                        }
                    }
                    if ("BUSINESS_CARD".equals(paymentType)) {
                        if (verAmountBusiness.compareTo(invoiceAmount) >= 0) {
                            tasTravelLine.setHasVerAmount(invoiceAmount);
                            verAmountBusiness = verAmountBusiness.subtract(invoiceAmount);
                        } else {
                            tasTravelLine.setHasVerAmount(verAmountBusiness);
                            verAmountBusiness = BigDecimal.ZERO;
                        }
                    }
                    if ("LIMIT_CHECK".equals(paymentType) || "PUBLIC_PAYMENT".equals(paymentType)) {
                        if (verAmountPublic.compareTo(invoiceAmount) >= 0) {
                            tasTravelLine.setHasVerAmount(invoiceAmount);
                            verAmountPublic = verAmountPublic.subtract(invoiceAmount);
                        } else {
                            tasTravelLine.setHasVerAmount(verAmountPublic);
                            verAmountPublic = BigDecimal.ZERO;
                        }
                    }
                    tasTravelLine.setNoVerAmount(invoiceAmount.subtract(BigDecimalUtil.convert(tasTravelLine.getHasVerAmount())));
                }
                if (CollectionUtils.isNotEmpty(tasTravelLineList)) {
                    tasTravelLineService.saveOrUpdateBatch(tasTravelLineList);
                }
                /*for (TasTravelLineForm tasTravelLineForm:tasTravelLineFormList) {
                    TasTravelLine tasTravelLine=new BeanUtils<TasTravelLine>().copyObj(tasTravelLineForm,TasTravelLine.class);
                    tasTravelLine.setDocumentId(tasTravelReimburse.getId());
                    tasTravelLine.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
                    tasTravelLineService.saveOrUpdate(tasTravelLine);
                    QueryWrapper<TasTravelTime>  timeQueryWrapperWrapper=new QueryWrapper<>();
                    timeQueryWrapperWrapper.eq("TRAVEL_LINE_ID", tasTravelLine.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_TRAVEL_LINE.getValue());
                    List<TasTravelTimeForm> tasTravelTimeForms=tasTravelLineForm.getTasTravelTimeForms();
                    List<Long> longLists = tasTravelTimeForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                    longLists.removeAll(Collections.singleton(null));
                    if(CollectionUtils.isNotEmpty(longLists)) {
                        timeQueryWrapperWrapper.notIn("id", longLists);
                    }
                    tasTravelTimeService.remove(timeQueryWrapperWrapper);
                    List<TasTravelTime> tasTravelTimes= new BeanUtils<TasTravelTime>().copyObjs(tasTravelTimeForms, TasTravelTime.class);
                    for (TasTravelTime time:tasTravelTimes) {
                        time.setTravelLineId(tasTravelLine.getId());
                        time.setDocumentType(FsscTableNameEums.TAS_TRAVEL_LINE.getValue());
                    }
                    tasTravelTimeService.saveOrUpdateBatch(tasTravelTimes);
                }*/
            } else {
                tasTravelLineService.remove(lineWrapper);
            }
            //关联借款或预付款表
            QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper = new QueryWrapper<>();
            borrowPrepayQueryWrapper.eq("DOCUMENT_ID", tasTravelReimburse.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            List<GeExpenseBorrowPrepayForm> geExpenseBorrowPrepayForms = tasTravelReimburseForm.getBorrowPrepayFormList();
            if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepayForms)) {
                List<Long> listIds = geExpenseBorrowPrepayForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                listIds.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(listIds)) {
                    borrowPrepayQueryWrapper.notIn("id", listIds);
                }
                prepayService.remove(borrowPrepayQueryWrapper);
                List<GeExpenseBorrowPrepay> geExpenseBorrowPrepayList = new BeanUtils<GeExpenseBorrowPrepay>().copyObjs(geExpenseBorrowPrepayForms, GeExpenseBorrowPrepay.class);
                for (GeExpenseBorrowPrepay geExpenseBorrowPrepay : geExpenseBorrowPrepayList) {
                    geExpenseBorrowPrepay.setDocumentId(tasTravelReimburse.getId());
                    geExpenseBorrowPrepay.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());

                    Long borrowOrPrepayId = geExpenseBorrowPrepay.getBorrowOrPrepayId();
                    String budgetAccountCode = prepayService.getPaymentOrderBudgetAccountCode(borrowOrPrepayId);
                    geExpenseBorrowPrepay.setPaymentBudgetAccountCode(budgetAccountCode);
                }
                if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepayList)) {
                    prepayService.saveOrUpdateBatch(geExpenseBorrowPrepayList);
                }

            } else {
                prepayService.remove(borrowPrepayQueryWrapper);
            }
            //工资卡 公务卡 保存 先删除 再新增
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq(BmBorrowBank.DOCUMENT_ID, tasTravelReimburse.getId())
                    .eq(BmBorrowBank.DOCUMENT_TYPE, FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            List<BmBorrowBankForm> bmBorrowBankForms = tasTravelReimburseForm.getBankFormsList();
            List<Long> longList = bmBorrowBankForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));
            if (CollectionUtils.isNotEmpty(longList)) {
                bankQueryWrapper.notIn(BmBorrowBank.ID, longList);
            }
            bmBorrowBankService.remove(bankQueryWrapper);
            List<BmBorrowBank> bmBorrowBanks = new BeanUtils<BmBorrowBank>().copyObjs(bmBorrowBankForms, BmBorrowBank.class);
            for (BmBorrowBank bank : bmBorrowBanks) {
                bank.setDocumentNum(tasTravelReimburse.getDocumentNum());
                bank.setDocumentId(tasTravelReimburse.getId());
                bank.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            }
            if (CollectionUtils.isNotEmpty(bmBorrowBanks)) {
                bmBorrowBankService.saveOrUpdateBatch(bmBorrowBanks);
            }

           /* //保存借款预付款
            List<GeExpenseBorrowPrepay> geExpenseBorrowPrepays = new BeanUtils<GeExpenseBorrowPrepay>()
                    .copyObjs(tasTravelReimburseForm.getBorrowPrepayFormList(), GeExpenseBorrowPrepay.class);
            //先删除差集的行
            QueryWrapper<GeExpenseBorrowPrepay> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID,tasTravelReimburse.getId())
                    .eq(GeExpenseBorrowPrepay.DOCUMENT_TYPE,FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            List<Long> cuLineIds = geExpenseBorrowPrepays.stream().map(k ->  k.getId()).collect(Collectors.toList());
            cuLineIds.removeAll(Collections.singleton(null));
            if(CollectionUtils.isNotEmpty(cuLineIds)) {
                queryWrapper.notIn(GeExpenseBorrowPrepay.ID,cuLineIds);
            }
            prepayService.remove(queryWrapper);
            for (GeExpenseBorrowPrepay prepay:geExpenseBorrowPrepays){
                prepay.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
                prepay.setDocumentId(tasTravelReimburse.getId());
            }
            if(CollectionUtils.isNotEmpty(geExpenseBorrowPrepays)){
                prepayService.saveOrUpdateBatch(geExpenseBorrowPrepays);
            }*/

            //保存对公付款清单
            List<GeExpensePaymentList> geExpensePaymentLists = new BeanUtils<GeExpensePaymentList>()
                    .copyObjs(tasTravelReimburseForm.getGeExpensePaymentList(), GeExpensePaymentList.class);
            //先删除差集的行
            QueryWrapper<GeExpensePaymentList> gePaymentQueryWrapper = new QueryWrapper<>();
            gePaymentQueryWrapper.eq(GeExpensePaymentList.DOCUMENT_ID, tasTravelReimburse.getId())
                    .eq(GeExpensePaymentList.DOCUMENT_TYPE, FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            List<Long> lineIds = geExpensePaymentLists.stream().map(k -> k.getId()).collect(Collectors.toList());
            lineIds.removeAll(Collections.singleton(null));
            if (CollectionUtils.isNotEmpty(lineIds)) {
                gePaymentQueryWrapper.notIn(GeExpensePaymentList.ID, lineIds);
            }
            paymentListService.remove(gePaymentQueryWrapper);
            for (GeExpensePaymentList paymentList : geExpensePaymentLists) {
                paymentList.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
                paymentList.setDocumentId(tasTravelReimburse.getId());
            }
            if (CollectionUtils.isNotEmpty(geExpensePaymentLists)) {
                paymentListService.saveOrUpdateBatch(geExpensePaymentLists);
            }
            //文件列表保存
            List<Long> fileIds = tasTravelReimburseForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, tasTravelReimburse.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                AssertUtils.asserts(CollectionUtils.isNotEmpty(files),
                        FsscErrorType.FILE_IS_NULL);
                files.stream().forEach(ka -> ka.setDocumentId(tasTravelReimburse.getId()));
                if (CollectionUtils.isNotEmpty(files)) {
                    fileService.saveOrUpdateBatch(files);
                }

            }
            return Result.success(new BeanUtils<TasTravelReimburseVo>().copyObj(tasTravelReimburseService.getById(tasTravelReimburse.getId()), TasTravelReimburseVo.class));
        }
        throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
    }

    public void vaildExpenseAmount(TasTravelReimburseForm tasTravelReimburseForm) {
        //报销总金额
        BigDecimal expenseAmount = tasTravelReimburseForm.getExpenseAmount();
        String paymentType = tasTravelReimburseForm.getPaymentType();
        //借款预付款
        List<GeExpenseBorrowPrepayForm> borrowPrepayFormList = tasTravelReimburseForm.getBorrowPrepayFormList();
        //本次核销金额
        BigDecimal bcVerAmount = BigDecimal.ZERO;
        //对公付款
        List<GeExpensePaymentListForm> geExpensePaymentList = tasTravelReimburseForm.getGeExpensePaymentList();
        //付款金额
        BigDecimal geFkAmount = BigDecimal.ZERO;
        //工资卡公务卡
        List<BmBorrowBankForm> bankFormsList = tasTravelReimburseForm.getBankFormsList();
        // 工资卡付款金额
        BigDecimal fkAmount = BigDecimal.ZERO;
        //公务卡 还款金额
        BigDecimal hkAmount = BigDecimal.ZERO;

        //混合方式
        if ("MIX".equals(paymentType)) {
            //借款预付款
            if (CollectionUtils.isNotEmpty(borrowPrepayFormList)) {
                for (GeExpenseBorrowPrepayForm geExpenseBorrowPrepayForm : borrowPrepayFormList) {
                    bcVerAmount = bcVerAmount.add(BigDecimalUtil.convert(geExpenseBorrowPrepayForm.getCurrentVerAmount()));
                }
            }
            //对公付款信息
            if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                for (GeExpensePaymentListForm geExpensePaymentListForm : geExpensePaymentList) {
                    geFkAmount = geFkAmount.add(BigDecimalUtil.convert(geExpensePaymentListForm.getPayAmount()));
                }
            }
            if (CollectionUtils.isNotEmpty(bankFormsList)) {
                for (BmBorrowBankForm bmBorrowBankForm : bankFormsList) {
                    if ("SALARY".equals(bmBorrowBankForm.getGetOrReturn())) {
                        fkAmount = fkAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                    }
                    if ("BUSINESS".equals(bmBorrowBankForm.getGetOrReturn())) {
                        hkAmount = hkAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                        BigDecimal tAmount = BigDecimalUtil.convert(bmBorrowBankForm.getTransactionAmount());
                        AssertUtils.asserts(hkAmount.compareTo(tAmount) <= 0,
                                FsscErrorType.BUSINESS_BORROW_AMOUNT_MUST_TH_TRAMOUNT);
                    }
                }
            }
            AssertUtils.asserts(expenseAmount.compareTo(bcVerAmount.add(geFkAmount.add(fkAmount.add(hkAmount)))) == 0, FsscErrorType.FIX_DOCUMENT_MONERY_RIGHT);
        }
        //对公支付
        if ("PUBLIC_PAYMENT".equals(paymentType)) {
            //借款预付款
            if (CollectionUtils.isNotEmpty(borrowPrepayFormList)) {
                for (GeExpenseBorrowPrepayForm geExpenseBorrowPrepayForm : borrowPrepayFormList) {
                    bcVerAmount = bcVerAmount.add(BigDecimalUtil.convert(geExpenseBorrowPrepayForm.getCurrentVerAmount()));
                }
            }
            //对公付款
            if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                for (GeExpensePaymentListForm geExpensePaymentListForm : geExpensePaymentList) {
                    geFkAmount = geFkAmount.add(BigDecimalUtil.convert(geExpensePaymentListForm.getPayAmount()));
                }
            }
            AssertUtils.asserts(expenseAmount.compareTo(bcVerAmount.add(geFkAmount)) == 0, FsscErrorType.PUBLIC_PAYMENT_DOCUMENT_MONERY_RIGHT);
        }
        //支票
        if ("CHECK".equals(paymentType)) {
            //借款预付款
            if (CollectionUtils.isNotEmpty(borrowPrepayFormList)) {
                for (GeExpenseBorrowPrepayForm geExpenseBorrowPrepayForm : borrowPrepayFormList) {
                    bcVerAmount = bcVerAmount.add(BigDecimalUtil.convert(geExpenseBorrowPrepayForm.getCurrentVerAmount()));
                }
            }

            AssertUtils.asserts(expenseAmount.compareTo(bcVerAmount) == 0, FsscErrorType.PUBLIC_PAYMENT_DOCUMENT_MONERY_RIGHT);
        }
        //转账
        if ("TRANSFER".equals(paymentType)) {
            //借款预付款
            if (CollectionUtils.isNotEmpty(borrowPrepayFormList)) {
                for (GeExpenseBorrowPrepayForm geExpenseBorrowPrepayForm : borrowPrepayFormList) {
                    bcVerAmount = bcVerAmount.add(BigDecimalUtil.convert(geExpenseBorrowPrepayForm.getCurrentVerAmount()));
                }
            }
            if (CollectionUtils.isNotEmpty(bankFormsList)) {
                for (BmBorrowBankForm bmBorrowBankForm : bankFormsList) {
                    if ("SALARY".equals(bmBorrowBankForm.getGetOrReturn())) {
                        fkAmount = fkAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                    }
                    if ("BUSINESS".equals(bmBorrowBankForm.getGetOrReturn())) {
                        hkAmount = hkAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                        BigDecimal tAmount = BigDecimalUtil.convert(bmBorrowBankForm.getTransactionAmount());
                        AssertUtils.asserts(hkAmount.compareTo(tAmount) <= 0,
                                FsscErrorType.BUSINESS_BORROW_AMOUNT_MUST_TH_TRAMOUNT);
                    }
                }
            }
            AssertUtils.asserts(expenseAmount.compareTo(bcVerAmount.add(fkAmount.add(hkAmount))) == 0, FsscErrorType.TRANSFER_DOCUMENT_MONERY_RIGHT);
        }
    }

    @ApiOperation(value = "根据id删除", notes = "根据url的id来指定删除对象")
    @Transactional
    @DeleteMapping(value = "/deleteId")
    public Result deleteId(@RequestBody List<Long> ids) {
        log.info("ids" + ids);
        for (Long id : ids) {
            TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getById(id);
            AssertUtils.asserts(tasTravelReimburse != null, FsscErrorType.NOT_FIND_DATA);
            String documentStatus = tasTravelReimburse.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                    FsscEums.RECALLED.getValue().equals(documentStatus) ||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
            removeLock(id);
            tasTravelReimburseService.removeById(id);
            /*//删除费用清单的同时删除 先删差旅时间
            QueryWrapper<TasTravelLine> costWrapper1=new QueryWrapper<>();
            costWrapper1.eq("DOCUMENT_ID",id);
            List<TasTravelLine>  costLines=tasTravelLineService.list(costWrapper1);
            for (TasTravelLine costLine:costLines) {
                QueryWrapper<TasTravelTime> timeWrapper=new QueryWrapper<>();
                timeWrapper.eq("TRAVEL_LINE_ID",costLine.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_TRAVEL_LINE.getValue());
                tasTravelTimeService.remove(timeWrapper);
            }*/
            //删除费用清单
            QueryWrapper<TasTravelLine> costWrapper = new QueryWrapper<>();
            costWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            tasTravelLineService.remove(costWrapper);

            //删除工资卡 公务卡
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()).eq(BmBorrowBank.DOCUMENT_ID, id);
            bmBorrowBankService.remove(bankQueryWrapper);
            //删除对公付款清单
            QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper = new QueryWrapper<>();
            paymentListQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()).eq("DOCUMENT_ID", id);
            paymentListService.remove(paymentListQueryWrapper);
            //删除关联付款借款
            QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
            prepayQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()).eq("DOCUMENT_ID", id);
            prepayService.remove(prepayQueryWrapper);

            //停止流程
            bpmProcessBiz.stopProcess(id);
        }
        return Result.success();
    }


    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<TasTravelReimburseVo>> search(@Valid @ApiParam(name = "tasTravelReimburseQueryForm", value = "TasTravelReimburse查询参数", required = true) TasTravelReimburseQueryForm tasTravelReimburseQueryForm) {
        log.info("search with tasTravelReimburseQueryForm:", tasTravelReimburseQueryForm.toString());
        IPage<TasTravelReimburse> tasTravelReimbursePage = tasTravelReimburseService.selectPage(tasTravelReimburseQueryForm);
        IPage<TasTravelReimburseVo> tasTravelReimburseVoPage = new BeanUtils<TasTravelReimburseVo>().copyPageObjs(tasTravelReimbursePage, TasTravelReimburseVo.class);
        return new Result<IPage<TasTravelReimburseVo>>().sucess(tasTravelReimburseVoPage);
    }

    /**
     * 根据id获取报账单相关信息
     */
    @GetMapping(value = "/getTravelReimburseId")
    @ApiOperation(value = "根据ID获取报账信息", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(message = "处理成功", code = 200, response = Result.class)
    )
    @ResponseBody
    public Result<TasTravelReimburseVo> getTravelReimburseId(Long id) {
        TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getById(id);
        AssertUtils.asserts(tasTravelReimburse != null, FsscErrorType.DOCUMENT_NOT_FIND);
        TasTravelReimburseVo tasTravelReimburseVo = new BeanUtils<TasTravelReimburseVo>().copyObj(tasTravelReimburse, TasTravelReimburseVo.class);
        //查询行明细
        QueryWrapper<TasTravelLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()).eq("DOCUMENT_ID", id);
        List<TasTravelLine> tasTravelLines = tasTravelLineService.list(lineQueryWrapper);
        List<TasTravelLineVo> tasTravelLineVos = new BeanUtils<TasTravelLineVo>().copyObjs(tasTravelLines, TasTravelLineVo.class);
        /*if(CollectionUtils.isNotEmpty(tasTravelLineVos)){
            for (TasTravelLineVo tasTravelLineVo:tasTravelLineVos) {
                    QueryWrapper<TasTravelTime>  timeQueryWrapper=new QueryWrapper<>();
                    timeQueryWrapper.eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_TRAVEL_LINE.getValue()).eq("TRAVEL_LINE_ID",tasTravelLineVo.getId());
                    List<TasTravelTime> tasTravelTime=tasTravelTimeService.list(timeQueryWrapper);
                    List<TasTravelTimeVo> timeVosVos=
                            new BeanUtils<TasTravelTimeVo>().copyObjs(tasTravelTime,TasTravelTimeVo.class);
                    tasTravelLineVo.setTasTravelTimeVos(timeVosVos);
            }
        }*/
        tasTravelReimburseVo.setTasTravelLineVo(tasTravelLineVos);

        //工资卡 和
        QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
        bankQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())
                .eq("GET_OR_RETURN", "SALARY");
        List<BmBorrowBank> bmBorrowBanks = tasTravelReimburseService.queryList(bankQueryWrapper);
        tasTravelReimburseVo.setSalaryCards(new BeanUtils<BmBorrowBankVo>().copyObjs(bmBorrowBanks, BmBorrowBankVo.class));

        //公务卡
        QueryWrapper<BmBorrowBank> businessQueryWrapper = new QueryWrapper<>();
        businessQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())
                .eq("GET_OR_RETURN", "BUSINESS");
        List<BmBorrowBank> business = tasTravelReimburseService.queryList(businessQueryWrapper);
        tasTravelReimburseVo.setBusinessCards(new BeanUtils<BmBorrowBankVo>().copyObjs(business, BmBorrowBankVo.class));

        //查询对公付款清单
        QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper = new QueryWrapper<>();
        paymentListQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()).eq("DOCUMENT_ID", id);
        tasTravelReimburseVo.setGeExpensePaymentList(new BeanUtils<GeExpensePaymentListVo>()
                .copyObjs(paymentListService.list(paymentListQueryWrapper), GeExpensePaymentListVo.class));
        //查询关联付款借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()).eq("DOCUMENT_ID", id);
        List<GeExpenseBorrowPrepay> prepays = prepayService.list(prepayQueryWrapper);
        prepayService.paddingHexiaomingxi(prepays);
        tasTravelReimburseVo.setBorrowPrepayVoList(new BeanUtils<GeExpenseBorrowPrepayVo>()
                .copyObjs(prepays, GeExpenseBorrowPrepayVo.class));
        //查询审批历史
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
            tasTravelReimburseVo.setBpmProcessTaskVos(history);
        } catch (FSSCException e) {
            log.error(e.getMessage());
        }
        return new Result<TasTravelReimburseVo>().sucess(tasTravelReimburseVo);
    }

    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, TasTravelReimburseQueryForm tasTravelReimburseQueryForm)
            throws IOException {
        log.info("search with tasTravleApplyInfoForm:{}", JSON.toJSONString(tasTravelReimburseQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<TasTravelReimburse> records = tasTravelReimburseService.selectPage(tasTravelReimburseQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "归属单位", "归属部门",
                "项目", "支出大类", "报销金额", "创建日期", "单据状态", "付款状态"};
        String fileName = "差旅报账单列表.xls";
        String sheetName = "差旅报账单列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            TasTravelReimburse vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            content[i][5] = vo.getProjectName();
            content[i][6] = vo.getMainTypeName();
            // content[i][5] = StringUtil.objectToString(vo.getBorrowAmount());
            content[i][7] = StringUtil.objectToString(vo.getExpenseAmount());
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

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<TasTravelReimburseVo> doSubmitProcess(@RequestBody @Valid TasTravelReimburseForm tasTravelReimburseForm) {
        //todo 待验证参数 加注解
        log.info("tasTravelReimburseForm:{}", tasTravelReimburseForm.toString());
        Result<TasTravelReimburseVo> queryDatas = addOrUpdate(tasTravelReimburseForm);
        TasTravelReimburseVo expense = queryDatas.getData();
        //TasTravelReimburseVo expense=new BeanUtils<TasTravelReimburseVo>().copyObj(tasTravelReimburse,TasTravelReimburseVo.class);

        AssertUtils.asserts(FsscEums.NEW.getValue().equals(expense.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(expense.getDocumentStatus())
                || FsscEums.RECALLED.getValue().equals(expense.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        prepayService.modifyAfterSubmit(true, expense.getId(), FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest(expense.getApplyForId()!=null?"Y":"N");
        valueForm.setProjectId(expense.getProjectId());
        valueForm.setTotalAmount(expense.getTotalSum().toString());
        valueForm.setMainTypeCode(expense.getMainTypeCode());
        valueForm.setDocumentId(expense.getId());
        valueForm.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        valueForm.setCreateBy(expense.getCreateBy());
        valueForm.setDeptCode(expense.getDeptCode());
        valueForm.setProjectCode(expense.getProjectCode());
        valueForm.setUnitCode(expense.getUnitCode());

        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(expense.getId());
        startForm.setDocumentNum(expense.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        startForm.setBudgetWarningCheck(tasTravelReimburseForm.getBudgetWarningCheck());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(), startForm.getDocumentId());

        if (FsscEums.FIRST_SUBMIT.getValue().equals(tasTravelReimburseForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(tasTravelReimburseForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        //提交时修改金额
        return Result.success(expense);
    }

    /**
     * 根据选择的差旅申请id带出关联借款单的信息
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "根据条件列表查询借款信息", notes = "根据条件列表查询借款信息")
    @ResponseBody
    public Result<List<BorrowMoneyInfoVo>> list(@Valid TasTravelApplyInfoQueryForm tasTravelApplyInfoQueryForm) {
        log.info("search with borrowMoneyInfoQueryForm:", tasTravelApplyInfoQueryForm.toString());
        Long id = tasTravelApplyInfoQueryForm.getId();
        QueryWrapper<BorrowMoneyInfo> bmWrapper = new QueryWrapper<>();
        bmWrapper.eq("APPLY_FOR_ID", id);
        BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getOne(bmWrapper);
        BorrowMoneyInfoVo borrowMoneyInfoVos = new BeanUtils<BorrowMoneyInfoVo>().copyObj(borrowMoneyInfo, BorrowMoneyInfoVo.class);
        if (borrowMoneyInfo != null) {
            QueryWrapper<BorrowMoneyLine> wrapper = new QueryWrapper<>();
            wrapper.eq(BorrowMoneyLine.DOCUMENT_ID, borrowMoneyInfoVos.getId());
            List<BorrowMoneyLine> lines = borrowMoneyLineService.list(wrapper);
            List<BorrowMoneyLineVo> borrowMoneyLineVos =
                    new BeanUtils<BorrowMoneyLineVo>().copyObjs(lines, BorrowMoneyLineVo.class);
            borrowMoneyInfoVos.setBorrowMoneyLineList(borrowMoneyLineVos);
        }
        return new Result<List<BorrowMoneyInfoVo>>().sucess(borrowMoneyInfoVos);
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
        Result<TasTravelReimburseVo> resultData = getTravelReimburseId(id);
        TasTravelReimburseVo tasTravelReimburseVo = resultData.getData();
        String documentStatus = tasTravelReimburseVo.getDocumentStatus();
        String documentNum = tasTravelReimburseVo.getDocumentNum();
        if (pyPamentOrderInfoService.selectCount(documentNum)) {
            QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
            PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(queryWrapper);
            if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                throw new FSSCException(FsscErrorType.DOCUMENT_READY_PAYMENT);
            }
        }
        BigDecimal noPaidMount = tasTravelReimburseVo.getNoPaidAmount();
        BigDecimal zero = BigDecimal.ZERO;
        if (FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus) && noPaidMount.compareTo(zero) != 0) {
            PyPamentOrderInfoVo pamentOrderInfoVo = new PyPamentOrderInfoVo();
            pamentOrderInfoVo.setDocumentStatus(tasTravelReimburseVo.getDocumentStatus());
            // pamentOrderInfoVo.setDocumentNum();
            // pamentOrderInfoVo.setBankAccount();
            //pamentOrderInfoVo.setBankType();
            pamentOrderInfoVo.setCost(tasTravelReimburseVo.getCost());
            pamentOrderInfoVo.setCreateBy(tasTravelReimburseVo.getCreateBy());
            pamentOrderInfoVo.setCreateTime(tasTravelReimburseVo.getCreateTime());
            pamentOrderInfoVo.setCreateUserName(tasTravelReimburseVo.getCreateUserName());
            pamentOrderInfoVo.setCurrencyCode(tasTravelReimburseVo.getCurrencyCode());
            pamentOrderInfoVo.setDeptCode(tasTravelReimburseVo.getDeptCode());
            pamentOrderInfoVo.setDeptId(tasTravelReimburseVo.getDeptId());
            pamentOrderInfoVo.setDeptName(tasTravelReimburseVo.getDeptName());
            pamentOrderInfoVo.setIsAfterPatch(tasTravelReimburseVo.getIsAfterPatch());
            pamentOrderInfoVo.setNoPaidAmount(tasTravelReimburseVo.getNoPaidAmount());
            pamentOrderInfoVo.setPayDocumentNum(tasTravelReimburseVo.getDocumentNum());
            pamentOrderInfoVo.setPayDocumentType("TAS_TRAVEL_REIMBURSE");
            pamentOrderInfoVo.setPaidAmount(tasTravelReimburseVo.getPaidAmount());
            pamentOrderInfoVo.setPaymentType(tasTravelReimburseVo.getPaymentType());
            pamentOrderInfoVo.setPayStatus(tasTravelReimburseVo.getPayStatus());
            pamentOrderInfoVo.setRemark(tasTravelReimburseVo.getRemark());
            pamentOrderInfoVo.setTotalAmount(tasTravelReimburseVo.getTotalSum());
            pamentOrderInfoVo.setTotalAmountOtherCurrency(tasTravelReimburseVo.getTotalSumPosition());
            pamentOrderInfoVo.setUnitCode(tasTravelReimburseVo.getUnitCode());
            pamentOrderInfoVo.setUnitId(tasTravelReimburseVo.getUnitId());
            pamentOrderInfoVo.setUnitName(tasTravelReimburseVo.getUnitName());
            pamentOrderInfoVo.setUpdateTime(tasTravelReimburseVo.getUpdateTime());
            pamentOrderInfoVo.setUpdateBy(tasTravelReimburseVo.getUpdateBy());
            pamentOrderInfoVo.setVersion(tasTravelReimburseVo.getVersion());
            List<BmBorrowBankVo> salaryCards = tasTravelReimburseVo.getSalaryCards();

            if (CollectionUtils.isNotEmpty(salaryCards)) {
                List<PyPamentBusinessLineVo> pyPamentPrivateLineVos = new ArrayList<PyPamentBusinessLineVo>();
                for (BmBorrowBankVo bmBorrowBankVo : salaryCards) {
                    PyPamentBusinessLineVo pyPamentBusinessLineVo = new PyPamentBusinessLineVo();
                    pyPamentBusinessLineVo.setBankAccount(bmBorrowBankVo.getBankAccount());
                    // pyPamentBusinessLineVo.setBankInternationalCode();
                    pyPamentBusinessLineVo.setBankName(bmBorrowBankVo.getBankName());
                    //pyPamentBusinessLineVo.setBankReturnInfo();
                    //pyPamentBusinessLineVo.setBranchBankName();
                    pyPamentBusinessLineVo.setBusinessCardNum(bmBorrowBankVo.getBankAccount());
                    //pyPamentBusinessLineVo.setCommonCreditCode();
                    pyPamentBusinessLineVo.setConnectDocumentId(tasTravelReimburseVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(tasTravelReimburseVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PRIVATE");
                    pyPamentBusinessLineVo.setConnectDocumentType("TAS_TRAVEL_REIMBURSE");
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
                    pyPamentBusinessLineVo.setUnitId(tasTravelReimburseVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(tasTravelReimburseVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(tasTravelReimburseVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(tasTravelReimburseVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(tasTravelReimburseVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("差旅报账单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                    pyPamentPrivateLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentPrivateLineVos(pyPamentPrivateLineVos);
            }

            List<BmBorrowBankVo> businessCards = tasTravelReimburseVo.getBusinessCards();
            if (CollectionUtils.isNotEmpty(businessCards)) {
                List<PyPamentBusinessLineVo> pyPamentBusinessLineVos = new ArrayList<PyPamentBusinessLineVo>();
                for (BmBorrowBankVo bmBorrowBankVo : businessCards) {
                    PyPamentBusinessLineVo pyPamentBusinessLineVo = new PyPamentBusinessLineVo();
                    pyPamentBusinessLineVo.setBankAccount(bmBorrowBankVo.getBankAccount());
                    // pyPamentBusinessLineVo.setBankInternationalCode();
                    pyPamentBusinessLineVo.setBankName(bmBorrowBankVo.getBankName());
                    //pyPamentBusinessLineVo.setBankReturnInfo();
                    //pyPamentBusinessLineVo.setBranchBankName();
                    pyPamentBusinessLineVo.setBusinessCardNum(bmBorrowBankVo.getBankAccount());
                    //pyPamentBusinessLineVo.setCommonCreditCode();
                    pyPamentBusinessLineVo.setConnectDocumentId(tasTravelReimburseVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(tasTravelReimburseVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("BUSINESS_CARD");
                    pyPamentBusinessLineVo.setConnectDocumentType("TAS_TRAVEL_REIMBURSE");
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
                    pyPamentBusinessLineVo.setUnitId(tasTravelReimburseVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(tasTravelReimburseVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(tasTravelReimburseVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(tasTravelReimburseVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(tasTravelReimburseVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("差旅报账单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                    pyPamentBusinessLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentBusinessLineVos(pyPamentBusinessLineVos);
            }
            List<GeExpensePaymentListVo> geExpensePaymentList = tasTravelReimburseVo.getGeExpensePaymentList();
            if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                List<PyPamentBusinessLineVo> pyPamentPublicLineVos = new ArrayList<PyPamentBusinessLineVo>();
                for (GeExpensePaymentListVo geExpensePaymentListVo : geExpensePaymentList) {
                    PyPamentBusinessLineVo pyPamentBusinessLineVo = new PyPamentBusinessLineVo();
                    pyPamentBusinessLineVo.setBankAccount(geExpensePaymentListVo.getBankAccount());
                    // pyPamentBusinessLineVo.setBankInternationalCode();
                    pyPamentBusinessLineVo.setBankName(geExpensePaymentListVo.getBankName());
                    pyPamentBusinessLineVo.setBankId(geExpensePaymentListVo.getBankUnitId());
                    //pyPamentBusinessLineVo.setBankReturnInfo();
                    //pyPamentBusinessLineVo.setBranchBankName();
                    pyPamentBusinessLineVo.setBusinessCardNum(geExpensePaymentListVo.getBankAccount());
                    pyPamentBusinessLineVo.setCommonCreditCode(geExpensePaymentListVo.getCommonCreditCode());
                    pyPamentBusinessLineVo.setConnectDocumentId(tasTravelReimburseVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(tasTravelReimburseVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(geExpensePaymentListVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PUBLIC");
                    pyPamentBusinessLineVo.setConnectDocumentType("TAS_TRAVEL_REIMBURSE");
                    pyPamentBusinessLineVo.setCreateBy(geExpensePaymentListVo.getCreateBy());
                    pyPamentBusinessLineVo.setCreateTime(geExpensePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setCreateUserName(geExpensePaymentListVo.getCreateUserName());
                    //   pyPamentBusinessLineVo.setEmpNo(geExpensePaymentListVo.getEmpNo());
                    pyPamentBusinessLineVo.setInterBranchNumber(geExpensePaymentListVo.getInterBranchNumber());
                    // pyPamentBusinessLineVo.setLineNumber();
                    pyPamentBusinessLineVo.setPayAmount(geExpensePaymentListVo.getPayAmount());
                    pyPamentBusinessLineVo.setPayStatus(geExpensePaymentListVo.getPayStatus());
                    pyPamentBusinessLineVo.setRecieveEmpName(tasTravelReimburseVo.getCreateUserName());
                    pyPamentBusinessLineVo.setRecieveId(tasTravelReimburseVo.getCreateBy());
                    // pyPamentBusinessLineVo.setTransactionAmount(geExpensePaymentListVo.getTransactionAmount());
                    //pyPamentBusinessLineVo.setTransactionComments();
                    pyPamentBusinessLineVo.setTransactionDate(geExpensePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setUnitId(tasTravelReimburseVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(tasTravelReimburseVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(tasTravelReimburseVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(tasTravelReimburseVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(tasTravelReimburseVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("差旅报账单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(geExpensePaymentListVo.getId());
                    pyPamentPublicLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentPublicLineVos(pyPamentPublicLineVos);
            }
            return Result.success(pamentOrderInfoVo);
        }
        throw new FSSCException(FsscErrorType.NOT_GENERATE_BILL_PAYMENT);
    }

    public void removeLock(Long id) {
        //去除差旅报账单关联差旅申请
        removeSign(id);
        TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getById(id);
        Long applyForId = tasTravelReimburse.getApplyForId();
        if (applyForId != null) {
            TasTravelApplyInfo tasTravelApplyInfo = tasTravleApplyInfoService.getById(applyForId);
            if (tasTravelApplyInfo != null) {
                tasTravelApplyInfo.setIsReimburConnect("N");
                tasTravleApplyInfoService.updateById(tasTravelApplyInfo);
            }
        }
    }

    public void removeSign(Long id) {
        if (id != null) {
            TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getById(id);
            Long paymentId = tasTravelReimburse.getPayMentId();
            if (paymentId != null) {
                tasTravelReimburse.setPayMentId(null);
                tasTravelReimburseService.updateById(tasTravelReimburse);
            }
        }
    }

    public void changeTravelIsConnect(Long id, Long travelId) {
        if (id != null) {
            TasTravelReimburse travelReimburse = tasTravelReimburseService.getById(id);
            Long applyForId = travelReimburse.getApplyForId();
            changeTravelIsBorrowConnect(applyForId, "N");
        }
        changeTravelIsBorrowConnect(travelId, "Y");
    }

    private void changeTravelIsBorrowConnect(Long id, String status) {
        if (id != null) {
            TasTravelApplyInfo applyInfo = tasTravleApplyInfoService.getById(id);
            if (applyInfo != null) {
                applyInfo.setIsReimburConnect(status);
                tasTravleApplyInfoService.updateById(applyInfo);
            }
        }
    }

    public void validCarPayContact(TasTravelReimburseForm tasTravelReimburseForm) {
        List<TasTravelLineForm> tasTravelLineForms = tasTravelReimburseForm.getTasTravelLineForm();//行详情
        List<BmBorrowBankForm> bmBorrowBankForms = tasTravelReimburseForm.getBankFormsList();//公务卡
        List<GeExpensePaymentListForm> geExpensePaymentFormList = tasTravelReimburseForm.getGeExpensePaymentList();//对公付款清单
        List<GeExpenseBorrowPrepayForm> geExpenseBorrowPrepayForms = tasTravelReimburseForm.getBorrowPrepayFormList();//关联借款
        if (CollectionUtils.isNotEmpty(tasTravelLineForms)) {
            BigDecimal contactDetailAmountunt = BigDecimal.ZERO;//实际付款金额合计（为公务卡的）
            BigDecimal actualPayAmountunt = BigDecimal.ZERO;//实际付款金额合计（为“对公支付”“限额支票”）
            BigDecimal salaryAmountC = BigDecimal.ZERO;//工资卡和现金
            BigDecimal salaryAmount = BigDecimal.ZERO;//工资卡
            BigDecimal bmAmount = BigDecimal.ZERO;//公务卡的行合计金额
            BigDecimal geAmount = BigDecimal.ZERO;//对公付款的行合计金额

            BigDecimal gwHeXiao = BigDecimal.ZERO;//关联借款单的合计金额公务卡
            BigDecimal gzHeXiao = BigDecimal.ZERO;//关联借款单的合计金额工资卡
            BigDecimal dgHeXiao = BigDecimal.ZERO;//关联借款单的合计金额对公付款

            for (TasTravelLineForm tasTravelLineForm : tasTravelLineForms) {
                if ("BUSINESS_CARD".equals(tasTravelLineForm.getPaymentType())) {
                    contactDetailAmountunt = contactDetailAmountunt.add(BigDecimalUtil.convert(tasTravelLineForm.getTotalForehead()));
                }
                if ("PUBLIC_PAYMENT".equals(tasTravelLineForm.getPaymentType()) ||
                        "LIMIT_CHECK".equals(tasTravelLineForm.getPaymentType())) {
                    actualPayAmountunt = actualPayAmountunt.add(BigDecimalUtil.convert(tasTravelLineForm.getTotalForehead()));
                }
                if ("SALARY_CARD".equals(tasTravelLineForm.getPaymentType()) || "CASH".equals(tasTravelLineForm.getPaymentType())) {
                    salaryAmountC = salaryAmountC.add(BigDecimalUtil.convert(tasTravelLineForm.getTotalForehead()));
                }
            }
            if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepayForms)) {
                for (GeExpenseBorrowPrepayForm ge : geExpenseBorrowPrepayForms) {
                    if ("BUSINESS_CARD".equals(ge.getPaymentType())) {
                        gwHeXiao = gwHeXiao.add(BigDecimalUtil.convert(ge.getCurrentVerAmount()));
                    }
                    if ("PUBLIC_PAYMENT".equals(ge.getPaymentType()) ||
                            "LIMIT_CHECK".equals(ge.getPaymentType())) {
                        dgHeXiao = dgHeXiao.add(BigDecimalUtil.convert(ge.getCurrentVerAmount()));
                    }
                    if ("SALARY_CARD".equals(ge.getPaymentType()) || "CASH".equals(ge.getPaymentType())) {
                        gzHeXiao = gzHeXiao.add(BigDecimalUtil.convert(ge.getCurrentVerAmount()));
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
                for (BmBorrowBankForm bmBorrowBankForm : bmBorrowBankForms) {
                    if ("BUSINESS".equals(bmBorrowBankForm.getGetOrReturn())) {
                        bmAmount = bmAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                        BigDecimal bAmount = BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount());
                        BigDecimal tAmount = BigDecimalUtil.convert(bmBorrowBankForm.getTransactionAmount());
                        AssertUtils.asserts(bAmount.compareTo(tAmount) <= 0,
                                FsscErrorType.BUSINESS_BORROW_AMOUNT_MUST_TH_TRAMOUNT);
                    }
                    if ("SALARY".equals(bmBorrowBankForm.getGetOrReturn())) {
                        salaryAmount = salaryAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(geExpensePaymentFormList)) {
                for (GeExpensePaymentListForm geExpensePaymentListForm : geExpensePaymentFormList) {
                    geAmount = geAmount.add(BigDecimalUtil.convert(geExpensePaymentListForm.getPayAmount()));
                }
            }
            if (geAmount.compareTo(actualPayAmountunt.subtract(dgHeXiao)) != 0) {
                throw new FSSCException(FsscErrorType.PUBLIC_AMOUNT_NOT_EQ);
            }
            if (bmAmount.compareTo(contactDetailAmountunt.subtract(gwHeXiao)) != 0) {
                throw new FSSCException(FsscErrorType.BUSINESS_AMOUNT_NOT_EQ);
            }
            if (salaryAmount.compareTo(salaryAmountC.subtract(gzHeXiao)) != 0) {
                throw new FSSCException(FsscErrorType.SALARY_AMOUNT_NOT_EQ);
            }
        }
        BigDecimal verAmountBusiness = BigDecimalUtil.convert(tasTravelReimburseForm.getVerAmountBusiness());
        BigDecimal verAmountSarlary = BigDecimalUtil.convert(tasTravelReimburseForm.getVerAmountSalary());
        BigDecimal verAmountPublic = BigDecimalUtil.convert(tasTravelReimburseForm.getVerAmountExpense());
        tasTravelReimburseForm.setVerificationAmount(verAmountBusiness.add(verAmountSarlary).add(verAmountPublic));
    }
}



