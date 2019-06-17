package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  DeputyAccountRole查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeputyAccountRoleQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long deputyAccountId;
    private Long roleId;

}
