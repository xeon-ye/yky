package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.vo.ProjectLibraryForm;
import com.deloitte.platform.api.project.vo.ProjectLibraryVo;
import com.deloitte.services.project.entity.ProjectLibrary;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/7 14:03
 * @Description :
 * @Modified:
 */
public interface IProjectLibraryService extends IService<ProjectLibrary> {

    /**
     * 项目库查询（分页）
     * @param libraryQueryForm
     * @return
     */
    IPage<ProjectLibraryVo> selectProjectLibraryLisPage(ProjectLibraryForm libraryQueryForm);

    /**
     * 项目库查询（不分页）
     * @param libraryQueryForm
     * @return
     */
    List<ProjectLibrary> selectProjectLibraryList(ProjectLibraryForm libraryQueryForm);

}
