package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ProUserQueryForm;
import com.deloitte.services.project.entity.ProUser;
import com.deloitte.services.project.mapper.ProUserMapper;
import com.deloitte.services.project.service.IProUserService;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-24
 * @Description :  ProUser服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProUserServiceImpl extends ServiceImpl<ProUserMapper, ProUser> implements IProUserService {


    @Autowired
    private ProUserMapper proUserMapper;

    @Override
    public IPage<ProUser> selectPage(ProUserQueryForm queryForm ) {
        QueryWrapper<ProUser> queryWrapper = new QueryWrapper <ProUser>();
        //getQueryWrapper(queryWrapper,queryForm);
        return proUserMapper.selectPage(new Page<ProUser>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProUser> selectList(ProUserQueryForm queryForm) {
        QueryWrapper<ProUser> queryWrapper = new QueryWrapper <ProUser>();
        //getQueryWrapper(queryWrapper,queryForm);
        return proUserMapper.selectList(queryWrapper);
    }

    @Override
    public List<ProUser> getProUserList(String userMark) {
        /**
         * userMark:
         *  项目负责人  pro_charge
         *  项目代管人  pro_escrow
         *  单位负责人  dpt_charge
         *  财务负责人  fin_charge
         */
        if ("pro_charge".equals(userMark)) {
            return generalUser(userMark);
        } else if ("pro_escrow".equals(userMark)) {
            return generalUser(userMark);
        } else if ("dpt_charge".equals(userMark)) {
            return generalUser(userMark);
        } else if ("fin_charge".equals(userMark)) {
            return generalUser(userMark);
        }
        return null;
    }

    private List<ProUser> generalUser(String userMark) {
        List<ProUser> proUserList = new ArrayList<>();
        ProUser proUser = new ProUser();
        proUser.setUserMarkName(userMark);
        proUser.setId(NumberUtils.toLong("1001"));
        proUser.setUserName("狲政翼");
        proUser.setEmail("sunzhengyi@gmail.com");
        proUser.setMajor("劳务工作者");
        proUser.setMajorCode("3030380");
        proUser.setPhone("138****4552");
        proUser.setProjectId("133885602432");
        proUser.setProjectCode("NO20190526");
        proUser.setTel("138****4552");
        proUser.setUserPostCode("34343");
        proUser.setUserPostName("职务测试");
        proUser.setUserMarkCode("NO"+userMark);
        proUser.setUserId("34343434");
        proUser.setUserDep("不萌星系");
        proUser.setUserNo("dfdfd");
        proUser.setUserKs("所处科室");

        proUserList.add(proUser);

        return proUserList;
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProUser> getQueryWrapper(QueryWrapper<ProUser> queryWrapper,ProUserQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ProUser.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(ProUser.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(ProUser.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(ProUser.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getUserNo())){
            queryWrapper.eq(ProUser.USER_NO,queryForm.getUserNo());
        }
        if(StringUtils.isNotBlank(queryForm.getUserDep())){
            queryWrapper.eq(ProUser.USER_DEP,queryForm.getUserDep());
        }
        if(StringUtils.isNotBlank(queryForm.getUserKs())){
            queryWrapper.eq(ProUser.USER_KS,queryForm.getUserKs());
        }
        if(StringUtils.isNotBlank(queryForm.getUserPostCode())){
            queryWrapper.eq(ProUser.USER_POST_CODE,queryForm.getUserPostCode());
        }
        if(StringUtils.isNotBlank(queryForm.getUserPostName())){
            queryWrapper.eq(ProUser.USER_POST_NAME,queryForm.getUserPostName());
        }
        if(StringUtils.isNotBlank(queryForm.getUserMarkCode())){
            queryWrapper.eq(ProUser.USER_MARK_CODE,queryForm.getUserMarkCode());
        }
        if(StringUtils.isNotBlank(queryForm.getUserMarkName())){
            queryWrapper.eq(ProUser.USER_MARK_NAME,queryForm.getUserMarkName());
        }
        if(StringUtils.isNotBlank(queryForm.getPhone())){
            queryWrapper.eq(ProUser.PHONE,queryForm.getPhone());
        }
        if(StringUtils.isNotBlank(queryForm.getEmail())){
            queryWrapper.eq(ProUser.EMAIL,queryForm.getEmail());
        }
        if(StringUtils.isNotBlank(queryForm.getTel())){
            queryWrapper.eq(ProUser.TEL,queryForm.getTel());
        }
        if(StringUtils.isNotBlank(queryForm.getMajor())){
            queryWrapper.eq(ProUser.MAJOR,queryForm.getMajor());
        }
        if(StringUtils.isNotBlank(queryForm.getMajorCode())){
            queryWrapper.eq(ProUser.MAJOR_CODE,queryForm.getMajorCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProUser.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ProUser.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProUser.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ProUser.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ProUser.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ProUser.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ProUser.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(ProUser.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(ProUser.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}

