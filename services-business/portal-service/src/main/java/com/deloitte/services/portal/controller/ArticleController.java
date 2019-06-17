package com.deloitte.services.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.portal.client.ArticleClient;
import com.deloitte.platform.api.portal.param.ArticleQueryForm;
import com.deloitte.platform.api.portal.vo.ArticleForm;
import com.deloitte.platform.api.portal.vo.ArticleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.portal.entity.Article;
import com.deloitte.services.portal.service.IArticleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :   Article控制器实现类
 * @Modified :
 */
@Api(tags = "Article操作接口")
@Slf4j
@RestController
public class ArticleController implements ArticleClient {

    @Autowired
    public IArticleService articleService;

    @Override
    @ApiOperation(value = "新增Article", notes = "新增一个Article")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "articleForm", value = "新增Article的form表单", required = true) ArticleForm articleForm) {
        log.info("form:{}", articleForm.toString());
        Article article = new BeanUtils<Article>().copyObj(articleForm, Article.class);
        return Result.success(articleService.save(article));
    }


    @Override
    @ApiOperation(value = "删除Article", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ArticleID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        articleService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Article", notes = "修改指定Article信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Article的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "articleForm", value = "修改Article的form表单", required = true) ArticleForm articleForm) {
        Article article = new BeanUtils<Article>().copyObj(articleForm, Article.class);
        article.setArticleId(id);
        articleService.saveOrUpdate(article);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Article", notes = "获取指定ID的Article信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Article的ID", required = true, dataType = "long")
    public Result<ArticleVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Article article = articleService.getById(id);
        ArticleVo articleVo = new BeanUtils<ArticleVo>().copyObj(article, ArticleVo.class);
        return new Result<ArticleVo>().sucess(articleVo);
    }

    @Override
    @ApiOperation(value = "列表查询Article", notes = "根据条件查询Article列表数据")
    public Result<List<ArticleVo>> list(@Valid @RequestBody @ApiParam(name = "articleQueryForm", value = "Article查询参数", required = true) ArticleQueryForm articleQueryForm) {
        log.info("search with articleQueryForm:{}", articleQueryForm.toString());
        List<Article> articleList = articleService.selectList(articleQueryForm);
        List<ArticleVo> articleVoList = new BeanUtils<ArticleVo>().copyObjs(articleList, ArticleVo.class);
        return new Result<List<ArticleVo>>().sucess(articleVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Article", notes = "根据条件查询Article分页数据")
    public Result<IPage<ArticleVo>> search(@Valid @RequestBody @ApiParam(name = "articleQueryForm", value = "Article查询参数", required = true) ArticleQueryForm articleQueryForm) {
        log.info("search with articleQueryForm:{}", articleQueryForm.toString());
        IPage<Article> articlePage = articleService.selectPage(articleQueryForm);
        IPage<ArticleVo> articleVoPage = new BeanUtils<ArticleVo>().copyPageObjs(articlePage, ArticleVo.class);
        return new Result<IPage<ArticleVo>>().sucess(articleVoPage);
    }

    /**
     * @param num 首页新闻栏展示新闻条数
     * @return 具体内容
     */
    @Override
    @ApiOperation(value = "首页查询Article", notes = "根据条件查询Article列表数据")
    public Result<List<ArticleVo>> getHomeList(@PathVariable int num) {
        log.info("home page news number:{}", num);
        List<Article> articleList = articleService.getHomeList(num);
        List<ArticleVo> articleVoList = new BeanUtils<ArticleVo>().copyObjs(articleList, ArticleVo.class);
        return new Result<List<ArticleVo>>().sucess(articleVoList);
    }
}



