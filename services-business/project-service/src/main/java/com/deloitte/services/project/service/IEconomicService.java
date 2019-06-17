package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.EconomicQueryForm;
import com.deloitte.services.project.entity.Economic;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description : Economic服务类接口
 * @Modified :
 */
public interface IEconomicService extends IService<Economic> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Economic>
     */
    IPage<Economic> selectPage(EconomicQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Economic>
     */
    List<Economic> selectList(EconomicQueryForm queryForm);

    /**
     * 获取
     * @param projectId
     * @param replyId
     * @return
     */
    List<Economic> getList(String replyId);

    /**
     * 全部
     * @param projectId
     * @return
     */
    List<Economic> getAllList(String projectId);

    /**
     * 删除
     * @param projectId
     * @param replyId
     */
    void deleteEcoList(String applicationId);
}
