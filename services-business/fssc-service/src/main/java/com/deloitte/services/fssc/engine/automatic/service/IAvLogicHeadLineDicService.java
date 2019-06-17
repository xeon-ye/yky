package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvLogicHeadLineDicQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvLogicHeadLineDic;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-04-26
 * @Description : AvLogicHeadLineDic服务类接口
 * @Modified :
 */
public interface IAvLogicHeadLineDicService extends IService<AvLogicHeadLineDic> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvLogicHeadLineDic>
     */
    IPage<AvLogicHeadLineDic> selectPage(AvLogicHeadLineDicQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvLogicHeadLineDic>
     */
    List<AvLogicHeadLineDic> selectList(AvLogicHeadLineDicQueryForm queryForm);
}
