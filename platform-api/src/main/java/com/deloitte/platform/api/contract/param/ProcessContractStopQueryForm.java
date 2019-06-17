package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   ProcessContractStop查询from对象
 * @Modified :
 */
@ApiModel("ProcessContractStop查询表单")
@Data
public class ProcessContractStopQueryForm extends BaseQueryForm<ProcessContractStopQueryParam>  {


    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "合同编号")
    private Long contractId;

    @ApiModelProperty(value = "发起人编号")
    private String senderCode;

    @ApiModelProperty(value = "发起人")
    private String sneder;

    @ApiModelProperty(value = "流程id")
    private String processId;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "审批状态")
    private String statue;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

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
