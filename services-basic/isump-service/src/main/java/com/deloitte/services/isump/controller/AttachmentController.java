package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.AttachmentClient;
import com.deloitte.platform.api.isump.param.AttachmentQueryForm;
import com.deloitte.platform.api.isump.vo.AttachmentForm;
import com.deloitte.platform.api.isump.vo.AttachmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IAttachmentService;
import com.deloitte.services.isump.entity.Attachment;


/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description :   Attachment控制器实现类
 * @Modified :
 */
@Api(tags = "Attachment操作接口")
@Slf4j
@RestController
public class AttachmentController implements AttachmentClient {

    @Autowired
    public IAttachmentService  attachmentService;


    @Override
    @ApiOperation(value = "新增Attachment", notes = "新增一个Attachment")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="attachmentForm",value="新增Attachment的form表单",required=true)  AttachmentForm attachmentForm) {
        log.info("form:",  attachmentForm.toString());
        Attachment attachment =new BeanUtils<Attachment>().copyObj(attachmentForm,Attachment.class);
        return Result.success( attachmentService.save(attachment));
    }

    @Override
    @ApiOperation(value = "批量新增Attachment", notes = "新增多个Attachment")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result batchAdd(@Valid @RequestBody @ApiParam(name="attachmentForms",value="新增Attachment的form表单",required=true)  List<AttachmentForm> attachmentForms) {
        log.info("form:",  attachmentForms.toString());
        List<Attachment> attachments =new BeanUtils<Attachment>().copyObjs(attachmentForms,Attachment.class);
        return Result.success( attachmentService.saveBatch(attachments));
    }


    @Override
    @ApiOperation(value = "删除Attachment", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AttachmentID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        attachmentService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Attachment", notes = "修改指定Attachment信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Attachment的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="attachmentForm",value="修改Attachment的form表单",required=true)  AttachmentForm attachmentForm) {
        Attachment attachment =new BeanUtils<Attachment>().copyObj(attachmentForm,Attachment.class);
        attachment.setId(id);
        attachmentService.saveOrUpdate(attachment);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Attachment", notes = "获取指定ID的Attachment信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Attachment的ID", required = true, dataType = "long")
    public Result<AttachmentVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Attachment attachment=attachmentService.getById(id);
        AttachmentVo attachmentVo=new BeanUtils<AttachmentVo>().copyObj(attachment,AttachmentVo.class);
        return new Result<AttachmentVo>().sucess(attachmentVo);
    }

    @Override
    @ApiOperation(value = "列表查询Attachment", notes = "根据条件查询Attachment列表数据")
    public Result<List<AttachmentVo>> list(@Valid @RequestBody @ApiParam(name="attachmentQueryForm",value="Attachment查询参数",required=true) AttachmentQueryForm attachmentQueryForm) {
        log.info("search with attachmentQueryForm:", attachmentQueryForm.toString());
        List<Attachment> attachmentList=attachmentService.selectList(attachmentQueryForm);
        List<AttachmentVo> attachmentVoList=new BeanUtils<AttachmentVo>().copyObjs(attachmentList,AttachmentVo.class);
        return new Result<List<AttachmentVo>>().sucess(attachmentVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Attachment", notes = "根据条件查询Attachment分页数据")
    public Result<IPage<AttachmentVo>> search(@Valid @RequestBody @ApiParam(name="attachmentQueryForm",value="Attachment查询参数",required=true) AttachmentQueryForm attachmentQueryForm) {
        log.info("search with attachmentQueryForm:", attachmentQueryForm.toString());
        IPage<Attachment> attachmentPage=attachmentService.selectPage(attachmentQueryForm);
        IPage<AttachmentVo> attachmentVoPage=new BeanUtils<AttachmentVo>().copyPageObjs(attachmentPage,AttachmentVo.class);
        return new Result<IPage<AttachmentVo>>().sucess(attachmentVoPage);
    }

}



