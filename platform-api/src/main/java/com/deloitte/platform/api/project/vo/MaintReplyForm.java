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
 * @Date : Create in 2019-06-04
 * @Description : MaintReply新增修改form对象
 * @Modified :
 */
@ApiModel("新增MaintReply表单")
@Data
public class MaintReplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "审批id")
    private String replyDocumentId;

    @ApiModelProperty(value = "维护项目id")
    private String maintId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "审批code")
    private String replyCode;

    @ApiModelProperty(value = "审批name")
    private String replyName;

    @ApiModelProperty(value = "审批意见")
    private String replyAdvice;

    @ApiModelProperty(value = "审批部门id")
    private String replyPartId;

    @ApiModelProperty(value = "审批部门")
    private String replyPartName;

    @ApiModelProperty(value = "审批人id")
    private String replyPersonId;

    @ApiModelProperty(value = "审批人")
    private String replyPersonName;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime replyTime;

    @ApiModelProperty(value = "上一个审批人id")
    private String replyLastId;

    @ApiModelProperty(value = "上一个审批人")
    private String replyLastName;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "批复年度")
    private String replyYear;

    @ApiModelProperty(value = "状态code")
    private String replyStatusCode;

    @ApiModelProperty(value = "状态name")
    private String replyStatusName;

}
