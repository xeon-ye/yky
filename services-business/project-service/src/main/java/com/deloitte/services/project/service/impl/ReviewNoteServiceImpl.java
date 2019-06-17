package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ReviewNoteQueryForm;
import com.deloitte.services.project.entity.ReviewNote;
import com.deloitte.services.project.mapper.ReviewNoteMapper;
import com.deloitte.services.project.service.IReviewNoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description :  ReviewNote服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReviewNoteServiceImpl extends ServiceImpl<ReviewNoteMapper, ReviewNote> implements IReviewNoteService {


    @Autowired
    private ReviewNoteMapper reviewNoteMapper;

    @Override
    public IPage<ReviewNote> selectPage(ReviewNoteQueryForm queryForm ) {
        QueryWrapper<ReviewNote> queryWrapper = new QueryWrapper <ReviewNote>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reviewNoteMapper.selectPage(new Page<ReviewNote>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
        }

    @Override
    public List<ReviewNote> selectList(ReviewNoteQueryForm queryForm) {
        QueryWrapper<ReviewNote> queryWrapper = new QueryWrapper <ReviewNote>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reviewNoteMapper.selectList(queryWrapper);
    }

    @Override
    public List<ReviewNote> getList(String applicationId) {
        QueryWrapper<ReviewNote> queryWrapper = new QueryWrapper <ReviewNote>();
        queryWrapper.eq(ReviewNote.APPLICATION_ID, applicationId);
        return reviewNoteMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ReviewNote> getQueryWrapper(QueryWrapper<ReviewNote> queryWrapper,ReviewNoteQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ReviewNote.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewMan())){
            queryWrapper.eq(ReviewNote.REVIEW_MAN,queryForm.getReviewMan());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewDate())){
            queryWrapper.eq(ReviewNote.REVIEW_DATE,queryForm.getReviewDate());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewOpi())){
            queryWrapper.eq(ReviewNote.REVIEW_OPI,queryForm.getReviewOpi());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ReviewNote.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ReviewNote.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ReviewNote.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ReviewNote.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ReviewNote.EXT3,queryForm.getExt3());
        }
        return queryWrapper;
    }
     */
}

