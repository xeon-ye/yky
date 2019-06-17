package com.deloitte.services.fssc.business.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.pay.param.PyAllDocumentQueryForm;
import com.deloitte.platform.api.fssc.pay.param.PyPamentOrderInfoQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOverListVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.mapper.PyPamentOrderInfoMapper;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentOrderInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PyPamentOrderInfoServiceImpl extends ServiceImpl<PyPamentOrderInfoMapper, PyPamentOrderInfo> implements IPyPamentOrderInfoService {


    @Autowired
    private PyPamentOrderInfoMapper pyPamentOrderInfoMapper;

    @Autowired
    private FsscCommonServices commonServices;

    @Override
    public IPage<PyPamentOrderInfo> selectPage(PyPamentOrderInfoQueryForm queryForm ) {
        QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper <PyPamentOrderInfo>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPaymentType()),"PAYMENT_TYPE",queryForm.getPaymentType());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCurrencyCode()),"CURRENCY_CODE",queryForm.getCurrencyCode());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getBankAccount()),"BANK_ACCOUNT",queryForm.getBankAccount());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getIsAfterPatch()),"IS_AFTER_PATCH",queryForm.getIsAfterPatch());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_AMOUNT",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_AMOUNT",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.orderByDesc("CREATE_TIME");
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentOrderInfoMapper.selectPage(new Page<PyPamentOrderInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PyPamentOrderInfo> selectList(PyPamentOrderInfoQueryForm queryForm) {
        QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper <PyPamentOrderInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pyPamentOrderInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<PyPamentOverListVo> findDocumentAll(PyAllDocumentQueryForm form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.findDocumentAll(form);
    }
    public List<PyPamentOverListVo> findPrivateAll(PyAllDocumentQueryForm form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.findPrivateAll(form);
    }
    public  List<PyPamentOverListVo>  findBussinessCardAll(PyAllDocumentQueryForm form){
        UserVo currentUser = commonServices.getCurrentUser();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.findBussinessCardAll(form);
    }

    @Override
    public boolean selectCount(String documentNum) {
        QueryWrapper<PyPamentOrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PAY_DOCUMENT_NUM",documentNum);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public Integer geExpenseAllDocument(PyPamentOrderInfoVo form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.geExpenseAllDocument(form);
    }

    @Override
    public Integer tavelAllDocument(PyPamentOrderInfoVo form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.tavelAllDocument(form);
    }

    @Override
    public Integer advanceAllDocument(PyPamentOrderInfoVo form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.advanceAllDocument(form);
    }
    @Override
    public Integer borrowAllDocument(PyPamentOrderInfoVo form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.borrowAllDocument(form);
    }
    @Override
    public Integer laborAllDocument(PyPamentOrderInfoVo form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.laborAllDocument(form);
    }
    @Override
    public Integer contractAllDocument(PyPamentOrderInfoVo form) {
        UserVo currentUser = commonServices.getCurrentUser();
        //OrganizationVo currentOrg = commonServices.getCurrentOrg();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        return pyPamentOrderInfoMapper.contractAllDocument(form);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PyPamentOrderInfo> getQueryWrapper(QueryWrapper<PyPamentOrderInfo> queryWrapper,PyPamentOrderInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PyPamentOrderInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(PyPamentOrderInfo.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PyPamentOrderInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PyPamentOrderInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PyPamentOrderInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(PyPamentOrderInfo.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(PyPamentOrderInfo.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){
            queryWrapper.eq(PyPamentOrderInfo.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(PyPamentOrderInfo.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(PyPamentOrderInfo.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(PyPamentOrderInfo.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptCode())){
            queryWrapper.eq(PyPamentOrderInfo.DEPT_CODE,queryForm.getDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(PyPamentOrderInfo.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(PyPamentOrderInfo.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(PyPamentOrderInfo.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(PyPamentOrderInfo.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCost())){
            queryWrapper.eq(PyPamentOrderInfo.COST,queryForm.getCost());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalAmount())){
            queryWrapper.eq(PyPamentOrderInfo.TOTAL_AMOUNT,queryForm.getTotalAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalAmountOtherCurrency())){
            queryWrapper.eq(PyPamentOrderInfo.TOTAL_AMOUNT_OTHER_CURRENCY,queryForm.getTotalAmountOtherCurrency());
        }
        if(StringUtils.isNotBlank(queryForm.getPaidAmount())){
            queryWrapper.eq(PyPamentOrderInfo.PAID_AMOUNT,queryForm.getPaidAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getNoPaidAmount())){
            queryWrapper.eq(PyPamentOrderInfo.NO_PAID_AMOUNT,queryForm.getNoPaidAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getPayDocumentNum())){
            queryWrapper.eq(PyPamentOrderInfo.PAY_DOCUMENT_NUM,queryForm.getPayDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getPayDocumentType())){
            queryWrapper.eq(PyPamentOrderInfo.PAY_DOCUMENT_TYPE,queryForm.getPayDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getPaymentType())){
            queryWrapper.eq(PyPamentOrderInfo.PAYMENT_TYPE,queryForm.getPaymentType());
        }
        if(StringUtils.isNotBlank(queryForm.getBankAccount())){
            queryWrapper.eq(PyPamentOrderInfo.BANK_ACCOUNT,queryForm.getBankAccount());
        }
        if(StringUtils.isNotBlank(queryForm.getBankType())){
            queryWrapper.eq(PyPamentOrderInfo.BANK_TYPE,queryForm.getBankType());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAfterPatch())){
            queryWrapper.eq(PyPamentOrderInfo.IS_AFTER_PATCH,queryForm.getIsAfterPatch());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(PyPamentOrderInfo.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(PyPamentOrderInfo.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(PyPamentOrderInfo.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(PyPamentOrderInfo.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(PyPamentOrderInfo.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(PyPamentOrderInfo.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(PyPamentOrderInfo.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(PyPamentOrderInfo.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(PyPamentOrderInfo.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(PyPamentOrderInfo.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(PyPamentOrderInfo.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(PyPamentOrderInfo.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(PyPamentOrderInfo.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(PyPamentOrderInfo.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(PyPamentOrderInfo.EX15,queryForm.getEx15());
        }
        return queryWrapper;
    }
     */
}

