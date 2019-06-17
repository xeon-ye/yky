package com.deloitte.services.fssc.system.dic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.dic.param.DicValueQueryForm;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : DicValue服务类接口
 * @Modified :
 */
public interface IDicValueService extends IService<DicValue> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DicValue>
     */
    IPage<DicValue> selectPage(DicValueQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DicValue>
     */
    List<DicValue> selectList(DicValueQueryForm queryForm);
}
