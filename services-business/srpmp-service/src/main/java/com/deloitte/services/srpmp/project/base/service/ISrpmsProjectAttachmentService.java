package com.deloitte.services.srpmp.project.base.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.services.srpmp.common.enums.AttachmentCategoryEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectAttachment;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-22
 * @Description : SrpmsProjectAttachment服务类接口
 * @Modified :
 */
public interface ISrpmsProjectAttachmentService extends IService<SrpmsProjectAttachment> {

    JSONObject queryAttachmentListApply(Long projectId);

    JSONObject queryAttachmentListTaskType1(Long projectId);
    
    JSONObject queryAttachmentListTaskType2(Long projectId);

    void saveAttachmentListApply(JSONObject json, Long projectId);

    void saveAttachmentListTask(JSONObject jsonData, Long projectId);

    void saveAttachmentListTaskType1(JSONObject json, Long projectId);
    
    void saveAttachmentListTaskType2(JSONObject json, Long projectId);

    JSONObject queryAttachmentListUpdate(AttachmentCategoryEnums categoryEnums, Long updateId);

    List<SrpmsProjectAttachmentVo> queryAttachmentListTranLong(Long projectId);

    List<SrpmsProjectAttachmentVo> queryAttachmentListAccept(Long acceptId);

    void saveAttachmentListUpdate(List<SrpmsProjectAttachmentVo> attachmentFile, AttachmentCategoryEnums categoryEnums, Long updateId);

    void saveAttachmentListAccept(List<SrpmsProjectAttachmentVo> attachmentVoList, Long acceptId);

    /**
     * 清除年度报告附件
     * @param projectId
     */
    void cleanAttachmentProjectReport(Long projectId, String reportType);

    /**
     * 查询年度报告附件
     * @param projectId
     * @return
     */
    List<SrpmsProjectAttachment> queryAttachmentListReport(String reportType, Long projectId);

    List<SrpmsProjectAttachment> query(AttachmentCategoryEnums type, Long projectId);
}
