package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvDailyRatesQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvDailyRates;
import com.deloitte.services.fssc.engine.automatic.mapper.AvDailyRatesMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvDailyRatesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvDailyRates服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvDailyRatesServiceImpl extends ServiceImpl<AvDailyRatesMapper, AvDailyRates> implements IAvDailyRatesService {


    @Autowired
    private AvDailyRatesMapper avDailyRatesMapper;

    @Override
    public IPage<AvDailyRates> selectPage(AvDailyRatesQueryForm queryForm ) {
        QueryWrapper<AvDailyRates> queryWrapper = new QueryWrapper <AvDailyRates>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avDailyRatesMapper.selectPage(new Page<AvDailyRates>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvDailyRates> selectList(AvDailyRatesQueryForm queryForm) {
        QueryWrapper<AvDailyRates> queryWrapper = new QueryWrapper <AvDailyRates>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avDailyRatesMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AvDailyRates> getQueryWrapper(QueryWrapper<AvDailyRates> queryWrapper,AvDailyRatesQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getFromCurrency())){
            queryWrapper.eq(AvDailyRates.FROM_CURRENCY,queryForm.getFromCurrency());
        }
        if(StringUtils.isNotBlank(queryForm.getToCurrency())){
            queryWrapper.eq(AvDailyRates.TO_CURRENCY,queryForm.getToCurrency());
        }
        if(StringUtils.isNotBlank(queryForm.getConversionDate())){
            queryWrapper.eq(AvDailyRates.CONVERSION_DATE,queryForm.getConversionDate());
        }
        if(StringUtils.isNotBlank(queryForm.getConversionType())){
            queryWrapper.eq(AvDailyRates.CONVERSION_TYPE,queryForm.getConversionType());
        }
        if(StringUtils.isNotBlank(queryForm.getConversionRate())){
            queryWrapper.eq(AvDailyRates.CONVERSION_RATE,queryForm.getConversionRate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateDate())){
            queryWrapper.eq(AvDailyRates.CREATE_DATE,queryForm.getCreateDate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AvDailyRates.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx1())){
            queryWrapper.eq(AvDailyRates.ETX_1,queryForm.getEtx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx2())){
            queryWrapper.eq(AvDailyRates.ETX_2,queryForm.getEtx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx3())){
            queryWrapper.eq(AvDailyRates.ETX_3,queryForm.getEtx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx4())){
            queryWrapper.eq(AvDailyRates.ETX_4,queryForm.getEtx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx5())){
            queryWrapper.eq(AvDailyRates.ETX_5,queryForm.getEtx5());
        }
        return queryWrapper;
    }
     */
}

