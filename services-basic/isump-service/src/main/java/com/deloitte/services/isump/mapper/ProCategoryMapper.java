package com.deloitte.services.isump.mapper;

import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.services.isump.entity.ProCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-03-22
 */
public interface ProCategoryMapper extends BaseMapper<ProCategory> {
    List<ProCategoryVo> getListByDeputyAccountId(long id);

}
