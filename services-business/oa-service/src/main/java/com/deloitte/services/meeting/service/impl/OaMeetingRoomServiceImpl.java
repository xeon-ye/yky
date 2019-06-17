package com.deloitte.services.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingRoomQueryForm;
import com.deloitte.services.meeting.entity.OaMeetingRoom;
import com.deloitte.services.meeting.mapper.OaMeetingRoomMapper;
import com.deloitte.services.meeting.service.IOaMeetingRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingRoom服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMeetingRoomServiceImpl extends ServiceImpl<OaMeetingRoomMapper, OaMeetingRoom> implements IOaMeetingRoomService {


    @Autowired
    private OaMeetingRoomMapper oaMeetingRoomMapper;

    @Override
    public IPage<OaMeetingRoom> selectPage(OaMeetingRoomQueryForm queryForm ) {
        QueryWrapper<OaMeetingRoom> queryWrapper = new QueryWrapper <OaMeetingRoom>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingRoomMapper.selectPage(new Page<OaMeetingRoom>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMeetingRoom> selectList(OaMeetingRoomQueryForm queryForm) {
        QueryWrapper<OaMeetingRoom> queryWrapper = new QueryWrapper <OaMeetingRoom>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingRoomMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<OaMeetingRoom> getQueryWrapper(QueryWrapper<OaMeetingRoom> queryWrapper,OaMeetingRoomQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getRoomName())){
            queryWrapper.eq(OaMeetingRoom.ROOM_NAME,queryForm.getRoomName());
        }
        if(StringUtils.isNotBlank(queryForm.getAddress())){
            queryWrapper.eq(OaMeetingRoom.ADDRESS,queryForm.getAddress());
        }
        if(StringUtils.isNotBlank(queryForm.getMaxPersons())){
            queryWrapper.eq(OaMeetingRoom.MAX_PERSONS,queryForm.getMaxPersons());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(OaMeetingRoom.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(OaMeetingRoom.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomStatus())){
            queryWrapper.eq(OaMeetingRoom.ROOM_STATUS,queryForm.getRoomStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomResource())){
            queryWrapper.eq(OaMeetingRoom.ROOM_RESOURCE,queryForm.getRoomResource());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomDutyId())){
            queryWrapper.eq(OaMeetingRoom.ROOM_DUTY_ID,queryForm.getRoomDutyId());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomDutyName())){
            queryWrapper.eq(OaMeetingRoom.ROOM_DUTY_NAME,queryForm.getRoomDutyName());
        }
        if(StringUtils.isNotBlank(queryForm.getOrderNum())){
            queryWrapper.eq(OaMeetingRoom.ORDER_NUM,queryForm.getOrderNum());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomDesc())){
            queryWrapper.eq(OaMeetingRoom.ROOM_DESC,queryForm.getRoomDesc());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaMeetingRoom.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(OaMeetingRoom.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaMeetingRoom.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(OaMeetingRoom.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaMeetingRoom.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaMeetingRoom.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaMeetingRoom.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaMeetingRoom.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaMeetingRoom.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}

