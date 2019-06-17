package com.deloitte.services.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentForm;
import com.deloitte.platform.api.oaservice.news.param.NewsQueryForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import com.deloitte.services.news.entity.News;
import com.deloitte.services.news.mapper.NewsMapper;
import com.deloitte.services.news.service.INewsService;
import com.deloitte.services.oa.exception.OaErrorType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  News服务实现类
 * @Modified :
 */
@Service
@Transactional
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {


    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Autowired
    public IOaAttachmentService oaAttachmentService;

    @Override
    public Result saveOrUpdate(NewsForm entity) {
        News news = new BeanUtils<News>().copyObj(entity, News.class);
        if (news.getNewsId() == null) {
            UserVo userVo = UserUtil.getUserVo();
            if (userVo != null) {
                String id = userVo.getId();
                news.setApplyUserId(id);
                DeputyAccountVo account = userVo.getCurrentDeputyAccount();
                if (account != null) {
                    String orgCode = account.getOrgCode();
                    news.setOrgCode(orgCode);
                } else {
                    throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
                }
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        }
        //投稿平台为报纸 则不走流程(前端处理) 数据不在门户展示
        if ("newsPaper".equals(news.getNewsContributPlatformCode())) {
            news.setNewsStatus("normal");
        } else {
            if (!"notRead".equals(news.getNewsStatus())) {
                news.setNewsStatus("draft");
            }
        }
        if (StringUtils.isNotBlank(news.getNewsThumbnailsUrl())) {
            news.setIspicNews("1");
        } else {
            news.setIspicNews("0");
        }
        boolean flag = super.saveOrUpdate(news);
        attachmentMapper.deleteByBusinessId(String.valueOf(news.getNewsId()));
        List<OaAttachmentForm> attachments = entity.getAttachments();
        for (OaAttachmentForm oaAttachmentForm : attachments) {
            oaAttachmentForm.setBusicessId(String.valueOf(news.getNewsId()));
            oaAttachmentForm.setBusicessName(String.valueOf(news.getNewsTypeCode()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(oaAttachmentForm, OaAttachment.class);
            attachmentMapper.insert(temp);
        }
        if (flag) {
            return Result.success(String.valueOf(news.getNewsId()));
        } else {
            return Result.fail("新增或修改数据失败");
        }

//        News news = new BeanUtils<News>().copyObj(entity, News.class);
//        //todo 后期改为1
//        news.setNewsStatus("1");
//        //判断是否有轮播图 后台展示要用
//        if (StringUtils.isNotBlank(news.getNewsThumbnailsUrl())) {
//            news.setIspicNews("1");
//        } else {
//            news.setIspicNews("0");
//        }
//        //保存新闻信息到新闻表（包括轮播图信息）
//        boolean flag = super.save(news);
//        entity.getAttachments().forEach(atta -> {
//            //获取新闻表主键插入附件表中
//            atta.setBusicessId(String.valueOf(news.getNewsId()));
//            atta.setBusicessName(String.valueOf(news.getNewsTypeCode()));
//            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
//            attachmentMapper.insert(temp);
//        });
//        if (flag) {
//            return Result.success(news.getNewsId());
//        } else {
//            return Result.fail("新增数据失败");
//        }
    }

    @Override
    public News getById(String id) {
        News news = super.getById(id);
        Long newsId = news.getNewsId();
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(newsId));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        List<OaAttachment> oaAttachments = attachmentMapper.selectList(queryWrapper);
        news.setAttachments(oaAttachments);
        //查询一次增加一次点击量
        news.setNewsHit(news.getNewsHit() + 1);
        newsMapper.updateById(news);
        return news;
    }

    @Override
    public Result update(long id, NewsForm newsForm) {
        News news = new BeanUtils<News>().copyObj(newsForm, News.class);
        news.setNewsId(id);
        //判断是否有轮播图 后台展示要用
        if (StringUtils.isNotBlank(news.getNewsThumbnailsUrl())) {
            news.setIspicNews("1");
        } else {
            news.setIspicNews("0");
        }
        boolean flag = super.updateById(news);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        List<OaAttachmentForm> attachments = newsForm.getAttachments();
        for (OaAttachmentForm oaAttachmentForm : attachments) {
            oaAttachmentForm.setBusicessName(String.valueOf(news.getNewsTypeCode()));
            oaAttachmentForm.setBusicessId(String.valueOf(news.getNewsId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(oaAttachmentForm, OaAttachment.class);
            attachmentMapper.insert(temp);
        }
        if (flag) {
            return Result.success(String.valueOf(news.getNewsId()));
        } else {
            return Result.fail("更新数据失败");
        }
    }

    @Override
    public IPage<News> selectPage(NewsQueryForm queryForm, int page, int size) {
        Page pages = new Page<>(page, size);
        List<News> news = newsMapper.selectNewsPage(queryForm, pages);
        return pages.setRecords(news);

//        QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
//        getQueryWrapper(queryWrapper, queryForm);
//        return newsMapper.selectPage(new Page<News>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public IPage<News> selectManagePage(NewsQueryForm queryForm, int page, int size) {
        Page pages = new Page<>(page, size);
        List<News> news = newsMapper.selectManagePage(queryForm, pages);
        return pages.setRecords(news);
    }

    @Override
    public List<News> selectList(NewsQueryForm queryForm) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
        getQueryWrapper(queryWrapper, queryForm);
        return newsMapper.selectList(queryWrapper);
    }

    @Override
    public List<News> selectHomeList(int num, String newsTypeCode) {
        return newsMapper.selectHomeList(num, newsTypeCode);
    }

    @Override
    public IPage<News> selectPageWithPermission(NewsQueryForm queryForm, String token) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
        queryForm.setOrgCode(null);
        queryForm.setApplyUserId(null);
        getQueryWrapper(queryWrapper, queryForm);
        UserVo userVo = UserUtil.getUserVo();
        if (userVo != null) {
            //获取副账号
            DeputyAccountVo account = userVo.getCurrentDeputyAccount();
            if (account != null) {
                String orgCode = account.getOrgCode();
                String userId = String.valueOf(account.getId());
                queryWrapper.apply(" ( APPLY_USER_ID = {0} OR ( ORG_CODE like {1} and ORG_CODE <> {2} ) )",
                        userId, orgCode + "%", orgCode);
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户信息失败");
        }
        return newsMapper.selectPage(new Page<News>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<News> getQueryWrapper(QueryWrapper<News> queryWrapper, NewsQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getNewsTypeCode())) {
            queryWrapper.eq(News.NEWS_TYPE_CODE, queryForm.getNewsTypeCode());
        }
        if (StringUtils.isNotBlank(queryForm.getNewsTitle())) {
            queryWrapper.like(News.NEWS_TITLE, queryForm.getNewsTitle());
        }
        if (StringUtils.isNotBlank(queryForm.getNewsContributPlatform())) {
            queryWrapper.eq(News.NEWS_CONTRIBUT_PLATFORM, queryForm.getNewsContributPlatform());
        }
        queryWrapper.orderByDesc(News.NEWS_DATETIME);
        return queryWrapper;
    }

}

