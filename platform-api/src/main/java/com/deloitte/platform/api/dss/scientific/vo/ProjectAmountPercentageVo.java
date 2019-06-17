package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研项目创新工程下项目小类预算金额比例Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAmountPercentageVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目类别CODE")
    private String projectType;

    @ApiModelProperty(value = "金额")
    private Double amount;

}
