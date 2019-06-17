package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.param.ApplicationQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.services.project.entity.*;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description : Application服务类接口
 * @Modified :
 */
public interface IApplicationService extends IService<Application> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Application>
     */
    IPage<Application> selectPage(ApplicationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Application>
     */
    List<Application> selectList(ApplicationQueryForm queryForm);

    /**
     * 分页查询-项目申报-我的申请
     * @param currentPage 当前页数
     * @param pageSize 当前页数显示数据条数
     * @return
     */
    IPage<MyApplicationVo> searchMyApplication(Long currentPage, Long pageSize);

    /**
     * 所有的申报书
     * @param projectId
     * @return
     */
    List<Application> getAllList(String projectId);

    /**
     * 获取最新的批复项目
     * @param projectId
     * @return
     */
    Application getOneApp(String projectId);

    /**
     * 删除申报书以及项目
     * @param applicationId
     */
    void removeApplication(String applicationId);

}
