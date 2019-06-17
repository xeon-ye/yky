package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-06-11
 * @Description :  ApprovalVouchers查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalVouchersQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String bizNumber;
    private String voucherName;
    private String voucherType;
    private String voucherStatus;
    private Long userId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String orgCode;
    private String businessId;
    private String businessName;

}
