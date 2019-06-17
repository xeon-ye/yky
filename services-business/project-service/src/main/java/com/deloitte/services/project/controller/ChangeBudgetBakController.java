package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ChangeBudgetBakQueryForm;
import com.deloitte.platform.api.project.vo.ChangeBudgetBakForm;
import com.deloitte.platform.api.project.vo.ChangeBudgetBakVo;
import com.deloitte.platform.api.project.client.ChangeBudgetBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IChangeBudgetBakService;
import com.deloitte.services.project.entity.ChangeBudgetBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   ChangeBudgetBak控制器实现类
 * @Modified :
 */
@Api(tags = "ChangeBudgetBak操作接口")
@Slf4j
@RestController
public class ChangeBudgetBakController implements ChangeBudgetBakClient {

    @Autowired
    public IChangeBudgetBakService  changeBudgetBakService;


    @Override
    @ApiOperation(value = "新增ChangeBudgetBak", notes = "新增一个ChangeBudgetBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="changeBudgetBakForm",value="新增ChangeBudgetBak的form表单",required=true)  ChangeBudgetBakForm changeBudgetBakForm) {
        log.info("form:",  changeBudgetBakForm.toString());
        ChangeBudgetBak changeBudgetBak =new BeanUtils<ChangeBudgetBak>().copyObj(changeBudgetBakForm,ChangeBudgetBak.class);
        return Result.success( changeBudgetBakService.save(changeBudgetBak));
    }


    @Override
    @ApiOperation(value = "删除ChangeBudgetBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ChangeBudgetBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        changeBudgetBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ChangeBudgetBak", notes = "修改指定ChangeBudgetBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ChangeBudgetBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="changeBudgetBakForm",value="修改ChangeBudgetBak的form表单",required=true)  ChangeBudgetBakForm changeBudgetBakForm) {
        ChangeBudgetBak changeBudgetBak =new BeanUtils<ChangeBudgetBak>().copyObj(changeBudgetBakForm,ChangeBudgetBak.class);
        changeBudgetBak.setId(id);
        changeBudgetBakService.saveOrUpdate(changeBudgetBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ChangeBudgetBak", notes = "获取指定ID的ChangeBudgetBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ChangeBudgetBak的ID", required = true, dataType = "long")
    public Result<ChangeBudgetBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ChangeBudgetBak changeBudgetBak=changeBudgetBakService.getById(id);
        ChangeBudgetBakVo changeBudgetBakVo=new BeanUtils<ChangeBudgetBakVo>().copyObj(changeBudgetBak,ChangeBudgetBakVo.class);
        return new Result<ChangeBudgetBakVo>().sucess(changeBudgetBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询ChangeBudgetBak", notes = "根据条件查询ChangeBudgetBak列表数据")
    public Result<List<ChangeBudgetBakVo>> list(@Valid @RequestBody @ApiParam(name="changeBudgetBakQueryForm",value="ChangeBudgetBak查询参数",required=true) ChangeBudgetBakQueryForm changeBudgetBakQueryForm) {
        log.info("search with changeBudgetBakQueryForm:", changeBudgetBakQueryForm.toString());
        List<ChangeBudgetBak> changeBudgetBakList=changeBudgetBakService.selectList(changeBudgetBakQueryForm);
        List<ChangeBudgetBakVo> changeBudgetBakVoList=new BeanUtils<ChangeBudgetBakVo>().copyObjs(changeBudgetBakList,ChangeBudgetBakVo.class);
        return new Result<List<ChangeBudgetBakVo>>().sucess(changeBudgetBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ChangeBudgetBak", notes = "根据条件查询ChangeBudgetBak分页数据")
    public Result<IPage<ChangeBudgetBakVo>> search(@Valid @RequestBody @ApiParam(name="changeBudgetBakQueryForm",value="ChangeBudgetBak查询参数",required=true) ChangeBudgetBakQueryForm changeBudgetBakQueryForm) {
        log.info("search with changeBudgetBakQueryForm:", changeBudgetBakQueryForm.toString());
        IPage<ChangeBudgetBak> changeBudgetBakPage=changeBudgetBakService.selectPage(changeBudgetBakQueryForm);
        IPage<ChangeBudgetBakVo> changeBudgetBakVoPage=new BeanUtils<ChangeBudgetBakVo>().copyPageObjs(changeBudgetBakPage,ChangeBudgetBakVo.class);
        return new Result<IPage<ChangeBudgetBakVo>>().sucess(changeBudgetBakVoPage);
    }

}



