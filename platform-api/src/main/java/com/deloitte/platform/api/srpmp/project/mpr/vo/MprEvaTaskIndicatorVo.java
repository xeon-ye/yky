package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : MprEvaTaskIndicator返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprEvaTaskIndicatorVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "中期考核指标")
    private String indicatorName;

    @ApiModelProperty(value = "指标实际完成情况")
    private String indicatorCompleteStatus;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
