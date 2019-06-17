package com.deloitte.services.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.portal.param.NoticeQueryForm;
import com.deloitte.services.portal.entity.Article;
import com.deloitte.services.portal.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-03
 * @Description : Notice服务类接口
 * @Modified :
 */
public interface INoticeService extends IService<Notice> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Notice>
     */
    IPage<Notice> selectPage(NoticeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Notice>
     */
    List<Notice> selectList(NoticeQueryForm queryForm);

    /**
     *  条件查询
     * @param num 首页展示公告条数
     * @return List<Article>
     */
    List<Notice> getHomeList(int num);
}
