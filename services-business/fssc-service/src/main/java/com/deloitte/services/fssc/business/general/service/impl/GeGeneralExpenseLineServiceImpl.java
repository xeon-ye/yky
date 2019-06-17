package com.deloitte.services.fssc.business.general.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseLineQueryForm;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpenseLine;
import com.deloitte.services.fssc.business.general.mapper.GeGeneralExpenseLineMapper;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeGeneralExpenseLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class GeGeneralExpenseLineServiceImpl extends ServiceImpl<GeGeneralExpenseLineMapper, GeGeneralExpenseLine> implements IGeGeneralExpenseLineService {


    @Autowired
    private GeGeneralExpenseLineMapper geGeneralExpenseLineMapper;

    @Override
    public IPage<GeGeneralExpenseLine> selectPage(GeGeneralExpenseLineQueryForm queryForm ) {
        QueryWrapper<GeGeneralExpenseLine> queryWrapper = new QueryWrapper <GeGeneralExpenseLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geGeneralExpenseLineMapper.selectPage(new Page<GeGeneralExpenseLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<GeGeneralExpenseLine> selectList(GeGeneralExpenseLineQueryForm queryForm) {
        QueryWrapper<GeGeneralExpenseLine> queryWrapper = new QueryWrapper <GeGeneralExpenseLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geGeneralExpenseLineMapper.selectList(queryWrapper);
    }


    public boolean existsByExpenseSubTypeGeIds(List<Long> ExpenseSubTypeList) {
        return geGeneralExpenseLineMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<GeGeneralExpenseLine> getQueryWrapper(QueryWrapper<GeGeneralExpenseLine> queryWrapper,BaseQueryForm<GeGeneralExpenseLineQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getCreateBy())){
            queryWrapper.like(GeGeneralExpenseLine.CREATE_BY,geGeneralExpenseLine.getCreateBy());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getCreateUserName())){
            queryWrapper.like(GeGeneralExpenseLine.CREATE_USER_NAME,geGeneralExpenseLine.getCreateUserName());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getUpdateBy())){
            queryWrapper.like(GeGeneralExpenseLine.UPDATE_BY,geGeneralExpenseLine.getUpdateBy());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getUpdateTime())){
            queryWrapper.like(GeGeneralExpenseLine.UPDATE_TIME,geGeneralExpenseLine.getUpdateTime());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getCreateTime())){
            queryWrapper.like(GeGeneralExpenseLine.CREATE_TIME,geGeneralExpenseLine.getCreateTime());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getVersion())){
            queryWrapper.like(GeGeneralExpenseLine.VERSION,geGeneralExpenseLine.getVersion());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getDocumentNum())){
            queryWrapper.like(GeGeneralExpenseLine.DOCUMENT_NUM,geGeneralExpenseLine.getDocumentNum());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getSubTypeId())){
            queryWrapper.like(GeGeneralExpenseLine.SUB_TYPE_ID,geGeneralExpenseLine.getSubTypeId());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getSubTypeName())){
            queryWrapper.like(GeGeneralExpenseLine.SUB_TYPE_NAME,geGeneralExpenseLine.getSubTypeName());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getInvoiceCode())){
            queryWrapper.like(GeGeneralExpenseLine.INVOICE_CODE,geGeneralExpenseLine.getInvoiceCode());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getInvoiceNumber())){
            queryWrapper.like(GeGeneralExpenseLine.INVOICE_NUMBER,geGeneralExpenseLine.getInvoiceNumber());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getInvoiceAmount())){
            queryWrapper.like(GeGeneralExpenseLine.INVOICE_AMOUNT,geGeneralExpenseLine.getInvoiceAmount());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getTax())){
            queryWrapper.like(GeGeneralExpenseLine.TAX,geGeneralExpenseLine.getTax());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getTaxAmount())){
            queryWrapper.like(GeGeneralExpenseLine.TAX_AMOUNT,geGeneralExpenseLine.getTaxAmount());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getRemark())){
            queryWrapper.like(GeGeneralExpenseLine.REMARK,geGeneralExpenseLine.getRemark());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx1())){
            queryWrapper.like(GeGeneralExpenseLine.EX1,geGeneralExpenseLine.getEx1());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx2())){
            queryWrapper.like(GeGeneralExpenseLine.EX2,geGeneralExpenseLine.getEx2());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx3())){
            queryWrapper.like(GeGeneralExpenseLine.EX3,geGeneralExpenseLine.getEx3());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx4())){
            queryWrapper.like(GeGeneralExpenseLine.EX4,geGeneralExpenseLine.getEx4());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx5())){
            queryWrapper.like(GeGeneralExpenseLine.EX5,geGeneralExpenseLine.getEx5());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx6())){
            queryWrapper.like(GeGeneralExpenseLine.EX6,geGeneralExpenseLine.getEx6());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx7())){
            queryWrapper.like(GeGeneralExpenseLine.EX7,geGeneralExpenseLine.getEx7());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx8())){
            queryWrapper.like(GeGeneralExpenseLine.EX8,geGeneralExpenseLine.getEx8());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx9())){
            queryWrapper.like(GeGeneralExpenseLine.EX9,geGeneralExpenseLine.getEx9());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx10())){
            queryWrapper.like(GeGeneralExpenseLine.EX10,geGeneralExpenseLine.getEx10());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx11())){
            queryWrapper.like(GeGeneralExpenseLine.EX11,geGeneralExpenseLine.getEx11());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx12())){
            queryWrapper.like(GeGeneralExpenseLine.EX12,geGeneralExpenseLine.getEx12());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx13())){
            queryWrapper.like(GeGeneralExpenseLine.EX13,geGeneralExpenseLine.getEx13());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx14())){
            queryWrapper.like(GeGeneralExpenseLine.EX14,geGeneralExpenseLine.getEx14());
        }
        if(StringUtils.isNotBlank(geGeneralExpenseLine.getEx15())){
            queryWrapper.like(GeGeneralExpenseLine.EX15,geGeneralExpenseLine.getEx15());
        }
        return queryWrapper;
    }
     */
}

