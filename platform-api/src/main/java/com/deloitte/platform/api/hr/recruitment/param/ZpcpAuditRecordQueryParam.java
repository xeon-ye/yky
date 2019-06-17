package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-26
 * @Description :  ZpcpAuditRecord查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZpcpAuditRecordQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long declareId;
    private String checkStatus;
    private String auditOpinion;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String createBy;
    private String updateBy;
    private String organizationCode;
    private String remarks;

}
