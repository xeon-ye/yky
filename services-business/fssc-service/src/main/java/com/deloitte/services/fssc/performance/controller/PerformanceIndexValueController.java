package com.deloitte.services.fssc.performance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexValueClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexValueQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexValue;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexValueService;
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
 * @Description :   绩效指标值 控制器实现类
 * @Modified :
 */
@Api(tags = "绩效指标值 操作接口")
@Slf4j
@RestController
public class PerformanceIndexValueController implements PerformanceIndexValueClient {

    @Autowired
    public IPerformanceIndexValueService indexValueService;

    @Autowired
    public IPerformanceIndexService indexService;

    @Override
    @ApiOperation(value = "新增/修改 绩效指标值", notes = "新增 绩效指标值")
    @ApiParam(name = "indexValueFormList", value = "新增绩效指标值的form表单", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addOrUpdateIndexValue(
            @Valid @RequestBody List<PerformanceIndexValueForm> indexValueFormList) {
        log.info("add or update performanceIndexFormList:", JSONObject.toJSONString(indexValueFormList));
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”;
        if (CollectionUtils.isEmpty(indexValueFormList)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        indexValueService.updateOrSaveBatch(indexValueFormList);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "删除 绩效指标值", notes = "支出批量删除")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id的集合", required = true,
            dataType = "long", allowMultiple = true)
    public Result delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 如果关联指标定义后,不能删除
        if(indexService.existsByValueIds(ids)) {
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        indexValueService.removeByIds(ids);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取 绩效指标值", notes = "获取指定ID的绩效指标值 信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "绩效指标值 的ID", required = true, dataType = "long")
    public Result<PerformanceIndexValueVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PerformanceIndexValue performanceIndex = indexValueService.getById(id);
        if (performanceIndex == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        PerformanceIndexValueVo performanceIndexValueVo = new BeanUtils<PerformanceIndexValueVo>()
                .copyObj(performanceIndex, PerformanceIndexValueVo.class);
        return new Result<PerformanceIndexValueVo>().sucess(performanceIndexValueVo);
    }

    @ApiOperation(value = "修改生失效状态", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "form", value = "修改生失效状态Form", required = true)
    @PostMapping(value = path + "/modifyStatus")
    public Result modifyStatus(@RequestBody @Valid BaseModifyStatusForm form) {
        log.info("search with BaseModifyStatusForm:{}", JSON.toJSONString(form));
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
//        if (FsscEums.UN_VALID.getValue().equals(form.getStatus())) {
//            if(indexService.existsByValueIds(form.getIds(),FsscEums.VALID.getValue())) {
//                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
//            }
//        }
        indexValueService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @Override
    @ApiOperation(value = "列表查询 绩效指标值", notes = "根据条件查询 绩效指标值 列表数据")
    @ApiParam(name = "queryForm", value = "查询参数", required = true)
    public Result<List<PerformanceIndexValueVo>> list(
            @Valid @RequestBody PerformanceIndexValueQueryForm queryForm) {
        log.info("search with queryForm:", queryForm.toString());
        List<PerformanceIndexValue> performanceIndexList = indexValueService.selectList(queryForm);
        List<PerformanceIndexValueVo> performanceIndexValueVoList = new BeanUtils<PerformanceIndexValueVo>()
                .copyObjs(performanceIndexList, PerformanceIndexValueVo.class);
        return new Result<List<PerformanceIndexValueVo>>().sucess(performanceIndexValueVoList);
    }

    @ApiOperation(value = "列表查询 绩效指标值,用于下拉框数据源",
            notes = "根据条件查询 绩效指标值 列表数据,用于下拉框数据源")
    @ApiParam(name = "queryForm", value = "绩效指标值 查询参数", required = true)
    @PostMapping(value = path + "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(
            @Valid @RequestBody PerformanceIndexValueQueryForm queryForm) {
        List<PerformanceIndexValue> indexValueList = indexValueService.selectList(queryForm);
        List<PerformanceIndexValueVo> indexValueVoList = new BeanUtils<PerformanceIndexValueVo>()
                .copyObjs(indexValueList, PerformanceIndexValueVo.class);
        List<Select2DataSource> dataSourceList = new ArrayList<>(indexValueVoList.size());
        for (PerformanceIndexValueVo indexValueVo : indexValueVoList) {
            Select2DataSource dataSource = new Select2DataSource(indexValueVo.getId(),
                    indexValueVo.getCode()
                    , indexValueVo.getName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @Override
    @ApiOperation(value = "分页查询 绩效指标值", notes = "根据条件查询 绩效指标分页数据")
    @ApiParam(name = "queryForm", value = "绩效指标值 查询参数", required = true)
    public Result<GdcPage<PerformanceIndexValueVo>> search(
            @Valid @RequestBody PerformanceIndexValueQueryForm queryForm) {
        log.info("search with queryForm:", queryForm.toString());
        IPage<PerformanceIndexValue> performanceIndexPage = indexValueService
                .selectPage(queryForm);
        IPage<PerformanceIndexValueVo> performanceIndexVoPage = new BeanUtils<PerformanceIndexValueVo>()
                .copyPageObjs(performanceIndexPage, PerformanceIndexValueVo.class);
        return new Result<GdcPage<PerformanceIndexValueVo>>().sucess(new GdcPage(performanceIndexVoPage));
    }

}



