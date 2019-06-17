package com.deloitte.services.fssc.system.bank.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.api.fssc.dic.vo.DicValueVo;
import com.deloitte.platform.api.fssc.trade.client.TradeInformationClient;
import com.deloitte.platform.api.fssc.trade.param.TradeInformationQueryForm;
import com.deloitte.platform.api.fssc.trade.vo.BusinessRelateDocument;
import com.deloitte.platform.api.fssc.trade.vo.TradeInformationVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.service.IAvBaseElementService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.bank.entity.BankInfo;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.entity.TradeInformation;
import com.deloitte.services.fssc.system.bank.service.IBankInfoService;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.bank.service.ITradeInformationService;
import com.deloitte.services.fssc.util.DateUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.UUIDUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.ExcelImportUtil;
import com.deloitte.services.fssc.util.excel.ExcelResult;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description :   交易信息-控制器实现类
 * @Modified :
 */
@Api(tags = "交易信息-操作接口")
@Slf4j
@RestController
public class TradeInformationController implements TradeInformationClient {

    @Autowired
    private ITradeInformationService tradeInformationService;

    @Autowired
    private IBankUnitInfoService bankUnitInfoService;

    @Autowired
    private IBankInfoService bankInfoService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IAvBaseElementService avBaseElementService;

    @Autowired
    private IRecievePaymentService receivePaymentService;

    @Override
    @ApiOperation(value = "获取 交易信息", notes = "获取指定ID的TradeInformation信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "TradeInformation的ID", required = true, dataType = "long")
    public Result<TradeInformationVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        TradeInformation tradeInformation = tradeInformationService.getById(id);
        TradeInformationVo tradeInformationVo = new BeanUtils<TradeInformationVo>().copyObj(tradeInformation, TradeInformationVo.class);
        return new Result<TradeInformationVo>().sucess(tradeInformationVo);
    }

