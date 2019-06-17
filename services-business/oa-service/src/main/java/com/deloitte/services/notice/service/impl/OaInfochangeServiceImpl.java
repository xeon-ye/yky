package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.notice.param.OaInfochangeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.entity.OaInfochange;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.mapper.OaInfochangeMapper;
import com.deloitte.services.notice.service.IOaInfochangeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaInfochange服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaInfochangeServiceImpl extends ServiceImpl<OaInfochangeMapper, OaInfochange> implements IOaInfochangeService {

    @Autowired
    private OaInfochangeMapper oaInfochangeMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Override
    public OaInfochange getById(Serializable id) {
        OaInfochange oaInfochange = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaInfochange.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaInfochange.setAttachments(attachmentMapper.selectList(queryWrapper));
        
        return oaInfochange;
    }

    @Override
    public Result save(OaInfochangeForm entity) {
        OaInfochange oaInfochange = new BeanUtils<OaInfochange>().copyObj(entity, OaInfochange.class);
        boolean flag = super.save(oaInfochange);
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaInfochange.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });
        if(flag){
            return Result.success(String.valueOf(oaInfochange.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaInfochangeForm entity) {
        OaInfochange oaInfochange = new BeanUtils<OaInfochange>().copyObj(entity, OaInfochange.class);
        oaInfochange.setId(id);
        boolean flag = super.updateById(oaInfochange);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaInfochange.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        return Result.success(flag);
    }

    @Override
    public IPage<OaInfochange> selectPage(OaInfochangeQueryForm queryForm ) {
        QueryWrapper<OaInfochange> queryWrapper = new QueryWrapper <OaInfochange>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaInfochangeMapper.selectPage(new Page<OaInfochange>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaInfochange> selectList(OaInfochangeQueryForm queryForm) {
        QueryWrapper<OaInfochange> queryWrapper = new QueryWrapper <OaInfochange>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaInfochangeMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaInfochange> getHomeList(int num) {
        return oaInfochangeMapper.getHomeList(num);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaInfochange> getQueryWrapper(QueryWrapper<OaInfochange> queryWrapper,OaInfochangeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaInfochange.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptId())){
            queryWrapper.eq(OaInfochange.APPLY_DEPT_ID,queryForm.getApplyDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptName())){
            queryWrapper.eq(OaInfochange.APPLY_DEPT_NAME,queryForm.getApplyDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaInfochange.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaInfochange.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(null != queryForm.getUrgentLevel()){
            queryWrapper.eq(OaInfochange.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(null != queryForm.getDelFlag()){
            queryWrapper.eq(OaInfochange.DEL_FLAG,queryForm.getDelFlag());
        }
        queryWrapper.orderByDesc(OaInfochange.APPLY_DATETIME);
        return queryWrapper;
    }
}

