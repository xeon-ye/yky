package com.deloitte.platform.api.hr.staffAllotment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :  HrAccount查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrAccountQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String password;
    private String type;
    private String email;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String staffCode;
    private String orgCode;

}
