package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.PerformanceBakQueryForm;
import com.deloitte.platform.api.project.vo.PerformanceBakForm;
import com.deloitte.platform.api.project.vo.PerformanceBakVo;
import com.deloitte.platform.api.project.client.PerformanceBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IPerformanceBakService;
import com.deloitte.services.project.entity.PerformanceBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   PerformanceBak控制器实现类
 * @Modified :
 */
@Api(tags = "PerformanceBak操作接口")
@Slf4j
@RestController
public class PerformanceBakController implements PerformanceBakClient {

    @Autowired
    public IPerformanceBakService  performanceBakService;


    @Override
    @ApiOperation(value = "新增PerformanceBak", notes = "新增一个PerformanceBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="performanceBakForm",value="新增PerformanceBak的form表单",required=true)  PerformanceBakForm performanceBakForm) {
        log.info("form:",  performanceBakForm.toString());
        PerformanceBak performanceBak =new BeanUtils<PerformanceBak>().copyObj(performanceBakForm,PerformanceBak.class);
        return Result.success( performanceBakService.save(performanceBak));
    }


    @Override
    @ApiOperation(value = "删除PerformanceBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PerformanceBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        performanceBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改PerformanceBak", notes = "修改指定PerformanceBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "PerformanceBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="performanceBakForm",value="修改PerformanceBak的form表单",required=true)  PerformanceBakForm performanceBakForm) {
        PerformanceBak performanceBak =new BeanUtils<PerformanceBak>().copyObj(performanceBakForm,PerformanceBak.class);
        performanceBak.setId(id);
        performanceBakService.saveOrUpdate(performanceBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取PerformanceBak", notes = "获取指定ID的PerformanceBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PerformanceBak的ID", required = true, dataType = "long")
    public Result<PerformanceBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PerformanceBak performanceBak=performanceBakService.getById(id);
        PerformanceBakVo performanceBakVo=new BeanUtils<PerformanceBakVo>().copyObj(performanceBak,PerformanceBakVo.class);
        return new Result<PerformanceBakVo>().sucess(performanceBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询PerformanceBak", notes = "根据条件查询PerformanceBak列表数据")
    public Result<List<PerformanceBakVo>> list(@Valid @RequestBody @ApiParam(name="performanceBakQueryForm",value="PerformanceBak查询参数",required=true) PerformanceBakQueryForm performanceBakQueryForm) {
        log.info("search with performanceBakQueryForm:", performanceBakQueryForm.toString());
        List<PerformanceBak> performanceBakList=performanceBakService.selectList(performanceBakQueryForm);
        List<PerformanceBakVo> performanceBakVoList=new BeanUtils<PerformanceBakVo>().copyObjs(performanceBakList,PerformanceBakVo.class);
        return new Result<List<PerformanceBakVo>>().sucess(performanceBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询PerformanceBak", notes = "根据条件查询PerformanceBak分页数据")
    public Result<IPage<PerformanceBakVo>> search(@Valid @RequestBody @ApiParam(name="performanceBakQueryForm",value="PerformanceBak查询参数",required=true) PerformanceBakQueryForm performanceBakQueryForm) {
        log.info("search with performanceBakQueryForm:", performanceBakQueryForm.toString());
        IPage<PerformanceBak> performanceBakPage=performanceBakService.selectPage(performanceBakQueryForm);
        IPage<PerformanceBakVo> performanceBakVoPage=new BeanUtils<PerformanceBakVo>().copyPageObjs(performanceBakPage,PerformanceBakVo.class);
        return new Result<IPage<PerformanceBakVo>>().sucess(performanceBakVoPage);
    }

}



