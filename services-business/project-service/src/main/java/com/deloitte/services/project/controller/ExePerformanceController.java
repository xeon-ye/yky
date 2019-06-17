package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExePerformanceQueryForm;
import com.deloitte.platform.api.project.vo.ExePerformanceForm;
import com.deloitte.platform.api.project.vo.ExePerformanceVo;
import com.deloitte.platform.api.project.client.ExePerformanceClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IExePerformanceService;
import com.deloitte.services.project.entity.ExePerformance;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   ExePerformance控制器实现类
 * @Modified :
 */
@Api(tags = "ExePerformance操作接口")
@Slf4j
@RestController
public class ExePerformanceController implements ExePerformanceClient {

    @Autowired
    public IExePerformanceService  exePerformanceService;


    @Override
    @ApiOperation(value = "新增ExePerformance", notes = "新增一个ExePerformance")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="exePerformanceForm",value="新增ExePerformance的form表单",required=true)  ExePerformanceForm exePerformanceForm) {
        log.info("form:",  exePerformanceForm.toString());
        ExePerformance exePerformance =new BeanUtils<ExePerformance>().copyObj(exePerformanceForm,ExePerformance.class);
        return Result.success( exePerformanceService.save(exePerformance));
    }


    @Override
    @ApiOperation(value = "删除ExePerformance", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExePerformanceID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        exePerformanceService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ExePerformance", notes = "修改指定ExePerformance信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ExePerformance的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="exePerformanceForm",value="修改ExePerformance的form表单",required=true)  ExePerformanceForm exePerformanceForm) {
        ExePerformance exePerformance =new BeanUtils<ExePerformance>().copyObj(exePerformanceForm,ExePerformance.class);
        exePerformance.setId(id);
        exePerformanceService.saveOrUpdate(exePerformance);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ExePerformance", notes = "获取指定ID的ExePerformance信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExePerformance的ID", required = true, dataType = "long")
    public Result<ExePerformanceVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ExePerformance exePerformance=exePerformanceService.getById(id);
        ExePerformanceVo exePerformanceVo=new BeanUtils<ExePerformanceVo>().copyObj(exePerformance,ExePerformanceVo.class);
        return new Result<ExePerformanceVo>().sucess(exePerformanceVo);
    }

    @Override
    @ApiOperation(value = "列表查询ExePerformance", notes = "根据条件查询ExePerformance列表数据")
    public Result<List<ExePerformanceVo>> list(@Valid @RequestBody @ApiParam(name="exePerformanceQueryForm",value="ExePerformance查询参数",required=true) ExePerformanceQueryForm exePerformanceQueryForm) {
        log.info("search with exePerformanceQueryForm:", exePerformanceQueryForm.toString());
        List<ExePerformance> exePerformanceList=exePerformanceService.selectList(exePerformanceQueryForm);
        List<ExePerformanceVo> exePerformanceVoList=new BeanUtils<ExePerformanceVo>().copyObjs(exePerformanceList,ExePerformanceVo.class);
        return new Result<List<ExePerformanceVo>>().sucess(exePerformanceVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ExePerformance", notes = "根据条件查询ExePerformance分页数据")
    public Result<IPage<ExePerformanceVo>> search(@Valid @RequestBody @ApiParam(name="exePerformanceQueryForm",value="ExePerformance查询参数",required=true) ExePerformanceQueryForm exePerformanceQueryForm) {
        log.info("search with exePerformanceQueryForm:", exePerformanceQueryForm.toString());
        IPage<ExePerformance> exePerformancePage=exePerformanceService.selectPage(exePerformanceQueryForm);
        IPage<ExePerformanceVo> exePerformanceVoPage=new BeanUtils<ExePerformanceVo>().copyPageObjs(exePerformancePage,ExePerformanceVo.class);
        return new Result<IPage<ExePerformanceVo>>().sucess(exePerformanceVoPage);
    }

}



