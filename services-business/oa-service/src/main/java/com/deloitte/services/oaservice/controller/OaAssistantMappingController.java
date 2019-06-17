package com.deloitte.services.oaservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.client.OaAssistantMappingClient;
import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaAssistantMappingForm;
import com.deloitte.platform.api.oaservice.vo.OaAssistantMappingVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.oa.util.OaBeanUtils;
import com.deloitte.services.oaservice.entity.OaAssistantMapping;
import com.deloitte.services.oaservice.service.IOaAssistantMappingService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :   OaAssistantMapping控制器实现类
 * @Modified :
 */
@Api(tags = "OaAssistantMapping操作接口")
@Slf4j
@RestController
public class OaAssistantMappingController implements OaAssistantMappingClient {

    @Autowired
    public IOaAssistantMappingService  oaAssistantMappingService;

    @Override
    @ApiOperation(value = "批量新增和更新OaAssistantMappingForm", notes = "批量新增和更新OaAssistantMappingForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@Valid @RequestBody @ApiParam(name="oaAssistantMappingForm",value="批量新增和更新OaAssistantMappingForm",required=true) OaAssistantMappingForm[] oaAssistantMappingForm){
        for(OaAssistantMappingForm form : oaAssistantMappingForm){
            OaAssistantMapping oaAssistantMapping = new OaBeanUtils<OaAssistantMapping>().copyObj(form,OaAssistantMapping.class);
            if(oaAssistantMapping.getEnableFlag()==null || "".equals(oaAssistantMapping.getEnableFlag())){
                oaAssistantMapping.setEnableFlag("Y");
            }
            if(oaAssistantMapping.getId()!=null&&!"".equals(oaAssistantMapping.getId())){
                oaAssistantMappingService.updateById(oaAssistantMapping);
            }else{
                oaAssistantMappingService.save(oaAssistantMapping);
            }
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "新增OaAssistantMapping", notes = "新增一个OaAssistantMapping")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaAssistantMappingForm",value="新增OaAssistantMapping的form表单",required=true)  OaAssistantMappingForm oaAssistantMappingForm) {
        log.info("form:",  oaAssistantMappingForm.toString());
        OaAssistantMapping oaAssistantMapping =new OaBeanUtils<OaAssistantMapping>().copyObj(oaAssistantMappingForm,OaAssistantMapping.class);
        return Result.success( oaAssistantMappingService.save(oaAssistantMapping));
    }


    @Override
    @ApiOperation(value = "删除OaAssistantMapping", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaAssistantMappingID", required = true, dataType = "string")
    public Result delete(@PathVariable String id) {
        oaAssistantMappingService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaAssistantMapping", notes = "修改指定OaAssistantMapping信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaAssistantMapping的ID", required = true, dataType = "string")
    public Result update(@PathVariable String id,
                         @Valid @RequestBody @ApiParam(name="oaAssistantMappingForm",value="修改OaAssistantMapping的form表单",required=true)  OaAssistantMappingForm oaAssistantMappingForm) {
        OaAssistantMapping oaAssistantMapping =new OaBeanUtils<OaAssistantMapping>().copyObj(oaAssistantMappingForm,OaAssistantMapping.class);
        long pk = -1l;
        if(id!=null&&!"".equals(id)){
            pk = Long.valueOf(id);
        }
        oaAssistantMapping.setId(pk);
        oaAssistantMappingService.saveOrUpdate(oaAssistantMapping);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaAssistantMapping", notes = "获取指定ID的OaAssistantMapping信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaAssistantMapping的ID", required = true, dataType = "string")
    public Result<OaAssistantMappingVo> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        OaAssistantMapping oaAssistantMapping=oaAssistantMappingService.getById(id);
        OaAssistantMappingVo oaAssistantMappingVo=new OaBeanUtils<OaAssistantMappingVo>().copyObj(oaAssistantMapping,OaAssistantMappingVo.class);
        return new Result<OaAssistantMappingVo>().sucess(oaAssistantMappingVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaAssistantMapping", notes = "根据条件查询OaAssistantMapping列表数据")
    public Result<List<OaAssistantMappingVo>> list(@Valid @RequestBody @ApiParam(name="oaAssistantMappingQueryForm",value="OaAssistantMapping查询参数",required=true) OaAssistantMappingQueryForm oaAssistantMappingQueryForm) {
        log.info("search with oaAssistantMappingQueryForm:", oaAssistantMappingQueryForm.toString());
        List<OaAssistantMapping> oaAssistantMappingList=oaAssistantMappingService.selectList(oaAssistantMappingQueryForm);
        List<OaAssistantMappingVo> oaAssistantMappingVoList=new OaBeanUtils<OaAssistantMappingVo>().copyObjs(oaAssistantMappingList,OaAssistantMappingVo.class);
        return new Result<List<OaAssistantMappingVo>>().sucess(oaAssistantMappingVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaAssistantMapping", notes = "根据条件查询OaAssistantMapping分页数据")
    public Result<IPage<OaAssistantMappingVo>> search(@Valid @RequestBody @ApiParam(name="oaAssistantMappingQueryForm",value="OaAssistantMapping查询参数",required=true) OaAssistantMappingQueryForm oaAssistantMappingQueryForm) {
        log.info("search with oaAssistantMappingQueryForm:", oaAssistantMappingQueryForm.toString());
        IPage<OaAssistantMapping> oaAssistantMappingPage=oaAssistantMappingService.selectPage(oaAssistantMappingQueryForm);
        IPage<OaAssistantMappingVo> oaAssistantMappingVoPage=new BeanUtils<OaAssistantMappingVo>().copyPageObjs(oaAssistantMappingPage,OaAssistantMappingVo.class);
        return new Result<IPage<OaAssistantMappingVo>>().sucess(oaAssistantMappingVoPage);
    }

}



