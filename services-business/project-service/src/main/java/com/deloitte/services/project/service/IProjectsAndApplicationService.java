package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.project.entity.ProjectsAndApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description : Projects服务类接口
 * @Modified :
 */
public interface IProjectsAndApplicationService extends IService<ProjectsAndApplication> {
    /**
     * 综合查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectAllListPage(Page<ProjectsAndApplication> page, Map<String, Object> map);

    /**
     * 项目申报查询
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectAllApps(Page<ProjectsAndApplication> page, Map<String, Object> map);

    /**
     * 分页查询--项目评审查询
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectReviewListPage(Page<ProjectsAndApplication> page, Map<String, Object> map);


    /**
     * 立项批复查询前导
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectStartReplyPage(Page<ProjectsAndApplication> page, Map<String, Object> map);

    /**
     * 批复关联查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectAllReplyListPage(Page<ProjectsAndApplication> page, Map<String, Object> map);

    /**
     * 项目执行前导查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> searchExecutionPages(Page<ProjectsAndApplication> page, Map<String, Object> map);

    /**
     *项目变更审批列表
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> searchChangePages(Page<ProjectsAndApplication> page, Map<String, Object> map);


    /**
     * 项目维护列表
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> searchMaintenancePages(Page<ProjectsAndApplication> page, Map<String, Object> map);
}
