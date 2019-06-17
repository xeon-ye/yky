package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.UserClient;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.isump.entity.*;
import com.deloitte.services.isump.exception.IsumpErrorType;
import com.deloitte.services.isump.returnEntity.UserEBSFlage;
import com.deloitte.services.isump.service.*;
import com.deloitte.services.isump.util.PasswordEncoderUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   User控制器实现类
 * @Modified :
 */
@Api(tags = "User操作接口")
@Slf4j
@RestController
public class UserController implements UserClient {

    @Autowired
    public IUserService userService;
    @Autowired
    public IDeptService deptService;
    @Autowired
    public IBpmService bpmService;
    @Autowired
    private IFsscUserService FsscUserService;
    @Autowired
    private IDeputyAccountService deputyAccountService;
    @Autowired
    private IOrganizationService iOrganizationService;
    @Autowired
    private IDeputyAccountRoleService deputyAccountRoleService;
    @Autowired
    private IRoleService roleService;


    @Override
    @ApiOperation(value = "新增User", notes = "新增一个User")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result add(@Valid @RequestBody @ApiParam(name = "userForm", value = "新增User的form表单", required = true) UserForm userForm) {
        log.info("form:", userForm.toString());
        User user = new BeanUtils<User>().copyObj(userForm, User.class);
        //验证手机号、邮箱、身份证号码是否已经注册
        User userEntity = null;
        if(StringUtils.isNotBlank(user.getPhone())){
            userEntity = userService.getByUserName(user.getPhone());
            if(userEntity != null){
                if(Integer.parseInt(userEntity.getState()) >= 0){
                    throw new BaseException(PlatformErrorType.USER_PHONE_ERROR);
                }
            }
        }
        if(StringUtils.isNotBlank(user.getEmail())){
            userEntity = userService.getByUserName(user.getEmail());
            if(userEntity != null){
                if(Integer.parseInt(userEntity.getState()) >= 0){
                    throw new BaseException(PlatformErrorType.USER_EMAIL_ERROR);
                }
            }
        }
        if(StringUtils.isNotBlank(user.getIdCard())){
            userEntity = userService.getByUserName(user.getIdCard());
            if(userEntity != null){
                if(Integer.parseInt(userEntity.getState()) >= 0){
                    throw new BaseException(PlatformErrorType.USER_IDCARD_ERROR);
                }
            }
        }
//        if(bindingResult.hasErrors()){
//            throw new BaseException(PlatformErrorType.USER_SAVE_ERROR, bindingResult.getFieldError().getDefaultMessage());
//        }

        if(userEntity != null){
            user.setId(userEntity.getId());
            user.setState("0");
            userService.updateById(user);
        }else{
            //生成员工编码（当前年份+单位编码+随机四位）
            user.setEmpNo(produceEmpNo(user.getDept()));
            userService.save(user);
            System.out.println("保存结果ID："+user.getId());
            //添加副账号
            DeputyAccount deputyAccount = new DeputyAccount();
            deputyAccount.setUserId(user.getId());
            //deputyAccount.setOrgId(26L);
            deputyAccountService.save(deputyAccount);
            //添加用户表默认副账号
            User user2 = new User();
            user2.setId(user.getId());
            user2.setDeputyAccountId(deputyAccount.getId());
            userService.updateById(user2);
        }
        //调用流程
        Result result=bpmService.submitProcess(user, userForm.getObjectUrl());
        if(result.isFail()){
            if(user.getId() != null){
                userService.updateState("-1", user.getId().toString());
            }
            throw new BaseException(PlatformErrorType.USER_SAVE_ERROR);
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "删除User", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "UserID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        userService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改User", notes = "修改指定User信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "User的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
            @Valid @RequestBody @ApiParam(name = "userForm", value = "修改User的form表单", required = true) UserForm userForm) {
        User user = new BeanUtils<User>().copyObj(userForm, User.class);
        user.setId(id);
        userService.saveOrUpdate(user);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取User", notes = "获取指定ID的User信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "User的ID", required = true, dataType = "long")
    public Result<UserVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        UserVo user = userService.getUserVoById(id);
        if (StringUtils.isNotEmpty(user.getDept())) {
            DeptVo dept = deptService.getByCode(user.getDept());
            if(dept != null){
                user.setDeptName(dept.getDeptName());
                if(dept.getGroupType() != null){
                    user.setDeptGroupType(dept.getGroupType().toString());
                }
            }
        }
        return new Result<UserVo>().sucess(user);
    }

