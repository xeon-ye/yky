package com.deloitte.services.fssc.business.travle.mapper;

import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-04-01
 */
public interface TasTravelApplyInfoMapper extends BaseMapper<TasTravelApplyInfo> {
    Long countByExpenseMainTypeIds(@Param("idList") List<Long> idList);
}
