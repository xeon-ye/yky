package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结题项目经费展示Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcludeProjectFundVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "预算金额")
    @Builder.Default
    private Double amount = 0.0;

    @ApiModelProperty(value = "到位经费")
    @Builder.Default
    private Double receive = 0.0;

}
