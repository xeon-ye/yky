package com.deloitte.services.dss.finance.service;


import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.ProjectVo;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinRate服务类接口
 * @Modified :
 */
public interface IFinProjectService {

    /**
     * 三年项目库预算
     * @param map
     * @return
     */
    List<ProjectVo> selectProject(Map map);

    /**
     * 三年项目库预算分布情况
     * @param map
     * @return
     */
    List<ProjectVo> selectProEveStu(Map map);
    /**
     * 三年项目库预算情况
     * @param map
     * @return
     */
    List<ProjectVo> selectProStu(Map map);

    /**
     * 三年项目库指标列表
     * @return
     */
    List<ProjectVo> selectProIndexCode();
}
