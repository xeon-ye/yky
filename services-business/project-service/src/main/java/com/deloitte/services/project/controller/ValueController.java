package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.client.ValueClient;
import com.deloitte.platform.api.project.param.ValueQueryForm;
import com.deloitte.platform.api.project.vo.ValueForm;
import com.deloitte.platform.api.project.vo.ValueTypeVo;
import com.deloitte.platform.api.project.vo.ValueVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.entity.Value;
import com.deloitte.services.project.service.impl.ValueServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Value控制器实现类
 * @Modified :
 */
@Api(tags = "Value操作接口")
@Slf4j
@RestController
@RequestMapping("/project/value")
public class ValueController implements ValueClient {

    @Autowired
    public ValueServiceImpl valueService;


    @Override
    @ApiOperation(value = "新增Value", notes = "新增一个Value")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="valueForm",value="新增Value的form表单",required=true)  ValueForm valueForm) {
        log.info("form:",  valueForm.toString());
        Value value =new BeanUtils<Value>().copyObj(valueForm,Value.class);
        return Result.success( valueService.save(value));
    }


    @Override
    @ApiOperation(value = "删除Value", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ValueID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        valueService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Value", notes = "修改指定Value信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Value的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="valueForm",value="修改Value的form表单",required=true)  ValueForm valueForm) {
        Value value =new BeanUtils<Value>().copyObj(valueForm,Value.class);
        value.setId(id);
        valueService.saveOrUpdate(value);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Value", notes = "获取指定ID的Value信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Value的ID", required = true, dataType = "long")
    public Result<ValueVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Value value=valueService.getById(id);
        ValueVo valueVo=new BeanUtils<ValueVo>().copyObj(value,ValueVo.class);
        return new Result<ValueVo>().sucess(valueVo);
    }

    @Override
    @ApiOperation(value = "列表查询Value", notes = "根据条件查询Value列表数据")
    public Result<List<ValueVo>> list(@Valid @RequestBody @ApiParam(name="valueQueryForm",value="Value查询参数",required=true) ValueQueryForm valueQueryForm) {
        log.info("search with valueQueryForm:", valueQueryForm.toString());
        List<Value> valueList=valueService.selectList(valueQueryForm);
        List<ValueVo> valueVoList=new BeanUtils<ValueVo>().copyObjs(valueList,ValueVo.class);
        return new Result<List<ValueVo>>().sucess(valueVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Value", notes = "根据条件查询Value分页数据")
    public Result<IPage<ValueVo>> search(@Valid @RequestBody @ApiParam(name="valueQueryForm",value="Value查询参数",required=true) ValueQueryForm valueQueryForm) {
        log.info("search with valueQueryForm:", valueQueryForm.toString());
        IPage<Value> valuePage=valueService.selectPage(valueQueryForm);
        IPage<ValueVo> valueVoPage=new BeanUtils<ValueVo>().copyPageObjs(valuePage,ValueVo.class);
        return new Result<IPage<ValueVo>>().sucess(valueVoPage);
    }

    @ApiOperation(value = "获取指定parId的项目类型信息", notes = "获取ValueType")
    @ApiImplicitParam(paramType = "path", name = "parId", value = "parId", required = true, dataType = "long")
    @GetMapping(value = "/getSubTypeList/{parId}")
    public Result<List<ValueTypeVo>> getValueTypeByPraIdToList(@PathVariable(value = "parId") Long parId) {
        log.info("parId: " + parId.toString());
        return new Result<List<ValueTypeVo>>().sucess(valueService.getValueTypeByPraIdToList(parId));
    }

    @ApiOperation(value = "获取指定集值描述的项目类型信息", notes = "获取ValueType")
    @ApiImplicitParam(paramType = "path", name = "valueDesc", value = "valueDesc", required = true, dataType = "String")
    @GetMapping(value = "/getTypeList/{valueDesc}")
    public Result<List<ValueTypeVo>> getValueTypeList(@PathVariable(value = "valueDesc") String valueDesc) {
        log.info("valueDesc: " + valueDesc);
        return new Result<List<ValueTypeVo>>().sucess(valueService.getValueTypeList(valueDesc));
    }

    @ApiOperation(value = "获取指定集值描述的项目类型信息", notes = "获取ValueType")
    @ApiImplicitParam(paramType = "path", name = "valueDesc", value = "valueDesc", required = true, dataType = "String")
    @GetMapping(value = "/getValueTypeListByDes/{valueDesc}")
    public Result<List<ValueTypeVo>> getValueTypeListByDes(@PathVariable(value = "valueDesc") String valueDesc) {
        log.info("valueDesc: " + valueDesc);
        return new Result<List<ValueTypeVo>>().sucess(valueService.getValueTypeListByDes(valueDesc));
    }

    @ApiModelProperty(value = "获取支出大类父子级JSON数据包", notes = "获取JSONS")
    @GetMapping(value = "/getPayCatagoryJsonDatas")
    public Result getPayCatagoryJsonDatas() {
        return Result.success(valueService.getPayCatagoryJsonDatas());
    }


    @ApiModelProperty(value = "获取支出大类父子级JSON数据包", notes = "获取JSONS")
    @GetMapping(value = "/getValueTypeDataJsonByDes/{valueDesc}")
    public Result getValueTypeDataJsonByDes(@PathVariable(value = "valueDesc") String valueDesc) {
        return Result.success(valueService.getValueTypeDataJsonByDes(valueDesc));
    }

    @GetMapping(value = "/getValueSubTypeList/{valueDesc}")
    @ApiOperation(value = "获取项目类型对应的类别", notes = "获取项目类型对应的类别")
    Result getValueSubTypeList(@PathVariable(value = "valueDesc") String valueDesc){
        List<Value> values = valueService.getValueSubTypeList(valueDesc);
        return new Result().sucess(values);
    }
}



