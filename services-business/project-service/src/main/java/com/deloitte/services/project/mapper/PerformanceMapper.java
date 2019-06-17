package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.Performance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 项目绩效 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-24
 */
public interface PerformanceMapper extends BaseMapper<Performance> {


    /**
     * 根据申报书ID获取绩效类型（中期，年度） 去重
     * @param applicationId
     * @return
     */
    List<Performance> getIndexTypeListWithDistinct(String applicationId);

    List<Performance> getIndexTypeListWithDistinctByRep(String replyId);
}
