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
 * @Date : Create in 2019-06-04
 * @Description :   MaintReply查询from对象
 * @Modified :
 */
@ApiModel("MaintReply查询表单")
@Data
public class MaintReplyQueryForm extends BaseQueryForm<MaintReplyQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

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
