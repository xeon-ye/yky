package com.deloitte.platform.api.fssc.performance.client;

import com.deloitte.platform.api.fssc.performance.param.PerformancePrjMainTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  绩效 项目大类 控制器接口
 * @Modified :
 */
public interface PerformancePrjMainTypeClient {

    String path = "/fssc/performance-prj-main-type";


    /**
     * POST---新增
     */
    @PostMapping(value = path + "/addOrUpdate")
    Result addOrUpdateMainType(@Valid @RequestBody List<PerformancePrjMainTypeForm> mainTypeFormList);

    /**
     * Delete---删除
     */
    @DeleteMapping(value = "/deleteByIds")
    Result delete(@RequestBody List<Long> ids);

    /**
     * GET----根据ID获取
     */
    @GetMapping(value = path + "/{id}")
    Result<PerformancePrjMainTypeVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<PerformancePrjMainTypeVo>> list(
            @Valid @RequestBody PerformancePrjMainTypeQueryForm performancePrjMainTypeQueryForm);


    /**
     * POST----复杂查询
     */
    @PostMapping(value = path + "/page/conditions")
    Result<GdcPage<PerformancePrjMainTypeVo>> search(
            @Valid @RequestBody PerformancePrjMainTypeQueryForm performancePrjMainTypeQueryForm);
}
