package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.ProjectDetailInformationVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectMemberInformationVo;
import com.deloitte.services.dss.scientific.mapper.IProjectInformationlistMapper;
import com.deloitte.services.dss.scientific.service.IProjectInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ProjectInformationServiceImpl
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-04
 * @version: v1.0
 */
@Service
@Transactional
public class ProjectInformationServiceImpl implements IProjectInformationService {

    @Autowired
    private IProjectInformationlistMapper informationlistMapper;
    @Override
    public ProjectDetailInformationVo queryProjectDetailInformation(String projectNum) {
        return informationlistMapper.queryProjectDetailInformation(projectNum);
    }

    @Override
    public List<ProjectMemberInformationVo> queryProjectMemberInformation(String projectNum) {
        return informationlistMapper.queryProjectMemberInformation(projectNum);
    }
}
