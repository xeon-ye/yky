package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ProcessQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessForm;
import com.deloitte.platform.api.contract.vo.ProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-30
 * @Description :  Process控制器接口
 * @Modified :
 */
public interface ProcessClient {

    public static final String path="/contract/process";


    /**
     *  POST---新增
     * @param processForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessForm processForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessForm processForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessVo>> list(@Valid @RequestBody ProcessQueryForm processQueryForm);


    /**
     *  POST----复杂查询
     * @param  processQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessVo>> search(@Valid @RequestBody ProcessQueryForm processQueryForm);
}
