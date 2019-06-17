package com.deloitte.platform.api.fssc.carryforward.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description : DssScientificRecive新增修改form对象
 * @Modified :
 */
@ApiModel("新增DssScientificRecive表单")
@Data
public class DssScientificReciveForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "任务编码")
    private String taskCode;

    @ApiModelProperty(value = "到位时间 到位时间 存储yyyymm")
    private LocalDateTime reciveDate;

    @ApiModelProperty(value = "到位金额 到位金额")
    private Double funds;

    @ApiModelProperty(value = "到位单位ID 到位单位ID")
    private Long reciveDeptId;

    @ApiModelProperty(value = "来源系统")
    private String fromSystem;

    @ApiModelProperty(value = "预算年度")
    private String budgetYear;

    @ApiModelProperty(value = "财务单据编码")
    private String documentNum  ;

    @ApiModelProperty(value = "Y  在用  N 已冲销")
    private String status;

    @ApiModelProperty(value = "财务系统总唯一标识数据的ID")
    private Long cwLineId;

}
