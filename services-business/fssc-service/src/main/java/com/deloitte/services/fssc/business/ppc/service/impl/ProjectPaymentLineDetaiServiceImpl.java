package com.deloitte.services.fssc.business.ppc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.ppc.param.ProjectPaymentLineDetaiQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectPaymentLineDetai;
import com.deloitte.services.fssc.business.ppc.mapper.ProjectPaymentLineDetaiMapper;
import com.deloitte.services.fssc.business.ppc.service.IProjectPaymentLineDetaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectPaymentLineDetai服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProjectPaymentLineDetaiServiceImpl extends ServiceImpl<ProjectPaymentLineDetaiMapper, ProjectPaymentLineDetai> implements IProjectPaymentLineDetaiService {


    @Autowired
    private ProjectPaymentLineDetaiMapper projectPaymentLineDetaiMapper;

    @Override
    public IPage<ProjectPaymentLineDetai> selectPage(ProjectPaymentLineDetaiQueryForm queryForm ) {
        QueryWrapper<ProjectPaymentLineDetai> queryWrapper = new QueryWrapper <ProjectPaymentLineDetai>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectPaymentLineDetaiMapper.selectPage(new Page<ProjectPaymentLineDetai>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProjectPaymentLineDetai> selectList(ProjectPaymentLineDetaiQueryForm queryForm) {
        QueryWrapper<ProjectPaymentLineDetai> queryWrapper = new QueryWrapper <ProjectPaymentLineDetai>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectPaymentLineDetaiMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByReceivePayment(List<Long> incomeSubTypeIds) {
        QueryWrapper<ProjectPaymentLineDetai> countWrapper = new QueryWrapper();
        countWrapper.in(ProjectPaymentLineDetai.IN_COME_SUB_TYPE_ID,incomeSubTypeIds);
        return this.count(countWrapper) > 0;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProjectPaymentLineDetai> getQueryWrapper(QueryWrapper<ProjectPaymentLineDetai> queryWrapper,ProjectPaymentLineDetaiQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ProjectPaymentLineDetai.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(ProjectPaymentLineDetai.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ProjectPaymentLineDetai.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProjectPaymentLineDetai.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProjectPaymentLineDetai.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(ProjectPaymentLineDetai.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(ProjectPaymentLineDetai.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(ProjectPaymentLineDetai.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeId())){
            queryWrapper.eq(ProjectPaymentLineDetai.IN_COME_SUB_TYPE_ID,queryForm.getInComeSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubSubjectCode())){
            queryWrapper.eq(ProjectPaymentLineDetai.IN_COME_SUB_SUBJECT_CODE,queryForm.getInComeSubSubjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeCode())){
            queryWrapper.eq(ProjectPaymentLineDetai.IN_COME_SUB_TYPE_CODE,queryForm.getInComeSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeName())){
            queryWrapper.eq(ProjectPaymentLineDetai.IN_COME_SUB_TYPE_NAME,queryForm.getInComeSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getExpectedAmount())){
            queryWrapper.eq(ProjectPaymentLineDetai.EXPECTED_AMOUNT,queryForm.getExpectedAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getExpectedRecieveTime())){
            queryWrapper.eq(ProjectPaymentLineDetai.EXPECTED_RECIEVE_TIME,queryForm.getExpectedRecieveTime());
        }
        if(StringUtils.isNotBlank(queryForm.getAdverseUnitId())){
            queryWrapper.eq(ProjectPaymentLineDetai.ADVERSE_UNIT_ID,queryForm.getAdverseUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getAdverseUnitCode())){
            queryWrapper.eq(ProjectPaymentLineDetai.ADVERSE_UNIT_CODE,queryForm.getAdverseUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAdverseUnitName())){
            queryWrapper.eq(ProjectPaymentLineDetai.ADVERSE_UNIT_NAME,queryForm.getAdverseUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getCapitalSource())){
            queryWrapper.eq(ProjectPaymentLineDetai.CAPITAL_SOURCE,queryForm.getCapitalSource());
        }
        if(StringUtils.isNotBlank(queryForm.getAdverseCommonCreditCode())){
            queryWrapper.eq(ProjectPaymentLineDetai.ADVERSE_COMMON_CREDIT_CODE,queryForm.getAdverseCommonCreditCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAdverseBankName())){
            queryWrapper.eq(ProjectPaymentLineDetai.ADVERSE_BANK_NAME,queryForm.getAdverseBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getAdverseBankAccount())){
            queryWrapper.eq(ProjectPaymentLineDetai.ADVERSE_BANK_ACCOUNT,queryForm.getAdverseBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(ProjectPaymentLineDetai.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(ProjectPaymentLineDetai.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(ProjectPaymentLineDetai.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

