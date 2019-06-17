package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.PoQueryForm;
import com.deloitte.platform.api.project.vo.PoForm;
import com.deloitte.platform.api.project.vo.PoVo;
import com.deloitte.platform.api.project.client.PoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IPoService;
import com.deloitte.services.project.entity.Po;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Po控制器实现类
 * @Modified :
 */
@Api(tags = "Po操作接口")
@Slf4j
@RestController
public class PoController implements PoClient {

    @Autowired
    public IPoService  poService;


    @Override
    @ApiOperation(value = "新增Po", notes = "新增一个Po")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="poForm",value="新增Po的form表单",required=true)  PoForm poForm) {
        log.info("form:",  poForm.toString());
        Po po =new BeanUtils<Po>().copyObj(poForm,Po.class);
        return Result.success( poService.save(po));
    }


    @Override
    @ApiOperation(value = "删除Po", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        poService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Po", notes = "修改指定Po信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Po的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="poForm",value="修改Po的form表单",required=true)  PoForm poForm) {
        Po po =new BeanUtils<Po>().copyObj(poForm,Po.class);
        po.setId(id);
        poService.saveOrUpdate(po);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Po", notes = "获取指定ID的Po信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Po的ID", required = true, dataType = "long")
    public Result<PoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Po po=poService.getById(id);
        PoVo poVo=new BeanUtils<PoVo>().copyObj(po,PoVo.class);
        return new Result<PoVo>().sucess(poVo);
    }

    @Override
    @ApiOperation(value = "列表查询Po", notes = "根据条件查询Po列表数据")
    public Result<List<PoVo>> list(@Valid @RequestBody @ApiParam(name="poQueryForm",value="Po查询参数",required=true) PoQueryForm poQueryForm) {
        log.info("search with poQueryForm:", poQueryForm.toString());
        List<Po> poList=poService.selectList(poQueryForm);
        List<PoVo> poVoList=new BeanUtils<PoVo>().copyObjs(poList,PoVo.class);
        return new Result<List<PoVo>>().sucess(poVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Po", notes = "根据条件查询Po分页数据")
    public Result<IPage<PoVo>> search(@Valid @RequestBody @ApiParam(name="poQueryForm",value="Po查询参数",required=true) PoQueryForm poQueryForm) {
        log.info("search with poQueryForm:", poQueryForm.toString());
        IPage<Po> poPage=poService.selectPage(poQueryForm);
        IPage<PoVo> poVoPage=new BeanUtils<PoVo>().copyPageObjs(poPage,PoVo.class);
        return new Result<IPage<PoVo>>().sucess(poVoPage);
    }

}



