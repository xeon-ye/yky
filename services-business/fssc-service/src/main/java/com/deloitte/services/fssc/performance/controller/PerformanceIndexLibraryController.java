package com.deloitte.services.fssc.performance.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexLibraryClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexLibrary;
import com.deloitte.services.fssc.performance.entity.PerformancePrjSubType;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexLibraryService;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
import com.deloitte.services.fssc.performance.service.IPerformancePrjSubTypeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :   绩效指标库 控制器实现类
 * @Modified :
 */
@Api(tags = "绩效指标库 操作接口")
@Slf4j
@RestController
public class PerformanceIndexLibraryController implements PerformanceIndexLibraryClient {

    @Autowired
    private IPerformanceIndexLibraryService indexLibraryService;

    @Autowired
    private IPerformancePrjSubTypeService subTypeService;

    @Autowired
    private IPerformanceIndexService indexService;

    @Override
    @ApiOperation(value = "新增/修改 绩效指标库", notes = "新增/修改多个 绩效指标库,批量操作")
    @ApiParam(name = "indexLibraryFormList", value = "新增 绩效指标库的form表单集合",
            required = true,allowMultiple = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addOrUpdateIndexLibrary(
            @Valid @RequestBody List<PerformanceIndexLibraryForm> indexLibraryFormList) {
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”;
        if (CollectionUtils.isEmpty(indexLibraryFormList)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        indexLibraryService.updateOrSaveBatch(indexLibraryFormList);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "删除 绩效指标库", notes = "支持批量")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "绩效指标库的ID集合", required = true,
            dataType = "long", allowMultiple = true)
    public Result delete(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        //TODO 如果被指标定义关联,不能删除
        if(indexService.existsByLibraryIds(ids)) {
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        indexLibraryService.removeByIds(ids);
        return Result.success();
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
//            //TODO 如果关联指标定义关联,并且是有效状态，不能失效
//            if(indexService.existsByLibraryIds(form.getIds(),FsscEums.VALID.getValue())) {
//                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
//            }
//        }
        indexLibraryService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取单个 绩效指标库", notes = "获取指定ID的 绩效指标库信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "绩效指标库的ID", required = true,
            dataType = "long")
    public Result<PerformanceIndexLibraryVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PerformanceIndexLibrary indexLibrary = indexLibraryService
                .getById(id);
        if (indexLibrary == null) {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        PerformanceIndexLibraryVo indexLibraryVo = new BeanUtils<PerformanceIndexLibraryVo>()
                .copyObj(indexLibrary, PerformanceIndexLibraryVo.class);
        return new Result<PerformanceIndexLibraryVo>().sucess(indexLibraryVo);
    }

    @Override
    @ApiOperation(value = "列表查询 绩效指标库", notes = "根据条件查询 绩效指标库 列表数据")
    @ApiParam(name = "queryForm", value = "绩效指标库 查询参数", required = true)
    public Result<List<PerformanceIndexLibraryVo>> list(
            @Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm) {
        log.info("search with performanceIndexLibraryQueryForm:", queryForm.toString());
        List<PerformanceIndexLibrary> indexLibraryList = indexLibraryService
                .selectList(queryForm);
        List<PerformanceIndexLibraryVo> indexLibraryVoList = new BeanUtils<PerformanceIndexLibraryVo>()
                .copyObjs(indexLibraryList, PerformanceIndexLibraryVo.class);
        return new Result<List<PerformanceIndexLibraryVo>>().sucess(indexLibraryVoList);
    }

    @ApiOperation(value = "列表查询 绩效项目大类,用于下拉框数据源",
            notes = "根据条件查询 绩效项目大类 列表数据,用于下拉框数据源")
    @ApiParam(name = "queryForm", value = "绩效项目大类 查询参数", required = true)
    @PostMapping(value = path + "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(
            @Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm) {
        List<PerformanceIndexLibrary> indexLibraryList = indexLibraryService.selectList(queryForm);
        List<PerformanceIndexLibraryVo> indexValueVoList = new BeanUtils<PerformanceIndexLibraryVo>()
                .copyObjs(indexLibraryList, PerformanceIndexLibraryVo.class);
        List<Select2DataSource> dataSourceList = new ArrayList<>(indexValueVoList.size());
        for (PerformanceIndexLibraryVo indexValueVo : indexValueVoList) {
            Select2DataSource dataSource = new Select2DataSource(indexValueVo.getId(),
                    indexValueVo.getCode(), indexValueVo.getName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @Override
    @ApiOperation(value = "分页查询 绩效指标库", notes = "根据条件查询 绩效指标库分页数据")
    @ApiParam(name = "performanceIndexLibraryQueryForm", value = "绩效指标库 查询参数", required = true)
    public Result<GdcPage<PerformanceIndexLibraryVo>> search(
            @Valid @RequestBody  PerformanceIndexLibraryQueryForm queryForm) {
        log.info("search with performanceIndexLibraryQueryForm:", queryForm.toString());
        IPage<PerformanceIndexLibrary> indexLibraryIPage = indexLibraryService
                .selectPage(queryForm);
        IPage<PerformanceIndexLibraryVo> indexLibraryVoIPage = new BeanUtils<PerformanceIndexLibraryVo>()
                .copyPageObjs(indexLibraryIPage, PerformanceIndexLibraryVo.class);
        List<PerformancePrjSubType> subTypeList = subTypeService.selectList(new PerformancePrjSubTypeQueryForm());
        Map<String,String> idNameMap = new HashMap<>(subTypeList.size());
        if (CollectionUtils.isNotEmpty(subTypeList)){
            for (PerformancePrjSubType subType : subTypeList){
                idNameMap.put(String.valueOf(subType.getId()),subType.getName());
            }
        }
        for (PerformanceIndexLibraryVo indexLibraryVo : indexLibraryVoIPage.getRecords()){
            String subTypeName = idNameMap.get(indexLibraryVo.getSubTypeId());
            indexLibraryVo.setSubTypeName(subTypeName);
        }
        return new Result<GdcPage<PerformanceIndexLibraryVo>>().sucess(new GdcPage(indexLibraryVoIPage));
    }

}



