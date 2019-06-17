package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "是否参与")
    private String isPartake;

    @ApiModelProperty(value = "不参加原因")
    private String notPartakeReason;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
