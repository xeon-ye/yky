package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.DictQueryForm;
import com.deloitte.platform.api.isump.param.DictQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.Dict;
import com.deloitte.services.isump.mapper.DictMapper;
import com.deloitte.services.isump.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.isump.util.DictUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Dict服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {




    @Autowired
    private DictMapper dictMapper;

    @Override
    public boolean save(Dict entity) {
        boolean result = super.save(entity);
        DictUtil.setDict(entity.getParentCode());
        return result;
    }

    @Override
    public boolean removeById(Serializable id) {
        Dict dict = dictMapper.selectById(id);
        boolean result = super.removeById(id);
        DictUtil.setDict(dict.getParentCode());
        return result;
    }

    @Override
    public IPage<Dict> selectPage(DictQueryForm queryForm ) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper <Dict>();
        getQueryWrapper(queryWrapper,queryForm);
        return dictMapper.selectPage(new Page<Dict>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Dict> selectList(DictQueryForm queryForm) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper <Dict>();
        getQueryWrapper(queryWrapper,queryForm);
        return dictMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<Dict> getQueryWrapper(QueryWrapper<Dict> queryWrapper, BaseQueryForm<DictQueryParam> queryForm){
        DictQueryParam dict = queryForm.toParam(DictQueryParam.class);
        //条件拼接
        if(StringUtils.isNotBlank(dict.getName())){
            queryWrapper.like(Dict.NAME,dict.getName());
        }
        if(StringUtils.isNotBlank(dict.getCode())){
            queryWrapper.like(Dict.CODE,dict.getCode());
        }

        if(dict.getParentId() != null){
            queryWrapper.eq(Dict.PARENT_ID,dict.getParentId());
        }
        if(StringUtils.isNotBlank(dict.getState())){
            queryWrapper.eq(Dict.STATE,dict.getState());
        }
        if(StringUtils.isNotBlank(dict.getRemark())){
            queryWrapper.like(Dict.REMARK,dict.getRemark());
        }
        if(StringUtils.isNotBlank(dict.getReserve())){
            queryWrapper.eq(Dict.RESERVE,dict.getReserve());
        }
        if(StringUtils.isNotBlank(dict.getVersion())){
            queryWrapper.eq(Dict.VERSION,dict.getVersion());
        }
        if(dict.getCreateBy() != null){
            queryWrapper.eq(Dict.CREATE_BY,dict.getCreateBy());
        }
        if(dict.getUpdateBy() != null){
            queryWrapper.eq(Dict.UPDATE_BY,dict.getUpdateBy());
        }
        if(StringUtils.isNotBlank(dict.getParentCode())){
            queryWrapper.eq(Dict.PARENT_CODE,dict.getParentCode());
        }
        queryWrapper.orderByAsc(Dict.SORT);
        return queryWrapper;
    }
}

