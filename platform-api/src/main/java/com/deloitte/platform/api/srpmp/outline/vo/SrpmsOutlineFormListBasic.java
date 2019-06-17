package com.deloitte.platform.api.srpmp.outline.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineAcaExc新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineAcaExc表单")
@Data
public class SrpmsOutlineFormListBasic extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "单位名称")
    private String orgName;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
