package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.ProjectDetailInformationVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectMemberInformationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: IProjectInformationlistMapper
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-04
 * @version: v1.0
 */

public interface IProjectInformationlistMapper {
    /**
     * 根据项目编号获取项目明细列表
     * @param projectNum
     * @return
     */
    ProjectDetailInformationVo queryProjectDetailInformation(@Param("projectNum") String projectNum);

    /**
     * 根据项目编码获取项目成员信息列表
     * @param projectNum
     * @return
     */
    List<ProjectMemberInformationVo> queryProjectMemberInformation(@Param("projectNum") String projectNum);
}
