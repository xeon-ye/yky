package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.project.entity.ProjectEvaluation;

import java.util.List;
import java.util.Map;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/6 14:30
 * @Description : 项目评价接口类
 * @Modified:
 */
public interface IProjectEvaluationService {

    /**
     * 项目评价查询列表
     * @param page
     * @param map
     * @return
     */
    List<ProjectEvaluation> searchProjectEvaluation(Page<ProjectEvaluation> page, Map<String, Object> map);

    /**
     * 项目取消---分页查询附件数据
     * @param page
     * @param projectId
     * @return
     */
    List<ProjectEvaluation> selectEnclosureList(Page<ProjectEvaluation> page, String projectId);

}