    @Override
    @ApiOperation(value = "启用用户", notes = "根据指定ID的启用用户账号")
    @ApiImplicitParam(paramType = "path", name = "id", value = "User的ID", required = true, dataType = "long")
    public Result accountEnabled(@PathVariable long id) {
        User user = userService.getById(id);
        user.setState("1");
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation(value = "根据员工编号查询部门信息")
    @Override
    public Result<List<OrganizationVo>> getUserVOByEmpNoDept(@PathVariable String empNo) {
        List<OrganizationVo> list = userService.getUserVOByEmpNoDept(empNo);
        return new Result<List<OrganizationVo>>().sucess(list);
    }

    @Override
    @ApiOperation(value = "列表查询User", notes = "根据条件查询User列表数据")
    public Result<List<UserVo>> list(
            @Valid @RequestBody @ApiParam(name = "userQueryForm", value = "User查询参数", required = true) UserQueryForm userQueryForm) {
        log.info("search with userQueryForm:", userQueryForm.toString());
        List<User> userList = userService.selectList(userQueryForm);
        List<UserVo> userVoList = new BeanUtils<UserVo>().copyObjs(userList, UserVo.class);
        //TODO 这里后面要改为读取缓存
        for (UserVo uv : userVoList) {
            if (StringUtils.isNotEmpty(uv.getDept())) {
                DeptVo dept = deptService.getByCode(uv.getDept());
                if(dept != null){
                    uv.setDeptName(dept.getDeptName());
                    if(dept.getGroupType() != null){
                        uv.setDeptGroupType(dept.getGroupType().toString());
                    }
                }
            }
        }
        return new Result<List<UserVo>>().sucess(userVoList);
    }


    @Override
    @ApiOperation(value = "分页查询User", notes = "根据条件查询User分页数据")
    public Result<IPage<UserVo>> search(
            @Valid @RequestBody @ApiParam(name = "userQueryForm", value = "User查询参数", required = true) UserQueryForm userQueryForm) {
        log.info("search with userQueryForm:", userQueryForm.toString());
        IPage<User> userPage = userService.selectPage(userQueryForm);
        IPage<UserVo> userVoPage = new BeanUtils<UserVo>().copyPageObjs(userPage, UserVo.class);
        for (UserVo uv : userVoPage.getRecords()) {
            if (StringUtils.isNotEmpty(uv.getDept())) {
                DeptVo dept = deptService.getByCode(uv.getDept());
                if(dept != null){
                    uv.setDeptName(dept.getDeptName());
                    if(dept.getGroupType() != null){
                        uv.setDeptGroupType(dept.getGroupType().toString());
                    }
                }
            }
        }
        return new Result<IPage<UserVo>>().sucess(userVoPage);
    }

    @Override
    @ApiOperation(value = "分页查询User", notes = "根据条件查询User分页数据")
    @ApiParam(name = "userQueryForm", value = "User查询参数", required = true)
    public Result<GdcPage<UserVo>> search2(@Valid @RequestBody UserQueryForm userQueryForm) {
        log.info("search with userQueryForm:", userQueryForm.toString());
        IPage<User> userPage = userService.selectPage(userQueryForm);
        IPage<UserVo> userVoPage = new BeanUtils<UserVo>().copyPageObjs(userPage, UserVo.class);
        for (UserVo uv : userVoPage.getRecords()) {
            if (StringUtils.isNotEmpty(uv.getDept())) {
                DeptVo dept = deptService.getByCode(uv.getDept());
                if(dept!=null){
                    uv.setDeptName(dept.getDeptName());
                    if(dept.getGroupType() != null){
                        uv.setDeptGroupType(dept.getGroupType().toString());
                    }
                }
            }
        }
        return new Result<GdcPage<UserVo>>().sucess(new GdcPage<UserVo>(userVoPage));
    }

    @Override
    @ApiOperation(value = "抽取专家", notes = "根据条件抽取专家列表")
    public Result<List<UserVo>> randSpecialist(
            @Valid @RequestBody @ApiParam(name = "userQueryForm", value = "User查询参数",
                    required = true) UserQueryForm userQueryForm) {
        IPage<UserVo> userVoPage = userService.randSpecialist(userQueryForm);
        List<UserVo> list = userVoPage.getRecords();
        return new Result<List<UserVo>>().sucess(list);
    }

    @Override
    @ApiOperation(value = "获取登录用户信息", notes = "根据token获取登录用户信息")
    public Result getLoginUser(@PathVariable String token) {
        UserVo user = userService.getUserByToken(token);
        if (user != null && user.getId() != null) {
            return new Result<UserVo>().sucess(user);
        } else {
            return Result.fail(PlatformErrorType.NO_LOGIN);
        }
    }

    @Override
    @ApiOperation(value = "用户是否登录", notes = "根据token查看用户是否登录")
    public Result hasLogin(@PathVariable String token) {
        Long id = userService.hasLogin(token);
        if (id != null) {
            return Result.success(new User());
        } else {
            return Result.fail(PlatformErrorType.NO_LOGIN);
        }
    }

    @Override
    @ApiOperation(value = "根据职工编号查询用户信息", notes = "根据职工编号empNo查询用户信息")
    public Result getByEmpNo(@PathVariable(value = "empNo") String empNo) {
        User user = userService.getByUserName(empNo);
        //查询用户副账号列表
        DeputyAccountQueryForm form = new DeputyAccountQueryForm();
        form.setUserId(Long.valueOf(user.getId()));
        List<DeputyAccount> list = deputyAccountService.selectList(form);
        List<DeputyAccountVo> deputyAccountVoList=new BeanUtils<DeputyAccountVo>().copyObjs(list,DeputyAccountVo.class);
        //获取orgName/orgCode
        for(DeputyAccountVo dVo : deputyAccountVoList){
            if(dVo.getOrgId() != null){
                OrganizationVo orgVo= iOrganizationService.getByID(dVo.getOrgId().toString());
                if(orgVo != null){
                    dVo.setOrgName(orgVo.getName());
                    dVo.setOrgCode(orgVo.getCode());
                }
            }
        }
        UserVo userVo = new BeanUtils<UserVo>().copyObj(user, UserVo.class);
        userVo.setCurrentDeputyAccount(deputyAccountVoList.get(0));
        return new Result<UserVo>().sucess(userVo);
    }


    /**
     * 修改密码
     * @param
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = path+"/password/modify")
    public Result modifyPwd(@Valid @RequestBody @ApiParam(name = "userQueryForm", value = "User参数",
            required = true) UserQueryForm userQueryForm){
        UserVo vo = userService.getUserByToken(userQueryForm.getToken());
        if(vo == null || StringUtils.isEmpty(vo.getId())){
            return Result.fail(new ServiceException(IsumpErrorType.NO_USER));
        }
        //查询密码
        User user = userService.getById(vo.getId());
        //验证密码
        boolean b = PasswordEncoderUtil.passwordVerify(userQueryForm.getPassword(),user.getPassword());
        //验证通过则修改密码
        if(b){
            user.setPassword(PasswordEncoderUtil.getSaltMd5AndSha(userQueryForm.getNewPassword()));
            userService.updateById(user);
            return Result.success();
        }
        return Result.fail(new ServiceException(IsumpErrorType.PASSWORD_ERROR));
    }

    @Override
    @ApiOperation(value = "根据组织查询人员信息", notes = "根据组织编码查询组织人员信息")
    public Result<List<UserVo>> getByOrgCode(@PathVariable(value = "orgCode") String orgCode) {
        List<User> userList = userService.getByOrgCode(orgCode);
        List<UserVo> userVoList = new BeanUtils<UserVo>().copyObjs(userList, UserVo.class);
        return new Result<List<UserVo>>().sucess(userVoList);
    }

    @Override
    @ApiOperation(value = "查询组织及下级组织人员信息", notes = "根据组织编码查询当前组织及下级组织人员信息")
    public Result<List<UserVo>> getByOrgCodeList(@PathVariable(value = "orgCode") String orgCode) {
        List<User> userList = userService.getByOrgCode(orgCode);
        List<UserVo> userVoList = new BeanUtils<UserVo>().copyObjs(userList, UserVo.class);
        return new Result<List<UserVo>>().sucess(userVoList);
    }

    @Override
    @ApiOperation(value = "查询组织及下级组织人员信息", notes = "根据组织编码查询当前组织及下级组织人员信息（分页）")
    public Result<GdcPage<UserVo>> getByOrgCodeListPage(@Valid @RequestBody UserQueryForm userQueryForm) {
        if(StringUtils.isEmpty(userQueryForm.getOrgCode())){
            throw new BaseException(PlatformErrorType.ARGUMENT_NOT_VALID);
        }
        IPage<User> userList = userService.getByOrgCodePage(userQueryForm);
        IPage<UserVo> userVoList = new BeanUtils<UserVo>().copyPageObjs(userList, UserVo.class);
        return new Result<GdcPage<UserVo>>().sucess(new GdcPage<UserVo>(userVoList));
    }

    @Override
    @ApiOperation(value = "根据系统 角色 单位查询用户信息", notes = "根据系统code 角色code 单位code查询用户信息")
    public Result<List<UserVo>> getByRoleCode(@ApiParam(value = "系统code", required = true) @RequestParam(value = "systemCode") String systemCode,
                                              @ApiParam(value = "角色code", required = true) @RequestParam(value = "roleCode") String roleCode,
                                              @ApiParam(value = "单位code", required = true) @RequestParam(value = "deptList") List<String> deptList) {
        List<User> userList = userService.getByRoleCode(systemCode, roleCode, deptList);
        List<UserVo> userVoList = new BeanUtils<UserVo>().copyObjs(userList, UserVo.class);
        return new Result<List<UserVo>>().sucess(userVoList);
    }

    @Override
    @ApiOperation(value = "token获取用户基本信息", notes = "根据token查询用户简单基本信息")
    public Result<UserInterfaceVo> getByToken(@PathVariable(value = "token") String token) {
        UserInterfaceVo userInterfaceVo = userService.getByToken(token);
        return new Result<UserInterfaceVo>().sucess(userInterfaceVo);
    }

    @Override
    @ApiOperation(value = "根据用户ID查询注册用户信息", notes = "根据用户ID查询注册用户信息")
    public Result<UserVo> getUserById(@PathVariable(value = "id") String id) {
        UserVo userVo = userService.getUserById(id);
        return new Result<UserVo>().sucess(userVo);
    }

    @Override
    @ApiOperation(value = "注册用户自助更新信息", notes = "注册用户自助更新信息")
    public Result updateUserSelfHelp(@ApiParam(value = "用户ID", required = true) @PathVariable(value = "id") long id,
                                     @ApiParam(value = "详情", required = true) @Valid @RequestBody userSelfHelpFormVo userSelfHelpFormVo) {
        User user = new BeanUtils<User>().copyObj(userSelfHelpFormVo, User.class);
        FsscUser fsscUser = new BeanUtils<FsscUser>().copyObj(userSelfHelpFormVo.getFsscUser(), FsscUser.class);
        user.setId(id);
        userService.saveOrUpdate(user);
        //保存或更新fssc_user
        String fsscUserId = userSelfHelpFormVo.getFsscUser().getId();
        if(StringUtils.isNotBlank(fsscUserId)){
            fsscUser.setId(Long.parseLong(fsscUserId));
        }
        fsscUser.setUserId(String.valueOf(id));
        FsscUserService.saveOrUpdate(fsscUser);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "BPM 获取人员信息")
    public Result<List<UserBpmVo>> getBpmUser(@RequestBody UserBpmForm userBpmForm) {
        List<UserBpmVo> list = userService.getBpmUser(userBpmForm);
        return new Result<List<UserBpmVo>>().sucess(list);
    }

    @Override
    @ApiOperation(value = "BPM 获取人员信息列表" ,notes = "来源系统（账务：fssc  门户：oaservice 合同：" +
            "contract  决策：dssv1 hr: hr  科研:srpmp）")
    public Result<GdcPage<UserBpmVo>> getBpmUserListPage(@RequestBody UserBpmForm userBpmForm) {
        IPage<UserBpmVo> list = userService.getBpmUserListPage(userBpmForm);
        if(list != null){
            return new Result<GdcPage<UserBpmVo>>().sucess(new GdcPage<UserBpmVo>(list));
        }else{
            return Result.success();
        }
    }

    @Override
    @ApiOperation(value = "批量添加用户")
    public Result addList(@Valid @RequestBody List<UserEBSForm> listUserEBSForm) {
        List<UserEBSFlage> errList = new ArrayList<>();
        for(UserEBSForm userEBSForm : listUserEBSForm){
            try {
                User user = new BeanUtils<User>().copyObj(userEBSForm, User.class);
                user.setState("1");
                user.setFirstLogin("1");
                if(user.getDept().length() > 4){
                    String uDept = user.getDept();
                    user.setDept(uDept.substring(0,4));
                }
                //查询员工编号是否存
                QueryWrapper<User> userAllQueryWrapper = new QueryWrapper<>();
                userAllQueryWrapper.eq("EMP_NO", user.getEmpNo());
                User userResult = userService.getOne(userAllQueryWrapper);
                if(userResult != null){
                    user.setId(userResult.getId());
                    userService.updateById(user);
                }else{
                    user.setPassword(PasswordEncoderUtil.getSaltMd5AndSha("666666"));
                    boolean saveState = userService.save(user);
                    if(saveState){
                        //添加副账号
                        DeputyAccount deputyAccount = new DeputyAccount();
                        deputyAccount.setUserId(user.getId());
                        deputyAccount.setOrgId(Long.valueOf("26"));
                        deputyAccountService.save(deputyAccount);
                        //配置角色关系
                        //财务
                        List<Role> roleList = roleService.getRoleList("fssc", "ADMIN");
                        for(Role r : roleList){
                            //添加副账号和角色的关系
                            DeputyAccountRole dar = new DeputyAccountRole();
                            dar.setDeputyAccountId(deputyAccount.getId());
                            dar.setRoleId(r.getId());
                            deputyAccountRoleService.save(dar);
                        }
                        //科研
                        List<Role> fsscRoleList = roleService.getRoleList("srpmp", "admin");
                        for(Role r : fsscRoleList){
                            //添加副账号和角色的关系
                            DeputyAccountRole dar = new DeputyAccountRole();
                            dar.setDeputyAccountId(deputyAccount.getId());
                            dar.setRoleId(r.getId());
                            deputyAccountRoleService.save(dar);
                        }
                    }
                }
            } catch (Exception e){
                UserEBSFlage userEBSFlage =  new UserEBSFlage();
                userEBSFlage.setEmpNo(userEBSForm.getEmpNo());
                userEBSFlage.setIdCard(userEBSForm.getIdCard());
                userEBSFlage.setErrorMessage(e.getMessage());
                errList.add(userEBSFlage);
                continue;
            }
        }
        if(errList.size() > 0){
            return  Result.fail(errList);
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "根据角色查询用户信息")
    public Result<List<UserBpmVo>> getBpmUserByRole(UserBpmForm userBpmForm) {
        List<UserEBSFlage> errList = Lists.newArrayList();
        String systemCode = userBpmForm.getSystemCode();
        if (StringUtils.isEmpty(systemCode)) {
            return Result.fail(new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID));
        }

        List<String> roleCodeList = userBpmForm.getRoleCodeList();
        if (roleCodeList == null || roleCodeList.size() <= 0) {
            return Result.fail(new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID));
        }

        List<UserBpmVo> list = userService.getBpmUserInfoByRole(userBpmForm);
        return new Result<List<UserBpmVo>>().sucess(list);
    }


    /**
     * 生成员工编号
     */
    private String produceEmpNo(String deptCode){
        String emp_no;
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        //验证编码是否重复
        for(;;){
            String str ="0123456789";
            String s = "";
            for(int i=0; i<4; i++){
                char ch = str.charAt(new Random().nextInt(str.length()));
                s += ch;
            }
            System.out.println(s);
            emp_no = year+deptCode+s;
            User user = userService.getByUserName(emp_no);
            if(user == null || user.getId() == null){
                break;
            }
        }
        return emp_no;
    }
}



