package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/9 9:44
 * @Description :
 * @Modified:
 */
@ApiModel("项目申报-我的申请-查询结果返回视图（VO）")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyApplicationVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private String id;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型Code")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectTypeName;

    @ApiModelProperty(value = "项目计划执行年度")
    private String planYear;

    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatusName;

    @ApiModelProperty(value = "评审状态Code")
    private String reviewState;

    @ApiModelProperty(value = "评审状态")
    private String reviewResult;

    @ApiModelProperty(value = "申报年度")
    private String theApplicationYear;

}
