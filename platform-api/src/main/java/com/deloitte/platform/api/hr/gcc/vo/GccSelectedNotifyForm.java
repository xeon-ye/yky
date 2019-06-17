package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccSelectedNotify新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccSelectedNotify表单")
@Data
public class GccSelectedNotifyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

}
