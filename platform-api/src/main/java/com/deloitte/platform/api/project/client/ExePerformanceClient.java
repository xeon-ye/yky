package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ExePerformanceQueryForm;
import com.deloitte.platform.api.project.vo.ExePerformanceForm;
import com.deloitte.platform.api.project.vo.ExePerformanceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ExePerformance控制器接口
 * @Modified :
 */
public interface ExePerformanceClient {

    public static final String path="/project/exe-performance";


    /**
     *  POST---新增
     * @param exePerformanceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ExePerformanceForm exePerformanceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, exePerformanceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ExePerformanceForm exePerformanceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ExePerformanceVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   exePerformanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ExePerformanceVo>> list(@Valid @RequestBody ExePerformanceQueryForm exePerformanceQueryForm);


    /**
     *  POST----复杂查询
     * @param  exePerformanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ExePerformanceVo>> search(@Valid @RequestBody ExePerformanceQueryForm exePerformanceQueryForm);
}
