package com.deloitte.services.meeting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingHeaderClient;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingHeaderQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.meeting.entity.OaMeetingHeader;
import com.deloitte.services.meeting.service.IOaMeetingHeaderService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingHeader控制器实现类
 * @Modified :
 */
@Api(tags = "OaMeetingHeader操作接口")
@Slf4j
@RestController
public class OaMeetingHeaderController implements OaMeetingHeaderClient {

    @Autowired
    public IOaMeetingHeaderService  oaMeetingHeaderService;

    @Override
    @ApiOperation(value = "新增OaMeetingHeader", notes = "新增一个OaMeetingHeader")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMeetingHeaderForm",value="新增OaMeetingHeader的form表单",required=true)  OaMeetingForm oaMeetingForm) {
        log.info("form:",  oaMeetingForm.toString());
        return Result.success(oaMeetingHeaderService.saveMeeting(oaMeetingForm));
    }


    @Override
    @ApiOperation(value = "删除OaMeetingHeader", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingHeaderID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMeetingHeaderService.removeById(id);
        //更加meetingId删除
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMeetingHeader", notes = "修改指定OaMeetingHeader信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMeetingHeader的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMeetingForm",value="修改OaMeetingHeader的form表单",required=true) OaMeetingForm oaMeetingForm) {
        oaMeetingHeaderService.updateMeeting(id,oaMeetingForm);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaMeetingHeader", notes = "获取指定ID的OaMeetingHeader信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingHeader的ID", required = true, dataType = "long")
    public Result<OaMeetingVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        return new Result<OaMeetingVo>().sucess(oaMeetingHeaderService.getOaMeeting(id));
    }

    @Override
    @ApiOperation(value = "列表查询OaMeetingHeader", notes = "根据条件查询OaMeetingHeader列表数据")
    public Result<List<OaMeetingVo>> list(@Valid @RequestBody @ApiParam(name="oaMeetingHeaderQueryForm",value="OaMeetingHeader查询参数",required=true) OaMeetingHeaderQueryForm oaMeetingHeaderQueryForm) {
        log.info("search with oaMeetingHeaderQueryForm:", oaMeetingHeaderQueryForm.toString());
        List<OaMeetingHeader> oaMeetingHeaderList=oaMeetingHeaderService.selectList(oaMeetingHeaderQueryForm);
        List<OaMeetingVo> oaMeetingHeaderVoList=new BeanUtils<OaMeetingVo>().copyObjs(oaMeetingHeaderList,OaMeetingVo.class);
        return new Result<List<OaMeetingVo>>().sucess(oaMeetingHeaderVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMeetingHeader", notes = "根据条件查询OaMeetingHeader分页数据")
    public Result<IPage<OaMeetingVo>> search(@Valid @RequestBody @ApiParam(name="oaMeetingHeaderQueryForm",value="OaMeetingHeader查询参数",required=true) OaMeetingHeaderQueryForm oaMeetingHeaderQueryForm) {
        log.info("search with oaMeetingHeaderQueryForm:", oaMeetingHeaderQueryForm.toString());
        IPage<OaMeetingHeader> oaMeetingHeaderPage=oaMeetingHeaderService.selectPage(oaMeetingHeaderQueryForm);
        IPage<OaMeetingVo> oaMeetingHeaderVoPage=new BeanUtils<OaMeetingVo>().copyPageObjs(oaMeetingHeaderPage,OaMeetingVo.class);
        return new Result<IPage<OaMeetingVo>>().sucess(oaMeetingHeaderVoPage);
    }

}



