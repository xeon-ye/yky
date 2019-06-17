package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasTravelLineQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description : TasTravelLine服务类接口
 * @Modified :
 */
public interface ITasTravelLineService extends IService<TasTravelLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasTravelLine>
     */
    IPage<TasTravelLine> selectPage(TasTravelLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasTravelLine>
     */
    List<TasTravelLine> selectList(TasTravelLineQueryForm queryForm);

    /**
     * 根据支出小类id查询是关联的差旅报账单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubTypeTsIds(List<Long> expenseMainTypeIdList);
}
