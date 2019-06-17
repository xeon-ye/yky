package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.MonitorInfoQueryForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoVo;
import com.deloitte.platform.api.contract.client.MonitorInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IMonitorInfoService;
import com.deloitte.services.contract.entity.MonitorInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   MonitorInfo控制器实现类
 * @Modified :
 */
@Api(tags = "合同履行-其他监控操作接口")
@Slf4j
@RestController
public class MonitorInfoController implements MonitorInfoClient {

    @Autowired
    public IMonitorInfoService  monitorInfoService;


    @Override
    @ApiOperation(value = "新增MonitorInfo", notes = "新增一个MonitorInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="monitorInfoForm",value="新增MonitorInfo的form表单",required=true)  MonitorInfoForm monitorInfoForm) {
        log.info("form:",  monitorInfoForm.toString());
        MonitorInfo monitorInfo =new BeanUtils<MonitorInfo>().copyObj(monitorInfoForm,MonitorInfo.class);
        return Result.success( monitorInfoService.save(monitorInfo));
    }


    @Override
    @ApiOperation(value = "删除MonitorInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MonitorInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        monitorInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改MonitorInfo", notes = "修改指定MonitorInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "MonitorInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="monitorInfoForm",value="修改MonitorInfo的form表单",required=true)  MonitorInfoForm monitorInfoForm) {
        MonitorInfo monitorInfo =new BeanUtils<MonitorInfo>().copyObj(monitorInfoForm,MonitorInfo.class);
        monitorInfo.setId(id);
        monitorInfoService.saveOrUpdate(monitorInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取MonitorInfo", notes = "获取指定ID的MonitorInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MonitorInfo的ID", required = true, dataType = "long")
    public Result<MonitorInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        MonitorInfo monitorInfo=monitorInfoService.getById(id);
        MonitorInfoVo monitorInfoVo=new BeanUtils<MonitorInfoVo>().copyObj(monitorInfo,MonitorInfoVo.class);
        return new Result<MonitorInfoVo>().sucess(monitorInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询MonitorInfo", notes = "根据条件查询MonitorInfo列表数据")
    public Result<List<MonitorInfoVo>> list(@Valid @RequestBody @ApiParam(name="monitorInfoQueryForm",value="MonitorInfo查询参数",required=true) MonitorInfoQueryForm monitorInfoQueryForm) {
        log.info("search with monitorInfoQueryForm:", monitorInfoQueryForm.toString());
        List<MonitorInfo> monitorInfoList=monitorInfoService.selectList(monitorInfoQueryForm);
        List<MonitorInfoVo> monitorInfoVoList=new BeanUtils<MonitorInfoVo>().copyObjs(monitorInfoList,MonitorInfoVo.class);
        return new Result<List<MonitorInfoVo>>().sucess(monitorInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询MonitorInfo", notes = "根据条件查询MonitorInfo分页数据")
    public Result<IPage<MonitorInfoVo>> search(@Valid @RequestBody @ApiParam(name="monitorInfoQueryForm",value="MonitorInfo查询参数",required=true) MonitorInfoQueryForm monitorInfoQueryForm) {
        log.info("search with monitorInfoQueryForm:", monitorInfoQueryForm.toString());
        IPage<MonitorInfo> monitorInfoPage=monitorInfoService.selectPage(monitorInfoQueryForm);
        IPage<MonitorInfoVo> monitorInfoVoPage=new BeanUtils<MonitorInfoVo>().copyPageObjs(monitorInfoPage,MonitorInfoVo.class);
        return new Result<IPage<MonitorInfoVo>>().sucess(monitorInfoVoPage);
    }

}



