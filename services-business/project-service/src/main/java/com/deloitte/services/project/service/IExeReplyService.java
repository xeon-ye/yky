package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.ExeReplyQueryForm;
import com.deloitte.platform.api.project.vo.ProjectChangeVo;
import com.deloitte.services.project.entity.ExeReply;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description : ExeReply服务类接口
 * @Modified :
 */
public interface IExeReplyService extends IService<ExeReply> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ExeReply>
     */
    IPage<ExeReply> selectPage(ExeReplyQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ExeReply>
     */
    List<ExeReply> selectList(ExeReplyQueryForm queryForm);

    List<ExeReply> getAllByMainId(String mainId);
    void deleteAllByMainId(String mainId);

    List<ExeReply> getAllByRepId(String replyId);
    void deleteAllByRepId(String replyId);

    ProjectChangeVo toSubmit(ProjectChangeVo projectChangeVo);
}
