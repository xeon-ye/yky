package com.deloitte.services.fssc.engine.dockingEbs.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessMapper;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificRecive;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.deloitte.services.fssc.business.carryforward.service.IDssScientificReciveService;
import com.deloitte.services.fssc.business.carryforward.service.IIncomeOfCarryForwardService;
import com.deloitte.services.fssc.business.ppc.entity.ProjectRecieveDetail;
import com.deloitte.services.fssc.business.ppc.service.IProjectRecieveDetailService;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementService;
import com.deloitte.services.fssc.engine.automatic.service.IAvChartOfAccountService;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvAccountDocSeqNumFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvErrorMessageFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvLedgerFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvLedgerReturnEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.CrossValidationRuleReturnEbs;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherHeadService;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherLineService;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : chenx
 * @Date : Create in 2019-04-10
 * @Description :   EBS推送会计凭证处理结果数据到财务系统
 * @Modified :
 */
@Api(tags = "EBS推送会计凭证处理结果数据到财务系统")
@Slf4j
@RestController
@RequestMapping("/fssc/dockingEbs/docSeqNum")
public class AvAccountDocSeqNumFromEbsController {
    @Autowired
    private IAvAccountElementService avAccountElementService;
    @Autowired
    private IAvManualVoucherHeadService avManualVoucherHeadService;
    @Autowired
    private IAvManualVoucherLineService avManualVoucherLineService;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private IIncomeOfCarryForwardService incomeOfCarryForwardService;
    @Autowired
    private IProjectRecieveDetailService  projectRecieveDetailService;
    @Autowired
    private IBudgetProjectService budgetProjectService;
    @Autowired
    private IDssScientificReciveService  dssScientificReciveService;
    @Autowired
    private IRecieveLineMsgService recieveLineMsgService;

