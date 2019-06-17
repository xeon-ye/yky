package com.deloitte.services.isump.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.UserBpmVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.isump.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserVo> randSpecialist(IPage<UserVo> page , @Param("user") UserQueryForm user);

    User getByUserName(String username);

    int updateToken(User user);
    int updateFirstLogin(User user);

    UserVo getByToken(String token);

    int logout(String token);

    /**
     * 组织编码查询人员信息
     * @param orgcode 组织code
     * @return
     */
    List<User> getByOrgCode(@Param("orgcode") String orgcode);

    List<User> getByOrgCodePage(@Param("orgcode") String orgcode,
                                @Param("name") String name,
                                @Param("empNo") String empNo,
                                @Param("page") IPage page);

    /**
     * 组织编码查询当前组织及下级组织人员信息
     * @param orgcode 组织code
     * @return
     */
    List<User> getByOrgCodeList(@Param("orgcode") String orgcode);


    /**
     * 查询用户信息
     * @param systemCode 系统CODE
     * @param roleCode 角色CODE
     * @param deptList 单位code
     * @return
     */
    List<User> getByRoleCode(@Param("systemCode") String systemCode,
                             @Param("roleCode") String roleCode,
                             @Param("deptList") List<String> deptList);


    /**
     * 查询用户信息
     * @param id 用户ID
     * @return
     */
    UserVo getById(@Param("id") String id);

    /**
     * 查询用户信息
     * @param id 用户ID
     * @return
     */
    User getUserById(@Param("id") String id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delById(@Param("id") String id);

    /**
     * BPM 根据员工编号和组织编号查询用户信息
     * @param empNo
     * @return
     */
    List<UserBpmVo> getBpmUserByEmpNo(@Param("empNo") String empNo, @Param("orgCode") String orgCode);

    /**
     *  BPM 查询员工信息(分页查询)
     *  @param deptCode  岗位名称
     * @param orgName  岗位名称
     * @param orgCode 组织编号
     * @param roleCode 角色编号
     * @param page 分页参数
     * @return
     */
    List<UserBpmVo> getBpmUserListPage(@Param("deptCode") String deptCode,
                                       @Param("orgName") List<String> orgName,
                                       @Param("orgCode") List<String> orgCode,
                                       @Param("roleCode") List<String> roleCode,
                                       @Param("systemName") String systemName,
                                       @Param("page") IPage page);

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

    List<User> getBmpUserInfoByRole(@Param("systemCode") String systemCode,
                                    @Param("roleCodeList") List<String> roleCodeList);

    /**
     * 更新状态
     * @param state 状态
     * @param id ID
     * @return
     */
    int updateState(@Param("state") String state,@Param("id") String id);
}
