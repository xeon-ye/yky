package com.deloitte.services.attachment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.attachment.param.OaAttachmentQueryForm;
import com.deloitte.services.attachment.entity.OaAttachment;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description : OaAttachment服务类接口
 * @Modified :
 */
public interface IOaAttachmentService extends IService<OaAttachment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaAttachment>
     */
    IPage<OaAttachment> selectPage(OaAttachmentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaAttachment>
     */
    List<OaAttachment> selectList(OaAttachmentQueryForm queryForm);

    /**
     * 查询首页数据
     * @param num
     * @param busicessName
     * @return
     */
    List<OaAttachment> getHomeList(int num, String busicessName);

    boolean deleteByBusinessId(String businessId);

}
