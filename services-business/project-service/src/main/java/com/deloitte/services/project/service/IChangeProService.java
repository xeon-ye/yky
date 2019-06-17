package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.ChangeProQueryForm;
import com.deloitte.platform.api.project.vo.ProjectChangeVo;
import com.deloitte.services.project.entity.ChangePro;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : ChangePro服务类接口
 * @Modified :
 */
public interface IChangeProService extends IService<ChangePro> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ChangePro>
     */
    IPage<ChangePro> selectPage(ChangeProQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ChangePro>
     */
    List<ChangePro> selectList(ChangeProQueryForm queryForm);

    List<ChangePro> getChangeByProjectId(String projectId);

    List<ChangePro> getChangeByReplyId(String replyId);

    List<ChangePro> getChangeByMaintenceId(String maintenceId);

    ProjectChangeVo toSaveBak(ProjectChangeVo projectChangeVo);
}
