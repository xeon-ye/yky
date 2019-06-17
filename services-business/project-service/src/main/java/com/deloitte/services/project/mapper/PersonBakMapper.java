package com.deloitte.services.project.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.project.entity.PersonBak;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目执行人员变更表 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-20
 */
public interface PersonBakMapper extends BaseMapper<PersonBak> {
    List<PersonBak> selectListPage(Page page, @Param("data") Map<String, Object> map) ;
}
