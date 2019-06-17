package com.deloitte.platform.api.srpmp.project.budget;

import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description :  SrpmsProjectBudgetAccount控制器接口
 * @Modified :
 */
public interface SrpmsProjectBudgetAccountClient {

    public static final String path="/srpmp/project/budget/account";


    /**
     *  POST---新增
     * @param srpmsProjectBudgetAccountForm
     * @return
     */
    @PostMapping(value = path + "/saveOrUpdate")
    Result saveOrUpdate(@Valid @ModelAttribute SrpmsProjectBudgetAccountForm srpmsProjectBudgetAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/delete/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/get/{id}")
    Result<SrpmsProjectBudgetAccountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   srpmsProjectBudgetAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SrpmsProjectBudgetAccountVo>> list(@Valid @RequestBody SrpmsProjectBudgetAccountQueryForm srpmsProjectBudgetAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  srpmsProjectBudgetAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SrpmsProjectBudgetAccountVo>> search(@Valid @RequestBody SrpmsProjectBudgetAccountQueryForm srpmsProjectBudgetAccountQueryForm);
}
