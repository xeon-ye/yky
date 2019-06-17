package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-14
 * @Description : Projects返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目属性")
    private String projectAttribute;

    @ApiModelProperty(value = "项目类型code")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型名称")
    private String projectTypeName;

    @ApiModelProperty(value = "项目执行年度")
    private String planYear;

    @ApiModelProperty(value = "项目周期")
    private String cycle;

    @ApiModelProperty(value = "项目负责人ID")
    private String projectHeaderId;

    @ApiModelProperty(value = "项目负责人名称")
    private String projectHeaderName;

    @ApiModelProperty(value = "财务负责人ID")
    private String finHeaderId;

    @ApiModelProperty(value = "项目代管人ID")
    private String projectConnectStaffId;

    @ApiModelProperty(value = "项目单位ID")
    private String organizationId;

    @ApiModelProperty(value = "项目单位名称")
    private String organizationName;

    @ApiModelProperty(value = "一级项目名称")
    private String projectCatgory;

    @ApiModelProperty(value = "项目取消原因描述")
    private String proCancelDes;

    @ApiModelProperty(value = "项目执行中项目变更原因")
    private String proChange;

    @ApiModelProperty(value = "优先级排序")
    private String priority;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    private String ext5;

    @ApiModelProperty(value = "数据权限维度字段ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "拓展")
    private String orgPath;

    @ApiModelProperty(value = "项目状态code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态名称")
    private String projectStatusName;

    @ApiModelProperty(value = "委托方id")
    private String entrustId;

    @ApiModelProperty(value = "承担方id")
    private String assumeId;

    @ApiModelProperty(value = "是否未维护项目 1是 2 否")
    private String projectMark;

    @ApiModelProperty(value = "财务负责人名称")
    private String finHeaderName;

    @ApiModelProperty(value = "项目代管人名称")
    private String projectConnectStaffName;

    @ApiModelProperty(value = "一级项目code")
    private String projectCatgoryCode;

    @ApiModelProperty(value = "委托方")
    private String entrustName;

    @ApiModelProperty(value = "承担方")
    private String assumeName;

    @ApiModelProperty(value = "true关联 false未关联")
    private String applicationMark;

    @ApiModelProperty(value = "单位邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "单位负责人ID")
    private String ouChargeStaffId;

    @ApiModelProperty(value = "单位负责人名称")
    private String ouChargeStaffName;

    @ApiModelProperty(value = "项目负责人职务")
    private String projectHeaderPost;

    @ApiModelProperty(value = "项目负责人电话")
    private String projectHeaderTel;

    @ApiModelProperty(value = "项目代管人职务")
    private String proConnectStaffPost;

    @ApiModelProperty(value = "项目代管人电话")
    private String proConnectStaffTel;

    @ApiModelProperty(value = "单位地址")
    private String adress;

    @ApiModelProperty(value = "主管部门")
    private String department;

    @ApiModelProperty(value = "主管部门代码")
    private String departmentCode;

    @ApiModelProperty(value = "实施单位")
    private String appOpartionOu;

    @ApiModelProperty(value = "是否为项目新增 1是 2 否")
    private String replyNewMark;

    @ApiModelProperty(value = "子活动类别Code")
    private String subactCatagoryCode;

    @ApiModelProperty(value = "子活动类别名称")
    private String subactCatagoryName;

    @ApiModelProperty(value = "项目单位CODE")
    private String organizationCode;

    @ApiModelProperty(value = "项目类别CODE")
    private String projectCatagoryCode;

    @ApiModelProperty(value = "项目类别名称")
    private String projectCatagoryName;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "创建人id")
    private String createUserId;

}
