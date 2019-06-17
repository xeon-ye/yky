package com.deloitte.services.oaservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.param.OaMenuFavoritesQueryForm;
import com.deloitte.services.oaservice.entity.OaMenuFavorites;
import com.deloitte.services.oaservice.mapper.OaMenuFavoritesMapper;
import com.deloitte.services.oaservice.service.IOaMenuFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description :  OaMenuFavorites服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMenuFavoritesServiceImpl extends ServiceImpl<OaMenuFavoritesMapper, OaMenuFavorites> implements IOaMenuFavoritesService {


    @Autowired
    private OaMenuFavoritesMapper oaMenuFavoritesMapper;

    @Override
    public IPage<OaMenuFavorites> selectPage(OaMenuFavoritesQueryForm queryForm ) {
        QueryWrapper<OaMenuFavorites> queryWrapper = new QueryWrapper <OaMenuFavorites>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMenuFavoritesMapper.selectPage(new Page<OaMenuFavorites>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMenuFavorites> selectList(OaMenuFavoritesQueryForm queryForm) {
        QueryWrapper<OaMenuFavorites> queryWrapper = new QueryWrapper <OaMenuFavorites>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMenuFavoritesMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaMenuFavorites> getQueryWrapper(QueryWrapper<OaMenuFavorites> queryWrapper,OaMenuFavoritesQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMenuCode())){
            queryWrapper.eq(OaMenuFavorites.MENU_CODE,queryForm.getMenuCode());
        }
        if(StringUtils.isNotBlank(queryForm.getMenuName())){
            queryWrapper.eq(OaMenuFavorites.MENU_NAME,queryForm.getMenuName());
        }
        if(StringUtils.isNotBlank(queryForm.getMenuUrl())){
            queryWrapper.eq(OaMenuFavorites.MENU_URL,queryForm.getMenuUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getParentId())){
            queryWrapper.eq(OaMenuFavorites.PARENT_ID,queryForm.getParentId());
        }
        if(StringUtils.isNotBlank(queryForm.getModuleCode())){
            queryWrapper.eq(OaMenuFavorites.MODULE_CODE,queryForm.getModuleCode());
        }
        if(StringUtils.isNotBlank(queryForm.getModuleName())){
            queryWrapper.eq(OaMenuFavorites.MODULE_NAME,queryForm.getModuleName());
        }
        if(StringUtils.isNotBlank(queryForm.getIcon())){
            queryWrapper.eq(OaMenuFavorites.ICON,queryForm.getIcon());
        }
        if(StringUtils.isNotBlank(queryForm.getSystemSource())){
            queryWrapper.eq(OaMenuFavorites.SYSTEM_SOURCE,queryForm.getSystemSource());
        }

        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaMenuFavorites.CREATE_BY,queryForm.getCreateBy());
        }
        if(queryForm.getCreateTime()!=null){
            queryWrapper.eq(OaMenuFavorites.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaMenuFavorites.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(queryForm.getUpdateTime()!=null){
            queryWrapper.eq(OaMenuFavorites.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaMenuFavorites.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaMenuFavorites.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaMenuFavorites.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaMenuFavorites.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaMenuFavorites.EXT5,queryForm.getExt5());
        }
        queryWrapper.orderByAsc(OaMenuFavorites.ORDER_SORT);
        return queryWrapper;
    }

}

