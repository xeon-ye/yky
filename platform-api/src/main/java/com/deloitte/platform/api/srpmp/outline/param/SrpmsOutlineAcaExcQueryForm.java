package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlineAcaExc查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineAcaExc查询表单")
@Data
public class SrpmsOutlineAcaExcQueryForm extends BaseQueryForm<SrpmsOutlineAcaExcQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private Long baseId;

    @ApiModelProperty(value = "区域")
    private String region;

    @ApiModelProperty(value = "主办/参办 10-参办 20-主办")
    private String sponsorFlag;

    @ApiModelProperty(value = "参与单位")
    private String joinOrg;

    @ApiModelProperty(value = "会议类型")
    private String joinType;

    @ApiModelProperty(value = "参与形式")
    private String joinCat;

    @ApiModelProperty(value = "举办时间")
    private String holdingTime;

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

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
