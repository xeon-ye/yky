package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-11
 * @Description : GccReportUnit返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccReportUnitVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "上报项目")
    private String projectId;

    @ApiModelProperty(value = "上报项目")
    private String projectName;

    @ApiModelProperty(value = "上报项目类别")
    private String projectCategoryId;

    @ApiModelProperty(value = "上报人编号")
    private String userId;

    @ApiModelProperty(value = "上报人单位")
    private String userUnit;

    @ApiModelProperty(value = "上报人姓名")
    private String userName;

    @ApiModelProperty(value = "上报人部门")
    private String userDept;

    @ApiModelProperty(value = "院校通知")
    private String yxNotice;

    @ApiModelProperty(value = "提交部门")
    private String sumbitDept;

    @ApiModelProperty(value = "上报时间")
    private LocalDateTime reportedTime;

    @ApiModelProperty(value = "推荐报告")
    private String enclosure1;

    @ApiModelProperty(value = "一览表")
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

    @ApiModelProperty(value = "推荐报告")
    private HrAttachmentVo attachmentVo;

    @ApiModelProperty(value = "一览表")
    private HrAttachmentVo attachmentVo1;

}
