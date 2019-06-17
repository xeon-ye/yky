package com.deloitte.platform.api.project.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.BudgetBakQueryForm;
import com.deloitte.platform.api.project.vo.BudgetBakForm;
import com.deloitte.platform.api.project.vo.BudgetBakVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :  BudgetBak控制器接口
 * @Modified :
 */
public interface BudgetBakClient {

    public static final String path="/project/budget-bak";


    /**
     *  POST---新增
     * @param budgetBakForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BudgetBakForm budgetBakForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, budgetBakForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody BudgetBakForm budgetBakForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BudgetBakVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   budgetBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BudgetBakVo>> list(@Valid @RequestBody BudgetBakQueryForm budgetBakQueryForm);


    /**
     *  POST----复杂查询
     * @param  budgetBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BudgetBakVo>> search(@Valid @RequestBody BudgetBakQueryForm budgetBakQueryForm);
}
