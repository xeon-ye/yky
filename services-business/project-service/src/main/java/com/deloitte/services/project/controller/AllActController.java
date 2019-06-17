package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.AllActQueryForm;
import com.deloitte.platform.api.project.vo.AllActForm;
import com.deloitte.platform.api.project.vo.AllActVo;
import com.deloitte.platform.api.project.client.AllActClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IAllActService;
import com.deloitte.services.project.entity.AllAct;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   AllAct控制器实现类
 * @Modified :
 */
@Api(tags = "AllAct操作接口")
@Slf4j
@RestController
public class AllActController implements AllActClient {

    @Autowired
    public IAllActService  allActService;


    @Override
    @ApiOperation(value = "新增AllAct", notes = "新增一个AllAct")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="allActForm",value="新增AllAct的form表单",required=true)  AllActForm allActForm) {
        log.info("form:",  allActForm.toString());
        AllAct allAct =new BeanUtils<AllAct>().copyObj(allActForm,AllAct.class);
        return Result.success( allActService.save(allAct));
    }


    @Override
    @ApiOperation(value = "删除AllAct", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AllActID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        allActService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改AllAct", notes = "修改指定AllAct信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AllAct的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="allActForm",value="修改AllAct的form表单",required=true)  AllActForm allActForm) {
        AllAct allAct =new BeanUtils<AllAct>().copyObj(allActForm,AllAct.class);
        allAct.setId(id);
        allActService.saveOrUpdate(allAct);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取AllAct", notes = "获取指定ID的AllAct信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AllAct的ID", required = true, dataType = "long")
    public Result<AllActVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AllAct allAct=allActService.getById(id);
        AllActVo allActVo=new BeanUtils<AllActVo>().copyObj(allAct,AllActVo.class);
        return new Result<AllActVo>().sucess(allActVo);
    }

    @Override
    @ApiOperation(value = "列表查询AllAct", notes = "根据条件查询AllAct列表数据")
    public Result<List<AllActVo>> list(@Valid @RequestBody @ApiParam(name="allActQueryForm",value="AllAct查询参数",required=true) AllActQueryForm allActQueryForm) {
        log.info("search with allActQueryForm:", allActQueryForm.toString());
        List<AllAct> allActList=allActService.selectList(allActQueryForm);
        List<AllActVo> allActVoList=new BeanUtils<AllActVo>().copyObjs(allActList,AllActVo.class);
        return new Result<List<AllActVo>>().sucess(allActVoList);
    }


    @Override
    @ApiOperation(value = "分页查询AllAct", notes = "根据条件查询AllAct分页数据")
    public Result<IPage<AllActVo>> search(@Valid @RequestBody @ApiParam(name="allActQueryForm",value="AllAct查询参数",required=true) AllActQueryForm allActQueryForm) {
        log.info("search with allActQueryForm:", allActQueryForm.toString());
        IPage<AllAct> allActPage=allActService.selectPage(allActQueryForm);
        IPage<AllActVo> allActVoPage=new BeanUtils<AllActVo>().copyPageObjs(allActPage,AllActVo.class);
        return new Result<IPage<AllActVo>>().sucess(allActVoPage);
    }

}



