package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.notice.param.OaResourceQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaResourceForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.entity.OaResource;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.mapper.OaResourceMapper;
import com.deloitte.services.notice.service.IOaResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaResource服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaResourceServiceImpl extends ServiceImpl<OaResourceMapper, OaResource> implements IOaResourceService {

    @Autowired
    private OaResourceMapper oaResourceMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Override
    public OaResource getById(Serializable id) {
        OaResource oaResource = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaResource.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaResource.setAttachments(attachmentMapper.selectList(queryWrapper));
        
        return oaResource;
    }

    @Override
    public Result save(OaResourceForm entity) {
        OaResource oaResource = new BeanUtils<OaResource>().copyObj(entity, OaResource.class);
        boolean flag = super.save(oaResource);
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaResource.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });
        if(flag){
            return Result.success(String.valueOf(oaResource.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaResourceForm entity) {
        OaResource oaResource = new BeanUtils<OaResource>().copyObj(entity, OaResource.class);
        oaResource.setId(id);
        boolean flag = super.updateById(oaResource);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaResource.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        return Result.success(flag);
    }

    @Override
    public IPage<OaResource> selectPage(OaResourceQueryForm queryForm ) {
        QueryWrapper<OaResource> queryWrapper = new QueryWrapper <OaResource>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaResourceMapper.selectPage(new Page<OaResource>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaResource> selectList(OaResourceQueryForm queryForm) {
        QueryWrapper<OaResource> queryWrapper = new QueryWrapper <OaResource>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaResourceMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaResource> getHomeList(int num) {
        return oaResourceMapper.getHomeList(num);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaResource> getQueryWrapper(QueryWrapper<OaResource> queryWrapper,OaResourceQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaResource.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptId())){
            queryWrapper.eq(OaResource.APPLY_DEPT_ID,queryForm.getApplyDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptName())){
            queryWrapper.eq(OaResource.APPLY_DEPT_NAME,queryForm.getApplyDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaResource.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaResource.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(null != queryForm.getUrgentLevel()){
            queryWrapper.eq(OaResource.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(null != queryForm.getDelFlag()){
            queryWrapper.eq(OaResource.DEL_FLAG,queryForm.getDelFlag());
        }
        queryWrapper.orderByDesc(OaResource.APPLY_DATETIME);
        return queryWrapper;
    }
}

