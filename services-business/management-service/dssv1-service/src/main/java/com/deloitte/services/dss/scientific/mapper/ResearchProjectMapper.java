package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.ProjectAnnualNumberVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectVo;

import java.util.List;

/**
 * @interfaceName: ResearchProjectMapper
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-02-28
 * @version: v1.0
 */
public interface ResearchProjectMapper {


    /**
     * 当年项目数量
     * @return
     */
    List<ResearchProjectVo> queryProjectNum();

    /**
     * 当年项目金额
     * @return
     */
    List<ResearchProjectVo> queryFund();

    /**
     * 年度项目数量
     * @return
     */
    List<ProjectAnnualNumberVo> queryAnnualProjectNum();
    /**
     * ....
     * @return
     */
    List<ResearchProjectVo> queryProjectNumAndAmount();
}
