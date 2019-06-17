package com.deloitte.services.attachment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.client.OaAttachmentClient;
import com.deloitte.platform.api.oaservice.attachment.param.OaAttachmentQueryForm;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentForm;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.entity.OaAttachmentOutVo;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description :   OaAttachment控制器实现类
 * @Modified :
 */
@Api(tags = "OaAttachment操作接口")
@Slf4j
@RestController
public class OaAttachmentController implements OaAttachmentClient {

    @Autowired
    public IOaAttachmentService oaAttachmentService;


    @Override
    @ApiOperation(value = "新增OaAttachment", notes = "新增一个OaAttachment")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaAttachmentForm",value="新增OaAttachment的form表单",required=true) OaAttachmentForm oaAttachmentForm) {
        log.info("form:",  oaAttachmentForm.toString());
        OaAttachment oaAttachment =new BeanUtils<OaAttachment>().copyObj(oaAttachmentForm,OaAttachment.class);
        return Result.success( oaAttachmentService.save(oaAttachment));
    }


    @Override
    @ApiOperation(value = "删除OaAttachment", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaAttachmentID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaAttachmentService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaAttachment", notes = "修改指定OaAttachment信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaAttachment的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaAttachmentForm",value="修改OaAttachment的form表单",required=true)  OaAttachmentForm oaAttachmentForm) {
        OaAttachment oaAttachment =new BeanUtils<OaAttachment>().copyObj(oaAttachmentForm,OaAttachment.class);
        oaAttachment.setId(id);
        oaAttachmentService.saveOrUpdate(oaAttachment);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaAttachment", notes = "获取指定ID的OaAttachment信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaAttachment的ID", required = true, dataType = "long")
    public Result<OaAttachmentVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaAttachment oaAttachment=oaAttachmentService.getById(id);
        OaAttachmentVo oaAttachmentVo=new BeanUtils<OaAttachmentVo>().copyObj(oaAttachment,OaAttachmentVo.class);
        return new Result<OaAttachmentVo>().sucess(oaAttachmentVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaAttachment", notes = "根据条件查询OaAttachment列表数据")
    public Result<List<OaAttachmentVo>> list(@Valid @RequestBody @ApiParam(name="oaAttachmentQueryForm",value="OaAttachment查询参数",required=true) OaAttachmentQueryForm oaAttachmentQueryForm) {
        log.info("search with oaAttachmentQueryForm:", oaAttachmentQueryForm.toString());
        List<OaAttachment> oaAttachmentList=oaAttachmentService.selectList(oaAttachmentQueryForm);
        List<OaAttachmentVo> oaAttachmentVoList=new BeanUtils<OaAttachmentVo>().copyObjs(oaAttachmentList,OaAttachmentVo.class);
        return new Result<List<OaAttachmentVo>>().sucess(oaAttachmentVoList);
    }

    @PostMapping(value = path+"/list/conditions/out")
    @ApiOperation(value = "列表查询OaAttachment", notes = "根据条件查询OaAttachment列表数据")
    public Result<List<OaAttachmentOutVo>> listOut(@Valid @RequestBody @ApiParam(name="oaAttachmentQueryForm",value="OaAttachment查询参数",required=true) OaAttachmentQueryForm oaAttachmentQueryForm) {
        log.info("search with oaAttachmentQueryForm:", oaAttachmentQueryForm.toString());
        List<OaAttachment> oaAttachmentList=oaAttachmentService.selectList(oaAttachmentQueryForm);
        List<OaAttachmentOutVo> oaAttachmentVoList=new BeanUtils<OaAttachmentOutVo>().copyObjs(oaAttachmentList,OaAttachmentOutVo.class);
        oaAttachmentVoList.forEach(atta -> {
            atta.setUrl(atta.getAttachUrl());
            atta.setTitle(atta.getAttachName());
        });
        return new Result<List<OaAttachmentOutVo>>().sucess(oaAttachmentVoList);
    }

    @GetMapping(value = path+"/list/home")
    @ApiOperation(value = "首页查询OaAttachment", notes = "根据条件查询OaAttachment首页列表数据")
    public Result<List<OaAttachmentOutVo>> listOut(@RequestParam(value = "num", defaultValue = "3", required = false)int num,
                                                   @RequestParam(value = "busicessName", required = false)String busicessName) {
        log.info("search home list");
        List<OaAttachment> oaAttachmentList=oaAttachmentService.getHomeList(num, busicessName);
        List<OaAttachmentOutVo> oaAttachmentVoList=new BeanUtils<OaAttachmentOutVo>().copyObjs(oaAttachmentList,OaAttachmentOutVo.class);
        oaAttachmentVoList.forEach(atta -> {
            atta.setUrl(atta.getAttachUrl());
            atta.setTitle(atta.getAttachName());
        });
        return new Result<List<OaAttachmentOutVo>>().sucess(oaAttachmentVoList);
    }

    @Override
    @ApiOperation(value = "分页查询OaAttachment", notes = "根据条件查询OaAttachment分页数据")
    public Result<IPage<OaAttachmentVo>> search(@Valid @RequestBody @ApiParam(name="oaAttachmentQueryForm",value="OaAttachment查询参数",required=true) OaAttachmentQueryForm oaAttachmentQueryForm) {
        log.info("search with oaAttachmentQueryForm:", oaAttachmentQueryForm.toString());
        IPage<OaAttachment> oaAttachmentPage=oaAttachmentService.selectPage(oaAttachmentQueryForm);
        IPage<OaAttachmentVo> oaAttachmentVoPage=new BeanUtils<OaAttachmentVo>().copyPageObjs(oaAttachmentPage,OaAttachmentVo.class);
        return new Result<IPage<OaAttachmentVo>>().sucess(oaAttachmentVoPage);
    }

    @PostMapping(value = path+"/page/conditions/out")
    @ApiOperation(value = "列表查询OaAttachment", notes = "根据条件查询OaAttachment列表数据")
    public Result<IPage<OaAttachmentOutVo>> searchOut(@Valid @RequestBody @ApiParam(name="oaAttachmentQueryForm",value="OaAttachment查询参数",required=true) OaAttachmentQueryForm oaAttachmentQueryForm) {
        log.info("search with oaAttachmentQueryForm:", oaAttachmentQueryForm.toString());
        IPage<OaAttachment> oaAttachmentPage=oaAttachmentService.selectPage(oaAttachmentQueryForm);
        IPage<OaAttachmentOutVo> oaAttachmentVoPage=new BeanUtils<OaAttachmentOutVo>().copyPageObjs(oaAttachmentPage,OaAttachmentOutVo.class);
        oaAttachmentVoPage.getRecords().forEach(atta -> {
            atta.setUrl(atta.getAttachUrl());
            atta.setTitle(atta.getAttachName());
        });

        return new Result<IPage<OaAttachmentOutVo>>().sucess(oaAttachmentVoPage);
    }

}



