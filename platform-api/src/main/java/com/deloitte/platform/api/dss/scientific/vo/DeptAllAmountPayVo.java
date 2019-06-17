package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 单位项目经费展示Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptAllAmountPayVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    private String deptID;


    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "预算经费")
    @Builder.Default
    private Double budgetAmout = 0.0;

    @ApiModelProperty(value = "到位经费")
    @Builder.Default
    private Double reciveAmount = 0.0;

    @ApiModelProperty(value = "支出经费")
    @Builder.Default
    private Double payAmount = 0.0;

}
