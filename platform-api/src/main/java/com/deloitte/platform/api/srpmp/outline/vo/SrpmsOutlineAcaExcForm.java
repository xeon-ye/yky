package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineAcaExc新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineAcaExc表单")
@Data
public class SrpmsOutlineAcaExcForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

    @ApiModelProperty(value = "区域")
    private String region;

    @ApiModelProperty(value = "主办/参办 10-参办 20-主办")
    private String sponsorFlag;

    @ApiModelProperty(value = "参与单位")
    private String joinOrg;

    @ApiModelProperty(value = "会议类型")
    private String joinType;

    @ApiModelProperty(value = "参与形式")
    private String joinCat;

    @ApiModelProperty(value = "举办时间")
    private LocalDateTime holdingTime;

    @ApiModelProperty(value = "会议名称")
    private String teamName;

}
