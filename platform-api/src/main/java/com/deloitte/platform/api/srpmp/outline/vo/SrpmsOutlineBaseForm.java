package com.deloitte.platform.api.srpmp.outline.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description : SrpmsOutlineBase新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineBase表单")
@Data
public class SrpmsOutlineBaseForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "题录类型")
    private String type;

    @ApiModelProperty(value = "记录总条数")
    private Long total;

}
