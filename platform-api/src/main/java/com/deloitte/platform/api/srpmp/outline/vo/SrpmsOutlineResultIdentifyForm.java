package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineResultIdentify新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineResultIdentify表单")
@Data
public class SrpmsOutlineResultIdentifyForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

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

}
