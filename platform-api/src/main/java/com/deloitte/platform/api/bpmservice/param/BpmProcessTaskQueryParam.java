package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jackliu
 * @Date : Create in 2019-04-02
 * @Description :  BpmProcessTask查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BpmProcessTaskQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String processDefineKey;
    private String processInstanceId;
    private String taskId;
    private String taskKey;
    private String taskName;
    private String taskTitle;
    private String taskDescription;
    private String taskStauts;
    private String approverAcount;
    private String approverName;
    private String approverStation;
    private String approverDescription;
    private String objectId;
    private String objectOrderNum;
    private String objectStauts;
    private String objectType;
    private String objectUrl;
    private String objectDescription;
    private String opinion;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long durationTime;
    private String submitterCode;
    private String submitterName;
    private String submitterOrg;
    private String approverOrg;
    private String sourceSystem;
    private String submitterStation;

    private String objectAppUrl;

    private String emergency;

    private LocalDateTime signTime;

    private String createBy;

    private String createByName;

    private String createByOrgId;

    private String previousTaskId;

    private LocalDateTime processCreateTime;

    private String agentId;

    private String agentName;

    private String customText01;
    private String customText02;
    private String customText03;
    private String customText04;
    private String customText05;
    private String customText06;
    private String customText07;
    private String customText08;
    private String customText09;
    private String customText10;
    private Double customNumber01;
    private Double customNumber02;
    private LocalDateTime customDate01;
    private LocalDateTime customDate02;

}
