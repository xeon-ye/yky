package com.deloitte.services.srpmp.project.apply.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchTeachQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchStuSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchTeachSaveVo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchTeach;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-20
 * @Description : SrpmsProjectApplySchTeach服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplySchTeachService extends IService<SrpmsProjectApplySchTeach> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectApplySchTeach>
     */
    IPage<SrpmsProjectApplySchTeach> selectPage(SrpmsProjectApplySchTeachQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplySchTeach>
     */
    List<SrpmsProjectApplySchTeach> selectList(SrpmsProjectApplySchTeachQueryForm queryForm);

    /**
     * 保存
     * @param vo
     * @return
     */
    Long saveAndUpdateSchTeach(SrpmsProjectApplySchTeachSaveVo vo, DeptVo deptVo);


    JSONObject queryApplyVoById(Long projectId, UserVo user, DeptVo dept);

    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;

    /**
     * 根据id删除
     * @param id
     */
    void removeSchTeachById(long id);


    void submitApply(SrpmsProjectApplySchTeachSaveVo vo, UserVo userVo,DeptVo deptVo);

    /**
     * word导出
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId,UserVo user, DeptVo dept);

    /**
     * 导入
     * @param wordFileUrl
     * @return
     */
    SrpmsProjectApplySchTeachSaveVo importWord(String wordFileUrl);
}
