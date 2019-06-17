package com.deloitte.platform.api.fssc.advance;

import com.deloitte.platform.api.fssc.advance.param.AdvancePaymentInfoQueryForm;
import com.deloitte.platform.api.fssc.advance.vo.AdvancePaymentInfoForm;
import com.deloitte.platform.api.fssc.advance.vo.AdvancePaymentInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmAdvancePaymentInfo控制器接口
 * @Modified :
 */
public interface AdvancePaymentInfoClient {

    public static final String path="/advance/bm-advance-payment-info";


    /**
     *  POST---新增
     * @param advancePaymentInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AdvancePaymentInfoForm advancePaymentInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, advancePaymentInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AdvancePaymentInfoForm advancePaymentInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AdvancePaymentInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   bmAdvancePaymentInfoForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AdvancePaymentInfoVo>> list(@Valid @RequestBody AdvancePaymentInfoQueryForm advancePaymentInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  advancePaymentInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AdvancePaymentInfoVo>> search(@Valid @RequestBody AdvancePaymentInfoQueryForm advancePaymentInfoQueryForm);
}
