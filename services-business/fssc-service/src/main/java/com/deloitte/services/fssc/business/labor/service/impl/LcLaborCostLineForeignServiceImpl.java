package com.deloitte.services.fssc.business.labor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineForeignQueryForm;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineForeign;
import com.deloitte.services.fssc.business.labor.mapper.LcLaborCostLineForeignMapper;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostLineForeignService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCostLineForeign服务实现类
 * @Modified :
 */
@Service
@Transactional
public class LcLaborCostLineForeignServiceImpl extends ServiceImpl<LcLaborCostLineForeignMapper, LcLaborCostLineForeign> implements ILcLaborCostLineForeignService {


    @Autowired
    private LcLaborCostLineForeignMapper lcLaborCostLineForeignMapper;

    @Override
    public IPage<LcLaborCostLineForeign> selectPage(LcLaborCostLineForeignQueryForm queryForm ) {
        QueryWrapper<LcLaborCostLineForeign> queryWrapper = new QueryWrapper <LcLaborCostLineForeign>();
        //getQueryWrapper(queryWrapper,queryForm);
        return lcLaborCostLineForeignMapper.selectPage(new Page<LcLaborCostLineForeign>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<LcLaborCostLineForeign> selectList(LcLaborCostLineForeignQueryForm queryForm) {
        QueryWrapper<LcLaborCostLineForeign> queryWrapper = new QueryWrapper <LcLaborCostLineForeign>();
        //getQueryWrapper(queryWrapper,queryForm);
        return lcLaborCostLineForeignMapper.selectList(queryWrapper);
    }

    public boolean existsByExpenseSubTypeLWFIds(List<Long> ExpenseSubTypeList) {
        return lcLaborCostLineForeignMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<LcLaborCostLineForeign> getQueryWrapper(QueryWrapper<LcLaborCostLineForeign> queryWrapper,LcLaborCostLineForeignQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(LcLaborCostLineForeign.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(LcLaborCostLineForeign.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(LcLaborCostLineForeign.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(LcLaborCostLineForeign.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(LcLaborCostLineForeign.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(LcLaborCostLineForeign.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(LcLaborCostLineForeign.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeId())){
            queryWrapper.eq(LcLaborCostLineForeign.SUB_TYPE_ID,queryForm.getSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeCode())){
            queryWrapper.eq(LcLaborCostLineForeign.SUB_TYPE_CODE,queryForm.getSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUserName())){
            queryWrapper.eq(LcLaborCostLineForeign.RECIEVE_USER_NAME,queryForm.getRecieveUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getNationality())){
            queryWrapper.eq(LcLaborCostLineForeign.NATIONALITY,queryForm.getNationality());
        }
        if(StringUtils.isNotBlank(queryForm.getFirstInChina())){
            queryWrapper.eq(LcLaborCostLineForeign.FIRST_IN_CHINA,queryForm.getFirstInChina());
        }
        if(StringUtils.isNotBlank(queryForm.getBirthday())){
            queryWrapper.eq(LcLaborCostLineForeign.BIRTHDAY,queryForm.getBirthday());
        }
        if(StringUtils.isNotBlank(queryForm.getBirthAddress())){
            queryWrapper.eq(LcLaborCostLineForeign.BIRTH_ADDRESS,queryForm.getBirthAddress());
        }
        if(StringUtils.isNotBlank(queryForm.getGender())){
            queryWrapper.eq(LcLaborCostLineForeign.GENDER,queryForm.getGender());
        }
        if(StringUtils.isNotBlank(queryForm.getHasDomicileInChina())){
            queryWrapper.eq(LcLaborCostLineForeign.HAS_DOMICILE_IN_CHINA,queryForm.getHasDomicileInChina());
        }
        if(StringUtils.isNotBlank(queryForm.getEstimatedTimeOfDeparture())){
            queryWrapper.eq(LcLaborCostLineForeign.ESTIMATED_TIME_OF_DEPARTURE,queryForm.getEstimatedTimeOfDeparture());
        }
        if(StringUtils.isNotBlank(queryForm.getTypeOfEmployment())){
            queryWrapper.eq(LcLaborCostLineForeign.TYPE_OF_EMPLOYMENT,queryForm.getTypeOfEmployment());
        }
        if(StringUtils.isNotBlank(queryForm.getTypeOfEmploymentTime())){
            queryWrapper.eq(LcLaborCostLineForeign.TYPE_OF_EMPLOYMENT_TIME,queryForm.getTypeOfEmploymentTime());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUserId())){
            queryWrapper.eq(LcLaborCostLineForeign.RECIEVE_USER_ID,queryForm.getRecieveUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(LcLaborCostLineForeign.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(LcLaborCostLineForeign.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(LcLaborCostLineForeign.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getPosition())){
            queryWrapper.eq(LcLaborCostLineForeign.POSITION,queryForm.getPosition());
        }
        if(StringUtils.isNotBlank(queryForm.getCertType())){
            queryWrapper.eq(LcLaborCostLineForeign.CERT_TYPE,queryForm.getCertType());
        }
        if(StringUtils.isNotBlank(queryForm.getCertNum())){
            queryWrapper.eq(LcLaborCostLineForeign.CERT_NUM,queryForm.getCertNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDistributionStandard())){
            queryWrapper.eq(LcLaborCostLineForeign.DISTRIBUTION_STANDARD,queryForm.getDistributionStandard());
        }
        if(StringUtils.isNotBlank(queryForm.getBankName())){
            queryWrapper.eq(LcLaborCostLineForeign.BANK_NAME,queryForm.getBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getBankCode())){
            queryWrapper.eq(LcLaborCostLineForeign.BANK_CODE,queryForm.getBankCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBankAccount())){
            queryWrapper.eq(LcLaborCostLineForeign.BANK_ACCOUNT,queryForm.getBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getInterBranchNumber())){
            queryWrapper.eq(LcLaborCostLineForeign.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getShouldGiveAmount())){
            queryWrapper.eq(LcLaborCostLineForeign.SHOULD_GIVE_AMOUNT,queryForm.getShouldGiveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getDeductedAmount())){
            queryWrapper.eq(LcLaborCostLineForeign.DEDUCTED_AMOUNT,queryForm.getDeductedAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRealGiveAmount())){
            queryWrapper.eq(LcLaborCostLineForeign.REAL_GIVE_AMOUNT,queryForm.getRealGiveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeName())){
            queryWrapper.eq(LcLaborCostLineForeign.SUB_TYPE_NAME,queryForm.getSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(LcLaborCostLineForeign.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(LcLaborCostLineForeign.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(LcLaborCostLineForeign.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(LcLaborCostLineForeign.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(LcLaborCostLineForeign.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(LcLaborCostLineForeign.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(LcLaborCostLineForeign.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(LcLaborCostLineForeign.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(LcLaborCostLineForeign.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(LcLaborCostLineForeign.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(LcLaborCostLineForeign.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(LcLaborCostLineForeign.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(LcLaborCostLineForeign.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(LcLaborCostLineForeign.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(LcLaborCostLineForeign.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(LcLaborCostLineForeign.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(LcLaborCostLineForeign.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

