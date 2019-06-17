package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : MprEvaTaskIndicator新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaTaskIndicator表单")
@Data
public class MprEvaTaskIndicatorForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "中期考核指标")
    private String indicatorName;

    @ApiModelProperty(value = "指标实际完成情况")
    private String indicatorCompleteStatus;

}
