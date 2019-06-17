package com.deloitte.services.srpmp.project.apply.service;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnUnit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description : SrpmsProjectApplyInnUnit服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplyInnUnitService extends IService<SrpmsProjectApplyInnUnit> {

    /**
     * 保存或更新创新单元申请书
     * @param innUnitForm
     * @param deptVo
     * @return
     */
    Long save(SrpmsProjectApplyInnUnitForm innUnitForm, DeptVo deptVo);

    /**
     * 提交创新单元申请书
     * @param innUnitForm
     * @param deptVo
     * @return
     */
    void submit(SrpmsProjectApplyInnUnitForm innUnitForm, UserVo userVo, DeptVo deptVo);

    /**
     * 查询创新单元申请书
     * @param id
     * @return
     */
    SrpmsProjectApplyInnUnitVo get(Long id);

}
