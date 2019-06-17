package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  AuditingHistory查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditingHistoryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long applyUserId;
    private Long auditingUserId;
    private Long businessId;
    private String auditingBusinessItem;
    private LocalDateTime auditingTime;
    private String auditingResult;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long cretaeBy;
    private Long updateBy;
    private String orgCode;

}
