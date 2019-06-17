package com.deloitte.services.srpmp.project.accept.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.accept.param.SrpmsProjectAcceptQueryForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptForm;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAccept;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAccept服务类接口
 * @Modified :
 */
public interface ISrpmsProjectAcceptService extends IService<SrpmsProjectAccept> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectAccept>
     */
    JSONObject selectPage(SrpmsProjectAcceptQueryForm queryForm, UserVo user, DeptVo dept);

    /**
     * 根据ID查询验收报告service接口
     *
     * @param id
     * @return
     */
    JSONObject queryById(Long id);

    /**
     * 项目验收催告数据查询
     */
    void projectAcceptRemain();

    /**
     * 项目验收报告跳转请求service接口
     *
     * @param form
     * @return
     */
    JSONObject searchNewVersion(SrpmsProjectAcceptQueryForm form);

    void searchAcceptExists(SrpmsProjectAcceptQueryForm form, UserVo user);

    /**
     * 保存service接口
     *
     * @param form
     * @param user
     * @return
     */
    String saveOrUpdate(SrpmsProjectAcceptForm form, UserVo user, DeptVo dept, boolean submitFlag);

    /**
     * 提交service接口
     *
     * @param form
     * @param user
     * @return
     */
    void submit(SrpmsProjectAcceptForm form, UserVo user, DeptVo dept);

    String exportPdfFile(Long id) throws Exception;
}
