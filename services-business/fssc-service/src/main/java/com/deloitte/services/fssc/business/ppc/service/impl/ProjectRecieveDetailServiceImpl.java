package com.deloitte.services.fssc.business.ppc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.ppc.param.ProjectRecieveDetailQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectRecieveDetail;
import com.deloitte.services.fssc.business.ppc.mapper.ProjectRecieveDetailMapper;
import com.deloitte.services.fssc.business.ppc.service.IProjectRecieveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectRecieveDetail服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProjectRecieveDetailServiceImpl extends ServiceImpl<ProjectRecieveDetailMapper, ProjectRecieveDetail> implements IProjectRecieveDetailService {


    @Autowired
    private ProjectRecieveDetailMapper projectRecieveDetailMapper;

    @Override
    public IPage<ProjectRecieveDetail> selectPage(ProjectRecieveDetailQueryForm queryForm ) {
        QueryWrapper<ProjectRecieveDetail> queryWrapper = new QueryWrapper <ProjectRecieveDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectRecieveDetailMapper.selectPage(new Page<ProjectRecieveDetail>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProjectRecieveDetail> selectList(ProjectRecieveDetailQueryForm queryForm) {
        QueryWrapper<ProjectRecieveDetail> queryWrapper = new QueryWrapper <ProjectRecieveDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectRecieveDetailMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByReceivePayment(List<Long> incomeSubTypeIds) {
        QueryWrapper<ProjectRecieveDetail> countWrapper = new QueryWrapper <>();
        countWrapper.in(ProjectRecieveDetail.IN_COME_SUB_TYPE_ID,incomeSubTypeIds);
        return this.count(countWrapper) > 0;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProjectRecieveDetail> getQueryWrapper(QueryWrapper<ProjectRecieveDetail> queryWrapper,ProjectRecieveDetailQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ProjectRecieveDetail.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(ProjectRecieveDetail.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ProjectRecieveDetail.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProjectRecieveDetail.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProjectRecieveDetail.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(ProjectRecieveDetail.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(ProjectRecieveDetail.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(ProjectRecieveDetail.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentId())){
            queryWrapper.eq(ProjectRecieveDetail.CONNECT_DOCUMENT_ID,queryForm.getConnectDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentType())){
            queryWrapper.eq(ProjectRecieveDetail.CONNECT_DOCUMENT_TYPE,queryForm.getConnectDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentNum())){
            queryWrapper.eq(ProjectRecieveDetail.CONNECT_DOCUMENT_NUM,queryForm.getConnectDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUnitName())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_UNIT_NAME,queryForm.getRecieveUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveBankName())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_BANK_NAME,queryForm.getRecieveBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveBankType())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_BANK_TYPE,queryForm.getRecieveBankType());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveBankAccount())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_BANK_ACCOUNT,queryForm.getRecieveBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeId())){
            queryWrapper.eq(ProjectRecieveDetail.IN_COME_SUB_TYPE_ID,queryForm.getInComeSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeCode())){
            queryWrapper.eq(ProjectRecieveDetail.IN_COME_SUB_TYPE_CODE,queryForm.getInComeSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeName())){
            queryWrapper.eq(ProjectRecieveDetail.IN_COME_SUB_TYPE_NAME,queryForm.getInComeSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveAmount())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_AMOUNT,queryForm.getRecieveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveConfirmAmount())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_CONFIRM_AMOUNT,queryForm.getRecieveConfirmAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveTime())){
            queryWrapper.eq(ProjectRecieveDetail.RECIEVE_TIME,queryForm.getRecieveTime());
        }
        if(StringUtils.isNotBlank(queryForm.getPayBankName())){
            queryWrapper.eq(ProjectRecieveDetail.PAY_BANK_NAME,queryForm.getPayBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getPayBankAccount())){
            queryWrapper.eq(ProjectRecieveDetail.PAY_BANK_ACCOUNT,queryForm.getPayBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getPayUnitName())){
            queryWrapper.eq(ProjectRecieveDetail.PAY_UNIT_NAME,queryForm.getPayUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(ProjectRecieveDetail.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(ProjectRecieveDetail.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(ProjectRecieveDetail.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(ProjectRecieveDetail.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(ProjectRecieveDetail.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(ProjectRecieveDetail.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(ProjectRecieveDetail.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(ProjectRecieveDetail.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(ProjectRecieveDetail.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(ProjectRecieveDetail.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(ProjectRecieveDetail.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(ProjectRecieveDetail.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(ProjectRecieveDetail.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(ProjectRecieveDetail.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(ProjectRecieveDetail.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(ProjectRecieveDetail.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(ProjectRecieveDetail.LINE_NUMBER,queryForm.getLineNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubSubjectCode())){
            queryWrapper.eq(ProjectRecieveDetail.IN_COME_SUB_SUBJECT_CODE,queryForm.getInComeSubSubjectCode());
        }
        return queryWrapper;
    }
     */
}

