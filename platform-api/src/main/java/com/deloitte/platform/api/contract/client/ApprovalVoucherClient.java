package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ApprovalVoucherQueryForm;
import com.deloitte.platform.api.contract.vo.ApprovalVoucherForm;
import com.deloitte.platform.api.contract.vo.ApprovalVoucherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-27
 * @Description :  ApprovalVoucher控制器接口
 * @Modified :
 */
public interface ApprovalVoucherClient {

    public static final String path="/contract/approval-voucher";


    /**
     *  POST---新增
     * @param approvalVoucherForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ApprovalVoucherForm approvalVoucherForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, approvalVoucherForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ApprovalVoucherForm approvalVoucherForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ApprovalVoucherVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   approvalVoucherQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ApprovalVoucherVo>> list(@Valid @RequestBody ApprovalVoucherQueryForm approvalVoucherQueryForm);


    /**
     *  POST----复杂查询
     * @param  approvalVoucherQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ApprovalVoucherVo>> search(@Valid @RequestBody ApprovalVoucherQueryForm approvalVoucherQueryForm);
}
