package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.DictQueryForm;
import com.deloitte.services.isump.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : Dict服务类接口
 * @Modified :
 */
public interface IDictService extends IService<Dict> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Dict>
     */
    IPage<Dict> selectPage(DictQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Dict>
     */
    List<Dict> selectList(DictQueryForm queryForm);
}
