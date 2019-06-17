package com.deloitte.services.notice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.notice.client.OaMeetingRecordClient;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingRecordQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaMeetingRecord;
import com.deloitte.services.notice.service.IOaMeetingRecordService;
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
 * @Description :   OaMeetingRecord控制器实现类
 * @Modified :
 */
@Api(tags = "OaMeetingRecord操作接口")
@Slf4j
@RestController
public class OaMeetingRecordController implements OaMeetingRecordClient {

    @Autowired
    public IOaMeetingRecordService oaMeetingRecordService;


    @Override
    @ApiOperation(value = "新增OaMeetingRecord", notes = "新增一个OaMeetingRecord")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMeetingRecordForm",value="新增OaMeetingRecord的form表单",required=true)  OaMeetingRecordForm oaMeetingRecordForm) {
        log.info("form:",  oaMeetingRecordForm.toString());
        return oaMeetingRecordService.save(oaMeetingRecordForm);
    }


    @Override
    @ApiOperation(value = "删除OaMeetingRecord", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingRecordID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMeetingRecordService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMeetingRecord", notes = "修改指定OaMeetingRecord信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMeetingRecord的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMeetingRecordForm",value="修改OaMeetingRecord的form表单",required=true) OaMeetingRecordForm oaMeetingRecordForm) {
//        OaMeetingRecord oaMeetingRecord =new BeanUtils<OaMeetingRecord>().copyObj(oaMeetingRecordForm,OaMeetingRecord.class);
//        oaMeetingRecord.setId(id);
//        oaMeetingRecordService.saveOrUpdate(oaMeetingRecord);
        return oaMeetingRecordService.update(id, oaMeetingRecordForm);
    }


    @Override
    @ApiOperation(value = "获取OaMeetingRecord", notes = "获取指定ID的OaMeetingRecord信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingRecord的ID", required = true, dataType = "long")
    public Result<OaMeetingRecordVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaMeetingRecord oaMeetingRecord=oaMeetingRecordService.getById(id);
        OaMeetingRecordVo oaMeetingRecordVo=new BeanUtils<OaMeetingRecordVo>().copyObj(oaMeetingRecord,OaMeetingRecordVo.class);
        oaMeetingRecordVo.setAttachments(new BeanUtils<OaAttachmentVo>().copyObjs(oaMeetingRecord.getAttachments(), OaAttachmentVo.class));
        return new Result<OaMeetingRecordVo>().sucess(oaMeetingRecordVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaMeetingRecord", notes = "根据条件查询OaMeetingRecord列表数据")
    public Result<List<OaMeetingRecordVo>> list(@Valid @RequestBody @ApiParam(name="oaMeetingRecordQueryForm",value="OaMeetingRecord查询参数",required=true) OaMeetingRecordQueryForm oaMeetingRecordQueryForm) {
        log.info("search with oaMeetingRecordQueryForm:", oaMeetingRecordQueryForm.toString());
        List<OaMeetingRecord> oaMeetingRecordList=oaMeetingRecordService.selectList(oaMeetingRecordQueryForm);
        List<OaMeetingRecordVo> oaMeetingRecordVoList=new BeanUtils<OaMeetingRecordVo>().copyObjs(oaMeetingRecordList,OaMeetingRecordVo.class);
        return new Result<List<OaMeetingRecordVo>>().sucess(oaMeetingRecordVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMeetingRecord", notes = "根据条件查询OaMeetingRecord分页数据")
    public Result<IPage<OaMeetingRecordVo>> search(@Valid @RequestBody @ApiParam(name="oaMeetingRecordQueryForm",value="OaMeetingRecord查询参数",required=true) OaMeetingRecordQueryForm oaMeetingRecordQueryForm) {
        log.info("search with oaMeetingRecordQueryForm:", oaMeetingRecordQueryForm.toString());
        IPage<OaMeetingRecord> oaMeetingRecordPage=oaMeetingRecordService.selectPage(oaMeetingRecordQueryForm);
        IPage<OaMeetingRecordVo> oaMeetingRecordVoPage=new BeanUtils<OaMeetingRecordVo>().copyPageObjs(oaMeetingRecordPage,OaMeetingRecordVo.class);
        return new Result<IPage<OaMeetingRecordVo>>().sucess(oaMeetingRecordVoPage);
    }

    @Override
    public Result<List<OaMeetingRecordVo>> home(Integer num) {
        log.info("search oaMeetingRecord home list:");
        List<OaMeetingRecord> oaMeetingRecordList=oaMeetingRecordService.getHomeList(num);
        List<OaMeetingRecordVo> oaMeetingRecordVoList=new BeanUtils<OaMeetingRecordVo>().copyObjs(oaMeetingRecordList,OaMeetingRecordVo.class);
        return new Result<List<OaMeetingRecordVo>>().sucess(oaMeetingRecordVoList);
    }

}



