package com.deloitte.services.isump.mapper;

import com.deloitte.services.isump.entity.DeputyAccountRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 副账号-角色 关联表 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface DeputyAccountRoleMapper extends BaseMapper<DeputyAccountRole> {

    List<String> selectRolesByDeputyAccountId(long deputyAccountId);
}
