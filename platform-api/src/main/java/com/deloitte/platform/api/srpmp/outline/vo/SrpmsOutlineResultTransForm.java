package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineResultTrans新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineResultTrans表单")
@Data
public class SrpmsOutlineResultTransForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "成果名称")
    private String proResultName;

    @ApiModelProperty(value = "负责人")
    private String proInCharge;

    @ApiModelProperty(value = "技术成果转化形式")
    private String transWay;

    @ApiModelProperty(value = "合同签订年份")
    private String contractSigningYear;

    @ApiModelProperty(value = "合同金额(万元)")
    private Double contractAmount;

    @ApiModelProperty(value = "本年到位金额(万元)")
    private Double yearEnsuredAmount;

    @ApiModelProperty(value = "转化费来源")
    private String transFeeSource;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
