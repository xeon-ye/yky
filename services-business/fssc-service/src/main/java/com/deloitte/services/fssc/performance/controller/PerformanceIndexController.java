package com.deloitte.services.fssc.performance.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformanceIndex;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :   绩效指标 控制器实现类
 * @Modified :
 */
@Api(tags = "绩效指标定义 操作接口")
@Slf4j
@RestController
public class PerformanceIndexController implements PerformanceIndexClient {

    @Autowired
    public IPerformanceIndexService indexService;


    @Override
    @ApiOperation(value = "新增/修改 绩效指标", notes = "新增一个绩效指标")
    @ApiParam(name = "indexFormList", value = "新增绩效指标的form表单", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addOrUpdateIndex(@Valid @RequestBody List<PerformanceIndexForm> indexFormList) {
        log.info("addOrUpdateIndex with indexFormList:", indexFormList.toString());
        // TODO 编辑时，若行数据已被其他数据表占用，则提示“该数据已被占用，不可编辑”;
        if (CollectionUtils.isEmpty(indexFormList)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        indexService.updateOrSaveBatch(indexFormList);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "删除 绩效指标", notes = "支持批量")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "", required = true, dataType = "long", allowMultiple = true)
    public Result delete(@RequestBody List<Long> ids) {
        //TODO 如果被下级指标关联,不能删除
        if(indexService.existsByChild(ids)) {
            throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        }
        indexService.removeByIds(ids);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取 绩效指标", notes = "获取指定ID的绩效指标信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "绩效指标的ID", required = true, dataType = "long")
    public Result<PerformanceIndexVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PerformanceIndex performanceIndex = indexService.getById(id);
        PerformanceIndexVo performanceIndexVo = new BeanUtils<PerformanceIndexVo>()
                .copyObj(performanceIndex, PerformanceIndexVo.class);
        return new Result<PerformanceIndexVo>().sucess(performanceIndexVo);
    }

