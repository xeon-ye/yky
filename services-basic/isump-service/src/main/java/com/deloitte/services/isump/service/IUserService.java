package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.services.isump.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : User服务类接口
 * @Modified :
 */
public interface IUserService extends IService<User> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<User>
     */
    IPage<User> selectPage(UserQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<User>
     */
    List<User> selectList(UserQueryForm queryForm);

    IPage<UserVo> randSpecialist(UserQueryForm userQueryForm);

    User getByUserName(String username);

    String login(User user);

    Long hasLogin(String token);

    UserVo getUserByToken(String token);

    void logout(String tiken);

    UserVo getUserVoById(long id);

    /**
     * 查询部门信息
     * @param empno 员工编号
     * @return
     */
    List<OrganizationVo> getUserVOByEmpNoDept(String empno);

    /**
     * 根据组织编码查询人员信息
     * @param orgcode 组织code
     * @return
     */
    List<User> getByOrgCode(String orgcode);
    /**
     * 根据组织编码查询人员信息
     * @param queryForm 组织code
     * @return
     */
    IPage<User> getByOrgCodePage(UserQueryForm queryForm);

    /**
     * 根据组织编码查询当前组织及下级人员信息
     * @param orgcode 组织code
     * @return
     */
    List<User> getByOrgCodeList(String orgcode);

    /**
     * 查询用户信息
     * @param systemCode 系统CODE
     * @param roleCode 角色CODE
     * @param deptList 单位CODE
     * @return
     */
    List<User> getByRoleCode(String systemCode, String roleCode, List<String> deptList);

    /**
     * 根据token查询用户信息
     * @param token token
     * @return
     */
    UserInterfaceVo getByToken(String token);

    /**
     * 查询用户信息
     * @param id 用户ID
     * @return
     */
    UserVo getUserById(String id);

    /**
     * 查询用户信息
     * @param id 用户ID
     * @return
     */
    User getUserByIdOne(String id);

    /**
     * 查询用户信息（bpm使用）
     * @param userBpmForm
     * @return
     */
    List<UserBpmVo> getBpmUser(UserBpmForm userBpmForm);

    /**
     * 查询用户信息（bpm使用）
     * @param userBpmForm
     * @return
     */
    IPage<UserBpmVo> getBpmUserListPage(UserBpmForm userBpmForm);


    /**
     * 根据单位ID查询用户信息
     * @param id
     * @return
     */
    User getDeptById(@Param("id") String id);

    /**
     * 根据单位编码查询用户信息
     * @param deptCode
     * @return
     */
    User getDeptByCode(@Param("deptCode") String deptCode);

    /**
     * 根据角色查询用户信息
     * @param userBpmForm
     * @return
     */
    List<UserBpmVo> getBpmUserInfoByRole(UserBpmForm userBpmForm);

    /**
     * 更新状态
     * @param state
     * @param id
     * @return
     */
    int updateState(String state,String id);
}
