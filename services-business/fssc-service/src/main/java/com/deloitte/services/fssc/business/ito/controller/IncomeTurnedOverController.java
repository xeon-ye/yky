package com.deloitte.services.fssc.business.ito.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.ito.param.IncomeTurnedOverQueryForm;
import com.deloitte.platform.api.fssc.ito.vo.DetailsOfFundsForm;
import com.deloitte.platform.api.fssc.ito.vo.IncomeTurnedOverForm;
import com.deloitte.platform.api.fssc.ito.vo.IncomeTurnedOverVo;
import com.deloitte.platform.api.fssc.rep.param.RecievePaymentQueryForm;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgVo;
import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.ito.entity.DetailsOfFunds;
import com.deloitte.services.fssc.business.ito.entity.IncomeTurnedOver;
import com.deloitte.services.fssc.business.ito.service.IDetailsOfFundsService;
import com.deloitte.services.fssc.business.ito.service.IIncomeTurnedOverService;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description :   IncomeTurnedOver控制器实现类
 * @Modified :
 */
@Api(tags = "收入上缴操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/ito/income-turned-over")
public class IncomeTurnedOverController {

    @Autowired
    public IIncomeTurnedOverService incomeTurnedOverService;

    @Autowired
    public IDetailsOfFundsService detailsOfFundsService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    public IRecievePaymentService recievePaymentService;

    @Autowired
    private IRecieveLineMsgService recieveLineMsgService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;


