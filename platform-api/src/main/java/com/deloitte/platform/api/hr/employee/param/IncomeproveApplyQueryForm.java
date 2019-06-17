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
 * @Description :   IncomeproveApply查询from对象
 * @Modified :
 */
@ApiModel("IncomeproveApply查询表单")
@Data
public class IncomeproveApplyQueryForm extends BaseQueryForm<IncomeproveApplyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "用户表唯一标识")
    private String userid;

    @ApiModelProperty(value = "申请原因")
    private String applyReason;

    @ApiModelProperty(value = "申请单位")
    private String applyDep;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "签字盖章")
    private String sign;

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

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    private List<String> ids;
}
