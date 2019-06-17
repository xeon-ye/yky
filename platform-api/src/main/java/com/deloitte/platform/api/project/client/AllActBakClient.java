package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.AllActBakQueryForm;
import com.deloitte.platform.api.project.vo.AllActBakForm;
import com.deloitte.platform.api.project.vo.AllActBakVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  AllActBak控制器接口
 * @Modified :
 */
public interface AllActBakClient {

    public static final String path="/project/all-act-bak";


    /**
     *  POST---新增
     * @param allActBakForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AllActBakForm allActBakForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, allActBakForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AllActBakForm allActBakForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AllActBakVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   allActBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AllActBakVo>> list(@Valid @RequestBody AllActBakQueryForm allActBakQueryForm);


    /**
     *  POST----复杂查询
     * @param  allActBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AllActBakVo>> search(@Valid @RequestBody AllActBakQueryForm allActBakQueryForm);
}
