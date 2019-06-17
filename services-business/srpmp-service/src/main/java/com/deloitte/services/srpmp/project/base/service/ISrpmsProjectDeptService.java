package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectDeptQueryForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectDept;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectDept服务类接口
 * @Modified :
 */
public interface ISrpmsProjectDeptService extends IService<SrpmsProjectDept> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectDept>
     */
    IPage<SrpmsProjectDept> selectPage(SrpmsProjectDeptQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectDept>
     */
    List<SrpmsProjectDept> selectList(SrpmsProjectDeptQueryForm queryForm);
}
