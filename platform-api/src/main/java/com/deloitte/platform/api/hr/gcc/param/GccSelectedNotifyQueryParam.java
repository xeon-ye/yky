package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccSelectedNotify查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccSelectedNotifyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String declarationProjectNotifyId;
    private String declarationNumber;
    private String personnelProject;
    private String personnelProjectStatus;
    private String personnelProjectType;
    private String gainTitle;
    private String dispatchNumber;
    private String grantUnit;
    private LocalDateTime grantTime;
    private Long validityTime;
    private String remarks;
    private String fileId;
    private String notifyContent;
    private String sender;
    private LocalDateTime sendTime;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
