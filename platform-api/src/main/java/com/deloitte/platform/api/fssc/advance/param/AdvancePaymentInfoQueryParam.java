package com.deloitte.platform.api.fssc.advance.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmAdvancePaymentInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancePaymentInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private String documentNum;
    private String documentStatus;
    private String payStatus;
    private Long unitId;
    private String unitCode;
    private Long deptId;
    private String deptCode;
    private Long projectId;
    private String projectCode;
    private Long projectUnitId;
    private String projectUnitCode;
    private String projectUnitName;
    private Long mainTypeId;
    private String mainTypeCode;
    private BigDecimal totalSum;
    private BigDecimal hasVerAmount;
    private BigDecimal noVerAmount;
    private String currencyCode;
    private String isAgreedPromis;
    private String remark;
    private BigDecimal cost;
    private String unitName;
    private String deptName;
    private String mainTypeName;
    private String projectName;
    private String paymentType;
    private String supplier;
    private Long supplierId;
    private String supplierCode;
    private Long contactNumberId;
    private String contactNumber;
    private String contactName;
    private Long supportFileNum;
    private BigDecimal totalSumPosition;
    private BigDecimal hasVerAmountPosition;
    private BigDecimal noVerAmountPosition;
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
    private Long payMentId;
    private String fsscCode;

}
