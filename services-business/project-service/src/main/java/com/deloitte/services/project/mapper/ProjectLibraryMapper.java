package com.deloitte.services.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.project.entity.ProjectLibrary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/7 10:47
 * @Description :
 * @Modified:
 */
@Mapper
public interface ProjectLibraryMapper extends BaseMapper<ProjectLibrary> {

    /**
     * 项目库查询
     * @param page
     * @param map
     * @return
     */
    List<ProjectLibrary> selectProjectLibraryLisPage(Page page, @Param("map") Map<String, Object> map);

    /**
     * 项目库查询（不分页）
     * @param map
     * @return
     */
    List<ProjectLibrary> selectProjectLibraryList(@Param("map") Map<String, Object> map);

}
