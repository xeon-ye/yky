package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingArrgeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.entity.OaMeetingArrge;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.mapper.OaMeetingArrgeMapper;
import com.deloitte.services.notice.service.IOaMeetingArrgeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaMeetingArrge服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMeetingArrgeServiceImpl extends ServiceImpl<OaMeetingArrgeMapper, OaMeetingArrge> implements IOaMeetingArrgeService {

    @Autowired
    private OaMeetingArrgeMapper oaMeetingArrgeMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Override
    public OaMeetingArrge getById(Serializable id) {
        OaMeetingArrge oaMeetingArrge = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaMeetingArrge.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaMeetingArrge.setAttachments(attachmentMapper.selectList(queryWrapper));
        
        return oaMeetingArrge;
    }

    @Override
    public Result save(OaMeetingArrgeForm entity) {
        OaMeetingArrge oaMeetingArrge = new BeanUtils<OaMeetingArrge>().copyObj(entity, OaMeetingArrge.class);
        boolean flag = super.save(oaMeetingArrge);
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaMeetingArrge.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });
        if(flag){
            return Result.success(String.valueOf(oaMeetingArrge.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaMeetingArrgeForm entity) {
        OaMeetingArrge oaMeetingArrge = new BeanUtils<OaMeetingArrge>().copyObj(entity, OaMeetingArrge.class);
        oaMeetingArrge.setId(id);
        boolean flag = super.updateById(oaMeetingArrge);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaMeetingArrge.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        return Result.success(flag);
    }

    @Override
    public IPage<OaMeetingArrge> selectPage(OaMeetingArrgeQueryForm queryForm ) {
        QueryWrapper<OaMeetingArrge> queryWrapper = new QueryWrapper <OaMeetingArrge>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingArrgeMapper.selectPage(new Page<OaMeetingArrge>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMeetingArrge> selectList(OaMeetingArrgeQueryForm queryForm) {
        QueryWrapper<OaMeetingArrge> queryWrapper = new QueryWrapper <OaMeetingArrge>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingArrgeMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaMeetingArrge> getHomeList(int num) {
        return oaMeetingArrgeMapper.getHomeList(num);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaMeetingArrge> getQueryWrapper(QueryWrapper<OaMeetingArrge> queryWrapper,OaMeetingArrgeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaMeetingArrge.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptId())){
            queryWrapper.eq(OaMeetingArrge.APPLY_DEPT_ID,queryForm.getApplyDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptName())){
            queryWrapper.eq(OaMeetingArrge.APPLY_DEPT_NAME,queryForm.getApplyDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaMeetingArrge.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaMeetingArrge.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(null != queryForm.getUrgentLevel()){
            queryWrapper.eq(OaMeetingArrge.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(null != queryForm.getDelFlag()){
            queryWrapper.eq(OaMeetingArrge.DEL_FLAG,queryForm.getDelFlag());
        }
        queryWrapper.orderByDesc(OaMeetingArrge.APPLY_DATETIME);
        return queryWrapper;
    }
}

