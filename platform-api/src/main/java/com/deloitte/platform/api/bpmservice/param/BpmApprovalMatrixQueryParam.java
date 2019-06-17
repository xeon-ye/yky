package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description :  BpmApprovalMatrix查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BpmApprovalMatrixQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String processDefineKey;
    private String activityId;
    private String activityName;
    private String chargeOrg;
    private String chargeBusiness;
    private String accountNum;
    private String accountName;
    private String orgCode;
    private String position;
    private String effective;
    private LocalDateTime effectiveStart;
    private LocalDateTime effectiveEnd;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String accountEmpNo;
    private String orgNoList;
    private String positionList;
    private String roleNoList;
    private String ruleType;
    private String customRule;
    private String orderNum;

}
