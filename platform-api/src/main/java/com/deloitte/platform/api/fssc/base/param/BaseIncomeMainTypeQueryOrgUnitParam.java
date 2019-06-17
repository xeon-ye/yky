package com.deloitte.platform.api.fssc.base.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  收入大类询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseIncomeMainTypeQueryOrgUnitParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long incomeMainTypeId;
    private Long orgUnitId;
    private String orgUnitCode;
    private String orgUnitName;
    private String validFlag;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateBy;
    private String updateTime;

}
