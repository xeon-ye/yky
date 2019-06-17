package com.deloitte.platform.api.fssc.unit.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : UnitInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增UnitInfo表单")
@Data
public class UnitInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "单位编码")
    private Long unitCode;

    @ApiModelProperty(value = "单位名称")
    @NotBlank
    private String unitName;

    @ApiModelProperty(value = "单位类型")
    @NotBlank
    private String unitType;

    @ApiModelProperty(value = "所属区域")
    @NotNull
    private Long areaId;

    @ApiModelProperty(value = "联系人")
    @NotBlank
    private String concatUserName;
    @ApiModelProperty(value = "联系方式")
    private String concatWay;

    @ApiModelProperty(value = "创建人ID")
    private String createUserName;

    private Long createBy;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "状态")
    private String auditStatus;

    @ApiModelProperty(value = "纳税识别号")
    @NotBlank
    private String taxRegistNum;


    @ApiModelProperty(value = "收款方式")
    @NotBlank
    private String recieveMoneyType;

    @ApiModelProperty(value = "版本")
    private Long version;

    private String address;
    @ApiModelProperty(value = "统一信用代码")
    @NotBlank
    private String commonCreditCode;
}
