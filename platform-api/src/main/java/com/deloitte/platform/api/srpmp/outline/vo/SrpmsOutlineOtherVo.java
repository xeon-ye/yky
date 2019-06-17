package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineOther返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineOtherVo extends SrpmsOutlineVoListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long baseId;

    @ApiModelProperty(value = "科研基地类型")
    private String srpmsBaseCat;

    @ApiModelProperty(value = "科研基地类型字典名称")
    private String srpmsBaseCatName;

    @ApiModelProperty(value = "基地级别")
    private String srpmsBaseLevel;

    @ApiModelProperty(value = "基地级别字典名称")
    private String srpmsBaseLevelName;

    @ApiModelProperty(value = "实验室/中心名称")
    private String expCenterName;

    @ApiModelProperty(value = "实验室/中心主任")
    private String expCenterDirector;

    @ApiModelProperty(value = "依托单位")
    private String supportOrg;

    @ApiModelProperty(value = "批准时间")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "批准文号")
    private String approvalNum;

    @ApiModelProperty(value = "成立时间")
    private LocalDateTime builtTime;

    @ApiModelProperty(value = "详细地址")
    private String detailAddress;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
