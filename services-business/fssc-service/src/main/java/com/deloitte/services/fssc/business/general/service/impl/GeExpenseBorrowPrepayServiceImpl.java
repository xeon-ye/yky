package com.deloitte.services.fssc.business.general.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.entity.VerificationDetail;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentLineService;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.advance.service.IVerificationDetailService;
import com.deloitte.services.fssc.business.advance.service.impl.ContactDetailServiceImpl;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanLine;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractPlanLineService;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyLineService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.mapper.GeExpenseBorrowPrepayMapper;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeExpenseBorrowPrepay服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class GeExpenseBorrowPrepayServiceImpl extends ServiceImpl<GeExpenseBorrowPrepayMapper, GeExpenseBorrowPrepay> implements IGeExpenseBorrowPrepayService {

    @Autowired
    private IBorrowMoneyLineService borrowMoneyLineService;
    @Autowired
    private IAdvancePaymentLineService advancePaymentLineService;
    @Autowired
    private IBorrowMoneyInfoService borrowMoneyInfoService;
    @Autowired
    private IAdvancePaymentInfoService advancePaymentInfoService;

    @Autowired
    public IContactDetailService bmContactDetailService;

    @Autowired
    private GeExpenseBorrowPrepayMapper geExpenseBorrowPrepayMapper;

    @Autowired
    private IVerificationDetailService verificationDetailService;

    @Autowired
    private ContactDetailServiceImpl contactDetailService;
    @Autowired
    private IPyPamentBusinessLineService businessLineService;
    @Autowired
    private IPyPamentOrderInfoService pyPamentOrderInfoService;
    @Autowired
    private IPepaymentOrderInfoService pepaymentOrderInfoService;
    @Autowired
    private IBaseContractPlanLineService contractPlanLineService;

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;


    @Override
    public IPage<GeExpenseBorrowPrepay> selectPage(GeExpenseBorrowPrepayQueryForm queryForm) {
        QueryWrapper<GeExpenseBorrowPrepay> queryWrapper = new QueryWrapper<GeExpenseBorrowPrepay>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geExpenseBorrowPrepayMapper.selectPage(new Page<GeExpenseBorrowPrepay>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<GeExpenseBorrowPrepay> selectList(GeExpenseBorrowPrepayQueryForm queryForm) {
        QueryWrapper<GeExpenseBorrowPrepay> queryWrapper = new QueryWrapper<GeExpenseBorrowPrepay>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geExpenseBorrowPrepayMapper.selectList(queryWrapper);
    }


    /**
     * 报账单提交时修改金额
     *
     * @param documentId
     * @param documentType
     */
    public void modifyAfterSubmit(boolean isSubmit, Long documentId, String documentType) {
        //获取关联借款预付款行
        QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper = new QueryWrapper<>();
        borrowPrepayQueryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, documentId)
                .eq(GeExpenseBorrowPrepay.DOCUMENT_TYPE, documentType);
        List<GeExpenseBorrowPrepay> borrowPrepays = geExpenseBorrowPrepayMapper.selectList(borrowPrepayQueryWrapper);
        if (CollectionUtils.isNotEmpty(borrowPrepays)) {
            for (GeExpenseBorrowPrepay prepay : borrowPrepays) {
                //本次核销金额
                BigDecimal currentVerAmount = BigDecimalUtil.convert(prepay.getCurrentVerAmount());
                //修改借款单行或预付款行
                String connectDocumentType = prepay.getConnectDocumentType();
                Long borrowOrPrepayId = prepay.getBorrowOrPrepayId();
                Long lineNumber = prepay.getLineNumber();
                //如果关联的借款
                if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(connectDocumentType)) {
                    QueryWrapper<BorrowMoneyLine> borrowMoneyLineQueryWrapper = new QueryWrapper<>();
                    borrowMoneyLineQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, borrowOrPrepayId)
                            .eq(BorrowMoneyLine.LINE_NUMBER, lineNumber);
                    BorrowMoneyLine line = borrowMoneyLineService.getOne(borrowMoneyLineQueryWrapper);
                    BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getById(borrowOrPrepayId);
                    String paymentType = prepay.getPaymentType();
                    if (isSubmit) {
                        if ("LIMIT_CHECK".equalsIgnoreCase(paymentType)) {
                            line.setIsAssociated("Y");
                        }
                    } else {
                        if ("LIMIT_CHECK".equalsIgnoreCase(paymentType)) {
                            line.setIsAssociated("N");
                        }
                    }
                    BigDecimal hasVerAmountLine = BigDecimalUtil.convert(line.getHasVerAmount());
                    BigDecimal noVerAmountLine = BigDecimalUtil.convert(line.getNoVerAmount());
                    if (isSubmit) {
                        line.setHasVerAmount(hasVerAmountLine.add(currentVerAmount));
                        line.setNoVerAmount(noVerAmountLine.subtract(currentVerAmount));
                    } else {
                        line.setHasVerAmount(hasVerAmountLine.subtract(currentVerAmount));
                        line.setNoVerAmount(noVerAmountLine.add(currentVerAmount));
                    }
                    //修改行表
                    borrowMoneyLineService.update(line, borrowMoneyLineQueryWrapper);
                    //修改头表
                    BigDecimal noVerAmountHeader = BigDecimalUtil.convert(borrowMoneyInfo.getNoVerAmount());
                    BigDecimal hasVerAmountHeader = BigDecimalUtil.convert(borrowMoneyInfo.getHasVerAmount());
                    BigDecimal cost = BigDecimalUtil.convert(borrowMoneyInfo.getCost());
                    if (isSubmit) {
                        BigDecimal subtract = noVerAmountHeader.subtract(currentVerAmount);
                        BigDecimal add = hasVerAmountHeader.add(currentVerAmount);
                        borrowMoneyInfo.setNoVerAmount(subtract);
                        borrowMoneyInfo.setHasVerAmount(add);
                        borrowMoneyInfo.setHasVerAmountOtherCurrency(add.multiply(cost));
                        borrowMoneyInfo.setNoVerAmountOtherCurrency(subtract.multiply(cost));
                    } else {
                        BigDecimal no = noVerAmountHeader.add(currentVerAmount);
                        BigDecimal has = hasVerAmountHeader.subtract(currentVerAmount);
                        borrowMoneyInfo.setNoVerAmount(no);
                        borrowMoneyInfo.setHasVerAmount(has);
                        borrowMoneyInfo.setHasVerAmountOtherCurrency(no.multiply(cost));
                        borrowMoneyInfo.setNoVerAmountOtherCurrency(has.multiply(cost));
                    }
                    BigDecimal lastNoVerAmount = borrowMoneyInfo.getNoVerAmount();

                    borrowMoneyInfoService.updateById(borrowMoneyInfo);
                }
                //如果关联的预付款
                if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(connectDocumentType)) {
                    QueryWrapper<ContactDetail> borrowMoneyLineQueryWrapper = new QueryWrapper<>();
                    borrowMoneyLineQueryWrapper.eq(AdvancePaymentLine.DOCUMENT_ID, borrowOrPrepayId)
                            .eq(AdvancePaymentLine.LINE_NUMBER, lineNumber);
                    ContactDetail line = bmContactDetailService.getOne(borrowMoneyLineQueryWrapper);
                    BigDecimal hasVerAmountLine = BigDecimalUtil.convert(line.getHasVerAmount());
                    BigDecimal noVerAmountLine = BigDecimalUtil.convert(line.getNoVerAmount());
                    if (!isSubmit) {
                        line.setHasVerAmount(hasVerAmountLine.subtract(currentVerAmount));
                        line.setNoVerAmount(noVerAmountLine.add(currentVerAmount));
                    } else {
                        line.setHasVerAmount(hasVerAmountLine.add(currentVerAmount));
                        line.setNoVerAmount(noVerAmountLine.subtract(currentVerAmount));
                    }
                    AdvancePaymentInfo paymentInfo = advancePaymentInfoService.getById(borrowOrPrepayId);
                    String paymentType = paymentInfo.getPaymentType();
                    if (isSubmit) {
                        if ("LIMIT_CHECK".equalsIgnoreCase(paymentType)) {
                            line.setEx5("Y");
                        }
                    } else {
                        if ("LIMIT_CHECK".equalsIgnoreCase(paymentType)) {
                            line.setEx5("N");
                        }
                    }

                    //修改行表
                    bmContactDetailService.update(line, borrowMoneyLineQueryWrapper);
                    //修改头表
                    BigDecimal noVerAmountHeader = BigDecimalUtil.convert(paymentInfo.getNoVerAmount());
                    BigDecimal hasVerAmountHeader = BigDecimalUtil.convert(paymentInfo.getHasVerAmount());
                    BigDecimal cost = BigDecimalUtil.convert(paymentInfo.getCost());
                    if (isSubmit) {
                        BigDecimal no = noVerAmountHeader.subtract(currentVerAmount);
                        BigDecimal has = hasVerAmountHeader.add(currentVerAmount);
                        paymentInfo.setNoVerAmount(no);
                        paymentInfo.setHasVerAmount(has);
                        paymentInfo.setNoVerAmountPosition(no.multiply(cost));
                        paymentInfo.setHasVerAmountPosition(has.multiply(cost));
                    } else {
                        BigDecimal no = noVerAmountHeader.add(currentVerAmount);
                        BigDecimal has = hasVerAmountHeader.subtract(currentVerAmount);
                        paymentInfo.setNoVerAmount(no);
                        paymentInfo.setHasVerAmount(has);
                        paymentInfo.setNoVerAmountPosition(no.multiply(cost));
                        paymentInfo.setHasVerAmountPosition(has.multiply(cost));
                    }
                    advancePaymentInfoService.updateById(paymentInfo);
                }

            }
        }

    }

    @Override
    public void modifyContactAmount(boolean isSubmit, Long documentId, String documentType) {
        //获取关联合同详情的BASE_CONTRACT_PLAN_LINE
        QueryWrapper<ContactDetail> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq("DOCUMENT_ID",documentId).eq("DOCUMENT_TYPE",documentType);
        List<ContactDetail> contactDetailList=contactDetailService.list(prepayQueryWrapper);
        if(CollectionUtils.isNotEmpty(contactDetailList)){
            for(ContactDetail contact:contactDetailList) {
                if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentType)) {
                    //实际付款金额
                    BigDecimal actualPlayAmountCon = BigDecimalUtil.convert(contact.getActualPlayAmount());
                    Long travelPlanId = contact.getTravelPlanId();
                    QueryWrapper<BaseContractPlanLine> baseWrapper = new QueryWrapper<>();
                    baseWrapper.eq("TRAVEL_PLAN_ID", travelPlanId);
                    BaseContractPlanLine contractPlanLine = contractPlanLineService.getOne(baseWrapper);
                    BigDecimal actualPlayAmount = BigDecimalUtil.convert(contractPlanLine.getActualPlayAmount());
                    //已支付
                    BigDecimal paidAmount = BigDecimalUtil.convert(contractPlanLine.getPaidAmount());
                    //BigDecimal paidAmountConDe = BigDecimalUtil.convert(contact.getPaidAmount());
                    //合同带出的实际支付金额
                    //BigDecimal conActualPlayAmount = BigDecimalUtil.convert(contractPlanLine.getActualPlayAmount());
                    if (!isSubmit) {
                        contractPlanLine.setPaidAmount(paidAmount.subtract(actualPlayAmountCon));
                        contractPlanLine.setActualPlayAmount(actualPlayAmount.add(paidAmount));
                    } else {
                        contractPlanLine.setPaidAmount(paidAmount.add(actualPlayAmountCon));
                        contractPlanLine.setActualPlayAmount(actualPlayAmount.subtract(paidAmount));
                    }
                    contractPlanLineService.updateById(contractPlanLine);
                }
                if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentType)) {
                    QueryWrapper<GeExpenseBorrowPrepay>  queryWrapper =new QueryWrapper<>();
                    queryWrapper.eq("DOCUMENT_ID",documentId);
                    List<GeExpenseBorrowPrepay> geExpenseBorrowPrepays =prepayService.list(queryWrapper);
                    if(CollectionUtils.isEmpty(geExpenseBorrowPrepays)){
                        //实际付款金额
                        BigDecimal actualPlayAmountCon = BigDecimalUtil.convert(contact.getActualPlayAmount());
                        Long travelPlanId = contact.getTravelPlanId();
                        QueryWrapper<BaseContractPlanLine> baseWrapper = new QueryWrapper<>();
                        baseWrapper.eq("TRAVEL_PLAN_ID", travelPlanId);
                        BaseContractPlanLine contractPlanLine = contractPlanLineService.getOne(baseWrapper);
                        BigDecimal actualPlayAmount = BigDecimalUtil.convert(contractPlanLine.getActualPlayAmount());
                        //已支付
                      //  BigDecimal paidAmount = BigDecimalUtil.convert(contact.getPaidAmount());
                        BigDecimal paidAmountConDe = BigDecimalUtil.convert(contractPlanLine.getPaidAmount());
                        //合同带出的实际支付金额
                       // BigDecimal conActualPlayAmount = BigDecimalUtil.convert(contractPlanLine.getReceipPlayAmount());
                        if (!isSubmit) {
                            contractPlanLine.setPaidAmount(paidAmountConDe.subtract(actualPlayAmountCon));
                            contractPlanLine.setActualPlayAmount(actualPlayAmount.add(actualPlayAmountCon));
                        } else {
                            contractPlanLine.setPaidAmount(paidAmountConDe.add(actualPlayAmountCon));
                            contractPlanLine.setActualPlayAmount(actualPlayAmount.subtract(actualPlayAmountCon));
                        }
                        contractPlanLineService.updateById(contractPlanLine);
                    }
                    }
            }
        }

    }

    public void modifyAfterContractSubmit(boolean isSubmit, Long documentId, String documentType) {
        //获取关联借款预付款行
        QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper = new QueryWrapper<>();
        borrowPrepayQueryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, documentId)
                .eq(GeExpenseBorrowPrepay.DOCUMENT_TYPE, documentType);
        List<GeExpenseBorrowPrepay> borrowPrepays = geExpenseBorrowPrepayMapper.selectList(borrowPrepayQueryWrapper);
        if (CollectionUtils.isNotEmpty(borrowPrepays)) {
            for (GeExpenseBorrowPrepay prepay : borrowPrepays) {
                //未核销金额
                BigDecimal noVerAmount = BigDecimalUtil.convert(prepay.getNoVerAmount());
                //已核销金额
                BigDecimal verficatedAmount = BigDecimalUtil.convert(prepay.getVerficatedAmount());
                //本次核销金额
                BigDecimal currentVerAmount = BigDecimalUtil.convert(prepay.getCurrentVerAmount());
                //预付金额
                BigDecimal bpAmount = BigDecimalUtil.convert(prepay.getBpAmount());
                //关联预付款行表
                //修改之后的金额
                //未核销金额=预付金额-(本次核销金额+已核销金额)
                BigDecimal weihexiao = bpAmount.subtract((currentVerAmount.add(verficatedAmount)));
                // weihexiao = noVerAmount.subtract(currentVerAmount);
                if (!isSubmit) {
                    weihexiao = bpAmount.add((currentVerAmount.add(verficatedAmount)));
                    // weihexiao = noVerAmount.add(currentVerAmount);
                }
                BigDecimal yihexiao = verficatedAmount.add(currentVerAmount);
                if (!isSubmit) {
                    yihexiao = verficatedAmount.subtract(currentVerAmount);
                }
                prepay.setNoVerAmount(weihexiao);
                prepay.setVerficatedAmount(yihexiao);
                geExpenseBorrowPrepayMapper.updateById(prepay);
                //修改预付款行合同详情
                String connectDocumentType = prepay.getConnectDocumentType();
                Long borrowOrPrepayId = prepay.getBorrowOrPrepayId();
                Long lineNumber = prepay.getLineNumber();

                //关联的预付款
                if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(connectDocumentType)) {
                    ContactDetail line = new ContactDetail();
                    line.setHasVerAmount(yihexiao);
                    //line.setNoVerAmount(yihexiao);
                    AdvancePaymentInfo paymentInfo = advancePaymentInfoService.getById(borrowOrPrepayId);
                    QueryWrapper<ContactDetail> borrowMoneyLineQueryWrapper = new QueryWrapper<>();
                    borrowMoneyLineQueryWrapper.eq(AdvancePaymentLine.DOCUMENT_ID, borrowOrPrepayId)
                            .eq(AdvancePaymentLine.LINE_NUMBER, lineNumber);
                    //修改行表
                    contactDetailService.update(line, borrowMoneyLineQueryWrapper);
                    //修改核销明细（根据小类id关联）
                    ContactDetail contactDetail = contactDetailService.getOne(borrowMoneyLineQueryWrapper);
                    Long subTypeId = contactDetail.getSubTypeId();
                    Long contactNumberId = contactDetail.getContactNumberId();
                    QueryWrapper<VerificationDetail> verificationWrapper = new QueryWrapper<>();
                    verificationWrapper.eq("SUB_TYPE_ID", subTypeId).eq("TRAVEL_PLAN_ID", contactNumberId);
                    VerificationDetail verificationDetail = verificationDetailService.getOne(verificationWrapper);
                    if (verificationDetail != null) {
                        if (isSubmit) {
                            verificationDetail.setVerificationAmount(yihexiao);
                            verificationDetailService.updateById(verificationDetail);
                        }
                    }


                    //修改头表
                    /*BigDecimal noVerAmountHeader = BigDecimalUtil.convert(paymentInfo.getNoVerAmount());
                    BigDecimal hasVerAmountHeader = BigDecimalUtil.convert(paymentInfo.getHasVerAmount());
                    BigDecimal cost = BigDecimalUtil.convert(paymentInfo.getCost());
                    if(isSubmit){
                        BigDecimal no = noVerAmountHeader.subtract(currentVerAmount);
                        BigDecimal has = hasVerAmountHeader.add(currentVerAmount);
                        paymentInfo.setNoVerAmount(no);
                        paymentInfo.setHasVerAmount(has);
                        paymentInfo.setNoVerAmountPosition(no.multiply(cost));
                        paymentInfo.setHasVerAmountPosition(has.multiply(cost));
                    }else {
                        BigDecimal no = noVerAmountHeader.add(currentVerAmount);
                        BigDecimal has = hasVerAmountHeader.subtract(currentVerAmount);
                        paymentInfo.setNoVerAmount(no);
                        paymentInfo.setHasVerAmount(has);
                        paymentInfo.setNoVerAmountPosition(no.multiply(cost));
                        paymentInfo.setHasVerAmountPosition(has.multiply(cost));
                    }
                    advancePaymentInfoService.updateById(paymentInfo);*/


                }

            }
        }

    }

    @Autowired
    private IGeGeneralExpenseService getExpenseService;

    @Autowired
    private ITasTravelReimburseService tasTravelReimburseService;

    @Override
    public void paddingHexiaomingxi(List<GeExpenseBorrowPrepay> borrowPrepays) {
        if (CollectionUtils.isNotEmpty(borrowPrepays)) {
            Iterator<GeExpenseBorrowPrepay> iterator = borrowPrepays.iterator();
            while (iterator.hasNext()){
                GeExpenseBorrowPrepay prepay = iterator.next();
                String connectDocumentType = prepay.getConnectDocumentType();
                Long borrowOrPrepayId = prepay.getBorrowOrPrepayId();
                Long lineNumber = prepay.getLineNumber();
                if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(connectDocumentType)) {
                    boolean isAc=false;
                    if(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(prepay.getDocumentType())){
                        PepaymentOrderInfo info = pepaymentOrderInfoService.getById(prepay.getDocumentId());
                        if(info !=null&& (
                                FsscEums.RECALLED.getValue().equals(info.getDocumentStatus())||
                                FsscEums.REJECTED.getValue().equals(info.getDocumentStatus()))){

                            iterator.remove();
                            continue;
                        }

                        if(info !=null&&FsscEums.HAS_CHARGE_AGAINST.getValue().equals(info.getDocumentStatus())){
                            isAc=true;
                        }
                    }

                    if(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(prepay.getDocumentType())){
                        GeGeneralExpense info = getExpenseService.getById(prepay.getDocumentId());
                        if(info!=null&& (
                                FsscEums.RECALLED.getValue().equals(info.getDocumentStatus())||
                                FsscEums.REJECTED.getValue().equals(info.getDocumentStatus()))){
                            iterator.remove();
                            continue;
                        }
                        if(info !=null&&FsscEums.HAS_CHARGE_AGAINST.getValue().equals(info.getDocumentStatus())){
                            isAc=true;
                        }
                    }

                    if(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(prepay.getDocumentType())){
                        TasTravelReimburse info = tasTravelReimburseService.getById(prepay.getDocumentId());
                        if(info !=null&&(
                                FsscEums.RECALLED.getValue().equals(info.getDocumentStatus())||
                                FsscEums.REJECTED.getValue().equals(info.getDocumentStatus())) ){
                            iterator.remove();
                            continue;
                        }
                        if(info !=null&&FsscEums.HAS_CHARGE_AGAINST.getValue().equals(info.getDocumentStatus())){
                            isAc=true;
                        }
                    }

                    QueryWrapper<BorrowMoneyLine> borrowMoneyLineQueryWrapper = new QueryWrapper<>();
                    borrowMoneyLineQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, borrowOrPrepayId)
                            .eq(BorrowMoneyLine.LINE_NUMBER, lineNumber);
                    BorrowMoneyLine one = borrowMoneyLineService.getOne(borrowMoneyLineQueryWrapper);
                    if (one != null) {
                        prepay.setNoVerAmount(one.getNoVerAmount());
                        if(isAc){
                            prepay.setVerficatedAmount(BigDecimal.ZERO.subtract(BigDecimalUtil.convert(prepay.getCurrentVerAmount())));
                        }
                        prepay.setVerficatedAmount(one.getHasVerAmount());
                        prepay.setBpAmount(one.getBorrowAmount());

                    }

                }

                //如果关联的预付款
                if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(connectDocumentType)) {
                    QueryWrapper<AdvancePaymentLine> borrowMoneyLineQueryWrapper = new QueryWrapper<>();
                    borrowMoneyLineQueryWrapper.eq(AdvancePaymentLine.DOCUMENT_ID, borrowOrPrepayId)
                            .eq(AdvancePaymentLine.LINE_NUMBER, lineNumber);
                    AdvancePaymentLine line = advancePaymentLineService.getOne(borrowMoneyLineQueryWrapper);
                    if (line != null) {
                        prepay.setNoVerAmount(line.getNoVerAmount());
                        prepay.setVerficatedAmount(line.getHasVerAmount());
                        prepay.setBpAmount(line.getPrepaidAmount());
                    }

                }
            }
        }

    }


    public String getPaymentOrderBudgetAccountCode(Long borrowOrPrepayId) {

        if (borrowOrPrepayId != null) {
            QueryWrapper<PyPamentBusinessLine> businessLineQueryWrapper = new QueryWrapper<>();
            businessLineQueryWrapper
                    .eq("CONNECT_DOCUMENT_ID", borrowOrPrepayId);
            List<PyPamentBusinessLine> businessLineList = businessLineService.list(businessLineQueryWrapper);
            if (CollectionUtils.isNotEmpty(businessLineList)) {
                PyPamentBusinessLine pyPamentBusinessLine = businessLineList.get(0);
                Long documentId = pyPamentBusinessLine.getDocumentId();
                if (documentId != null) {
                    PyPamentOrderInfo orderInfo = pyPamentOrderInfoService.getById(documentId);
                    if (orderInfo != null) {
                        log.info("取付款单中的预算科目code:{}", orderInfo.getBudgetAccountCode());
                        return orderInfo.getBudgetAccountCode();
                    }
                }
            }
        }
        log.info("取付款单中的预算科目code,结果未查询到数据!");
        return null;

    }

    @Autowired
    private IGeGeneralExpenseService expenseService;

    @Autowired
    private ICrbContractReimburseBillService billService;

    @Autowired
    private ITasTravelReimburseService reimburseService;


    @Override
    public void modifyPayStatus(Long documentId, String documentType) {
        PyPamentBusinessLine py = new PyPamentBusinessLine();
        py.setConnectDocumentType(documentType);
        py.setConnectDocumentId(documentId);
        if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentType)) {
            GeGeneralExpense expense = expenseService.getById(documentId);
            BigDecimal expenseAmount = expense.getExpenseAmount();
            BigDecimal verAmountBusiness = expense.getVerAmountBusiness();
            BigDecimal verAmountPublic = expense.getVerAmountPublic();
            BigDecimal verAmountSarlary = expense.getVerAmountSarlary();
            if(expenseAmount.compareTo(verAmountBusiness.add(verAmountPublic).add(verAmountSarlary))==0){
                expense.setPayStatus("PAYED");
                expenseService.updateById(expense);
                //收入结转
                bpmProcessBiz.carryForward(py);
            }
        }
        if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentType)) {
            CrbContractReimburseBill bill = billService.getById(documentId);
            BigDecimal totalAmountReimburse = bill.getTotalAmountReimburse();
            BigDecimal verAmountBusiness = bill.getVerAmountBusiness();
            BigDecimal verAmountExpense = bill.getVerAmountExpense();
            if(totalAmountReimburse.compareTo(verAmountBusiness.add(verAmountExpense))==0){
                bill.setPayStatus("PAYED");
                billService.updateById(bill);
                //收入结转
                bpmProcessBiz.carryForward(py);
            }

        }
        if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentType)) {
            TasTravelReimburse reimburse = reimburseService.getById(documentId);
            BigDecimal expenseAmount = reimburse.getExpenseAmount();
            BigDecimal verAmountBusiness = reimburse.getVerAmountBusiness();
            BigDecimal verAmountPublic = reimburse.getVerAmountExpense();
            BigDecimal verAmountSarlary = reimburse.getVerAmountSalary();
            if(expenseAmount.compareTo(verAmountBusiness.add(verAmountPublic).add(verAmountSarlary))==0){
                reimburse.setPayStatus("PAYED");
                reimburseService.updateById(reimburse);
                //收入结转
                bpmProcessBiz.carryForward(py);
            }
        }

    }

}

