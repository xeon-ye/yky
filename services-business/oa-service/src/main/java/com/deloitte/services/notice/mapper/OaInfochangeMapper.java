package com.deloitte.services.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.notice.entity.OaInfochange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 校历 Mapper 接口
 * </p>
 *
 * @author jianghaixun
 * @since 2019-04-19
 */
public interface OaInfochangeMapper extends BaseMapper<OaInfochange> {

    List<OaInfochange> getHomeList(@Param(value = "num") int num);

}
