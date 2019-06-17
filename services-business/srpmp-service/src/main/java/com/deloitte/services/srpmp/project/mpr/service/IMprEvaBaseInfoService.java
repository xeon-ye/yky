package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaBaseInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaBaseInfoVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprSaveOrUpdateAnnexOneForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprSaveOrUpdateAnnexOneVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprSaveOrUpdateForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaBaseInfo服务类接口
 * @Modified :
 */
public interface IMprEvaBaseInfoService extends IService<MprEvaBaseInfo> {


    /**
     * 新增或者保存附件一内容
     * @param saveOrUpdateAnnexOneForm
     */
    void saveOrUpdateAnnexOne(MprSaveOrUpdateAnnexOneForm saveOrUpdateAnnexOneForm, UserVo user, DeptVo deptVo);

    /**
     * 删除附件一
     * @param projectId
     */
    void delAnnexOneInfo(Long projectId);

    /**
     * 根据项目编号获取附件一
     * @param projectNo
     * @return
     */
    MprSaveOrUpdateAnnexOneVo getAnnexOne(Long projectNo);

    /**
     *  查询基本信息
     * @param projectId
     * @return MprEvaBaseInfo
     */
    MprEvaBaseInfo getMprBaseInfo(Long projectId);

    /**
     * 导出附件一为Excel
     * @param projectNo
     * @return
     */
    String exportAnnexOneExcel(Long projectNo);

    // 以下代码：wuhebiao新增
//    /**
//     *  分页查询
//     * @param queryForm
//     * @return IPage<MprEvaBaseInfo>
//     */
//    IPage<MprEvaBaseInfo> selectPage(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept);

    /**
     *  条件查询：我的报告
     * @param queryForm
     * @return List<MprEvaBaseInfo>
     */
    IPage<MprEvaBaseInfoVo> selectList(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept);

//    /**
//     *  条件查询：我的报告
//     * @param queryForm
//     * @return List<MprEvaBaseInfo>
//     */
//    List<MprEvaBaseInfo> selectOneList(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept);

//    /**
//     *  条件查询：我的部门或所有部门报告
//     * @param queryForm
//     * @return List<MprEvaBaseInfo>
//     */
//    List<MprEvaBaseInfo> selectAllDeptList(MprEvaBaseInfoQueryForm queryForm, UserVo user, DeptVo dept);

    /**
     *  根据报告其它组合唯一（如：项目ID+年份+报告类型）条件查询报告详情
     * @param queryForm
     */
    JSONObject getByConditions(MprEvaBaseInfoQueryForm queryForm);

    /**
     * 判断年度报告是否存在
     * @param queryForm
     * @return
     */
    JSONObject checkReportExists(MprEvaBaseInfoQueryForm queryForm);

    /**
     * 年度报告保存
     * @param mprSaveOrUpdateForm
     */
    Long saveOrUpdate(MprSaveOrUpdateForm mprSaveOrUpdateForm, UserVo user, DeptVo dept);

    /**
     * 年度报告流程发起
     * @param mprSaveOrUpdateForm
     * @param user
     * @param dept
     */
    void submitReport(MprSaveOrUpdateForm mprSaveOrUpdateForm, UserVo user, DeptVo dept);

    /**
     * 审核通过年度报告
     * @param actionVO
     * @param deptVo
     */
    void agreeReport(TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 拒绝年度报告
     * @param actionVO
     */
    void refuseReport(TaskNodeActionVO actionVO);

//    /**
//     * 驳回年度报告
//     * @param actionVO
//     */
//    void rejectReport(TaskNodeActionVO actionVO);
}
