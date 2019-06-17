package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.vo.ProjectLibraryForm;
import com.deloitte.platform.api.project.vo.ProjectLibraryVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.common.util.CommonUtil;
import com.deloitte.services.project.entity.ProjectLibrary;
import com.deloitte.services.project.mapper.ProjectLibraryMapper;
import com.deloitte.services.project.service.IProjectLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/7 14:07
 * @Description :
 * @Modified:
 */
@Slf4j
@Transactional
@Service
public class ProjectLibraryServiceImpl extends ServiceImpl<ProjectLibraryMapper, ProjectLibrary> implements IProjectLibraryService {

    @Autowired
    private ProjectLibraryMapper projectLibraryMapper;

    @Override
    public IPage<ProjectLibraryVo> selectProjectLibraryLisPage(ProjectLibraryForm libraryQueryForm) {
        AssertUtils.asserts(Objects.isNull(libraryQueryForm), ProjectErrorType.No_FROM);
        Map<String, Object> map = CommonUtil.objectToMap(libraryQueryForm);
        Page<ProjectLibrary> page = new Page<>(1,5);
        List<ProjectLibrary> libraries = projectLibraryMapper.selectProjectLibraryLisPage(page, map);
        IPage<ProjectLibraryVo> libraryVoIPage = new BeanUtils<ProjectLibraryVo>().copyPageObjs(page.setRecords(libraries), ProjectLibraryVo.class);
        return libraryVoIPage;
    }

    @Override
    public List<ProjectLibrary> selectProjectLibraryList(ProjectLibraryForm libraryQueryForm) {
        AssertUtils.asserts(Objects.isNull(libraryQueryForm), ProjectErrorType.No_FROM);
        Map<String, Object> map = CommonUtil.objectToMap(libraryQueryForm);
        List<ProjectLibrary> libraries = projectLibraryMapper.selectProjectLibraryList(map);
        return libraries;
    }
}
