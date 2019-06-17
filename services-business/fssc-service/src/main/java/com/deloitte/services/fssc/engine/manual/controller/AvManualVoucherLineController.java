package com.deloitte.services.fssc.engine.manual.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherLineQueryForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherLineForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherLineVo;
import com.deloitte.platform.api.fssc.engine.manual.AvManualVoucherLineClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherLineService;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;


/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :   AvManualVoucherLine控制器实现类
 * @Modified :
 */
@Api(tags = "手工录入凭证行信息操作接口")
@Slf4j
@RestController
public class AvManualVoucherLineController implements AvManualVoucherLineClient {

    @Autowired
    public IAvManualVoucherLineService  avManualVoucherLineService;


    @Override
    @ApiOperation(value = "新增AvManualVoucherLine", notes = "新增一个AvManualVoucherLine")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="avManualVoucherLineForm",value="新增AvManualVoucherLine的form表单",required=true)  AvManualVoucherLineForm avManualVoucherLineForm) {
        log.info("form:",  avManualVoucherLineForm.toString());
        AvManualVoucherLine avManualVoucherLine =new BeanUtils<AvManualVoucherLine>().copyObj(avManualVoucherLineForm,AvManualVoucherLine.class);
        return Result.success( avManualVoucherLineService.save(avManualVoucherLine));
    }


    @Override
    @ApiOperation(value = "删除AvManualVoucherLine", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvManualVoucherLineID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        avManualVoucherLineService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改AvManualVoucherLine", notes = "修改指定AvManualVoucherLine信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AvManualVoucherLine的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="avManualVoucherLineForm",value="修改AvManualVoucherLine的form表单",required=true)  AvManualVoucherLineForm avManualVoucherLineForm) {
        AvManualVoucherLine avManualVoucherLine =new BeanUtils<AvManualVoucherLine>().copyObj(avManualVoucherLineForm,AvManualVoucherLine.class);
        avManualVoucherLine.setId(id);
        avManualVoucherLineService.saveOrUpdate(avManualVoucherLine);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取AvManualVoucherLine", notes = "获取指定ID的AvManualVoucherLine信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvManualVoucherLine的ID", required = true, dataType = "long")
    public Result<AvManualVoucherLineVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AvManualVoucherLine avManualVoucherLine=avManualVoucherLineService.getById(id);
        AvManualVoucherLineVo avManualVoucherLineVo=new BeanUtils<AvManualVoucherLineVo>().copyObj(avManualVoucherLine,AvManualVoucherLineVo.class);
        return new Result<AvManualVoucherLineVo>().sucess(avManualVoucherLineVo);
    }

    @Override
    @ApiOperation(value = "列表查询AvManualVoucherLine", notes = "根据条件查询AvManualVoucherLine列表数据")
    public Result<List<AvManualVoucherLineVo>> list(@Valid @RequestBody @ApiParam(name="avManualVoucherLineQueryForm",value="AvManualVoucherLine查询参数",required=true) AvManualVoucherLineQueryForm avManualVoucherLineQueryForm) {
        log.info("search with avManualVoucherLineQueryForm:", avManualVoucherLineQueryForm.toString());
        List<AvManualVoucherLine> avManualVoucherLineList=avManualVoucherLineService.selectList(avManualVoucherLineQueryForm);
        List<AvManualVoucherLineVo> avManualVoucherLineVoList=new BeanUtils<AvManualVoucherLineVo>().copyObjs(avManualVoucherLineList,AvManualVoucherLineVo.class);
        return new Result<List<AvManualVoucherLineVo>>().sucess(avManualVoucherLineVoList);
    }


    @Override
    @ApiOperation(value = "分页查询AvManualVoucherLine", notes = "根据条件查询AvManualVoucherLine分页数据")
    public Result<IPage<AvManualVoucherLineVo>> search(@Valid @RequestBody @ApiParam(name="avManualVoucherLineQueryForm",value="AvManualVoucherLine查询参数",required=true) AvManualVoucherLineQueryForm avManualVoucherLineQueryForm) {
        log.info("search with avManualVoucherLineQueryForm:", avManualVoucherLineQueryForm.toString());
        IPage<AvManualVoucherLine> avManualVoucherLinePage=avManualVoucherLineService.selectPage(avManualVoucherLineQueryForm);
        IPage<AvManualVoucherLineVo> avManualVoucherLineVoPage=new BeanUtils<AvManualVoucherLineVo>().copyPageObjs(avManualVoucherLinePage,AvManualVoucherLineVo.class);
        return new Result<IPage<AvManualVoucherLineVo>>().sucess(avManualVoucherLineVoPage);
    }

}



