package com.deloitte.services.fssc.engine.manual.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.base.vo.ModifyMainTypeStatusForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessPassOrRejectForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessStartForm;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.dic.vo.DicValueVo;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.platform.api.fssc.engine.manual.param.AvBusinessForEnterAccountForm;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherHeadEditQueryForm;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherHeadQueryForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.*;
import com.deloitte.platform.api.fssc.file.param.FileQueryForm;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.deloitte.services.fssc.business.carryforward.service.IIncomeOfCarryForwardService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvDailyRates;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.deloitte.services.fssc.engine.automatic.service.IAvBaseElementService;
import com.deloitte.services.fssc.engine.automatic.service.IAvChartOfAccountService;
import com.deloitte.services.fssc.engine.automatic.service.IAvDailyRatesService;
import com.deloitte.services.fssc.engine.automatic.service.IAvLedgerUnitRelationService;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherHeadService;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherLineService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :   AvManualVoucherHead控制器实现类
 * @Modified :
 */
@Api(tags = "手动录入凭证头信息操作接口")
@Slf4j
@RestController
@RequestMapping("/fssc/av-manual-voucher-head")
public class AvManualVoucherHeadController  {

    @Autowired
    public IAvManualVoucherHeadService avManualVoucherHeadService;
    @Autowired
    public BpmTaskBiz bpmTaskBiz;
    @Autowired
    public IAvManualVoucherLineService avManualVoucherLineService;
    @Autowired
    public IAvDailyRatesService avDailyRatesService;
    @Autowired
    public BpmProcessBiz bpmProcessBiz;
    @Autowired
    public IAvChartOfAccountService avChartOfAccountService;
    @Autowired
    public IAccountVoucherToEbsService  accountVoucherToEbsService;
    @Autowired
    public IAvBaseElementService  avBaseElementService;
    @Autowired
    public IFileService fileService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IAvLedgerUnitRelationService avLedgerUnitRelationService;
    @Autowired
    private FsscCommonServices fsscCommonServices;
    @Autowired
    private IIncomeOfCarryForwardService incomeOfCarryForwardService;

//
//    @Autowired
//    private IRecievePaymentService recievePaymentService;

