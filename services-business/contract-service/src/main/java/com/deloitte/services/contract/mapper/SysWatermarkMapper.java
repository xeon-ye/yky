package com.deloitte.services.contract.mapper;

import com.deloitte.services.contract.entity.SysWatermark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 水印配置 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
public interface SysWatermarkMapper extends BaseMapper<SysWatermark> {

    List<SysWatermark> getWatermark(@Param("departmentCode") String departmentCode);
}
