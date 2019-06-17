package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ExpenseQueryForm;
import com.deloitte.services.project.entity.Expense;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Expense服务类接口
 * @Modified :
 */
public interface IExpenseService extends IService<Expense> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Expense>
     */
    IPage<Expense> selectPage(ExpenseQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Expense>
     */
    List<Expense> selectList(ExpenseQueryForm queryForm);

    /**
     * 根据项目批复书ID查询支出绩效明细
     * @param projectApprovalId
     * @return
     */
    List<Expense> getList(String projectApprovalId);

    /**
     * 删除
     * @param applicationId
     */
    void deleteList(String applicationId);
    void delByRep(String replyId);

    List<Expense> getAllList(String replyId);
}
