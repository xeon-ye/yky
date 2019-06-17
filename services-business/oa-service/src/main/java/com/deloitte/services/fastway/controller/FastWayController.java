package com.deloitte.services.fastway.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.fastway.client.FastWayClient;
import com.deloitte.platform.api.oaservice.fastway.param.FastWayQueryForm;
import com.deloitte.platform.api.oaservice.fastway.vo.FastWayForm;
import com.deloitte.platform.api.oaservice.fastway.vo.FastWayVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fastway.entity.FastWay;
import com.deloitte.services.fastway.service.IFastWayService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description :   FastWay控制器实现类
 * @Modified :
 */
@Api(tags = "FastWay操作接口")
@Slf4j
@RestController
public class FastWayController implements FastWayClient {

    @Autowired
    public IFastWayService fastWayService;


    @Override
    @ApiOperation(value = "新增FastWay", notes = "新增一个FastWay")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "fastWayForm", value = "新增FastWay的form表单", required = true) FastWayForm fastWayForm) {
        log.info("form:", fastWayForm.toString());
        FastWay fastWay = new BeanUtils<FastWay>().copyObj(fastWayForm, FastWay.class);
        return Result.success(fastWayService.save(fastWay));
    }


    @Override
    @ApiOperation(value = "删除FastWay", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FastWayID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        fastWayService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改FastWay", notes = "修改指定FastWay信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FastWay的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "fastWayForm", value = "修改FastWay的form表单", required = true) FastWayForm fastWayForm) {
        FastWay fastWay = new BeanUtils<FastWay>().copyObj(fastWayForm, FastWay.class);
        fastWay.setId(id);
        fastWayService.saveOrUpdate(fastWay);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取FastWay", notes = "获取指定ID的FastWay信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FastWay的ID", required = true, dataType = "long")
    public Result<FastWayVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        FastWay fastWay = fastWayService.getById(id);
        FastWayVo fastWayVo = new BeanUtils<FastWayVo>().copyObj(fastWay, FastWayVo.class);
        return new Result<FastWayVo>().sucess(fastWayVo);
    }

    @Override
    @ApiOperation(value = "列表查询FastWay", notes = "根据条件查询FastWay列表数据")
    public Result<List<FastWayVo>> list(@Valid @RequestBody @ApiParam(name = "fastWayQueryForm", value = "FastWay查询参数", required = true) FastWayQueryForm fastWayQueryForm) {
        log.info("search with fastWayQueryForm:", fastWayQueryForm.toString());
        List<FastWay> fastWayList = fastWayService.selectList(fastWayQueryForm);
        List<FastWayVo> fastWayVoList = new BeanUtils<FastWayVo>().copyObjs(fastWayList, FastWayVo.class);
        return new Result<List<FastWayVo>>().sucess(fastWayVoList);
    }


    @Override
    @ApiOperation(value = "分页查询FastWay", notes = "根据条件查询FastWay分页数据")
    public Result<IPage<FastWayVo>> search(@Valid @RequestBody @ApiParam(name = "fastWayQueryForm", value = "FastWay查询参数", required = true) FastWayQueryForm fastWayQueryForm) {
        log.info("search with fastWayQueryForm:", fastWayQueryForm.toString());
        IPage<FastWay> fastWayPage = fastWayService.selectPage(fastWayQueryForm);
        IPage<FastWayVo> fastWayVoPage = new BeanUtils<FastWayVo>().copyPageObjs(fastWayPage, FastWayVo.class);
        return new Result<IPage<FastWayVo>>().sucess(fastWayVoPage);
    }

}



