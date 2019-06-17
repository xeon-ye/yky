package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.InsandequipQueryForm;
import com.deloitte.platform.api.project.vo.InsandequipForm;
import com.deloitte.platform.api.project.vo.InsandequipVo;
import com.deloitte.platform.api.project.client.InsandequipClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IInsandequipService;
import com.deloitte.services.project.entity.Insandequip;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :   Insandequip控制器实现类
 * @Modified :
 */
@Api(tags = "Insandequip操作接口")
@Slf4j
@RestController
public class InsandequipController implements InsandequipClient {

    @Autowired
    public IInsandequipService  insandequipService;


    @Override
    @ApiOperation(value = "新增Insandequip", notes = "新增一个Insandequip")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="insandequipForm",value="新增Insandequip的form表单",required=true)  InsandequipForm insandequipForm) {
        log.info("form:",  insandequipForm.toString());
        Insandequip insandequip =new BeanUtils<Insandequip>().copyObj(insandequipForm,Insandequip.class);
        return Result.success( insandequipService.save(insandequip));
    }


    @Override
    @ApiOperation(value = "删除Insandequip", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "InsandequipID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        insandequipService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Insandequip", notes = "修改指定Insandequip信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Insandequip的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="insandequipForm",value="修改Insandequip的form表单",required=true)  InsandequipForm insandequipForm) {
        Insandequip insandequip =new BeanUtils<Insandequip>().copyObj(insandequipForm,Insandequip.class);
        insandequip.setId(id);
        insandequipService.saveOrUpdate(insandequip);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Insandequip", notes = "获取指定ID的Insandequip信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Insandequip的ID", required = true, dataType = "long")
    public Result<InsandequipVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Insandequip insandequip=insandequipService.getById(id);
        InsandequipVo insandequipVo=new BeanUtils<InsandequipVo>().copyObj(insandequip,InsandequipVo.class);
        return new Result<InsandequipVo>().sucess(insandequipVo);
    }

    @Override
    @ApiOperation(value = "列表查询Insandequip", notes = "根据条件查询Insandequip列表数据")
    public Result<List<InsandequipVo>> list(@Valid @RequestBody @ApiParam(name="insandequipQueryForm",value="Insandequip查询参数",required=true) InsandequipQueryForm insandequipQueryForm) {
        log.info("search with insandequipQueryForm:", insandequipQueryForm.toString());
        List<Insandequip> insandequipList=insandequipService.selectList(insandequipQueryForm);
        List<InsandequipVo> insandequipVoList=new BeanUtils<InsandequipVo>().copyObjs(insandequipList,InsandequipVo.class);
        return new Result<List<InsandequipVo>>().sucess(insandequipVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Insandequip", notes = "根据条件查询Insandequip分页数据")
    public Result<IPage<InsandequipVo>> search(@Valid @RequestBody @ApiParam(name="insandequipQueryForm",value="Insandequip查询参数",required=true) InsandequipQueryForm insandequipQueryForm) {
        log.info("search with insandequipQueryForm:", insandequipQueryForm.toString());
        IPage<Insandequip> insandequipPage=insandequipService.selectPage(insandequipQueryForm);
        IPage<InsandequipVo> insandequipVoPage=new BeanUtils<InsandequipVo>().copyPageObjs(insandequipPage,InsandequipVo.class);
        return new Result<IPage<InsandequipVo>>().sucess(insandequipVoPage);
    }

}



