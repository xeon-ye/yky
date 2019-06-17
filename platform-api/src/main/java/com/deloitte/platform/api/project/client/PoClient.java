package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.PoQueryForm;
import com.deloitte.platform.api.project.vo.PoForm;
import com.deloitte.platform.api.project.vo.PoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Po控制器接口
 * @Modified :
 */
public interface PoClient {

    public static final String path="/project/po";


    /**
     *  POST---新增
     * @param poForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PoForm poForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, poForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PoForm poForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   poQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PoVo>> list(@Valid @RequestBody PoQueryForm poQueryForm);


    /**
     *  POST----复杂查询
     * @param  poQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PoVo>> search(@Valid @RequestBody PoQueryForm poQueryForm);
}
