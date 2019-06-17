package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.FinanceInfoQueryForm;
import com.deloitte.platform.api.contract.vo.FinanceInfoForm;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  FinanceInfo控制器接口
 * @Modified :
 */
public interface FinanceInfoClient {

    public static final String path="/contract/finance-info";


    /**
     *  POST---新增
     * @param financeInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute FinanceInfoForm financeInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, financeInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody FinanceInfoForm financeInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<FinanceInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   financeInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<FinanceInfoVo>> list(@Valid @RequestBody FinanceInfoQueryForm financeInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  financeInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<FinanceInfoVo>> search(@Valid @RequestBody FinanceInfoQueryForm financeInfoQueryForm);
}
