package com.deloitte.services.srpmp.project.task.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongVo;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTranLong;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description : SrpmsProjectTaskTranLong服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskTranLongService extends IService<SrpmsProjectTranLong> {

    /**
     * 点击新增按钮跳转请求service接口
     *
     * @param user
     * @param dept
     * @return
     */
    JSONObject saveJumpPage(UserVo user, DeptVo dept);

    SrpmsProjectTaskTranLongVo queryById(Long id);

    SrpmsProjectTaskTranLongVo getTranById(Long id);

    /**
     * 保存变更记录service接口
     *
     * @param form
     * @param user
     * @return
     */
    String saveOrUpdate(SrpmsProjectTaskTranLongForm form, UserVo user, DeptVo dept);

    /**
     * 提交变更记录service接口
     *
     * @param form
     * @param user
     * @return
     */
    String submit(SrpmsProjectTaskTranLongForm form, UserVo user, DeptVo dept);
}
