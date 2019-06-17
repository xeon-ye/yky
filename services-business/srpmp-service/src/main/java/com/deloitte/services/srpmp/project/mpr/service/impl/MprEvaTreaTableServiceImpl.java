package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTreaTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTreaTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTreaTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTreaTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTreaTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTreaTableServiceImpl extends ServiceImpl<MprEvaTreaTableMapper, MprEvaTreaTable> implements IMprEvaTreaTableService {


    @Autowired
    private MprEvaTreaTableMapper mprEvaTreaTableMapper;

    @Override
    public IPage<MprEvaTreaTable> selectPage(MprEvaTreaTableQueryForm queryForm ) {
        QueryWrapper<MprEvaTreaTable> queryWrapper = new QueryWrapper <MprEvaTreaTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTreaTableMapper.selectPage(new Page<MprEvaTreaTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTreaTable> selectList(MprEvaTreaTableQueryForm queryForm) {
        QueryWrapper<MprEvaTreaTable> queryWrapper = new QueryWrapper <MprEvaTreaTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTreaTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTreaTable> getQueryWrapper(QueryWrapper<MprEvaTreaTable> queryWrapper,MprEvaTreaTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(MprEvaTreaTable.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTreaTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getTreaTopic())){
            queryWrapper.eq(MprEvaTreaTable.TREA_TOPIC,queryForm.getTreaTopic());
        }
        if(StringUtils.isNotBlank(queryForm.getJournalName())){
            queryWrapper.eq(MprEvaTreaTable.JOURNAL_NAME,queryForm.getJournalName());
        }
        if(StringUtils.isNotBlank(queryForm.getAuthor())){
            queryWrapper.eq(MprEvaTreaTable.AUTHOR,queryForm.getAuthor());
        }
        if(StringUtils.isNotBlank(queryForm.getAuthorType())){
            queryWrapper.eq(MprEvaTreaTable.AUTHOR_TYPE,queryForm.getAuthorType());
        }
        if(StringUtils.isNotBlank(queryForm.getYear())){
            queryWrapper.eq(MprEvaTreaTable.YEAR,queryForm.getYear());
        }
        if(StringUtils.isNotBlank(queryForm.getVolume())){
            queryWrapper.eq(MprEvaTreaTable.VOLUME,queryForm.getVolume());
        }
        if(StringUtils.isNotBlank(queryForm.getPage())){
            queryWrapper.eq(MprEvaTreaTable.PAGE,queryForm.getPage());
        }
        if(StringUtils.isNotBlank(queryForm.getSourceInclusion())){
            queryWrapper.eq(MprEvaTreaTable.SOURCE_INCLUSION,queryForm.getSourceInclusion());
        }
        if(StringUtils.isNotBlank(queryForm.getSciPartition())){
            queryWrapper.eq(MprEvaTreaTable.SCI_PARTITION,queryForm.getSciPartition());
        }
        if(StringUtils.isNotBlank(queryForm.getIssuTime())){
            queryWrapper.eq(MprEvaTreaTable.ISSU_TIME,queryForm.getIssuTime());
        }
        if(StringUtils.isNotBlank(queryForm.getImpactFactor())){
            queryWrapper.eq(MprEvaTreaTable.IMPACT_FACTOR,queryForm.getImpactFactor());
        }
        if(StringUtils.isNotBlank(queryForm.getRefeFreq())){
            queryWrapper.eq(MprEvaTreaTable.REFE_FREQ,queryForm.getRefeFreq());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTreaTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTreaTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTreaTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTreaTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

