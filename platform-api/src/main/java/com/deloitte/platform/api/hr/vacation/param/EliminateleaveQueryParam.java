package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-01
 * @Description :  Eliminateleave查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EliminateleaveQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String vacationTrainId;
    private String state;
    private String eliminateleaveIllu;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;

}
