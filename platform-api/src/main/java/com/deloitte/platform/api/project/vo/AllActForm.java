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
 * @Date : Create in 2019-05-25
 * @Description : AllAct新增修改form对象
 * @Modified :
 */
@ApiModel("新增AllAct表单")
@Data
public class AllActForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "活动汇总ID")
    private String allActId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "资金来源code")
    private String finSourceCode;

    @ApiModelProperty(value = "资金来源")
    private String finSource;

    @ApiModelProperty(value = "总投资")
    private String allInvestment;

    @ApiModelProperty(value = "已批复预算数")
    private String replyQuantity;

    @ApiModelProperty(value = "截至上年底结转数")
    private String carryOverEndOfLastYear;

    @ApiModelProperty(value = "申请预算")
    private String budgetApplication;

    @ApiModelProperty(value = "备注")
    private String remarks;

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
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

}
