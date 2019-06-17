package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : Organization服务类接口
 * @Modified :
 */
public interface IOrganizationService extends IService<Organization> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Organization>
     */
    IPage<Organization> selectPage(OrganizationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Organization>
     */
    List<Organization> selectList(OrganizationQueryForm queryForm);

    /**
     * 根据code查询组织实体
     * @param code
     * @return
     */
    OrganizationVo getByCode(String code);

    /**
     * 根据名称查询组织实体
     * @param name
     * @return
     */
    OrganizationVo getByName(String name);

    /**
     * 根据id查询组织实体
     * @param id
     * @return
     */
    OrganizationVo getByID(String id);

    /**
     * 查询组织信息
     * @param list 副账号信息
     * @return
     */
    List<Organization> getByListID(List<DeputyAccount> list);

    /**
     * 组织编码查询下级组织
     * @param code 组织编号
     * @return
     */
    List<OrganizationVo> getOrgTreeByCode(String code);

    /**
     * 组织编码查询下级虚拟组织
     * @param code 组织编码
     * @return
     */
    List<Organization> getOrgFinctionByCode(String code);

    /**
     * 根据组织code查询下级所有部门
     * @param code 组织code
     * @return
     */
    List<Organization> getOrgDeptByCode(String code);
}
