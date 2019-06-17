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
 * @Description :  GccSpecialistGroup查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccSpecialistGroupQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String groupName;
    private Long reviewProject;
    private String reviewYear;
    private String reviewUnit;
    private String status;
    private String remarks;
    private String type;
    private String classify;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;

}
