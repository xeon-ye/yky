package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 项目批复书 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-22
 */
public interface ReplyMapper extends BaseMapper<Reply> {
    /**
     * 获取最新的批复书
     * @param projectId
     * @return
     */
    Reply getOneRep(String projectId);

    /**
     * 有一个待批复的批复书
     * @param projectId
     * @return
     */
    Reply getRepToRep(String projectId);

    /**
     * 查询当前年度已批复的项目
     * @param map
     * @return
     */
    Reply getRepByYear(@Param("data") Map map);


    Reply getNewRep(@Param("data") Map map);
}
