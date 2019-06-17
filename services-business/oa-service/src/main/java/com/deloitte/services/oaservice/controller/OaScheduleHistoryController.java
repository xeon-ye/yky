package com.deloitte.services.oaservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.param.OaScheduleHistoryQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleHistoryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleHistoryVo;
import com.deloitte.platform.api.oaservice.client.OaScheduleHistoryClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.oaservice.service.IOaScheduleHistoryService;
import com.deloitte.services.oaservice.entity.OaScheduleHistory;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :   OaScheduleHistory控制器实现类
 * @Modified :
 */
@Api(tags = "OaScheduleHistory操作接口")
@Slf4j
@RestController
public class OaScheduleHistoryController implements OaScheduleHistoryClient {

    @Autowired
    public IOaScheduleHistoryService  oaScheduleHistoryService;


    @Override
    @ApiOperation(value = "新增OaScheduleHistory", notes = "新增一个OaScheduleHistory")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaScheduleHistoryForm",value="新增OaScheduleHistory的form表单",required=true)  OaScheduleHistoryForm oaScheduleHistoryForm) {
        log.info("form:",  oaScheduleHistoryForm.toString());
        OaScheduleHistory oaScheduleHistory =new BeanUtils<OaScheduleHistory>().copyObj(oaScheduleHistoryForm,OaScheduleHistory.class);
        return Result.success( oaScheduleHistoryService.save(oaScheduleHistory));
    }


    @Override
    @ApiOperation(value = "删除OaScheduleHistory", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaScheduleHistoryID", required = true, dataType = "string")
    public Result delete(@PathVariable String id) {
        oaScheduleHistoryService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaScheduleHistory", notes = "修改指定OaScheduleHistory信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaScheduleHistory的ID", required = true, dataType = "string")
    public Result update(@PathVariable String id,
                         @Valid @RequestBody @ApiParam(name="oaScheduleHistoryForm",value="修改OaScheduleHistory的form表单",required=true)  OaScheduleHistoryForm oaScheduleHistoryForm) {
        OaScheduleHistory oaScheduleHistory =new BeanUtils<OaScheduleHistory>().copyObj(oaScheduleHistoryForm,OaScheduleHistory.class);
        long pk = -1l;
        if(id!=null&&!"".equals(id)){
            pk = Long.valueOf(id);
        }else{
            return Result.fail("无法获取到对应的id信息");
        }
        oaScheduleHistory.setId(pk);
        oaScheduleHistoryService.saveOrUpdate(oaScheduleHistory);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaScheduleHistory", notes = "获取指定ID的OaScheduleHistory信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaScheduleHistory的ID", required = true, dataType = "string")
    public Result<OaScheduleHistoryVo> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        OaScheduleHistory oaScheduleHistory=oaScheduleHistoryService.getById(id);
        OaScheduleHistoryVo oaScheduleHistoryVo=new BeanUtils<OaScheduleHistoryVo>().copyObj(oaScheduleHistory,OaScheduleHistoryVo.class);
        return new Result<OaScheduleHistoryVo>().sucess(oaScheduleHistoryVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaScheduleHistory", notes = "根据条件查询OaScheduleHistory列表数据")
    public Result<List<OaScheduleHistoryVo>> list(@Valid @RequestBody @ApiParam(name="oaScheduleHistoryQueryForm",value="OaScheduleHistory查询参数",required=true) OaScheduleHistoryQueryForm oaScheduleHistoryQueryForm) {
        log.info("search with oaScheduleHistoryQueryForm:", oaScheduleHistoryQueryForm.toString());
        List<OaScheduleHistory> oaScheduleHistoryList=oaScheduleHistoryService.selectList(oaScheduleHistoryQueryForm);
        List<OaScheduleHistoryVo> oaScheduleHistoryVoList=new BeanUtils<OaScheduleHistoryVo>().copyObjs(oaScheduleHistoryList,OaScheduleHistoryVo.class);
        return new Result<List<OaScheduleHistoryVo>>().sucess(oaScheduleHistoryVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaScheduleHistory", notes = "根据条件查询OaScheduleHistory分页数据")
    public Result<IPage<OaScheduleHistoryVo>> search(@Valid @RequestBody @ApiParam(name="oaScheduleHistoryQueryForm",value="OaScheduleHistory查询参数",required=true) OaScheduleHistoryQueryForm oaScheduleHistoryQueryForm) {
        log.info("search with oaScheduleHistoryQueryForm:", oaScheduleHistoryQueryForm.toString());
        IPage<OaScheduleHistory> oaScheduleHistoryPage=oaScheduleHistoryService.selectPage(oaScheduleHistoryQueryForm);
        IPage<OaScheduleHistoryVo> oaScheduleHistoryVoPage=new BeanUtils<OaScheduleHistoryVo>().copyPageObjs(oaScheduleHistoryPage,OaScheduleHistoryVo.class);
        return new Result<IPage<OaScheduleHistoryVo>>().sucess(oaScheduleHistoryVoPage);
    }

}



