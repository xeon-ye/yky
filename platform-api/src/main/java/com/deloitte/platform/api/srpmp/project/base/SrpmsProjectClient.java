package com.deloitte.platform.api.srpmp.project.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProject控制器接口
 * @Modified :
 */
public interface SrpmsProjectClient {

    public static final String path="/srpmp/project";

    /**
     *  POST----复杂查询
     * @param  srpmsProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<IPage<SrpmsProjectVo>> list(@Valid @RequestBody SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept);

    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SrpmsProjectVo>> search(@Valid @RequestBody SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept);

    @PostMapping(value = path+"/modify/conditions")
    Result<IPage<SrpmsProjectVo>> searchModify(@Valid @RequestBody SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept);

    @PostMapping(value = path+"/create/conditions")
    Result<IPage<SrpmsProjectVo>> searchCreate(@Valid @RequestBody SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept);

    /**
     *  POST----复杂查询
     * @param  id
     * @return
     */
    @PostMapping(value = path+"/delete/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  POST----查询任务书列表
     * @return
     */
    @GetMapping(value = path+"/task")
    Result<IPage<SrpmsProjectVo>> taskQuery( UserVo user, DeptVo dept);

    /**
     * GET----根据待审核单位ID获取
     *
     * @return
     */
    @GetMapping(value = path+"/approveSrpmsProject/{id}/{type}")
    Result getProject(@PathVariable(value = "id") long id, @PathVariable(value = "type") String type, UserVo user, DeptVo dept);

    /**
     * 工作台删除评审专家数据
     *
     * @param id
     * @return
     */
    @GetMapping(value = path+"/delete/expert/{id}")
    Result deleteExpertById(@PathVariable(value="id") long id);
}
