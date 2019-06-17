package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchUserListVoQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.param.RemindQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindProcessForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindRecordForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :  RetireRemind控制器接口
 * @Modified :
 */
public interface RemindClient {

    public static final String path = "/hr/retire-remind";


    /**
     * POST---新增
     *
     * @param retireRemindForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RemindForm retireRemindForm);

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
     * @param id, retireRemindForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RemindForm retireRemindForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<RemindVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param retireRemindQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<RemindVo>> list(@Valid @RequestBody RemindQueryForm retireRemindQueryForm);


    /**
     * POST----复杂查询
     *
     * @param retireRemindQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<RemindVo>> search(@Valid @RequestBody RemindQueryForm retireRemindQueryForm);


    @PostMapping(value = path + "/remindDeptMouth")
    Result remindDeptMouth(@Valid @RequestBody @ApiParam(name = "retireRemindQueryForm", value = "RetireRemind查询参数", required = true) List<RemindRecordForm> forms);

    @PostMapping(value = path + "/remindApproveProcess")
    Result remindApproveProcess(@Valid @RequestBody @ApiParam(name = "RemindProcessForm", value = "流程提交参数", required = true) RemindProcessForm remindProcessForm);



    /**
     * 导出Excel
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportExcel")
    void exportExcel(@RequestParam String id, HttpServletRequest request, HttpServletResponse response);
}
