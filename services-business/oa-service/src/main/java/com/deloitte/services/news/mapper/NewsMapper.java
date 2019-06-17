package com.deloitte.services.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.news.param.NewsQueryForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.services.news.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 院校新闻表 Mapper 接口
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-12
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

    List<News> selectHomeList(@Param(value = "num") int num, @Param(value = "newsTypeCode") String newsTypeCode);

    String getOrderNum(@Param(value = "head")String head);

    List<News> selectNewsPage(@Param("newsForm") NewsQueryForm newsForm, @Param("page") Page page);

    List<News> selectManagePage(@Param("newsForm") NewsQueryForm newsForm, @Param("page") Page page);

}
