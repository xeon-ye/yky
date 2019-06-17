package com.deloitte.services.fastway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.fastway.param.FastWayQueryForm;
import com.deloitte.services.fastway.entity.FastWay;
import com.deloitte.services.fastway.mapper.FastWayMapper;
import com.deloitte.services.fastway.service.IFastWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description :  FastWay服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FastWayServiceImpl extends ServiceImpl<FastWayMapper, FastWay> implements IFastWayService {


    @Autowired
    private FastWayMapper fastWayMapper;

    @Override
    public IPage<FastWay> selectPage(FastWayQueryForm queryForm) {
        QueryWrapper<FastWay> queryWrapper = new QueryWrapper<FastWay>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fastWayMapper.selectPage(new Page<FastWay>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<FastWay> selectList(FastWayQueryForm queryForm) {
        QueryWrapper<FastWay> queryWrapper = new QueryWrapper<FastWay>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fastWayMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return public QueryWrapper<FastWay> getQueryWrapper(QueryWrapper<FastWay> queryWrapper,FastWayQueryForm queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(queryForm.getPicUrl())){
    queryWrapper.eq(FastWay.PIC_URL,queryForm.getPicUrl());
    }
    if(StringUtils.isNotBlank(queryForm.getApplyName())){
    queryWrapper.eq(FastWay.APPLY_NAME,queryForm.getApplyName());
    }
    if(StringUtils.isNotBlank(queryForm.getApplySort())){
    queryWrapper.eq(FastWay.APPLY_SORT,queryForm.getApplySort());
    }
    if(StringUtils.isNotBlank(queryForm.getApplyDatetime())){
    queryWrapper.eq(FastWay.APPLY_DATETIME,queryForm.getApplyDatetime());
    }
    if(StringUtils.isNotBlank(queryForm.getApplyUpdatetime())){
    queryWrapper.eq(FastWay.APPLY_UPDATETIME,queryForm.getApplyUpdatetime());
    }
    return queryWrapper;
    }
     */
}

