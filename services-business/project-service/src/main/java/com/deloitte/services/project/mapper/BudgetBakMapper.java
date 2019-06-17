package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.Budget;
import com.deloitte.services.project.entity.BudgetBak;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目预算备份 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-09
 */
public interface BudgetBakMapper extends BaseMapper<BudgetBak> {

    List getYearListRep(String replyId);
    List<BudgetBak> getListRep(@Param("data") Map map);

    void deleteByRepIdAndYear(@Param("data") Map map);
}
