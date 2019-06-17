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
 * @Date : Create in 2019-04-11
 * @Description : FinanceProjectItems返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceProjectItemsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "项目名称")
    private String proName;

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
