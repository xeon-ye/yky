package com.deloitte.platform.api.fssc.bpm;

import com.deloitte.platform.api.fssc.bpm.param.ProcessDefQueryForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessDefForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessDefVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description :  ProcessDef控制器接口
 * @Modified :
 */
public interface ProcessDefClient {

    public static final String path="/bpm/process-def";


    /**
     *  POST---新增
     * @param processDefForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessDefForm processDefForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processDefForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessDefForm processDefForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessDefVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processDefQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessDefVo>> list(@Valid @RequestBody ProcessDefQueryForm processDefQueryForm);


    /**
     *  POST----复杂查询
     * @param  processDefQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessDefVo>> search(@Valid @RequestBody ProcessDefQueryForm processDefQueryForm);
}