    @Override
    @ApiOperation(value = "列表查询 交易信息", notes = "根据条件查询TradeInformation列表数据")
    public Result<List<TradeInformationVo>> list(@Valid @RequestBody @ApiParam(name = "tradeInformationQueryForm", value = "TradeInformation查询参数", required = true)
                                                         TradeInformationQueryForm queryForm) {
        log.info("search with tradeInformationQueryForm:", queryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        List<TradeInformation> tradeInformationList = tradeInformationService.selectList(queryForm);
        List<TradeInformationVo> tradeInformationVoList = new BeanUtils<TradeInformationVo>().copyObjs(tradeInformationList, TradeInformationVo.class);
        return new Result<List<TradeInformationVo>>().sucess(tradeInformationVoList);
    }


    @Override
    @ApiOperation(value = "分页查询 交易信息", notes = "根据条件查询TradeInformation分页数据")
    public Result<IPage<TradeInformationVo>> search(
            @Valid @RequestBody @ApiParam(name = "tradeInformationQueryForm", value = "TradeInformation查询参数", required = true)
                    TradeInformationQueryForm queryForm) {
        log.info("search with tradeInformationQueryForm:", queryForm.toString());
//        DeptVo deptVo = commonServices.getCurrentDept();
//        queryForm.setUnitCode(deptVo.getDeptCode());
        IPage<TradeInformation> tradeInformationPage = tradeInformationService.selectPage(queryForm);
        IPage<TradeInformationVo> tradeInformationVoPage = new BeanUtils<TradeInformationVo>().copyPageObjs(tradeInformationPage, TradeInformationVo.class);
        if (CollectionUtils.isEmpty(tradeInformationVoPage.getRecords())){
            return Result.success(tradeInformationVoPage);
        }
        List<DicValueVo> valueVoList = commonServices.findDicValueList("BANK_TYPE");
        Map<String,String> bankAccountTypeMap = commonServices.getValueByCode(valueVoList);
        Map<String,BankUnitInfo> bankUnitInfoMap = bankUnitInfoService.getBankNumBeanMap();
        Map<Long,BankInfo> bankInfoMap = bankInfoService.getBankIdBeanMap();
        Map<String,List<BankUnitInfoVo>> bankUnitInfoVoMap = bankUnitInfoService.getBankAccountVoMap();
        List<String> serialNumList = tradeInformationPage.getRecords().stream().map(TradeInformation :: getTradeSerialNum).collect(Collectors.toList());
        Map<String,List<BusinessRelateDocument>> relateDocumentMap =  receivePaymentService.getSerialNumDocumentMap(serialNumList);
        for (TradeInformationVo tradeInformationVo : tradeInformationVoPage.getRecords()){
            BankUnitInfo payBankUnitInfo =  bankUnitInfoMap.get(tradeInformationVo.getPaymentAccount());
            BankInfo bankInfo = null;
            if (payBankUnitInfo != null){
                tradeInformationVo.setPaymentUnitId(String.valueOf(payBankUnitInfo.getUnitId()));
                tradeInformationVo.setPaymentUnitCode(payBankUnitInfo.getUnitCode() );
                tradeInformationVo.setPaymentUnitName(payBankUnitInfo.getUnitName());
                bankInfo = bankInfoMap.get(payBankUnitInfo.getBankId());
            }
            tradeInformationVo.setPaymentBankName(bankInfo != null ? bankInfo.getBankName() : null);
            BankUnitInfo receiverBankUnitInfo = bankUnitInfoMap.get(tradeInformationVo.getReceiverAccount());
            if (receiverBankUnitInfo != null) {
                tradeInformationVo.setReceiverBankAccountName(receiverBankUnitInfo.getBankAccountName());
                tradeInformationVo.setReceiverUnitId(String.valueOf(receiverBankUnitInfo.getUnitId()));
                tradeInformationVo.setReceiverUnitCode(receiverBankUnitInfo.getUnitCode());
                tradeInformationVo.setReceiverUnitName(receiverBankUnitInfo.getUnitName());
                tradeInformationVo.setReceiverAccountTypeCode(receiverBankUnitInfo.getBankType());
                tradeInformationVo.setReceiverAccountType(bankAccountTypeMap.get(receiverBankUnitInfo.getBankType()));
            }
            if (tradeInformationVo.getTradeTime() != null) {
                String tradeDateStr = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(tradeInformationVo.getTradeTime()),DateUtil.FM_YYYY_MM_DD);
                tradeInformationVo.setTradeDateStr(tradeDateStr);
                String tradeTimeStr =  DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(tradeInformationVo.getTradeTime()),DateUtil.FM_YYYY_MM_DD_HHMMSS);
                tradeTimeStr = tradeTimeStr.substring(11,tradeTimeStr.length());
                tradeInformationVo.setTradeTimeStr(tradeTimeStr);
            }
            if(StringUtil.isNotEmpty( tradeInformationVo.getReceiverAccount())){
                tradeInformationVo.setBankUnitInfoVos(bankUnitInfoVoMap.get( tradeInformationVo.getReceiverAccount()));
            }
            if (FsscEums.TRADE_BUSINESS_BATCH_PAY.getDescription().equals(tradeInformationVo.getBusinessType())){
                tradeInformationVo.setProcessStatus(FsscEums.TRADE_PROCESS_STATUS_DONE.getDescription());
            }
            if (FsscEums.TRADE_BUSINESS_BATCH_RECEIPT.getDescription().equals(tradeInformationVo.getBusinessType())){
                //需要判断收款单的金额情况
                if (CollectionUtils.isEmpty(relateDocumentMap.get(tradeInformationVo.getTradeSerialNum()))){
                    tradeInformationVo.setProcessStatus(FsscEums.TRADE_PROCESS_STATUS_TODO.getDescription());
                }else {
                    List<BusinessRelateDocument> relateDocumentList = relateDocumentMap.get(tradeInformationVo.getTradeSerialNum());
                    BigDecimal totalAMount = relateDocumentList.stream().map(BusinessRelateDocument :: getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    if (tradeInformationVo.getTradeAmount().compareTo(totalAMount) <= 0){
                        tradeInformationVo.setProcessStatus(FsscEums.TRADE_PROCESS_STATUS_DONE.getDescription());
                        tradeInformationVo.setRelateDocumentList(relateDocumentList);
                    }else if(tradeInformationVo.getTradeAmount().compareTo(totalAMount) > 0){
                        tradeInformationVo.setProcessStatus(FsscEums.TRADE_PROCESS_STATUS_PART_DONE.getDescription());
                        tradeInformationVo.setRelateDocumentList(relateDocumentList);
                    }
                }
            }
        }
        return new Result<IPage<TradeInformationVo>>().sucess(tradeInformationVoPage);
    }


