package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.BudgetQueryForm;
import com.deloitte.services.project.entity.Budget;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description : Budget服务类接口
 * @Modified :
 */
public interface IBudgetService extends IService<Budget> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Budget>
     */
    IPage<Budget> selectPage(BudgetQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Budget>
     */
    List<Budget> selectList(BudgetQueryForm queryForm);
    List<Budget> getBudgetYearList(String applicationId);

    List getAppYearCount(String applicationId);
    List<Budget> getAppBudgetList(String applicationId);
    List<Budget> getAppBudgetMap(Map map);

    List getRepYearCount(String replyId);
    List<Budget> getRepBudgetList(String replyId);
    List<Budget> getRepBudgetMap(Map map);

    void deleteByApp(String applicationId);
    void deleteByRep(String replyId);

    void deleteByRepIdAndYear(Map map);
}
