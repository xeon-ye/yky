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
 * @Description : SrpmsOutlinePatent返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlinePatentVo extends SrpmsOutlineVoListBasic {
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

    @ApiModelProperty(value = "申请专利号")
    private String applicationNum;

    @ApiModelProperty(value = "授权专利号")
    private String patentNum;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利类别")
    private String patentCat;

    @ApiModelProperty(value = "专利类别字典名称")
    private String patentCatName;

    @ApiModelProperty(value = "专利是否授权")
    private String authorizedFlag;

    @ApiModelProperty(value = "专利是否授权字典名称")
    private String authorizedFlagName;

    @ApiModelProperty(value = "申请人")
    private String proposer;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime applicationTime;

    @ApiModelProperty(value = "授权时间")
    private LocalDateTime authorizedTime;

    @ApiModelProperty(value = "区域(国内、国外)")
    private String authorizedRegion;

    @ApiModelProperty(value = "区域字典名称")
    private String authorizedRegionName;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
