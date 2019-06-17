package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  BaseIncomeSubType查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseIncomeSubTypeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String code;
    private String name;
    private Long incomeMainTypeId;
    private Long accountId;
    private String validFlag;
    private LocalDateTime validDate;
    private LocalDateTime invalidDate;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateBy;
    private String updateTime;

}
