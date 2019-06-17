package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-13
 * @Description : SetupInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增SetupInfo表单")
@Data
public class SetupInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "合同办结原因")
    private String setupResult;

    @ApiModelProperty(value = "合同办结时间")
    private LocalDateTime setupTime;

    @ApiModelProperty(value = "备用字段1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段5")
    private Long spareField5;

}
