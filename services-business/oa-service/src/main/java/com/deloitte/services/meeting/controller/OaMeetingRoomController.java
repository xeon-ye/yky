package com.deloitte.services.meeting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingRoomQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingRoomForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingRoomVo;
import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingRoomClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.meeting.service.IOaMeetingRoomService;
import com.deloitte.services.meeting.entity.OaMeetingRoom;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingRoom控制器实现类
 * @Modified :
 */
@Api(tags = "OaMeetingRoom操作接口")
@Slf4j
@RestController
public class OaMeetingRoomController implements OaMeetingRoomClient {

    @Autowired
    public IOaMeetingRoomService  oaMeetingRoomService;


    @Override
    @ApiOperation(value = "新增OaMeetingRoom", notes = "新增一个OaMeetingRoom")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMeetingRoomForm",value="新增OaMeetingRoom的form表单",required=true)  OaMeetingRoomForm oaMeetingRoomForm) {
        log.info("form:",  oaMeetingRoomForm.toString());
        OaMeetingRoom oaMeetingRoom =new BeanUtils<OaMeetingRoom>().copyObj(oaMeetingRoomForm,OaMeetingRoom.class);
        return Result.success( oaMeetingRoomService.save(oaMeetingRoom));
    }


    @Override
    @ApiOperation(value = "删除OaMeetingRoom", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingRoomID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMeetingRoomService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMeetingRoom", notes = "修改指定OaMeetingRoom信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMeetingRoom的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMeetingRoomForm",value="修改OaMeetingRoom的form表单",required=true)  OaMeetingRoomForm oaMeetingRoomForm) {
        OaMeetingRoom oaMeetingRoom =new BeanUtils<OaMeetingRoom>().copyObj(oaMeetingRoomForm,OaMeetingRoom.class);
        oaMeetingRoom.setId(id);
        oaMeetingRoomService.saveOrUpdate(oaMeetingRoom);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaMeetingRoom", notes = "获取指定ID的OaMeetingRoom信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingRoom的ID", required = true, dataType = "long")
    public Result<OaMeetingRoomVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaMeetingRoom oaMeetingRoom=oaMeetingRoomService.getById(id);
        OaMeetingRoomVo oaMeetingRoomVo=new BeanUtils<OaMeetingRoomVo>().copyObj(oaMeetingRoom,OaMeetingRoomVo.class);
        return new Result<OaMeetingRoomVo>().sucess(oaMeetingRoomVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaMeetingRoom", notes = "根据条件查询OaMeetingRoom列表数据")
    public Result<List<OaMeetingRoomVo>> list(@Valid @RequestBody @ApiParam(name="oaMeetingRoomQueryForm",value="OaMeetingRoom查询参数",required=true) OaMeetingRoomQueryForm oaMeetingRoomQueryForm) {
        log.info("search with oaMeetingRoomQueryForm:", oaMeetingRoomQueryForm.toString());
        List<OaMeetingRoom> oaMeetingRoomList=oaMeetingRoomService.selectList(oaMeetingRoomQueryForm);
        List<OaMeetingRoomVo> oaMeetingRoomVoList=new BeanUtils<OaMeetingRoomVo>().copyObjs(oaMeetingRoomList,OaMeetingRoomVo.class);
        return new Result<List<OaMeetingRoomVo>>().sucess(oaMeetingRoomVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMeetingRoom", notes = "根据条件查询OaMeetingRoom分页数据")
    public Result<IPage<OaMeetingRoomVo>> search(@Valid @RequestBody @ApiParam(name="oaMeetingRoomQueryForm",value="OaMeetingRoom查询参数",required=true) OaMeetingRoomQueryForm oaMeetingRoomQueryForm) {
        log.info("search with oaMeetingRoomQueryForm:", oaMeetingRoomQueryForm.toString());
        IPage<OaMeetingRoom> oaMeetingRoomPage=oaMeetingRoomService.selectPage(oaMeetingRoomQueryForm);
        IPage<OaMeetingRoomVo> oaMeetingRoomVoPage=new BeanUtils<OaMeetingRoomVo>().copyPageObjs(oaMeetingRoomPage,OaMeetingRoomVo.class);
        return new Result<IPage<OaMeetingRoomVo>>().sucess(oaMeetingRoomVoPage);
    }

}



