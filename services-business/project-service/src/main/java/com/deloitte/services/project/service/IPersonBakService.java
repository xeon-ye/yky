package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.PersonBakQueryForm;
import com.deloitte.services.project.entity.PersonBak;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.project.entity.ProjectsAndApplication;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : PersonBak服务类接口
 * @Modified :
 */
public interface IPersonBakService extends IService<PersonBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PersonBak>
     */
    IPage<PersonBak> selectPage(PersonBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PersonBak>
     */
    List<PersonBak> selectList(PersonBakQueryForm queryForm);

    /**
     * 获取
     * @param applicationId
     * @return
     */
    List<PersonBak> getAllList(String applicationId);

    /**
     * 删除
     * @param applicationId
     */
    void deleteAllList(String applicationId);

    List<PersonBak> getAllByRepId(String replyId);
    void deleteAllByRepId(String replyId);

    List<PersonBak> getAllByProId(String projectId);
    void deleteAllByProId(String projectId);
    List<PersonBak> selectListPage(Page<PersonBak> page, Map<String, Object> map);
}
