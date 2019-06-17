package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccProDecNotice返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccProDecNoticeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @ApiModelProperty(value = "发布单位")
    private String releaseUnit;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类别")
    private String projectCategroy;

    @ApiModelProperty(value = "项目类别编号")
    private String projectCategroyId;

    @ApiModelProperty(value = "发布时间")
    private LocalDate releaseTime;

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
    private String enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "项目编号")
    private String projectId;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "所院是否启动0否1是")
    private String acaStart;

    @ApiModelProperty(value = "院校是否启动0否1是")
    private String schStart;

    @ApiModelProperty(value = "所院项目中上级院校项目")
    private String parentId;

    @ApiModelProperty(value = "文件信息")
    private HrAttachmentVo hrAttachmentVo;

    @ApiModelProperty(value = "单位名称")
    private String unitName;
}