    @ApiOperation(value = "EBS推送会计凭证处理结果数据到财务系统", notes = "EBS推送会计凭证处理结果数据到财务系统")
    @PostMapping(value = "/getListFromEbs")
    public Result searchBaseElement(@Valid @RequestBody @ApiParam(name="jsonData",value="获取得到EBS数据",required=true) JSONArray jsonData) {
        //  List<AvBaseFromEbs>  list= JSON.parseArray(jsonData, AvBaseFromEbs.class);
        List<CrossValidationRuleReturnEbs> ebsList = new ArrayList<>();
        try{
            List<AvAccountDocSeqNumFromEbs> list = (ArrayList<AvAccountDocSeqNumFromEbs>) JSONArray.parseArray(jsonData.toString(),AvAccountDocSeqNumFromEbs.class);
            if(list.size()>0){
                Map<String,String> map = new HashMap<>();

                for(AvAccountDocSeqNumFromEbs p:list){
                    AvManualVoucherHead entity = new AvManualVoucherHead();
                    entity.setId(p.getSOURCE_HEAD_ID());
                    try{
                        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq(AvManualVoucherHead.ID,p.getSOURCE_HEAD_ID());
                        List<AvManualVoucherHead> headList = avManualVoucherHeadService.list(queryWrapper);
                        if("Y".equals(p.getIMPORT_FLAG())){//会计凭证导入成功

                            if(headList.size()>0){
                                entity.setAccountStatus("Y");
                                entity.setPostStatus("Y");
                                entity.setDocSeqNum(p.getDOC_SEQ_NUM());
                                entity.setPostedDate(LocalDateTime.now());
                                entity.setRequestId(p.getREQUEST_ID());
                                avManualVoucherHeadService.updateById(entity);
                                AvManualVoucherHead entity1 =headList.get(0);
                                if(entity1.getFromRecurringHeaderId()!=null){//对于冲销单据
                                    AvManualVoucherHead oldEntity = new AvManualVoucherHead();
                                    oldEntity.setDocumentStatus(FsscEums.HAS_CHARGE_AGAINST.getValue());
                                    oldEntity.setId(entity1.getFromRecurringHeaderId());//原被冲销单据
                                    avManualVoucherHeadService.updateById(oldEntity);
                                    entity.setDocumentStatus(FsscEums.HAS_ACCOUTED.getValue());//冲销单据
                                    avManualVoucherHeadService.updateById(entity);//更新凭证状态
                                    if(!entity1.getDocumentType().equals(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue())){//更新原业务单据状态
                                        processMapper.updateDocumentStatus(entity1.getAccountResourceTypeId(),entity1.getDocumentType(), FsscEums.HAS_CHARGE_AGAINST.getValue());
                                    }
                                    AvManualVoucherHead headEntity = avManualVoucherHeadService.getById(entity1.getFromRecurringHeaderId());
                                    if(StringUtils.isNotEmpty(headEntity.getCarrayStatus())){//是否结转，如果结转需要取消掉关联关系
                                        QueryWrapper<IncomeOfCarryForward> queryWrapperForward = new QueryWrapper<>();
                                        queryWrapperForward.eq(IncomeOfCarryForward.JE_HEADER_ID,headEntity.getId());
                                        List<IncomeOfCarryForward> incomeList = incomeOfCarryForwardService.list(queryWrapperForward);
                                        for(IncomeOfCarryForward n:incomeList){
                                            n.setJeHeaderId(null);
                                            n.setStatus("N");
                                            incomeOfCarryForwardService.saveOrUpdate(n);
                                        }
                                    }
                                }else{//对于正常的入账
                                    if(!entity1.getDocumentType().equals(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue())) {//更新原业务单据状态
                                        processMapper.updateDocumentStatus(entity1.getAccountResourceTypeId(),entity1.getDocumentType(), FsscEums.HAS_ACCOUTED.getValue());
                                    }
                                    AvManualVoucherHead oldEntity = new AvManualVoucherHead();
                                    oldEntity.setDocumentStatus(FsscEums.HAS_ACCOUTED.getValue());
                                    oldEntity.setId(entity1.getId());
                                    avManualVoucherHeadService.saveOrUpdate(oldEntity);
                                    //项目经费到位
                                    if(entity1.getDocumentType().equals(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue())||entity1.getDocumentType().equals(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue())) {//款项确认单
                                        moneyInPlace(entity1);
                                    }
                                }

                            }
                        }else if("E".equals(p.getIMPORT_FLAG())){
                            for(AvErrorMessageFromEbs n:p.getLINES()){
                                AvManualVoucherLine lineEntity = new AvManualVoucherLine();
                                lineEntity.setErrorMessage(n.getERROR_MESSAGE());
                                lineEntity.setId(n.getSOURCE_LINE_ID());
                                avManualVoucherLineService.updateById(lineEntity);
                                AvManualVoucherHead entity1 =headList.get(0);
                                entity1.setDocumentStatus(FsscEums.ACCOUNT_FAILD.getValue());//冲销单据
                                avManualVoucherHeadService.updateById(entity1);//更新凭证状态
                                if(!entity1.getDocumentType().equals(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue())){//更新报账单据状态失败
                                    processMapper.updateDocumentStatus(entity1.getAccountResourceTypeId(),entity1.getDocumentType(), FsscEums.ACCOUNT_FAILD.getValue());
                                }
                            }
                        }
                    }catch (Exception e){
                        log.error("eb回传送数据报错"+e);
                        CrossValidationRuleReturnEbs ebsEntity = new CrossValidationRuleReturnEbs();
                        ebsEntity.setSourceHeadId(p.getSOURCE_HEAD_ID().toString());
                        ebsEntity.setErrorMessage(e.toString());
                        ebsEntity.setImportFlag("N");
                        ebsList.add(ebsEntity);
                        continue;
                    }


                }
            }
        }catch (Exception e){
            log.error("EBS传送数据报错"+e);

        }
        if(ebsList.size()>0){
            return Result.fail(ebsList);
        }
        return  Result.success();
    }

