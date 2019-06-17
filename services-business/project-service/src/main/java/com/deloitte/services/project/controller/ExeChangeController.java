package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExeChangeQueryForm;
import com.deloitte.platform.api.project.vo.ExeChangeForm;
import com.deloitte.platform.api.project.vo.ExeChangeVo;
import com.deloitte.platform.api.project.client.ExeChangeClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IExeChangeService;
import com.deloitte.services.project.entity.ExeChange;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :   ExeChange控制器实现类
 * @Modified :
 */
@Api(tags = "ExeChange操作接口")
@Slf4j
@RestController
public class ExeChangeController implements ExeChangeClient {

    @Autowired
    public IExeChangeService  exeChangeService;


    @Override
    @ApiOperation(value = "新增ExeChange", notes = "新增一个ExeChange")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="exeChangeForm",value="新增ExeChange的form表单",required=true)  ExeChangeForm exeChangeForm) {
        log.info("form:",  exeChangeForm.toString());
        ExeChange exeChange =new BeanUtils<ExeChange>().copyObj(exeChangeForm,ExeChange.class);
        return Result.success( exeChangeService.save(exeChange));
    }


    @Override
    @ApiOperation(value = "删除ExeChange", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeChangeID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        exeChangeService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ExeChange", notes = "修改指定ExeChange信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ExeChange的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="exeChangeForm",value="修改ExeChange的form表单",required=true)  ExeChangeForm exeChangeForm) {
        ExeChange exeChange =new BeanUtils<ExeChange>().copyObj(exeChangeForm,ExeChange.class);
        exeChange.setId(id);
        exeChangeService.saveOrUpdate(exeChange);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ExeChange", notes = "获取指定ID的ExeChange信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeChange的ID", required = true, dataType = "long")
    public Result<ExeChangeVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ExeChange exeChange=exeChangeService.getById(id);
        ExeChangeVo exeChangeVo=new BeanUtils<ExeChangeVo>().copyObj(exeChange,ExeChangeVo.class);
        return new Result<ExeChangeVo>().sucess(exeChangeVo);
    }

    @Override
    @ApiOperation(value = "列表查询ExeChange", notes = "根据条件查询ExeChange列表数据")
    public Result<List<ExeChangeVo>> list(@Valid @RequestBody @ApiParam(name="exeChangeQueryForm",value="ExeChange查询参数",required=true) ExeChangeQueryForm exeChangeQueryForm) {
        log.info("search with exeChangeQueryForm:", exeChangeQueryForm.toString());
        List<ExeChange> exeChangeList=exeChangeService.selectList(exeChangeQueryForm);
        List<ExeChangeVo> exeChangeVoList=new BeanUtils<ExeChangeVo>().copyObjs(exeChangeList,ExeChangeVo.class);
        return new Result<List<ExeChangeVo>>().sucess(exeChangeVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ExeChange", notes = "根据条件查询ExeChange分页数据")
    public Result<IPage<ExeChangeVo>> search(@Valid @RequestBody @ApiParam(name="exeChangeQueryForm",value="ExeChange查询参数",required=true) ExeChangeQueryForm exeChangeQueryForm) {
        log.info("search with exeChangeQueryForm:", exeChangeQueryForm.toString());
        IPage<ExeChange> exeChangePage=exeChangeService.selectPage(exeChangeQueryForm);
        IPage<ExeChangeVo> exeChangeVoPage=new BeanUtils<ExeChangeVo>().copyPageObjs(exeChangePage,ExeChangeVo.class);
        return new Result<IPage<ExeChangeVo>>().sucess(exeChangeVoPage);
    }

}



