package com.deloitte.platform.api.fssc.pe.client;

import com.deloitte.platform.api.fssc.pe.param.PerSelfTargetQueryForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfTargetForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfTargetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :  PerSelfTarget控制器接口
 * @Modified :
 */
public interface PerSelfTargetClient {

    public static final String path="/pe/per-self-target";


    /**
     *  POST---新增
     * @param perSelfTargetForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PerSelfTargetForm perSelfTargetForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, perSelfTargetForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PerSelfTargetForm perSelfTargetForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PerSelfTargetVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   perSelfTargetQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PerSelfTargetVo>> list(@Valid @RequestBody PerSelfTargetQueryForm perSelfTargetQueryForm);


    /**
     *  POST----复杂查询
     * @param  perSelfTargetQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PerSelfTargetVo>> search(@Valid @RequestBody PerSelfTargetQueryForm perSelfTargetQueryForm);
}
