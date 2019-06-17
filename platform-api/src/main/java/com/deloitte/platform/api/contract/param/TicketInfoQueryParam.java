package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yangyq
 * @Date : Create in 2019-04-16
 * @Description :  TicketInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long taxpayerCode;
    private String taxpayer;
    private String taxNum;
    private String invoiceCode;
    private String invoiceNum;
    private Long amountExcludTax;
    private Long taxRate;
    private Long taxAmount;
    private Long amount;
    private LocalDateTime ticketTime;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String isUsed;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private String contractType;
    private String type;

}
