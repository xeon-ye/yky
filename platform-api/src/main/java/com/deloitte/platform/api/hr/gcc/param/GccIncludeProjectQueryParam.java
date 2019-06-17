package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccIncludeProject查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccIncludeProjectQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long technocracyId;
    private Long projectId;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String remarks;
    private LocalDateTime selectedTime;


}
