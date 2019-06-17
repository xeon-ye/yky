package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpWorkgroup查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrZpcpWorkgroupQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String employYear;
    private String groupName;
    private String unit;
    private String operator;
    private String remarks;
    private String groupType;
    private String status;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String authorizedOperator;

}
