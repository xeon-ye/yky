package com.deloitte.services.fssc.engine.manual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.manual.param.AvCarryManualRelationQueryForm;
import com.deloitte.services.fssc.engine.manual.entity.AvCarryManualRelation;
import com.deloitte.services.fssc.engine.manual.service.IAvCarryManualRelationService;
import com.deloitte.services.fssc.engine.manual.mapper.AvCarryManualRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-05-08
 * @Description :  AvCarryManualRelation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvCarryManualRelationServiceImpl extends ServiceImpl<AvCarryManualRelationMapper, AvCarryManualRelation> implements IAvCarryManualRelationService {


    @Autowired
    private AvCarryManualRelationMapper avCarryManualRelationMapper;

    @Override
    public IPage<AvCarryManualRelation> selectPage(AvCarryManualRelationQueryForm queryForm ) {
        QueryWrapper<AvCarryManualRelation> queryWrapper = new QueryWrapper <AvCarryManualRelation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avCarryManualRelationMapper.selectPage(new Page<AvCarryManualRelation>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvCarryManualRelation> selectList(AvCarryManualRelationQueryForm queryForm) {
        QueryWrapper<AvCarryManualRelation> queryWrapper = new QueryWrapper <AvCarryManualRelation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avCarryManualRelationMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AvCarryManualRelation> getQueryWrapper(QueryWrapper<AvCarryManualRelation> queryWrapper,AvCarryManualRelationQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getJeHeaderId())){
            queryWrapper.eq(AvCarryManualRelation.JE_HEADER_ID,queryForm.getJeHeaderId());
        }
        if(StringUtils.isNotBlank(queryForm.getCarrayId())){
            queryWrapper.eq(AvCarryManualRelation.CARRAY_ID,queryForm.getCarrayId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AvCarryManualRelation.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AvCarryManualRelation.CREATE_BY,queryForm.getCreateBy());
        }
        return queryWrapper;
    }
     */
}

