package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.EquipmentTransformQueryForm;
import com.deloitte.platform.api.project.vo.EquipmentTransformForm;
import com.deloitte.platform.api.project.vo.EquipmentTransformVo;
import com.deloitte.platform.api.project.client.EquipmentTransformClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IEquipmentTransformService;
import com.deloitte.services.project.entity.EquipmentTransform;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   EquipmentTransform控制器实现类
 * @Modified :
 */
@Api(tags = "EquipmentTransform操作接口")
@Slf4j
@RestController
public class EquipmentTransformController implements EquipmentTransformClient {

    @Autowired
    public IEquipmentTransformService  equipmentTransformService;


    @Override
    @ApiOperation(value = "新增EquipmentTransform", notes = "新增一个EquipmentTransform")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="equipmentTransformForm",value="新增EquipmentTransform的form表单",required=true)  EquipmentTransformForm equipmentTransformForm) {
        log.info("form:",  equipmentTransformForm.toString());
        EquipmentTransform equipmentTransform =new BeanUtils<EquipmentTransform>().copyObj(equipmentTransformForm,EquipmentTransform.class);
        return Result.success( equipmentTransformService.save(equipmentTransform));
    }


    @Override
    @ApiOperation(value = "删除EquipmentTransform", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "EquipmentTransformID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        equipmentTransformService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改EquipmentTransform", notes = "修改指定EquipmentTransform信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "EquipmentTransform的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="equipmentTransformForm",value="修改EquipmentTransform的form表单",required=true)  EquipmentTransformForm equipmentTransformForm) {
        EquipmentTransform equipmentTransform =new BeanUtils<EquipmentTransform>().copyObj(equipmentTransformForm,EquipmentTransform.class);
        equipmentTransform.setId(id);
        equipmentTransformService.saveOrUpdate(equipmentTransform);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取EquipmentTransform", notes = "获取指定ID的EquipmentTransform信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "EquipmentTransform的ID", required = true, dataType = "long")
    public Result<EquipmentTransformVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        EquipmentTransform equipmentTransform=equipmentTransformService.getById(id);
        EquipmentTransformVo equipmentTransformVo=new BeanUtils<EquipmentTransformVo>().copyObj(equipmentTransform,EquipmentTransformVo.class);
        return new Result<EquipmentTransformVo>().sucess(equipmentTransformVo);
    }

    @Override
    @ApiOperation(value = "列表查询EquipmentTransform", notes = "根据条件查询EquipmentTransform列表数据")
    public Result<List<EquipmentTransformVo>> list(@Valid @RequestBody @ApiParam(name="equipmentTransformQueryForm",value="EquipmentTransform查询参数",required=true) EquipmentTransformQueryForm equipmentTransformQueryForm) {
        log.info("search with equipmentTransformQueryForm:", equipmentTransformQueryForm.toString());
        List<EquipmentTransform> equipmentTransformList=equipmentTransformService.selectList(equipmentTransformQueryForm);
        List<EquipmentTransformVo> equipmentTransformVoList=new BeanUtils<EquipmentTransformVo>().copyObjs(equipmentTransformList,EquipmentTransformVo.class);
        return new Result<List<EquipmentTransformVo>>().sucess(equipmentTransformVoList);
    }


    @Override
    @ApiOperation(value = "分页查询EquipmentTransform", notes = "根据条件查询EquipmentTransform分页数据")
    public Result<IPage<EquipmentTransformVo>> search(@Valid @RequestBody @ApiParam(name="equipmentTransformQueryForm",value="EquipmentTransform查询参数",required=true) EquipmentTransformQueryForm equipmentTransformQueryForm) {
        log.info("search with equipmentTransformQueryForm:", equipmentTransformQueryForm.toString());
        IPage<EquipmentTransform> equipmentTransformPage=equipmentTransformService.selectPage(equipmentTransformQueryForm);
        IPage<EquipmentTransformVo> equipmentTransformVoPage=new BeanUtils<EquipmentTransformVo>().copyPageObjs(equipmentTransformPage,EquipmentTransformVo.class);
        return new Result<IPage<EquipmentTransformVo>>().sucess(equipmentTransformVoPage);
    }

}



