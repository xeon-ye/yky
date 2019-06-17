package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jetvae
 * @Date : Create in 2019-05-14
 * @Description :  FlowExpire查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowExpireQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long stationId;
    private String userCode;
    private Integer isValid;
    private LocalDateTime effectiveDate;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;

}
