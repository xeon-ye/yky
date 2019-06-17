package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ExecutionQueryForm;
import com.deloitte.services.project.entity.Execution;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description : Execution服务类接口
 * @Modified :
 */
public interface IExecutionService extends IService<Execution> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Execution>
     */
    IPage<Execution> selectPage(ExecutionQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Execution>
     */
    List<Execution> selectList(ExecutionQueryForm queryForm);

    /**
     * 查询
     * @param projectId
     * @return
     */
    List<Execution> getExeList(String projectId);
}
