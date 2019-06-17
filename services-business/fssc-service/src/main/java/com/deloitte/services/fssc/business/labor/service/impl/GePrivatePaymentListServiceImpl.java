package com.deloitte.services.fssc.business.labor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.labor.param.GePrivatePaymentListQueryForm;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.deloitte.services.fssc.business.labor.mapper.GePrivatePaymentListMapper;
import com.deloitte.services.fssc.business.labor.service.IGePrivatePaymentListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  GePrivatePaymentList服务实现类
 * @Modified :
 */
@Service
@Transactional
public class GePrivatePaymentListServiceImpl extends ServiceImpl<GePrivatePaymentListMapper, GePrivatePaymentList> implements IGePrivatePaymentListService {


    @Autowired
    private GePrivatePaymentListMapper gePrivatePaymentListMapper;

    @Override
    public IPage<GePrivatePaymentList> selectPage(GePrivatePaymentListQueryForm queryForm ) {
        QueryWrapper<GePrivatePaymentList> queryWrapper = new QueryWrapper <GePrivatePaymentList>();
        //getQueryWrapper(queryWrapper,queryForm);
        return gePrivatePaymentListMapper.selectPage(new Page<GePrivatePaymentList>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<GePrivatePaymentList> selectList(GePrivatePaymentListQueryForm queryForm) {
        QueryWrapper<GePrivatePaymentList> queryWrapper = new QueryWrapper <GePrivatePaymentList>();
        //getQueryWrapper(queryWrapper,queryForm);
        return gePrivatePaymentListMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<GePrivatePaymentList> getQueryWrapper(QueryWrapper<GePrivatePaymentList> queryWrapper,GePrivatePaymentListQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(GePrivatePaymentList.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(GePrivatePaymentList.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(GePrivatePaymentList.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(GePrivatePaymentList.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(GePrivatePaymentList.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(GePrivatePaymentList.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(GePrivatePaymentList.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(GePrivatePaymentList.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getPayAmount())){
            queryWrapper.eq(GePrivatePaymentList.PAY_AMOUNT,queryForm.getPayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveUserName())){
            queryWrapper.eq(GePrivatePaymentList.RECIEVE_USER_NAME,queryForm.getRecieveUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getCertType())){
            queryWrapper.eq(GePrivatePaymentList.CERT_TYPE,queryForm.getCertType());
        }
        if(StringUtils.isNotBlank(queryForm.getCertNum())){
            queryWrapper.eq(GePrivatePaymentList.CERT_NUM,queryForm.getCertNum());
        }
        if(StringUtils.isNotBlank(queryForm.getBankCode())){
            queryWrapper.eq(GePrivatePaymentList.BANK_CODE,queryForm.getBankCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBankId())){
            queryWrapper.eq(GePrivatePaymentList.BANK_ID,queryForm.getBankId());
        }
        if(StringUtils.isNotBlank(queryForm.getBankBame())){
            queryWrapper.eq(GePrivatePaymentList.BANK_BAME,queryForm.getBankBame());
        }
        if(StringUtils.isNotBlank(queryForm.getBanAccount())){
            queryWrapper.eq(GePrivatePaymentList.BAN_ACCOUNT,queryForm.getBanAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getInterBranchNumber())){
            queryWrapper.eq(GePrivatePaymentList.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getPayTime())){
            queryWrapper.eq(GePrivatePaymentList.PAY_TIME,queryForm.getPayTime());
        }
        if(StringUtils.isNotBlank(queryForm.getPayStatus())){
            queryWrapper.eq(GePrivatePaymentList.PAY_STATUS,queryForm.getPayStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(GePrivatePaymentList.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(GePrivatePaymentList.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(GePrivatePaymentList.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(GePrivatePaymentList.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(GePrivatePaymentList.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(GePrivatePaymentList.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(GePrivatePaymentList.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(GePrivatePaymentList.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(GePrivatePaymentList.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(GePrivatePaymentList.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(GePrivatePaymentList.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(GePrivatePaymentList.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(GePrivatePaymentList.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(GePrivatePaymentList.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(GePrivatePaymentList.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(GePrivatePaymentList.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(GePrivatePaymentList.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

