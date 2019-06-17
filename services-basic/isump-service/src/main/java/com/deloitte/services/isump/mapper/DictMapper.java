package com.deloitte.services.isump.mapper;

import com.deloitte.services.isump.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> selectListByParentCode(String code);
}
