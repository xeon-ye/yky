package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SysDictQueryForm;
import com.deloitte.platform.api.contract.vo.SysDictForm;
import com.deloitte.platform.api.contract.vo.SysDictVo;
import com.deloitte.platform.api.contract.client.SysDictClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import com.deloitte.services.contract.service.ISysDictService;
import com.deloitte.services.contract.entity.SysDict;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SysDict控制器实现类
 * @Modified :
 */
@Api(tags = "字典操作接口")
@Slf4j
@RestController
@RequestMapping("/sysDict")
public class SysDictController implements SysDictClient {

    @Autowired
    public ISysDictService  sysDictService;


    @Override
    @ApiOperation(value = "新增SysDict", notes = "新增一个SysDict")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="sysDictForm",value="新增SysDict的form表单",required=true)  SysDictForm sysDictForm) {
        log.info("form:",  sysDictForm.toString());
        SysDict sysDict =new BeanUtils<SysDict>().copyObj(sysDictForm,SysDict.class);
        return Result.success( sysDictService.save(sysDict));
    }


    @Override
    @ApiOperation(value = "删除SysDict", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysDictID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sysDictService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SysDict", notes = "修改指定SysDict信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SysDict的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="sysDictForm",value="修改SysDict的form表单",required=true)  SysDictForm sysDictForm) {
        SysDict sysDict =new BeanUtils<SysDict>().copyObj(sysDictForm,SysDict.class);
        sysDict.setId(id);
        sysDictService.saveOrUpdate(sysDict);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SysDict", notes = "获取指定ID的SysDict信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysDict的ID", required = true, dataType = "long")
    public Result<SysDictVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SysDict sysDict=sysDictService.getById(id);
        SysDictVo sysDictVo=new BeanUtils<SysDictVo>().copyObj(sysDict,SysDictVo.class);
        return new Result<SysDictVo>().sucess(sysDictVo);
    }

    @Override
    @ApiOperation(value = "列表查询SysDict", notes = "根据条件查询SysDict列表数据")
    public Result<List<SysDictVo>> list(@Valid @RequestBody @ApiParam(name="sysDictQueryForm",value="SysDict查询参数",required=true) SysDictQueryForm sysDictQueryForm) {
        log.info("search with sysDictQueryForm:", sysDictQueryForm.toString());
        List<SysDict> sysDictList=sysDictService.selectList(sysDictQueryForm);
        List<SysDictVo> sysDictVoList=new BeanUtils<SysDictVo>().copyObjs(sysDictList,SysDictVo.class);
        return new Result<List<SysDictVo>>().sucess(sysDictVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SysDict", notes = "根据条件查询SysDict分页数据")
    public Result<IPage<SysDictVo>> search(@Valid @RequestBody @ApiParam(name="sysDictQueryForm",value="SysDict查询参数",required=true) SysDictQueryForm sysDictQueryForm) {
        log.info("search with sysDictQueryForm:", sysDictQueryForm.toString());
        IPage<SysDict> sysDictPage=sysDictService.selectPage(sysDictQueryForm);
        IPage<SysDictVo> sysDictVoPage=new BeanUtils<SysDictVo>().copyPageObjs(sysDictPage,SysDictVo.class);
        return new Result<IPage<SysDictVo>>().sucess(sysDictVoPage);
    }

    @ApiOperation(value = "根据传入的字典类型查询字典信息", notes = "根据传入的字典类型查询字典信息")
    @PostMapping("/getSysDictVoByTypes")
    public Result<Map<String, List<SysDictVo>>> getSysDictVoByTypes( @Valid @RequestBody SysDictQueryForm sysDict) {
        log.info("search w;ith sysDictQueryForm:", sysDict);
//        List<String> types = (List<String>) params.get("types");
        String type = sysDict.getSysDictType();
        System.out.println(type);
        String[] types = type.split(",");
        System.out.println(types);
        Map<String, SysDictVo> sysDictPages = sysDictService.getSysDictVoByTypes(types);
//        IPage<SysDictVo> sysDictVoPage=new BeanUtils<SysDictVo>().copyPageObjs(sysDictPage,SysDictVo.class);
        return new Result<Map<String, List<SysDictVo>>>().sucess(sysDictPages);
    }

    @ApiOperation(value = "保存合同类型信息", notes = "保存合同类型信息")
    @PostMapping("/saveContractType")
    public Result saveContractType(@RequestBody SysDict sysDict){
        return sysDictService.saveContractType(sysDict);
    }

    @ApiOperation(value = "修改合同类型信息", notes = "修改合同类型信息")
    @PostMapping("/updateContractType")
    public Result updateContractType(@RequestBody SysDict sysDict){
        return sysDictService.updateContractType(sysDict);
    }

    @ApiOperation(value = "删除合同类型信息", notes = "删除合同类型信息")
    @PostMapping("/deleteContractType")
    public Result deleteContractType(@RequestBody SysDict sysDict){
        return sysDictService.deleteContractType(sysDict);
    }
}



