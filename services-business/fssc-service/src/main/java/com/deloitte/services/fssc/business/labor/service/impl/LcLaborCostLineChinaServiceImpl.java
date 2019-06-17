package com.deloitte.services.fssc.business.labor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineChinaQueryForm;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineChina;
import com.deloitte.services.fssc.business.labor.mapper.LcLaborCostLineChinaMapper;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostLineChinaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCostLineChina服务实现类
 * @Modified :
 */
@Service
@Transactional
public class LcLaborCostLineChinaServiceImpl extends ServiceImpl<LcLaborCostLineChinaMapper, LcLaborCostLineChina> implements ILcLaborCostLineChinaService {


    @Autowired
    private LcLaborCostLineChinaMapper lcLaborCostLineChinaMapper;

    @Override
    public IPage<LcLaborCostLineChina> selectPage(LcLaborCostLineChinaQueryForm queryForm ) {
        QueryWrapper<LcLaborCostLineChina> queryWrapper = new QueryWrapper <LcLaborCostLineChina>();
        //getQueryWrapper(queryWrapper,queryForm);
        return lcLaborCostLineChinaMapper.selectPage(new Page<LcLaborCostLineChina>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<LcLaborCostLineChina> selectList(LcLaborCostLineChinaQueryForm queryForm) {
        QueryWrapper<LcLaborCostLineChina> queryWrapper = new QueryWrapper <LcLaborCostLineChina>();
        //getQueryWrapper(queryWrapper,queryForm);
        return lcLaborCostLineChinaMapper.selectList(queryWrapper);
    }

    public boolean existsByExpenseSubTypeLWCIds(List<Long> ExpenseSubTypeList) {
        return lcLaborCostLineChinaMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<LcLaborCostLineChina> getQueryWrapper(QueryWrapper<LcLaborCostLineChina> queryWrapper,LcLaborCostLineChinaQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(LcLaborCostLineChina.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(LcLaborCostLineChina.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(LcLaborCostLineChina.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(LcLaborCostLineChina.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(LcLaborCostLineChina.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(LcLaborCostLineChina.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeId())){
            queryWrapper.eq(LcLaborCostLineChina.SUB_TYPE_ID,queryForm.getSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeCode())){
            queryWrapper.eq(LcLaborCostLineChina.SUB_TYPE_CODE,queryForm.getSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUserName())){
            queryWrapper.eq(LcLaborCostLineChina.RECIEVE_USER_NAME,queryForm.getRecieveUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUserId())){
            queryWrapper.eq(LcLaborCostLineChina.RECIEVE_USER_ID,queryForm.getRecieveUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(LcLaborCostLineChina.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(LcLaborCostLineChina.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(LcLaborCostLineChina.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getPosition())){
            queryWrapper.eq(LcLaborCostLineChina.POSITION,queryForm.getPosition());
        }
        if(StringUtils.isNotBlank(queryForm.getCertType())){
            queryWrapper.eq(LcLaborCostLineChina.CERT_TYPE,queryForm.getCertType());
        }
        if(StringUtils.isNotBlank(queryForm.getCertNum())){
            queryWrapper.eq(LcLaborCostLineChina.CERT_NUM,queryForm.getCertNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDistributionStandard())){
            queryWrapper.eq(LcLaborCostLineChina.DISTRIBUTION_STANDARD,queryForm.getDistributionStandard());
        }
        if(StringUtils.isNotBlank(queryForm.getBankName())){
            queryWrapper.eq(LcLaborCostLineChina.BANK_NAME,queryForm.getBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getBankCode())){
            queryWrapper.eq(LcLaborCostLineChina.BANK_CODE,queryForm.getBankCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBankAccount())){
            queryWrapper.eq(LcLaborCostLineChina.BANK_ACCOUNT,queryForm.getBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getInterBranchNumber())){
            queryWrapper.eq(LcLaborCostLineChina.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getShouldGiveAmount())){
            queryWrapper.eq(LcLaborCostLineChina.SHOULD_GIVE_AMOUNT,queryForm.getShouldGiveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getDeductedAmount())){
            queryWrapper.eq(LcLaborCostLineChina.DEDUCTED_AMOUNT,queryForm.getDeductedAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRealGiveAmount())){
            queryWrapper.eq(LcLaborCostLineChina.REAL_GIVE_AMOUNT,queryForm.getRealGiveAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeName())){
            queryWrapper.eq(LcLaborCostLineChina.SUB_TYPE_NAME,queryForm.getSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(LcLaborCostLineChina.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(LcLaborCostLineChina.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(LcLaborCostLineChina.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(LcLaborCostLineChina.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(LcLaborCostLineChina.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(LcLaborCostLineChina.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(LcLaborCostLineChina.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(LcLaborCostLineChina.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(LcLaborCostLineChina.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(LcLaborCostLineChina.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(LcLaborCostLineChina.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(LcLaborCostLineChina.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(LcLaborCostLineChina.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(LcLaborCostLineChina.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(LcLaborCostLineChina.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(LcLaborCostLineChina.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(LcLaborCostLineChina.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

