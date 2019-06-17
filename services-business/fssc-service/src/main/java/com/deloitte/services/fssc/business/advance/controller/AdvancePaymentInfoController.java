package com.deloitte.services.fssc.business.advance.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.advance.param.AdvancePaymentInfoQueryForm;
import com.deloitte.platform.api.fssc.advance.vo.*;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractPlanLineQueryForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.fssc.unit.vo.UnitBankBaseInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.entity.VerificationDetail;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentLineService;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.advance.service.IVerificationDetailService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
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
import com.deloitte.services.fssc.system.bank.entity.BankInfo;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.service.IBankInfoService;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
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
 * @Date : Create in 2019-03-12
 * @Description :   BmAdvancePaymentInfo控制器实现类
 * @Modified :
 */
@Api(tags = "对公预付款-操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/advance/advance-payment-info")
public class AdvancePaymentInfoController {

    @Autowired
    public IAdvancePaymentInfoService bmAdvancePaymentInfoService;

    @Autowired
    public IAdvancePaymentLineService bmAdvancePaymentLineService;

    @Autowired
    public IContactDetailService bmContactDetailService;

    @Autowired
    public IVerificationDetailService bmVerificationDetailService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    public IGeExpensePaymentListService geExpensePaymentListService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;
    @Autowired
    private IBudgetProjectService budgetProjectService;
    @Autowired
    private IBankUnitInfoService bankUnitInfoService;

    @Autowired
    private IFileService fileService;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;
    @Autowired
    private IBmBorrowBankService bmBorrowBankService;

    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;


    @GetMapping(value = "/loadDataByadvanceId")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result loadDataByadvanceId(Long advanceId) {
        AdvancePaymentInfoVo infoById = bmAdvancePaymentInfoService.findInfoById(advanceId);
        return Result.success(infoById);
    }


    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<IPage<AdvancePaymentInfo>> pageCondition(@Valid AdvancePaymentInfoQueryForm form) {
        log.info("form:{}" + form);
        IPage<AdvancePaymentInfo> bmAdvancePaymentInfo = bmAdvancePaymentInfoService.selectPage(form);
        IPage<AdvancePaymentInfoVo> BmAdvancePaymentInfoVoPage = new BeanUtils<AdvancePaymentInfoVo>()
                .copyPageObjs(bmAdvancePaymentInfo, AdvancePaymentInfoVo.class);
        return Result.success(BmAdvancePaymentInfoVoPage);
    }

    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, AdvancePaymentInfoQueryForm advanceMoneyInfoForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(advanceMoneyInfoForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<AdvancePaymentInfo> records = bmAdvancePaymentInfoService.selectPage(advanceMoneyInfoForm).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "归属单位", "归属部门", "预付款金额",
                "支持附件数量", "合同信息", "创建日期", "单据状态", "付款状态"};
        String fileName = "对公预付款列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            AdvancePaymentInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            ;
            content[i][4] = vo.getDeptName();
            // content[i][5] = StringUtil.objectToString(vo.getBorrowAmount());
            /*ontent[i][5] = vo.getSupplierCode();
            content[i][6] = vo.getSupplier();*/
            content[i][5] = StringUtil.objectToString(vo.getTotalSum());
            content[i][6] = String.valueOf(vo.getSupportFileNum());
            content[i][7] = vo.getContactNumber();
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

