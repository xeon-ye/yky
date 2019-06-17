package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckRelationApprovalQueryFrom;
import com.deloitte.platform.api.hr.check.param.CheckRelationQueryForm;
import com.deloitte.platform.api.hr.check.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckRelation控制器接口
 * @Modified :
 */
public interface CheckRelationClient {

    public static final String path="/hr/check-relation";


    /**
     *  POST---新增
     * @param checkRelationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckRelationForm checkRelationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkRelationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckRelationForm checkRelationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckRelationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkRelationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckRelationVo>> list(@Valid @RequestBody CheckRelationQueryForm checkRelationQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkRelationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckRelationVo>> search(@Valid @RequestBody CheckRelationQueryForm checkRelationQueryForm);


    /**
     *  POST----复杂查询考核关系详情
     * @param  checkRelationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckRelationInfoVo")
    Result<IPage<CheckRelationInfoVo>> getCheckRelationInfoVo(@Valid @RequestBody CheckRelationQueryForm checkRelationQueryForm);


    /**
     *  Delete---删除
     * @param  ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);

    /**
     *  POST----列表查询
     * @param   checkRelationApprovalQueryFrom
     * @return
     */
    @PostMapping(value = path+"/getCheckRelationApproval")
    Result<List<CheckRelationApprovalVo>> getCheckRelationApproval(@Valid @RequestBody CheckRelationApprovalQueryFrom checkRelationApprovalQueryFrom);

    /**
     *导出输出设置表
     * @param checkRelationApprovalQueryFrom
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckRelationApproval")
    void exportCheckRelationApproval(@Valid @ModelAttribute CheckRelationApprovalQueryFrom checkRelationApprovalQueryFrom, HttpServletRequest request, HttpServletResponse response);

    /**
     * 提交审批
     * @param checkRelationApprovalQueryFrom
     * @return
     */
    @PostMapping(value = path+"/approveCheckRelation")
    Result approveCheckRelation(@Valid @RequestBody CheckRelationApprovalQueryFrom checkRelationApprovalQueryFrom);

    /**
     * 审批操作，同意或者不同意
     * @param form
     * @return
     */
    @PostMapping(value = path+"/dealApprove")
    Result  dealApprove(@Valid @RequestBody CommonProcessForm form);
}
