package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.travle.param.TasTravelLineQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelLine;
import com.deloitte.services.fssc.business.travle.mapper.TasTravelLineMapper;
import com.deloitte.services.fssc.business.travle.service.ITasTravelLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description :  TasTravelLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasTravelLineServiceImpl extends
        ServiceImpl<TasTravelLineMapper, TasTravelLine> implements ITasTravelLineService {


    @Autowired
    private TasTravelLineMapper tasTravelLineMapper;

    @Override
    public IPage<TasTravelLine> selectPage(TasTravelLineQueryForm queryForm) {
        QueryWrapper<TasTravelLine> queryWrapper = new QueryWrapper<TasTravelLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasTravelLineMapper.selectPage(
                new Page<TasTravelLine>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);

    }

    @Override
    public List<TasTravelLine> selectList(TasTravelLineQueryForm queryForm) {
        QueryWrapper<TasTravelLine> queryWrapper = new QueryWrapper<TasTravelLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasTravelLineMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByExpenseSubTypeTsIds(List<Long> ExpenseSubTypeList) {
        return tasTravelLineMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return public QueryWrapper<TasTravelLine> getQueryWrapper(QueryWrapper<TasTravelLine> queryWrapper,TasTravelLineQueryForm queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(queryForm.getCreateBy())){
    queryWrapper.eq(TasTravelLine.CREATE_BY,queryForm.getCreateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
    queryWrapper.eq(TasTravelLine.CREATE_USER_NAME,queryForm.getCreateUserName());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
    queryWrapper.eq(TasTravelLine.UPDATE_BY,queryForm.getUpdateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
    queryWrapper.eq(TasTravelLine.UPDATE_TIME,queryForm.getUpdateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateTime())){
    queryWrapper.eq(TasTravelLine.CREATE_TIME,queryForm.getCreateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getExt1())){
    queryWrapper.eq(TasTravelLine.EXT1,queryForm.getExt1());
    }
    if(StringUtils.isNotBlank(queryForm.getExt2())){
    queryWrapper.eq(TasTravelLine.EXT2,queryForm.getExt2());
    }
    if(StringUtils.isNotBlank(queryForm.getExt3())){
    queryWrapper.eq(TasTravelLine.EXT3,queryForm.getExt3());
    }
    if(StringUtils.isNotBlank(queryForm.getExt4())){
    queryWrapper.eq(TasTravelLine.EXT4,queryForm.getExt4());
    }
    if(StringUtils.isNotBlank(queryForm.getExt5())){
    queryWrapper.eq(TasTravelLine.EXT5,queryForm.getExt5());
    }
    if(StringUtils.isNotBlank(queryForm.getExt6())){
    queryWrapper.eq(TasTravelLine.EXT6,queryForm.getExt6());
    }
    if(StringUtils.isNotBlank(queryForm.getExt7())){
    queryWrapper.eq(TasTravelLine.EXT7,queryForm.getExt7());
    }
    if(StringUtils.isNotBlank(queryForm.getExt8())){
    queryWrapper.eq(TasTravelLine.EXT8,queryForm.getExt8());
    }
    if(StringUtils.isNotBlank(queryForm.getExt9())){
    queryWrapper.eq(TasTravelLine.EXT9,queryForm.getExt9());
    }
    if(StringUtils.isNotBlank(queryForm.getExt10())){
    queryWrapper.eq(TasTravelLine.EXT10,queryForm.getExt10());
    }
    if(StringUtils.isNotBlank(queryForm.getExt11())){
    queryWrapper.eq(TasTravelLine.EXT11,queryForm.getExt11());
    }
    if(StringUtils.isNotBlank(queryForm.getExt12())){
    queryWrapper.eq(TasTravelLine.EXT12,queryForm.getExt12());
    }
    if(StringUtils.isNotBlank(queryForm.getExt13())){
    queryWrapper.eq(TasTravelLine.EXT13,queryForm.getExt13());
    }
    if(StringUtils.isNotBlank(queryForm.getExt14())){
    queryWrapper.eq(TasTravelLine.EXT14,queryForm.getExt14());
    }
    if(StringUtils.isNotBlank(queryForm.getExt15())){
    queryWrapper.eq(TasTravelLine.EXT15,queryForm.getExt15());
    }
    if(StringUtils.isNotBlank(queryForm.getVersion())){
    queryWrapper.eq(TasTravelLine.VERSION,queryForm.getVersion());
    }
    if(StringUtils.isNotBlank(queryForm.getName())){
    queryWrapper.eq(TasTravelLine.NAME,queryForm.getName());
    }
    if(StringUtils.isNotBlank(queryForm.getJobNumber())){
    queryWrapper.eq(TasTravelLine.JOB_NUMBER,queryForm.getJobNumber());
    }
    if(StringUtils.isNotBlank(queryForm.getLevelName())){
    queryWrapper.eq(TasTravelLine.LEVEL_NAME,queryForm.getLevelName());
    }
    if(StringUtils.isNotBlank(queryForm.getTravelBeginTime())){
    queryWrapper.eq(TasTravelLine.TRAVEL_BEGIN_TIME,queryForm.getTravelBeginTime());
    }
    if(StringUtils.isNotBlank(queryForm.getTravelEndTime())){
    queryWrapper.eq(TasTravelLine.TRAVEL_END_TIME,queryForm.getTravelEndTime());
    }
    if(StringUtils.isNotBlank(queryForm.getLocationBegTravel())){
    queryWrapper.eq(TasTravelLine.LOCATION_BEG_TRAVEL,queryForm.getLocationBegTravel());
    }
    if(StringUtils.isNotBlank(queryForm.getLocationEndTravel())){
    queryWrapper.eq(TasTravelLine.LOCATION_END_TRAVEL,queryForm.getLocationEndTravel());
    }
    if(StringUtils.isNotBlank(queryForm.getTotalForehead())){
    queryWrapper.eq(TasTravelLine.TOTAL_FOREHEAD,queryForm.getTotalForehead());
    }
    if(StringUtils.isNotBlank(queryForm.getRemark())){
    queryWrapper.eq(TasTravelLine.REMARK,queryForm.getRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getDayNum())){
    queryWrapper.eq(TasTravelLine.DAY_NUM,queryForm.getDayNum());
    }
    if(StringUtils.isNotBlank(queryForm.getUnitPrice())){
    queryWrapper.eq(TasTravelLine.UNIT_PRICE,queryForm.getUnitPrice());
    }
    if(StringUtils.isNotBlank(queryForm.getTotalSum())){
    queryWrapper.eq(TasTravelLine.TOTAL_SUM,queryForm.getTotalSum());
    }
    if(StringUtils.isNotBlank(queryForm.getLineNumber())){
    queryWrapper.eq(TasTravelLine.LINE_NUMBER,queryForm.getLineNumber());
    }
    if(StringUtils.isNotBlank(queryForm.getDocumentId())){
    queryWrapper.eq(TasTravelLine.DOCUMENT_ID,queryForm.getDocumentId());
    }
    if(StringUtils.isNotBlank(queryForm.getDocumentType())){
    queryWrapper.eq(TasTravelLine.DOCUMENT_TYPE,queryForm.getDocumentType());
    }
    if(StringUtils.isNotBlank(queryForm.getSubTypeId())){
    queryWrapper.eq(TasTravelLine.SUB_TYPE_ID,queryForm.getSubTypeId());
    }
    if(StringUtils.isNotBlank(queryForm.getSubTypeCode())){
    queryWrapper.eq(TasTravelLine.SUB_TYPE_CODE,queryForm.getSubTypeCode());
    }
    if(StringUtils.isNotBlank(queryForm.getSubTypeName())){
    queryWrapper.eq(TasTravelLine.SUB_TYPE_NAME,queryForm.getSubTypeName());
    }
    if(StringUtils.isNotBlank(queryForm.getInvoiceNumber())){
    queryWrapper.eq(TasTravelLine.INVOICE_NUMBER,queryForm.getInvoiceNumber());
    }
    if(StringUtils.isNotBlank(queryForm.getTaxAmount())){
    queryWrapper.eq(TasTravelLine.TAX_AMOUNT,queryForm.getTaxAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getTaxRate())){
    queryWrapper.eq(TasTravelLine.TAX_RATE,queryForm.getTaxRate());
    }
    return queryWrapper;
    }
     */
}

