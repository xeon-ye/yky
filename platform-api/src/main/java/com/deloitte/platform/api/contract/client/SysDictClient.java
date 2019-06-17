package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SysDictQueryForm;
import com.deloitte.platform.api.contract.vo.SysDictForm;
import com.deloitte.platform.api.contract.vo.SysDictVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysDict控制器接口
 * @Modified :
 */
public interface SysDictClient {

    public static final String path="/contract/sys-dict";


    /**
     *  POST---新增
     * @param sysDictForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysDictForm sysDictForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysDictForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysDictForm sysDictForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysDictVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   sysDictQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysDictVo>> list(@Valid @RequestBody SysDictQueryForm sysDictQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysDictQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysDictVo>> search(@Valid @RequestBody SysDictQueryForm sysDictQueryForm);
}
