package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.AllActBakQueryForm;
import com.deloitte.platform.api.project.vo.AllActBakForm;
import com.deloitte.platform.api.project.vo.AllActBakVo;
import com.deloitte.platform.api.project.client.AllActBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IAllActBakService;
import com.deloitte.services.project.entity.AllActBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   AllActBak控制器实现类
 * @Modified :
 */
@Api(tags = "AllActBak操作接口")
@Slf4j
@RestController
public class AllActBakController implements AllActBakClient {

    @Autowired
    public IAllActBakService  allActBakService;


    @Override
    @ApiOperation(value = "新增AllActBak", notes = "新增一个AllActBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="allActBakForm",value="新增AllActBak的form表单",required=true)  AllActBakForm allActBakForm) {
        log.info("form:",  allActBakForm.toString());
        AllActBak allActBak =new BeanUtils<AllActBak>().copyObj(allActBakForm,AllActBak.class);
        return Result.success( allActBakService.save(allActBak));
    }


    @Override
    @ApiOperation(value = "删除AllActBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AllActBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        allActBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改AllActBak", notes = "修改指定AllActBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AllActBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="allActBakForm",value="修改AllActBak的form表单",required=true)  AllActBakForm allActBakForm) {
        AllActBak allActBak =new BeanUtils<AllActBak>().copyObj(allActBakForm,AllActBak.class);
        allActBak.setId(id);
        allActBakService.saveOrUpdate(allActBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取AllActBak", notes = "获取指定ID的AllActBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AllActBak的ID", required = true, dataType = "long")
    public Result<AllActBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AllActBak allActBak=allActBakService.getById(id);
        AllActBakVo allActBakVo=new BeanUtils<AllActBakVo>().copyObj(allActBak,AllActBakVo.class);
        return new Result<AllActBakVo>().sucess(allActBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询AllActBak", notes = "根据条件查询AllActBak列表数据")
    public Result<List<AllActBakVo>> list(@Valid @RequestBody @ApiParam(name="allActBakQueryForm",value="AllActBak查询参数",required=true) AllActBakQueryForm allActBakQueryForm) {
        log.info("search with allActBakQueryForm:", allActBakQueryForm.toString());
        List<AllActBak> allActBakList=allActBakService.selectList(allActBakQueryForm);
        List<AllActBakVo> allActBakVoList=new BeanUtils<AllActBakVo>().copyObjs(allActBakList,AllActBakVo.class);
        return new Result<List<AllActBakVo>>().sucess(allActBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询AllActBak", notes = "根据条件查询AllActBak分页数据")
    public Result<IPage<AllActBakVo>> search(@Valid @RequestBody @ApiParam(name="allActBakQueryForm",value="AllActBak查询参数",required=true) AllActBakQueryForm allActBakQueryForm) {
        log.info("search with allActBakQueryForm:", allActBakQueryForm.toString());
        IPage<AllActBak> allActBakPage=allActBakService.selectPage(allActBakQueryForm);
        IPage<AllActBakVo> allActBakVoPage=new BeanUtils<AllActBakVo>().copyPageObjs(allActBakPage,AllActBakVo.class);
        return new Result<IPage<AllActBakVo>>().sucess(allActBakVoPage);
    }

}



