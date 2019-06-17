package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.MaintReplyQueryForm;
import com.deloitte.services.project.entity.MaintReply;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : MaintReply服务类接口
 * @Modified :
 */
public interface IMaintReplyService extends IService<MaintReply> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MaintReply>
     */
    IPage<MaintReply> selectPage(MaintReplyQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MaintReply>
     */
    List<MaintReply> selectList(MaintReplyQueryForm queryForm);

    /**
     * 获取
     * @param projectId
     * @return
     */
    List<MaintReply> getAllList(String projectId);

    /**
     * 最新的维护批复书
     * @param map
     * @return
     */
    MaintReply getNewMainReply(Map map);


}
