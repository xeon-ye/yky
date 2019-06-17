package com.deloitte.platform.api.fssc.performance.client;

import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  绩效 项目小类 控制器接口
 * @Modified :
 */
public interface PerformancePrjSubTypeClient {

    String path = "/fssc/performance-prj-sub-type";

    /**
     * POST---新增
     */
    @PostMapping(value = path + "/addOrUpdate")
    Result addOrUpdateSubType(@Valid @RequestBody List<PerformancePrjSubTypeForm> subTypeFormList);

    /**
     * Delete---删除
     */
    @DeleteMapping(value = path + "/{deleteByIds}")
    Result delete(@RequestBody List<Long> ids);

    /**
     * GET----根据ID获取
     */
    @GetMapping(value = path + "/{id}")
    Result<PerformancePrjSubTypeVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<PerformancePrjSubTypeVo>> list(
            @Valid @RequestBody PerformancePrjSubTypeQueryForm performancePrjSubTypeQueryForm);


    /**
     * POST----复杂查询
     */
    @PostMapping(value = path + "/page/conditions")
    Result<GdcPage<PerformancePrjSubTypeVo>> search(
            @Valid @RequestBody PerformancePrjSubTypeQueryForm performancePrjSubTypeQueryForm);
}
