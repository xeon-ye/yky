package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ApprovalVoucherQueryForm;
import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.common.util.StringUtil;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.mapper.*;
import com.deloitte.services.contract.service.IApprovalVoucherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.contract.service.ICommonService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-27
 * @Description :  ApprovalVoucher服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ApprovalVoucherServiceImpl extends ServiceImpl<ApprovalVoucherMapper, ApprovalVoucher> implements IApprovalVoucherService {


    @Autowired
    private ApprovalVoucherMapper approvalVoucherMapper;

    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private ProcessOperatorTransferMapper processOperatorTransferMapper;
    @Autowired
    private ProcessExecuterTransferMapper processExecuterTransferMapper;
    @Autowired
    private StandardTemplateMapper standardTemplateMapper;
    @Autowired
    public ICommonService commonService;

    @Override
    public IPage<ApprovalVoucher> selectPage(ApprovalVoucherQueryForm queryForm ) {
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper <ApprovalVoucher>();
        //getQueryWrapper(queryWrapper,queryForm);
        return approvalVoucherMapper.selectPage(new Page<ApprovalVoucher>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ApprovalVoucher> selectList(ApprovalVoucherQueryForm queryForm) {
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper <ApprovalVoucher>();
//        getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,queryForm.getBusinessId());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,queryForm.getVoucherStatus());
        return approvalVoucherMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public ApprovalVoucher generateNewVoucher(Long contractId, VoucherTypeEnums typeEnums, String voucherName) {
        if(VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE.getCode().equals(typeEnums.getCode())){
            QueryWrapper<BasicInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(BasicInfo.ID,contractId);
            BasicInfo contract = basicInfoMapper.selectOne(queryWrapper);
            AssertUtils.asserts(null == contract , ContractErrorType.CONTRACT_IS_NULL);
            UserVo systemUser = new UserVo();
            systemUser.setId("99999999");
            systemUser.setName("合同发起流程");
            OrganizationVo sysOrg = new OrganizationVo();
            sysOrg.setCode(contract.getOrgCode());

            ApprovalVoucher voucherEntity = new ApprovalVoucher();

            voucherEntity.setVoucherStatus(VoucherStatusEnums.READ.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setPersonName(systemUser.getName());
            voucherEntity.setUserId(Long.parseLong(systemUser.getId()));
            voucherEntity.setBusinessId(contractId+"");
            voucherEntity.setOrgCode(sysOrg.getCode());
            voucherEntity.setVoucherName(voucherName);
            this.save(voucherEntity);
            return voucherEntity;
        }
        return new ApprovalVoucher();
    }

    @Override
    @Transactional
    public ApprovalVoucher generateNewVoucher(Long contractId, VoucherTypeEnums typeEnums) {

        if(VoucherTypeEnums.STANDARD_BOOK.getCode().equals(typeEnums.getCode()) || VoucherTypeEnums.STANDARD_UNABLE.getCode().equals(typeEnums.getCode())|| VoucherTypeEnums.STANDARD_BOOK_SUCCESS.getCode().equals(typeEnums.getCode())){
            QueryWrapper<StandardTemplate> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(StandardTemplate.ID,contractId);
            StandardTemplate standardTemplate = standardTemplateMapper.selectOne(queryWrapper);
            AssertUtils.asserts(null == standardTemplate , ContractErrorType.STANDARD_IS_NULL);
            ApprovalVoucher voucherEntity = new ApprovalVoucher();

            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            UserVo userVo = commonService.getCurrentUser();
            OrganizationVo organizationVo = commonService.getOrganization();
            voucherEntity.setPersonName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(contractId+"");
            voucherEntity.setOrgCode(organizationVo.getCode());
            voucherEntity.setVoucherName(standardTemplate.getTemplateName() + "【"+ typeEnums.getValue()+"】");
            this.save(voucherEntity);
            return voucherEntity;

        }else if(VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode().equals(typeEnums.getCode())){
            QueryWrapper<ProcessOperatorTransfer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ProcessOperatorTransfer.ID,contractId);
            ProcessOperatorTransfer contract = processOperatorTransferMapper.selectOne(queryWrapper);
            AssertUtils.asserts(null == contract , ContractErrorType.CONTRACT_IS_NULL);
            ApprovalVoucher voucherEntity = new ApprovalVoucher();
//            voucherEntity.setBizNumber(contract.getContractSerialNumber());//合同流水号

            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            if (null != contract.getOperatorCode()){
                voucherEntity.setPersonName(contract.getOperator());
                voucherEntity.setUserId(Long.parseLong(contract.getOperatorCode()));
            }
            voucherEntity.setBusinessId(contractId+"");
            voucherEntity.setVoucherName(contract.getOperator()+ "【"+ typeEnums.getValue()+"】");
            UserVo userVo = commonService.getCurrentUser();
            OrganizationVo organizationVo = commonService.getOrganization();
            voucherEntity.setOrgCode(organizationVo.getCode());
            this.save(voucherEntity);
            return voucherEntity;

        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode().equals(typeEnums.getCode())){
            QueryWrapper<ProcessExecuterTransfer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ProcessExecuterTransfer.ID,contractId);
            ProcessExecuterTransfer processExecuterTransfer = processExecuterTransferMapper.selectOne(queryWrapper);
            AssertUtils.asserts(null == processExecuterTransfer || null == processExecuterTransfer.getContractId(), ContractErrorType.EXECUTER_TRANSFER_IS_NULL);

            QueryWrapper<BasicInfo> contractWrapper = new QueryWrapper<>();
            contractWrapper.eq(BasicInfo.ID,processExecuterTransfer.getContractId());
            BasicInfo contract = basicInfoMapper.selectOne(contractWrapper);
            ApprovalVoucher voucherEntity = new ApprovalVoucher();

            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            if (null != processExecuterTransfer.getExecuterCode()){
                voucherEntity.setPersonName(processExecuterTransfer.getExecuter());
                voucherEntity.setUserId(Long.parseLong(processExecuterTransfer.getExecuterCode()));
            }
            voucherEntity.setBusinessId(contractId+"");
            voucherEntity.setVoucherName(contract.getContractName()+ "【"+ typeEnums.getValue()+"】");
            UserVo userVo = commonService.getCurrentUser();
            OrganizationVo organizationVo = commonService.getOrganization();
            voucherEntity.setOrgCode(organizationVo.getCode());
            this.save(voucherEntity);
            return voucherEntity;
        }else {
            QueryWrapper<BasicInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(BasicInfo.ID, contractId);
            BasicInfo contract = basicInfoMapper.selectOne(queryWrapper);

            AssertUtils.asserts(null == contract, ContractErrorType.CONTRACT_IS_NULL);
            ApprovalVoucher voucherEntity = new ApprovalVoucher();
            voucherEntity.setBizNumber(contract.getContractSerialNumber());//合同流水号

            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            if (null != contract.getOperatorCode()) {
                voucherEntity.setPersonName(contract.getOperator());
                voucherEntity.setUserId(Long.parseLong(contract.getOperatorCode()));
            }
            voucherEntity.setBusinessId(contractId+"");
            voucherEntity.setVoucherName(contract.getContractName() + "【" + typeEnums.getValue() + "】");

            voucherEntity.setOrgCode(contract.getOrgCode());
            this.save(voucherEntity);
            return voucherEntity;
        }
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ApprovalVoucher> getQueryWrapper(QueryWrapper<ApprovalVoucher> queryWrapper,ApprovalVoucherQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBizNumber())){
            queryWrapper.eq(ApprovalVoucher.BIZ_NUMBER,queryForm.getBizNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherName())){
            queryWrapper.eq(ApprovalVoucher.VOUCHER_NAME,queryForm.getVoucherName());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherType())){
            queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,queryForm.getVoucherType());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherStatus())){
            queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,queryForm.getVoucherStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(ApprovalVoucher.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonName())){
            queryWrapper.eq(ApprovalVoucher.PERSON_NAME,queryForm.getPersonName());
        }
        if(StringUtils.isNotBlank(queryForm.getStartTime())){
            queryWrapper.eq(ApprovalVoucher.START_TIME,queryForm.getStartTime());
        }
        if(StringUtils.isNotBlank(queryForm.getEndTime())){
            queryWrapper.eq(ApprovalVoucher.END_TIME,queryForm.getEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ApprovalVoucher.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ApprovalVoucher.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ApprovalVoucher.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ApprovalVoucher.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(ApprovalVoucher.CONTRACT_ID,queryForm.getContractId());
        }
        return queryWrapper;
    }
     */

    /**
     * 根据合同id获取合同审批单据
     * @param id
     * @return
     */
    public ApprovalVoucher getApprovalFrist(String id){
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper <ApprovalVoucher>();
//        getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,id);
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE, "1");
        return approvalVoucherMapper.selectOne(queryWrapper);
    }
}

