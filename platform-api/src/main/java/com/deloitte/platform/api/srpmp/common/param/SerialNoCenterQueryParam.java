package com.deloitte.platform.api.srpmp.common.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-03-04
 * @Description :  SerialNoCenter查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerialNoCenterQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String serialType;
    private String serialHeader;
    private Long serialNo;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
