package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.EvaluateQueryForm;
import com.deloitte.platform.api.contract.vo.EvaluateForm;
import com.deloitte.platform.api.contract.vo.EvaluateVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description :  Evaluate控制器接口
 * @Modified :
 */
public interface EvaluateClient {

    public static final String path="/contract/evaluate";


    /**
     *  POST---新增
     * @param evaluateForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EvaluateForm evaluateForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, evaluateForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EvaluateForm evaluateForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EvaluateVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   evaluateQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EvaluateVo>> list(@Valid @RequestBody EvaluateQueryForm evaluateQueryForm);


    /**
     *  POST----复杂查询
     * @param  evaluateQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EvaluateVo>> search(@Valid @RequestBody EvaluateQueryForm evaluateQueryForm);
}
