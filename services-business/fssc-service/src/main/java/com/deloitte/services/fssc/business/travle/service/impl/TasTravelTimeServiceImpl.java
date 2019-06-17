package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.travle.param.TasTravelTimeQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelTime;
import com.deloitte.services.fssc.business.travle.mapper.TasTravelTimeMapper;
import com.deloitte.services.fssc.business.travle.service.ITasTravelTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-04-02
 * @Description :  TasTravelTime服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasTravelTimeServiceImpl extends ServiceImpl<TasTravelTimeMapper, TasTravelTime> implements ITasTravelTimeService {


    @Autowired
    private TasTravelTimeMapper tasTravelTimeMapper;

    @Override
    public IPage<TasTravelTime> selectPage(TasTravelTimeQueryForm queryForm ) {
        QueryWrapper<TasTravelTime> queryWrapper = new QueryWrapper <TasTravelTime>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasTravelTimeMapper.selectPage(new Page<TasTravelTime>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TasTravelTime> selectList(TasTravelTimeQueryForm queryForm) {
        QueryWrapper<TasTravelTime> queryWrapper = new QueryWrapper <TasTravelTime>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasTravelTimeMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TasTravelTime> getQueryWrapper(QueryWrapper<TasTravelTime> queryWrapper,TasTravelTimeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(TasTravelTime.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelLineId())){
            queryWrapper.eq(TasTravelTime.TRAVEL_LINE_ID,queryForm.getTravelLineId());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelBeginTime())){
            queryWrapper.eq(TasTravelTime.TRAVEL_BEGIN_TIME,queryForm.getTravelBeginTime());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelEdnTime())){
            queryWrapper.eq(TasTravelTime.TRAVEL_EDN_TIME,queryForm.getTravelEdnTime());
        }
        return queryWrapper;
    }
     */
}

