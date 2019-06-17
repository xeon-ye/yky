package com.deloitte.services.news.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.news.client.NewsClient;
import com.deloitte.platform.api.oaservice.news.param.NewsQueryForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.news.entity.News;
import com.deloitte.services.news.service.INewsService;
import com.deloitte.services.oa.exception.OaErrorType;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :   News控制器实现类
 * @Modified :
 */
@Api(tags = "News操作接口")
@Slf4j
@RestController
@CrossOrigin
//@RequestMapping("/demo")
public class NewsController implements NewsClient {

    @Autowired
    public INewsService newsService;

    @Override
    @ApiOperation(value = "新增News", notes = "新增一个News")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "newsForm", value = "新增News的form表单", required = true) NewsForm newsForm) {
        log.info("form:{}", newsForm.toString());
        //无则保存，有则修改
        return Result.success(newsService.saveOrUpdate(newsForm));
    }


    @Override
    @ApiOperation(value = "删除News", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "NewsID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        News news = newsService.getById(id);
        if ("notRead".equals(news.getNewsStatus())) {
            return Result.fail(new ServiceException(OaErrorType.BPM_DELETE_ERROE));
        }
        newsService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改News", notes = "修改指定News信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "News的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "newsForm", value = "修改News的form表单", required = true) NewsForm newsForm) {
//        News news = new BeanUtils<News>().copyObj(newsForm, News.class);
//        news.setNewsId(id);
//        newsService.saveOrUpdate(news);
        newsService.update(id, newsForm);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取News", notes = "获取指定ID的News信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "News的ID", required = true, dataType = "string")
    public Result<NewsVo> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        News news = newsService.getById(id);
        if (null == news) {
            return Result.fail("无法获取到对应的id信息");
        }
        NewsVo newsVo = new BeanUtils<NewsVo>().copyObj(news, NewsVo.class);
        newsVo.setAttachments(new BeanUtils().copyObjs(news.getAttachments(), OaAttachmentVo.class));
        return new Result<NewsVo>().sucess(newsVo);
    }

