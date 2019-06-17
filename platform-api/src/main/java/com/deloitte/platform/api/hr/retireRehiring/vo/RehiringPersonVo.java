package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetireRehiringPerson返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RehiringPersonVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "返聘部门")
    private Long rehiringDept;

    @ApiModelProperty(value = "返聘岗位")
    private Long rehiringPost;

    @ApiModelProperty(value = "返聘开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "返聘结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

}
