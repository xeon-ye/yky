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
 * @Date : Create in 2019-04-17
 * @Description :  VacationtypeExplain查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationtypeExplainQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String vacationType;
    private String vacationExplain;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String updateBy;
    private String careteBy;

}
