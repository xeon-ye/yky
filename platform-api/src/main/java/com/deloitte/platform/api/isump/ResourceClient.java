package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.ResourceQueryForm;
import com.deloitte.platform.api.isump.vo.ResourceForm;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Resource控制器接口
 * @Modified :
 */
public interface ResourceClient {

    public static final String path="/isump/resource";


    /**
     *  POST---新增
     * @param resourceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ResourceForm resourceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, resourceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ResourceForm resourceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ResourceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ResourceVo>> list(@Valid @RequestBody ResourceQueryForm resourceQueryForm);


    /**
     *  POST----复杂查询
     * @param  resourceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ResourceVo>> search(@Valid @RequestBody ResourceQueryForm resourceQueryForm);

    @GetMapping(value = path+"/tree")
    Result tree(@RequestParam("deputyAccountId") Long deputyAccountId,@RequestParam("sysCode") String sysCode);
}
