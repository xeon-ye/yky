package com.deloitte.services.fssc.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypePayWayQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypePayWayForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypePayWayVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypePayWay;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypePayWayService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : jaws
 * @Date : Create in 2019-04-11
 * @Description :   BaseDocumentTypePayWay控制器实现类
 * @Modified :
 */
@Api(tags = "单据类型-付款方式 操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/base/documentTypePayWay")
public class BaseDocumentTypePayWayController {

    @Autowired
    public IBaseDocumentTypePayWayService payWayService;

    @Autowired
    private IBaseDocumentTypeService documentTypeService;

    @Autowired
    private FsscCommonServices commonServices;


    @ApiOperation(value = "新增/修改付款方式", notes = "新增/修改多个付款方式")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiParam(name = "payWayForm", value = "新增支付方式的form表单", required = true)
    @PostMapping(value = "addOrUpdate")
    public Result addOrUpdate(
            @Valid @RequestBody List<BaseDocumentTypePayWayForm> payWayFormList) {
        log.info("addOrUpdate form:", payWayFormList.toString());
        if (CollectionUtils.isEmpty(payWayFormList)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        Map<String,BaseDocumentTypePayWayForm> codeNameMap = new HashMap<>();
        for (BaseDocumentTypePayWayForm payWayForm : payWayFormList) {
            if (StringUtils.isBlank(payWayForm.getCode())){
                throw new FSSCException(FsscErrorType.PAY_WAY_CODE_IS_EMPTY);
            }
            if (StringUtils.isBlank(payWayForm.getName())){
                throw new FSSCException(FsscErrorType.PAY_WAY_NAME_IS_EMPTY);
            }
            if (!codeNameMap.containsKey(payWayForm.getCode())){
                codeNameMap.put(payWayForm.getCode(),payWayForm);
            }else{
                throw new FSSCException(FsscErrorType.CODE_REPEAT);
            }
        }
        payWayService.updateOrSaveBatch(payWayFormList);
        return Result.success();
    }


    @ApiOperation(value = "删除 付款方式", notes = "支持批量")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "付款方式的ID", required = true,
            dataType = "long",allowMultiple = true)
    @DeleteMapping(value = "delete")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("delete ids:", ids);
        if (CollectionUtils.isEmpty(ids)){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        payWayService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "获取 付款方式", notes = "获取指定ID的付款方式信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "付款方式的ID", required = true, dataType = "long")
    @GetMapping(value = "get/{id}")
    public Result<BaseDocumentTypePayWayVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BaseDocumentTypePayWay payWay = payWayService.getById(id);
        if (payWay == null){
            return Result.success();
        }
        BaseDocumentTypePayWayVo baseDocumentTypePayWayVo = new BeanUtils<BaseDocumentTypePayWayVo>()
                .copyObj(payWay, BaseDocumentTypePayWayVo.class);
        return new Result<BaseDocumentTypePayWayVo>().sucess(baseDocumentTypePayWayVo);
    }

    @ApiOperation(value = "列表查询付款方式", notes = "根据条件查询付款方式列表数据")
    @ApiParam(name = "payWayQueryForm", value = "付款方式查询参数", required = true)
    @GetMapping("list/condition")
    public Result<List<BaseDocumentTypePayWayVo>> list(
            @Valid BaseDocumentTypePayWayQueryForm payWayQueryForm) {
        log.info("search with payWayQueryForm:",
                payWayQueryForm.toString());
        List<BaseDocumentTypePayWay> baseDocumentTypePayWayList = payWayService
                .selectList(payWayQueryForm);
        List<BaseDocumentTypePayWayVo> baseDocumentTypePayWayVoList = new BeanUtils<BaseDocumentTypePayWayVo>()
                .copyObjs(baseDocumentTypePayWayList, BaseDocumentTypePayWayVo.class);
        return new Result<List<BaseDocumentTypePayWayVo>>().sucess(baseDocumentTypePayWayVoList);
    }

    @ApiOperation(value = "分页查询付款方式", notes = "根据条件查询付款方式分页数据")
    @ApiParam(name = "payWayQueryForm", value = "付款方式查询参数", required = true)
    @GetMapping("page/condition")
    public Result<IPage<BaseDocumentTypePayWayVo>> search(
            @Valid BaseDocumentTypePayWayQueryForm payWayQueryForm) {
        log.info("search with payWayQueryForm:", payWayQueryForm.toString());
        IPage<BaseDocumentTypePayWay> baseDocumentTypePayWayPage = payWayService
                .selectPage(payWayQueryForm);
        IPage<BaseDocumentTypePayWayVo> baseDocumentTypePayWayVoPage = new BeanUtils<BaseDocumentTypePayWayVo>()
                .copyPageObjs(baseDocumentTypePayWayPage, BaseDocumentTypePayWayVo.class);
        return new Result<IPage<BaseDocumentTypePayWayVo>>().sucess(baseDocumentTypePayWayVoPage);
    }

    @ApiOperation(value = "根据功能模块查询付款方式，用于下拉框", notes = "根据功能模块查询付款方式，用于下拉框")
    @ApiParam(name = "functionModule", value = "功能模块", required = true)
    @GetMapping("listSelectByFunctionModule/{functionModule}")
    public Result<List<Select2DataSource>> listSelectByFunctionModule(
            @PathVariable String functionModule) {
        log.info("search with payWayQueryForm:", functionModule);
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(functionModule,deptVo.getDeptCode());
        if (documentType == null) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        BaseDocumentTypePayWayQueryForm payWayQueryForm = new BaseDocumentTypePayWayQueryForm();
        payWayQueryForm.setDocumentTypeId(documentType.getId()+"");
        payWayQueryForm.setValidFlag(FsscEums.VALID.getValue());
        List<BaseDocumentTypePayWay> payWayList = payWayService.selectList(payWayQueryForm);
        List<Select2DataSource> dataSourceList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(payWayList)){
            for(BaseDocumentTypePayWay payWay : payWayList){
                dataSourceList.add(new Select2DataSource(payWay.getId(),payWay.getCode(),payWay.getName()));
            }
        }
        return new Result<List<Select2DataSource>>().sucess(dataSourceList);
    }
}



