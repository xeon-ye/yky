package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlinePatent查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlinePatent查询表单")
@Data
public class SrpmsOutlinePatentQueryForm extends BaseQueryForm<SrpmsOutlinePatentQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
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

    @ApiModelProperty(value = "专利是否授权")
    private String authorizedFlag;

    @ApiModelProperty(value = "申请人")
    private String proposer;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime applicationTime;
    @ApiModelProperty(value = "查询申请开始时间")
    private LocalDateTime applicationStartDate;
    @ApiModelProperty(value = "查询申请结束时间")
    private LocalDateTime applicationEndDate;

    @ApiModelProperty(value = "授权时间")
    private LocalDateTime authorizedTime;
    @ApiModelProperty(value = "查询授权开始时间")
    private LocalDateTime authorizedStartDate;
    @ApiModelProperty(value = "查询授权结束时间")
    private LocalDateTime authorizedEndDate;

    @ApiModelProperty(value = "区域(国内、国外)")
    private String authorizedRegion;

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