    @ApiOperation(value = "修改生失效状态", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "form", value = "修改生失效状态Form", required = true)
    @PostMapping(value = path + "/modifyStatus")
    public Result modifyStatus(@RequestBody @Valid BaseModifyStatusForm form) {
        log.info("search with BaseModifyStatusForm:{}", JSON.toJSONString(form));
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        if (FsscEums.UN_VALID.getValue().equals(form.getStatus())) {
            if (indexService.existsByChild(form.getIds(),FsscEums.VALID.getValue())){
                throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
            }
        }
        indexService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @Override
    @ApiOperation(value = "列表查询 绩效指标", notes = "根据条件查询 绩效指标列表数据")
     @ApiParam(name = "indexQueryForm", value = "绩效指标 查询参数", required = true)
    public Result<List<PerformanceIndexVo>> list(@Valid @RequestBody PerformanceIndexQueryForm indexQueryForm) {
        log.info("search with indexQueryForm:", indexQueryForm.toString());
        indexQueryForm.setPageSize(999999);
        IPage<PerformanceIndexVo> voPage = indexService.selectVoPage(indexQueryForm);
        return new Result<List<PerformanceIndexVo>>().sucess(voPage.getRecords());
    }

    @ApiOperation(value = "列表查询 绩效指标,用于下拉框数据源",
            notes = "根据条件查询 绩效指标 列表数据,用于下拉框数据源")
    @ApiParam(name = "queryForm", value = "绩效指标 查询参数", required = true)
    @PostMapping(value = path + "/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelect(
            @Valid @RequestBody PerformanceIndexQueryForm queryForm) {
        List<PerformanceIndex> indexValueList = indexService.selectList(queryForm);
        List<PerformanceIndexVo> indexValueVoList = new BeanUtils<PerformanceIndexVo>()
                .copyObjs(indexValueList, PerformanceIndexVo.class);
        List<Select2DataSource> dataSourceList = new ArrayList<>(indexValueVoList.size());
        for (PerformanceIndexVo indexVo : indexValueVoList) {
            Select2DataSource dataSource = new Select2DataSource(indexVo.getId(),
                    indexVo.getCode(), indexVo.getName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @Override
    @ApiOperation(value = "分页查询 绩效指标", notes = "根据条件查询 绩效指标分页数据")
    @ApiParam(name = "indexQueryForm", value = "绩效指标 查询参数", required = true)
    public Result<GdcPage<PerformanceIndexVo>> search(@Valid @RequestBody PerformanceIndexQueryForm indexQueryForm) {
        log.info("search with indexQueryForm:", indexQueryForm.toString());
        IPage<PerformanceIndexVo> voIPage = indexService.selectVoPage(indexQueryForm);
        return new Result<GdcPage<PerformanceIndexVo>>().sucess(new GdcPage<>(voIPage));
    }


    @ApiOperation(value = "列表查询 一级绩效指标,用于下拉框数据源",
            notes = "列表查询一级绩效指标,用于下拉框数据源")
    @PostMapping(value = path + "/listSelectLevel1")
    public Result<List<Select2DataSource>> listSelectLevel1(){
        PerformanceIndexQueryForm queryForm = new PerformanceIndexQueryForm();
        queryForm.setValueLevel(FsscEums.PERFORMANCE_INDEX_LEVEL_1.getValue());
        List<PerformanceIndex> indexValueList = indexService.selectList(queryForm);
        List<PerformanceIndexVo> indexValueVoList = new BeanUtils<PerformanceIndexVo>()
                .copyObjs(indexValueList, PerformanceIndexVo.class);
        List<Select2DataSource> dataSourceList = new ArrayList<>(indexValueVoList.size());
        for (PerformanceIndexVo indexVo : indexValueVoList) {
            Select2DataSource dataSource = new Select2DataSource(indexVo.getId(),
                    indexVo.getCode(), indexVo.getName());
            dataSourceList.add(dataSource);
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "导出", httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "queryForm", value = "导出Form", required = true)
    @GetMapping(value = path + "/export")
    public void exportExcel(HttpServletResponse response, PerformanceIndexQueryForm queryForm) {
        log.info("exportExcel with PerformanceIndexQueryForm:{}", JSON.toJSONString(queryForm));
        List<PerformanceIndexVo> indexVoList = indexService.selectVoPage(queryForm).getRecords();
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("预算绩效指标代码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("预算绩效指标名称").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("预算绩效指标库").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("预算指标判断条件").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("预算指标单位").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("指标级别").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("对应一级指标").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("对应二级指标").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(4000));
        String fileName = "预算绩效指标";
        Object[][] content = new Object[indexVoList.size()][];
        for (int i = 0; i < indexVoList.size(); i++) {
            content[i] = new String[headerList.size()];
            PerformanceIndexVo indexVo = indexVoList.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = indexVo.getCode();
            content[i][2] = indexVo.getName();
            content[i][3] = indexVo.getIndexLibraryName();
            content[i][4] = indexVo.getValueJudgeConditionName();
            content[i][5] = indexVo.getValueUnitName();
            content[i][6] = indexVo.getValueLevel();
            content[i][7] = indexVo.getLevel1Name();
            content[i][8] = indexVo.getLevel2Name();
            content[i][9] = StringUtil.isEmpty(indexVo.getValidFlag()) ? ""
                    : (FsscEums.VALID.getValue().equals(indexVo.getValidFlag()) ? "是" : "否");
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil
                .getSimpleInstance2(headerList, response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    @Override
    @ApiOperation(value = "根据指标库查询一、二、三级指标的数据型结构数据")
    @ApiParam(name = "libraryId", value = "指标库id", required = true)
    public Result<List<PerformanceIndexVo>> search(Long libraryId) {
        PerformanceIndexQueryForm level1QueryForm = new PerformanceIndexQueryForm();
        level1QueryForm.setValueLevel(FsscEums.PERFORMANCE_INDEX_LEVEL_1.getValue());
        List<PerformanceIndex> level1IndexList = indexService.selectList(level1QueryForm);
        List<PerformanceIndexVo> level1IndexVoList = new BeanUtils<PerformanceIndexVo>()
                .copyObjs(level1IndexList, PerformanceIndexVo.class);
        for (PerformanceIndexVo level1IndexVo : level1IndexVoList){
            PerformanceIndexQueryForm level2QueryForm = new PerformanceIndexQueryForm();
            level2QueryForm.setValueLevel(FsscEums.PERFORMANCE_INDEX_LEVEL_2.getValue());
            level2QueryForm.setIndexLibraryId(libraryId);
            level2QueryForm.setValidFlag(FsscEums.VALID.getValue());
            level2QueryForm.setLevel1Id(Long.valueOf(level1IndexVo.getId()));
            List<PerformanceIndex> level2IndexList = indexService.selectList(level2QueryForm);
            List<PerformanceIndexVo> level2IndexVoList = new BeanUtils<PerformanceIndexVo>().copyObjs(level2IndexList,PerformanceIndexVo.class);
            for (PerformanceIndexVo level2IndexVo : level2IndexVoList){
                PerformanceIndexQueryForm level3QueryForm = new PerformanceIndexQueryForm();
                level3QueryForm.setValueLevel(FsscEums.PERFORMANCE_INDEX_LEVEL_3.getValue());
                level3QueryForm.setValidFlag(FsscEums.VALID.getValue());
                level3QueryForm.setIndexLibraryId(libraryId);
                level3QueryForm.setLevel2Id(Long.valueOf(level2IndexVo.getId()));
                List<PerformanceIndex> level3IndexList = indexService.selectList(level3QueryForm);
                List<PerformanceIndexVo> level3IndexVoList = new BeanUtils<PerformanceIndexVo>().copyObjs(level3IndexList,PerformanceIndexVo.class);
                level2IndexVo.setChildIndexVoList(level3IndexVoList);
            }
            level1IndexVo.setChildIndexVoList(level2IndexVoList);
        }
        return Result.success(level1IndexVoList);
    }
}



