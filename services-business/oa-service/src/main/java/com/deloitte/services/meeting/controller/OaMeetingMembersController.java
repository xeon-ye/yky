package com.deloitte.services.meeting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingMembersQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingMembersForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingMembersVo;
import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingMembersClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.meeting.service.IOaMeetingMembersService;
import com.deloitte.services.meeting.entity.OaMeetingMembers;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingMembers控制器实现类
 * @Modified :
 */
@Api(tags = "OaMeetingMembers操作接口")
@Slf4j
@RestController
public class OaMeetingMembersController implements OaMeetingMembersClient {

    @Autowired
    public IOaMeetingMembersService  oaMeetingMembersService;


    @Override
    @ApiOperation(value = "新增OaMeetingMembers", notes = "新增一个OaMeetingMembers")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMeetingMembersForm",value="新增OaMeetingMembers的form表单",required=true)  OaMeetingMembersForm oaMeetingMembersForm) {
        log.info("form:",  oaMeetingMembersForm.toString());
        OaMeetingMembers oaMeetingMembers =new BeanUtils<OaMeetingMembers>().copyObj(oaMeetingMembersForm,OaMeetingMembers.class);
        return Result.success( oaMeetingMembersService.save(oaMeetingMembers));
    }


    @Override
    @ApiOperation(value = "删除OaMeetingMembers", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingMembersID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMeetingMembersService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMeetingMembers", notes = "修改指定OaMeetingMembers信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMeetingMembers的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMeetingMembersForm",value="修改OaMeetingMembers的form表单",required=true)  OaMeetingMembersForm oaMeetingMembersForm) {
        OaMeetingMembers oaMeetingMembers =new BeanUtils<OaMeetingMembers>().copyObj(oaMeetingMembersForm,OaMeetingMembers.class);
        oaMeetingMembers.setId(id);
        oaMeetingMembersService.saveOrUpdate(oaMeetingMembers);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaMeetingMembers", notes = "获取指定ID的OaMeetingMembers信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingMembers的ID", required = true, dataType = "long")
    public Result<OaMeetingMembersVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaMeetingMembers oaMeetingMembers=oaMeetingMembersService.getById(id);
        OaMeetingMembersVo oaMeetingMembersVo=new BeanUtils<OaMeetingMembersVo>().copyObj(oaMeetingMembers,OaMeetingMembersVo.class);
        return new Result<OaMeetingMembersVo>().sucess(oaMeetingMembersVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaMeetingMembers", notes = "根据条件查询OaMeetingMembers列表数据")
    public Result<List<OaMeetingMembersVo>> list(@Valid @RequestBody @ApiParam(name="oaMeetingMembersQueryForm",value="OaMeetingMembers查询参数",required=true) OaMeetingMembersQueryForm oaMeetingMembersQueryForm) {
        log.info("search with oaMeetingMembersQueryForm:", oaMeetingMembersQueryForm.toString());
        List<OaMeetingMembers> oaMeetingMembersList=oaMeetingMembersService.selectList(oaMeetingMembersQueryForm);
        List<OaMeetingMembersVo> oaMeetingMembersVoList=new BeanUtils<OaMeetingMembersVo>().copyObjs(oaMeetingMembersList,OaMeetingMembersVo.class);
        return new Result<List<OaMeetingMembersVo>>().sucess(oaMeetingMembersVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMeetingMembers", notes = "根据条件查询OaMeetingMembers分页数据")
    public Result<IPage<OaMeetingMembersVo>> search(@Valid @RequestBody @ApiParam(name="oaMeetingMembersQueryForm",value="OaMeetingMembers查询参数",required=true) OaMeetingMembersQueryForm oaMeetingMembersQueryForm) {
        log.info("search with oaMeetingMembersQueryForm:", oaMeetingMembersQueryForm.toString());
        IPage<OaMeetingMembers> oaMeetingMembersPage=oaMeetingMembersService.selectPage(oaMeetingMembersQueryForm);
        IPage<OaMeetingMembersVo> oaMeetingMembersVoPage=new BeanUtils<OaMeetingMembersVo>().copyPageObjs(oaMeetingMembersPage,OaMeetingMembersVo.class);
        return new Result<IPage<OaMeetingMembersVo>>().sucess(oaMeetingMembersVoPage);
    }

}



