package com.deloitte.services.fssc.business.rep.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryParam;
import com.deloitte.platform.api.fssc.rep.param.RecievePaymentQueryForm;
import com.deloitte.platform.api.fssc.rep.vo.RecieveClaimAreaVo;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgVo;
import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentForm;
import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentVo;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.budget.mq.MqConfig;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.bpm.vo.SendToOaVo;
import com.deloitte.services.fssc.business.rep.biz.RecievePaymentBiz;
import com.deloitte.services.fssc.business.rep.entity.RecieveClaimArea;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import com.deloitte.services.fssc.business.rep.service.IRecieveClaimAreaService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :   RecievePayment控制器实现类
 * @Modified :
 */
@Api(tags = "收款单操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/rep/recieve-payment")
public class RecievePaymentController {
    @Autowired
    private IBankUnitInfoService bankUnitInfoService;
    @Autowired
    public IRecievePaymentService recievePaymentService;
    @Autowired
    private IBaseIncomeSubTypeService baseIncomeSubTypeService;
    @Autowired
    private RecievePaymentBiz recievePaymentBiz;
    @Autowired
    private BpmProcessBiz bpmProcessBiz;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;
    @Autowired
    private IRecieveClaimAreaService claimAreaService;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增修改收款单", notes = "新增修改收款单")
    public Result<RecievePaymentVo> add(@Valid @RequestBody
                                        @ApiParam(name = "recievePaymentForm", value = "新增RecievePayment的form表单", required = true)
                                                RecievePaymentForm recievePaymentForm) {
        log.info("form:{}", recievePaymentForm.toString());
        return Result.success(recievePaymentBiz.saveOrUpdate(recievePaymentForm));
    }
    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<RecievePaymentVo> doProcess(@Valid @RequestBody
                                              @ApiParam(name = "recievePaymentForm", value = "新增RecievePayment的form表单", required = true)
                                                      RecievePaymentForm recievePaymentForm) {
        RecievePaymentVo data = recievePaymentBiz.saveOrUpdate(recievePaymentForm);
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(data.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(data.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(data.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        recievePaymentService.modifyCliamStatus(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue(),
                StringUtil.castTolong(data.getId()),true);

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setProjectId(data.getProjectId());
        valueForm.setTotalAmount(data.getTotalAmount().toString());
        valueForm.setMainTypeCode(data.getInComeMainTypeCode());
        valueForm.setDocumentId(StringUtil.castTolong(data.getId()));
        valueForm.setDocumentType(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        valueForm.setCreateBy(data.getCreateBy());
        valueForm.setDeptCode(data.getDeptCode());
        valueForm.setProjectCode(data.getProjectCode());
        valueForm.setUnitCode(data.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);


        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setCopyEmpNos(recievePaymentForm.getCopyEmpNos());
        startForm.setDocumentId(StringUtil.castTolong(data.getId()));
        startForm.setDocumentNum(data.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue(),startForm.getDocumentId());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(recievePaymentForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(recievePaymentForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        return Result.success(data);
    }



    @GetMapping(value = "/findClaimPerson")
    @ApiOperation(value = "查询认领人", notes = "查询认领人")
    @ResponseBody
    public Result<List<RecieveClaimAreaVo>> findClaimPerson(@RequestParam Long documentId){
        QueryWrapper<RecieveClaimArea> areaQueryWrapper=new QueryWrapper<>();
        areaQueryWrapper.eq(RecieveClaimArea.DOCUMENT_ID,documentId)
                .eq(RecieveClaimArea.DOCUMENT_TYPE,FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        List<RecieveClaimArea> claimAreas = claimAreaService.list(areaQueryWrapper);

        if(CollectionUtils.isNotEmpty(claimAreas)){
            RecievePayment recievePayment = recievePaymentService.getById(documentId);
            recievePayment.setEx1("Y");
            recievePaymentService.saveOrUpdate(recievePayment);
            List<RecieveClaimAreaVo> recieveClaimAreaVos =
                    new BeanUtils<RecieveClaimAreaVo>().copyObjs(claimAreas, RecieveClaimAreaVo.class);
            for (RecieveClaimAreaVo vo:recieveClaimAreaVos){
                String claimEmpNo = vo.getClaimEmpNo();
                Result byEmpNo = userFeignService.getByEmpNo(claimEmpNo);
                Object data = byEmpNo.getData();
                if(data!=null){
                    Map<String, Object> userInfo = (Map<String, Object>) data;
                    //vo.setClaimEmpId(userInfo.get("id"));
                    vo.setClaimEmpName(String.valueOf(userInfo.get("name")));
                    Object object = userInfo.get("currentDeputyAccount");
                    if (object != null) {
                        Map<String, Object> accountMap = (Map<String, Object>) object;
                        vo.setClaimDeptCode(String.valueOf(accountMap.get("orgCode")));
                        vo.setClaimDeptName(String.valueOf(accountMap.get("orgName")));
                    }
                }
            }
            return Result.success(recieveClaimAreaVos);
        }
        return Result.success();
    }


    @PostMapping(value = "/doSendToOa")
    @ApiOperation(value = "推送到OA系统", notes = "推送到OA系统")
    @ResponseBody
    public Result doSendToOa(@RequestBody SendToOaVo vo){
        Long documentId = vo.getDocumentId();
        RecievePayment payment = recievePaymentService.getById(documentId);
        AssertUtils.asserts(payment!=null,FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        RecievePaymentForm recievePaymentForm=new RecievePaymentForm();
        recievePaymentForm.setId(payment.getId());
        recievePaymentForm.setCopyEmpNos(vo.getCopyEmpNos());
        recievePaymentBiz.saveClaimArea(recievePaymentForm);
        vo.setDocumentType(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        vo.setDocumentNum(payment.getDocumentNum());
        vo.setProcessDefKey(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
        vo.setProcessInstanceId(StringUtil.objectToString(payment.getId()));
        MessageBuilder messageBuilder = MessageBuilder.withBody(JSON.toJSONBytes(vo));
        amqpTemplate.convertAndSend(MqConfig.fsscTopicEx,MqConfig.sendToOa,messageBuilder.build());
        return Result.success();
    }

    @ApiOperation(value = "删除收款单", notes = "删除收款单")
    @DeleteMapping(value = "deleteById/{id}")
    public Result delete(@PathVariable(value = "id") Long id) {
         AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        recievePaymentBiz.deleteById(id);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }


    @ApiOperation(value = "获取收款单", notes = "获取指定ID的RecievePayment信息")
    @GetMapping(value = "getById/{id}")
    public Result<RecievePaymentVo> get(@PathVariable(value = "id") Long id) {
        log.info("get with id:{}", id);
        return Result.success(recievePaymentBiz.getById(id));
    }


    @ApiOperation(value = "分页查询收款单", notes = "根据条件查询RecievePayment分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<RecievePaymentVo>> search(@ApiParam(name = "recievePaymentQueryForm", value = "RecievePayment查询参数", required = true) RecievePaymentQueryForm recievePaymentQueryForm) {
        log.info("search with recievePaymentQueryForm:", recievePaymentQueryForm.toString());
        IPage<RecievePayment> recievePaymentPage = recievePaymentService.selectPage(recievePaymentQueryForm);
        IPage<RecievePaymentVo> recievePaymentVoPage = new BeanUtils<RecievePaymentVo>().copyPageObjs(recievePaymentPage, RecievePaymentVo.class);
        for (RecievePaymentVo vo:recievePaymentVoPage.getRecords()){
            //已认领金额 未认领金额
            vo.setNoIncomeClaimAmount(
                    BigDecimalUtil.convert(vo.getSkTotal()).subtract(BigDecimalUtil.convert(vo.getIncomeClaimAmount()))
            );
        }
        return new Result<IPage<RecievePaymentVo>>().sucess(recievePaymentVoPage);
    }



    @ApiOperation(value = "查询收款单行款项确认查询", notes = "查询收款单行款项确认查询")
    @GetMapping(value = "findRecieveLines")
    public Result<List<RecieveLineMsgVo>> findRecieveLines(RecieveLineMsgQueryParam recieveLineMsgQueryParam) {
        List<RecieveLineMsgVo> msgVos=recievePaymentService.findRecieveLines(recieveLineMsgQueryParam);
        addMOo(msgVos);
        return Result.success(msgVos);
    }

    @ApiOperation(value = "查询收款单行收入上缴调用", notes = "查询收款单行收入上缴调用")
    @GetMapping(value = "findRecieveLinesShourushangjiao")
    public Result<List<RecieveLineMsgVo>> findRecieveLinesShourushangjiao(RecieveLineMsgQueryParam recieveLineMsgQueryParam) {
        List<RecieveLineMsgVo> msgVos=recievePaymentService.findRecieveLineShouRuShangjiao(recieveLineMsgQueryParam);
        addMOo(msgVos);
        return Result.success(msgVos);
    }


    private void addMOo(List<RecieveLineMsgVo> msgVos){
        if(CollectionUtils.isNotEmpty(msgVos)){
            for (RecieveLineMsgVo vo:msgVos){
                Long inComeSubTypeId = vo.getInComeSubTypeId();
                if(inComeSubTypeId!=null){
                    BaseIncomeSubType subType = baseIncomeSubTypeService.getById(inComeSubTypeId);
                    if(subType!=null){
                        vo.setInComeSubTypeCode(subType.getCode());
                        vo.setInComeSubTypeName(subType.getName());
                    }
                }
                Long bankUnitId = vo.getRecieveBankUnitId();
                if(bankUnitId!=null){
                    BankUnitInfo info = bankUnitInfoService.getById(bankUnitId);
                    if(info!=null){
                        vo.setRecieveBankAccountName(info.getBankAccountName());
                    }
                }
            }
        }
    }



    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(RecievePaymentQueryForm form)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(form));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        List<RecievePayment> records = recievePaymentService.selectPage(form).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "管理部门", "收款金额", "申请日期", "单据状态", "收入认领状态"};
        String fileName = "借款信息列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            RecievePayment vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getDeptName();
            if( vo.getTotalAmount()!=null){
                content[i][4] = vo.getSkTotal().toString();
            }
            if (vo.getCreateTime() != null) {
                content[i][5] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][6] = docStatusMap.get(vo.getDocumentStatus());
            String recieveStatus = vo.getIncomeClaimStatus();
            if("Y".equals(recieveStatus)){
                content[i][7] = "已认领";
            }
            if("N".equals(recieveStatus)){
                content[i][7] = "未认领";
            }
            if("SOME".equals(recieveStatus)){
                content[i][7] = "部分认领";
            }

        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }
}



