package com.deloitte.platform.basic.bpmservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.param.BpmPositionQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionVo;
import com.deloitte.platform.api.bpmservice.clinet.BpmPositionClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.platform.basic.bpmservice.service.IBpmPositionService;
import com.deloitte.platform.basic.bpmservice.entity.BpmPosition;


/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :   BpmPosition控制器实现类
 * @Modified :
 */
@Api("BpmPosition")
@Slf4j
@RestController
public class BpmPositionController implements BpmPositionClient {

    @Autowired
    public IBpmPositionService  bpmPositionService;


    @Override
    @ApiOperation(value = "新增BpmPosition", notes = "新增一个BpmPosition")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="bpmPositionForm",value="新增BpmPosition的form表单",required=true)  BpmPositionForm bpmPositionForm) {
        log.info("form:",  bpmPositionForm.toString());
        BpmPosition bpmPosition =new BeanUtils<BpmPosition>().copyObj(bpmPositionForm,BpmPosition.class);
        return Result.success( bpmPositionService.save(bpmPosition));
    }


    @Override
    @ApiOperation(value = "删除BpmPosition", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmPositionID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        bpmPositionService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BpmPosition", notes = "修改指定BpmPosition信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BpmPosition的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="bpmPositionForm",value="修改BpmPosition的form表单",required=true)  BpmPositionForm bpmPositionForm) {
        BpmPosition bpmPosition =new BeanUtils<BpmPosition>().copyObj(bpmPositionForm,BpmPosition.class);
        bpmPosition.setId(id);
        bpmPositionService.saveOrUpdate(bpmPosition);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取BpmPosition", notes = "获取指定ID的BpmPosition信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmPosition的ID", required = true, dataType = "long")
    public Result<BpmPositionVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BpmPosition bpmPosition=bpmPositionService.getById(id);
        BpmPositionVo bpmPositionVo=new BeanUtils<BpmPositionVo>().copyObj(bpmPosition,BpmPositionVo.class);
        return new Result<BpmPositionVo>().sucess(bpmPositionVo);
    }

    @Override
    @ApiOperation(value = "列表查询BpmPosition", notes = "根据条件查询BpmPosition列表数据")
    public Result<List<BpmPositionVo>> list(@Valid @RequestBody @ApiParam(name="bpmPositionQueryForm",value="BpmPosition查询参数",required=true) BpmPositionQueryForm bpmPositionQueryForm) {
        log.info("search with bpmPositionQueryForm:", bpmPositionQueryForm.toString());
        List<BpmPosition> bpmPositionList=bpmPositionService.selectList(bpmPositionQueryForm);
        List<BpmPositionVo> bpmPositionVoList=new BeanUtils<BpmPositionVo>().copyObjs(bpmPositionList,BpmPositionVo.class);
        return new Result<List<BpmPositionVo>>().sucess(bpmPositionVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BpmPosition", notes = "根据条件查询BpmPosition分页数据")
    public Result<IPage<BpmPositionVo>> search(@Valid @RequestBody @ApiParam(name="bpmPositionQueryForm",value="BpmPosition查询参数",required=true) BpmPositionQueryForm bpmPositionQueryForm) {
        log.info("search with bpmPositionQueryForm:", bpmPositionQueryForm.toString());
        IPage<BpmPosition> bpmPositionPage=bpmPositionService.selectPage(bpmPositionQueryForm);
        IPage<BpmPositionVo> bpmPositionVoPage=new BeanUtils<BpmPositionVo>().copyPageObjs(bpmPositionPage,BpmPositionVo.class);
        return new Result<IPage<BpmPositionVo>>().sucess(bpmPositionVoPage);
    }

}



