package com.deloitte.services.dss.scientific.service;



import com.deloitte.platform.api.dss.scientific.vo.ProjectAnnualNumberVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectVo;

import java.util.List;
import java.util.Map;

/**
 * @interfaceName: IResearchProjectService
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-02-28
 * @version: v1.0
 */
public interface IResearchProjectService {


    /**
     * 当年项目数量和金额比
     * @return
     */
    List<ResearchProjectVo> queryNumAndFundRatio();

    /**
     * 年度项目数量
     * @return
     */
    List<ProjectAnnualNumberVo> queryAnnualProjectNum();

}