    @ApiOperation(value = "新增AvManualVoucherHead", notes = "新增一个AvManualVoucherHead,并且sumbitType 设置的类型值为保存：SAVE 提交：SUMBIT")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @Transactional
    @PostMapping(value = "/addAndUpdate")
    public Result addAndUpdate(@RequestBody AvManualVoucherHeadForm avManualVoucherHeadForm) {
        log.info("form:",  avManualVoucherHeadForm.toString());
        AvManualVoucherHead avManualVoucherHead =new BeanUtils<AvManualVoucherHead>().copyObj(avManualVoucherHeadForm,AvManualVoucherHead.class);
        String  submitType = avManualVoucherHeadForm.getSubmitType();
        UserVo userVo = commonServices.getCurrentUser();
        Long userId = Long.parseLong(userVo.getId());
        try {//缺单据编码

            Boolean status = false;
            List<AvManualVoucherLineForm> lineFormList =  avManualVoucherHeadForm.getLineList();
            avManualVoucherHead.setDocumentType(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());//设置手工凭证类型
            avManualVoucherHead.setAccountStatus("N");//预制凭证
            judgementLineInfo(lineFormList);
            //生成凭证行里面的会计凭证会计结构科目描述
            List<AvAccountElement> avAccountElementsForStruList=avChartOfAccountService.selectAccountFrameList(avManualVoucherHead.getLedgerId());
            String accountStructure ="";
/*            if(avAccountElementsForStruList.size()>0){
                for(AvAccountElement p:avAccountElementsForStruList){
                    accountStructure+=p.getSegmentDesc()+".";
                }
            }*/
            String periodS ="";
            if(avManualVoucherHead.getDefaultEffectiveDate()!=null){//根据会计日期计算出期间范围
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
                LocalDateTime period = avManualVoucherHead.getDefaultEffectiveDate();
                periodS = dateFormat.format(period);
                avManualVoucherHead.setPeriodName(periodS);
            }
            /** -- 保存和更新凭证表头 --**/
            List<AvManualVoucherLine> lineOldList = new ArrayList<AvManualVoucherLine>();
            if(avManualVoucherHeadForm.getId()!=null){//编辑凭证头
                status =true;
                QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
                queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,avManualVoucherHeadForm.getId());
                lineOldList = avManualVoucherLineService.list(queryWrapper);
                avManualVoucherHead.setUpdateBy(Long.parseLong(userVo.getId()));
                avManualVoucherHead.setUpdateTime(LocalDateTime.now());
                avManualVoucherHeadService.updateById(avManualVoucherHead);
            }else{//新增凭证头
                avManualVoucherHead.setCreateBy(userId);
                avManualVoucherHead.setCurrencyConversionType("CNY");
                avManualVoucherHead.setPostStatus("N");
                avManualVoucherHead.setAccrualRevFlag("N");
                avManualVoucherHead.setCreateTime(LocalDateTime.now());
                avManualVoucherHeadService.saveOrUpdate(avManualVoucherHead);
            }
            if(StringUtils.isNotEmpty(avManualVoucherHeadForm.getCarrayStatus())){
                if(avManualVoucherHeadForm.getCarrayIdList()!=null){
                    for(int i=0;i<avManualVoucherHeadForm.getCarrayIdList().size();i++){
                        IncomeOfCarryForward carForwardEntity = new IncomeOfCarryForward();
                        carForwardEntity.setId(avManualVoucherHeadForm.getCarrayIdList().get(i));
                        carForwardEntity.setJeHeaderId(avManualVoucherHead.getId());
                        carForwardEntity.setStatus("Y");
                        incomeOfCarryForwardService.saveOrUpdate(carForwardEntity);
                    }
                }
            }
            //保存冲销时候需要填充关联之前被冲销的单子
            if(avManualVoucherHead.getFromRecurringHeaderId()!=null){
                if(avManualVoucherHead.getFromRecurringHeaderId()>0){
                    AvManualVoucherHead oldEntity = new AvManualVoucherHead();
                    //更新原被冲销的单子
                    oldEntity.setId(avManualVoucherHead.getFromRecurringHeaderId());
                    oldEntity.setAccrualRevJeHeaderId(avManualVoucherHead.getId());
                    oldEntity.setAccrualRevFlag("Y");
                    oldEntity.setUpdateTime(LocalDateTime.now());
                    oldEntity.setUpdateBy(userId);
                    oldEntity.setDocumentStatus(FsscEums.CHARGE_AGAINSTING.getValue());
                    avManualVoucherHeadService.updateById(oldEntity);
                }
            }
            List<AvManualVoucherLine> lineList=new BeanUtils<AvManualVoucherLine>().copyObjs(lineFormList,AvManualVoucherLine.class);
            List<Long> lineIdArray = new ArrayList<Long>();
            for(int i=0;i<lineList.size();i++){
                AvManualVoucherLine lineEntity = new AvManualVoucherLine();
                lineEntity = lineList.get(i);
                lineEntity.setJeHeaderId(avManualVoucherHead.getId());
                lineEntity.setPeriodName(periodS);
                lineEntity.setDocumentNum(avManualVoucherHead.getDocumentNum());
                lineEntity.setAccountStructure(accountStructure);//凭证行信息会计科目结构
                if(lineEntity.getEnteredDr().compareTo(BigDecimal.ZERO)==0){
                    lineEntity.setVoucherType("CR");//贷方
                }else{
                    lineEntity.setVoucherType("DR");//借方
                }
                if(lineEntity.getId()!=null){//编辑凭证行
                    lineEntity.setUpdateBy(userId);
                    lineEntity.setUpdateTime(LocalDateTime.now());
                    avManualVoucherLineService.saveOrUpdate(lineEntity);
                }else{//新增凭证行
                    lineEntity.setCreateBy(userId);
                    lineEntity.setLedgerId(avManualVoucherHead.getLedgerId());
                    lineEntity.setCreateTime(LocalDateTime.now());
                    avManualVoucherLineService.saveOrUpdate(lineEntity);
                }
                lineIdArray.add(lineEntity.getId());
            }
            if(status){
                for(int i=0;i<lineOldList.size();i++){
                    AvManualVoucherLine lineEntity = new AvManualVoucherLine();
                    lineEntity = lineOldList.get(i);
                    if(!lineIdArray.contains(lineEntity.getId())){//移除删除的凭证行
                        avManualVoucherLineService.removeById(lineEntity.getId());
                    }
                }
            }
            //手工录入凭证保存附件-》逻辑步骤：先存附件数据，返回id集合，通过form提交关联手工录入凭证
            if(avManualVoucherHeadForm.getFileList()!=null){
                List<Long> fileIds = avManualVoucherHeadForm.getFileList();
                for(Long p:fileIds){
                    File file = new File();
                    file.setId(p);
                    file.setDocumentId(avManualVoucherHead.getId());
                    file.setDocumentType(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());
                    fileService.saveOrUpdate(file);
                }

            }
            //因为单据编号是在保存到数据库的时候写的统一方法时候生成，所以需要再次保存一下。
            if(!StringUtils.isNotEmpty(avManualVoucherHead.getName())){
                avManualVoucherHead.setName(avManualVoucherHead.getCreateUserName()+"_"+avManualVoucherHead.getJeCategory()+"_"+avManualVoucherHead.getDocumentNum());//新增获取凭证名
                avManualVoucherHead.setAccountResourceTypeId(avManualVoucherHead.getId());
                avManualVoucherHeadService.saveOrUpdate(avManualVoucherHead);
            }

            if(submitType.equals("SUBMIT")){//手工凭证提交
                //先交叉验证规则
               List<Long> jeHeads = new ArrayList();
                jeHeads.add(avManualVoucherHead.getId());
                EbsReturnStatusVo msg =  accountVoucherToEbsService.CrossValidationRuleSendToEsb("",jeHeads);
                if(msg.getStatus()){
                    ProcessStartForm processEntity = new ProcessStartForm();
                    if(avManualVoucherHeadForm.getDocumentStatus().equals(FsscEums.NEW.getValue())){
                        processEntity.setDocumentId(avManualVoucherHead.getId());
                        processEntity.setDocumentNum(avManualVoucherHead.getDocumentNum());
                        processEntity.setDocumentType(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());
                        bpmProcessBiz.startProcess(processEntity);
                    }else{
                        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
                        startForm.setDocumentId(avManualVoucherHead.getId());
                        startForm.setDocumentNum(avManualVoucherHead.getDocumentNum());
                        startForm.setDocumentType(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());
                        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
                        startForm.setReSubmitType(avManualVoucherHeadForm.getDocumentStatus().equals(FsscEums.REJECTED.getValue())?FsscEums.RESUBMIT_REJECT.getValue():FsscEums.RESUBMIT_ROLLBACK.getValue());
                        bpmProcessBiz.reSubmit(startForm);
                    }

                }else{
                    //如果交叉验证失败的情况下直接返回数据到前台
                    throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,msg.getErrMsg());
                }
                //avManualVoucherHead.setDocumentStatus(FsscEums.SUBMIT.getValue());
                //avManualVoucherHeadService.saveOrUpdate(avManualVoucherHead);
            }

        }catch (Exception e){
            log.error("保存手工凭证失败"+e.getStackTrace());
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,e.getMessage());
        }
        return Result.success(avManualVoucherHead.getId().toString());//返回手工凭证编号，便于存储附件文件需要得Id
    }

    private  String parseCoaCode(AvManualVoucherLine line){
        List<String> coaCode = new ArrayList<String>();
        coaCode.add(line.getSegment1());
        if(line.getSegment2()!=null){
            coaCode.add(line.getSegment2());
        }
        if(line.getSegment3()!=null){
            coaCode.add(line.getSegment3());
        }
        if(line.getSegment4()!=null){
            coaCode.add(line.getSegment4());
        }
        if(line.getSegment5()!=null){
            coaCode.add(line.getSegment5());
        }
        if(line.getSegment6()!=null){
            coaCode.add(line.getSegment6());
        }
        if(line.getSegment7()!=null){
            coaCode.add(line.getSegment7());
        }
        if(line.getSegment8()!=null){
            coaCode.add(line.getSegment8());
        }
        if(line.getSegment9()!=null){
            coaCode.add(line.getSegment9());
        }
        if(line.getSegment10()!=null){
            coaCode.add(line.getSegment10());
        }
        String coaDesc ="";
        QueryWrapper<AvBaseElement> queryWrapper = new QueryWrapper <AvBaseElement>();
        queryWrapper.in(AvBaseElement.DATA_CODE,coaCode);
        List<AvBaseElement> baseList =  avBaseElementService.list(queryWrapper);
        for(AvBaseElement p:baseList){
            coaDesc+=p.getDataDesc();
        }
        return  coaDesc;
    }

    //主要判断凭证行信息的借贷相等
    private  void judgementLineInfo( List<AvManualVoucherLineForm>lineFormList ){
        if(lineFormList.size()<=1){
            //凭证行信息不能少于2行数据（至少是一借一贷）
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_MANUALVOUCHERhEAD_IS_NULL.getMsg());
        }else{
            BigDecimal cJTotal = new BigDecimal("0");//财-借
            BigDecimal cDTotal = new BigDecimal("0");//财-贷
            BigDecimal yJTotal = new BigDecimal("0");//预-借
            BigDecimal yDTotal = new BigDecimal("0");//预-贷
            for(int i=0;i<lineFormList.size();i++){
                AvManualVoucherLineForm p = lineFormList.get(i);
                if(p.getEnteredCr().compareTo(BigDecimal.ZERO)==0&&p.getEnteredDr().compareTo(BigDecimal.ZERO)==0){//主要判断借贷是否都是小于等于0
                    throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_ZERO);
                }
                /*if(p.getEnteredCr().compareTo(BigDecimal.ZERO)>0&&p.getEnteredDr().compareTo(BigDecimal.ZERO)>0){//主要判断借贷是否都是大于0
                    throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_GT_ZERO);
                }*/
                if(p.getLineTypeCode().equals(FsscEums.AV_ACCOUNT_CAI.getValue())){//财
                    cJTotal= cJTotal.add(p.getEnteredDr());
                    cDTotal =cDTotal.add(p.getEnteredCr());
                }
                if(p.getLineTypeCode().equals(FsscEums.AV_ACCOUNT_YU.getValue())){//预
                    yJTotal=yJTotal.add(p.getEnteredDr());
                    yDTotal=yDTotal.add(p.getEnteredCr());
                }
            }
            if(cJTotal.compareTo(cDTotal)!=0){
                throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_JD_NO_EQUAL);
            }
            if(yJTotal.compareTo(yDTotal)!=0){
                throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_JD_NO_EQUAL);
            }
        }


    }


    @ApiOperation(value = "删除手工凭证", notes = "根据url的id来指定删除对象")
    @DeleteMapping (value = "/delete")
    @Transactional
    public Result delete(@RequestParam("jeHeaderId") Long jeHeaderId) {
        String  info ="";
        try {
            UserVo userVo = commonServices.getCurrentUser();
            Long userId = Long.parseLong(userVo.getId());
            AvManualVoucherHead entity = avManualVoucherHeadService.getById(jeHeaderId);
            if(FsscEums.NEW.getValue().equals(entity.getDocumentStatus())||FsscEums.RECALLED.getValue().equals(entity.getDocumentStatus())){
                //删除冲销时候需要填充关联之前被冲销的单子
                if(entity.getFromRecurringHeaderId()!=null){
                    if(entity.getFromRecurringHeaderId()>0){
                        AvManualVoucherHead oldEntity = new AvManualVoucherHead();
                        //更新原被冲销的单子
                        oldEntity.setId(entity.getFromRecurringHeaderId());
                        oldEntity.setAccrualRevJeHeaderId(entity.getId());
                        oldEntity.setAccrualRevFlag("Y");
                        oldEntity.setUpdateTime(LocalDateTime.now());
                        oldEntity.setUpdateBy(userId);
                        oldEntity.setDocumentStatus(FsscEums.HAS_ACCOUTED.getValue());
                        avManualVoucherHeadService.updateById(oldEntity);
                    }
                }
                if(StringUtils.isNotEmpty(entity.getCarrayStatus())){//是否结转，如果结转需要取消掉关联关系
                    QueryWrapper<IncomeOfCarryForward> queryWrapperForward = new QueryWrapper<>();
                    queryWrapperForward.eq(IncomeOfCarryForward.JE_HEADER_ID,entity.getId());
                    List<IncomeOfCarryForward> incomeList = incomeOfCarryForwardService.list(queryWrapperForward);
                    for(IncomeOfCarryForward p:incomeList){
                        p.setJeHeaderId(null);
                        p.setStatus("N");
                        incomeOfCarryForwardService.saveOrUpdate(p);
                    }
                }
                avManualVoucherHeadService.removeById(jeHeaderId);
                QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
                queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,jeHeaderId);
                avManualVoucherLineService.remove(queryWrapper);
                info="删除成功";
                //停止流程
                bpmProcessBiz.stopProcess(jeHeaderId);
            }else{
                info ="只有新建,已撤回的凭证才能删除";
            }

        }catch (Exception e){
            log.error("删除手工凭证报错"+e);
            info ="删除失败";
        }
        return Result.success(info);
    }


    @ApiOperation(value = "修改AvManualVoucherHead", notes = "修改指定AvManualVoucherHead信息")
    @PostMapping(value = "/update")
    public Result update(AvManualVoucherHeadForm avManualVoucherHeadForm) {
        AvManualVoucherHead avManualVoucherHead =new BeanUtils<AvManualVoucherHead>().copyObj(avManualVoucherHeadForm,AvManualVoucherHead.class);
        avManualVoucherHead.setId(avManualVoucherHeadForm.getId());
        avManualVoucherHeadService.saveOrUpdate(avManualVoucherHead);
        return Result.success();
    }


    @ApiOperation(value = "获取AvManualVoucherHead", notes = "获取指定ID的AvManualVoucherHead信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvManualVoucherHead的ID", required = true, dataType = "long")
    @GetMapping(value = "/getInfo")
    public Result<AvManualVoucherHeadVo> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        AvManualVoucherHead avManualVoucherHead=avManualVoucherHeadService.getById(id);
        AvManualVoucherHeadVo avManualVoucherHeadVo=new BeanUtils<AvManualVoucherHeadVo>().copyObj(avManualVoucherHead,AvManualVoucherHeadVo.class);
        return new Result<AvManualVoucherHeadVo>().sucess(avManualVoucherHeadVo);
    }

    @ApiOperation(value = "列表查询AvManualVoucherHead", notes = "根据条件查询AvManualVoucherHead列表数据")
    @GetMapping(value = "/getList")
    public Result<List<AvManualVoucherHeadVo>> list(AvManualVoucherHeadQueryForm avManualVoucherHeadQueryForm) {
        log.info("search with avManualVoucherHeadQueryForm:", avManualVoucherHeadQueryForm.toString());
        List<AvManualVoucherHead> avManualVoucherHeadList=avManualVoucherHeadService.selectList(avManualVoucherHeadQueryForm);
        List<AvManualVoucherHeadVo> avManualVoucherHeadVoList=new BeanUtils<AvManualVoucherHeadVo>().copyObjs(avManualVoucherHeadList,AvManualVoucherHeadVo.class);
        return new Result<List<AvManualVoucherHeadVo>>().sucess(avManualVoucherHeadVoList);
    }


    @ApiOperation(value = "分页查询AvManualVoucherHead", notes = "根据条件查询AvManualVoucherHead分页数据")
    @GetMapping(value = "/getPageList")
    public Result<IPage<AvManualVoucherHeadVo>> search( AvManualVoucherHeadQueryForm avManualVoucherHeadQueryForm) {
        log.info("search with avManualVoucherHeadQueryForm:", avManualVoucherHeadQueryForm.toString());
        IPage<AvManualVoucherHead> avManualVoucherHeadPage=avManualVoucherHeadService.selectPage(avManualVoucherHeadQueryForm,false);
        IPage<AvManualVoucherHeadVo> avManualVoucherHeadVoPage=new BeanUtils<AvManualVoucherHeadVo>().copyPageObjs(avManualVoucherHeadPage,AvManualVoucherHeadVo.class);
        return new Result<IPage<AvManualVoucherHeadVo>>().sucess(avManualVoucherHeadVoPage);
    }


    @ApiOperation(value = "分页查全部的预制凭证", notes = "根据条件查询AvManualVoucherHead分页数据")
    @GetMapping(value = "/getAllPageList")
    public Result<IPage<AvManualVoucherHeadVo>> getAllPageList( AvManualVoucherHeadQueryForm avManualVoucherHeadQueryForm) {
        log.info("search with avManualVoucherHeadQueryForm:", avManualVoucherHeadQueryForm.toString());
        IPage<AvManualVoucherHead> avManualVoucherHeadPage=avManualVoucherHeadService.selectPage(avManualVoucherHeadQueryForm,true);
        IPage<AvManualVoucherHeadVo> avManualVoucherHeadVoPage=new BeanUtils<AvManualVoucherHeadVo>().copyPageObjs(avManualVoucherHeadPage,AvManualVoucherHeadVo.class);
        return new Result<IPage<AvManualVoucherHeadVo>>().sucess(avManualVoucherHeadVoPage);
    }

    @ApiModelProperty(value="撤回手工凭证申请",notes="撤回手工凭证信息根据审批状态")
    @Transactional
    @GetMapping(value="/recallApply")
    public Result  recallApply(ModifyMainTypeStatusForm form, HttpServletRequest request, HttpServletResponse response){
       StringBuffer info = new StringBuffer();
       Boolean status = true;
        UserVo userVo = commonServices.getCurrentUser();
        Long userId = Long.parseLong(userVo.getId());
        try{
            List<Long> jeHeaderIds = form.getIds();
            for(int i=0;i<jeHeaderIds.size();i++){
                if(jeHeaderIds.get(i)>0){
                    AvManualVoucherHead entity = avManualVoucherHeadService.getById(jeHeaderIds.get(i));
                    if(entity.getDocumentStatus().equals(FsscEums.NEW.getValue())){
                        continue;
                    }
                    List<BpmProcessTaskVo>  taskList = bpmTaskBiz.findHistory(entity.getId(),FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());//获取审批历史;
                    if(taskList.size()<=1){
                        //撤回申请流程
                        ProcessPassOrRejectForm rejectEntity = new ProcessPassOrRejectForm();
                        rejectEntity.setDocumentType(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());
                        rejectEntity.setDocumentId(entity.getId());
                        rejectEntity.setDocumentNum(entity.getDocumentNum());
                        bpmProcessBiz.rollBackProcess(rejectEntity);
                        entity.setUpdateBy(userId);
                        entity.setUpdateTime(LocalDateTime.now());
                        entity.setDocumentStatus(FsscEums.NEW.getValue());
                        avManualVoucherHeadService.saveOrUpdate(entity);
                    }else{
                        info.append("手工编码"+entity.getDocumentNum()+"已经开始审批不能撤回").append("/n");
                        status = false;
                    }
                }

            }

        }catch (Exception e){
            log.error("撤回失败："+e);
            status =false;
        }
        if(status){
            info.append("撤回成功！");
        }
        return Result.success(info.toString());
    }
    @ApiModelProperty(value = "获取凭证类别",notes="根据账目获取凭证类别")
    @PostMapping("/getAccountCategory")
    public Result<List<AvBaseElement>> getAccountCategory(){
        List<AvBaseElement> list = new ArrayList<AvBaseElement>();
        try {

        }catch (Exception e){
            log.error("获取失败");
        }
        return  Result.success(list);
    }
    @ApiModelProperty(value = "获取当日汇率",notes ="根据当日和选取的币种获取当前的汇率")
    @GetMapping("/getDailyRates")
    public Result<AvDailyRates> getDailyRates(@RequestParam("currentDate")String currentDate,@RequestParam("toCurrency")String toCurrency){
        AvDailyRates entity = new AvDailyRates();
        QueryWrapper<AvDailyRates> queryWrapper = new QueryWrapper <AvDailyRates>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(currentDate,df);
        queryWrapper.eq(AvDailyRates.FROM_CURRENCY,toCurrency);//这边因为跟EBS取了反方向，造成数据有点问题。
        LocalDateTime today_start = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MIN);//获取当前选取时间的当天开始时间
        LocalDateTime today_End = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MAX);//获取当前选取时间的当天结束时间
        queryWrapper.ge(AvDailyRates.CONVERSION_DATE,today_start);
        queryWrapper.le(AvDailyRates.CONVERSION_DATE,today_End);
        entity = avDailyRatesService.getOne(queryWrapper);
        if(entity==null){
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,"该币种在当前汇率日期上没有汇率");
        }
        return  Result.success(entity);
    }

    @ApiModelProperty(value="获取手工凭证单据得审批历史",notes="获取手工凭证单据得审批历史")
    @GetMapping("/processTask")
    public Result<List<BpmProcessTaskVo>> processTask(@RequestParam("id")Long id, HttpServletRequest request, HttpServletResponse response){
        List<BpmProcessTaskVo>  taskList = new ArrayList<BpmProcessTaskVo>();
        try{
            if(id!=null){
                 taskList = bpmTaskBiz.findHistory(id,FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());//获取审批历史;
            }

        }catch (Exception e){
            log.error("获取审批历史失败："+e);
        }
        return Result.success(taskList);
    }

    @ApiModelProperty(value = "获取得到预制凭证信息",notes="根据单据类型,单据ID,人员信息获取得到预制凭证")
    @GetMapping("/parkedDocument")
    public Result<List<AvManualVoucherHeadVo>> parkedDocument(@RequestParam("documentType")String documentType,@RequestParam("documentId")Long documentId,@RequestParam("accountStatus")String accountStatus){
        List<AvManualVoucherHead> headList = new ArrayList<AvManualVoucherHead>();
        UserVo userVo = commonServices.getCurrentUser();
        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper <AvManualVoucherHead>();
        queryWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE,documentType);
        queryWrapper.eq(AvManualVoucherHead.ACCOUNT_RESOURCE_TYPE_ID,documentId);
        //queryWrapper.eq(AvManualVoucherHead.ACCOUNT_STATUS,accountStatus);
        headList = avManualVoucherHeadService.list(queryWrapper);
        List<AvManualVoucherHeadVo> voList = new ArrayList<AvManualVoucherHeadVo>();
        try{
            if(headList.size()>0){
                for (AvManualVoucherHead entity:headList){
                    QueryWrapper<AvManualVoucherLine> queryWrapperLine = new QueryWrapper <AvManualVoucherLine>();
                    queryWrapperLine.eq(AvManualVoucherLine.JE_HEADER_ID,entity.getId());
                    List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapperLine);
                    AvManualVoucherHeadVo voEntity = new AvManualVoucherHeadVo();
                    voEntity=new BeanUtils<AvManualVoucherHeadVo>().copyObj(entity,AvManualVoucherHeadVo.class);
                    List<AvManualVoucherLineVo> avLineList=new BeanUtils<AvManualVoucherLineVo>().copyObjs(lineList,AvManualVoucherLineVo.class);
                    voEntity.setLineList(avLineList);
                    voList.add(voEntity);
                }

            }
        }catch (Exception e){
            throw new FSSCException(FsscErrorType.SQL_EXCEPTION);
        }
        return  Result.success(voList);
    }


    @ApiModelProperty(value = "获取得到冲销分录信息",notes="根据单据类型,单据ID,获取得到冲销分录")
    @GetMapping("/eliminationEntry")
    public Result<AvManualVoucherHeadVo> eliminationEntry(@RequestParam("documentType")String documentType,@RequestParam("documentId")Long documentId){
        AvManualVoucherHead entity = new AvManualVoucherHead();
        AvManualVoucherHeadVo voEntity = new AvManualVoucherHeadVo();
        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper <AvManualVoucherHead>();
        queryWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE,documentType);
        queryWrapper.eq(AvManualVoucherHead.ACCOUNT_RESOURCE_TYPE_ID,documentId);
        UserVo userVo = commonServices.getCurrentUser();
        //queryWrapper.eq(AvManualVoucherHead.CREATE_BY,111L);
        queryWrapper.eq(AvManualVoucherHead.ACCRUAL_REV_FLAG,"N");
        entity = avManualVoucherHeadService.getOne(queryWrapper);
        try{
            if(entity!=null){
                QueryWrapper<AvManualVoucherLine> queryWrapperLine = new QueryWrapper <AvManualVoucherLine>();
                queryWrapperLine.eq(AvManualVoucherLine.ID,entity.getId());
                List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapperLine);
                voEntity=new BeanUtils<AvManualVoucherHeadVo>().copyObj(entity,AvManualVoucherHeadVo.class);
                List<AvManualVoucherLineVo> avLineList=new BeanUtils<AvManualVoucherLineVo>().copyObjs(lineList,AvManualVoucherLineVo.class);
                voEntity.setLineList(avLineList);
            }
        }catch (Exception e){
            throw new FSSCException(FsscErrorType.SQL_EXCEPTION);
        }
        return  Result.success(voEntity);
    }


    @ApiModelProperty(value = "手工录入凭证入账操作",notes="根据手工录入的Id信息进行入账操作")
    @Transactional
    @GetMapping("/enterAccount")
    public Result enterAccount(ModifyMainTypeStatusForm form){
        List<Long> jeHeadIds = form.getIds();
        if(jeHeadIds.size()>0){
            String  url ="";
            EbsReturnStatusVo entity =   accountVoucherToEbsService.sendAccountVoucherSendToEbs(url,jeHeadIds);
            if(!entity.getStatus()){
                throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,entity.getErrMsg());
                //throw new FSSCException(FsscErrorType.AV_VOUCHERHEADER_IS_ERROR);
            }
        }
        return  Result.success();
    }

    @ApiModelProperty(value = "业务凭证点击入账操作",notes="根据业务单据的Id和单据类型信息进行入账操作")
    @Transactional
    @GetMapping("/enterAccountForBusiness")
    public Result enterAccountForBusiness(AvBusinessForEnterAccountForm form){
        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper <AvManualVoucherHead>();
        queryWrapper.eq(AvManualVoucherHead.ACCOUNT_RESOURCE_TYPE_ID,form.getDocumentId());
        queryWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE,form.getDocumentType());
        queryWrapper.eq(AvManualVoucherHead.ACCRUAL_REV_FLAG,"N");
        List<AvManualVoucherHead> headList =  avManualVoucherHeadService.list(queryWrapper);
        UserVo userVo = commonServices.getCurrentUser();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
        try{
            if(headList.size()>0){

                String  url ="";
                AvManualVoucherHead headEntity = new AvManualVoucherHead();
                headEntity = headList.get(0);
                if(headEntity.getDocumentStatus().equals(FsscEums.ACCOUNT_FAILD.getValue())){
                    accountVoucherToEbsService.generatePrefabricatedCredentials(form.getDocumentType(),form.getDocumentId());
                    List<AvManualVoucherHead> headNewList =  avManualVoucherHeadService.list(queryWrapper);
                    headEntity = headNewList.get(0);
                }
                List<Long> jeHeadIds = new ArrayList<Long>();
                jeHeadIds.add(headEntity.getId());
                headEntity.setDefaultEffectiveDate(form.getDefaultEffectiveDate());//先保存会计日期
                headEntity.setPeriodName(dateFormat.format(form.getDefaultEffectiveDate())+"");//会计期间
                headEntity.setAccountStatus("Y");

                //headEntity.setDocumentStatus(FsscEums.IN_ACCOUNTING.getValue());
                avManualVoucherHeadService.saveOrUpdate(headEntity);
                //对于业务单据生成的会计凭证,先进行会计凭证的交叉验证规则验证，完成之后在能提交会计凭证到ebs;
                EbsReturnStatusVo msg= accountVoucherToEbsService.CrossValidationRuleSendToEsb(url,jeHeadIds);
                if(msg.getStatus()){
                    EbsReturnStatusVo msg1 = accountVoucherToEbsService.sendAccountVoucherSendToEbs(url,jeHeadIds);
                    if(!msg1.getStatus()){
                        throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,msg1.getErrMsg());
                    }
//                        //收款单 认领状态改变 需求变为提交的时候变
//                        recievePaymentService.modifyCliamStatus(form.getDocumentType(),form.getDocumentId(),true);

                }else{
                    throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,msg.getErrMsg());
                }
            }else{
                throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_MANUAL_NOT_CARE.getMsg());
            }
        }catch (Exception e){
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,e.getMessage());

        }
        return  Result.success();
    }

    @ApiModelProperty(value = "业务凭证点击冲销操作",notes="根据业务单据的Id和单据类型信息进行冲销操作")
    @Transactional
    @GetMapping("/reverseAccountForBusiness")
    public Result reverseAccountForBusiness(AvBusinessForEnterAccountForm form){
      accountVoucherToEbsService.reverseAccountForBusiness(form.getDocumentType(),form.getDocumentId(),form.getDefaultEffectiveDate());
        return  Result.success();
    }


    @ApiOperation(value = "导出", httpMethod = "get" , notes = "导出")
    @ApiParam(name = "queryForm", value = "导出账薄信息", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, AvManualVoucherHeadQueryForm queryForm){
        log.info("exportExcel with AvAccountElementQueryForm:{}", JSON.toJSONString(queryForm));
        List<AvManualVoucherHead> records = avManualVoucherHeadService.selectList(queryForm);
        List<DicValueVo> list = fsscCommonServices.findDicValueList("DOCUMENT_STATUS");
        Map<String,String> map = new HashMap<String,String>();
        for(DicValueVo vo:list){
            map.put(vo.getDicValue(),vo.getDicDesciption());
        }
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("单据编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("凭证编号").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("凭证名").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("单位名称").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("业务类别").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("原币金额").setOneCellWidth(5000));
        headerList.add(new ExcelHeader("本币金额").setOneCellWidth(5000));
        headerList.add(new ExcelHeader("币种").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("会计日期").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("申请日期").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("制单人").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("摘要").setOneCellWidth(5000));
        headerList.add(new ExcelHeader("状态").setOneCellWidth(3000));
        String fileName = "总账手工凭证";
        Object[][] content = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[headerList.size()];
            AvManualVoucherHead vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getDocSeqNum();
            content[i][3] = vo.getName();
            content[i][4] = vo.getUnitName();
            content[i][5] = vo.getJeCategory();
            content[i][6] = vo.getTotalOriginalAmount()+"";
            content[i][7] = vo.getTotalStandardAmount()+"";
            content[i][8] = vo.getCurrencyCode();
            content[i][9] = vo.getDefaultEffectiveDate()==null?"":vo.getDefaultEffectiveDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"";
            content[i][10] = vo.getCreateTime()==null?"":vo.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"";
            content[i][11] = vo.getCreateUserName();
            content[i][12] = vo.getDescription();
            content[i][13] = map.get(vo.getDocumentStatus());
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }
    @ApiOperation(value = "获取手工凭证新建，复制，编辑，查看实体，冲销", notes = "根据凭证头Id获取实体值 pageType：NEW,COPY,EDIT,VIEW,REVERSE")
    @GetMapping(value = "/editPage")
    public Result<AvManualVoucherHeadVo> editPage(AvManualVoucherHeadEditQueryForm form) {
        UserVo userVo = commonServices.getCurrentUser();
        DeptVo deptVo = commonServices.getCurrentDept();
        AvManualVoucherHeadVo entity = new AvManualVoucherHeadVo();

        String pageType = form.getPageType();
        Long jeHeaderId = form.getId();

        String  name =userVo.getName();
        if(FsscEums.AV_PAGE_STATUS_NEW.getValue().equals(pageType)){
            QueryWrapper<AvLedgerUnitRelation> queryWrapperQ = new QueryWrapper <AvLedgerUnitRelation>();
            queryWrapperQ.eq(AvLedgerUnitRelation.BALANCE_CODE,deptVo.getDeptCode());
            AvLedgerUnitRelation aurEntity =  avLedgerUnitRelationService.getOne(queryWrapperQ);
            if(aurEntity!=null){
                entity.setLedgerId(aurEntity.getLedgerId());
            }
            if(StringUtils.isNotEmpty(form.getCarrayStatus())){
                entity.setCarrayStatus("Y");
                 if(form.getCarrayIdList().size()>0){
                     List<AvManualVoucherLineVo> lineList = new ArrayList<>();
                     BigDecimal money = new BigDecimal(0);
                     for(int i=0;i<form.getCarrayIdList().size();i++){
                         AvManualVoucherLineVo lineDrVo = new AvManualVoucherLineVo();
                         AvManualVoucherLineVo lineCrVo = new AvManualVoucherLineVo();
                         if(form.getCarrayIdList().get(i)<=0){
                             continue;
                         }
                         //借方
                         IncomeOfCarryForward incomeEntity =  incomeOfCarryForwardService.getById(form.getCarrayIdList().get(i));
                         lineDrVo.setAccountedDr(incomeEntity.getMoney());
                         lineDrVo.setAccountedCr(new BigDecimal(0));
                         lineDrVo.setEnteredCr(new BigDecimal(0));
                         lineDrVo.setEnteredDr(incomeEntity.getMoney());
                         lineDrVo.setSegment1(incomeEntity.getEtx1());//取得依托单位编码
                         lineDrVo.setSegment2("0");
                         lineDrVo.setSegment3(incomeEntity.getDrAccountCode());
                         lineDrVo.setSegment4(incomeEntity.getFsscCode());
                         lineDrVo.setSegment5("0");
                         lineDrVo.setSegment6("0");
                         lineDrVo.setSegment7("0");
                         lineDrVo.setSegment8("0");
                         lineDrVo.setSegment9("0");
                         lineDrVo.setSegment10("0");
                         lineDrVo.setAccountStructureCode(incomeEntity.getEtx1()+"."+"0."+incomeEntity.getDrAccountCode()+"."+incomeEntity.getFsscCode()+"0.0.0.0.0.0");
                         lineDrVo.setDescription(incomeEntity.getProjectName()+"项目收入结转");
                         lineDrVo.setVoucherType("DR");
                         lineDrVo.setLineTypeCode("C");
                         lineDrVo.setLedgerId(aurEntity.getLedgerId());
                         //贷方
                         lineCrVo.setAccountedDr(new BigDecimal(0));
                         lineCrVo.setAccountedCr(incomeEntity.getMoney());
                         lineCrVo.setEnteredCr(incomeEntity.getMoney());
                         lineCrVo.setEnteredDr(new BigDecimal(0));
                         lineCrVo.setSegment1(incomeEntity.getEtx1());//取得依托单位编码
                         lineCrVo.setSegment2("0");
                         lineCrVo.setSegment3(incomeEntity.getCrAccountCode());
                         lineCrVo.setSegment4(incomeEntity.getFsscCode());
                         lineCrVo.setSegment5("0");
                         lineCrVo.setSegment6("0");
                         lineCrVo.setSegment7("0");
                         lineCrVo.setSegment8("0");
                         lineCrVo.setSegment9("0");
                         lineCrVo.setSegment10("0");
                         lineCrVo.setAccountStructureCode(incomeEntity.getEtx1()+"."+"0."+incomeEntity.getCrAccountCode()+"."+incomeEntity.getFsscCode()+"0.0.0.0.0.0");
                         lineCrVo.setDescription(incomeEntity.getProjectName()+"项目收入结转");
                         lineCrVo.setVoucherType("CR");
                         lineCrVo.setLineTypeCode("C");
                         lineCrVo.setLedgerId(aurEntity.getLedgerId());
                         lineList.add(lineDrVo);
                         lineList.add(lineCrVo);
                         money = money.add(incomeEntity.getMoney()==null?new BigDecimal("0"):incomeEntity.getMoney());
                     }
                     entity.setLineList(lineList);
                     entity.setTotalOriginalAmount(money);
                     entity.setTotalStandardAmount(money);
                     entity.setCarrayIdList(form.getCarrayIdList());
                 }
            }
            entity.setCreateUserName(name);
            entity.setUnitId(deptVo.getDeptId());
            entity.setUnitName(deptVo.getDeptName());
            entity.setDocumentStatus(FsscEums.NEW.getValue());
            //新建手工凭证
        }else if(FsscEums.AV_PAGE_STATUS_COPY.getValue().equals(pageType)){
            //复制
            if(jeHeaderId!=null){
                AvManualVoucherHead  headEntity= avManualVoucherHeadService.getById(jeHeaderId);
                QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
                queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,jeHeaderId);
                List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapper);
                entity=new BeanUtils<AvManualVoucherHeadVo>().copyObj(headEntity,AvManualVoucherHeadVo.class);
                List<AvManualVoucherLineVo> avLineList=new BeanUtils<AvManualVoucherLineVo>().copyObjs(lineList,AvManualVoucherLineVo.class);
                for(AvManualVoucherLineVo p:avLineList){
                    p.setId(null);
                    p.setErrorMessage(null);
                }
                entity.setLineList(avLineList);
                entity.setId(null);
                entity.setName(null);
                entity.setDocumentNum(null);
                entity.setFromRecurringHeaderId(null);
                entity.setAccrualRevJeHeaderId(null);
                entity.setDefaultEffectiveDate(null);
                entity.setPeriodName(null);
                entity.setPostedDate(null);
                entity.setDocSeqNum(null);
                entity.setAccountStatus("N");
                entity.setAccrualRevFlag("N");
                entity.setCreateUserName(name);
                entity.setPostStatus("N");
                entity.setAccountResourceTypeId(null);
                entity.setDocumentStatus(FsscEums.NEW.getValue());
            }
        }else if(FsscEums.AV_PAGE_STATUS_EDIT.getValue().equals(pageType)||FsscEums.AV_PAGE_STATUS_VIEW.getValue().equals(pageType)){
            //编辑
            AvManualVoucherHead  headEntity= avManualVoucherHeadService.getById(jeHeaderId);
            QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
            queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,jeHeaderId);
            List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapper);
            entity=new BeanUtils<AvManualVoucherHeadVo>().copyObj(headEntity,AvManualVoucherHeadVo.class);
            List<AvManualVoucherLineVo> avLineList=new BeanUtils<AvManualVoucherLineVo>().copyObjs(lineList,AvManualVoucherLineVo.class);
            entity.setLineList(avLineList);
        }else if(FsscEums.AC_PAGE_STATUS_AGAINST.getValue().equals(pageType)){
            //-冲销手工录入凭证
            LocalDateTime reverseDate = form.getReverseDate();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
            String periodS = dateFormat.format(reverseDate);;
            AvManualVoucherHead  headEntity= avManualVoucherHeadService.getById(jeHeaderId);
            QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
            queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,jeHeaderId);
            List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapper);
            entity=new BeanUtils<AvManualVoucherHeadVo>().copyObj(headEntity,AvManualVoucherHeadVo.class);
            List<AvManualVoucherLineVo> avLineList=new BeanUtils<AvManualVoucherLineVo>().copyObjs(lineList,AvManualVoucherLineVo.class);
            entity.setDocumentStatus("N");
            entity.setCreateUserName(name);
            entity.setPostStatus("N");
            entity.setPeriodName(periodS);
            entity.setName(entity.getName()+"(冲销)");
            entity.setDefaultEffectiveDate(reverseDate);
            entity.setCreateBy(null);
            entity.setId(null);//设置为空数据
            entity.setDocumentNum(null);
            entity.setCreateTime(null);
            entity.setTotalStandardAmount(entity.getTotalStandardAmount().multiply(new BigDecimal(-1)));//冲销数据本币金额为负数
            entity.setTotalOriginalAmount(entity.getTotalOriginalAmount().multiply(new BigDecimal(-1)));//冲销数据原币金额为负数
            entity.setFromRecurringHeaderId(headEntity.getId()+"");
            entity.setAccrualRevFlag("N");
            entity.setDescription(entity.getDescription()==null?"":entity.getDescription()+"(冲销)");
            entity.setDocumentStatus(FsscEums.NEW.getValue());
            for(AvManualVoucherLineVo p: avLineList){
                p.setAccountedCr(p.getAccountedCr()==null?p.getAccountedCr():p.getAccountedCr().multiply(new BigDecimal(-1)));
                p.setAccountedDr(p.getAccountedDr()==null?p.getAccountedDr():p.getAccountedDr().multiply(new BigDecimal(-1)));
                p.setEnteredCr(p.getEnteredCr()==null?p.getEnteredCr():p.getEnteredCr().multiply(new BigDecimal(-1)));
                p.setEnteredDr(p.getEnteredDr()==null?p.getEnteredDr():p.getEnteredDr().multiply(new BigDecimal(-1)));
                p.setId(null);
                p.setId(null);
            }
            entity.setLineList(avLineList);

        }
        if(jeHeaderId!=null){
            FileQueryForm fileForm = new FileQueryForm();
            fileForm.setDocumentId(jeHeaderId);
            fileForm.setDocumentType(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue());
            List<File> fileList = fileService.selectListByType(fileForm);
            List<FileVo> fileVoList=new BeanUtils<FileVo>().copyObjs(fileList,FileVo.class);
            entity.setFileInfoList(fileVoList);
        }
        entity.setPageType(pageType);

        return Result.success(entity);
    }
    @ApiOperation(value="获取得到'COA值'供手工凭证使用",notes="根据账薄和序号获取段下面的COA值")
    @GetMapping(value="/getAccountBaseElementByNumList")
    public Result<AvBaseElementVo> getAccountBaseElementByNumList(@RequestParam(name ="ledgerId",required=false)Long ledgerId,HttpServletRequest request, HttpServletResponse response){
        List<AvBaseElementVo> baseList = avManualVoucherHeadService.selectBaseElementByNum(ledgerId);
        AvBaseElementVo entity =new AvBaseElementVo();
        DeptVo deptVo = commonServices.getCurrentDept();
       // Map<String, List<AvBaseElementVo>> groupBySex = baseList.stream().collect(Collectors.groupingBy(AvBaseElementVo::getSegmentNum+""));
        entity.setSegment1(baseList.stream().filter(a -> a.getSegmentNum().equals(1L)&&a.getDataCode().equals(deptVo.getDeptCode())).collect(Collectors.toList()));
        entity.setSegment2(baseList.stream().filter(a -> a.getSegmentNum().equals(2L)).collect(Collectors.toList()));
        entity.setSegment3(baseList.stream().filter(a -> a.getSegmentNum().equals(3L)&&a.getSummaryFlag().equals("N")).collect(Collectors.toList()));
        entity.setSegment4(baseList.stream().filter(a -> a.getSegmentNum().equals(4L)).collect(Collectors.toList()));
        entity.setSegment5(baseList.stream().filter(a -> a.getSegmentNum().equals(5L)).collect(Collectors.toList()));
        entity.setSegment6(baseList.stream().filter(a -> a.getSegmentNum().equals(6L)).collect(Collectors.toList()));
        entity.setSegment7(baseList.stream().filter(a -> a.getSegmentNum().equals(7L)).collect(Collectors.toList()));
        entity.setSegment8(baseList.stream().filter(a -> a.getSegmentNum().equals(8L)).collect(Collectors.toList()));
        entity.setSegment9(baseList.stream().filter(a -> a.getSegmentNum().equals(9L)).collect(Collectors.toList()));
        entity.setSegment10(baseList.stream().filter(a -> a.getSegmentNum().equals(10L)).collect(Collectors.toList()));
        return Result.success(entity);

    }

    @ApiOperation(value="获取得到'业务类别'供手工凭证使用",notes="获取业务类别")
    @GetMapping(value="/getAccountCatagroyList")
    public Result<List<AvBaseElement>> getAccountCatagroyList(HttpServletRequest request, HttpServletResponse response){
        List<AvBaseElement> baseList = avManualVoucherHeadService.selectJeCategory();
        return Result.success(baseList);

    }

    @ApiOperation(value="生成预制凭证",notes="生成预制凭证")
    @GetMapping(value="/generatePrefabricatedCredentials")
    public Result<List<AvBaseElement>> generatePrefabricatedCredentials(AvBusinessForEnterAccountForm form,HttpServletRequest request, HttpServletResponse response){
        try{
            accountVoucherToEbsService.generatePrefabricatedCredentials(form.getDocumentType(),form.getDocumentId());
        }catch (Exception e){
            return Result.fail(e);
        }
       // List<AvBaseElement> baseList = avManualVoucherHeadService.selectJeCategory();
        return Result.success();

    }

    @ApiOperation(value="收入结转",notes="收入结转")
    @GetMapping(value="/generatePrefabricatedCredentialszzzz")
    public Result<List<AvBaseElement>> generatePrefabricatedCredentialsss(AvBusinessForEnterAccountForm form,HttpServletRequest request, HttpServletResponse response){
        try{
            bpmProcessBiz.paymentOrOverDocumentChange(form.getDocumentType(),form.getDocumentId());
        }catch (Exception e){
            return Result.fail(e);
        }
        // List<AvBaseElement> baseList = avManualVoucherHeadService.selectJeCategory();
        return Result.success();

    }





}



