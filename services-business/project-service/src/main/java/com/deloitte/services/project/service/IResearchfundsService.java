package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ResearchfundsQueryForm;
import com.deloitte.services.project.entity.Researchfunds;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description : Researchfunds服务类接口
 * @Modified :
 */
public interface IResearchfundsService extends IService<Researchfunds> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Researchfunds>
     */
    IPage<Researchfunds> selectPage(ResearchfundsQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Researchfunds>
     */
    List<Researchfunds> selectList(ResearchfundsQueryForm queryForm);
}
