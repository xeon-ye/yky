package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrApplyingFlow新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrApplyingFlow表单")
@Data
public class HrApplyingFlowForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "流程")
    private String technologicalProcess;

    @ApiModelProperty(value = "结果：待审核，通过，未通过")
    private String result;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "拟入职日期")
    private LocalDateTime proposedEntryDate;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "人员类型")
    private String type;

}
