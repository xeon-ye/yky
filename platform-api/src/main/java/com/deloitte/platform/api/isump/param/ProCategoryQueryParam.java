package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :  ProCategory查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProCategoryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String code;
    private String name;
    private String parentCode;
    private String remark;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String sysType;

}
