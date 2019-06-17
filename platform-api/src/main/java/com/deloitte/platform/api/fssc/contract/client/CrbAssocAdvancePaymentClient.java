package com.deloitte.platform.api.fssc.contract.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.contract.param.CrbAssocAdvancePaymentQueryForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbAssocAdvancePaymentForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbAssocAdvancePaymentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description :  CrbAssocAdvancePayment控制器接口
 * @Modified :
 */
public interface CrbAssocAdvancePaymentClient {

    public static final String path="/contract/crb-assoc-advance-payment";


    /**
     *  POST---新增
     * @param crbAssocAdvancePaymentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CrbAssocAdvancePaymentForm crbAssocAdvancePaymentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, crbAssocAdvancePaymentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody CrbAssocAdvancePaymentForm crbAssocAdvancePaymentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CrbAssocAdvancePaymentVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   crbAssocAdvancePaymentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CrbAssocAdvancePaymentVo>> list(@Valid @RequestBody CrbAssocAdvancePaymentQueryForm crbAssocAdvancePaymentQueryForm);


    /**
     *  POST----复杂查询
     * @param  crbAssocAdvancePaymentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CrbAssocAdvancePaymentVo>> search(@Valid @RequestBody CrbAssocAdvancePaymentQueryForm crbAssocAdvancePaymentQueryForm);
}
