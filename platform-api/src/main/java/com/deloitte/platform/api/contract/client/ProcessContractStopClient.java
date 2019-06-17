package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ProcessContractStopQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessContractStopForm;
import com.deloitte.platform.api.contract.vo.ProcessContractStopVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  ProcessContractStop控制器接口
 * @Modified :
 */
public interface ProcessContractStopClient {

    public static final String path="/contract/process-contract-stop";


    /**
     *  POST---新增
     * @param processContractStopForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessContractStopForm processContractStopForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processContractStopForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessContractStopForm processContractStopForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessContractStopVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processContractStopQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessContractStopVo>> list(@Valid @RequestBody ProcessContractStopQueryForm processContractStopQueryForm);


    /**
     *  POST----复杂查询
     * @param  processContractStopQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessContractStopVo>> search(@Valid @RequestBody ProcessContractStopQueryForm processContractStopQueryForm);
}
