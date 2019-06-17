package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.notice.client.OaDzggInterfaceTempClient;
import com.deloitte.platform.api.oaservice.notice.param.OaDzggInterfaceTempQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaDzggInterfaceTempForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaDzggInterfaceTempVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaDzggInterfaceTemp;
import com.deloitte.services.notice.service.IOaDzggInterfaceTempService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-16
 * @Description :   OaDzggInterfaceTemp控制器实现类
 * @Modified :
 */
@Api(tags = "OaDzggInterfaceTemp操作接口")
@Slf4j
@RestController
public class OaDzggInterfaceTempController implements OaDzggInterfaceTempClient {

    @Autowired
    public IOaDzggInterfaceTempService oaDzggInterfaceTempService;


    @Override
    @ApiOperation(value = "新增OaDzggInterfaceTemp", notes = "新增一个OaDzggInterfaceTemp")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaDzggInterfaceTempForm",value="新增OaDzggInterfaceTemp的form表单",required=true) OaDzggInterfaceTempForm oaDzggInterfaceTempForm) {
        OaDzggInterfaceTemp entity =new BeanUtils<OaDzggInterfaceTemp>().copyObj(oaDzggInterfaceTempForm,OaDzggInterfaceTemp.class);
        log.info("form:",  oaDzggInterfaceTempForm.toString());
        return Result.success( oaDzggInterfaceTempService.save(entity));
    }


    @Override
    @ApiOperation(value = "删除OaDzggInterfaceTemp", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaDzggInterfaceTempID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaDzggInterfaceTempService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaDzggInterfaceTemp", notes = "修改指定OaDzggInterfaceTemp信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaDzggInterfaceTemp的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaDzggInterfaceTempForm",value="修改OaDzggInterfaceTemp的form表单",required=true)  OaDzggInterfaceTempForm oaDzggInterfaceTempForm) {
        OaDzggInterfaceTemp oaDzggInterfaceTemp =new BeanUtils<OaDzggInterfaceTemp>().copyObj(oaDzggInterfaceTempForm,OaDzggInterfaceTemp.class);
        oaDzggInterfaceTemp.setRequestId(String.valueOf(id));
        oaDzggInterfaceTempService.saveOrUpdate(oaDzggInterfaceTemp);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaDzggInterfaceTemp", notes = "获取指定ID的OaDzggInterfaceTemp信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaDzggInterfaceTemp的ID", required = true, dataType = "long")
    public Result<OaDzggInterfaceTempVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaDzggInterfaceTemp oaDzggInterfaceTemp=oaDzggInterfaceTempService.getById(id);
        OaDzggInterfaceTempVo oaDzggInterfaceTempVo=new BeanUtils<OaDzggInterfaceTempVo>().copyObj(oaDzggInterfaceTemp,OaDzggInterfaceTempVo.class);
        return new Result<OaDzggInterfaceTempVo>().sucess(oaDzggInterfaceTempVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaDzggInterfaceTemp", notes = "根据条件查询OaDzggInterfaceTemp列表数据")
    public Result<List<OaDzggInterfaceTempVo>> list(@Valid @RequestBody @ApiParam(name="oaDzggInterfaceTempQueryForm",value="OaDzggInterfaceTemp查询参数",required=true) OaDzggInterfaceTempQueryForm oaDzggInterfaceTempQueryForm) {
        log.info("search with oaDzggInterfaceTempQueryForm:", oaDzggInterfaceTempQueryForm.toString());
        List<OaDzggInterfaceTemp> oaDzggInterfaceTempList=oaDzggInterfaceTempService.selectList(oaDzggInterfaceTempQueryForm);
        List<OaDzggInterfaceTempVo> oaDzggInterfaceTempVoList=new BeanUtils<OaDzggInterfaceTempVo>().copyObjs(oaDzggInterfaceTempList,OaDzggInterfaceTempVo.class);
        return new Result<List<OaDzggInterfaceTempVo>>().sucess(oaDzggInterfaceTempVoList);
    }

    @Override
    @ApiOperation(value = "分页查询OaDzggInterfaceTemp", notes = "根据条件查询OaDzggInterfaceTemp分页数据")
    public Result<IPage<OaDzggInterfaceTempVo>> search(@Valid @RequestBody @ApiParam(name="oaDzggInterfaceTempQueryForm",value="OaDzggInterfaceTemp查询参数",required=true) OaDzggInterfaceTempQueryForm oaDzggInterfaceTempQueryForm) {
        log.info("search with oaDzggInterfaceTempQueryForm:", oaDzggInterfaceTempQueryForm.toString());
        IPage<OaDzggInterfaceTemp> oaDzggInterfaceTempPage=oaDzggInterfaceTempService.selectPage(oaDzggInterfaceTempQueryForm);
        IPage<OaDzggInterfaceTempVo> oaDzggInterfaceTempVoPage=new BeanUtils<OaDzggInterfaceTempVo>().copyPageObjs(oaDzggInterfaceTempPage,OaDzggInterfaceTempVo.class);
        return new Result<IPage<OaDzggInterfaceTempVo>>().sucess(oaDzggInterfaceTempVoPage);
    }

}