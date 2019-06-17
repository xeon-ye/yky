package com.deloitte.platform.api.fssc.performance.client;

import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :  PerformanceIndex控制器接口
 * @Modified :
 */
public interface PerformanceIndexClient {

    String path="/fssc/performance-index";


    /**
     *  POST---新增
     * @param indexFormList
     * @return
     */
    @PostMapping(value = path + "/addOrUpdateIndex")
    Result addOrUpdateIndex(@Valid @RequestBody List<PerformanceIndexForm> indexFormList);

    /**
    *  Delete---删除
    * @param  ids
    * @return
    */
    @DeleteMapping(value = path+"/delete")
    Result delete(@RequestBody List<Long> ids);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/get/{id}")
    Result<PerformanceIndexVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询 预算绩效指标定义
     * @param   indexQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PerformanceIndexVo>> list(@Valid @RequestBody PerformanceIndexQueryForm indexQueryForm);


    /**
     *  POST----复杂查询
     * @param  indexQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<GdcPage<PerformanceIndexVo>> search(@Valid @RequestBody PerformanceIndexQueryForm indexQueryForm);

    /**
     * 根据指标库查询包含一、二、三级指标无RootNode的树形结构的数据
     * @param libraryId
     * @return
     */
    @PostMapping(value = path+"/searchByLibraryId")
    Result<List<PerformanceIndexVo>> search(@RequestParam(value = "libraryId",required = true) Long libraryId);

}
