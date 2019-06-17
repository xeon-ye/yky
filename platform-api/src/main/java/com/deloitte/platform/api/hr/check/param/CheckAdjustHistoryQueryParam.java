package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :  CheckAdjustHistory查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAdjustHistoryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkResultId;
    private String adjustLevel;
    private String adjustReason;
    private String systemLevel;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
