package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.BudgetQueryForm;
import com.deloitte.platform.api.project.vo.BudgetForm;
import com.deloitte.platform.api.project.vo.BudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :  Budget控制器接口
 * @Modified :
 */
public interface BudgetClient {

    public static final String path="/project/budget";


    /**
     *  POST---新增
     * @param budgetForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BudgetForm budgetForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, budgetForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody BudgetForm budgetForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{applicationId}")
    Result get(@PathVariable(value = "applicationId") String applicationId);


    /**
     *  POST----列表查询
     * @param   budgetQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BudgetVo>> list(@Valid @RequestBody BudgetQueryForm budgetQueryForm);


    /**
     *  POST----复杂查询
     * @param  budgetQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BudgetVo>> search(@Valid @RequestBody BudgetQueryForm budgetQueryForm);
}
