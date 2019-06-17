package com.deloitte.services.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingHeaderQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingVo;
import com.deloitte.services.meeting.entity.OaMeetingHeader;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingHeader服务类接口
 * @Modified :
 */
public interface IOaMeetingHeaderService extends IService<OaMeetingHeader> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMeetingHeader>
     */
    IPage<OaMeetingHeader> selectPage(OaMeetingHeaderQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMeetingHeader>
     */
    List<OaMeetingHeader> selectList(OaMeetingHeaderQueryForm queryForm);

    /**
     *  保存会议室预定
     * @param oaMeetingForm
     * @return
     */
    boolean saveMeeting(OaMeetingForm oaMeetingForm);

    /**
     *  更新会议室预定
     * @param oaMeetingForm
     * @return
     */
    boolean updateMeeting(long id,OaMeetingForm oaMeetingForm);

    /**
     *  获取会议室预定
     * @param id
     * @return
     */
    OaMeetingVo getOaMeeting(long id);
}
