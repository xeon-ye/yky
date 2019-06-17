package com.deloitte.services.fssc.business.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.pay.param.PyPamentBusinessLineQueryForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.deloitte.services.fssc.business.pay.mapper.PyPamentBusinessLineMapper;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentBusinessLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PyPamentBusinessLineServiceImpl extends ServiceImpl<PyPamentBusinessLineMapper, PyPamentBusinessLine> implements IPyPamentBusinessLineService {


    @Autowired
    private PyPamentBusinessLineMapper pyPamentBusinessLineMapper;

    @Override
    public IPage<PyPamentBusinessLine> selectPage(PyPamentBusinessLineQueryForm queryForm ) {
        QueryWrapper<PyPamentBusinessLine> queryWrapper = new QueryWrapper <PyPamentBusinessLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentBusinessLineMapper.selectPage(new Page<PyPamentBusinessLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PyPamentBusinessLine> selectList(PyPamentBusinessLineQueryForm queryForm) {
        QueryWrapper<PyPamentBusinessLine> queryWrapper = new QueryWrapper <PyPamentBusinessLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentBusinessLineMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PyPamentBusinessLine> getQueryWrapper(QueryWrapper<PyPamentBusinessLine> queryWrapper,PyPamentBusinessLineQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PyPamentBusinessLine.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(PyPamentBusinessLine.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PyPamentBusinessLine.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PyPamentBusinessLine.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PyPamentBusinessLine.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(PyPamentBusinessLine.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(PyPamentBusinessLine.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(PyPamentBusinessLine.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentNum())){
            queryWrapper.eq(PyPamentBusinessLine.CONNECT_DOCUMENT_NUM,queryForm.getConnectDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentType())){
            queryWrapper.eq(PyPamentBusinessLine.CONNECT_DOCUMENT_TYPE,queryForm.getConnectDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getTransactionAmount())){
            queryWrapper.eq(PyPamentBusinessLine.TRANSACTION_AMOUNT,queryForm.getTransactionAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getTransactionDate())){
            queryWrapper.eq(PyPamentBusinessLine.TRANSACTION_DATE,queryForm.getTransactionDate());
        }
        if(StringUtils.isNotBlank(queryForm.getPayAmount())){
            queryWrapper.eq(PyPamentBusinessLine.PAY_AMOUNT,queryForm.getPayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getBusinessCardNum())){
            queryWrapper.eq(PyPamentBusinessLine.BUSINESS_CARD_NUM,queryForm.getBusinessCardNum());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveEmpName())){
            queryWrapper.eq(PyPamentBusinessLine.RECIEVE_EMP_NAME,queryForm.getRecieveEmpName());
        }
        if(StringUtils.isNotBlank(queryForm.getEmpNo())){
            queryWrapper.eq(PyPamentBusinessLine.EMP_NO,queryForm.getEmpNo());
        }
        if(StringUtils.isNotBlank(queryForm.getBankAccount())){
            queryWrapper.eq(PyPamentBusinessLine.BANK_ACCOUNT,queryForm.getBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getBankName())){
            queryWrapper.eq(PyPamentBusinessLine.BANK_NAME,queryForm.getBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getBranchBankName())){
            queryWrapper.eq(PyPamentBusinessLine.BRANCH_BANK_NAME,queryForm.getBranchBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getInterBranchNumber())){
            queryWrapper.eq(PyPamentBusinessLine.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getBankInternationalCode())){
            queryWrapper.eq(PyPamentBusinessLine.BANK_INTERNATIONAL_CODE,queryForm.getBankInternationalCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPayStatus())){
            queryWrapper.eq(PyPamentBusinessLine.PAY_STATUS,queryForm.getPayStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(PyPamentBusinessLine.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(PyPamentBusinessLine.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(PyPamentBusinessLine.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(PyPamentBusinessLine.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(PyPamentBusinessLine.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(PyPamentBusinessLine.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(PyPamentBusinessLine.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(PyPamentBusinessLine.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(PyPamentBusinessLine.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(PyPamentBusinessLine.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(PyPamentBusinessLine.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(PyPamentBusinessLine.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(PyPamentBusinessLine.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(PyPamentBusinessLine.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(PyPamentBusinessLine.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(PyPamentBusinessLine.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

