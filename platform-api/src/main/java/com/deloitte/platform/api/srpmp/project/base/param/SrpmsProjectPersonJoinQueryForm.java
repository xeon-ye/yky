package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsProjectPersonJoin查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectPersonJoin查询表单")
@Data
public class SrpmsProjectPersonJoinQueryForm extends BaseQueryForm<SrpmsProjectPersonJoinQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "合作类型CODE")
    private String joinWay;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生年月")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称CODE")
    private String positionTitle;

    @ApiModelProperty(value = "人员类别CODE")
    private String personCategory;

    @ApiModelProperty(value = "学位CODE")
    private String degree;

    @ApiModelProperty(value = "单位名称，科室名称")
    private String deptName;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @ApiModelProperty(value = "每年工作时间")
    private Integer workPerYear;

    @ApiModelProperty(value = "所属研究任务，项目分工")
    private String belongTask;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "源人员ID")
    private Long sourcePersonId;

    @ApiModelProperty(value = "承担其它相关国家科技计划-角色")
    private String otherProjectRole;

    @ApiModelProperty(value = "承担其它相关国家科技计划-项目名称")
    private String otherProjectName;

    @ApiModelProperty(value = "承担其它相关国家科技计划-项目来源")
    private String otherProjectSource;

    @ApiModelProperty(value = "承担其它相关国家科技计划-开始时间")
    private LocalDateTime otherProjectDateStart;

    @ApiModelProperty(value = "承担其它相关国家科技计划-结束时间")
    private LocalDateTime otherProjectDateEnd;

    @ApiModelProperty(value = "姓名")
    private String personName;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "年龄")
    private Double age;

    @ApiModelProperty(value = "是否有工资性收入")
    private String hasSalaryInput;
}
