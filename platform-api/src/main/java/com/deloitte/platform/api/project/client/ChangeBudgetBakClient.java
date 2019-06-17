package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ChangeBudgetBakQueryForm;
import com.deloitte.platform.api.project.vo.ChangeBudgetBakForm;
import com.deloitte.platform.api.project.vo.ChangeBudgetBakVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ChangeBudgetBak控制器接口
 * @Modified :
 */
public interface ChangeBudgetBakClient {

    public static final String path="/project/change-budget-bak";


    /**
     *  POST---新增
     * @param changeBudgetBakForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ChangeBudgetBakForm changeBudgetBakForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, changeBudgetBakForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ChangeBudgetBakForm changeBudgetBakForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ChangeBudgetBakVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   changeBudgetBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ChangeBudgetBakVo>> list(@Valid @RequestBody ChangeBudgetBakQueryForm changeBudgetBakQueryForm);


    /**
     *  POST----复杂查询
     * @param  changeBudgetBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ChangeBudgetBakVo>> search(@Valid @RequestBody ChangeBudgetBakQueryForm changeBudgetBakQueryForm);
}
