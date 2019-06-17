package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ActQueryForm;
import com.deloitte.services.project.entity.Act;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Act服务类接口
 * @Modified :
 */
public interface IActService extends IService<Act> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Act>
     */
    IPage<Act> selectPage(ActQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Act>
     */
    List<Act> selectList(ActQueryForm queryForm);

    /**
     * 获取List
     * @param applicationId
     * @return
     */
    List<Act> getActList(String applicationId);

    /**
     * 删除
     * @param applicationId
     */
    void deleteList(String applicationId);

    void deleteRepList(String replyId);

    /**
     * hhh
     * @param replyId
     * @return
     */
    List<Act> getRepActList(String replyId);


}
