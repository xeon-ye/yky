package com.deloitte.platform.api.srpmp.project.task;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description :  SrpmsProjectTaskTranLong控制器接口
 * @Modified :
 */
public interface SrpmsProjectTaskTranLongClient {

    String path="/srpmp/project/task/tran/long";

    /**
     * 点击新增跳转界面
     *
     * @param user
     * @param dept
     * @return
     */
    @GetMapping(value = path)
    Result jump(UserVo user, DeptVo dept);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result getById(@PathVariable(value = "id") long id);

    /**
     *  POST---保存
     * @param taskForm
     * @return
     */
    @PostMapping(value = path)
    Result save(@ModelAttribute SrpmsProjectTaskTranLongForm taskForm, UserVo userVo, DeptVo deptVo);

    /**
     *  POST---提交
     * @param taskForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectTaskTranLongForm taskForm, UserVo userVo, DeptVo deptVo);


}
