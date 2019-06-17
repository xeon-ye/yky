package com.deloitte.services.fssc.business.ppc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.ppc.param.ProjectBilingInfoQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectBilingInfo;
import com.deloitte.services.fssc.business.ppc.mapper.ProjectBilingInfoMapper;
import com.deloitte.services.fssc.business.ppc.service.IProjectBilingInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectBilingInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProjectBilingInfoServiceImpl extends ServiceImpl<ProjectBilingInfoMapper, ProjectBilingInfo> implements IProjectBilingInfoService {


    @Autowired
    private ProjectBilingInfoMapper projectBilingInfoMapper;

    @Override
    public IPage<ProjectBilingInfo> selectPage(ProjectBilingInfoQueryForm queryForm ) {
        QueryWrapper<ProjectBilingInfo> queryWrapper = new QueryWrapper <ProjectBilingInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectBilingInfoMapper.selectPage(new Page<ProjectBilingInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProjectBilingInfo> selectList(ProjectBilingInfoQueryForm queryForm) {
        QueryWrapper<ProjectBilingInfo> queryWrapper = new QueryWrapper <ProjectBilingInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectBilingInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProjectBilingInfo> getQueryWrapper(QueryWrapper<ProjectBilingInfo> queryWrapper,ProjectBilingInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ProjectBilingInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(ProjectBilingInfo.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ProjectBilingInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProjectBilingInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProjectBilingInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(ProjectBilingInfo.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(ProjectBilingInfo.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(ProjectBilingInfo.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getPaperType())){
            queryWrapper.eq(ProjectBilingInfo.PAPER_TYPE,queryForm.getPaperType());
        }
        if(StringUtils.isNotBlank(queryForm.getInvoiceNum())){
            queryWrapper.eq(ProjectBilingInfo.INVOICE_NUM,queryForm.getInvoiceNum());
        }
        if(StringUtils.isNotBlank(queryForm.getInvoiceCode())){
            queryWrapper.eq(ProjectBilingInfo.INVOICE_CODE,queryForm.getInvoiceCode());
        }
        if(StringUtils.isNotBlank(queryForm.getNoCostAmount())){
            queryWrapper.eq(ProjectBilingInfo.NO_COST_AMOUNT,queryForm.getNoCostAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getCost())){
            queryWrapper.eq(ProjectBilingInfo.COST,queryForm.getCost());
        }
        if(StringUtils.isNotBlank(queryForm.getCostAmount())){
            queryWrapper.eq(ProjectBilingInfo.COST_AMOUNT,queryForm.getCostAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalAmount())){
            queryWrapper.eq(ProjectBilingInfo.TOTAL_AMOUNT,queryForm.getTotalAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(ProjectBilingInfo.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(ProjectBilingInfo.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(ProjectBilingInfo.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(ProjectBilingInfo.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(ProjectBilingInfo.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(ProjectBilingInfo.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(ProjectBilingInfo.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(ProjectBilingInfo.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(ProjectBilingInfo.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(ProjectBilingInfo.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(ProjectBilingInfo.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(ProjectBilingInfo.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(ProjectBilingInfo.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(ProjectBilingInfo.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(ProjectBilingInfo.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(ProjectBilingInfo.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(ProjectBilingInfo.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

