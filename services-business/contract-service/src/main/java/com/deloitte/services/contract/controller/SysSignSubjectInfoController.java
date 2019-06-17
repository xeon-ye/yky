package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SysSignSubjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoVo;
import com.deloitte.platform.api.contract.client.SysSignSubjectInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ISysSignSubjectInfoService;
import com.deloitte.services.contract.entity.SysSignSubjectInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SysSignSubjectInfo控制器实现类
 * @Modified :
 */
@Api(tags = "系统投资主体操作接口")
@Slf4j
@RestController
public class SysSignSubjectInfoController implements SysSignSubjectInfoClient {

    @Autowired
    public ISysSignSubjectInfoService  sysSignSubjectInfoService;


    @Override
    @ApiOperation(value = "新增SysSignSubjectInfo", notes = "新增一个SysSignSubjectInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="sysSignSubjectInfoForm",value="新增SysSignSubjectInfo的form表单",required=true)  SysSignSubjectInfoForm sysSignSubjectInfoForm) {
        log.info("form:",  sysSignSubjectInfoForm.toString());
        SysSignSubjectInfo sysSignSubjectInfo =new BeanUtils<SysSignSubjectInfo>().copyObj(sysSignSubjectInfoForm,SysSignSubjectInfo.class);
        return Result.success( sysSignSubjectInfoService.save(sysSignSubjectInfo));
    }


    @Override
    @ApiOperation(value = "删除SysSignSubjectInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysSignSubjectInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sysSignSubjectInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SysSignSubjectInfo", notes = "修改指定SysSignSubjectInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SysSignSubjectInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="sysSignSubjectInfoForm",value="修改SysSignSubjectInfo的form表单",required=true)  SysSignSubjectInfoForm sysSignSubjectInfoForm) {
        SysSignSubjectInfo sysSignSubjectInfo =new BeanUtils<SysSignSubjectInfo>().copyObj(sysSignSubjectInfoForm,SysSignSubjectInfo.class);
        sysSignSubjectInfo.setId(id);
        sysSignSubjectInfoService.saveOrUpdate(sysSignSubjectInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SysSignSubjectInfo", notes = "获取指定ID的SysSignSubjectInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysSignSubjectInfo的ID", required = true, dataType = "long")
    public Result<SysSignSubjectInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SysSignSubjectInfo sysSignSubjectInfo=sysSignSubjectInfoService.getById(id);
        SysSignSubjectInfoVo sysSignSubjectInfoVo=new BeanUtils<SysSignSubjectInfoVo>().copyObj(sysSignSubjectInfo,SysSignSubjectInfoVo.class);
        return new Result<SysSignSubjectInfoVo>().sucess(sysSignSubjectInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询SysSignSubjectInfo", notes = "根据条件查询SysSignSubjectInfo列表数据")
    public Result<List<SysSignSubjectInfoVo>> list(@Valid @RequestBody @ApiParam(name="sysSignSubjectInfoQueryForm",value="SysSignSubjectInfo查询参数",required=true) SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm) {
        log.info("search with sysSignSubjectInfoQueryForm:", sysSignSubjectInfoQueryForm.toString());
        List<SysSignSubjectInfo> sysSignSubjectInfoList=sysSignSubjectInfoService.selectList(sysSignSubjectInfoQueryForm);
        List<SysSignSubjectInfoVo> sysSignSubjectInfoVoList=new BeanUtils<SysSignSubjectInfoVo>().copyObjs(sysSignSubjectInfoList,SysSignSubjectInfoVo.class);
        return new Result<List<SysSignSubjectInfoVo>>().sucess(sysSignSubjectInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SysSignSubjectInfo", notes = "根据条件查询SysSignSubjectInfo分页数据")
    public Result<IPage<SysSignSubjectInfoVo>> search(@Valid @RequestBody @ApiParam(name="sysSignSubjectInfoQueryForm",value="SysSignSubjectInfo查询参数",required=true) SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm) {
        log.info("search with sysSignSubjectInfoQueryForm:", sysSignSubjectInfoQueryForm.toString());
        IPage<SysSignSubjectInfo> sysSignSubjectInfoPage=sysSignSubjectInfoService.selectPage(sysSignSubjectInfoQueryForm);
        IPage<SysSignSubjectInfoVo> sysSignSubjectInfoVoPage=new BeanUtils<SysSignSubjectInfoVo>().copyPageObjs(sysSignSubjectInfoPage,SysSignSubjectInfoVo.class);
        return new Result<IPage<SysSignSubjectInfoVo>>().sucess(sysSignSubjectInfoVoPage);
    }

}



