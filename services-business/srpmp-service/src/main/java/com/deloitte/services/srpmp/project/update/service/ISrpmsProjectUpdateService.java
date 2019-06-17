package com.deloitte.services.srpmp.project.update.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.update.param.SrpmsProjectUpdateQueryForm;
import com.deloitte.platform.api.srpmp.project.update.vo.SrpmsProjectUpdateForm;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate服务类接口
 * @Modified :
 */
public interface ISrpmsProjectUpdateService extends IService<SrpmsProjectUpdate> {

    /**
     * 查询变更记录service接口
     *
     * @param form
     * @return
     */
    JSONObject list(SrpmsProjectUpdateQueryForm form, UserVo user, DeptVo dept);

    JSONObject queryById(Long id);

    JSONObject addNewRecord(SrpmsProjectUpdateQueryForm form, UserVo user);

    void delete(Long id);

    /**
     * 保存变更记录service接口
     *
     * @param form
     * @param user
     * @return
     */
    String saveOrUpdate(SrpmsProjectUpdateForm form, UserVo user, DeptVo dept);

    /**
     * 提交变更记录service接口
     *
     * @param form
     * @param user
     * @return
     */
    String submit(SrpmsProjectUpdateForm form, UserVo user, DeptVo dept);
}
