package com.deloitte.services.fssc.engine.automatic.mapper;

import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 主体段值和账薄的关系 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-30
 */
public interface AvLedgerUnitRelationMapper extends BaseMapper<AvLedgerUnitRelation> {

    List<BaseDocumentType> getDocumentTypeList(@Param("ledgerId")Long ledgerId);


}
