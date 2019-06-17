package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlinePaper查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlinePaper查询表单")
@Data
public class SrpmsOutlinePaperQueryForm extends BaseQueryForm<SrpmsOutlinePaperQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
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

    @ApiModelProperty(value = "期刊区域")
    private String region;

    @ApiModelProperty(value = "等级")
    private String paperLevel;

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

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
