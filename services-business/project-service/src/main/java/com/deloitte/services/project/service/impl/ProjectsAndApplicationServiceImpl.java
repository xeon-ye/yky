package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.project.entity.ProjectsAndApplication;
import com.deloitte.services.project.mapper.ProjectsAndApplicationMapper;
import com.deloitte.services.project.service.IProjectsAndApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectsAndApplicationServiceImpl extends ServiceImpl<ProjectsAndApplicationMapper, ProjectsAndApplication> implements IProjectsAndApplicationService {
    @Autowired
    ProjectsAndApplicationMapper projectsAndApplicationMapper;

    @Override
    public List<ProjectsAndApplication> selectAllListPage(Page<ProjectsAndApplication> page,Map<String, Object> map){
        return this.projectsAndApplicationMapper.selectAllListPage(page,map);
    }

    @Override
    public List<ProjectsAndApplication> selectAllApps(Page<ProjectsAndApplication> page, Map<String, Object> map) {
        return this.projectsAndApplicationMapper.selectAllApps(page, map);
    }

    @Override
    public List<ProjectsAndApplication> selectReviewListPage(Page<ProjectsAndApplication> page, Map<String, Object> map){
        return this.projectsAndApplicationMapper.selectReviewListPage(page, map);
    }

    @Override
    public List<ProjectsAndApplication> selectAllReplyListPage(Page<ProjectsAndApplication> page, Map<String, Object> map) {
        return this.projectsAndApplicationMapper.selectAllReplyListPage(page, map);
    }

    @Override
    public List<ProjectsAndApplication> searchExecutionPages(Page<ProjectsAndApplication> page, Map<String, Object> map) {
        return this.projectsAndApplicationMapper.searchExecutionPages(page, map);
    }

    @Override
    public List<ProjectsAndApplication> searchChangePages(Page<ProjectsAndApplication> page, Map<String, Object> map) {
        return this.projectsAndApplicationMapper.searchChangePages(page,map);
    }

    @Override
    public List<ProjectsAndApplication> searchMaintenancePages(Page<ProjectsAndApplication> page, Map<String, Object> map) {
        return this.projectsAndApplicationMapper.searchMaintenancePages(page,map);
    }

    @Override
    public List<ProjectsAndApplication> selectStartReplyPage(Page<ProjectsAndApplication> page, Map<String, Object> map) {
        return this.projectsAndApplicationMapper.selectStartReplyPage(page,map);
    }
}
