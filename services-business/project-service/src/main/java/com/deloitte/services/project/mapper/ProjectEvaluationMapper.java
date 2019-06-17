package com.deloitte.services.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.project.entity.ProjectEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/6 14:36
 * @Description :
 * @Modified:
 */
@Mapper
public interface ProjectEvaluationMapper extends BaseMapper<ProjectEvaluation> {

    /**
     * 项目评价查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectEvaluation> searchProjectEvaluation(Page page, @Param("app") Map<String, Object> map);


    /**
     * 项目取消---分页查询附件
     * @param page
     * @param projectId
     * @return
     */
    List<ProjectEvaluation> selectEnclosureList(Page page, @Param("projectId") String projectId);

}
