package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description :   ExecuteWaring查询from对象
 * @Modified :
 */
@ApiModel("ExecuteWaring查询表单")
@Data
public class ExecuteWaringQueryForm extends BaseQueryForm<ExecuteWaringQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "公司编码")
    private String deptCode;

    @ApiModelProperty(value = "公司名称")
    private String deptName;

    @ApiModelProperty(value = "部门编码")
    private String orgCode;

    @ApiModelProperty(value = "公司名称")
    private String orgName;

    @ApiModelProperty(value = "履行提醒时间")
    private Double waringTime;

    @ApiModelProperty(value = "提醒频率")
    private Double waringFrequency;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;
}
