package com.deloitte.platform.api.fssc.bpm.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description : Process新增修改form对象
 * @Modified :
 */
@ApiModel("启动流程Process表单")
@Data
@NoArgsConstructor
public class ProcessStartForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单据ID")
    @NotNull
    private Long documentId;

    @ApiModelProperty(value = "单据类型取表名,如借款就是借款的头表名称")
    @NotEmpty
    private String documentType;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "审批类型 若为空则走正常审批 参照FsscEums CHARGE_AGAINST " +
            " NORMAL_APPROVAL里面的工作流枚举值,审批时可不传此参数,默认正常审批")
    private String processType="NORMAL_APPROVAL";

    @ApiModelProperty(value = "是否预算警告检查,预算超过80%,警告")
    private String budgetWarningCheck = "Y";

    @ApiModelProperty(value = "抄送人工号 备注")
    private List<Map<String,String>> copyEmpNos;

    @ApiModelProperty(value = "流程变量")
    Map<String,String> processVariables=new HashMap<>();

}
