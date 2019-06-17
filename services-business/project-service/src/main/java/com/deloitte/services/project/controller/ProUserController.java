package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ProUserQueryForm;
import com.deloitte.platform.api.project.vo.ProUserForm;
import com.deloitte.platform.api.project.vo.ProUserVo;
import com.deloitte.platform.api.project.client.ProUserClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IProUserService;
import com.deloitte.services.project.entity.ProUser;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-24
 * @Description :   ProUser控制器实现类
 * @Modified :
 */
@Api(tags = "ProUser操作接口")
@Slf4j
@RestController
@RequestMapping("/project/pro_user")
public class ProUserController implements ProUserClient {

    @Autowired
    public IProUserService  proUserService;


    @Override
    @ApiOperation(value = "新增ProUser", notes = "新增一个ProUser")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="proUserForm",value="新增ProUser的form表单",required=true)  ProUserForm proUserForm) {
        log.info("form:",  proUserForm.toString());
        ProUser proUser =new BeanUtils<ProUser>().copyObj(proUserForm,ProUser.class);
        return Result.success( proUserService.save(proUser));
    }


    @Override
    @ApiOperation(value = "删除ProUser", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProUserID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        proUserService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProUser", notes = "修改指定ProUser信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProUser的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="proUserForm",value="修改ProUser的form表单",required=true)  ProUserForm proUserForm) {
        ProUser proUser =new BeanUtils<ProUser>().copyObj(proUserForm,ProUser.class);
        proUser.setId(id);
        proUserService.saveOrUpdate(proUser);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProUser", notes = "获取指定ID的ProUser信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProUser的ID", required = true, dataType = "long")
    public Result<ProUserVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProUser proUser=proUserService.getById(id);
        ProUserVo proUserVo=new BeanUtils<ProUserVo>().copyObj(proUser,ProUserVo.class);
        return new Result<ProUserVo>().sucess(proUserVo);
    }

    @Override
    @ApiOperation(value = "列表查询ProUser", notes = "根据条件查询ProUser列表数据")
    public Result<List<ProUserVo>> list(@Valid @RequestBody @ApiParam(name="proUserQueryForm",value="ProUser查询参数",required=true) ProUserQueryForm proUserQueryForm) {
        log.info("search with proUserQueryForm:", proUserQueryForm.toString());
        List<ProUser> proUserList=proUserService.selectList(proUserQueryForm);
        List<ProUserVo> proUserVoList=new BeanUtils<ProUserVo>().copyObjs(proUserList,ProUserVo.class);
        return new Result<List<ProUserVo>>().sucess(proUserVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProUser", notes = "根据条件查询ProUser分页数据")
    public Result<IPage<ProUserVo>> search(@Valid @RequestBody @ApiParam(name="proUserQueryForm",value="ProUser查询参数",required=true) ProUserQueryForm proUserQueryForm) {
        log.info("search with proUserQueryForm:", proUserQueryForm.toString());
        IPage<ProUser> proUserPage=proUserService.selectPage(proUserQueryForm);
        IPage<ProUserVo> proUserVoPage=new BeanUtils<ProUserVo>().copyPageObjs(proUserPage,ProUserVo.class);
        return new Result<IPage<ProUserVo>>().sucess(proUserVoPage);
    }

    @ApiOperation(value = "查询项目各负责人数据", notes = "根据查询项目各负责人数据")
    @ApiImplicitParam(paramType = "path", name = "userMark", value = "userMark", required = true, dataType = "string")
    @GetMapping(value = "/getProUserList/{userMark}")
    public Result<List<ProUserVo>> getProUserList(@PathVariable String userMark) {
        log.info("userMark: {}", userMark);
        List<ProUser> proUserList = proUserService.getProUserList(userMark);
        List<ProUserVo> proUserVoList = new BeanUtils<ProUserVo>().copyObjs(proUserList, ProUserVo.class);
        return new Result<List<ProUserVo>>().sucess(proUserVoList);
    }

}



