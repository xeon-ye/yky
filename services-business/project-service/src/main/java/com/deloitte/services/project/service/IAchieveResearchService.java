package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.AchieveResearchQueryForm;
import com.deloitte.services.project.entity.AchieveResearch;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description : AchieveResearch服务类接口
 * @Modified :
 */
public interface IAchieveResearchService extends IService<AchieveResearch> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AchieveResearch>
     */
    IPage<AchieveResearch> selectPage(AchieveResearchQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AchieveResearch>
     */
    List<AchieveResearch> selectList(AchieveResearchQueryForm queryForm);
}
