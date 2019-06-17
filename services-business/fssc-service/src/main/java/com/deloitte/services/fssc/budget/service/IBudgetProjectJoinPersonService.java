package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.bpm.param.BudgetProjectJoinPersonQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetProjectJoinPerson;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-18
 * @Description : BudgetProjectJoinPerson服务类接口
 * @Modified :
 */
public interface IBudgetProjectJoinPersonService extends IService<BudgetProjectJoinPerson> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetProjectJoinPerson>
     */
    IPage<BudgetProjectJoinPerson> selectPage(BudgetProjectJoinPersonQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetProjectJoinPerson>
     */
    List<BudgetProjectJoinPerson> selectList(BudgetProjectJoinPersonQueryForm queryForm);
}
