package com.deloitte.platform.api.oaservice.rulesystem.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :  OaRuleSystem查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaRuleSystemQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String urgentLevel;
    private String ruleSortCode;
    private String ruleSortName;
    private String applyOrgCode;
    private String applyOrgName;
    private String applyUserId;
    private String applyUserName;
    private LocalDateTime applyDatetime;
    private Integer delFlag;
    private String ruleContent;
    private Integer ruleHit;
    private String approvalStatus;
    private Integer isneedBussiness;
    private LocalDateTime updateDatetime;
    private String updateUserName;
    private String updateUserId;
    private String orderNum;
    private String applyDeptCode;
    private String deptPer;

}
