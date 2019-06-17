package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ReviewQueryForm;
import com.deloitte.platform.api.project.vo.ProjectReviewVo;
import com.deloitte.services.project.entity.Review;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Review服务类接口
 * @Modified :
 */
public interface IReviewService extends IService<Review> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Review>
     */
    IPage<Review> selectPage(ReviewQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Review>
     */
    List<Review> selectList(ReviewQueryForm queryForm);

    /**
     * h获取
     * @param applicationId
     * @return
     */
    List<Review> getList(String applicationId);

    /**
     * 更新
     * @param projectReviewVo
     * @return
     */
    ProjectReviewVo toUpdate(ProjectReviewVo projectReviewVo);
}
