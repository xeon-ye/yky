package com.deloitte.services.fssc.business.ito.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.ito.param.DetailsOfFundsQueryForm;
import com.deloitte.services.fssc.business.ito.entity.DetailsOfFunds;
import com.deloitte.services.fssc.business.ito.mapper.DetailsOfFundsMapper;
import com.deloitte.services.fssc.business.ito.service.IDetailsOfFundsService;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description :  DetailsOfFunds服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DetailsOfFundsServiceImpl extends ServiceImpl<DetailsOfFundsMapper, DetailsOfFunds> implements IDetailsOfFundsService {


    @Autowired
    private DetailsOfFundsMapper detailsOfFundsMapper;

    @Autowired
    private IRecieveLineMsgService recieveLineMsgService;

    @Override
    public IPage<DetailsOfFunds> selectPage(DetailsOfFundsQueryForm queryForm ) {
        QueryWrapper<DetailsOfFunds> queryWrapper = new QueryWrapper <>();
        //getQueryWrapper(queryWrapper,queryForm);
        return detailsOfFundsMapper.selectPage(new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<DetailsOfFunds> selectList(DetailsOfFundsQueryForm queryForm) {
        QueryWrapper<DetailsOfFunds> queryWrapper = new QueryWrapper <>();
        //getQueryWrapper(queryWrapper,queryForm);
        return detailsOfFundsMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByFunds(List<Long> incomeSubTypeIds) {
        QueryWrapper<DetailsOfFunds> queryWrapper = new QueryWrapper <>();
        queryWrapper.in(DetailsOfFunds.INCOME_SUB_TYPE_ID,incomeSubTypeIds);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public void setRecieveLineY(Long documentId,String documentType,String flag) {
        if(documentId!=null&&"ITO_INCOME_TURNED_OVER".equals(documentType)){
            QueryWrapper<DetailsOfFunds> fundsQueryWrapper=new QueryWrapper<>();
            fundsQueryWrapper.eq("DOCUMENT_ID",documentId);
            List<DetailsOfFunds> list = this.list(fundsQueryWrapper);
            if(CollectionUtils.isNotEmpty(list)){
                for (DetailsOfFunds funds:list){
                    Long receiptLineId = funds.getReceiptLineId();
                    if(receiptLineId!=null){
                        RecieveLineMsg msg = recieveLineMsgService.getById(receiptLineId);
                        if(msg!=null){
                            msg.setEx1(flag);
                            recieveLineMsgService.saveOrUpdate(msg);
                        }
                    }
                }
            }
        }
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<DetailsOfFunds> getQueryWrapper(QueryWrapper<DetailsOfFunds> queryWrapper,DetailsOfFundsQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(DetailsOfFunds.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(DetailsOfFunds.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(DetailsOfFunds.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(DetailsOfFunds.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(DetailsOfFunds.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(DetailsOfFunds.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(DetailsOfFunds.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(DetailsOfFunds.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(DetailsOfFunds.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(DetailsOfFunds.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(DetailsOfFunds.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(DetailsOfFunds.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(DetailsOfFunds.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(DetailsOfFunds.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(DetailsOfFunds.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(DetailsOfFunds.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(DetailsOfFunds.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(DetailsOfFunds.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(DetailsOfFunds.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(DetailsOfFunds.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeSubTypeId())){
            queryWrapper.eq(DetailsOfFunds.INCOME_SUB_TYPE_ID,queryForm.getIncomeSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeSubTypeCode())){
            queryWrapper.eq(DetailsOfFunds.INCOME_SUB_TYPE_CODE,queryForm.getIncomeSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeSubTypeName())){
            queryWrapper.eq(DetailsOfFunds.INCOME_SUB_TYPE_NAME,queryForm.getIncomeSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeTypeId())){
            queryWrapper.eq(DetailsOfFunds.INCOME_TYPE_ID,queryForm.getIncomeTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeTypeName())){
            queryWrapper.eq(DetailsOfFunds.INCOME_TYPE_NAME,queryForm.getIncomeTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeTypeCode())){
            queryWrapper.eq(DetailsOfFunds.INCOME_TYPE_CODE,queryForm.getIncomeTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountCode())){
            queryWrapper.eq(DetailsOfFunds.ACCOUNT_CODE,queryForm.getAccountCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetAccountCode())){
            queryWrapper.eq(DetailsOfFunds.BUDGET_ACCOUNT_CODE,queryForm.getBudgetAccountCode());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(DetailsOfFunds.LINE_NUMBER,queryForm.getLineNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiptNumber())){
            queryWrapper.eq(DetailsOfFunds.RECEIPT_NUMBER,queryForm.getReceiptNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getPaidUnit())){
            queryWrapper.eq(DetailsOfFunds.PAID_UNIT,queryForm.getPaidUnit());
        }
        if(StringUtils.isNotBlank(queryForm.getPaymentBankInfo())){
            queryWrapper.eq(DetailsOfFunds.PAYMENT_BANK_INFO,queryForm.getPaymentBankInfo());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountType())){
            queryWrapper.eq(DetailsOfFunds.ACCOUNT_TYPE,queryForm.getAccountType());
        }
        if(StringUtils.isNotBlank(queryForm.getPaymentBankAccount())){
            queryWrapper.eq(DetailsOfFunds.PAYMENT_BANK_ACCOUNT,queryForm.getPaymentBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getAmountPaid())){
            queryWrapper.eq(DetailsOfFunds.AMOUNT_PAID,queryForm.getAmountPaid());
        }
        if(StringUtils.isNotBlank(queryForm.getPaidTime())){
            queryWrapper.eq(DetailsOfFunds.PAID_TIME,queryForm.getPaidTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCollectionAccount())){
            queryWrapper.eq(DetailsOfFunds.COLLECTION_ACCOUNT,queryForm.getCollectionAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getCollectionNumber())){
            queryWrapper.eq(DetailsOfFunds.COLLECTION_NUMBER,queryForm.getCollectionNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(DetailsOfFunds.REMARK,queryForm.getRemark());
        }
        return queryWrapper;
    }
     */
}

