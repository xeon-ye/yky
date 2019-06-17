package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.BudgetBakQueryForm;
import com.deloitte.services.project.entity.Budget;
import com.deloitte.services.project.entity.BudgetBak;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description : BudgetBak服务类接口
 * @Modified :
 */
public interface IBudgetBakService extends IService<BudgetBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetBak>
     */
    IPage<BudgetBak> selectPage(BudgetBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetBak>
     */
    List<BudgetBak> selectList(BudgetBakQueryForm queryForm);

    List<BudgetBak> selectByRepId(String replyId);
    void deleteByRepId(String replyId);

    List getYearListRep(String replyId);
    List<BudgetBak> getListRep(Map map);

    void deleteByRepIdAndYear(Map map);

}
