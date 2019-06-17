package com.deloitte.services.fssc.business.rep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryParam;
import com.deloitte.platform.api.fssc.rep.param.RecievePaymentQueryForm;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgVo;
import com.deloitte.platform.api.fssc.trade.vo.BusinessRelateDocument;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.services.fssc.business.ppc.entity.ProjectRecieveDetail;
import com.deloitte.services.fssc.business.ppc.service.IProjectRecieveDetailService;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import com.deloitte.services.fssc.business.rep.mapper.RecievePaymentMapper;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :  RecievePayment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class RecievePaymentServiceImpl extends ServiceImpl<RecievePaymentMapper, RecievePayment> implements IRecievePaymentService {

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private RecievePaymentMapper recievePaymentMapper;

    @Autowired
    private IProjectRecieveDetailService projectRecieveDetailService;
    @Override
    public IPage<RecievePayment> selectPage(RecievePaymentQueryForm queryForm) {
        QueryWrapper<RecievePayment> queryWrapper = new QueryWrapper<RecievePayment>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId() != null, "CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()), RecievePayment.DOCUMENT_NUM, queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()), RecievePayment.DOCUMENT_STATUS, queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getRecieveStatus()), RecievePayment.RECIEVE_STATUS, queryForm.getRecieveStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()), RecievePayment.CREATE_USER_NAME, queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()), RecievePayment.UNIT_ID, queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()), RecievePayment.DEPT_ID, queryForm.getDeptId());
        queryWrapper.eq(StringUtil.isNotEmpty(queryForm.getIncomeClaimStatus()), RecievePayment.INCOME_CLAIM_STATUS, queryForm.getIncomeClaimStatus());
        queryWrapper.ge(queryForm.getMoneyStart()!=null, RecievePayment.SK_TOTAL, queryForm.getMoneyStart());
        queryWrapper.le(queryForm.getMoneyEnd()!=null, RecievePayment.SK_TOTAL, queryForm.getMoneyEnd());
        queryWrapper.eq(StringUtil.isNotEmpty(queryForm.getEx1()), RecievePayment.EX1, queryForm.getEx1());
        queryWrapper.ge(queryForm.getTimeStart()!=null, RecievePayment.CREATE_TIME, queryForm.getTimeStart());
        queryWrapper.le(queryForm.getTimeEnd()!=null, RecievePayment.CREATE_TIME, queryForm.getTimeEnd());
        queryWrapper.orderByDesc("UPDATE_TIME");
        return recievePaymentMapper.selectPage(new Page<RecievePayment>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<RecievePayment> selectList(RecievePaymentQueryForm queryForm) {
        QueryWrapper<RecievePayment> queryWrapper = new QueryWrapper<RecievePayment>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()), RecievePayment.DOCUMENT_NUM, queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()), RecievePayment.DOCUMENT_STATUS, queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getRecieveStatus()), RecievePayment.RECIEVE_STATUS, queryForm.getRecieveStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()), RecievePayment.CREATE_USER_NAME, queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()), RecievePayment.UNIT_ID, queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()), RecievePayment.DEPT_ID, queryForm.getDeptId());

        queryWrapper.ge(queryForm.getMoneyStart()!=null, RecievePayment.SK_TOTAL, queryForm.getMoneyStart());
        queryWrapper.le(queryForm.getMoneyEnd()!=null, RecievePayment.SK_TOTAL, queryForm.getMoneyEnd());
        queryWrapper.ge(queryForm.getTimeStart()!=null, RecievePayment.CREATE_TIME, queryForm.getTimeStart());
        queryWrapper.le(queryForm.getTimeEnd()!=null, RecievePayment.CREATE_TIME, queryForm.getTimeEnd());
        queryWrapper.orderByDesc("UPDATE_TIME");
        return recievePaymentMapper.selectList(queryWrapper);
    }

    @Override
    public List<RecieveLineMsgVo> findRecieveLines(RecieveLineMsgQueryParam recieveLineMsgQueryParam) {
        DeptVo currentDept = commonServices.getCurrentDept();
        if (currentDept != null) {
            recieveLineMsgQueryParam.setRecieveUnitId(StringUtil.castTolong(currentDept.getId()));
        }
        return recievePaymentMapper.findRecieveLines(recieveLineMsgQueryParam);
    }

    @Override
    public List<RecieveLineMsgVo> findRecieveLineShouRuShangjiao(RecieveLineMsgQueryParam param) {
        DeptVo currentDept = commonServices.getCurrentDept();
        if (currentDept != null) {
            param.setRecieveUnitId(StringUtil.castTolong(currentDept.getId()));
        }
        return recievePaymentMapper.findRecieveLineShouRuShangjiao(param);
    }

    @Override
    public boolean existsByReceivePayment(List<Long> incomeMainTypeIds) {
        QueryWrapper<RecievePayment> countWrapper = new QueryWrapper<>();
        countWrapper.in(RecievePayment.IN_COME_MAIN_TYPE_ID, incomeMainTypeIds);
        return this.count(countWrapper) > 0;
    }




    public void modifyCliamStatus(String documentType, Long documentId,boolean isSubmit) {

        //款项确认 修改收款单认领状态
        if (FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue().equals(documentType) && documentId != null) {
            QueryWrapper<ProjectRecieveDetail> detailQueryWrapper = new QueryWrapper<>();
            detailQueryWrapper.eq(ProjectRecieveDetail.DOCUMENT_ID, documentId)
                    .eq(ProjectRecieveDetail.DOCUMENT_TYPE, documentType);
            List<ProjectRecieveDetail> recieveDetails = projectRecieveDetailService.list(detailQueryWrapper);
            Set<Long> skId = new HashSet<>();
            if (CollectionUtils.isNotEmpty(recieveDetails)) {
                for (ProjectRecieveDetail detail : recieveDetails) {
                    Long connectDocumentId = detail.getConnectDocumentId();
                    if (connectDocumentId != null) {
                        skId.add(connectDocumentId);
                    }
                }
                for (Long l : skId) {
                    RecievePayment payment = this.getById(l);
                    //收款单总金额
                    BigDecimal skAmount = payment.getSkTotal();
                    BigDecimal kxAmount = BigDecimal.ZERO;
                    for (ProjectRecieveDetail detail : recieveDetails) {
                        //款项行收入确认金额
                        BigDecimal recieveConfirmAmount = BigDecimalUtil.convert(detail.getRecieveConfirmAmount());
                        if (l.equals(detail.getConnectDocumentId())) {
                            kxAmount = recieveConfirmAmount.add(kxAmount);
                        }
                    }
                    BigDecimal incomeClaimAmount = BigDecimalUtil.convert(payment.getIncomeClaimAmount());
                    BigDecimal t;
                    if(!isSubmit){
                        t =incomeClaimAmount.subtract(kxAmount);
                    }else {
                        t = incomeClaimAmount.add(kxAmount);
                    }
                    payment.setIncomeClaimAmount(t);
                    if (skAmount.compareTo(t) <= 0) {
                        payment.setIncomeClaimStatus("Y");
                    } else if (skAmount.compareTo(t) > 0 && t.compareTo(BigDecimal.ZERO) > 0) {
                        payment.setIncomeClaimStatus("SOME");
                    } else  {
                        payment.setIncomeClaimStatus("N");
                    }
                    this.saveOrUpdate(payment);
                }
            }
        }

        //收款单
        if (FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue().equals(documentType) && documentId != null) {

            RecievePayment payment = this.getById(documentId);
            BigDecimal totalAmount = BigDecimalUtil.convert(payment.getSkTotal());
            BigDecimal incomeClaimAmount = BigDecimalUtil.convert(payment.getIncomeClaimAmount());
            if (totalAmount.compareTo(incomeClaimAmount) <= 0) {
                payment.setIncomeClaimStatus("Y");
            } else if (totalAmount.compareTo(incomeClaimAmount) >= 0 && incomeClaimAmount.compareTo(BigDecimal.ZERO) > 0) {
                payment.setIncomeClaimStatus("SOME");
            } else if (incomeClaimAmount.compareTo(BigDecimal.ZERO) == 0) {
                payment.setIncomeClaimStatus("N");
            }
            if(!isSubmit){
                payment.setIncomeClaimAmount(null);
                QueryWrapper<ProjectRecieveDetail> detailQueryWrapper = new QueryWrapper<>();
                detailQueryWrapper.eq(ProjectRecieveDetail.CONNECT_DOCUMENT_ID, documentId)
                        .eq(ProjectRecieveDetail.CONNECT_DOCUMENT_TYPE, documentType);
                List<ProjectRecieveDetail> recieveDetails = projectRecieveDetailService.list(detailQueryWrapper);
                AssertUtils.asserts(CollectionUtils.isEmpty(recieveDetails),FsscErrorType.HAD_CONNECTED_CAN_ROLLBACK);
            }
            this.saveOrUpdate(payment);

        }
    }

    @Override
    public Map<String,List<BusinessRelateDocument>> getSerialNumDocumentMap(List<String> serialNumList) {
        List<BusinessRelateDocument> relateDocumentList = recievePaymentMapper.findBySerialNum(serialNumList);
        if (CollectionUtils.isEmpty(relateDocumentList)){
            return Collections.EMPTY_MAP;
        }
        Map<String, List<BusinessRelateDocument>> serialNumDocumentMap = new HashMap<>();
        for (BusinessRelateDocument relateDocument : relateDocumentList){
            List<BusinessRelateDocument> relateDocumentList1 = serialNumDocumentMap.get(relateDocument.getSerialNum());
            if (serialNumDocumentMap.get(relateDocument.getSerialNum()) == null) {
                relateDocumentList1 = new ArrayList<>();
                relateDocumentList1.add(relateDocument);
                serialNumDocumentMap.put(relateDocument.getSerialNum(), relateDocumentList1);
            }else{
                relateDocumentList1.add(relateDocument);
            }
        }
        return serialNumDocumentMap;
    }
}

