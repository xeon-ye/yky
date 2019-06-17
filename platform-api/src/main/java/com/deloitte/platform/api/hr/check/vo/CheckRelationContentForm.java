package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckRelationContent新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckRelationContent表单")
@Data
public class CheckRelationContentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核关系id")
    private Long checkRelationId;

    @ApiModelProperty(value = "被评价人分组id")
    private Long checkBeAppraiserId;

    @ApiModelProperty(value = "评价人分组id")
    private Long checkAppraiserId;

    @ApiModelProperty(value = "评价人权重")
    private Long appraiserWeight;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
