package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/6 10:33
 * @Description :
 * @Modified:
 */
@ApiModel("项目库查询视图（VO）")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLibraryVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目类型Code")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectTypeName;

    @ApiModelProperty(value = "项目申报年度")
    private String theApplicationYear;

    @ApiModelProperty(value = "计划执行年度")
    private String playYear;

    @ApiModelProperty(value = "项目周期（年）")
    private String projectCycle;

    @ApiModelProperty(value = "项目单位")
    private String operationOu;

    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatusName;

    @ApiModelProperty(value = "院校排序")
    private Integer schoolPriority;

    @ApiModelProperty(value = "总预算（万元）")
    private Integer totalBudget;

    @ApiModelProperty(value = "支出计划（万元）")
    private String planPay;


}
