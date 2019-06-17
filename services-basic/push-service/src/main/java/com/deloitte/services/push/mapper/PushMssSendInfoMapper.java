package com.deloitte.services.push.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.push.entity.PushMssSendInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-03
 */
public interface PushMssSendInfoMapper extends BaseMapper<PushMssSendInfo> {

    Long getMssMonthTotal(@Param(value = "date") String date);

}
