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
public class DeptFundVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目Id")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "所获经费")
    @Builder.Default
    private double deptAmount = 0L;

    @ApiModelProperty(value = "实际支出")
    @Builder.Default
    private double deptPay = 0L;

}
