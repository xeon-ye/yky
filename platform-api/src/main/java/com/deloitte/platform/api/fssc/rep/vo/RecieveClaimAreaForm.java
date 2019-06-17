package com.deloitte.platform.api.fssc.rep.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecieveClaimArea新增修改form对象
 * @Modified :
 */
@ApiModel("新增RecieveClaimArea表单")
@Data
public class RecieveClaimAreaForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "认领部门code")
    private String claimDeptCode;

    @ApiModelProperty(value = "认领部门名称")
    private String claimDeptName;

    @ApiModelProperty(value = "认领部门id")
    private Long claimDeptId;

    @ApiModelProperty(value = "认领人id")
    private Long claimEmpId;

    @ApiModelProperty(value = "认领人名称")
    private String claimEmpName;

    @ApiModelProperty(value = "认领人工号")
    private String claimEmpNo;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

}
