package com.deloitte.services.fssc.business.labor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineForeignQueryForm;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineForeign;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCostLineForeign服务类接口
 * @Modified :
 */
public interface ILcLaborCostLineForeignService extends IService<LcLaborCostLineForeign> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<LcLaborCostLineForeign>
     */
    IPage<LcLaborCostLineForeign> selectPage(LcLaborCostLineForeignQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<LcLaborCostLineForeign>
     */
    List<LcLaborCostLineForeign> selectList(LcLaborCostLineForeignQueryForm queryForm);

    /**
     * 根据支出小类id查询是关联的发放明细-外国籍
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubTypeLWFIds(List<Long> expenseMainTypeIdList);

}
