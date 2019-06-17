package com.deloitte.services.fssc.business.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.contract.param.CrbAssocAdvancePaymentQueryForm;
import com.deloitte.services.fssc.business.contract.entity.CrbAssocAdvancePayment;
import com.deloitte.services.fssc.business.contract.mapper.CrbAssocAdvancePaymentMapper;
import com.deloitte.services.fssc.business.contract.service.ICrbAssocAdvancePaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description :  CrbAssocAdvancePayment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class CrbAssocAdvancePaymentServiceImpl extends ServiceImpl<CrbAssocAdvancePaymentMapper, CrbAssocAdvancePayment> implements ICrbAssocAdvancePaymentService {


    @Autowired
    private CrbAssocAdvancePaymentMapper crbAssocAdvancePaymentMapper;

    @Override
    public IPage<CrbAssocAdvancePayment> selectPage(CrbAssocAdvancePaymentQueryForm queryForm ) {
        QueryWrapper<CrbAssocAdvancePayment> queryWrapper = new QueryWrapper <CrbAssocAdvancePayment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return crbAssocAdvancePaymentMapper.selectPage(new Page<CrbAssocAdvancePayment>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<CrbAssocAdvancePayment> selectList(CrbAssocAdvancePaymentQueryForm queryForm) {
        QueryWrapper<CrbAssocAdvancePayment> queryWrapper = new QueryWrapper <CrbAssocAdvancePayment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return crbAssocAdvancePaymentMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<CrbAssocAdvancePayment> getQueryWrapper(QueryWrapper<CrbAssocAdvancePayment> queryWrapper,CrbAssocAdvancePaymentQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(CrbAssocAdvancePayment.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(CrbAssocAdvancePayment.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(CrbAssocAdvancePayment.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(CrbAssocAdvancePayment.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(CrbAssocAdvancePayment.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(CrbAssocAdvancePayment.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(CrbAssocAdvancePayment.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeId())){
            queryWrapper.eq(CrbAssocAdvancePayment.MAIN_TYPE_ID,queryForm.getMainTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalSum())){
            queryWrapper.eq(CrbAssocAdvancePayment.TOTAL_SUM,queryForm.getTotalSum());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeName())){
            queryWrapper.eq(CrbAssocAdvancePayment.MAIN_TYPE_NAME,queryForm.getMainTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(CrbAssocAdvancePayment.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeCode())){
            queryWrapper.eq(CrbAssocAdvancePayment.MAIN_TYPE_CODE,queryForm.getMainTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(CrbAssocAdvancePayment.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(CrbAssocAdvancePayment.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getHasVerAmount())){
            queryWrapper.eq(CrbAssocAdvancePayment.HAS_VER_AMOUNT,queryForm.getHasVerAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getNoVerAmount())){
            queryWrapper.eq(CrbAssocAdvancePayment.NO_VER_AMOUNT,queryForm.getNoVerAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getThisVerAmount())){
            queryWrapper.eq(CrbAssocAdvancePayment.THIS_VER_AMOUNT,queryForm.getThisVerAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getVerInstructions())){
            queryWrapper.eq(CrbAssocAdvancePayment.VER_INSTRUCTIONS,queryForm.getVerInstructions());
        }
        if(StringUtils.isNotBlank(queryForm.getPrepaidAmount())){
            queryWrapper.eq(CrbAssocAdvancePayment.PREPAID_AMOUNT,queryForm.getPrepaidAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getCancellationTime())){
            queryWrapper.eq(CrbAssocAdvancePayment.CANCELLATION_TIME,queryForm.getCancellationTime());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeId())){
            queryWrapper.eq(CrbAssocAdvancePayment.SUB_TYPE_ID,queryForm.getSubTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeCode())){
            queryWrapper.eq(CrbAssocAdvancePayment.SUB_TYPE_CODE,queryForm.getSubTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSubTypeName())){
            queryWrapper.eq(CrbAssocAdvancePayment.SUB_TYPE_NAME,queryForm.getSubTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(CrbAssocAdvancePayment.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

