package com.deloitte.platform.api.srpmp.project.budget.vo.ext;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonDeserializer;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDeviceVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BudgetApplySubmitVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    //项目信息开始
    @ApiModelProperty(value = "申请编号")
    private String applyNum;

    @NotBlank(message = "项目名称不能为空。")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;
    //项目信息结束

    @ApiModelProperty(value = "预算执行期限开始")
    private LocalDateTime budgetActionDateStart;

    @ApiModelProperty(value = "预算执行期限结束")
    private LocalDateTime budgetActionDateEnd;

    @ApiModelProperty(value = "项目联系人")
    private SrpmsProjectPersonVo contactPerson;

    @ApiModelProperty(value = "项目财务部负责人")
    private SrpmsProjectPersonVo financePerson;

    @ApiModelProperty(value = "投入本项目的工作时间-固定研究人员")
    private Long workTimeFix;

    @ApiModelProperty(value = "投入本项目的工作时间-流动人员")
    private Long workTimeFlow;

    @ApiModelProperty(value = "投入本项目的工作时间-累计")
    private Long workTimeAll;

    @ApiModelProperty(value = "绩效指标详情(JSON文本)")
    private List<JSONObject> performanceIndicatorDetail;

    @ApiModelProperty(value = "预算说明书-单位支撑条件说明")
    private String specifSupport;

    @ApiModelProperty(value = "预算说明书-经费安排说明")
    private String specifMoneyPlan;

    @ApiModelProperty(value = "预算说明书-设备费")
    private String specifFacility;

    @ApiModelProperty(value = "预算说明书-材料费")
    private String specifMaterial;

    @ApiModelProperty(value = "预算说明书-测试化验收加工费")
    private String specifTest;

    @ApiModelProperty(value = "预算说明书-燃料动力费")
    private String specifFuel;

    @ApiModelProperty(value = "预算说明书-差旅费")
    private String specifTravel;

    @ApiModelProperty(value = "预算说明书-出版费")
    private String specifPublish;

    @ApiModelProperty(value = "预算说明书-劳务费")
    private String specifLabour;

    @ApiModelProperty(value = "预算说明书-专家咨询费")
    private String specifConsult;

    @ApiModelProperty(value = "预算说明书-其他支出")
    private String specifOther;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "总预算明细")
    private String budgetDetailAll;

    @ApiModelProperty(value = "项目支出绩效申报表-资金总额")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "项目支出绩效申报表-当年财政拨款")
    private Double fundSourceProject;

    @ApiModelProperty(value = "项目支出绩效申报表-单位自筹经费")
    private Double fundSourceSelf;

    @ApiModelProperty(value = "项目支出绩效申报表-其他经费")
    private Double fundSourceOther;

    @ApiModelProperty(value = "项目总体目标")
    private String projectTarget;

    @ApiModelProperty(value = "合作单位名称")
    private String joinDeptName;

    @ApiModelProperty(value = "单价10万元以下试制设备金额")
    private Double priceReagentLess;

    @ApiModelProperty(value = "单价10万元以下试制设备数量")
    private Double countReagentLess;

    @ApiModelProperty(value = "单价10万元以下购置设备金额")
    private Double priceDeviceLess;

    @ApiModelProperty(value = "单价10万元以下购置设备数量")
    private Double countDeviceLess;

    @ApiModelProperty(value = "总费用在5万元以下的测试化验加工费合计")
    private Double otherTestAmount;

    @ApiModelProperty(value = "项目参加人员")
    private List<SrpmsProjectPersonJoinVo> mianMemberList;

    @ApiModelProperty(value = "分任务预算表")
    private List<SrpmsProjectBudgetDetailVo> taskBudgetList;

    @ApiModelProperty(value = "分单位预算表")
    private List<SrpmsProjectBudgetDetailVo> deptBudgetList;

    @ApiModelProperty(value = "设备费-购置试制设备预算明细表")
    private List<SrpmsProjectBudgetDeviceVo> deviceBudgetList;

    @ApiModelProperty(value = "测试化验加工费预算明细表")
    private List<SrpmsProjectBudgetDeviceVo> testBudgetList;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;
}
