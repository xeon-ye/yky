package com.deloitte.platform.api.fssc.performance.client;

import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  绩效 指标库 控制器接口
 * @Modified :
 */
public interface PerformanceIndexLibraryClient {

    String path = "/fssc/performance-index-library";


    /**
     * POST---新增
     */
    @PostMapping(value = path + "/addOrUpdate")
    Result addOrUpdateIndexLibrary(@Valid @RequestBody List<PerformanceIndexLibraryForm> indexLibraryFormList);

    /**
     * Delete---删除
     */
    @DeleteMapping(value = path + "/delete")
    Result delete(@RequestBody List<Long> ids);

    /**
     * GET----根据ID获取
     */
    @GetMapping(value = path + "/get/{id}")
    Result<PerformanceIndexLibraryVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<PerformanceIndexLibraryVo>> list(
            @Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm);


    /**
     * POST----复杂查询
     */
    @PostMapping(value = path + "/page/conditions")
    Result<GdcPage<PerformanceIndexLibraryVo>> search(
            @Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm);
}
