package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProDecNotice查询from对象
 * @Modified :
 */
@ApiModel("GccProDecNotice查询表单")
@Data
public class GccProDecNoticeQueryForm extends BaseQueryForm<GccProDecNoticeQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @ApiModelProperty(value = "发布单位")
    private String releaseUnit;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类别")
    private String projectCategroy;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releaseTime;

    @ApiModelProperty(value = "通知内容")
    private String noticeCotent;

    @ApiModelProperty(value = "是否立即发布 0 否，1是")
    private String publishNow;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "类型（所院/院校）")
    private String type;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "项目类别编号")
    private Long projectCategroyId;


    @ApiModelProperty(value = "项目编号")
    private Long projecId;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "所院是否启动0否1是")
    private String acaStart;

    @ApiModelProperty(value = "院校是否启动0否1是")
    private String schStart;

    @ApiModelProperty(value = "所院项目中上级院校项目")
    private Long parentId;

    @ApiModelProperty(value = "所院项目中上级院校项目")
    private String declare;
}