//    @ApiOperation(value = "获取News", notes = "获取指定ID的News信息")
//    @ApiImplicitParam(paramType = "path", name = "id", value = "News的ID", required = true, dataType = "string")
//    @RequestMapping("/api")
//    public Result<NewsVo> select(String id) {
//        log.info("get with id:{}", id);
//        News news = newsService.getById(id);
//        if (null == news) {
//            return Result.fail("无法获取到对应的id信息");
//        }
//        NewsVo newsVo = new BeanUtils<NewsVo>().copyObj(news, NewsVo.class);
//        newsVo.setAttachments(new BeanUtils().copyObjs(news.getAttachments(), OaAttachmentVo.class));
//        return new Result<NewsVo>().sucess(newsVo);
//    }
//
//    @ApiOperation(value = "获取News", notes = "获取指定ID的News信息")
//    @ApiImplicitParam(paramType = "path", name = "id", value = "News的ID", required = true, dataType = "string")
//    @RequestMapping("/api/{id}")
//    public Result<NewsVo> selectDemo(@PathVariable String id) {
//        log.info("get with id:{}", id);
//        News news = newsService.getById(id);
//        if (null == news) {
//            return Result.fail("无法获取到对应的id信息");
//        }
//        NewsVo newsVo = new BeanUtils<NewsVo>().copyObj(news, NewsVo.class);
//        newsVo.setAttachments(new BeanUtils().copyObjs(news.getAttachments(), OaAttachmentVo.class));
//        return new Result<NewsVo>().sucess(newsVo);
//    }
//
//    @ApiOperation(value = "列表查询News", notes = "根据条件查询News列表数据")
//    @RequestMapping("/list")
//    public Result<List<NewsVo>> listData(@Valid @RequestBody @ApiParam(name = "newsQueryForm", value = "News查询参数", required = true) NewsQueryForm newsQueryForm) {
//        log.info("search with newsQueryForm:{}", newsQueryForm.toString());
//        List<News> newsList = newsService.selectList(newsQueryForm);
//        List<NewsVo> newsVoList = new BeanUtils<NewsVo>().copyObjs(newsList, NewsVo.class);
//        return new Result<List<NewsVo>>().sucess(newsVoList);
//    }

    /**
     * @param newsQueryForm 传入值
     * @return 返回展示信息
     */
    @Override
    @ApiOperation(value = "列表查询News", notes = "根据条件查询News列表数据")
    public Result<List<NewsVo>> list(@Valid @RequestBody @ApiParam(name = "newsQueryForm", value = "News查询参数", required = true) NewsQueryForm newsQueryForm) {
        log.info("search with newsQueryForm:{}", newsQueryForm.toString());
        List<News> newsList = newsService.selectList(newsQueryForm);
        List<NewsVo> newsVoList = new BeanUtils<NewsVo>().copyObjs(newsList, NewsVo.class);
        return new Result<List<NewsVo>>().sucess(newsVoList);
    }


    @Override
    @ApiOperation(value = "门户分页查询News", notes = "根据条件查询News分页数据")
    public Result<GdcPage<NewsVo>> search(@Valid @RequestBody @ApiParam(name = "newsQueryForm", value = "News查询参数", required = true) NewsQueryForm newsQueryForm, @RequestParam int page, @RequestParam int size) {
        log.info("search with newsQueryForm:{}", newsQueryForm.toString());
        IPage<News> newsIPage = newsService.selectPage(newsQueryForm, page, size);
        IPage<NewsVo> newsVoPage = new BeanUtils<NewsVo>().copyPageObjs(newsIPage, NewsVo.class);
        return new Result<GdcPage<NewsVo>>().sucess(new GdcPage<>(newsVoPage));
//        IPage<News> newsPage = newsService.selectPage(newsQueryForm);
//        IPage<NewsVo> newsVoPage = new BeanUtils<NewsVo>().copyPageObjs(newsPage, NewsVo.class);
//        return new Result<IPage<NewsVo>>().sucess(newsVoPage);
    }

    @Override
    @ApiOperation(value = "协同办公分页查询News", notes = "根据条件查询News分页数据")
    public Result<GdcPage<NewsVo>> manageSearch(@Valid @RequestBody NewsQueryForm newsQueryForm, @RequestParam int currentPage, @RequestParam int pageSize) {
        log.info("search with newsQueryForm:{}", newsQueryForm.toString());
        IPage<News> newsIPage = newsService.selectManagePage(newsQueryForm, currentPage, pageSize);
        IPage<NewsVo> newsVoPage = new BeanUtils<NewsVo>().copyPageObjs(newsIPage, NewsVo.class);
        return new Result<GdcPage<NewsVo>>().sucess(new GdcPage<>(newsVoPage));
    }

    /**
     * 首页新闻展示条数与置顶设置
     *
     * @param num 首页新闻展示条数
     * @return 返回展示信息
     */
    @Override
    @ApiOperation(value = "首页新闻列表查询News", notes = "根据条件查询News列表数据")
    public Result<List<NewsVo>> homeList(Integer num, String newsTypeCode) {
        log.info("home page news number:{}", num);
        //不传或者非数字，默认查询5条数据
        if (num == null || !NumberUtils.isDigits(String.valueOf(num))) {
            num = 5;
        }
        //查询新闻列表
        List<News> newsList = newsService.selectHomeList(num, newsTypeCode);
        List<NewsVo> newsVoList = new BeanUtils<NewsVo>().copyObjs(newsList, NewsVo.class);
        return new Result<List<NewsVo>>().sucess(newsVoList);
    }

    @Override
    @ApiOperation(value = "协同办公待权限分页查询News", notes = "协同办公待权限分页查询News")
    public Result<IPage<NewsVo>> searchWithPermission(@Valid @RequestBody @ApiParam(name = "newsQueryForm", value = "news查询参数", required = true) NewsQueryForm newsQueryForm,
                                                      @RequestHeader(value = "token") String token) {
        log.info("search with newsQueryForm:{}", newsQueryForm.toString());
        IPage<News> oaNewsPage = newsService.selectPageWithPermission(newsQueryForm, token);
        IPage<NewsVo> oaNewsVoPage = new BeanUtils<NewsVo>().copyPageObjs(oaNewsPage, NewsVo.class);
        return new Result<IPage<NewsVo>>().sucess(oaNewsVoPage);
    }
}



