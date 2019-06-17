package com.deloitte.services.fssc.business.pe.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.pe.param.PerSelfTargetQueryForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfTargetForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfTargetVo;
import com.deloitte.platform.api.fssc.pe.client.PerSelfTargetClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.business.pe.service.IPerSelfTargetService;
import com.deloitte.services.fssc.business.pe.entity.PerSelfTarget;


/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :   PerSelfTarget控制器实现类
 * @Modified :
 */
@Api(tags = "PerSelfTarget操作接口")
@Slf4j
@RestController
public class PerSelfTargetController implements PerSelfTargetClient {

    @Autowired
    private IPerSelfTargetService  perSelfTargetService;


    @Override
    @ApiOperation(value = "新增PerSelfTarget", notes = "新增一个PerSelfTarget")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="perSelfTargetForm",value="新增PerSelfTarget的form表单",required=true)  PerSelfTargetForm perSelfTargetForm) {
        log.info("form:",  perSelfTargetForm.toString());
        PerSelfTarget perSelfTarget = new BeanUtils<PerSelfTarget>().copyObj(perSelfTargetForm,PerSelfTarget.class);
        return Result.success( perSelfTargetService.save(perSelfTarget));
    }


    @Override
    @ApiOperation(value = "删除PerSelfTarget", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PerSelfTargetID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        perSelfTargetService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改PerSelfTarget", notes = "修改指定PerSelfTarget信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "PerSelfTarget的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="perSelfTargetForm",value="修改PerSelfTarget的form表单",required=true)  PerSelfTargetForm perSelfTargetForm) {
        PerSelfTarget perSelfTarget =new BeanUtils<PerSelfTarget>().copyObj(perSelfTargetForm,PerSelfTarget.class);
        perSelfTarget.setId(id);
        perSelfTargetService.saveOrUpdate(perSelfTarget);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取PerSelfTarget", notes = "获取指定ID的PerSelfTarget信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PerSelfTarget的ID", required = true, dataType = "long")
    public Result<PerSelfTargetVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PerSelfTarget perSelfTarget = perSelfTargetService.getById(id);
        PerSelfTargetVo perSelfTargetVo=new BeanUtils<PerSelfTargetVo>().copyObj(perSelfTarget,PerSelfTargetVo.class);
        return new Result<PerSelfTargetVo>().sucess(perSelfTargetVo);
    }

    @Override
    @ApiOperation(value = "列表查询PerSelfTarget", notes = "根据条件查询PerSelfTarget列表数据")
    public Result<List<PerSelfTargetVo>> list(@Valid @RequestBody @ApiParam(name="perSelfTargetQueryForm",value="PerSelfTarget查询参数",required=true) PerSelfTargetQueryForm perSelfTargetQueryForm) {
        log.info("search with perSelfTargetQueryForm:", perSelfTargetQueryForm.toString());
        List<PerSelfTarget> perSelfTargetList=perSelfTargetService.selectList(perSelfTargetQueryForm);
        List<PerSelfTargetVo> perSelfTargetVoList=new BeanUtils<PerSelfTargetVo>().copyObjs(perSelfTargetList,PerSelfTargetVo.class);
        return new Result<List<PerSelfTargetVo>>().sucess(perSelfTargetVoList);
    }


    @Override
    @ApiOperation(value = "分页查询PerSelfTarget", notes = "根据条件查询PerSelfTarget分页数据")
    public Result<IPage<PerSelfTargetVo>> search(@Valid @RequestBody @ApiParam(name="perSelfTargetQueryForm",value="PerSelfTarget查询参数",required=true) PerSelfTargetQueryForm perSelfTargetQueryForm) {
        log.info("search with perSelfTargetQueryForm:", perSelfTargetQueryForm.toString());
        IPage<PerSelfTarget> perSelfTargetPage=perSelfTargetService.selectPage(perSelfTargetQueryForm);
        IPage<PerSelfTargetVo> perSelfTargetVoPage=new BeanUtils<PerSelfTargetVo>().copyPageObjs(perSelfTargetPage,PerSelfTargetVo.class);
        return new Result<IPage<PerSelfTargetVo>>().sucess(perSelfTargetVoPage);
    }

}



