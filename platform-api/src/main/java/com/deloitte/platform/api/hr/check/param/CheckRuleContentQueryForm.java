package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckRuleContent查询from对象
 * @Modified :
 */
@ApiModel("CheckRuleContent查询表单")
@Data
public class CheckRuleContentQueryForm extends BaseQueryForm<CheckRuleContentQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核等级规则id")
    private String checkRuleId;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "最小分数")
    private Long minScore;

    @ApiModelProperty(value = "最大分数")
    private Long maxScore;

    @ApiModelProperty(value = "比例")
    private Double ratio;

    @ApiModelProperty(value = "人数")
    private Long peopleNumber;

    @ApiModelProperty(value = "排序")
    private String orderNumber;

    @ApiModelProperty(value = "计算系数")
    private Double computeRatio;

    @ApiModelProperty(value = "描述")
    private String describe;

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
