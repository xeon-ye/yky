package com.deloitte.services.srpmp.relust.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransVo;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultVo;
import com.deloitte.services.srpmp.relust.entity.SrpmsResultTrans;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResultTrans服务类接口
 * @Modified :
 */
public interface ISrpmsResultTransService extends IService<SrpmsResultTrans> {

    SrpmsResultTransVo queryByResultId(Long id);

    SrpmsResultTransVo queryById(Long id);

    String saveOrUpdate(SrpmsResultTransForm form);

    void submit(SrpmsResultTransForm form, UserVo userVo, DeptVo deptVo);

    void approvePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo);

    void refuseBpm(TaskNodeActionVO actionVO);
}