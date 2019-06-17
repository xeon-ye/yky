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
 * @Description : SrpmsOutlineResultIdentify返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineResultIdentifyVo extends SrpmsOutlineVoListBasic {
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

    @ApiModelProperty(value = "第一完成单位")
    private String completionOrg;

    @ApiModelProperty(value = "完成人")
    private String completionPerson;

    @ApiModelProperty(value = "任务来源")
    private String taskSource;

    @ApiModelProperty(value = "鉴定/验收单位")
    private String identifyOrg;

    @ApiModelProperty(value = "鉴定/验收形式")
    private String identifyWay;

    @ApiModelProperty(value = "鉴定/验收形式字典名称")
    private String identifyWayName;

    @ApiModelProperty(value = "鉴定/验收时间")
    private LocalDateTime identifyTime;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
