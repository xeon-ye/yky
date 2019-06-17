package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.MaintReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 维护项目审批表 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-20
 */
public interface MaintReplyMapper extends BaseMapper<MaintReply> {
    MaintReply getNewMainReply(@Param("data") Map map);
}
