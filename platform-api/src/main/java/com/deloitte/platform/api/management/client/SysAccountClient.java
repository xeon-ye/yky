package com.deloitte.platform.api.management.client;

import com.deloitte.platform.api.management.param.SysAccountQueryForm;
import com.deloitte.platform.api.management.vo.AccountInfoVo;
import com.deloitte.platform.api.management.vo.SysAccountForm;
import com.deloitte.platform.api.management.vo.SysAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jack
 * @Date : Create in 2019-04-18
 * @Description :  SysAccount控制器接口
 * @Modified :
 */
public interface SysAccountClient {

    public static final String path="/management/sys-account";


    /**
     *  POST---新增
     * @param sysAccountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysAccountForm sysAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysAccountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysAccountForm sysAccountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysAccountVo> get(@PathVariable(value="id") long id);


    /**
     *  GET----根据name获取
     * @param  name
     * @return
     */
    @GetMapping(value = path+"/{name}")
    Result<AccountInfoVo> getByName(@PathVariable(value="name") String  name);

    /**
     *  POST----列表查询
     * @param   sysAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysAccountVo>> list(@Valid @RequestBody SysAccountQueryForm sysAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysAccountVo>> search(@Valid @RequestBody SysAccountQueryForm sysAccountQueryForm);
}
