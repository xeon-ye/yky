package com.deloitte.services.fssc.business.ppc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.ppc.param.ContractInformationQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ContractInformation;
import com.deloitte.services.fssc.business.ppc.mapper.ContractInformationMapper;
import com.deloitte.services.fssc.business.ppc.service.IContractInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ContractInformation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ContractInformationServiceImpl extends ServiceImpl<ContractInformationMapper, ContractInformation> implements IContractInformationService {


    @Autowired
    private ContractInformationMapper contractInformationMapper;

    @Override
    public IPage<ContractInformation> selectPage(ContractInformationQueryForm queryForm ) {
        QueryWrapper<ContractInformation> queryWrapper = new QueryWrapper <ContractInformation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return contractInformationMapper.selectPage(new Page<ContractInformation>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ContractInformation> selectList(ContractInformationQueryForm queryForm) {
        QueryWrapper<ContractInformation> queryWrapper = new QueryWrapper <ContractInformation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return contractInformationMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ContractInformation> getQueryWrapper(QueryWrapper<ContractInformation> queryWrapper,ContractInformationQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ContractInformation.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(ContractInformation.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ContractInformation.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ContractInformation.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ContractInformation.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(ContractInformation.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(ContractInformation.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(ContractInformation.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(ContractInformation.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUnitName())){
            queryWrapper.eq(ContractInformation.RECIEVE_UNIT_NAME,queryForm.getRecieveUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveBankName())){
            queryWrapper.eq(ContractInformation.RECIEVE_BANK_NAME,queryForm.getRecieveBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveBankType())){
            queryWrapper.eq(ContractInformation.RECIEVE_BANK_TYPE,queryForm.getRecieveBankType());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveBankAccount())){
            queryWrapper.eq(ContractInformation.RECIEVE_BANK_ACCOUNT,queryForm.getRecieveBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getContractDocumentId())){
            queryWrapper.eq(ContractInformation.CONTRACT_DOCUMENT_ID,queryForm.getContractDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractDocumentNum())){
            queryWrapper.eq(ContractInformation.CONTRACT_DOCUMENT_NUM,queryForm.getContractDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(ContractInformation.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getFeasor())){
            queryWrapper.eq(ContractInformation.FEASOR,queryForm.getFeasor());
        }
        if(StringUtils.isNotBlank(queryForm.getAgreeRecieveBatch())){
            queryWrapper.eq(ContractInformation.AGREE_RECIEVE_BATCH,queryForm.getAgreeRecieveBatch());
        }
        if(StringUtils.isNotBlank(queryForm.getAgreeConventionalScale())){
            queryWrapper.eq(ContractInformation.AGREE_CONVENTIONAL_SCALE,queryForm.getAgreeConventionalScale());
        }
        if(StringUtils.isNotBlank(queryForm.getAgreeRecieveAmount())){
            queryWrapper.eq(ContractInformation.AGREE_RECIEVE_AMOUNT,queryForm.getAgreeRecieveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getPlanRecieveTime())){
            queryWrapper.eq(ContractInformation.PLAN_RECIEVE_TIME,queryForm.getPlanRecieveTime());
        }
        if(StringUtils.isNotBlank(queryForm.getContractDocumentType())){
            queryWrapper.eq(ContractInformation.CONTRACT_DOCUMENT_TYPE,queryForm.getContractDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getContractType())){
            queryWrapper.eq(ContractInformation.CONTRACT_TYPE,queryForm.getContractType());
        }
        if(StringUtils.isNotBlank(queryForm.getContractName())){
            queryWrapper.eq(ContractInformation.CONTRACT_NAME,queryForm.getContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeId())){
            queryWrapper.eq(ContractInformation.IN_COME_SUB_TYPE_ID,queryForm.getInComeSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeCode())){
            queryWrapper.eq(ContractInformation.IN_COME_SUB_TYPE_CODE,queryForm.getInComeSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubSubjectCode())){
            queryWrapper.eq(ContractInformation.IN_COME_SUB_SUBJECT_CODE,queryForm.getInComeSubSubjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeName())){
            queryWrapper.eq(ContractInformation.IN_COME_SUB_TYPE_NAME,queryForm.getInComeSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveAmount())){
            queryWrapper.eq(ContractInformation.RECIEVE_AMOUNT,queryForm.getRecieveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveConfirmAmount())){
            queryWrapper.eq(ContractInformation.RECIEVE_CONFIRM_AMOUNT,queryForm.getRecieveConfirmAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveTime())){
            queryWrapper.eq(ContractInformation.RECIEVE_TIME,queryForm.getRecieveTime());
        }
        if(StringUtils.isNotBlank(queryForm.getPayBankName())){
            queryWrapper.eq(ContractInformation.PAY_BANK_NAME,queryForm.getPayBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getPayBankAccount())){
            queryWrapper.eq(ContractInformation.PAY_BANK_ACCOUNT,queryForm.getPayBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getPayUnitName())){
            queryWrapper.eq(ContractInformation.PAY_UNIT_NAME,queryForm.getPayUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(ContractInformation.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(ContractInformation.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(ContractInformation.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(ContractInformation.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(ContractInformation.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(ContractInformation.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(ContractInformation.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(ContractInformation.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(ContractInformation.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(ContractInformation.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(ContractInformation.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(ContractInformation.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(ContractInformation.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(ContractInformation.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(ContractInformation.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(ContractInformation.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(ContractInformation.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