    @PostMapping(value = "/deleteById")
    @ApiOperation(value = "根据id删除", notes = "根据id删除")
    @ResponseBody
    @Transactional
    public Result deleteById(@ApiParam(value = "id") @RequestBody Map<String, Long> map) {
        Long id = map.get("id");
        log.info("id" + id);
        if (id != null) {
            AdvancePaymentInfo advancePaymentInfo = bmAdvancePaymentInfoService.getById(id);
            String documentStatus = advancePaymentInfo.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                    FsscEums.RECALLED.getValue().equals(documentStatus) ||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
            removeSign(id);
            bmAdvancePaymentInfoService.removeById(id);
            QueryWrapper<AdvancePaymentLine> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq("DOCUMENT_ID", id);
            bmAdvancePaymentLineService.remove(lineQueryWrapper);
            //合同
            QueryWrapper<ContactDetail> contactDetailQueryWrapper = new QueryWrapper<>();
            contactDetailQueryWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            bmContactDetailService.remove(contactDetailQueryWrapper);
            //核销
            QueryWrapper<VerificationDetail> verifQueryWrapper = new QueryWrapper<>();
            verifQueryWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            bmVerificationDetailService.remove(verifQueryWrapper);
            //对公付款清单
            QueryWrapper<GeExpensePaymentList> gePaymentListQueryWrapper = new QueryWrapper<>();
            gePaymentListQueryWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            geExpensePaymentListService.remove(gePaymentListQueryWrapper);
            //删除公务卡
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq("DOCUMENT_ID", id)
                    .eq("DOCUMENT_TYPE", FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            bmBorrowBankService.remove(bankQueryWrapper);
            //停止流程
            bpmProcessBiz.stopProcess(id);
            return Result.success();
        }

        throw new FSSCException(FsscErrorType.NOT_SAVE_NOT_DELETE);
    }

    public void removeSign(Long id) {
        if (id != null) {
            AdvancePaymentInfo advancePaymentInfo = bmAdvancePaymentInfoService.getById(id);
            Long paymentId = advancePaymentInfo.getPayMentId();
            if (paymentId != null) {
                advancePaymentInfo.setPayMentId(null);
                bmAdvancePaymentInfoService.updateById(advancePaymentInfo);
            }
        }
    }


    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增或修改", notes = "新增或修改")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result saveOrUpdata(@RequestBody @Valid AdvancePaymentInfoForm advancePaymentInfoForm) {
        //todo 待验证参数 加注解
        log.info("advancePaymentInfoForm:{}", advancePaymentInfoForm);
        return saveOrUpdateAdvance(advancePaymentInfoForm);
    }

