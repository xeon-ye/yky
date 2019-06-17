package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :   WorkproveApply查询from对象
 * @Modified :
 */
@ApiModel("WorkproveApply查询表单")
@Data
public class WorkproveApplyQueryForm extends BaseQueryForm<WorkproveApplyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "员工表唯一标识")
    private String userid;

    @ApiModelProperty(value = "申请原因")
    private String applyReason;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请人姓名")
    private String name;

    @ApiModelProperty(value = "使用标准模板 0不使用 1使用")
    private String worktemplate;

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    private List<String> ids;
}
