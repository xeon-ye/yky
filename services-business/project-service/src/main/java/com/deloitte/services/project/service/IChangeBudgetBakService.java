package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ChangeBudgetBakQueryForm;
import com.deloitte.services.project.entity.ChangeBudgetBak;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : ChangeBudgetBak服务类接口
 * @Modified :
 */
public interface IChangeBudgetBakService extends IService<ChangeBudgetBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ChangeBudgetBak>
     */
    IPage<ChangeBudgetBak> selectPage(ChangeBudgetBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ChangeBudgetBak>
     */
    List<ChangeBudgetBak> selectList(ChangeBudgetBakQueryForm queryForm);

    /**
     * 获取
     * @param mainId
     * @return
     */
    List<ChangeBudgetBak> getList(String mainId);

    /**
     * 删除
     * @param mainId
     */
    void deleteList(String mainId);
}
