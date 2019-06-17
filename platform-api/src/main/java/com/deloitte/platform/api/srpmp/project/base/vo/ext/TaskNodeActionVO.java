package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lixin on 18/03/2019.
 * 处理任务节点的VO
 */
@Data
public class TaskNodeActionVO {

    @ApiModelProperty(value = "审核节点ID")
    private String id;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "单据ID")
    private String objectId;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

}
