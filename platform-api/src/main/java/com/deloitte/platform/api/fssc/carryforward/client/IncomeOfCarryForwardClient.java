package com.deloitte.platform.api.fssc.carryforward.client;

import com.deloitte.platform.api.fssc.carryforward.param.IncomeOfCarryForwardQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.IncomeOfCarryForwardForm;
import com.deloitte.platform.api.fssc.carryforward.vo.IncomeOfCarryForwardVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-06
 * @Description :  IncomeOfCarryForward控制器接口
 * @Modified :
 */
public interface IncomeOfCarryForwardClient {

    public static final String path="/fssc/income-of-carry-forward";


    /**
     *  POST---新增
     * @param incomeOfCarryForwardForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute IncomeOfCarryForwardForm incomeOfCarryForwardForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, incomeOfCarryForwardForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody IncomeOfCarryForwardForm incomeOfCarryForwardForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<IncomeOfCarryForwardVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   incomeOfCarryForwardQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<IncomeOfCarryForwardVo>> list(@Valid @RequestBody IncomeOfCarryForwardQueryForm incomeOfCarryForwardQueryForm);


    /**
     *  POST----复杂查询
     * @param  incomeOfCarryForwardQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<IncomeOfCarryForwardVo>> search(@Valid @RequestBody IncomeOfCarryForwardQueryForm incomeOfCarryForwardQueryForm);
}
