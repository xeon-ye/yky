package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundViewAuditVo extends BaseVo{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类别")
    private String proType;
    @ApiModelProperty(value = "高层次人才数")
    private Long highTalent;
    @ApiModelProperty(value = "高级人才数")
    private Long highNum;
    @ApiModelProperty(value = "总人数")
    private Long menberNum;
    @ApiModelProperty(value = "博士后人数")
    private Long categroyNum;

    @ApiModelProperty(value = "到位经费")
    private Double reciveFunds;
    @ApiModelProperty(value = "支出经费")
    private Double payFunds;
    @ApiModelProperty(value = "项目数")
    private Long proNum;

    @ApiModelProperty(value = "提交成果项目数量")
    private Long proResultNum;
}
