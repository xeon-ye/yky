package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-05-07
 * @Description :   MprEvaBaseInfo查询from对象
 * @Modified :
 */
@ApiModel("MprEvaBaseInfo查询表单")
@Data
public class MprEvaBaseInfoQueryForm extends BaseQueryForm<MprEvaBaseInfoQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目目标")
    private String projectObjectives;

    @ApiModelProperty(value = "岗位数")
    private String jobQuantity;

    @ApiModelProperty(value = "经费预算")
    private String budget;

    @ApiModelProperty(value = "执行周期（开始时间）")
    private LocalDateTime executionStartTime;

    @ApiModelProperty(value = "执行周期（结束时间）")
    private LocalDateTime executionEndTime;

    @ApiModelProperty(value = "项目类别")
    private String projectCategory;

    @ApiModelProperty(value = "项目分类")
    private String projectClassification;

    @ApiModelProperty(value = "牵头单位")
    private String leadUnit;

    @ApiModelProperty(value = "参加单位")
    private String takeUnit;

    @ApiModelProperty(value = "首席专家")
    private String chiefSpecialist;

    @ApiModelProperty(value = "共同首席专家")
    private String jointChiefSpecialist;

    @ApiModelProperty(value = "进展情况")
    private String progressState;

    @ApiModelProperty(value = "实施情况")
    private String workState;

    @ApiModelProperty(value = "博士后人数")
    private String postdoctoralNum;

    @ApiModelProperty(value = "博士生人数")
    private String doctoralNum;

    @ApiModelProperty(value = "硕士生人数")
    private String masterNum;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "附件一统计信息")
    private String annexStat;

    @ApiModelProperty(value = "中期绩效报告处理状态")
    private String processStatus;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目类型code，跟PROJECT_TYPE无关")
    private String projectTypeCode;

    @ApiModelProperty(value = "报告类型")
    private String reportType;

    @ApiModelProperty(value = "申请人所属单位CODE")
    private String applyDeptCode;

    @ApiModelProperty(value = "其他情况")
    private String otherCase;

    @ApiModelProperty(value = "报告ID")
    private Long reportId;

    @ApiModelProperty(value = "报告年份")
    private Long reportYear;

    @ApiModelProperty(value = "报告标题")
    private String reportTitle;

    @ApiModelProperty(value = "其它类型名称")
    private String repOtherType;

    @ApiModelProperty(value = "查询类型：1-我的申请；2-我的报告；3-报告列表")
    private String queryType;

}
