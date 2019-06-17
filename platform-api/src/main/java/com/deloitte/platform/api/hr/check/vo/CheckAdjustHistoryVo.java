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
 * @Description : CheckAdjustHistory返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAdjustHistoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核结果id")
    private String checkResultId;

    @ApiModelProperty(value = "调整等级")
    private String adjustLevel;

    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    @ApiModelProperty(value = "系统等级")
    private String systemLevel;

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
