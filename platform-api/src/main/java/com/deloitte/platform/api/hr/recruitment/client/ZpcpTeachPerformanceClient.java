package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTeachPerformanceQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTeachPerformanceForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTeachPerformanceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpTeachPerformance控制器接口
 * @Modified :
 */
public interface ZpcpTeachPerformanceClient {

    public static final String path="/hr/zpcp-teach-performance";


    /**
     *  POST---新增
     * @param zpcpTeachPerformanceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpTeachPerformanceForm zpcpTeachPerformanceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpTeachPerformanceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpTeachPerformanceForm zpcpTeachPerformanceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpTeachPerformanceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpTeachPerformanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpTeachPerformanceVo>> list(@Valid @RequestBody ZpcpTeachPerformanceQueryForm zpcpTeachPerformanceQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpTeachPerformanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpTeachPerformanceVo>> search(@Valid @RequestBody ZpcpTeachPerformanceQueryForm zpcpTeachPerformanceQueryForm);


    /**
     *  POST---批量新增或更新
     * @param teachPerformanceForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpTeachPerformanceForm> teachPerformanceForms);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
