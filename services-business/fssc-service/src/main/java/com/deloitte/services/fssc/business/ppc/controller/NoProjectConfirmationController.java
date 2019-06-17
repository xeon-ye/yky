package com.deloitte.services.fssc.business.ppc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.ppc.param.NoProjectConfirmationQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.NoProjectConfirmationForm;
import com.deloitte.platform.api.fssc.ppc.vo.NoProjectConfirmationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.ppc.biz.ProjectConfirmationBiz;
import com.deloitte.services.fssc.business.ppc.entity.NoProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.service.INoProjectConfirmationService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :   NoProjectConfirmation控制器实现类
 * @Modified :
 */
@Api(tags = "非项目款项确认操作接口")
@Slf4j
@RestController
@RequestMapping(value = "no-project-confirmation")
public class NoProjectConfirmationController  {

    @Autowired
    public INoProjectConfirmationService  noProjectConfirmationService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    @Qualifier("noProjectConfirmationBizImpl")
    private ProjectConfirmationBiz<NoProjectConfirmation,
            NoProjectConfirmationForm, NoProjectConfirmationVo> projectConfirmationBiz;





    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSubmitProcess(@Valid @RequestBody
                                  @ApiParam(name = "projectConfirmationForm", value = "新增ProjectConfirmation的form表单", required = true)
                                          NoProjectConfirmationForm projectConfirmationForm) {
        NoProjectConfirmation o = projectConfirmationBiz.saveOrUpdate(projectConfirmationForm);
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(o.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(o.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setDocumentId(o.getId());
        startForm.setDocumentNum(o.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.PPC_NO_PROJECT_CONFIRMATION.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        //startForm.setDocumentUrl(geGeneralExpenseForm.getDocumentUrl());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(projectConfirmationForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(projectConfirmationForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        //提交时修改金额
        return Result.success(o);
    }



    @ApiOperation(value = "新增NoProjectConfirmation", notes = "新增一个NoProjectConfirmation")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "addOrUpdate")
    public Result add(@Valid @RequestBody @ApiParam(name="noProjectConfirmationForm",value="新增NoProjectConfirmation的form表单",required=true)  NoProjectConfirmationForm noProjectConfirmationForm) {
        log.info("form:{}",  noProjectConfirmationForm.toString());
        return Result.success( projectConfirmationBiz.saveOrUpdate(noProjectConfirmationForm));
    }



    @ApiOperation(value = "删除NoProjectConfirmation", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "NoProjectConfirmationID", required = true, dataType = "long")
    @DeleteMapping(value = "deleteById/{id}")
    public Result delete(@PathVariable(value = "id") Long id) {
        projectConfirmationBiz.deleteById(id);
        return Result.success();
    }




    @ApiOperation(value = "获取NoProjectConfirmation", notes = "获取指定ID的NoProjectConfirmation信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "NoProjectConfirmation的ID", required = true, dataType = "long")
    @GetMapping(value = "getById/{id}")
    public Result<NoProjectConfirmationVo> get(@PathVariable(value = "id") Long id) {
        log.info("get with id:{}", id);

        return new Result<NoProjectConfirmationVo>().sucess(projectConfirmationBiz.getById(id));
    }






    @ApiOperation(value = "分页查询NoProjectConfirmation", notes = "根据条件查询NoProjectConfirmation分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<NoProjectConfirmationVo>> search(@Valid  @ApiParam(name="noProjectConfirmationQueryForm",value="NoProjectConfirmation查询参数",required=true) NoProjectConfirmationQueryForm noProjectConfirmationQueryForm) {
        log.info("search with noProjectConfirmationQueryForm:", noProjectConfirmationQueryForm.toString());
        IPage<NoProjectConfirmation> noProjectConfirmationPage=noProjectConfirmationService.selectPage(noProjectConfirmationQueryForm);
        IPage<NoProjectConfirmationVo> noProjectConfirmationVoPage=
                new BeanUtils<NoProjectConfirmationVo>().copyPageObjs(noProjectConfirmationPage,NoProjectConfirmationVo.class);
        return new Result<IPage<NoProjectConfirmationVo>>().sucess(noProjectConfirmationVoPage);
    }


    /**
     * 方法名: export 方法描述: 导出
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(NoProjectConfirmationQueryForm projectConfirmationQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(projectConfirmationQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.REVIEVE_STATUS.getValue()));
        List<NoProjectConfirmation> records = noProjectConfirmationService.selectPage(projectConfirmationQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "申请人", "款项大类", "管理部门"
                , "收入金额", "申请日期", "单据状态", "收款状态"};
        String fileName = "通用报账单信息列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            NoProjectConfirmation vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getInComeMainTypeName();
            content[i][4] = vo.getDeptName();
            content[i][5] = vo.getTotalAmount() == null ? "0" : vo.getTotalAmount().toString();
            if (vo.getCreateTime() != null) {
                content[i][6] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][7] = docStatusMap.get(vo.getDocumentStatus());
            content[i][8] = payStatusMap.get(vo.getRecieveStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }
}



