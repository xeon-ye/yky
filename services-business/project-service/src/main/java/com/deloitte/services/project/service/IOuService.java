package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.OuQueryForm;
import com.deloitte.services.project.entity.Ou;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Ou服务类接口
 * @Modified :
 */
public interface IOuService extends IService<Ou> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Ou>
     */
    IPage<Ou> selectPage(OuQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Ou>
     */
    List<Ou> selectList(OuQueryForm queryForm);
}
