package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprUnitEvaInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoVo;
import com.deloitte.services.srpmp.project.mpr.entity.MprUnitEvaInfo;

import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprUnitEvaInfo服务类接口
 * @Modified :
 */
public interface IMprUnitEvaInfoService extends IService<MprUnitEvaInfo> {

    /**
     * 导出Word文档
     * @param id
     * @return
     */
    String exportWord(Long id);

    /**
     * 导入Word文档
     * @param wordFileUrl
     * @return
     */
    MprUnitEvaInfoVo importWord(String wordFileUrl);

    /**
     * 保存和更新
     * @param unitEvaInfoForm
     * @return
     */
    Long saveAndUpdate(MprUnitEvaInfoForm unitEvaInfoForm, UserVo user, DeptVo dept);

    /**
     * 查询
     * @param
     * @return
     */
    MprUnitEvaInfoVo getOne(MprUnitEvaInfoQueryForm unitEvaInfoQueryForm, UserVo user, DeptVo dept);

}
