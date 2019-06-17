package com.deloitte.services.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingAddressQueryForm;
import com.deloitte.services.meeting.entity.OaMeetingAddress;
import com.deloitte.services.meeting.mapper.OaMeetingAddressMapper;
import com.deloitte.services.meeting.service.IOaMeetingAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingAddress服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMeetingAddressServiceImpl extends ServiceImpl<OaMeetingAddressMapper, OaMeetingAddress> implements IOaMeetingAddressService {


    @Autowired
    private OaMeetingAddressMapper oaMeetingAddressMapper;

    @Override
    public IPage<OaMeetingAddress> selectPage(OaMeetingAddressQueryForm queryForm ) {
        QueryWrapper<OaMeetingAddress> queryWrapper = new QueryWrapper <OaMeetingAddress>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingAddressMapper.selectPage(new Page<OaMeetingAddress>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMeetingAddress> selectList(OaMeetingAddressQueryForm queryForm) {
        QueryWrapper<OaMeetingAddress> queryWrapper = new QueryWrapper <OaMeetingAddress>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaMeetingAddressMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<OaMeetingAddress> getQueryWrapper(QueryWrapper<OaMeetingAddress> queryWrapper,OaMeetingAddressQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMeetingId())){
            queryWrapper.eq(OaMeetingAddress.MEETING_ID,queryForm.getMeetingId());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomId())){
            queryWrapper.eq(OaMeetingAddress.ROOM_ID,queryForm.getRoomId());
        }
        if(StringUtils.isNotBlank(queryForm.getAddress())){
            queryWrapper.eq(OaMeetingAddress.ADDRESS,queryForm.getAddress());
        }
        if(StringUtils.isNotBlank(queryForm.getRoomName())){
            queryWrapper.eq(OaMeetingAddress.ROOM_NAME,queryForm.getRoomName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaMeetingAddress.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(OaMeetingAddress.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaMeetingAddress.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(OaMeetingAddress.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaMeetingAddress.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaMeetingAddress.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaMeetingAddress.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaMeetingAddress.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaMeetingAddress.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}

