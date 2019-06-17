package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingRecordQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.entity.OaMeetingRecord;
import com.deloitte.services.notice.mapper.OaMeetingRecordMapper;
import com.deloitte.services.notice.service.IOaMeetingRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaMeetingRecord服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMeetingRecordServiceImpl extends ServiceImpl<OaMeetingRecordMapper, OaMeetingRecord> implements IOaMeetingRecordService {


    @Autowired
    private OaMeetingRecordMapper oaMeetingRecordMapper;

    @Autowired
    private OaAttachmentMapper oaAttachmentMapper;

    @Override
    public OaMeetingRecord getById(Serializable id) {
        OaMeetingRecord oaMeetingRecord = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaMeetingRecord.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaMeetingRecord.setAttachments(oaAttachmentMapper.selectList(queryWrapper));
        return oaMeetingRecord;
    }

    @Override
    public Result save(OaMeetingRecordForm entity) {
        OaMeetingRecord oaMeetingRecord = new BeanUtils<OaMeetingRecord>().copyObj(entity, OaMeetingRecord.class);
        boolean flag = super.save(oaMeetingRecord);
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaMeetingRecord.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            oaAttachmentMapper.insert(temp);
        });
        if(flag){
            return Result.success(String.valueOf(oaMeetingRecord.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaMeetingRecordForm entity) {
        OaMeetingRecord oaMeetingRecord = new BeanUtils<OaMeetingRecord>().copyObj(entity, OaMeetingRecord.class);
        oaMeetingRecord.setId(id);
        boolean flag = super.updateById(oaMeetingRecord);
        oaAttachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaMeetingRecord.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            oaAttachmentMapper.insert(temp);
        });

        return Result.success(flag);
    }


    @Override
    public IPage<OaMeetingRecord> selectPage(OaMeetingRecordQueryForm queryForm ) {
        QueryWrapper<OaMeetingRecord> queryWrapper = new QueryWrapper <OaMeetingRecord>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingRecordMapper.selectPage(new Page<OaMeetingRecord>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMeetingRecord> selectList(OaMeetingRecordQueryForm queryForm) {
        QueryWrapper<OaMeetingRecord> queryWrapper = new QueryWrapper <OaMeetingRecord>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingRecordMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaMeetingRecord> getHomeList(int num) {
        return oaMeetingRecordMapper.getHomeList(num);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaMeetingRecord> getQueryWrapper(QueryWrapper<OaMeetingRecord> queryWrapper,OaMeetingRecordQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaMeetingRecord.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptId())){
            queryWrapper.eq(OaMeetingRecord.APPLY_DEPT_ID,queryForm.getApplyDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptName())){
            queryWrapper.eq(OaMeetingRecord.APPLY_DEPT_NAME,queryForm.getApplyDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaMeetingRecord.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaMeetingRecord.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(null != queryForm.getDelFlag()){
            queryWrapper.eq(OaMeetingRecord.DEL_FLAG,queryForm.getDelFlag());
        }
        if(null != queryForm.getIsneedBussiness()){
            queryWrapper.eq(OaMeetingRecord.ISNEED_BUSSINESS,queryForm.getIsneedBussiness());
        }
        if(StringUtils.isNotBlank(queryForm.getBussinessId())){
            queryWrapper.eq(OaMeetingRecord.BUSSINESS_ID,queryForm.getBussinessId());
        }
        if(StringUtils.isNotBlank(queryForm.getBussinessName())){
            queryWrapper.eq(OaMeetingRecord.BUSSINESS_NAME,queryForm.getBussinessName());
        }
        if(null != queryForm.getUrgentLevel()){
            queryWrapper.eq(OaMeetingRecord.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        queryWrapper.orderByDesc(OaMeetingRecord.APPLY_DATETIME);
        return queryWrapper;
    }
}

