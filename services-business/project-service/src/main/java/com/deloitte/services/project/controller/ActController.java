package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ActQueryForm;
import com.deloitte.platform.api.project.vo.ActForm;
import com.deloitte.platform.api.project.vo.ActVo;
import com.deloitte.platform.api.project.client.ActClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IActService;
import com.deloitte.services.project.entity.Act;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :   Act控制器实现类
 * @Modified :
 */
@Api(tags = "Act操作接口")
@Slf4j
@RestController
public class ActController implements ActClient {

    @Autowired
    public IActService  actService;


    @Override
    @ApiOperation(value = "新增Act", notes = "新增一个Act")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="actForm",value="新增Act的form表单",required=true)  ActForm actForm) {
        log.info("form:",  actForm.toString());
        Act act =new BeanUtils<Act>().copyObj(actForm,Act.class);
        return Result.success( actService.save(act));
    }


    @Override
    @ApiOperation(value = "删除Act", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ActID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        actService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Act", notes = "修改指定Act信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Act的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="actForm",value="修改Act的form表单",required=true)  ActForm actForm) {
        Act act =new BeanUtils<Act>().copyObj(actForm,Act.class);
        act.setId(id);
        actService.saveOrUpdate(act);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Act", notes = "获取指定ID的Act信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Act的ID", required = true, dataType = "long")
    public Result<ActVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Act act=actService.getById(id);
        ActVo actVo=new BeanUtils<ActVo>().copyObj(act,ActVo.class);
        return new Result<ActVo>().sucess(actVo);
    }

    @Override
    @ApiOperation(value = "列表查询Act", notes = "根据条件查询Act列表数据")
    public Result<List<ActVo>> list(@Valid @RequestBody @ApiParam(name="actQueryForm",value="Act查询参数",required=true) ActQueryForm actQueryForm) {
        log.info("search with actQueryForm:", actQueryForm.toString());
        List<Act> actList=actService.selectList(actQueryForm);
        List<ActVo> actVoList=new BeanUtils<ActVo>().copyObjs(actList,ActVo.class);
        return new Result<List<ActVo>>().sucess(actVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Act", notes = "根据条件查询Act分页数据")
    public Result<IPage<ActVo>> search(@Valid @RequestBody @ApiParam(name="actQueryForm",value="Act查询参数",required=true) ActQueryForm actQueryForm) {
        log.info("search with actQueryForm:", actQueryForm.toString());
        IPage<Act> actPage=actService.selectPage(actQueryForm);
        IPage<ActVo> actVoPage=new BeanUtils<ActVo>().copyPageObjs(actPage,ActVo.class);
        return new Result<IPage<ActVo>>().sucess(actVoPage);
    }

}



