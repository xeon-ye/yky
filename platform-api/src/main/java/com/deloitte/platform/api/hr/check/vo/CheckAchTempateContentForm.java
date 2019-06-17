package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchTempateContent新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchTempateContent表单")
@Data
public class CheckAchTempateContentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "修改时传id")
    private String id;

    @ApiModelProperty(value = "业绩模板id")
    private Long achTempateId;

    @ApiModelProperty(value = "序号")
    private String orderNumber;

    @ApiModelProperty(value = "显示名称")
    private String displayName;

    @ApiModelProperty(value = "字段类型")
    private String fieldType;

    @ApiModelProperty(value = "表格列标题")
    private String columnTitle;

    @ApiModelProperty(value = "是否显示")
    private String isDisplay;

    @ApiModelProperty(value = "允许字数")
    private Long wordNumber;

    @ApiModelProperty(value = "列宽")
    private Long columnWidth;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
