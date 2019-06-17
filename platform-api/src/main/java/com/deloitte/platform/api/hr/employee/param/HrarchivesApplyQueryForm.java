package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-21
 * @Description :   HrarchivesApply查询from对象
 * @Modified :
 */
@ApiModel("HrarchivesApply查询表单")
@Data
public class HrarchivesApplyQueryForm extends BaseQueryForm<HrarchivesApplyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "查档事由")
    private String applyReason;

    @ApiModelProperty(value = "${field.comment}")
    private String applyState;

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

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "查档内容")
    private String applyContent;

    private String types;
}
