package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryParam;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsUpdateSyncFsscVo;
import com.deloitte.services.fssc.budget.entity.BudgetProject;

import java.util.List;
import java.util.Map;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetProject服务类接口
 * @Modified :
 */
public interface IBudgetProjectService extends IService<BudgetProject> {


    /**
     * 查询是否被项目关联
     * @param ids
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> ids);


    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetProject>
     */
    IPage<BudgetProject> selectPage(BudgetProjectQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetProject>
     */
    List<BudgetProject> selectList(BudgetProjectQueryForm queryForm);

    /**
     * 条件查询VO
     * @param queryForm
     * @return
     */
    List<BudgetProjectVo> selectVoList(BudgetProjectQueryForm queryForm);


    /**
     * 获取项目编码和名称映射
     * @param unitCode
     * @return
     */
    Map<String, BudgetProject>  getProjectCodeBeanMap(String unitCode);

    /**
     * 获取任务编码和名称映射
     * @param unitCode 单位编码
     * @return
     */
    Map<String, BudgetProject>  getTaskCodeBeanMap(String unitCode);

    /**
     * 查询项目
     * @param unitCode
     * @param projectCode
     * @param taskCode
     * @return
     */
    BudgetProject getBudgetProject(String unitCode,String projectCode, String taskCode);

    /**
     * 根据科目项目id获取预算项目
     * @param projectId
     * @return
     */
    BudgetProject getProjectById(Long projectId);

    Map<Long,BudgetProject> selectByProjectByIds(List<Long> ids);

    void saveBudgetProject(SrpmsUpdateSyncFsscVo vo);

    IPage<BudgetProject> pageConditions(BudgetProjectQueryParam projectQueryParam);
}
