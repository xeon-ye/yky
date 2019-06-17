package com.deloitte.services.fssc.business.general.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeGeneralExpense服务类接口
 * @Modified :
 */
public interface IGeGeneralExpenseService extends IService<GeGeneralExpense> {


    /**
     *  分页查询
     * @param queryForm
     * @return IPage<GeGeneralExpense>
     */
    IPage<GeGeneralExpense> selectPage(GeGeneralExpenseQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<GeGeneralExpense>
     */
    List<GeGeneralExpense> selectList(GeGeneralExpenseQueryForm queryForm);

    /**
     * 根据支出大类id查询是关联的通用报账单
     * @param idList
     * @return
     */
    boolean existsByExpenseMainTypeGeIds(List<Long> idList);

    List<BorrowPrepayListVo> findBorrowPrepayList(GeExpenseBorrowPrepayQueryForm form);
}
