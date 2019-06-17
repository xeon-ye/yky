package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaMeetingArrgeClient;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingArrgeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import com.deloitte.services.notice.entity.OaMeetingArrge;
import com.deloitte.services.notice.service.IOaMeetingArrgeService;
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
 * @Description :   OaMeetingArrge控制器实现类
 * @Modified :
 */
@Api(tags = "OaMeetingArrge操作接口")
@Slf4j
@RestController
public class OaMeetingArrgeController implements OaMeetingArrgeClient {

    @Autowired
    public IOaMeetingArrgeService oaMeetingArrgeService;

    @Override
    @ApiOperation(value = "新增OaMeetingArrge", notes = "新增一个OaMeetingArrge")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMeetingArrgeForm",value="新增OaMeetingArrge的form表单",required=true) OaMeetingArrgeForm oaMeetingArrgeForm) {
        log.info("form:",  oaMeetingArrgeForm.toString());
        return oaMeetingArrgeService.save(oaMeetingArrgeForm);
    }


    @Override
    @ApiOperation(value = "删除OaMeetingArrge", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingArrgeID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMeetingArrgeService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMeetingArrge", notes = "修改指定OaMeetingArrge信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMeetingArrge的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMeetingArrgeForm",value="修改OaMeetingArrge的form表单",required=true)  OaMeetingArrgeForm oaMeetingArrgeForm) {
//        OaMeetingArrge oaMeetingArrge =new BeanUtils<OaMeetingArrge>().copyObj(oaMeetingArrgeForm,OaMeetingArrge.class);
//        oaMeetingArrge.setId(id);
//        oaMeetingArrgeService.saveOrUpdate(oaMeetingArrge);
        return oaMeetingArrgeService.update(id, oaMeetingArrgeForm);
    }

    @Override
    @ApiOperation(value = "获取OaMeetingArrge", notes = "获取指定ID的OaMeetingArrge信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingArrge的ID", required = true, dataType = "long")
    public Result<OaMeetingArrgeVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaMeetingArrge oaMeetingArrge=oaMeetingArrgeService.getById(id);
        OaMeetingArrgeVo oaMeetingArrgeVo=new BeanUtils<OaMeetingArrgeVo>().copyObj(oaMeetingArrge,OaMeetingArrgeVo.class);
        oaMeetingArrgeVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaMeetingArrge.getAttachments(), OaAttachmentVo.class));
        return new Result<OaMeetingArrgeVo>().sucess(oaMeetingArrgeVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaMeetingArrge", notes = "根据条件查询OaMeetingArrge列表数据")
    public Result<List<OaMeetingArrgeVo>> list(@Valid @RequestBody @ApiParam(name="oaMeetingArrgeQueryForm",value="OaMeetingArrge查询参数",required=true) OaMeetingArrgeQueryForm oaMeetingArrgeQueryForm) {
        log.info("search with oaMeetingArrgeQueryForm:", oaMeetingArrgeQueryForm.toString());
        List<OaMeetingArrge> oaMeetingArrgeList=oaMeetingArrgeService.selectList(oaMeetingArrgeQueryForm);
        List<OaMeetingArrgeVo> oaMeetingArrgeVoList=new BeanUtils<OaMeetingArrgeVo>().copyObjs(oaMeetingArrgeList,OaMeetingArrgeVo.class);
        return new Result<List<OaMeetingArrgeVo>>().sucess(oaMeetingArrgeVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMeetingArrge", notes = "根据条件查询OaMeetingArrge分页数据")
    public Result<IPage<OaMeetingArrgeVo>> search(@Valid @RequestBody @ApiParam(name="oaMeetingArrgeQueryForm",value="OaMeetingArrge查询参数",required=true) OaMeetingArrgeQueryForm oaMeetingArrgeQueryForm) {
        log.info("search with oaMeetingArrgeQueryForm:", oaMeetingArrgeQueryForm.toString());
        IPage<OaMeetingArrge> oaMeetingArrgePage=oaMeetingArrgeService.selectPage(oaMeetingArrgeQueryForm);
        IPage<OaMeetingArrgeVo> oaMeetingArrgeVoPage=new BeanUtils<OaMeetingArrgeVo>().copyPageObjs(oaMeetingArrgePage,OaMeetingArrgeVo.class);
        return new Result<IPage<OaMeetingArrgeVo>>().sucess(oaMeetingArrgeVoPage);
    }

    @Override
    public Result<List<OaMeetingArrgeVo>> home(Integer num) {
        log.info("search oaMeetingArrge home list");
        List<OaMeetingArrge> oaMeetingArrgeList=oaMeetingArrgeService.getHomeList(num);
        List<OaMeetingArrgeVo> oaMeetingArrgeVoList=new BeanUtils<OaMeetingArrgeVo>().copyObjs(oaMeetingArrgeList,OaMeetingArrgeVo.class);
        return new Result<List<OaMeetingArrgeVo>>().sucess(oaMeetingArrgeVoList);
    }

}



