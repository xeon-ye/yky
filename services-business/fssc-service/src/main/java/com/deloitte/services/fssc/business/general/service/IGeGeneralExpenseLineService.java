package com.deloitte.services.fssc.business.general.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseLineQueryForm;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpenseLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeGeneralExpenseLine服务类接口
 * @Modified :
 */
public interface IGeGeneralExpenseLineService extends IService<GeGeneralExpenseLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<GeGeneralExpenseLine>
     */
    IPage<GeGeneralExpenseLine> selectPage(GeGeneralExpenseLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<GeGeneralExpenseLine>
     */
    List<GeGeneralExpenseLine> selectList(GeGeneralExpenseLineQueryForm queryForm);

    /**
     * 根据支出小类id查询是关联的通用报账单行明细
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubTypeGeIds(List<Long> expenseMainTypeIdList);

}
