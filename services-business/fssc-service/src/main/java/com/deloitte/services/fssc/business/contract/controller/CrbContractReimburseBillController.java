package com.deloitte.services.fssc.business.contract.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.advance.vo.ContactDetailForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.contract.param.CrbContractReimburseBillQueryForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbContractReimburseBillForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbContractReimburseBillVo;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.fssc.unit.vo.UnitBankBaseInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentLineService;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractInfo;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractInfoService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.contract.service.ICrbAssocAdvancePaymentService;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description :   CrbContractReimburseBill控制器实现类
 * @Modified :
 */
@Api(tags = "合同报账单-操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/contract/contract-reimburse-bill")
public class CrbContractReimburseBillController {

    @Autowired
    public ICrbContractReimburseBillService crbContractReimburseBillService;

    @Autowired
    public ICrbAssocAdvancePaymentService crbAssocAdvancePaymentService;

    @Autowired
    public IGeExpensePaymentListService geExpensePaymentListService;

    @Autowired
    public IContactDetailService bmContactDetailService;

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;

    @Autowired
    public IAdvancePaymentLineService bmAdvancePaymentLineService;

    @Autowired
    IContactDetailService contactDetailService;


    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IFileService fileService;


    @Autowired
    public IAdvancePaymentInfoService bmAdvancePaymentInfoService;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @Autowired
    private IBmBorrowBankService bmBorrowBankService;

    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    @Autowired
    private IBaseContractInfoService contractInfoService;

    /**
     * 根据单据contractId 获取信息
     *
     * @param contractId
     * @return
     */
    @GetMapping(value = "/loadDataByContractId")
    @ApiOperation(value = "根据id查询", notes = "获取指定ID的查询")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result<CrbContractReimburseBillVo> loadDataByContractId(Long contractId) {
        CrbContractReimburseBillVo crbContractReimburseBillVo = crbContractReimburseBillService.findByAllData(contractId);
        return Result.success(crbContractReimburseBillVo);
    }

    @ApiOperation(value = "分页查询", notes = "根据条件查询 CrbContractReimburseBill分页数据")
    @PostMapping(value = "page/conditions")
    public Result<IPage<CrbContractReimburseBillVo>> pageCondition(
            @RequestBody @ApiParam(name = "geGeneralExpenseQueryForm", value = "GeGeneralExpense查询参数", required = true)
                    CrbContractReimburseBillQueryForm crbContractReimburseBillQueryForm) {
        IPage<CrbContractReimburseBill> crbContractReimburseBillIPage = crbContractReimburseBillService.selectPage(crbContractReimburseBillQueryForm);
        IPage<CrbContractReimburseBillVo> contractReimburseBillVoIPage = new BeanUtils<CrbContractReimburseBillVo>().
                copyPageObjs(crbContractReimburseBillIPage, CrbContractReimburseBillVo.class);
        return Result.success(contractReimburseBillVoIPage);
    }

    @ApiOperation(value = "新增CrbContractReimburseBill", notes = "新增一个CrbContractReimburseBill")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/doSaveOrUpdateCrb")
    @Transactional
    @ResponseBody
    public Result doSaveOrUpdateCrb(@Valid @RequestBody CrbContractReimburseBillForm form) {
        log.info("form:" + form.toString());
        return saveOrUpdateContract(form);
    }

