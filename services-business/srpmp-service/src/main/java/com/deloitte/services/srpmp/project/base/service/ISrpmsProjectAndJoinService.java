package com.deloitte.services.srpmp.project.base.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProject服务类接口
 * @Modified :
 */
public interface ISrpmsProjectAndJoinService extends IService<SrpmsProject> {

    /**
     *  项目查询
     * @param queryForm
     * @return IPage<SrpmsProject>
     */
    JSONObject selectPage(SrpmsProjectQueryForm queryForm, UserVo user, DeptVo dept);
}
