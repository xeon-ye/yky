package com.deloitte.platform.api.fssc.performance.client;

import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexValueQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  绩效 指标 控制器接口
 * @Modified :
 */
public interface PerformanceIndexValueClient {

    String path = "/fssc/performance-index-value";


    /**
     * POST---新增
     */
    @PostMapping(value = path + "/addOrUpdate")
    Result addOrUpdateIndexValue(@Valid @RequestBody List<PerformanceIndexValueForm> indexFormList);

    /**
     * Delete---删除
     */
    @DeleteMapping(value = path + "/delete")
    Result delete(@RequestBody List<Long> ids);

    /**
     * GET----根据ID获取
     */
    @GetMapping(value = path + "/get/{id}")
    Result<PerformanceIndexValueVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<PerformanceIndexValueVo>> list(
            @Valid @RequestBody PerformanceIndexValueQueryForm queryForm);


    /**
     * POST----复杂查询
     */
    @PostMapping(value = path + "/page/conditions")
    Result<GdcPage<PerformanceIndexValueVo>> search(
            @Valid @RequestBody PerformanceIndexValueQueryForm queryForm);
}