    @ApiOperation(value = "导出交易信息导入模板", notes = "导出交易信息导入模板")
    @GetMapping(value = path + "/exportTemplet")
    public void exportTemplet(HttpServletResponse response){
        List<ExcelHeader> headerList = new ArrayList<>();
//        headerList.add(new ExcelHeader(TradeInformation.QUERY_ACCOUNT_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.BUSINESS_TYPE_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.DEALINGS_FLAG_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_BANK_NUM_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_BANK_NAME_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_ACCOUNT_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_NAME_EXCEL).setOneCellWidth(6000));

        //        headerList.add(new ExcelHeader(TradeInformation.REMITTING_BANK_BUSINESS_NUM_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_BANK_NUM_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_BANK_NAME_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_ACCOUNT_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_NAME_EXCEL).setOneCellWidth(4000));
//        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_BANK_BUSINESS_NUM_EXCEL).setOneCellWidth(6000));

        headerList.add(new ExcelHeader(TradeInformation.TRADE_DATE_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_TIME_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_CURRENCY_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_AMOUNT_EXCEL).setOneCellWidth(4000));
//        headerList.add(new ExcelHeader(TradeInformation.TRADE_REMAINING_AMOUNT_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.PURPOSE_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_SERIAL_NUM_EXCEL).setOneCellWidth(6000));
//        headerList.add(new ExcelHeader(TradeInformation.RECORD_NUM_EXCEL).setOneCellWidth(6000));
//        headerList.add(new ExcelHeader(TradeInformation.REMARK_EXCEL).setOneCellWidth(6000));

//        headerList.add(new ExcelHeader("被代理行号").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("被代理账号").setOneCellWidth(6000));
//        headerList.add(new ExcelHeader("被代理账户名").setOneCellWidth(6000));
//        headerList.add(new ExcelHeader("被代理账户开户行名").setOneCellWidth(6000));
//
//        headerList.add(new ExcelHeader("可用余额").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("冻结金额").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("透支额度").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("可用透支额度").setOneCellWidth(6000));
//        headerList.add(new ExcelHeader("起息日期").setOneCellWidth(4000));
//
//        headerList.add(new ExcelHeader("凭证类型").setOneCellWidth(6000));
//        headerList.add(new ExcelHeader("凭证号码").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("汇率").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("渠道标识").setOneCellWidth(6000));
//        headerList.add(new ExcelHeader("商户号").setOneCellWidth(4000));

        String fileName = "交易信息导入模板";
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.exportExcel();
    }

