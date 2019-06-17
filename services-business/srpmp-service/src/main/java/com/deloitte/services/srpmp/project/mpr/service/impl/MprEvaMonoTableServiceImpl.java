package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaMonoTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaMonoTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaMonoTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaMonoTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaMonoTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaMonoTableServiceImpl extends ServiceImpl<MprEvaMonoTableMapper, MprEvaMonoTable> implements IMprEvaMonoTableService {


    @Autowired
    private MprEvaMonoTableMapper mprEvaMonoTableMapper;

    @Override
    public IPage<MprEvaMonoTable> selectPage(MprEvaMonoTableQueryForm queryForm ) {
        QueryWrapper<MprEvaMonoTable> queryWrapper = new QueryWrapper <MprEvaMonoTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaMonoTableMapper.selectPage(new Page<MprEvaMonoTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaMonoTable> selectList(MprEvaMonoTableQueryForm queryForm) {
        QueryWrapper<MprEvaMonoTable> queryWrapper = new QueryWrapper <MprEvaMonoTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaMonoTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaMonoTable> getQueryWrapper(QueryWrapper<MprEvaMonoTable> queryWrapper,MprEvaMonoTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaMonoTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getMonographTeachName())){
            queryWrapper.eq(MprEvaMonoTable.MONOGRAPH_TEACH_NAME,queryForm.getMonographTeachName());
        }
        if(StringUtils.isNotBlank(queryForm.getCompletePerson())){
            queryWrapper.eq(MprEvaMonoTable.COMPLETE_PERSON,queryForm.getCompletePerson());
        }
        if(StringUtils.isNotBlank(queryForm.getLevel())){
            queryWrapper.eq(MprEvaMonoTable.LEVEL,queryForm.getLevel());
        }
        if(StringUtils.isNotBlank(queryForm.getIsbn())){
            queryWrapper.eq(MprEvaMonoTable.ISBN,queryForm.getIsbn());
        }
        if(StringUtils.isNotBlank(queryForm.getMonographType())){
            queryWrapper.eq(MprEvaMonoTable.MONOGRAPH_TYPE,queryForm.getMonographType());
        }
        if(StringUtils.isNotBlank(queryForm.getPubHouse())){
            queryWrapper.eq(MprEvaMonoTable.PUB_HOUSE,queryForm.getPubHouse());
        }
        if(StringUtils.isNotBlank(queryForm.getPubDate())){
            queryWrapper.eq(MprEvaMonoTable.PUB_DATE,queryForm.getPubDate());
        }
        if(StringUtils.isNotBlank(queryForm.getWordCount())){
            queryWrapper.eq(MprEvaMonoTable.WORD_COUNT,queryForm.getWordCount());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaMonoTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaMonoTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaMonoTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaMonoTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

