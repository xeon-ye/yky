package com.deloitte.services.srpmp.project.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.api.srpmp.project.task.param.SrpmsProjectTaskInnUnitQueryForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitVo;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInnUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description : SrpmsProjectTaskInnUnit服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskInnUnitService extends IService<SrpmsProjectTaskInnUnit> {

    void submitTaskInnUnit(SrpmsProjectTaskInnUnitForm innUnitForm, UserVo userVo, DeptVo deptVo);

    SrpmsProjectTaskInnUnitVo get(Long id);

    Long saveTask(SrpmsProjectTaskInnUnitForm innUnitForm);
}
