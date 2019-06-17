package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.OuQueryForm;
import com.deloitte.platform.api.project.vo.OuForm;
import com.deloitte.platform.api.project.vo.OuVo;
import com.deloitte.platform.api.project.client.OuClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IOuService;
import com.deloitte.services.project.entity.Ou;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Ou控制器实现类
 * @Modified :
 */
@Api(tags = "Ou操作接口")
@Slf4j
@RestController
public class OuController implements OuClient {

    @Autowired
    public IOuService  ouService;


    @Override
    @ApiOperation(value = "新增Ou", notes = "新增一个Ou")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="ouForm",value="新增Ou的form表单",required=true)  OuForm ouForm) {
        log.info("form:",  ouForm.toString());
        Ou ou =new BeanUtils<Ou>().copyObj(ouForm,Ou.class);
        return Result.success( ouService.save(ou));
    }


    @Override
    @ApiOperation(value = "删除Ou", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OuID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        ouService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Ou", notes = "修改指定Ou信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Ou的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="ouForm",value="修改Ou的form表单",required=true)  OuForm ouForm) {
        Ou ou =new BeanUtils<Ou>().copyObj(ouForm,Ou.class);
        ou.setId(id);
        ouService.saveOrUpdate(ou);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Ou", notes = "获取指定ID的Ou信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Ou的ID", required = true, dataType = "long")
    public Result<OuVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Ou ou=ouService.getById(id);
        OuVo ouVo=new BeanUtils<OuVo>().copyObj(ou,OuVo.class);
        return new Result<OuVo>().sucess(ouVo);
    }

    @Override
    @ApiOperation(value = "列表查询Ou", notes = "根据条件查询Ou列表数据")
    public Result<List<OuVo>> list(@Valid @RequestBody @ApiParam(name="ouQueryForm",value="Ou查询参数",required=true) OuQueryForm ouQueryForm) {
        log.info("search with ouQueryForm:", ouQueryForm.toString());
        List<Ou> ouList=ouService.selectList(ouQueryForm);
        List<OuVo> ouVoList=new BeanUtils<OuVo>().copyObjs(ouList,OuVo.class);
        return new Result<List<OuVo>>().sucess(ouVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Ou", notes = "根据条件查询Ou分页数据")
    public Result<IPage<OuVo>> search(@Valid @RequestBody @ApiParam(name="ouQueryForm",value="Ou查询参数",required=true) OuQueryForm ouQueryForm) {
        log.info("search with ouQueryForm:", ouQueryForm.toString());
        IPage<Ou> ouPage=ouService.selectPage(ouQueryForm);
        IPage<OuVo> ouVoPage=new BeanUtils<OuVo>().copyPageObjs(ouPage,OuVo.class);
        return new Result<IPage<OuVo>>().sucess(ouVoPage);
    }

}



