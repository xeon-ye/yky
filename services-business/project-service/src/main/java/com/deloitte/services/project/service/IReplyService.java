package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ReplyQueryForm;
import com.deloitte.platform.api.project.vo.ProjectReplyVo;
import com.deloitte.services.project.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description : Reply服务类接口
 * @Modified :
 */
public interface IReplyService extends IService<Reply> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Reply>
     */
    IPage<Reply> selectPage(ReplyQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Reply>
     */
    List<Reply> selectList(ReplyQueryForm queryForm);

    /**
     * huoqu
     * @param applicationId
     * @return
     */
    List<Reply> getReplyList(String applicationId);


    /**
     * 获取所有的批复书
     * @param projectId
     * @return
     */
    List<Reply> getAllListByRep(String projectId);

    /**
     * 获取批复项目
     * @param projectId
     * @return
     */
    ProjectReplyVo getOneRep(String projectId);

    /**
     * 获取申报项目
     * @param projectId
     * @return
     */
    ProjectReplyVo getOneApp(String projectId);
    Reply getNewRep(@Param("data") Map map);

    ProjectReplyVo toRemove(String replyId);
    ProjectReplyVo toFindReply(String replyId);

    ProjectReplyVo newReply(ProjectReplyVo projectReplyVo);
    ProjectReplyVo repToUpdate(ProjectReplyVo projectReplyVo);
    ProjectReplyVo proToUpdate(ProjectReplyVo projectReplyVo);
}
