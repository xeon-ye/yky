package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-23
 * @Description : BasicConfigura新增修改form对象
 * @Modified :
 */
@ApiModel("新增BasicConfigura表单")
@Data
public class BasicConfiguraForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "合同类型")
    private String contractCode;

    @ApiModelProperty(value = "基本信息（1：显示；0：不显示）")
    private String basicFlag;

    @ApiModelProperty(value = "关联合同（1：显示；0：不显示）")
    private String contractFlag;

    @ApiModelProperty(value = "履行人信息（1：显示；0：不显示）")
    private String executFlag;

    @ApiModelProperty(value = "财务信息（1：显示；0：不显示）")
    private String financeFlag;

    @ApiModelProperty(value = "订单信息（1：显示；0：不显示）")
    private String orderFlag;

    @ApiModelProperty(value = "项目信息（1：显示；0：不显示）")
    private String projectFlag;

    @ApiModelProperty(value = "其他监控计划（1：显示；0：不显示）")
    private String monitorFlag;

    @ApiModelProperty(value = "收票信息（1：显示；0：不显示）")
    private String ticketFlag;

    @ApiModelProperty(value = "履行计划1，履行查验2")
    private String type;

    @ApiModelProperty(value = "备注")
    private String remarkFlag;

    @ApiModelProperty(value = "履行计划1，履行查验2")
    private List<BasicConfiguraForm> basicConfiguraFormList;
}
