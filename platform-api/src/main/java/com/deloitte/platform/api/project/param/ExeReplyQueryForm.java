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
 * @Date : Create in 2019-06-06
 * @Description :   ExeReply查询from对象
 * @Modified :
 */
@ApiModel("ExeReply查询表单")
@Data
public class ExeReplyQueryForm extends BaseQueryForm<ExeReplyQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "执行审批id")
    private String exeReplyId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "项目变更id")
    private String changeId;

    @ApiModelProperty(value = "审批结果code")
    private String exeReplyCode;

    @ApiModelProperty(value = "审批结果name")
    private String exeReplyName;

    @ApiModelProperty(value = "审批意见")
    private String exeReplyAdv;

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

    @ApiModelProperty(value = "审批人id")
    private String repPersonId;

    @ApiModelProperty(value = "审批人name")
    private String repPersonName;

    @ApiModelProperty(value = "审批部门id")
    private String repDepartmentId;

    @ApiModelProperty(value = "审批部门name")
    private String repDepartmentName;

    @ApiModelProperty(value = "批复id")
    private String replyId;

    @ApiModelProperty(value = "项目维护id")
    private String maintenanceId;
}
