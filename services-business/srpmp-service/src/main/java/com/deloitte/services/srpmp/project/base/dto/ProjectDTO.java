package com.deloitte.services.srpmp.project.base.dto;

import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import lombok.Data;

/**
 * Created by lixin on 12/04/2019.
 */
@Data
public class ProjectDTO extends SrpmsProject {

    //预算金额
    private String applyFound;

    //项目执行期限开始
    private String projectActionDateStartStr;

    //项目执行期限结束
    private String projectActionDateEndStr;

}
