package com.deloitte.services.attachment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.attachment.param.OaAttachmentQueryForm;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description :  OaAttachment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaAttachmentServiceImpl extends ServiceImpl<OaAttachmentMapper, OaAttachment> implements IOaAttachmentService {

    @Autowired
    private OaAttachmentMapper oaAttachmentMapper;

    @Override
    public IPage<OaAttachment> selectPage(OaAttachmentQueryForm queryForm ) {
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper <OaAttachment>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaAttachmentMapper.selectPage(new Page<OaAttachment>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaAttachment> selectList(OaAttachmentQueryForm queryForm) {
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper <OaAttachment>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaAttachmentMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaAttachment> getHomeList(int num, String busicessName) {
        return oaAttachmentMapper.getHomeList(num, busicessName);
    }

    @Override
    public boolean deleteByBusinessId(String businessId) {
        return oaAttachmentMapper.deleteByBusinessId(businessId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaAttachment> getQueryWrapper(QueryWrapper<OaAttachment> queryWrapper,OaAttachmentQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBusicessId())){
            queryWrapper.eq(OaAttachment.BUSICESS_ID,queryForm.getBusicessId());
        }
        if(StringUtils.isNotBlank(queryForm.getBusicessName())){
            queryWrapper.eq(OaAttachment.BUSICESS_NAME,queryForm.getBusicessName());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachName())){
            queryWrapper.like(OaAttachment.ATTACH_NAME,queryForm.getAttachName());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachUrl())){
            queryWrapper.eq(OaAttachment.ATTACH_URL,queryForm.getAttachUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getDelFlag())){
            queryWrapper.eq(OaAttachment.DEL_FLAG,queryForm.getDelFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachPath())){
            queryWrapper.eq(OaAttachment.ATTACH_PATH,queryForm.getAttachPath());
        }
        queryWrapper.orderByDesc(OaAttachment.APPLY_DATETIME);
        return queryWrapper;
    }
}