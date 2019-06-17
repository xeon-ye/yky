package com.deloitte.platform.api.srpmp.project.apply.param;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : zhouchen
 * @Date : Create in 2019-04-17
 * @Description :   SrpmsProjectApplyReform查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectApplyReform查询表单")
@Data
public class SrpmsProjectApplyReformQueryForm extends BaseQueryForm<SrpmsProjectApplyReformQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "申请人ID")
    private Long projectApplicantId;

    @ApiModelProperty(value = "经费支出预算明细")
    private String expenditureBudgetDetail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "立项依据，研究意义")
    private String approvalBasisMeaning;

    @ApiModelProperty(value = "研究内容/拟解决的关键问题")
    private String contentTargetProblem;

    @ApiModelProperty(value = "拟采取的研究方案及可行性分析")
    private String researchSchemeFeasibility;

    @ApiModelProperty(value = "预期目标")
    private String projectExpectTarget;

    @ApiModelProperty(value = "主要技术特点和创新点")
    private String projectTechnicalInnovation;

    @ApiModelProperty(value = "工作基础")
    private String researchFoundation;

    @ApiModelProperty(value = "工作条件")
    private String workinfConditions;

    @ApiModelProperty(value = "申请人简介")
    private String projectApplicantIntroduction;

    @ApiModelProperty(value = "预算说明")
    private String budgetDescription;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "项目组织单位")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "年度预算明细")
    private List<SrpmsProjectBudgetDetailVo> budgetPreYearList;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;
}
