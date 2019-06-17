package com.deloitte.platform.api.fssc.travle.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description :  TasTravelStandards查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasTravelStandardsQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nationAdminCode;
    private String country;
    private String placeName;
    private BigDecimal ministerialLevel;
    private BigDecimal secretaries;
    private BigDecimal otherPersonnel;
    private BigDecimal foodAllowance;
    private BigDecimal trafficSubsidy;
    private String peakMonth;

}
