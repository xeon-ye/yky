package com.deloitte.services.fssc.business.basecontract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractPlanLineQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractPlanLineVo;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanLine;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description : BaseContractPlanLine服务类接口
 * @Modified :
 */
public interface IBaseContractPlanLineService extends IService<BaseContractPlanLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseContractPlanLine>
     */
    IPage<BaseContractPlanLineVo> selectPage(BaseContractPlanLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseContractPlanLine>
     */
    List<BaseContractPlanLine> selectList(BaseContractPlanLineQueryForm queryForm);
}
