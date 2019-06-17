package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.OrgRoleQueryForm;
import com.deloitte.services.isump.entity.OrgRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : OrgRole服务类接口
 * @Modified :
 */
public interface IOrgRoleService extends IService<OrgRole> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OrgRole>
     */
    IPage<OrgRole> selectPage(OrgRoleQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OrgRole>
     */
    List<OrgRole> selectList(OrgRoleQueryForm queryForm);

    List<String> selectRolesByOrgId(Long orgId);
}
