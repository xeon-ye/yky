package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlineResultIdentify查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineResultIdentify查询表单")
@Data
public class SrpmsOutlineResultIdentifyQueryForm extends BaseQueryForm<SrpmsOutlineResultIdentifyQueryParam>  {


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

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
