package com.deloitte.services.isump.mapper;

import com.deloitte.services.isump.entity.OrgRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织-角色 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface OrgRoleMapper extends BaseMapper<OrgRole> {

    List<String> selectRolesByOrgId(long orgId);

}
