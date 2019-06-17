package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.AttachmentCategoryEnums;
import com.deloitte.services.srpmp.common.enums.ReportTypeEnum;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectAttachment;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectAttachmentMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-22
 * @Description :  SrpmsProjectAttachment服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectAttachmentServiceImpl extends ServiceImpl<SrpmsProjectAttachmentMapper, SrpmsProjectAttachment> implements ISrpmsProjectAttachmentService {

    @Override
    public JSONObject queryAttachmentListApply(Long projectId) {

        JSONObject relust = new JSONObject();

        List<SrpmsProjectAttachment> list = null;
        // 附件
        list = this.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_NORMAL, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentFile", JSONObject.parseArray(JSONObject.toJSONString(list)));
        }

        // 牵头单位学术委员会推荐意见
        list = this.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_01, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentCommittee", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        // 牵头单位审核意见及承诺
        list = this.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_02, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentAudit", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        // 创新工程-重大协同创新申请书声明
        list = this.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_03, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentStatement", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_04, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentDept", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_05, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentSchool", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        return relust;
    }

    @Override
    public JSONObject queryAttachmentListTaskType1(Long projectId) {

        JSONObject relust = new JSONObject();

        List<SrpmsProjectAttachment> list = null;

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL, projectId);
        if (list != null && list.size() != 0) {
            JSONArray attachmentFileJson = new JSONArray();
            for (int i =0 ; i < list.size(); i ++) {
                JSONObject tempObject = JSONObject.parseObject(JSON.toJSONString(list.get(i)));
                attachmentFileJson.add(tempObject);
            }
            relust.put("attachmentFile", attachmentFileJson);
        } else {
            relust.put("attachmentFile", new JSONArray());
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_11, projectId);
        if (list != null && list.size() != 0) {
            relust.put("opinionsAndSignatures", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_12, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentCommittee", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_13, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentAudit", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_14, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentStatement", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        return relust;
    }

    @Override
    public JSONObject queryAttachmentListTaskType2(Long projectId) {

        
        JSONObject relust = new JSONObject();

        List<SrpmsProjectAttachment> list = null;

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentCommittee", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_11, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentAudit", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        list = this.query(AttachmentCategoryEnums.ATTACHMENT_TASK_12, projectId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentStatement", JSONObject.parseObject(JSONObject.toJSONString(list.get(0))));
        }

        return relust;
    }
    
    @Override
    public void saveAttachmentListApply(JSONObject jsonData, Long projectId) {

        this.cleanAttachmentApply(projectId);

        AttachmentCategoryEnums categoryEnums;
        if(SrpmsConstant.PROJECT_TYPE_1.equals(jsonData.getString("projectFlag"))) {// 横纵向项目设置项目标识
            categoryEnums = AttachmentCategoryEnums.ATTACHMENT_TRAN_LONG;
        } else {
            categoryEnums = AttachmentCategoryEnums.ATTACHMENT_APPLY_NORMAL;
        }
        // 附件
        if (jsonData.getJSONArray("attachmentFile") != null) {
            this.saveAttachment(jsonData.getJSONArray("attachmentFile").toJavaList(SrpmsProjectAttachment.class), categoryEnums, projectId);
        }

        // 牵头单位学术委员会推荐意见
        if (jsonData.getJSONObject("attachmentCommittee") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentCommittee").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_APPLY_01, projectId);
        }

        if (jsonData.getJSONObject("attachmentAudit") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentAudit").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_APPLY_02, projectId);
        }

        if (jsonData.getJSONObject("attachmentStatement") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentStatement").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_APPLY_03, projectId);
        }

        if (jsonData.getJSONObject("attachmentDept") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentDept").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_APPLY_04, projectId);
        }

        if (jsonData.getJSONObject("attachmentSchool") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentSchool").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_APPLY_05, projectId);
        }
    }

    @Override
    public void saveAttachmentListTask(JSONObject jsonData, Long projectId) {

        this.cleanAttachmentTask(projectId);

        AttachmentCategoryEnums categoryEnums = AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL;

        // 附件
        if (jsonData.getJSONArray("attachmentFile") != null) {
            this.saveAttachment(jsonData.getJSONArray("attachmentFile").toJavaList(SrpmsProjectAttachment.class), categoryEnums, projectId);
        }
        if (jsonData.getJSONObject("attachmentCommittee") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentCommittee").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_11, projectId);
        }
        if (jsonData.getJSONObject("attachmentAudit") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentAudit").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_12, projectId);
        }
        if (jsonData.getJSONObject("attachmentStatement") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentStatement").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_13, projectId);
        }
        if (jsonData.getJSONObject("attachmentDept") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentDept").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_14, projectId);
        }
    }

    @Override
    public void saveAttachmentListTaskType1(JSONObject jsonData, Long projectId) {

        this.cleanAttachmentTask(projectId);

        // 附件
        if (jsonData.getJSONArray("attachmentFile") != null && jsonData.getJSONArray("attachmentFile").size() != 0)  {
            this.saveAttachment(jsonData.getJSONArray("attachmentFile").toJavaList( SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL, projectId);
        }

        if (jsonData.getJSONObject("opinionsAndSignatures") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("opinionsAndSignatures").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_11, projectId);
        }

        // 附件
        if (jsonData.getJSONObject("attachmentCommittee") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentCommittee").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_12, projectId);
        }

        // 牵头单位学术委员会推荐意见
        if (jsonData.getJSONObject("attachmentAudit") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentAudit").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_13, projectId);
        }

        if (jsonData.getJSONObject("attachmentStatement") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentStatement").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_14, projectId);
        }

    }

    @Override
    public void saveAttachmentListTaskType2(JSONObject jsonData, Long projectId) {

        this.cleanAttachmentTask(projectId);

        // 附件
        if (jsonData.getJSONObject("attachmentCommittee") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentCommittee").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL, projectId);
        }

        // 牵头单位学术委员会推荐意见
        if (jsonData.getJSONObject("attachmentAudit") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentAudit").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_11, projectId);
        }

        if (jsonData.getJSONObject("attachmentStatement") != null) {
            this.saveAttachment(JSONObject.parseObject(jsonData.getJSONObject("attachmentStatement").toJSONString(), SrpmsProjectAttachment.class), AttachmentCategoryEnums.ATTACHMENT_TASK_12, projectId);
        }
    }
    
    @Override
    public void saveAttachmentListUpdate(List<SrpmsProjectAttachmentVo> attachmentFile, AttachmentCategoryEnums categoryEnums, Long updateId) {

        this.cleanAttachmentUpdate(updateId);
        // 附件
        SrpmsProjectAttachmentVo attachmentVo;
        SrpmsProjectAttachment attachment;
        List<SrpmsProjectAttachment> attachmentList = new ArrayList<>();
        for (Iterator i = attachmentFile.iterator(); i.hasNext(); ) {
            attachmentVo = (SrpmsProjectAttachmentVo) i.next();
            attachment = new SrpmsProjectAttachment();
            BeanUtils.copyProperties(attachmentVo, attachment);
            attachmentList.add(attachment);
        }
        saveAttachment(attachmentList, categoryEnums, updateId);
    }

    @Override
    public void saveAttachmentListAccept(List<SrpmsProjectAttachmentVo> attachmentVoList, Long acceptId) {
        removeAttachment(acceptId);// 根据验收ID清除历史附件数据

        // 附件
        SrpmsProjectAttachmentVo attachmentVo;
        SrpmsProjectAttachment attachment;
        List<SrpmsProjectAttachment> attachmentList = new ArrayList<>();
        for (Iterator i = attachmentVoList.iterator(); i.hasNext(); ) {
            attachmentVo = (SrpmsProjectAttachmentVo) i.next();
            attachment = new SrpmsProjectAttachment();
            BeanUtils.copyProperties(attachmentVo, attachment);
            attachment.setProjectId(acceptId);
//            attachment.setId(null);
            attachmentList.add(attachment);
        }

        this.saveBatch(attachmentList);// 添加新的附件数据
    }

    @Override
    public JSONObject queryAttachmentListUpdate(AttachmentCategoryEnums categoryEnums, Long updateId) {

        JSONObject relust = new JSONObject();

        List<SrpmsProjectAttachment> list = this.query(categoryEnums, updateId);
        if (list != null && list.size() != 0) {
            relust.put("attachmentFile", JSONObject.parseArray(JSONObject.toJSONString(list)));
        }
        return relust;
    }

    /**
     * 横纵项附件
     *
     * @param projectId
     * @return
     */
    @Override
    public List<SrpmsProjectAttachmentVo> queryAttachmentListTranLong(Long projectId) {

        List<SrpmsProjectAttachmentVo> attachmentVoList = new ArrayList<>();
        List<SrpmsProjectAttachment> list = this.query(AttachmentCategoryEnums.ATTACHMENT_TRAN_LONG, projectId);
        if (list != null && list.size() != 0) {
            SrpmsProjectAttachmentVo attachmentVo;
            SrpmsProjectAttachment attachment;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                attachment = (SrpmsProjectAttachment) e.next();
                attachmentVo = new SrpmsProjectAttachmentVo();
                BeanUtils.copyProperties(attachment, attachmentVo);
                attachmentVoList.add(attachmentVo);
            }
        }
        return attachmentVoList;
    }

    /**
     * 验收报告附件
     *
     * @param acceptId
     * @return
     */
    @Override
    public List<SrpmsProjectAttachmentVo> queryAttachmentListAccept(Long acceptId) {

        List<SrpmsProjectAttachmentVo> attachmentVoList = new ArrayList<>();
        List<SrpmsProjectAttachment> list = this.queryByProjectId(acceptId);
        if (list != null && list.size() != 0) {
            SrpmsProjectAttachmentVo attachmentVo;
            SrpmsProjectAttachment attachment;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                attachment = (SrpmsProjectAttachment) e.next();
                attachmentVo = new SrpmsProjectAttachmentVo();
                BeanUtils.copyProperties(attachment, attachmentVo);
                attachmentVoList.add(attachmentVo);
            }
        }
        return attachmentVoList;
    }

    @Override
    public List<SrpmsProjectAttachment> query(AttachmentCategoryEnums type, Long projectId) {
        QueryWrapper<SrpmsProjectAttachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectAttachment.PROJECT_ID, projectId);
        queryWrapper.eq(SrpmsProjectAttachment.ATTACHMENT_CATEGORY, type.getCode());
        List<SrpmsProjectAttachment> entityList = this.list(queryWrapper);
        return entityList;
    }

    public void saveAttachment(List<SrpmsProjectAttachment> attachmentVoList, AttachmentCategoryEnums categoryEnum, Long projectId) {
        if (attachmentVoList == null){
            return;
        }
        List<SrpmsProjectAttachment> taskEntityList = new ArrayList<>();
        for(SrpmsProjectAttachment attachment: attachmentVoList){
            attachment.setAttachmentCategory(categoryEnum.getCode());
            attachment.setProjectId(projectId);
//            attachment.setId(null);
            taskEntityList.add(attachment);
        }
        this.saveBatch(taskEntityList);
    }

    public void saveAttachment(SrpmsProjectAttachment attachment, AttachmentCategoryEnums type, Long projectId) {
        if (attachment == null){
            return;
        }
        attachment.setAttachmentCategory(type.getCode());
        attachment.setProjectId(projectId);
//        attachment.setId(null);
        this.save(attachment);
    }

    public void cleanAttachmentApply(Long projectId) {

        this.removeList(AttachmentCategoryEnums.ATTACHMENT_APPLY_NORMAL, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_APPLY_01, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_APPLY_02, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_APPLY_03, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_APPLY_04, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_APPLY_05, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_TRAN_LONG, projectId);// 横纵向项目
    }

    public void cleanAttachmentTask(Long projectId) {
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_TASK_11, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_TASK_12, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_TASK_13, projectId);
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_TASK_14, projectId);
    }

    public void cleanAttachmentUpdate(Long updateId) {
        this.removeList(AttachmentCategoryEnums.ATTACHMENT_UPDATE, updateId);
    }

    public void removeList(AttachmentCategoryEnums categoryEnums, Long projectId) {

        QueryWrapper<SrpmsProjectAttachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectAttachment.ATTACHMENT_CATEGORY, categoryEnums.getCode());
        queryWrapper.eq(SrpmsProjectAttachment.PROJECT_ID, projectId);
        this.remove(queryWrapper);
    }

    public List<SrpmsProjectAttachment> queryByProjectId(Long projectId) {
        QueryWrapper<SrpmsProjectAttachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectAttachment.PROJECT_ID, projectId);
        List<SrpmsProjectAttachment> entityList = this.list(queryWrapper);
        return entityList;
    }

    public void removeAttachment(Long projectId) {
        QueryWrapper<SrpmsProjectAttachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectAttachment.PROJECT_ID, projectId);
        this.remove(queryWrapper);
    }

    /**
     * 清除年度报告附件
     * @param projectId
     */
    @Override
    public void cleanAttachmentProjectReport(Long projectId, String reportType) {
        if (StringUtils.equals(ReportTypeEnum.YEAR_REPORT.getCode(), reportType)) {
            this.removeList(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_YEAR, projectId);
        }
        if (StringUtils.equals(ReportTypeEnum.MID_REPORT.getCode(), reportType)) {
            this.removeList(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_MID, projectId);
        }
        if (StringUtils.equals(ReportTypeEnum.OTHER.getCode(), reportType)) {
            this.removeList(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_OTHER, projectId);
        }
    }

    @Override
    public List<SrpmsProjectAttachment> queryAttachmentListReport(String reportType, Long projectId) {


        List<SrpmsProjectAttachment> list = null;

        if(StringUtils.equals(ReportTypeEnum.YEAR_REPORT.getCode(), reportType)) {
            list = this.query(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_YEAR, projectId);
        } else if(StringUtils.equals(ReportTypeEnum.MID_REPORT.getCode(), reportType)) {
            list = this.query(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_MID, projectId);
        } else if(StringUtils.equals(ReportTypeEnum.OTHER.getCode(), reportType)) {
            list = this.query(AttachmentCategoryEnums.ATTACHMENT_PROJECT_REPORT_OTHER, projectId);
        }

        return list;
    }
}
