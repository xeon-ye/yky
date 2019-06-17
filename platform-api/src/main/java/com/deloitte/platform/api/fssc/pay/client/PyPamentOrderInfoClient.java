package com.deloitte.platform.api.fssc.pay.client;

import com.deloitte.platform.api.fssc.pay.param.PyPamentOrderInfoQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentOrderInfo控制器接口
 * @Modified :
 */
public interface PyPamentOrderInfoClient {

    public static final String path="/pay/py-pament-order-info";


    /**
     *  POST---新增
     * @param pyPamentOrderInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PyPamentOrderInfoForm pyPamentOrderInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, pyPamentOrderInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PyPamentOrderInfoForm pyPamentOrderInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PyPamentOrderInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   pyPamentOrderInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PyPamentOrderInfoVo>> list(@Valid @RequestBody PyPamentOrderInfoQueryForm pyPamentOrderInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  pyPamentOrderInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PyPamentOrderInfoVo>> search(@Valid @RequestBody PyPamentOrderInfoQueryForm pyPamentOrderInfoQueryForm);
}
