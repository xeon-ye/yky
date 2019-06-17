package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasCostInformationLineQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasCostInformationLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description : TasCostInformationLine服务类接口
 * @Modified :
 */
public interface ITasCostInformationLineService extends IService<TasCostInformationLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasCostInformationLine>
     */
    IPage<TasCostInformationLine> selectPage(TasCostInformationLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasCostInformationLine>
     */
    List<TasCostInformationLine> selectList(TasCostInformationLineQueryForm queryForm);

    /**
     * 根据支出小类id查询是关联的差旅申请单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubTypeClIds(List<Long> expenseMainTypeIdList);


}
