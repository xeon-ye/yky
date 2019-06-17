package com.deloitte.services.isump.mapper;

import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织架构表 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface OrganizationMapper extends BaseMapper<Organization> {
    OrganizationVo getByCode(String code);

    OrganizationVo getByName(@Param("name") String name);

    OrganizationVo getByID(@Param("id") String id);

    /**
     * 通过组织ID查询组织信息
     * @param list
     * @return
     */
    List<Organization> getByListID(@Param("list") List<DeputyAccount> list);

    /**
     * 组织编码查询下级组织
     * @param code
     * @return
     */
    List<OrganizationVo> getOrgTreeByCode(@Param("code") String code);

    /**
     * 组织编码查询下级虚拟组织
     * @param code 组织编码
     * @return
     */
    List<Organization> getOrgFinctionByCode(@Param("code") String code);

    /**
     * 根据组织code查询组织所有部门
     * @param code 组织CODE
     * @return
     */
    List<Organization> getOrgDeptByCode(@Param("code") String code);

    /**
     * 根据组织ID与组织类型查询
     * @param id 组织ID
     * @param type 组织类型
     * @return
     */
    Organization getOrgByIdOrType(@Param("id") String id, @Param("type") String type);

}
