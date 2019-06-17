package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.BaseUserQueryForm;
import com.deloitte.platform.api.isump.vo.BaseUserForm;
import com.deloitte.platform.api.isump.vo.BaseUserVo;
import com.deloitte.platform.api.isump.BaseUserClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IBaseUserService;
import com.deloitte.services.isump.entity.BaseUser;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   BaseUser控制器实现类
 * @Modified :
 */
@Api(tags = "BaseUser操作接口")
@Slf4j
@RestController
public class BaseUserController implements BaseUserClient {

    @Autowired
    public IBaseUserService  baseUserService;


    @Override
    @ApiOperation(value = "新增BaseUser", notes = "新增一个BaseUser")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="baseUserForm",value="新增BaseUser的form表单",required=true) BaseUserForm baseUserForm) {
        log.info("form:",  baseUserForm.toString());
        BaseUser baseUser =new BeanUtils<BaseUser>().copyObj(baseUserForm,BaseUser.class);
        return Result.success( baseUserService.save(baseUser));
    }


    @Override
    @ApiOperation(value = "删除BaseUser", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BaseUserID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        baseUserService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BaseUser", notes = "修改指定BaseUser信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BaseUser的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="baseUserForm",value="修改BaseUser的form表单",required=true)  BaseUserForm baseUserForm) {
        BaseUser baseUser =new BeanUtils<BaseUser>().copyObj(baseUserForm,BaseUser.class);
        baseUser.setId(id);
        baseUserService.saveOrUpdate(baseUser);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取BaseUser", notes = "获取指定ID的BaseUser信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BaseUser的ID", required = true, dataType = "long")
    public Result<BaseUserVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BaseUser baseUser=baseUserService.getById(id);
        BaseUserVo baseUserVo=new BeanUtils<BaseUserVo>().copyObj(baseUser,BaseUserVo.class);
        return new Result<BaseUserVo>().sucess(baseUserVo);
    }

    @Override
    @ApiOperation(value = "列表查询BaseUser", notes = "根据条件查询BaseUser列表数据")
    public Result<List<BaseUserVo>> list(@Valid @RequestBody @ApiParam(name="baseUserQueryForm",value="BaseUser查询参数",required=true) BaseUserQueryForm baseUserQueryForm) {
        log.info("search with baseUserQueryForm:", baseUserQueryForm.toString());
        List<BaseUser> baseUserList=baseUserService.selectList(baseUserQueryForm);
        List<BaseUserVo> baseUserVoList=new BeanUtils<BaseUserVo>().copyObjs(baseUserList,BaseUserVo.class);
        return new Result<List<BaseUserVo>>().sucess(baseUserVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BaseUser", notes = "根据条件查询BaseUser分页数据")
    public Result<IPage<BaseUserVo>> search(@Valid @RequestBody @ApiParam(name="baseUserQueryForm",value="BaseUser查询参数",required=true) BaseUserQueryForm baseUserQueryForm) {
        log.info("search with baseUserQueryForm:", baseUserQueryForm.toString());
        IPage<BaseUser> baseUserPage=baseUserService.selectPage(baseUserQueryForm);
        IPage<BaseUserVo> baseUserVoPage=new BeanUtils<BaseUserVo>().copyPageObjs(baseUserPage,BaseUserVo.class);
        return new Result<IPage<BaseUserVo>>().sucess(baseUserVoPage);
    }

}



