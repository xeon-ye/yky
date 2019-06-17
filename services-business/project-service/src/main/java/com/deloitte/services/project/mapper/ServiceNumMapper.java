package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.ServiceNum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 业务单号 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-23
 */
public interface ServiceNumMapper extends BaseMapper<ServiceNum> {
    /**
     *
     * @param map
     * @return
     */
    ServiceNum selectMaxNum(@Param("data") Map<String, Object> map);

    int getMaxNum(String serviceOnly);
}
