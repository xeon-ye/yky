package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTreaPopTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTreaPopTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTreaPopTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTreaPopTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTreaPopTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTreaPopTableServiceImpl extends ServiceImpl<MprEvaTreaPopTableMapper, MprEvaTreaPopTable> implements IMprEvaTreaPopTableService {


    @Autowired
    private MprEvaTreaPopTableMapper mprEvaTreaPopTableMapper;

    @Override
    public IPage<MprEvaTreaPopTable> selectPage(MprEvaTreaPopTableQueryForm queryForm ) {
        QueryWrapper<MprEvaTreaPopTable> queryWrapper = new QueryWrapper <MprEvaTreaPopTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTreaPopTableMapper.selectPage(new Page<MprEvaTreaPopTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTreaPopTable> selectList(MprEvaTreaPopTableQueryForm queryForm) {
        QueryWrapper<MprEvaTreaPopTable> queryWrapper = new QueryWrapper <MprEvaTreaPopTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTreaPopTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTreaPopTable> getQueryWrapper(QueryWrapper<MprEvaTreaPopTable> queryWrapper,MprEvaTreaPopTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTreaPopTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getPopularBaseName())){
            queryWrapper.eq(MprEvaTreaPopTable.POPULAR_BASE_NAME,queryForm.getPopularBaseName());
        }
        if(StringUtils.isNotBlank(queryForm.getPopularScienceContent())){
            queryWrapper.eq(MprEvaTreaPopTable.POPULAR_SCIENCE_CONTENT,queryForm.getPopularScienceContent());
        }
        if(StringUtils.isNotBlank(queryForm.getYearEstablished())){
            queryWrapper.eq(MprEvaTreaPopTable.YEAR_ESTABLISHED,queryForm.getYearEstablished());
        }
        if(StringUtils.isNotBlank(queryForm.getReceptionNumber())){
            queryWrapper.eq(MprEvaTreaPopTable.RECEPTION_NUMBER,queryForm.getReceptionNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTreaPopTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTreaPopTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTreaPopTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTreaPopTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

