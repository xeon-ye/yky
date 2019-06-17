package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**

 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description :  GccEmployContract查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccEmployContractQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String name;
    private String unit;
    private LocalDate contractStartdate;
    private LocalDate contractEnddate;
    private String signContract;
    private Long enclosure;
    private String remarks;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;

}
