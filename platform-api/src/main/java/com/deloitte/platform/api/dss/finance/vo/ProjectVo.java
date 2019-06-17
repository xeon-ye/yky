package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-05-06
 * @Description : 统一接受数据
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVo extends BaseVo {


    @ApiModelProperty(value = "机构编码")
    private String comCode;

    @ApiModelProperty(value = "机构名称")
    private String comDes;

    @ApiModelProperty(value = "当年")
    private BigDecimal ytdT0;

    @ApiModelProperty(value = "后一年")
    private BigDecimal ytdT1;

    @ApiModelProperty(value = "后两年")
    private BigDecimal ytdT2;

    @ApiModelProperty(value = "后三年")
    private BigDecimal ytdT3;

    @ApiModelProperty(value = "指标编码")
    private String indexCode;

    @ApiModelProperty(value = "指标名称")
    private String indexDes;

}
