package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasTravelReimburseQueryForm;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description : TasTravelReimburse服务类接口
 * @Modified :
 */
public interface ITasTravelReimburseService extends IService<TasTravelReimburse> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasTravelReimburse>
     */
    IPage<TasTravelReimburse> selectPage(TasTravelReimburseQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasTravelReimburse>
     */
    List<TasTravelReimburse> selectList(TasTravelReimburseQueryForm queryForm);

    /**
     * 根据支出大类id查询是关联的差旅报账
     * @param idList
     * @return
     */
    boolean existsByExpenseMainTypeTrIds(List<Long> idList);

    List<BmBorrowBank>  queryList(QueryWrapper<BmBorrowBank> queryWrapper);
}
