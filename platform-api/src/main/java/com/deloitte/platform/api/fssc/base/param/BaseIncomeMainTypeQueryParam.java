package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  收入大类询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseIncomeMainTypeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String incomeMainTypeId;
    private String orgUnitId;
    private String orgUnitName;
    private Double validFlag;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateBy;
    private String updateTime;

}
