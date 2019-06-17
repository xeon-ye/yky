package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SysWatermarkQueryForm;
import com.deloitte.platform.api.contract.vo.SysWatermarkForm;
import com.deloitte.platform.api.contract.vo.SysWatermarkVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysWatermark控制器接口
 * @Modified :
 */
public interface SysWatermarkClient {

    public static final String path="/contract/sys-watermark";


    /**
     *  POST---新增
     * @param sysWatermarkForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysWatermarkForm sysWatermarkForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysWatermarkForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysWatermarkForm sysWatermarkForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysWatermarkVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   sysWatermarkQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysWatermarkVo>> list(@Valid @RequestBody SysWatermarkQueryForm sysWatermarkQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysWatermarkQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysWatermarkVo>> search(@Valid @RequestBody SysWatermarkQueryForm sysWatermarkQueryForm);
}
