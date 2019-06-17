package com.deloitte.platform.api.project.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExecutionQueryForm;
import com.deloitte.platform.api.project.vo.ExecutionForm;
import com.deloitte.platform.api.project.vo.ExecutionVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  Execution控制器接口
 * @Modified :
 */
public interface ExecutionClient {

    public static final String path="/project/execution";

    /**
     *  POST---新增
     * @param executionForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ExecutionForm executionForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, executionForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ExecutionForm executionForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ExecutionVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   executionQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ExecutionVo>> list(@Valid @RequestBody ExecutionQueryForm executionQueryForm);


    /**
     *  POST----复杂查询
     * @param  executionQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ExecutionVo>> search(@Valid @RequestBody ExecutionQueryForm executionQueryForm);

}
