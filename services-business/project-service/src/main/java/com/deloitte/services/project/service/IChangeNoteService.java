package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ChangeNoteQueryForm;
import com.deloitte.services.project.entity.ChangeNote;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : ChangeNote服务类接口
 * @Modified :
 */
public interface IChangeNoteService extends IService<ChangeNote> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ChangeNote>
     */
    IPage<ChangeNote> selectPage(ChangeNoteQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ChangeNote>
     */
    List<ChangeNote> selectList(ChangeNoteQueryForm queryForm);

    /**
     * 获取
     * @param applicationId
     * @return
     */
    List<ChangeNote> getOneChangeNote(String projectId);
}
