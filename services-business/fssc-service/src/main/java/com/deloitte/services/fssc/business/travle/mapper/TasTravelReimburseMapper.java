package com.deloitte.services.fssc.business.travle.mapper;

import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-04-08
 */
public interface TasTravelReimburseMapper extends BaseMapper<TasTravelReimburse> {
    Long countByExpenseMainTypeIds(@Param("idList") List<Long> idList);
}
