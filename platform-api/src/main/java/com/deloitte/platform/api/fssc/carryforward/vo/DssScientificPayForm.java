package com.deloitte.platform.api.fssc.carryforward.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description : DssScientificPay新增修改form对象
 * @Modified :
 */
@ApiModel("新增DssScientificPay表单")
@Data
public class DssScientificPayForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "来源系统")
    private String fromSystem;

    @ApiModelProperty(value = "财务单据编码")
    private String documentNum;

    @ApiModelProperty(value = "支出时间")
    private String payDate;

    @ApiModelProperty(value = "支出金额")
    private Double funds;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "任务编码")
    private String taskCode;

    @ApiModelProperty(value = "预算年度")
    private String budgetYear;

    @ApiModelProperty(value = "支出分类支出分类（预算项目） 支出分类（预算项目）")
    private String phyletic;

    @ApiModelProperty(value = "支出分类（预算项目）名称 支出分类（预算项目）名称")
    private String phyleticName;

    @ApiModelProperty(value = "Y 在用，N 已冲销")
    private String status;

    @ApiModelProperty(value = "经济分类-取单据头中“支出大类”对应的预算科目")
    private Long economicClassificationId;
    @ApiModelProperty(value = "单据头中备注")
    private String  remark;

}
