package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SysCommonLanguageQueryForm;
import com.deloitte.platform.api.contract.vo.SysCommonLanguageForm;
import com.deloitte.platform.api.contract.vo.SysCommonLanguageVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysCommonLanguage控制器接口
 * @Modified :
 */
public interface SysCommonLanguageClient {

    public static final String path="/contract/sys-common-language";


    /**
     *  POST---新增
     * @param sysCommonLanguageForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysCommonLanguageForm sysCommonLanguageForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysCommonLanguageForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysCommonLanguageForm sysCommonLanguageForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysCommonLanguageVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   sysCommonLanguageQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysCommonLanguageVo>> list(@Valid @RequestBody SysCommonLanguageQueryForm sysCommonLanguageQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysCommonLanguageQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysCommonLanguageVo>> search(@Valid @RequestBody SysCommonLanguageQueryForm sysCommonLanguageQueryForm);
}
