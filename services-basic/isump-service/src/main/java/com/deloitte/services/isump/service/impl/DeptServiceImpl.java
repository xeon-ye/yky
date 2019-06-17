package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.DeptQueryParam;
import com.deloitte.platform.api.isump.vo.AttachmentForm;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.isump.entity.*;
import com.deloitte.services.isump.mapper.DeptMapper;
import com.deloitte.services.isump.service.*;
import com.deloitte.services.isump.util.ConfigPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description :  Dept服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    IUserService userService;
    @Autowired
    IAttachmentService attachmentService;
    @Autowired
    public IBpmService bpmService;
    @Autowired
    private IDeputyAccountService deputyAccountService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDeputyAccountRoleService deputyAccountRoleService;
    @Autowired
    private ConfigPropertiesUtil configUtil;

    @Override
    public Result save2(DeptForm dept){
        Dept entity = new Dept();
        //查询随机数是否存在
        for(;;){
            int code = new Random().nextInt(8999)+1000;
            DeptVo temp = deptMapper.getByCode(String.valueOf(code));
            if(temp == null || StringUtils.isEmpty(temp.getId())){
                dept.setDeptCode(String.valueOf(code));
                break;
            }
        }
        //验证主要联系人是否已存在
        User user = userService.getByUserName(dept.getMainPhone());
        if(user != null){
            if(Integer.parseInt(user.getState()) >= 0){
                throw new BaseException(PlatformErrorType.USER_PHONE_ERROR);
            }
        }else{
            user = userService.getByUserName(dept.getMainEmail());
            if(user != null){
                if(Integer.parseInt(user.getState()) >= 0){
                    throw new BaseException(PlatformErrorType.USER_EMAIL_ERROR);
                }
            }
        }
        if(user != null){
            user.setState("0");
            userService.updateById(user);
        }else{
            user = new User();
            user.setPhone(dept.getMainPhone());
            user.setName(dept.getMainName());
            user.setEmail(dept.getMainEmail());
            user.setGender(dept.getMainGender());
            user.setPositionTitle(dept.getMainJob());
            user.setBirthDate(dept.getMainBothday());
            user.setDept(String.valueOf(dept.getDeptCode()));
            user.setEmpNo(produceEmpNo());
            userService.save(user);
            //添加副账号
            DeputyAccount deputyAccount = new DeputyAccount();
            deputyAccount.setUserId(user.getId());
            deputyAccountService.save(deputyAccount);
            //添加用户表默认副账号
            User user2 = new User();
            user2.setId(user.getId());
            user2.setDeputyAccountId(deputyAccount.getId());
            userService.updateById(user2);
            //添加协同办公审批权限
            List<Role> roleList = roleService.getRoleList(configUtil.systemNameOaService, configUtil.oaserviceRole);
            for(Role r : roleList){
                DeputyAccountRole dar = new DeputyAccountRole();
                dar.setDeputyAccountId(deputyAccount.getId());
                dar.setRoleId(r.getId());
                deputyAccountRoleService.save(dar);
            }
        }
        //保存单位
        entity = new BeanUtils<Dept>().copyObj(dept,Dept.class);
        Dept deptName = deptMapper.getByName2(entity.getDeptName());
        if(deptName != null){
            if(Integer.parseInt(deptName.getState()) >= 0){
                //throw new BaseException(PlatformErrorType.DPET_NAME_ERROR);
            }else{
                entity.setId(deptName.getId());
                entity.setState("0");
                entity.setGroupType(2);
                super.updateById(entity);
                //删除原有附件
                attachmentService.delByMasterId(entity.getId().toString());
            }
        }else{
            //设置成外部单位
            entity.setGroupType(2);
            super.save(entity);
        }
        //保存单位附件
        List<AttachmentForm> atts = dept.getAttachments();
        List<Attachment> list = new BeanUtils<Attachment>().copyObjs(atts,Attachment.class);
        for (int i = 0; i < list.size(); i++) {
            Attachment attachment = list.get(i);
            if( i == 0){
                attachment.setFileName("独立法人资格证书");
            } else if( i == 1){
                attachment.setFileName("组织机构代码证书");
            } else if( i == 2){
                attachment.setFileName("银行账户开户许可证书");
            }
            attachment.setMasterType("ISUMP_DEPT");
            attachment.setMasterId(entity.getId());
        }
        attachmentService.saveBatch(list);
        //调用流程审批
        Map<String, String> map = new HashMap<String, String>();
        //下一个审批人参数
        map.put("taskId", configUtil.orgRegisterTaskId);
        map.put("processDefineKey", configUtil.orgRegisterProcessDefineKey);
        map.put("chargeOrg", configUtil.orgRegisterChargeOrg);
        map.put("objectUrl", dept.getObjectUrl());
        //流程参数
        map.put("sourceSystem", configUtil.systemNameOaService);
        map.put("objectType", configUtil.orgrRegisterObjectType);
        map.put("objectDescription", entity.getDeptName());
        User userProcess = new User();
        userProcess.setId(entity.getId());
        userProcess.setName(user.getName());
        userProcess.setDept(user.getDept());
        Result result = bpmService.submitProcessOrg(map, userProcess, entity);
        if(result.isFail()){
            if(entity.getId() != null){
                deptMapper.updateState("-1", entity.getId().toString());
                throw new BaseException(PlatformErrorType.ORG_SAVE_ERROR);
            }
        }
        return Result.success();
    }

    @Override
    public IPage<Dept> selectPage(DeptQueryForm queryForm ) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper <Dept>();
        getQueryWrapper(queryWrapper,queryForm);
        return deptMapper.selectPage(new Page<Dept>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Dept> selectList(DeptQueryForm queryForm) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper <Dept>();
        getQueryWrapper(queryWrapper,queryForm);
        return deptMapper.selectList(queryWrapper);
    }

    @Override
    public DeptVo getByCode(String code){
        DeptVo dept = deptMapper.getByCode(code);
        return dept;
    }

    @Override
    public DeptVo getByName(String name){
        DeptVo dept = deptMapper.getByName(name);
        return dept;
    }

    @Override
    public boolean deptEnabled(long id) {
        //启用单位
        Dept dept = deptMapper.selectById(id);
        dept.setState("1");
        deptMapper.updateById(dept);
        //启用单位主管人
        User user = userService.getByUserName(dept.getMainTell());
        user.setState("1");
        return userService.updateById(user);
    }

    @Override
    public int updateState(String state, String id) {
        return deptMapper.updateState(state, id);
    }

    /**
     * 生成员工编号
     */
    private String produceEmpNo(){
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
            emp_no = "WB"+year+s;
            User user = userService.getByUserName(emp_no);
            if(user == null || user.getId() == null){
                break;
            }
        }
        return emp_no;
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<Dept> getQueryWrapper(QueryWrapper<Dept> queryWrapper, BaseQueryForm<DeptQueryParam> queryForm){
        DeptQueryParam dept = queryForm.toParam(DeptQueryParam.class);
        //条件拼接
        if(StringUtils.isNotBlank(dept.getDeptName())){
            queryWrapper.like(Dept.DEPT_NAME,dept.getDeptName());
        }
        if(StringUtils.isNotBlank(dept.getDeptCode())){
            queryWrapper.like(Dept.DEPT_CODE,dept.getDeptCode());
        }
        if(StringUtils.isNotBlank(dept.getTaxpayerNumber())){
            queryWrapper.like(Dept.TAXPAYER_NUMBER,dept.getTaxpayerNumber());
        }
        if(StringUtils.isNotBlank(dept.getZipCode())){
            queryWrapper.like(Dept.ZIP_CODE,dept.getZipCode());
        }
        if(StringUtils.isNotBlank(dept.getAddress())){
            queryWrapper.like(Dept.ADDRESS,dept.getAddress());
        }
        if(StringUtils.isNotBlank(dept.getContactsName())){
            queryWrapper.like(Dept.CONTACTS_NAME,dept.getContactsName());
        }
        if(StringUtils.isNotBlank(dept.getPhone())){
            queryWrapper.like(Dept.PHONE,dept.getPhone());
        }
        if(StringUtils.isNotBlank(dept.getEmail())){
            queryWrapper.like(Dept.EMAIL,dept.getEmail());
        }
        if(StringUtils.isNotBlank(dept.getFaxNumber())){
            queryWrapper.like(Dept.FAX_NUMBER,dept.getFaxNumber());
        }
        if(StringUtils.isNotBlank(dept.getDeptQuality())){
            queryWrapper.like(Dept.DEPT_QUALITY,dept.getDeptQuality());
        }
        if(StringUtils.isNotBlank(dept.getDeptManDepartment())){
            queryWrapper.like(Dept.DEPT_MAN_DEPARTMENT,dept.getDeptManDepartment());
        }
        if(StringUtils.isNotBlank(dept.getDeptSubjection())){
            queryWrapper.like(Dept.DEPT_SUBJECTION,dept.getDeptSubjection());
        }
        if(StringUtils.isNotBlank(dept.getDeptLegalPersonName())){
            queryWrapper.like(Dept.DEPT_LEGAL_PERSON_NAME,dept.getDeptLegalPersonName());
        }
        if(StringUtils.isNotBlank(dept.getProvinceCode())){
            queryWrapper.like(Dept.PROVINCE_CODE,dept.getProvinceCode());
        }
        if(StringUtils.isNotBlank(dept.getCityCode())){
            queryWrapper.like(Dept.CITY_CODE,dept.getCityCode());
        }
        if(StringUtils.isNotBlank(dept.getCountyCode())){
            queryWrapper.like(Dept.COUNTY_CODE,dept.getCountyCode());
        }
        if(StringUtils.isNotBlank(dept.getBankAccountNameLoose())){
            queryWrapper.like(Dept.BANK_ACCOUNT_NAME_LOOSE,dept.getBankAccountNameLoose());
        }
        if(StringUtils.isNotBlank(dept.getBankNameLoose())){
            queryWrapper.like(Dept.BANK_NAME_LOOSE,dept.getBankNameLoose());
        }
        if(StringUtils.isNotBlank(dept.getBankAccountNumberLoose())){
            queryWrapper.like(Dept.BANK_ACCOUNT_NUMBER_LOOSE,dept.getBankAccountNumberLoose());
        }
        if(StringUtils.isNotBlank(dept.getBankAccountNameBase())){
            queryWrapper.like(Dept.BANK_ACCOUNT_NAME_BASE,dept.getBankAccountNameBase());
        }
        if(StringUtils.isNotBlank(dept.getBankNameBase())){
            queryWrapper.like(Dept.BANK_NAME_BASE,dept.getBankNameBase());
        }
        if(StringUtils.isNotBlank(dept.getBankAccountNumberBase())){
            queryWrapper.like(Dept.BANK_ACCOUNT_NUMBER_BASE,dept.getBankAccountNumberBase());
        }
        if(StringUtils.isNotBlank(dept.getCreateBy())){
            queryWrapper.eq(Dept.CREATE_BY,dept.getCreateBy());
        }
        if(StringUtils.isNotBlank(dept.getUpdateBy())){
            queryWrapper.eq(Dept.UPDATE_BY,dept.getUpdateBy());
        }
        if(StringUtils.isNotBlank(dept.getOrgCode())){
            queryWrapper.eq(Dept.ORG_CODE,dept.getOrgCode());
        }
        if(StringUtils.isNotBlank(dept.getDeptType())){
            queryWrapper.eq(Dept.DEPT_TYPE,dept.getDeptType());
        }
        if(dept.getGroupType() != null ){
            queryWrapper.eq(Dept.GROUP_TYPE,dept.getGroupType());
        }
        //查询列表时，只能查询正常状态的单位
        queryWrapper.eq(Dept.STATE,"1");
        return queryWrapper;
    }
}

