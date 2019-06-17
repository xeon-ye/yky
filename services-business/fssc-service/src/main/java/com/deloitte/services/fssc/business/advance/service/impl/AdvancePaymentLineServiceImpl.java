package com.deloitte.services.fssc.business.advance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.advance.param.AdvancePaymentLineQueryForm;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import com.deloitte.services.fssc.business.advance.mapper.AdvancePaymentLineMapper;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmAdvancePaymentLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AdvancePaymentLineServiceImpl extends ServiceImpl<AdvancePaymentLineMapper, AdvancePaymentLine> implements IAdvancePaymentLineService {


    @Autowired
    private AdvancePaymentLineMapper advancePaymentLineMapper;

    @Override
    public IPage<AdvancePaymentLine> selectPage(AdvancePaymentLineQueryForm queryForm ) {
        QueryWrapper<AdvancePaymentLine> queryWrapper = new QueryWrapper <AdvancePaymentLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return advancePaymentLineMapper.selectPage(new Page<AdvancePaymentLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AdvancePaymentLine> selectList(AdvancePaymentLineQueryForm queryForm) {
        QueryWrapper<AdvancePaymentLine> queryWrapper = new QueryWrapper <AdvancePaymentLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return advancePaymentLineMapper.selectList(queryWrapper);
    }

    @Override
    public List<AdvancePaymentLine> selectList(Long advancePaymentId) {
        QueryWrapper<AdvancePaymentLine> queryWrapper = new QueryWrapper();
        queryWrapper.eq(AdvancePaymentLine.DOCUMENT_ID,advancePaymentId);
        return this.list(queryWrapper);
    }

    public boolean existsByExpenseSubApTypeIds(List<Long> ExpenseSubTypeList) {
        return advancePaymentLineMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AdvancePaymentLine> getQueryWrapper(QueryWrapper<AdvancePaymentLine> queryWrapper,BaseQueryForm<AdvancePaymentLineQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getCreateBy())){
            queryWrapper.like(AdvancePaymentLine.CREATE_BY,bmAdvancePaymentLine.getCreateBy());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getCreateUserName())){
            queryWrapper.like(AdvancePaymentLine.CREATE_USER_NAME,bmAdvancePaymentLine.getCreateUserName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getUpdateBy())){
            queryWrapper.like(AdvancePaymentLine.UPDATE_BY,bmAdvancePaymentLine.getUpdateBy());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getUpdateTime())){
            queryWrapper.like(AdvancePaymentLine.UPDATE_TIME,bmAdvancePaymentLine.getUpdateTime());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getCreateTime())){
            queryWrapper.like(AdvancePaymentLine.CREATE_TIME,bmAdvancePaymentLine.getCreateTime());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getEx1())){
            queryWrapper.like(AdvancePaymentLine.EX1,bmAdvancePaymentLine.getEx1());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getEx2())){
            queryWrapper.like(AdvancePaymentLine.EX2,bmAdvancePaymentLine.getEx2());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getEx3())){
            queryWrapper.like(AdvancePaymentLine.EX3,bmAdvancePaymentLine.getEx3());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getEx4())){
            queryWrapper.like(AdvancePaymentLine.EX4,bmAdvancePaymentLine.getEx4());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getEx5())){
            queryWrapper.like(AdvancePaymentLine.EX5,bmAdvancePaymentLine.getEx5());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getVersion())){
            queryWrapper.like(AdvancePaymentLine.VERSION,bmAdvancePaymentLine.getVersion());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getMainTypeId())){
            queryWrapper.like(AdvancePaymentLine.MAIN_TYPE_ID,bmAdvancePaymentLine.getMainTypeId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getSubTypeId())){
            queryWrapper.like(AdvancePaymentLine.SUB_TYPE_ID,bmAdvancePaymentLine.getSubTypeId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getPrepaidAmount())){
            queryWrapper.like(AdvancePaymentLine.PREPAID_AMOUNT,bmAdvancePaymentLine.getPrepaidAmount());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getRemark())){
            queryWrapper.like(AdvancePaymentLine.REMARK,bmAdvancePaymentLine.getRemark());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getAdvancePaymentId())){
            queryWrapper.like(AdvancePaymentLine.DOCUMENT_ID,bmAdvancePaymentLine.getAdvancePaymentId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getMainTypeName())){
            queryWrapper.like(AdvancePaymentLine.MAIN_TYPE_NAME,bmAdvancePaymentLine.getMainTypeName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentLine.getSubTypeName())){
            queryWrapper.like(AdvancePaymentLine.SUB_TYPE_NAME,bmAdvancePaymentLine.getSubTypeName());
        }
        return queryWrapper;
    }
     */
}

