package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseContentCommitmentQueryForm;
import com.deloitte.platform.api.fssc.base.vo.*;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseContentCommitment;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.service.IBaseContentCommitmentService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.ReturnPage;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :   BaseContentCommitment控制器实现类
 * @Modified :
 */
@Api(tags = "承诺书内容操作接口")
@Slf4j
@RestController
@RequestMapping("/base/contentCommitment")
public class BaseContentCommitmentController {

    @Autowired
    private IBaseContentCommitmentService commitmentService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IBaseDocumentTypeService docTypeService;


    @ApiOperation(value = "新增承诺书内容", notes = "新增一个承诺书内容定义")
    @ApiParam(name = "commitmentForm", value = "新增承诺书内容的form表单", required = true)
    @PostMapping(value = "/addOrUpdateContentCommitment")
    public Result addOrUpdateMainType(
            @RequestBody BaseContentCommitmentForm commitmentForm) {
        BaseContentCommitment baseContentCommitment;
        if (commitmentForm.getId() != null) {
            //修改
            baseContentCommitment = commitmentService
                    .getById(commitmentForm.getId());
            if (baseContentCommitment != null) {
                baseContentCommitment.setBillName(commitmentForm.getBillName());
                baseContentCommitment.setContentCommitment(commitmentForm.getContentCommitment());
            }
            return Result.success(
                    commitmentService.updateContentCommitment(baseContentCommitment));
        } else {
            //新增
            baseContentCommitment = new BeanUtils<BaseContentCommitment>()
                    .copyObj(commitmentForm, BaseContentCommitment.class);
            baseContentCommitment.setValidDate(LocalDateTime.now());
            if (FsscEums.VALID.getValue().equals(baseContentCommitment.getValidFlag())) {
                baseContentCommitment.setValidDate(LocalDateTime.now());
            } else {
                baseContentCommitment.setInvalidDate(LocalDateTime.now());
            }
            return Result.success(commitmentService.saveContentCommitment(baseContentCommitment));
        }
    }

//    @ApiOperation(value = "删除承诺书", notes = "根据url的id来指定删除对象")
//    @ApiImplicitParam(paramType = "path", name = "id", value = "BaseExpenseMainTypeID", required = true,
//            dataType = "String")
//    @DeleteMapping(value = "/deleteByIds")
//    public Result deleteByIds(@RequestBody List<Long> ids) {
//        log.info("ids：{}", ids);
//        commitmentService.removeByIds(ids);
//        return Result.success();
//    }

    @ApiOperation(value = "获取承诺书", notes = "获取单据类型的承诺书信息")
    @ApiImplicitParam(paramType = "path", name = "documentTypeId", value = "单据类型ID", required = true, dataType = "long")
    @GetMapping(value = "/get/{documentTypeId}")
    public Result<BaseContentCommitmentVo> get(@PathVariable Long documentTypeId) {
        log.info("get with documentTypeId:{}", documentTypeId);
        BaseContentCommitment baseContentCommitment = commitmentService.getByDocumentTypeId(documentTypeId);
        if (baseContentCommitment == null) {
            return Result.success();
        }
        BaseContentCommitmentVo baseContentCommitmentVo = new BeanUtils<BaseContentCommitmentVo>()
                .copyObj(baseContentCommitment, BaseContentCommitmentVo.class);
        return new Result<BaseContentCommitmentVo>().sucess(baseContentCommitmentVo);
    }

    @ApiOperation(value = "获取承诺书", notes = "获取单据类型的承诺书信息")
    @ApiImplicitParam(paramType = "path", name = "functionModule", value = "功能模块,单据的主表名",
            required = true, dataType = "string")
    @GetMapping(value = "/getByFunctionModule/{functionModule}")
    public Result<BaseContentCommitmentVo> getByFunctionModule(@PathVariable String functionModule) {
        log.info("get with functionModule:{}", functionModule);
        DeptVo currentDept = commonServices.getCurrentDept();
        BaseDocumentType documentType = docTypeService.getDocTypeByFunction(functionModule,currentDept.getDeptCode());
        if (documentType == null){
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        BaseContentCommitment baseContentCommitment = commitmentService.getByDocumentTypeId(documentType.getId());
        if (baseContentCommitment == null) {
            return Result.success();
        }
        BaseContentCommitmentVo baseContentCommitmentVo = new BeanUtils<BaseContentCommitmentVo>()
                .copyObj(baseContentCommitment, BaseContentCommitmentVo.class);
        return new Result<BaseContentCommitmentVo>().sucess(baseContentCommitmentVo);
    }

//
//    @ApiOperation(value = "列表查询承诺书", notes = "根据条件查询承诺书分页数据")
//    @ApiParam(name = "baseContentCommitmentQueryForm", value = "承诺书大类查询参数", required = true)
//    @PostMapping(value = "/page/conditions")
//    public ReturnPage<BaseContentCommitment> list(
//            @Valid @RequestBody BaseContentCommitmentQueryForm queryForm) {
//        log.info("queryForm:" + queryForm.toString());
//        IPage<BaseContentCommitment> pageInfo = commitmentService.selectPage(queryForm);
//        IPage<BaseContentCommitmentVo> baseContentCommitmentVoPag = new BeanUtils<BaseContentCommitmentVo>()
//                .copyPageObjs(pageInfo, BaseContentCommitmentVo.class);
//        return new ReturnPage(baseContentCommitmentVoPag);
//    }
//
//    @ApiOperation(value = "修改生失效状态", notes = "支持批量")
//    @PostMapping(value = "/modifyCommitmentStatus")
//    public Result modifyContentCommitmentStatus(
//            @Valid @RequestBody ModifyMainTypeStatusForm form) {
//        log.info("search with ModifyMainTypeStatusForm:{}", JSON.toJSONString(form));
//        commitmentService.modifyStatus(form.getIds(), form.getStatus());
//        return Result.success();
//    }
//
//    @GetMapping(value = "/export")
//    public void exportExcel(HttpServletResponse response, BaseContentCommitmentQueryForm queryForm) {
//        log.info("search with BaseContentCommitmentQueryForm:{}", JSON.toJSONString(queryForm));
//        List<BaseContentCommitment> records = commitmentService.selectPage(queryForm)
//                .getRecords();
//        List<ExcelHeader> headerList = new ArrayList<>();
//        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
//        headerList.add(new ExcelHeader("单据名称").setOneCellWidth(4000));
//        headerList.add(new ExcelHeader("承诺书内容").setOneCellWidth(6000));
//        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(3000));
//        String fileName = "承诺书内容信息";
//        Object[][] content = new Object[records.size()][];
//        for (int i = 0; i < records.size(); i++) {
//            content[i] = new String[headerList.size()];
//            BaseContentCommitment vo = records.get(i);
//            content[i][0] = i + 1 + "";
//            content[i][1] = vo.getBillName();
//            content[i][2] = vo.getContentCommitment();
//            content[i][3] = StringUtil.isEmpty(vo.getValidFlag()) ? "N/A" :
//                    (FsscEums.VALID.getValue().equals(vo.getValidFlag()) ? "是" : "否");
//        }
//        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil
//                .getSimpleInstance2(headerList, response);
//        exportUtil.setFileName(fileName);
//        exportUtil.setData2Array(content);
//        exportUtil.exportExcel();
//    }

}



