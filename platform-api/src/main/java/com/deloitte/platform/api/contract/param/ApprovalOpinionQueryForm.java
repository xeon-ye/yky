package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-02
 * @Description :   ApprovalOpinion查询from对象
 * @Modified :
 */
@ApiModel("ApprovalOpinion查询表单")
@Data
public class ApprovalOpinionQueryForm extends BaseQueryForm<ApprovalOpinionQueryParam>  {


    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "流程节点ID")
    private String processTaskId;

    @ApiModelProperty(value = "合同编号")
    private String contractId;

    @ApiModelProperty(value = "审批意见类型")
    private String opinionType;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "审批人ID")
    private String approvalerCode;

    @ApiModelProperty(value = "审批人")
    private String approvaler;

    @ApiModelProperty(value = "回复内容")
    private String replyText;

    @ApiModelProperty(value = "回复人ID")
    private String replyPersonCode;

    @ApiModelProperty(value = "回复人")
    private String replyPerson;

    @ApiModelProperty(value = "反馈意见ID")
    private String opinionFileId;

    @ApiModelProperty(value = "反馈意见url")
    private String opinionFileUrl;

    @ApiModelProperty(value = "反馈意见文件名")
    private String opinionFileName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;
}
