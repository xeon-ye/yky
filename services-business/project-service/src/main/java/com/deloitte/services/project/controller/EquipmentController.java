package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.EquipmentQueryForm;
import com.deloitte.platform.api.project.vo.EquipmentForm;
import com.deloitte.platform.api.project.vo.EquipmentVo;
import com.deloitte.platform.api.project.client.EquipmentClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IEquipmentService;
import com.deloitte.services.project.entity.Equipment;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Equipment控制器实现类
 * @Modified :
 */
@Api(tags = "Equipment操作接口")
@Slf4j
@RestController
public class EquipmentController implements EquipmentClient {

    @Autowired
    public IEquipmentService  equipmentService;


    @Override
    @ApiOperation(value = "新增Equipment", notes = "新增一个Equipment")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="equipmentForm",value="新增Equipment的form表单",required=true)  EquipmentForm equipmentForm) {
        log.info("form:",  equipmentForm.toString());
        Equipment equipment =new BeanUtils<Equipment>().copyObj(equipmentForm,Equipment.class);
        return Result.success( equipmentService.save(equipment));
    }


    @Override
    @ApiOperation(value = "删除Equipment", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "EquipmentID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        equipmentService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Equipment", notes = "修改指定Equipment信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Equipment的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="equipmentForm",value="修改Equipment的form表单",required=true)  EquipmentForm equipmentForm) {
        Equipment equipment =new BeanUtils<Equipment>().copyObj(equipmentForm,Equipment.class);
        equipment.setId(id);
        equipmentService.saveOrUpdate(equipment);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Equipment", notes = "获取指定ID的Equipment信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Equipment的ID", required = true, dataType = "long")
    public Result<EquipmentVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Equipment equipment=equipmentService.getById(id);
        EquipmentVo equipmentVo=new BeanUtils<EquipmentVo>().copyObj(equipment,EquipmentVo.class);
        return new Result<EquipmentVo>().sucess(equipmentVo);
    }

    @Override
    @ApiOperation(value = "列表查询Equipment", notes = "根据条件查询Equipment列表数据")
    public Result<List<EquipmentVo>> list(@Valid @RequestBody @ApiParam(name="equipmentQueryForm",value="Equipment查询参数",required=true) EquipmentQueryForm equipmentQueryForm) {
        log.info("search with equipmentQueryForm:", equipmentQueryForm.toString());
        List<Equipment> equipmentList=equipmentService.selectList(equipmentQueryForm);
        List<EquipmentVo> equipmentVoList=new BeanUtils<EquipmentVo>().copyObjs(equipmentList,EquipmentVo.class);
        return new Result<List<EquipmentVo>>().sucess(equipmentVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Equipment", notes = "根据条件查询Equipment分页数据")
    public Result<IPage<EquipmentVo>> search(@Valid @RequestBody @ApiParam(name="equipmentQueryForm",value="Equipment查询参数",required=true) EquipmentQueryForm equipmentQueryForm) {
        log.info("search with equipmentQueryForm:", equipmentQueryForm.toString());
        IPage<Equipment> equipmentPage=equipmentService.selectPage(equipmentQueryForm);
        IPage<EquipmentVo> equipmentVoPage=new BeanUtils<EquipmentVo>().copyPageObjs(equipmentPage,EquipmentVo.class);
        return new Result<IPage<EquipmentVo>>().sucess(equipmentVoPage);
    }

}



