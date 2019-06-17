package com.deloitte.services.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.portal.param.ArticleQueryForm;
import com.deloitte.services.portal.entity.Article;
import com.deloitte.services.portal.mapper.ArticleMapper;
import com.deloitte.services.portal.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :  Article服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public IPage<Article> selectPage(ArticleQueryForm queryForm ) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper <Article>();
        //getQueryWrapper(queryWrapper,queryForm);
        return articleMapper.selectPage(new Page<Article>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Article> selectList(ArticleQueryForm queryForm) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper <Article>();
        //getQueryWrapper(queryWrapper,queryForm);
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Article> getHomeList(int num) {
        return articleMapper.getHomeList(num);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Article> getQueryWrapper(QueryWrapper<Article> queryWrapper,ArticleQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getArticleCategoryId())){
            queryWrapper.eq(Article.ARTICLE_CATEGORY_ID,queryForm.getArticleCategoryId());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleTitle())){
            queryWrapper.eq(Article.ARTICLE_TITLE,queryForm.getArticleTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleThumbnails())){
            queryWrapper.eq(Article.ARTICLE_THUMBNAILS,queryForm.getArticleThumbnails());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleHit())){
            queryWrapper.eq(Article.ARTICLE_HIT,queryForm.getArticleHit());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleDatetime())){
            queryWrapper.eq(Article.ARTICLE_DATETIME,queryForm.getArticleDatetime());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleUpdatetime())){
            queryWrapper.eq(Article.ARTICLE_UPDATETIME,queryForm.getArticleUpdatetime());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleAuthor())){
            queryWrapper.eq(Article.ARTICLE_AUTHOR,queryForm.getArticleAuthor());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleContent())){
            queryWrapper.eq(Article.ARTICLE_CONTENT,queryForm.getArticleContent());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleUrl())){
            queryWrapper.eq(Article.ARTICLE_URL,queryForm.getArticleUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleMovieUrl())){
            queryWrapper.eq(Article.ARTICLE_MOVIE_URL,queryForm.getArticleMovieUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getArticleKeyword())){
            queryWrapper.eq(Article.ARTICLE_KEYWORD,queryForm.getArticleKeyword());
        }
        if(StringUtils.isNotBlank(queryForm.getDelFlg())){
            queryWrapper.eq(Article.DEL_FLG,queryForm.getDelFlg());
        }
        return queryWrapper;
    }
     */
}

