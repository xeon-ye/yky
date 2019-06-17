package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.TicketInfoQueryForm;
import com.deloitte.platform.api.contract.vo.TicketInfoForm;
import com.deloitte.platform.api.contract.vo.TicketInfoVo;
import com.deloitte.platform.api.contract.client.TicketInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ITicketInfoService;
import com.deloitte.services.contract.entity.TicketInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   TicketInfo控制器实现类
 * @Modified :
 */
@Api(tags = "履行中发票信息操作接口")
@Slf4j
@RestController
public class TicketInfoController implements TicketInfoClient {

    @Autowired
    public ITicketInfoService  ticketInfoService;


    @Override
    @ApiOperation(value = "新增TicketInfo", notes = "新增一个TicketInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="ticketInfoForm",value="新增TicketInfo的form表单",required=true)  TicketInfoForm ticketInfoForm) {
        log.info("form:",  ticketInfoForm.toString());
        TicketInfo ticketInfo =new BeanUtils<TicketInfo>().copyObj(ticketInfoForm,TicketInfo.class);
        return Result.success( ticketInfoService.save(ticketInfo));
    }


    @Override
    @ApiOperation(value = "删除TicketInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "TicketInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        ticketInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改TicketInfo", notes = "修改指定TicketInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "TicketInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="ticketInfoForm",value="修改TicketInfo的form表单",required=true)  TicketInfoForm ticketInfoForm) {
        TicketInfo ticketInfo =new BeanUtils<TicketInfo>().copyObj(ticketInfoForm,TicketInfo.class);
        ticketInfo.setId(id);
        ticketInfoService.saveOrUpdate(ticketInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取TicketInfo", notes = "获取指定ID的TicketInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "TicketInfo的ID", required = true, dataType = "long")
    public Result<TicketInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        TicketInfo ticketInfo=ticketInfoService.getById(id);
        TicketInfoVo ticketInfoVo=new BeanUtils<TicketInfoVo>().copyObj(ticketInfo,TicketInfoVo.class);
        return new Result<TicketInfoVo>().sucess(ticketInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询TicketInfo", notes = "根据条件查询TicketInfo列表数据")
    public Result<List<TicketInfoVo>> list(@Valid @RequestBody @ApiParam(name="ticketInfoQueryForm",value="TicketInfo查询参数",required=true) TicketInfoQueryForm ticketInfoQueryForm) {
        log.info("search with ticketInfoQueryForm:", ticketInfoQueryForm.toString());
        List<TicketInfo> ticketInfoList=ticketInfoService.selectList(ticketInfoQueryForm);
        List<TicketInfoVo> ticketInfoVoList=new BeanUtils<TicketInfoVo>().copyObjs(ticketInfoList,TicketInfoVo.class);
        return new Result<List<TicketInfoVo>>().sucess(ticketInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询TicketInfo", notes = "根据条件查询TicketInfo分页数据")
    public Result<IPage<TicketInfoVo>> search(@Valid @RequestBody @ApiParam(name="ticketInfoQueryForm",value="TicketInfo查询参数",required=true) TicketInfoQueryForm ticketInfoQueryForm) {
        log.info("search with ticketInfoQueryForm:", ticketInfoQueryForm.toString());
        IPage<TicketInfo> ticketInfoPage=ticketInfoService.selectPage(ticketInfoQueryForm);
        IPage<TicketInfoVo> ticketInfoVoPage=new BeanUtils<TicketInfoVo>().copyPageObjs(ticketInfoPage,TicketInfoVo.class);
        return new Result<IPage<TicketInfoVo>>().sucess(ticketInfoVoPage);
    }

}



