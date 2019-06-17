package com.deloitte.services.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.portal.param.ArticleQueryForm;
import com.deloitte.services.portal.entity.Article;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description : Article服务类接口
 * @Modified :
 */
public interface IArticleService extends IService<Article> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Article>
     */
    IPage<Article> selectPage(ArticleQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Article>
     */
    List<Article> selectList(ArticleQueryForm queryForm);

    /**
     *  条件查询
     * @param num 首页展示新闻条数
     * @return List<Article>
     */
    List<Article> getHomeList(int num);
}
