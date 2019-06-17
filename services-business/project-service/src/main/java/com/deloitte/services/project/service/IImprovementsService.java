package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ImprovementsQueryForm;
import com.deloitte.services.project.entity.Improvements;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Improvements服务类接口
 * @Modified :
 */
public interface IImprovementsService extends IService<Improvements> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Improvements>
     */
    IPage<Improvements> selectPage(ImprovementsQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Improvements>
     */
    List<Improvements> selectList(ImprovementsQueryForm queryForm);
}
