package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExeRepHisQueryForm;
import com.deloitte.platform.api.project.vo.ExeRepHisForm;
import com.deloitte.platform.api.project.vo.ExeRepHisVo;
import com.deloitte.platform.api.project.client.ExeRepHisClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IExeRepHisService;
import com.deloitte.services.project.entity.ExeRepHis;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :   ExeRepHis控制器实现类
 * @Modified :
 */
@Api(tags = "ExeRepHis操作接口")
@Slf4j
@RestController
public class ExeRepHisController implements ExeRepHisClient {

    @Autowired
    public IExeRepHisService  exeRepHisService;


    @Override
    @ApiOperation(value = "新增ExeRepHis", notes = "新增一个ExeRepHis")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="exeRepHisForm",value="新增ExeRepHis的form表单",required=true)  ExeRepHisForm exeRepHisForm) {
        log.info("form:",  exeRepHisForm.toString());
        ExeRepHis exeRepHis =new BeanUtils<ExeRepHis>().copyObj(exeRepHisForm,ExeRepHis.class);
        return Result.success( exeRepHisService.save(exeRepHis));
    }


    @Override
    @ApiOperation(value = "删除ExeRepHis", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeRepHisID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        exeRepHisService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ExeRepHis", notes = "修改指定ExeRepHis信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ExeRepHis的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="exeRepHisForm",value="修改ExeRepHis的form表单",required=true)  ExeRepHisForm exeRepHisForm) {
        ExeRepHis exeRepHis =new BeanUtils<ExeRepHis>().copyObj(exeRepHisForm,ExeRepHis.class);
        exeRepHis.setId(id);
        exeRepHisService.saveOrUpdate(exeRepHis);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ExeRepHis", notes = "获取指定ID的ExeRepHis信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeRepHis的ID", required = true, dataType = "long")
    public Result<ExeRepHisVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ExeRepHis exeRepHis=exeRepHisService.getById(id);
        ExeRepHisVo exeRepHisVo=new BeanUtils<ExeRepHisVo>().copyObj(exeRepHis,ExeRepHisVo.class);
        return new Result<ExeRepHisVo>().sucess(exeRepHisVo);
    }

    @Override
    @ApiOperation(value = "列表查询ExeRepHis", notes = "根据条件查询ExeRepHis列表数据")
    public Result<List<ExeRepHisVo>> list(@Valid @RequestBody @ApiParam(name="exeRepHisQueryForm",value="ExeRepHis查询参数",required=true) ExeRepHisQueryForm exeRepHisQueryForm) {
        log.info("search with exeRepHisQueryForm:", exeRepHisQueryForm.toString());
        List<ExeRepHis> exeRepHisList=exeRepHisService.selectList(exeRepHisQueryForm);
        List<ExeRepHisVo> exeRepHisVoList=new BeanUtils<ExeRepHisVo>().copyObjs(exeRepHisList,ExeRepHisVo.class);
        return new Result<List<ExeRepHisVo>>().sucess(exeRepHisVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ExeRepHis", notes = "根据条件查询ExeRepHis分页数据")
    public Result<IPage<ExeRepHisVo>> search(@Valid @RequestBody @ApiParam(name="exeRepHisQueryForm",value="ExeRepHis查询参数",required=true) ExeRepHisQueryForm exeRepHisQueryForm) {
        log.info("search with exeRepHisQueryForm:", exeRepHisQueryForm.toString());
        IPage<ExeRepHis> exeRepHisPage=exeRepHisService.selectPage(exeRepHisQueryForm);
        IPage<ExeRepHisVo> exeRepHisVoPage=new BeanUtils<ExeRepHisVo>().copyPageObjs(exeRepHisPage,ExeRepHisVo.class);
        return new Result<IPage<ExeRepHisVo>>().sucess(exeRepHisVoPage);
    }

}



