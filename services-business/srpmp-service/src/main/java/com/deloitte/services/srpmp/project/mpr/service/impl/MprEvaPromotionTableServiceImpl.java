package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaPromotionTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaPromotionTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaPromotionTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaPromotionTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaPromotionTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaPromotionTableServiceImpl extends ServiceImpl<MprEvaPromotionTableMapper, MprEvaPromotionTable> implements IMprEvaPromotionTableService {


    @Autowired
    private MprEvaPromotionTableMapper mprEvaPromotionTableMapper;

    @Override
    public IPage<MprEvaPromotionTable> selectPage(MprEvaPromotionTableQueryForm queryForm ) {
        QueryWrapper<MprEvaPromotionTable> queryWrapper = new QueryWrapper <MprEvaPromotionTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaPromotionTableMapper.selectPage(new Page<MprEvaPromotionTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaPromotionTable> selectList(MprEvaPromotionTableQueryForm queryForm) {
        QueryWrapper<MprEvaPromotionTable> queryWrapper = new QueryWrapper <MprEvaPromotionTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaPromotionTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaPromotionTable> getQueryWrapper(QueryWrapper<MprEvaPromotionTable> queryWrapper,MprEvaPromotionTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaPromotionTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getPromotionArea())){
            queryWrapper.eq(MprEvaPromotionTable.PROMOTION_AREA,queryForm.getPromotionArea());
        }
        if(StringUtils.isNotBlank(queryForm.getPromotionContent())){
            queryWrapper.eq(MprEvaPromotionTable.PROMOTION_CONTENT,queryForm.getPromotionContent());
        }
        if(StringUtils.isNotBlank(queryForm.getPromotionTimes())){
            queryWrapper.eq(MprEvaPromotionTable.PROMOTION_TIMES,queryForm.getPromotionTimes());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaPromotionTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaPromotionTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaPromotionTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaPromotionTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

