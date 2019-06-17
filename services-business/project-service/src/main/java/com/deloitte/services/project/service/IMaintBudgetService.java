package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.MaintBudgetQueryForm;
import com.deloitte.services.project.entity.MaintBudget;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : MaintBudget服务类接口
 * @Modified :
 */
public interface IMaintBudgetService extends IService<MaintBudget> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MaintBudget>
     */
    IPage<MaintBudget> selectPage(MaintBudgetQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MaintBudget>
     */
    List<MaintBudget> selectList(MaintBudgetQueryForm queryForm);

    /**
     * 获取
     * @param mainId
     * @return
     */
    List<MaintBudget> getAllList(String mainId);

    /**
     * 删除
     * @param mainId
     */
    void deleteAllList(String mainId);
}
