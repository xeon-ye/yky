package com.deloitte.services.fssc.business.ppc.biz.impl;

import com.deloitte.platform.api.fssc.ppc.vo.NoProjectConfirmationForm;
import com.deloitte.platform.api.fssc.ppc.vo.NoProjectConfirmationVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.ppc.biz.ProjectConfirmationAdpter;
import com.deloitte.services.fssc.business.ppc.entity.NoProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.service.INoProjectConfirmationService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "noProjectConfirmationBizImpl")
public class NoProjectConfirmationBizImpl extends ProjectConfirmationAdpter<NoProjectConfirmation,
        NoProjectConfirmationForm, NoProjectConfirmationVo> {

    @Autowired
    private INoProjectConfirmationService projectConfirmationService;

    @Transactional
    @Override
    public NoProjectConfirmation saveOrUpdate(NoProjectConfirmationForm projectConfirmationForm) {
        //todo 生成单据编号
        NoProjectConfirmation projectConfirmation =
                new BeanUtils<NoProjectConfirmation>().copyObj(projectConfirmationForm, NoProjectConfirmation.class);
        boolean saveOrUpdate = projectConfirmationService.saveOrUpdate(projectConfirmation);
        AssertUtils.asserts(saveOrUpdate, FsscErrorType.SAVE_FAIL);

        //保存项目款项确认单款项信息
        saveOrUpdateProjectPaymentLineDetai(projectConfirmationForm.getProjectPaymentLineDetaiForms()
                ,projectConfirmation.getId(), FsscTableNameEums.PPC_NO_PROJECT_CONFIRMATION.getValue());
        //收款明细
        saveOrUpdateProjectRecieveDetail(projectConfirmationForm.getProjectRecieveDetailForms()
                ,projectConfirmation.getId(), FsscTableNameEums.PPC_NO_PROJECT_CONFIRMATION.getValue());
        //开票明细
        saveOrUpdateProjectBilingInfo(projectConfirmationForm.getProjectBilingInfoForms()
                ,projectConfirmation.getId(),FsscTableNameEums.PPC_NO_PROJECT_CONFIRMATION.getValue());
        //合同明细
        saveOrUpdateContractInformation(projectConfirmationForm.getContractInformationForms()
                ,projectConfirmation.getId(),FsscTableNameEums.PPC_NO_PROJECT_CONFIRMATION.getValue());


        return projectConfirmationService.getById(projectConfirmation.getId());
    }

    @Override
    public void deleteById(Long documentId) {
        NoProjectConfirmation confirmation = projectConfirmationService.getById(documentId);
        AssertUtils.asserts(confirmation!=null,FsscErrorType.DOCUMENT_NOT_FIND);
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(confirmation.getDocumentStatus()),
                FsscErrorType.ONLY_DELETE_NEW);
        projectConfirmationService.removeById(documentId);
        deleteLines(documentId,FsscTableNameEums.PPC_NO_PROJECT_CONFIRMATION.getValue());
    }

    @Override
    public NoProjectConfirmationVo getById(Long documentId) {
        NoProjectConfirmation confirmation = projectConfirmationService.getById(documentId);
        NoProjectConfirmationVo projectConfirmationVo =
                new BeanUtils<NoProjectConfirmationVo>().copyObj(confirmation, NoProjectConfirmationVo.class);
        projectConfirmationVo.setProjectBilingInfoVos(getProjectBilingInfoVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        //projectConfirmationVo.setContractInformationVos(getContractInformationVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        projectConfirmationVo.setProjectRecieveDetailVos(getProjectRecieveDetailVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        projectConfirmationVo.setProjectPaymentLineDetaiVos(getProjectPaymentLineDetaiVos(documentId,FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        return projectConfirmationVo;
    }
}
