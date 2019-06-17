package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlinePatent新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlinePatent表单")
@Data
public class SrpmsOutlinePatentForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

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

    @ApiModelProperty(value = "专利是否授权")
    private String authorizedFlag;

    @ApiModelProperty(value = "申请人")
    private String proposer;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime applicationTime;

    @ApiModelProperty(value = "授权时间")
    private LocalDateTime authorizedTime;

    @ApiModelProperty(value = "区域(国内、国外)")
    private String authorizedRegion;

}
