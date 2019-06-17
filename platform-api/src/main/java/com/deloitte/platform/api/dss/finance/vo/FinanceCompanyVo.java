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
 * @Date : Create in 2019-04-09
 * @Description : FinanceCompany返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceCompanyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private String id;

    @ApiModelProperty(value = "组织编码")
    private Integer comCode;

    @ApiModelProperty(value = "组织名称")
    private String comDes;

    @ApiModelProperty(value = "行政层级")
    private String comLev;

    @ApiModelProperty(value = "业务类型")
    private String comBus;

    @ApiModelProperty(value = "${field.comment}")
    private String ex1;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

}
