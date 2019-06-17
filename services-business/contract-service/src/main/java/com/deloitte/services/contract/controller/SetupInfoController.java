package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SetupInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SetupInfoForm;
import com.deloitte.platform.api.contract.vo.SetupInfoVo;
import com.deloitte.platform.api.contract.client.SetupInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ISetupInfoService;
import com.deloitte.services.contract.entity.SetupInfo;


/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-13
 * @Description :   SetupInfo控制器实现类
 * @Modified :
 */
@Api(tags = "SetupInfo操作接口")
@Slf4j
@RestController
public class SetupInfoController implements SetupInfoClient {

    @Autowired
    public ISetupInfoService  setupInfoService;


    @Override
    @ApiOperation(value = "新增SetupInfo", notes = "新增一个SetupInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="setupInfoForm",value="新增SetupInfo的form表单",required=true)  SetupInfoForm setupInfoForm) {
        log.info("form:",  setupInfoForm.toString());
        SetupInfo setupInfo =new BeanUtils<SetupInfo>().copyObj(setupInfoForm,SetupInfo.class);
        return Result.success( setupInfoService.save(setupInfo));
    }


    @Override
    @ApiOperation(value = "删除SetupInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SetupInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        setupInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SetupInfo", notes = "修改指定SetupInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SetupInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="setupInfoForm",value="修改SetupInfo的form表单",required=true)  SetupInfoForm setupInfoForm) {
        SetupInfo setupInfo =new BeanUtils<SetupInfo>().copyObj(setupInfoForm,SetupInfo.class);
        setupInfo.setId(id);
        setupInfoService.saveOrUpdate(setupInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SetupInfo", notes = "获取指定ID的SetupInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SetupInfo的ID", required = true, dataType = "long")
    public Result<SetupInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SetupInfo setupInfo=setupInfoService.getById(id);
        SetupInfoVo setupInfoVo=new BeanUtils<SetupInfoVo>().copyObj(setupInfo,SetupInfoVo.class);
        return new Result<SetupInfoVo>().sucess(setupInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询SetupInfo", notes = "根据条件查询SetupInfo列表数据")
    public Result<List<SetupInfoVo>> list(@Valid @RequestBody @ApiParam(name="setupInfoQueryForm",value="SetupInfo查询参数",required=true) SetupInfoQueryForm setupInfoQueryForm) {
        log.info("search with setupInfoQueryForm:", setupInfoQueryForm.toString());
        List<SetupInfo> setupInfoList=setupInfoService.selectList(setupInfoQueryForm);
        List<SetupInfoVo> setupInfoVoList=new BeanUtils<SetupInfoVo>().copyObjs(setupInfoList,SetupInfoVo.class);
        return new Result<List<SetupInfoVo>>().sucess(setupInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SetupInfo", notes = "根据条件查询SetupInfo分页数据")
    public Result<IPage<SetupInfoVo>> search(@Valid @RequestBody @ApiParam(name="setupInfoQueryForm",value="SetupInfo查询参数",required=true) SetupInfoQueryForm setupInfoQueryForm) {
        log.info("search with setupInfoQueryForm:", setupInfoQueryForm.toString());
        IPage<SetupInfo> setupInfoPage=setupInfoService.selectPage(setupInfoQueryForm);
        IPage<SetupInfoVo> setupInfoVoPage=new BeanUtils<SetupInfoVo>().copyPageObjs(setupInfoPage,SetupInfoVo.class);
        return new Result<IPage<SetupInfoVo>>().sucess(setupInfoVoPage);
    }

}



