package com.deloitte.services.fssc.business.rep.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgForm;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgVo;
import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentForm;
import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.rep.biz.RecievePaymentBiz;
import com.deloitte.services.fssc.business.rep.entity.RecieveClaimArea;
import com.deloitte.services.fssc.business.rep.entity.RecieveIncomeMsg;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import com.deloitte.services.fssc.business.rep.service.IRecieveClaimAreaService;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecievePaymentBizImpl implements RecievePaymentBiz {

    @Autowired
    private IRecievePaymentService recievePaymentService;
    @Autowired
    private IRecieveLineMsgService recieveLineMsgService;
    @Autowired
    private IBankUnitInfoService bankUnitInfoService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private BpmTaskBiz bpmTaskBiz;
    @Autowired
    private IBudgetProjectService budgetProjectService;
    @Autowired
    private IBaseIncomeSubTypeService baseIncomeSubTypeService;
    @Autowired
    private IRecieveClaimAreaService claimAreaService;
    @Autowired
    protected IBaseIncomeMainTypeService baseIncomeMainTypeService;
    @Override
    @Transactional
    public RecievePaymentVo saveOrUpdate(RecievePaymentForm recievePaymentForm) {

        //验证单据是否存在
        FsscCommonUtil.valiHasData(recievePaymentForm.getId()
                , FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());

        RecievePayment recievePayment = new BeanUtils<RecievePayment>().copyObj(recievePaymentForm, RecievePayment.class);

        Long projectId = recievePayment.getProjectId();
        if (projectId != null) {
            BudgetProject project = budgetProjectService.getById(projectId);
            if (project != null) {
                recievePayment.setProjectCode(project.getProjectCode());
                recievePayment.setProjectName(project.getProjectName());
                recievePayment.setProjectUnitCode(project.getResponsibleUnitCode());
                recievePayment.setProjectUnitName(project.getResponsibleUnitName());
                recievePayment.setFsscCode(project.getFsscCode());
                recievePayment.setAccountCode(project.getAccountCode());
            }
        }
        Long inComeMainTypeId = recievePayment.getInComeMainTypeId();
        if(inComeMainTypeId!=null){
            BaseIncomeMainType mainType = baseIncomeMainTypeService.getById(inComeMainTypeId);
            if(mainType!=null){
                recievePayment.setInComeMainTypeName(mainType.getName());
                recievePayment.setInComeMainTypeCode(mainType.getCode());
            }
        }

        List<RecieveLineMsgForm> recieveLineMsgForms = recievePaymentForm.getRecieveLineMsgForms();
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal renlingTotal = BigDecimal.ZERO;
        for (RecieveLineMsgForm lineMsgForm : recieveLineMsgForms) {
            BigDecimal amountCollected = BigDecimalUtil.convert(lineMsgForm.getAmountCollected());
            BigDecimal revenueAmount = BigDecimalUtil.convert(lineMsgForm.getRevenueAmount());
            total = total.add(amountCollected);
            renlingTotal = renlingTotal.add(revenueAmount);
            recievePayment.setIncomeClaimAmount(renlingTotal);
            recievePayment.setSkTotal(total);
        }


        if(CollectionUtils.isNotEmpty(recievePaymentForm.getCopyEmpNos())){
            recievePayment.setEx1("Y");
        }else {
            recievePayment.setEx1("N");
        }

        boolean b = recievePaymentService.saveOrUpdate(recievePayment);
        AssertUtils.asserts(b, FsscErrorType.SAVE_FAIL);
        recievePaymentForm.setId(recievePayment.getId());
        //文件列表保存
        List<Long> fileIds = recievePaymentForm.getFileIds();
        if (CollectionUtils.isNotEmpty(fileIds)) {
            fileIds.removeAll(Collections.singleton(null));
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID, recievePayment.getId())
                    .eq(File.DOCUMENT_TYPE, FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())
                    .notIn(File.ID, fileIds);
            fileService.remove(fileQueryWrapper);

            Collection<File> files = fileService.listByIds(fileIds);
            files.stream().forEach(ka -> ka.setDocumentId(recievePayment.getId()));
            fileService.saveOrUpdateBatch(files);
        }

        //保存收款信息
        saveRecieveMsg(recievePaymentForm, recievePayment.getId());
        //保存认领人
        saveClaimArea(recievePaymentForm);

        return new BeanUtils<RecievePaymentVo>().copyObj(recievePaymentService.getById(recievePayment.getId()),
                RecievePaymentVo.class);
    }

    /**
     * 保存认领人
     * @param recievePaymentForm
     */
    public void saveClaimArea(RecievePaymentForm recievePaymentForm) {
        List<Map<String, String>> copyEmpNos = recievePaymentForm.getCopyEmpNos();
        if(CollectionUtils.isNotEmpty(copyEmpNos)){
            QueryWrapper<RecieveClaimArea> areaQueryWrapper=new QueryWrapper<>();
            areaQueryWrapper.eq(RecieveClaimArea.DOCUMENT_ID,recievePaymentForm.getId())
                    .eq(RecieveClaimArea.DOCUMENT_TYPE,FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
            claimAreaService.remove(areaQueryWrapper);
            List<RecieveClaimArea> areas=new ArrayList<>();
            for (Map<String,String> map:copyEmpNos){
                RecieveClaimArea area=new RecieveClaimArea();
                String empNo = map.get("copyEmpNo");
                String remark = map.get("remark");
                String orgCode=map.get("orgCode");
                area.setRemark(remark);
                area.setClaimEmpNo(empNo);
                area.setClaimDeptCode(orgCode);
                area.setDocumentId(recievePaymentForm.getId());
                area.setDocumentType(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
                areas.add(area);

            }
            claimAreaService.saveBatch(areas);
        }

    }

    @Override
    @Transactional
    public void deleteById(Long documentId) {

        RecievePayment recievePayment = recievePaymentService.getById(documentId);
        String documentStatus = recievePayment.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                FsscEums.RECALLED.getValue().equals(documentStatus) ||
                FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        recievePaymentService.removeById(documentId);

        QueryWrapper<RecieveLineMsg> lineMsgQueryWrapper = new QueryWrapper<>();
        lineMsgQueryWrapper.eq(RecieveLineMsg.DOCUMENT_ID, documentId)
                .eq(RecieveClaimArea.DOCUMENT_TYPE, FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        recieveLineMsgService.remove(lineMsgQueryWrapper);

    }

    @Override
    public RecievePaymentVo getById(Long id) {
        RecievePayment recievePayment = recievePaymentService.getById(id);
        RecievePaymentVo recievePaymentVo =
                new BeanUtils<RecievePaymentVo>().copyObj(recievePayment, RecievePaymentVo.class);
        //已认领金额 未认领金额
        recievePaymentVo.setNoIncomeClaimAmount(
                BigDecimalUtil.convert(recievePayment.getSkTotal()).subtract(BigDecimalUtil.convert(recievePayment.getIncomeClaimAmount()))
        );

        //审批历史纪录
        try {
            recievePaymentVo.setBpmProcessTaskVos(bpmTaskBiz.findHistory(id, FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue()));
        } catch (Exception e) {
            log.error("error:{}" + e.getMessage());
        }

        //收款信息
        QueryWrapper<RecieveLineMsg> lineMsgQueryWrapper = new QueryWrapper<>();
        lineMsgQueryWrapper.eq(RecieveLineMsg.DOCUMENT_ID, id)
                .eq(RecieveClaimArea.DOCUMENT_TYPE, FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        List<RecieveLineMsgVo> msgVos = new BeanUtils<RecieveLineMsgVo>().copyObjs(
                recieveLineMsgService.list(lineMsgQueryWrapper), RecieveLineMsgVo.class);
        if(CollectionUtils.isNotEmpty(msgVos)){
            for (RecieveLineMsgVo detailVo:msgVos){
                Long inComeSubTypeId = detailVo.getInComeSubTypeId();
                if(inComeSubTypeId!=null){
                    BaseIncomeSubType type = baseIncomeSubTypeService.getById(inComeSubTypeId);
                    if(type!=null){
                        detailVo.setInComeSubTypeName(type.getName());
                        detailVo.setInComeSubTypeCode(type.getCode());
                    }
                }

                Long bankUnitId = detailVo.getRecieveBankUnitId();
                if(bankUnitId!=null){
                    BankUnitInfo info = bankUnitInfoService.getById(bankUnitId);
                    if(info!=null){
                         detailVo.setRecieveBankAccountName(info.getBankAccountName());
                    }
                }
                String recieveBankAccount = detailVo.getRecieveBankAccount();
                if(StringUtil.isNotEmpty(recieveBankAccount)){
                    BankUnitInfoQueryForm queryForm=new BankUnitInfoQueryForm();
                    queryForm.setBankAccount(recieveBankAccount);
                    IPage<BankUnitInfoVo> page = bankUnitInfoService.selectPage(queryForm);
                    detailVo.setBankUnitInfoVos(page.getRecords());
                }

            }
        }
        recievePaymentVo.setRecieveLineMsgVos(msgVos);

        return recievePaymentVo;
    }


    /**
     * 保存收款信息
     *
     * @param recievePaymentForm
     * @param documentId
     */
    private void saveRecieveMsg(RecievePaymentForm recievePaymentForm, Long documentId) {
        AssertUtils.asserts(CollectionUtils.isNotEmpty(recievePaymentForm.getRecieveLineMsgForms()), FsscErrorType.GE_LINE_IS_NOT_EMPTY);
        List<RecieveLineMsg> recieveLineMsgs = new BeanUtils<RecieveLineMsg>()
                .copyObjs(recievePaymentForm.getRecieveLineMsgForms(), RecieveLineMsg.class);
        //先删除差集的行
        QueryWrapper<RecieveLineMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RecieveLineMsg.DOCUMENT_ID, documentId)
                .eq(RecieveClaimArea.DOCUMENT_TYPE, FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        List<Long> cuLineIds = recieveLineMsgs.stream().map(k -> k.getId()).collect(Collectors.toList());
        cuLineIds.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(cuLineIds)) {
            queryWrapper.notIn(RecieveIncomeMsg.ID, cuLineIds);
        }
        recieveLineMsgService.remove(queryWrapper);
        for (RecieveLineMsg area : recieveLineMsgs) {
            area.setDocumentId(documentId);
            area.setDocumentType(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
            Long bankUnitId = area.getRecieveBankUnitId();
            if(bankUnitId!=null){
                BankUnitInfo info = bankUnitInfoService.getById(bankUnitId);
                if(info!=null){
                    area.setBankSubjectCode(info.getSubjectCode());
                    area.setBudgetBankSubjectCode(info.getBudgetSubjectCode());
                }
            }
        }
        recieveLineMsgService.saveOrUpdateBatch(recieveLineMsgs);
    }



}






















