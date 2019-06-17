package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SysContractNoQueryForm;
import com.deloitte.platform.api.contract.vo.SysContractNoForm;
import com.deloitte.platform.api.contract.vo.SysContractNoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysContractNo控制器接口
 * @Modified :
 */
public interface SysContractNoClient {

    public static final String path="/contract/sys-contract-no";


    /**
     *  POST---新增
     * @param sysContractNoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysContractNoForm sysContractNoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysContractNoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysContractNoForm sysContractNoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysContractNoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   sysContractNoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysContractNoVo>> list(@Valid @RequestBody SysContractNoQueryForm sysContractNoQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysContractNoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysContractNoVo>> search(@Valid @RequestBody SysContractNoQueryForm sysContractNoQueryForm);
}
