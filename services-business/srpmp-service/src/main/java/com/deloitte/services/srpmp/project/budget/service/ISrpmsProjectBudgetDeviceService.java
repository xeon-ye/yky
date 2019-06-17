package com.deloitte.services.srpmp.project.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetDeviceQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDeviceVo;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetDevice;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectBudgetDevice服务类接口
 * @Modified :
 */
public interface ISrpmsProjectBudgetDeviceService extends IService<SrpmsProjectBudgetDevice> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectBudgetDevice>
     */
    IPage<SrpmsProjectBudgetDevice> selectPage(SrpmsProjectBudgetDeviceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectBudgetDevice>
     */
    List<SrpmsProjectBudgetDevice> selectList(SrpmsProjectBudgetDeviceQueryForm queryForm);

    void cleanAndSaveBudgetDevice(List<SrpmsProjectBudgetDeviceVo> deviceVoList, BudgetCategoryEnums joinWayEnum, Long projectId);

    void saveBudgetDevice(List<SrpmsProjectBudgetDeviceVo> deviceVoList, BudgetCategoryEnums joinWayEnum, Long projectId);

    List<SrpmsProjectBudgetDeviceVo> queryBudgetDeviceList(BudgetCategoryEnums joinWay, Long projectId);

}
