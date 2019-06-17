package com.deloitte.services.fssc.business.labor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineChinaQueryForm;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineChina;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCostLineChina服务类接口
 * @Modified :
 */
public interface ILcLaborCostLineChinaService extends IService<LcLaborCostLineChina> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<LcLaborCostLineChina>
     */
    IPage<LcLaborCostLineChina> selectPage(LcLaborCostLineChinaQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<LcLaborCostLineChina>
     */
    List<LcLaborCostLineChina> selectList(LcLaborCostLineChinaQueryForm queryForm);

    /**
     * 根据支出小类id查询是关联的发放明细-中国籍
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubTypeLWCIds(List<Long> expenseMainTypeIdList);

}
