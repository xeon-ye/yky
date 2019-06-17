package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchUserApproval返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAchUserApprovalHistoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审批人名字")
    private String approvalUserName;

    @ApiModelProperty(value = "审批人编码")
    private String approvalUserCode;

    @ApiModelProperty(value = "审批人岗位")
    private String approvalUserPost;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "审批状态")
    private String approvalStatus;

    @ApiModelProperty(value = "审批意见")
    private String approvalOpinion;

}
