package com.deloitte.services.fssc.budget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectVo;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-03-20
 */
public interface BudgetProjectMapper extends BaseMapper<BudgetProject> {

    /**
     * 列表关联查询
     * @param queryForm
     * @return
     */
    List<BudgetProjectVo> listVo(@Param("queryForm") BudgetProjectQueryForm queryForm);
}
