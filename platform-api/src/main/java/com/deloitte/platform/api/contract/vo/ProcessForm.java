package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-30
 * @Description : Process新增修改form对象
 * @Modified :
 */
@ApiModel("新增Process表单")
@Data
public class ProcessForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "机构")
    private String orgCode;

    @ApiModelProperty(value = "收支类型")
    private String incomeExpenditureTypeCode;

    @ApiModelProperty(value = "流程类型(1：合同审批，2：合同打印签署，3：合同办结，4：合同作废，5：经办人移交，6：履行人移交)")
    private String processType;

    @ApiModelProperty(value = "最小合同金额")
    private Long contractAmountMin;

    @ApiModelProperty(value = "最大合同金额")
    private Long contractAmountMax;

    @ApiModelProperty(value = "流程key")
    private String processDefineKey;

    @ApiModelProperty(value = "流程名称")
    private String processDefineName;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

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

}
