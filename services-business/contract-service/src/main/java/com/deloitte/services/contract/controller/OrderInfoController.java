package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.OrderInfoQueryForm;
import com.deloitte.platform.api.contract.vo.OrderInfoForm;
import com.deloitte.platform.api.contract.vo.OrderInfoVo;
import com.deloitte.platform.api.contract.client.OrderInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IOrderInfoService;
import com.deloitte.services.contract.entity.OrderInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   OrderInfo控制器实现类
 * @Modified :
 */
@Api(tags = "合同履行-订单操作接口")
@Slf4j
@RestController
public class OrderInfoController implements OrderInfoClient {

    @Autowired
    public IOrderInfoService  orderInfoService;


    @Override
    @ApiOperation(value = "新增OrderInfo", notes = "新增一个OrderInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="orderInfoForm",value="新增OrderInfo的form表单",required=true)  OrderInfoForm orderInfoForm) {
        log.info("form:",  orderInfoForm.toString());
        OrderInfo orderInfo =new BeanUtils<OrderInfo>().copyObj(orderInfoForm,OrderInfo.class);
        return Result.success( orderInfoService.save(orderInfo));
    }


    @Override
    @ApiOperation(value = "删除OrderInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OrderInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        orderInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OrderInfo", notes = "修改指定OrderInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OrderInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="orderInfoForm",value="修改OrderInfo的form表单",required=true)  OrderInfoForm orderInfoForm) {
        OrderInfo orderInfo =new BeanUtils<OrderInfo>().copyObj(orderInfoForm,OrderInfo.class);
        orderInfo.setId(id);
        orderInfoService.saveOrUpdate(orderInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OrderInfo", notes = "获取指定ID的OrderInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OrderInfo的ID", required = true, dataType = "long")
    public Result<OrderInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OrderInfo orderInfo=orderInfoService.getById(id);
        OrderInfoVo orderInfoVo=new BeanUtils<OrderInfoVo>().copyObj(orderInfo,OrderInfoVo.class);
        return new Result<OrderInfoVo>().sucess(orderInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询OrderInfo", notes = "根据条件查询OrderInfo列表数据")
    public Result<List<OrderInfoVo>> list(@Valid @RequestBody @ApiParam(name="orderInfoQueryForm",value="OrderInfo查询参数",required=true) OrderInfoQueryForm orderInfoQueryForm) {
        log.info("search with orderInfoQueryForm:", orderInfoQueryForm.toString());
        List<OrderInfo> orderInfoList=orderInfoService.selectList(orderInfoQueryForm);
        List<OrderInfoVo> orderInfoVoList=new BeanUtils<OrderInfoVo>().copyObjs(orderInfoList,OrderInfoVo.class);
        return new Result<List<OrderInfoVo>>().sucess(orderInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OrderInfo", notes = "根据条件查询OrderInfo分页数据")
    public Result<IPage<OrderInfoVo>> search(@Valid @RequestBody @ApiParam(name="orderInfoQueryForm",value="OrderInfo查询参数",required=true) OrderInfoQueryForm orderInfoQueryForm) {
        log.info("search with orderInfoQueryForm:", orderInfoQueryForm.toString());
        IPage<OrderInfo> orderInfoPage=orderInfoService.selectPage(orderInfoQueryForm);
        IPage<OrderInfoVo> orderInfoVoPage=new BeanUtils<OrderInfoVo>().copyPageObjs(orderInfoPage,OrderInfoVo.class);
        return new Result<IPage<OrderInfoVo>>().sucess(orderInfoVoPage);
    }

}



