package com.deloitte.services.fssc.business.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.pay.param.PyPamentPrivateLineQueryForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentPrivateLine;
import com.deloitte.services.fssc.business.pay.mapper.PyPamentPrivateLineMapper;
import com.deloitte.services.fssc.business.pay.service.IPyPamentPrivateLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentPrivateLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PyPamentPrivateLineServiceImpl extends ServiceImpl<PyPamentPrivateLineMapper, PyPamentPrivateLine> implements IPyPamentPrivateLineService {


    @Autowired
    private PyPamentPrivateLineMapper pyPamentPrivateLineMapper;

    @Override
    public IPage<PyPamentPrivateLine> selectPage(PyPamentPrivateLineQueryForm queryForm ) {
        QueryWrapper<PyPamentPrivateLine> queryWrapper = new QueryWrapper <PyPamentPrivateLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentPrivateLineMapper.selectPage(new Page<PyPamentPrivateLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PyPamentPrivateLine> selectList(PyPamentPrivateLineQueryForm queryForm) {
        QueryWrapper<PyPamentPrivateLine> queryWrapper = new QueryWrapper <PyPamentPrivateLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentPrivateLineMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PyPamentPrivateLine> getQueryWrapper(QueryWrapper<PyPamentPrivateLine> queryWrapper,PyPamentPrivateLineQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PyPamentPrivateLine.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(PyPamentPrivateLine.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PyPamentPrivateLine.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PyPamentPrivateLine.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PyPamentPrivateLine.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(PyPamentPrivateLine.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(PyPamentPrivateLine.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(PyPamentPrivateLine.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentNum())){
            queryWrapper.eq(PyPamentPrivateLine.CONNECT_DOCUMENT_NUM,queryForm.getConnectDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentType())){
            queryWrapper.eq(PyPamentPrivateLine.CONNECT_DOCUMENT_TYPE,queryForm.getConnectDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getPayAmount())){
            queryWrapper.eq(PyPamentPrivateLine.PAY_AMOUNT,queryForm.getPayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRecieveEmpName())){
            queryWrapper.eq(PyPamentPrivateLine.RECIEVE_EMP_NAME,queryForm.getRecieveEmpName());
        }
        if(StringUtils.isNotBlank(queryForm.getEmpNo())){
            queryWrapper.eq(PyPamentPrivateLine.EMP_NO,queryForm.getEmpNo());
        }
        if(StringUtils.isNotBlank(queryForm.getBankAccount())){
            queryWrapper.eq(PyPamentPrivateLine.BANK_ACCOUNT,queryForm.getBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getBankName())){
            queryWrapper.eq(PyPamentPrivateLine.BANK_NAME,queryForm.getBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getBranchBankName())){
            queryWrapper.eq(PyPamentPrivateLine.BRANCH_BANK_NAME,queryForm.getBranchBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getInterBranchNumber())){
            queryWrapper.eq(PyPamentPrivateLine.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getBankInternationalCode())){
            queryWrapper.eq(PyPamentPrivateLine.BANK_INTERNATIONAL_CODE,queryForm.getBankInternationalCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPayStatus())){
            queryWrapper.eq(PyPamentPrivateLine.PAY_STATUS,queryForm.getPayStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(PyPamentPrivateLine.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(PyPamentPrivateLine.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(PyPamentPrivateLine.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(PyPamentPrivateLine.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(PyPamentPrivateLine.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(PyPamentPrivateLine.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(PyPamentPrivateLine.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(PyPamentPrivateLine.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(PyPamentPrivateLine.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(PyPamentPrivateLine.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(PyPamentPrivateLine.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(PyPamentPrivateLine.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(PyPamentPrivateLine.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(PyPamentPrivateLine.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(PyPamentPrivateLine.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(PyPamentPrivateLine.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

