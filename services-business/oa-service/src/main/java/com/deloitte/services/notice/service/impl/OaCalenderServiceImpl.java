package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.notice.param.OaCalenderQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.entity.OaCalender;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.mapper.OaCalenderMapper;
import com.deloitte.services.notice.service.IOaCalenderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaCalender服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaCalenderServiceImpl extends ServiceImpl<OaCalenderMapper, OaCalender> implements IOaCalenderService {

    @Autowired
    private OaCalenderMapper oaCalenderMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Override
    public OaCalender getById(Serializable id) {
        OaCalender oaCalender = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaCalender.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaCalender.setAttachments(attachmentMapper.selectList(queryWrapper));
        
        return oaCalender;
    }

    @Override
    public Result save(OaCalenderForm entity) {
        OaCalender oaCalender = new BeanUtils<OaCalender>().copyObj(entity, OaCalender.class);
        boolean flag = super.save(oaCalender);
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaCalender.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });
        if(flag){
            return Result.success(String.valueOf(oaCalender.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaCalenderForm entity) {
        OaCalender oaCalender = new BeanUtils<OaCalender>().copyObj(entity, OaCalender.class);
        oaCalender.setId(id);
        boolean flag = super.updateById(oaCalender);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaCalender.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        return Result.success(flag);
    }

    @Override
    public IPage<OaCalender> selectPage(OaCalenderQueryForm queryForm ) {
        QueryWrapper<OaCalender> queryWrapper = new QueryWrapper <OaCalender>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaCalenderMapper.selectPage(new Page<OaCalender>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaCalender> selectList(OaCalenderQueryForm queryForm) {
        QueryWrapper<OaCalender> queryWrapper = new QueryWrapper <OaCalender>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaCalenderMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaCalender> getHomeList(int num) {
        return oaCalenderMapper.getHomeList(num);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaCalender> getQueryWrapper(QueryWrapper<OaCalender> queryWrapper,OaCalenderQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaCalender.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptId())){
            queryWrapper.eq(OaCalender.APPLY_DEPT_ID,queryForm.getApplyDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptName())){
            queryWrapper.eq(OaCalender.APPLY_DEPT_NAME,queryForm.getApplyDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaCalender.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaCalender.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(null != queryForm.getUrgentLevel()){
            queryWrapper.eq(OaCalender.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(null != queryForm.getDelFlag()){
            queryWrapper.eq(OaCalender.DEL_FLAG,queryForm.getDelFlag());
        }
        queryWrapper.orderByDesc(OaCalender.APPLY_DATETIME);
        return queryWrapper;
    }
}

