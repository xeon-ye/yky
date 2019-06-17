package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-03-15
 * @Description :  SrpmsVoucher查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsVoucherQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String bizNumber;
    private String voucherName;
    private String voucherType;
    private String voucherStatus;
    private Long userId;
    private String personName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
