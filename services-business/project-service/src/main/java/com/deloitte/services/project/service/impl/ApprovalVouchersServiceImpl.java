package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.param.ApprovalVouchersQueryForm;
import com.deloitte.services.project.common.enums.VoucherStatusEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.entity.Application;
import com.deloitte.services.project.entity.ApprovalVouchers;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.mapper.ApprovalVouchersMapper;
import com.deloitte.services.project.service.IApplicationService;
import com.deloitte.services.project.service.IApprovalVouchersService;
import com.deloitte.services.project.service.ICommonService;
import com.deloitte.services.project.service.IProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :  ApprovalVouchers服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ApprovalVouchersServiceImpl extends ServiceImpl<ApprovalVouchersMapper, ApprovalVouchers> implements IApprovalVouchersService {


    @Autowired
    private ApprovalVouchersMapper approvalVouchersMapper;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IProjectsService projectsService;

    @Override
    public IPage<ApprovalVouchers> selectPage(ApprovalVouchersQueryForm queryForm ) {
        QueryWrapper<ApprovalVouchers> queryWrapper = new QueryWrapper <ApprovalVouchers>();
        //getQueryWrapper(queryWrapper,queryForm);
        return approvalVouchersMapper.selectPage(new Page<ApprovalVouchers>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ApprovalVouchers> selectList(ApprovalVouchersQueryForm queryForm) {
        QueryWrapper<ApprovalVouchers> queryWrapper = new QueryWrapper <ApprovalVouchers>();
        //getQueryWrapper(queryWrapper,queryForm);
        return approvalVouchersMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ApprovalVouchers> getQueryWrapper(QueryWrapper<ApprovalVouchers> queryWrapper,ApprovalVouchersQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBizNumber())){
            queryWrapper.eq(ApprovalVouchers.BIZ_NUMBER,queryForm.getBizNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherName())){
            queryWrapper.eq(ApprovalVouchers.VOUCHER_NAME,queryForm.getVoucherName());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherType())){
            queryWrapper.eq(ApprovalVouchers.VOUCHER_TYPE,queryForm.getVoucherType());
        }
        if(StringUtils.isNotBlank(queryForm.getVoucherStatus())){
            queryWrapper.eq(ApprovalVouchers.VOUCHER_STATUS,queryForm.getVoucherStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(ApprovalVouchers.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(ApprovalVouchers.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getStartTime())){
            queryWrapper.eq(ApprovalVouchers.START_TIME,queryForm.getStartTime());
        }
        if(StringUtils.isNotBlank(queryForm.getEndTime())){
            queryWrapper.eq(ApprovalVouchers.END_TIME,queryForm.getEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ApprovalVouchers.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ApprovalVouchers.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ApprovalVouchers.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ApprovalVouchers.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(ApprovalVouchers.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBusinessId())){
            queryWrapper.eq(ApprovalVouchers.BUSINESS_ID,queryForm.getBusinessId());
        }
        return queryWrapper;
    }
     */
    @Override
    @Transactional
    public ApprovalVouchers generateNewVoucher(String businessId, VoucherTypeEnums typeEnums) {
        UserVo userVo = commonService.getCurrentUser();
        ApprovalVouchers voucherEntity = new ApprovalVouchers();
        //根据businessid查询对应的业务实体
        //***********************************************每个流程单据的生成规则需要按照类型重新，这里只是一个demo***********************************************
        //***********************************************每个流程单据的生成规则需要按照类型重新，这里只是一个demo***********************************************
        //***********************************************每个流程单据的生成规则需要按照类型重新，这里只是一个demo***********************************************
        //***********************************************每个流程单据的生成规则需要按照类型重新，这里只是一个demo***********************************************
        //***********************************************每个流程单据的生成规则需要按照类型重新，这里只是一个demo***********************************************
        //医院服务能力建设 项目申报流程
        if(VoucherTypeEnums.PROJECT_APPLY_PROCESS.getCode().equals(typeEnums.getCode())){
            Application application = applicationService.getById(businessId);
            Projects projects = projectsService.getById(application.getProjectId());
            voucherEntity.setBizNumber(application.getApplicationId()+"");//流水号
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setUserName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(businessId+"");
            voucherEntity.setBusinessName(projects.getProjectName()+"");
            voucherEntity.setVoucherName(projects.getProjectName() + "【" + application.getServiceNum() + "】");
            voucherEntity.setOrgCode(projects.getOrganizationCode());
        }else if(VoucherTypeEnums.PROJECT_CANCLE_PROCESS.getCode().equals(typeEnums.getCode())){
            Application application = applicationService.getById(businessId);
            Projects projects = projectsService.getById(application.getProjectId());
            voucherEntity.setBizNumber(application.getApplicationId()+"");//流水号
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setUserName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(businessId+"");
            voucherEntity.setBusinessName(projects.getProjectName()+"");
            voucherEntity.setVoucherName(projects.getProjectName() + "【" + application.getServiceNum() + "】");
            voucherEntity.setOrgCode(projects.getOrganizationCode());
        }else if(VoucherTypeEnums.PROJECT_CHANGE_PROCESS.getCode().equals(typeEnums.getCode())){
            Application application = applicationService.getById(businessId);
            Projects projects = projectsService.getById(application.getProjectId());
            voucherEntity.setBizNumber(application.getApplicationId()+"");//流水号
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setUserName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(businessId+"");
            voucherEntity.setBusinessName(projects.getProjectName()+"");
            voucherEntity.setVoucherName(projects.getProjectName() + "【" + application.getServiceNum() + "】");
            voucherEntity.setOrgCode(projects.getOrganizationCode());
        }else if(VoucherTypeEnums.PROJECT_OPERATE_PROCESS.getCode().equals(typeEnums.getCode())){
            Application application = applicationService.getById(businessId);
            Projects projects = projectsService.getById(application.getProjectId());
            voucherEntity.setBizNumber(application.getApplicationId()+"");//流水号
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setUserName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(businessId+"");
            voucherEntity.setBusinessName(projects.getProjectName()+"");
            voucherEntity.setVoucherName(projects.getProjectName() + "【" + application.getServiceNum() + "】");
            voucherEntity.setOrgCode(projects.getOrganizationCode());
        }else if(VoucherTypeEnums.PROJECT_ADJUST_PROCESS.getCode().equals(typeEnums.getCode())){
            Application application = applicationService.getById(businessId);
            Projects projects = projectsService.getById(application.getProjectId());
            voucherEntity.setBizNumber(application.getApplicationId()+"");//流水号
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setUserName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(businessId+"");
            voucherEntity.setBusinessName(projects.getProjectName()+"");
            voucherEntity.setVoucherName(projects.getProjectName() + "【" + application.getServiceNum() + "】");
            voucherEntity.setOrgCode(projects.getOrganizationCode());
        }else if (VoucherTypeEnums.REVIEW_READ.getCode().equals(typeEnums.getCode())){
            voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            voucherEntity.setStartTime(LocalDateTime.now());
            voucherEntity.setVoucherType(typeEnums.getCode());
            voucherEntity.setUserName(userVo.getName());
            voucherEntity.setUserId(Long.parseLong(userVo.getId()));
            voucherEntity.setBusinessId(businessId+"");
            voucherEntity.setVoucherName("对应名称" + "【" + typeEnums.getValue() + "】");
            voucherEntity.setOrgCode("对象部门");
        }
        this.save(voucherEntity);
        return voucherEntity;
    }
}

