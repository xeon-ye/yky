package com.deloitte.services.isump.mapper;

import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.services.isump.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表（模块，菜单，按钮，数据） Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据角色Id列表和系统code查询菜单树
     * @param roles
     * @param sysCode
     * @return
     */
    List<ResourceVo> findTree(@Param("roleIds") List<String> roles, @Param("sysCode") String sysCode);
}
