package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description : GccReviewOption新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccReviewOption表单")
@Data
public class GccReviewOptionBatchForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "评审意见")
    private String reviewContent;

    @ApiModelProperty(value = "类型 0 所院，1 院校 ")
    private String type;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
