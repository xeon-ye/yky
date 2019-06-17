package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccOnjobInformation查询from对象
 * @Modified :
 */
@ApiModel("GccOnjobInformation查询表单")
@Data
public class GccBaseQueryForm extends BaseQueryForm<GccBaseQueryParam>  {


    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "入选年份")
    private String choiceYear;

    @ApiModelProperty(value = "人才项目名称")
    private String projectName;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "人才项目类别")
    private String projectCategory;

    @ApiModelProperty(value = "类型 0所院,1 院校")
    private String type;
}
