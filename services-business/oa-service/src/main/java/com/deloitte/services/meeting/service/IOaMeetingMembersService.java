package com.deloitte.services.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingMembersQueryForm;
import com.deloitte.services.meeting.entity.OaMeetingMembers;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingMembers服务类接口
 * @Modified :
 */
public interface IOaMeetingMembersService extends IService<OaMeetingMembers> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMeetingMembers>
     */
    IPage<OaMeetingMembers> selectPage(OaMeetingMembersQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMeetingMembers>
     */
    List<OaMeetingMembers> selectList(OaMeetingMembersQueryForm queryForm);
}
