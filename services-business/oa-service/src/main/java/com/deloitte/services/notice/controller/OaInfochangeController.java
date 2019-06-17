package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaInfochangeClient;
import com.deloitte.platform.api.oaservice.notice.param.OaInfochangeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import com.deloitte.services.notice.entity.OaInfochange;
import com.deloitte.services.notice.service.IOaInfochangeService;
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
 * @Description :   OaInfochange控制器实现类
 * @Modified :
 */
@Api(tags = "OaInfochange操作接口")
@Slf4j
@RestController
public class OaInfochangeController implements OaInfochangeClient {

    @Autowired
    public IOaInfochangeService oaInfochangeService;

    @Override
    @ApiOperation(value = "新增OaInfochange", notes = "新增一个OaInfochange")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaInfochangeForm",value="新增OaInfochange的form表单",required=true) OaInfochangeForm oaInfochangeForm) {
        log.info("form:",  oaInfochangeForm.toString());
        return oaInfochangeService.save(oaInfochangeForm);
    }


    @Override
    @ApiOperation(value = "删除OaInfochange", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaInfochangeID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaInfochangeService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaInfochange", notes = "修改指定OaInfochange信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaInfochange的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaInfochangeForm",value="修改OaInfochange的form表单",required=true)  OaInfochangeForm oaInfochangeForm) {
//        OaInfochange oaInfochange =new BeanUtils<OaInfochange>().copyObj(oaInfochangeForm,OaInfochange.class);
//        oaInfochange.setId(id);
//        oaInfochangeService.saveOrUpdate(oaInfochange);
        return oaInfochangeService.update(id, oaInfochangeForm);
    }

    @Override
    @ApiOperation(value = "获取OaInfochange", notes = "获取指定ID的OaInfochange信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaInfochange的ID", required = true, dataType = "long")
    public Result<OaInfochangeVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaInfochange oaInfochange=oaInfochangeService.getById(id);
        OaInfochangeVo oaInfochangeVo=new BeanUtils<OaInfochangeVo>().copyObj(oaInfochange,OaInfochangeVo.class);
        oaInfochangeVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaInfochange.getAttachments(), OaAttachmentVo.class));
        return new Result<OaInfochangeVo>().sucess(oaInfochangeVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaInfochange", notes = "根据条件查询OaInfochange列表数据")
    public Result<List<OaInfochangeVo>> list(@Valid @RequestBody @ApiParam(name="oaInfochangeQueryForm",value="OaInfochange查询参数",required=true) OaInfochangeQueryForm oaInfochangeQueryForm) {
        log.info("search with oaInfochangeQueryForm:", oaInfochangeQueryForm.toString());
        List<OaInfochange> oaInfochangeList=oaInfochangeService.selectList(oaInfochangeQueryForm);
        List<OaInfochangeVo> oaInfochangeVoList=new BeanUtils<OaInfochangeVo>().copyObjs(oaInfochangeList,OaInfochangeVo.class);
        return new Result<List<OaInfochangeVo>>().sucess(oaInfochangeVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaInfochange", notes = "根据条件查询OaInfochange分页数据")
    public Result<IPage<OaInfochangeVo>> search(@Valid @RequestBody @ApiParam(name="oaInfochangeQueryForm",value="OaInfochange查询参数",required=true) OaInfochangeQueryForm oaInfochangeQueryForm) {
        log.info("search with oaInfochangeQueryForm:", oaInfochangeQueryForm.toString());
        IPage<OaInfochange> oaInfochangePage=oaInfochangeService.selectPage(oaInfochangeQueryForm);
        IPage<OaInfochangeVo> oaInfochangeVoPage=new BeanUtils<OaInfochangeVo>().copyPageObjs(oaInfochangePage,OaInfochangeVo.class);
        return new Result<IPage<OaInfochangeVo>>().sucess(oaInfochangeVoPage);
    }

    @Override
    public Result<List<OaInfochangeVo>> home(Integer num) {
        log.info("search oaInfochange home list");
        List<OaInfochange> oaInfochangeList=oaInfochangeService.getHomeList(num);
        List<OaInfochangeVo> oaInfochangeVoList=new BeanUtils<OaInfochangeVo>().copyObjs(oaInfochangeList,OaInfochangeVo.class);
        return new Result<List<OaInfochangeVo>>().sucess(oaInfochangeVoList);
    }

}



