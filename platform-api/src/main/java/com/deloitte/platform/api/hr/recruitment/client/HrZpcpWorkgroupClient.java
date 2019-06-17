package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpWorkgroupQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpWorkgroupForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpWorkgroupVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpWorkgroup控制器接口
 * @Modified :
 */
public interface HrZpcpWorkgroupClient {

    public static final String path = "/hr/recruitment/hr-zpcp-workgroup";


    /**
     * POST---新增
     *
     * @param hrZpcpWorkgroupForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpWorkgroupForm hrZpcpWorkgroupForm);

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
     * @param id, hrZpcpWorkgroupForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpWorkgroupForm hrZpcpWorkgroupForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<HrZpcpWorkgroupVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param hrZpcpWorkgroupQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<HrZpcpWorkgroupVo>> list(@Valid @RequestBody HrZpcpWorkgroupQueryForm hrZpcpWorkgroupQueryForm);


    /**
     * POST----复杂查询
     *
     * @param hrZpcpWorkgroupQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<HrZpcpWorkgroupVo>> search(@Valid @RequestBody HrZpcpWorkgroupQueryForm hrZpcpWorkgroupQueryForm);

    /**
     * 条件导出数据列表
     *
     * @param queryForm 查询条件
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path + "/list/exportGroupList")
    void exportGroupList(@Valid @ModelAttribute HrZpcpWorkgroupQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);


}
