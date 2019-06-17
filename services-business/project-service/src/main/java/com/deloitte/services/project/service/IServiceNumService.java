package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ServiceNumQueryForm;
import com.deloitte.services.project.entity.ServiceNum;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description : ServiceNum服务类接口
 * @Modified :
 */
public interface IServiceNumService extends IService<ServiceNum> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ServiceNum>
     */
    IPage<ServiceNum> selectPage(ServiceNumQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ServiceNum>
     */
    List<ServiceNum> selectList(ServiceNumQueryForm queryForm);

    /**
     * 生成业务编码单号
     * @param businessCode
     * @return
     */
    String generalBusinessNO(String businessCode);

    /**
     * 保存业务单号
     * @param projectId
     * @param applicationId
     */
    String toSaveNum(String projectId, String applicationId);

    /**
     * 批复书业务单号
     * @param projectId
     * @return
     */
    String replySerNum(String projectId);

    int getMaxNum(String serviceOnly);
}
