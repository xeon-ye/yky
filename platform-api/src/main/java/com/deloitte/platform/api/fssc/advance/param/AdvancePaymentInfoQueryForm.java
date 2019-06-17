package com.deloitte.platform.api.fssc.advance.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :   BmAdvancePaymentInfo查询from对象
 * @Modified :
 */
@ApiModel("BmAdvancePaymentInfo查询表单")
@Data
public class AdvancePaymentInfoQueryForm extends BaseQueryForm<AdvancePaymentInfoQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建开始时间")
    private LocalDateTime timeStart;

    @ApiModelProperty(value="创建结束时间")
    private LocalDateTime timeEnd;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "归属部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "供应商")
    private String supplier;

    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "合同id")
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号")
    private String contactNumber;

    @ApiModelProperty(value = "合同名称")
    private String contactName;

    @ApiModelProperty(value = "支持附件数量")
    private Long supportFileNum;

    @ApiModelProperty(value = "合计金额本币位")
    private BigDecimal totalSumPosition;

    @ApiModelProperty(value = "已核销本币位金额")
    private BigDecimal hasVerAmountPosition;

    @ApiModelProperty(value = "未核销本币位金额")
    private BigDecimal noVerAmountPosition;

    @ApiModelProperty(value="是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "${field.comment}")
    private String ext1;

    @ApiModelProperty(value = "${field.comment}")
    private String ext2;

    @ApiModelProperty(value = "${field.comment}")
    private String ext3;

    @ApiModelProperty(value = "${field.comment}")
    private String ext4;

    @ApiModelProperty(value = "${field.comment}")
    private String ext5;

    @ApiModelProperty(value = "${field.comment}")
    private String ext6;

    @ApiModelProperty(value = "${field.comment}")
    private String ext7;

    @ApiModelProperty(value = "${field.comment}")
    private String ext8;

    @ApiModelProperty(value = "${field.comment}")
    private String ext9;

    @ApiModelProperty(value = "${field.comment}")
    private String ext10;

    @ApiModelProperty(value = "${field.comment}")
    private String ext11;

    @ApiModelProperty(value = "${field.comment}")
    private String ext12;

    @ApiModelProperty(value = "${field.comment}")
    private String ext13;

    @ApiModelProperty(value = "${field.comment}")
    private String ext14;

    @ApiModelProperty(value = "${field.comment}")
    private String ext15;

    private BigDecimal moneyStart;

    private BigDecimal moneyEnd;

    @ApiModelProperty(value="已支付金额")
    private BigDecimal amountPaid;

    @ApiModelProperty(value="未支付金额")
    private BigDecimal unpaidAmount;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织PATH")
    private String orgPath;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;
}
