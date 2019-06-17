package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccSelectedNotify查询from对象
 * @Modified :
 */
@ApiModel("GccSelectedNotify查询表单")
@Data
public class GccSelectedNotifyQueryForm extends BaseQueryForm<GccSelectedNotifyQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申报项目通知编号")
    private String declarationProjectNotifyId;

    @ApiModelProperty(value = "申报编号")
    private String declarationNumber;

    @ApiModelProperty(value = "所属人才项目")
    private String personnelProject;

    @ApiModelProperty(value = "人才项目状态")
    private String personnelProjectStatus;

    @ApiModelProperty(value = "人才项目类型")
    private String personnelProjectType;

    @ApiModelProperty(value = "获得专家称号")
    private String gainTitle;

    @ApiModelProperty(value = "发文编号")
    private String dispatchNumber;

    @ApiModelProperty(value = "授予单位")
    private String grantUnit;

    @ApiModelProperty(value = "授予时间")
    private LocalDateTime grantTime;

    @ApiModelProperty(value = "有效期")
    private Long validityTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private String fileId;

    @ApiModelProperty(value = "通知内容")
    private String notifyContent;

    @ApiModelProperty(value = "发送人")
    private String sender;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
