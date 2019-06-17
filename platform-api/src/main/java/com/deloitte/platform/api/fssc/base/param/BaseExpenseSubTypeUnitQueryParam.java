package com.deloitte.platform.api.fssc.base.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description :  BaseExpenseSubTypeUnit查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseExpenseSubTypeUnitQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long orgUnitId;
    private Long expenseSubTypeId;
    private LocalDateTime createTime;
    private String createBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgUnitName;
    private String validFlag;
    private String orgUnitCode;

}
