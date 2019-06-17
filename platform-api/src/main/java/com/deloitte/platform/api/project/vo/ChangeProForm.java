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
 * @Date : Create in 2019-06-06
 * @Description : ChangePro新增修改form对象
 * @Modified :
 */
@ApiModel("新增ChangePro表单")
@Data
public class ChangeProForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "变更id")
    private String changeId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "变更原因")
    private String changeAdv;

    @ApiModelProperty(value = "变更类型code")
    private String changeCode;

    @ApiModelProperty(value = "变更类型name")
    private String changeName;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    private String ext4;

    @ApiModelProperty(value = "维度id")
    private Long orgId;

    @ApiModelProperty(value = "维度path")
    private String orgPath;

    @ApiModelProperty(value = "业务单号流水号")
    private String serviceNum;

    @ApiModelProperty(value = "变更时间")
    private LocalDateTime changeDate;

    @ApiModelProperty(value = "批复id")
    private String replyId;

    @ApiModelProperty(value = "项目维护id")
    private String maintenceid;

    @ApiModelProperty(value = "提交状态code")
    private String changeStatusCode;

    @ApiModelProperty(value = "提交状态name")
    private String changeStatusName;

}
