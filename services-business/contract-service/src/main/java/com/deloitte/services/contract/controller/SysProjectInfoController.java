package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SysProjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysProjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysProjectInfoVo;
import com.deloitte.platform.api.contract.client.SysProjectInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ISysProjectInfoService;
import com.deloitte.services.contract.entity.SysProjectInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SysProjectInfo控制器实现类
 * @Modified :
 */
@Api(tags = "系统项目操作接口")
@Slf4j
@RestController
public class SysProjectInfoController implements SysProjectInfoClient {

    @Autowired
    public ISysProjectInfoService  sysProjectInfoService;


    @Override
    @ApiOperation(value = "新增SysProjectInfo", notes = "新增一个SysProjectInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="sysProjectInfoForm",value="新增SysProjectInfo的form表单",required=true)  SysProjectInfoForm sysProjectInfoForm) {
        log.info("form:",  sysProjectInfoForm.toString());
        SysProjectInfo sysProjectInfo =new BeanUtils<SysProjectInfo>().copyObj(sysProjectInfoForm,SysProjectInfo.class);
        return Result.success( sysProjectInfoService.save(sysProjectInfo));
    }


    @Override
    @ApiOperation(value = "删除SysProjectInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysProjectInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sysProjectInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SysProjectInfo", notes = "修改指定SysProjectInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SysProjectInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="sysProjectInfoForm",value="修改SysProjectInfo的form表单",required=true)  SysProjectInfoForm sysProjectInfoForm) {
        SysProjectInfo sysProjectInfo =new BeanUtils<SysProjectInfo>().copyObj(sysProjectInfoForm,SysProjectInfo.class);
        sysProjectInfo.setId(id);
        sysProjectInfoService.saveOrUpdate(sysProjectInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SysProjectInfo", notes = "获取指定ID的SysProjectInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysProjectInfo的ID", required = true, dataType = "long")
    public Result<SysProjectInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SysProjectInfo sysProjectInfo=sysProjectInfoService.getById(id);
        SysProjectInfoVo sysProjectInfoVo=new BeanUtils<SysProjectInfoVo>().copyObj(sysProjectInfo,SysProjectInfoVo.class);
        return new Result<SysProjectInfoVo>().sucess(sysProjectInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询SysProjectInfo", notes = "根据条件查询SysProjectInfo列表数据")
    public Result<List<SysProjectInfoVo>> list(@Valid @RequestBody @ApiParam(name="sysProjectInfoQueryForm",value="SysProjectInfo查询参数",required=true) SysProjectInfoQueryForm sysProjectInfoQueryForm) {
        log.info("search with sysProjectInfoQueryForm:", sysProjectInfoQueryForm.toString());
        List<SysProjectInfo> sysProjectInfoList=sysProjectInfoService.selectList(sysProjectInfoQueryForm);
        List<SysProjectInfoVo> sysProjectInfoVoList=new BeanUtils<SysProjectInfoVo>().copyObjs(sysProjectInfoList,SysProjectInfoVo.class);
        return new Result<List<SysProjectInfoVo>>().sucess(sysProjectInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SysProjectInfo", notes = "根据条件查询SysProjectInfo分页数据")
    public Result<IPage<SysProjectInfoVo>> search(@Valid @RequestBody @ApiParam(name="sysProjectInfoQueryForm",value="SysProjectInfo查询参数",required=true) SysProjectInfoQueryForm sysProjectInfoQueryForm) {
//        log.info("search with sysProjectInfoQueryForm:", sysProjectInfoQueryForm.toString());
//        IPage<SysProjectInfo> sysProjectInfoPage=sysProjectInfoService.selectPage(sysProjectInfoQueryForm);
//        IPage<SysProjectInfoVo> sysProjectInfoVoPage=new BeanUtils<SysProjectInfoVo>().copyPageObjs(sysProjectInfoPage,SysProjectInfoVo.class);
        IPage<SysProjectInfoVo> sysProjectInfoVoPage = sysProjectInfoService.searchNoUser(sysProjectInfoQueryForm);
        return new Result<IPage<SysProjectInfoVo>>().sucess(sysProjectInfoVoPage);
    }

}



