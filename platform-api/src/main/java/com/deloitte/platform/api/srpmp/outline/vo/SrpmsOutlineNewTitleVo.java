package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description : SrpmsOutlineNewTitle新增修改form对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineNewTitleVo extends SrpmsOutlineVoListBasic {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID，主键，业务不相关")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "项目类别")
    private String proCategory;

    @ApiModelProperty(value = "项目类别数组")
    private String[] proCategoryArray;

    @ApiModelProperty(value = "项目类别名称")
    private String proCategoryName;

    @ApiModelProperty(value = "来源机构")
    private String proSourceOrg;

    @ApiModelProperty(value = "参与程度")
    private String proEnterType;

    @ApiModelProperty(value = "参与程度名称")
    private String proEnterTypeName;

    @ApiModelProperty(value = "项目负责人")
    private String proInCharge;

    @ApiModelProperty(value = "项目开始时间")
    private LocalDateTime proStartDate;

    @ApiModelProperty(value = "项目结束时间")
    private LocalDateTime proEndDate;

    @ApiModelProperty(value = "项目状态")
    private String proState;

    @ApiModelProperty(value = "项目状态名称")
    private String proStateName;

    @ApiModelProperty(value = "项目总经费")
    private Double proTotalOutlay;

    @ApiModelProperty(value = "项目到位经费")
    private Double proReceiveOutlay;

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
