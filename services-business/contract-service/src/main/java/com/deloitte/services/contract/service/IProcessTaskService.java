package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.services.contract.entity.ProcessTask;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-02
 * @Description : ProcessTask服务类接口
 * @Modified :
 */
public interface IProcessTaskService extends IService<ProcessTask> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProcessTask>
     */
    IPage<ProcessTask> selectPage(ProcessTaskQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProcessTask>
     */
    List<ProcessTask> selectList(ProcessTaskQueryForm queryForm);
}
