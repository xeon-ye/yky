package com.deloitte.services.demomybatiesauto.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.demomybatiesauto.param.UserDemoQueryForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoVo;
import com.deloitte.platform.api.demomybatiesauto.UserDemoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.demomybatiesauto.constant.DemoErrorType;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.demomybatiesauto.service.IUserDemoService;
import com.deloitte.services.demomybatiesauto.entity.UserDemo;


/**
 * @Author : jack
 * @Date : Create in 2019-02-20
 * @Description :   UserDemo控制器实现类
 * @Modified :
 */
@Api(tags = "UserDemo操作接口")
@Slf4j
@RestController
public class UserDemoController implements UserDemoClient {

    @Autowired
    public IUserDemoService  userDemoService;


    @Override
    @ApiOperation(value = "新增UserDemo", notes = "新增一个UserDemo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="userDemoForm",value="新增UserDemo的form表单",required=true)  UserDemoForm userDemoForm) {
        log.info("form:",  userDemoForm.toString());
        UserDemo userDemo =new BeanUtils<UserDemo>().copyObj(userDemoForm,UserDemo.class);
        return Result.success( userDemoService.save(userDemo));
    }


    @Override
    @ApiOperation(value = "删除UserDemo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "UserDemoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        if(userDemoService.getById(id)==null){
            return Result.fail(DemoErrorType.DEMO_RUN_ERROR);
        }
        userDemoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改UserDemo", notes = "修改指定UserDemo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "UserDemo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="userDemoForm",value="修改UserDemo的form表单",required=true)  UserDemoForm userDemoForm) {
        UserDemo userDemo =new BeanUtils<UserDemo>().copyObj(userDemoForm,UserDemo.class);
        userDemo.setId(id);
        userDemoService.saveOrUpdate(userDemo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取UserDemo", notes = "获取指定ID的UserDemo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "UserDemo的ID", required = true, dataType = "long")
    public Result<UserDemoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
//        log.error("===============》睡10秒《=================");
//        try {
//            Thread.sleep(10000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        log.error("===============》睡完10秒《=================");
//        log.error("改了一下");
        UserDemo userDemo=userDemoService.getById(id);
        UserDemoVo userDemoVo=new BeanUtils<UserDemoVo>().copyObj(userDemo,UserDemoVo.class);
        return new Result<UserDemoVo>().sucess(userDemoVo);
    }

    @Override
    @ApiOperation(value = "列表查询UserDemo", notes = "根据条件查询UserDemo列表数据")
    public Result<List<UserDemoVo>> list(@Valid @RequestBody @ApiParam(name="userDemoQueryForm",value="UserDemo查询参数",required=true) UserDemoQueryForm userDemoQueryForm) {
        log.info("search with userDemoQueryForm:", userDemoQueryForm.toString());
        List<UserDemo> userDemoList=userDemoService.selectList(userDemoQueryForm);
        List<UserDemoVo> userDemoVoList=new BeanUtils<UserDemoVo>().copyObjs(userDemoList,UserDemoVo.class);
        return new Result<List<UserDemoVo>>().sucess(userDemoVoList);
    }

    /* 普通调用使用IPage就可以了
     * 如果是Fegin调用，需要返回分页信息，就需要使用GdcPage对象
     */
    @Override
    @ApiOperation(value = "分页查询UserDemo", notes = "根据条件查询UserDemo分页数据")
    public Result<GdcPage<UserDemoVo>> search(@Valid @RequestBody @ApiParam(name="userDemoQueryForm",value="UserDemo查询参数",required=true) UserDemoQueryForm userDemoQueryForm) {
        log.info("search with userDemoQueryForm:", userDemoQueryForm.toString());
        IPage<UserDemo> userDemoPage=userDemoService.selectPage(userDemoQueryForm);
        IPage<UserDemoVo> userDemoVoPage=new BeanUtils<UserDemoVo>().copyPageObjs(userDemoPage,UserDemoVo.class);
        GdcPage<UserDemoVo> pages=new GdcPage(userDemoVoPage.getRecords(),userDemoVoPage.getTotal(),(int)userDemoVoPage.getCurrent(),(int)userDemoVoPage.getSize());
        Result<GdcPage<UserDemoVo>> result=new Result<GdcPage<UserDemoVo>>().sucess(pages);
        return result;
    }



}



