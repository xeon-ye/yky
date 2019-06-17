package com.deloitte.services.fssc.performance.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.services.fssc.performance.entity.PerformanceIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 绩效指标 Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-04-08
 */
public interface PerformanceIndexMapper extends BaseMapper<PerformanceIndex> {


    /**
     * 分页查询
     * @param iPage
     * @param queryForm
     * @return
     */
   List<PerformanceIndexVo> selectVo(@Param("iPage") IPage<PerformanceIndex> iPage,
            @Param("queryForm") PerformanceIndexQueryForm queryForm);
}
