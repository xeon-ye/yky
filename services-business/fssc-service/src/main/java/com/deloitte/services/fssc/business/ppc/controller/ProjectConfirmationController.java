package com.deloitte.services.fssc.business.ppc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.ppc.param.ProjectConfirmationQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.IncomeClaimedVo;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectConfirmationForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectConfirmationVo;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.ppc.biz.ProjectConfirmationBiz;
import com.deloitte.services.fssc.business.ppc.entity.ProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.service.IProjectConfirmationService;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :   ProjectConfirmation控制器实现类
 * @Modified :
 */
@Api(tags = "项目款项确认操作接口")
@Slf4j
@RestController
@RequestMapping(value = "project-confirmation")
public class ProjectConfirmationController {

    @Autowired
    public IProjectConfirmationService projectConfirmationService;
    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    public IRecievePaymentService recievePaymentService;


    @Autowired
    private IRecieveLineMsgService recieveLineMsgService;

    @Autowired
    private IBaseIncomeMainTypeService baseIncomeMainTypeService;

    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @Autowired
    @Qualifier("projectConfirmationBizImpl")
    private ProjectConfirmationBiz<ProjectConfirmation,ProjectConfirmationForm, ProjectConfirmationVo> projectConfirmationBiz;




    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<ProjectConfirmationVo> doSubmitProcess(@Valid @RequestBody
                                  @ApiParam(name = "projectConfirmationForm", value = "新增ProjectConfirmation的form表单", required = true)
                                          ProjectConfirmationForm projectConfirmationForm) {
        ProjectConfirmation o = projectConfirmationBiz.saveOrUpdate(projectConfirmationForm);
        recievePaymentService.modifyCliamStatus(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue(),
                o.getId(),true);
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(o.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(o.getDocumentStatus())
                ||FsscEums.RECALLED.getValue().equals(o.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setProjectId(o.getProjectId());
        valueForm.setTotalAmount(o.getTotalAmount().toString());
        valueForm.setMainTypeCode(o.getInComeMainTypeCode());
        valueForm.setDocumentId(o.getId());
        valueForm.setDocumentType(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());
        valueForm.setCreateBy(o.getCreateBy());
        valueForm.setDeptCode(o.getDeptCode());
        valueForm.setProjectCode(o.getProjectCode());
        valueForm.setUnitCode(o.getUnitCode());
        valueForm.setHasContract(o.getContractId()!=null?"Y":"N");
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(o.getId());
        startForm.setDocumentNum(o.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue(),o.getId());
        //startForm.setDocumentUrl(geGeneralExpenseForm.getDocumentUrl());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(projectConfirmationForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(projectConfirmationForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        //提交时修改金额
        return Result.success(new BeanUtils<ProjectConfirmationVo>().copyObj(o,ProjectConfirmationVo.class));
    }


    @ApiOperation(value = "新增或删除项目款项", notes = "新增或删除项目款项")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "addOrUpdate")
    public Result addOrUpdate(@Valid @RequestBody @ApiParam(name = "projectConfirmationForm", value = "新增ProjectConfirmation的form表单", required = true)
                                      ProjectConfirmationForm projectConfirmationForm) {
        log.info("form:{}", projectConfirmationForm.toString());

        return Result.success(new BeanUtils<ProjectConfirmationVo>().copyObj(projectConfirmationBiz.saveOrUpdate(projectConfirmationForm),ProjectConfirmationVo.class));
    }


    @ApiOperation(value = "删除项目款项", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProjectConfirmationID", required = true, dataType = "long")
    @DeleteMapping(value = "deleteById/{id}")
    public Result delete(@PathVariable(value = "id") Long id) {
        AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        projectConfirmationBiz.deleteById(id);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }


    @ApiOperation(value = "获取项目款项根据id", notes = "获取指定ID的ProjectConfirmation信息")
    @GetMapping(value = "getById/{id}")
    public Result<ProjectConfirmationVo> get(@PathVariable(value = "id") Long id) {
        log.info("get with id:{}", id);
        AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        return new Result<ProjectConfirmationVo>().sucess(projectConfirmationBiz.getById(id));
    }


    @ApiOperation(value = "查询所有款项类型", notes = "查询所有款项类型")
    @GetMapping(value = "getPaymentType")
    public Result<List<String>> getPaymentType(){
        QueryWrapper<BaseIncomeMainType> incomeMainTypeQueryWrapper=new QueryWrapper<>();
        incomeMainTypeQueryWrapper.select("PARENT_NAME").apply("PARENT_NAME IS NOT NULL").groupBy("PARENT_NAME");
        List<BaseIncomeMainType> list = baseIncomeMainTypeService.list(incomeMainTypeQueryWrapper);
        if(CollectionUtils.isNotEmpty(list)){
            List<String> collect = list.stream().map(l -> l.getParentName()).collect(Collectors.toList());
            return Result.success(collect);
        }
        return Result.success();
    }


    @ApiOperation(value = "分页查询ProjectConfirmation", notes = "根据条件查询ProjectConfirmation分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<ProjectConfirmationVo>> search(@Valid @ApiParam(name = "projectConfirmationQueryForm", value = "ProjectConfirmation查询参数", required = true)
                                                               ProjectConfirmationQueryForm projectConfirmationQueryForm) {
        log.info("search with projectConfirmationQueryForm:", projectConfirmationQueryForm.toString());
        IPage<ProjectConfirmation> projectConfirmationPage = projectConfirmationService.selectPage(projectConfirmationQueryForm);
        IPage<ProjectConfirmationVo> projectConfirmationVoPage = new BeanUtils<ProjectConfirmationVo>().copyPageObjs(projectConfirmationPage, ProjectConfirmationVo.class);
//        for (ProjectConfirmationVo vo:projectConfirmationVoPage.getRecords()){
//            Long inComeMainTypeId = vo.getInComeMainTypeId();
//            if(inComeMainTypeId!=null){
//                BaseIncomeMainType incomeMainType = baseIncomeMainTypeService.getById(inComeMainTypeId);
//                if(incomeMainType!=null){
//                    vo.setPaymentType(incomeMainType.getParentName());
//                }
//            }
//
//        }
        return new Result<IPage<ProjectConfirmationVo>>().sucess(projectConfirmationVoPage);
    }


    /**
     * 方法名: export 方法描述: 导出
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(ProjectConfirmationQueryForm projectConfirmationQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(projectConfirmationQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        List<ProjectConfirmation> records = projectConfirmationService.selectPage(projectConfirmationQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "申请人", "款项类型","款项大类", "项目名称", "管理部门"
                , "合计金额", "申请日期", "单据状态", "收款状态","备注"};
        String fileName = "通用报账单信息列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            ProjectConfirmation vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();

            content[i][3] = vo.getPaymentType();

            content[i][4] = vo.getInComeMainTypeName();
            content[i][5] = vo.getProjectName();
            content[i][6] = vo.getDeptName();
            content[i][7] = vo.getTotalAmount() == null ? "0" : vo.getTotalAmount().toString();
            if (vo.getCreateTime() != null) {
                content[i][8] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][9] = docStatusMap.get(vo.getDocumentStatus());
            if("Y".equalsIgnoreCase(vo.getRecieveStatus())){
                content[i][10] = "已收款";
            }
            if("N".equalsIgnoreCase(vo.getRecieveStatus())){
                content[i][10] = "未收款";
            }
            content[i][11]=vo.getRemark();
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    @ApiOperation(value = "收入认领", notes = "收入认领")
    @GetMapping(value = "page/conditionsRecie")
    public Result<IPage<IncomeClaimedVo>> search(@ApiParam(name="recievePaymentQueryForm",value="RecievePayment查询参数",required=true)
                                                          RecieveLineMsgQueryForm recievePaymentQueryForm) {
        IPage<IncomeClaimedVo> dataPage=recieveLineMsgService.conditionsRecie(recievePaymentQueryForm);

        return Result.success(dataPage);

    }
}