    /**
     * 保存
     *
     * @param crbContractReimburseBillForm
     * @return
     */
    public Result saveOrUpdateContract(CrbContractReimburseBillForm crbContractReimburseBillForm) {
        //验证单据是否存在
        FsscCommonUtil.valiHasData(crbContractReimburseBillForm.getId(),FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        //验证公务卡或对公付款清单与合同详情的实际付款金额验证
        validCarPayContact(crbContractReimburseBillForm);
        CrbContractReimburseBill info = new BeanUtils<CrbContractReimburseBill>().copyObj(crbContractReimburseBillForm, CrbContractReimburseBill.class);
        try {
            Long contactNumberId = info.getContactNumberId();
            if (contactNumberId != null) {
                QueryWrapper<BaseContractInfo> infoQueryWrapper = new QueryWrapper<>();
                infoQueryWrapper.eq(BaseContractInfo.CONTRACT_ID, contactNumberId);
                BaseContractInfo one = contractInfoService.getOne(infoQueryWrapper);
                if (one != null) {
                    info.setContactName(one.getContractName());
                    info.setContactNumber(one.getContractNo());
                }
            }
        } catch (Exception e) {
            log.error("填充合同信息报错!");
        }

        boolean b = crbContractReimburseBillService.saveOrUpdate(info);
        if (b) {
            //合同详情 保存 先删除 再新增
            QueryWrapper<ContactDetail> contactQueryWrapper = new QueryWrapper<>();
            contactQueryWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE", FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            List<ContactDetailForm> contactDetailForm = crbContractReimburseBillForm.getBmContactDetaiForm();
            if (CollectionUtils.isNotEmpty(contactDetailForm)) {
                List<ContactDetail> contactDetails = new BeanUtils<ContactDetail>().copyObjs(contactDetailForm, ContactDetail.class);
                //收集前台传过来的id
                List<Long> longList = contactDetailForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                //移除id为null的数据（针对新增的）
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(contactDetails)) {
                    contactQueryWrapper.notIn("id", longList);
                }
                bmContactDetailService.remove(contactQueryWrapper);

                //核销金额填充
                BigDecimal verAmountBusiness = BigDecimalUtil.convert(crbContractReimburseBillForm.getVerAmountBusiness());//核销金额公务卡
                // BigDecimal verAmountSarlary = BigDecimalUtil.convert(crbContractReimburseBillForm.getVerAmountSalary());//核销金额工资卡
                BigDecimal verAmountPublic = BigDecimalUtil.convert(crbContractReimburseBillForm.getVerAmountExpense());//核销金额对公付款
                if (CollectionUtils.isNotEmpty(contactDetails)) {
                    for (ContactDetail contactDetail : contactDetails) {
                        contactDetail.setDocumentId(info.getId());
                        contactDetail.setDocumentType(FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
                        BigDecimal invoiceAmount = BigDecimalUtil.convert(contactDetail.getActualPlayAmount());
                        String paymentType = contactDetail.getPaymentType();
                        if ("BUSINESS_CARD".equals(paymentType)) {
                            if(verAmountBusiness.compareTo(invoiceAmount)>=0){
                                contactDetail.setHasVerAmount(invoiceAmount);
                                verAmountBusiness=verAmountBusiness.subtract(invoiceAmount);
                            }else {
                                contactDetail.setHasVerAmount(verAmountBusiness);
                                verAmountBusiness=BigDecimal.ZERO;
                            }
                        }
                        if ("LIMIT_CHECK".equals(paymentType)||"PUBLIC_PAYMENT".equals(paymentType)) {
                            if(verAmountPublic.compareTo(invoiceAmount)>=0){
                                contactDetail.setHasVerAmount(invoiceAmount);
                                verAmountPublic=verAmountPublic.subtract(invoiceAmount);
                            }else {
                                contactDetail.setHasVerAmount(verAmountPublic);
                                verAmountPublic=BigDecimal.ZERO;
                            }
                        }
                        contactDetail.setNoVerAmount(invoiceAmount.subtract(BigDecimalUtil.convert(contactDetail.getHasVerAmount())));
                    }
                    bmContactDetailService.saveOrUpdateBatch(contactDetails);
                }
            } else {
                bmContactDetailService.remove(contactQueryWrapper);
            }

            //关联预付款
            QueryWrapper<GeExpenseBorrowPrepay> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, info.getId())
                    .eq(GeExpenseBorrowPrepay.DOCUMENT_TYPE, FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());

            List<GeExpenseBorrowPrepayForm> geExpenseBorrowPrepaForm = crbContractReimburseBillForm.getBorrowPrepayFormList();
            if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepaForm)) {
                List<GeExpenseBorrowPrepay> geExpenseBorrowPrepaFormList = new BeanUtils<GeExpenseBorrowPrepay>().copyObjs(geExpenseBorrowPrepaForm, GeExpenseBorrowPrepay.class);
                List<Long> idsList = geExpenseBorrowPrepaForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                idsList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepaFormList)) {
                    queryWrapper.notIn("id", idsList);
                }
                prepayService.remove(queryWrapper);
                if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepaFormList)) {
                    for (GeExpenseBorrowPrepay crbAssocAdvancePayment : geExpenseBorrowPrepaFormList) {
                        crbAssocAdvancePayment.setDocumentId(info.getId());
                        crbAssocAdvancePayment.setDocumentType(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
                        crbAssocAdvancePayment.setEx1("合同报账单");
                        crbAssocAdvancePayment.setConnectDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
                        crbAssocAdvancePayment.setDocumentNum(info.getDocumentNum());
                        crbAssocAdvancePayment.setVerficationDate(LocalDateTime.now());

                        Long borrowOrPrepayId = crbAssocAdvancePayment.getBorrowOrPrepayId();
                        String budgetAccountCode = prepayService.getPaymentOrderBudgetAccountCode(borrowOrPrepayId);
                        crbAssocAdvancePayment.setPaymentBudgetAccountCode(budgetAccountCode);


                    }
                    prepayService.saveOrUpdateBatch(geExpenseBorrowPrepaFormList);
                }
            }

            //公务卡 保存 先删除 再新增
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE",
                    FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            List<BmBorrowBankForm> bmBorrowBankForms = crbContractReimburseBillForm.getBmBorrowBankForms();
            List<BmBorrowBank> bmBorrowBanks = new BeanUtils<BmBorrowBank>().copyObjs(bmBorrowBankForms, BmBorrowBank.class);
            List<Long> longList1 = bmBorrowBankForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
            longList1.removeAll(Collections.singleton(null));
            if (CollectionUtils.isNotEmpty(longList1)) {
                bankQueryWrapper.notIn("id", longList1);
            }
            bmBorrowBankService.remove(bankQueryWrapper);

            for (BmBorrowBank bank : bmBorrowBanks) {
                bank.setDocumentNum(info.getDocumentNum());
                bank.setDocumentId(info.getId());
                bank.setGetOrReturn("BUSINESS");
                bank.setDocumentType(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            }
            if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
                bmBorrowBankService.saveOrUpdateBatch(bmBorrowBanks);
            }

            //对公付款清单，先删除后保存
            QueryWrapper<GeExpensePaymentList> geExpensePaymentWrapper = new QueryWrapper<>();
            geExpensePaymentWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE", FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            List<GeExpensePaymentListForm> geExpensePaymentForm = crbContractReimburseBillForm.getGeExpensePaymentFormList();
            if (CollectionUtils.isNotEmpty(geExpensePaymentForm)) {
                List<GeExpensePaymentList> geExpensePaymentList = new BeanUtils<GeExpensePaymentList>().copyObjs(geExpensePaymentForm, GeExpensePaymentList.class);
                List<Long> longList = geExpensePaymentForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                    geExpensePaymentWrapper.notIn("id", longList);
                }
                geExpensePaymentListService.remove(geExpensePaymentWrapper);
                if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                    for (GeExpensePaymentList geExpensePaymen : geExpensePaymentList) {
                        geExpensePaymen.setDocumentId(info.getId());
                        geExpensePaymen.setDocumentType(FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
                    }
                    geExpensePaymentListService.saveOrUpdateBatch(geExpensePaymentList);
                }
            } else {
                geExpensePaymentListService.remove(geExpensePaymentWrapper);
            }

            //文件列表保存
            List<Long> fileIds = crbContractReimburseBillForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, info.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                files.stream().forEach(ka -> ka.setDocumentId(info.getId()));
                fileService.saveOrUpdateBatch(files);
            }

            return Result.success(crbContractReimburseBillService.findByAllData(info.getId()));
        }
        throw new FSSCException(FsscErrorType.DATA_IS_NOT_LATEST);
    }

    public void validCarPayContact(CrbContractReimburseBillForm crbContractReimburseBillForm) {
        List<ContactDetailForm> contactDetailFormList = crbContractReimburseBillForm.getBmContactDetaiForm();//合同详情
        List<BmBorrowBankForm> bmBorrowBankForms = crbContractReimburseBillForm.getBmBorrowBankForms();//公务卡
        List<GeExpensePaymentListForm> geExpensePaymentFormList = crbContractReimburseBillForm.getGeExpensePaymentFormList();//对公付款清单
        //借款预付款
        List<GeExpenseBorrowPrepayForm> geExpenseBorrowPrepayForms=crbContractReimburseBillForm.getBorrowPrepayFormList();
        if (CollectionUtils.isNotEmpty(contactDetailFormList)) {
            BigDecimal contactDetailAmountunt = BigDecimal.ZERO;//实际付款金额合计（为公务卡的）
            BigDecimal actualPayAmountunt = BigDecimal.ZERO;//实际付款金额合计（为“对公支付”“限额支票”）
            BigDecimal bmAmount = BigDecimal.ZERO;//公务卡的行合计金额
            BigDecimal geAmount = BigDecimal.ZERO;//对公付款的行合计金额
            BigDecimal salaryAmount=BigDecimal.ZERO;//工资卡
            BigDecimal  gwHeXiao=BigDecimal.ZERO;//关联借款单的合计金额公务卡
            BigDecimal  gzHeXiao=BigDecimal.ZERO;//关联借款单的合计金额工资卡
            BigDecimal  dgHeXiao=BigDecimal.ZERO;//关联借款单的合计金额对公付款
            BigDecimal salaryAmountC=BigDecimal.ZERO;//工资卡和现金
            for (ContactDetailForm contactDetailForm : contactDetailFormList) {
                if ("BUSINESS_CARD".equals(contactDetailForm.getPaymentType())) {
                    contactDetailAmountunt = contactDetailAmountunt.add(BigDecimalUtil.convert(contactDetailForm.getActualPlayAmount()));
                }
                if ("PUBLIC_PAYMENT".equals(contactDetailForm.getPaymentType()) ||
                        "LIMIT_CHECK".equals(contactDetailForm.getPaymentType())) {
                    actualPayAmountunt = actualPayAmountunt.add(BigDecimalUtil.convert(contactDetailForm.getActualPlayAmount()));
                }
            }
            if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
                for (BmBorrowBankForm bmBorrowBankForm : bmBorrowBankForms) {
                    bmAmount = bmAmount.add(BigDecimalUtil.convert(bmBorrowBankForm.getBorrowAmount()));
                }
            }
            if (CollectionUtils.isNotEmpty(geExpensePaymentFormList)) {
                for (GeExpensePaymentListForm geExpensePaymentListForm : geExpensePaymentFormList) {
                    geAmount = geAmount.add(BigDecimalUtil.convert(geExpensePaymentListForm.getPayAmount()));
                }

            }
            if(CollectionUtils.isNotEmpty(geExpenseBorrowPrepayForms)){
                for(GeExpenseBorrowPrepayForm ge:geExpenseBorrowPrepayForms){
                    if("BUSINESS_CARD".equals(ge.getPaymentType())){
                        gwHeXiao=gwHeXiao.add(BigDecimalUtil.convert(ge.getCurrentVerAmount()));
                    }
                    if("PUBLIC_PAYMENT".equals(ge.getPaymentType())||
                            "LIMIT_CHECK".equals(ge.getPaymentType())){
                        dgHeXiao=dgHeXiao.add(BigDecimalUtil.convert(ge.getCurrentVerAmount()));
                    }
                    if("SALARY_CARD".equals(ge.getPaymentType())||"CASH".equals(ge.getPaymentType())){
                        gzHeXiao=gzHeXiao.add(BigDecimalUtil.convert(ge.getCurrentVerAmount()));
                    }
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

        BigDecimal verAmountBusiness = BigDecimalUtil.convert(crbContractReimburseBillForm.getVerAmountBusiness());
        BigDecimal verAmountPublic = BigDecimalUtil.convert(crbContractReimburseBillForm.getVerAmountExpense());
        crbContractReimburseBillForm.setVerAmount(verAmountBusiness.add(verAmountPublic));
    }

    /**
     * 可复用此方法
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取单位信息和银行信息", notes = "获取指定id的单位信息和银行信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "供应商的根据id获取单位信息和银行信息", required = true, dataType = "long")
    @GetMapping(value = "/get/{id}")
    public Result<UnitBankBaseInfoVo> getBankBaseInfoVo(@PathVariable Long id) {
        log.info("get with id:{}", id);
        UnitBankBaseInfoVo unitBankBaseInfoVo = commonServices.unitBankBaseUnitType(id);
        if (unitBankBaseInfoVo == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        return new Result<UnitBankBaseInfoVo>().sucess(unitBankBaseInfoVo);
    }

    @ApiOperation(value = "删除bContractReimburseBill信息", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(value = "ContractReimburseBillID", paramType = "path", name = "id", required = true,
            dataType = "long")
    @DeleteMapping(value = "deleteById/{id}")
    @Transactional
    public Result deleteById(@PathVariable Long id) {
        CrbContractReimburseBill crbContractReimburseBill = crbContractReimburseBillService.getById(id);
        if (crbContractReimburseBill == null) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND);
        }
        String documentStatus = crbContractReimburseBill.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                FsscEums.RECALLED.getValue().equals(documentStatus) ||
                FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        removeSign(id);
        crbContractReimburseBillService.removeById(id);
        //删除合同信息
        QueryWrapper<ContactDetail> ContractQueryWrapper = new QueryWrapper<>();
        ContractQueryWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        bmContactDetailService.remove(ContractQueryWrapper);
        //删除关联预付款
        QueryWrapper<GeExpenseBorrowPrepay> crbAssocQueryWrapper = new QueryWrapper<>();
        crbAssocQueryWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        prepayService.remove(crbAssocQueryWrapper);
        //删除付款清单
        QueryWrapper<GeExpensePaymentList> geExpensePaymenQueryWrapper = new QueryWrapper<>();
        geExpensePaymenQueryWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        geExpensePaymentListService.remove(geExpensePaymenQueryWrapper);

        //删除公务卡
        QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
        bankQueryWrapper.eq("DOCUMENT_ID", id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        bmBorrowBankService.remove(bankQueryWrapper);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }


    public void removeSign(Long id) {
        if (id != null) {
            CrbContractReimburseBill crbContractReimburseBill = crbContractReimburseBillService.getById(id);
            Long paymentId = crbContractReimburseBill.getPayMentId();
            if (paymentId != null) {
                crbContractReimburseBill.setPayMentId(null);
                crbContractReimburseBillService.updateById(crbContractReimburseBill);
            }
        }
    }


    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/sumbitAdvance")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result sumbitContract(@RequestBody @Valid CrbContractReimburseBillForm crbContractReimburseBillForm) {
        Result<CrbContractReimburseBillVo> queryDatas = saveOrUpdateContract(crbContractReimburseBillForm);
        CrbContractReimburseBillVo crbContractReimburseBill = queryDatas.getData();
        if("Y".equals(crbContractReimburseBillForm.getHasBorrowLines())
        &&CollectionUtils.isEmpty(crbContractReimburseBillForm.getBorrowPrepayFormList())) {
            GeExpenseBorrowPrepayQueryForm form=new GeExpenseBorrowPrepayQueryForm();
            form.setMainTypeId(crbContractReimburseBillForm.getMainTypeId());
            form.setCurrencyCode(crbContractReimburseBillForm.getCurrencyCode());
            form.setDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            List<BorrowPrepayListVo> borrowPrepayList
                    = crbContractReimburseBillService.findAdpPrepayList(form);
            AssertUtils.asserts(CollectionUtils.isEmpty(borrowPrepayList),FsscErrorType.YOU_HAVE_MAINTYPE_BORROW_IS_CONTRACT);
        }
        Long id = crbContractReimburseBill.getId();
        if (id == null) {
            throw new FSSCException(FsscErrorType.ADVANCE_PLAYMENT_NO_EMPTY);
        }
        if (FsscEums.REJECTED.getValue().equals(crbContractReimburseBill.getDocumentStatus())
                || FsscEums.NEW.getValue().equals(crbContractReimburseBill.getDocumentStatus())
                || FsscEums.RECALLED.getValue().equals(crbContractReimburseBill.getDocumentStatus())) {
           /* QueryWrapper<AdvancePaymentLine>  lineQueryWrapper=new QueryWrapper<>();
            lineQueryWrapper.eq("DOCUMENT_ID",id);
            List<AdvancePaymentLine> advancePaymentLines=bmAdvancePaymentLineService.list(lineQueryWrapper);
            if(CollectionUtils.isNotEmpty(advancePaymentLines)){
                //对被本单据和关联的单据金额进行计算回写(关联行)
                prepayService.modifyAfterSubmit(true,id,FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            }
            QueryWrapper<ContactDetail>  contactWrapper=new QueryWrapper<>();
            contactWrapper.eq("DOCUMENT_ID",id).eq("DOCUMENT_TYPE",FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            List<ContactDetail> contactDetails=contactDetailService.list(contactWrapper);
            if(CollectionUtils.isNotEmpty(contactDetails)){
                //对关联预付款关联对公预付款合同详情单据进行回写
            }*/
            ProcessValueForm valueForm = new ProcessValueForm();
            valueForm.setRequest("N");
            valueForm.setProjectId(crbContractReimburseBill.getProjectId());
            valueForm.setTotalAmount(crbContractReimburseBill.getTotalSum().toString());
            valueForm.setMainTypeCode(crbContractReimburseBill.getMainTypeCode());
            valueForm.setDocumentId(crbContractReimburseBill.getId());
            valueForm.setDocumentType(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            valueForm.setCreateBy(crbContractReimburseBill.getCreateBy());
            valueForm.setDeptCode(crbContractReimburseBill.getDeptCode());
            valueForm.setProjectCode(crbContractReimburseBill.getProjectCode());
            valueForm.setUnitCode(crbContractReimburseBill.getUnitCode());
            valueForm.setHasContract("Y");
            Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

            prepayService.modifyAfterSubmit(true, id, FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            prepayService.modifyContactAmount(true, id, FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            ReSubmitProcessForm form = new ReSubmitProcessForm();
            form.setProcessVariables(andAddProcessValue);
            form.setDocumentId(id);
            form.setDocumentType(FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
            form.setDocumentNum(crbContractReimburseBill.getDocumentNum());
            form.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
            form.setBudgetWarningCheck(crbContractReimburseBillForm.getBudgetWarningCheck());
            accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(), form.getDocumentId());
            if (FsscEums.FIRST_SUBMIT.getValue().equals(crbContractReimburseBillForm.getReSubmitType())) {
                bpmProcessBiz.startProcess(form);
            } else {
                form.setReSubmitType(crbContractReimburseBillForm.getReSubmitType());
                bpmProcessBiz.reSubmit(form);
            }
            return Result.success();
        }
        throw new FSSCException(FsscErrorType.SUBMIT_NEW_REJECTED);
    }


    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, CrbContractReimburseBillQueryForm crbContractReimburseBillForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(crbContractReimburseBillForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<CrbContractReimburseBill> records = crbContractReimburseBillService.selectPage(crbContractReimburseBillForm).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "归属单位", "归属部门", "供应商编码", "供应商名称", "报账金额",
                "支持附件数量", "合同信息", "制单日期", "单据状态", "付款状态"};
        String fileName = "合同报账单列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            CrbContractReimburseBill vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            // content[i][5] = StringUtil.objectToString(vo.getBorrowAmount());
            content[i][5] = vo.getSupplierCode();
            content[i][6] = vo.getSupplier();
            content[i][7] = StringUtil.objectToString(vo.getTotalSum());
            content[i][8] = String.valueOf(vo.getSupportFileNum());
            content[i][9] = vo.getContactNumber();
            if (vo.getCreateTime() != null) {
                content[i][10] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][11] = docStatusMap.get(vo.getDocumentStatus());
            content[i][12] = payStatusMap.get(vo.getPayStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    /**
     * 查询对公付预款清单 对合同报账单提供接口
     *
     * @return
     */
    @ApiOperation(value = "查询借款单或对公预付款单", notes = "查询借款单或对公预付款单")
    @GetMapping(value = "findBorrowList")
    @ResponseBody
    public Result<List<BorrowPrepayListVo>> findBorrowList(@Valid GeExpenseBorrowPrepayQueryForm form) {
        List<BorrowPrepayListVo> borrowPrepayList
                = crbContractReimburseBillService.findAdpPrepayList(form);

        return Result.success(borrowPrepayList);
    }


    /**
     * 查询借款单 对通用报账单提供接口
     *
     * @return
     */
    @ApiOperation(value = "根据供应商查询对公付款清单", notes = "根据供应商查询对公付款清单")
    @PostMapping(value = "/findPublicPayment")
    @ResponseBody
    public Result<List<GeExpensePaymentListVo>> findDuiGongfukuanqingdan(@RequestBody List<Long> supplierIds) {
        QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper = new QueryWrapper<>();

        paymentListQueryWrapper.in(GeExpensePaymentList.VENDOR_ID, supplierIds);
        List<GeExpensePaymentList> list = geExpensePaymentListService.list(paymentListQueryWrapper);

        return Result.success(new BeanUtils<GeExpensePaymentListVo>().copyObjs(list, GeExpensePaymentListVo.class));
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
        Result<CrbContractReimburseBillVo> resultData = loadDataByContractId(id);
        CrbContractReimburseBillVo crbContractReimburseBillVo = resultData.getData();
        String documentStatus = crbContractReimburseBillVo.getDocumentStatus();
        String documentNum = crbContractReimburseBillVo.getDocumentNum();
        if (pyPamentOrderInfoService.selectCount(documentNum)) {
            QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
            PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(queryWrapper);
            if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                throw new FSSCException(FsscErrorType.DOCUMENT_READY_PAYMENT);
            }
        }
        BigDecimal noPaidMount = crbContractReimburseBillVo.getUnpaidAmount();
        if (noPaidMount == null) {
            noPaidMount = BigDecimal.ZERO;
        }
        BigDecimal zero = BigDecimal.ZERO;
        if (FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus) && noPaidMount.compareTo(zero) != 0) {
            PyPamentOrderInfoVo pamentOrderInfoVo = new PyPamentOrderInfoVo();
            pamentOrderInfoVo.setDocumentStatus(crbContractReimburseBillVo.getDocumentStatus());
            // pamentOrderInfoVo.setDocumentNum();
            // pamentOrderInfoVo.setBankAccount();
            //pamentOrderInfoVo.setBankType();
            pamentOrderInfoVo.setCost(crbContractReimburseBillVo.getCost());
            pamentOrderInfoVo.setCreateBy(crbContractReimburseBillVo.getCreateBy());
            pamentOrderInfoVo.setCreateTime(crbContractReimburseBillVo.getCreateTime());
            pamentOrderInfoVo.setCreateUserName(crbContractReimburseBillVo.getCreateUserName());
            pamentOrderInfoVo.setCurrencyCode(crbContractReimburseBillVo.getCurrencyCode());
            pamentOrderInfoVo.setDeptCode(crbContractReimburseBillVo.getDeptCode());
            pamentOrderInfoVo.setDeptId(crbContractReimburseBillVo.getDeptId());
            pamentOrderInfoVo.setDeptName(crbContractReimburseBillVo.getDeptName());
            pamentOrderInfoVo.setIsAfterPatch(crbContractReimburseBillVo.getIsAfterPatch());
            pamentOrderInfoVo.setNoPaidAmount(crbContractReimburseBillVo.getUnpaidAmount());
            pamentOrderInfoVo.setPayDocumentNum(crbContractReimburseBillVo.getDocumentNum());
            pamentOrderInfoVo.setPayDocumentType("CRB_CONTRACT_REIMBURSE_BILL");
            pamentOrderInfoVo.setPaidAmount(crbContractReimburseBillVo.getAmountPaid());
            pamentOrderInfoVo.setPaymentType(crbContractReimburseBillVo.getPaymentType());
            pamentOrderInfoVo.setPayStatus(crbContractReimburseBillVo.getPayStatus());
            pamentOrderInfoVo.setRemark(crbContractReimburseBillVo.getRemark());
            pamentOrderInfoVo.setTotalAmount(crbContractReimburseBillVo.getTotalSum());
            pamentOrderInfoVo.setTotalAmountOtherCurrency(crbContractReimburseBillVo.getTotalSumPosition());
            pamentOrderInfoVo.setUnitCode(crbContractReimburseBillVo.getUnitCode());
            pamentOrderInfoVo.setUnitId(crbContractReimburseBillVo.getUnitId());
            pamentOrderInfoVo.setUnitName(crbContractReimburseBillVo.getUnitName());
            pamentOrderInfoVo.setUpdateTime(crbContractReimburseBillVo.getUpdateTime());
            pamentOrderInfoVo.setUpdateBy(crbContractReimburseBillVo.getUpdateBy());
            pamentOrderInfoVo.setVersion(crbContractReimburseBillVo.getVersion());
            List<GeExpensePaymentListVo> geExpensePaymentList = crbContractReimburseBillVo.getGeExpensePaymentFormList();
            if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                List<PyPamentBusinessLineVo> pyPamentPublicLineVos = new ArrayList<PyPamentBusinessLineVo>();
                for (GeExpensePaymentListVo geExpensePaymentListVo : geExpensePaymentList) {
                    PyPamentBusinessLineVo pyPamentBusinessLineVo = new PyPamentBusinessLineVo();
                    pyPamentBusinessLineVo.setBankAccount(geExpensePaymentListVo.getBankAccount());
                    pyPamentBusinessLineVo.setBankId(geExpensePaymentListVo.getBankUnitId());
                    // pyPamentBusinessLineVo.setBankInternationalCode();
                    pyPamentBusinessLineVo.setBankName(geExpensePaymentListVo.getBankName());
                    //pyPamentBusinessLineVo.setBankReturnInfo();
                    //pyPamentBusinessLineVo.setBranchBankName();
                    pyPamentBusinessLineVo.setBusinessCardNum(geExpensePaymentListVo.getBankAccount());
                    pyPamentBusinessLineVo.setCommonCreditCode(geExpensePaymentListVo.getCommonCreditCode());
                    pyPamentBusinessLineVo.setConnectDocumentId(crbContractReimburseBillVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(crbContractReimburseBillVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(geExpensePaymentListVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PUBLIC");
                    pyPamentBusinessLineVo.setConnectDocumentType("CRB_CONTRACT_REIMBURSE_BILL");
                    pyPamentBusinessLineVo.setCreateBy(geExpensePaymentListVo.getCreateBy());
                    pyPamentBusinessLineVo.setCreateTime(geExpensePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setCreateUserName(geExpensePaymentListVo.getCreateUserName());
                    //   pyPamentBusinessLineVo.setEmpNo(geExpensePaymentListVo.getEmpNo());
                    pyPamentBusinessLineVo.setInterBranchNumber(geExpensePaymentListVo.getInterBranchNumber());
                    // pyPamentBusinessLineVo.setLineNumber();
                    pyPamentBusinessLineVo.setPayAmount(geExpensePaymentListVo.getPayAmount());
                    pyPamentBusinessLineVo.setPayStatus(geExpensePaymentListVo.getPayStatus());
                    pyPamentBusinessLineVo.setRecieveEmpName(geExpensePaymentListVo.getCreateUserName());
                    pyPamentBusinessLineVo.setRecieveId(geExpensePaymentListVo.getCreateBy());
                    // pyPamentBusinessLineVo.setTransactionAmount(geExpensePaymentListVo.getTransactionAmount());
                    //pyPamentBusinessLineVo.setTransactionComments();
                    pyPamentBusinessLineVo.setTransactionDate(geExpensePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setUnitId(crbContractReimburseBillVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(crbContractReimburseBillVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(crbContractReimburseBillVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(crbContractReimburseBillVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(crbContractReimburseBillVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(geExpensePaymentListVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("合同报账单");
                    pyPamentPublicLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentPublicLineVos(pyPamentPublicLineVos);
            }
            List<BmBorrowBankVo> businessCards = crbContractReimburseBillVo.getBusinessCards();
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
                    pyPamentBusinessLineVo.setConnectDocumentId(crbContractReimburseBillVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(crbContractReimburseBillVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("BUSINESS_CARD");
                    pyPamentBusinessLineVo.setConnectDocumentType("CRB_CONTRACT_REIMBURSE_BILL");
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
                    pyPamentBusinessLineVo.setUnitId(crbContractReimburseBillVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(crbContractReimburseBillVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(crbContractReimburseBillVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(crbContractReimburseBillVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(crbContractReimburseBillVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("合同报账单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                    pyPamentBusinessLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentBusinessLineVos(pyPamentBusinessLineVos);
            }
            return Result.success(pamentOrderInfoVo);
        }
        throw new FSSCException(FsscErrorType.NOT_GENERATE_BILL_PAYMENT);
    }

}