    @GetMapping(value = "/loadDataByadvanceId")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result loadDataByadvanceId(Long id) {
        IncomeTurnedOverVo infoById = incomeTurnedOverService.findInfoById(id);
        return Result.success(infoById);
    }

    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<IPage<IncomeTurnedOverVo>> pageCondition(@Valid IncomeTurnedOverQueryForm incomeTurnedOverQueryForm) {
        log.info("search with incomeTurnedOverQueryForm:", incomeTurnedOverQueryForm.toString());
        IPage<IncomeTurnedOver> incomeTurnedOverPage = incomeTurnedOverService.selectPage(incomeTurnedOverQueryForm);
        IPage<IncomeTurnedOverVo> incomeTurnedOverVoPage = new BeanUtils<IncomeTurnedOverVo>().copyPageObjs(incomeTurnedOverPage, IncomeTurnedOverVo.class);
        return new Result<IPage<IncomeTurnedOverVo>>().sucess(incomeTurnedOverVoPage);
    }

    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "删除IncomeTurnedOver", notes = "根据url的id来指定删除对象")
    @Transactional
    public Result delete(@RequestBody Long id) {
        log.info("id:" + id);
        IncomeTurnedOver incomeTurnedOver = incomeTurnedOverService.getById(id);
        AssertUtils.asserts(incomeTurnedOver != null, FsscErrorType.NOT_FIND_DATA);
        String documentStatus = incomeTurnedOver.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                FsscEums.RECALLED.getValue().equals(documentStatus) ||
                FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        incomeTurnedOverService.removeById(id);

        //删除款项明细信息
        QueryWrapper<DetailsOfFunds> detailsBjWrapper = new QueryWrapper<>();
        detailsBjWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
        detailsOfFundsService.remove(detailsBjWrapper);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }

    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增或修改", notes = "新增或修改")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSaveOrUpdate(@RequestBody @Valid IncomeTurnedOverForm incomeTurnedOverForm) {
        log.info("tasTravelApplyInfoForm:" + incomeTurnedOverForm.toString());
        return saveOrUpdate(incomeTurnedOverForm);
    }

    public Result saveOrUpdate(IncomeTurnedOverForm incomeTurnedOverForm) {
        //验证单据是否存在
        FsscCommonUtil.valiHasData(incomeTurnedOverForm.getId()
                , FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
        IncomeTurnedOver incomeTurnedOver = new BeanUtils<IncomeTurnedOver>().copyObj(incomeTurnedOverForm, IncomeTurnedOver.class);
        boolean saveOrUpdateSuccess = incomeTurnedOverService.saveOrUpdate(incomeTurnedOver);
        if (saveOrUpdateSuccess) {
            //款项信息
            QueryWrapper<DetailsOfFunds> leaveaBjWrapper = new QueryWrapper<>();
            leaveaBjWrapper.eq("DOCUMENT_ID", incomeTurnedOver.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
            List<DetailsOfFundsForm> detailsOfFundsForm = incomeTurnedOverForm.getDetailsOfFundsForms();
            if (CollectionUtils.isNotEmpty(detailsOfFundsForm)) {
                List<DetailsOfFunds> geExpensePaymentList = new BeanUtils<DetailsOfFunds>().copyObjs(detailsOfFundsForm, DetailsOfFunds.class);
                List<Long> longList = detailsOfFundsForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(longList)) {
                    leaveaBjWrapper.notIn("id", longList);
                }
                detailsOfFundsService.remove(leaveaBjWrapper);
                if (CollectionUtils.isNotEmpty(geExpensePaymentList)) {
                    for (DetailsOfFunds geExpensePaymen : geExpensePaymentList) {
                        geExpensePaymen.setDocumentId(incomeTurnedOver.getId());
                        geExpensePaymen.setDocumentType(FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
                        detailsOfFundsService.setRecieveLineY(geExpensePaymen.getReceiptLineId()
                                , FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue(), "Y");
                    }
                    detailsOfFundsService.saveOrUpdateBatch(geExpensePaymentList);
                }
            } else {
                detailsOfFundsService.remove(leaveaBjWrapper);
            }
            //文件列表保存
            List<Long> fileIds = incomeTurnedOverForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, incomeTurnedOver.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                files.stream().forEach(ka -> ka.setDocumentId(incomeTurnedOver.getId()));
                fileService.saveOrUpdateBatch(files);
            }

            return Result.success(incomeTurnedOverService.findInfoById(incomeTurnedOver.getId()));
        }
        throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
    }

    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, IncomeTurnedOverQueryForm incomeTurnedOverQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(incomeTurnedOverQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<IncomeTurnedOver> records = incomeTurnedOverService.selectPage(incomeTurnedOverQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "申请人", "款项大类", "归属单位", "归属部门", "收款金额", "申请日期",
                "单据状态"};
        String fileName = "收入上缴列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            IncomeTurnedOver vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getIncomeTypeName();
            content[i][4] = vo.getUnitName();
            ;
            content[i][5] = vo.getDeptName();
            // content[i][5] = StringUtil.obj;ectToString(vo.getBorrowAmount());

            content[i][6] = StringUtil.objectToString(vo.getTotalSum());

            if (vo.getCreateTime() != null) {
                content[i][7] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][8] = docStatusMap.get(vo.getDocumentStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "根据条件列表查询", notes = "根据条件列表查询")
    @ResponseBody
    public Result<List<RecievePaymentVo>> search(@ApiParam(name = "recievePaymentQueryForm", value = "RecievePayment查询参数", required = true) RecievePaymentQueryForm recievePaymentQueryForm) {
        log.info("search with recievePaymentQueryForm:", recievePaymentQueryForm.toString());
        List<RecievePayment> recievePayment = recievePaymentService.selectList(recievePaymentQueryForm);
        List<RecievePaymentVo> recievePaymentVoList = new BeanUtils<RecievePaymentVo>().copyObjs(recievePayment, RecievePaymentVo.class);
        for (RecievePaymentVo recievePaymentVo : recievePaymentVoList) {
            QueryWrapper<RecieveLineMsg> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("DOCUMENT_ID", recievePaymentVo.getId())
                    .eq("DOCUMENT_TYPE", FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue());
            List<RecieveLineMsg> recieveLineMsgList = recieveLineMsgService.list(queryWrapper);
            List<RecieveLineMsgVo> recieveLineMsgVoList = new BeanUtils<RecieveLineMsgVo>().copyObjs(recieveLineMsgList, RecieveLineMsgVo.class);
            recievePaymentVo.setRecieveLineMsgVos(recieveLineMsgVoList);
        }
        return new Result<List<RecievePaymentVo>>().sucess(recievePaymentVoList);
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<IncomeTurnedOverVo> doProcess(@Valid @RequestBody
                                                @ApiParam(name = "incomeTurnedOverForm", value = "IncomeTurnedOver", required = true)
                                                        IncomeTurnedOverForm incomeTurnedOverForm) {
        Result<IncomeTurnedOverVo> queryDatas = doSaveOrUpdate(incomeTurnedOverForm);
        IncomeTurnedOverVo data = queryDatas.getData();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(data.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(data.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(data.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        detailsOfFundsService.setRecieveLineY(data.getId(), FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue(),
                "Y");

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");

        valueForm.setTotalAmount(data.getTotalSum().toString());

        valueForm.setDocumentId(data.getId());
        valueForm.setDocumentType(FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
        valueForm.setCreateBy(data.getCreateBy());
        valueForm.setDeptCode(data.getDeptCode());
        valueForm.setUnitCode(data.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(data.getId());
        startForm.setDocumentNum(data.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue(), startForm.getDocumentId());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(incomeTurnedOverForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(incomeTurnedOverForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        return Result.success(data);
    }

}



