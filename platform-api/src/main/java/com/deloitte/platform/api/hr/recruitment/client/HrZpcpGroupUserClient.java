package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpGroupUserQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpGroupUserForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpGroupUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpGroupUser控制器接口
 * @Modified :
 */
public interface HrZpcpGroupUserClient {

    public static final String path="/hr/recruitment/hr-zpcp-group-user";


    /**
     *  POST---新增
     * @param hrZpcpGroupUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpGroupUserForm hrZpcpGroupUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpGroupUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpGroupUserForm hrZpcpGroupUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpGroupUserVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpGroupUserVo>> list(@Valid @RequestBody HrZpcpGroupUserQueryForm hrZpcpGroupUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpGroupUserVo>> search(@Valid @RequestBody HrZpcpGroupUserQueryForm hrZpcpGroupUserQueryForm);



    /**
     *
     *  条件导出数据列表
     *
     * @param queryForm  查询条件
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/list/exportGroupUserList")
    void exportGroupUserList(@Valid @ModelAttribute HrZpcpGroupUserQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);


    /**
     * POST---批量新增
     *
     * @param hrZpcpGroupUserForms
     * @return
     */
    @PostMapping(value = path+"/addlist")
    public Result addList(@Valid @RequestBody List<HrZpcpGroupUserForm> hrZpcpGroupUserForms);

    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);

}
