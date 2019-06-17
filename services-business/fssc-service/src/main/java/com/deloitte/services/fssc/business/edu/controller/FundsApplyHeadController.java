package com.deloitte.services.fssc.business.edu.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.edu.param.FundsApplyHeadQueryForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyHeadForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyHeadVo;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyLineForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyLineVo;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyHead;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyLine;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyHeadService;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyLineService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description :   FundsApplyHead控制器实现类
 * @Modified :
 */
@Api(tags = "FundsApplyHead操作接口")
@Slf4j
@RestController
@RequestMapping(value = "edu-funds-apply")
public class FundsApplyHeadController  {

    @Autowired
    public IFundsApplyHeadService  fundsApplyHeadService;

    @Autowired
    public IFundsApplyLineService fundsApplyLineService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IFileService fileService;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @ApiOperation(value = "新增FundsApplyHead", notes = "新增一个FundsApplyHead")
    @PostMapping(value = "saveOrUpdate")
    @Transactional
    public Result add(@Valid @RequestBody
                          @ApiParam(name="fundsApplyHeadForm",value="新增FundsApplyHead的form表单",required=true)
                                  FundsApplyHeadForm fundsApplyHeadForm) {
        return Result.success(saveOrUpdate(fundsApplyHeadForm));
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @Transactional
    public Result submit(@Valid @RequestBody
                      @ApiParam(name="fundsApplyHeadForm",value="新增FundsApplyHead的form表单",required=true)
                                     FundsApplyHeadForm fundsApplyHeadForm) {
        FundsApplyHeadVo fundsApplyHeadVo = saveOrUpdate(fundsApplyHeadForm);
        fundsApplyHeadService.modifyAmount(true,StringUtil.castTolong(fundsApplyHeadVo.getId()),FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue());
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(fundsApplyHeadVo.getDocumentStatus())||
                FsscEums.REJECTED.getValue().equals(fundsApplyHeadVo.getDocumentStatus())||
                FsscEums.RECALLED.getValue().equals(fundsApplyHeadVo.getDocumentStatus()),FsscErrorType.SUBMIT_NEW_REJECTED);

        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setTotalAmount(fundsApplyHeadVo.getTotalAmount().toString());

        valueForm.setDocumentId(StringUtil.castTolong(fundsApplyHeadVo.getId()));
        valueForm.setDocumentType(FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue());
        valueForm.setCreateBy(fundsApplyHeadVo.getCreateBy());
        valueForm.setDeptCode(fundsApplyHeadVo.getDeptCode());
        valueForm.setUnitCode(fundsApplyHeadVo.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setProcessVariables(andAddProcessValue);
        startForm.setDocumentId(StringUtil.castTolong(fundsApplyHeadVo.getId()));
        startForm.setDocumentNum(fundsApplyHeadVo.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        startForm.setBudgetWarningCheck("N");
        if(FsscEums.FIRST_SUBMIT.getValue().equals(fundsApplyHeadForm.getReSubmitType())){
            bpmProcessBiz.startProcess(startForm);
        }else {
            startForm.setReSubmitType(fundsApplyHeadForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }


        return Result.success(fundsApplyHeadVo);
    }


    private FundsApplyHeadVo saveOrUpdate(FundsApplyHeadForm fundsApplyHeadForm){
        log.info("form:",  fundsApplyHeadForm.toString());
        FundsApplyHead fundsApplyHead =new BeanUtils<FundsApplyHead>().copyObj(fundsApplyHeadForm,FundsApplyHead.class);
        List<FundsApplyLineForm> fundsApplyLineForms = fundsApplyHeadForm.getFundsApplyLineForms();
        BigDecimal totalAmount=BigDecimal.ZERO;
        for (FundsApplyLineForm fundsApplyLineForm:fundsApplyLineForms){
            totalAmount=totalAmount.add(BigDecimalUtil.convert(fundsApplyLineForm.getApplyAmount()));
        }
        fundsApplyHead.setTotalAmount(totalAmount);
        boolean save = fundsApplyHeadService.saveOrUpdate(fundsApplyHead);
        AssertUtils.asserts(save, FsscErrorType.SAVE_FAIL);

        QueryWrapper<FundsApplyLine> lineQueryWrapper=new QueryWrapper<>();
        lineQueryWrapper.eq(FundsApplyLine.DOCUMENT_ID,fundsApplyHead.getId());
        if(CollectionUtils.isNotEmpty(fundsApplyLineForms)){
            List<Long> collect = fundsApplyLineForms.stream().map(k -> k.getId()).collect(Collectors.toList());
            collect.removeAll(Collections.singleton(null));
            if(CollectionUtils.isNotEmpty(collect)){
                lineQueryWrapper.notIn("ID",collect);
            }
            fundsApplyLineService.remove(lineQueryWrapper);
            List<FundsApplyLine> fundsApplyLines = new BeanUtils<FundsApplyLine>().copyObjs(fundsApplyLineForms, FundsApplyLine.class);
            for (FundsApplyLine line:fundsApplyLines){
                line.setDocumentId(fundsApplyHead.getId());
                line.setDocumentType(FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue());
            }

            fundsApplyLineService.saveOrUpdateBatch(fundsApplyLines);
        }
        //文件列表保存
        List<Long> fileIds = fundsApplyHeadForm.getFileIds();
        if(fileIds!=null){
            fileIds.removeAll(Collections.singleton(null));
        }
        if(CollectionUtils.isNotEmpty(fileIds)){
            QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID,fundsApplyHead.getId())
                    .eq(File.DOCUMENT_TYPE,FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue())
                    .notIn(File.ID,fileIds);
            fileService.remove(fileQueryWrapper);

            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files),
                    FsscErrorType.FILE_IS_NULL);
            files.stream().forEach(ka->ka.setDocumentId(fundsApplyHead.getId()));
            fileService.saveOrUpdateBatch(files);
        }


        return new BeanUtils<FundsApplyHeadVo>().copyObj(fundsApplyHeadService.getById(fundsApplyHead.getId()),FundsApplyHeadVo.class);
    }





