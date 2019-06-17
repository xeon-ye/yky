package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研项目各单位人均情况Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptProjectPersonVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位Id")
    private Long deptId;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "申请数量")
    private Integer applyNum;

    @ApiModelProperty(value = "申请金额")
    private Double applyFunds;

    @ApiModelProperty(value = "单位参与人数")
    private Integer personNum;
}
