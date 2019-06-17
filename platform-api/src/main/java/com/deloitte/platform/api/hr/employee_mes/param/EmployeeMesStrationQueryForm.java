package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-14
 * @Description :   EmployeeMesStration查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesStration查询表单")
@Data
public class EmployeeMesStrationQueryForm extends BaseQueryForm<EmployeeMesStrationQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Double id;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String dep;

    @ApiModelProperty(value = "行政职务名称")
    private String segment1;

    @ApiModelProperty(value = "行政职务等级")
    private String segment2;

    @ApiModelProperty(value = "任职文号")
    private String segment3;

    @ApiModelProperty(value = "任职起始时间")
    private LocalDateTime segment4;

    @ApiModelProperty(value = "任命单位")
    private String segment5;

    @ApiModelProperty(value = "任职单位及部门")
    private String segment6;

    @ApiModelProperty(value = "免职时间")
    private LocalDateTime segment7;

    @ApiModelProperty(value = "免职文号")
    private String segment8;

    @ApiModelProperty(value = "免职批准单位")
    private String segment9;

    @ApiModelProperty(value = "${field.comment}")
    private String empMesId;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "三定岗位名称")
    private String column1;
}
