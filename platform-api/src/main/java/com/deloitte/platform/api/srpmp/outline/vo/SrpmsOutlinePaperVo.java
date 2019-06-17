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
 * @Description : SrpmsOutlinePaper返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlinePaperVo extends SrpmsOutlineVoListBasic {
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

    @ApiModelProperty(value = "通讯作者")
    private String correspondenceAuthor;

    @ApiModelProperty(value = "第一作者")
    private String firstAuthor;

    @ApiModelProperty(value = "其他作者")
    private String otherAuthor;

    @ApiModelProperty(value = "类别")
    private String paperCat;

    @ApiModelProperty(value = "类别名称")
    private String paperCatName;

    @ApiModelProperty(value = "论文题目")
    private String paperTitle;

    @ApiModelProperty(value = "期刊名称")
    private String journalTitle;

    @ApiModelProperty(value = "发表单位")
    private String publicationOrg;

    @ApiModelProperty(value = "卷")
    private String paperVolume;

    @ApiModelProperty(value = "期")
    private String stage;

    @ApiModelProperty(value = "页码")
    private String page;

    @ApiModelProperty(value = "影响因子")
    private Double influenceFactor;

    @ApiModelProperty(value = "期刊性质")
    private String property;

    @ApiModelProperty(value = "期刊性质字典名称")
    private String propertyName;

    @ApiModelProperty(value = "期刊区域")
    private String region;

    @ApiModelProperty(value = "期刊区域字典名称")
    private String regionName;

    @ApiModelProperty(value = "等级")
    private String paperLevel;

    @ApiModelProperty(value = "等级字典名称")
    private String paperLevelName;

    @ApiModelProperty(value = "项目负责人")
    private String hproInCharge;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
