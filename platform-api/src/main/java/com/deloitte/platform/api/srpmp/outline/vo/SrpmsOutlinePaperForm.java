package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlinePaper新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlinePaper表单")
@Data
public class SrpmsOutlinePaperForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

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

}
