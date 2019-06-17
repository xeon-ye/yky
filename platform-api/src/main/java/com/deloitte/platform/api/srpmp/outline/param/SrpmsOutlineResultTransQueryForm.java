package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlineResultTrans查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineResultTrans查询表单")
@Data
public class SrpmsOutlineResultTransQueryForm extends BaseQueryForm<SrpmsOutlineResultTransQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
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

    @ApiModelProperty(value = "合同签订年份")
    private String contractSigningYear;

    @ApiModelProperty(value = "合同金额(万元)")
    private Double contractAmount;

    @ApiModelProperty(value = "本年到位金额(万元)")
    private Double yearEnsuredAmount;

    @ApiModelProperty(value = "转化费来源")
    private String transFeeSource;

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

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
