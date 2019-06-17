package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.EquipmentTransformQueryForm;
import com.deloitte.services.project.entity.EquipmentTransform;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : EquipmentTransform服务类接口
 * @Modified :
 */
public interface IEquipmentTransformService extends IService<EquipmentTransform> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<EquipmentTransform>
     */
    IPage<EquipmentTransform> selectPage(EquipmentTransformQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<EquipmentTransform>
     */
    List<EquipmentTransform> selectList(EquipmentTransformQueryForm queryForm);
}
