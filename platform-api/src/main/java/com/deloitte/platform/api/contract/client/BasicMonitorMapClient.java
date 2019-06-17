package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.BasicMonitorMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicMonitorMapForm;
import com.deloitte.platform.api.contract.vo.BasicMonitorMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicMonitorMap控制器接口
 * @Modified :
 */
public interface BasicMonitorMapClient {

    public static final String path="/contract/basic-monitor-map";


    /**
     *  POST---新增
     * @param basicMonitorMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicMonitorMapForm basicMonitorMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicMonitorMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicMonitorMapForm basicMonitorMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicMonitorMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicMonitorMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicMonitorMapVo>> list(@Valid @RequestBody BasicMonitorMapQueryForm basicMonitorMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicMonitorMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicMonitorMapVo>> search(@Valid @RequestBody BasicMonitorMapQueryForm basicMonitorMapQueryForm);
}
