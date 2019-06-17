package com.deloitte.platform.api.project.vo;
import com.alibaba.fastjson.JSONArray;
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
 * @Date : Create in 2019-05-26
 * @Description : Application返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申报书ID")
    private String id;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "申报年度")
    private String theApplicationYear;

    @ApiModelProperty(value = "预算立项依据")
    private String budBasis;

    @ApiModelProperty(value = "预算主要内容")
    private String budContent;

    @ApiModelProperty(value = "预算项目总体目标及分阶段实施方案")
    private String budTargetMeasure;

    @ApiModelProperty(value = "预算项目实施条件")
    private String budCondition;

    @ApiModelProperty(value = "科研修购项目立项依据")
    private String sciBasis;

    @ApiModelProperty(value = "科研修购项目情况概述")
    private String sciContent;

    @ApiModelProperty(value = "教育修购项目实施必要性分析")
    private String eduNeed;

    @ApiModelProperty(value = "教育修购项目实施可行性分析")
    private String eduMaybe;

    @ApiModelProperty(value = "教育修购实施条件")
    private String eduCondi;

    @ApiModelProperty(value = "教育修购项目实施主要内容和相关预算")
    private String eduBudge;

    @ApiModelProperty(value = "教育修购项目进度与计划安排")
    private String eduPlan;

    @ApiModelProperty(value = "教育修购子活动风险与不确定性分析")
    private String eduAnalysis;

    @ApiModelProperty(value = "教育修购预期经济社会效益")
    private String eduBenefit;

    @ApiModelProperty(value = "教育修购项目立项依据")
    private String eduContent;

    @ApiModelProperty(value = "预算科目类")
    private String budgetAccType;

    @ApiModelProperty(value = "预算科目款")
    private String budgetAccValue;

    @ApiModelProperty(value = "优先级排序")
    private String priority;

    @ApiModelProperty(value = "单位预算编码")
    private String ouBudgetCode;

    @ApiModelProperty(value = "主管部门")
    private String department;

    @ApiModelProperty(value = "项目学校")
    private String school;

    @ApiModelProperty(value = "申报状态")
    private String appStateCode;

    @ApiModelProperty(value = "申报状态名称")
    private String appStateName;

    @ApiModelProperty(value = "主管部门代码")
    private String departmentCode;

    @ApiModelProperty(value = "项目属性")
    private String projectAttribute;

    @ApiModelProperty(value = "实施单位")
    private String operationOu;

    @ApiModelProperty(value = "项目执行中项目变更原因")
    private String proChange;

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

    @ApiModelProperty(value = "数据权限维度字段ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "申报书的ID")
    private String applicationId;

    @ApiModelProperty(value = "项目类别CODE")
    private String subprojectTypeCode;

    @ApiModelProperty(value = "项目类别CODE（多个）")
    private JSONArray subprojectTypeCodeList;

    @ApiModelProperty(value = "项目类别NAME")
    private String subprojectTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "业务单号流水号")
    private String serviceNum;

    @ApiModelProperty(value = "单位地址")
    private String proAdress;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "创建人id")
    private String createUserId;

}
