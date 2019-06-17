package com.deloitte.services.fssc.engine.automatic.mapper;

import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElementRele;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.AfterDomainEventPublication;

/**
 * <p>
 * 核算要素分配账薄信息表 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-27
 */
public interface AvAccountElementReleMapper extends BaseMapper<AvAccountElementRele> {

    Integer getRele(@Param("chartOfAccountsId")Long chartOfAccountsId,@Param("segmentCode")String segmentCode );

}
