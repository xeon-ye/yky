package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :  RetireRemindRecord查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemindRecordQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String name;
    private String gender;
    private Integer age;
    private String positionCode;
    private String positionName;
    private String organizationCode;
    private String organizationName;
    private LocalDateTime retireDate;
    private LocalDateTime rebateData;
    private LocalDateTime createTime;
    private String createBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private Double remindId;

}
