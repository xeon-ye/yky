package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.PersonQueryForm;
import com.deloitte.services.project.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description : Person服务类接口
 * @Modified :
 */
public interface IPersonService extends IService<Person> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Person>
     */
    IPage<Person> selectPage(PersonQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Person>
     */
    List<Person> selectList(PersonQueryForm queryForm);

    /**
     * 获取批复人员
     * @param projectId
     * @param replyId
     * @return
     */
    List<Person> getReplyList(String replyId);

    /**
     * 删除批复人员
     * @param projectId
     * @param replyId
     */
    void deleteReplyList(String replyId);

    /**
     * 获取维护项目人员
     * @param projectId
     * @return
     */
    List<Person> getMainList(String projectId);

    /**
     * 删除项目维护人员
     * @param projectId
     */
    void deleteMainList(String projectId);
}
