package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaResourceClient;
import com.deloitte.platform.api.oaservice.notice.param.OaResourceQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaResourceForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaResourceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import com.deloitte.services.notice.entity.OaResource;
import com.deloitte.services.notice.service.IOaResourceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :   OaResource控制器实现类
 * @Modified :
 */
@Api(tags = "OaResource操作接口")
@Slf4j
@RestController
public class OaResourceController implements OaResourceClient {

    @Autowired
    public IOaResourceService oaResourceService;

    @Override
    @ApiOperation(value = "新增OaResource", notes = "新增一个OaResource")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaResourceForm",value="新增OaResource的form表单",required=true) OaResourceForm oaResourceForm) {
        log.info("form:",  oaResourceForm.toString());
        return oaResourceService.save(oaResourceForm);
    }


    @Override
    @ApiOperation(value = "删除OaResource", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaResourceID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaResourceService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaResource", notes = "修改指定OaResource信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaResource的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaResourceForm",value="修改OaResource的form表单",required=true)  OaResourceForm oaResourceForm) {
//        OaResource oaResource =new BeanUtils<OaResource>().copyObj(oaResourceForm,OaResource.class);
//        oaResource.setId(id);
//        oaResourceService.saveOrUpdate(oaResource);
        return oaResourceService.update(id, oaResourceForm);
    }

    @Override
    @ApiOperation(value = "获取OaResource", notes = "获取指定ID的OaResource信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaResource的ID", required = true, dataType = "long")
    public Result<OaResourceVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaResource oaResource=oaResourceService.getById(id);
        OaResourceVo oaResourceVo=new BeanUtils<OaResourceVo>().copyObj(oaResource,OaResourceVo.class);
        oaResourceVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaResource.getAttachments(), OaAttachmentVo.class));
        return new Result<OaResourceVo>().sucess(oaResourceVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaResource", notes = "根据条件查询OaResource列表数据")
    public Result<List<OaResourceVo>> list(@Valid @RequestBody @ApiParam(name="oaResourceQueryForm",value="OaResource查询参数",required=true) OaResourceQueryForm oaResourceQueryForm) {
        log.info("search with oaResourceQueryForm:", oaResourceQueryForm.toString());
        List<OaResource> oaResourceList=oaResourceService.selectList(oaResourceQueryForm);
        List<OaResourceVo> oaResourceVoList=new BeanUtils<OaResourceVo>().copyObjs(oaResourceList,OaResourceVo.class);
        return new Result<List<OaResourceVo>>().sucess(oaResourceVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaResource", notes = "根据条件查询OaResource分页数据")
    public Result<IPage<OaResourceVo>> search(@Valid @RequestBody @ApiParam(name="oaResourceQueryForm",value="OaResource查询参数",required=true) OaResourceQueryForm oaResourceQueryForm) {
        log.info("search with oaResourceQueryForm:", oaResourceQueryForm.toString());
        IPage<OaResource> oaResourcePage=oaResourceService.selectPage(oaResourceQueryForm);
        IPage<OaResourceVo> oaResourceVoPage=new BeanUtils<OaResourceVo>().copyPageObjs(oaResourcePage,OaResourceVo.class);
        return new Result<IPage<OaResourceVo>>().sucess(oaResourceVoPage);
    }

    @Override
    public Result<List<OaResourceVo>> home(Integer num) {
        log.info("search oaResource home list");
        List<OaResource> oaResourceList=oaResourceService.getHomeList(num);
        List<OaResourceVo> oaResourceVoList=new BeanUtils<OaResourceVo>().copyObjs(oaResourceList,OaResourceVo.class);
        return new Result<List<OaResourceVo>>().sucess(oaResourceVoList);
    }

}



