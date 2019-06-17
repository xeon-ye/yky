package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.EvaluateQueryForm;
import com.deloitte.services.contract.entity.Evaluate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description : Evaluate服务类接口
 * @Modified :
 */
public interface IEvaluateService extends IService<Evaluate> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Evaluate>
     */
    IPage<Evaluate> selectPage(EvaluateQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Evaluate>
     */
    List<Evaluate> selectList(EvaluateQueryForm queryForm);
}
