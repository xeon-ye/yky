package com.deloitte.services.srpmp.project.accept.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAcceptAcademy;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAcceptAcademy服务类接口
 * @Modified :
 */
public interface ISrpmsProjectAcceptAcademyService extends IService<SrpmsProjectAcceptAcademy> {

    JSONObject queryByAcceptId(Long id);

    void cleanAndSaveAcceptAcademy(SrpmsProjectAcceptAcademy academy, Long accpetId);
}
