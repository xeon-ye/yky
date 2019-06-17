package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-24
 * @Description :  HrZpcpSalaryWelfare查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrZpcpSalaryWelfareQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empCode;
    private String infoId;
    private String post;
    private String salarySystem;
    private Long salaryYear;
    private Long enclosure;
    private String remark;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private Double settle;
    private String tenureService;

}
