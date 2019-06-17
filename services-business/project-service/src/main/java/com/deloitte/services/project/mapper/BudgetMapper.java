package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.Budget;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目预算 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-09
 */
public interface BudgetMapper extends BaseMapper<Budget> {

    List<Budget> getBudgetYearList(String applicationId);

    List getAppYearCount(String applicationId);
    List<Budget> getAppBudgetMap(@Param("data") Map map);

    List getRepYearCount(String replyId);
    List<Budget> getRepBudgetMap(@Param("data") Map map);

    void deleteByRepIdAndYear(@Param("data") Map map);
}
