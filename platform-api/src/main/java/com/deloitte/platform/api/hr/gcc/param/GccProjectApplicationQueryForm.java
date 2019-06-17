package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProjectApplication查询from对象
 * @Modified :
 */
@ApiModel("GccProjectApplication查询表单")
@Data
public class GccProjectApplicationQueryForm extends BaseQueryForm<GccProjectApplicationQueryParam>  {

    @ApiModelProperty(value = "申报人编号")
    private Long userId;

    @ApiModelProperty(value = "申报人编号")
    private String noticeName;

    @ApiModelProperty(value = "申报人姓名")
    private String userName;

    @ApiModelProperty(value = "申报人单位")
    private String applyUnit;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "项目编号")
    private Long projectId;

    @ApiModelProperty(value = "申报年份")
    private String applyYear;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "项目编号")
    private String  projectName;

    @ApiModelProperty(value = "分类名")
    private String  projetCategoryName;
}