    @ApiOperation(value = "删除FundsApplyHead", notes = "根据url的id来指定删除对象")
    @DeleteMapping(value = "deleteById/{id}")
    @Transactional
    public Result delete(@PathVariable(value = "id") Long id) {
        AssertUtils.asserts(id!=null,FsscErrorType.ID_CANT_BE_NULL);
        FundsApplyHead applyHead = fundsApplyHeadService.getById(id);
        AssertUtils.asserts(applyHead!=null,FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        String documentStatus = applyHead.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus)||
                FsscEums.RECALLED.getValue().equals(documentStatus)||
                FsscEums.REJECTED.getValue().equals(documentStatus),FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);

        fundsApplyHeadService.removeById(id);

        QueryWrapper<FundsApplyLine> lineQueryWrapper=new QueryWrapper<>();
        lineQueryWrapper.eq(FundsApplyLine.DOCUMENT_ID,applyHead.getId());
        fundsApplyLineService.remove(lineQueryWrapper);

        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping(value = "getById/{id}")
    public Result<FundsApplyHeadVo> getById(@PathVariable(value = "id") Long id){
        AssertUtils.asserts(id!=null,FsscErrorType.ID_CANT_BE_NULL);
        FundsApplyHead head = fundsApplyHeadService.getById(id);
        AssertUtils.asserts(head!=null,FsscErrorType.DOCUMENT_DATA_IS_EMPTY);

        FundsApplyHeadVo fundsApplyHeadVo = new BeanUtils<FundsApplyHeadVo>().copyObj(head, FundsApplyHeadVo.class);
        QueryWrapper<FundsApplyLine> lineQueryWrapper=new QueryWrapper<>();
        lineQueryWrapper.eq(FundsApplyLine.DOCUMENT_ID,head.getId());
        List<FundsApplyLine> list = fundsApplyLineService.list(lineQueryWrapper);
        if(CollectionUtils.isNotEmpty(list)){
            fundsApplyHeadVo.setFundsApplyLineVos(new BeanUtils<FundsApplyLineVo>().copyObjs(list,FundsApplyLineVo.class));
        }
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue());
            fundsApplyHeadVo.setTaskVos(history);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
        fileQueryWrapper.eq(File.DOCUMENT_ID,id)
                .eq(File.DOCUMENT_TYPE,FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue());
        List<File> files = fileService.list(fileQueryWrapper);
        if(CollectionUtils.isNotEmpty(files)){
            fundsApplyHeadVo.setFileVos(new BeanUtils<FileVo>().copyObjs(files,FileVo.class));
        }
        return Result.success(fundsApplyHeadVo);
    }



    @ApiOperation(value = "分页查询FundsApplyHead", notes = "根据条件查询FundsApplyHead分页数据")
    @GetMapping(value = "page/conditions")
    public Result<IPage<FundsApplyHeadVo>> search(@Valid @ApiParam(name="fundsApplyHeadQueryForm",value="FundsApplyHead查询参数",required=true)
                                                              FundsApplyHeadQueryForm fundsApplyHeadQueryForm) {
        log.info("search with fundsApplyHeadQueryForm:", fundsApplyHeadQueryForm.toString());
        IPage<FundsApplyHead> fundsApplyHeadPage=fundsApplyHeadService.selectPage(fundsApplyHeadQueryForm);
        IPage<FundsApplyHeadVo> fundsApplyHeadVoPage=new BeanUtils<FundsApplyHeadVo>().copyPageObjs(fundsApplyHeadPage,FundsApplyHeadVo.class);
        return new Result<IPage<FundsApplyHeadVo>>().sucess(fundsApplyHeadVoPage);
    }




    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "exportExcel")
    @ResponseBody
    public void exportExcel( FundsApplyHeadQueryForm fundsApplyHeadQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(fundsApplyHeadQueryForm));
        Map<String, String> docStatus = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        IPage<FundsApplyHead> fundsApplyHeadPage=fundsApplyHeadService.selectPage(fundsApplyHeadQueryForm);
        List<FundsApplyHead> records = fundsApplyHeadPage.getRecords();
        String[] title = {"序号", "单据编号", "申请人", "归属单位", "归属部门", "申请金额", "申请日期"
                , "单据状态"};
        String fileName = "项目列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            FundsApplyHead vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            if(vo.getTotalAmount()!=null){
                content[i][5] = vo.getTotalAmount().toString();
            }
            if(vo.getCreateTime()!=null){
                content[i][6] = LocalDateTimeUtils.formatTime(vo.getCreateTime(),"yyyy-MM-dd HH:mm:ss");
            }
            content[i][7]=docStatus.get(vo.getDocumentStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

}



