package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.PoQueryForm;
import com.deloitte.services.project.entity.Po;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Po服务类接口
 * @Modified :
 */
public interface IPoService extends IService<Po> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Po>
     */
    IPage<Po> selectPage(PoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Po>
     */
    List<Po> selectList(PoQueryForm queryForm);
}
