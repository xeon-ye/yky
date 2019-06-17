package com.deloitte.platform.api.oaservice.applycenter.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-09
 * @Description :  SendProcessTask查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendProcessTaskQueryParam extends BaseParam {
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
    private String createBy;
    private String createByOrgId;
    private LocalDateTime processCreateTime;
    private String objectAppUrl;
    private String emergency;
    private String submitterStation;
    private String createByName;
    private String previousTaskId;
    private String money;

}
