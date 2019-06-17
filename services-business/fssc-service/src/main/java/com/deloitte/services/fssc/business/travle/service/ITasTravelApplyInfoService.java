package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasTravelApplyInfoQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description : TasTravleApplyInfo服务类接口
 * @Modified :
 */
public interface ITasTravelApplyInfoService extends IService<TasTravelApplyInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasTravelApplyInfo>
     */
    IPage<TasTravelApplyInfo> selectPage(TasTravelApplyInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasTravelApplyInfo>
     */
    List<TasTravelApplyInfo> selectList(TasTravelApplyInfoQueryForm queryForm);

    /**
     *  条件查询是否能被差旅报账关联
     * @param queryForm
     * @return List<TasTravelApplyInfo>
     */
    List<TasTravelApplyInfo> listReimBurse(TasTravelApplyInfoQueryForm queryForm);

    /**
     * 根据支出大类id查询是关联的差旅申请
     * @param idList
     * @return
     */
    boolean existsByExpenseMainTypeTaIds(List<Long> idList);
}
