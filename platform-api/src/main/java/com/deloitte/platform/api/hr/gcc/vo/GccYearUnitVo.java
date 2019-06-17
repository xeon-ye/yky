package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description : GccHighLevelPenson返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccYearUnitVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表头")
    private List<String> head;

    @ApiModelProperty(value = "内容")
    private List<GccYearUnitDetailVo> gccYearUnitDetailVos;

}
