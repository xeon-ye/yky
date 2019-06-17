package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineAward新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineAward表单")
@Data
public class SrpmsOutlineAwardForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "获奖成果")
    private String awardResults;

    @ApiModelProperty(value = "奖项类别")
    private String awardCat;

    @ApiModelProperty(value = "完成单位")
    private String completionOrg;

    @ApiModelProperty(value = "主要完成人")
    private String completionPerson;

    @ApiModelProperty(value = "奖项名称")
    private String awardName;

    @ApiModelProperty(value = "奖项等级")
    private String awardLevel;

}