    private  void moneyInPlace( AvManualVoucherHead entity1){
        Map<String,Object> documentEntity = avManualVoucherLineService.selectMapLimitOne(entity1.getDocumentType(),entity1.getAccountResourceTypeId());
        if(entity1.getDocumentType().equals(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue())){//款项确认单
            if(documentEntity.containsKey("PROJECT_CODE")&&documentEntity.containsKey("RECIEVE_STATUS")&&documentEntity.containsKey("DOCUMENT_TYPE")){
                if (documentEntity.get("RECIEVE_STATUS").equals("Y")) {
                    QueryWrapper<BudgetProject> projectQueryWrapper = new QueryWrapper<>();
                    projectQueryWrapper.eq(BudgetProject.PROJECT_CODE,documentEntity.get("PROJECT_CODE"));
                    BudgetProject projectEntity = budgetProjectService.getOne(projectQueryWrapper);
                    QueryWrapper<ProjectRecieveDetail> queryWrapper2 = new QueryWrapper<>();
                    queryWrapper2.eq(ProjectRecieveDetail.DOCUMENT_ID, Long.parseLong(documentEntity.get("ID").toString()));
                    queryWrapper2.eq(ProjectRecieveDetail.DOCUMENT_TYPE, entity1.getDocumentType());
                    List<ProjectRecieveDetail> documentChildList = projectRecieveDetailService.list(queryWrapper2);
                    for (ProjectRecieveDetail pp : documentChildList) {
                        DssScientificRecive reciveEntity = new DssScientificRecive();
                        reciveEntity.setBudgetYear(pp.getRecieveTime().format(DateTimeFormatter.ofPattern("yyyy")));
                        reciveEntity.setCreateTime(LocalDateTime.now());
                        reciveEntity.setCwLineId(pp.getId());
                        reciveEntity.setDocumentNum(documentEntity.get("DOCUMENT_NUM").toString());
                        reciveEntity.setFromSystem(projectEntity.getExt1());
                        reciveEntity.setFunds(pp.getRecieveConfirmAmount());
                        reciveEntity.setProCode(projectEntity.getProjectCode());
                        reciveEntity.setReciveDate(pp.getRecieveTime());
                        reciveEntity.setReciveDeptId(Long.parseLong(documentEntity.get("DEPT_ID").toString()));
                        reciveEntity.setStatus("Y");
                        reciveEntity.setTaskCode(projectEntity.getProjectCode());
                        dssScientificReciveService.saveOrUpdate(reciveEntity);
                    }
                }
            }
        }else{
            if(documentEntity.containsKey("PROJECT_CODE")){
                QueryWrapper<BudgetProject> projectQueryWrapper = new QueryWrapper<>();
                projectQueryWrapper.eq(BudgetProject.PROJECT_CODE,documentEntity.get("PROJECT_CODE"));
                BudgetProject projectEntity = budgetProjectService.getOne(projectQueryWrapper);
                QueryWrapper<RecieveLineMsg> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq(RecieveLineMsg.DOCUMENT_ID, Long.parseLong(documentEntity.get("ID").toString()));
                queryWrapper2.eq(RecieveLineMsg.DOCUMENT_TYPE, entity1.getDocumentType());
                List<RecieveLineMsg> documentChildList = recieveLineMsgService.list(queryWrapper2);
                for (RecieveLineMsg pp : documentChildList) {
                    DssScientificRecive reciveEntity = new DssScientificRecive();
                    reciveEntity.setBudgetYear(pp.getRecieveTime().format(DateTimeFormatter.ofPattern("yyyy")));
                    reciveEntity.setCreateTime(LocalDateTime.now());
                    reciveEntity.setCwLineId(pp.getId());
                    reciveEntity.setDocumentNum(documentEntity.get("DOCUMENT_NUM").toString());
                    reciveEntity.setFromSystem(projectEntity.getExt1());
                    reciveEntity.setFunds(pp.getAmountCollected());
                    reciveEntity.setProCode(projectEntity.getProjectCode());
                    reciveEntity.setReciveDate(pp.getRecieveTime());
                    reciveEntity.setReciveDeptId(Long.parseLong(documentEntity.get("DEPT_ID").toString()));
                    reciveEntity.setStatus("Y");
                    reciveEntity.setTaskCode(projectEntity.getProjectCode());
                    dssScientificReciveService.saveOrUpdate(reciveEntity);
                }
            }
        }

    }
}
