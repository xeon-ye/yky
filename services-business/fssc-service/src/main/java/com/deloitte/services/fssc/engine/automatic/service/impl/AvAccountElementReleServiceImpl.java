package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementReleQueryForm;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementReleService;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElementRele;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountElementReleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-27
 * @Description :  AvAccountElementRele服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvAccountElementReleServiceImpl extends ServiceImpl<AvAccountElementReleMapper, AvAccountElementRele> implements IAvAccountElementReleService {


    @Autowired
    private AvAccountElementReleMapper avAccountElementReleMapper;

    @Override
    public IPage<AvAccountElementRele> selectPage(AvAccountElementReleQueryForm queryForm ) {
        QueryWrapper<AvAccountElementRele> queryWrapper = new QueryWrapper <AvAccountElementRele>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avAccountElementReleMapper.selectPage(new Page<AvAccountElementRele>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvAccountElementRele> selectList(AvAccountElementReleQueryForm queryForm) {
        QueryWrapper<AvAccountElementRele> queryWrapper = new QueryWrapper <AvAccountElementRele>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avAccountElementReleMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AvAccountElementRele> getQueryWrapper(QueryWrapper<AvAccountElementRele> queryWrapper,AvAccountElementReleQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getElementId())){
            queryWrapper.eq(AvAccountElementRele.ELEMENT_ID,queryForm.getElementId());
        }
        if(StringUtils.isNotBlank(queryForm.getLedgerId())){
            queryWrapper.eq(AvAccountElementRele.LEDGER_ID,queryForm.getLedgerId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateDate())){
            queryWrapper.eq(AvAccountElementRele.CREATE_DATE,queryForm.getCreateDate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AvAccountElementRele.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateDate())){
            queryWrapper.eq(AvAccountElementRele.UPDATE_DATE,queryForm.getUpdateDate());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AvAccountElementRele.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx1())){
            queryWrapper.eq(AvAccountElementRele.ETX_1,queryForm.getEtx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx2())){
            queryWrapper.eq(AvAccountElementRele.ETX_2,queryForm.getEtx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx3())){
            queryWrapper.eq(AvAccountElementRele.ETX_3,queryForm.getEtx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx4())){
            queryWrapper.eq(AvAccountElementRele.ETX_4,queryForm.getEtx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx5())){
            queryWrapper.eq(AvAccountElementRele.ETX_5,queryForm.getEtx5());
        }
        return queryWrapper;
    }
     */
}

