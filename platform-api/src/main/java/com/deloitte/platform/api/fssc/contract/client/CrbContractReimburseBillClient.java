package com.deloitte.platform.api.fssc.contract.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.contract.param.CrbContractReimburseBillQueryForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbContractReimburseBillForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbContractReimburseBillVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description :  CrbContractReimburseBill控制器接口
 * @Modified :
 */
public interface CrbContractReimburseBillClient {

    public static final String path="/contract/crb-contract-reimburse-bill";


    /**
     *  POST---新增
     * @param crbContractReimburseBillForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CrbContractReimburseBillForm crbContractReimburseBillForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, crbContractReimburseBillForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody CrbContractReimburseBillForm crbContractReimburseBillForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CrbContractReimburseBillVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   crbContractReimburseBillQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CrbContractReimburseBillVo>> list(@Valid @RequestBody CrbContractReimburseBillQueryForm crbContractReimburseBillQueryForm);


    /**
     *  POST----复杂查询
     * @param  crbContractReimburseBillQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CrbContractReimburseBillVo>> search(@Valid @RequestBody CrbContractReimburseBillQueryForm crbContractReimburseBillQueryForm);
}
