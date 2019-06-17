package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ProcessOperatorTransferQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessOperatorTransferForm;
import com.deloitte.platform.api.contract.vo.ProcessOperatorTransferVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  ProcessOperatorTransfer控制器接口
 * @Modified :
 */
public interface ProcessOperatorTransferClient {

    public static final String path="/contract/process-operator-transfer";


    /**
     *  POST---新增
     * @param processOperatorTransferForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessOperatorTransferForm processOperatorTransferForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processOperatorTransferForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessOperatorTransferForm processOperatorTransferForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessOperatorTransferVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processOperatorTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessOperatorTransferVo>> list(@Valid @RequestBody ProcessOperatorTransferQueryForm processOperatorTransferQueryForm);


    /**
     *  POST----复杂查询
     * @param  processOperatorTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessOperatorTransferVo>> search(@Valid @RequestBody ProcessOperatorTransferQueryForm processOperatorTransferQueryForm);
}
