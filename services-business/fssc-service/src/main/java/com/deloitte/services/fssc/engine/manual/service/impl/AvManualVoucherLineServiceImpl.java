package com.deloitte.services.fssc.engine.manual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherLineQueryForm;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.deloitte.services.fssc.engine.manual.mapper.AvManualVoucherLineMapper;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Map;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :  AvManualVoucherLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvManualVoucherLineServiceImpl extends ServiceImpl<AvManualVoucherLineMapper, AvManualVoucherLine> implements IAvManualVoucherLineService {


    @Autowired
    private AvManualVoucherLineMapper avManualVoucherLineMapper;

    @Override
    public IPage<AvManualVoucherLine> selectPage(AvManualVoucherLineQueryForm queryForm ) {
        QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avManualVoucherLineMapper.selectPage(new Page<AvManualVoucherLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvManualVoucherLine> selectList(AvManualVoucherLineQueryForm queryForm) {
        QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avManualVoucherLineMapper.selectList(queryWrapper);
    }
    public Map<String,Object> selectMapLimitOne(String documentType, Long documentId){
        return avManualVoucherLineMapper.selectMapLimitOne(documentType,documentId);
    }

    /**
     *  通用查询
     * @param ,queryForm
     * @return
    public QueryWrapper<AvManualVoucherLine> getQueryWrapper(QueryWrapper<AvManualVoucherLine> queryWrapper,AvManualVoucherLineQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getJeLineNum())){
            queryWrapper.eq(AvManualVoucherLine.JE_LINE_NUM,queryForm.getJeLineNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(AvManualVoucherLine.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountStructure())){
            queryWrapper.eq(AvManualVoucherLine.ACCOUNT_STRUCTURE,queryForm.getAccountStructure());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountStructureCode())){
            queryWrapper.eq(AvManualVoucherLine.ACCOUNT_STRUCTURE_CODE,queryForm.getAccountStructureCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountStructureDesc())){
            queryWrapper.eq(AvManualVoucherLine.ACCOUNT_STRUCTURE_DESC,queryForm.getAccountStructureDesc());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherType())){
            queryWrapper.eq(AvManualVoucherLine.VOUCHER_TYPE,queryForm.getVoucherType());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AvManualVoucherLine.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(AvManualVoucherLine.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AvManualVoucherLine.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AvManualVoucherLine.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(AvManualVoucherLine.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getJeHeaderId())){
            queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,queryForm.getJeHeaderId());
        }
        if(StringUtils.isNotBlank(queryForm.getLedger())){
            queryWrapper.eq(AvManualVoucherLine.LEDGER,queryForm.getLedger());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodName())){
            queryWrapper.eq(AvManualVoucherLine.PERIOD_NAME,queryForm.getPeriodName());
        }
        if(StringUtils.isNotBlank(queryForm.getEffectiveDate())){
            queryWrapper.eq(AvManualVoucherLine.EFFECTIVE_DATE,queryForm.getEffectiveDate());
        }
        if(StringUtils.isNotBlank(queryForm.getEnteredDr())){
            queryWrapper.eq(AvManualVoucherLine.ENTERED_DR,queryForm.getEnteredDr());
        }
        if(StringUtils.isNotBlank(queryForm.getEnteredCr())){
            queryWrapper.eq(AvManualVoucherLine.ENTERED_CR,queryForm.getEnteredCr());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountedDr())){
            queryWrapper.eq(AvManualVoucherLine.ACCOUNTED_DR,queryForm.getAccountedDr());
        }
        if(StringUtils.isNotBlank(queryForm.getAccountedCr())){
            queryWrapper.eq(AvManualVoucherLine.ACCOUNTED_CR,queryForm.getAccountedCr());
        }
        if(StringUtils.isNotBlank(queryForm.getDescription())){
            queryWrapper.eq(AvManualVoucherLine.DESCRIPTION,queryForm.getDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getLineTypeCode())){
            queryWrapper.eq(AvManualVoucherLine.LINE_TYPE_CODE,queryForm.getLineTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(AvManualVoucherLine.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getPostStatus())){
            queryWrapper.eq(AvManualVoucherLine.POST_STATUS,queryForm.getPostStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment1())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT1,queryForm.getSegment1());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment2())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT2,queryForm.getSegment2());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment3())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT3,queryForm.getSegment3());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment4())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT4,queryForm.getSegment4());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment5())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT5,queryForm.getSegment5());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment6())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT6,queryForm.getSegment6());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment7())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT7,queryForm.getSegment7());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment8())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT8,queryForm.getSegment8());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment9())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT9,queryForm.getSegment9());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment10())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT10,queryForm.getSegment10());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment11())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT11,queryForm.getSegment11());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment12())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT12,queryForm.getSegment12());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment13())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT13,queryForm.getSegment13());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment14())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT14,queryForm.getSegment14());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment15())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT15,queryForm.getSegment15());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment16())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT16,queryForm.getSegment16());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment17())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT17,queryForm.getSegment17());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment18())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT18,queryForm.getSegment18());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment19())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT19,queryForm.getSegment19());
        }
        if(StringUtils.isNotBlank(queryForm.getSegment20())){
            queryWrapper.eq(AvManualVoucherLine.SEGMENT20,queryForm.getSegment20());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(AvManualVoucherLine.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(AvManualVoucherLine.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(AvManualVoucherLine.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(AvManualVoucherLine.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(AvManualVoucherLine.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(AvManualVoucherLine.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(AvManualVoucherLine.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(AvManualVoucherLine.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(AvManualVoucherLine.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(AvManualVoucherLine.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(AvManualVoucherLine.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(AvManualVoucherLine.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(AvManualVoucherLine.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(AvManualVoucherLine.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(AvManualVoucherLine.EXT15,queryForm.getExt15());
        }
        return queryWrapper;
    }
     */

}

