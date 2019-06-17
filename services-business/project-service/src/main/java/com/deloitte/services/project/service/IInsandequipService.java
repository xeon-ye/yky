package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.InsandequipQueryForm;
import com.deloitte.services.project.entity.Insandequip;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description : Insandequip服务类接口
 * @Modified :
 */
public interface IInsandequipService extends IService<Insandequip> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Insandequip>
     */
    IPage<Insandequip> selectPage(InsandequipQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Insandequip>
     */
    List<Insandequip> selectList(InsandequipQueryForm queryForm);
}
