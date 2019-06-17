package com.deloitte.services.fssc.business.ppc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.ppc.param.ProjectConfirmationQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.business.ppc.entity.ProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.mapper.ProjectConfirmationMapper;
import com.deloitte.services.fssc.business.ppc.service.IProjectConfirmationService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectConfirmation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProjectConfirmationServiceImpl extends ServiceImpl<ProjectConfirmationMapper, ProjectConfirmation> implements IProjectConfirmationService {


    @Autowired
    private ProjectConfirmationMapper projectConfirmationMapper;

    @Autowired
    private IBaseIncomeMainTypeService iBaseIncomeMainTypeService;

    @Override
    public IPage<ProjectConfirmation> selectPage(ProjectConfirmationQueryForm queryForm) {
        QueryWrapper<ProjectConfirmation> queryWrapper = new QueryWrapper<ProjectConfirmation>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()), "DOCUMENT_NUM", queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()), "DOCUMENT_STATUS", queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getRecieveStatus()), "RECIEVE_STATUS", queryForm.getRecieveStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()), "CREATE_USER_NAME", queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()), "DEPT_ID", queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()), "PROJECT_ID", queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getInComeMainTypeId()), "IN_COME_MAIN_TYPE_ID", queryForm.getInComeMainTypeId());
        queryWrapper.ge(queryForm.getMoneyStart()!=null, "TOTAL_AMOUNT", queryForm.getMoneyStart());
        queryWrapper.le(queryForm.getMoneyEnd()!=null, "TOTAL_AMOUNT", queryForm.getMoneyEnd());
        queryWrapper.ge(queryForm.getTimeStart()!=null, "CREATE_TIME", queryForm.getTimeStart());
        queryWrapper.le(queryForm.getTimeEnd()!=null, "CREATE_TIME", queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPaymentType()),ProjectConfirmation.PAYMENT_TYPE,queryForm.getPaymentType());
        queryWrapper.orderByDesc("UPDATE_TIME");
        IPage<ProjectConfirmation> projectConfirmationIPage =
                projectConfirmationMapper.selectPage(new Page<ProjectConfirmation>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        for (ProjectConfirmation confirmation:projectConfirmationIPage.getRecords()){
            Long inComeMainTypeId = confirmation.getInComeMainTypeId();
            if(inComeMainTypeId!=null){
                BaseIncomeMainType incomeMainTypeServiceById = iBaseIncomeMainTypeService.getById(inComeMainTypeId);
                if(incomeMainTypeServiceById!=null){
                    confirmation.setInComeMainTypeCode(incomeMainTypeServiceById.getCode());
                    confirmation.setInComeMainTypeName(incomeMainTypeServiceById.getName());
                }
            }
        }

        return projectConfirmationIPage;
    }

    @Override
    public List<ProjectConfirmation> selectList(ProjectConfirmationQueryForm queryForm) {
        QueryWrapper<ProjectConfirmation> queryWrapper = new QueryWrapper<ProjectConfirmation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return projectConfirmationMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByReceivePayment(List<Long> incomeMainTypeIds) {
        QueryWrapper<ProjectConfirmation> countWrapper = new QueryWrapper<>();
        countWrapper.in(ProjectConfirmation.IN_COME_MAIN_TYPE_ID,incomeMainTypeIds);
        return this.count(countWrapper) > 0;
    }

}

