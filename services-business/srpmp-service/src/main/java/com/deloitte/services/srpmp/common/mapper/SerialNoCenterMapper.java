package com.deloitte.services.srpmp.common.mapper;

import com.deloitte.services.srpmp.common.entity.SerialNoCenter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 序列号中心 Mapper 接口
 * </p>
 *
 * @author lixin
 * @since 2019-03-04
 */
public interface SerialNoCenterMapper extends BaseMapper<SerialNoCenter> {

    SerialNoCenter selectByTypeAndHeaderWithLock(SerialNoCenter vo);
}
