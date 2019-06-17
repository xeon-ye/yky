package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpPolicyReportQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpPolicyReportForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpPolicyReportVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpPolicyReport控制器接口
 * @Modified :
 */
public interface ZpcpPolicyReportClient {

    public static final String path="/hr/zpcp-policy-report";


    /**
     *  POST---新增
     * @param zpcpPolicyReportForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpPolicyReportForm zpcpPolicyReportForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpPolicyReportForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpPolicyReportForm zpcpPolicyReportForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpPolicyReportVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpPolicyReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpPolicyReportVo>> list(@Valid @RequestBody ZpcpPolicyReportQueryForm zpcpPolicyReportQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpPolicyReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpPolicyReportVo>> search(@Valid @RequestBody ZpcpPolicyReportQueryForm zpcpPolicyReportQueryForm);

    /**
     *  POST---批量新增或更新
     * @param policyReportForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpPolicyReportForm> policyReportForms);



    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
