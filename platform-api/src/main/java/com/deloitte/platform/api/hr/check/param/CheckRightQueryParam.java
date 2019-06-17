package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :  CheckRight查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRightQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userId;
    private String checkOrgId;
    private String departId;
    private String remark;
    private String workDescription;
    private String checkWorkId;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
