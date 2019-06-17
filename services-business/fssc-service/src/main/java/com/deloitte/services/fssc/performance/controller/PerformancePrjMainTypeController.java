package com.deloitte.services.fssc.performance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.api.fssc.performance.client.PerformancePrjMainTypeClient;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjMainTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformancePrjMainType;
import com.deloitte.services.fssc.performance.service.IPerformancePrjMainTypeService;
import com.deloitte.services.fssc.performance.service.IPerformancePrjSubTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :   绩效项目大类 控制器实现类
 * @Modified :
 */
@Api(tags = "绩效项目大类 操作接口")
@Slf4j
@RestController
public class PerformancePrjMainTypeController implements PerformancePrjMainTypeClient {

    @Autowired
    public IPerformancePrjMainTypeService mainTypeService;

    @Autowired
    private IPerformancePrjSubTypeService subTypeService;

    @Override
    @ApiOperation(value = "新增/修改 绩效项目大类", notes = "新增/修改 多个绩效项目大类")
    @ApiParam(name = "mainTypeFormList", value = "新增/修改 绩效项目大类的form表单 集合", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addOrUpdateMainType(
            @Valid @RequestBody List<PerformancePrjMainTypeForm> mainTypeFormList) {
        log.info("add or update mainTypeFormList:", JSONObject.toJSONString(mainTypeFormList));
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”；
        if (CollectionUtils.isEmpty(mainTypeFormList)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        mainTypeService.updateOrSaveBatch(mainTypeFormList);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "删除 绩效项目大类", notes = "支出批量删除")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id的集合", required = true,
            dataType = "long", allowMultiple = true)
    public Result delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        if (subTypeService.existsByMainTypeIds(ids)) {
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        mainTypeService.removeByIds(ids);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取 绩效项目大类", notes = "获取指定ID的P绩效项目大类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "绩效项目大类的ID", required = true,
            dataType = "long")
    public Result<PerformancePrjMainTypeVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PerformancePrjMainType performancePrjMainType = mainTypeService.getById(id);
        if (performancePrjMainType == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        PerformancePrjMainTypeVo performancePrjMainTypeVo = new BeanUtils<PerformancePrjMainTypeVo>()
                .copyObj(performancePrjMainType, PerformancePrjMainTypeVo.class);
        return new Result<PerformancePrjMainTypeVo>().sucess(performancePrjMainTypeVo);
    }

    @ApiOperation(value = "修改生失效状态", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "form", value = "修改生失效状态Form", required = true)
    @PostMapping(value = path + "/modifyStatus")
    public Result modifyStatus(@RequestBody @Valid BaseModifyStatusForm form) {
        log.info("search with BaseModifyStatusForm:{}", JSON.toJSONString(form));
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //不判断是否被引用
//        if (FsscEums.UN_VALID.getValue().equals(form.getStatus())) {
//            if (subTypeService.existsByMainTypeIds(form.getIds(), FsscEums.VALID.getValue())) {
//                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
//            }
 //       }
        mainTypeService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @Override
    @ApiOperation(value = "列表查询 绩效项目大类", notes = "根据条件查询 绩效项目大类 列表数据")
    @ApiParam(name = "queryForm", value = "绩效项目大类 查询参数", required = true)
    public Result<List<PerformancePrjMainTypeVo>> list(
            @Valid @RequestBody PerformancePrjMainTypeQueryForm queryForm) {
        log.info("search with queryForm:", queryForm.toString());
        List<PerformancePrjMainType> performancePrjMainTypeList = mainTypeService
                .selectList(queryForm);
        List<PerformancePrjMainTypeVo> performancePrjMainTypeVoList = new BeanUtils<PerformancePrjMainTypeVo>()
                .copyObjs(performancePrjMainTypeList, PerformancePrjMainTypeVo.class);
        return new Result<List<PerformancePrjMainTypeVo>>().sucess(performancePrjMainTypeVoList);
    }

    @ApiOperation(value = "列表查询 绩效项目大类,用于下拉框数据源",
            notes = "根据条件查询 绩效项目大类 列表数据,用于下拉框数据源")
    @ApiParam(name = "queryForm", value = "绩效项目大类 查询参数", required = true)
    @PostMapping(value = path + "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(
            @Valid @RequestBody PerformancePrjMainTypeQueryForm queryForm) {
        List<PerformancePrjMainType> mainTypeList = mainTypeService.selectList(queryForm);
        List<PerformancePrjMainTypeVo> mainTypeVoList = new BeanUtils<PerformancePrjMainTypeVo>()
                .copyObjs(mainTypeList, PerformancePrjMainTypeVo.class);
        List<Select2DataSource> dataSourceList = new ArrayList<>(mainTypeVoList.size());
        for (PerformancePrjMainTypeVo mainTypeVo : mainTypeVoList) {
            Select2DataSource dataSource = new Select2DataSource(mainTypeVo.getId(),
                    mainTypeVo.getCode(), mainTypeVo.getName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @Override
    @ApiOperation(value = "分页查询 绩效项目大类", notes = "根据条件查询 绩效项目大类分页数据")
    @ApiParam(name = "queryForm", value = "绩效项目大类 查询参数", required = true)
    public Result<GdcPage<PerformancePrjMainTypeVo>> search(
            @Valid @RequestBody PerformancePrjMainTypeQueryForm queryForm) {
        log.info("search with queryForm:", queryForm.toString());
        IPage<PerformancePrjMainType> performancePrjMainTypePage = mainTypeService
                .selectPage(queryForm);
        IPage<PerformancePrjMainTypeVo> performancePrjMainTypeVoPage = new BeanUtils<PerformancePrjMainTypeVo>()
                .copyPageObjs(performancePrjMainTypePage, PerformancePrjMainTypeVo.class);
        return new Result<GdcPage<PerformancePrjMainTypeVo>>()
                .sucess(new GdcPage<>(performancePrjMainTypeVoPage));
    }

}



