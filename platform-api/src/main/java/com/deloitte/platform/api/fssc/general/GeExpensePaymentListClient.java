package com.deloitte.platform.api.fssc.general;

import com.deloitte.platform.api.fssc.general.param.GeExpensePaymentListQueryForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeExpensePaymentList控制器接口
 * @Modified :
 */
public interface GeExpensePaymentListClient {

    public static final String path="/general/ge-expense-payment-list";


    /**
     *  POST---新增
     * @param geExpensePaymentListForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GeExpensePaymentListForm geExpensePaymentListForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, geExpensePaymentListForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody GeExpensePaymentListForm geExpensePaymentListForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GeExpensePaymentListVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   geExpensePaymentListQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GeExpensePaymentListVo>> list(@Valid @RequestBody GeExpensePaymentListQueryForm geExpensePaymentListQueryForm);


    /**
     *  POST----复杂查询
     * @param  geExpensePaymentListQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GeExpensePaymentListVo>> search(@Valid @RequestBody GeExpensePaymentListQueryForm geExpensePaymentListQueryForm);
}
