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
 * @Description : SrpmsOutlineAcaExc返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineAcaExcVo extends SrpmsOutlineVoListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long baseId;

    @ApiModelProperty(value = "区域")
    private String region;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "主办/参办 10-参办 20-主办")
    private String sponsorFlag;

    @ApiModelProperty(value = "主办/参办名称")
    private String sponsorFlagName;

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

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
