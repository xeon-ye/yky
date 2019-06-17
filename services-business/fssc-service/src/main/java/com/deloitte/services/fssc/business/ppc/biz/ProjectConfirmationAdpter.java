package com.deloitte.services.fssc.business.ppc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractPlanLineQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractPlanLineVo;
import com.deloitte.platform.api.fssc.ppc.vo.*;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractInfoService;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractPlanLineService;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.ppc.entity.ContractInformation;
import com.deloitte.services.fssc.business.ppc.entity.ProjectBilingInfo;
import com.deloitte.services.fssc.business.ppc.entity.ProjectPaymentLineDetai;
import com.deloitte.services.fssc.business.ppc.entity.ProjectRecieveDetail;
import com.deloitte.services.fssc.business.ppc.service.IContractInformationService;
import com.deloitte.services.fssc.business.ppc.service.IProjectBilingInfoService;
import com.deloitte.services.fssc.business.ppc.service.IProjectPaymentLineDetaiService;
import com.deloitte.services.fssc.business.ppc.service.IProjectRecieveDetailService;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public abstract class ProjectConfirmationAdpter<T, Z, K> implements ProjectConfirmationBiz<T, Z, K> {


    @Autowired
    protected IContractInformationService contractInformationService;
    @Autowired
    protected IProjectBilingInfoService projectBilingInfoService;
    @Autowired
    protected IProjectRecieveDetailService projectRecieveDetailService;
    @Autowired
    protected IProjectPaymentLineDetaiService projectPaymentLineDetaiService;
    @Autowired
    protected BpmTaskBiz bpmTaskBiz;
    @Autowired
    private IRecieveLineMsgService recieveLineMsgService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    public IRecievePaymentService recievePaymentService;
    @Autowired
    protected IBaseContractInfoService contractInfoService;
    @Autowired
    private IBaseContractPlanLineService contractPlanLineService;

    @Autowired
    private IBankUnitInfoService bankUnitInfoService;

    @Autowired
    private IBaseIncomeSubTypeService baseIncomeSubTypeService;

    @Autowired
    protected IBaseIncomeMainTypeService baseIncomeMainTypeService;

    @Autowired
    private IFileService fileService;

    @Autowired
    protected IBudgetProjectService budgetProjectService;
    /**
     * 删除所有行明细
     *
     * @param documentId
     * @param documentType
     */
    protected void deleteLines(Long documentId, String documentType) {
        //删除合同信息
        QueryWrapper<ContractInformation> contractInformationQueryWrapper = new QueryWrapper<>();
        contractInformationQueryWrapper.eq(ContractInformation.DOCUMENT_ID, documentId)
                .eq(ContractInformation.DOCUMENT_TYPE, documentType);
        contractInformationService.remove(contractInformationQueryWrapper);
        //删除开票信息
        QueryWrapper<ProjectBilingInfo> projectBilingInfoQueryWrapper = new QueryWrapper<>();
        projectBilingInfoQueryWrapper.eq(ProjectBilingInfo.DOCUMENT_ID, documentId)
                .eq(ProjectBilingInfo.DOCUMENT_TYPE, documentType);
        projectBilingInfoService.remove(projectBilingInfoQueryWrapper);
        //删除收款明细
        QueryWrapper<ProjectRecieveDetail> projectRecieveDetailQueryWrapper = new QueryWrapper<>();
        projectRecieveDetailQueryWrapper.eq(ProjectRecieveDetail.DOCUMENT_ID, documentId)
                .eq(ProjectRecieveDetail.DOCUMENT_TYPE, documentType);
        projectRecieveDetailService.remove(projectRecieveDetailQueryWrapper);
        //删除项目款项确认单款项信息
        QueryWrapper<ProjectPaymentLineDetai> lineDetaiQueryWrapper = new QueryWrapper<>();
        lineDetaiQueryWrapper.eq(ProjectPaymentLineDetai.DOCUMENT_ID, documentId)
                .eq(ProjectPaymentLineDetai.DOCUMENT_TYPE, documentType);
        projectPaymentLineDetaiService.remove(lineDetaiQueryWrapper);


    }

    /**
     * 查询合同信息
     * @param documentId
     * @param documentType
     * @return
     */
    protected List<ContractInformationVo> getContractInformationVos(Long documentId, String documentType,String contractType){
        QueryWrapper<ContractInformation> contractInformationQueryWrapper = new QueryWrapper<>();
        contractInformationQueryWrapper.eq(ContractInformation.DOCUMENT_ID, documentId)
                .eq(ContractInformation.DOCUMENT_TYPE, documentType);
        List<ContractInformation> informations = contractInformationService.list(contractInformationQueryWrapper);
        List<ContractInformationVo> contractInformationVos = new BeanUtils<ContractInformationVo>().copyObjs(informations, ContractInformationVo.class);
        for (ContractInformationVo vo:contractInformationVos){
            Long contractDocumentId = vo.getContractId();
            if(contractDocumentId!=null){
                BaseContractPlanLineQueryForm queryForm=new BaseContractPlanLineQueryForm();
                queryForm.setContractId(contractDocumentId);
                IPage<BaseContractPlanLineVo> baseContractPlanLineVoIPage
                        = contractPlanLineService.selectPage(queryForm);
                List<BaseContractPlanLineVo> records = baseContractPlanLineVoIPage.getRecords();
                if(CollectionUtils.isNotEmpty(records)){
                    BaseContractPlanLineVo baseContractPlanLineVo = records.get(0);
                    org.springframework.beans.BeanUtils.copyProperties(baseContractPlanLineVo,vo);
                }
            }
            vo.setContractType(contractType);
        }
        return contractInformationVos;
    }

    /**
     * 开票信息
     * @param documentId
     * @param documentType
     * @return
     */
    protected List<ProjectBilingInfoVo> getProjectBilingInfoVos(Long documentId, String documentType){
        QueryWrapper<ProjectBilingInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(ProjectBilingInfo.DOCUMENT_ID, documentId)
                .eq(ProjectBilingInfo.DOCUMENT_TYPE, documentType);
        List<ProjectBilingInfo> informations = projectBilingInfoService.list(wrapper);
        return new BeanUtils<ProjectBilingInfoVo>().copyObjs(informations,ProjectBilingInfoVo.class);
    }




    /**
     * 收款信息
     * @param documentId
     * @param documentType
     * @return
     */
    protected List<ProjectRecieveDetailVo> getProjectRecieveDetailVos(Long documentId, String documentType){
        QueryWrapper<ProjectRecieveDetail> wrapper = new QueryWrapper<>();
        wrapper.eq(ProjectRecieveDetail.DOCUMENT_ID, documentId)
                .eq(ProjectRecieveDetail.DOCUMENT_TYPE, documentType);
        List<ProjectRecieveDetail> informations = projectRecieveDetailService.list(wrapper);
        List<ProjectRecieveDetailVo> projectRecieveDetailVos = new BeanUtils<ProjectRecieveDetailVo>().copyObjs(informations, ProjectRecieveDetailVo.class);
        for (ProjectRecieveDetailVo detailVo:projectRecieveDetailVos){
            Long connectRecieveLineId = detailVo.getConnectRecieveLineId();
            if(connectRecieveLineId!=null){
                RecieveLineMsg msg = recieveLineMsgService.getById(connectRecieveLineId);
                if(msg!=null){
                    detailVo.setRecieveAmount(msg.getAmountCollected());
                    detailVo.setRecieveBankAccount(msg.getRecieveBankAccount());
                    QueryWrapper<BankUnitInfo> bankUnitInfoQueryWrapper=new QueryWrapper<>();
                    if(StringUtil.isNotEmpty(msg.getRecieveBankAccount())){
                        bankUnitInfoQueryWrapper.eq(BankUnitInfo.BANK_ACCOUNT,msg.getRecieveBankAccount());
                        try {
                            BankUnitInfo bankUnitInfo = bankUnitInfoService.getOne(bankUnitInfoQueryWrapper);
                            if(bankUnitInfo!=null){
                                Map<String, String> unitType =
                                        commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.BANK_TYPE.getValue()));
                                detailVo.setRecieveBankType(unitType.get(bankUnitInfo.getBankType()));
                                detailVo.setRecieveBankAccountName(bankUnitInfo.getBankAccountName());
                            }
                        }catch (Exception e){
                            log.error(e.getMessage());
                        }
                    }
                    detailVo.setRecieveBankName(msg.getRecieveBankName());
                    detailVo.setRecieveTime(msg.getRecieveTime());
                    detailVo.setPayBankAccount(msg.getPayBankAccount());
                    detailVo.setRecieveUnitName(msg.getRecieveUnitName());
                    detailVo.setPayBankName(msg.getRecieveBankName());
                    detailVo.setPayUnitName(msg.getRecieveUnitName());

                }

            }
            Long inComeSubTypeId = detailVo.getInComeSubTypeId();
            if(inComeSubTypeId!=null){
                BaseIncomeSubType type = baseIncomeSubTypeService.getById(inComeSubTypeId);
                if(type!=null){
                    detailVo.setInComeSubTypeName(type.getName());
                    detailVo.setInComeSubTypeCode(type.getCode());
                }
            }
        }
        return projectRecieveDetailVos;
    }

    /**
     * 行明细
     * @param documentId
     * @param documentType
     * @return
     */
    protected List<ProjectPaymentLineDetaiVo> getProjectPaymentLineDetaiVos(Long documentId, String documentType){
        QueryWrapper<ProjectPaymentLineDetai> wrapper = new QueryWrapper<>();
        wrapper.eq(ProjectPaymentLineDetai.DOCUMENT_ID, documentId)
                .eq(ProjectPaymentLineDetai.DOCUMENT_TYPE, documentType);
        List<ProjectPaymentLineDetai> informations = projectPaymentLineDetaiService.list(wrapper);
        List<ProjectPaymentLineDetaiVo> projectPaymentLineDetaiVos = new BeanUtils<ProjectPaymentLineDetaiVo>().copyObjs(informations, ProjectPaymentLineDetaiVo.class);
        for (ProjectPaymentLineDetaiVo detaiVo:projectPaymentLineDetaiVos){
            Long inComeSubTypeId = detaiVo.getInComeSubTypeId();
            if(inComeSubTypeId!=null){
                BaseIncomeSubType incomeSubType = baseIncomeSubTypeService.getById(inComeSubTypeId);
                if(incomeSubType!=null){
                    detaiVo.setInComeSubTypeCode(incomeSubType.getCode());
                    detaiVo.setInComeSubTypeName(incomeSubType.getName());
                }
            }
        }
        return projectPaymentLineDetaiVos;
    }
    /**
     * 保存合同信息
     *
     * @param contractInformationForms
     * @param documentId
     * @param documentType
     */
    protected void saveOrUpdateContractInformation(List<ContractInformationForm> contractInformationForms
            , Long documentId, String documentType) {
        if (CollectionUtils.isNotEmpty(contractInformationForms)) {
            //获取id集合并去重
            List<Long> longList = contractInformationForms.stream().map(c -> c.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));

            QueryWrapper<ContractInformation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ContractInformation.DOCUMENT_ID, documentId)
                    .eq(ContractInformation.DOCUMENT_TYPE, documentType);
            if (CollectionUtils.isNotEmpty(longList)) {
                queryWrapper.notIn(ContractInformation.ID, longList);
            }
            contractInformationService.remove(queryWrapper);
            List<ContractInformation> contractInformations = new BeanUtils<ContractInformation>()
                    .copyObjs(contractInformationForms, ContractInformation.class);
            for (ContractInformation information : contractInformations) {
                information.setDocumentId(documentId);
                information.setDocumentType(documentType);
            }
            contractInformationService.saveOrUpdateBatch(contractInformations);
        }
    }

    /**
     * 保存开票信息
     *
     * @param projectBilingInfoForms
     * @param documentId
     * @param documentType
     */
    protected void saveOrUpdateProjectBilingInfo(List<ProjectBilingInfoForm> projectBilingInfoForms
            , Long documentId, String documentType) {
        if (CollectionUtils.isNotEmpty(projectBilingInfoForms)) {
            //获取id集合并去重
            List<Long> longList = projectBilingInfoForms.stream().map(c -> c.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));

            QueryWrapper<ProjectBilingInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ProjectBilingInfo.DOCUMENT_ID, documentId)
                    .eq(ProjectBilingInfo.DOCUMENT_TYPE, documentType);
            if (CollectionUtils.isNotEmpty(longList)) {
                queryWrapper.notIn(ProjectBilingInfo.ID, longList);
            }
            projectBilingInfoService.remove(queryWrapper);
            List<ProjectBilingInfo> bilingInfos = new BeanUtils<ProjectBilingInfo>()
                    .copyObjs(projectBilingInfoForms, ProjectBilingInfo.class);
            for (ProjectBilingInfo information : bilingInfos) {
                information.setDocumentId(documentId);
                information.setDocumentType(documentType);
            }
            projectBilingInfoService.saveOrUpdateBatch(bilingInfos);
        }
    }

    /**
     * 保存收款明细
     *
     * @param projectRecieveDetailForms
     * @param documentId
     * @param documentType
     */
    protected void saveOrUpdateProjectRecieveDetail(List<ProjectRecieveDetailForm> projectRecieveDetailForms
            , Long documentId, String documentType) {

        if (CollectionUtils.isNotEmpty(projectRecieveDetailForms)) {
            //获取id集合并去重
            List<Long> longList = projectRecieveDetailForms.stream().map(c -> c.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));

            QueryWrapper<ProjectRecieveDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ProjectRecieveDetail.DOCUMENT_ID, documentId)
                    .eq(ProjectRecieveDetail.DOCUMENT_TYPE, documentType);
            if (CollectionUtils.isNotEmpty(longList)) {
                queryWrapper.notIn(ProjectRecieveDetail.ID, longList);
            }
            projectRecieveDetailService.remove(queryWrapper);
            List<ProjectRecieveDetail> recieveDetails = new BeanUtils<ProjectRecieveDetail>()
                    .copyObjs(projectRecieveDetailForms, ProjectRecieveDetail.class);
            for (ProjectRecieveDetail information : recieveDetails) {
                information.setDocumentId(documentId);
                information.setDocumentType(documentType);
                AssertUtils.asserts(information.getConnectRecieveLineId()!=null, FsscErrorType.RECIEVE_LINE_MUST_BE_NOT_NULL);
                RecieveLineMsg recieveLineMsg=recieveLineMsgService.getById(information.getConnectRecieveLineId());
                if(recieveLineMsg!=null){
                    recieveLineMsg.setWhetherLock("Y");
                    recieveLineMsgService.saveOrUpdate(recieveLineMsg);
                    information.setConnectDocumentId(recieveLineMsg.getDocumentId());
                    information.setConnectDocumentType(recieveLineMsg.getDocumentType());
                    information.setRecieveBankUnitId(recieveLineMsg.getRecieveBankUnitId());
                    information.setBankSubjectCode(recieveLineMsg.getBankSubjectCode());
                    information.setBudgetBankSubjectCode(recieveLineMsg.getBudgetBankSubjectCode());
                }
                Long inComeSubTypeId = information.getInComeSubTypeId();
                if(inComeSubTypeId!=null){
                    BaseIncomeSubType type = baseIncomeSubTypeService.getById(inComeSubTypeId);
                    if(type!=null){
                        information.setInComeSubTypeName(type.getName());
                        information.setInComeSubTypeCode(type.getCode());
                    }
                }


            }

            projectRecieveDetailService.saveOrUpdateBatch(recieveDetails);
        }
    }



    /**
     * 保存项目款项确认单款项信息
     *
     * @param projectPaymentLineDetaiForms
     * @param documentId
     * @param documentType
     */
    protected void saveOrUpdateProjectPaymentLineDetai(List<ProjectPaymentLineDetaiForm> projectPaymentLineDetaiForms
            , Long documentId, String documentType) {

        if (CollectionUtils.isNotEmpty(projectPaymentLineDetaiForms)) {
            //获取id集合并去重
            List<Long> longList = projectPaymentLineDetaiForms.stream().map(c -> c.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));

            QueryWrapper<ProjectPaymentLineDetai> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ProjectPaymentLineDetai.DOCUMENT_ID, documentId)
                    .eq(ProjectPaymentLineDetai.DOCUMENT_TYPE, documentType);
            if (CollectionUtils.isNotEmpty(longList)) {
                queryWrapper.notIn(ProjectPaymentLineDetai.ID, longList);
            }
            projectPaymentLineDetaiService.remove(queryWrapper);
            List<ProjectPaymentLineDetai> projectPaymentLineDetais = new BeanUtils<ProjectPaymentLineDetai>()
                    .copyObjs(projectPaymentLineDetaiForms, ProjectPaymentLineDetai.class);
            for (ProjectPaymentLineDetai information : projectPaymentLineDetais) {
                information.setDocumentId(documentId);
                information.setDocumentType(documentType);
                Long adverseUnitId = information.getAdverseUnitId();
                if(adverseUnitId!=null){
                    BankUnitInfoQueryForm queryForm=new BankUnitInfoQueryForm();
                    queryForm.setUnitId(adverseUnitId);
                    IPage<BankUnitInfoVo> bankUnitInfoVoIPage =
                            bankUnitInfoService.selectPage(queryForm);
                    List<BankUnitInfoVo> records = bankUnitInfoVoIPage.getRecords();
                    if(CollectionUtils.isNotEmpty(records)){
                        BankUnitInfoVo bankUnitInfoVo = records.get(0);
                        information.setAdverseUnitName(bankUnitInfoVo.getUnitName());
                        information.setAdverseBankAccount(bankUnitInfoVo.getBankAccount());
                    }
                }


            }
            projectPaymentLineDetaiService.saveOrUpdateBatch(projectPaymentLineDetais);
        }
    }



    protected void saveOrUpdateFiles(List<Long> fileIds, Long id, String documentType) {
        //文件列表保存

        if(fileIds!=null){
            fileIds.removeAll(Collections.singleton(null));
        }
        if(CollectionUtils.isNotEmpty(fileIds)){
            QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID,id)
                    .eq(File.DOCUMENT_TYPE, documentType)
                    .notIn(File.ID,fileIds);
            fileService.remove(fileQueryWrapper);

            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files),
                    FsscErrorType.FILE_IS_NULL);
            files.stream().forEach(ka->ka.setDocumentId(id));
            fileService.saveOrUpdateBatch(files);
        }

    }

}
