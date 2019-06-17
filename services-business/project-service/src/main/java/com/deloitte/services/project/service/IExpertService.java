package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ExpertQueryForm;
import com.deloitte.services.project.entity.Expert;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-16
 * @Description : Expert服务类接口
 * @Modified :
 */
public interface IExpertService extends IService<Expert> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Expert>
     */
    IPage<Expert> selectPage(ExpertQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Expert>
     */
    List<Expert> selectList(ExpertQueryForm queryForm);

    /**
     * 好难顶
     * @param applicationId
     * @return
     */
    List<Expert> getList(String applicationId);

    /**
     * ss
     * @param applicationId
     */
    void deleteList(String applicationId);
}
