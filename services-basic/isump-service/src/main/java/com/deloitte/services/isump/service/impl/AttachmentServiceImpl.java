package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.AttachmentQueryForm;
import com.deloitte.services.isump.entity.Attachment;
import com.deloitte.services.isump.mapper.AttachmentMapper;
import com.deloitte.services.isump.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description :  Attachment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {


    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public IPage<Attachment> selectPage(AttachmentQueryForm queryForm ) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper <Attachment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return attachmentMapper.selectPage(new Page<Attachment>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Attachment> selectList(AttachmentQueryForm queryForm) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper <Attachment>();
        //getQueryWrapper(queryWrapper,queryForm);
        if(queryForm.getMasterId() != null){
            queryWrapper.eq("MASTER_ID", queryForm.getMasterId());
        }
        if(queryForm.getMasterType() != null){
            queryWrapper.eq("MASTER_TYPE", queryForm.getMasterType());
        }
        return attachmentMapper.selectList(queryWrapper);
    }

    @Override
    public int delByMasterId(String id) {
        return attachmentMapper.delByMasterId(id);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Attachment> getQueryWrapper(QueryWrapper<Attachment> queryWrapper,AttachmentQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getFileId())){
            queryWrapper.eq(Attachment.FILE_ID,queryForm.getFileId());
        }
        if(StringUtils.isNotBlank(queryForm.getMasterType())){
            queryWrapper.eq(Attachment.MASTER_TYPE,queryForm.getMasterType());
        }
        if(StringUtils.isNotBlank(queryForm.getMasterId())){
            queryWrapper.eq(Attachment.MASTER_ID,queryForm.getMasterId());
        }
        if(StringUtils.isNotBlank(queryForm.getFileName())){
            queryWrapper.eq(Attachment.FILE_NAME,queryForm.getFileName());
        }
        if(StringUtils.isNotBlank(queryForm.getFileUrl())){
            queryWrapper.eq(Attachment.FILE_URL,queryForm.getFileUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getSort())){
            queryWrapper.eq(Attachment.SORT,queryForm.getSort());
        }
        return queryWrapper;
    }
     */
}

