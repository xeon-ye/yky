package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.EquipmentQueryForm;
import com.deloitte.services.project.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Equipment服务类接口
 * @Modified :
 */
public interface IEquipmentService extends IService<Equipment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Equipment>
     */
    IPage<Equipment> selectPage(EquipmentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Equipment>
     */
    List<Equipment> selectList(EquipmentQueryForm queryForm);
}
