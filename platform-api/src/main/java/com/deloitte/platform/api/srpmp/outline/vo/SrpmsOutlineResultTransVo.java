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
 * @Description : SrpmsOutlineResultTrans返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineResultTransVo extends SrpmsOutlineVoListBasic {
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

    @ApiModelProperty(value = "成果名称")
    private String proResultName;

    @ApiModelProperty(value = "负责人")
    private String proInCharge;

    @ApiModelProperty(value = "技术成果转化形式")
    private String transWay;

    @ApiModelProperty(value = "技术成果转化形式字典名称")
    private String transWayName;

    @ApiModelProperty(value = "合同签订年份")
    private String contractSigningYear;

    @ApiModelProperty(value = "合同签订年份")
    private String contractSigningYearName;

    @ApiModelProperty(value = "合同金额(万元)")
    private Double contractAmount;

    @ApiModelProperty(value = "本年到位金额(万元)")
    private Double yearEnsuredAmount;

    @ApiModelProperty(value = "转化费来源")
    private String transFeeSource;

    @ApiModelProperty(value = "转化费来源字典名称")
    private String transFeeSourceName;

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
