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
 * @Date : Create in 2019-05-22
 * @Description :   HrdataApply查询from对象
 * @Modified :
 */
@ApiModel("HrdataApply查询表单")
@Data
public class HrdataApplyQueryForm extends BaseQueryForm<HrdataApplyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "人员类型")
    private String popType;

    @ApiModelProperty(value = "数据用途")
    private String dataUsage;

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

    @ApiModelProperty(value = "申请人ID")
    private String empId;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "${field.comment}")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remarks;
}
