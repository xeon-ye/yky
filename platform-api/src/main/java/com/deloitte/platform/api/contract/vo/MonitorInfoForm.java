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
 * @Date : Create in 2019-04-20
 * @Description : MonitorInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增MonitorInfo表单")
@Data
public class MonitorInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "监控事项")
    private String monitorName;

    @ApiModelProperty(value = "计划完成日期")
    private LocalDateTime planFinishTime;

    @ApiModelProperty(value = "计划完成金额/数量")
    private Double planFinishNum;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "合同编号")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "实际完成日期")
    private LocalDateTime actFinishTime;

    @ApiModelProperty(value = "实际完成金额/数量")
    private Double actFinishNum;

}
