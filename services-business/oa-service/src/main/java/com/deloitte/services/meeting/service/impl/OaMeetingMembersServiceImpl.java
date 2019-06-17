package com.deloitte.services.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingMembersQueryForm;
import com.deloitte.services.meeting.entity.OaMeetingMembers;
import com.deloitte.services.meeting.mapper.OaMeetingMembersMapper;
import com.deloitte.services.meeting.service.IOaMeetingMembersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingMembers服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMeetingMembersServiceImpl extends ServiceImpl<OaMeetingMembersMapper, OaMeetingMembers> implements IOaMeetingMembersService {


    @Autowired
    private OaMeetingMembersMapper oaMeetingMembersMapper;

    @Override
    public IPage<OaMeetingMembers> selectPage(OaMeetingMembersQueryForm queryForm ) {
        QueryWrapper<OaMeetingMembers> queryWrapper = new QueryWrapper <OaMeetingMembers>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingMembersMapper.selectPage(new Page<OaMeetingMembers>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMeetingMembers> selectList(OaMeetingMembersQueryForm queryForm) {
        QueryWrapper<OaMeetingMembers> queryWrapper = new QueryWrapper <OaMeetingMembers>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingMembersMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<OaMeetingMembers> getQueryWrapper(QueryWrapper<OaMeetingMembers> queryWrapper,OaMeetingMembersQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMeetingId())){
            queryWrapper.eq(OaMeetingMembers.MEETING_ID,queryForm.getMeetingId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(OaMeetingMembers.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(OaMeetingMembers.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(OaMeetingMembers.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(OaMeetingMembers.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getTelphone())){
            queryWrapper.eq(OaMeetingMembers.TELPHONE,queryForm.getTelphone());
        }
        if(StringUtils.isNotBlank(queryForm.getEmail())){
            queryWrapper.eq(OaMeetingMembers.EMAIL,queryForm.getEmail());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaMeetingMembers.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(OaMeetingMembers.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaMeetingMembers.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(OaMeetingMembers.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaMeetingMembers.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaMeetingMembers.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaMeetingMembers.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaMeetingMembers.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaMeetingMembers.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}

