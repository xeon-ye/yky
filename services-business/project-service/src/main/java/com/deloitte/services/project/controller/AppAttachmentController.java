package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.AppAttachmentQueryForm;
import com.deloitte.platform.api.project.vo.AppAttachmentForm;
import com.deloitte.platform.api.project.vo.AppAttachmentVo;
import com.deloitte.platform.api.project.client.AppAttachmentClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IAppAttachmentService;
import com.deloitte.services.project.entity.AppAttachment;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   AppAttachment控制器实现类
 * @Modified :
 */
@Api(tags = "AppAttachment操作接口")
@Slf4j
@RestController
public class AppAttachmentController implements AppAttachmentClient {

    @Autowired
    public IAppAttachmentService  appAttachmentService;


    @Override
    @ApiOperation(value = "新增AppAttachment", notes = "新增一个AppAttachment")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="appAttachmentForm",value="新增AppAttachment的form表单",required=true)  AppAttachmentForm appAttachmentForm) {
        log.info("form:",  appAttachmentForm.toString());
        AppAttachment appAttachment =new BeanUtils<AppAttachment>().copyObj(appAttachmentForm,AppAttachment.class);
        return Result.success( appAttachmentService.save(appAttachment));
    }


    @Override
    @ApiOperation(value = "删除AppAttachment", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AppAttachmentID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        appAttachmentService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改AppAttachment", notes = "修改指定AppAttachment信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AppAttachment的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="appAttachmentForm",value="修改AppAttachment的form表单",required=true)  AppAttachmentForm appAttachmentForm) {
        AppAttachment appAttachment =new BeanUtils<AppAttachment>().copyObj(appAttachmentForm,AppAttachment.class);
        appAttachment.setId(id);
        appAttachmentService.saveOrUpdate(appAttachment);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取AppAttachment", notes = "获取指定ID的AppAttachment信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AppAttachment的ID", required = true, dataType = "long")
    public Result<AppAttachmentVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AppAttachment appAttachment=appAttachmentService.getById(id);
        AppAttachmentVo appAttachmentVo=new BeanUtils<AppAttachmentVo>().copyObj(appAttachment,AppAttachmentVo.class);
        return new Result<AppAttachmentVo>().sucess(appAttachmentVo);
    }

    @Override
    @ApiOperation(value = "列表查询AppAttachment", notes = "根据条件查询AppAttachment列表数据")
    public Result<List<AppAttachmentVo>> list(@Valid @RequestBody @ApiParam(name="appAttachmentQueryForm",value="AppAttachment查询参数",required=true) AppAttachmentQueryForm appAttachmentQueryForm) {
        log.info("search with appAttachmentQueryForm:", appAttachmentQueryForm.toString());
        List<AppAttachment> appAttachmentList=appAttachmentService.selectList(appAttachmentQueryForm);
        List<AppAttachmentVo> appAttachmentVoList=new BeanUtils<AppAttachmentVo>().copyObjs(appAttachmentList,AppAttachmentVo.class);
        return new Result<List<AppAttachmentVo>>().sucess(appAttachmentVoList);
    }


    @Override
    @ApiOperation(value = "分页查询AppAttachment", notes = "根据条件查询AppAttachment分页数据")
    public Result<IPage<AppAttachmentVo>> search(@Valid @RequestBody @ApiParam(name="appAttachmentQueryForm",value="AppAttachment查询参数",required=true) AppAttachmentQueryForm appAttachmentQueryForm) {
        log.info("search with appAttachmentQueryForm:", appAttachmentQueryForm.toString());
        IPage<AppAttachment> appAttachmentPage=appAttachmentService.selectPage(appAttachmentQueryForm);
        IPage<AppAttachmentVo> appAttachmentVoPage=new BeanUtils<AppAttachmentVo>().copyPageObjs(appAttachmentPage,AppAttachmentVo.class);
        return new Result<IPage<AppAttachmentVo>>().sucess(appAttachmentVoPage);
    }

}



