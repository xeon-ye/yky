package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsProjectExpertSaveForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectExpert;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description : SrpmsProjectExpert服务类接口
 * @Modified :
 */
public interface ISrpmsProjectExpertService extends IService<SrpmsProjectExpert> {

    List<SrpmsProjectExpert> queryListByProjectId(Long projectId, String type);

    void cleanAndSave(SrpmsProjectExpertSaveForm voList);

    /**
     * 根据ID删除评审专家service接口
     *
     * @param id
     */
    void delete(Long id);
}
