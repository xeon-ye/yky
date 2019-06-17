package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ProcessExecuterTransferQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessExecuterTransferForm;
import com.deloitte.platform.api.contract.vo.ProcessExecuterTransferVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  ProcessExecuterTransfer控制器接口
 * @Modified :
 */
public interface ProcessExecuterTransferClient {

    public static final String path="/contract/process-executer-transfer";


    /**
     *  POST---新增
     * @param processExecuterTransferForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessExecuterTransferForm processExecuterTransferForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processExecuterTransferForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessExecuterTransferForm processExecuterTransferForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessExecuterTransferVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processExecuterTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessExecuterTransferVo>> list(@Valid @RequestBody ProcessExecuterTransferQueryForm processExecuterTransferQueryForm);


    /**
     *  POST----复杂查询
     * @param  processExecuterTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessExecuterTransferVo>> search(@Valid @RequestBody ProcessExecuterTransferQueryForm processExecuterTransferQueryForm);
}
