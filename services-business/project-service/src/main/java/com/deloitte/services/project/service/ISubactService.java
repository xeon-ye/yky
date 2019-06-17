package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.SubactQueryForm;
import com.deloitte.services.project.entity.Subact;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description : Subact服务类接口
 * @Modified :
 */
public interface ISubactService extends IService<Subact> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Subact>
     */
    IPage<Subact> selectPage(SubactQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Subact>
     */
    List<Subact> selectList(SubactQueryForm queryForm);

    /**
     * 查询
     * @param applicationId
     * @return
     */
    List<Subact> getSubActList(String actId);

    /**
     * 删除
     * @param actId
     */
    void deleteSubActList(String actId);
}
