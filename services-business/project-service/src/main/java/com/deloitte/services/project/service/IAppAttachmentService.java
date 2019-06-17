package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.AppAttachmentQueryForm;
import com.deloitte.services.project.entity.AppAttachment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : AppAttachment服务类接口
 * @Modified :
 */
public interface IAppAttachmentService extends IService<AppAttachment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AppAttachment>
     */
    IPage<AppAttachment> selectPage(AppAttachmentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AppAttachment>
     */
    List<AppAttachment> selectList(AppAttachmentQueryForm queryForm);
}
