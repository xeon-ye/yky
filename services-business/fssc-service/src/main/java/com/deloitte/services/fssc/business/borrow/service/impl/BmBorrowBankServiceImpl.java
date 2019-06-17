package com.deloitte.services.fssc.business.borrow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.borrow.param.BmBorrowBankQueryForm;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.mapper.BmBorrowBankMapper;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-06
 * @Description :  BmBorrowBank服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BmBorrowBankServiceImpl extends ServiceImpl<BmBorrowBankMapper, BmBorrowBank> implements IBmBorrowBankService {


    @Autowired
    private BmBorrowBankMapper bmBorrowBankMapper;

    @Override
    public IPage<BmBorrowBank> selectPage(BmBorrowBankQueryForm queryForm ) {
        QueryWrapper<BmBorrowBank> queryWrapper = new QueryWrapper <BmBorrowBank>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bmBorrowBankMapper.selectPage(new Page<BmBorrowBank>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BmBorrowBank> selectList(BmBorrowBankQueryForm queryForm) {
        QueryWrapper<BmBorrowBank> queryWrapper = new QueryWrapper <BmBorrowBank>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bmBorrowBankMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BmBorrowBank> getQueryWrapper(QueryWrapper<BmBorrowBank> queryWrapper,BaseQueryForm<BmBorrowBankQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bmBorrowBank.getCreateBy())){
            queryWrapper.like(BmBorrowBank.CREATE_BY,bmBorrowBank.getCreateBy());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getCreateUserName())){
            queryWrapper.like(BmBorrowBank.CREATE_USER_NAME,bmBorrowBank.getCreateUserName());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getUpdateBy())){
            queryWrapper.like(BmBorrowBank.UPDATE_BY,bmBorrowBank.getUpdateBy());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getUpdateTime())){
            queryWrapper.like(BmBorrowBank.UPDATE_TIME,bmBorrowBank.getUpdateTime());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getCreateTime())){
            queryWrapper.like(BmBorrowBank.CREATE_TIME,bmBorrowBank.getCreateTime());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getEx1())){
            queryWrapper.like(BmBorrowBank.EX1,bmBorrowBank.getEx1());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getEx2())){
            queryWrapper.like(BmBorrowBank.EX2,bmBorrowBank.getEx2());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getEx3())){
            queryWrapper.like(BmBorrowBank.EX3,bmBorrowBank.getEx3());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getEx4())){
            queryWrapper.like(BmBorrowBank.EX4,bmBorrowBank.getEx4());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getEx5())){
            queryWrapper.like(BmBorrowBank.EX5,bmBorrowBank.getEx5());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getVersion())){
            queryWrapper.like(BmBorrowBank.VERSION,bmBorrowBank.getVersion());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getDocumentNum())){
            queryWrapper.like(BmBorrowBank.DOCUMENT_NUM,bmBorrowBank.getDocumentNum());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getPayStatus())){
            queryWrapper.like(BmBorrowBank.PAY_STATUS,bmBorrowBank.getPayStatus());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getBorrowAmount())){
            queryWrapper.like(BmBorrowBank.BORROW_AMOUNT,bmBorrowBank.getBorrowAmount());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getGetOrReturn())){
            queryWrapper.like(BmBorrowBank.GET_OR_RETURN,bmBorrowBank.getGetOrReturn());
        }
        if(StringUtils.isNotBlank(bmBorrowBank.getBorrowId())){
            queryWrapper.like(BmBorrowBank.BORROW_ID,bmBorrowBank.getBorrowId());
        }
        return queryWrapper;
    }
     */
}

