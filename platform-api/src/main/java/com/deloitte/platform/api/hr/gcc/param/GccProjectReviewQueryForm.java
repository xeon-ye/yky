package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProjectReview查询from对象
 * @Modified :
 */
@ApiModel("GccProjectReview查询表单")
@Data
public class GccProjectReviewQueryForm extends BaseQueryForm<GccProjectReviewQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

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

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;
}
