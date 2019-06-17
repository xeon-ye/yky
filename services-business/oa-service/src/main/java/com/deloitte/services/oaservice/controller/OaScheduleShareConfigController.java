package com.deloitte.services.oaservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaScheduleShareConfigQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleShareConfigForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleShareConfigVo;
import com.deloitte.platform.api.oaservice.client.OaScheduleShareConfigClient;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oa.util.OaBeanUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.oaservice.service.IOaScheduleShareConfigService;
import com.deloitte.services.oaservice.entity.OaScheduleShareConfig;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description :   OaScheduleShareConfig控制器实现类
 * @Modified :
 */
@Api(tags = "OaScheduleShareConfig操作接口")
@Slf4j
@RestController
public class OaScheduleShareConfigController implements OaScheduleShareConfigClient {

    @Autowired
    public IOaScheduleShareConfigService  oaScheduleShareConfigService;


    @Override
    @ApiOperation(value = "新增OaScheduleShareConfig", notes = "新增一个OaScheduleShareConfig")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@Valid @RequestBody @ApiParam(name="oaScheduleShareConfigForm",value="新增OaScheduleShareConfig的form表单",required=true)  OaScheduleShareConfigForm[] oaScheduleShareConfigForm) {
        log.info("form:",  oaScheduleShareConfigForm.toString());
        UserVo user = UserUtil.getUserVo();
        for(OaScheduleShareConfigForm form : oaScheduleShareConfigForm){
            OaScheduleShareConfig oaScheduleShareConfig = new OaBeanUtils<OaScheduleShareConfig>().copyObj(form,OaScheduleShareConfig.class);
            if(oaScheduleShareConfig.getEnableFlag()==null || "".equals(oaScheduleShareConfig.getEnableFlag())){
                oaScheduleShareConfig.setEnableFlag("Y");
            }
            if(oaScheduleShareConfig.getId()!=null&&!"".equals(oaScheduleShareConfig.getId())){
                if(user!=null && user.getId()!=null) {
                    oaScheduleShareConfig.setUpdateBy(user.getId());
                }
                oaScheduleShareConfigService.updateById(oaScheduleShareConfig);
            }else{
                if(user!=null && user.getId()!=null) {
                    oaScheduleShareConfig.setCreateBy(user.getId());
                    oaScheduleShareConfig.setUpdateBy(user.getId());
                }
                oaScheduleShareConfigService.save(oaScheduleShareConfig);
            }
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "新增OaScheduleShareConfig", notes = "新增一个OaScheduleShareConfig")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaScheduleShareConfigForm",value="新增OaScheduleShareConfig的form表单",required=true)  OaScheduleShareConfigForm oaScheduleShareConfigForm) {
        log.info("form:",  oaScheduleShareConfigForm.toString());
        UserVo user = UserUtil.getUserVo();
        OaScheduleShareConfig oaScheduleShareConfig =new OaBeanUtils<OaScheduleShareConfig>().copyObj(oaScheduleShareConfigForm,OaScheduleShareConfig.class);
        if(user!=null && user.getId()!=null) {
            oaScheduleShareConfig.setCreateBy(user.getId());
            oaScheduleShareConfig.setUpdateBy(user.getId());
        }
        return Result.success( oaScheduleShareConfigService.save(oaScheduleShareConfig));
    }


    @Override
    @ApiOperation(value = "删除OaScheduleShareConfig", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaScheduleShareConfigID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaScheduleShareConfigService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaScheduleShareConfig", notes = "修改指定OaScheduleShareConfig信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaScheduleShareConfig的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaScheduleShareConfigForm",value="修改OaScheduleShareConfig的form表单",required=true)  OaScheduleShareConfigForm oaScheduleShareConfigForm) {
        OaScheduleShareConfig oaScheduleShareConfig =new OaBeanUtils<OaScheduleShareConfig>().copyObj(oaScheduleShareConfigForm,OaScheduleShareConfig.class);
        UserVo user = UserUtil.getUserVo();
        oaScheduleShareConfig.setId(id);
        if(user!=null && user.getId()!=null) {
            oaScheduleShareConfig.setUpdateBy(user.getId());
        }
        oaScheduleShareConfigService.saveOrUpdate(oaScheduleShareConfig);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaScheduleShareConfig", notes = "获取指定ID的OaScheduleShareConfig信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaScheduleShareConfig的ID", required = true, dataType = "long")
    public Result<OaScheduleShareConfigVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaScheduleShareConfig oaScheduleShareConfig=oaScheduleShareConfigService.getById(id);
        OaScheduleShareConfigVo oaScheduleShareConfigVo=new OaBeanUtils<OaScheduleShareConfigVo>().copyObj(oaScheduleShareConfig,OaScheduleShareConfigVo.class);
        return new Result<OaScheduleShareConfigVo>().sucess(oaScheduleShareConfigVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaScheduleShareConfig", notes = "根据条件查询OaScheduleShareConfig列表数据")
    public Result<List<OaScheduleShareConfigVo>> list(@Valid @RequestBody @ApiParam(name="oaScheduleShareConfigQueryForm",value="OaScheduleShareConfig查询参数",required=true) OaScheduleShareConfigQueryForm oaScheduleShareConfigQueryForm) {
        log.info("search with oaScheduleShareConfigQueryForm:", oaScheduleShareConfigQueryForm.toString());
        List<OaScheduleShareConfig> oaScheduleShareConfigList=oaScheduleShareConfigService.selectList(oaScheduleShareConfigQueryForm);
        List<OaScheduleShareConfigVo> oaScheduleShareConfigVoList=new OaBeanUtils<OaScheduleShareConfigVo>().copyObjs(oaScheduleShareConfigList,OaScheduleShareConfigVo.class);
        return new Result<List<OaScheduleShareConfigVo>>().sucess(oaScheduleShareConfigVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaScheduleShareConfig", notes = "根据条件查询OaScheduleShareConfig分页数据")
    public Result<IPage<OaScheduleShareConfigVo>> search(@Valid @RequestBody @ApiParam(name="oaScheduleShareConfigQueryForm",value="OaScheduleShareConfig查询参数",required=true) OaScheduleShareConfigQueryForm oaScheduleShareConfigQueryForm) {
        log.info("search with oaScheduleShareConfigQueryForm:", oaScheduleShareConfigQueryForm.toString());
        IPage<OaScheduleShareConfig> oaScheduleShareConfigPage=oaScheduleShareConfigService.selectPage(oaScheduleShareConfigQueryForm);
        IPage<OaScheduleShareConfigVo> oaScheduleShareConfigVoPage=new OaBeanUtils<OaScheduleShareConfigVo>().copyPageObjs(oaScheduleShareConfigPage,OaScheduleShareConfigVo.class);
        return new Result<IPage<OaScheduleShareConfigVo>>().sucess(oaScheduleShareConfigVoPage);
    }

}



