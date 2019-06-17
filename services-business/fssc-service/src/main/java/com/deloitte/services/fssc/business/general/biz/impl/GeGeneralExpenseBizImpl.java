package com.deloitte.services.fssc.business.general.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.api.fssc.general.vo.GeGeneralExpenseForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.general.biz.GeGeneralExpenseBiz;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpenseLine;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseLineService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeGeneralExpenseBizImpl implements GeGeneralExpenseBiz {

    @Autowired
    private IGeGeneralExpenseService expenseService;

    @Autowired
    private IGeGeneralExpenseLineService lineService;

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;

    @Autowired
    private IGeExpensePaymentListService paymentListService;

    @Autowired
    private IBmBorrowBankService bmBorrowBankService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IBudgetProjectService budgetProjectService;

    /**
     * 保存通用报账单
     *
     * @param geGeneralExpenseForm
     */
    @Override
    @Transactional
    public GeGeneralExpense saveOrUpdateGeExpense(GeGeneralExpenseForm geGeneralExpenseForm) {
//验证单据是否存在
        FsscCommonUtil.valiHasData(geGeneralExpenseForm.getId()
                ,FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        //头表
        validateForm(geGeneralExpenseForm);
        GeGeneralExpense geGeneralExpense = new BeanUtils<GeGeneralExpense>()
                .copyObj(geGeneralExpenseForm, GeGeneralExpense.class);
        //todo 单据编号待写

        Long projectId = geGeneralExpense.getProjectId();
        if (projectId != null) {
            BudgetProject project = budgetProjectService.getById(projectId);
            if (project != null) {
                geGeneralExpense.setProjectCode(project.getProjectCode());
                geGeneralExpense.setProjectName(project.getProjectName());
                geGeneralExpense.setProjectUnitCode(project.getResponsibleUnitCode());
                geGeneralExpense.setProjectUnitName(project.getResponsibleUnitName());
                geGeneralExpense.setFsscCode(project.getFsscCode());
            }
        }


        boolean bGeFlag = expenseService.saveOrUpdate(geGeneralExpense);
        AssertUtils.asserts(bGeFlag, FsscErrorType.DATA_IS_NOT_LATEST);
        geGeneralExpense = expenseService.getById(geGeneralExpense.getId());
        geGeneralExpenseForm.setId(geGeneralExpense.getId());
        //行明细表处理
        saveExpenseLine(geGeneralExpenseForm);
        //关联借款或预付款表
        saveBorrowPrepay(geGeneralExpenseForm, geGeneralExpense.getDocumentNum());
        //对公付款清单
        savePaymentList(geGeneralExpenseForm);
        //工资卡公务卡
        saveBankCard(geGeneralExpenseForm);

        //保存文件
        saveFiles(geGeneralExpenseForm);

        return geGeneralExpense;
    }

    private void validateForm(GeGeneralExpenseForm geGeneralExpenseForm) {
        //附件数量验证
        List<Long> fileIds = geGeneralExpenseForm.getFileIds();
        if (fileIds != null) {
            fileIds.removeAll(Collections.singleton(null));
            AssertUtils.asserts(fileIds.size() <= geGeneralExpenseForm.getAttachCount()
                    , FsscErrorType.FILEIDS_MUSTBE_LE_ATTCHFILES);
        }
        //公务卡 交易金额和还款金额验证
        List<BmBorrowBankForm> bmBorrowBankForms = geGeneralExpenseForm.getBankFormList();
        BigDecimal cardAmount = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
            for (BmBorrowBankForm form : bmBorrowBankForms) {
                cardAmount = cardAmount.add(BigDecimalUtil.convert(form.getBorrowAmount()));
                if ("BUSINESS".equals(form.getGetOrReturn())) {
                    BigDecimal bAmount = BigDecimalUtil.convert(form.getBorrowAmount());
                    BigDecimal tAmount = BigDecimalUtil.convert(form.getTransactionAmount());
                    AssertUtils.asserts(bAmount.compareTo(tAmount) <= 0,
                            FsscErrorType.BUSINESS_BORROW_AMOUNT_MUST_TH_TRAMOUNT);

                    //时间验证
                    LocalDateTime payTime = form.getPayTime();
                    LocalDateTime now = LocalDateTime.now();
                    boolean before = now.isBefore(payTime);
                    AssertUtils.asserts(!before, FsscErrorType.JIAOYI_TIME_MUST_BE_BEFORE_NOW);

                }
            }
        }

        //工资卡 公务卡 对公付款 金额验证 是否等于头
        BigDecimal paySalaryAmount = BigDecimalUtil.convert(geGeneralExpenseForm.getPaySalaryAmount());
        BigDecimal payCompanyAmount = BigDecimalUtil.convert(geGeneralExpenseForm.getPayCompanyAmount());
        BigDecimal businussAmount = BigDecimalUtil.convert(geGeneralExpenseForm.getBusinussAmount());
        List<BmBorrowBankForm> bankFormList = geGeneralExpenseForm.getBankFormList();
        BigDecimal sa = BigDecimal.ZERO;
        BigDecimal bu = BigDecimal.ZERO;
        BigDecimal gongfu = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(bankFormList)) {
            for (BmBorrowBankForm bank : bankFormList) {
                String getOrReturn = bank.getGetOrReturn();
                if ("SALARY".equals(getOrReturn)) {
                    sa = sa.add(BigDecimalUtil.convert(bank.getBorrowAmount()));
                }
                if ("BUSINESS".equals(getOrReturn)) {
                    bu = bu.add(BigDecimalUtil.convert(bank.getBorrowAmount()));
                }
            }
        }
        List<GeExpensePaymentListForm> geExpensePaymentList = geGeneralExpenseForm.getGeExpensePaymentList();
        if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
            for (GeExpensePaymentListForm ge : geExpensePaymentList) {
                gongfu = gongfu.add(BigDecimalUtil.convert(ge.getPayAmount()));
            }
        }
        AssertUtils.asserts(paySalaryAmount.compareTo(sa) == 0, FsscErrorType.SALARY_AMOUNT_NOT_EQ);
        AssertUtils.asserts(businussAmount.compareTo(bu) == 0, FsscErrorType.BUSINESS_AMOUNT_NOT_EQ);
        AssertUtils.asserts(payCompanyAmount.compareTo(gongfu) == 0, FsscErrorType.PUBLIC_AMOUNT_NOT_EQ);


        //核销金额公务卡 验证
        BigDecimal verAmountBusiness = BigDecimalUtil.convert(geGeneralExpenseForm.getVerAmountBusiness());
        BigDecimal verAmountSarlary = BigDecimalUtil.convert(geGeneralExpenseForm.getVerAmountSarlary());
        BigDecimal verAmountPublic = BigDecimalUtil.convert(geGeneralExpenseForm.getVerAmountPublic());
        //核销金额 对公付款验证
        List<GeExpenseBorrowPrepayForm> borrowPrepayFormList = geGeneralExpenseForm.getBorrowPrepayFormList();
        BigDecimal vs = BigDecimal.ZERO;
        BigDecimal vb = BigDecimal.ZERO;
        BigDecimal vp = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(borrowPrepayFormList)) {
            for (GeExpenseBorrowPrepayForm b : borrowPrepayFormList) {
                String paymentType = b.getPaymentType();
                if ("CASH".equals(paymentType) || "SALARY_CARD".equals(paymentType)) {
                    vs = vs.add(BigDecimalUtil.convert(b.getCurrentVerAmount()));
                }
                if ("BUSINESS_CARD".equals(paymentType)) {
                    vb = vb.add(BigDecimalUtil.convert(b.getCurrentVerAmount()));
                }
                if ("LIMIT_CHECK".equals(paymentType)||"PUBLIC_PAYMENT".equals(paymentType)) {
                    vp = vp.add(BigDecimalUtil.convert(b.getCurrentVerAmount()));
                }
            }
        }
        AssertUtils.asserts(verAmountBusiness.compareTo(vb) == 0, FsscErrorType.VER_BUSINESS_AMOUNT_NOT_EQ);
        AssertUtils.asserts(verAmountSarlary.compareTo(vs) == 0, FsscErrorType.VER_SALARY_AMOUNT_NOT_EQ);
        AssertUtils.asserts(verAmountPublic.compareTo(vp) == 0, FsscErrorType.VER_PUBLIC_AMOUNT_NOT_EQ);
        geGeneralExpenseForm.setVerificationAmount(verAmountBusiness.add(verAmountSarlary).add(verAmountPublic));
    }


    /**
     * 保存文件
     *
     * @param geGeneralExpenseForm
     */
    private void saveFiles(GeGeneralExpenseForm geGeneralExpenseForm) {
        //文件列表保存
        List<Long> fileIds = geGeneralExpenseForm.getFileIds();
        if (fileIds != null) {
            fileIds.removeAll(Collections.singleton(null));
        }
        if (CollectionUtils.isNotEmpty(fileIds)) {
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID, geGeneralExpenseForm.getId())
                    .eq(File.DOCUMENT_TYPE, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())
                    .notIn(File.ID, fileIds);
            fileService.remove(fileQueryWrapper);

            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files),
                    FsscErrorType.FILE_IS_NULL);
            files.stream().forEach(ka -> ka.setDocumentId(geGeneralExpenseForm.getId()));
            fileService.saveOrUpdateBatch(files);
        }
    }


    /**
     * 保存工资卡公务卡
     *
     * @param geGeneralExpenseForm
     */
    private void saveBankCard(GeGeneralExpenseForm geGeneralExpenseForm) {

        //工资卡 公务卡 保存 先删除 再新增
        QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
        bankQueryWrapper.eq(BmBorrowBank.DOCUMENT_ID, geGeneralExpenseForm.getId())
                .eq(BmBorrowBank.DOCUMENT_TYPE, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        List<BmBorrowBankForm> bmBorrowBankForms = geGeneralExpenseForm.getBankFormList();
        List<Long> longList = bmBorrowBankForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
        longList.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(longList)) {
            bankQueryWrapper.notIn(BmBorrowBank.ID, longList);
        }
        bmBorrowBankService.remove(bankQueryWrapper);
        List<BmBorrowBank> bmBorrowBanks = new BeanUtils<BmBorrowBank>().copyObjs(bmBorrowBankForms, BmBorrowBank.class);
        for (BmBorrowBank bank : bmBorrowBanks) {
            bank.setDocumentNum(geGeneralExpenseForm.getDocumentNum());
            bank.setDocumentId(geGeneralExpenseForm.getId());
            bank.setDocumentType(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        }
        if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
            bmBorrowBankService.saveOrUpdateBatch(bmBorrowBanks);
        }
    }

    /**
     * 保存行明细
     *
     * @param geGeneralExpenseForm
     */
    private void saveExpenseLine(GeGeneralExpenseForm geGeneralExpenseForm) {
        List<GeGeneralExpenseLine> geGeneralExpenseLines = new BeanUtils<GeGeneralExpenseLine>()
                .copyObjs(geGeneralExpenseForm.getGeneralExpenseLines(), GeGeneralExpenseLine.class);
        AssertUtils.asserts(CollectionUtils.isNotEmpty(geGeneralExpenseLines), FsscErrorType.GE_LINE_IS_NOT_EMPTY);

        //先删除差集的行
        QueryWrapper<GeGeneralExpenseLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GeGeneralExpenseLine.DOCUMENT_ID, geGeneralExpenseForm.getId());
        List<Long> cuLineIds = geGeneralExpenseLines.stream().map(k -> k.getId()).collect(Collectors.toList());
        cuLineIds.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(cuLineIds)) {
            queryWrapper.notIn(GeGeneralExpenseLine.ID, cuLineIds);
        }
        lineService.remove(queryWrapper);

        //核销金额填充
        BigDecimal verAmountBusiness = BigDecimalUtil.convert(geGeneralExpenseForm.getVerAmountBusiness());
        BigDecimal verAmountSarlary = BigDecimalUtil.convert(geGeneralExpenseForm.getVerAmountSarlary());
        BigDecimal verAmountPublic = BigDecimalUtil.convert(geGeneralExpenseForm.getVerAmountPublic());

        for (GeGeneralExpenseLine line : geGeneralExpenseLines) {
            line.setDocumentId(geGeneralExpenseForm.getId());
            line.setDocumentType(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
            BigDecimal invoiceAmount = BigDecimalUtil.convert(line.getInvoiceAmount());
            String paymentType = line.getPaymentType();
            if ("CASH".equals(paymentType) || "SALARY_CARD".equals(paymentType)) {
                if(verAmountSarlary.compareTo(invoiceAmount)>=0){
                    line.setHasVerAmount(invoiceAmount);

                    verAmountSarlary=verAmountSarlary.subtract(invoiceAmount);
                }else {
                    line.setHasVerAmount(verAmountSarlary);
                    verAmountSarlary=BigDecimal.ZERO;
                }
            }
            if ("BUSINESS_CARD".equals(paymentType)) {
                if(verAmountBusiness.compareTo(invoiceAmount)>=0){
                    line.setHasVerAmount(invoiceAmount);
                    verAmountBusiness=verAmountBusiness.subtract(invoiceAmount);
                }else {
                    line.setHasVerAmount(verAmountBusiness);
                    verAmountBusiness=BigDecimal.ZERO;
                }
            }
            if ("LIMIT_CHECK".equals(paymentType)||"PUBLIC_PAYMENT".equals(paymentType)) {
                if(verAmountPublic.compareTo(invoiceAmount)>=0){
                    line.setHasVerAmount(invoiceAmount);
                    verAmountPublic=verAmountPublic.subtract(invoiceAmount);
                }else {
                    line.setHasVerAmount(verAmountPublic);
                    verAmountPublic=BigDecimal.ZERO;
                }
            }
            line.setNoVerAmount(invoiceAmount.subtract(BigDecimalUtil.convert(line.getHasVerAmount())));

        }
        lineService.saveOrUpdateBatch(geGeneralExpenseLines);
    }

    /**
     * 保存关联借款预付款
     *
     * @param geGeneralExpenseForm
     */
    private void saveBorrowPrepay(GeGeneralExpenseForm geGeneralExpenseForm, String documentNum) {
        List<GeExpenseBorrowPrepay> geExpenseBorrowPrepays = new BeanUtils<GeExpenseBorrowPrepay>()
                .copyObjs(geGeneralExpenseForm.getBorrowPrepayFormList(), GeExpenseBorrowPrepay.class);
        //先删除差集的行
        QueryWrapper<GeExpenseBorrowPrepay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, geGeneralExpenseForm.getId())
                .eq(GeExpenseBorrowPrepay.DOCUMENT_TYPE, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        List<Long> cuLineIds = geExpenseBorrowPrepays.stream().map(k -> k.getId()).collect(Collectors.toList());
        cuLineIds.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(cuLineIds)) {
            queryWrapper.notIn(GeExpenseBorrowPrepay.ID, cuLineIds);
        }
        prepayService.remove(queryWrapper);
        for (GeExpenseBorrowPrepay prepay : geExpenseBorrowPrepays) {
            prepay.setDocumentType(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
            prepay.setDocumentId(geGeneralExpenseForm.getId());
            prepay.setDocumentNum(documentNum);
            prepay.setMainTypeCode(geGeneralExpenseForm.getMainTypeCode());
            prepay.setMainTypeId(geGeneralExpenseForm.getMainTypeId());
            prepay.setMainTypeName(geGeneralExpenseForm.getMainTypeName());
            prepay.setVerficationDate(LocalDateTime.now());

            Long borrowOrPrepayId = prepay.getBorrowOrPrepayId();
            String budgetAccountCode = prepayService.getPaymentOrderBudgetAccountCode(borrowOrPrepayId);
            prepay.setPaymentBudgetAccountCode(budgetAccountCode);
        }
        if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepays)) {
            prepayService.saveOrUpdateBatch(geExpenseBorrowPrepays);
        }

    }


    /**
     * 保存对公付款清单
     *
     * @param geGeneralExpenseForm
     */
    private void savePaymentList(GeGeneralExpenseForm geGeneralExpenseForm) {
        List<GeExpensePaymentList> geExpensePaymentLists = new BeanUtils<GeExpensePaymentList>()
                .copyObjs(geGeneralExpenseForm.getGeExpensePaymentList(), GeExpensePaymentList.class);
        //先删除差集的行
        QueryWrapper<GeExpensePaymentList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GeExpensePaymentList.DOCUMENT_ID, geGeneralExpenseForm.getId())
                .eq(GeExpensePaymentList.DOCUMENT_TYPE, FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        List<Long> cuLineIds = geExpensePaymentLists.stream().map(k -> k.getId()).collect(Collectors.toList());
        cuLineIds.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(cuLineIds)) {
            queryWrapper.notIn(GeExpensePaymentList.ID, cuLineIds);
        }
        paymentListService.remove(queryWrapper);
        for (GeExpensePaymentList paymentList : geExpensePaymentLists) {
            paymentList.setDocumentType(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
            paymentList.setDocumentId(geGeneralExpenseForm.getId());
        }
        if (CollectionUtils.isNotEmpty(geExpensePaymentLists)) {
            paymentListService.saveOrUpdateBatch(geExpensePaymentLists);
        }
    }
}
