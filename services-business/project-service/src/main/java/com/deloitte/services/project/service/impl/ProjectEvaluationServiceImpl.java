package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.project.entity.ProjectEvaluation;
import com.deloitte.services.project.mapper.ProjectEvaluationMapper;
import com.deloitte.services.project.service.IProjectEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/6 14:31
 * @Description : 项目评价业务服务类
 * @Modified:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectEvaluationServiceImpl extends ServiceImpl<ProjectEvaluationMapper, ProjectEvaluation> implements IProjectEvaluationService {

    @Autowired
    private ProjectEvaluationMapper projectEvaluationMapper;

    @Override
    public List<ProjectEvaluation> searchProjectEvaluation(Page<ProjectEvaluation> page, Map<String, Object> map) {
        return projectEvaluationMapper.searchProjectEvaluation(page, map);
    }

    @Override
    public List<ProjectEvaluation> selectEnclosureList(Page<ProjectEvaluation> page, String projectId) {
        return projectEvaluationMapper.selectEnclosureList(page, projectId);
    }
}
