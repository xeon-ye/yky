package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-28
 * @Description : ActBak新增修改form对象
 * @Modified :
 */
@ApiModel("新增ActBak表单")
@Data
public class ActBakForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "活动ID")
    private String actId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "活动序号")
    private String actNo;

    @ApiModelProperty(value = "项目活动")
    private String actName;

    @ApiModelProperty(value = "项目活动描述")
    private String description;

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

    @ApiModelProperty(value = "评审金额")
    private String reviewAmount;

    @ApiModelProperty(value = "评审备注")
    private String reviewRemark;

    @ApiModelProperty(value = "批复ID")
    private String replyId;

    @ApiModelProperty(value = "批复金额")
    private String replayAmount;

    @ApiModelProperty(value = "批复备注")
    private String replayRemark;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private String orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

    @ApiModelProperty(value = "父子活动对应关系关联字段")
    private String isRelated;

}
