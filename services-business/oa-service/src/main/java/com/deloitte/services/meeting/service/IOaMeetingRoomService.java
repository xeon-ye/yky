package com.deloitte.services.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingRoomQueryForm;
import com.deloitte.services.meeting.entity.OaMeetingRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingRoom服务类接口
 * @Modified :
 */
public interface IOaMeetingRoomService extends IService<OaMeetingRoom> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMeetingRoom>
     */
    IPage<OaMeetingRoom> selectPage(OaMeetingRoomQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMeetingRoom>
     */
    List<OaMeetingRoom> selectList(OaMeetingRoomQueryForm queryForm);
}
