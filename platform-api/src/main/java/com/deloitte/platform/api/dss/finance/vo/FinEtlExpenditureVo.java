package com.deloitte.platform.api.dss.finance.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-22
 * @Description : FinEtlExpenditure返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinEtlExpenditureVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private String id;

    @ApiModelProperty(value = "期间")
    private String period;

    @ApiModelProperty(value = "机构")
    private String entity;

    @ApiModelProperty(value = "项目")
    private String item;

    @ApiModelProperty(value = "金额类型")
    private String toa;

    @ApiModelProperty(value = "金额")
    private Double tc;

    @ApiModelProperty(value = "${field.comment}")
    private String ex1;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

    @ApiModelProperty(value = "${field.comment}")
    private String ex5;

}