    @ApiOperation("交易信息导入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件对象", required = true, dataType = "MultipartFile")
    })
    @PostMapping(value = path +"/importTradeInfo")
    public Result importTradeInfo(@RequestPart("file") MultipartFile file) throws IOException {
        if (file != null) {
            MultipartFile excelFile = file;
            String originalFilename = excelFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (!FsscEums.EXCEL_2003.getValue().equals(ext) && !FsscEums.EXCEL_2007.getValue()
                    .equals(ext)) {
                throw new FSSCException(FsscErrorType.FILE_FORMAT_NOT_EXCEL);
            }
            File cacheFile;
            try {
                cacheFile = File.createTempFile(UUIDUtil.getRandom32PK(), ext);
                excelFile.transferTo(cacheFile);
                ExcelResult excelResult = ExcelImportUtil.getExcelDate(cacheFile);
                if (!excelResult.readSuccess()) {
                    return Result.fail(excelResult.getErrorMsgBeanList());
                }
                List<Map<String, String>> rowMapList = excelResult.getTableData();
                List<TradeInformation> tradeInformationList = new ArrayList<>();
                Map<String,String> currencyCodeNameMap = getCurrencyMap();
                Map<String,Integer> serialNumLineMap = new HashMap<>();
                int rowNum = 0;
                for (Map<String, String> rowMap : rowMapList) {
                    rowNum++;
                    TradeInformation tradeInfo = new TradeInformation();
                    //业务类型
                    String businessType = rowMap.get(tradeInfo.BUSINESS_TYPE_EXCEL);
                    if (StringUtils.isBlank(businessType)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.BUSINESS_TYPE_EXCEL);
                    }else{
                        tradeInfo.setBusinessType(businessType);
                    }
                    //来往帐标识
                    String dealingsFlag = rowMap.get(tradeInfo.DEALINGS_FLAG_EXCEL);
                    if (StringUtils.isBlank(dealingsFlag)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.DEALINGS_FLAG_EXCEL);
                    }else{
                        tradeInfo.setDealingsFlag(dealingsFlag);
                    }
                    //付款人开户行号
                    String paymentBankNum = rowMap.get(tradeInfo.PAYMENT_BANK_NUM_EXCEL);
                    if (StringUtils.isBlank(paymentBankNum)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.PAYMENT_BANK_NUM_EXCEL);
                    }else{
                        tradeInfo.setPaymentBankNum(paymentBankNum);
                    }
                    //付款人开户行名
                    String paymentBankName = rowMap.get(tradeInfo.PAYMENT_BANK_NAME_EXCEL);
                    if (StringUtils.isBlank(paymentBankName)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.PAYMENT_BANK_NAME_EXCEL);
                    }else{
                        tradeInfo.setPaymentBankName(paymentBankName);
                    }
                    //付款人账户
                    String paymentAccount = rowMap.get(tradeInfo.PAYMENT_ACCOUNT_EXCEL);
                    if (StringUtils.isBlank(paymentAccount)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.PAYMENT_ACCOUNT_EXCEL);
                    }else{
                        BankUnitInfo bankUnitInfo = bankUnitInfoService.getBankUnitInfo(paymentAccount);
                        if (bankUnitInfo == null){
                            excelResult.addErrorMsg(rowNum, TradeInformation.PAYMENT_ACCOUNT_EXCEL,"付款账户未在系统中维护");
                        }
                        tradeInfo.setPaymentAccount(paymentAccount);
                    }
                    //付款人名称
                    String paymentName = rowMap.get(tradeInfo.PAYMENT_NAME_EXCEL);
                    if (StringUtils.isBlank(paymentName)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.PAYMENT_NAME_EXCEL);
                    }else{
                        tradeInfo.setPaymentName(paymentName);
                    }
                    //收款人行号
                    String receiverBankNum = rowMap.get(tradeInfo.RECEIVER_BANK_NUM_EXCEL);
                    if (StringUtils.isBlank(receiverBankNum)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.RECEIVER_BANK_NUM_EXCEL);
                    }else{
                        tradeInfo.setReceiverBankNum(receiverBankNum);
                    }
                    //收款人开户行名
                    String receiverBankName = rowMap.get(tradeInfo.RECEIVER_BANK_NAME_EXCEL);
                    if (StringUtils.isBlank(receiverBankName)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.RECEIVER_BANK_NAME_EXCEL);
                    }else{
                        tradeInfo.setReceiverBankName(receiverBankName);
                    }
                    //收款人账户
                    String receiverAccount = rowMap.get(tradeInfo.RECEIVER_ACCOUNT_EXCEL);
                    if (StringUtils.isBlank(receiverAccount)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.RECEIVER_ACCOUNT_EXCEL);
                    }else{
                        BankUnitInfo bankUnitInfo = bankUnitInfoService.getBankUnitInfo(receiverAccount);
                        if (bankUnitInfo == null){
                            excelResult.addErrorMsg(rowNum, TradeInformation.PAYMENT_ACCOUNT_EXCEL,"收款账户未在系统中维护");
                        }
                        tradeInfo.setReceiverAccount(receiverAccount);
                    }
                    //收款人名称
                    String receiverName = rowMap.get(tradeInfo.RECEIVER_NAME_EXCEL);
                    if (StringUtils.isBlank(receiverName)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.RECEIVER_NAME_EXCEL);
                    }else{
                        tradeInfo.setReceiverName(receiverName);
                    }
                    //交易日期
                    Date tradeDate = null;
                    String tradeDateStr = rowMap.get(tradeInfo.TRADE_DATE_EXCEL);
                    if (StringUtils.isBlank(tradeDateStr)){
                        excelResult.addErrorMsg(rowNum, tradeInfo.TRADE_DATE_EXCEL,
                                "交易日期不能为空,请按照yyyy-MM-dd格式填写");
                    }else {
                        tradeDateStr = tradeDateStr.trim();
                        if (tradeDateStr.length() != 10) {
                            excelResult.addErrorMsg(rowNum, tradeInfo.TRADE_DATE_EXCEL,
                                    "交易日期格式错误,需要yyyy-MM-dd格式");
                        }else {
                            try {
                                tradeDate = DateUtil.stringToDateThrow(tradeDateStr, DateUtil.FM_YYYY_MM_DD);
                            } catch (Exception e3) {
                                log.error("交易日期格式错误: {}", e3.getMessage());
                                excelResult
                                        .addErrorMsg(rowNum, TradeInformation.TRADE_DATE_EXCEL, "交易日期格式错误,"
                                                + "需要yyyy-MM-dd格式");
                            }
                            tradeInfo.setTradeDate(tradeDate != null ? LocalDateTimeUtils.convertDateToLDT(tradeDate) : null);
                        }
                    }
                    //交易时间
                    String tradeTimeStr = rowMap.get(tradeInfo.TRADE_TIME_EXCEL);
                    if (StringUtils.isBlank(tradeTimeStr)){
                        excelResult.addErrorMsg(rowNum, tradeInfo.TRADE_TIME_EXCEL,
                                "交易时间不能为空,请按照HH:mm:ss格式填写");
                    }else {
                        tradeTimeStr = tradeTimeStr.trim();
                        if (tradeTimeStr.length() != 8) {
                            excelResult.addErrorMsg(rowNum, tradeInfo.TRADE_TIME_EXCEL,
                                    "交易时间格式错误,需要HH:mm:ss格式");
                        } else {
                            if (tradeDate != null) {
                                Date tradeTime = null;
                                try {
                                    tradeTimeStr = tradeDateStr + " " + tradeTimeStr;
                                    tradeTime = DateUtil.stringToDateThrow(tradeTimeStr, DateUtil.FM_YYYY_MM_DD_HHMMSS);
                                } catch (Exception e3) {
                                    log.error("转换交易时间错误: {}", e3.getMessage());
                                    excelResult
                                            .addErrorMsg(rowNum, TradeInformation.TRADE_TIME_EXCEL, "交易时间格式错误,"
                                                    + "需要HH:mm:ss格式");
                                }
                                tradeInfo.setTradeTime(tradeTime != null ? LocalDateTimeUtils.convertDateToLDT(tradeTime) : null);
                            }
                        }
                    }
                    //交易货币
                    String tradeCurrency = rowMap.get(tradeInfo.TRADE_CURRENCY_EXCEL);
                    if (StringUtils.isBlank(tradeCurrency)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.TRADE_CURRENCY_EXCEL);
                    }else{
                        if (currencyCodeNameMap.get(tradeCurrency) == null){
                            excelResult.addErrorMsg(rowNum, tradeInfo.TRADE_CURRENCY_EXCEL,
                                    "交易货币不是标准货币代码,如果是人民币请填入CNY");
                        }
                        tradeInfo.setTradeCurrency(tradeCurrency);
                    }
                    //交易金额
                    String tradeAmountStr = rowMap.get(tradeInfo.TRADE_AMOUNT_EXCEL);
                    if (StringUtils.isBlank(tradeAmountStr)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.TRADE_AMOUNT_EXCEL);
                    }else{
                        try {
                            BigDecimal tradeAmount = new BigDecimal(tradeAmountStr);
                            tradeInfo.setTradeAmount(tradeAmount);
                        }catch (Exception e){
                            excelResult
                                    .addErrorMsg(rowNum, TradeInformation.TRADE_AMOUNT_EXCEL, "交易金额格式错误,"
                                            + "需要数值类型");
                        }
                    }
                    //用途
                    String purpose = rowMap.get(tradeInfo.PURPOSE_EXCEL);
                    if (StringUtils.isBlank(purpose)){
                        //excelResult.addEmptyErrorMsg(rowNum, TradeInformation.PURPOSE_EXCEL);
                    }else{
                        tradeInfo.setPurpose(purpose);
                    }
                    //交易流水
                    String serialNum = rowMap.get(tradeInfo.TRADE_SERIAL_NUM_EXCEL);
                    if (StringUtils.isBlank(serialNum)){
                        excelResult.addEmptyErrorMsg(rowNum, TradeInformation.TRADE_SERIAL_NUM_EXCEL);
                    }else{
                        Integer existsLineNum = serialNumLineMap.get(serialNum);
                        if (existsLineNum != null){
                            excelResult.addErrorMsg(rowNum,TradeInformation.TRADE_SERIAL_NUM_EXCEL,TradeInformation.TRADE_SERIAL_NUM_EXCEL + "与第"+existsLineNum + "行重复");
                        }else if(tradeInformationService.countByTradeSerialNum(serialNum) > 0){
                            excelResult.addErrorMsg(rowNum,TradeInformation.TRADE_SERIAL_NUM_EXCEL,TradeInformation.TRADE_SERIAL_NUM_EXCEL + "与历史数据重复");
                        }
                        tradeInfo.setTradeSerialNum(serialNum);
                    }
                    tradeInformationList.add(tradeInfo);
                } if (excelResult.readSuccess()) {
                    tradeInformationService.saveBatch(tradeInformationList);
                    return Result.success();
                } else {
                    return Result.fail(excelResult.getErrorMsgBeanList());
                }
            } catch (IOException ioe) {
                log.error("读取Excel文件出错: {}", ioe.getMessage());
                throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
            }
        }
        throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
    }

    @ApiOperation(value = "导出交易信息", notes = "导出交易信息")
    @GetMapping(value = path + "/exportExcel")
    @ResponseBody
    public void exportExcel(TradeInformationQueryForm queryForm)
            throws IOException {
        log.info("search with TradeInformationQueryForm:{}", JSON.toJSONString(queryForm));
        //DeptVo deptVo = commonServices.getCurrentDept();
        //queryForm.setUnitCode(deptVo.getDeptCode());
        List<ExcelHeader> headerList = new ArrayList<>();
        List<TradeInformation> records = tradeInformationService.selectPage(queryForm).getRecords();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader(TradeInformation.BUSINESS_TYPE_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.DEALINGS_FLAG_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader("处理状态").setOneCellWidth(4000));

        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_BANK_NUM_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_BANK_NAME_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_ACCOUNT_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.PAYMENT_NAME_EXCEL).setOneCellWidth(6000));

        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_BANK_NUM_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_BANK_NAME_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_ACCOUNT_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.RECEIVER_NAME_EXCEL).setOneCellWidth(6000));

        headerList.add(new ExcelHeader(TradeInformation.TRADE_DATE_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_TIME_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_CURRENCY_EXCEL).setOneCellWidth(4000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_AMOUNT_EXCEL).setOneCellWidth(4000));

        headerList.add(new ExcelHeader(TradeInformation.PURPOSE_EXCEL).setOneCellWidth(6000));
        headerList.add(new ExcelHeader(TradeInformation.TRADE_SERIAL_NUM_EXCEL).setOneCellWidth(6000));

        Map<String, BankUnitInfo> bankUnitInfoMap = bankUnitInfoService.getBankNumBeanMap();
        Map<Long,BankInfo> bankInfoMap = bankInfoService.getBankIdBeanMap();
        Map<String,String> currencyCodeNameMap = getCurrencyMap();
        List<String> serialNumList = records.stream().map(TradeInformation :: getTradeSerialNum).collect(Collectors.toList());
        Map<String,List<BusinessRelateDocument>> relateDocumentMap =  receivePaymentService.getSerialNumDocumentMap(serialNumList);
        String fileName = "交易信息";
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[headerList.size()];
            TradeInformation vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getBusinessType();
            content[i][2] = vo.getDealingsFlag();
            if (FsscEums.TRADE_BUSINESS_BATCH_PAY.getDescription().equals(vo.getBusinessType())){
                content[i][3] = FsscEums.TRADE_PROCESS_STATUS_DONE.getDescription();
            }
            if (FsscEums.TRADE_BUSINESS_BATCH_RECEIPT.getDescription().equals(vo.getBusinessType())){
                //需要判断收款单的金额情况
                if (CollectionUtils.isEmpty(relateDocumentMap.get(vo.getTradeSerialNum()))){
                    content[i][3] = FsscEums.TRADE_PROCESS_STATUS_TODO.getDescription();
                }else {
                    List<BusinessRelateDocument> relateDocumentList = relateDocumentMap.get(vo.getTradeSerialNum());
                    BigDecimal totalAMount = relateDocumentList.stream().map(BusinessRelateDocument :: getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    if (vo.getTradeAmount().compareTo(totalAMount) <= 0){
                        content[i][3] = FsscEums.TRADE_PROCESS_STATUS_DONE.getDescription();
                    }else if(vo.getTradeAmount().compareTo(totalAMount) > 0){
                        content[i][3] = FsscEums.TRADE_PROCESS_STATUS_PART_DONE.getDescription();
                    }
                }
            }
            content[i][4] = vo.getPaymentBankNum();
            BankUnitInfo payBankUnitInfo = bankUnitInfoMap.get(vo.getPaymentAccount());
            BankInfo payBankInfo = null;
            if (payBankUnitInfo != null){
                payBankInfo = bankInfoMap.get(payBankUnitInfo.getBankId());
            }
            content[i][5] = payBankInfo != null ? payBankInfo.getBankName() : "";
            content[i][6] = vo.getPaymentAccount();
            content[i][7] = payBankUnitInfo != null ? payBankUnitInfo.getUnitName() : "";

            content[i][8] = vo.getReceiverBankNum();
            BankUnitInfo receiveBankUnitInfo = bankUnitInfoMap.get(vo.getReceiverAccount());
            BankInfo receiveBankInfo = null;
            if (receiveBankUnitInfo != null){
                receiveBankInfo = bankInfoMap.get(receiveBankUnitInfo.getBankId());
            }
            content[i][9] = receiveBankInfo != null ? receiveBankInfo.getBankName() : "";
            content[i][10] = vo.getReceiverAccount();
            content[i][11] = receiveBankUnitInfo != null ? receiveBankUnitInfo.getUnitName() : "";

            if (vo.getTradeTime() != null){
                content[i][12] = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(vo.getTradeTime()),DateUtil.FM_YYYY_MM_DD);
                content[i][13] = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(vo.getTradeTime()),DateUtil.FM_HH_MM_SS);
            }else{
                content[i][12] = "";
                content[i][13] = "";
            }
            content[i][14] = StringUtil.objectToString(currencyCodeNameMap.get(vo.getTradeCurrency()));
            content[i][15] = StringUtil.objectToString(vo.getTradeAmount());
            content[i][16] = StringUtil.objectToString(vo.getPurpose());
            content[i][17] = StringUtil.objectToString(vo.getTradeSerialNum());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    private Map<String, String> getCurrencyMap() {
        Map<String, String> currencyCodeNameMap;QueryWrapper<AvBaseElement> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(AvBaseElement.DATA_TYPE,"CURRENCY");
        queryWrapper.eq(AvBaseElement.DATA_STATUS,"Y");
        List<AvBaseElement> currencyList = avBaseElementService.list(queryWrapper);
        currencyCodeNameMap = new HashMap<>(currencyList.size());
        if (CollectionUtils.isNotEmpty(currencyList)){
            for(AvBaseElement element : currencyList){
                currencyCodeNameMap.put(element.getDataCode(),element.getDataDesc());
            }
        }
        return currencyCodeNameMap;
    }

}



