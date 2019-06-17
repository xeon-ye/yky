package com.deloitte.platform.api.srpmp.project.accept.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.accept.param.SrpmsProjectAcceptQueryForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAccept控制器接口
 * @Modified :
 */
public interface SrpmsProjectAcceptClient {

    public static final String path = "/srpmp/project/accept";

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<SrpmsProjectAcceptVo> get(@PathVariable(value = "id") long id);

    /**
     * POST----复杂查询
     *
     * @param srpmsProjectAcceptQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<SrpmsProjectAcceptVo>> search(@Valid @RequestBody SrpmsProjectAcceptQueryForm srpmsProjectAcceptQueryForm, UserVo user, DeptVo dept);

    /**
     * 项目验收催告
     *
     * @return
     */
    @GetMapping(value = path + "/remain")
    Result projectAcceptRemain();

    @GetMapping(value = path + "/search")
    Result searchNewVersion(@ModelAttribute SrpmsProjectAcceptQueryForm form, UserVo user);

    /**
     * POST---保存
     *
     * @param form
     * @return
     */
    @PostMapping(value = path)
    Result save(@ModelAttribute SrpmsProjectAcceptForm form, UserVo user, DeptVo dept);

    /**
     * POST---提交
     *
     * @param form
     * @return
     */
    @PostMapping(value = path + "/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectAcceptForm form, UserVo user, DeptVo dept);

    @GetMapping(value = path + "/export/pdf/{id}")
    Result exportPdf(@PathVariable(value = "id") Long id, String projectType, HttpServletResponse response);

    @GetMapping(value = path + "/exists")
    Result searchAcceptExists(@ModelAttribute SrpmsProjectAcceptQueryForm form, UserVo user);

}
