package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ValueQueryForm;
import com.deloitte.platform.api.project.vo.ValueForm;
import com.deloitte.platform.api.project.vo.ValueTypeVo;
import com.deloitte.platform.api.project.vo.ValueVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Value控制器接口
 * @Modified :
 */
public interface ValueClient {

    public static final String path="/project/value";


    /**
     *  POST---新增
     * @param valueForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ValueForm valueForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, valueForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ValueForm valueForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ValueVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   valueQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ValueVo>> list(@Valid @RequestBody ValueQueryForm valueQueryForm);


    /**
     *  POST----复杂查询
     * @param  valueQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ValueVo>> search(@Valid @RequestBody ValueQueryForm valueQueryForm);

}
