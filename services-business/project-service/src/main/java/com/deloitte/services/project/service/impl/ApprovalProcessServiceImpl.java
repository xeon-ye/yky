package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ApprovalProcessQueryForm;
import com.deloitte.services.project.entity.ApprovalProcess;
import com.deloitte.services.project.mapper.ApprovalProcessMapper;
import com.deloitte.services.project.service.IApprovalProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :  ApprovalProcess服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ApprovalProcessServiceImpl extends ServiceImpl<ApprovalProcessMapper, ApprovalProcess> implements IApprovalProcessService {


    @Autowired
    private ApprovalProcessMapper approvalProcessMapper;

    @Override
    public IPage<ApprovalProcess> selectPage(ApprovalProcessQueryForm queryForm ) {
        QueryWrapper<ApprovalProcess> queryWrapper = new QueryWrapper <ApprovalProcess>();
        getQueryWrapper(queryWrapper,queryForm);
        return approvalProcessMapper.selectPage(new Page<ApprovalProcess>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
        }

    @Override
    public List<ApprovalProcess> selectList(ApprovalProcessQueryForm queryForm) {
        QueryWrapper<ApprovalProcess> queryWrapper = new QueryWrapper <ApprovalProcess>();
        getQueryWrapper(queryWrapper,queryForm);
        return approvalProcessMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<ApprovalProcess> getQueryWrapper(QueryWrapper<ApprovalProcess> queryWrapper,ApprovalProcessQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(ApprovalProcess.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessType())){
            queryWrapper.eq(ApprovalProcess.PROCESS_TYPE,queryForm.getProcessType());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessDefineKey())){
            queryWrapper.eq(ApprovalProcess.PROCESS_DEFINE_KEY,queryForm.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessDefineName())){
            queryWrapper.eq(ApprovalProcess.PROCESS_DEFINE_NAME,queryForm.getProcessDefineName());
        }
        /*if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ApprovalProcess.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ApprovalProcess.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ApprovalProcess.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ApprovalProcess.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(ApprovalProcess.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ApprovalProcess.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ApprovalProcess.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ApprovalProcess.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(ApprovalProcess.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(ApprovalProcess.EXT5,queryForm.getExt5());
        }*/
        return queryWrapper;
    }

}

