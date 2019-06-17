package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaNoticeOtherClient;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherVo;
import com.deloitte.platform.api.oaservice.noticeper.vo.OaNoticePermissionVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.attachment.service.IOaAttachmentService;
import com.deloitte.services.notice.entity.OaNoticeOther;
import com.deloitte.services.notice.service.IOaNoticeOtherService;
import com.deloitte.services.noticeper.entity.OaNoticePermission;
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
 * @Description :   OaNoticeOther控制器实现类
 * @Modified :
 */
@Api(tags = "OaNoticeOther操作接口")
@Slf4j
@RestController
public class OaNoticeOtherController implements OaNoticeOtherClient {

    @Autowired
    public IOaNoticeOtherService oaNoticeOtherService;

    @Override
    @ApiOperation(value = "新增OaNoticeOther", notes = "新增一个OaNoticeOther")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaNoticeOtherForm",value="新增OaNoticeOther的form表单",required=true) OaNoticeOtherForm oaNoticeOtherForm) {
        log.info("form:",  oaNoticeOtherForm.toString());
        return oaNoticeOtherService.save(oaNoticeOtherForm);
    }


    @Override
    @ApiOperation(value = "删除OaNoticeOther", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaNoticeOtherID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaNoticeOtherService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaNoticeOther", notes = "修改指定OaNoticeOther信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaNoticeOther的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaNoticeOtherForm",value="修改OaNoticeOther的form表单",required=true)  OaNoticeOtherForm oaNoticeOtherForm) {
//        OaNoticeOther oaNoticeOther =new BeanUtils<OaNoticeOther>().copyObj(oaNoticeOtherForm,OaNoticeOther.class);
//        oaNoticeOther.setId(id);
//        oaNoticeOtherService.saveOrUpdate(oaNoticeOther);
        return oaNoticeOtherService.update(id, oaNoticeOtherForm);
    }

    @Override
    @ApiOperation(value = "获取OaNoticeOther", notes = "获取指定ID的OaNoticeOther信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaNoticeOther的ID", required = true, dataType = "long")
    public Result<OaNoticeOtherVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaNoticeOther oaNoticeOther=oaNoticeOtherService.getById(id);
        OaNoticeOtherVo oaNoticeOtherVo=new BeanUtils<OaNoticeOtherVo>().copyObj(oaNoticeOther,OaNoticeOtherVo.class);
        oaNoticeOtherVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaNoticeOther.getAttachments(), OaAttachmentVo.class));
        oaNoticeOtherVo.setPermissionDepts(new BeanUtils<OaNoticePermissionVo>().copyObjs(oaNoticeOther.getPermissionDepts(), OaNoticePermissionVo.class));
        return new Result<OaNoticeOtherVo>().sucess(oaNoticeOtherVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaNoticeOther", notes = "根据条件查询OaNoticeOther列表数据")
    public Result<List<OaNoticeOtherVo>> list(@Valid @RequestBody @ApiParam(name="oaNoticeOtherQueryForm",value="OaNoticeOther查询参数",required=true) OaNoticeOtherQueryForm oaNoticeOtherQueryForm) {
        log.info("search with oaNoticeOtherQueryForm:", oaNoticeOtherQueryForm.toString());
        List<OaNoticeOther> oaNoticeOtherList=oaNoticeOtherService.selectList(oaNoticeOtherQueryForm);
        List<OaNoticeOtherVo> oaNoticeOtherVoList=new BeanUtils<OaNoticeOtherVo>().copyObjs(oaNoticeOtherList,OaNoticeOtherVo.class);
        return new Result<List<OaNoticeOtherVo>>().sucess(oaNoticeOtherVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaNoticeOther", notes = "根据条件查询OaNoticeOther分页数据")
    public Result<IPage<OaNoticeOtherVo>> search(@Valid @RequestBody @ApiParam(name="oaNoticeOtherQueryForm",value="OaNoticeOther查询参数",required=true) OaNoticeOtherQueryForm oaNoticeOtherQueryForm,
                                                 @RequestHeader(value = "token")String token) {
        log.info("search with oaNoticeOtherQueryForm:", oaNoticeOtherQueryForm.toString());
        IPage<OaNoticeOther> oaNoticeOtherPage=oaNoticeOtherService.selectPage(oaNoticeOtherQueryForm, token);
        IPage<OaNoticeOtherVo> oaNoticeOtherVoPage=new BeanUtils<OaNoticeOtherVo>().copyPageObjs(oaNoticeOtherPage,OaNoticeOtherVo.class);
        return new Result<IPage<OaNoticeOtherVo>>().sucess(oaNoticeOtherVoPage);
    }

    @Override
    public Result<IPage<OaNoticeOtherVo>> searchWithPermission(@Valid @RequestBody @ApiParam(name="oaNoticeOtherQueryForm",value="OaNoticeOther查询参数",required=true) OaNoticeOtherQueryForm oaNoticeOtherQueryForm,
                                                               @RequestHeader(value = "token")String token) {
        log.info("search with oaNoticeOtherQueryForm:", oaNoticeOtherQueryForm.toString());
        IPage<OaNoticeOther> oaNoticeOtherPage=oaNoticeOtherService.selectPageWithPermission(oaNoticeOtherQueryForm, token);
        IPage<OaNoticeOtherVo> oaNoticeOtherVoPage=new BeanUtils<OaNoticeOtherVo>().copyPageObjs(oaNoticeOtherPage,OaNoticeOtherVo.class);
        return new Result<IPage<OaNoticeOtherVo>>().sucess(oaNoticeOtherVoPage);
    }

    @Override
    @ApiOperation(value = "首页列表查询OaNotice",
            notes = "首页查询一定数量的OaNotice列表数据, typeCode: oa_type_infochange 信息交流, oa_type_meeting_arrge 会议安排" +
                    "oa_type_meeting_record: 会议纪要, oa_type_calender: 校历, oa_type_resource: 资源下载")
    public Result<List<OaNoticeOtherVo>> home(Integer num, String typeCode, @RequestHeader(value = "token")String token) {
        log.info("search oaNoticeOther home list");
        List<OaNoticeOther> oaNoticeOtherList=oaNoticeOtherService.getHomeList(num, typeCode, token);
        List<OaNoticeOtherVo> oaNoticeOtherVoList=new BeanUtils<OaNoticeOtherVo>().copyObjs(oaNoticeOtherList,OaNoticeOtherVo.class);
        return new Result<List<OaNoticeOtherVo>>().sucess(oaNoticeOtherVoList);
    }

}



