package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.MaintenanceQueryForm;
import com.deloitte.platform.api.project.vo.MaintenanceForm;
import com.deloitte.platform.api.project.vo.MaintenanceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  Maintenance控制器接口
 * @Modified :
 */
public interface MaintenanceClient {

    public static final String path="/project/maintenance";


    /**
     *  POST---新增
     * @param maintenanceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute MaintenanceForm maintenanceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, maintenanceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody MaintenanceForm maintenanceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<MaintenanceVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   maintenanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<MaintenanceVo>> list(@Valid @RequestBody MaintenanceQueryForm maintenanceQueryForm);


    /**
     *  POST----复杂查询
     * @param  maintenanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<MaintenanceVo>> search(@Valid @RequestBody MaintenanceQueryForm maintenanceQueryForm);
}
