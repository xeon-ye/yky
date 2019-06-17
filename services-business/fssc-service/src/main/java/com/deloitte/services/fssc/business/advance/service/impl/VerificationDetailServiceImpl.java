package com.deloitte.services.fssc.business.advance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.advance.param.VerificationDetailQueryForm;
import com.deloitte.services.fssc.business.advance.entity.VerificationDetail;
import com.deloitte.services.fssc.business.advance.mapper.VerificationDetailMapper;
import com.deloitte.services.fssc.business.advance.service.IVerificationDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmVerificationDetail服务实现类
 * @Modified :
 */
@Service
@Transactional
public class VerificationDetailServiceImpl extends ServiceImpl<VerificationDetailMapper, VerificationDetail> implements IVerificationDetailService {


    @Autowired
    private VerificationDetailMapper verificationDetailMapper;

    @Override
    public IPage<VerificationDetail> selectPage(VerificationDetailQueryForm queryForm ) {
        QueryWrapper<VerificationDetail> queryWrapper = new QueryWrapper <VerificationDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return verificationDetailMapper.selectPage(new Page<VerificationDetail>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<VerificationDetail> selectList(VerificationDetailQueryForm queryForm) {
        QueryWrapper<VerificationDetail> queryWrapper = new QueryWrapper <VerificationDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return verificationDetailMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<VerificationDetail> getQueryWrapper(QueryWrapper<VerificationDetail> queryWrapper,BaseQueryForm<VerificationDetailQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bmVerificationDetail.getCreateBy())){
            queryWrapper.like(VerificationDetail.CREATE_BY,bmVerificationDetail.getCreateBy());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getCreateUserName())){
            queryWrapper.like(VerificationDetail.CREATE_USER_NAME,bmVerificationDetail.getCreateUserName());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getUpdateBy())){
            queryWrapper.like(VerificationDetail.UPDATE_BY,bmVerificationDetail.getUpdateBy());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getUpdateTime())){
            queryWrapper.like(VerificationDetail.UPDATE_TIME,bmVerificationDetail.getUpdateTime());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getCreateTime())){
            queryWrapper.like(VerificationDetail.CREATE_TIME,bmVerificationDetail.getCreateTime());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getEx1())){
            queryWrapper.like(VerificationDetail.EX1,bmVerificationDetail.getEx1());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getEx2())){
            queryWrapper.like(VerificationDetail.EX2,bmVerificationDetail.getEx2());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getEx3())){
            queryWrapper.like(VerificationDetail.EX3,bmVerificationDetail.getEx3());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getEx4())){
            queryWrapper.like(VerificationDetail.EX4,bmVerificationDetail.getEx4());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getEx5())){
            queryWrapper.like(VerificationDetail.EX5,bmVerificationDetail.getEx5());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getVersion())){
            queryWrapper.like(VerificationDetail.VERSION,bmVerificationDetail.getVersion());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getMainTypeId())){
            queryWrapper.like(VerificationDetail.MAIN_TYPE_ID,bmVerificationDetail.getMainTypeId());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getSubTypeId())){
            queryWrapper.like(VerificationDetail.SUB_TYPE_ID,bmVerificationDetail.getSubTypeId());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getAdvancePaymentId())){
            queryWrapper.like(VerificationDetail.DOCUMENT_ID,bmVerificationDetail.getAdvancePaymentId());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getMainTypeName())){
            queryWrapper.like(VerificationDetail.MAIN_TYPE_NAME,bmVerificationDetail.getMainTypeName());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getSubTypeName())){
            queryWrapper.like(VerificationDetail.SUB_TYPE_NAME,bmVerificationDetail.getSubTypeName());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getBillType())){
            queryWrapper.like(VerificationDetail.BILL_TYPE,bmVerificationDetail.getBillType());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getBillCode())){
            queryWrapper.like(VerificationDetail.BILL_CODE,bmVerificationDetail.getBillCode());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getBillId())){
            queryWrapper.like(VerificationDetail.BILL_ID,bmVerificationDetail.getBillId());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getVerificationAmount())){
            queryWrapper.like(VerificationDetail.VERIFICATION_AMOUNT,bmVerificationDetail.getVerificationAmount());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getVerificationTime())){
            queryWrapper.like(VerificationDetail.VERIFICATION_TIME,bmVerificationDetail.getVerificationTime());
        }
        if(StringUtils.isNotBlank(bmVerificationDetail.getVerificationExplain())){
            queryWrapper.like(VerificationDetail.VERIFICATION_EXPLAIN,bmVerificationDetail.getVerificationExplain());
        }
        return queryWrapper;
    }
     */
}