    /**
     * 保存
     * 为什么先删除然后保存后修改，针对行信息（前台编辑表头对行信息进行了删除操作）
     *
     * @param advancePaymentInfoForm
     * @return
     */
    private Result saveOrUpdateAdvance(AdvancePaymentInfoForm advancePaymentInfoForm) {
        //验证单据是否存在
        FsscCommonUtil.valiHasData(advancePaymentInfoForm.getId(), FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        //验证付款单
        //valiPayAmount(advancePaymentInfoForm);
        //验证公务卡或对公付款清单与合同详情的实际付款金额验证
        validCarPayContact(advancePaymentInfoForm);
        AdvancePaymentInfo info = new BeanUtils<AdvancePaymentInfo>().copyObj(advancePaymentInfoForm, AdvancePaymentInfo.class);
        Long projectId = info.getProjectId();
        if (projectId != null) {
            BudgetProject project = budgetProjectService.getById(projectId);
            if (project != null) {
                info.setProjectCode(project.getProjectCode());
                info.setProjectName(project.getProjectName());
                info.setProjectUnitCode(project.getResponsibleUnitCode());
                info.setProjectUnitName(project.getResponsibleUnitName());
                info.setFsscCode(project.getFsscCode());
            }
        }
        BigDecimal convert = BigDecimalUtil.convert(info.getTotalSum());
        info.setNoVerAmount(convert);
        info.setHasVerAmount(BigDecimal.ZERO);
        info.setUnpaidAmount(convert);
        info.setAmountPaid(BigDecimal.ZERO);
        //todo 单据编号生成规则待出方案
        boolean b = bmAdvancePaymentInfoService.saveOrUpdate(info);
        if (b) {
            //行信息保存 先删除原有的，再新增
            QueryWrapper<AdvancePaymentLine> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq("DOCUMENT_ID", info.getId());
            List<AdvancePaymentLineForm> advancePaymentMoneyLineList = advancePaymentInfoForm.getAdvancePaymentLineFormList();
            if (CollectionUtils.isNotEmpty(advancePaymentMoneyLineList)) {
                List<Long> cuLineIds = advancePaymentMoneyLineList.stream().map(k -> k.getId()).collect(Collectors.toList());
                cuLineIds.removeAll(Collections.singleton(null));
                advancePaymentMoneyLineList.forEach(e -> e.setDocumentId(info.getId()));
                //根据对公预付款id查询所有行信息
                if (CollectionUtils.isNotEmpty(cuLineIds)) {
                    lineQueryWrapper.notIn("id", cuLineIds);
                }
                bmAdvancePaymentLineService.remove(lineQueryWrapper);
                List<AdvancePaymentLine> lines = new BeanUtils<AdvancePaymentLine>().copyObjs(advancePaymentMoneyLineList, AdvancePaymentLine.class);
                if (CollectionUtils.isNotEmpty(lines)) {
                    for (AdvancePaymentLine line : lines) {
                        line.setHasVerAmount(BigDecimal.ZERO);
                        line.setNoVerAmount(line.getPrepaidAmount());
                        line.setDocumentId(info.getId());
                        line.setDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
                    }
                    bmAdvancePaymentLineService.saveOrUpdateBatch(lines);
                }
            } else {
                bmAdvancePaymentLineService.remove(lineQueryWrapper);
            }
            //合同详情 保存 先删除 再新增
            QueryWrapper<ContactDetail> contactQueryWrapper = new QueryWrapper<>();
            contactQueryWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            List<ContactDetailForm> contactDetailForm = advancePaymentInfoForm.getContactDetailFormList();
            if (CollectionUtils.isNotEmpty(contactDetailForm)) {
                List<ContactDetail> contactDetails = new BeanUtils<ContactDetail>().copyObjs(contactDetailForm, ContactDetail.class);
                //收集前台传过来的id
                List<Long> longList = contactDetailForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                //移除id为null的数据（针对新增的）
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(longList)) {
                    contactQueryWrapper.notIn("id", longList);
                }
                bmContactDetailService.remove(contactQueryWrapper);
                if (CollectionUtils.isNotEmpty(contactDetails)) {
                    for (ContactDetail contactDetail : contactDetails) {
                        //contactDetail.setDocumentNum(info.getDocumentNum());
                        contactDetail.setHasVerAmount(BigDecimal.ZERO);
                        contactDetail.setNoVerAmount(contactDetail.getActualPlayAmount());
                        contactDetail.setDocumentId(info.getId());
                        contactDetail.setDocumentType(FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
                    }
                    bmContactDetailService.saveOrUpdateBatch(contactDetails);

                }
            } else {
                bmContactDetailService.remove(contactQueryWrapper);
            }
            //公务卡 保存 先删除 再新增
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE",
                    FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            List<BmBorrowBankForm> bmBorrowBankForms = advancePaymentInfoForm.getBmBorrowBankForms();
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
                bank.setDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            }
            if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
                bmBorrowBankService.saveOrUpdateBatch(bmBorrowBanks);
            }

            //核销详情 保存 先删除 再新增
            QueryWrapper<VerificationDetail> verificationQueryWrapper = new QueryWrapper<>();
            verificationQueryWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            List<VerificationDetailForm> bmVerificationForm = advancePaymentInfoForm.getVerificationDetailFormList();
            if (CollectionUtils.isNotEmpty(bmVerificationForm)) {
                List<VerificationDetail> bmVerifications = new BeanUtils<VerificationDetail>().copyObjs(bmVerificationForm, VerificationDetail.class);
                List<Long> longList = bmVerificationForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(longList)) {
                    verificationQueryWrapper.notIn("id", longList);
                }
                bmVerificationDetailService.remove(verificationQueryWrapper);
                if (CollectionUtils.isNotEmpty(bmVerifications)) {
                    for (VerificationDetail verification : bmVerifications) {
                        verification.setDocumentNum(info.getDocumentNum());
                        verification.setDocumentId(info.getId());
                        verification.setDocumentType(FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
                    }
                    bmVerificationDetailService.saveOrUpdateBatch(bmVerifications);
                }
            } else {
                bmVerificationDetailService.remove(verificationQueryWrapper);
            }
            //对公付款清单，先删除后保存
            QueryWrapper<GeExpensePaymentList> geExpensePaymentWrapper = new QueryWrapper<>();
            geExpensePaymentWrapper.eq("DOCUMENT_ID", info.getId()).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            geExpensePaymentListService.remove(geExpensePaymentWrapper);
            List<GeExpensePaymentListForm> geExpensePaymentFormList = advancePaymentInfoForm.getGeExpensePaymentFormList();
            if (CollectionUtils.isNotEmpty(geExpensePaymentFormList)) {
                for (GeExpensePaymentListForm form : geExpensePaymentFormList) {
                    GeExpensePaymentList paymentList = new BeanUtils<GeExpensePaymentList>().copyObj(form, GeExpensePaymentList.class);
                    paymentList.setPayStatus("NO_PAY");
                    paymentList.setDocumentId(info.getId());
                    paymentList.setDocumentType(FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
                    geExpensePaymentListService.saveOrUpdate(paymentList);
                }
            }

            //文件列表保存
            List<Long> fileIds = advancePaymentInfoForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, info.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                files.stream().forEach(ka -> ka.setDocumentId(info.getId()));
                fileService.saveOrUpdateBatch(files);
            }

            return Result.success(new BeanUtils<AdvancePaymentInfoVo>().copyObj(bmAdvancePaymentInfoService.getById(info.getId()), AdvancePaymentInfoVo.class));
        }

