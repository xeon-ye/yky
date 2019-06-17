package com.deloitte.services.applycenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.applycenter.entity.SendProcessTask;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-09
 * @Description : SendProcessTask服务类接口
 * @Modified :
 */
public interface ISendProcessTaskService extends IService<SendProcessTask> {

    /**
     * 内部系统待阅已阅数据同步
     * @param sendProcessTaskForm
     * @return
     */
    Result save(SendProcessTaskForm sendProcessTaskForm);

    /**
     * 分页查询
     *
     * @param queryForm
     * @return IPage<SendProcessTask>
     */
    IPage<SendProcessTask> selectPage(SendProcessTaskQueryForm queryForm);

    /**
     * 条件查询
     *
     * @param queryForm
     * @return List<SendProcessTask>
     */
    List<SendProcessTask> selectList(SendProcessTaskQueryForm queryForm);

    /**
     * 泛微数据同步
     * @param sendProcessTaskQueryForm
     * @return
     */
    Result syncData(SendProcessTaskQueryForm sendProcessTaskQueryForm);
    /**
     * 分页查询
     *
     * @param queryForm
     * @return IPage<SendProcessTask>
     */
    IPage<SendProcessTask> selectPage2(SendProcessTaskQueryForm queryForm);
}
