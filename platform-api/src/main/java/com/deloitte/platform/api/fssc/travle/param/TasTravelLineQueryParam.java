package com.deloitte.platform.api.fssc.travle.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description :  TasTravelLine查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasTravelLineQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
    private String ext11;
    private String ext12;
    private String ext13;
    private String ext14;
    private String ext15;
    private Long version;
    private String name;
    private String jobNumber;
    private String levelName;
    private String travelTime;
    private String locationBegTravel;
    private String locationEndTravel;
    private BigDecimal totalForehead;
    private String remark;
    private Long dayNum;
    private BigDecimal unitPrice;
    private BigDecimal totalSum;
    private Long lineNumber;
    private Long documentId;
    private String documentType;
    private Long subTypeId;
    private String subTypeCode;
    private String accountCode;
    private String budgetAccountCode;
    private String subTypeName;
    private String invoiceNumber;
    private BigDecimal taxAmount;
    private BigDecimal taxRate;
    private BigDecimal hasVerAmount;
    private String paymentType;
    private BigDecimal noVerAmount;
    private LocalDateTime travelBeginTime;
    private LocalDateTime travelEndTime;
    private BigDecimal noTaxAmount;



}
