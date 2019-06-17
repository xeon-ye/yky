package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ExecuteWaringQueryForm;
import com.deloitte.services.contract.entity.ExecuteWaring;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description : ExecuteWaring服务类接口
 * @Modified :
 */
public interface IExecuteWaringService extends IService<ExecuteWaring> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ExecuteWaring>
     */
    IPage<ExecuteWaring> selectPage(ExecuteWaringQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ExecuteWaring>
     */
    List<ExecuteWaring> selectList(ExecuteWaringQueryForm queryForm);
}
