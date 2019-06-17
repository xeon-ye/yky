package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.AllActQueryForm;
import com.deloitte.platform.api.project.vo.AllActForm;
import com.deloitte.platform.api.project.vo.AllActVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  AllAct控制器接口
 * @Modified :
 */
public interface AllActClient {

    public static final String path="/project/all-act";


    /**
     *  POST---新增
     * @param allActForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AllActForm allActForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, allActForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AllActForm allActForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AllActVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   allActQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AllActVo>> list(@Valid @RequestBody AllActQueryForm allActQueryForm);


    /**
     *  POST----复杂查询
     * @param  allActQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AllActVo>> search(@Valid @RequestBody AllActQueryForm allActQueryForm);
}
