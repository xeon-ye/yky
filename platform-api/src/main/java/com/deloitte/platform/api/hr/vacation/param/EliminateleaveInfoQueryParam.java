package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-13
 * @Description :  EliminateleaveInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EliminateleaveInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long vacationdays;
    private String vacationtype;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String eliId;

}
