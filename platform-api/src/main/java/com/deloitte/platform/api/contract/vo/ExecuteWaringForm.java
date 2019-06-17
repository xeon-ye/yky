package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description : ExecuteWaring新增修改form对象
 * @Modified :
 */
@ApiModel("新增ExecuteWaring表单")
@Data
public class ExecuteWaringForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

}
