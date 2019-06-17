package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description :   MprEvaTaskIndicator查询from对象
 * @Modified :
 */
@ApiModel("MprEvaTaskIndicator查询表单")
@Data
public class MprEvaTaskIndicatorQueryForm extends BaseQueryForm<MprEvaTaskIndicatorQueryParam>  {


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
