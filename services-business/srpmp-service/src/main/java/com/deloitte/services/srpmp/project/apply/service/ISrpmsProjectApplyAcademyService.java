package com.deloitte.services.srpmp.project.apply.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplyAcademyQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyAcademySaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyAcademy;
import com.baomidou.mybatisplus.extension.service.IService;
import org.docx4j.wml.U;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description : SrpmsProjectApplyAcademy服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplyAcademyService extends IService<SrpmsProjectApplyAcademy> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectApplyAcademy>
     */
    IPage<SrpmsProjectApplyAcademy> selectPage(SrpmsProjectApplyAcademyQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplyAcademy>
     */
    List<SrpmsProjectApplyAcademy> selectList(SrpmsProjectApplyAcademyQueryForm queryForm);

    /**
     * 保存更新
     * @param vo
     * @return
     */
    Long saveAndUpdateAcademy(SrpmsProjectApplyAcademySaveVo vo, DeptVo deptVo);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    SrpmsProjectApplyAcademySaveVo getAcademyById(long id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteAcademyById(long id);

    public JSONObject queryApplyVoById(Long projectId, UserVo user, DeptVo dept);

    /**
     * 提交申请书
     * @param vo
     * @param deptVo
     */
    void submitApply(SrpmsProjectApplyAcademySaveVo vo, UserVo userVo, DeptVo deptVo);

    /**
     * word导出 tanwx
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo);

    /**tanwx
     * 生成pdf
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) ;

    /**tanwx
     * word导入项目申请书
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectApplyAcademySaveVo importWord(String wordFileUrl);

    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;
}
