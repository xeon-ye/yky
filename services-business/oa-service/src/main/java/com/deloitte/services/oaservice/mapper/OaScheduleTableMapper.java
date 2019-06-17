package com.deloitte.services.oaservice.mapper;

import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryParam;
import com.deloitte.services.oaservice.entity.OaScheduleTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fuqiao
 * @since 2019-03-26
 */
public interface OaScheduleTableMapper extends BaseMapper<OaScheduleTable> {

    List<OaScheduleTable> getScheduleByBusinessId(@Param(value="businessId") String businessId, @Param(value="param")OaScheduleTableQueryParam param);

    int removeByBusinessIdAndRowNum(@Param(value="businessId") String businessId,@Param(value="rowNum")int rowNum);

    int updateStatusAfterSubmit(@Param(value="businessId")String businessId,@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="workType")String workType,@Param(value="filterIds")String filterIds,@Param(value="oldStatus")String oldStatus,@Param(value="newStatus")String newStatus);
}
