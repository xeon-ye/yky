package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ExePerformanceQueryForm;
import com.deloitte.services.project.entity.ExePerformance;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : ExePerformance服务类接口
 * @Modified :
 */
public interface IExePerformanceService extends IService<ExePerformance> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ExePerformance>
     */
    IPage<ExePerformance> selectPage(ExePerformanceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ExePerformance>
     */
    List<ExePerformance> selectList(ExePerformanceQueryForm queryForm);

    /**
     * 查询所有
     * @param executionId
     * @return
     */
    List<ExePerformance> getAllList(String replyId);

    void deleteAllByRep(String replyId);

}
