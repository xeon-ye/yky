package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  FlowStation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowStationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long pid;
    private String stationCode;
    private String stationName;
    private Integer type;
    private String stationPreside;
    private Long stationExpirePresideId;
    private Integer status;
    private Integer isDelete;
    private Integer inside;
    private String describe;
    private String discussMajor;
    private LocalDate effectiveDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updateBy;
    private Long createBy;
    private String orgCode;

}
