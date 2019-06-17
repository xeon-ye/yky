package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.RoleQueryForm;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.isump.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : Role服务类接口
 * @Modified :
 */
public interface IRoleService extends IService<Role> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Role>
     */
    IPage<Role> selectPage(RoleQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Role>
     */
    List<Role> selectList(RoleQueryForm queryForm);

    /**
     * 根据副账号查询角色列表
     * @param id
     * @return
     */
     List<RoleVo> getByDeputyAccountId(long id,String type,String service);

    /**
     * 查询角色列表
     * @param service 系统ID
     * @param code 角色编码
     * @return
     */
    List<Role> getRoleList(String service, String code);

    /**
     * 查询用户有权限的系统
     * @param userId
     * @return
     */
    List<String> getSystemNameList(String userId);
}
