package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaNoticeClient;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeBpmProcessTaskForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeVo;
import com.deloitte.platform.api.oaservice.noticeper.vo.OaNoticePermissionVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.service.IOaNoticeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :   OaNotice控制器实现类
 * @Modified :
 */
@Api(tags = "OaNotice操作接口")
@Slf4j
@RestController
public class OaNoticeController implements OaNoticeClient {

    @Autowired
    public IOaNoticeService oaNoticeService;

    @Override
    @ApiOperation(value = "新增OaNotice", notes = "新增一个OaNotice")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaNoticeForm",value="新增OaNotice的form表单",required=true) OaNoticeForm oaNoticeForm) {
        log.info("form:",  oaNoticeForm.toString());
        return oaNoticeService.save(oaNoticeForm);
    }

    @Override
    public Result getNextNodeParamVos(@Valid @RequestBody @ApiParam(name = "processVariables", value = "下一个流程审批节点参数") Map<String, Object> processVariables) {
        log.info("processVariables", processVariables.toString());
        return oaNoticeService.getNextNodeParamVos(processVariables);
    }

    @Override
    public Result submit(@Valid @RequestBody OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
        log.info("form: ", bpmProcessTaskForm.toString());
        return oaNoticeService.submit(bpmProcessTaskForm);
    }

    @Override
    public Result reSubmit(@Valid @RequestBody OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
        log.info("form: ", bpmProcessTaskForm.toString());
        return oaNoticeService.reSubmit(bpmProcessTaskForm);
    }

    @Override
    public Result reply(@Valid @RequestBody OaNoticeBpmProcessTaskForm bpmProcessTaskForm) {
        log.info("form: ", bpmProcessTaskForm.toString());
        return oaNoticeService.reply(bpmProcessTaskForm);
    }

    @Override
    public Result submitStart(@Valid @RequestBody @ApiParam(name="oaNoticeForm",value="新增OaNotice的form表单",required=true) OaNoticeForm oaNoticeForm
        ,@RequestHeader(value = "token")String token) {
        log.info("form:",  oaNoticeForm.toString());
        return Result.success(oaNoticeService.submitStart(oaNoticeForm, token));
    }

    @Override
    @ApiOperation(value = "删除OaNotice", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaNoticeID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaNoticeService.removeById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "修改OaNotice", notes = "修改指定OaNotice信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaNotice的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaNoticeForm",value="修改OaNotice的form表单",required=true)  OaNoticeForm oaNoticeForm) {
//        OaNotice oaNotice =new BeanUtils<OaNotice>().copyObj(oaNoticeForm,OaNotice.class);
//        oaNotice.setId(id);
//        oaNoticeService.saveOrUpdate(oaNotice);
        return oaNoticeService.update(id, oaNoticeForm);
    }


    @Override
    @ApiOperation(value = "获取OaNotice", notes = "获取指定ID的OaNotice信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaNotice的ID", required = true, dataType = "long")
    public Result<OaNoticeVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaNotice oaNotice=oaNoticeService.getById(id);
        OaNoticeVo oaNoticeVo=new BeanUtils<OaNoticeVo>().copyObj(oaNotice,OaNoticeVo.class);
        oaNoticeVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaNotice.getAttachments(), OaAttachmentVo.class));
        oaNoticeVo.setPermissionDepts(new BeanUtils<OaNoticePermissionVo>().copyObjs(oaNotice.getPermissionDepts(), OaNoticePermissionVo.class));
        return new Result<OaNoticeVo>().sucess(oaNoticeVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaNotice", notes = "s根据条件查询OaNotice列表数据")
    public Result<List<OaNoticeVo>> list(@Valid @RequestBody @ApiParam(name="oaNoticeQueryForm",value="OaNotice查询参数",required=true) OaNoticeQueryForm oaNoticeQueryForm) {
        log.info("search with oaNoticeQueryForm:", oaNoticeQueryForm.toString());
        List<OaNotice> oaNoticeList=oaNoticeService.selectList(oaNoticeQueryForm);
        List<OaNoticeVo> oaNoticeVoList=new BeanUtils<OaNoticeVo>().copyObjs(oaNoticeList,OaNoticeVo.class);
        return new Result<List<OaNoticeVo>>().sucess(oaNoticeVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaNotice", notes = "根据条件查询OaNotice分页数据")
    public Result<IPage<OaNoticeVo>> search(@Valid @RequestBody @ApiParam(name="oaNoticeQueryForm",value="OaNotice查询参数",required=true) OaNoticeQueryForm oaNoticeQueryForm,
                                            @RequestHeader(value = "token")String token) {
        log.info("search with oaNoticeQueryForm:", oaNoticeQueryForm.toString());
        IPage<OaNotice> oaNoticePage=oaNoticeService.selectPage(oaNoticeQueryForm, token);
        IPage<OaNoticeVo> oaNoticeVoPage=new BeanUtils<OaNoticeVo>().copyPageObjs(oaNoticePage,OaNoticeVo.class);
        return new Result<IPage<OaNoticeVo>>().sucess(oaNoticeVoPage);
    }

    @Override
    @ApiOperation(value = "首页列表查询OaNotice", notes = "首页查询一定数量的OaNotice列表数据, noticeTypeCode: oa_notice_type_inner 内部公告, oa_notice_type_academy 院校公告")
    public Result<List<OaNoticeVo>> homeList(Integer num, String noticeTypeCode, @RequestHeader(value = "token")String token) {
        List<OaNotice> oaNoticeList=oaNoticeService.getHomeList(num, noticeTypeCode, token);
        List<OaNoticeVo> oaNoticeVoList=new BeanUtils<OaNoticeVo>().copyObjs(oaNoticeList,OaNoticeVo.class);
        return new Result<List<OaNoticeVo>>().sucess(oaNoticeVoList);
    }

    @Override
    @ApiOperation(value = "分页查询OaNotice", notes = "根据权限查询OaNotice分页数据")
    public Result<IPage<OaNoticeVo>> listWithPermission(@Valid @RequestBody @ApiParam(name="oaNoticeQueryForm",value="OaNotice查询参数",required=true) OaNoticeQueryForm oaNoticeQueryForm,
                                                        @RequestHeader(value = "token")String token) {
        log.info("search with oaNoticeQueryForm:", oaNoticeQueryForm.toString());
        IPage<OaNotice> oaNoticePage=oaNoticeService.selectPageWithPermission(oaNoticeQueryForm, token);
        IPage<OaNoticeVo> oaNoticeVoPage=new BeanUtils<OaNoticeVo>().copyPageObjs(oaNoticePage,OaNoticeVo.class);
        return new Result<IPage<OaNoticeVo>>().sucess(oaNoticeVoPage);
    }

    @GetMapping(value = path+"/pics")
    public Result<List<String>> getNoticePics(){
        List<String> imgs = new ArrayList<>();
        imgs.add("http://www.pumc.edu.cn/wp-content/uploads/2018/12/%E4%B8%AD%E5%9B%BD%E5%8C%BB%E9%99%A2%E7%A7%91%E6%8A%80%E9%87%8F%E5%80%BC%EF%BC%88STEM%EF%BC%89%E5%8F%91%E5%B8%83%E4%BC%9A-977x353.jpg");
        imgs.add("http://www.pumc.edu.cn/wp-content/uploads/2019/01/%E5%9B%BE%E7%89%871-2.png");
        imgs.add("http://www.pumc.edu.cn/wp-content/uploads/2019/01/%E5%9B%A2%E6%8B%9C%E4%BC%9A-e1548748140535.jpg");
        return new Result<List<String>>().sucess(imgs);
    }

}



