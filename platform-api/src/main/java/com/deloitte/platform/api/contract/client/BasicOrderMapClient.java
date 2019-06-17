package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.BasicOrderMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicOrderMapForm;
import com.deloitte.platform.api.contract.vo.BasicOrderMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicOrderMap控制器接口
 * @Modified :
 */
public interface BasicOrderMapClient {

    public static final String path="/contract/basic-order-map";


    /**
     *  POST---新增
     * @param basicOrderMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicOrderMapForm basicOrderMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicOrderMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicOrderMapForm basicOrderMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicOrderMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicOrderMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicOrderMapVo>> list(@Valid @RequestBody BasicOrderMapQueryForm basicOrderMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicOrderMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicOrderMapVo>> search(@Valid @RequestBody BasicOrderMapQueryForm basicOrderMapQueryForm);
}
