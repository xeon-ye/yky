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
 * @Date : Create in 2019-04-17
 * @Description : FinEtl返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinEtlVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private String id;

    @ApiModelProperty(value = "指标")
    private Double indexCode;

    @ApiModelProperty(value = "期间")
    private Double periodCode;

    @ApiModelProperty(value = "部门编号")
    private Double comCode;

    @ApiModelProperty(value = "${field.comment}")
    private Double spare1;

    @ApiModelProperty(value = "${field.comment}")
    private Double spare2;

    @ApiModelProperty(value = "${field.comment}")
    private Double spare3;

    @ApiModelProperty(value = "月发生")
    private Double ptd;

    @ApiModelProperty(value = "月发生-分子")
    private Double ptdN;

    @ApiModelProperty(value = "月发生-分母")
    private Double ptdD;

    @ApiModelProperty(value = "年累计")
    private Double ytd;

    @ApiModelProperty(value = "年累计-分子")
    private Double ytdN;

    @ApiModelProperty(value = "年累计-分母")
    private Double ytdD;

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

    @ApiModelProperty(value = "${field.comment}")
    private String ex6;

    @ApiModelProperty(value = "${field.comment}")
    private String ex7;

    @ApiModelProperty(value = "${field.comment}")
    private String ex8;

    @ApiModelProperty(value = "${field.comment}")
    private String ex9;

    @ApiModelProperty(value = "${field.comment}")
    private String ex10;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
