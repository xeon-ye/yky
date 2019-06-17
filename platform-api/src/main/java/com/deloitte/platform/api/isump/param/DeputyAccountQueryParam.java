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
 * @Description :  DeputyAccount查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeputyAccountQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long orgId;
    private String name;
    private Long sort;
    private Long orgSort;
    private String state;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private String remark;
    private String reserve;
    private String version;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String parentEmpNo;

}
