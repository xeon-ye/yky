package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetPublicAdjustQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustLineVo;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.services.fssc.budget.entity.Budget;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjust;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjustLine;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description : BudgetPublicAdjust服务类接口
 * @Modified :
 */
public interface IBudgetPublicAdjustService extends IService<BudgetPublicAdjust> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetPublicAdjust>
     */
    IPage<BudgetPublicAdjust> selectPage(BudgetPublicAdjustQueryForm queryForm);

    /**
     *
     * @param deptVo
     * @param organizationVo
     * @param annual
     * @return
     */
    BudgetPublicAdjust getInitAdjust(DeptVo deptVo, OrganizationVo organizationVo, String annual);

    /**
     * 查询初始化的科目预算信息
     * @param orgCode
     * @param deptCode
     * @param annual
     * @return
     */
    List<BudgetPublicAdjustLine> selectInitLine(String orgCode,String deptCode,String annual);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetPublicAdjust>
     */
    List<BudgetPublicAdjust> selectList(BudgetPublicAdjustQueryForm queryForm);

    /**
     * 根据ID查询
     * @param adjustId 预算调整单据的id
     * @param readLast 是否读取基本预算的最新数据
     * @return
     */
    BudgetPublicAdjustVo findInfoById(Long adjustId, boolean readLast);

    /**
     * 读取基本预算信息并刷新到入参中
     * @param adjust
     * @param adjustLineList
     */
    void readLastBasicBudget(BudgetPublicAdjust adjust, List<BudgetPublicAdjustLine> adjustLineList);
}
