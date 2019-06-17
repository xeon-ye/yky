package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.param.OaMenuFavoritesQueryForm;
import com.deloitte.services.oaservice.entity.OaMenuFavorites;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description : OaMenuFavorites服务类接口
 * @Modified :
 */
public interface IOaMenuFavoritesService extends IService<OaMenuFavorites> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMenuFavorites>
     */
    IPage<OaMenuFavorites> selectPage(OaMenuFavoritesQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMenuFavorites>
     */
    List<OaMenuFavorites> selectList(OaMenuFavoritesQueryForm queryForm);
}
