package com.deloitte.services.fssc.business.ppc.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectConfirmationForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectConfirmationVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractInfo;
import com.deloitte.services.fssc.business.ppc.biz.ProjectConfirmationAdpter;
import com.deloitte.services.fssc.business.ppc.entity.ProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.service.IProjectConfirmationService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "projectConfirmationBizImpl")
@Slf4j
public class ProjectConfirmationBizImpl extends
        ProjectConfirmationAdpter<ProjectConfirmation,ProjectConfirmationForm, ProjectConfirmationVo>{
    @Autowired
    protected IProjectConfirmationService projectConfirmationService;

    @Transactional
    @Override
    public ProjectConfirmation saveOrUpdate(ProjectConfirmationForm projectConfirmationForm){
        //验证单据是否存在
        FsscCommonUtil.valiHasData(projectConfirmationForm.getId()
                ,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());

        ProjectConfirmation projectConfirmation =
                new BeanUtils<ProjectConfirmation>().copyObj(projectConfirmationForm, ProjectConfirmation.class);
        Long inComeMainTypeId = projectConfirmation.getInComeMainTypeId();
        if(inComeMainTypeId!=null){
            BaseIncomeMainType mainType = baseIncomeMainTypeService.getById(inComeMainTypeId);
            if(mainType!=null){
                projectConfirmation.setPaymentType(mainType.getParentName());
                projectConfirmation.setInComeMainTypeName(mainType.getName());
                projectConfirmation.setInComeMainTypeCode(mainType.getCode());
            }
        }
        Long projectId = projectConfirmation.getProjectId();
        if(projectId!=null){
            BudgetProject project = budgetProjectService.getById(projectId);
            if(project!=null){
                projectConfirmation.setProjectCode(project.getProjectCode());
                projectConfirmation.setProjectName(project.getProjectName());
                projectConfirmation.setProjectUnitCode(project.getResponsibleUnitCode());
                projectConfirmation.setProjectUnitName(project.getResponsibleUnitName());
                projectConfirmation.setFsscCode(project.getFsscCode());
                projectConfirmation.setAccountCode(project.getAccountCode());
            }
        }

        boolean saveOrUpdate = projectConfirmationService.saveOrUpdate(projectConfirmation);
        AssertUtils.asserts(saveOrUpdate, FsscErrorType.SAVE_FAIL);

        //保存项目款项确认单款项信息
        saveOrUpdateProjectPaymentLineDetai(projectConfirmationForm.getProjectPaymentLineDetaiForms()
        ,projectConfirmation.getId(), FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());
        //收款明细
        saveOrUpdateProjectRecieveDetail(projectConfirmationForm.getProjectRecieveDetailForms()
        ,projectConfirmation.getId(), FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());
        //开票明细
        saveOrUpdateProjectBilingInfo(projectConfirmationForm.getProjectBilingInfoForms()
        ,projectConfirmation.getId(),FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());
        //合同明细
        saveOrUpdateContractInformation(projectConfirmationForm.getContractInformationForms()
        ,projectConfirmation.getId(),FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());

        saveOrUpdateFiles(projectConfirmationForm.getFileIds()
                ,projectConfirmation.getId(),FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());

        return projectConfirmationService.getById(projectConfirmation.getId());
    }



    @Override
    @Transactional
    public void deleteById(Long documentId) {
        ProjectConfirmation confirmation = projectConfirmationService.getById(documentId);
        AssertUtils.asserts(confirmation!=null,FsscErrorType.DOCUMENT_NOT_FIND);
        String documentStatus = confirmation.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus)||
                FsscEums.RECALLED.getValue().equals(documentStatus)||
                FsscEums.REJECTED.getValue().equals(documentStatus),FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        projectConfirmationService.removeById(documentId);
        deleteLines(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());
    }

    @Override
    public ProjectConfirmationVo getById(Long documentId) {
        ProjectConfirmation confirmation = projectConfirmationService.getById(documentId);
        ProjectConfirmationVo projectConfirmationVo =
                new BeanUtils<ProjectConfirmationVo>().copyObj(confirmation, ProjectConfirmationVo.class);
        Long contractId = confirmation.getContractId();
        String contractType="";
        if(contractId!=null){
            QueryWrapper<BaseContractInfo> infoQueryWrapper=new QueryWrapper<>();
            infoQueryWrapper.eq(BaseContractInfo.CONTRACT_ID,contractId);
            try {
                BaseContractInfo one = contractInfoService.getOne(infoQueryWrapper);
                if(one!=null){
                    projectConfirmationVo.setContractName(one.getContractName());
                    contractType=one.getContractType();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        projectConfirmationVo.setProjectBilingInfoVos(getProjectBilingInfoVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        projectConfirmationVo.setContractInformationVos
                (getContractInformationVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue(),contractType));
        projectConfirmationVo.setProjectRecieveDetailVos(getProjectRecieveDetailVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        projectConfirmationVo.setProjectPaymentLineDetaiVos(getProjectPaymentLineDetaiVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        try {
            projectConfirmationVo.setBpmProcessTaskVos(bpmTaskBiz.findHistory(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        }catch (Exception e){
            log.error("error:{}"+e.getMessage());
        }
        return projectConfirmationVo;
    }


}
