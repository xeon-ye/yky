package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ReviewNoteQueryForm;
import com.deloitte.services.project.entity.ReviewNote;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description : ReviewNote服务类接口
 * @Modified :
 */
public interface IReviewNoteService extends IService<ReviewNote> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReviewNote>
     */
    IPage<ReviewNote> selectPage(ReviewNoteQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReviewNote>
     */
    List<ReviewNote> selectList(ReviewNoteQueryForm queryForm);

    /**
     * 获取
     * @param applicationId
     * @return
     */
    List<ReviewNote> getList(String applicationId);
}
