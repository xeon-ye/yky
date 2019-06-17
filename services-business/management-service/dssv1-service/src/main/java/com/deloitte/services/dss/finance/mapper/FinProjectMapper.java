package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.ProjectVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 绩效表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
public interface FinProjectMapper {

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
    /**
     * 三年项目库预算
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectProjectNow(Map map);
    List<IncomeBudgetVo> selectProjectNowAll(Map map);



}
