package com.deloitte.platform.api.fssc.poi.client;

import com.deloitte.platform.api.fssc.poi.param.PepaymentOrderInfoQueryForm;
import com.deloitte.platform.api.fssc.poi.vo.PepaymentOrderInfoForm;
import com.deloitte.platform.api.fssc.poi.vo.PepaymentOrderInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-05-13
 * @Description :  PepaymentOrderInfo控制器接口
 * @Modified :
 */
public interface PepaymentOrderInfoClient {

    public static final String path="/poi/pepayment-order-info";


    /**
     *  POST---新增
     * @param pepaymentOrderInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PepaymentOrderInfoForm pepaymentOrderInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, pepaymentOrderInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PepaymentOrderInfoForm pepaymentOrderInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PepaymentOrderInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   pepaymentOrderInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PepaymentOrderInfoVo>> list(@Valid @RequestBody PepaymentOrderInfoQueryForm pepaymentOrderInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  pepaymentOrderInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PepaymentOrderInfoVo>> search(@Valid @RequestBody PepaymentOrderInfoQueryForm pepaymentOrderInfoQueryForm);
}
