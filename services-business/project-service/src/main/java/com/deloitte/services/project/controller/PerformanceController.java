package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.PerformanceQueryForm;
import com.deloitte.platform.api.project.vo.PerformanceForm;
import com.deloitte.platform.api.project.vo.PerformanceVo;
import com.deloitte.platform.api.project.client.PerformanceClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IPerformanceService;
import com.deloitte.services.project.entity.Performance;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Performance控制器实现类
 * @Modified :
 */
@Api(tags = "Performance操作接口")
@Slf4j
@RestController
@RequestMapping("/project/performance")
public class PerformanceController implements PerformanceClient {

    @Autowired
    public IPerformanceService  performanceService;


    @Override
    @ApiOperation(value = "新增Performance", notes = "新增一个Performance")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="performanceForm",value="新增Performance的form表单",required=true)  PerformanceForm performanceForm) {
        log.info("form:",  performanceForm.toString());
        Performance performance =new BeanUtils<Performance>().copyObj(performanceForm,Performance.class);
        return Result.success( performanceService.save(performance));
    }


    @Override
    @ApiOperation(value = "删除Performance", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PerformanceID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        performanceService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Performance", notes = "修改指定Performance信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Performance的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="performanceForm",value="修改Performance的form表单",required=true)  PerformanceForm performanceForm) {
        Performance performance =new BeanUtils<Performance>().copyObj(performanceForm,Performance.class);
        performance.setId(id);
        performanceService.saveOrUpdate(performance);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Performance", notes = "获取指定ID的Performance信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Performance的ID", required = true, dataType = "long")
    public Result<PerformanceVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Performance performance=performanceService.getById(id);
        PerformanceVo performanceVo=new BeanUtils<PerformanceVo>().copyObj(performance,PerformanceVo.class);
        return new Result<PerformanceVo>().sucess(performanceVo);
    }

    @Override
    @ApiOperation(value = "列表查询Performance", notes = "根据条件查询Performance列表数据")
    public Result<List<PerformanceVo>> list(@Valid @RequestBody @ApiParam(name="performanceQueryForm",value="Performance查询参数",required=true) PerformanceQueryForm performanceQueryForm) {
        log.info("search with performanceQueryForm:", performanceQueryForm.toString());
        List<Performance> performanceList=performanceService.selectList(performanceQueryForm);
        List<PerformanceVo> performanceVoList=new BeanUtils<PerformanceVo>().copyObjs(performanceList,PerformanceVo.class);
        return new Result<List<PerformanceVo>>().sucess(performanceVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Performance", notes = "根据条件查询Performance分页数据")
    public Result<IPage<PerformanceVo>> search(@Valid @RequestBody @ApiParam(name="performanceQueryForm",value="Performance查询参数",required=true) PerformanceQueryForm performanceQueryForm) {
        log.info("search with performanceQueryForm:", performanceQueryForm.toString());
        IPage<Performance> performancePage=performanceService.selectPage(performanceQueryForm);
        IPage<PerformanceVo> performanceVoPage=new BeanUtils<PerformanceVo>().copyPageObjs(performancePage,PerformanceVo.class);
        return new Result<IPage<PerformanceVo>>().sucess(performanceVoPage);
    }

    @ApiOperation(value = "获取财务指标库", notes = "获取财务指标库")
    @GetMapping(value = "/getPerformanceFromFsscLibrary")
    public Result getPerformanceFromFsscLibrary() {
        return Result.success(performanceService.getPerformanceFromFsscLibrary());
    }

}



