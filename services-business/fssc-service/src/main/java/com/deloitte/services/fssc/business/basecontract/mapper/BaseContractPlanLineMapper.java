package com.deloitte.services.fssc.business.basecontract.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractPlanLineQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractPlanLineVo;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 合同履行计划 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-04-17
 */
public interface BaseContractPlanLineMapper extends BaseMapper<BaseContractPlanLine> {

    List<BaseContractPlanLineVo> findPlanDetail(@Param("planLineIPage") IPage<BaseContractPlanLineVo> planLineIPage,
                                                @Param("queryForm") BaseContractPlanLineQueryForm queryForm);
}
