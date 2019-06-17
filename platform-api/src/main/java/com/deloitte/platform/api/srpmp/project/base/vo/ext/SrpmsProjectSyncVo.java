package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author:LIJUN
 * Date:22/04/2019
 * Description:
 */
@ApiModel("项目信息同步VO")
@Data
public class SrpmsProjectSyncVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "申请书编号")
    private String aplNum;

    @ApiModelProperty(value = "预算书编号")
    private String budNum;

    @ApiModelProperty(value = "任务书编号")
    private String tasNum;

    @ApiModelProperty(value = "公示件编号")
    private String pudNum;

    @ApiModelProperty(value = "批复件编号")
    private String apdNum;

    @ApiModelProperty(value = "申报年度")
    private String applyYear;

    @ApiModelProperty(value = "项目类型CODE")
    private String projectCategory;

    @ApiModelProperty(value = "项目类型描述")
    private String projectCategoryDesc;

    @ApiModelProperty(value = "项目类型（最小分类）")
    private String projectType;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    private Long leadPersonId;

    @ApiModelProperty(value = "共同首席专家ID")
    private Long bothTopExpertPersonId;

    @ApiModelProperty(value = "负责人信息JSON")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "共同负责人信息JSON")
    private JSONObject bothTopExpertPerson;

    @ApiModelProperty(value = "承担单位ID")
    private Long leadDeptId;

    @ApiModelProperty(value = "承担单位信息JSON")
    private JSONObject leadDept;

    @ApiModelProperty(value = "项目角色CODE")
    private String projectRole;

    @ApiModelProperty(value = "学科分类CODE")
    private String subjectCategory;

    @ApiModelProperty(value = "项目状态 0：未提交 10：已提交(待审核) 15：二级已审核  20：已审核通过  30：已审核拒绝 40：已公示  50：已批复  ")
    private String status;

    @ApiModelProperty(value = "公示时间")
    private LocalDateTime publicTime;

    @ApiModelProperty(value = "批复时间")
    private LocalDateTime approveTime;

    @ApiModelProperty(value = "删除标识位")
    private String delFlg;

    @ApiModelProperty(value = "预算年度")
    private Integer budgetYear;

    @ApiModelProperty(value = "预算信息")
    private List<BudgetDetailVo> budgetDetail;

    @ApiModelProperty(value = "预算金额")
    private Double budgetAmount;

    @ApiModelProperty(value = "非创新工程项目预算明细（多年数据）")
    private List<SrpmsProjectBudgetDetailVo> budgetDetailVoList;

    @ApiModelProperty(value = "创新工程总预算明细")
    private List<SrpmsProjectTaskSyncVo> srpmsProjectTaskSyncVoList;

    @ApiModelProperty(value = "参与人员")
    private List<SrpmsProjectPersonJoinVo> projectPersonJoinVoList;
}
