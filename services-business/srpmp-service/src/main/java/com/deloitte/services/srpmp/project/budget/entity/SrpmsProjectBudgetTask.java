package com.deloitte.services.srpmp.project.budget.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目任务书对应的预算书
 * </p>
 *
 * @author lixin
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_BUDGET_TASK")
@ApiModel(value="SrpmsProjectBudgetTask对象", description="项目任务书对应的预算书")
public class SrpmsProjectBudgetTask extends Model<SrpmsProjectBudgetTask> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField(value = "PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "预算执行期限开始")
    @TableField("BUDGET_ACTION_DATE_START")
    private LocalDateTime budgetActionDateStart;

    @ApiModelProperty(value = "预算执行期限结束")
    @TableField("BUDGET_ACTION_DATE_END")
    private LocalDateTime budgetActionDateEnd;

    @ApiModelProperty(value = "项目联系人ID")
    @TableField("PROJECT_CONTACT_PERSON_ID")
    private Long projectContactPersonId;

    @ApiModelProperty(value = "项目财务部负责人ID")
    @TableField("PROJECT_FINANCE_PERSON_ID")
    private Long projectFinancePersonId;

    @ApiModelProperty(value = "投入本项目的工作时间-固定研究人员")
    @TableField("WORK_TIME_FIX")
    private Long workTimeFix;

    @ApiModelProperty(value = "投入本项目的工作时间-流动人员")
    @TableField("WORK_TIME_FLOW")
    private Long workTimeFlow;

    @ApiModelProperty(value = "投入本项目的工作时间-累计")
    @TableField("WORK_TIME_ALL")
    private Long workTimeAll;

    @ApiModelProperty(value = "绩效指标详情(JSON文本)")
    @TableField("PERFORMANCE_INDICATOR_DETAIL")
    private String performanceIndicatorDetail;

    @ApiModelProperty(value = "预算说明书-单位支撑条件说明")
    @TableField("SPECIF_SUPPORT")
    private String specifSupport;

    @ApiModelProperty(value = "预算说明书-经费安排说明")
    @TableField("SPECIF_MONEY_PLAN")
    private String specifMoneyPlan;

    @ApiModelProperty(value = "预算说明书-设备费")
    @TableField("SPECIF_FACILITY")
    private String specifFacility;

    @ApiModelProperty(value = "预算说明书-材料费")
    @TableField("SPECIF_MATERIAL")
    private String specifMaterial;

    @ApiModelProperty(value = "预算说明书-测试化验收加工费")
    @TableField("SPECIF_TEST")
    private String specifTest;

    @ApiModelProperty(value = "预算说明书-燃料动力费")
    @TableField("SPECIF_FUEL")
    private String specifFuel;

    @ApiModelProperty(value = "预算说明书-差旅费")
    @TableField("SPECIF_TRAVEL")
    private String specifTravel;

    @ApiModelProperty(value = "预算说明书-出版费")
    @TableField("SPECIF_PUBLISH")
    private String specifPublish;

    @ApiModelProperty(value = "预算说明书-劳务费")
    @TableField("SPECIF_LABOUR")
    private String specifLabour;

    @ApiModelProperty(value = "预算说明书-专家咨询费")
    @TableField("SPECIF_CONSULT")
    private String specifConsult;

    @ApiModelProperty(value = "预算说明书-其他支出")
    @TableField("SPECIF_OTHER")
    private String specifOther;

    @ApiModelProperty(value = "流动人员合计")
    @TableField(value = "TEMP_MEMBER_COUNT")
    private long tempMemberCount;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "总预算明细")
    @TableField("BUDGET_DETAIL_ALL")
    private String budgetDetailAll;

    @ApiModelProperty(value = "项目支出绩效申报表-资金总额")
    @TableField("FUND_SOURCE_AMOUNT")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "项目支出绩效申报表-当年财政拨款")
    @TableField("FUND_SOURCE_PROJECT")
    private Double fundSourceProject;

    @ApiModelProperty(value = "项目支出绩效申报表-单位自筹经费")
    @TableField("FUND_SOURCE_SELF")
    private Double fundSourceSelf;

    @ApiModelProperty(value = "项目支出绩效申报表-其他经费")
    @TableField("FUND_SOURCE_OTHER")
    private Double fundSourceOther;

    @ApiModelProperty(value = "项目总体目标")
    @TableField("PROJECT_TARGET")
    private String projectTarget;

    @ApiModelProperty(value = "合作单位名称")
    @TableField("JOIN_DEPT_NAME")
    private String joinDeptName;

    @ApiModelProperty(value = "项目支出绩效申报表-全年资金总额")
    @TableField("FUND_SOURCE_AMOUNT_YEAR")
    private Double fundSourceAmountYear;

    @ApiModelProperty(value = "项目支出绩效申报表-全年财政拨款")
    @TableField("FUND_SOURCE_PROJECT_YEAR")
    private Double fundSourceProjectYear;

    @ApiModelProperty(value = "项目支出绩效申报表-全年其他经费")
    @TableField("FUND_SOURCE_OTHER_YEAR")
    private Double fundSourceOtherYear;

    @ApiModelProperty(value = "绩效指标库CODE")
    @TableField("PERFORMANCE_LIBRARY_CODE")
    private String performanceLibraryCode;

    @ApiModelProperty(value = "单价10万元以下试制设备金额")
    @TableField("PRICE_REAGENT_LESS")
    private Double priceReagentLess;

    @ApiModelProperty(value = "单价10万元以下试制设备数量")
    @TableField("COUNT_REAGENT_LESS")
    private Double countReagentLess;

    @ApiModelProperty(value = "单价10万元以下购置设备金额")
    @TableField("PRICE_DEVICE_LESS")
    private Double priceDeviceLess;

    @ApiModelProperty(value = "单价10万元以下购置设备数量")
    @TableField("COUNT_DEVICE_LESS")
    private Double countDeviceLess;

    @ApiModelProperty(value = "总费用在5万元以下的测试化验加工费合计")
    @TableField("OTHER_TEST_AMOUNT")
    private Double otherTestAmount;


    public static final String ID = "ID";

    public static final String BUDGET_ACTION_DATE_START = "BUDGET_ACTION_DATE_START";

    public static final String BUDGET_ACTION_DATE_END = "BUDGET_ACTION_DATE_END";

    public static final String PROJECT_CONTACT_PERSON_ID = "PROJECT_CONTACT_PERSON_ID";

    public static final String PROJECT_FINANCE_PERSON_ID = "PROJECT_FINANCE_PERSON_ID";

    public static final String WORK_TIME_FIX = "WORK_TIME_FIX";

    public static final String WORK_TIME_FLOW = "WORK_TIME_FLOW";

    public static final String WORK_TIME_ALL = "WORK_TIME_ALL";

    public static final String PERFORMANCE_INDICATOR_DETAIL = "PERFORMANCE_INDICATOR_DETAIL";

    public static final String SPECIF_SUPPORT = "SPECIF_SUPPORT";

    public static final String SPECIF_MONEY_PLAN = "SPECIF_MONEY_PLAN";

    public static final String SPECIF_FACILITY = "SPECIF_FACILITY";

    public static final String SPECIF_MATERIAL = "SPECIF_MATERIAL";

    public static final String SPECIF_TEST = "SPECIF_TEST";

    public static final String SPECIF_FUEL = "SPECIF_FUEL";

    public static final String SPECIF_TRAVEL = "SPECIF_TRAVEL";

    public static final String SPECIF_PUBLISH = "SPECIF_PUBLISH";

    public static final String SPECIF_LABOUR = "SPECIF_LABOUR";

    public static final String SPECIF_CONSULT = "SPECIF_CONSULT";

    public static final String SPECIF_OTHER = "SPECIF_OTHER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String BUDGET_DETAIL_ALL = "BUDGET_DETAIL_ALL";

    public static final String FUND_SOURCE_AMOUNT = "FUND_SOURCE_AMOUNT";

    public static final String FUND_SOURCE_PROJECT = "FUND_SOURCE_PROJECT";

    public static final String FUND_SOURCE_SELF = "FUND_SOURCE_SELF";

    public static final String FUND_SOURCE_OTHER = "FUND_SOURCE_OTHER";

    public static final String PROJECT_TARGET = "PROJECT_TARGET";

    public static final String JOIN_DEPT_NAME = "JOIN_DEPT_NAME";

    public static final String TEMP_MEMBER_COUNT = "TEMP_MEMBER_COUNT";

    public static final String FUND_SOURCE_AMOUNT_YEAR = "FUND_SOURCE_AMOUNT_YEAR";

    public static final String FUND_SOURCE_PROJECT_YEAR = "FUND_SOURCE_PROJECT_YEAR";

    public static final String FUND_SOURCE_OTHER_YEAR = "FUND_SOURCE_OTHER_YEAR";

    public static final String PERFORMANCE_LIBRARY_CODE = "PERFORMANCE_LIBRARY_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
