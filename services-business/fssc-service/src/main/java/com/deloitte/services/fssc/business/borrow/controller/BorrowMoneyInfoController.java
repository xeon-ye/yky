package com.deloitte.services.fssc.business.borrow.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.borrow.BorrowMoneyInfoClient;
import com.deloitte.platform.api.fssc.borrow.param.BorrowMoneyInfoQueryForm;
import com.deloitte.platform.api.fssc.borrow.vo.*;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
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
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :   BorrowMoneyInfo控制器实现类
 * @Modified :
 */
@Api(tags = "借款操作接口")
@Slf4j
@Controller
@RequestMapping(value = "/borrow/borrow-money-info")
public class BorrowMoneyInfoController implements BorrowMoneyInfoClient {

    @Autowired
    public IBorrowMoneyInfoService borrowMoneyInfoService;
    @Autowired
    private IBorrowMoneyLineService borrowMoneyLineService;
    @Autowired
    private IBmBorrowBankService bmBorrowBankService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private BpmProcessBiz bpmProcessBiz;
    @Autowired
    private IFileService fileService;
    @Autowired
    private ITasTravelApplyInfoService tasTravelApplyInfoService;
    @Autowired
    private IGeExpensePaymentListService geExpensePaymentListService;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;
    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;
    @Autowired
    private IBudgetProjectService budgetProjectService;

    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    @Transactional
    public Result deleteById(@ApiParam(value = "借款ID") @RequestBody Map<String, Long> map) {
        Long borrowId = map.get("borrowId");
        if (borrowId != null) {
            BorrowMoneyInfo moneyInfo = borrowMoneyInfoService.getById(borrowId);
            String documentStatus = moneyInfo.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                    FsscEums.RECALLED.getValue().equals(documentStatus) ||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);

            removeLock(borrowId);
            borrowMoneyInfoService.removeById(borrowId);
            QueryWrapper<BorrowMoneyLine> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, borrowId);
            borrowMoneyLineService.remove(lineQueryWrapper);
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, borrowId)
                    .eq(BorrowMoneyLine.DOCUMENT_TYPE, FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
            bmBorrowBankService.remove(bankQueryWrapper);
            //停止流程
            bpmProcessBiz.stopProcess(borrowId);
            return Result.success();
        }
        throw new FSSCException(FsscErrorType.NOT_SAVE_NOT_DELETE);
    }


    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增修改保存单据", notes = "新增修改保存单据")
    @ResponseBody
    @Transactional
    public Result<BorrowMoneyInfoVo> saveOrUpdata(@RequestBody @Valid BorrowMoneyInfoForm borrowMoneyInfoForm) {
        //todo 待验证参数 加注解
        log.info("borrowMoneyInfoForm:{}", borrowMoneyInfoForm.toString());

        return saveOrUpdateBorrow(borrowMoneyInfoForm);
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<BorrowMoneyInfoVo> doSubmitProcess(@RequestBody @Valid BorrowMoneyInfoForm borrowMoneyInfoForm
    ) {
        //todo 待验证参数 加注解
        log.info("borrowMoneyInfoForm:{}", borrowMoneyInfoForm.toString());
        Result<BorrowMoneyInfoVo> result = saveOrUpdateBorrow(borrowMoneyInfoForm);
        BorrowMoneyInfoVo data = result.getData();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(data.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(data.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(data.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest(data.getApplyForId()!=null?"Y":"N");
        valueForm.setProjectId(data.getProjectId());
        valueForm.setTotalAmount(data.getBorrowAmount().toString());
        valueForm.setMainTypeCode(data.getMainTypeCode());
        valueForm.setDocumentId(data.getId());
        valueForm.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
        valueForm.setCreateBy(data.getCreateBy());
        valueForm.setDeptCode(data.getDeptCode());
        valueForm.setProjectCode(data.getProjectCode());
        valueForm.setUnitCode(data.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);
        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(data.getId());
        startForm.setDocumentNum(data.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        startForm.setBudgetWarningCheck(borrowMoneyInfoForm.getBudgetWarningCheck());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue(), data.getId());//生成预制凭证
        if (FsscEums.FIRST_SUBMIT.getValue().equals(borrowMoneyInfoForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(borrowMoneyInfoForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }

        return result;
    }


    /**
     * 保存
     *
     * @param borrowMoneyInfoForm
     * @return
     */
    private Result<BorrowMoneyInfoVo> saveOrUpdateBorrow(BorrowMoneyInfoForm borrowMoneyInfoForm) {
        //验证单据是否存在
        FsscCommonUtil.valiHasData(borrowMoneyInfoForm.getId(), FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
        BorrowMoneyInfo info = new BeanUtils<BorrowMoneyInfo>().copyObj(borrowMoneyInfoForm, BorrowMoneyInfo.class);
        //验证时间
        validateForm(borrowMoneyInfoForm);
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

        boolean b = borrowMoneyInfoService.saveOrUpdate(info);
        //对关联的申请单做变更
        changeTravelIsConnect(borrowMoneyInfoForm.getId(), borrowMoneyInfoForm.getApplyForId());
        if (b) {
            //行信息保存 先删除原有的，再新增
            QueryWrapper<BorrowMoneyLine> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, info.getId());
            List<BorrowMoneyLineForm> borrowMoneyLineList = borrowMoneyInfoForm.getBorrowMoneyLineList();
            List<Long> cuLineIds = borrowMoneyLineList.stream().map(k -> k.getId()).collect(Collectors.toList());
            cuLineIds.removeAll(Collections.singleton(null));
            for (BorrowMoneyLineForm form : borrowMoneyLineList) {
                form.setDocumentId(info.getId());
                form.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
            }
            //根据借款id查询所有行信息
            if (CollectionUtils.isNotEmpty(cuLineIds)) {
                lineQueryWrapper.notIn("id", cuLineIds);
            }
            borrowMoneyLineService.remove(lineQueryWrapper);
            List<BorrowMoneyLine> lines = new BeanUtils<BorrowMoneyLine>().copyObjs(borrowMoneyLineList, BorrowMoneyLine.class);
            if (CollectionUtils.isNotEmpty(lines)) {
                for (BorrowMoneyLine line : lines) {
                    line.setNoVerAmount(line.getBorrowAmount());
                    line.setHasVerAmount(BigDecimal.ZERO);
                }
                borrowMoneyLineService.saveOrUpdateBatch(lines);
            }

            //工资卡 公务卡 保存 先删除 再新增
            QueryWrapper<BmBorrowBank> bankQueryWrapper = new QueryWrapper<>();
            bankQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID, info.getId()).eq(BorrowMoneyLine.DOCUMENT_TYPE,
                    FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
            List<BmBorrowBankForm> bmBorrowBankForms = borrowMoneyInfoForm.getBmBorrowBankForms();
            List<BmBorrowBank> bmBorrowBanks = new BeanUtils<BmBorrowBank>().copyObjs(bmBorrowBankForms, BmBorrowBank.class);
            List<Long> longList = bmBorrowBankForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));
            if (CollectionUtils.isNotEmpty(longList)) {
                bankQueryWrapper.notIn("id", longList);
            }
            bmBorrowBankService.remove(bankQueryWrapper);

            for (BmBorrowBank bank : bmBorrowBanks) {
                bank.setDocumentNum(info.getDocumentNum());
                bank.setDocumentId(info.getId());
                bank.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
            }
            if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
                bmBorrowBankService.saveOrUpdateBatch(bmBorrowBanks);
            }
            //文件列表保存
            List<Long> fileIds = borrowMoneyInfoForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, info.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                AssertUtils.asserts(CollectionUtils.isNotEmpty(files),
                        FsscErrorType.FILE_IS_NULL);
                files.stream().forEach(ka -> ka.setDocumentId(info.getId()));
                fileService.saveOrUpdateBatch(files);
            }

            //对公付款清单
            List<GeExpensePaymentListForm> geExpensePaymentListForms = borrowMoneyInfoForm.getGeExpensePaymentListForms();
            QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper = new QueryWrapper<>();
            paymentListQueryWrapper.eq(GeExpensePaymentList.DOCUMENT_ID, info.getId())
                    .eq(GeExpensePaymentList.DOCUMENT_TYPE, FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
            List<Long> paymentListIds = geExpensePaymentListForms.stream().map(lll -> lll.getId()).collect(Collectors.toList());
            paymentListIds.removeAll(Collections.singleton(null));

            if (CollectionUtils.isNotEmpty(paymentListIds)) {
                paymentListQueryWrapper.notIn(GeExpensePaymentList.ID, paymentListIds);
            }
            geExpensePaymentListService.remove(paymentListQueryWrapper);
            if (CollectionUtils.isNotEmpty(geExpensePaymentListForms)) {
                List<GeExpensePaymentList> expensePaymentLists =
                        new BeanUtils<GeExpensePaymentList>().copyObjs(geExpensePaymentListForms, GeExpensePaymentList.class);
                for (GeExpensePaymentList expensePaymentList : expensePaymentLists) {
                    expensePaymentList.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                    expensePaymentList.setDocumentId(info.getId());
                }
                geExpensePaymentListService.saveOrUpdateBatch(expensePaymentLists);
            }


            return Result.success(new BeanUtils<BorrowMoneyInfoVo>().copyObj(borrowMoneyInfoService.getById(info.getId()), BorrowMoneyInfoVo.class));
        }

        throw new FSSCException(FsscErrorType.DATA_IS_NOT_LATEST);
    }


    /**
     * 验证借款单
     *
     * @param borrowMoneyInfoForm
     */
    private void validateForm(BorrowMoneyInfoForm borrowMoneyInfoForm) {
        //时间验证
        LocalDateTime repaymentTime = borrowMoneyInfoForm.getRepaymentTime();
        LocalDateTime now = LocalDateTime.now();
        boolean before = now.isBefore(repaymentTime);
        AssertUtils.asserts(before, FsscErrorType.REPAYMENT_TIME_MUST_BE_AFTER_NOW);

        //金额验证
        List<BorrowMoneyLineForm> borrowMoneyLineList = borrowMoneyInfoForm.getBorrowMoneyLineList();
        AssertUtils.asserts(CollectionUtils.isNotEmpty(borrowMoneyLineList), FsscErrorType.GE_LINE_IS_NOT_EMPTY);
        BigDecimal salaryAmount = BigDecimal.ZERO;
        BigDecimal businessAmount = BigDecimal.ZERO;
        BigDecimal publicAmount = BigDecimal.ZERO;
        for (BorrowMoneyLineForm lineForm : borrowMoneyLineList) {
            String paymentType = lineForm.getPaymentType();
            if ("BUSINESS_CARD".equals(paymentType)) {
                businessAmount = businessAmount.add(BigDecimalUtil.convert(lineForm.getBorrowAmount()));
            }
            if ("SALARY_CARD".equals(paymentType) || "CASH".equals(paymentType)) {
                salaryAmount = salaryAmount.add(BigDecimalUtil.convert(lineForm.getBorrowAmount()));
            }
            if ("LIMIT_CHECK".equals(paymentType) || "PUBLIC_PAYMENT".equals(paymentType)) {
                publicAmount = publicAmount.add(BigDecimalUtil.convert(lineForm.getBorrowAmount()));
            }
        }

        List<BmBorrowBankForm> bmBorrowBankForms = borrowMoneyInfoForm.getBmBorrowBankForms();

        BigDecimal cardBusinussAmount = BigDecimal.ZERO;
        BigDecimal cardSarlaryAmount = BigDecimal.ZERO;
        BigDecimal cardPublicAmount = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(bmBorrowBankForms)) {
            for (BmBorrowBankForm form : bmBorrowBankForms) {
                if ("BUSINESS".equals(form.getGetOrReturn())) {
                    cardBusinussAmount = cardBusinussAmount.add(BigDecimalUtil.convert(form.getBorrowAmount()));
                    BigDecimal bAmount = BigDecimalUtil.convert(form.getBorrowAmount());
                    BigDecimal tAmount = BigDecimalUtil.convert(form.getTransactionAmount());
                    AssertUtils.asserts(bAmount.compareTo(tAmount) <= 0,
                            FsscErrorType.BUSINESS_BORROW_AMOUNT_MUST_TH_TRAMOUNT);
                }
                if ("SALARY".equals(form.getGetOrReturn())) {
                    cardSarlaryAmount = cardSarlaryAmount.add(BigDecimalUtil.convert(form.getBorrowAmount()));
                }
            }
        }
        List<GeExpensePaymentListForm> geExpensePaymentListForms = borrowMoneyInfoForm.getGeExpensePaymentListForms();
        if (CollectionUtils.isNotEmpty(geExpensePaymentListForms)) {
            for (GeExpensePaymentListForm listForm : geExpensePaymentListForms) {
                cardPublicAmount = cardPublicAmount.add(BigDecimalUtil.convert(listForm.getPayAmount()));
            }
        }

        AssertUtils.asserts(salaryAmount.compareTo(cardSarlaryAmount) == 0, FsscErrorType.SALARY_AMOUNT_NOT_EQ);
        AssertUtils.asserts(businessAmount.compareTo(cardBusinussAmount) == 0, FsscErrorType.BUSINESS_AMOUNT_NOT_EQ);
        AssertUtils.asserts(publicAmount.compareTo(cardPublicAmount) == 0, FsscErrorType.PUBLIC_AMOUNT_NOT_EQ);

        //附件数量验证
        List<Long> fileIds = borrowMoneyInfoForm.getFileIds();
        if (fileIds != null) {
            fileIds.removeAll(Collections.singleton(null));
            AssertUtils.asserts(fileIds.size() <= borrowMoneyInfoForm.getAttachCount()
                    , FsscErrorType.FILEIDS_MUSTBE_LE_ATTCHFILES);
        }


    }


    /**
     * 修改差旅申请单是否被关联
     */
    private void changeTravelIsConnect(Long borrowId, Long travelId) {
        if (borrowId != null) {
            BorrowMoneyInfo moneyInfo = borrowMoneyInfoService.getById(borrowId);
            Long applyForId = moneyInfo.getApplyForId();
            changeTravelIsBorrowConnect(applyForId, "N");
        }
        changeTravelIsBorrowConnect(travelId, "Y");
    }

    private void changeTravelIsBorrowConnect(Long id, String status) {
        if (id != null) {
            TasTravelApplyInfo applyInfo = tasTravelApplyInfoService.getById(id);
            if (applyInfo != null) {
                applyInfo.setIsBorrowConnect(status);
                tasTravelApplyInfoService.updateById(applyInfo);
            }
        }

    }

    /**
     * 删除后解除借款单和差旅申请的关系
     *
     * @param id
     * @return
     */

    public void removeLock(Long id) {
        if (id != null) {
            BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getById(id);
            Long paymentId = borrowMoneyInfo.getPayMentId();
            if (paymentId != null) {
                borrowMoneyInfo.setPayMentId(null);
                borrowMoneyInfoService.updateById(borrowMoneyInfo);
            }
            Long applyForId = borrowMoneyInfo.getApplyForId();
            if (applyForId != null) {
                TasTravelApplyInfo tasTravelApplyInfo = tasTravelApplyInfoService.getById(applyForId);
                if (tasTravelApplyInfo != null) {
                    tasTravelApplyInfo.setIsBorrowConnect("N");
                    tasTravelApplyInfoService.updateById(tasTravelApplyInfo);
                }
            }
        }
    }

    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<BorrowMoneyInfoVo>> pageCondition(BorrowMoneyInfoQueryForm borrowMoneyInfoForm) {
        IPage<BorrowMoneyInfo> borrowMoneyInfoIPage = borrowMoneyInfoService.selectPage(borrowMoneyInfoForm);
        IPage<BorrowMoneyInfoVo> borrowMoneyInfoVos =
                new BeanUtils<BorrowMoneyInfoVo>().copyPageObjs(borrowMoneyInfoIPage, BorrowMoneyInfoVo.class);
        return Result.success(borrowMoneyInfoVos);
    }


    @GetMapping(value = "/loadDataByBorrowId")
    @ApiOperation(value = "查询详情根据借款ID", notes = "查询详情根据借款ID")
    @ResponseBody
    public Result<BorrowMoneyInfoVo> loadDataByBorrowId(@ApiParam(value = "借款ID") @RequestParam(value = "borrowId") Long borrowId) {
        BorrowMoneyInfoVo infoById = borrowMoneyInfoService.findInfoById(borrowId);
        return Result.success(infoById);
    }


    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(BorrowMoneyInfoQueryForm borrowMoneyInfoForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(borrowMoneyInfoForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<BorrowMoneyInfo> records = borrowMoneyInfoService.selectPage(borrowMoneyInfoForm).getRecords();
        String[] title = {"序号", "单据编号", "借款人", "支出大类", "归属单位", "归属部门", "借款金额", "未核销借款"
                , "创建日期", "单据状态", "支付状态"};
        String fileName = "借款信息列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BorrowMoneyInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getMainTypeName();
            content[i][4] = vo.getUnitName();
            content[i][5] = vo.getDeptName();
            content[i][6] = StringUtil.objectToString(vo.getBorrowAmount());
            content[i][7] = StringUtil.objectToString(vo.getNoVerAmount());
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
     * 已入账，未支付金额大于0
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/generatePayBill")
    @ApiOperation(value = "生成付款单", notes = "生成付款单")
    @ResponseBody
    public Result<PyPamentOrderInfoVo> generatePayBill(@ApiParam(value = "借款ID") @RequestParam(value = "id") Long id) {
        log.info("search with geGeneralExpenseQueryForm:", id);
        Result<BorrowMoneyInfoVo> resultData = loadDataByBorrowId(id);
        BorrowMoneyInfoVo borrowMoneyInfoVo = resultData.getData();
        String documentNum = borrowMoneyInfoVo.getDocumentNum();
        if (pyPamentOrderInfoService.selectCount(documentNum)) {
            QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
            PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(queryWrapper);
             if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                 throw new FSSCException(FsscErrorType.DOCUMENT_READY_PAYMENT);
             }
        }
        String documentStatus = borrowMoneyInfoVo.getDocumentStatus();
        BigDecimal noPaidMount = borrowMoneyInfoVo.getNoPaidAmount();
        if (noPaidMount == null) {
            noPaidMount = BigDecimal.ZERO;
        }
        BigDecimal zero = BigDecimal.ZERO;
        if (FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus) && noPaidMount.compareTo(zero) != 0) {
            PyPamentOrderInfoVo pamentOrderInfoVo = new PyPamentOrderInfoVo();
            pamentOrderInfoVo.setDocumentStatus(borrowMoneyInfoVo.getDocumentStatus());
            // pamentOrderInfoVo.setDocumentNum();
            // pamentOrderInfoVo.setBankAccount();
            //pamentOrderInfoVo.setBankType();
            pamentOrderInfoVo.setCost(borrowMoneyInfoVo.getCost());
            pamentOrderInfoVo.setCreateBy(borrowMoneyInfoVo.getCreateBy());
            pamentOrderInfoVo.setCreateTime(borrowMoneyInfoVo.getCreateTime());
            pamentOrderInfoVo.setCreateUserName(borrowMoneyInfoVo.getCreateUserName());
            pamentOrderInfoVo.setCurrencyCode(borrowMoneyInfoVo.getCurrencyCode());
            pamentOrderInfoVo.setDeptCode(borrowMoneyInfoVo.getDeptCode());
            pamentOrderInfoVo.setDeptId(borrowMoneyInfoVo.getDeptId());
            pamentOrderInfoVo.setDeptName(borrowMoneyInfoVo.getDeptName());
            pamentOrderInfoVo.setIsAfterPatch(borrowMoneyInfoVo.getIsAfterPatch());
            pamentOrderInfoVo.setNoPaidAmount(borrowMoneyInfoVo.getNoPaidAmount());
            pamentOrderInfoVo.setPayDocumentNum(borrowMoneyInfoVo.getDocumentNum());
            pamentOrderInfoVo.setPayDocumentType("BM_BORROW_MONEY_INFO");
            pamentOrderInfoVo.setPaidAmount(borrowMoneyInfoVo.getPaidAmount());
            pamentOrderInfoVo.setPaymentType(borrowMoneyInfoVo.getPaymentType());
            pamentOrderInfoVo.setPayStatus(borrowMoneyInfoVo.getPayStatus());
            pamentOrderInfoVo.setRemark(borrowMoneyInfoVo.getRemark());
            pamentOrderInfoVo.setTotalAmount(borrowMoneyInfoVo.getBorrowAmount());
            pamentOrderInfoVo.setTotalAmountOtherCurrency(borrowMoneyInfoVo.getBorrowAmountOtherCurrency());
            pamentOrderInfoVo.setUnitCode(borrowMoneyInfoVo.getUnitCode());
            pamentOrderInfoVo.setUnitId(borrowMoneyInfoVo.getUnitId());
            pamentOrderInfoVo.setUnitName(borrowMoneyInfoVo.getUnitName());
            pamentOrderInfoVo.setUpdateTime(borrowMoneyInfoVo.getUpdateTime());
            pamentOrderInfoVo.setUpdateBy(borrowMoneyInfoVo.getUpdateBy());
            pamentOrderInfoVo.setVersion(borrowMoneyInfoVo.getVersion());
            List<BmBorrowBankVo> salaryCards = borrowMoneyInfoVo.getSalaryCards();

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
                    pyPamentBusinessLineVo.setConnectDocumentId(borrowMoneyInfoVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(borrowMoneyInfoVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PRIVATE");
                    pyPamentBusinessLineVo.setConnectDocumentType("BM_BORROW_MONEY_INFO");
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
                    pyPamentBusinessLineVo.setUnitId(borrowMoneyInfoVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(borrowMoneyInfoVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(borrowMoneyInfoVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(borrowMoneyInfoVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(borrowMoneyInfoVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("借款单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                    pyPamentPrivateLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentPrivateLineVos(pyPamentPrivateLineVos);
            }

            List<BmBorrowBankVo> businessCards = borrowMoneyInfoVo.getBusinessCards();
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
                    pyPamentBusinessLineVo.setConnectDocumentId(borrowMoneyInfoVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(borrowMoneyInfoVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(bmBorrowBankVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("BUSINESS_CARD");
                    pyPamentBusinessLineVo.setConnectDocumentType("BM_BORROW_MONEY_INFO");
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
                    pyPamentBusinessLineVo.setUnitId(borrowMoneyInfoVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(borrowMoneyInfoVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(borrowMoneyInfoVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(borrowMoneyInfoVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(borrowMoneyInfoVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("借款单");
                    pyPamentBusinessLineVo.setConnectDocumentIdLine(bmBorrowBankVo.getId());
                    pyPamentBusinessLineVos.add(pyPamentBusinessLineVo);
                }
                pamentOrderInfoVo.setPyPamentBusinessLineVos(pyPamentBusinessLineVos);
            }
            List<GeExpensePaymentListVo> geExpensePaymentList = borrowMoneyInfoVo.getPaymentListVos();//对公付款
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
                    pyPamentBusinessLineVo.setConnectDocumentId(borrowMoneyInfoVo.getId());
                    pyPamentBusinessLineVo.setConnectDocumentNum(borrowMoneyInfoVo.getDocumentNum());
                    pyPamentBusinessLineVo.setConnectNumber(geExpensePaymentListVo.getLineNumber());
                    pyPamentBusinessLineVo.setLineType("PUBLIC");
                    pyPamentBusinessLineVo.setConnectDocumentType("BM_BORROW_MONEY_INFO");
                    pyPamentBusinessLineVo.setCreateBy(geExpensePaymentListVo.getCreateBy());
                    pyPamentBusinessLineVo.setCreateTime(geExpensePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setCreateUserName(geExpensePaymentListVo.getCreateUserName());
                    //   pyPamentBusinessLineVo.setEmpNo(geExpensePaymentListVo.getEmpNo());
                    pyPamentBusinessLineVo.setInterBranchNumber(geExpensePaymentListVo.getInterBranchNumber());
                    // pyPamentBusinessLineVo.setLineNumber();
                    pyPamentBusinessLineVo.setPayAmount(geExpensePaymentListVo.getPayAmount());
                    pyPamentBusinessLineVo.setPayStatus(geExpensePaymentListVo.getPayStatus());
                    pyPamentBusinessLineVo.setRecieveEmpName(borrowMoneyInfoVo.getCreateUserName());
                    pyPamentBusinessLineVo.setRecieveId(borrowMoneyInfoVo.getCreateBy());
                    // pyPamentBusinessLineVo.setTransactionAmount(geExpensePaymentListVo.getTransactionAmount());
                    //pyPamentBusinessLineVo.setTransactionComments();
                    pyPamentBusinessLineVo.setTransactionDate(geExpensePaymentListVo.getCreateTime());
                    pyPamentBusinessLineVo.setUnitId(borrowMoneyInfoVo.getUnitId());
                    pyPamentBusinessLineVo.setUnitName(borrowMoneyInfoVo.getUnitName());
                    pyPamentBusinessLineVo.setUpdateBy(borrowMoneyInfoVo.getUpdateBy());
                    pyPamentBusinessLineVo.setUpdateTime(borrowMoneyInfoVo.getUpdateTime());
                    pyPamentBusinessLineVo.setVersion(borrowMoneyInfoVo.getVersion());
                    pyPamentBusinessLineVo.setConnectDocumentTypeName("借款单");
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



