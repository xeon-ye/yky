package com.deloitte.services.project.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.PerformanceQueryForm;
import com.deloitte.services.project.entity.Performance;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Performance服务类接口
 * @Modified :
 */
public interface IPerformanceService extends IService<Performance> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Performance>
     */
    IPage<Performance> selectPage(PerformanceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Performance>
     */
    List<Performance> selectList(PerformanceQueryForm queryForm);

    /**
     * huoqu
     * @param applicationId
     * @return
     */
    List<Performance> getPerformanceList(String applicationId);

    /**
     * 删除
     * @param applicationId
     */
    void deleteList(String applicationId);
    void deleteRepList(String replyId);

    List<Performance> getRepPerformanceList(String replyId);

    /**
     * 根据申报书ID获取绩效类型（中期，年度） 去重
     * @param applicationId
     * @return
     */
    List<Performance> getIndexTypeListWithDistinct(String applicationId);

    /**
     * 获取财务绩效指标库
     * @return
     */
    JSONArray getPerformanceFromFsscLibrary();

    List<Performance> getIndexTypeListWithDistinctByRep(String replyId);

}
