package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SysProjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysProjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysProjectInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysProjectInfo控制器接口
 * @Modified :
 */
public interface SysProjectInfoClient {

    public static final String path="/contract/sys-project-info";


    /**
     *  POST---新增
     * @param sysProjectInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysProjectInfoForm sysProjectInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysProjectInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysProjectInfoForm sysProjectInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysProjectInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   sysProjectInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysProjectInfoVo>> list(@Valid @RequestBody SysProjectInfoQueryForm sysProjectInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysProjectInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysProjectInfoVo>> search(@Valid @RequestBody SysProjectInfoQueryForm sysProjectInfoQueryForm);
}
