package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaCalenderClient;
import com.deloitte.platform.api.oaservice.notice.param.OaCalenderQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import com.deloitte.services.notice.entity.OaCalender;
import com.deloitte.services.notice.service.IOaCalenderService;
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
 * @Description :   OaCalender控制器实现类
 * @Modified :
 */
@Api(tags = "OaCalender操作接口")
@Slf4j
@RestController
public class OaCalenderController implements OaCalenderClient {

    @Autowired
    public IOaCalenderService oaCalenderService;

    @Override
    @ApiOperation(value = "新增OaCalender", notes = "新增一个OaCalender")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaCalenderForm",value="新增OaCalender的form表单",required=true) OaCalenderForm oaCalenderForm) {
        log.info("form:",  oaCalenderForm.toString());
        return oaCalenderService.save(oaCalenderForm);
    }


    @Override
    @ApiOperation(value = "删除OaCalender", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaCalenderID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaCalenderService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaCalender", notes = "修改指定OaCalender信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaCalender的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaCalenderForm",value="修改OaCalender的form表单",required=true)  OaCalenderForm oaCalenderForm) {
//        OaCalender oaCalender =new BeanUtils<OaCalender>().copyObj(oaCalenderForm,OaCalender.class);
//        oaCalender.setId(id);
//        oaCalenderService.saveOrUpdate(oaCalender);
        return oaCalenderService.update(id, oaCalenderForm);
    }

    @Override
    @ApiOperation(value = "获取OaCalender", notes = "获取指定ID的OaCalender信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaCalender的ID", required = true, dataType = "long")
    public Result<OaCalenderVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaCalender oaCalender=oaCalenderService.getById(id);
        OaCalenderVo oaCalenderVo=new BeanUtils<OaCalenderVo>().copyObj(oaCalender,OaCalenderVo.class);
        oaCalenderVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaCalender.getAttachments(), OaAttachmentVo.class));
        return new Result<OaCalenderVo>().sucess(oaCalenderVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaCalender", notes = "根据条件查询OaCalender列表数据")
    public Result<List<OaCalenderVo>> list(@Valid @RequestBody @ApiParam(name="oaCalenderQueryForm",value="OaCalender查询参数",required=true) OaCalenderQueryForm oaCalenderQueryForm) {
        log.info("search with oaCalenderQueryForm:", oaCalenderQueryForm.toString());
        List<OaCalender> oaCalenderList=oaCalenderService.selectList(oaCalenderQueryForm);
        List<OaCalenderVo> oaCalenderVoList=new BeanUtils<OaCalenderVo>().copyObjs(oaCalenderList,OaCalenderVo.class);
        return new Result<List<OaCalenderVo>>().sucess(oaCalenderVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaCalender", notes = "根据条件查询OaCalender分页数据")
    public Result<IPage<OaCalenderVo>> search(@Valid @RequestBody @ApiParam(name="oaCalenderQueryForm",value="OaCalender查询参数",required=true) OaCalenderQueryForm oaCalenderQueryForm) {
        log.info("search with oaCalenderQueryForm:", oaCalenderQueryForm.toString());
        IPage<OaCalender> oaCalenderPage=oaCalenderService.selectPage(oaCalenderQueryForm);
        IPage<OaCalenderVo> oaCalenderVoPage=new BeanUtils<OaCalenderVo>().copyPageObjs(oaCalenderPage,OaCalenderVo.class);
        return new Result<IPage<OaCalenderVo>>().sucess(oaCalenderVoPage);
    }

    @Override
    public Result<List<OaCalenderVo>> home(Integer num) {
        log.info("search oaCalender home list");
        List<OaCalender> oaCalenderList=oaCalenderService.getHomeList(num);
        List<OaCalenderVo> oaCalenderVoList=new BeanUtils<OaCalenderVo>().copyObjs(oaCalenderList,OaCalenderVo.class);
        return new Result<List<OaCalenderVo>>().sucess(oaCalenderVoList);
    }

}



