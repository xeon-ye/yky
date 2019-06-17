package com.deloitte.platform.api.srpmp.project.budget.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectBudgetApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectBudgetApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "预算执行期限开始")
    private LocalDateTime budgetActionDateStart;

    @ApiModelProperty(value = "预算执行期限结束")
    private LocalDateTime budgetActionDateEnd;

    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectDeptId;

    @ApiModelProperty(value = "项目负责人ID")
    private Long projectLeadPersonId;

    @ApiModelProperty(value = "项目联系人ID")
    private Long projectContactPersonId;

    @ApiModelProperty(value = "项目财务部负责人ID")
    private Long projectFinancePersonId;

    @ApiModelProperty(value = "投入本项目的工作时间-固定研究人员")
    private Long workTimeFix;

    @ApiModelProperty(value = "投入本项目的工作时间-流动人员")
    private Long workTimeFlow;

    @ApiModelProperty(value = "投入本项目的工作时间-累计")
    private Long workTimeAll;

    @ApiModelProperty(value = "绩效指标详情(JSON文本)")
    private String performanceIndicatorDetail;

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

    @ApiModelProperty(value = "项目支出绩效申报表-全年资金总额")
    private Double fundSourceAmountYear;

    @ApiModelProperty(value = "项目支出绩效申报表-全年财政拨款")
    private Double fundSourceProjectYear;

    @ApiModelProperty(value = "项目支出绩效申报表-全年其他经费")
    private Double fundSourceOtherYear;

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
