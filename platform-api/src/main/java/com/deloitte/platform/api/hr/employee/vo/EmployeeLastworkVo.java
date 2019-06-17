package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description : EmployeeLastwork返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLastworkVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "${field.comment}")
    private Long empId;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "${field.comment}")
    private String company;

    @ApiModelProperty(value = "${field.comment}")
    private String dep;

    @ApiModelProperty(value = "${field.comment}")
    private String position;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateTime;

}
