package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.client.ServiceNumClient;
import com.deloitte.platform.api.project.param.ServiceNumQueryForm;
import com.deloitte.platform.api.project.vo.ServiceNumForm;
import com.deloitte.platform.api.project.vo.ServiceNumVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.entity.ServiceNum;
import com.deloitte.services.project.service.IServiceNumService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :   ServiceNum控制器实现类
 * @Modified :
 */
@Api(tags = "ServiceNum操作接口")
@Slf4j
@RestController
public class ServiceNumController implements ServiceNumClient {

    @Autowired
    public IServiceNumService  serviceNumService;


    @Override
    @ApiOperation(value = "新增ServiceNum", notes = "新增一个ServiceNum")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="serviceNumForm",value="新增ServiceNum的form表单",required=true)  ServiceNumForm serviceNumForm) {
        log.info("form:",  serviceNumForm.toString());
        ServiceNum serviceNum =new BeanUtils<ServiceNum>().copyObj(serviceNumForm,ServiceNum.class);
        return Result.success( serviceNumService.save(serviceNum));
    }


    @Override
    @ApiOperation(value = "删除ServiceNum", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ServiceNumID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        serviceNumService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ServiceNum", notes = "修改指定ServiceNum信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ServiceNum的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="serviceNumForm",value="修改ServiceNum的form表单",required=true)  ServiceNumForm serviceNumForm) {
        ServiceNum serviceNum =new BeanUtils<ServiceNum>().copyObj(serviceNumForm,ServiceNum.class);
        serviceNum.setId(id);
        serviceNumService.saveOrUpdate(serviceNum);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ServiceNum", notes = "获取指定ID的ServiceNum信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ServiceNum的ID", required = true, dataType = "long")
    public Result<ServiceNumVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ServiceNum serviceNum=serviceNumService.getById(id);
        ServiceNumVo serviceNumVo=new BeanUtils<ServiceNumVo>().copyObj(serviceNum,ServiceNumVo.class);
        return new Result<ServiceNumVo>().sucess(serviceNumVo);
    }

    @Override
    @ApiOperation(value = "列表查询ServiceNum", notes = "根据条件查询ServiceNum列表数据")
    public Result<List<ServiceNumVo>> list(@Valid @RequestBody @ApiParam(name="serviceNumQueryForm",value="ServiceNum查询参数",required=true) ServiceNumQueryForm serviceNumQueryForm) {
        log.info("search with serviceNumQueryForm:", serviceNumQueryForm.toString());
        List<ServiceNum> serviceNumList=serviceNumService.selectList(serviceNumQueryForm);
        List<ServiceNumVo> serviceNumVoList=new BeanUtils<ServiceNumVo>().copyObjs(serviceNumList,ServiceNumVo.class);
        return new Result<List<ServiceNumVo>>().sucess(serviceNumVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ServiceNum", notes = "根据条件查询ServiceNum分页数据")
    public Result<IPage<ServiceNumVo>> search(@Valid @RequestBody @ApiParam(name="serviceNumQueryForm",value="ServiceNum查询参数",required=true) ServiceNumQueryForm serviceNumQueryForm) {
        log.info("search with serviceNumQueryForm:", serviceNumQueryForm.toString());
        IPage<ServiceNum> serviceNumPage=serviceNumService.selectPage(serviceNumQueryForm);
        IPage<ServiceNumVo> serviceNumVoPage=new BeanUtils<ServiceNumVo>().copyPageObjs(serviceNumPage,ServiceNumVo.class);
        return new Result<IPage<ServiceNumVo>>().sucess(serviceNumVoPage);
    }

    @ApiOperation(value = "生成业务编码单号", notes = "根据业务编码生成对应的业务单号")
    @ApiImplicitParam(paramType = "path", name = "businessCode", value = "businessCode", required = true, dataType = "String")
    @GetMapping(value = "/generalBusinessNO/{businessCode}")
    public Result generalBusinessNO(@PathVariable String businessCode) {
        log.info("businessCode: {}", businessCode);
        return Result.success(serviceNumService.generalBusinessNO(businessCode));
    }
}



