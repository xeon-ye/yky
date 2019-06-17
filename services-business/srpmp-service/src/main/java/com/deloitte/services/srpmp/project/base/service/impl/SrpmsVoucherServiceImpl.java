package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsVoucherQueryForm;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherAuditModeEnums;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAccept;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsVoucherMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @Author : lixin
 * @Date : Create in 2019-03-15
 * @Description :  SrpmsVoucher服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsVoucherServiceImpl extends ServiceImpl<SrpmsVoucherMapper, SrpmsVoucher> implements ISrpmsVoucherService {


    @Autowired
    private SrpmsVoucherMapper srpmsVoucherMapper;

    @Autowired
    private ISrpmsProjectService projectService;

    @Override
    public IPage<SrpmsVoucher> selectPage(SrpmsVoucherQueryForm queryForm , UserVo userVo) {
        QueryWrapper<SrpmsVoucher> queryWrapper = new QueryWrapper <SrpmsVoucher>();
        queryWrapper.eq(SrpmsVoucher.USER_ID, userVo.getId());
        queryWrapper.notIn(SrpmsVoucher.VOUCHER_STATUS, VoucherStatusEnums.RECALL.getCode());
        queryWrapper.orderByDesc(SrpmsVoucher.UPDATE_TIME);
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsVoucherMapper.selectPage(new Page<SrpmsVoucher>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsVoucher> selectList(SrpmsVoucherQueryForm queryForm) {
        QueryWrapper<SrpmsVoucher> queryWrapper = new QueryWrapper <SrpmsVoucher>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsVoucherMapper.selectList(queryWrapper);
    }

    @Override
    public void updateStatus(Long voucherId, VoucherStatusEnums statusEnums) {
        SrpmsVoucher voucherEntity = new SrpmsVoucher();
        voucherEntity.setId(voucherId);
        voucherEntity.setVoucherStatus(statusEnums.getCode());
        this.updateById(voucherEntity);
    }

    @Override
    public SrpmsVoucher generateNewVoucher(Long projectId, VoucherTypeEnums typeEnums) {
        SrpmsProject projectEntity = projectService.getById(projectId);
        String deptCode = JSONObject.parseObject(projectEntity.getLeadDept()).getString("deptCode");
        SrpmsVoucher voucherEntity = new SrpmsVoucher();
        if (VoucherTypeEnums.APPLY_BOOK.equals(typeEnums)){
            voucherEntity.setBizNumber(projectEntity.getAplNum());
        } else {
            voucherEntity.setBizNumber(projectEntity.getTasNum());
        }
        voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
        voucherEntity.setStartTime(LocalDateTime.now());
        voucherEntity.setVoucherType(typeEnums.getCode());
        if (projectEntity.getLeadPerson() != null){
            JSONObject leadPerson = JSONObject.parseObject(projectEntity.getLeadPerson());
            voucherEntity.setPersonName(leadPerson.getString("name"));
            voucherEntity.setUserId(leadPerson.getLong("id"));
        }
        voucherEntity.setProjectType(projectEntity.getProjectType());
        voucherEntity.setProjectId(projectId);
        voucherEntity.setVoucherName(projectEntity.getProjectName()+"审核");
        voucherEntity.setLeadDeptCode(deptCode);
        if ("1".equals(projectEntity.getProjectFlag())){
            //横纵向
            voucherEntity.setAuditMode(VoucherAuditModeEnums.SELF_ONLY.getCode());
        }else{
            voucherEntity.setAuditMode(VoucherAuditModeEnums.ALL.getCode());
        }
        this.save(voucherEntity);
        return voucherEntity;
    }

    /**
     * 项目变更
     *
     * @param accept
     * @param typeEnums
     * @param userVo
     * @return
     */
    @Override
    public SrpmsVoucher generateNewVoucherAccept(SrpmsProjectAccept accept, VoucherTypeEnums typeEnums, UserVo userVo) {

        SrpmsVoucher voucherEntity = new SrpmsVoucher();
        String deptCode = projectService.getLeadDeptCodeByProjectId(accept.getProjectId());
        voucherEntity.setBizNumber(accept.getAcceptNum());
        voucherEntity.setVoucherName(typeEnums.getCode());
        voucherEntity.setPersonName(userVo.getName());
        voucherEntity.setUserId(CommonUtil.getLongValue(userVo.getId()));
        voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
        voucherEntity.setStartTime(LocalDateTime.now());
        voucherEntity.setVoucherType(typeEnums.getCode());
        voucherEntity.setProjectId(accept.getId());
        voucherEntity.setProjectType(accept.getProjectType());
        voucherEntity.setProjectFlag(accept.getProjectFlag());
        voucherEntity.setLeadDeptCode(deptCode);
        voucherEntity.setAuditMode(VoucherAuditModeEnums.ALL.getCode());

        this.save(voucherEntity);
        return voucherEntity;
    }

    @Override
    public SrpmsVoucher generateNewVoucherModify(SrpmsProjectUpdate update, VoucherTypeEnums typeEnums, UserVo userVo) {

        SrpmsVoucher voucherEntity = new SrpmsVoucher();
        String deptCode = projectService.getLeadDeptCodeByProjectId(update.getProjectId());
        voucherEntity.setBizNumber(update.getUpdateNumber());
        voucherEntity.setVoucherName(typeEnums.getCode());
        voucherEntity.setPersonName(userVo.getName());
        voucherEntity.setUserId(CommonUtil.getLongValue(userVo.getId()));
        voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
        voucherEntity.setStartTime(LocalDateTime.now());
        voucherEntity.setVoucherType(typeEnums.getCode());
        voucherEntity.setProjectId(update.getId());
        voucherEntity.setProjectType(update.getProjectType());
        voucherEntity.setProjectFlag(update.getProjectFlag());
        voucherEntity.setLeadDeptCode(deptCode);
        String projectType = update.getProjectType();
        if (ProjectCategoryEnums.SCHOOL_STU.getHeader().equals(projectType) || ProjectCategoryEnums.SCHOOL_TEACH.getHeader().equals(projectType)){
            voucherEntity.setAuditMode(VoucherAuditModeEnums.TOP_ONLY.getCode());
        }else{
            voucherEntity.setAuditMode(VoucherAuditModeEnums.ALL.getCode());
        }
        this.save(voucherEntity);
        return voucherEntity;
    }

    /**
     * 创新工程新建年度预算
     *
     * @param budgetYear
     * @param typeEnums
     * @param userVo
     * @return
     */
    @Override
    public SrpmsVoucher generateNewVoucherBudget(SrpmsProjectBudgetYear budgetYear, VoucherTypeEnums typeEnums, UserVo userVo) {

        SrpmsVoucher voucherEntity = new SrpmsVoucher();
        String deptCode = projectService.getLeadDeptCodeByProjectId(budgetYear.getProjectId());
        voucherEntity.setBizNumber(budgetYear.getBudNum());
        voucherEntity.setVoucherName(typeEnums.getCode());
        voucherEntity.setPersonName(userVo.getName());
        voucherEntity.setUserId(CommonUtil.getLongValue(userVo.getId()));
        voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
        voucherEntity.setStartTime(LocalDateTime.now());
        voucherEntity.setVoucherType(typeEnums.getCode());
        voucherEntity.setProjectId(budgetYear.getId());
        voucherEntity.setProjectType(budgetYear.getProjectType());
        voucherEntity.setLeadDeptCode(deptCode);
        voucherEntity.setAuditMode(VoucherAuditModeEnums.ALL.getCode());

        this.save(voucherEntity);
        return voucherEntity;
    }

    /**
     * 根据变更ID查询单据记录
     *
     * @param updateId
     * @return
     */
    @Override
    public SrpmsVoucher getSrpmsVoucherByUpdateId(Long updateId) {
        QueryWrapper<SrpmsVoucher> queryWrapper = new QueryWrapper <SrpmsVoucher>();
        queryWrapper.eq(SrpmsVoucher.PROJECT_ID, updateId);
        queryWrapper.notIn(SrpmsVoucher.VOUCHER_STATUS, VoucherStatusEnums.RECALL.getCode());
        return srpmsVoucherMapper.selectOne(queryWrapper);
    }

    /**
     * @title getSrpmsVoucherByReject
     * @description 查询审批单据是否存在已驳回的数据
     * @auther Mr.Carlos
     * @date 2019.06.05 09:15
     * @param projectId
     */
    @Override
    public SrpmsVoucher getSrpmsVoucherByReject(Long projectId) {

        QueryWrapper<SrpmsVoucher> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(SrpmsVoucher.PROJECT_ID, projectId);
        queryWrapper.eq(SrpmsVoucher.VOUCHER_STATUS, VoucherStatusEnums.REJECT.getCode());
        return srpmsVoucherMapper.selectOne(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsVoucher> getQueryWrapper(QueryWrapper<SrpmsVoucher> queryWrapper,SrpmsVoucherQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsVoucher.getBizNumber())){
            queryWrapper.eq(SrpmsVoucher.BIZ_NUMBER,srpmsVoucher.getBizNumber());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getVoucherName())){
            queryWrapper.eq(SrpmsVoucher.VOUCHER_NAME,srpmsVoucher.getVoucherName());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getVoucherType())){
            queryWrapper.eq(SrpmsVoucher.VOUCHER_TYPE,srpmsVoucher.getVoucherType());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getVoucherStatus())){
            queryWrapper.eq(SrpmsVoucher.VOUCHER_STATUS,srpmsVoucher.getVoucherStatus());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getUserId())){
            queryWrapper.eq(SrpmsVoucher.USER_ID,srpmsVoucher.getUserId());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getPersonName())){
            queryWrapper.eq(SrpmsVoucher.PERSON_NAME,srpmsVoucher.getPersonName());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getStartTime())){
            queryWrapper.eq(SrpmsVoucher.START_TIME,srpmsVoucher.getStartTime());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getEndTime())){
            queryWrapper.eq(SrpmsVoucher.END_TIME,srpmsVoucher.getEndTime());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getCreateTime())){
            queryWrapper.eq(SrpmsVoucher.CREATE_TIME,srpmsVoucher.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getCreateBy())){
            queryWrapper.eq(SrpmsVoucher.CREATE_BY,srpmsVoucher.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getUpdateTime())){
            queryWrapper.eq(SrpmsVoucher.UPDATE_TIME,srpmsVoucher.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsVoucher.getUpdateBy())){
            queryWrapper.eq(SrpmsVoucher.UPDATE_BY,srpmsVoucher.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

