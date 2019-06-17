package com.deloitte.services.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.portal.param.NoticeQueryForm;
import com.deloitte.services.portal.entity.Article;
import com.deloitte.services.portal.entity.Notice;
import com.deloitte.services.portal.mapper.NoticeMapper;
import com.deloitte.services.portal.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-03
 * @Description :  Notice服务实现类
 * @Modified :
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {


    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public IPage<Notice> selectPage(NoticeQueryForm queryForm ) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper <Notice>();
        //getQueryWrapper(queryWrapper,queryForm);
        return noticeMapper.selectPage(new Page<Notice>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Notice> selectList(NoticeQueryForm queryForm) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper <Notice>();
        //getQueryWrapper(queryWrapper,queryForm);
        return noticeMapper.selectList(queryWrapper);
    }

    @Override
    public List<Notice> getHomeList(int num) {
        return noticeMapper.getHomeList(num);
    }



    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Notice> getQueryWrapper(QueryWrapper<Notice> queryWrapper,NoticeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getNoticeId())){
            queryWrapper.eq(Notice.NOTICE_ID,queryForm.getNoticeId());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeTitle())){
            queryWrapper.eq(Notice.NOTICE_TITLE,queryForm.getNoticeTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeHit())){
            queryWrapper.eq(Notice.NOTICE_HIT,queryForm.getNoticeHit());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeDatetime())){
            queryWrapper.eq(Notice.NOTICE_DATETIME,queryForm.getNoticeDatetime());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeUpdatetime())){
            queryWrapper.eq(Notice.NOTICE_UPDATETIME,queryForm.getNoticeUpdatetime());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeAuthor())){
            queryWrapper.eq(Notice.NOTICE_AUTHOR,queryForm.getNoticeAuthor());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeContent())){
            queryWrapper.eq(Notice.NOTICE_CONTENT,queryForm.getNoticeContent());
        }
        if(StringUtils.isNotBlank(queryForm.getDelFlg())){
            queryWrapper.eq(Notice.DEL_FLG,queryForm.getDelFlg());
        }
        return queryWrapper;
    }
     */
}

