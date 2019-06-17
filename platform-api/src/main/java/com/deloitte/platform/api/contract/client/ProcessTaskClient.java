package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessTaskForm;
import com.deloitte.platform.api.contract.vo.ProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-02
 * @Description :  ProcessTask控制器接口
 * @Modified :
 */
public interface ProcessTaskClient {

    public static final String path="/contract/process-task";


    /**
     *  POST---新增
     * @param processTaskForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessTaskForm processTaskForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processTaskForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessTaskForm processTaskForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessTaskVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processTaskQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessTaskVo>> list(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm);


    /**
     *  POST----复杂查询
     * @param  processTaskQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessTaskVo>> search(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm);
}
