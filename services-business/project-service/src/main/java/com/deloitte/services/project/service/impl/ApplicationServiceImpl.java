package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.param.ApplicationQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.mapper.ApplicationMapper;
import com.deloitte.services.project.mapper.MyApplicationMapper;
import com.deloitte.services.project.service.IApplicationService;
import com.deloitte.services.project.service.IProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description :  Application服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private MyApplicationMapper myApplicationMapper;
    @Autowired
    private IProjectsService projectsService;

    @Override
    public IPage<MyApplicationVo> searchMyApplication(Long currentPage, Long pageSize) {
        if (Objects.isNull(currentPage) && Objects.isNull(pageSize)) {
            throw new ServiceException(ProjectErrorType.DATA_IS_NULL);
        }
        Page<MyApplication> page = new Page<>(1,5);
        List<MyApplication> myApplicationList =  myApplicationMapper.searchMyApplication(page);
        IPage<MyApplicationVo> iPage = new BeanUtils<MyApplicationVo>().copyPageObjs(page.setRecords(myApplicationList), MyApplicationVo.class);
        return iPage;
    }

    @Override
    public IPage<Application> selectPage(ApplicationQueryForm queryForm ) {
        QueryWrapper<Application> queryWrapper = new QueryWrapper <Application>();
        //getQueryWrapper(queryWrapper,queryForm);
        return applicationMapper.selectPage(new Page<Application>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Application> selectList(ApplicationQueryForm queryForm) {
        QueryWrapper<Application> queryWrapper = new QueryWrapper <Application>();
        //getQueryWrapper(queryWrapper,queryForm);
        return applicationMapper.selectList(queryWrapper);
    }

    @Override
    public List<Application> getAllList(String projectId) {
        QueryWrapper<Application> queryWrapper = new QueryWrapper <Application>();
        queryWrapper.eq(Application.PROJECT_ID, projectId);
        return applicationMapper.selectList(queryWrapper);
    }

    @Override
    public Application getOneApp(String projectId) {
        return applicationMapper.getOneApp(projectId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeApplication(String applicationId) {
        QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
        applicationQueryWrapper.eq(Application.APPLICATION_ID, applicationId);
        Application application = this.getOne(applicationQueryWrapper);
        String projectId = null;
        if (Objects.nonNull(application)) {
            projectId = application.getProjectId();
            QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
            projectsQueryWrapper.eq(Projects.PROJECT_ID, projectId);
            projectsService.remove(projectsQueryWrapper);
        }
        this.remove(applicationQueryWrapper);
    }
}

