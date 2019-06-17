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
 * @Date : Create in 2019-05-28
 * @Description :   SubactBak查询from对象
 * @Modified :
 */
@ApiModel("SubactBak查询表单")
@Data
public class SubactBakQueryForm extends BaseQueryForm<SubactBakQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "活动ID")
    private String subactId;

    @ApiModelProperty(value = "项目活动ID")
    private String actId;

    @ApiModelProperty(value = "子活动")
    private String subact;

    @ApiModelProperty(value = "子活动描述")
    private String subactAbs;

    @ApiModelProperty(value = "创建")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建")
    private String createBy;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新")
    private String updateBy;

    @ApiModelProperty(value = "评审金额")
    private String reviewAmount;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

    @ApiModelProperty(value = "父子活动对应关系关联字段")
    private String isRelated;

    @ApiModelProperty(value = "数量/频率")
    private String quantityFrenquence;

    @ApiModelProperty(value = "分项支出")
    private String subExpense;

    @ApiModelProperty(value = "价格/标准")
    private String priceStandard;

    @ApiModelProperty(value = "支出计划")
    private String actPlan;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "父活动名称")
    private String actName;

    @ApiModelProperty(value = "父活动描述")
    private String actAbs;
}
