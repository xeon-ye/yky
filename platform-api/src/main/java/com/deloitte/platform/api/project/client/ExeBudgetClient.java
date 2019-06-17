package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ExeBudgetQueryForm;
import com.deloitte.platform.api.project.vo.ExeBudgetForm;
import com.deloitte.platform.api.project.vo.ExeBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ExeBudget控制器接口
 * @Modified :
 */
public interface ExeBudgetClient {

    public static final String path="/project/exe-budget";


    /**
     *  POST---新增
     * @param exeBudgetForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ExeBudgetForm exeBudgetForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, exeBudgetForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ExeBudgetForm exeBudgetForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ExeBudgetVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   exeBudgetQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ExeBudgetVo>> list(@Valid @RequestBody ExeBudgetQueryForm exeBudgetQueryForm);


    /**
     *  POST----复杂查询
     * @param  exeBudgetQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ExeBudgetVo>> search(@Valid @RequestBody ExeBudgetQueryForm exeBudgetQueryForm);
}
