package com.deloitte.services.fssc.business.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.pay.param.PyPamentPublicLineQueryForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentPublicLine;
import com.deloitte.services.fssc.business.pay.mapper.PyPamentPublicLineMapper;
import com.deloitte.services.fssc.business.pay.service.IPyPamentPublicLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentPublicLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PyPamentPublicLineServiceImpl extends ServiceImpl<PyPamentPublicLineMapper, PyPamentPublicLine> implements IPyPamentPublicLineService {


    @Autowired
    private PyPamentPublicLineMapper pyPamentPublicLineMapper;

    @Override
    public IPage<PyPamentPublicLine> selectPage(PyPamentPublicLineQueryForm queryForm ) {
        QueryWrapper<PyPamentPublicLine> queryWrapper = new QueryWrapper <PyPamentPublicLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentPublicLineMapper.selectPage(new Page<PyPamentPublicLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PyPamentPublicLine> selectList(PyPamentPublicLineQueryForm queryForm) {
        QueryWrapper<PyPamentPublicLine> queryWrapper = new QueryWrapper <PyPamentPublicLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentPublicLineMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PyPamentPublicLine> getQueryWrapper(QueryWrapper<PyPamentPublicLine> queryWrapper,PyPamentPublicLineQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PyPamentPublicLine.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(PyPamentPublicLine.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PyPamentPublicLine.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PyPamentPublicLine.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PyPamentPublicLine.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(PyPamentPublicLine.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(PyPamentPublicLine.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(PyPamentPublicLine.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentNum())){
            queryWrapper.eq(PyPamentPublicLine.CONNECT_DOCUMENT_NUM,queryForm.getConnectDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectDocumentType())){
            queryWrapper.eq(PyPamentPublicLine.CONNECT_DOCUMENT_TYPE,queryForm.getConnectDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getPayAmount())){
            queryWrapper.eq(PyPamentPublicLine.PAY_AMOUNT,queryForm.getPayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(PyPamentPublicLine.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getCommonCreditCode())){
            queryWrapper.eq(PyPamentPublicLine.COMMON_CREDIT_CODE,queryForm.getCommonCreditCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBankAccount())){
            queryWrapper.eq(PyPamentPublicLine.BANK_ACCOUNT,queryForm.getBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getBankName())){
            queryWrapper.eq(PyPamentPublicLine.BANK_NAME,queryForm.getBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getBranchBankName())){
            queryWrapper.eq(PyPamentPublicLine.BRANCH_BANK_NAME,queryForm.getBranchBankName());
        }
        if(StringUtils.isNotBlank(queryForm.getInterBranchNumber())){
            queryWrapper.eq(PyPamentPublicLine.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getBankInternationalCode())){
            queryWrapper.eq(PyPamentPublicLine.BANK_INTERNATIONAL_CODE,queryForm.getBankInternationalCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPayStatus())){
            queryWrapper.eq(PyPamentPublicLine.PAY_STATUS,queryForm.getPayStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(PyPamentPublicLine.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(PyPamentPublicLine.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(PyPamentPublicLine.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(PyPamentPublicLine.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(PyPamentPublicLine.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(PyPamentPublicLine.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(PyPamentPublicLine.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(PyPamentPublicLine.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(PyPamentPublicLine.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(PyPamentPublicLine.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(PyPamentPublicLine.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(PyPamentPublicLine.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(PyPamentPublicLine.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(PyPamentPublicLine.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(PyPamentPublicLine.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(PyPamentPublicLine.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

