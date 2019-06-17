package com.deloitte.platform.basic.bpmservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.param.BpmPositionRelationshipQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionRelationshipForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionRelationshipVo;
import com.deloitte.platform.api.bpmservice.clinet.BpmPositionRelationshipClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.platform.basic.bpmservice.service.IBpmPositionRelationshipService;
import com.deloitte.platform.basic.bpmservice.entity.BpmPositionRelationship;


/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :   BpmPositionRelationship控制器实现类
 * @Modified :
 */
@Api("BpmPositionRelationship")
@Slf4j
@RestController
public class BpmPositionRelationshipController implements BpmPositionRelationshipClient {

    @Autowired
    public IBpmPositionRelationshipService  bpmPositionRelationshipService;


    @Override
    @ApiOperation(value = "新增BpmPositionRelationship", notes = "新增一个BpmPositionRelationship")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="bpmPositionRelationshipForm",value="新增BpmPositionRelationship的form表单",required=true)  BpmPositionRelationshipForm bpmPositionRelationshipForm) {
        log.info("form:",  bpmPositionRelationshipForm.toString());
        BpmPositionRelationship bpmPositionRelationship =new BeanUtils<BpmPositionRelationship>().copyObj(bpmPositionRelationshipForm,BpmPositionRelationship.class);
        return Result.success( bpmPositionRelationshipService.save(bpmPositionRelationship));
    }


    @Override
    @ApiOperation(value = "删除BpmPositionRelationship", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmPositionRelationshipID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        bpmPositionRelationshipService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BpmPositionRelationship", notes = "修改指定BpmPositionRelationship信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BpmPositionRelationship的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="bpmPositionRelationshipForm",value="修改BpmPositionRelationship的form表单",required=true)  BpmPositionRelationshipForm bpmPositionRelationshipForm) {
        BpmPositionRelationship bpmPositionRelationship =new BeanUtils<BpmPositionRelationship>().copyObj(bpmPositionRelationshipForm,BpmPositionRelationship.class);
        bpmPositionRelationship.setId(id);
        bpmPositionRelationshipService.saveOrUpdate(bpmPositionRelationship);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取BpmPositionRelationship", notes = "获取指定ID的BpmPositionRelationship信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmPositionRelationship的ID", required = true, dataType = "long")
    public Result<BpmPositionRelationshipVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BpmPositionRelationship bpmPositionRelationship=bpmPositionRelationshipService.getById(id);
        BpmPositionRelationshipVo bpmPositionRelationshipVo=new BeanUtils<BpmPositionRelationshipVo>().copyObj(bpmPositionRelationship,BpmPositionRelationshipVo.class);
        return new Result<BpmPositionRelationshipVo>().sucess(bpmPositionRelationshipVo);
    }

    @Override
    @ApiOperation(value = "列表查询BpmPositionRelationship", notes = "根据条件查询BpmPositionRelationship列表数据")
    public Result<List<BpmPositionRelationshipVo>> list(@Valid @RequestBody @ApiParam(name="bpmPositionRelationshipQueryForm",value="BpmPositionRelationship查询参数",required=true) BpmPositionRelationshipQueryForm bpmPositionRelationshipQueryForm) {
        log.info("search with bpmPositionRelationshipQueryForm:", bpmPositionRelationshipQueryForm.toString());
        List<BpmPositionRelationship> bpmPositionRelationshipList=bpmPositionRelationshipService.selectList(bpmPositionRelationshipQueryForm);
        List<BpmPositionRelationshipVo> bpmPositionRelationshipVoList=new BeanUtils<BpmPositionRelationshipVo>().copyObjs(bpmPositionRelationshipList,BpmPositionRelationshipVo.class);
        return new Result<List<BpmPositionRelationshipVo>>().sucess(bpmPositionRelationshipVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BpmPositionRelationship", notes = "根据条件查询BpmPositionRelationship分页数据")
    public Result<IPage<BpmPositionRelationshipVo>> search(@Valid @RequestBody @ApiParam(name="bpmPositionRelationshipQueryForm",value="BpmPositionRelationship查询参数",required=true) BpmPositionRelationshipQueryForm bpmPositionRelationshipQueryForm) {
        log.info("search with bpmPositionRelationshipQueryForm:", bpmPositionRelationshipQueryForm.toString());
        IPage<BpmPositionRelationship> bpmPositionRelationshipPage=bpmPositionRelationshipService.selectPage(bpmPositionRelationshipQueryForm);
        IPage<BpmPositionRelationshipVo> bpmPositionRelationshipVoPage=new BeanUtils<BpmPositionRelationshipVo>().copyPageObjs(bpmPositionRelationshipPage,BpmPositionRelationshipVo.class);
        return new Result<IPage<BpmPositionRelationshipVo>>().sucess(bpmPositionRelationshipVoPage);
    }

}



