package com.deloitte.services.fssc.business.carryforward.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.carryforward.param.DssScientificPayQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayVo;
import com.deloitte.platform.api.fssc.carryforward.client.DssScientificPayClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.business.carryforward.service.IDssScientificPayService;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificPay;


/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :   DssScientificPay控制器实现类
 * @Modified :
 */
@Api(tags = "DssScientificPay操作接口")
@Slf4j
@RestController
public class DssScientificPayController implements DssScientificPayClient {

    @Autowired
    public IDssScientificPayService  dssScientificPayService;


    @Override
    @ApiOperation(value = "新增DssScientificPay", notes = "新增一个DssScientificPay")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="dssScientificPayForm",value="新增DssScientificPay的form表单",required=true)  DssScientificPayForm dssScientificPayForm) {
        log.info("form:",  dssScientificPayForm.toString());
        DssScientificPay dssScientificPay =new BeanUtils<DssScientificPay>().copyObj(dssScientificPayForm,DssScientificPay.class);
        return Result.success( dssScientificPayService.save(dssScientificPay));
    }


    @Override
    @ApiOperation(value = "删除DssScientificPay", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DssScientificPayID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        dssScientificPayService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改DssScientificPay", notes = "修改指定DssScientificPay信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "DssScientificPay的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="dssScientificPayForm",value="修改DssScientificPay的form表单",required=true)  DssScientificPayForm dssScientificPayForm) {
        DssScientificPay dssScientificPay =new BeanUtils<DssScientificPay>().copyObj(dssScientificPayForm,DssScientificPay.class);
        dssScientificPay.setId(id);
        dssScientificPayService.saveOrUpdate(dssScientificPay);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取DssScientificPay", notes = "获取指定ID的DssScientificPay信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DssScientificPay的ID", required = true, dataType = "long")
    public Result<DssScientificPayVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        DssScientificPay dssScientificPay=dssScientificPayService.getById(id);
        DssScientificPayVo dssScientificPayVo=new BeanUtils<DssScientificPayVo>().copyObj(dssScientificPay,DssScientificPayVo.class);
        return new Result<DssScientificPayVo>().sucess(dssScientificPayVo);
    }

    @Override
    @ApiOperation(value = "列表查询DssScientificPay", notes = "根据条件查询DssScientificPay列表数据")
    public Result<List<DssScientificPayVo>> list(@Valid @RequestBody @ApiParam(name="dssScientificPayQueryForm",value="DssScientificPay查询参数",required=true) DssScientificPayQueryForm dssScientificPayQueryForm) {
        log.info("search with dssScientificPayQueryForm:", dssScientificPayQueryForm.toString());
        List<DssScientificPay> dssScientificPayList=dssScientificPayService.selectList(dssScientificPayQueryForm);
        List<DssScientificPayVo> dssScientificPayVoList=new BeanUtils<DssScientificPayVo>().copyObjs(dssScientificPayList,DssScientificPayVo.class);
        return new Result<List<DssScientificPayVo>>().sucess(dssScientificPayVoList);
    }


    @Override
    @ApiOperation(value = "分页查询DssScientificPay", notes = "根据条件查询DssScientificPay分页数据")
    public Result<GdcPage<DssScientificPayVo>> search(@Valid @RequestBody @ApiParam(name="dssScientificPayQueryForm",value="DssScientificPay查询参数",required=true) DssScientificPayQueryForm dssScientificPayQueryForm) {
        log.info("search with dssScientificPayQueryForm:", dssScientificPayQueryForm.toString());
        IPage<DssScientificPay> dssScientificPayPage=dssScientificPayService.selectPage(dssScientificPayQueryForm);
        IPage<DssScientificPayVo> dssScientificPayVoPage=new BeanUtils<DssScientificPayVo>().copyPageObjs(dssScientificPayPage,DssScientificPayVo.class);
        GdcPage<DssScientificPayVo> pages=new GdcPage(dssScientificPayVoPage.getRecords(),dssScientificPayVoPage.getTotal(),(int)dssScientificPayVoPage.getCurrent(),(int)dssScientificPayVoPage.getSize());
        //return new Result<IPage<DssScientificPayVo>>().sucess(dssScientificPayVoPage);
        return  new Result<GdcPage<DssScientificPayVo>>().sucess(pages);
    }

}



