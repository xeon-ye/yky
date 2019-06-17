package com.deloitte.services.fssc.business.rep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.rep.param.RecieveIncomeMsgQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveIncomeMsg;
import com.deloitte.services.fssc.business.rep.mapper.RecieveIncomeMsgMapper;
import com.deloitte.services.fssc.business.rep.service.IRecieveIncomeMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :  RecieveIncomeMsg服务实现类
 * @Modified :
 */
@Service
@Transactional
public class RecieveIncomeMsgServiceImpl extends ServiceImpl<RecieveIncomeMsgMapper, RecieveIncomeMsg> implements IRecieveIncomeMsgService {


    @Autowired
    private RecieveIncomeMsgMapper recieveIncomeMsgMapper;

    @Override
    public IPage<RecieveIncomeMsg> selectPage(RecieveIncomeMsgQueryForm queryForm ) {
        QueryWrapper<RecieveIncomeMsg> queryWrapper = new QueryWrapper <RecieveIncomeMsg>();
        //getQueryWrapper(queryWrapper,queryForm);
        return recieveIncomeMsgMapper.selectPage(new Page<RecieveIncomeMsg>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<RecieveIncomeMsg> selectList(RecieveIncomeMsgQueryForm queryForm) {
        QueryWrapper<RecieveIncomeMsg> queryWrapper = new QueryWrapper <RecieveIncomeMsg>();
        //getQueryWrapper(queryWrapper,queryForm);
        return recieveIncomeMsgMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<RecieveIncomeMsg> getQueryWrapper(QueryWrapper<RecieveIncomeMsg> queryWrapper,RecieveIncomeMsgQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(RecieveIncomeMsg.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(RecieveIncomeMsg.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(RecieveIncomeMsg.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(RecieveIncomeMsg.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(RecieveIncomeMsg.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(RecieveIncomeMsg.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(RecieveIncomeMsg.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(RecieveIncomeMsg.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeId())){
            queryWrapper.eq(RecieveIncomeMsg.IN_COME_SUB_TYPE_ID,queryForm.getInComeSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeCode())){
            queryWrapper.eq(RecieveIncomeMsg.IN_COME_SUB_TYPE_CODE,queryForm.getInComeSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInComeSubTypeName())){
            queryWrapper.eq(RecieveIncomeMsg.IN_COME_SUB_TYPE_NAME,queryForm.getInComeSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountCode())){
            queryWrapper.eq(RecieveIncomeMsg.ACCOUNT_CODE,queryForm.getAccountCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetAccountCode())){
            queryWrapper.eq(RecieveIncomeMsg.BUDGET_ACCOUNT_CODE,queryForm.getBudgetAccountCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTax())){
            queryWrapper.eq(RecieveIncomeMsg.TAX,queryForm.getTax());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxAmount())){
            queryWrapper.eq(RecieveIncomeMsg.TAX_AMOUNT,queryForm.getTaxAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalAmount())){
            queryWrapper.eq(RecieveIncomeMsg.TOTAL_AMOUNT,queryForm.getTotalAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(RecieveIncomeMsg.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(RecieveIncomeMsg.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(RecieveIncomeMsg.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(RecieveIncomeMsg.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(RecieveIncomeMsg.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(RecieveIncomeMsg.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(RecieveIncomeMsg.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(RecieveIncomeMsg.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(RecieveIncomeMsg.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(RecieveIncomeMsg.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(RecieveIncomeMsg.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(RecieveIncomeMsg.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(RecieveIncomeMsg.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(RecieveIncomeMsg.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(RecieveIncomeMsg.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(RecieveIncomeMsg.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(RecieveIncomeMsg.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

