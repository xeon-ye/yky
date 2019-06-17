package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description : GccHighLevelPenson返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccHighLevelPensonUnitVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "入选年份")
    private String choiceYear;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "人数")
    private String total;


}
