package com.deloitte.platform.api.bpmservice.clinet;

import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixForm;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description :  BpmApprovalMatrix控制器接口
 * @Modified :
 */
public interface BpmApprovalMatrixClient {

    public static final String path = "/bpmservice/bpm-approval-matrix";


    /**
     * POST---新增
     *
     * @param bpmApprovalMatrixForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BpmApprovalMatrixForm bpmApprovalMatrixForm);

    /**
     * POST---批量新增
     *
     * @param bpmApprovalMatrixForm
     * @return
     */
    @PostMapping(value = path + "/save")
    Result save(@Valid @RequestBody BpmApprovalMatrixForm[] bpmApprovalMatrixForms);

    /**
     * Delete---删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path + "/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     * Patch----部分更新
     *
     * @param id, bpmApprovalMatrixForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody BpmApprovalMatrixForm bpmApprovalMatrixForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<BpmApprovalMatrixVo> get(@PathVariable(value = "id") long id);

    /**
     * POST----列表查询
     *
     * @param bpmApprovalMatrixForm
     * @return
     */
    @PostMapping(value = path + "/findNextApprover")
    Result<List<BpmApprovalMatrixVo>> findNextApprover(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) ;

    /**
     * POST----列表查询
     *
     * @param bpmApprovalMatrixForm
     * @return
     */
    @PostMapping(value = path + "/findThisApproverPage")
    Result<GdcPage<BpmApprovalMatrixVo>> findThisApproverPage(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) ;

    /**
     * POST----列表查询
     *
     * @param bpmApprovalMatrixForm
     * @return
     */
    @PostMapping(value = path + "/findNextApproverPage")
    Result<GdcPage<BpmApprovalMatrixVo>> findNextApproverPage(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) ;

    /**
     * POST----列表查询
     *
     * @param bpmApprovalMatrixForm
     * @return
     */
    @PostMapping(value = path + "/findThisApprover")
    Result<List<BpmApprovalMatrixVo>> findThisApprover(BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixForm) ;

        /**
         * POST----列表查询
         *
         * @param bpmApprovalMatrixQueryForm
         * @return
         */
    @PostMapping(value = path + "/list/conditions")
    Result<List<BpmApprovalMatrixVo>> list(@Valid @RequestBody BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm);


    /**
     * POST----复杂查询
     *
     * @param bpmApprovalMatrixQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<BpmApprovalMatrixVo>> search(@Valid @RequestBody BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm);


}

