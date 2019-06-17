package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.PerformanceBakQueryForm;
import com.deloitte.services.project.entity.PerformanceBak;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : PerformanceBak服务类接口
 * @Modified :
 */
public interface IPerformanceBakService extends IService<PerformanceBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerformanceBak>
     */
    IPage<PerformanceBak> selectPage(PerformanceBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerformanceBak>
     */
    List<PerformanceBak> selectList(PerformanceBakQueryForm queryForm);

    /**
     * 获取
     * @param applicationId
     * @return
     */
    List<PerformanceBak> getAllList(String applicationId);

    /**
     * 删除
     * @param applicationId
     */
    void deleteAllList(String applicationId);

    List<PerformanceBak> getAllByRepId(String replyId);
    void deleteAllByRepId(String replyId);
}
