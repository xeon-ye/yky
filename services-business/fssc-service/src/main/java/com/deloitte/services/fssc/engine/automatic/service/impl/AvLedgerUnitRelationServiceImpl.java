package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvLedgerUnitRelationQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.deloitte.services.fssc.engine.automatic.mapper.AvLedgerUnitRelationMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvLedgerUnitRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-30
 * @Description :  AvLedgerUnitRelation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvLedgerUnitRelationServiceImpl extends ServiceImpl<AvLedgerUnitRelationMapper, AvLedgerUnitRelation> implements IAvLedgerUnitRelationService {


    @Autowired
    private AvLedgerUnitRelationMapper avLedgerUnitRelationMapper;

    @Override
    public IPage<AvLedgerUnitRelation> selectPage(AvLedgerUnitRelationQueryForm queryForm ) {
        QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper <AvLedgerUnitRelation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avLedgerUnitRelationMapper.selectPage(new Page<AvLedgerUnitRelation>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvLedgerUnitRelation> selectList(AvLedgerUnitRelationQueryForm queryForm) {
        QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper <AvLedgerUnitRelation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avLedgerUnitRelationMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AvLedgerUnitRelation> getQueryWrapper(QueryWrapper<AvLedgerUnitRelation> queryWrapper,AvLedgerUnitRelationQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBalanceCode())){
            queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,queryForm.getBalanceCode());
        }
        if(StringUtils.isNotBlank(queryForm.getLedgerId())){
            queryWrapper.eq(AvLedgerUnitRelation.LEDGER_ID,queryForm.getLedgerId());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(AvLedgerUnitRelation.EXT_1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(AvLedgerUnitRelation.EXT_2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(AvLedgerUnitRelation.EXT_3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(AvLedgerUnitRelation.EXT_4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(AvLedgerUnitRelation.EXT_5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AvLedgerUnitRelation.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateDate())){
            queryWrapper.eq(AvLedgerUnitRelation.CREATE_DATE,queryForm.getCreateDate());
        }
        return queryWrapper;
    }
     */

   public Long getLedgerId(String unitCode){
       QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper <AvLedgerUnitRelation>();
       queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,unitCode);
       AvLedgerUnitRelation entity = avLedgerUnitRelationMapper.selectOne(queryWrapper);
       return entity.getLedgerId();
    }
}

