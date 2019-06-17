package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研项目各单位人均情况Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectApplyNumVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总数量")
    private Long totalNum;

    @ApiModelProperty(value = "已提交申请数量")
    private Long submitNum;

    @ApiModelProperty(value = "评审论证数量")
    private Long auditNum;

    @ApiModelProperty(value = "公示数量")
    private Long publicNum;

    @ApiModelProperty(value = "任务签订数量")
    private Long taskSignNum;

    @ApiModelProperty(value = "批复数量")
    private Long replyNum;


}
