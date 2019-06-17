package com.deloitte.platform.api.srpmp.project.base.vo;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description : SrpmsProjectTask新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectTask表单")
@Data
public class SrpmsProjectTaskForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long projectId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务类型CODE")
    private String taskCategory;

    @ApiModelProperty(value = "任务年度")
    private Integer taskYear;

    @ApiModelProperty(value = "序号")
    private Integer serial;

    @ApiModelProperty(value = "年度考核目标")
    private String taskTargetYear;

    @ApiModelProperty(value = "重要任务的时间节点")
    private String importantPoint;

    @ApiModelProperty(value = "主要内容")
    private String taskContent;

    @ApiModelProperty(value = "3年考核指标")
    private String threeYearTarget;

    @ApiModelProperty(value = "分年度考核指标")
    private String targetPerYear;

    @ApiModelProperty(value = "负责人ID")
    private Long leadPersonId;

    @ApiModelProperty(value = "参加人员")
    private Long joinPersonId;

    @ApiModelProperty(value = "xx年经费分配比例")
    private String budgetAllotRatio;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "参加课题组长及人员")
    private String groupLeaderMember;

    @ApiModelProperty(value = "第一年预期目标及考核指标(任务书)")
    private String firstYearTarget;

    @ApiModelProperty(value = "考核方式（任务书）")
    private String examMode;

    @ApiModelProperty(value = "中期考核目标")
    private String mediumTarget;

    @ApiModelProperty(value = "任务编码")
    private String projectTaskNum;

}
