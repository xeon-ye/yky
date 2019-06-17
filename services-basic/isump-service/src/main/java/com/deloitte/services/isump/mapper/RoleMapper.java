package com.deloitte.services.isump.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.isump.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色数据
     * @param service 系统编码
     * @param code 角色编码
     * @return
     */
   List<Role> getRoleList(@Param("service") String service, @Param("code")  String code);

    /**
     * 根据用户ID查询用户有权限的系统
     * @param userId
     * @return
     */
    List<String> getSystemNameList(@Param("userId") String userId);

}
