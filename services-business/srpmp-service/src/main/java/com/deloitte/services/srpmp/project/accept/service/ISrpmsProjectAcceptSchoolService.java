package com.deloitte.services.srpmp.project.accept.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAcceptSchool;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAcceptSchool服务类接口
 * @Modified :
 */
public interface ISrpmsProjectAcceptSchoolService extends IService<SrpmsProjectAcceptSchool> {

    JSONObject queryByAcceptId(Long id);

    void cleanAndSaveAcceptSchool(SrpmsProjectAcceptSchool school, Long accpetId);
}
