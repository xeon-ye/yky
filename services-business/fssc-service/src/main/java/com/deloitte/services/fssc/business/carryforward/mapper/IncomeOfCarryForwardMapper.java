package com.deloitte.services.fssc.business.carryforward.mapper;

import com.deloitte.platform.api.fssc.carryforward.param.IncomeOfCarryForwardQueryForm;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收入结转 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-05-06
 */
public interface IncomeOfCarryForwardMapper extends BaseMapper<IncomeOfCarryForward> {

    Integer hasCarryId(@Param("id")Long id);

    List<IncomeOfCarryForward> selectListByManual(IncomeOfCarryForwardQueryForm form);

    /**
     * 查询单子如果冲销是否被结转
     * @param documentType
     * @param documentId
     * @return
     */
    List<IncomeOfCarryForward> hasCarryByManualId(@Param("documentType")String documentType,@Param("documentId")Long documentId);
}
