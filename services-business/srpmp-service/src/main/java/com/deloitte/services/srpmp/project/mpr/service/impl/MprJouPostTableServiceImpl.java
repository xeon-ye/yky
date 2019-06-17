package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprJouPostTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprJouPostTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprJouPostTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprJouPostTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprJouPostTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprJouPostTableServiceImpl extends ServiceImpl<MprJouPostTableMapper, MprJouPostTable> implements IMprJouPostTableService {


    @Autowired
    private MprJouPostTableMapper mprJouPostTableMapper;

    @Override
    public IPage<MprJouPostTable> selectPage(MprJouPostTableQueryForm queryForm ) {
        QueryWrapper<MprJouPostTable> queryWrapper = new QueryWrapper <MprJouPostTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprJouPostTableMapper.selectPage(new Page<MprJouPostTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprJouPostTable> selectList(MprJouPostTableQueryForm queryForm) {
        QueryWrapper<MprJouPostTable> queryWrapper = new QueryWrapper <MprJouPostTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprJouPostTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprJouPostTable> getQueryWrapper(QueryWrapper<MprJouPostTable> queryWrapper,MprJouPostTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprJouPostTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getJouName())){
            queryWrapper.eq(MprJouPostTable.JOU_NAME,queryForm.getJouName());
        }
        if(StringUtils.isNotBlank(queryForm.getJouLevel())){
            queryWrapper.eq(MprJouPostTable.JOU_LEVEL,queryForm.getJouLevel());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(MprJouPostTable.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getPosition())){
            queryWrapper.eq(MprJouPostTable.POSITION,queryForm.getPosition());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprJouPostTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprJouPostTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprJouPostTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprJouPostTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

