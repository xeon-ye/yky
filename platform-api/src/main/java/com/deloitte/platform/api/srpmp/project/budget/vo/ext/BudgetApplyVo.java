package com.deloitte.platform.api.srpmp.project.budget.vo.ext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.srpmp.common.config.LongJsonDeserializer;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
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
public class BudgetApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "预算执行期限开始")
    private String budgetActionDateStart;

    @ApiModelProperty(value = "预算执行期限结束")
    private String budgetActionDateEnd;

    @ApiModelProperty(value = "项目联系人")
    private SrpmsProjectPersonVo contactPerson;

    @ApiModelProperty(value = "项目财务部负责人")
    private SrpmsProjectPersonVo financePerson;

    @ApiModelProperty(value = "投入本项目的工作时间-固定研究人员")
    private String workTimeFix;

    @ApiModelProperty(value = "投入本项目的工作时间-流动人员")
    private String workTimeFlow;

    @ApiModelProperty(value = "投入本项目的工作时间-累计")
    private String workTimeAll;

    @ApiModelProperty(value = "绩效指标详情(JSON文本)")
    private JSONArray performanceIndicatorDetail;

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
    private String fundSourceAmount;

    @ApiModelProperty(value = "项目支出绩效申报表-当年财政拨款")
    private String fundSourceProject;

    @ApiModelProperty(value = "项目支出绩效申报表-单位自筹经费")
    private String fundSourceSelf;

    @ApiModelProperty(value = "项目支出绩效申报表-其他经费")
    private String fundSourceOther;

    @ApiModelProperty(value = "项目总体目标")
    private String projectTarget;

    @ApiModelProperty(value = "合作单位名称")
    private String joinDeptName;

    @ApiModelProperty(value = "项目参加人员")
    private List<SrpmsProjectPersonJoinVo> mainMemberList;

    @ApiModelProperty(value = "分任务预算表")
    private List<SrpmsProjectTaskVo> taskDecompositionList;
    
    @ApiModelProperty(value = "分单位预算表")
    private List<SrpmsProjectTaskVo> budgetDeptList;

    @ApiModelProperty(value = "总预算表")
    private List<SrpmsProjectTaskVo> budgetAllList;

    @ApiModelProperty(value = "设备费-购置试制设备预算明细表")
    private List<SrpmsProjectBudgetDeviceVo> deviceBudgetList;

    @ApiModelProperty(value = "测试化验加工费预算明细表")
    private List<SrpmsProjectBudgetDeviceVo> testBudgetList;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "共同首席专家信息")
    private JSONObject bothTopExpertPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;

    @ApiModelProperty(value = "临时和流动人员合计")
    private String tempMemberCount;

    @ApiModelProperty(value = "项目支出绩效申报表-全年资金总额")
    private String fundSourceAmountYear;

    @ApiModelProperty(value = "项目支出绩效申报表-全年财政拨款")
    private String fundSourceProjectYear;

    @ApiModelProperty(value = "项目支出绩效申报表-全年其他经费")
    private String fundSourceOtherYear;

    @ApiModelProperty(value = "绩效指标库CODE")
    private String performanceLibraryCode;

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
}
