package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.param.FsscUserQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryParam;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.isump.entity.*;
import com.deloitte.services.isump.mapper.UserMapper;
import com.deloitte.services.isump.service.*;
import com.deloitte.services.isump.util.PasswordEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  User服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IDeputyAccountService deputyAccountService;
    @Autowired
    private IOrganizationService iOrganizationService;
    @Autowired
    private IFsscUserService iFsscUserService;
    @Autowired
    private IDeptService iDeptService;

//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        User user = userMapper.getByUserName(userId);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthority());
//    }
//
//    private List getAuthority() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }

    @Override
    public boolean save(User user) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String pwd = df.format(user.getBirthDate());
        //加密
        user.setPassword(PasswordEncoderUtil.getSaltMd5AndSha(pwd));
        //待审核
        user.setState("0");
        return super.save(user);
    }

    @Override
    public UserVo getUserVoById(long id) {
        User entity = super.getById(id);
        UserVo user = new BeanUtils<UserVo>().copyObj(entity, UserVo.class);
        if (user != null && StringUtils.isNotEmpty(user.getId())) {
            //查询用户副账号列表
            DeputyAccountQueryForm form = new DeputyAccountQueryForm();
            form.setUserId(Long.valueOf(user.getId()));
            List<DeputyAccount> list = deputyAccountService.selectList(form);
            List<DeputyAccountVo> deputyAccountVoList = new BeanUtils<DeputyAccountVo>().copyObjs(list, DeputyAccountVo.class);
            //获取orgName/orgCode
            for (DeputyAccountVo dVo : deputyAccountVoList) {
                if (dVo.getOrgId() != null) {
                    OrganizationVo orgVo = iOrganizationService.getByID(dVo.getOrgId().toString());
                    if (orgVo != null) {
                        dVo.setOrgName(orgVo.getName());
                        dVo.setOrgCode(orgVo.getCode());
                    }
                }
            }
            user.setDeputyAccount(deputyAccountVoList);
            if (deputyAccountVoList != null && deputyAccountVoList.size() > 0) {
                user.setCurrentDeputyAccount(deputyAccountVoList.get(0));
            }
        }
        return user;
    }

    /**
     * 查询部门信息
     *
     * @param empno 员工编号
     * @return
     */
    @Override
    public List<OrganizationVo> getUserVOByEmpNoDept(String empno) {
        List<OrganizationVo> organizationVoList = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EMP_NO", empno);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null && StringUtils.isNotEmpty(user.getId().toString())) {
            //查询用户副账号列表
            DeputyAccountQueryForm form = new DeputyAccountQueryForm();
            form.setUserId(Long.valueOf(user.getId()));
            List<DeputyAccount> list = deputyAccountService.selectList(form);
            if (list != null && list.size() > 0) {
                List<Organization> organizationsList = iOrganizationService.getByListID(list);
                organizationVoList = new BeanUtils<OrganizationVo>().copyObjs(organizationsList, OrganizationVo.class);
            }
        }
        return organizationVoList;
    }

    /**
     * 通过组织查询人员信息
     *
     * @param orgcode 组织code
     * @return
     */
    @Override
    public List<User> getByOrgCode(String orgcode) {
        return userMapper.getByOrgCode(orgcode);
    }

    /**
     * 通过组织查询人员信息分页
     *
     * @param queryForm 组织code
     * @return
     */
    @Override
    public IPage<User> getByOrgCodePage(UserQueryForm queryForm) {
        Page pages = new Page<User>(queryForm.getCurrentPage(), queryForm.getPageSize());
        return pages.setRecords(userMapper.getByOrgCodePage(queryForm.getOrgCode(), queryForm.getName(), queryForm.getEmpNo(),
                pages));
    }

    /**
     * 根据组织编码查询当前组织及下级人员信息
     *
     * @param orgcode 组织code
     * @return
     */
    @Override
    public List<User> getByOrgCodeList(String orgcode) {
        //判断code是否有效
        OrganizationVo orgVo = iOrganizationService.getByCode(orgcode);
        if (orgVo != null) {
            return userMapper.getByOrgCodeList(orgcode);
        }
        return null;
    }

    /**
     * 查询用户信息
     *
     * @param systemCode 系统CODE
     * @param roleCode   角色CODE
     * @param deptList   单位CODE
     * @return
     */
    @Override
    public List<User> getByRoleCode(String systemCode, String roleCode, List<String> deptList) {
        return userMapper.getByRoleCode(systemCode, roleCode, deptList);
    }

    /**
     * 根据token查询用户信息
     *
     * @param token token
     * @return
     */
    @Override
    public UserInterfaceVo getByToken(String token) {
        UserVo user = userMapper.getByToken(token);
        UserInterfaceVo userInterfaceVo = new BeanUtils<UserInterfaceVo>().copyObj(user, UserInterfaceVo.class);
        return userInterfaceVo;
    }

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public UserVo getUserById(String id) {
        UserVo user = userMapper.getById(id);
        if (user != null && StringUtils.isNotEmpty(user.getId())) {
            if (StringUtils.isNotBlank(user.getDept())) {
                QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
                deptQueryWrapper.eq("DEPT_CODE", user.getDept());
                Dept dept = iDeptService.getOne(deptQueryWrapper);
                if (dept != null) {
                    user.setDeptName(dept.getDeptName());
                    if (dept.getGroupType() != null) {
                        user.setDeptGroupType(dept.getGroupType().toString());
                    }
                }
            }

            //财务用户信息
            FsscUserQueryForm fsscUserForm = new FsscUserQueryForm();
            fsscUserForm.setUserId(user.getId());
            List<FsscUser> fsscUserList = iFsscUserService.selectList(fsscUserForm);
            List<FsscUserVo> fsscUserVoList = new BeanUtils<FsscUserVo>().copyObjs(fsscUserList, FsscUserVo.class);
            if (fsscUserVoList != null && fsscUserVoList.size() > 0) {
                user.setFsscUser(fsscUserVoList);
            } else {
                user.setFsscUser(new ArrayList<>());
            }
        }
        return user;
    }

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public User getUserByIdOne(String id) {
        return userMapper.getUserById(id);
    }

    /**
     * 查询员工信息（bpm使用）
     *
     * @param userBpmForm
     * @return
     */
    @Override
    public List<UserBpmVo> getBpmUser(UserBpmForm userBpmForm) {
//        List<UserBpmVo> userListResult = new ArrayList<UserBpmVo>();
        if (StringUtils.isBlank(userBpmForm.getEmpNo())) {
            return null;
        }
        List<UserBpmVo> userList = userMapper.getBpmUserByEmpNo(userBpmForm.getEmpNo(), userBpmForm.getOrgCode());
        return userList;
        //        if(userList.size() == 0){
//            return null;
//        }
//        for(UserBpmVo ub : userList){
//            if("org".equals(ub.getOrgType())){
//                ub.setOrgId("");
//                ub.setOrgCode("");
//                ub.setOrgName("");
//            }else if("post".equals(ub.getOrgType())){
//                //查询岗位
//                Organization orgPost = organizationMapper.getOrgByIdOrType(ub.getOrgId(), "post");
//                if(orgPost != null){
//                    ub.setStationName(orgPost.getName());
//                    //查询岗位所在部门
//                    OrganizationVo organizationVo = organizationMapper.getByCode(orgPost.getParentCode());
//                    ub.setOrgId(organizationVo.getId().toString());
//                    ub.setOrgCode(organizationVo.getCode());
//                    ub.setOrgName(organizationVo.getName());
//                }
//            }
//            userListResult.add(ub);
//        }
//        return userListResult;
    }

    /**
     * 查询用户信息  （bpm使用）
     *
     * @param userBpmForm
     * @return
     */
    @Override
    public IPage<UserBpmVo> getBpmUserListPage(UserBpmForm userBpmForm) {
//        List<UserBpmVo> userListResult = new ArrayList<UserBpmVo>();
        Page pages = new Page<>(userBpmForm.getPage(), userBpmForm.getSize());
        List<UserBpmVo> list = userMapper.getBpmUserListPage(userBpmForm.getOrgCode(), userBpmForm.getOrgNameList(), userBpmForm.getOrgCodeList(),
                userBpmForm.getRoleCodeList(), userBpmForm.getSystemName(), pages);
        return pages.setRecords(list);
//        if(list.size() == 0){
//            return null;
//        }
//        for(UserBpmVo ub : list){
//            if("org".equals(ub.getOrgType())){
//                ub.setOrgId("");
//                ub.setOrgCode("");
//                ub.setOrgName("");
//            }else if("post".equals(ub.getOrgType())){
//                //查询岗位
//                Organization orgPost = organizationMapper.getOrgByIdOrType(ub.getOrgId(), "post");
//                if(orgPost != null){
//                    ub.setStationName(orgPost.getName());
//                    //查询岗位所在部门
//                    OrganizationVo organizationVo = organizationMapper.getByCode(orgPost.getParentCode());
//                    ub.setOrgId(organizationVo.getId().toString());
//                    ub.setOrgCode(organizationVo.getCode());
//                    ub.setOrgName(organizationVo.getName());
//                }
//            }
//            userListResult.add(ub);
//        }
//        return pages.setRecords(userListResult);
    }

    /**
     * 根据单位ID查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getDeptById(String id) {
        return userMapper.getDeptById(id);
    }

    /**
     * 根据单位编码查询用户信息
     *
     * @param deptCode
     * @return
     */
    @Override
    public User getDeptByCode(String deptCode) {
        return userMapper.getDeptByCode(deptCode);
    }

    @Override
    public List<UserBpmVo> getBpmUserInfoByRole(UserBpmForm userBpmForm) {
        List<UserBpmVo> userBpmVoList = Lists.newArrayList();
        List<User> bmpUserInfoByRoleList = userMapper.getBmpUserInfoByRole(userBpmForm.getSystemCode(), userBpmForm.getRoleCodeList());
        if (bmpUserInfoByRoleList.size() > 0) {
            userBpmVoList = new BeanUtils<UserBpmVo>().copyObjs(bmpUserInfoByRoleList, UserBpmVo.class);
        }
        return userBpmVoList;
    }

    /**
     * 更新状态
     *
     * @param state
     * @param id
     * @return
     */
    @Override
    public int updateState(String state, String id) {
        return userMapper.updateState(state, id);
    }

    @Override
    public User getByUserName(String username) {
        return userMapper.getByUserName(username);
    }

    @Override
    public IPage<User> selectPage(UserQueryForm queryForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        getQueryWrapper(queryWrapper, queryForm);
        return userMapper.selectPage(new Page<User>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<User> selectList(UserQueryForm queryForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        getQueryWrapper(queryWrapper, queryForm);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<UserVo> randSpecialist(UserQueryForm userQueryForm) {
        IPage<UserVo> page = new Page<>(userQueryForm.getCurrentPage(), userQueryForm.getPageSize());
        List<UserVo> list = userMapper.randSpecialist(page, userQueryForm);
        page.setRecords(list);
        return page;
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<User> getQueryWrapper(QueryWrapper<User> queryWrapper, BaseQueryForm<UserQueryParam> queryForm) {
        UserQueryParam user = queryForm.toParam(UserQueryParam.class);
        //条件拼接
        if (StringUtils.isNotBlank(user.getName())) {
            queryWrapper.like(User.NAME, user.getName());
        }
        if (StringUtils.isNotBlank(user.getEmpNo())) {
            queryWrapper.like(User.EMP_NO, user.getEmpNo());
        }
        if (StringUtils.isNotBlank(user.getPhone())) {
            queryWrapper.like(User.PHONE, user.getPhone());
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            queryWrapper.like(User.EMAIL, user.getEmail());
        }
        if (StringUtils.isNotBlank(user.getHonor())) {
            queryWrapper.like(User.HONOR, user.getHonor());
        }
        if (StringUtils.isNotBlank(user.getType())) {
            queryWrapper.eq(User.TYPE, user.getType());
        }
        if (StringUtils.isNotBlank(user.getState())) {
            queryWrapper.eq(User.STATE, user.getState());
        }
        if (user.getExcludeIds() != null && user.getExcludeIds().size() > 0) {
            queryWrapper.notIn(User.ID, user.getExcludeIds());
        }
        if (user.getEmpNos() != null && user.getEmpNos().size() > 0) {
            queryWrapper.in(User.EMP_NO, user.getEmpNos());
        }
        if (user.getIdList() != null && user.getIdList().size() > 0) {
            queryWrapper.in(Dept.ID, user.getIdList());
        }
        //查询列表只能查询正常用户
        queryWrapper.eq(User.STATE, "1");
        //条件拼接
//    if(StringUtils.isNotBlank(user.getName())){
//    queryWrapper.like(User.NAME,user.getName());
//    }
//    if(StringUtils.isNotBlank(user.getEmpNo())){
//    queryWrapper.like(User.EMP_NO,user.getEmpNo());
//    }
//    if(StringUtils.isNotBlank(user.getPhone())){
//    queryWrapper.like(User.PHONE,user.getPhone());
//    }
//    if(StringUtils.isNotBlank(user.getEmail())){
//    queryWrapper.like(User.EMAIL,user.getEmail());
//    }
//    if(StringUtils.isNotBlank(user.getPassword())){
//    queryWrapper.like(User.PASSWORD,user.getPassword());
//    }
//    if(StringUtils.isNotBlank(user.getSalt())){
//    queryWrapper.like(User.SALT,user.getSalt());
//    }
//    if(StringUtils.isNotBlank(user.getAvatar())){
//    queryWrapper.like(User.AVATAR,user.getAvatar());
//    }
//    if(StringUtils.isNotBlank(user.getHonor())){
//    queryWrapper.like(User.HONOR,user.getHonor());
//    }
//    if(StringUtils.isNotBlank(user.getType())){
//    queryWrapper.like(User.TYPE,user.getType());
//    }
//    if(StringUtils.isNotBlank(user.getState())){
//    queryWrapper.like(User.STATE,user.getState());
//    }
//    if(StringUtils.isNotBlank(user.getCreateBy())){
//    queryWrapper.like(User.CREATE_BY,user.getCreateBy());
//    }
//    if(StringUtils.isNotBlank(user.getCreateTime())){
//    queryWrapper.like(User.CREATE_TIME,user.getCreateTime());
//    }
//    if(StringUtils.isNotBlank(user.getRemark())){
//    queryWrapper.like(User.REMARK,user.getRemark());
//    }
//    if(StringUtils.isNotBlank(user.getReserve())){
//    queryWrapper.like(User.RESERVE,user.getReserve());
//    }
//    if(StringUtils.isNotBlank(user.getVersion())){
//    queryWrapper.like(User.VERSION,user.getVersion());
//    }
//    if(StringUtils.isNotBlank(user.getUpdateTime())){
//    queryWrapper.like(User.UPDATE_TIME,user.getUpdateTime());
//    }
//    if(StringUtils.isNotBlank(user.getUpdateBy())){
//    queryWrapper.like(User.UPDATE_BY,user.getUpdateBy());
//    }
//    if(StringUtils.isNotBlank(user.getExpertise())){
//    queryWrapper.like(User.EXPERTISE,user.getExpertise());
//    }
//    if(StringUtils.isNotBlank(user.getSubject())){
//    queryWrapper.like(User.SUBJECT,user.getSubject());
//    }
//    if(StringUtils.isNotBlank(user.getGender())){
//    queryWrapper.like(User.GENDER,user.getGender());
//    }
//    if(StringUtils.isNotBlank(user.getBirthDate())){
//    queryWrapper.like(User.BIRTH_DATE,user.getBirthDate());
//    }
//    if(StringUtils.isNotBlank(user.getPositionTitle())){
//    queryWrapper.like(User.POSITION_TITLE,user.getPositionTitle());
//    }
//    if(StringUtils.isNotBlank(user.getEducation())){
//    queryWrapper.like(User.EDUCATION,user.getEducation());
//    }
//    if(StringUtils.isNotBlank(user.getMajor())){
//    queryWrapper.like(User.MAJOR,user.getMajor());
//    }
//    if(StringUtils.isNotBlank(user.getTel())){
//    queryWrapper.like(User.TEL,user.getTel());
//    }
//    if(StringUtils.isNotBlank(user.getFax())){
//    queryWrapper.like(User.FAX,user.getFax());
//    }
//    if(StringUtils.isNotBlank(user.getIdCardType())){
//    queryWrapper.like(User.ID_CARD_TYPE,user.getIdCardType());
//    }
//    if(StringUtils.isNotBlank(user.getIdCard())){
//    queryWrapper.like(User.ID_CARD,user.getIdCard());
//    }
//    if(StringUtils.isNotBlank(user.getEducationCountry())){
//    queryWrapper.like(User.EDUCATION_COUNTRY,user.getEducationCountry());
//    }
//    if(StringUtils.isNotBlank(user.getDept())){
//    queryWrapper.like(User.DEPT,user.getDept());
//    }
//    if(StringUtils.isNotBlank(user.getWorkPerYear())){
//    queryWrapper.like(User.WORK_PER_YEAR,user.getWorkPerYear());
//    }
//    if(StringUtils.isNotBlank(user.getAddress())){
//    queryWrapper.like(User.ADDRESS,user.getAddress());
//    }
//    if(StringUtils.isNotBlank(user.getZipCode())){
//    queryWrapper.like(User.ZIP_CODE,user.getZipCode());
//    }
//    if(StringUtils.isNotBlank(user.getLiveBaseName())){
//    queryWrapper.like(User.LIVE_BASE_NAME,user.getLiveBaseName());
//    }
//    if(StringUtils.isNotBlank(user.getTalentPlan())){
//    queryWrapper.like(User.TALENT_PLAN,user.getTalentPlan());
//    }
//    if(StringUtils.isNotBlank(user.getSourcePersonId())){
//    queryWrapper.like(User.SOURCE_PERSON_ID,user.getSourcePersonId());
//    }
//    if(StringUtils.isNotBlank(user.getDegree())){
//    queryWrapper.like(User.DEGREE,user.getDegree());
//    }
//    if(StringUtils.isNotBlank(user.getOfficeName())){
//    queryWrapper.like(User.OFFICE_NAME,user.getOfficeName());
//    }
//    if(StringUtils.isNotBlank(user.getProjectCommitmentUnit())){
//    queryWrapper.like(User.PROJECT_COMMITMENT_UNIT,user.getProjectCommitmentUnit());
//    }
        return queryWrapper;
    }


    @Override
    public String login(User user) {
        userMapper.updateToken(user);
        userMapper.updateFirstLogin(user);
        return user.getToken();
    }

    @Override
    public Long hasLogin(String token) {
        UserVo user = userMapper.getByToken(token);
        if (user != null && user.getId() != null && Long.parseLong(user.getId()) > 0) {
            return Long.parseLong(user.getId());
        } else {
            return null;
        }
    }

    @Override
    public UserVo getUserByToken(String token) {
        UserVo user = userMapper.getByToken(token);
        if (user != null && StringUtils.isNotEmpty(user.getId())) {
            if (StringUtils.isNotBlank(user.getDept())) {
                QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
                deptQueryWrapper.eq("DEPT_CODE", user.getDept());
                Dept dept = iDeptService.getOne(deptQueryWrapper);
                if (dept != null) {
                    user.setDeptName(dept.getDeptName());
                    if (dept.getGroupType() != null) {
                        user.setDeptGroupType(dept.getGroupType().toString());
                    }
                }
            }

            //查询用户副账号列表
            DeputyAccountQueryForm form = new DeputyAccountQueryForm();
            form.setUserId(Long.valueOf(user.getId()));
            List<DeputyAccount> list = deputyAccountService.selectList(form);
            List<DeputyAccountVo> deputyAccountVoList = new BeanUtils<DeputyAccountVo>().copyObjs(list, DeputyAccountVo.class);
            //获取orgName/orgCode
            for (DeputyAccountVo dVo : deputyAccountVoList) {
                if (dVo.getOrgId() != null) {
                    OrganizationVo orgVo = iOrganizationService.getByID(dVo.getOrgId().toString());
                    if (orgVo != null) {
                        dVo.setOrgName(orgVo.getName());
                        dVo.setOrgCode(orgVo.getCode());
                    }
                }
            }
            if (deputyAccountVoList != null && deputyAccountVoList.size() > 0) {
                user.setDeputyAccount(deputyAccountVoList);
            } else {
                user.setDeputyAccount(new ArrayList<>());
            }

            //财务用户信息
            FsscUserQueryForm fsscUserForm = new FsscUserQueryForm();
            fsscUserForm.setUserId(user.getId());
            List<FsscUser> fsscUserList = iFsscUserService.selectList(fsscUserForm);
            List<FsscUserVo> fsscUserVoList = new BeanUtils<FsscUserVo>().copyObjs(fsscUserList, FsscUserVo.class);
            if (fsscUserVoList != null && fsscUserVoList.size() > 0) {
                user.setFsscUser(fsscUserVoList);
            } else {
                user.setFsscUser(new ArrayList<>());
            }
            if (deputyAccountVoList != null && deputyAccountVoList.size() > 0) {
                user.setCurrentDeputyAccount(deputyAccountVoList.get(0));
            } else {
                user.setCurrentDeputyAccount(new DeputyAccountVo());
            }
        }
        return user;
    }

    @Override
    public void logout(String token) {
        userMapper.logout(token);
    }
}

