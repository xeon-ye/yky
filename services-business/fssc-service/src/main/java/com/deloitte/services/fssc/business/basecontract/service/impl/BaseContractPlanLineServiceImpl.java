package com.deloitte.services.fssc.business.basecontract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractPlanLineQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractPlanLineVo;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanLine;
import com.deloitte.services.fssc.business.basecontract.mapper.BaseContractPlanLineMapper;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractPlanLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :  BaseContractPlanLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseContractPlanLineServiceImpl extends ServiceImpl<BaseContractPlanLineMapper, BaseContractPlanLine> implements IBaseContractPlanLineService {


    @Autowired
    private BaseContractPlanLineMapper baseContractPlanLineMapper;

    @Override
    public IPage<BaseContractPlanLineVo> selectPage(BaseContractPlanLineQueryForm queryForm) {
        IPage<BaseContractPlanLineVo> planLineIPage=new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize());
        planLineIPage.setRecords(baseContractPlanLineMapper.findPlanDetail(planLineIPage,queryForm));
        return planLineIPage;

    }

    @Override
    public List<BaseContractPlanLine> selectList(BaseContractPlanLineQueryForm queryForm) {
        QueryWrapper<BaseContractPlanLine> queryWrapper = new QueryWrapper<BaseContractPlanLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return baseContractPlanLineMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return public QueryWrapper<BaseContractPlanLine> getQueryWrapper(QueryWrapper<BaseContractPlanLine> queryWrapper,BaseContractPlanLineQueryForm queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(queryForm.getCreateBy())){
    queryWrapper.eq(BaseContractPlanLine.CREATE_BY,queryForm.getCreateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
    queryWrapper.eq(BaseContractPlanLine.CREATE_USER_NAME,queryForm.getCreateUserName());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
    queryWrapper.eq(BaseContractPlanLine.UPDATE_BY,queryForm.getUpdateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
    queryWrapper.eq(BaseContractPlanLine.UPDATE_TIME,queryForm.getUpdateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateTime())){
    queryWrapper.eq(BaseContractPlanLine.CREATE_TIME,queryForm.getCreateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getVersion())){
    queryWrapper.eq(BaseContractPlanLine.VERSION,queryForm.getVersion());
    }
    if(StringUtils.isNotBlank(queryForm.getContractNo())){
    queryWrapper.eq(BaseContractPlanLine.CONTRACT_NO,queryForm.getContractNo());
    }
    if(StringUtils.isNotBlank(queryForm.getContractName())){
    queryWrapper.eq(BaseContractPlanLine.CONTRACT_NAME,queryForm.getContractName());
    }
    if(StringUtils.isNotBlank(queryForm.getTravelPlanId())){
    queryWrapper.eq(BaseContractPlanLine.TRAVEL_PLAN_ID,queryForm.getTravelPlanId());
    }
    if(StringUtils.isNotBlank(queryForm.getUnitName())){
    queryWrapper.eq(BaseContractPlanLine.UNIT_NAME,queryForm.getUnitName());
    }
    if(StringUtils.isNotBlank(queryForm.getDeptName())){
    queryWrapper.eq(BaseContractPlanLine.DEPT_NAME,queryForm.getDeptName());
    }
    if(StringUtils.isNotBlank(queryForm.getSideSubjectName())){
    queryWrapper.eq(BaseContractPlanLine.SIDE_SUBJECT_NAME,queryForm.getSideSubjectName());
    }
    if(StringUtils.isNotBlank(queryForm.getAppointPayStage())){
    queryWrapper.eq(BaseContractPlanLine.APPOINT_PAY_STAGE,queryForm.getAppointPayStage());
    }
    if(StringUtils.isNotBlank(queryForm.getAgreedPropertions())){
    queryWrapper.eq(BaseContractPlanLine.AGREED_PROPERTIONS,queryForm.getAgreedPropertions());
    }
    if(StringUtils.isNotBlank(queryForm.getAgreedPaymentAmount())){
    queryWrapper.eq(BaseContractPlanLine.AGREED_PAYMENT_AMOUNT,queryForm.getAgreedPaymentAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getPlanPayTime())){
    queryWrapper.eq(BaseContractPlanLine.PLAN_PAY_TIME,queryForm.getPlanPayTime());
    }
    if(StringUtils.isNotBlank(queryForm.getEx1())){
    queryWrapper.eq(BaseContractPlanLine.EX1,queryForm.getEx1());
    }
    if(StringUtils.isNotBlank(queryForm.getEx2())){
    queryWrapper.eq(BaseContractPlanLine.EX2,queryForm.getEx2());
    }
    if(StringUtils.isNotBlank(queryForm.getEx3())){
    queryWrapper.eq(BaseContractPlanLine.EX3,queryForm.getEx3());
    }
    if(StringUtils.isNotBlank(queryForm.getEx4())){
    queryWrapper.eq(BaseContractPlanLine.EX4,queryForm.getEx4());
    }
    if(StringUtils.isNotBlank(queryForm.getEx5())){
    queryWrapper.eq(BaseContractPlanLine.EX5,queryForm.getEx5());
    }
    if(StringUtils.isNotBlank(queryForm.getEx6())){
    queryWrapper.eq(BaseContractPlanLine.EX6,queryForm.getEx6());
    }
    if(StringUtils.isNotBlank(queryForm.getEx7())){
    queryWrapper.eq(BaseContractPlanLine.EX7,queryForm.getEx7());
    }
    if(StringUtils.isNotBlank(queryForm.getEx8())){
    queryWrapper.eq(BaseContractPlanLine.EX8,queryForm.getEx8());
    }
    if(StringUtils.isNotBlank(queryForm.getEx9())){
    queryWrapper.eq(BaseContractPlanLine.EX9,queryForm.getEx9());
    }
    if(StringUtils.isNotBlank(queryForm.getEx10())){
    queryWrapper.eq(BaseContractPlanLine.EX10,queryForm.getEx10());
    }
    if(StringUtils.isNotBlank(queryForm.getEx11())){
    queryWrapper.eq(BaseContractPlanLine.EX11,queryForm.getEx11());
    }
    if(StringUtils.isNotBlank(queryForm.getEx12())){
    queryWrapper.eq(BaseContractPlanLine.EX12,queryForm.getEx12());
    }
    if(StringUtils.isNotBlank(queryForm.getEx13())){
    queryWrapper.eq(BaseContractPlanLine.EX13,queryForm.getEx13());
    }
    if(StringUtils.isNotBlank(queryForm.getEx14())){
    queryWrapper.eq(BaseContractPlanLine.EX14,queryForm.getEx14());
    }
    if(StringUtils.isNotBlank(queryForm.getEx15())){
    queryWrapper.eq(BaseContractPlanLine.EX15,queryForm.getEx15());
    }
    return queryWrapper;
    }
     */
}

