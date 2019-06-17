package com.deloitte.platform.api.contract.vo;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-03
 * @Description : BasicInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoVoToFssc extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "合同编号")
    private String contractNo;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "原合同id")
    private String oldContractId;

    @ApiModelProperty(value = "原合同编号")
    private String oldContractNo;

    @ApiModelProperty(value = "原合同名称")
    private String oldContractName;

    @ApiModelProperty(value = "合同流水号")
    private String contractSerialNumber;

    @ApiModelProperty(value = "收支类型编号")
    private String incomeExpenditureTypeCode;

    @ApiModelProperty(value = "收支类型")
    private String incomeExpenditureType;

    @ApiModelProperty(value = "合同类型编号")
    private String contractTypeCode;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同性质编号")
    private String contractNatureCode;

    @ApiModelProperty(value = "合同性质")
    private String contractNature;

    @ApiModelProperty(value = "标准文本属性编号")
    private String templateAttributeCode;

    @ApiModelProperty(value = "标准文本属性")
    private String templateAttribute;

    @ApiModelProperty(value = "标准文本属性补充（选择方式为其他时填写）")
    private String templateAttributeRemark;

    @ApiModelProperty(value = "标准文本编号")
    private String templateCode;

    @ApiModelProperty(value = "标准文本名称")
    private String templateName;

    @ApiModelProperty(value = "合同币种编号")
    private String contractCurrencyCode;

    @ApiModelProperty(value = "合同币种")
    private String contractCurrency;

    @ApiModelProperty(value = "合同币种补充（选择方式为其他时填写）")
    private String contractCurrencyRemark;

    @ApiModelProperty(value = "对方选择方式编号")
    private String otherWayCode;

    @ApiModelProperty(value = "对方选择方式")
    private String otherWay;

    @ApiModelProperty(value = "对方选择方式补充（选择方式为其他时填写）")
    private String otherWayRemark;

    @ApiModelProperty(value = "经费来源")
    private String sourcesFunding;

    @ApiModelProperty(value = "收付款方式编号")
    private String paymentWayCode;

    @ApiModelProperty(value = "收付款方式")
    private String paymentWay;

    @ApiModelProperty(value = "收付款方式补充（选择方式为其他时填写）")
    private String paymentWayRemark;

    @ApiModelProperty(value = "结算比例")
    private String billingRatio;

    @ApiModelProperty(value = "原含税合同金额")
    private BigDecimal oldAmountIncludTax;

    @ApiModelProperty(value = "原税率")
    private BigDecimal oldTaxRate;

    @ApiModelProperty(value = "原税额")
    private BigDecimal oldTaxAmount;

    @ApiModelProperty(value = "原不含税合同金额")
    private BigDecimal oldAmountExcludTax;

    @ApiModelProperty(value = "原含税合同金额大写（汉字版本）")
    private String oldAmountExcludTaxCapital;

    @ApiModelProperty(value = "已报账金额")
    private BigDecimal reportedAmount;

    @ApiModelProperty(value = "已付款金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "含税合同金额")
    private BigDecimal amountIncludTax;

    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "税率补充（选择方式为其他时填写）")
    private BigDecimal taxRateRemark;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "不含税合同金额")
    private BigDecimal amountExcludTax;

    @ApiModelProperty(value = "含税合同金额大写（汉字版本）")
    private String amountExcludTaxCapital;

    @ApiModelProperty(value = "保证金/保函编号")
    private String isPayDepositCode;

    @ApiModelProperty(value = "保证金/保函")
    private String isPayDeposit;

    @ApiModelProperty(value = "保证金/保函金额")
    private BigDecimal contractDeposit;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "明确到期时间")
    private LocalDateTime performanceEffectiveTime;

    @ApiModelProperty(value = "是否为涉外合同")
    private String isForeignContract;

    @ApiModelProperty(value = "经办人编号")
    private String operatorCode;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "原经办人编号")
    private String oldOperatorCode;

    @ApiModelProperty(value = "原经办人")
    private String oldOperator;

    @ApiModelProperty(value = "主办部门编号")
    private String orgCode;

    @ApiModelProperty(value = "主办部门")
    private String org;

    @ApiModelProperty(value = "联系电话")
    private String contactNum;

    @ApiModelProperty(value = "原主办部门编号")
    private String oldOrgCode;

    @ApiModelProperty(value = "原主办部门")
    private String oldOrg;

    @ApiModelProperty(value = "原联系电话")
    private String oldContactNum;

    @ApiModelProperty(value = "合同履行情况编号")
    private String executeInfoCode;

    @ApiModelProperty(value = "合同履行情况")
    private String executeInfo;

    @ApiModelProperty(value = "合同履行情况补充（选择方式为其他时填写）")
    private String executeInfoRemark;

    @ApiModelProperty(value = "履行原因说明")
    private String executeCause;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "履行期限-开始时间")
    private LocalDateTime executeStartTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "履行期限-结束时间")
    private LocalDateTime executeEndTime;

    @ApiModelProperty(value = "合同正文")
    private String contractBody;

    @ApiModelProperty(value = "合同附件")
    private String contractAttament;

    @ApiModelProperty(value = "合同启动依据")
    private String contractStartupBasis;

    @ApiModelProperty(value = "定稿人编号")
    private String surePersonCode;

    @ApiModelProperty(value = "定稿人")
    private String surePerson;

    @ApiModelProperty(value = "履行人编号")
    private String executerCode;

    @ApiModelProperty(value = "履行人")
    private String executer;

    @ApiModelProperty(value = "原履行人编号")
    private String oldExecutePersonCode;

    @ApiModelProperty(value = "原履行人")
    private String oldExecutePerson;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "无效时间")
    private LocalDateTime disabledTime;

    @ApiModelProperty(value = "无效人")
    private String disabledBy;

    @ApiModelProperty(value = "审批状态")
    private String statue;

    @ApiModelProperty(value = "审批状态名称")
    private String statueName;

    @ApiModelProperty(value = "定稿状态")
    private String sureStatue;

    @ApiModelProperty(value = "签署状态")
    private String signStatue;

    @ApiModelProperty(value = "履行状态")
    private String executeStatue;

    @ApiModelProperty(value = "办结状态")
    private String finishStatue;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private BigDecimal spareField5;

    @ApiModelProperty(value = "关联性质编号")
    private String relationCode;

    @ApiModelProperty(value = "关联性质")
    private String relation;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "定稿时间")
    private LocalDateTime sureStatueTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "签署时间")
    private LocalDateTime signStatueTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "履行时间")
    private LocalDateTime executeStatueTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "办结时间")
    private LocalDateTime finishStatueTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "审批时间")
    private LocalDateTime statueTime;

    @ApiModelProperty(value = "保函到期方式code")
    private String performanceTypeCode;

    @ApiModelProperty(value = "保函到期方式")
    private String performanceType;

    @ApiModelProperty(value = "条件到期时间")
    private String performanceEffectiveCond;

    @ApiModelProperty(value = "履行期限方式")
    private String executeType;

    @ApiModelProperty(value = "履行期限方式code")
    private String executeTypeCode;

    @ApiModelProperty(value = "固定时长")
    private Long executeLong;

    @ApiModelProperty(value = "时间类型（年；月；日）")
    private String timeType;

    @ApiModelProperty(value = "结束条件")
    private String engCond;

    @ApiModelProperty(value = "签约主体对象")
    private ArrayList<BasicSubjectMapVo> basicSubjectList;

    @ApiModelProperty(value = "上传文件")
    private ArrayList<BasicAttamentMapVo> basicAttamentList;

    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @ApiModelProperty(value = "合同id")
    private String contractId;
    @ApiModelProperty(value="签约主体")
    private List<SysSignSubjectInfoForm> sysSignSubjectInfoList;

    @ApiModelProperty(value = "打印状态")
    private String printStatue;

    @ApiModelProperty(value = "打印时间")
    private String printStatueTime;

    @ApiModelProperty(value = "标准文本名称对象")
    private  StandardTemplateVo standardTemplateVo;
    @ApiModelProperty(value = "我方主体信息")
    private String outSubject;

    @ApiModelProperty(value = "对方主体信息")
    private String otherSubject;

    @ApiModelProperty(value="财务信息")
    private List<FinanceInfoVoToFssc> listFinanceInfoVo;

    @ApiModelProperty(value = "项目信息")
    private List<SysProjectInfoVoFssc> listSysProjectInfoVo;

    @ApiModelProperty(value = "其他监控计划")
    private List<MonitorInfoVo> listMonitorInfoVo;

    @ApiModelProperty(value = "关联合同")
    private List<BasicInfoVoToFssc> listBasicInfoVo;

    @ApiModelProperty(value = "履行人信息")
    private UserVo executerUserVo;

    @ApiModelProperty(value = "订单信息")
    private List<OrderInfoVo> orderInfoVo;

    @ApiModelProperty(value = "收/发票信息")
    private List<TicketInfoVo> TicketInfoVo;

    @ApiModelProperty(value = "合同打印方式")
    private String way;

    @ApiModelProperty(value = "审批节点")
    private List<ProcessTaskVo> processTaskVoList;

    @ApiModelProperty(value = "合同打印方式 1是可以撤回  0是不可以撤回")
    private String canRollBack;

    @ApiModelProperty(value = "签约主体名称")
    private String subjectName;

    @ApiModelProperty(value = "到期预警")
    private String executeWaring;

    @ApiModelProperty(value = "收付款预警和非正常履行事项")
    private String financeWaring;

    @ApiModelProperty(value = "查询列表状态")
    private String statueList;

    @ApiModelProperty(value = "签署信息")
    private List<SignInfoVo> signInfoVo;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "经办人信息")
    private List<ProcessOperatorTransferForm> listProcessOperatorTransferForm;

    @ApiModelProperty(value = "查询集")
    private List<BasicInfoForm> queryBasicInfoForm;

    @ApiModelProperty(value = "删除集")
    private List<BasicInfoForm> deleteBasicInfoForm;

    @ApiModelProperty(value = "约定收款比例汇总")
    private String appointIncomeRateSum;

    @ApiModelProperty(value = "约定收款金额汇总")
    private String appointIncomeAmountSum;

    @ApiModelProperty(value = "实际收款金额汇总")
    private String actIncomeRateSum;

    @ApiModelProperty(value = "约定付款比例汇总")
    private String appointPayRateSum;

    @ApiModelProperty(value = "约定付款金额汇总")
    private String appointPayAmountSum;

    @ApiModelProperty(value = "实际付款金额汇总")
    private String actPayRateSum;

    @ApiModelProperty(value = "履行检查总数")
    private String total;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "生效时间")
    private LocalDateTime validTime;

    @ApiModelProperty(value = "原保证金/保函金额")
    private BigDecimal oldContractDeposit;
}
