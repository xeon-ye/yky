package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.SubactBakQueryForm;
import com.deloitte.services.project.entity.SubactBak;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description : SubactBak服务类接口
 * @Modified :
 */
public interface ISubactBakService extends IService<SubactBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SubactBak>
     */
    IPage<SubactBak> selectPage(SubactBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SubactBak>
     */
    List<SubactBak> selectList(SubactBakQueryForm queryForm);


    /**
     * 删除
     * @param actBakId
     */
    void deleteALLList(String actBakId);

    /**
     * 获取
     * @param actId
     * @return
     */
    List<SubactBak> getAllList(String actBakId);
}
