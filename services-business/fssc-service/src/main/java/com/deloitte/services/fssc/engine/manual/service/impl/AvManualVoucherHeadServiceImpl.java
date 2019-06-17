package com.deloitte.services.fssc.engine.manual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherHeadQueryForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.mapper.AvBaseElementMapper;
import com.deloitte.services.fssc.engine.manual.mapper.AvManualVoucherHeadMapper;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherHeadService;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :  AvManualVoucherHead服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvManualVoucherHeadServiceImpl extends ServiceImpl<AvManualVoucherHeadMapper, AvManualVoucherHead> implements IAvManualVoucherHeadService {


    @Autowired
    private AvManualVoucherHeadMapper avManualVoucherHeadMapper;
    @Autowired
    private AvBaseElementMapper avBaseElementMapper;
    @Autowired
    private FsscCommonServices commonServices;

    @Override
    public IPage<AvManualVoucherHead> selectPage(AvManualVoucherHeadQueryForm queryForm ,Boolean lookAll) {
        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper <AvManualVoucherHead>();
        getQueryWrapper(queryWrapper,queryForm,lookAll);
        return avManualVoucherHeadMapper.selectPage(new Page<AvManualVoucherHead>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvManualVoucherHead> selectList(AvManualVoucherHeadQueryForm queryForm) {
        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper <AvManualVoucherHead>();
        getQueryWrapper(queryWrapper,queryForm,true);
        return avManualVoucherHeadMapper.selectList(queryWrapper);
    }

    /**
     *
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    public QueryWrapper<AvManualVoucherHead> getQueryWrapper(QueryWrapper<AvManualVoucherHead> queryWrapper,AvManualVoucherHeadQueryForm queryForm,Boolean lookAll){
        //条件拼接
        if(!lookAll){
            queryWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE, FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());//定义手工录入凭证
            UserVo userVo = commonServices.getCurrentUser();
            queryWrapper.eq(AvManualVoucherHead.CREATE_BY,Long.parseLong(userVo.getId()));//申请人
        }

        queryWrapper.orderByDesc(AvManualVoucherHead.CREATE_TIME);
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){//单据编码
            queryWrapper.like(AvManualVoucherHead.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){//审批状态
            queryWrapper.eq(AvManualVoucherHead.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){//单位名称
            queryWrapper.eq(AvManualVoucherHead.UNIT_ID,queryForm.getUnitName());
        }
        if(queryForm.getUnitId()!=null){//单位名称
            queryWrapper.eq(AvManualVoucherHead.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){//币种
            queryWrapper.eq(AvManualVoucherHead.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDocSeqNum())){//凭证编码
            queryWrapper.eq(AvManualVoucherHead.DOC_SEQ_NUM,queryForm.getDocSeqNum());
        }
        if(StringUtils.isNotBlank(queryForm.getJeCategory())){//业务类别
            queryWrapper.eq(AvManualVoucherHead.JE_CATEGORY,queryForm.getJeCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){//创建人
            queryWrapper.like(AvManualVoucherHead.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(queryForm.getTotalOriginalAmountStart()!=null){//原币金额范围
            queryWrapper.ge(AvManualVoucherHead.TOTAL_ORIGINAL_AMOUNT,queryForm.getTotalOriginalAmountStart());
        }
        if(queryForm.getTotalOriginalAmountEnd()!=null){
            queryWrapper.le(AvManualVoucherHead.TOTAL_ORIGINAL_AMOUNT,queryForm.getTotalOriginalAmountEnd());
        }
        if(queryForm.getTotalStandardAmountStart()!=null){//本币金额
            queryWrapper.ge(AvManualVoucherHead.TOTAL_STANDARD_AMOUNT,queryForm.getTotalStandardAmountStart());
        }
        if(queryForm.getTotalStandardAmountEnd()!=null){
            queryWrapper.le(AvManualVoucherHead.TOTAL_ORIGINAL_AMOUNT,queryForm.getTotalStandardAmountEnd());
        }
        if(queryForm.getCreateDateStart()!=null){//单据时间范围
            queryWrapper.ge(AvManualVoucherHead.CREATE_TIME,queryForm.getCreateDateStart());
        }
        if(queryForm.getCreateDateEnd()!=null){
            queryWrapper.le(AvManualVoucherHead.CREATE_TIME,queryForm.getCreateDateEnd());
        }
        if(queryForm.getDefaultEffectiveDateStart()!=null){//会计日期
            queryWrapper.ge(AvManualVoucherHead.DEFAULT_EFFECTIVE_DATE,queryForm.getDefaultEffectiveDateStart());
        }
        if(queryForm.getDefaultEffectiveDateEnd()!=null){
            queryWrapper.le(AvManualVoucherHead.DEFAULT_EFFECTIVE_DATE,queryForm.getDefaultEffectiveDateEnd());
        }

        if(StringUtils.isNotBlank(queryForm.getName())){//凭证名
            queryWrapper.like(AvManualVoucherHead.NAME,queryForm.getName());
        }
        return queryWrapper;
    }

    public List<AvBaseElement> selectJeCategory(){
        return  avBaseElementMapper.selectJeCategory();
    }

    public List<AvBaseElementVo> selectBaseElementByNum(Long ledgerId){
        return avBaseElementMapper.selectBaseElementByNum(ledgerId);
    }

}

