package com.deloitte.platform.api.fssc.bpm;

import com.deloitte.platform.api.fssc.bpm.param.EventDefQueryForm;
import com.deloitte.platform.api.fssc.bpm.vo.EventDefForm;
import com.deloitte.platform.api.fssc.bpm.vo.EventDefVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description :  EventDef控制器接口
 * @Modified :
 */
public interface EventDefClient {

    public static final String path="/bpm/event-def";


    /**
     *  POST---新增
     * @param eventDefForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EventDefForm eventDefForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, eventDefForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EventDefForm eventDefForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EventDefVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   eventDefQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EventDefVo>> list(@Valid @RequestBody EventDefQueryForm eventDefQueryForm);


    /**
     *  POST----复杂查询
     * @param  eventDefQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EventDefVo>> search(@Valid @RequestBody EventDefQueryForm eventDefQueryForm);
}
