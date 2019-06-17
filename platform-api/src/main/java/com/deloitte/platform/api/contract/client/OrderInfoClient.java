package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.OrderInfoQueryForm;
import com.deloitte.platform.api.contract.vo.OrderInfoForm;
import com.deloitte.platform.api.contract.vo.OrderInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  OrderInfo控制器接口
 * @Modified :
 */
public interface OrderInfoClient {

    public static final String path="/contract/order-info";


    /**
     *  POST---新增
     * @param orderInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OrderInfoForm orderInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, orderInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OrderInfoForm orderInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OrderInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   orderInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OrderInfoVo>> list(@Valid @RequestBody OrderInfoQueryForm orderInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  orderInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OrderInfoVo>> search(@Valid @RequestBody OrderInfoQueryForm orderInfoQueryForm);
}
