package com.deloitte.services.meeting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingAddressQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingAddressForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingAddressVo;
import com.deloitte.platform.api.oaservice.meeting.client.OaMeetingAddressClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.meeting.service.IOaMeetingAddressService;
import com.deloitte.services.meeting.entity.OaMeetingAddress;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingAddress控制器实现类
 * @Modified :
 */
@Api(tags = "OaMeetingAddress操作接口")
@Slf4j
@RestController
public class OaMeetingAddressController implements OaMeetingAddressClient {

    @Autowired
    public IOaMeetingAddressService  oaMeetingAddressService;


    @Override
    @ApiOperation(value = "新增OaMeetingAddress", notes = "新增一个OaMeetingAddress")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMeetingAddressForm",value="新增OaMeetingAddress的form表单",required=true)  OaMeetingAddressForm oaMeetingAddressForm) {
        log.info("form:",  oaMeetingAddressForm.toString());
        OaMeetingAddress oaMeetingAddress =new BeanUtils<OaMeetingAddress>().copyObj(oaMeetingAddressForm,OaMeetingAddress.class);
        return Result.success( oaMeetingAddressService.save(oaMeetingAddress));
    }


    @Override
    @ApiOperation(value = "删除OaMeetingAddress", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingAddressID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMeetingAddressService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMeetingAddress", notes = "修改指定OaMeetingAddress信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMeetingAddress的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMeetingAddressForm",value="修改OaMeetingAddress的form表单",required=true)  OaMeetingAddressForm oaMeetingAddressForm) {
        OaMeetingAddress oaMeetingAddress =new BeanUtils<OaMeetingAddress>().copyObj(oaMeetingAddressForm,OaMeetingAddress.class);
        oaMeetingAddress.setId(id);
        oaMeetingAddressService.saveOrUpdate(oaMeetingAddress);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaMeetingAddress", notes = "获取指定ID的OaMeetingAddress信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMeetingAddress的ID", required = true, dataType = "long")
    public Result<OaMeetingAddressVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaMeetingAddress oaMeetingAddress=oaMeetingAddressService.getById(id);
        OaMeetingAddressVo oaMeetingAddressVo=new BeanUtils<OaMeetingAddressVo>().copyObj(oaMeetingAddress,OaMeetingAddressVo.class);
        return new Result<OaMeetingAddressVo>().sucess(oaMeetingAddressVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaMeetingAddress", notes = "根据条件查询OaMeetingAddress列表数据")
    public Result<List<OaMeetingAddressVo>> list(@Valid @RequestBody @ApiParam(name="oaMeetingAddressQueryForm",value="OaMeetingAddress查询参数",required=true) OaMeetingAddressQueryForm oaMeetingAddressQueryForm) {
        log.info("search with oaMeetingAddressQueryForm:", oaMeetingAddressQueryForm.toString());
        List<OaMeetingAddress> oaMeetingAddressList=oaMeetingAddressService.selectList(oaMeetingAddressQueryForm);
        List<OaMeetingAddressVo> oaMeetingAddressVoList=new BeanUtils<OaMeetingAddressVo>().copyObjs(oaMeetingAddressList,OaMeetingAddressVo.class);
        return new Result<List<OaMeetingAddressVo>>().sucess(oaMeetingAddressVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMeetingAddress", notes = "根据条件查询OaMeetingAddress分页数据")
    public Result<IPage<OaMeetingAddressVo>> search(@Valid @RequestBody @ApiParam(name="oaMeetingAddressQueryForm",value="OaMeetingAddress查询参数",required=true) OaMeetingAddressQueryForm oaMeetingAddressQueryForm) {
        log.info("search with oaMeetingAddressQueryForm:", oaMeetingAddressQueryForm.toString());
        IPage<OaMeetingAddress> oaMeetingAddressPage=oaMeetingAddressService.selectPage(oaMeetingAddressQueryForm);
        IPage<OaMeetingAddressVo> oaMeetingAddressVoPage=new BeanUtils<OaMeetingAddressVo>().copyPageObjs(oaMeetingAddressPage,OaMeetingAddressVo.class);
        return new Result<IPage<OaMeetingAddressVo>>().sucess(oaMeetingAddressVoPage);
    }

}



