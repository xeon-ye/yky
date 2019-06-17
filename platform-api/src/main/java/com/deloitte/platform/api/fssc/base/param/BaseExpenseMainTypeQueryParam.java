package com.deloitte.platform.api.fssc.base.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hjy
 * @Date : Create in 2019-02-27
 * @Description :  BaseExpenseMainType查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseExpenseMainTypeQueryParam extends BaseParam {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String code;
    private String name;
    private String parentFlag;
    private String parentCode;
    private String invalidFlag;
    private LocalDateTime validDate;
    private LocalDateTime invalidDate;
    private LocalDateTime createTime;
    private String createBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private Long budgetAccountId;

}
