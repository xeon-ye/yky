package com.deloitte.services.fssc.business.travle.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.travle.param.TasTravelApplyInfoQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetControlService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.travle.entity.*;
import com.deloitte.services.fssc.business.travle.service.*;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :   TasTravleApplyInfo控制器实现类
 * @Modified :
 */
@Api(tags = "差旅申请单操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/travle/travle-apply-info")
public class TasTravelApplyInfoController {

    @Autowired
    public ITasTravelApplyInfoService tasTravleApplyInfoService;

    @Autowired
    ITasLeaveaBjInformationService tasLeaveaBjInformationService;

    @Autowired
    public ITasCostInformationLineService tasCostInformationLineService;

    @Autowired
    public ITasTravelReimburseService tasTravelReimburseService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    public ITasTravelTimeService tasTravelTimeService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IBudgetControlService budgetControlService;

    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IBudgetProjectService budgetProjectService;
    @Autowired
    public ITasTravelStandardsService tasTravelStandardsService;
    @Autowired
    public IBorrowMoneyInfoService borrowMoneyInfoService;

    @ApiOperation(value = "差旅申请撤销", notes = "差旅申请撤销")
    @PostMapping(value = "/doChargeRevoke/{documentId}")
    public Result doChargeRevoke(@PathVariable(value = "documentId") Long documentId) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_NOT_FIND);
        //撤销对差旅申请单单据先判断其他单据关联的差旅申请单是否冲销(借款或差旅报账)
        QueryWrapper<TasTravelReimburse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("APPLY_FOR_ID", documentId);
        List<TasTravelReimburse> travelReimburseList = tasTravelReimburseService.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(travelReimburseList)) {
            for (TasTravelReimburse tasTravelReimburse : travelReimburseList) {
                String dStatus = tasTravelReimburse.getDocumentStatus();
                AssertUtils.asserts(FsscEums.HAS_CHARGE_AGAINST.getValue().equals(dStatus),
                        FsscErrorType.MUST_ORDER_CHARGE);
            }
        }

        QueryWrapper<BorrowMoneyInfo> bmWrapper = new QueryWrapper<>();
        bmWrapper.eq("APPLY_FOR_ID", documentId);
        List<BorrowMoneyInfo> borrowMoneyInfos = borrowMoneyInfoService.list(bmWrapper);
        if (CollectionUtils.isNotEmpty(borrowMoneyInfos)) {
            for (BorrowMoneyInfo borrowMoneyInfo : borrowMoneyInfos) {
                String dStatus = borrowMoneyInfo.getDocumentStatus();
                AssertUtils.asserts(FsscEums.HAS_CHARGE_AGAINST.getValue().equals(dStatus),
                        FsscErrorType.MUST_ORDER_CHARGE);
            }
        }
        TasTravelApplyInfo tasTravelApplyInfo = tasTravleApplyInfoService.getById(documentId);
        AssertUtils.asserts(tasTravelApplyInfo != null, FsscErrorType.DOCUMENT_NOT_FIND);
        String documentStatus = tasTravelApplyInfo.getDocumentStatus();
        AssertUtils.asserts(FsscEums.APPROVED.getValue().equals(documentStatus), FsscErrorType.ONLY_REVOKE_IN_ACCOUNT);
        tasTravelApplyInfo.setDocumentStatus(FsscEums.REVOKE.getValue());
        tasTravleApplyInfoService.updateById(tasTravelApplyInfo);
        budgetControlService.executeBudgetFree(documentId, FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        //todo 预算释放 会计引擎数据变更
        //撤销对差旅申请单单据先判断其他单据关联的差旅申请单是否冲销
        /*i QueryWrapper<TasTravelReimburse>  queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())
                .eq("DOCUMENT_ID",documentId);
        List<TasTravelReimburse> travelReimburseList=tasTravelReimburseService.list(queryWrapper);

       f(CollectionUtils.isNotEmpty(travelReimburseList)) {
            for (TasTravelReimburse tasTravelReimburse : travelReimburseList) {

                Long applyForId = tasTravelReimburse.getApplyForId();
                String dStatus = prepayService.findStatus(documentId2, documentType);
                AssertUtils.asserts(FsscEums.HAS_CHARGE_AGAINST.getValue().equals(dStatus),
                        FsscErrorType.MUST_ORDER_CHARGE);
            }
        }*/
        return Result.success();
    }

    /**
     * 根据单据id 获取信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getTravleById")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<TasTravelApplyInfoVo> getTravleById(Long id) {
        log.info("id:" + id);
        TasTravelApplyInfo tasTravelApplyInfo = tasTravleApplyInfoService.getById(id);
        AssertUtils.asserts(tasTravelApplyInfo != null, FsscErrorType.DOCUMENT_NOT_FIND);
        TasTravelApplyInfoVo tasTravelApplyInfoVo = new BeanUtils<TasTravelApplyInfoVo>().copyObj(tasTravelApplyInfo, TasTravelApplyInfoVo.class);
        String documentType = TasCostInformationLine.DOCUMENT_TYPE;
        String documentId = TasCostInformationLine.DOCUMENT_ID;
        //String connectCostType=TasCostInformationLine.CONNECT_COST_TYPE;//关联费用类型；交通，住宿，其他等。
        //查询费用明细
        QueryWrapper<TasCostInformationLine> costQueryWrapper = new QueryWrapper<>();
        costQueryWrapper.eq(documentId, id).eq(documentType, FsscTableNameEums.TAS_TRAVLE_APPLY_INFO);
        List<TasCostInformationLine> tasCostInformationLine = tasCostInformationLineService.list(costQueryWrapper);
        List<TasCostInformationLineVo> tasCostInformationLineVos =
                new BeanUtils<TasCostInformationLineVo>().copyObjs(tasCostInformationLine, TasCostInformationLineVo.class);
        /*//查询费用明细下的差旅时间
        for (TasCostInformationLineVo costVo:tasCostInformationLineVos) {
            QueryWrapper<TasTravelTime>  timeQueryWrapper= new QueryWrapper<>();
            timeQueryWrapper.eq("TRAVEL_LINE_ID",costVo.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_COST_INFORMATION_LINE.getValue());
            List<TasTravelTime> tasTravelTime=tasTravelTimeService.list(timeQueryWrapper);
            List<TasTravelTimeVo> timeVosVos=
                    new BeanUtils<TasTravelTimeVo>().copyObjs(tasTravelTime,TasTravelTimeVo.class);
            costVo.setTasTravelTimeVos(timeVosVos);
        }*/
        tasTravelApplyInfoVo.setTasCostInformationLineVos(tasCostInformationLineVos);
        //离京信息
        QueryWrapper<TasLeaveaBjInformation> leaveaBJWrapper = new QueryWrapper<>();
        leaveaBJWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue()).eq("DOCUMENT_ID", id);
        List<TasLeaveaBjInformation> tasLeaveaBjInformations = tasLeaveaBjInformationService.list(leaveaBJWrapper);
        List<TasLeaveaBjInformationVo> tasLeaveaBjInformationVo =
                new BeanUtils<TasLeaveaBjInformationVo>().copyObjs(tasLeaveaBjInformations, TasLeaveaBjInformationVo.class);
        tasTravelApplyInfoVo.setTasLeaveaBjInformationVos(tasLeaveaBjInformationVo);
        //审批历史
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            tasTravelApplyInfoVo.setBpmProcessTaskVos(new BeanUtils<BpmProcessTaskVo>().copyObjs(history, BpmProcessTaskVo.class));
        } catch (FSSCException e) {
            e.getMessage();
        }
        return new Result<TasTravelApplyInfoVo>().success(tasTravelApplyInfoVo);
    }

    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增或修改", notes = "新增或修改")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSaveOrUpdate(@RequestBody @Valid TasTravelApplyInfoForm tasTravelApplyInfoForm) {
        log.info("tasTravelApplyInfoForm:" + tasTravelApplyInfoForm.toString());
        return saveOrUpdate(tasTravelApplyInfoForm);
    }

    public Result saveOrUpdate(TasTravelApplyInfoForm tasTravelApplyInfoForm) {

        //验证单据是否存在
        FsscCommonUtil.valiHasData(tasTravelApplyInfoForm.getId()
                , FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());

        TasTravelApplyInfo tasTravelApplyInfo = new BeanUtils<TasTravelApplyInfo>().copyObj(tasTravelApplyInfoForm, TasTravelApplyInfo.class);
        Long projectId = tasTravelApplyInfo.getProjectId();
        if (projectId != null) {
            BudgetProject project = budgetProjectService.getById(projectId);
            if (project != null) {
                tasTravelApplyInfo.setProjectCode(project.getProjectCode());
                tasTravelApplyInfo.setProjectName(project.getProjectName());
                tasTravelApplyInfo.setProjectUnitCode(project.getResponsibleUnitCode());
                tasTravelApplyInfo.setProjectUnitName(project.getResponsibleUnitName());
                tasTravelApplyInfo.setFsscCode(project.getFsscCode());
            }
        }


        tasTravelApplyInfo.setIsReimburConnect("N");
        tasTravelApplyInfo.setIsBorrowConnect("N");
        //验证是否是淡季月份；
        validMonth(tasTravelApplyInfoForm);
        boolean saveOrUpdateSuccess = tasTravleApplyInfoService.saveOrUpdate(tasTravelApplyInfo);
        if (saveOrUpdateSuccess) {
            //保存或修改行费用信息  先删除原有的，再新增
            QueryWrapper<TasCostInformationLine> costWrapper = new QueryWrapper<>();
            costWrapper.eq("DOCUMENT_ID", tasTravelApplyInfo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            List<TasCostInformationLineForm> costInformationLineForm = tasTravelApplyInfoForm.getTasCostInformationLineForm();
            if (CollectionUtils.isNotEmpty(costInformationLineForm)) {
                List<TasCostInformationLine> tasCostInformationLineList = new BeanUtils<TasCostInformationLine>().copyObjs(costInformationLineForm, TasCostInformationLine.class);
                List<Long> longList = costInformationLineForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(longList)) {
                    costWrapper.notIn("id", longList);
                }
                tasCostInformationLineService.remove(costWrapper);
                for (TasCostInformationLine tasCostInformationLine : tasCostInformationLineList) {
                    tasCostInformationLine.setDocumentId(tasTravelApplyInfo.getId());
                    tasCostInformationLine.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
                }
                tasCostInformationLineService.saveOrUpdateBatch(tasCostInformationLineList);
               /* for (TasCostInformationLineForm costLine : costInformationLineForm) {
                    TasCostInformationLine cost=new BeanUtils<TasCostInformationLine>().copyObj(costLine,TasCostInformationLine.class);
                    cost.setDocumentId(tasTravelApplyInfo.getId());
                    cost.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
                    tasCostInformationLineService.saveOrUpdate(cost);
                    QueryWrapper<TasTravelTime>  timeQueryWrapperWrapper=new QueryWrapper<>();
                    timeQueryWrapperWrapper.eq("TRAVEL_LINE_ID", cost.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_COST_INFORMATION_LINE.getValue());
                    List<TasTravelTimeForm> tasTravelTimeForms=costLine.getTasTravelTimeForms();
                    List<Long> longLists = tasTravelTimeForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                    longLists.removeAll(Collections.singleton(null));
                    if(CollectionUtils.isNotEmpty(longLists)) {
                        timeQueryWrapperWrapper.notIn("id", longLists);
                    }
                    tasTravelTimeService.remove(timeQueryWrapperWrapper);
                    List<TasTravelTime> tasTravelTimes= new BeanUtils<TasTravelTime>().copyObjs(tasTravelTimeForms, TasTravelTime.class);
                    for (TasTravelTime time:tasTravelTimes) {
                        time.setTravelLineId(cost.getId());
                        time.setDocumentType(FsscTableNameEums.TAS_COST_INFORMATION_LINE.getValue());
                    }
                    tasTravelTimeService.saveOrUpdateBatch(tasTravelTimes);

                } for (TasCostInformationLineForm costLine : costInformationLineForm) {
                    TasCostInformationLine cost=new BeanUtils<TasCostInformationLine>().copyObj(costLine,TasCostInformationLine.class);
                    cost.setDocumentId(tasTravelApplyInfo.getId());
                    cost.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
                    tasCostInformationLineService.saveOrUpdate(cost);
                    QueryWrapper<TasTravelTime>  timeQueryWrapperWrapper=new QueryWrapper<>();
                    timeQueryWrapperWrapper.eq("TRAVEL_LINE_ID", cost.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_COST_INFORMATION_LINE.getValue());
                    List<TasTravelTimeForm> tasTravelTimeForms=costLine.getTasTravelTimeForms();
                    List<Long> longLists = tasTravelTimeForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                    longLists.removeAll(Collections.singleton(null));
                    if(CollectionUtils.isNotEmpty(longLists)) {
                        timeQueryWrapperWrapper.notIn("id", longLists);
                    }
                    tasTravelTimeService.remove(timeQueryWrapperWrapper);
                    List<TasTravelTime> tasTravelTimes= new BeanUtils<TasTravelTime>().copyObjs(tasTravelTimeForms, TasTravelTime.class);
                    for (TasTravelTime time:tasTravelTimes) {
                        time.setTravelLineId(cost.getId());
                        time.setDocumentType(FsscTableNameEums.TAS_COST_INFORMATION_LINE.getValue());
                    }
                    tasTravelTimeService.saveOrUpdateBatch(tasTravelTimes);
                }*/
            } else {
                tasCostInformationLineService.remove(costWrapper);
            }

            //离京信息
            QueryWrapper<TasLeaveaBjInformation> leaveaBjWrapper = new QueryWrapper<>();
            leaveaBjWrapper.eq("DOCUMENT_ID", tasTravelApplyInfo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            List<TasLeaveaBjInformationForm> leaveaBjInformationForm = tasTravelApplyInfoForm.getTasLeaveaBjInformationForm();
            if (CollectionUtils.isNotEmpty(leaveaBjInformationForm)) {
                List<TasLeaveaBjInformation> geExpensePaymentList = new BeanUtils<TasLeaveaBjInformation>().copyObjs(leaveaBjInformationForm, TasLeaveaBjInformation.class);
                List<Long> longList = leaveaBjInformationForm.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(longList)) {
                    leaveaBjWrapper.notIn("id", longList);
                }
                tasLeaveaBjInformationService.remove(leaveaBjWrapper);

                for (TasLeaveaBjInformation geExpensePaymen : geExpensePaymentList) {
                    geExpensePaymen.setDocumentId(tasTravelApplyInfo.getId());
                    geExpensePaymen.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
                }
                tasLeaveaBjInformationService.saveOrUpdateBatch(geExpensePaymentList);
            } else {
                tasLeaveaBjInformationService.remove(leaveaBjWrapper);
            }
            //文件列表保存
            List<Long> fileIds = tasTravelApplyInfoForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, tasTravelApplyInfo.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                files.stream().forEach(ka -> ka.setDocumentId(tasTravelApplyInfo.getId()));
                fileService.saveOrUpdateBatch(files);
            }
            return Result.success(new BeanUtils<TasTravelApplyInfoVo>().copyObj(tasTravleApplyInfoService.getById(tasTravelApplyInfo.getId()), TasTravelApplyInfoVo.class));
        }
        throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
    }

    public void validMonth(TasTravelApplyInfoForm tasTravelApplyInfoForm) {

        List<TasCostInformationLineForm> tasCostInformationLineForms = tasTravelApplyInfoForm.getTasCostInformationLineForm();
        if (CollectionUtils.isNotEmpty(tasCostInformationLineForms)) {
            for (TasCostInformationLineForm tasCostInformationLineForm : tasCostInformationLineForms) {
                List<TasTravelStandards> tasTravelStandardsPage=new ArrayList<TasTravelStandards>();
                int travelBeginTime = tasCostInformationLineForm.getTravelBeginTime().getMonth().getValue();//默认去掉为0位
                int travelEndTime = tasCostInformationLineForm.getTravelEndTime().getMonth().getValue();
                String nationAdminCode=tasCostInformationLineForm.getExt4();//行政区域代码存的是
                if("accommodation".equals(tasCostInformationLineForm.getExt5())
                        ||"subsidies".equals(tasCostInformationLineForm.getExt5())){
                    for (int i = travelBeginTime; i <= travelEndTime; i++) {
                        String travelDate = i + "";
                        QueryWrapper<TasTravelStandards> standardWrapper = new QueryWrapper<>();
                        standardWrapper.eq("PEAK_MONTH", travelDate).like("NATION_ADMIN_CODE", nationAdminCode);

                        QueryWrapper<TasTravelStandards> queryWrapper = new QueryWrapper <TasTravelStandards>();
                        queryWrapper.like(StringUtil.isNotEmpty(nationAdminCode),"NATION_ADMIN_CODE",nationAdminCode);
                        if (tasTravelStandardsService.count(standardWrapper) == 0) {
                            queryWrapper.isNull("PEAK_MONTH");
                        } else {
                            queryWrapper.eq(StringUtil.isNotEmpty(travelDate),"PEAK_MONTH",travelDate);
                        }
                        TasTravelStandards tasTravelStandards= tasTravelStandardsService.getOne(queryWrapper);
                        if(tasTravelStandards!=null){
                            tasTravelStandardsPage.add(tasTravelStandards);
                        }
                    }

                }

                List<StandardsVo> standardsVos=new ArrayList<StandardsVo>();
                if(CollectionUtils.isNotEmpty(tasTravelStandardsPage)){
                    for(int k=0; k<tasTravelStandardsPage.size();k++){
                        StandardsVo standardsVo=new StandardsVo();
                        BigDecimal sec=tasTravelStandardsPage.get(k).getMinisterialLevel();
                        standardsVo.setOverMoney(sec);
                        standardsVos.add(standardsVo);
                    }
                }

                //去重
                for  ( int  i  =   0 ; i  <  standardsVos.size()  -   1 ; i ++ )  {
                    for  ( int  j  =  standardsVos.size()  -   1 ; j  >  i; j -- )  {
                        if  (standardsVos.get(j).equals(standardsVos.get(i)))  {
                            standardsVos.remove(j);
                        }
                    }
                }
                if(standardsVos.size()>1){
                    throw new FSSCException(FsscErrorType.TRAVLE_APPLY_TIME);//只能有一条数据
                }

            }
        }

    }

    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "删除TasTravleApplyInfo", notes = "根据url的id来指定删除对象")
    @Transactional
    public Result delete(@RequestBody List<Long> ids) {
        log.info("id:" + ids);
        for (Long id : ids) {
            TasTravelApplyInfo tasTravelApplyInfo = tasTravleApplyInfoService.getById(id);
            AssertUtils.asserts(tasTravelApplyInfo != null, FsscErrorType.NOT_FIND_DATA);
            String documentStatus = tasTravelApplyInfo.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                    FsscEums.RECALLED.getValue().equals(documentStatus) ||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
            tasTravleApplyInfoService.removeById(id);

            //删除费用清单的同时删除 先删差旅时间
           /* QueryWrapper<TasCostInformationLine> costWrapper1=new QueryWrapper<>();
            costWrapper1.eq("DOCUMENT_ID",id);
            List<TasCostInformationLine>  costLines=tasCostInformationLineService.list(costWrapper1);
            for (TasCostInformationLine costLine:costLines) {
                QueryWrapper<TasTravelTime> timeWrapper=new QueryWrapper<>();
                timeWrapper.eq("TRAVEL_LINE_ID",costLine.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_COST_INFORMATION_LINE.getValue());
                tasTravelTimeService.remove(timeWrapper);
            }*/
            //删除费用清单
            QueryWrapper<TasCostInformationLine> costWrapper = new QueryWrapper<>();
            costWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            tasCostInformationLineService.remove(costWrapper);
            //删除离京信息
            QueryWrapper<TasLeaveaBjInformation> leaveaBjWrapper = new QueryWrapper<>();
            leaveaBjWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            tasLeaveaBjInformationService.remove(leaveaBjWrapper);

            //停止流程
            bpmProcessBiz.stopProcess(id);
        }
        return Result.success();
    }

    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, TasTravelApplyInfoQueryForm tasTravleApplyInfoForm)
            throws IOException {
        log.info("search with tasTravleApplyInfoForm:{}", JSON.toJSONString(tasTravleApplyInfoForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<TasTravelApplyInfo> records = tasTravleApplyInfoService.selectPage(tasTravleApplyInfoForm).getRecords();
        String[] title = {"序号", "单据编号", "创建人", "归属单位", "归属部门",
                "支出大类", "申请金额", "申请日期", "单据状态"};
        String fileName = "差旅申请单列表.xls";
        String sheetName = "差旅申请单列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            TasTravelApplyInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            content[i][5] = vo.getMainTypeName();
            // content[i][5] = StringUtil.objectToString(vo.getBorrowAmount());
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

    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<TasTravelApplyInfoVo>> search(@Valid TasTravelApplyInfoQueryForm tasTravelApplyInfoQueryForm) {
        log.info("search with tasTravelApplyInfoQueryForm:", tasTravelApplyInfoQueryForm.toString());
        IPage<TasTravelApplyInfo> tasTravleApplyInfoPage = tasTravleApplyInfoService.selectPage(tasTravelApplyInfoQueryForm);
        IPage<TasTravelApplyInfoVo> tasTravleApplyInfoVoPage = new BeanUtils<TasTravelApplyInfoVo>().copyPageObjs(tasTravleApplyInfoPage, TasTravelApplyInfoVo.class);
        return new Result<IPage<TasTravelApplyInfoVo>>().sucess(tasTravleApplyInfoVoPage);
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "根据条件列表查询", notes = "根据条件列表查询")
    @ResponseBody
    public Result<List<TasTravelApplyInfoVo>> list(@Valid TasTravelApplyInfoQueryForm tasTravelApplyInfoQueryForm) {
        log.info("search with tasTravelApplyInfoQueryForm:", tasTravelApplyInfoQueryForm.toString());
        List<TasTravelApplyInfo> tasTravleApplyInfoPage = tasTravleApplyInfoService.selectList(tasTravelApplyInfoQueryForm);
        List<TasTravelApplyInfoVo> tasTravleApplyInfoVos = new BeanUtils<TasTravelApplyInfoVo>().copyObjs(tasTravleApplyInfoPage, TasTravelApplyInfoVo.class);
        for (TasTravelApplyInfoVo tasTravelApplyInfoVo : tasTravleApplyInfoVos) {
            QueryWrapper<TasCostInformationLine> costQueryWrapper = new QueryWrapper<>();
            costQueryWrapper.eq("DOCUMENT_ID", tasTravelApplyInfoVo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO);
            List<TasCostInformationLine> tasCostInformationLine = tasCostInformationLineService.list(costQueryWrapper);
            List<TasCostInformationLineVo> tasCostInformationLineVos =
                    new BeanUtils<TasCostInformationLineVo>().copyObjs(tasCostInformationLine, TasCostInformationLineVo.class);
            tasTravelApplyInfoVo.setTasCostInformationLineVos(tasCostInformationLineVos);
        }
        return new Result<List<TasTravelApplyInfoVo>>().sucess(tasTravleApplyInfoVos);
    }

    @GetMapping(value = "/listReimBurse")
    @ApiOperation(value = "列表查询是否能被差旅报账关联", notes = "列表查询是否能被差旅报账关联")
    @ResponseBody
    public Result<List<TasTravelApplyInfoVo>> listReimBurse(@Valid TasTravelApplyInfoQueryForm tasTravelApplyInfoQueryForm) {
        log.info("search with tasTravelApplyInfoQueryForm:", tasTravelApplyInfoQueryForm.toString());
        List<TasTravelApplyInfo> tasTravleApplyInfoPage = tasTravleApplyInfoService.listReimBurse(tasTravelApplyInfoQueryForm);
        if (CollectionUtils.isNotEmpty(tasTravleApplyInfoPage)) {
            Iterator<TasTravelApplyInfo> iterator = tasTravleApplyInfoPage.iterator();
            while (iterator.hasNext()) {
                TasTravelApplyInfo tasTravelApplyInfo = iterator.next();
                Long id = tasTravelApplyInfo.getId();
                String isBorrowConect = tasTravelApplyInfo.getIsBorrowConnect();
                String whetherBorrow=tasTravelApplyInfo.getWhetherBorrow();
                QueryWrapper<BorrowMoneyInfo> borrowWrapper = new QueryWrapper<>();
                if("Y".equals(whetherBorrow)){
                    borrowWrapper.eq("APPLY_FOR_ID", id);
                    BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getOne(borrowWrapper);
                    if (borrowMoneyInfo == null) {
                        iterator.remove();
                    }
                }
                if ("Y".equals(isBorrowConect)) {//如果关联报账单，但已经被借款单关联了（若果是已支付则报账单还可以关联，否则不能）
                    borrowWrapper.eq("APPLY_FOR_ID", id);
                    borrowWrapper.ne("PAY_STATUS", "PAYED");
                    BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getOne(borrowWrapper);
                    if (borrowMoneyInfo != null) {
                        iterator.remove();
                    }
                }
            }

        }

        List<TasTravelApplyInfoVo> tasTravleApplyInfoVos = new BeanUtils<TasTravelApplyInfoVo>().copyObjs(tasTravleApplyInfoPage, TasTravelApplyInfoVo.class);
        for (TasTravelApplyInfoVo tasTravelApplyInfoVo : tasTravleApplyInfoVos) {
            QueryWrapper<TasCostInformationLine> costQueryWrapper = new QueryWrapper<>();
            costQueryWrapper.eq("DOCUMENT_ID", tasTravelApplyInfoVo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO);
            List<TasCostInformationLine> tasCostInformationLine = tasCostInformationLineService.list(costQueryWrapper);
            List<TasCostInformationLineVo> tasCostInformationLineVos =
                    new BeanUtils<TasCostInformationLineVo>().copyObjs(tasCostInformationLine, TasCostInformationLineVo.class);
            tasTravelApplyInfoVo.setTasCostInformationLineVos(tasCostInformationLineVos);
        }
        return new Result<List<TasTravelApplyInfoVo>>().sucess(tasTravleApplyInfoVos);
    }


    @PostMapping(value = "/sumbitAdvance")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<TasTravelApplyInfoVo> sumbitAdvance(@RequestBody @Valid TasTravelApplyInfoForm tasTravelApplyInfoForm) {
        Result<TasTravelApplyInfoVo> queryDatas = doSaveOrUpdate(tasTravelApplyInfoForm);
        TasTravelApplyInfoVo tasTravelApplyInfo = queryDatas.getData();
        Long id = tasTravelApplyInfo.getId();
        if (id == null) {
            throw new FSSCException(FsscErrorType.TRAVLE_APPLY_NO_EMPTY);
        }
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(tasTravelApplyInfo.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(tasTravelApplyInfo.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(tasTravelApplyInfo.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setProjectId(tasTravelApplyInfo.getProjectId());
        valueForm.setTotalAmount(tasTravelApplyInfo.getTotalSum().toString());
        valueForm.setMainTypeCode(tasTravelApplyInfo.getMainTypeCode());
        valueForm.setDocumentId(tasTravelApplyInfo.getId());
        valueForm.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        valueForm.setCreateBy(tasTravelApplyInfo.getCreateBy());
        valueForm.setDeptCode(tasTravelApplyInfo.getDeptCode());
        valueForm.setProjectCode(tasTravelApplyInfo.getProjectCode());
        valueForm.setUnitCode(tasTravelApplyInfo.getUnitCode());

        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);


        ReSubmitProcessForm form = new ReSubmitProcessForm();
        form.setProcessVariables(andAddProcessValue);
        form.setDocumentId(id);
        form.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        form.setDocumentNum(tasTravelApplyInfo.getDocumentNum());
        form.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        form.setBudgetWarningCheck(tasTravelApplyInfoForm.getBudgetWarningCheck());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(tasTravelApplyInfoForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(form);
        } else {
            form.setReSubmitType(tasTravelApplyInfoForm.getReSubmitType());
            bpmProcessBiz.reSubmit(form);
        }
        return Result.success();
    }


    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;


    @GetMapping(value = "/isClose")
    @ApiOperation(value = "是否关闭", notes = "是否关闭")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result isClose(Long id) {
        log.info("id:" + id);
        AssertUtils.asserts(id!=null,FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            TasTravelApplyInfo tasTravelApplyInfo=tasTravleApplyInfoService.getById(id);
            if(tasTravelApplyInfo!=null){
                QueryWrapper<TasTravelReimburse> reimburseQueryWrapper=new QueryWrapper<>();
                reimburseQueryWrapper.eq("APPLY_FOR_ID",id);
                TasTravelReimburse tasTravelReimburse=tasTravelReimburseService.getOne(reimburseQueryWrapper);
                if(tasTravelReimburse!=null){
                    if("APPROVED".equals(tasTravelApplyInfo.getDocumentStatus())&&"PAYED".equals(tasTravelReimburse.getPayStatus())){
                        return Result.success("Y");
                    }
                }
            }
        return Result.success("N");
    }

    @GetMapping(value = "/updateTasAppStatus")
    @ApiOperation(value = "点击关闭改变申请状态", notes = "点击关闭改变申请状态")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result updateTasAppStatus(Long id) {
        log.info("id:" + id);
        if(id!=null){
            TasTravelApplyInfo tasTravelApplyInfo=tasTravleApplyInfoService.getById(id);
            if(tasTravelApplyInfo!=null){
                tasTravelApplyInfo.setDocumentStatus(FsscEums.CLOSED.getValue());
                tasTravleApplyInfoService.updateById(tasTravelApplyInfo);
                budgetControlService.executeBudgetFree(id, FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            }
        }
        return Result.success();
    }
}



