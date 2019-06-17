package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.MonitorInfoQueryForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  MonitorInfo控制器接口
 * @Modified :
 */
public interface MonitorInfoClient {

    public static final String path="/contract/monitor-info";


    /**
     *  POST---新增
     * @param monitorInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute MonitorInfoForm monitorInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, monitorInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody MonitorInfoForm monitorInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<MonitorInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   monitorInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<MonitorInfoVo>> list(@Valid @RequestBody MonitorInfoQueryForm monitorInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  monitorInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<MonitorInfoVo>> search(@Valid @RequestBody MonitorInfoQueryForm monitorInfoQueryForm);
}
