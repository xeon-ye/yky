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
 * @Description : CheckAchUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAchUserInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "业绩通知id")
    private String checkAchNotifyId;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "填写时间")
    private LocalDateTime writeTime;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "评估状态")
    private String evaluateStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "通知名称")
    private String achNotifyName;
}
