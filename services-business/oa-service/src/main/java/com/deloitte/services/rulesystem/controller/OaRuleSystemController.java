package com.deloitte.services.rulesystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.noticeper.vo.OaNoticePermissionVo;
import com.deloitte.platform.api.oaservice.rulesystem.client.OaRuleSystemClient;
import com.deloitte.platform.api.oaservice.rulesystem.param.OaRuleSystemQueryForm;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemForm;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;
import com.deloitte.services.rulesystem.service.IOaRuleSystemService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :   OaRuleSystem控制器实现类
 * @Modified :
 */
@Api(tags = "OaRuleSystem操作接口")
@Slf4j
@RestController
public class OaRuleSystemController implements OaRuleSystemClient {

    @Autowired
    public IOaRuleSystemService oaRuleSystemService;

    @Override
    @ApiOperation(value = "新增OaRuleSystem", notes = "新增一个OaRuleSystem")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaRuleSystemForm",value="新增OaRuleSystem的form表单",required=true) OaRuleSystemForm oaRuleSystemForm) {
        log.info("form:",  oaRuleSystemForm.toString());
        return oaRuleSystemService.save(oaRuleSystemForm);
    }


    @Override
    @ApiOperation(value = "删除OaRuleSystem", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaRuleSystemID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaRuleSystemService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaRuleSystem", notes = "修改指定OaRuleSystem信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaRuleSystem的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaRuleSystemForm",value="修改OaRuleSystem的form表单",required=true)  OaRuleSystemForm oaRuleSystemForm) {
        // OaRuleSystem oaRuleSystem =new BeanUtils<OaRuleSystem>().copyObj(oaRuleSystemForm,OaRuleSystem.class);
        // oaRuleSystem.setId(id);
        // oaRuleSystemService.saveOrUpdate(oaRuleSystem);
        return oaRuleSystemService.update(id, oaRuleSystemForm);
    }


    @Override
    @ApiOperation(value = "获取OaRuleSystem", notes = "获取指定ID的OaRuleSystem信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaRuleSystem的ID", required = true, dataType = "long")
    public Result<OaRuleSystemVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaRuleSystem oaRuleSystem=oaRuleSystemService.getById(id);
        OaRuleSystemVo oaRuleSystemVo=new BeanUtils<OaRuleSystemVo>().copyObj(oaRuleSystem, OaRuleSystemVo.class);
        oaRuleSystemVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaRuleSystem.getAttachments(), OaAttachmentVo.class));
        oaRuleSystemVo.setPermissionDepts(new BeanUtils<OaNoticePermissionVo>().copyObjs(oaRuleSystem.getPermissionDepts(), OaNoticePermissionVo.class));
        return new Result<OaRuleSystemVo>().sucess(oaRuleSystemVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaRuleSystem", notes = "根据条件查询OaRuleSystem列表数据")
    public Result<List<OaRuleSystemVo>> list(@Valid @RequestBody @ApiParam(name="oaRuleSystemQueryForm",value="OaRuleSystem查询参数",required=true) OaRuleSystemQueryForm oaRuleSystemQueryForm) {
        log.info("search with oaRuleSystemQueryForm:", oaRuleSystemQueryForm.toString());
        List<OaRuleSystem> oaRuleSystemList=oaRuleSystemService.selectList(oaRuleSystemQueryForm);
        List<OaRuleSystemVo> oaRuleSystemVoList=new BeanUtils<OaRuleSystemVo>().copyObjs(oaRuleSystemList,OaRuleSystemVo.class);
        return new Result<List<OaRuleSystemVo>>().sucess(oaRuleSystemVoList);
    }

    @Override
    @ApiOperation(value = "分页查询OaRuleSystem", notes = "根据条件查询OaRuleSystem分页数据")
    public Result<IPage<OaRuleSystemVo>> search(@Valid @RequestBody @ApiParam(name="oaRuleSystemQueryForm",value="OaRuleSystem查询参数",required=true) OaRuleSystemQueryForm oaRuleSystemQueryForm,
                                                @RequestHeader(value = "token") String token) {
        log.info("search with oaRuleSystemQueryForm:", oaRuleSystemQueryForm.toString());
        IPage<OaRuleSystem> oaRuleSystemPage=oaRuleSystemService.selectPage(oaRuleSystemQueryForm, token);
        IPage<OaRuleSystemVo> oaRuleSystemVoPage=new BeanUtils<OaRuleSystemVo>().copyPageObjs(oaRuleSystemPage,OaRuleSystemVo.class);
        return new Result<IPage<OaRuleSystemVo>>().sucess(oaRuleSystemVoPage);
    }

    @Override
    public Result<IPage<OaRuleSystemVo>> searchWithPermission(@Valid @RequestBody @ApiParam(name="oaRuleSystemQueryForm",value="OaRuleSystem查询参数",required=true) OaRuleSystemQueryForm oaRuleSystemQueryForm,
                                                              @RequestHeader(value = "token") String token) {
        log.info("search with oaRuleSystemQueryForm:", oaRuleSystemQueryForm.toString());
        IPage<OaRuleSystem> oaRuleSystemPage=oaRuleSystemService.selectPageWithPermission(oaRuleSystemQueryForm, token);
        IPage<OaRuleSystemVo> oaRuleSystemVoPage=new BeanUtils<OaRuleSystemVo>().copyPageObjs(oaRuleSystemPage,OaRuleSystemVo.class);
        return new Result<IPage<OaRuleSystemVo>>().sucess(oaRuleSystemVoPage);
    }

    @Override
    @ApiOperation(value = "查询首页OaRuleSystem", notes = "首页查询一定数量的OaRuleSystem数据")
    public Result<List<OaRuleSystemVo>> homeList(Integer num, @RequestHeader(value = "token") String token) {
        List<OaRuleSystem> oaRuleSystemList=oaRuleSystemService.getHomeList(num, token);
        List<OaRuleSystemVo> oaRuleSystemVoList=new BeanUtils<OaRuleSystemVo>().copyObjs(oaRuleSystemList,OaRuleSystemVo.class);
        return new Result<List<OaRuleSystemVo>>().sucess(oaRuleSystemVoList);
    }

}



