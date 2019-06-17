package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.MaintBudgetQueryForm;
import com.deloitte.platform.api.project.vo.MaintBudgetForm;
import com.deloitte.platform.api.project.vo.MaintBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  MaintBudget控制器接口
 * @Modified :
 */
public interface MaintBudgetClient {

    public static final String path="/project/maint-budget";


    /**
     *  POST---新增
     * @param maintBudgetForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute MaintBudgetForm maintBudgetForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, maintBudgetForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody MaintBudgetForm maintBudgetForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<MaintBudgetVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   maintBudgetQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<MaintBudgetVo>> list(@Valid @RequestBody MaintBudgetQueryForm maintBudgetQueryForm);


    /**
     *  POST----复杂查询
     * @param  maintBudgetQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<MaintBudgetVo>> search(@Valid @RequestBody MaintBudgetQueryForm maintBudgetQueryForm);
}
