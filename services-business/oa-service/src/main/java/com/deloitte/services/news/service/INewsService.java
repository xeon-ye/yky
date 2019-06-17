package com.deloitte.services.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.news.param.NewsQueryForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.news.entity.News;
import com.deloitte.services.notice.entity.OaNoticeOther;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description : News服务类接口
 * @Modified :
 */
public interface INewsService extends IService<News> {

    /**
     * 门户分页查询
     *
     * @param queryForm
     * @return IPage<News>
     */
    IPage<News> selectPage(NewsQueryForm queryForm, int page, int size);

    /**
     * 协同办公分页查询
     *
     * @param queryForm
     * @return IPage<News>
     */
    IPage<News> selectManagePage(NewsQueryForm queryForm, int page, int size);

    /**
     * 条件查询
     *
     * @param queryForm
     * @return List<News>
     */
    List<News> selectList(NewsQueryForm queryForm);

    /**
     * 条件查询
     *
     * @param num 首页展示新闻条数
     * @return List<Article>
     */
    List<News> selectHomeList(int num, String newsTypeCode);

    /**
     * 新增新闻
     *
     * @param entity
     * @return
     */
    Result saveOrUpdate(NewsForm entity);

    /**
     * @param id
     * @return
     */
    News getById(String id);

    /**
     * 更新操作
     * @param id
     * @param newsForm
     * @return
     */
    Result update(long id, NewsForm newsForm);


    /**
     * 根据权限查找数据
     * @param newsQueryForm
     * @param token
     * @return
     */
    IPage<News> selectPageWithPermission(NewsQueryForm newsQueryForm, String token);

}
