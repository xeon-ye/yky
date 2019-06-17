package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ApprovalOpinionQueryForm;
import com.deloitte.services.contract.entity.ApprovalOpinion;
import com.deloitte.services.contract.mapper.ApprovalOpinionMapper;
import com.deloitte.services.contract.service.IApprovalOpinionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-01
 * @Description :  ApprovalOpinion服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ApprovalOpinionServiceImpl extends ServiceImpl<ApprovalOpinionMapper, ApprovalOpinion> implements IApprovalOpinionService {


    @Autowired
    private ApprovalOpinionMapper approvalOpinionMapper;

    @Override
    public IPage<ApprovalOpinion> selectPage(ApprovalOpinionQueryForm queryForm ) {
        QueryWrapper<ApprovalOpinion> queryWrapper = new QueryWrapper <ApprovalOpinion>();
        //getQueryWrapper(queryWrapper,queryForm);
        return approvalOpinionMapper.selectPage(new Page<ApprovalOpinion>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ApprovalOpinion> selectList(ApprovalOpinionQueryForm queryForm) {
        QueryWrapper<ApprovalOpinion> queryWrapper = new QueryWrapper <ApprovalOpinion>();
        //getQueryWrapper(queryWrapper,queryForm);
        return approvalOpinionMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ApprovalOpinion> getQueryWrapper(QueryWrapper<ApprovalOpinion> queryWrapper,ApprovalOpinionQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(ApprovalOpinion.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessFefineKey())){
            queryWrapper.eq(ApprovalOpinion.PROCESS_FEFINE_KEY,queryForm.getProcessFefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(ApprovalOpinion.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinionType())){
            queryWrapper.eq(ApprovalOpinion.OPINION_TYPE,queryForm.getOpinionType());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyText())){
            queryWrapper.eq(ApprovalOpinion.REPLY_TEXT,queryForm.getReplyText());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPerson())){
            queryWrapper.eq(ApprovalOpinion.REPLY_PERSON,queryForm.getReplyPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPersonCode())){
            queryWrapper.eq(ApprovalOpinion.REPLY_PERSON_CODE,queryForm.getReplyPersonCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinion())){
            queryWrapper.eq(ApprovalOpinion.OPINION,queryForm.getOpinion());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinionFileId())){
            queryWrapper.eq(ApprovalOpinion.OPINION_FILE_ID,queryForm.getOpinionFileId());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinionFileUrl())){
            queryWrapper.eq(ApprovalOpinion.OPINION_FILE_URL,queryForm.getOpinionFileUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinionFileName())){
            queryWrapper.eq(ApprovalOpinion.OPINION_FILE_NAME,queryForm.getOpinionFileName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ApprovalOpinion.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ApprovalOpinion.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ApprovalOpinion.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ApprovalOpinion.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(ApprovalOpinion.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(ApprovalOpinion.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(ApprovalOpinion.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(ApprovalOpinion.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(ApprovalOpinion.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(ApprovalOpinion.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

