package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.MaintReplyNoteQueryForm;
import com.deloitte.services.project.entity.MaintReplyNote;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : MaintReplyNote服务类接口
 * @Modified :
 */
public interface IMaintReplyNoteService extends IService<MaintReplyNote> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MaintReplyNote>
     */
    IPage<MaintReplyNote> selectPage(MaintReplyNoteQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MaintReplyNote>
     */
    List<MaintReplyNote> selectList(MaintReplyNoteQueryForm queryForm);

    /**
     * 获取
     * @param projectId
     * @return
     */
    List<MaintReplyNote> getAllList(String projectId);
}
