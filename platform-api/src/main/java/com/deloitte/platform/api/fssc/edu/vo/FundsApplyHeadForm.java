package com.deloitte.platform.api.fssc.edu.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description : FundsApplyHead新增修改form对象
 * @Modified :
 */
@ApiModel("新增FundsApplyHead表单")
@Data
public class FundsApplyHeadForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "管理部门code")
    @NotEmpty
    private String deptCode;

    @ApiModelProperty(value = "管理部门ID")
    @NotNull
    private Long deptId;

    @ApiModelProperty(value = "管理部门名称")
    @NotEmpty
    private String deptName;

    @ApiModelProperty(value = "申请经费类型")
    private String fundType;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "行")
    private List<FundsApplyLineForm> fundsApplyLineForms;

    @ApiModelProperty(value = "附件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    private String reSubmitType;

}
