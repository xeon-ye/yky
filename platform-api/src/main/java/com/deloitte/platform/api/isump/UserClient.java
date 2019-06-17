package com.deloitte.platform.api.isump;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  User控制器接口
 * @Modified :
 */
public interface UserClient {

    public static final String path="/isump/user";

    /**
     *  POST---新增
     * @param userForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute UserForm userForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, userForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody UserForm userForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<UserVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   userQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<UserVo>> list(@Valid @RequestBody UserQueryForm userQueryForm);


    /**
     *  POST----复杂查询
     * @param  userQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<UserVo>> search(@Valid @RequestBody UserQueryForm userQueryForm);

    /**
     *  POST----复杂查询
     * @param  userQueryForm
     * @return
     */
    @PostMapping(value = path+"/page2/conditions")
    Result<GdcPage<UserVo>> search2(@Valid @RequestBody UserQueryForm userQueryForm);

    @PostMapping(value = path+"/rand/specialist")
    Result<List<UserVo>> randSpecialist(@Valid @RequestBody UserQueryForm userQueryForm);

    /**
     * 获取登录用户信息
     * @param token
     * @return
     */
    @GetMapping(value = path+"/logininfo/{token}")
    Result getLoginUser(@PathVariable(value = "token") String token);

    /**
     * 查看用户是否登录
     * @param token
     * @return
     */
    @GetMapping(value = path+"/haslogin/{token}")
    Result hasLogin(@PathVariable(value = "token") String token);

    /**
     * 根据职工编号查询用户信息
     * @param empNo
     * @return
     */
    @GetMapping(value = path+"/getByEmpNo/{empNo}")
    Result getByEmpNo(@PathVariable(value = "empNo") String empNo);
    /**
     * get ---- 启用账号
     * @param id
     * @return
     */
    @GetMapping(value = path+"/accountEnabled/{id}")
    Result accountEnabled(@PathVariable(value = "id") long id);

    /**
     * 根据员工编号查询部门信息
     * @param empNo 员工编号
     * @return
     */
    @GetMapping(value = path+"/getByEmpNo/dept/{empNo}")
    Result<List<OrganizationVo>> getUserVOByEmpNoDept(@PathVariable(value = "empNo") String empNo);

    /**
     * 通过组织编码查询人员信息
     * @param orgCode 组织code
     * @return
     */
    @GetMapping(value = path+"/org/{orgCode}")
    Result<List<UserVo>> getByOrgCode(@PathVariable(value = "orgCode") String orgCode);

    /**
     * 通过组织编码查询人员信息
     * @param orgCode 组织code
     * @return
     */
    @GetMapping(value = path+"/org/group/{orgCode}")
    Result<List<UserVo>> getByOrgCodeList(@PathVariable(value = "orgCode") String orgCode);


    /**
     * 通过组织编码查询人员信息
     * @param userQueryForm 组织code
     * @return
     */
    @PostMapping(value = path+"/org/group")
    Result<GdcPage<UserVo>> getByOrgCodeListPage(@Valid @RequestBody UserQueryForm userQueryForm);

    /**
     * 查询用户信息
     * @param systemCode 系统CODE
     * @param roleCode 角色CODE
     * @param deptList 单位CODE
     * @return
     */
    @PostMapping(value = path+"/role/userList")
    Result<List<UserVo>> getByRoleCode(@RequestParam(value = "systemCode") String systemCode,
                                       @RequestParam(value = "roleCode") String roleCode,
                                       @RequestParam(value = "deptList") List<String> deptList);

    /**
     * 查询用户信息
     * @param token token
     * @return
     */
    @GetMapping(value = path+"/simple/logininfo/{token}")
    Result<UserInterfaceVo> getByToken(@PathVariable(value = "token") String token);

    /**
     * 查询用户信息
     * @param id 用户ID
     * @return
     */
    @GetMapping(value = path+"/selfhelp/userInfo/{id}")
    Result<UserVo> getUserById(@PathVariable(value = "id") String id);

    /**
     * 注册用户自助更新信息
     * @param id 用户ID
     * @param userSelfHelpFormVo 更新对象
     * @return
     */
    @PostMapping(value = path+"/selfhelp/{id}")
    Result updateUserSelfHelp(@PathVariable(value = "id") long id, @Valid @RequestBody userSelfHelpFormVo userSelfHelpFormVo);

    /**
     * bpm 获取用户信息
     * @param userBpmForm
     * @return
     */
    @PostMapping(value = path+"/bpm/userInfo")
    Result<List<UserBpmVo>> getBpmUser(@RequestBody UserBpmForm userBpmForm);

    /**
     * bpm 获取用户信息
     * @param userBpmForm
     * @return
     */
    @PostMapping(value = path+"/bpm/page/userInfo")
    Result<GdcPage<UserBpmVo>> getBpmUserListPage(@RequestBody UserBpmForm userBpmForm);

    /**
     * 批量新增
     * @return
     */
    @PostMapping(value = path+"/add/list")
    Result addList(@Valid @RequestBody List<UserEBSForm> listUserEBSForm);


    /**
     * 根据角色查询用户信息
     * @return
     */
    @PostMapping(value = path+"/bpm/getUserInfoByRole")
    Result<List<UserBpmVo>> getBpmUserByRole(@RequestBody UserBpmForm userBpmForm);
}
