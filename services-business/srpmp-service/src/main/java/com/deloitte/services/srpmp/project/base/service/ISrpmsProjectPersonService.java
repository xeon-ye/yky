package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectPersonQueryForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPerson;

import java.util.List;

/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description : SrpmsProjectPerson服务类接口
 * @Modified :
 */
public interface ISrpmsProjectPersonService extends IService<SrpmsProjectPerson> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectPerson>
     */
    IPage<SrpmsProjectPerson> selectPage(SrpmsProjectPersonQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectPerson>
     */
    List<SrpmsProjectPerson> selectList(SrpmsProjectPersonQueryForm queryForm);
}
