package com.deloitte.services.project.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 *  * 项目表
 *  * </p>
 *
 * @author zhengchun
 * @since 2019-04-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsAndApplication {

    @ApiModelProperty(value = "projectId")
    private String id;
    @ApiModelProperty(value = "项目编码Code")
    private String projectCode;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "负责人Id")
    private String projectHeaderId;
    @ApiModelProperty(value = "负责人名称")
    private String projectHeaderName;
    @ApiModelProperty(value = "项目类型Code")
    private String projectTypeCode;
    @ApiModelProperty(value = "项目类型名称")
    private String projectTypeName;
    @ApiModelProperty(value = "单位Id")
    private String organizationId;
    @ApiModelProperty(value = "单位名称")
    private String organizationName;
    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;
    @ApiModelProperty(value = "项目状态名称")
    private String projectStatusName;
    @ApiModelProperty(value = "开始执行年度")
    private String planYear;
    private String planYear1;
    private String planYear2;
    @ApiModelProperty(value = "周期")
    private String cycle;
    private String cycle1;
    private String cycle2;
    @ApiModelProperty(value = "委托方")
    private String entrustId;
    private String entrustName;
    @ApiModelProperty(value = "承担方")
    private String assumeId;
    private String assumeName;
    @ApiModelProperty(value = "是否为批复新增项目")
    private String replyNewMark;
    @ApiModelProperty(value = "财务负责人ID")
    private String projectConnectStaffId;
    @ApiModelProperty(value = "财务负责人NAME")
    private String projectConnectStaffName;

    @ApiModelProperty(value = "applicationId(申报书Id)")
    private String applicationId;
    @ApiModelProperty(value = "（projectId）项目Id")
    private String projectId;
    @ApiModelProperty(value = "申报状态Code")
    private String appStateCode;
    @ApiModelProperty(value = "申报状态")
    private String appStateName;
    @ApiModelProperty(value = "申报年度")
    private String theApplicationYear;
    @ApiModelProperty(value = "项目类别Code")
    private String subProjectTypeCode;
    @ApiModelProperty(value = "项目类别")
    private String subProjectTypeName;

    @ApiModelProperty(value = "review 评审意见")
    private String reviewAdvice;
    @ApiModelProperty(value = "评审状态 code")
    private long reviewStatusCode;
    @ApiModelProperty(value = "评审状态 name")
    private String reviewStatusName;

    @ApiModelProperty(value = "最新批复年度")
    private String  beginYear;
    @ApiModelProperty(value = "批复id")
    private String  replyDocumentId;
    @ApiModelProperty(value = "批复编码")
    private String replyCode;
    @ApiModelProperty(value = "批复状态")
    private String replyStatusCode;
    private String replyStatusName;
    @ApiModelProperty(value = "批复年度")
    private String replyYear;
    private String replyYear1;
    private String replyYear2;

    @ApiModelProperty(value = "申报标志  是关联查询还是前导查询 0是关联 1是前导")
    private String appMark;

    @ApiModelProperty(value = "查询数据当前 页码")
    private Long currentPage;
    @ApiModelProperty(value = "当前页码 所要显示数据条数")
    private Long pageSize;

    @ApiModelProperty(value = "变更code")
    private String changeCode;
    @ApiModelProperty(value = "变更name")
    private String changeName;
    @ApiModelProperty(value = "变更原因")
    private String changeAdv;

    @ApiModelProperty(value = "变更时间")
    private Date changeDate;

    @ApiModelProperty(value = "变更开始时间")
    private Date changeBeginDate;

    @ApiModelProperty(value = "变更结束时间")
    private Date changeEndDate;

    @ApiModelProperty(value = "项目维护id")
    private String maintenanceId;
}
