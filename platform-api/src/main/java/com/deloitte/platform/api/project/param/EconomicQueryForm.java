package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-25
 * @Description :   Economic查询from对象
 * @Modified :
 */
@ApiModel("Economic查询表单")
@Data
public class EconomicQueryForm extends BaseQueryForm<EconomicQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "经济分类id")
    private String economicId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "经济类型code")
    private String ecoCode;

    @ApiModelProperty(value = "经济类型name")
    private String ecoName;

    @ApiModelProperty(value = "年度")
    private String ecoYear;

    @ApiModelProperty(value = "申报财政拨款")
    private String appFunding;

    @ApiModelProperty(value = "申报部门预算")
    private String appBudget;

    @ApiModelProperty(value = "申报其他金额")
    private String appOther;

    @ApiModelProperty(value = "评审财政拨款")
    private String reviewFunding;

    @ApiModelProperty(value = "评审预算金额")
    private String reviewBudget;

    @ApiModelProperty(value = "评审其他金额")
    private String reviewOther;

    @ApiModelProperty(value = "批复财政拨款")
    private String replyFunding;

    @ApiModelProperty(value = "批复部门预算")
    private String replyBudget;

    @ApiModelProperty(value = "批复其他金额")
    private String replyOther;

    @ApiModelProperty(value = "结转金额")
    private String carryAmount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    private String ext5;

    @ApiModelProperty(value = "维度id")
    private Long orgId;

    @ApiModelProperty(value = "维度")
    private String orgPath;

    @ApiModelProperty(value = "批复id")
    private String replyId;
}
