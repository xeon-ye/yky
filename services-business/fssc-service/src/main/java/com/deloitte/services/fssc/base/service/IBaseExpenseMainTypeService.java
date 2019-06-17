package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseMainTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : BaseExpenseMainType服务类接口
 * @Modified :
 */
public interface IBaseExpenseMainTypeService extends IService<BaseExpenseMainType> {

    void deleteByMainTypeIds(List<Long> ids);

    /**
     * 按照编码查询
     * @param code
     * @param unitCode
     * @return
     */
    BaseExpenseMainType getMainTypeByCode(String code,String unitCode);

    /**
     * 查询子支出大类
     * @param parentCode
     * @param unitCode
     * @return
     */
    List<BaseExpenseMainType> getChildMainType(String parentCode,String unitCode);

    /**
     * 更新支出大类
     * @param mainType
     * @return
     */
    boolean updateMainType(BaseExpenseMainType mainType);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseIncomeMainType>
     */
    IPage<BaseExpenseMainType> selectPage(BaseExpenseMainTypeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseIncomeMainType>
     */
    List<BaseExpenseMainType> selectList(BaseExpenseMainTypeQueryForm queryForm);

    /**
     * 生效/失效
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);


    /**
     * 更新关联的预算科目
     * @param ids
     * @param budgetAccountId 预算科目id
     * @param unitCode 单位编码
     * @return
     */
    boolean updateBudgetAccountId(List<Long> ids,Long budgetAccountId,String unitCode);

    /**
     * 解除与预算科目的关联关系
     * @param ids
     * @return
     */
    boolean unRelateBudgetAccount(List<Long> ids);

    /**
     * 查询预算科目关联的支出大类数量
     * @param budgetAccountIds
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> budgetAccountIds);

    /**
     * 查询预算科目关联的支出大类数量
     * @param budgetAccountIds
     * @param unitCode
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> budgetAccountIds,String unitCode);

    /**
     * 是否关联预算科目
     * @param ids
     * @return
     */
    boolean isRelateBudgetAccount(List<Long> ids);

    List<BaseExpenseMainType> selectListData(List<Long> ids);

}
