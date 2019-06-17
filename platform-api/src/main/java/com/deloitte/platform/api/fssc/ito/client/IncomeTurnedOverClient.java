package com.deloitte.platform.api.fssc.ito.client;

import com.deloitte.platform.api.fssc.ito.param.IncomeTurnedOverQueryForm;
import com.deloitte.platform.api.fssc.ito.vo.IncomeTurnedOverForm;
import com.deloitte.platform.api.fssc.ito.vo.IncomeTurnedOverVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description :  IncomeTurnedOver控制器接口
 * @Modified :
 */
public interface IncomeTurnedOverClient {

    public static final String path="/ito/income-turned-over";


    /**
     *  POST---新增
     * @param incomeTurnedOverForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute IncomeTurnedOverForm incomeTurnedOverForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, incomeTurnedOverForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody IncomeTurnedOverForm incomeTurnedOverForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<IncomeTurnedOverVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   incomeTurnedOverQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<IncomeTurnedOverVo>> list(@Valid @RequestBody IncomeTurnedOverQueryForm incomeTurnedOverQueryForm);


    /**
     *  POST----复杂查询
     * @param  incomeTurnedOverQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<IncomeTurnedOverVo>> search(@Valid @RequestBody IncomeTurnedOverQueryForm incomeTurnedOverQueryForm);
}
