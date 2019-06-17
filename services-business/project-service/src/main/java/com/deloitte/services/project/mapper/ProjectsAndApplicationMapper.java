package com.deloitte.services.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.project.entity.ProjectsAndApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-26
 */
@Mapper
public interface ProjectsAndApplicationMapper extends BaseMapper<ProjectsAndApplication> {
    /**
     * 综合查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectAllListPage(Page page, @Param("app")  Map<String, Object> map);

    /**
     * 项目申报查询前导
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectAllApps(Page page, @Param("app")  Map<String, Object> map);

    /**
     *评审查询
     * @param
     * @param
     * @return
     */
    List<ProjectsAndApplication> selectReviewListPage(Page page, @Param("app")  Map<String, Object> map);

    /**
     * 立项批复查询前导
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectStartReplyPage(Page page, @Param("app") Map<String, Object> map);

    /**
     * 批复书关联查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> selectAllReplyListPage(Page page, @Param("app") Map<String, Object> map);



    /**
     * 项目执行前导查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> searchExecutionPages(Page page, @Param("app")  Map<String, Object> map);

    /**
     * 项目变更审批列表
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> searchChangePages(Page page, @Param("app")  Map<String, Object> map);

    /**
     * 项目维护查询列表
     * @param page
     * @param map
     * @return
     */
    List<ProjectsAndApplication> searchMaintenancePages(Page page, @Param("app")  Map<String, Object> map);

}
