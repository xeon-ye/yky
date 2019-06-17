package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchNotify查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAchNotifyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkRelationId;
    private String checkWorkId;
    private String checkTimeId;
    private String checkOrgId;
    private String checkAchTempateId;
    private String achNotifyName;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
