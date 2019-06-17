package com.deloitte.services.srpmp.project.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectApprovalQueryForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectApproval;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectExpert;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectApprovalMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectApprovalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description :  SrpmsProjectApproval服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectApprovalServiceImpl extends ServiceImpl<SrpmsProjectApprovalMapper, SrpmsProjectApproval> implements ISrpmsProjectApprovalService {


    @Autowired
    private SrpmsProjectApprovalMapper srpmsProjectApprovalMapper;

    @Override
    public IPage<SrpmsProjectApproval> selectPage(SrpmsProjectApprovalQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectApproval> queryWrapper = new QueryWrapper <SrpmsProjectApproval>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApprovalMapper.selectPage(new Page<SrpmsProjectApproval>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectApproval> selectList(SrpmsProjectApprovalQueryForm queryForm) {
        QueryWrapper<SrpmsProjectApproval> queryWrapper = new QueryWrapper <SrpmsProjectApproval>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectApprovalMapper.selectList(queryWrapper);
    }

    @Override
    public List<SrpmsProjectApproval> queryListByProjectId(Long projectId) {
        QueryWrapper<SrpmsProjectApproval> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectApproval.PROJECT_ID, projectId);

        return this.list(queryWrapper);
    }
    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectApproval> getQueryWrapper(QueryWrapper<SrpmsProjectApproval> queryWrapper,BaseQueryForm<SrpmsProjectApprovalQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectApproval.getProjectId())){
            queryWrapper.like(SrpmsProjectApproval.PROJECT_ID,srpmsProjectApproval.getProjectId());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getApprovalComments())){
            queryWrapper.like(SrpmsProjectApproval.APPROVAL_COMMENTS,srpmsProjectApproval.getApprovalComments());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getApprovalTime())){
            queryWrapper.like(SrpmsProjectApproval.APPROVAL_TIME,srpmsProjectApproval.getApprovalTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getApprover())){
            queryWrapper.like(SrpmsProjectApproval.APPROVER,srpmsProjectApproval.getApprover());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getCreateTime())){
            queryWrapper.like(SrpmsProjectApproval.CREATE_TIME,srpmsProjectApproval.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getCreateBy())){
            queryWrapper.like(SrpmsProjectApproval.CREATE_BY,srpmsProjectApproval.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getUpdateTime())){
            queryWrapper.like(SrpmsProjectApproval.UPDATE_TIME,srpmsProjectApproval.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectApproval.getUpdateBy())){
            queryWrapper.like(SrpmsProjectApproval.UPDATE_BY,srpmsProjectApproval.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

