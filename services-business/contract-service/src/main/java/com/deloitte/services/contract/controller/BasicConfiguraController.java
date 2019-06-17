package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.BasicConfiguraQueryForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraVo;
import com.deloitte.platform.api.contract.client.BasicConfiguraClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IBasicConfiguraService;
import com.deloitte.services.contract.entity.BasicConfigura;


/**
 * @Author : yangyq
 * @Date : Create in 2019-04-23
 * @Description :   合同信息配置操作接口
 * @Modified :
 */
@Api(tags = "合同信息配置操作接口")
@Slf4j
@RestController
@RequestMapping("/basicConfigura")
public class BasicConfiguraController implements BasicConfiguraClient {

    @Autowired
    public IBasicConfiguraService  basicConfiguraService;


    @Override
    @ApiOperation(value = "新增BasicConfigura", notes = "新增一个BasicConfigura")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="basicConfiguraForm",value="新增BasicConfigura的form表单",required=true)  BasicConfiguraForm basicConfiguraForm) {
        log.info("form:",  basicConfiguraForm.toString());
        BasicConfigura basicConfigura =new BeanUtils<BasicConfigura>().copyObj(basicConfiguraForm,BasicConfigura.class);
        return Result.success( basicConfiguraService.save(basicConfigura));
    }


    @Override
    @ApiOperation(value = "删除BasicConfigura", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BasicConfiguraID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        basicConfiguraService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BasicConfigura", notes = "修改指定BasicConfigura信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BasicConfigura的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="basicConfiguraForm",value="修改BasicConfigura的form表单",required=true)  BasicConfiguraForm basicConfiguraForm) {
        BasicConfigura basicConfigura =new BeanUtils<BasicConfigura>().copyObj(basicConfiguraForm,BasicConfigura.class);
        basicConfigura.setId(id);
        basicConfiguraService.saveOrUpdate(basicConfigura);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取BasicConfigura", notes = "获取指定ID的BasicConfigura信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BasicConfigura的ID", required = true, dataType = "long")
    public Result<BasicConfiguraVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BasicConfigura basicConfigura=basicConfiguraService.getById(id);
        BasicConfiguraVo basicConfiguraVo=new BeanUtils<BasicConfiguraVo>().copyObj(basicConfigura,BasicConfiguraVo.class);
        return new Result<BasicConfiguraVo>().sucess(basicConfiguraVo);
    }

    @Override
    @ApiOperation(value = "列表查询BasicConfigura", notes = "根据条件查询BasicConfigura列表数据")
    public Result<List<BasicConfiguraVo>> list(@Valid @RequestBody @ApiParam(name="basicConfiguraQueryForm",value="BasicConfigura查询参数",required=true) BasicConfiguraQueryForm basicConfiguraQueryForm) {
        log.info("search with basicConfiguraQueryForm:", basicConfiguraQueryForm.toString());
        List<BasicConfigura> basicConfiguraList=basicConfiguraService.selectList(basicConfiguraQueryForm);
        List<BasicConfiguraVo> basicConfiguraVoList=new BeanUtils<BasicConfiguraVo>().copyObjs(basicConfiguraList,BasicConfiguraVo.class);
        return new Result<List<BasicConfiguraVo>>().sucess(basicConfiguraVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BasicConfigura", notes = "根据条件查询BasicConfigura分页数据")
    public Result<IPage<BasicConfiguraVo>> search(@Valid @RequestBody @ApiParam(name="basicConfiguraQueryForm",value="BasicConfigura查询参数",required=true) BasicConfiguraQueryForm basicConfiguraQueryForm) {
        log.info("search with basicConfiguraQueryForm:", basicConfiguraQueryForm.toString());
        IPage<BasicConfigura> basicConfiguraPage=basicConfiguraService.selectPage(basicConfiguraQueryForm);
        IPage<BasicConfiguraVo> basicConfiguraVoPage=new BeanUtils<BasicConfiguraVo>().copyPageObjs(basicConfiguraPage,BasicConfiguraVo.class);
        return new Result<IPage<BasicConfiguraVo>>().sucess(basicConfiguraVoPage);
    }

    @ApiOperation(value = "保存合同信息配置", notes = "保存合同信息配置")
    @PostMapping("/saveBasicConfigura")
    public Result<BasicConfiguraVo> saveBasicConfigura(@Valid @RequestBody BasicConfiguraForm basicConfiguraForm){
        return basicConfiguraService.saveBasicConfigura(basicConfiguraForm);
    }
}



