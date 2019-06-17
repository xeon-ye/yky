package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.PerformanceBakQueryForm;
import com.deloitte.platform.api.project.vo.PerformanceBakForm;
import com.deloitte.platform.api.project.vo.PerformanceBakVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  PerformanceBak控制器接口
 * @Modified :
 */
public interface PerformanceBakClient {

    public static final String path="/project/performance-bak";


    /**
     *  POST---新增
     * @param performanceBakForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PerformanceBakForm performanceBakForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, performanceBakForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PerformanceBakForm performanceBakForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PerformanceBakVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   performanceBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PerformanceBakVo>> list(@Valid @RequestBody PerformanceBakQueryForm performanceBakQueryForm);


    /**
     *  POST----复杂查询
     * @param  performanceBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PerformanceBakVo>> search(@Valid @RequestBody PerformanceBakQueryForm performanceBakQueryForm);
}
