package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseBudgetAccountQueryForm;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-06
 * @Description : BaseBudgetAccount服务类接口
 * @Modified :
 */
public interface IBaseBudgetAccountService extends IService<BaseBudgetAccount> {

    /**
     * 根据编码查询预算科目
     * @param code
     * @return
     */
    BaseBudgetAccount getBudgetAccountByCode(String code);

    /**
     * 根据编码查询预算科目
     * @param code
     * @return
     */
    BaseBudgetAccount getBudgetAccountByCode(String code,String budgetType);

    /**
     * 根据编码查询预算科目数量
     * @param code
     * @return
     */
    Integer countByCode(String code);

    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList,String validFlag);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseBudgetAccount>
     */
    IPage<BaseBudgetAccount> selectPage(BaseBudgetAccountQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseBudgetAccount>
     */
    List<BaseBudgetAccount> selectList(BaseBudgetAccountQueryForm queryForm);

    /**
     * 查询code和name的映射
     * @param queryForm
     * @return
     */
    Map<String,String> getCodeNameMap(BaseBudgetAccountQueryForm queryForm);

}
