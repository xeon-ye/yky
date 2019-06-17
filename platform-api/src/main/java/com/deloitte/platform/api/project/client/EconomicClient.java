package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.EconomicQueryForm;
import com.deloitte.platform.api.project.vo.EconomicForm;
import com.deloitte.platform.api.project.vo.EconomicVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description :  Economic控制器接口
 * @Modified :
 */
public interface EconomicClient {

    public static final String path="/project/economic";


    /**
     *  POST---新增
     * @param economicForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EconomicForm economicForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, economicForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EconomicForm economicForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EconomicVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   economicQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EconomicVo>> list(@Valid @RequestBody EconomicQueryForm economicQueryForm);


    /**
     *  POST----复杂查询
     * @param  economicQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EconomicVo>> search(@Valid @RequestBody EconomicQueryForm economicQueryForm);
}