        throw new FSSCException(FsscErrorType.DATA_IS_NOT_LATEST);
    }

    /*public void valiPayAmount(AdvancePaymentInfoForm advancePaymentInfoForm) {
        List<ContactDetailForm> contactDetailFormList = advancePaymentInfoForm.getContactDetailFormList();
        List<AdvancePaymentLineForm> advancePaymentLineFormList = advancePaymentInfoForm.getAdvancePaymentLineFormList();
        List<GeExpensePaymentListForm> geExpensePaymentFormList = advancePaymentInfoForm.getGeExpensePaymentFormList();
        if (CollectionUtils.isNotEmpty(geExpensePaymentFormList)) {
            BigDecimal agreedPaymentAmopayAmountunt = BigDecimal.ZERO;
            for (GeExpensePaymentListForm geExpensePaymentListForm: geExpensePaymentFormList) {
                //付款金额
                agreedPaymentAmopayAmountunt=agreedPaymentAmopayAmountunt.add(BigDecimalUtil.convert(geExpensePaymentListForm.getPayAmount()));
            }
            if (CollectionUtils.isNotEmpty(contactDetailFormList)) {
                BigDecimal agreedPaymentAmount = BigDecimal.ZERO;
                for (ContactDetailForm contactDetailForm : contactDetailFormList) {
                    //约定付款金额
                    agreedPaymentAmount = agreedPaymentAmount.add(BigDecimalUtil.convert(contactDetailForm.getAgreedPaymentAmount()));
                }
                if (agreedPaymentAmopayAmountunt.compareTo(agreedPaymentAmount) != 0) {
                    throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_MUST_EQ__CONTRACT);
                }
            }
            if (CollectionUtils.isNotEmpty(advancePaymentLineFormList)) {
                BigDecimal prepaidAmount = BigDecimal.ZERO;
                for (AdvancePaymentLineForm advancePaymentLineForm : advancePaymentLineFormList) {
                    //预付金额
                    prepaidAmount = prepaidAmount.add(BigDecimalUtil.convert(advancePaymentLineForm.getPrepaidAmount()));
                }
                if (agreedPaymentAmopayAmountunt.compareTo(prepaidAmount) != 0) {
                    throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_MUST_EQ_GEEXPENSE);
                }
            }
        }
    }*/

    public void validCarPayContact(AdvancePaymentInfoForm advancePaymentInfoForm) {
        List<ContactDetailForm> contactDetailFormList = advancePaymentInfoForm.getContactDetailFormList();//合同详情
        List<BmBorrowBankForm> bmBorrowBankForms = advancePaymentInfoForm.getBmBorrowBankForms();//公务卡
        List<GeExpensePaymentListForm> geExpensePaymentFormList = advancePaymentInfoForm.getGeExpensePaymentFormList();//对公付款清单
        if (CollectionUtils.isNotEmpty(contactDetailFormList)) {
            BigDecimal contactDetailAmountunt = BigDecimal.ZERO;//实际付款金额合计（为公务卡的）
            BigDecimal actualPayAmountunt = BigDecimal.ZERO;//实际付款金额合计（为“对公支付”“限额支票”）
            BigDecimal bmAmount = BigDecimal.ZERO;//公务卡的行合计金额
            BigDecimal geAmount = BigDecimal.ZERO;//对公付款的行合计金额
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
            if (bmAmount.compareTo(contactDetailAmountunt) != 0) {
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_BMBORROW_BANK);
            }
            if (geAmount.compareTo(actualPayAmountunt) != 0) {
                throw new FSSCException(FsscErrorType.ADVANCE_PAYMENT_INFO_GEEXPENSE);
            }
        }
    }


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

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/sumbitAdvance")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result sumbitAdvance(@RequestBody @Valid AdvancePaymentInfoForm advancePaymentInfoForm) {
        Result<AdvancePaymentInfoVo> queryDatas = saveOrUpdateAdvance(advancePaymentInfoForm);
        AdvancePaymentInfoVo advancePaymentInfo = queryDatas.getData();
        Long id = advancePaymentInfo.getId();
        if (id == null) {
            throw new FSSCException(FsscErrorType.ADVANCE_PLAYMENT_NO_EMPTY);
        }
        if (FsscEums.REJECTED.getValue().equals(advancePaymentInfo.getDocumentStatus())
                || FsscEums.NEW.getValue().equals(advancePaymentInfo.getDocumentStatus())
                || FsscEums.RECALLED.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
            ProcessValueForm valueForm = new ProcessValueForm();
            valueForm.setRequest("N");
            valueForm.setProjectId(advancePaymentInfo.getProjectId());
            valueForm.setTotalAmount(advancePaymentInfo.getTotalSum().toString());
            valueForm.setMainTypeCode(advancePaymentInfo.getMainTypeCode());
            valueForm.setDocumentId(id);
            valueForm.setDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            valueForm.setCreateBy(advancePaymentInfo.getCreateBy());
            valueForm.setDeptCode(advancePaymentInfo.getDeptCode());
            valueForm.setProjectCode(advancePaymentInfo.getProjectCode());
            valueForm.setUnitCode(advancePaymentInfo.getUnitCode());
            valueForm.setHasContract("Y");
            Map<String, String> andAddProcessValue =
                    baseBpmProcessValService.getAndSaveProcessValue(valueForm);
            prepayService.modifyContactAmount(true, id, FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            ReSubmitProcessForm form = new ReSubmitProcessForm();
            form.setProcessVariables(andAddProcessValue);
            form.setDocumentId(id);
            form.setDocumentType(FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            form.setDocumentNum(advancePaymentInfo.getDocumentNum());
            form.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
            form.setBudgetWarningCheck(advancePaymentInfoForm.getBudgetWarningCheck());
            accountVoucherToEbsService.generatePrefabricatedCredentials(FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue(), form.getDocumentId());
            if (FsscEums.FIRST_SUBMIT.getValue().equals(advancePaymentInfoForm.getReSubmitType())) {
                bpmProcessBiz.startProcess(form);
            } else {
                form.setReSubmitType(advancePaymentInfoForm.getReSubmitType());
                bpmProcessBiz.reSubmit(form);
            }

            return Result.success();
        }
        throw new FSSCException(FsscErrorType.SUBMIT_NEW_REJECTED);
    }


    @ApiOperation(value = "查询合同", notes = "查询合同")
    @GetMapping(value = "findContractInfo")
    public Result<List<ContactDetailVo>> findContractInfo(@RequestParam Long documentId, @RequestParam String documentType) {
        QueryWrapper<ContactDetail> contactDetailQueryWrapper = new QueryWrapper<>();
        contactDetailQueryWrapper.eq(ContactDetail.DOCUMENT_ID, documentId).eq(ContactDetail.DOCUMENT_TYPE, documentType);
        List<ContactDetail> list = bmContactDetailService.list(contactDetailQueryWrapper);
        return Result.success(new BeanUtils<ContactDetailVo>().copyObjs(list, ContactDetailVo.class));
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
        Result<AdvancePaymentInfoVo> resultData = loadDataByadvanceId(id);
        AdvancePaymentInfoVo advancePaymentInfoVo = resultData.getData();
        String documentStatus = advancePaymentInfoVo.getDocumentStatus();
        String documentNum = advancePaymentInfoVo.getDocumentNum();
        if (pyPamentOrderInfoService.selectCount(documentNum)) {
            QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
            PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(queryWrapper);
            if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                throw new FSSCException(FsscErrorType.DOCUMENT_READY_PAYMENT);
            }
        }
        BigDecimal noPaidMount = advancePaymentInfoVo.getUnpaidAmount();
        if (noPaidMount == null) {
            noPaidMount = BigDecimal.ZERO;
        }
        BigDecimal zero = BigDecimal.ZERO;
        if (FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus) && noPaidMount.compareTo(zero) != 0) {
            PyPamentOrderInfoVo pamentOrderInfoVo = new PyPamentOrderInfoVo();
            pamentOrderInfoVo.setDocumentStatus(advancePaymentInfoVo.getDocumentStatus());
            // pamentOrderInfoVo.setDocumentNum();
            // pamentOrderInfoVo.setBankAccount();
            //pamentOrderInfoVo.setBankType();
            pamentOrderInfoVo.setCost(advancePaymentInfoVo.getCost());
            pamentOrderInfoVo.setCreateBy(advancePaymentInfoVo.getCreateBy());
            pamentOrderInfoVo.setCreateTime(advancePaymentInfoVo.getCreateTime());
            pamentOrderInfoVo.setCreateUserName(advancePaymentInfoVo.getCreateUserName());
            pamentOrderInfoVo.setCurrencyCode(advancePaymentInfoVo.getCurrencyCode());
            pamentOrderInfoVo.setDeptCode(advancePaymentInfoVo.getDeptCode());
            pamentOrderInfoVo.setDeptId(advancePaymentInfoVo.getDeptId());
            pamentOrderInfoVo.setDeptName(advancePaymentInfoVo.getDeptName());
            pamentOrderInfoVo.setIsAfterPatch(advancePaymentInfoVo.getIsAfterPatch());
            pamentOrderInfoVo.setNoPaidAmount(advancePaymentInfoVo.getUnpaidAmount());
            pamentOrderInfoVo.setPayDocumentNum(advancePaymentInfoVo.getDocumentNum());
            pamentOrderInfoVo.setPayDocumentType("ADP_ADVANCE_PAYMENT_INFO");
            pamentOrderInfoVo.setPaidAmount(advancePaymentInfoVo.getAmountPaid());
            pamentOrderInfoVo.setPaymentType(advancePaymentInfoVo.getPaymentType());
            pamentOrderInfoVo.setPayStatus(advancePaymentInfoVo.getPayStatus());
            pamentOrderInfoVo.setRemark(advancePaymentInfoVo.getRemark());
            pamentOrderInfoVo.setTotalAmount(advancePaymentInfoVo.getTotalSum());
            pamentOrderInfoVo.setTotalAmountOtherCurrency(advancePaymentInfoVo.getTotalSumPosition());
            pamentOrderInfoVo.setUnitCode(advancePaymentInfoVo.getUnitCode());
            pamentOrderInfoVo.setUnitId(advancePaymentInfoVo.getUnitId());
            pamentOrderInfoVo.setUnitName(advancePaymentInfoVo.getUnitName());
            pamentOrderInfoVo.setUpdateTime(advancePaymentInfoVo.getUpdateTime());
            pamentOrderInfoVo.setUpdateBy(advancePaymentInfoVo.getUpdateBy());
            pamentOrderInfoVo.setVersion(advancePaymentInfoVo.getVersion());
            List<GeExpensePaymentListVo> geExpensePaymentList = advancePaymentInfoVo.getGeExpensePaymentFormList();
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
                    pyPamentBusinessLineVo.setConnectDocumentId(advancePaymentInfoVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(advancePaymentInfoVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(geExpensePaymentListVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PUBLIC");
                    pyPamentBusinessLineVo.setConnectDocumentType("ADP_ADVANCE_PAYMENT_INFO");
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
                    pyPamentBusinessLineVo.setUnitId(advancePaymentInfoVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(advancePaymentInfoVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(advancePaymentInfoVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(advancePaymentInfoVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(advancePaymentInfoVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("合同预付款");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(geExpensePaymentListVo.getId());
                    pyPamentPublicLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentPublicLineVos(pyPamentPublicLineVos);
            }
            List<BmBorrowBankVo> businessCards = advancePaymentInfoVo.getBusinessCards();
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
                    pyPamentBusinessLineVo.setConnectDocumentId(advancePaymentInfoVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(advancePaymentInfoVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("BUSINESS_CARD");
                    pyPamentBusinessLineVo.setConnectDocumentType("ADP_ADVANCE_PAYMENT_INFO");
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
                    pyPamentBusinessLineVo.setUnitId(advancePaymentInfoVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(advancePaymentInfoVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(advancePaymentInfoVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(advancePaymentInfoVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(advancePaymentInfoVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("合同预付款");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                    pyPamentBusinessLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentBusinessLineVos(pyPamentBusinessLineVos);
            }
            return Result.success(pamentOrderInfoVo);
        }
        throw new FSSCException(FsscErrorType.NOT_GENERATE_BILL_PAYMENT);
    }

    @Autowired
    private IBankInfoService bankInfoService;

    @GetMapping(value = "/loadsideSubjectCode")
    @ApiOperation(value = "根据我方签于code查询", notes = "根据我方签于code查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result queryUnitInfo(@Valid BaseContractPlanLineQueryForm baseContractPlanLineQueryForm) {
        String sideSubjectCode = baseContractPlanLineQueryForm.getSideSubjectCode();
        String sideSubjectName = baseContractPlanLineQueryForm.getSideSubjectName();
        AssertUtils.asserts(StringUtil.isNotEmpty(sideSubjectCode), FsscErrorType.NOT_FIND_DATA);
        QueryWrapper<BankUnitInfo> bankWrapper = new QueryWrapper<>();
        bankWrapper.eq("UNIT_CODE", sideSubjectCode);
        List<BankUnitInfo> bankUnitInfos = bankUnitInfoService.list(bankWrapper);
        List<GeExpensePaymentListVo> geExpensePaymentLists = new ArrayList<GeExpensePaymentListVo>();
        if (StringUtil.isNotEmpty(bankUnitInfos)) {
            for (BankUnitInfo bankUnitInfo : bankUnitInfos) {
                GeExpensePaymentListVo geExpensePaymentList = new GeExpensePaymentListVo();
                Long bankId = bankUnitInfo.getBankId();
                if (bankId != null) {
                    BankInfo bank = bankInfoService.getById(bankId);
                    if (bank != null) {
                        geExpensePaymentList.setInterBranchNumber(bank.getInterBranchNumber());
                        geExpensePaymentList.setBankName(bank.getBankName());
                    }
                }
                geExpensePaymentList.setAccountName(bankUnitInfo.getBankAccountName());
                geExpensePaymentList.setBankAccount(bankUnitInfo.getBankAccount());
                geExpensePaymentList.setBankId(bankUnitInfo.getBankId());
                geExpensePaymentList.setCommonCreditCode(bankUnitInfo.getCommonCreditCode());
                geExpensePaymentList.setVendorName(sideSubjectName);
                geExpensePaymentList.setBankUnitId(bankUnitInfo.getId());
                geExpensePaymentLists.add(geExpensePaymentList);
            }

        }

        return Result.success(geExpensePaymentLists);
    }

}



