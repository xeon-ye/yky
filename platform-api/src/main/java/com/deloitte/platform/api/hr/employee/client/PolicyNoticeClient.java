package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.PolicyNoticeQueryForm;
import com.deloitte.platform.api.hr.employee.vo.PolicyNoticeForm;
import com.deloitte.platform.api.hr.employee.vo.PolicyNoticeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.rmi.ServerException;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-04
 * @Description :  PolicyNotice控制器接口
 * @Modified :
 */
public interface PolicyNoticeClient {

    public static final String path="/hr/policy-notice";


    /**
     *  POST---新增
     * @param policyNoticeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PolicyNoticeForm policyNoticeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, policyNoticeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody PolicyNoticeForm policyNoticeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PolicyNoticeVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   policyNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PolicyNoticeVo>> list(@Valid @RequestBody PolicyNoticeQueryForm policyNoticeQueryForm);


    /**
     *  POST----复杂查询
     * @param  policyNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PolicyNoticeVo>> search(@Valid @RequestBody PolicyNoticeQueryForm policyNoticeQueryForm);
}
