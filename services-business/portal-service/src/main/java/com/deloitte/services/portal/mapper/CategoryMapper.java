package com.deloitte.services.portal.mapper;

import com.deloitte.services.portal.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 栏目表 Mapper 接口
 * </p>
 *
 * @author pengchao
 * @since 2019-04-03
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> selectHomeCategories();

}
