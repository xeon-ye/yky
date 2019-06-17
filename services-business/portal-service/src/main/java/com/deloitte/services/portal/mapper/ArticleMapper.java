package com.deloitte.services.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.portal.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-02
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> getHomeList(int num);

}
