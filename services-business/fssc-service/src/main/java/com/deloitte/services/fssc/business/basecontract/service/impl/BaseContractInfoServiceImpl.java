package com.deloitte.services.fssc.business.basecontract.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.contract.feign.BasicInfoFeignService;
import com.deloitte.platform.api.contract.vo.FinanceInfoVoToFssc;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractInfoQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractInfo;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanLine;
import com.deloitte.services.fssc.business.basecontract.mapper.BaseContractInfoMapper;
import com.deloitte.services.fssc.business.basecontract.mapper.BaseContractPlanLineMapper;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractInfoService;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import com.deloitte.services.fssc.business.ppc.entity.ContractInformation;
import com.deloitte.services.fssc.business.ppc.service.IContractInformationService;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :  BaseContractInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class BaseContractInfoServiceImpl extends ServiceImpl<BaseContractInfoMapper, BaseContractInfo> implements IBaseContractInfoService {


    @Autowired
    private BaseContractInfoMapper baseContractInfoMapper;

    @Autowired
    private BaseContractPlanLineMapper contractPlanLineMapper;

    @Autowired
    private BasicInfoFeignService feignService;

    @Autowired
    private IContractInformationService contractInformationService;



    @Override
    public IPage<BaseContractInfo> selectPage(BaseContractInfoQueryForm queryForm) {
        QueryWrapper<BaseContractInfo> queryWrapper = new QueryWrapper<BaseContractInfo>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getContractName()), BaseContractInfo.CONTRACT_NAME, queryForm.getContractName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getContractNo()), BaseContractInfo.CONTRACT_NO, queryForm.getContractNo());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getContractType()), BaseContractInfo.CONTRACT_TYPE, queryForm.getContractType());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getExecuteStartTime()), BaseContractInfo.EXECUTE_STATUE_TIME, queryForm.getExecuteStartTime());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getExecuteEndTime()), BaseContractInfo.EXECUTE_STATUE_TIME, queryForm.getExecuteEndTime());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getIsForeignContract()), BaseContractInfo.IS_FOREIGN_CONTRACT, queryForm.getIsForeignContract());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getOperator()), BaseContractInfo.OPERATOR, queryForm.getOperator());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getOrg()), BaseContractInfo.ORG, queryForm.getOrg());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getIsPayStampDuty()), BaseContractInfo.IS_PAY_STAMP_DUTY, queryForm.getIsPayStampDuty());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getOurSubjectName()), BaseContractInfo.OUR_SUBJECT_NAME, queryForm.getOurSubjectName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getSideSubjectName()), BaseContractInfo.SIDE_SUBJECT_NAME, queryForm.getSideSubjectName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getStatue()), BaseContractInfo.STATUE_NAME, queryForm.getStatue());
        queryWrapper.apply("OLD_CONTRACT_ID IS NULL");
        queryWrapper.orderByDesc(LcLaborCost.UPDATE_TIME);

        return baseContractInfoMapper.selectPage(new Page<BaseContractInfo>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<BaseContractInfo> selectList(BaseContractInfoQueryForm queryForm) {
        QueryWrapper<BaseContractInfo> queryWrapper = new QueryWrapper<BaseContractInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return baseContractInfoMapper.selectList(queryWrapper);
    }


    @Autowired
    private IContactDetailService contactDetailService;

    @Autowired
    private IPyPamentBusinessLineService pyPamentBusinessLineService;

    /**
     * 合同报账完成之后调用的接口
     */
    public void sendPlanToContract(Long documentId, String documentType) {
        log.info("推送履行计划开始");

        if(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue().equals(documentType)){
            log.info("推送履行计划开始，付款单");
            QueryWrapper<PyPamentBusinessLine> businessLineQueryWrapper=new QueryWrapper<>();
            businessLineQueryWrapper.eq("DOCUMENT_ID",documentId).eq("DOCUMENT_TYPE",documentType);
            List<PyPamentBusinessLine> businessLines = pyPamentBusinessLineService.list(businessLineQueryWrapper);
            if(CollectionUtils.isNotEmpty(businessLines)){
                log.info("推送履行计划开始，付款单行:{}",JSON.toJSONString(businessLines));
                for (PyPamentBusinessLine line:businessLines){
                    String connectDocumentType = line.getConnectDocumentType();
                    Long connectDocumentId = line.getConnectDocumentId();
                    if(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(connectDocumentType)
                    ||FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(connectDocumentType)){
                        QueryWrapper<ContactDetail> detailQueryWrapper = new QueryWrapper<>();
                        detailQueryWrapper.eq(ContactDetail.DOCUMENT_ID, connectDocumentId)
                                .eq(ContactDetail.DOCUMENT_TYPE, connectDocumentType);
                        List<ContactDetail> details = contactDetailService.list(detailQueryWrapper);
                        if (CollectionUtils.isNotEmpty(details)) {
                            List<FinanceInfoVoToFssc> listFinanceInfoVo = new ArrayList<>();
                            for (ContactDetail detail : details) {
                                if(detail.getTravelPlanId()!=null){
                                    listFinanceInfoVo.add(buildFssc(detail.getTravelPlanId(),detail.getPlanPaymentTime(),
                                            BigDecimalUtil.convert(detail.getActualPlayAmount()).doubleValue())) ;
                                }
                            }
                            if(CollectionUtils.isNotEmpty(listFinanceInfoVo)){
                                doSend(listFinanceInfoVo);
                            }
                        }
                    }
                }
            }
        }

        if(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue().equals(documentType)){
            QueryWrapper<ContractInformation> detailQueryWrapper = new QueryWrapper<>();
            detailQueryWrapper.eq(ContactDetail.DOCUMENT_ID, documentId)
                    .eq(ContactDetail.DOCUMENT_TYPE, documentType);
            List<ContractInformation> list = contractInformationService.list(detailQueryWrapper);
            if (CollectionUtils.isNotEmpty(list)) {
                List<FinanceInfoVoToFssc> listFinanceInfoVo = new ArrayList<>();
                for (ContractInformation detail : list) {
                    if(detail.getTravelPlanId()!=null){
                        QueryWrapper<BaseContractPlanLine> lineQueryWrapper=new QueryWrapper<>();
                        lineQueryWrapper.eq(BaseContractPlanLine.TRAVEL_PLAN_ID,detail.getTravelPlanId());
                        BaseContractPlanLine baseContractPlanLine = contractPlanLineMapper.selectOne(lineQueryWrapper);
                        if(baseContractPlanLine!=null){
                            listFinanceInfoVo.add(buildFssc(detail.getTravelPlanId(),detail.getUpdateTime(),
                                    BigDecimalUtil.convert(baseContractPlanLine.getAgreedRecieveAmount()).doubleValue())) ;
                        }

                    }
                }
                if(CollectionUtils.isNotEmpty(listFinanceInfoVo)){
                    doSend(listFinanceInfoVo);
                }
            }
        }
    }

    private FinanceInfoVoToFssc buildFssc(Long planId, LocalDateTime actIncomeTime, Double actIncomeRate) {
        BaseContractPlanLine baseContractPlanLine = contractPlanLineMapper.selectById(planId);
        FinanceInfoVoToFssc financeInfoVo = new FinanceInfoVoToFssc();
        financeInfoVo.setActIncomeRate(actIncomeRate);
        financeInfoVo.setActIncomeTime(actIncomeTime);
        financeInfoVo.setActPayRate(actIncomeRate);
        financeInfoVo.setActPayTime(actIncomeTime);
        financeInfoVo.setId(StringUtil.objectToString(planId));
        if (baseContractPlanLine != null) {
            financeInfoVo.setIsManualIncome(baseContractPlanLine.getIsManualIncome());
        }
        return financeInfoVo;
    }

    private void doSend(List<FinanceInfoVoToFssc> listFinanceInfoVo) {
        log.info("推送履行计划参数：{}", JSON.toJSONString(listFinanceInfoVo));
        Result result = feignService.saveFinanceInfo(listFinanceInfoVo);
        log.info("推送履行计划结束：{}", result.getMesg());
    }


    @Override
    public boolean updateBaseContract(BaseContractInfoForm form) {

        baseContractInfoMapper.updateContract(form);
        return true;
    }

}

