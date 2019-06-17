package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.EquipmentQueryForm;
import com.deloitte.platform.api.project.vo.EquipmentForm;
import com.deloitte.platform.api.project.vo.EquipmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Equipment控制器接口
 * @Modified :
 */
public interface EquipmentClient {

    public static final String path="/project/equipment";


    /**
     *  POST---新增
     * @param equipmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EquipmentForm equipmentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, equipmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EquipmentForm equipmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EquipmentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   equipmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EquipmentVo>> list(@Valid @RequestBody EquipmentQueryForm equipmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  equipmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EquipmentVo>> search(@Valid @RequestBody EquipmentQueryForm equipmentQueryForm);
}
