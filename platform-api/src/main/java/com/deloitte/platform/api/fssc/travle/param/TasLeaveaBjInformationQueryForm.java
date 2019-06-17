package com.deloitte.platform.api.fssc.travle.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :   TasLeaveaBjInformation查询from对象
 * @Modified :
 */
@ApiModel("TasLeaveaBjInformation查询表单")
@Data
public class TasLeaveaBjInformationQueryForm extends BaseQueryForm<TasLeaveaBjInformationQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    private String ext15;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String jobNumber;

    @ApiModelProperty(value = "级别")
    private String levelName;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "单价id")
    private Long documentId;

    @ApiModelProperty(value = "单价类型")
    private String documentType;

    @ApiModelProperty(value = "职务")
    private String post;

    @ApiModelProperty(value = "出差地点")
    private String travelAddress;

    @ApiModelProperty(value = "离京时间")
    private LocalDateTime leaveaBjTime;

    @ApiModelProperty(value = "返京时间")
    private LocalDateTime returnBjTime;

    @ApiModelProperty(value = "本年度出差次数统计")
    private Long yearTravelNum;

    @ApiModelProperty(value = "在京主持工作负责人")
    private String headWorkBj;

    @ApiModelProperty(value = "组织id")
    private Long orgId;

    @ApiModelProperty(value = "组织path")
    private String orgPath;
}
