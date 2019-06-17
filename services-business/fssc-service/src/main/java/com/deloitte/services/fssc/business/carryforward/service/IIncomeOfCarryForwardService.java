package com.deloitte.services.fssc.business.carryforward.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.carryforward.param.IncomeOfCarryForwardQueryForm;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-06
 * @Description : IncomeOfCarryForward服务类接口
 * @Modified :
 */
public interface IIncomeOfCarryForwardService extends IService<IncomeOfCarryForward> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<IncomeOfCarryForward>
     */
    IPage<IncomeOfCarryForward> selectPage(IncomeOfCarryForwardQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<IncomeOfCarryForward>
     */
    List<IncomeOfCarryForward> selectList(IncomeOfCarryForwardQueryForm queryForm);

    /**
     * 查看收入结转是否已经被手工录入了
     * @param id
     * @return
     */
    Integer hasCarryId (Long id);

    List<IncomeOfCarryForward> selectListByManual(IncomeOfCarryForwardQueryForm form);
}
