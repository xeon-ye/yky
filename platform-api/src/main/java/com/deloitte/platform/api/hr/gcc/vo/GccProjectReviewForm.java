package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccProjectReview新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccProjectReview表单")
@Data
public class GccProjectReviewForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "通知编号")
    private Long noticeId;

    @ApiModelProperty(value = "申报编号")
    private Long declareId;

    @ApiModelProperty(value = "专家小组编号")
    private Long panelNumber;

    @ApiModelProperty(value = "专家小组级别（所院/院校")
    private String panelType;

    @ApiModelProperty(value = "评审人编号")
    private Long userId;

    @ApiModelProperty(value = "评审意见")
    private String panelContent;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

}
