package com.deloitte.services.oaservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.oaservice.entity.OaMssInfo;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-03
 */
public interface OaMssSendInfoMapper extends BaseMapper<OaMssSendInfo> {

    Long getMssMonthTotal(@Param(value="date") String date);

}
