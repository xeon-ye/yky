package com.deloitte.services.fssc.business.labor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.labor.param.LcLaborCostQueryForm;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCost服务类接口
 * @Modified :
 */
public interface ILcLaborCostService extends IService<LcLaborCost> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<LcLaborCost>
     */
    IPage<LcLaborCost> selectPage(LcLaborCostQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<LcLaborCost>
     */
    List<LcLaborCost> selectList(LcLaborCostQueryForm queryForm);

    /**
     * 根据支出大类id查询是关联的劳务费
     * @param idList
     * @return
     */
    boolean existsByExpenseMainTypeLcIds(List<Long> idList);
}
