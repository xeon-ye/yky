package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : AuditingHistory返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditingHistoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "申请人ID")
    private Long applyUserId;

    @ApiModelProperty(value = "审核人ID")
    private Long auditingUserId;

    @ApiModelProperty(value = "申请审核的业务ID")
    private Long businessId;

    @ApiModelProperty(value = "审核事项")
    private String auditingBusinessItem;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditingTime;

    @ApiModelProperty(value = "审核结果")
    private String auditingResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

}
