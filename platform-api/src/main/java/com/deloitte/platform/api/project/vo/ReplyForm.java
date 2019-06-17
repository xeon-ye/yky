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
 * @Date : Create in 2019-05-30
 * @Description : Reply新增修改form对象
 * @Modified :
 */
@ApiModel("新增Reply表单")
@Data
public class ReplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目批复书ID")
    private String replyDocumentId;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "项目执行年度")
    private String operationYear;

    @ApiModelProperty(value = "批复年度")
    private String replyYear;

    @ApiModelProperty(value = "计划开始年度")
    private String beginYear;

    @ApiModelProperty(value = "项目周期")
    private String cycle;

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

    @ApiModelProperty(value = "批复编码")
    private String replyCode;

    @ApiModelProperty(value = "业务单号流水号")
    private String serviceNum;

    @ApiModelProperty(value = "批复状态code")
    private String replyStatusCode;

    @ApiModelProperty(value = "批复状态name")
    private String replyStatusName;

    @ApiModelProperty(value = "批复时间")
    private LocalDateTime replyTime;

    @ApiModelProperty(value = "批复人id")
    private String replyPersonId;

    @ApiModelProperty(value = "批复人name")
    private String replyPersonName;

}
