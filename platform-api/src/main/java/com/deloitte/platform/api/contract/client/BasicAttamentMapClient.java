package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.BasicAttamentMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapForm;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-29
 * @Description :  BasicAttamentMap控制器接口
 * @Modified :
 */
public interface BasicAttamentMapClient {

    public static final String path="/contract/basic-attament-map";


    /**
     *  POST---新增
     * @param basicAttamentMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicAttamentMapForm basicAttamentMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicAttamentMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicAttamentMapForm basicAttamentMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicAttamentMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicAttamentMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicAttamentMapVo>> list(@Valid @RequestBody BasicAttamentMapQueryForm basicAttamentMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicAttamentMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicAttamentMapVo>> search(@Valid @RequestBody BasicAttamentMapQueryForm basicAttamentMapQueryForm);
}
