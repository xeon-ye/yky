package com.deloitte.services.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingAddressQueryForm;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingHeaderQueryForm;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingMembersQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.*;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.meeting.entity.OaMeetingAddress;
import com.deloitte.services.meeting.entity.OaMeetingHeader;
import com.deloitte.services.meeting.entity.OaMeetingMembers;
import com.deloitte.services.meeting.mapper.OaMeetingHeaderMapper;
import com.deloitte.services.meeting.service.IOaMeetingAddressService;
import com.deloitte.services.meeting.service.IOaMeetingHeaderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.meeting.service.IOaMeetingMembersService;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.oa.util.OaBeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingHeader服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMeetingHeaderServiceImpl extends ServiceImpl<OaMeetingHeaderMapper, OaMeetingHeader> implements IOaMeetingHeaderService {


    @Autowired
    private OaMeetingHeaderMapper oaMeetingHeaderMapper;

    @Autowired
    public IOaMeetingAddressService oaMeetingAddressService;

    @Autowired
    public IOaMeetingMembersService oaMeetingMembersService;

    @Override
    public IPage<OaMeetingHeader> selectPage(OaMeetingHeaderQueryForm queryForm ) {
        QueryWrapper<OaMeetingHeader> queryWrapper = new QueryWrapper <OaMeetingHeader>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingHeaderMapper.selectPage(new Page<OaMeetingHeader>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMeetingHeader> selectList(OaMeetingHeaderQueryForm queryForm) {
        QueryWrapper<OaMeetingHeader> queryWrapper = new QueryWrapper <OaMeetingHeader>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingHeaderMapper.selectList(queryWrapper);
    }

    @Override
    public boolean saveMeeting(OaMeetingForm oaMeetingForm) {
        boolean flag = false;
        OaMeetingHeader oaMeetingHeader =new OaBeanUtils<OaMeetingHeader>().copyObj(oaMeetingForm.getOaMeetingHeaderForm(),OaMeetingHeader.class);
        flag = super.save(oaMeetingHeader);
        if(flag) {
            List<OaMeetingMembersForm> oaMeetingMembersFormList = oaMeetingForm.getOaMeetingMembersFormList();
            for (OaMeetingMembersForm oaMeetingMembersForm : oaMeetingMembersFormList) {
                oaMeetingMembersForm.setMeetingId(oaMeetingHeader.getId().toString());
                OaMeetingMembers oaMeetingMembers =new OaBeanUtils<OaMeetingMembers>().copyObj(oaMeetingMembersForm,OaMeetingMembers.class);
                flag = oaMeetingMembersService.save(oaMeetingMembers);
                if(!flag){
                    throw new ServiceException(OaErrorType.SAVE_ERROR,"保存参会人员失败");
                }
            }
            List<OaMeetingAddressForm> oaMeetingAddressFormList = oaMeetingForm.getOaMeetingAddressFormList();
            for (OaMeetingAddressForm oaMeetingAddressForm : oaMeetingAddressFormList) {
                oaMeetingAddressForm.setMeetingId(oaMeetingHeader.getId().toString());
                OaMeetingAddress oaMeetingAddress =new OaBeanUtils<OaMeetingAddress>().copyObj(oaMeetingAddressForm,OaMeetingAddress.class);
                flag = oaMeetingAddressService.save(oaMeetingAddress);
                if(!flag){
                    throw new ServiceException(OaErrorType.SAVE_ERROR,"保存会议地址失败");
                }
            }
        }
        return flag;
    }

    @Override
    public boolean updateMeeting(long id,OaMeetingForm oaMeetingForm) {
        boolean flag = false;
        OaMeetingHeader oaMeetingHeader = new BeanUtils<OaMeetingHeader>().copyObj(oaMeetingForm.getOaMeetingHeaderForm(),OaMeetingHeader.class);
        oaMeetingHeader.setId(id);
        flag = super.saveOrUpdate(oaMeetingHeader);
        List<OaMeetingMembersForm> oaMeetingMembersFormList = oaMeetingForm.getOaMeetingMembersFormList();
        if(flag) {
            for (OaMeetingMembersForm oaMeetingMembersForm : oaMeetingMembersFormList) {
                oaMeetingMembersForm.setMeetingId(oaMeetingHeader.getId().toString());
                OaMeetingMembers oaMeetingMembers = new OaBeanUtils<OaMeetingMembers>().copyObj(oaMeetingMembersForm, OaMeetingMembers.class);
                flag = oaMeetingMembersService.saveOrUpdate(oaMeetingMembers);
                if(!flag){
                    throw new ServiceException(OaErrorType.SAVE_ERROR,"保存会议地址失败");
                }
            }
            List<OaMeetingAddressForm> oaMeetingAddressFormList = oaMeetingForm.getOaMeetingAddressFormList();
            for (OaMeetingAddressForm oaMeetingAddressForm : oaMeetingAddressFormList) {
                oaMeetingAddressForm.setMeetingId(oaMeetingHeader.getId().toString());
                OaMeetingAddress oaMeetingAddress = new OaBeanUtils<OaMeetingAddress>().copyObj(oaMeetingAddressForm, OaMeetingAddress.class);
                flag = oaMeetingAddressService.saveOrUpdate(oaMeetingAddress);
                if(!flag){
                    throw new ServiceException(OaErrorType.SAVE_ERROR,"保存会议地址失败");
                }
            }
        }
        return flag;
    }

    @Override
    public OaMeetingVo getOaMeeting(long id) {
        OaMeetingVo vo = new OaMeetingVo();
        OaMeetingHeader oaMeetingHeader = super.getById(id);
        OaMeetingAddressQueryForm addressQueryForm = new OaMeetingAddressQueryForm();
        addressQueryForm.setMeetingId(id+"");

        OaMeetingMembersQueryForm oaMeetingMembersQueryForm = new OaMeetingMembersQueryForm();
        oaMeetingMembersQueryForm.setMeetingId(id+"");
        vo.setOaMeetingHeaderVo(new OaBeanUtils<OaMeetingHeaderVo>().copyObj(oaMeetingHeader,OaMeetingHeaderVo.class));
        List<OaMeetingAddressVo>  oaMeetingAddressVoList = new OaBeanUtils<OaMeetingAddressVo>().copyObjs(oaMeetingAddressService.selectList(addressQueryForm),OaMeetingAddressVo.class);
        List<OaMeetingMembersVo>  oaMeetingMembersVoList = new OaBeanUtils<OaMeetingMembersVo>().copyObjs(oaMeetingMembersService.selectList(oaMeetingMembersQueryForm),OaMeetingMembersVo.class);
        vo.setOaMeetingAddressVoList(oaMeetingAddressVoList);
        vo.setOaMeetingMembersVoList( oaMeetingMembersVoList);
        return vo;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<OaMeetingHeader> getQueryWrapper(QueryWrapper<OaMeetingHeader> queryWrapper,OaMeetingHeaderQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMeetingNo())){
            queryWrapper.eq(OaMeetingHeader.MEETING_NO,queryForm.getMeetingNo());
        }
        if(StringUtils.isNotBlank(queryForm.getMeetingTitle())){
            queryWrapper.eq(OaMeetingHeader.MEETING_TITLE,queryForm.getMeetingTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getMeetingContent())){
            queryWrapper.eq(OaMeetingHeader.MEETING_CONTENT,queryForm.getMeetingContent());
        }
        if(StringUtils.isNotBlank(queryForm.getEmergency())){
            queryWrapper.eq(OaMeetingHeader.EMERGENCY,queryForm.getEmergency());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateByName())){
            queryWrapper.eq(OaMeetingHeader.CREATE_BY_NAME,queryForm.getCreateByName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(OaMeetingHeader.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(OaMeetingHeader.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getContactUserId())){
            queryWrapper.eq(OaMeetingHeader.CONTACT_USER_ID,queryForm.getContactUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getContactUserName())){
            queryWrapper.eq(OaMeetingHeader.CONTACT_USER_NAME,queryForm.getContactUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getContactTelphone())){
            queryWrapper.eq(OaMeetingHeader.CONTACT_TELPHONE,queryForm.getContactTelphone());
        }
        if(StringUtils.isNotBlank(queryForm.getContactEmail())){
            queryWrapper.eq(OaMeetingHeader.CONTACT_EMAIL,queryForm.getContactEmail());
        }
//        if(StringUtils.isNotBlank(queryForm.getMeetingDate())){
//            queryWrapper.eq(OaMeetingHeader.MEETING_DATE,queryForm.getMeetingDate());
//        }
//
        if(StringUtils.isNotBlank(queryForm.getMeetingDateFrom())){
            queryWrapper.ge(OaMeetingHeader.MEETING_START_DATE,queryForm.getMeetingDateFrom());
        }

        if(StringUtils.isNotBlank(queryForm.getMeetingDateEnd())){
            queryWrapper.le(OaMeetingHeader.MEETING_START_DATE,queryForm.getMeetingDateEnd());
        }
        if(StringUtils.isNotBlank(queryForm.getStartTime())){
            queryWrapper.eq(OaMeetingHeader.START_TIME,queryForm.getStartTime());
        }
        if(StringUtils.isNotBlank(queryForm.getEndTime())){
            queryWrapper.eq(OaMeetingHeader.END_TIME,queryForm.getEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getMeetingType())){
            queryWrapper.eq(OaMeetingHeader.MEETING_TYPE,queryForm.getMeetingType());
        }
        if(StringUtils.isNotBlank(queryForm.getOutMembers())){
            queryWrapper.eq(OaMeetingHeader.OUT_MEMBERS,queryForm.getOutMembers());
        }
        if(StringUtils.isNotBlank(queryForm.getIsBack())){
            queryWrapper.eq(OaMeetingHeader.IS_BACK,queryForm.getIsBack());
        }
        if(StringUtils.isNotBlank(queryForm.getIsNotice())){
            queryWrapper.eq(OaMeetingHeader.IS_NOTICE,queryForm.getIsNotice());
        }
        if(StringUtils.isNotBlank(queryForm.getMeetingResouce())){
            queryWrapper.eq(OaMeetingHeader.MEETING_RESOUCE,queryForm.getMeetingResouce());
        }
        if(StringUtils.isNotBlank(queryForm.getRemarks())){
            queryWrapper.eq(OaMeetingHeader.REMARKS,queryForm.getRemarks());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaMeetingHeader.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaMeetingHeader.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaMeetingHeader.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaMeetingHeader.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaMeetingHeader.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaMeetingHeader.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaMeetingHeader.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

}

